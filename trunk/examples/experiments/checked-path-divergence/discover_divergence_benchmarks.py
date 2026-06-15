#!/usr/bin/env python3
"""Discover benchmarks that are likely to distinguish PAPER and LCPS."""

import argparse
import csv
import hashlib
import re
from pathlib import Path
from types import SimpleNamespace

from run_checked_path_divergence_experiment import (
    Benchmark,
    DEFAULT_ULTIMATE_CMD,
    REPO_ROOT,
    run_one,
)


DEFAULT_OUTPUT_DIR = Path(__file__).resolve().parent / "results" / "discovery"
DISCOVERY_COLUMNS = [
    "name",
    "input_file",
    "toolchain",
    "settings",
    "rationale",
    "result",
    "runtime_ms",
    "checked_paths",
    "stale_paths",
    "duplicate_freshness_failures",
    "refinements",
    "search_failed",
    "avg_pairwise_prefix_lca_divergence",
    "score",
    "recommended",
]
BENCHMARK_FILE_COLUMNS = ["name", "input_file", "toolchain", "settings", "rationale"]


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--ultimate-cmd", default=DEFAULT_ULTIMATE_CMD)
    parser.add_argument("--output-dir", type=Path, default=DEFAULT_OUTPUT_DIR)
    parser.add_argument("--threads", type=int, default=8)
    parser.add_argument("--timeout", type=int, default=120)
    parser.add_argument("--max-candidates", type=int, default=200)
    parser.add_argument("--dry-run", action="store_true")
    parser.add_argument("--include-c", action="store_true", help="Also include C candidates with inferred settings.")
    return parser.parse_args()


def repo_relative(path: Path) -> str:
    try:
        return str(path.resolve().relative_to(REPO_ROOT.resolve()))
    except ValueError:
        return str(path)


def unique_paths(paths: list[Path]) -> list[Path]:
    seen = set()
    result = []
    for path in paths:
        resolved = path.resolve()
        if resolved in seen or not path.is_file():
            continue
        seen.add(resolved)
        result.append(path)
    return result


def candidate_paths(include_c: bool) -> list[Path]:
    bpl_patterns = [
        REPO_ROOT / "trunk/examples/programs",
        REPO_ROOT / "trunk/examples/concurrent",
        REPO_ROOT / "trunk/examples/svcomp",
        REPO_ROOT / "trunk/examples/termination",
        REPO_ROOT / "trunk/examples/regression",
    ]
    paths: list[Path] = []
    for root in bpl_patterns:
        if root.exists():
            paths.extend(sorted(root.rglob("*.bpl")))

    if include_c:
        c_roots = [
            REPO_ROOT / "trunk/examples/svcomp",
            REPO_ROOT / "trunk/examples/programs",
            REPO_ROOT / "trunk/examples",
            REPO_ROOT / "trunk/examples/termination",
            REPO_ROOT / "trunk/examples/regression",
        ]
        for root in c_roots:
            if root.exists():
                paths.extend(sorted(root.rglob("*.c")))
    return unique_paths(paths)


def infer_toolchain_settings(path: Path) -> tuple[Path, Path] | None:
    path_text = str(path).lower()
    if path.suffix == ".bpl":
        if "concurrent" in path_text:
            toolchain = REPO_ROOT / "trunk/examples/concurrent/bpl/regression/ReachSafety.xml"
            settings = REPO_ROOT / "trunk/examples/concurrent/bpl/regression/ReachSafety-32bit-Automizer.epf"
            if toolchain.exists() and settings.exists():
                return toolchain, settings
        return (
            REPO_ROOT / "trunk/examples/toolchains/AutomizerBpl.xml",
            REPO_ROOT / "trunk/examples/Interactive/settings/ResetSettingsCamel.epf",
        )

    if path.suffix == ".c":
        if "concurrent" in path_text or "pthread" in path_text:
            toolchain = REPO_ROOT / "trunk/examples/concurrent/pthreads/regression/ReachSafety.xml"
            settings = REPO_ROOT / "trunk/examples/concurrent/pthreads/regression/ReachSafety-32bit-Automizer.epf"
            if toolchain.exists() and settings.exists():
                return toolchain, settings
        toolchain = REPO_ROOT / "trunk/examples/toolchains/AutomizerC.xml"
        settings = REPO_ROOT / "trunk/examples/Interactive/settings/SVCOMP2017/svcomp-Reach-32bit-Automizer_Default.epf"
        if toolchain.exists() and settings.exists():
            return toolchain, settings
    return None


def safe_name(path: Path) -> str:
    rel = repo_relative(path)
    stem = re.sub(r"[^A-Za-z0-9_.-]+", "-", Path(rel).with_suffix("").as_posix()).strip("-")
    digest = hashlib.sha1(rel.encode("utf-8")).hexdigest()[:8]
    if len(stem) > 80:
        stem = stem[-80:]
    return f"{stem}-{digest}"


def make_benchmark(path: Path) -> Benchmark | None:
    inferred = infer_toolchain_settings(path)
    if inferred is None:
        return None
    toolchain, settings = inferred
    suffix = "concurrent " if "concurrent" in str(path).lower() else ""
    rationale = f"Discovered {suffix}{path.suffix} candidate from {repo_relative(path)}."
    return Benchmark(safe_name(path), path, toolchain, settings, rationale)


def int_value(row: dict[str, str], key: str) -> int:
    try:
        return int(float(row.get(key, "0")))
    except ValueError:
        return 0


