#!/usr/bin/env python3
"""Run checked-path divergence experiments for Ultimate TraceAbstraction."""

import argparse
import csv
import os
import re
import shlex
import signal
import subprocess
import time
from dataclasses import dataclass
from pathlib import Path


REPO_ROOT = Path(__file__).resolve().parents[4]
DEFAULT_OUTPUT_DIR = Path(__file__).resolve().parent / "results"
DEFAULT_ULTIMATE_CMD = os.environ.get(
    "ULTIMATE_CMD", str(REPO_ROOT / "releaseScripts/default/adds/run-ultimate.sh")
)
DEFAULT_MODES = ["PAPER", "LCPS"]
BASIC_MODES = ["BFS", "DFS"]
ALL_MODES = BASIC_MODES + DEFAULT_MODES

CSV_COLUMNS = [
    "benchmark",
    "mode",
    "threads",
    "result",
    "runtime_ms",
    "checked_paths",
    "stale_paths",
    "duplicate_freshness_failures",
    "total_pairwise_prefix_lca_divergence",
    "avg_pairwise_prefix_lca_divergence",
    "refinements",
    "search_failed",
]

STAT_PATTERNS = {
    "checked_paths": [
        re.compile(r"Checked paths:\s*([0-9]+)", re.IGNORECASE),
        re.compile(r"CheckedPaths:\s*([0-9]+)", re.IGNORECASE),
    ],
    "stale_paths": [
        re.compile(r"Stale paths:\s*([0-9]+)", re.IGNORECASE),
        re.compile(r"StalePaths:\s*([0-9]+)", re.IGNORECASE),
        re.compile(r"Skipped paths:\s*([0-9]+)", re.IGNORECASE),
    ],
    "duplicate_freshness_failures": [
        re.compile(r"Duplicate freshness failures:\s*([0-9]+)", re.IGNORECASE),
        re.compile(r"DuplicateFreshnessFailures:\s*([0-9]+)", re.IGNORECASE),
    ],
    "total_pairwise_prefix_lca_divergence": [
        re.compile(
            r"Total pairwise prefix-LCA divergence:\s*([0-9]+(?:\.[0-9]+)?(?:[eE][+-]?[0-9]+)?)",
            re.IGNORECASE,
        ),
        re.compile(
            r"TotalPairwisePrefixLcaDivergence:\s*([0-9]+(?:\.[0-9]+)?(?:[eE][+-]?[0-9]+)?)",
            re.IGNORECASE,
        ),
    ],
    "avg_pairwise_prefix_lca_divergence": [
        re.compile(
            r"Avg pairwise prefix-LCA divergence:\s*([0-9]+(?:\.[0-9]+)?(?:[eE][+-]?[0-9]+)?)",
            re.IGNORECASE,
        ),
        re.compile(
            r"AvgPairwisePrefixLcaDivergence:\s*([0-9]+(?:\.[0-9]+)?(?:[eE][+-]?[0-9]+)?)",
            re.IGNORECASE,
        ),
    ],
    "refinements": [
        re.compile(r"Refinements:\s*([0-9]+)", re.IGNORECASE),
        re.compile(r"Overall iterations:\s*([0-9]+)", re.IGNORECASE),
        re.compile(r"OverallIterations:\s*([0-9]+)", re.IGNORECASE),
    ],
    "search_failed": [
        re.compile(r"SearchFailed:\s*([0-9]+)", re.IGNORECASE),
        re.compile(r"FailedToFindCounterexamples:\s*([0-9]+)", re.IGNORECASE),
    ],
}


@dataclass(frozen=True)
class Benchmark:
    name: str
    input_file: Path
    toolchain: Path
    settings: Path
    rationale: str


