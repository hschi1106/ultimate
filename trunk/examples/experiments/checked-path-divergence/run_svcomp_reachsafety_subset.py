#!/usr/bin/env python3
"""Run a selected SV-COMP ReachSafety subset through the checked-path divergence runner."""

from __future__ import annotations

import argparse
import csv
import hashlib
import json
import os
import re
import shlex
import statistics
import subprocess
import sys
from dataclasses import dataclass
from pathlib import Path
from typing import Any


REPO_ROOT = Path(__file__).resolve().parents[4]
EXPERIMENT_DIR = Path(__file__).resolve().parent
RUNNER = EXPERIMENT_DIR / "run_checked_path_divergence_experiment.py"
DEFAULT_BENCHMARKS_JSON = REPO_ROOT / "benchmarks_run.json"
DEFAULT_OUTPUT_DIR = EXPERIMENT_DIR / "results/svcomp-reachsafety-subset"
TOOLCHAIN = REPO_ROOT / "trunk/examples/toolchains/AutomizerC.xml"
SETTINGS = REPO_ROOT / "trunk/examples/Interactive/settings/SVCOMP2017/svcomp-Reach-32bit-Automizer_Default.epf"
DEFAULT_MODES = "PAPER,LCPS,BATCH_LCPS,ADAPTIVE_BATCH_LCPS"
REQUIRED_DIVERGENCE_COLUMNS = {
    "total_pairwise_prefix_lca_divergence",
    "avg_pairwise_prefix_lca_divergence",
}


@dataclass(frozen=True)
class TaskMetadata:
    benchmark_name: str
    category: str
    subcategory: str
    task: str
    input_file: Path
    expected_result: str
    rationale: str


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--benchmarks-json", type=Path, default=DEFAULT_BENCHMARKS_JSON)
    parser.add_argument("--svcomp-root", type=Path)
    parser.add_argument("--ultimate-cmd", type=Path)
    parser.add_argument("--output-dir", type=Path, default=DEFAULT_OUTPUT_DIR)
    parser.add_argument("--modes", default=DEFAULT_MODES)
    parser.add_argument("--threads", default="4")
    parser.add_argument("--timeout", type=int, default=150)
    parser.add_argument("--repeat", type=int, default=1)
    parser.add_argument("--jobs", type=int, default=1)
    parser.add_argument("--dry-run", action="store_true")
    return parser.parse_args()


def resolve_svcomp_root(arg: Path | None) -> Path:
    candidates: list[Path] = []
    if arg is not None:
        candidates.append(arg)
    else:
        candidates.extend([
            REPO_ROOT.parent / "sv-benchmarks",
            REPO_ROOT.parent.parent / "sv-benchmarks",
        ])
        env_root = os.environ.get("SVCOMP_ROOT")
        if env_root:
            candidates.append(Path(env_root))

    checked = []
    for candidate in candidates:
        resolved = candidate.expanduser().resolve()
        checked.append(str(resolved))
        if (resolved / "c").is_dir():
            return resolved
    raise SystemExit(
        "Could not find SV-COMP root with a c/ directory. Checked:\n  " + "\n  ".join(checked)
    )


def resolve_ultimate_cmd(arg: Path | None) -> Path:
    if arg is not None:
        return arg.expanduser().resolve()
    env_cmd = os.environ.get("ULTIMATE_CMD")
    if env_cmd:
        return Path(env_cmd).expanduser().resolve()
    return REPO_ROOT / "releaseScripts/default/adds/run-ultimate.sh"


def safe_benchmark_name(category: str, subcategory: str, task: str) -> str:
    base = f"{category}-{subcategory}-{task}"
    safe = re.sub(r"[^A-Za-z0-9_-]+", "-", base)
    safe = re.sub(r"-+", "-", safe).strip("-").lower()
    digest = hashlib.sha1(base.encode("utf-8")).hexdigest()[:8]
    if len(safe) > 140:
        safe = safe[:140].rstrip("-")
    return f"{safe}-{digest}"


def task_expected_result(task_entry: Any) -> str:
    if isinstance(task_entry, dict):
        for key in ("expected_result", "expected", "status", "result"):
            value = task_entry.get(key)
            if value:
                return str(value)
    return "UNKNOWN_EXPECTED"


