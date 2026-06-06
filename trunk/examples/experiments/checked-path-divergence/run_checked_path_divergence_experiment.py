#!/usr/bin/env python3
"""Run checked-path divergence experiments for Ultimate TraceAbstraction."""

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
TRACE_ABSTRACTION_STALE_PREFIX_CLI_KEY = "--traceabstraction.track.stale.prefixes.in.parallel.search"
TRACE_ABSTRACTION_USE_INITIAL_BFS_CLI_KEY = "--traceabstraction.use.initial.bfs.in.paper/lcps.parallel.search"
TRACE_ABSTRACTION_BATCH_LCPS_CANDIDATE_MULTIPLIER_CLI_KEY = (
    "--traceabstraction.batch.lcps.candidate.multiplier"
)
TRACE_ABSTRACTION_BATCH_LCPS_CANDIDATE_CAP_CLI_KEY = "--traceabstraction.batch.lcps.candidate.cap"
TRACE_ABSTRACTION_ADAPTIVE_BATCH_TRIGGER_MODE_CLI_KEY = "--traceabstraction.adaptive.batch.trigger.mode"
TRACE_ABSTRACTION_ADAPTIVE_BATCH_MIN_AVAILABLE_SLOTS_CLI_KEY = (
    "--traceabstraction.adaptive.batch.min.available.slots"
)
DEFAULT_MODES = ["PAPER", "LCPS", "BATCH_LCPS"]
BASIC_MODES = ["BFS", "DFS"]
LCPS_PRIORITY_MODES = ["LCPS", "LCPS_FULL", "LCPS_STALE_FIRST", "LCPS_FULL_STALE_FIRST"]
ADAPTIVE_TRIGGER_MODES = [
    "DUPLICATE_ONLY",
    "STALE_ONLY",
    "SEARCH_FAILED_ONLY",
    "IDLE_SLOT_ONLY",
    "FIRST_FILL_ONLY",
    "FIRST_FILL_OR_STALE",
    "THREADS_GE_4_FIRST_FILL",
    "ALWAYS_BATCH",
    "NEVER_BATCH",
]
ADAPTIVE_DISPLAY_MODES = {
    "DUPLICATE_ONLY": "ADAPTIVE_DUPLICATE",
    "STALE_ONLY": "ADAPTIVE_STALE",
    "SEARCH_FAILED_ONLY": "ADAPTIVE_SEARCH_FAILED",
    "IDLE_SLOT_ONLY": "ADAPTIVE_IDLE",
    "FIRST_FILL_ONLY": "ADAPTIVE_FIRST_FILL",
    "FIRST_FILL_OR_STALE": "ADAPTIVE_FIRST_FILL_OR_STALE",
    "THREADS_GE_4_FIRST_FILL": "ADAPTIVE_THREADS_GE_4_FIRST_FILL",
    "ALWAYS_BATCH": "ADAPTIVE_ALWAYS",
    "NEVER_BATCH": "ADAPTIVE_NEVER",
}
BATCH_MODES = ["BATCH_LCPS"] + list(ADAPTIVE_DISPLAY_MODES.values())
CACHE_MODES = LCPS_PRIORITY_MODES + BATCH_MODES
REQUEST_MODES = BASIC_MODES + ["PAPER"] + LCPS_PRIORITY_MODES + ["BATCH_LCPS", "ADAPTIVE_BATCH_LCPS"]
ALL_MODES = BASIC_MODES + ["PAPER"] + CACHE_MODES

