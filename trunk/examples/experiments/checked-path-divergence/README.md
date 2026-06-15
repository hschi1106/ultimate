# Checked Path Divergence Experiment

This experiment evaluates whether larger parallel CEGAR worker counts and different trace-selection modes lead to more
dispersed paths being checked by TraceAbstraction.

`avgPairwisePrefixLcaDivergence` reports the average normalized prefix-LCA divergence of the actually checked
root-to-node paths.

For checked paths ending at `u` and `v`, define `d_u = depth(u)`, `d_v = depth(v)`, and
`l_uv = depth(LCA(u, v))`. Each pair contributes:

```text
prefixLcaDivergence(u, v) =
  1 - l_uv / min(d_u, d_v)

avgPairwisePrefixLcaDivergence =
  (2 / (N * (N - 1))) * sum_{i < j} prefixLcaDivergence(path_i, path_j)
```

If `min(d_u, d_v) == 0`, that pair contributes `0.0`. This treats a root-only path as an empty non-root prefix that is
fully shared. If fewer than two paths were checked, the average is reported as `0.0`.

Each pairwise contribution is intended to be in `[0, 1]`, and the reported average should normally be in that range:

- `0.0` means the shorter path is fully shared with the longer path.
- `1.0` means the recorded paths share no node below the root.

The total pairwise value is the sum of all normalized prefix-LCA divergence contributions. It can exceed `1.0` when
more than one unordered pair was checked.

## Benchmarks

The default benchmark set is intentionally small:

- `easy-small`: `trunk/examples/programs/toy/easy.bpl`
- `medium-loop`: `trunk/examples/programs/toy/tooDifficultLoopInvariant/DrAlban02-medium.bpl`
- `hidden-inequality`: `trunk/examples/programs/toy/tooDifficultLoopInvariant/HiddenInequality.bpl`
- `concurrent-fischer`: `trunk/examples/concurrent/bpl/regression/showcase/Fischer.bpl`

The set contains one easy testcase, one medium testcase, one harder loop-invariant testcase with more checked paths,
and one concurrent testcase where parallel trace abstraction is expected to produce several checked paths without
relying on timeout results.

## Maintained Scope

The maintained experiment scope contains these main modes:

- `PAPER`: the active-continuation selector from the paper baseline.
- `LCPS`: the same local selector with checked/stale prefix-cache counts as lexicographic tie breakers.
- `BATCH_LCPS`: an always-batch baseline that fills idle workers from a candidate pool selected by fixed batch
  priority.
- `ADAPTIVE_BATCH_LCPS`: the maintained adaptive mode.

`BFS` and `DFS` remain available only as basic baselines.

`ADAPTIVE_BATCH_LCPS` uses this fixed rule:

```text
availableSlots = threadLimit - runningThreads

if availableSlots >= adaptiveMinAvailableSlots
   and (firstDispatchInCurrentAbstraction or staleTriggerSinceLastDispatch):
    use BATCH_LCPS for this dispatch
    reset staleTriggerSinceLastDispatch
else:
    use PAPER one-by-one dispatch

after every ADAPTIVE_BATCH_LCPS dispatch decision:
    firstDispatchInCurrentAbstraction = false

after every successful refinement:
    firstDispatchInCurrentAbstraction = true

when completed worker work is no longer accepted by the current abstraction:
    staleTriggerSinceLastDispatch = true
```

This mode does not use cache data to prove safety or unsafety. It only changes how accepted runs are selected for
available workers.

### Removed Exploratory Variants

- `LCPS_FULL` was removed because suffix continuation rarely activated and carried timeout risk in prior experiments.
- Duplicate-only adaptive triggering was removed because it caused timeout risk in prior experiments.
- Search-failed and idle-slot adaptive triggers were removed because they did not activate meaningfully in prior
  experiments.
- Priority-order variants such as stale-first or work-first were removed to avoid heuristic explosion.

These variants were exploratory and are no longer part of the maintained implementation.

## Running

Build or unpack Ultimate first, then point the runner at the Ultimate launcher:

```bash
cd /path/to/ultimate
export ULTIMATE_CMD="/path/to/run-ultimate.sh"
python3 trunk/examples/experiments/checked-path-divergence/run_checked_path_divergence_experiment.py
```

By default the runner uses `1,2,4,8,16` workers and runs `PAPER,LCPS,BATCH_LCPS,ADAPTIVE_BATCH_LCPS`.

To run the BFS/DFS baselines as well:

```bash
python3 trunk/examples/experiments/checked-path-divergence/run_checked_path_divergence_experiment.py --include-basic
```

To choose modes explicitly:

```bash
python3 trunk/examples/experiments/checked-path-divergence/run_checked_path_divergence_experiment.py --modes BFS,DFS,PAPER,LCPS
```

To run one benchmark:

```bash
python3 trunk/examples/experiments/checked-path-divergence/run_checked_path_divergence_experiment.py --benchmark medium-loop
```

