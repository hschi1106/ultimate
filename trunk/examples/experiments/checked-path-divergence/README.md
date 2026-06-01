# Checked Path Divergence Experiment

This experiment evaluates whether larger parallel CEGAR worker counts lead to more dispersed paths being checked by TraceAbstraction.

`avgPairwiseTreeDistance` measures how far apart the actually checked root-to-node paths are in the current exploration tree:

```text
avgPairwiseTreeDistance =
  (2 / (N * (N - 1))) * sum_{i < j} dist(path_i, path_j)
```

If fewer than two paths were checked, the average is reported as `0.0`.

## Benchmarks

The default benchmark set is intentionally small:

- `easy-small`: `trunk/examples/programs/toy/easy.bpl`
- `medium-loop`: `trunk/examples/programs/toy/tooDifficultLoopInvariant/DrAlban02-medium.bpl`
- `hidden-inequality`: `trunk/examples/programs/toy/tooDifficultLoopInvariant/HiddenInequality.bpl`
- `concurrent-fischer`: `trunk/examples/concurrent/bpl/regression/showcase/Fischer.bpl`

The set contains one easy testcase, one medium testcase, one harder loop-invariant testcase with higher checked-path dispersion, and one concurrent testcase where parallel trace abstraction is expected to produce several checked paths without relying on timeout results.

## Running

Build or unpack Ultimate first, then point the runner at the Ultimate launcher:

```bash
cd /path/to/ultimate
export ULTIMATE_CMD="/path/to/run-ultimate.sh"
python3 trunk/examples/experiments/checked-path-divergence/run_checked_path_divergence_experiment.py
```

By default the runner uses `1,2,4,8` workers. To include 16:

```bash
python3 trunk/examples/experiments/checked-path-divergence/run_checked_path_divergence_experiment.py --include-16
```

To run one benchmark:

```bash
python3 trunk/examples/experiments/checked-path-divergence/run_checked_path_divergence_experiment.py --benchmark medium-loop
```

## Outputs

Outputs are written to:

```text
trunk/examples/experiments/checked-path-divergence/results/
```

The runner writes:

- `checked-path-divergence-results.csv`: raw machine-readable data with columns
  `benchmark,threads,result,runtime_ms,checked_paths,total_pairwise_tree_distance,avg_pairwise_tree_distance,refinements,stale_paths`
- `checked-path-divergence-results.md`: grouped human-readable report
- `*-threads-*.log`: raw Ultimate output for each run

## Metrics

- `benchmark`: benchmark identifier from this experiment.
- `threads`: configured `Threadlimit for Parallel CEGAR`.
- `result`: parsed Ultimate verification result.
- `runtime_ms`: wall-clock runtime measured by the runner.
- `checked_paths`: number of paths that reached the real trace-checking step.
- `total_pairwise_tree_distance`: sum of pairwise tree distances among checked paths.
- `avg_pairwise_tree_distance`: average pairwise tree distance; higher values mean checked paths were more dispersed.
- `refinements`: existing iteration/refinement count if reported by Ultimate.
- `stale_paths`: existing stale/skipped-path count if reported by Ultimate; `0` means the current log did not expose such a counter.

The experiment changes only configuration, logging/statistics collection, and result formatting. It does not alter verifier behavior to influence the metric.