BENCHMARKS = [
    Benchmark(
        "easy-small",
        REPO_ROOT / "trunk/examples/programs/toy/easy.bpl",
        REPO_ROOT / "trunk/examples/toolchains/AutomizerBpl.xml",
        REPO_ROOT / "trunk/examples/Interactive/settings/ResetSettingsCamel.epf",
        "Small Boogie testcase from the toy suite; establishes the low-path-count baseline.",
    ),
    Benchmark(
        "medium-loop",
        REPO_ROOT / "trunk/examples/programs/toy/tooDifficultLoopInvariant/DrAlban02-medium.bpl",
        REPO_ROOT / "trunk/examples/toolchains/AutomizerBpl.xml",
        REPO_ROOT / "trunk/examples/Interactive/settings/ResetSettingsCamel.epf",
        "Medium loop-invariant testcase that usually needs more than the trivial refinement pattern.",
    ),
    Benchmark(
        "hidden-inequality",
        REPO_ROOT / "trunk/examples/programs/toy/tooDifficultLoopInvariant/HiddenInequality.bpl",
        REPO_ROOT / "trunk/examples/toolchains/AutomizerBpl.xml",
        REPO_ROOT / "trunk/examples/Interactive/settings/ResetSettingsCamel.epf",
        "Harder loop-invariant testcase with substantially more checked paths than the baseline cases.",
    ),
    Benchmark(
        "concurrent-fischer",
        REPO_ROOT / "trunk/examples/concurrent/bpl/regression/showcase/Fischer.bpl",
        REPO_ROOT / "trunk/examples/concurrent/bpl/regression/ReachSafety.xml",
        REPO_ROOT / "trunk/examples/concurrent/bpl/regression/ReachSafety-32bit-Automizer.epf",
        "Concurrent showcase testcase for parallel trace-abstraction behavior.",
    ),
]


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument(
        "--ultimate-cmd",
        default=DEFAULT_ULTIMATE_CMD,
        help="Command used to start Ultimate. Can contain spaces; defaults to $ULTIMATE_CMD or the release wrapper.",
    )
    parser.add_argument(
        "--output-dir",
        type=Path,
        default=DEFAULT_OUTPUT_DIR,
        help="Directory for CSV, Markdown report, and raw logs.",
    )
    parser.add_argument("--threads", default="1,2,4,8,16", help="Comma-separated thread counts.")
    parser.add_argument("--include-16", action="store_true", help="Compatibility flag; 16 is included by default.")
    parser.add_argument("--modes", default=",".join(DEFAULT_MODES), help="Comma-separated modes: BFS,DFS,PAPER,LCPS.")
    parser.add_argument("--include-basic", action="store_true", help="Also run BFS and DFS baselines.")
    parser.add_argument("--timeout", type=int, default=900, help="Per-run timeout in seconds.")
    parser.add_argument(
        "--benchmark",
        action="append",
        choices=[benchmark.name for benchmark in BENCHMARKS],
        help="Restrict to one benchmark; can be passed more than once.",
    )
    parser.add_argument(
        "--extra-arg",
        action="append",
        default=[],
        help="Additional Ultimate CLI argument, appended after the experiment overrides.",
    )
    parser.add_argument("--dry-run", action="store_true", help="Print commands without executing them.")
    return parser.parse_args()


def selected_threads(args: argparse.Namespace) -> list[int]:
    threads = [int(thread.strip()) for thread in args.threads.split(",") if thread.strip()]
    if args.include_16 and 16 not in threads:
        threads.append(16)
    return threads


def selected_modes(args: argparse.Namespace) -> list[str]:
    modes = [mode.strip().upper() for mode in args.modes.split(",") if mode.strip()]
    if args.include_basic:
        modes = BASIC_MODES + modes
    result = []
    for mode in modes:
        if mode not in ALL_MODES:
            raise ValueError(f"Unsupported mode {mode!r}; expected one of {','.join(ALL_MODES)}")
        if mode not in result:
            result.append(mode)
    return result


def selected_benchmarks(args: argparse.Namespace) -> list[Benchmark]:
    if not args.benchmark:
        return BENCHMARKS
    selected = set(args.benchmark)
    return [benchmark for benchmark in BENCHMARKS if benchmark.name in selected]


def ultimate_command(args: argparse.Namespace, benchmark: Benchmark, mode: str, threads: int) -> list[str]:
    command = shlex.split(args.ultimate_cmd)
    command.extend(
        [
            "-tc",
            str(benchmark.toolchain),
            "-s",
            str(benchmark.settings),
            "-i",
            str(benchmark.input_file),
            "--traceabstraction.use.cegar.loop.for.parallel.trace.abstraction",
            "true",
            "--traceabstraction.threadlimit.for.parallel.cegar",
            str(threads),
            "--traceabstraction.parallel.trace.search.selection.mode",
            mode,
        ]
    )
    command.extend(args.extra_arg)
    return command


def as_text(output: str | bytes | None) -> str:
    if output is None:
        return ""
    if isinstance(output, bytes):
        return output.decode("utf-8", errors="replace")
    return output


