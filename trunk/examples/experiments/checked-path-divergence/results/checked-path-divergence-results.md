# Checked Path Divergence Results

## Purpose

This experiment compares whether higher parallelization levels lead to more dispersed paths reaching the real trace checker, and whether that correlates with runtime, refinements, or stale work.

## Benchmark Selection

- `easy-small`: Small Boogie testcase from the toy suite; establishes the low-path-count baseline.
- `medium-loop`: Medium loop-invariant testcase that usually needs more than the trivial refinement pattern.
- `hidden-inequality`: Harder loop-invariant testcase with substantially more checked paths than the baseline cases, while still completing in a practical experiment run.
- `concurrent-fischer`: Concurrent showcase testcase that completes quickly but still produces several dispersed checked paths under parallel CEGAR.

## Results

### easy-small

| threads | result | runtime_ms | checked_paths | total_pairwise_tree_distance | avg_pairwise_tree_distance | refinements | stale_paths |
|---:|---|---:|---:|---:|---:|---:|---:|
| 1 | SAFE | 2063 | 1 | 0 | 0.0 | 1 | 0 |
| 2 | SAFE | 2155 | 1 | 0 | 0.0 | 1 | 0 |
| 4 | SAFE | 2139 | 1 | 0 | 0.0 | 1 | 0 |
| 8 | SAFE | 3024 | 1 | 0 | 0.0 | 1 | 0 |

From 1 to 8 threads, avgPairwiseTreeDistance did not change (0.0 -> 0.0).

### medium-loop

| threads | result | runtime_ms | checked_paths | total_pairwise_tree_distance | avg_pairwise_tree_distance | refinements | stale_paths |
|---:|---|---:|---:|---:|---:|---:|---:|
| 1 | SAFE | 1814 | 2 | 5 | 5.0 | 2 | 0 |
| 2 | SAFE | 2201 | 3 | 16 | 5.333333333333333 | 2 | 0 |
| 4 | SAFE | 2854 | 5 | 60 | 6.0 | 2 | 0 |
| 8 | SAFE | 4493 | 9 | 264 | 7.333333333333333 | 2 | 0 |

From 1 to 8 threads, avgPairwiseTreeDistance increased (5.0 -> 7.333333333333333).

### hidden-inequality

| threads | result | runtime_ms | checked_paths | total_pairwise_tree_distance | avg_pairwise_tree_distance | refinements | stale_paths |
|---:|---|---:|---:|---:|---:|---:|---:|
| 1 | SAFE | 2122 | 4 | 78 | 13.0 | 4 | 0 |
| 2 | SAFE | 2671 | 7 | 316 | 15.047619047619047 | 6 | 0 |
| 4 | SAFE | 3724 | 12 | 1157 | 17.53030303030303 | 9 | 0 |
| 8 | SAFE | 4781 | 16 | 2352 | 19.6 | 10 | 0 |

From 1 to 8 threads, avgPairwiseTreeDistance increased (13.0 -> 19.6).

### concurrent-fischer

| threads | result | runtime_ms | checked_paths | total_pairwise_tree_distance | avg_pairwise_tree_distance | refinements | stale_paths |
|---:|---|---:|---:|---:|---:|---:|---:|
| 1 | SAFE | 1828 | 9 | 1208 | 33.55555555555556 | 9 | 0 |
| 2 | SAFE | 1860 | 9 | 1208 | 33.55555555555556 | 9 | 0 |
| 4 | SAFE | 1754 | 9 | 1208 | 33.55555555555556 | 9 | 0 |
| 8 | SAFE | 1923 | 9 | 1208 | 33.55555555555556 | 9 | 0 |

From 1 to 8 threads, avgPairwiseTreeDistance did not change (33.55555555555556 -> 33.55555555555556).

## Overall Comparison

- `medium-loop` and `hidden-inequality` show increasing checked-path dispersion as thread count rises. In both cases, checked paths, total pairwise distance, average pairwise distance, and runtime all increase from 1 to 8 threads.
- `hidden-inequality` is the strongest non-timeout signal in this run: checked paths increase from 4 to 16, average distance from 13.0 to 19.6, and refinements from 4 to 10.
- `concurrent-fischer` produces high dispersion, but it is stable across thread counts in this configuration: all runs check 9 paths with average distance 33.55555555555556.
- No stale/skipped-path counter was exposed by these logs, so stale work correlation is inconclusive here.

## Interpretation Notes

- Increasing avgPairwiseTreeDistance means the actually checked paths ended farther apart in the exploration tree.
- Compare runtime and refinements against the distance columns per benchmark; positive correlation suggests path dispersion may be associated with additional useful or stale work.
- `stale_paths` is `0` when the current Ultimate log does not expose a stale/skipped-path counter.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the dispersion trend.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-threads-*.log`.