def task_path(task_entry: Any) -> str:
    if isinstance(task_entry, str):
        return task_entry
    if isinstance(task_entry, dict):
        for key in ("task", "path", "file", "input_file"):
            value = task_entry.get(key)
            if value:
                return str(value)
    raise ValueError(f"Unsupported task entry: {task_entry!r}")


def load_tasks(benchmarks_json: Path, svcomp_root: Path) -> list[TaskMetadata]:
    with benchmarks_json.open(encoding="utf-8") as handle:
        data = json.load(handle)
    categories = data.get("categories")
    if not isinstance(categories, dict):
        raise ValueError(f"{benchmarks_json} does not contain a categories object")

    tasks: list[TaskMetadata] = []
    for category, category_data in categories.items():
        subcategories = category_data.get("subcategories", {}) if isinstance(category_data, dict) else {}
        if not isinstance(subcategories, dict):
            continue
        for subcategory, subcategory_data in subcategories.items():
            task_entries = subcategory_data.get("tasks", []) if isinstance(subcategory_data, dict) else []
            expected_true = subcategory_data.get("expected_true", "n/a")
            expected_false = subcategory_data.get("expected_false", "n/a")
            for entry in task_entries:
                task = task_path(entry)
                input_file = (svcomp_root / "c" / task).resolve()
                expected = task_expected_result(entry)
                name = safe_benchmark_name(category, subcategory, task)
                rationale = (
                    f"category={category}; subcategory={subcategory}; "
                    f"subcategory_expected_true={expected_true}; subcategory_expected_false={expected_false}"
                )
                tasks.append(TaskMetadata(name, category, subcategory, task, input_file, expected, rationale))
    if not tasks:
        raise ValueError(f"{benchmarks_json} did not contain any tasks")
    return tasks


def check_input_files(tasks: list[TaskMetadata]) -> None:
    missing = [task for task in tasks if not task.input_file.is_file()]
    if not missing:
        return
    lines = [f"Missing {len(missing)} SV-COMP input file(s):"]
    lines.extend(f"  {task.task} -> {task.input_file}" for task in missing[:100])
    if len(missing) > 100:
        lines.append(f"  ... {len(missing) - 100} more")
    raise SystemExit("\n".join(lines))


def write_benchmark_csv(tasks: list[TaskMetadata], output_dir: Path) -> Path:
    generated_dir = output_dir / "generated"
    generated_dir.mkdir(parents=True, exist_ok=True)
    csv_path = generated_dir / "reachsafety-subset-benchmarks.csv"
    with csv_path.open("w", newline="", encoding="utf-8") as handle:
        writer = csv.DictWriter(handle, fieldnames=["name", "input_file", "toolchain", "settings", "rationale"])
        writer.writeheader()
        for task in tasks:
            writer.writerow({
                "name": task.benchmark_name,
                "input_file": str(task.input_file),
                "toolchain": str(TOOLCHAIN),
                "settings": str(SETTINGS),
                "rationale": task.rationale,
            })
    return csv_path


def runner_command(args: argparse.Namespace, benchmark_csv: Path, ultimate_cmd: Path) -> list[str]:
    return [
        sys.executable,
        str(RUNNER),
        "--ultimate-cmd",
        str(ultimate_cmd),
        "--benchmark-file",
        str(benchmark_csv),
        "--modes",
        args.modes,
        "--threads",
        str(args.threads),
        "--repeat",
        str(args.repeat),
        "--jobs",
        str(args.jobs),
        "--timeout",
        str(args.timeout),
        "--output-dir",
        str(args.output_dir),
    ]


def read_runner_results(output_dir: Path) -> list[dict[str, str]]:
    results_csv = output_dir / "checked-path-divergence-results.csv"
    if not results_csv.is_file():
        raise FileNotFoundError(f"Runner output CSV not found: {results_csv}")
    with results_csv.open(newline="", encoding="utf-8") as handle:
        reader = csv.DictReader(handle)
        missing = REQUIRED_DIVERGENCE_COLUMNS - set(reader.fieldnames or [])
        if missing:
            raise ValueError(
                f"{results_csv} is missing required divergence column(s): {', '.join(sorted(missing))}"
            )
        rows = list(reader)
    if not rows:
        raise ValueError(f"{results_csv} did not contain any result rows")
    validate_average_divergence(rows, results_csv)
    return rows