def parse_last(patterns: list[re.Pattern], text: str, default: str = "0") -> str:
    matches = []
    for pattern in patterns:
        matches.extend(pattern.findall(text))
    if not matches:
        return default
    return str(matches[-1])


def parse_result(output: str, returncode: int, timed_out: bool) -> str:
    if timed_out:
        return "TIMEOUT"
    result_matches = re.findall(
        r"(?:^|\])\s*Result:\s*(SAFE|UNSAFE|TIMEOUT|UNKNOWN|USER_LIMIT_[A-Z_]+)", output, re.MULTILINE
    )
    if result_matches:
        return result_matches[-1]
    if "Ultimate proved your program to be correct" in output or "AllSpecificationsHoldResult" in output:
        return "SAFE"
    if "Ultimate proved your program to be incorrect" in output:
        return "UNSAFE"
    if "TimeoutResult" in output:
        return "TIMEOUT"
    if "UnknownResult" in output:
        return "UNKNOWN"
    if "ExceptionOrErrorResult" in output or returncode != 0:
        return f"ERROR_{returncode}"
    return "UNKNOWN"


def empty_row(benchmark: Benchmark, mode: str, threads: int, result: str) -> dict[str, str]:
    return {
        "benchmark": benchmark.name,
        "mode": mode,
        "threads": str(threads),
        "result": result,
        "runtime_ms": "0",
        "checked_paths": "0",
        "stale_paths": "0",
        "duplicate_freshness_failures": "0",
        "total_pairwise_prefix_lca_divergence": "0",
        "avg_pairwise_prefix_lca_divergence": "0.0",
        "refinements": "0",
        "search_failed": "0",
    }


def run_one(args: argparse.Namespace, benchmark: Benchmark, mode: str, threads: int) -> dict[str, str]:
    command = ultimate_command(args, benchmark, mode, threads)
    if args.dry_run:
        print(shlex.join(command))
        return empty_row(benchmark, mode, threads, "DRY_RUN")

    start = time.monotonic()
    timed_out = False
    process = subprocess.Popen(
        command,
        text=True,
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
        start_new_session=True,
    )
    try:
        stdout, stderr = process.communicate(timeout=args.timeout)
        output = stdout + "\n" + stderr
        returncode = process.returncode
    except subprocess.TimeoutExpired as exc:
        timed_out = True
        try:
            os.killpg(process.pid, signal.SIGTERM)
        except ProcessLookupError:
            pass
        stdout, stderr = process.communicate()
        output = as_text(exc.stdout) + as_text(stdout) + "\n" + as_text(exc.stderr) + as_text(stderr)
        returncode = 124
    runtime_ms = int((time.monotonic() - start) * 1000)

    log_path = args.output_dir / f"{benchmark.name}-{mode}-threads-{threads}.log"
    log_path.write_text(output, encoding="utf-8")

    row = {
        "benchmark": benchmark.name,
        "mode": mode,
        "threads": str(threads),
        "result": parse_result(output, returncode, timed_out),
        "runtime_ms": str(runtime_ms),
        "checked_paths": parse_last(STAT_PATTERNS["checked_paths"], output),
        "stale_paths": parse_last(STAT_PATTERNS["stale_paths"], output),
        "duplicate_freshness_failures": parse_last(STAT_PATTERNS["duplicate_freshness_failures"], output),
        "total_pairwise_prefix_lca_divergence":
            parse_last(STAT_PATTERNS["total_pairwise_prefix_lca_divergence"], output),
        "avg_pairwise_prefix_lca_divergence":
            parse_last(STAT_PATTERNS["avg_pairwise_prefix_lca_divergence"], output, "0.0"),
        "refinements": parse_last(STAT_PATTERNS["refinements"], output),
        "search_failed": parse_last(STAT_PATTERNS["search_failed"], output),
    }
    print(
        f"{benchmark.name} mode={mode} threads={threads}: {row['result']}, "
        f"runtime={runtime_ms}ms, checked_paths={row['checked_paths']}, stale_paths={row['stale_paths']}, "
        f"avg_prefix_lca_divergence={row['avg_pairwise_prefix_lca_divergence']}",
        flush=True,
    )
    return row


def write_csv(output_dir: Path, rows: list[dict[str, str]]) -> Path:
    csv_path = output_dir / "checked-path-divergence-results.csv"
    with csv_path.open("w", newline="", encoding="utf-8") as csv_file:
        writer = csv.DictWriter(csv_file, fieldnames=CSV_COLUMNS, lineterminator="\n", extrasaction="ignore")
        writer.writeheader()
        writer.writerows(rows)
    return csv_path


