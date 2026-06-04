# Checked Path Divergence Results

## Purpose

This experiment compares whether higher parallelization levels lead to more dispersed paths reaching the real trace checker, and whether that correlates with runtime, refinements, or stale work.

Each unordered pair contributes normalized prefix-LCA divergence `1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.

## Benchmark Selection

- `easy-small`: Small Boogie testcase from the toy suite; establishes the low-path-count baseline.
- `medium-loop`: Medium loop-invariant testcase that usually needs more than the trivial refinement pattern.
- `hidden-inequality`: Harder loop-invariant testcase with substantially more checked paths than the baseline cases, while still completing in a practical experiment run.
- `concurrent-fischer`: Concurrent showcase testcase that completes quickly but still produces several dispersed checked paths under parallel CEGAR.

## Results

### easy-small

| threads | result | runtime_ms | checked_paths | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | stale_paths |
|---:|---|---:|---:|---:|---:|---:|---:|
| 1 | SAFE | 1481 | 1 | 0.0 | 0.0 | 1 | 0 |
| 2 | SAFE | 1630 | 1 | 0.0 | 0.0 | 1 | 0 |
| 4 | SAFE | 2083 | 1 | 0.0 | 0.0 | 1 | 0 |
| 8 | SAFE | 2901 | 1 | 0.0 | 0.0 | 1 | 0 |

From 1 to 8 threads, avgPairwisePrefixLcaDivergence did not change (0.0 -> 0.0).

### medium-loop

| threads | result | runtime_ms | checked_paths | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | stale_paths |
|---:|---|---:|---:|---:|---:|---:|---:|
| 1 | SAFE | 1786 | 2 | 1.0 | 1.0 | 2 | 0 |
| 2 | SAFE | 2159 | 3 | 2.5 | 0.8333333333333334 | 2 | 0 |
| 4 | SAFE | 2991 | 5 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 |
| 8 | SAFE | 4452 | 9 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 |

From 1 to 8 threads, avgPairwisePrefixLcaDivergence decreased (1.0 -> 0.4572420634920635).

### hidden-inequality

| threads | result | runtime_ms | checked_paths | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | stale_paths |
|---:|---|---:|---:|---:|---:|---:|---:|
| 1 | SAFE | 2080 | 4 | 6.0 | 1.0 | 4 | 0 |
| 2 | SAFE | 2669 | 7 | 20.666666666666668 | 0.9841269841269842 | 6 | 0 |
| 4 | SAFE | 3722 | 12 | 63.4 | 0.9606060606060606 | 9 | 0 |
| 8 | SAFE | 4751 | 16 | 104.57936507936509 | 0.871494708994709 | 10 | 0 |

From 1 to 8 threads, avgPairwisePrefixLcaDivergence decreased (1.0 -> 0.871494708994709).

### concurrent-fischer

| threads | result | runtime_ms | checked_paths | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | stale_paths |
|---:|---|---:|---:|---:|---:|---:|---:|
| 1 | SAFE | 1780 | 9 | 36.0 | 1.0 | 9 | 0 |
| 2 | SAFE | 1736 | 9 | 36.0 | 1.0 | 9 | 0 |
| 4 | SAFE | 1725 | 9 | 36.0 | 1.0 | 9 | 0 |
| 8 | SAFE | 1690 | 9 | 36.0 | 1.0 | 9 | 0 |

From 1 to 8 threads, avgPairwisePrefixLcaDivergence did not change (1.0 -> 1.0).

## Interpretation Notes

- Increasing avgPairwisePrefixLcaDivergence means the checked paths share less of their shorter root-to-node prefix.
- Compare runtime and refinements against the divergence columns per benchmark; positive correlation suggests path dispersion may be associated with additional useful or stale work.
- `stale_paths` is `0` when the current Ultimate log does not expose a stale/skipped-path counter.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the dispersion trend.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-threads-*.log`.
