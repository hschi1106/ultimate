#!/usr/bin/env python3
"""Run a small checked-path divergence experiment for Ultimate TraceAbstraction."""

import argparse
import csv
import signal
import os
import re
import shlex
import subprocess
import time
from dataclasses import dataclass
from pathlib import Path


REPO_ROOT = Path(__file__).resolve().parents[4]
DEFAULT_OUTPUT_DIR = Path(__file__).resolve().parent / "results"
DEFAULT_ULTIMATE_CMD = os.environ.get(
    "ULTIMATE_CMD", str(REPO_ROOT / "releaseScripts/default/adds/run-ultimate.sh")
)

CSV_COLUMNS = [
    "benchmark",
    "threads",
    "result",
    "runtime_ms",
    "checked_paths",
    "total_pairwise_tree_distance",
    "avg_pairwise_tree_distance",
    "refinements",
    "stale_paths",
]

STAT_PATTERNS = {
    "checked_paths": [
        re.compile(r"Checked paths:\s*([0-9]+)", re.IGNORECASE),
        re.compile(r"CheckedPaths:\s*([0-9]+)", re.IGNORECASE),
    ],
    "total_pairwise_tree_distance": [
        re.compile(r"Total pairwise tree distance:\s*([0-9]+)", re.IGNORECASE),
        re.compile(r"TotalPairwiseTreeDistance:\s*([0-9]+)", re.IGNORECASE),
    ],
    "avg_pairwise_tree_distance": [
        re.compile(r"Avg pairwise tree distance:\s*([0-9]+(?:\.[0-9]+)?(?:[eE][+-]?[0-9]+)?)", re.IGNORECASE),
        re.compile(r"AvgPairwiseTreeDistance:\s*([0-9]+(?:\.[0-9]+)?(?:[eE][+-]?[0-9]+)?)", re.IGNORECASE),
    ],
    "refinements": [
        re.compile(r"Refinements:\s*([0-9]+)", re.IGNORECASE),
        re.compile(r"Overall iterations:\s*([0-9]+)", re.IGNORECASE),
        re.compile(r"OverallIterations:\s*([0-9]+)", re.IGNORECASE),
    ],
    "stale_paths": [
        re.compile(r"Stale paths:\s*([0-9]+)", re.IGNORECASE),
        re.compile(r"Skipped paths:\s*([0-9]+)", re.IGNORECASE),
        re.compile(r"StalePaths:\s*([0-9]+)", re.IGNORECASE),
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
        "Harder loop-invariant testcase with substantially more checked paths than the baseline cases, while still completing in a practical experiment run.",
    ),
    Benchmark(
        "concurrent-fischer",
        REPO_ROOT / "trunk/examples/concurrent/bpl/regression/showcase/Fischer.bpl",
        REPO_ROOT / "trunk/examples/concurrent/bpl/regression/ReachSafety.xml",
        REPO_ROOT / "trunk/examples/concurrent/bpl/regression/ReachSafety-32bit-Automizer.epf",
        "Concurrent showcase testcase that completes quickly but still produces several dispersed checked paths under parallel CEGAR.",
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
    parser.add_argument("--threads", default="1,2,4,8", help="Comma-separated thread counts.")
    parser.add_argument("--include-16", action="store_true", help="Also run with 16 workers.")
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


def selected_benchmarks(args: argparse.Namespace) -> list[Benchmark]:
    if not args.benchmark:
        return BENCHMARKS
    selected = set(args.benchmark)
    return [benchmark for benchmark in BENCHMARKS if benchmark.name in selected]


def ultimate_command(args: argparse.Namespace, benchmark: Benchmark, threads: int) -> list[str]:
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
    if "Ultimate proved your program to be correct" in output or "AllSpecificationsHoldResult" in output:
        return "SAFE"
    if "Ultimate proved your program to be incorrect" in output:
        return "UNSAFE"
    if "ExceptionOrErrorResult" in output or returncode != 0:
        return f"ERROR_{returncode}"
    if "TimeoutResult" in output:
        return "TIMEOUT"
    if "UnknownResult" in output:
        return "UNKNOWN"
    return "UNKNOWN"


def run_one(args: argparse.Namespace, benchmark: Benchmark, threads: int) -> dict[str, str]:
    command = ultimate_command(args, benchmark, threads)
    if args.dry_run:
        print(shlex.join(command))
        return {
            "benchmark": benchmark.name,
            "threads": str(threads),
            "result": "DRY_RUN",
            "runtime_ms": "0",
            "checked_paths": "0",
            "total_pairwise_tree_distance": "0",
            "avg_pairwise_tree_distance": "0.0",
            "refinements": "0",
            "stale_paths": "0",
        }

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

    log_path = args.output_dir / f"{benchmark.name}-threads-{threads}.log"
    log_path.write_text(output, encoding="utf-8")

    row = {
        "benchmark": benchmark.name,
        "threads": str(threads),
        "result": parse_result(output, returncode, timed_out),
        "runtime_ms": str(runtime_ms),
        "checked_paths": parse_last(STAT_PATTERNS["checked_paths"], output),
        "total_pairwise_tree_distance": parse_last(STAT_PATTERNS["total_pairwise_tree_distance"], output),
        "avg_pairwise_tree_distance": parse_last(STAT_PATTERNS["avg_pairwise_tree_distance"], output, "0.0"),
        "refinements": parse_last(STAT_PATTERNS["refinements"], output),
        "stale_paths": parse_last(STAT_PATTERNS["stale_paths"], output),
    }
    print(
        f"{benchmark.name} threads={threads}: {row['result']}, "
        f"runtime={runtime_ms}ms, checked_paths={row['checked_paths']}, "
        f"avg_distance={row['avg_pairwise_tree_distance']}",
        flush=True,
    )
    return row


def write_csv(output_dir: Path, rows: list[dict[str, str]]) -> Path:
    csv_path = output_dir / "checked-path-divergence-results.csv"
    with csv_path.open("w", newline="", encoding="utf-8") as csv_file:
        writer = csv.DictWriter(csv_file, fieldnames=CSV_COLUMNS)
        writer.writeheader()
        writer.writerows(rows)
    return csv_path


def table_for_rows(rows: list[dict[str, str]]) -> str:
    lines = [
        "| threads | result | runtime_ms | checked_paths | total_pairwise_tree_distance | avg_pairwise_tree_distance | refinements | stale_paths |",
        "|---:|---|---:|---:|---:|---:|---:|---:|",
    ]
    for row in sorted(rows, key=lambda item: int(item["threads"])):
        lines.append(
            "| {threads} | {result} | {runtime_ms} | {checked_paths} | "
            "{total_pairwise_tree_distance} | {avg_pairwise_tree_distance} | "
            "{refinements} | {stale_paths} |".format(**row)
        )
    return "\n".join(lines)


def trend_sentence(rows: list[dict[str, str]]) -> str:
    ordered = sorted(rows, key=lambda item: int(item["threads"]))
    if len(ordered) < 2:
        return "Only one thread count was run, so no cross-thread trend can be inferred."
    first = float(ordered[0]["avg_pairwise_tree_distance"])
    last = float(ordered[-1]["avg_pairwise_tree_distance"])
    if last > first:
        direction = "increased"
    elif last < first:
        direction = "decreased"
    else:
        direction = "did not change"
    return (
        f"From {ordered[0]['threads']} to {ordered[-1]['threads']} threads, "
        f"avgPairwiseTreeDistance {direction} ({first} -> {last})."
    )


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
        "This experiment compares whether higher parallelization levels lead to more dispersed paths reaching the real trace checker, and whether that correlates with runtime, refinements, or stale work.",
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
        lines.extend([f"### {benchmark_name}", "", table_for_rows(benchmark_rows), "", trend_sentence(benchmark_rows), ""])

    lines.extend(
        [
            "## Interpretation Notes",
            "",
            "- Increasing avgPairwiseTreeDistance means the actually checked paths ended farther apart in the exploration tree.",
            "- Compare runtime and refinements against the distance columns per benchmark; positive correlation suggests path dispersion may be associated with additional useful or stale work.",
            "- `stale_paths` is `0` when the current Ultimate log does not expose a stale/skipped-path counter.",
            "- Treat timeouts, crashes, and zero checked paths as inconclusive for the dispersion trend.",
            "",
            "## Raw Data",
            "",
            "See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-threads-*.log`.",
            "",
        ]
    )
    report_path.write_text("\n".join(lines), encoding="utf-8")
    return report_path


def main() -> None:
    args = parse_args()
    args.output_dir.mkdir(parents=True, exist_ok=True)

    rows = []
    for benchmark in selected_benchmarks(args):
        for threads in selected_threads(args):
            rows.append(run_one(args, benchmark, threads))

    csv_path = write_csv(args.output_dir, rows)
    report_path = write_markdown(args.output_dir, rows)
    print(f"Wrote {csv_path}")
    print(f"Wrote {report_path}")


if __name__ == "__main__":
    main()