def validate_average_divergence(rows: list[dict[str, str]], results_csv: Path) -> None:
    bad_rows = []
    for row in rows:
        value_text = row.get("avg_pairwise_prefix_lca_divergence", "")
        try:
            value = float(value_text)
        except ValueError:
            bad_rows.append((row.get("benchmark", "?"), value_text))
            continue
        if value < 0.0 or value > 1.0:
            bad_rows.append((row.get("benchmark", "?"), value_text))
    if bad_rows:
        sample = ", ".join(f"{benchmark}={value}" for benchmark, value in bad_rows[:20])
        print(
            f"Warning: {results_csv} contains {len(bad_rows)} "
            f"avg_pairwise_prefix_lca_divergence value(s) outside [0,1]: {sample}",
            file=sys.stderr,
        )


def metadata_by_benchmark(tasks: list[TaskMetadata]) -> dict[str, TaskMetadata]:
    return {task.benchmark_name: task for task in tasks}


def write_enriched_csv(output_dir: Path, rows: list[dict[str, str]], tasks: list[TaskMetadata]) -> Path:
    enriched_csv = output_dir / "checked-path-divergence-results.enriched.csv"
    metadata = metadata_by_benchmark(tasks)
    extra_columns = ["category", "subcategory", "task", "expected_result"]
    fieldnames = list(rows[0].keys()) + [column for column in extra_columns if column not in rows[0]]
    with enriched_csv.open("w", newline="", encoding="utf-8") as handle:
        writer = csv.DictWriter(handle, fieldnames=fieldnames)
        writer.writeheader()
        for row in rows:
            task = metadata.get(row["benchmark"])
            enriched = dict(row)
            enriched["category"] = task.category if task else ""
            enriched["subcategory"] = task.subcategory if task else ""
            enriched["task"] = task.task if task else ""
            enriched["expected_result"] = task.expected_result if task else "UNKNOWN_EXPECTED"
            writer.writerow(enriched)
    return enriched_csv


def numeric(row: dict[str, str], key: str) -> float:
    try:
        return float(row.get(key, "0") or "0")
    except ValueError:
        return 0.0


def result_bucket(result: str) -> str:
    if result.startswith("ERROR"):
        return "ERROR"
    if result in {"SAFE", "UNSAFE", "UNKNOWN", "TIMEOUT"}:
        return result
    return "UNKNOWN"


def group_key(row: dict[str, str]) -> tuple[str, str, str]:
    return row["benchmark"], row.get("threads", ""), row.get("repeat_index", "0")


def result_mismatch_count(rows: list[dict[str, str]]) -> int:
    grouped: dict[tuple[str, str, str], set[str]] = {}
    for row in rows:
        grouped.setdefault(group_key(row), set()).add(row["result"])
    return sum(1 for results in grouped.values() if len(results) > 1)


def mean(values: list[float]) -> float:
    return sum(values) / len(values) if values else 0.0


def mode_rows(rows: list[dict[str, str]], mode: str) -> list[dict[str, str]]:
    return [row for row in rows if row.get("mode") == mode]


def pair_rows(rows: list[dict[str, str]], base_mode: str, target_mode: str) -> list[tuple[dict[str, str], dict[str, str]]]:
    base = {group_key(row): row for row in rows if row.get("mode") == base_mode}
    target = {group_key(row): row for row in rows if row.get("mode") == target_mode}
    return [(base[key], target[key]) for key in sorted(set(base) & set(target))]


def win_loss_tie(rows: list[dict[str, str]], target_mode: str, metric: str) -> tuple[int, int, int, float, float]:
    deltas = [numeric(target, metric) - numeric(base, metric) for base, target in pair_rows(rows, "PAPER", target_mode)]
    wins = sum(1 for delta in deltas if delta < 0)
    losses = sum(1 for delta in deltas if delta > 0)
    ties = len(deltas) - wins - losses
    return wins, losses, ties, mean(deltas), statistics.median(deltas) if deltas else 0.0