def score(row: dict[str, str]) -> int:
    return (
        5 * int_value(row, "stale_paths")
        + 2 * int_value(row, "duplicate_freshness_failures")
        + int_value(row, "checked_paths")
        + int_value(row, "refinements")
    )


def is_recommended(row: dict[str, str]) -> bool:
    result = row.get("result", "")
    runtime_ms = int_value(row, "runtime_ms")
    interesting_work = (
        int_value(row, "checked_paths") >= 20
        or int_value(row, "stale_paths") >= 2
        or int_value(row, "duplicate_freshness_failures") >= 2
        or int_value(row, "refinements") >= 10
    )
    return result in {"SAFE", "UNKNOWN"} and 5_000 <= runtime_ms <= 180_000 and interesting_work


def benchmark_csv_row(row: dict[str, str]) -> dict[str, str]:
    return {key: row[key] for key in BENCHMARK_FILE_COLUMNS}


def write_csv(path: Path, rows: list[dict[str, str]], columns: list[str]) -> None:
    with path.open("w", newline="", encoding="utf-8") as csv_file:
        writer = csv.DictWriter(csv_file, fieldnames=columns, lineterminator="\n", extrasaction="ignore")
        writer.writeheader()
        writer.writerows(rows)


def write_top_markdown(path: Path, rows: list[dict[str, str]]) -> None:
    lines = [
        "# Discovered Top 10 Benchmarks",
        "",
        "| rank | benchmark path | result | runtime_ms | checked_paths | stale_paths | duplicate freshness failures | refinements | score |",
        "|---:|---|---|---:|---:|---:|---:|---:|---:|",
    ]
    for rank, row in enumerate(rows, start=1):
        lines.append(
            "| {rank} | `{input_file}` | {result} | {runtime_ms} | {checked_paths} | {stale_paths} | "
            "{duplicate_freshness_failures} | {refinements} | {score} |".format(rank=rank, **row)
        )
    lines.append("")
    path.write_text("\n".join(lines), encoding="utf-8")


def main() -> None:
    args = parse_args()
    args.output_dir.mkdir(parents=True, exist_ok=True)
    raw_log_dir = args.output_dir / "raw"
    raw_log_dir.mkdir(parents=True, exist_ok=True)

    benchmarks = []
    for path in candidate_paths(args.include_c):
        benchmark = make_benchmark(path)
        if benchmark is not None:
            benchmarks.append(benchmark)
        if len(benchmarks) >= args.max_candidates:
            break

    runner_args = SimpleNamespace(
        ultimate_cmd=args.ultimate_cmd,
        output_dir=raw_log_dir,
        timeout=args.timeout,
        dry_run=args.dry_run,
        extra_arg=[],
    )

    rows = []
    for index, benchmark in enumerate(benchmarks, start=1):
        print(f"[{index}/{len(benchmarks)}] {repo_relative(benchmark.input_file)}", flush=True)
        experiment_row = run_one(runner_args, benchmark, "PAPER", args.threads, True)
        row = {
            **benchmark_csv_row({
                "name": benchmark.name,
                "input_file": repo_relative(benchmark.input_file),
                "toolchain": repo_relative(benchmark.toolchain),
                "settings": repo_relative(benchmark.settings),
                "rationale": benchmark.rationale,
            }),
            "result": experiment_row["result"],
            "runtime_ms": experiment_row["runtime_ms"],
            "checked_paths": experiment_row["checked_paths"],
            "stale_paths": experiment_row["stale_paths"],
            "duplicate_freshness_failures": experiment_row["duplicate_freshness_failures"],
            "refinements": experiment_row["refinements"],
            "search_failed": experiment_row["search_failed"],
            "avg_pairwise_prefix_lca_divergence": experiment_row["avg_pairwise_prefix_lca_divergence"],
        }
        row["score"] = str(score(row))
        row["recommended"] = "true" if is_recommended(row) else "false"
        rows.append(row)

    discovered_csv = args.output_dir / "discovered-benchmarks.csv"
    write_csv(discovered_csv, rows, DISCOVERY_COLUMNS)

    recommended = [row for row in rows if row["recommended"] == "true"]
    fallback = [row for row in rows if not row["result"].startswith("ERROR")]
    selected = sorted(recommended, key=lambda row: int_value(row, "score"), reverse=True)
    if len(selected) < 10:
        selected_names = {row["name"] for row in selected}
        selected.extend(
            row for row in sorted(fallback, key=lambda item: int_value(item, "score"), reverse=True)
            if row["name"] not in selected_names
        )
    top10 = selected[:10]
    top3 = selected[:3]

    write_top_markdown(args.output_dir / "discovered-top10.md", top10)
    write_csv(args.output_dir / "discovered-top10-benchmarks.csv",
            [benchmark_csv_row(row) for row in top10], BENCHMARK_FILE_COLUMNS)
    write_csv(args.output_dir / "discovered-top3-benchmarks.csv",
            [benchmark_csv_row(row) for row in top3], BENCHMARK_FILE_COLUMNS)

    print(f"Wrote {discovered_csv}")
    print(f"Wrote {args.output_dir / 'discovered-top10.md'}")
    print(f"Wrote {args.output_dir / 'discovered-top10-benchmarks.csv'}")
    print(f"Wrote {args.output_dir / 'discovered-top3-benchmarks.csv'}")


if __name__ == "__main__":
    main()