## Running SV-COMP ReachSafety subset

Use `run_svcomp_reachsafety_subset.py` when you have a JSON file with selected SV-COMP ReachSafety tasks. The JSON is
expected to contain `categories -> subcategories -> tasks`, where each task path is relative to the SV-COMP repository's
`c/` directory, for example `loops/array-1.c`.

The wrapper expands all tasks into a generated benchmark CSV and then calls
`run_checked_path_divergence_experiment.py`. For SV-COMP C ReachSafety tasks it uses:

- toolchain: `trunk/examples/toolchains/AutomizerC.xml`
- settings: `trunk/examples/Interactive/settings/SVCOMP2017/svcomp-Reach-32bit-Automizer_Default.epf`

Example:

```bash
python3 trunk/examples/experiments/checked-path-divergence/run_svcomp_reachsafety_subset.py \
  --benchmarks-json /path/to/benchmarks_run.json \
  --svcomp-root /path/to/sv-benchmarks \
  --threads 4 \
  --jobs 1 \
  --timeout 150 \
  --output-dir trunk/examples/experiments/checked-path-divergence/results/reachsafety189-t150-w4
```

Dry-run only generates the benchmark CSV and prints the runner command:

```bash
python3 trunk/examples/experiments/checked-path-divergence/run_svcomp_reachsafety_subset.py \
  --benchmarks-json /path/to/benchmarks_run.json \
  --svcomp-root /path/to/sv-benchmarks \
  --output-dir trunk/examples/experiments/checked-path-divergence/results/reachsafety189-t150-w4 \
  --dry-run
```

The wrapper writes:

- `generated/reachsafety-subset-benchmarks.csv`: generated runner benchmark list
- `checked-path-divergence-results.enriched.csv`: runner CSV enriched with `category`, `subcategory`, `task`, and
  `expected_result`
- `svcomp-reachsafety-subset-summary.md`: compact aggregate summary

## Outputs

Outputs are written to:

```text
trunk/examples/experiments/checked-path-divergence/results/
```

The runner writes:

- `checked-path-divergence-results.csv`: raw machine-readable data with columns
  `benchmark,mode,repeat_index,threads,result,runtime_ms,checked_paths,stale_paths,duplicate_freshness_failures,total_pairwise_prefix_lca_divergence,avg_pairwise_prefix_lca_divergence,refinements,search_failed`
- `checked-path-divergence-results.md`: grouped human-readable report
- `*-<mode>-threads-*.log`: raw Ultimate output for each run

## Metrics

- `benchmark`: benchmark identifier from this experiment.
- `mode`: configured trace selection mode, one of `BFS`, `DFS`, `PAPER`, `LCPS`, `BATCH_LCPS`, or
  `ADAPTIVE_BATCH_LCPS`.
- `repeat_index`: zero-based repeat number.
- `threads`: configured `Threadlimit for Parallel CEGAR`.
- `result`: parsed Ultimate verification result.
- `runtime_ms`: wall-clock runtime measured by the runner.
- `checked_paths`: number of paths that reached the real trace-checking step.
- `total_pairwise_prefix_lca_divergence`: sum of normalized prefix-LCA divergence over all unordered checked-path
  pairs. The value can exceed `1.0`.
- `avg_pairwise_prefix_lca_divergence`: average normalized prefix-LCA divergence over all unordered checked-path pairs.
  Higher values mean checked paths share less of their shorter prefixes. Values outside `[0, 1]` indicate that the
  underlying statistic aggregation should be inspected.
- `refinements`: existing iteration/refinement count if reported by Ultimate.
- `stale_paths`: existing stale/skipped-path count if reported by Ultimate; `0` means the current log did not expose such a counter.
- `duplicate_freshness_failures`: BFS/DFS/PAPER/LCPS attempts that found an active duplicate instead of a fresh worker trace.
- `search_failed`: search attempts that did not find a fresh counterexample while workers were still active.
- `lcps_checked_prefix_queries`, `lcps_checked_prefix_hits`, `lcps_stale_prefix_queries`,
  `lcps_stale_prefix_hits`: prefix-cache activation diagnostics for LCPS.
- `lcps_effective_priority_decisions`: successor sets where LCPS chose a different best successor than PAPER.
- `batch_lcps_*`: batch candidate generation, selection, and effective-decision diagnostics.
- `adaptive_batch_invocations`, `adaptive_batch_fallbacks`, `adaptive_triggered_by_first_fill`,
  `adaptive_triggered_by_stale`: diagnostics for the fixed first-fill-or-stale adaptive rule.

The experiment changes only configuration, logging/statistics collection, and result formatting. LCPS and batch modes
change only the order in which accepted runs are selected; they do not use the prefix cache to prove safety, prove
unsafety, or skip SMT checking/refinement.

The recorded paths are counterexample state sequences. State equality determines their common prefix, so paths from
different refinement iterations can appear maximally divergent when corresponding reconstructed states are not equal.
