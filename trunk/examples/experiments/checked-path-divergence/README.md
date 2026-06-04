# Checked Path Divergence Experiment

This experiment evaluates whether larger parallel CEGAR worker counts lead to more dispersed paths being checked by TraceAbstraction.

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

Each pairwise contribution and `avgPairwisePrefixLcaDivergence` are in `[0, 1]`:

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

## Representative Results

The checked-in CSV and Markdown report contain results from the latest run of this experiment.

Interpret `avg_pairwise_prefix_lca_divergence` as the average fraction of the shorter path that lies below the pair's
LCA. Higher values mean the checked paths share less of their shorter prefixes.

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
  `benchmark,threads,result,runtime_ms,checked_paths,total_pairwise_prefix_lca_divergence,avg_pairwise_prefix_lca_divergence,refinements,stale_paths`
- `checked-path-divergence-results.md`: grouped human-readable report
- `*-threads-*.log`: raw Ultimate output for each run

## Metrics

- `benchmark`: benchmark identifier from this experiment.
- `threads`: configured `Threadlimit for Parallel CEGAR`.
- `result`: parsed Ultimate verification result.
- `runtime_ms`: wall-clock runtime measured by the runner.
- `checked_paths`: number of paths that reached the real trace-checking step.
- `total_pairwise_prefix_lca_divergence`: sum of normalized prefix-LCA divergence over all unordered checked-path
  pairs. The value can exceed `1.0`.
- `avg_pairwise_prefix_lca_divergence`: average normalized prefix-LCA divergence over all unordered checked-path pairs.
  It is in `[0, 1]`; higher values mean checked paths share less of their shorter prefixes.
- `refinements`: existing iteration/refinement count if reported by Ultimate.
- `stale_paths`: existing stale/skipped-path count if reported by Ultimate; `0` means the current log did not expose such a counter.

The experiment changes only configuration, logging/statistics collection, and result formatting. It does not alter verifier behavior to influence the metric.

The recorded paths are counterexample state sequences. State equality determines their common prefix, so paths from
different refinement iterations can appear maximally divergent when corresponding reconstructed states are not equal.