def write_summary(output_dir: Path, rows: list[dict[str, str]], tasks: list[TaskMetadata],
        modes: str, threads: str, timeout: int) -> Path:
    summary_path = output_dir / "svcomp-reachsafety-subset-summary.md"
    mode_list = [mode.strip() for mode in modes.split(",") if mode.strip()]
    lines = [
        "# SV-COMP ReachSafety Subset Summary",
        "",
        f"- Total tasks: {len(tasks)}",
        f"- Modes: `{modes}`",
        f"- Threads: `{threads}`",
        f"- Timeout: {timeout}s",
        f"- Result mismatch groups across modes: {result_mismatch_count(rows)}",
        f"- ERROR count: {sum(1 for row in rows if result_bucket(row['result']) == 'ERROR')}",
        f"- TIMEOUT count: {sum(1 for row in rows if result_bucket(row['result']) == 'TIMEOUT')}",
        f"- UNKNOWN count: {sum(1 for row in rows if result_bucket(row['result']) == 'UNKNOWN')}",
        "",
        "## Per Mode",
        "",
        "| mode | SAFE | UNSAFE | UNKNOWN | TIMEOUT | ERROR | avg runtime_ms | median runtime_ms | avg checked_paths | avg stale_paths | avg avg_pairwise_prefix_lca_divergence |",
        "|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|",
    ]
    for mode in mode_list:
        rows_for_mode = mode_rows(rows, mode)
        buckets = {bucket: 0 for bucket in ["SAFE", "UNSAFE", "UNKNOWN", "TIMEOUT", "ERROR"]}
        for row in rows_for_mode:
            buckets[result_bucket(row["result"])] += 1
        runtimes = [numeric(row, "runtime_ms") for row in rows_for_mode]
        lines.append(
            f"| {mode} | {buckets['SAFE']} | {buckets['UNSAFE']} | {buckets['UNKNOWN']} | "
            f"{buckets['TIMEOUT']} | {buckets['ERROR']} | {mean(runtimes):.2f} | "
            f"{statistics.median(runtimes) if runtimes else 0.0:.2f} | "
            f"{mean([numeric(row, 'checked_paths') for row in rows_for_mode]):.2f} | "
            f"{mean([numeric(row, 'stale_paths') for row in rows_for_mode]):.2f} | "
            f"{mean([numeric(row, 'avg_pairwise_prefix_lca_divergence') for row in rows_for_mode]):.4f} |"
        )

    lines.extend([
        "",
        "## PAPER Comparisons",
        "",
        "| comparison | runtime wins/losses/ties | runtime delta mean/median | checked_paths delta mean/median | stale_paths delta mean/median |",
        "|---|---:|---:|---:|---:|",
    ])
    for target_mode in mode_list:
        if target_mode == "PAPER":
            continue
        runtime = win_loss_tie(rows, target_mode, "runtime_ms")
        checked = win_loss_tie(rows, target_mode, "checked_paths")
        stale = win_loss_tie(rows, target_mode, "stale_paths")
        lines.append(
            f"| PAPER vs {target_mode} | {runtime[0]}/{runtime[1]}/{runtime[2]} | "
            f"{runtime[3]:.2f}/{runtime[4]:.2f} | {checked[3]:.2f}/{checked[4]:.2f} | "
            f"{stale[3]:.2f}/{stale[4]:.2f} |"
        )
    summary_path.write_text("\n".join(lines) + "\n", encoding="utf-8")
    return summary_path


def main() -> None:
    args = parse_args()
    benchmarks_json = args.benchmarks_json.expanduser().resolve()
    if not benchmarks_json.is_file():
        raise SystemExit(f"Benchmark JSON not found: {benchmarks_json}")
    svcomp_root = resolve_svcomp_root(args.svcomp_root)
    ultimate_cmd = resolve_ultimate_cmd(args.ultimate_cmd)
    args.output_dir.mkdir(parents=True, exist_ok=True)

    tasks = load_tasks(benchmarks_json, svcomp_root)
    check_input_files(tasks)
    benchmark_csv = write_benchmark_csv(tasks, args.output_dir)
    command = runner_command(args, benchmark_csv, ultimate_cmd)

    if args.dry_run:
        print(shlex.join(command))
        print(f"Generated benchmark CSV: {benchmark_csv}")
        print(f"Total tasks: {len(tasks)}")
        return

    subprocess.run(command, check=True)
    rows = read_runner_results(args.output_dir)
    enriched_csv = write_enriched_csv(args.output_dir, rows, tasks)
    summary_md = write_summary(args.output_dir, rows, tasks, args.modes, args.threads, args.timeout)
    print(f"Generated benchmark CSV: {benchmark_csv}")
    print(f"Generated enriched CSV: {enriched_csv}")
    print(f"Generated summary: {summary_md}")


if __name__ == "__main__":
    main()
