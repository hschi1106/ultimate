#!/usr/bin/env python3
"""Run checked-path divergence experiments for Ultimate TraceAbstraction."""

from __future__ import annotations

import argparse
import csv
import os
import re
import shlex
import signal
import statistics
import subprocess
import time
from dataclasses import dataclass
from pathlib import Path


REPO_ROOT = Path(__file__).resolve().parents[4]
DEFAULT_OUTPUT_DIR = Path(__file__).resolve().parent / "results"
DEFAULT_ULTIMATE_CMD = os.environ.get(
    "ULTIMATE_CMD", str(REPO_ROOT / "releaseScripts/default/adds/run-ultimate.sh")
)

MAIN_MODES = ["PAPER", "LCPS", "BATCH_LCPS", "ADAPTIVE_BATCH_LCPS"]
BASIC_MODES = ["BFS", "DFS"]
SUPPORTED_MODES = BASIC_MODES + MAIN_MODES
MODE_ALIASES = {
    "ADAPTIVE_FIRST_FILL_OR_STALE": "ADAPTIVE_BATCH_LCPS",
}

CSV_COLUMNS = [
    "benchmark",
    "mode",
    "repeat_index",
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
    "lcps_checked_prefix_queries",
    "lcps_stale_prefix_queries",
    "lcps_checked_prefix_hits",
    "lcps_stale_prefix_hits",
    "lcps_search_invocations",
    "lcps_effective_priority_decisions",
    "batch_lcps_invocations",
    "batch_lcps_available_slots_total",
    "batch_lcps_candidates_generated",
    "batch_lcps_candidates_selected",
    "batch_lcps_candidate_generation_failures",
    "batch_lcps_avg_candidate_pool_size",
    "batch_lcps_avg_selected_batch_size",
    "batch_lcps_effective_batch_decisions",
    "batch_lcps_candidate_generation_time_ms",
    "batch_lcps_selection_time_ms",
    "adaptive_batch_invocations",
    "adaptive_batch_fallbacks",
    "adaptive_triggered_by_first_fill",
    "adaptive_triggered_by_stale",
    "adaptive_min_available_slots",
]