def table_for_rows(rows: list[dict[str, str]]) -> str:
    lines = [
        "| mode | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed |",
        "|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|",
    ]
    for row in sorted(rows, key=lambda item: (item["mode"], int(item["threads"]))):
        lines.append(
            "| {mode} | {threads} | {result} | {runtime_ms} | {checked_paths} | {stale_paths} | "
            "{duplicate_freshness_failures} | {total_pairwise_prefix_lca_divergence} | "
            "{avg_pairwise_prefix_lca_divergence} | {refinements} | {search_failed} |".format(**row)
        )
    return "\n".join(lines)


def numeric(row: dict[str, str], key: str) -> float:
    try:
        return float(row[key])
    except ValueError:
        return 0.0


def compare_paper_lcps(rows: list[dict[str, str]], key: str, label: str) -> str:
    by_thread_mode = {(row["threads"], row["mode"]): row for row in rows}
    comparisons = []
    for thread in sorted({row["threads"] for row in rows}, key=int):
        paper = by_thread_mode.get((thread, "PAPER"))
        lcps = by_thread_mode.get((thread, "LCPS"))
        if paper is None or lcps is None:
            continue
        delta = numeric(lcps, key) - numeric(paper, key)
        comparisons.append(f"{thread}t: {delta:+g}")
    if not comparisons:
        return f"- PAPER vs LCPS {label}: not available for the selected modes."
    return f"- PAPER vs LCPS {label}: " + ", ".join(comparisons) + " (LCPS - PAPER)."


def interpretation(rows: list[dict[str, str]]) -> list[str]:
    return [
        compare_paper_lcps(rows, "runtime_ms", "runtime_ms"),
        compare_paper_lcps(rows, "checked_paths", "checked_paths"),
        compare_paper_lcps(rows, "stale_paths", "stale_paths"),
        compare_paper_lcps(rows, "avg_pairwise_prefix_lca_divergence", "avg divergence"),
    ]


def write_markdown(output_dir: Path, rows: list[dict[str, str]]) -> Path:
    report_path = output_dir / "checked-path-divergence-results.md"
    rows_by_benchmark = {benchmark.name: [] for benchmark in BENCHMARKS}
    for row in rows:
        rows_by_benchmark.setdefault(row["benchmark"], []).append(row)

    lines = [
        "# Checked Path Divergence Results",
        "",
        "## Purpose",
        "",
        "This experiment compares PAPER, LCPS, and optional BFS/DFS path selection under parallel TraceAbstraction.",
        "",
        "Each unordered checked-path pair contributes normalized prefix-LCA divergence "
        "`1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.",
        "",
        "## Benchmark Selection",
        "",
    ]
    for benchmark in BENCHMARKS:
        if rows_by_benchmark.get(benchmark.name):
            lines.append(f"- `{benchmark.name}`: {benchmark.rationale}")
    lines.extend(["", "## Results", ""])
    for benchmark_name, benchmark_rows in rows_by_benchmark.items():
        if not benchmark_rows:
            continue
        lines.extend([f"### {benchmark_name}", "", table_for_rows(benchmark_rows), "", "#### Interpretation", ""])
        lines.extend(interpretation(benchmark_rows))
        lines.append("")

    lines.extend(
        [
            "## Interpretation Notes",
            "",
            "- Negative LCPS - PAPER runtime, checked_paths, or stale_paths deltas are improvements for that metric.",
            "- LCPS does not need the highest avg divergence to be useful; compare runtime, stale_paths, checked_paths, and result consistency.",
            "- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.",
            "",
            "## Raw Data",
            "",
            "See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log`.",
            "",
        ]
    )
    report_path.write_text("\n".join(lines), encoding="utf-8")
    return report_path


def main() -> None:
    args = parse_args()
    args.output_dir.mkdir(parents=True, exist_ok=True)

    rows = []
    modes = selected_modes(args)
    for benchmark in selected_benchmarks(args):
        for mode in modes:
            for threads in selected_threads(args):
                rows.append(run_one(args, benchmark, mode, threads))

    csv_path = write_csv(args.output_dir, rows)
    report_path = write_markdown(args.output_dir, rows)
    print(f"Wrote {csv_path}")
    print(f"Wrote {report_path}")


if __name__ == "__main__":
    main()