CSV_COLUMNS = [
    "benchmark",
    "mode",
    "adaptive_trigger_mode",
    "repeat_index",
    "stale_tracking",
    "use_initial_bfs",
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
    "lcps_full_cache_suffix_invocations",
    "lcps_full_cache_suffix_fallbacks",
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
    "adaptive_batch_trigger_mode",
    "adaptive_batch_invocations",
    "adaptive_batch_fallbacks",
    "adaptive_triggered_by_duplicate",
    "adaptive_triggered_by_stale",
    "adaptive_triggered_by_search_failed",
    "adaptive_triggered_by_idle_slot",
    "adaptive_triggered_by_first_fill",
    "adaptive_triggered_by_first_fill_or_stale",
    "adaptive_triggered_by_threads_ge_4_first_fill",
    "first_dispatch_in_current_abstraction",
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
    "lcps_checked_prefix_queries": [
        re.compile(r"LcpsCheckedPrefixQueries:\s*([0-9]+)", re.IGNORECASE),
    ],
    "lcps_stale_prefix_queries": [
        re.compile(r"LcpsStalePrefixQueries:\s*([0-9]+)", re.IGNORECASE),
    ],
    "lcps_checked_prefix_hits": [
        re.compile(r"LcpsCheckedPrefixHits:\s*([0-9]+)", re.IGNORECASE),
    ],
    "lcps_stale_prefix_hits": [
        re.compile(r"LcpsStalePrefixHits:\s*([0-9]+)", re.IGNORECASE),
    ],
    "lcps_search_invocations": [
        re.compile(r"LcpsSearchInvocations:\s*([0-9]+)", re.IGNORECASE),
    ],
    "lcps_full_cache_suffix_invocations": [
        re.compile(r"LcpsFullCacheSuffixInvocations:\s*([0-9]+)", re.IGNORECASE),
    ],
    "lcps_full_cache_suffix_fallbacks": [
        re.compile(r"LcpsFullCacheSuffixFallbacks:\s*([0-9]+)", re.IGNORECASE),
    ],
    "lcps_effective_priority_decisions": [
        re.compile(r"LcpsEffectivePriorityDecisions:\s*([0-9]+)", re.IGNORECASE),
    ],
    "batch_lcps_invocations": [
        re.compile(r"BatchLcpsInvocations:\s*([0-9]+)", re.IGNORECASE),
    ],
    "batch_lcps_available_slots_total": [
        re.compile(r"BatchLcpsAvailableSlotsTotal:\s*([0-9]+)", re.IGNORECASE),
    ],
    "batch_lcps_candidates_generated": [
        re.compile(r"BatchLcpsCandidatesGenerated:\s*([0-9]+)", re.IGNORECASE),
    ],
    "batch_lcps_candidates_selected": [
        re.compile(r"BatchLcpsCandidatesSelected:\s*([0-9]+)", re.IGNORECASE),
    ],
    "batch_lcps_candidate_generation_failures": [
        re.compile(r"BatchLcpsCandidateGenerationFailures:\s*([0-9]+)", re.IGNORECASE),
    ],
    "batch_lcps_avg_candidate_pool_size": [
        re.compile(r"BatchLcpsAvgCandidatePoolSize:\s*([0-9]+(?:\.[0-9]+)?(?:[eE][+-]?[0-9]+)?)", re.IGNORECASE),
    ],
    "batch_lcps_avg_selected_batch_size": [
        re.compile(r"BatchLcpsAvgSelectedBatchSize:\s*([0-9]+(?:\.[0-9]+)?(?:[eE][+-]?[0-9]+)?)", re.IGNORECASE),
    ],
    "batch_lcps_effective_batch_decisions": [
        re.compile(r"BatchLcpsEffectiveBatchDecisions:\s*([0-9]+)", re.IGNORECASE),
    ],
    "batch_lcps_candidate_generation_time_ms": [
        re.compile(r"BatchLcpsCandidateGenerationTimeMs:\s*([0-9]+)", re.IGNORECASE),
    ],
    "batch_lcps_selection_time_ms": [
        re.compile(r"BatchLcpsSelectionTimeMs:\s*([0-9]+)", re.IGNORECASE),
    ],
    "adaptive_batch_trigger_mode": [
        re.compile(r"AdaptiveBatchTriggerMode:\s*([A-Z_]+)", re.IGNORECASE),
    ],
    "adaptive_batch_invocations": [
        re.compile(r"AdaptiveBatchInvocations:\s*([0-9]+)", re.IGNORECASE),
    ],
    "adaptive_batch_fallbacks": [
        re.compile(r"AdaptiveBatchFallbacks:\s*([0-9]+)", re.IGNORECASE),
    ],
    "adaptive_triggered_by_duplicate": [
        re.compile(r"AdaptiveTriggeredByDuplicate:\s*([0-9]+)", re.IGNORECASE),
    ],
    "adaptive_triggered_by_stale": [
        re.compile(r"AdaptiveTriggeredByStale:\s*([0-9]+)", re.IGNORECASE),
    ],
    "adaptive_triggered_by_search_failed": [
        re.compile(r"AdaptiveTriggeredBySearchFailed:\s*([0-9]+)", re.IGNORECASE),
    ],
    "adaptive_triggered_by_idle_slot": [
        re.compile(r"AdaptiveTriggeredByIdleSlot:\s*([0-9]+)", re.IGNORECASE),
    ],
    "adaptive_triggered_by_first_fill": [
        re.compile(r"AdaptiveTriggeredByFirstFill:\s*([0-9]+)", re.IGNORECASE),
    ],
    "adaptive_triggered_by_first_fill_or_stale": [
        re.compile(r"AdaptiveTriggeredByFirstFillOrStale:\s*([0-9]+)", re.IGNORECASE),
    ],
    "adaptive_triggered_by_threads_ge_4_first_fill": [
        re.compile(r"AdaptiveTriggeredByThreadsGe4FirstFill:\s*([0-9]+)", re.IGNORECASE),
    ],
    "first_dispatch_in_current_abstraction": [
        re.compile(r"FirstDispatchInCurrentAbstraction:\s*(true|false)", re.IGNORECASE),
    ],
    "adaptive_min_available_slots": [
        re.compile(r"AdaptiveMinAvailableSlots:\s*([0-9]+)", re.IGNORECASE),
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
    parser.add_argument(
        "--benchmark-file",
        type=Path,
        help="CSV with columns name,input_file,toolchain,settings,rationale. Overrides the built-in benchmark set.",
    )
    parser.add_argument("--threads", default="1,2,4,8,16", help="Comma-separated thread counts.")
    parser.add_argument("--include-16", action="store_true", help="Compatibility flag; 16 is included by default.")
    parser.add_argument(
        "--modes",
        default=",".join(DEFAULT_MODES),
        help="Comma-separated modes: BFS,DFS,PAPER,LCPS,BATCH_LCPS,ADAPTIVE_BATCH_LCPS.",
    )
    parser.add_argument("--include-basic", action="store_true", help="Also run BFS and DFS baselines.")
    parser.add_argument(
        "--track-stale-prefixes",
        default="true",
        help="Boolean or comma-separated booleans. With true,false, PAPER/BFS/DFS run once and LCPS runs both variants.",
    )
    parser.add_argument(
        "--use-initial-bfs",
        default="true",
        help="Boolean or comma-separated booleans for the PAPER/LCPS initial BFS preference.",
    )
    parser.add_argument(
        "--batch-lcps-candidate-multiplier",
        type=int,
        default=4,
        help="BATCH_LCPS candidate multiplier preference.",
    )
    parser.add_argument(
        "--batch-lcps-candidate-cap",
        type=int,
        default=32,
        help="BATCH_LCPS candidate cap preference.",
    )
    parser.add_argument(
        "--adaptive-trigger-modes",
        default=",".join(ADAPTIVE_TRIGGER_MODES),
        help="Comma-separated adaptive trigger modes used when ADAPTIVE_BATCH_LCPS is selected.",
    )
    parser.add_argument(
        "--adaptive-min-available-slots",
        type=int,
        default=2,
        help="Minimum idle slots required before ADAPTIVE_BATCH_LCPS may use batch selection.",
    )
    parser.add_argument("--repeat", type=int, default=1, help="Repeat each run N times.")
    parser.add_argument("--timeout", type=int, default=900, help="Per-run timeout in seconds.")
    parser.add_argument(
        "--benchmark",
        action="append",
        help="Restrict to one benchmark name; can be passed more than once.",
    )
    parser.add_argument(
        "--extra-arg",
        action="append",
        default=[],
        help="Additional Ultimate CLI argument, appended after the experiment overrides.",
    )
    parser.add_argument("--dry-run", action="store_true", help="Print commands without executing them.")
    return parser.parse_args()


def resolve_repo_path(value: str) -> Path:
    path = Path(value)
    if path.is_absolute():
        return path
    return REPO_ROOT / path


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


def all_benchmarks(args: argparse.Namespace) -> list[Benchmark]:
    if args.benchmark_file:
        return load_benchmarks_from_file(args.benchmark_file)
    return BENCHMARKS


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
        if mode not in REQUEST_MODES:
            raise ValueError(f"Unsupported mode {mode!r}; expected one of {','.join(REQUEST_MODES)}")
        if mode not in result:
            result.append(mode)
    return result


def selected_adaptive_trigger_modes(args: argparse.Namespace) -> list[str]:
    triggers = [trigger.strip().upper() for trigger in args.adaptive_trigger_modes.split(",") if trigger.strip()]
    result = []
    for trigger in triggers:
        if trigger not in ADAPTIVE_TRIGGER_MODES:
            raise ValueError(
                f"Unsupported adaptive trigger {trigger!r}; expected one of {','.join(ADAPTIVE_TRIGGER_MODES)}"
            )
        if trigger not in result:
            result.append(trigger)
    return result or ["NEVER_BATCH"]


def adaptive_display_mode(trigger: str) -> str:
    return ADAPTIVE_DISPLAY_MODES[trigger]


def cli_mode_for_display(mode: str) -> str:
    if mode in ADAPTIVE_DISPLAY_MODES.values():
        return "ADAPTIVE_BATCH_LCPS"
    return mode


def parse_bool(value: str) -> bool:
    normalized = value.strip().lower()
    if normalized in {"1", "true", "yes", "on"}:
        return True
    if normalized in {"0", "false", "no", "off"}:
        return False
    raise ValueError(f"Expected boolean, got {value!r}")


def selected_stale_tracking_values(args: argparse.Namespace) -> list[bool]:
    values = [parse_bool(value) for value in args.track_stale_prefixes.split(",") if value.strip()]
    if not values:
        return [True]
    result = []
    for value in values:
        if value not in result:
            result.append(value)
    return result


def stale_tracking_values_for_mode(mode: str, values: list[bool]) -> list[bool]:
    if mode in CACHE_MODES:
        return values
    return [values[0]]


def selected_initial_bfs_values(args: argparse.Namespace) -> list[bool]:
    values = [parse_bool(value) for value in args.use_initial_bfs.split(",") if value.strip()]
    if not values:
        return [True]
    result = []
    for value in values:
        if value not in result:
            result.append(value)
    return result


def selected_benchmarks(args: argparse.Namespace, benchmarks: list[Benchmark]) -> list[Benchmark]:
    if not args.benchmark:
        return benchmarks
    selected = set(args.benchmark)
    result = [benchmark for benchmark in benchmarks if benchmark.name in selected]
    missing = selected - {benchmark.name for benchmark in result}
    if missing:
        raise ValueError(f"Unknown benchmark(s): {', '.join(sorted(missing))}")
    return result


def stale_label(stale_tracking: bool) -> str:
    return "true" if stale_tracking else "false"


def initial_bfs_label(use_initial_bfs: bool) -> str:
    return "true" if use_initial_bfs else "false"


def variant_label(mode: str, stale_tracking: bool, use_initial_bfs: bool, adaptive_trigger_mode: str,
        repeat_index: int, include_stale_suffix: bool, include_initial_suffix: bool, include_repeat_suffix: bool) -> str:
    label = mode
    if include_stale_suffix or (mode in CACHE_MODES and not stale_tracking):
        label += f"-stale-{'on' if stale_tracking else 'off'}"
    if include_initial_suffix or not use_initial_bfs:
        label += f"-initial-bfs-{'on' if use_initial_bfs else 'off'}"
    if adaptive_trigger_mode != "n/a" and mode not in ADAPTIVE_DISPLAY_MODES.values():
        label += f"-adaptive-{adaptive_trigger_mode.lower()}"
    if include_repeat_suffix:
        label += f"-repeat-{repeat_index}"
    return label


def ultimate_command(args: argparse.Namespace, benchmark: Benchmark, mode: str, threads: int,
        stale_tracking: bool, use_initial_bfs: bool, adaptive_trigger_mode: str) -> list[str]:
    cli_mode = cli_mode_for_display(mode)
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
            cli_mode,
            TRACE_ABSTRACTION_STALE_PREFIX_CLI_KEY,
            stale_label(stale_tracking),
            TRACE_ABSTRACTION_USE_INITIAL_BFS_CLI_KEY,
            initial_bfs_label(use_initial_bfs),
            TRACE_ABSTRACTION_BATCH_LCPS_CANDIDATE_MULTIPLIER_CLI_KEY,
            str(args.batch_lcps_candidate_multiplier),
            TRACE_ABSTRACTION_BATCH_LCPS_CANDIDATE_CAP_CLI_KEY,
            str(args.batch_lcps_candidate_cap),
            TRACE_ABSTRACTION_ADAPTIVE_BATCH_TRIGGER_MODE_CLI_KEY,
            adaptive_trigger_mode if adaptive_trigger_mode != "n/a" else "NEVER_BATCH",
            TRACE_ABSTRACTION_ADAPTIVE_BATCH_MIN_AVAILABLE_SLOTS_CLI_KEY,
            str(args.adaptive_min_available_slots),
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


def empty_row(benchmark: Benchmark, mode: str, stale_tracking: bool, use_initial_bfs: bool,
        adaptive_trigger_mode: str, repeat_index: int, threads: int, result: str) -> dict[str, str]:
    row = {
        "benchmark": benchmark.name,
        "mode": mode,
        "adaptive_trigger_mode": adaptive_trigger_mode,
        "repeat_index": str(repeat_index),
        "stale_tracking": stale_label(stale_tracking),
        "use_initial_bfs": initial_bfs_label(use_initial_bfs),
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
        "lcps_checked_prefix_queries": "0",
        "lcps_stale_prefix_queries": "0",
        "lcps_checked_prefix_hits": "0",
        "lcps_stale_prefix_hits": "0",
        "lcps_search_invocations": "0",
        "lcps_full_cache_suffix_invocations": "0",
        "lcps_full_cache_suffix_fallbacks": "0",
        "lcps_effective_priority_decisions": "0",
        "batch_lcps_invocations": "0",
        "batch_lcps_available_slots_total": "0",
        "batch_lcps_candidates_generated": "0",
        "batch_lcps_candidates_selected": "0",
        "batch_lcps_candidate_generation_failures": "0",
        "batch_lcps_avg_candidate_pool_size": "0.0",
        "batch_lcps_avg_selected_batch_size": "0.0",
        "batch_lcps_effective_batch_decisions": "0",
        "batch_lcps_candidate_generation_time_ms": "0",
        "batch_lcps_selection_time_ms": "0",
        "adaptive_batch_trigger_mode": adaptive_trigger_mode,
        "adaptive_batch_invocations": "0",
        "adaptive_batch_fallbacks": "0",
        "adaptive_triggered_by_duplicate": "0",
        "adaptive_triggered_by_stale": "0",
        "adaptive_triggered_by_search_failed": "0",
        "adaptive_triggered_by_idle_slot": "0",
        "adaptive_triggered_by_first_fill": "0",
        "adaptive_triggered_by_first_fill_or_stale": "0",
        "adaptive_triggered_by_threads_ge_4_first_fill": "0",
        "first_dispatch_in_current_abstraction": "false",
        "adaptive_min_available_slots": "0",
    }
    return row


def run_one(args: argparse.Namespace, benchmark: Benchmark, mode: str, threads: int, stale_tracking: bool,
        use_initial_bfs: bool = True, adaptive_trigger_mode: str = "n/a", repeat_index: int = 0,
        include_stale_suffix: bool = False, include_initial_suffix: bool = False,
        include_repeat_suffix: bool = False) -> dict[str, str]:
    command = ultimate_command(args, benchmark, mode, threads, stale_tracking, use_initial_bfs, adaptive_trigger_mode)
    if args.dry_run:
        print(shlex.join(command))
        return empty_row(benchmark, mode, stale_tracking, use_initial_bfs, adaptive_trigger_mode, repeat_index, threads,
                "DRY_RUN")

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

    run_label = variant_label(mode, stale_tracking, use_initial_bfs, adaptive_trigger_mode, repeat_index,
            include_stale_suffix, include_initial_suffix, include_repeat_suffix)
    log_path = args.output_dir / f"{benchmark.name}-{run_label}-threads-{threads}.log"
    log_path.write_text(output, encoding="utf-8")

    row = empty_row(benchmark, mode, stale_tracking, use_initial_bfs, adaptive_trigger_mode, repeat_index, threads,
            parse_result(output, returncode, timed_out))
    row.update(
        {
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
            "lcps_checked_prefix_queries": parse_last(STAT_PATTERNS["lcps_checked_prefix_queries"], output),
            "lcps_stale_prefix_queries": parse_last(STAT_PATTERNS["lcps_stale_prefix_queries"], output),
            "lcps_checked_prefix_hits": parse_last(STAT_PATTERNS["lcps_checked_prefix_hits"], output),
            "lcps_stale_prefix_hits": parse_last(STAT_PATTERNS["lcps_stale_prefix_hits"], output),
            "lcps_search_invocations": parse_last(STAT_PATTERNS["lcps_search_invocations"], output),
            "lcps_full_cache_suffix_invocations":
                parse_last(STAT_PATTERNS["lcps_full_cache_suffix_invocations"], output),
            "lcps_full_cache_suffix_fallbacks":
                parse_last(STAT_PATTERNS["lcps_full_cache_suffix_fallbacks"], output),
            "lcps_effective_priority_decisions":
                parse_last(STAT_PATTERNS["lcps_effective_priority_decisions"], output),
            "batch_lcps_invocations": parse_last(STAT_PATTERNS["batch_lcps_invocations"], output),
            "batch_lcps_available_slots_total":
                parse_last(STAT_PATTERNS["batch_lcps_available_slots_total"], output),
            "batch_lcps_candidates_generated":
                parse_last(STAT_PATTERNS["batch_lcps_candidates_generated"], output),
            "batch_lcps_candidates_selected":
                parse_last(STAT_PATTERNS["batch_lcps_candidates_selected"], output),
            "batch_lcps_candidate_generation_failures":
                parse_last(STAT_PATTERNS["batch_lcps_candidate_generation_failures"], output),
            "batch_lcps_avg_candidate_pool_size":
                parse_last(STAT_PATTERNS["batch_lcps_avg_candidate_pool_size"], output, "0.0"),
            "batch_lcps_avg_selected_batch_size":
                parse_last(STAT_PATTERNS["batch_lcps_avg_selected_batch_size"], output, "0.0"),
            "batch_lcps_effective_batch_decisions":
                parse_last(STAT_PATTERNS["batch_lcps_effective_batch_decisions"], output),
            "batch_lcps_candidate_generation_time_ms":
                parse_last(STAT_PATTERNS["batch_lcps_candidate_generation_time_ms"], output),
            "batch_lcps_selection_time_ms":
                parse_last(STAT_PATTERNS["batch_lcps_selection_time_ms"], output),
            "adaptive_batch_trigger_mode":
                parse_last(STAT_PATTERNS["adaptive_batch_trigger_mode"], output, adaptive_trigger_mode),
            "adaptive_batch_invocations":
                parse_last(STAT_PATTERNS["adaptive_batch_invocations"], output),
            "adaptive_batch_fallbacks":
                parse_last(STAT_PATTERNS["adaptive_batch_fallbacks"], output),
            "adaptive_triggered_by_duplicate":
                parse_last(STAT_PATTERNS["adaptive_triggered_by_duplicate"], output),
            "adaptive_triggered_by_stale":
                parse_last(STAT_PATTERNS["adaptive_triggered_by_stale"], output),
            "adaptive_triggered_by_search_failed":
                parse_last(STAT_PATTERNS["adaptive_triggered_by_search_failed"], output),
            "adaptive_triggered_by_idle_slot":
                parse_last(STAT_PATTERNS["adaptive_triggered_by_idle_slot"], output),
            "adaptive_triggered_by_first_fill":
                parse_last(STAT_PATTERNS["adaptive_triggered_by_first_fill"], output),
            "adaptive_triggered_by_first_fill_or_stale":
                parse_last(STAT_PATTERNS["adaptive_triggered_by_first_fill_or_stale"], output),
            "adaptive_triggered_by_threads_ge_4_first_fill":
                parse_last(STAT_PATTERNS["adaptive_triggered_by_threads_ge_4_first_fill"], output),
            "first_dispatch_in_current_abstraction":
                parse_last(STAT_PATTERNS["first_dispatch_in_current_abstraction"], output, "false").lower(),
            "adaptive_min_available_slots":
                parse_last(STAT_PATTERNS["adaptive_min_available_slots"], output),
        }
    )
    print(
        f"{benchmark.name} mode={mode} adaptive={row['adaptive_trigger_mode']} repeat={repeat_index} "
        f"stale_tracking={row['stale_tracking']} "
        f"use_initial_bfs={row['use_initial_bfs']} threads={threads}: "
        f"{row['result']}, runtime={runtime_ms}ms, checked_paths={row['checked_paths']}, "
        f"stale_paths={row['stale_paths']}, lcps_queries={row['lcps_checked_prefix_queries']}/"
        f"{row['lcps_stale_prefix_queries']}, lcps_hits={row['lcps_checked_prefix_hits']}/"
        f"{row['lcps_stale_prefix_hits']}, lcps_decisions={row['lcps_effective_priority_decisions']}, "
        f"batch_candidates={row['batch_lcps_candidates_generated']}, "
        f"batch_decisions={row['batch_lcps_effective_batch_decisions']}, "
        f"adaptive_invocations={row['adaptive_batch_invocations']}, "
        f"first_fill_triggers={row['adaptive_triggered_by_first_fill']}/"
        f"{row['adaptive_triggered_by_first_fill_or_stale']}/"
        f"{row['adaptive_triggered_by_threads_ge_4_first_fill']}",
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
        "| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_triggered_by_first_fill | adaptive_triggered_by_first_fill_or_stale | adaptive_triggered_by_threads_ge_4_first_fill | first_dispatch_in_current_abstraction | adaptive_min_available_slots |",
        "|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|---:|",
    ]
    mode_order = {mode: index for index, mode in enumerate(ALL_MODES)}
    for row in sorted(rows, key=lambda item: (
            mode_order.get(item["mode"], 99), item["adaptive_trigger_mode"], int(item["repeat_index"]),
            item["stale_tracking"], item["use_initial_bfs"], int(item["threads"]))):
        lines.append(
            "| {mode} | {adaptive_trigger_mode} | {repeat_index} | {stale_tracking} | {use_initial_bfs} | "
            "{threads} | {result} | {runtime_ms} | {checked_paths} | "
            "{stale_paths} | {duplicate_freshness_failures} | {total_pairwise_prefix_lca_divergence} | "
            "{avg_pairwise_prefix_lca_divergence} | {refinements} | {search_failed} | "
            "{lcps_checked_prefix_queries} | {lcps_stale_prefix_queries} | {lcps_checked_prefix_hits} | "
            "{lcps_stale_prefix_hits} | {lcps_search_invocations} | {lcps_full_cache_suffix_invocations} | "
            "{lcps_full_cache_suffix_fallbacks} | {lcps_effective_priority_decisions} | "
            "{batch_lcps_invocations} | {batch_lcps_available_slots_total} | "
            "{batch_lcps_candidates_generated} | {batch_lcps_candidates_selected} | "
            "{batch_lcps_candidate_generation_failures} | {batch_lcps_avg_candidate_pool_size} | "
            "{batch_lcps_avg_selected_batch_size} | {batch_lcps_effective_batch_decisions} | "
            "{batch_lcps_candidate_generation_time_ms} | {batch_lcps_selection_time_ms} | "
            "{adaptive_batch_invocations} | {adaptive_batch_fallbacks} | "
            "{adaptive_triggered_by_duplicate} | {adaptive_triggered_by_stale} | "
            "{adaptive_triggered_by_search_failed} | {adaptive_triggered_by_idle_slot} | "
            "{adaptive_triggered_by_first_fill} | {adaptive_triggered_by_first_fill_or_stale} | "
            "{adaptive_triggered_by_threads_ge_4_first_fill} | {first_dispatch_in_current_abstraction} | "
            "{adaptive_min_available_slots} |".format(**row)
        )
    return "\n".join(lines)


def numeric(row: dict[str, str], key: str) -> float:
    try:
        return float(row[key])
    except ValueError:
        return 0.0


def comparison_key(row: dict[str, str]) -> tuple[str, str, str, str]:
    return row["benchmark"], row["threads"], row.get("use_initial_bfs", "true"), row.get("repeat_index", "0")


def paper_mode_pairs(rows: list[dict[str, str]], target_mode: str) -> list[tuple[dict[str, str], dict[str, str]]]:
    paper = {
        comparison_key(row): row for row in rows
        if row["mode"] == "PAPER" and row.get("stale_tracking", "true") == "true"
    }
    target = {
        comparison_key(row): row for row in rows
        if row["mode"] == target_mode and row.get("stale_tracking", "true") == "true"
    }
    return [(paper[key], target[key]) for key in sorted(
        set(paper) & set(target), key=lambda item: (item[0], int(item[1]), item[2], int(item[3]))
    )]


def paper_lcps_pairs(rows: list[dict[str, str]]) -> list[tuple[dict[str, str], dict[str, str]]]:
    return paper_mode_pairs(rows, "LCPS")


def compare_paper_mode(rows: list[dict[str, str]], target_mode: str, key: str, label: str) -> str:
    pairs = paper_mode_pairs(rows, target_mode)
    comparisons = []
    for paper, target in pairs:
        if paper["benchmark"] != target["benchmark"]:
            continue
        delta = numeric(target, key) - numeric(paper, key)
        suffix = "" if paper.get("use_initial_bfs", "true") == "true" else " no-init"
        comparisons.append(f"{paper['threads']}t{suffix}: {delta:+g}")
    if not comparisons:
        return f"- PAPER vs {target_mode} {label}: not available for the selected modes."
    return f"- PAPER vs {target_mode} {label}: " + ", ".join(comparisons) + f" ({target_mode} - PAPER)."


def compare_paper_lcps(rows: list[dict[str, str]], key: str, label: str) -> str:
    return compare_paper_mode(rows, "LCPS", key, label)


def mode_pairs(rows: list[dict[str, str]], base_mode: str,
        target_mode: str) -> list[tuple[dict[str, str], dict[str, str]]]:
    base = {
        comparison_key(row): row for row in rows
        if row["mode"] == base_mode and row.get("stale_tracking", "true") == "true"
    }
    target = {
        comparison_key(row): row for row in rows
        if row["mode"] == target_mode and row.get("stale_tracking", "true") == "true"
    }
    return [(base[key], target[key]) for key in sorted(
        set(base) & set(target), key=lambda item: (item[0], int(item[1]), item[2], int(item[3]))
    )]


def compare_modes(rows: list[dict[str, str]], base_mode: str, target_mode: str, key: str, label: str) -> str:
    pairs = mode_pairs(rows, base_mode, target_mode)
    comparisons = []
    for base, target in pairs:
        delta = numeric(target, key) - numeric(base, key)
        suffix = "" if base.get("use_initial_bfs", "true") == "true" else " no-init"
        comparisons.append(f"{base['threads']}t{suffix}: {delta:+g}")
    if not comparisons:
        return f"- {target_mode} vs {base_mode} {label}: not available for the selected modes."
    return f"- {target_mode} vs {base_mode} {label}: " + ", ".join(comparisons) + f" ({target_mode} - {base_mode})."


def lcps_ablation_pairs(rows: list[dict[str, str]]) -> list[tuple[dict[str, str], dict[str, str]]]:
    on = {
        comparison_key(row): row for row in rows
        if row["mode"] == "LCPS" and row.get("stale_tracking") == "true"
    }
    off = {
        comparison_key(row): row for row in rows
        if row["mode"] == "LCPS" and row.get("stale_tracking") == "false"
    }
    return [(on[key], off[key]) for key in sorted(
        set(on) & set(off), key=lambda item: (item[0], int(item[1]), item[2], int(item[3]))
    )]


def compare_lcps_ablation(rows: list[dict[str, str]], key: str, label: str) -> str | None:
    pairs = lcps_ablation_pairs(rows)
    if not pairs:
        return None
    comparisons = [
        f"{on['benchmark']} {on['threads']}t: {numeric(off, key) - numeric(on, key):+g}"
        for on, off in pairs
    ]
    return f"- LCPS stale-off vs stale-on {label}: " + ", ".join(comparisons) + " (off - on)."


def interpretation(rows: list[dict[str, str]]) -> list[str]:
    present_modes = {row["mode"] for row in rows}
    result = []
    for mode in [m for m in CACHE_MODES if m in present_modes]:
        result.extend([
            compare_paper_mode(rows, mode, "runtime_ms", "runtime_ms"),
            compare_paper_mode(rows, mode, "checked_paths", "checked_paths"),
            compare_paper_mode(rows, mode, "stale_paths", "stale_paths"),
            compare_paper_mode(rows, mode, "duplicate_freshness_failures", "duplicate freshness failures"),
            compare_paper_mode(rows, mode, "avg_pairwise_prefix_lca_divergence", "avg divergence"),
            compare_paper_mode(rows, mode, "lcps_effective_priority_decisions",
                    "lcps_effective_priority_decisions"),
        ])
        if mode == "BATCH_LCPS":
            result.extend([
                compare_paper_mode(rows, mode, "batch_lcps_candidates_generated",
                        "batch_lcps_candidates_generated"),
                compare_paper_mode(rows, mode, "batch_lcps_candidates_selected",
                        "batch_lcps_candidates_selected"),
                compare_paper_mode(rows, mode, "batch_lcps_effective_batch_decisions",
                        "batch_lcps_effective_batch_decisions"),
            ])
    if "LCPS" in present_modes and "LCPS_FULL" in present_modes:
        result.extend([
            compare_modes(rows, "LCPS", "LCPS_FULL", "runtime_ms", "runtime_ms"),
            compare_modes(rows, "LCPS", "LCPS_FULL", "checked_paths", "checked_paths"),
            compare_modes(rows, "LCPS", "LCPS_FULL", "stale_paths", "stale_paths"),
            compare_modes(rows, "LCPS", "LCPS_FULL", "lcps_effective_priority_decisions",
                    "lcps_effective_priority_decisions"),
            compare_modes(rows, "LCPS", "LCPS_FULL", "lcps_full_cache_suffix_invocations",
                    "lcps_full_cache_suffix_invocations"),
        ])
    if "LCPS" in present_modes and "BATCH_LCPS" in present_modes:
        result.extend([
            compare_modes(rows, "LCPS", "BATCH_LCPS", "runtime_ms", "runtime_ms"),
            compare_modes(rows, "LCPS", "BATCH_LCPS", "checked_paths", "checked_paths"),
            compare_modes(rows, "LCPS", "BATCH_LCPS", "stale_paths", "stale_paths"),
            compare_modes(rows, "LCPS", "BATCH_LCPS", "duplicate_freshness_failures",
                    "duplicate freshness failures"),
        ])
    for key, label in [
            ("runtime_ms", "runtime_ms"),
            ("checked_paths", "checked_paths"),
            ("stale_paths", "stale_paths"),
            ("lcps_checked_prefix_hits", "checked prefix hits"),
            ("lcps_stale_prefix_hits", "stale prefix hits")]:
        sentence = compare_lcps_ablation(rows, key, label)
        if sentence:
            result.append(sentence)
    return result


def average(values: list[float]) -> float:
    return sum(values) / len(values) if values else 0.0


def format_ms(value: float) -> str:
    return f"{value:.1f}ms"


def pair_stats(rows: list[dict[str, str]], base_mode: str, target_mode: str, key: str) -> tuple[list[float], int, int, int]:
    deltas = [numeric(target, key) - numeric(base, key) for base, target in mode_pairs(rows, base_mode, target_mode)]
    wins = sum(1 for delta in deltas if delta < 0)
    losses = sum(1 for delta in deltas if delta > 0)
    ties = len(deltas) - wins - losses
    return deltas, wins, losses, ties


def summarize_pair_metric(rows: list[dict[str, str]], base_mode: str, target_mode: str, key: str,
        label: str) -> str:
    deltas, wins, losses, ties = pair_stats(rows, base_mode, target_mode, key)
    if not deltas:
        return f"- {target_mode} - {base_mode} {label}: not available."
    return (
        f"- {target_mode} - {base_mode} {label}: wins/losses/ties {wins}/{losses}/{ties}, "
        f"mean {average(deltas):.2f}, median {statistics.median(deltas):.2f}."
    )


def metric_delta_stats(rows: list[dict[str, str]], base_mode: str, target_mode: str, key: str) -> str:
    deltas, wins, losses, ties = pair_stats(rows, base_mode, target_mode, key)
    if not deltas:
        return "n/a"
    return (
        f"wins/losses/ties {wins}/{losses}/{ties}, "
        f"mean {average(deltas):.2f}, median {statistics.median(deltas):.2f}"
    )


def summarize_adaptive_trigger_mode(rows: list[dict[str, str]], mode: str) -> str:
    mode_rows = [row for row in rows if row["mode"] == mode]
    if not mode_rows:
        return f"- {mode}: no rows."
    invocations = sum(numeric(row, "adaptive_batch_invocations") for row in mode_rows)
    fallbacks = sum(numeric(row, "adaptive_batch_fallbacks") for row in mode_rows)
    generated = sum(numeric(row, "batch_lcps_candidates_generated") for row in mode_rows)
    effective = sum(numeric(row, "batch_lcps_effective_batch_decisions") for row in mode_rows)
    generation_ms = sum(numeric(row, "batch_lcps_candidate_generation_time_ms") for row in mode_rows)
    selection_ms = sum(numeric(row, "batch_lcps_selection_time_ms") for row in mode_rows)
    generated_per_invocation = generated / invocations if invocations else 0.0
    trigger_counts = {
        "dup": sum(numeric(row, "adaptive_triggered_by_duplicate") for row in mode_rows),
        "stale": sum(numeric(row, "adaptive_triggered_by_stale") for row in mode_rows),
        "failed": sum(numeric(row, "adaptive_triggered_by_search_failed") for row in mode_rows),
        "idle": sum(numeric(row, "adaptive_triggered_by_idle_slot") for row in mode_rows),
        "first": sum(numeric(row, "adaptive_triggered_by_first_fill") for row in mode_rows),
        "first_or_stale": sum(numeric(row, "adaptive_triggered_by_first_fill_or_stale") for row in mode_rows),
        "threads_ge_4_first":
            sum(numeric(row, "adaptive_triggered_by_threads_ge_4_first_fill") for row in mode_rows),
    }
    return (
        f"- {mode}: adaptive invocations/fallbacks {int(invocations)}/{int(fallbacks)}, "
        f"triggers dup/stale/failed/idle/first/first-or-stale/threads-ge4-first "
        f"{int(trigger_counts['dup'])}/{int(trigger_counts['stale'])}/{int(trigger_counts['failed'])}/"
        f"{int(trigger_counts['idle'])}/{int(trigger_counts['first'])}/"
        f"{int(trigger_counts['first_or_stale'])}/{int(trigger_counts['threads_ge_4_first'])}, "
        f"candidates {int(generated)}, effective batch decisions {int(effective)}, "
        f"candidates/invocation {generated_per_invocation:.2f}, "
        f"generation/selection time {generation_ms:.0f}ms/{selection_ms:.0f}ms, "
        f"vs PAPER runtime [{metric_delta_stats(rows, 'PAPER', mode, 'runtime_ms')}], "
        f"vs BATCH_LCPS runtime [{metric_delta_stats(rows, 'BATCH_LCPS', mode, 'runtime_ms')}], "
        f"vs PAPER checked [{metric_delta_stats(rows, 'PAPER', mode, 'checked_paths')}], "
        f"vs PAPER stale [{metric_delta_stats(rows, 'PAPER', mode, 'stale_paths')}]."
    )


def result_mismatches(rows: list[dict[str, str]]) -> list[tuple[tuple[str, str, str], set[str]]]:
    grouped: dict[tuple[str, str, str], set[str]] = {}
    for row in rows:
        if row.get("stale_tracking", "true") != "true":
            continue
        grouped.setdefault(comparison_key(row), set()).add(row["result"])
    return [(key, results) for key, results in grouped.items() if len(results) > 1]


def summary_section(rows: list[dict[str, str]]) -> list[str]:
    errors = [row for row in rows if row["result"].startswith("ERROR")]
    timeouts = [row for row in rows if row["result"] == "TIMEOUT"]
    mismatches = result_mismatches(rows)
    cache_rows = [row for row in rows if row["mode"] in CACHE_MODES]
    priority_rows = [row for row in rows if row["mode"] in LCPS_PRIORITY_MODES]
    batch_rows = [row for row in rows if row["mode"] in BATCH_MODES]
    cache_with_invocations = [row for row in priority_rows if numeric(row, "lcps_search_invocations") > 0]
    cache_with_checked_queries = [row for row in priority_rows if numeric(row, "lcps_checked_prefix_queries") > 0]
    cache_with_stale_queries = [row for row in priority_rows if numeric(row, "lcps_stale_prefix_queries") > 0]
    cache_with_checked_hits = [row for row in priority_rows if numeric(row, "lcps_checked_prefix_hits") > 0]
    cache_with_stale_hits = [row for row in priority_rows if numeric(row, "lcps_stale_prefix_hits") > 0]
    effective_decisions = sum(numeric(row, "lcps_effective_priority_decisions") for row in priority_rows)
    suffix_invocations = sum(numeric(row, "lcps_full_cache_suffix_invocations") for row in priority_rows)
    suffix_fallbacks = sum(numeric(row, "lcps_full_cache_suffix_fallbacks") for row in priority_rows)
    batch_invocations = sum(numeric(row, "batch_lcps_invocations") for row in batch_rows)
    batch_candidates = sum(numeric(row, "batch_lcps_candidates_generated") for row in batch_rows)
    batch_selected = sum(numeric(row, "batch_lcps_candidates_selected") for row in batch_rows)
    batch_failures = sum(numeric(row, "batch_lcps_candidate_generation_failures") for row in batch_rows)
    batch_effective_decisions = sum(numeric(row, "batch_lcps_effective_batch_decisions") for row in batch_rows)
    inactive_signal = [
        row for row in cache_rows
        if numeric(row, "lcps_search_invocations") > 0
        and numeric(row, "lcps_checked_prefix_hits") == 0
        and numeric(row, "lcps_stale_prefix_hits") == 0
    ]
    present_modes = [mode for mode in ALL_MODES if any(row["mode"] == mode for row in rows)]

    lines = [
        "## Summary",
        "",
        "### Correctness",
        "",
        f"- Modes present: {', '.join(present_modes)}.",
        f"- Result mismatch groups across modes: {len(mismatches)}.",
        f"- ERROR rows: {len(errors)}; TIMEOUT rows: {len(timeouts)}.",
    ]
    bfs_dfs_rows = [row for row in rows if row["mode"] in {"BFS", "DFS"}]
    if bfs_dfs_rows:
        total_dup = sum(numeric(row, "duplicate_freshness_failures") for row in bfs_dfs_rows)
        total_failed = sum(numeric(row, "search_failed") for row in bfs_dfs_rows)
        lines.append(
            f"- BFS/DFS duplicate freshness failures: {int(total_dup)}; search_failed rows total: {int(total_failed)}."
        )
    else:
        lines.append("- BFS/DFS were not part of this run.")

    lines.extend([
        "",
        "### Activation",
        "",
        f"- LCPS-priority rows with search invocations > 0: {len(cache_with_invocations)} / {len(priority_rows)}.",
        f"- LCPS-priority rows with checked prefix queries > 0: {len(cache_with_checked_queries)} / {len(priority_rows)}.",
        f"- LCPS-priority rows with stale prefix queries > 0: {len(cache_with_stale_queries)} / {len(priority_rows)}.",
        f"- LCPS-priority rows with checked prefix hits > 0: {len(cache_with_checked_hits)} / {len(priority_rows)}.",
        f"- LCPS-priority rows with stale prefix hits > 0: {len(cache_with_stale_hits)} / {len(priority_rows)}.",
        f"- Total LCPS effective priority decisions: {int(effective_decisions)}.",
        f"- Total LCPS_FULL cache-suffix invocations/fallbacks: {int(suffix_invocations)} / {int(suffix_fallbacks)}.",
        f"- BatchLcpsInvocations: {int(batch_invocations)}.",
        f"- BatchLcpsCandidatesGenerated/Selected: {int(batch_candidates)} / {int(batch_selected)}.",
        f"- BatchLcpsCandidateGenerationFailures: {int(batch_failures)}.",
        f"- BatchLcpsEffectiveBatchDecisions: {int(batch_effective_decisions)}.",
    ])
    if inactive_signal:
        sample = ", ".join(f"{row['benchmark']}:{row['threads']}t" for row in inactive_signal[:8])
        lines.append(f"- Cache signal did not activate for {len(inactive_signal)} rows: {sample}.")

    lines.extend(["", "### Performance", ""])
    for mode in [mode for mode in CACHE_MODES if mode in present_modes]:
        lines.append(summarize_pair_metric(rows, "PAPER", mode, "runtime_ms", "runtime_ms"))
    if "LCPS" in present_modes and "LCPS_FULL" in present_modes:
        lines.append(summarize_pair_metric(rows, "LCPS", "LCPS_FULL", "runtime_ms", "runtime_ms"))
    if "LCPS" in present_modes and "BATCH_LCPS" in present_modes:
        lines.append(summarize_pair_metric(rows, "LCPS", "BATCH_LCPS", "runtime_ms", "runtime_ms"))
    if "LCPS_STALE_FIRST" in present_modes and "LCPS_FULL_STALE_FIRST" in present_modes:
        lines.append(summarize_pair_metric(rows, "LCPS_STALE_FIRST", "LCPS_FULL_STALE_FIRST", "runtime_ms", "runtime_ms"))

    lines.extend(["", "### Work", ""])
    for metric, label in [
            ("checked_paths", "checked_paths"),
            ("stale_paths", "stale_paths"),
            ("duplicate_freshness_failures", "duplicate freshness failures"),
            ("search_failed", "search_failed")]:
        for mode in [mode for mode in CACHE_MODES if mode in present_modes]:
            lines.append(summarize_pair_metric(rows, "PAPER", mode, metric, label))
        if "LCPS" in present_modes and "LCPS_FULL" in present_modes:
            lines.append(summarize_pair_metric(rows, "LCPS", "LCPS_FULL", metric, label))
        if "LCPS" in present_modes and "BATCH_LCPS" in present_modes:
            lines.append(summarize_pair_metric(rows, "LCPS", "BATCH_LCPS", metric, label))

    lines.extend(["", "### Batch Quality", ""])
    if batch_rows:
        avg_pool_sizes = [numeric(row, "batch_lcps_avg_candidate_pool_size") for row in batch_rows]
        avg_selected_sizes = [numeric(row, "batch_lcps_avg_selected_batch_size") for row in batch_rows]
        lines.extend([
            f"- Average candidate pool size across BATCH_LCPS rows: {average(avg_pool_sizes):.2f}.",
            f"- Average selected batch size across BATCH_LCPS rows: {average(avg_selected_sizes):.2f}.",
            f"- Total effective batch decisions: {int(batch_effective_decisions)}.",
        ])
        if "PAPER" in present_modes and "BATCH_LCPS" in present_modes:
            lines.append(summarize_pair_metric(rows, "PAPER", "BATCH_LCPS", "checked_paths", "checked_paths"))
            lines.append(summarize_pair_metric(rows, "PAPER", "BATCH_LCPS", "stale_paths", "stale_paths"))
    else:
        lines.append("- BATCH_LCPS was not part of this run.")

    adaptive_modes_present = [mode for mode in ADAPTIVE_DISPLAY_MODES.values() if mode in present_modes]
    lines.extend(["", "### Adaptive Trigger Comparison", ""])
    if adaptive_modes_present:
        for mode in adaptive_modes_present:
            lines.append(summarize_adaptive_trigger_mode(rows, mode))
    else:
        lines.append("- ADAPTIVE_BATCH_LCPS was not part of this run.")

    lines.extend(["", "### Divergence", ""])
    for mode in [mode for mode in CACHE_MODES if mode in present_modes]:
        lines.append(summarize_pair_metric(rows, "PAPER", mode, "avg_pairwise_prefix_lca_divergence", "avg divergence"))
    if "LCPS" in present_modes and "LCPS_FULL" in present_modes:
        lines.append(summarize_pair_metric(rows, "LCPS", "LCPS_FULL", "avg_pairwise_prefix_lca_divergence", "avg divergence"))
    if "LCPS" in present_modes and "BATCH_LCPS" in present_modes:
        lines.append(summarize_pair_metric(rows, "LCPS", "BATCH_LCPS", "avg_pairwise_prefix_lca_divergence", "avg divergence"))

    lines.extend([
        "",
        "### Interpretation",
        "",
    ])
    if cache_rows and effective_decisions == 0:
        lines.append("- Cache hits occurred only as instrumentation; effective priority decisions stayed at 0, so ordering did not change.")
    elif cache_rows:
        lines.append("- Cache hits and effective priority decisions are both present; compare work/runtime deltas to see whether changed ordering helped.")
    if suffix_invocations == 0 and any(row["mode"] in {"LCPS_FULL", "LCPS_FULL_STALE_FIRST"} for row in rows):
        lines.append("- LCPS_FULL did not find cache-covered suffix choices after active divergence in this run.")
    elif suffix_invocations > 0:
        lines.append("- LCPS_FULL continued cache-guided suffix exploration after active divergence; fallback count shows how often it reached uncovered history.")
    if batch_rows and batch_effective_decisions == 0:
        lines.append("- BATCH_LCPS generated candidates but selected the same first-k set as the naive generator in this run.")
    elif batch_rows:
        lines.append("- BATCH_LCPS changed at least one dispatched batch relative to naive first-k candidate dispatch.")
    lines.append("- LCPS does not need higher divergence to be useful; interpret divergence together with runtime, checked_paths, stale_paths, duplicate freshness failures, and effective priority decisions.")
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
        "This experiment compares PAPER, LCPS, BATCH_LCPS, and optional BFS/DFS path selection under parallel TraceAbstraction.",
        "",
        "Each unordered checked-path pair contributes normalized prefix-LCA divergence "
        "`1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.",
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
        lines.extend([f"### {benchmark_name}", "", table_for_rows(benchmark_rows), "", "#### Interpretation", ""])
        lines.extend(interpretation(benchmark_rows))
        lines.append("")

    lines.extend(
        [
            "## Interpretation Notes",
            "",
            "- Negative LCPS - PAPER runtime, checked_paths, stale_paths, and duplicate-failure deltas are improvements for that metric.",
            "- LCPS cache activation requires positive LCPS search invocations and positive checked/stale prefix queries. Hits show that the query keys matched cached run prefixes.",
            "- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.",
            "",
            "## Raw Data",
            "",
            "See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log` or `*-<mode>-stale-<on|off>-threads-*.log`.",
            "",
        ]
    )
    report_path.write_text("\n".join(lines), encoding="utf-8")
    return report_path


def main() -> None:
    args = parse_args()
    args.output_dir.mkdir(parents=True, exist_ok=True)

    benchmarks = all_benchmarks(args)
    modes = selected_modes(args)
    adaptive_triggers = selected_adaptive_trigger_modes(args)
    stale_values = selected_stale_tracking_values(args)
    initial_bfs_values = selected_initial_bfs_values(args)
    include_stale_suffix = len(stale_values) > 1
    include_initial_suffix = len(initial_bfs_values) > 1
    include_repeat_suffix = args.repeat > 1
    rows = []
    for benchmark in selected_benchmarks(args, benchmarks):
        for mode in modes:
            adaptive_variants = (
                [(adaptive_display_mode(trigger), trigger) for trigger in adaptive_triggers]
                if mode == "ADAPTIVE_BATCH_LCPS"
                else [(mode, "n/a")]
            )
            for display_mode, adaptive_trigger_mode in adaptive_variants:
                for stale_tracking in stale_tracking_values_for_mode(display_mode, stale_values):
                    for use_initial_bfs in initial_bfs_values:
                        for threads in selected_threads(args):
                            for repeat_index in range(args.repeat):
                                rows.append(run_one(args, benchmark, display_mode, threads, stale_tracking,
                                        use_initial_bfs, adaptive_trigger_mode, repeat_index,
                                        include_stale_suffix, include_initial_suffix, include_repeat_suffix))

    csv_path = write_csv(args.output_dir, rows)
    report_path = write_markdown(args.output_dir, rows, benchmarks)
    print(f"Wrote {csv_path}")
    print(f"Wrote {report_path}")


if __name__ == "__main__":
    main()