STAT_PATTERNS = {
    "checked_paths": [
        re.compile(r"Checked paths:\s*([0-9]+)", re.IGNORECASE),
        re.compile(r"CheckedPaths:\s*([0-9]+)", re.IGNORECASE),
    ],
    "stale_paths": [
        re.compile(r"Stale paths:\s*([0-9]+)", re.IGNORECASE),
        re.compile(r"StalePaths:\s*([0-9]+)", re.IGNORECASE),
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
    "lcps_checked_prefix_queries": [re.compile(r"LcpsCheckedPrefixQueries:\s*([0-9]+)", re.IGNORECASE)],
    "lcps_stale_prefix_queries": [re.compile(r"LcpsStalePrefixQueries:\s*([0-9]+)", re.IGNORECASE)],
    "lcps_checked_prefix_hits": [re.compile(r"LcpsCheckedPrefixHits:\s*([0-9]+)", re.IGNORECASE)],
    "lcps_stale_prefix_hits": [re.compile(r"LcpsStalePrefixHits:\s*([0-9]+)", re.IGNORECASE)],
    "lcps_search_invocations": [re.compile(r"LcpsSearchInvocations:\s*([0-9]+)", re.IGNORECASE)],
    "lcps_effective_priority_decisions": [
        re.compile(r"LcpsEffectivePriorityDecisions:\s*([0-9]+)", re.IGNORECASE)
    ],
    "batch_lcps_invocations": [re.compile(r"BatchLcpsInvocations:\s*([0-9]+)", re.IGNORECASE)],
    "batch_lcps_available_slots_total": [
        re.compile(r"BatchLcpsAvailableSlotsTotal:\s*([0-9]+)", re.IGNORECASE)
    ],
    "batch_lcps_candidates_generated": [
        re.compile(r"BatchLcpsCandidatesGenerated:\s*([0-9]+)", re.IGNORECASE)
    ],
    "batch_lcps_candidates_selected": [
        re.compile(r"BatchLcpsCandidatesSelected:\s*([0-9]+)", re.IGNORECASE)
    ],
    "batch_lcps_candidate_generation_failures": [
        re.compile(r"BatchLcpsCandidateGenerationFailures:\s*([0-9]+)", re.IGNORECASE)
    ],
    "batch_lcps_avg_candidate_pool_size": [
        re.compile(r"BatchLcpsAvgCandidatePoolSize:\s*([0-9]+(?:\.[0-9]+)?(?:[eE][+-]?[0-9]+)?)",
                   re.IGNORECASE)
    ],
    "batch_lcps_avg_selected_batch_size": [
        re.compile(r"BatchLcpsAvgSelectedBatchSize:\s*([0-9]+(?:\.[0-9]+)?(?:[eE][+-]?[0-9]+)?)",
                   re.IGNORECASE)
    ],
    "batch_lcps_effective_batch_decisions": [
        re.compile(r"BatchLcpsEffectiveBatchDecisions:\s*([0-9]+)", re.IGNORECASE)
    ],
    "batch_lcps_candidate_generation_time_ms": [
        re.compile(r"BatchLcpsCandidateGenerationTimeMs:\s*([0-9]+)", re.IGNORECASE)
    ],
    "batch_lcps_selection_time_ms": [
        re.compile(r"BatchLcpsSelectionTimeMs:\s*([0-9]+)", re.IGNORECASE)
    ],
    "adaptive_batch_invocations": [re.compile(r"AdaptiveBatchInvocations:\s*([0-9]+)", re.IGNORECASE)],
    "adaptive_batch_fallbacks": [re.compile(r"AdaptiveBatchFallbacks:\s*([0-9]+)", re.IGNORECASE)],
    "adaptive_triggered_by_first_fill": [
        re.compile(r"AdaptiveTriggeredByFirstFill:\s*([0-9]+)", re.IGNORECASE)
    ],
    "adaptive_triggered_by_stale": [re.compile(r"AdaptiveTriggeredByStale:\s*([0-9]+)", re.IGNORECASE)],
    "adaptive_min_available_slots": [re.compile(r"AdaptiveMinAvailableSlots:\s*([0-9]+)", re.IGNORECASE)],
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
    parser.add_argument("--ultimate-cmd", default=DEFAULT_ULTIMATE_CMD)
    parser.add_argument("--modes", default=",".join(MAIN_MODES), help="Comma-separated final modes.")
    parser.add_argument("--threads", default="1,2,4,8,16", help="Comma-separated thread counts.")
    parser.add_argument("--benchmark", action="append", help="Restrict to one benchmark name; repeatable.")
    parser.add_argument("--benchmark-file", type=Path,
                        help="CSV with columns name,input_file,toolchain,settings,rationale.")
    parser.add_argument("--timeout", type=int, default=900, help="Per-run timeout in seconds.")
    parser.add_argument("--repeat", type=int, default=1, help="Repeat each run N times.")
    parser.add_argument("--include-basic", action="store_true", help="Also run BFS and DFS baselines.")
    parser.add_argument("--output-dir", type=Path, default=DEFAULT_OUTPUT_DIR)
    parser.add_argument("--extra-arg", action="append", default=[],
                        help="Additional Ultimate CLI argument appended after experiment overrides.")
    parser.add_argument("--dry-run", action="store_true", help="Print commands without executing them.")
    return parser.parse_args()


def resolve_repo_path(value: str) -> Path:
    path = Path(value)
    return path if path.is_absolute() else REPO_ROOT / path


def load_benchmarks_from_file(path: Path) -> list[Benchmark]:
    with path.open(newline="", encoding="utf-8") as csv_file:
        reader = csv.DictReader(csv_file)
        required = {"name", "input_file", "toolchain", "settings", "rationale"}
        missing = required - set(reader.fieldnames or [])
        if missing:
            raise ValueError(f"{path} is missing benchmark columns: {', '.join(sorted(missing))}")
        benchmarks = [
            Benchmark(
                row["name"],
                resolve_repo_path(row["input_file"]),
                resolve_repo_path(row["toolchain"]),
                resolve_repo_path(row["settings"]),
                row.get("rationale", ""),
            )
            for row in reader
        ]
    if not benchmarks:
        raise ValueError(f"{path} did not contain any benchmarks")
    return benchmarks


def selected_benchmarks(args: argparse.Namespace) -> list[Benchmark]:
    benchmarks = load_benchmarks_from_file(args.benchmark_file) if args.benchmark_file else BENCHMARKS
    if not args.benchmark:
        return benchmarks
    selected = set(args.benchmark)
    result = [benchmark for benchmark in benchmarks if benchmark.name in selected]
    missing = selected - {benchmark.name for benchmark in result}
    if missing:
        raise ValueError(f"Unknown benchmark(s): {', '.join(sorted(missing))}")
    return result


def selected_threads(args: argparse.Namespace) -> list[int]:
    return [int(thread.strip()) for thread in args.threads.split(",") if thread.strip()]


def normalize_mode(mode: str) -> str:
    normalized = mode.strip().upper()
    return MODE_ALIASES.get(normalized, normalized)


def selected_modes(args: argparse.Namespace) -> list[str]:
    modes = [normalize_mode(mode) for mode in args.modes.split(",") if mode.strip()]
    if args.include_basic:
        modes = BASIC_MODES + modes
    result = []
    for mode in modes:
        if mode not in SUPPORTED_MODES:
            raise ValueError(f"Unsupported mode {mode!r}; expected one of {','.join(SUPPORTED_MODES)}")
        if mode not in result:
            result.append(mode)
    return result


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


def parse_last(patterns: list[re.Pattern], text: str, default: str = "0") -> str:
    matches = []
    for pattern in patterns:
        matches.extend(pattern.findall(text))
    return str(matches[-1]) if matches else default


def as_text(output: str | bytes | None) -> str:
    if output is None:
        return ""
    if isinstance(output, bytes):
        return output.decode("utf-8", errors="replace")
    return output


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


def empty_row(benchmark: Benchmark, mode: str, repeat_index: int, threads: int, result: str) -> dict[str, str]:
    row = {
        "benchmark": benchmark.name,
        "mode": mode,
        "repeat_index": str(repeat_index),
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
    for column in CSV_COLUMNS:
        row.setdefault(column, "0")
    return row


def variant_label(mode: str, repeat_index: int, include_repeat_suffix: bool) -> str:
    if include_repeat_suffix:
        return f"{mode}-repeat-{repeat_index}"
    return mode


def run_one(args: argparse.Namespace, benchmark: Benchmark, mode: str, threads: int, repeat_index: int,
        include_repeat_suffix: bool) -> dict[str, str]:
    command = ultimate_command(args, benchmark, mode, threads)
    if args.dry_run:
        print(shlex.join(command))
        return empty_row(benchmark, mode, repeat_index, threads, "DRY_RUN")

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

    run_label = variant_label(mode, repeat_index, include_repeat_suffix)
    log_path = args.output_dir / f"{benchmark.name}-{run_label}-threads-{threads}.log"
    log_path.write_text(output, encoding="utf-8")

    row = empty_row(benchmark, mode, repeat_index, threads, parse_result(output, returncode, timed_out))
    row["runtime_ms"] = str(runtime_ms)
    for key, patterns in STAT_PATTERNS.items():
        default = "0.0" if "avg_" in key else "0"
        row[key] = parse_last(patterns, output, default)

    print(
        f"{benchmark.name} mode={mode} repeat={repeat_index} threads={threads}: "
        f"{row['result']}, runtime={runtime_ms}ms, checked={row['checked_paths']}, "
        f"stale={row['stale_paths']}, lcps_hits={row['lcps_checked_prefix_hits']}/"
        f"{row['lcps_stale_prefix_hits']}, batch_candidates={row['batch_lcps_candidates_generated']}, "
        f"adaptive={row['adaptive_batch_invocations']}/{row['adaptive_batch_fallbacks']}",
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


def numeric(row: dict[str, str], key: str) -> float:
    try:
        return float(row[key])
    except ValueError:
        return 0.0


def comparison_key(row: dict[str, str]) -> tuple[str, str, str]:
    return row["benchmark"], row["threads"], row["repeat_index"]


def mode_pairs(rows: list[dict[str, str]], base_mode: str,
        target_mode: str) -> list[tuple[dict[str, str], dict[str, str]]]:
    base = {comparison_key(row): row for row in rows if row["mode"] == base_mode}
    target = {comparison_key(row): row for row in rows if row["mode"] == target_mode}
    return [(base[key], target[key]) for key in sorted(set(base) & set(target), key=lambda x: (x[0], int(x[1]), int(x[2])))]


def pair_stats(rows: list[dict[str, str]], base_mode: str, target_mode: str, key: str) -> tuple[list[float], int, int, int]:
    deltas = [numeric(target, key) - numeric(base, key) for base, target in mode_pairs(rows, base_mode, target_mode)]
    wins = sum(1 for delta in deltas if delta < 0)
    losses = sum(1 for delta in deltas if delta > 0)
    ties = len(deltas) - wins - losses
    return deltas, wins, losses, ties


def average(values: list[float]) -> float:
    return sum(values) / len(values) if values else 0.0


def summarize_pair_metric(rows: list[dict[str, str]], base_mode: str, target_mode: str, key: str,
        label: str) -> str:
    deltas, wins, losses, ties = pair_stats(rows, base_mode, target_mode, key)
    if not deltas:
        return f"- {target_mode} - {base_mode} {label}: not available."
    return (
        f"- {target_mode} - {base_mode} {label}: wins/losses/ties {wins}/{losses}/{ties}, "
        f"mean {average(deltas):.2f}, median {statistics.median(deltas):.2f}."
    )


def result_mismatches(rows: list[dict[str, str]]) -> list[tuple[tuple[str, str, str], set[str]]]:
    grouped: dict[tuple[str, str, str], set[str]] = {}
    for row in rows:
        grouped.setdefault(comparison_key(row), set()).add(row["result"])
    return [(key, results) for key, results in grouped.items() if len(results) > 1]


def table_for_rows(rows: list[dict[str, str]]) -> str:
    lines = [
        "| mode | repeat_index | threads | result | runtime_ms | checked_paths | stale_paths | "
        "duplicate_freshness_failures | refinements | search_failed | lcps_checked_prefix_queries | "
        "lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | "
        "lcps_search_invocations | lcps_effective_priority_decisions | batch_lcps_invocations | "
        "batch_lcps_candidates_generated | batch_lcps_candidates_selected | "
        "batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | "
        "batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | "
        "adaptive_triggered_by_first_fill | adaptive_triggered_by_stale | adaptive_min_available_slots |",
        "|---|---:|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|",
    ]
    mode_order = {mode: index for index, mode in enumerate(SUPPORTED_MODES)}
    keys = [
        "mode",
        "repeat_index",
        "threads",
        "result",
        "runtime_ms",
        "checked_paths",
        "stale_paths",
        "duplicate_freshness_failures",
        "refinements",
        "search_failed",
        "lcps_checked_prefix_queries",
        "lcps_stale_prefix_queries",
        "lcps_checked_prefix_hits",
        "lcps_stale_prefix_hits",
        "lcps_search_invocations",
        "lcps_effective_priority_decisions",
        "batch_lcps_invocations",
        "batch_lcps_candidates_generated",
        "batch_lcps_candidates_selected",
        "batch_lcps_effective_batch_decisions",
        "batch_lcps_candidate_generation_time_ms",
        "batch_lcps_selection_time_ms",
        "adaptive_batch_invocations",
        "adaptive_batch_fallbacks",
        "adaptive_triggered_by_first_fill",
        "adaptive_triggered_by_stale",
        "adaptive_min_available_slots",
    ]
    for row in sorted(rows, key=lambda item: (mode_order.get(item["mode"], 99), int(item["repeat_index"]), int(item["threads"]))):
        lines.append("| " + " | ".join(row[key] for key in keys) + " |")
    return "\n".join(lines)


def summary_section(rows: list[dict[str, str]]) -> list[str]:
    present_modes = [mode for mode in SUPPORTED_MODES if any(row["mode"] == mode for row in rows)]
    errors = [row for row in rows if row["result"].startswith("ERROR")]
    timeouts = [row for row in rows if row["result"] == "TIMEOUT"]
    mismatches = result_mismatches(rows)
    lcps_rows = [row for row in rows if row["mode"] == "LCPS"]
    batch_rows = [row for row in rows if row["mode"] in {"BATCH_LCPS", "ADAPTIVE_BATCH_LCPS"}]

    lines = [
        "## Summary",
        "",
        "### Correctness",
        "",
        f"- Modes present: {', '.join(present_modes)}.",
        f"- Result mismatch groups across modes: {len(mismatches)}.",
        f"- ERROR rows: {len(errors)}; TIMEOUT rows: {len(timeouts)}.",
    ]

    if any(row["mode"] in BASIC_MODES for row in rows):
        basic_rows = [row for row in rows if row["mode"] in BASIC_MODES]
        total_dup = sum(numeric(row, "duplicate_freshness_failures") for row in basic_rows)
        total_failed = sum(numeric(row, "search_failed") for row in basic_rows)
        lines.append(f"- BFS/DFS duplicate freshness failures: {int(total_dup)}; search_failed total: {int(total_failed)}.")
    else:
        lines.append("- BFS/DFS were not part of this run.")

    lines.extend([
        "",
        "### Activation",
        "",
        f"- LCPS rows with search invocations > 0: {sum(numeric(row, 'lcps_search_invocations') > 0 for row in lcps_rows)} / {len(lcps_rows)}.",
        f"- LCPS checked prefix queries/hits: {int(sum(numeric(row, 'lcps_checked_prefix_queries') for row in lcps_rows))} / {int(sum(numeric(row, 'lcps_checked_prefix_hits') for row in lcps_rows))}.",
        f"- LCPS stale prefix queries/hits: {int(sum(numeric(row, 'lcps_stale_prefix_queries') for row in lcps_rows))} / {int(sum(numeric(row, 'lcps_stale_prefix_hits') for row in lcps_rows))}.",
        f"- LCPS effective priority decisions: {int(sum(numeric(row, 'lcps_effective_priority_decisions') for row in lcps_rows))}.",
        f"- BatchLcpsInvocations: {int(sum(numeric(row, 'batch_lcps_invocations') for row in batch_rows))}.",
        f"- BatchLcpsCandidatesGenerated: {int(sum(numeric(row, 'batch_lcps_candidates_generated') for row in batch_rows))}.",
        f"- BatchLcpsEffectiveBatchDecisions: {int(sum(numeric(row, 'batch_lcps_effective_batch_decisions') for row in batch_rows))}.",
        f"- AdaptiveBatchInvocations/Fallbacks: {int(sum(numeric(row, 'adaptive_batch_invocations') for row in rows))} / {int(sum(numeric(row, 'adaptive_batch_fallbacks') for row in rows))}.",
        f"- AdaptiveTriggeredByFirstFill/Stale: {int(sum(numeric(row, 'adaptive_triggered_by_first_fill') for row in rows))} / {int(sum(numeric(row, 'adaptive_triggered_by_stale') for row in rows))}.",
    ])

    lines.extend(["", "### Performance", ""])
    for mode in ["LCPS", "BATCH_LCPS", "ADAPTIVE_BATCH_LCPS"]:
        if mode in present_modes:
            lines.append(summarize_pair_metric(rows, "PAPER", mode, "runtime_ms", "runtime_ms"))
    if "LCPS" in present_modes and "BATCH_LCPS" in present_modes:
        lines.append(summarize_pair_metric(rows, "LCPS", "BATCH_LCPS", "runtime_ms", "runtime_ms"))
    if "BATCH_LCPS" in present_modes and "ADAPTIVE_BATCH_LCPS" in present_modes:
        lines.append(summarize_pair_metric(rows, "BATCH_LCPS", "ADAPTIVE_BATCH_LCPS", "runtime_ms", "runtime_ms"))

    lines.extend(["", "### Work", ""])
    for metric, label in [
            ("checked_paths", "checked_paths"),
            ("stale_paths", "stale_paths"),
            ("duplicate_freshness_failures", "duplicate freshness failures"),
            ("search_failed", "search_failed")]:
        for mode in ["LCPS", "BATCH_LCPS", "ADAPTIVE_BATCH_LCPS"]:
            if mode in present_modes:
                lines.append(summarize_pair_metric(rows, "PAPER", mode, metric, label))

    lines.extend(["", "### Batch Quality", ""])
    if batch_rows:
        pool_sizes = [numeric(row, "batch_lcps_avg_candidate_pool_size") for row in batch_rows]
        selected_sizes = [numeric(row, "batch_lcps_avg_selected_batch_size") for row in batch_rows]
        lines.append(f"- Average candidate pool size: {average(pool_sizes):.2f}.")
        lines.append(f"- Average selected batch size: {average(selected_sizes):.2f}.")
        lines.append(
            f"- Candidate generation/selection time: "
            f"{int(sum(numeric(row, 'batch_lcps_candidate_generation_time_ms') for row in batch_rows))}ms / "
            f"{int(sum(numeric(row, 'batch_lcps_selection_time_ms') for row in batch_rows))}ms."
        )
    else:
        lines.append("- Batch modes were not part of this run.")

    lines.extend(["", "### Interpretation", ""])
    if lcps_rows and sum(numeric(row, "lcps_effective_priority_decisions") for row in lcps_rows) == 0:
        lines.append("- LCPS cache may have been queried, but no effective priority decisions were observed.")
    elif lcps_rows:
        lines.append("- LCPS changed at least one successor choice; interpret this with runtime/work deltas.")
    if batch_rows and sum(numeric(row, "batch_lcps_effective_batch_decisions") for row in batch_rows) == 0:
        lines.append("- Batch selection did not differ from naive first-k candidate dispatch in this run.")
    elif batch_rows:
        lines.append("- Batch selection changed at least one dispatched batch relative to naive first-k dispatch.")
    lines.append("- ADAPTIVE_BATCH_LCPS uses batch on first fill per abstraction and after stale work; all other dispatches use PAPER.")
    lines.append("")
    return lines


def write_markdown(output_dir: Path, rows: list[dict[str, str]], benchmarks: list[Benchmark]) -> Path:
    report_path = output_dir / "checked-path-divergence-results.md"
    rows_by_benchmark = {benchmark.name: [] for benchmark in benchmarks}
    for row in rows:
        rows_by_benchmark.setdefault(row["benchmark"], []).append(row)

    lines = [
        "# Checked Path Divergence Results",
        "",
        "## Purpose",
        "",
        "This experiment compares the maintained parallel TraceAbstraction path-selection scope: PAPER, LCPS, "
        "BATCH_LCPS, ADAPTIVE_BATCH_LCPS, and optional BFS/DFS baselines.",
        "",
        "Each unordered checked-path pair contributes normalized prefix-LCA divergence "
        "`1 - depth(LCA(u, v)) / min(depth(u), depth(v))`.",
        "",
    ]
    lines.extend(summary_section(rows))
    lines.extend(["## Benchmark Selection", ""])
    for benchmark in benchmarks:
        if rows_by_benchmark.get(benchmark.name):
            lines.append(f"- `{benchmark.name}`: {benchmark.rationale}")
    lines.extend(["", "## Results", ""])
    for benchmark_name, benchmark_rows in rows_by_benchmark.items():
        if not benchmark_rows:
            continue
        lines.extend([f"### {benchmark_name}", "", table_for_rows(benchmark_rows), ""])
    report_path.write_text("\n".join(lines), encoding="utf-8")
    return report_path


def main() -> None:
    args = parse_args()
    args.output_dir.mkdir(parents=True, exist_ok=True)
    benchmarks = selected_benchmarks(args)
    modes = selected_modes(args)
    threads = selected_threads(args)
    include_repeat_suffix = args.repeat > 1

    rows = []
    for benchmark in benchmarks:
        for mode in modes:
            for repeat_index in range(args.repeat):
                for thread_count in threads:
                    rows.append(run_one(args, benchmark, mode, thread_count, repeat_index, include_repeat_suffix))

    csv_path = write_csv(args.output_dir, rows)
    markdown_path = write_markdown(args.output_dir, rows, benchmarks)
    print(f"Wrote {csv_path}")
    print(f"Wrote {markdown_path}")


if __name__ == "__main__":
    main()
