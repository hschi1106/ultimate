# Checked Path Divergence Results

## Purpose

This experiment compares PAPER, LCPS, and optional BFS/DFS path selection under parallel TraceAbstraction.

Each unordered checked-path pair contributes normalized prefix-LCA divergence `1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.

## Benchmark Selection

- `easy-small`: Small Boogie testcase from the toy suite; establishes the low-path-count baseline.
- `medium-loop`: Medium loop-invariant testcase that usually needs more than the trivial refinement pattern.
- `hidden-inequality`: Harder loop-invariant testcase with substantially more checked paths than the baseline cases.
- `concurrent-fischer`: Concurrent showcase testcase for parallel trace-abstraction behavior.

## Results

### easy-small

| mode | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed |
|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|
| LCPS | 1 | SAFE | 1491 | 1 | 0 | 0 | 0.0 | 0.0 | 1 | 0 |
| LCPS | 2 | SAFE | 1714 | 1 | 0 | 1 | 0.0 | 0.0 | 1 | 1 |
| LCPS | 4 | SAFE | 2158 | 1 | 0 | 1 | 0.0 | 0.0 | 1 | 1 |
| LCPS | 8 | SAFE | 3008 | 1 | 0 | 1 | 0.0 | 0.0 | 1 | 1 |
| LCPS | 16 | SAFE | 4686 | 1 | 0 | 1 | 0.0 | 0.0 | 1 | 1 |
| PAPER | 1 | SAFE | 1511 | 1 | 0 | 0 | 0.0 | 0.0 | 1 | 0 |
| PAPER | 2 | SAFE | 1727 | 1 | 0 | 1 | 0.0 | 0.0 | 1 | 1 |
| PAPER | 4 | SAFE | 2132 | 1 | 0 | 1 | 0.0 | 0.0 | 1 | 1 |
| PAPER | 8 | SAFE | 2993 | 1 | 0 | 1 | 0.0 | 0.0 | 1 | 1 |
| PAPER | 16 | SAFE | 4679 | 1 | 0 | 1 | 0.0 | 0.0 | 1 | 1 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 1t: -20, 2t: -13, 4t: +26, 8t: +15, 16t: +7 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 1t: +0, 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 1t: +0, 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 1t: +0, 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).

### medium-loop

| mode | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed |
|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|
| LCPS | 1 | SAFE | 1823 | 2 | 0 | 0 | 1.0 | 1.0 | 2 | 0 |
| LCPS | 2 | SAFE | 2227 | 3 | 0 | 2 | 2.5 | 0.8333333333333334 | 2 | 0 |
| LCPS | 4 | SAFE | 3090 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 |
| LCPS | 8 | SAFE | 4542 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 |
| LCPS | 16 | SAFE | 6721 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 |
| PAPER | 1 | SAFE | 1842 | 2 | 0 | 0 | 1.0 | 1.0 | 2 | 0 |
| PAPER | 2 | SAFE | 2211 | 3 | 0 | 2 | 2.5 | 0.8333333333333334 | 2 | 0 |
| PAPER | 4 | SAFE | 2832 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 |
| PAPER | 8 | SAFE | 4519 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 |
| PAPER | 16 | SAFE | 6922 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 1t: -19, 2t: +16, 4t: +258, 8t: +23, 16t: -201 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 1t: +0, 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 1t: +0, 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 1t: +0, 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).

### hidden-inequality

| mode | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed |
|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|
| LCPS | 1 | SAFE | 2193 | 4 | 0 | 0 | 6.0 | 1.0 | 4 | 0 |
| LCPS | 2 | SAFE | 2739 | 7 | 3 | 3 | 20.666666666666668 | 0.9841269841269842 | 6 | 0 |
| LCPS | 4 | SAFE | 3784 | 12 | 6 | 6 | 63.4 | 0.9606060606060606 | 9 | 0 |
| LCPS | 8 | SAFE | 4810 | 16 | 6 | 9 | 104.57936507936509 | 0.871494708994709 | 10 | 0 |
| LCPS | 16 | SAFE | 6533 | 20 | 6 | 9 | 109.82389081506727 | 0.5780204779740383 | 10 | 4 |
| PAPER | 1 | SAFE | 2177 | 4 | 0 | 0 | 6.0 | 1.0 | 4 | 0 |
| PAPER | 2 | SAFE | 2729 | 7 | 3 | 3 | 20.666666666666668 | 0.9841269841269842 | 6 | 0 |
| PAPER | 4 | SAFE | 3790 | 12 | 6 | 6 | 63.4 | 0.9606060606060606 | 9 | 0 |
| PAPER | 8 | SAFE | 4826 | 16 | 6 | 9 | 104.57936507936509 | 0.871494708994709 | 9 | 0 |
| PAPER | 16 | SAFE | 6529 | 20 | 5 | 8 | 109.82389081506727 | 0.5780204779740383 | 9 | 3 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 1t: +16, 2t: +10, 4t: -6, 8t: -16, 16t: +4 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 1t: +0, 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 1t: +0, 2t: +0, 4t: +0, 8t: +0, 16t: +1 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 1t: +0, 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).

### concurrent-fischer

| mode | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed |
|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|
| LCPS | 1 | SAFE | 1811 | 9 | 0 | 0 | 36.0 | 1.0 | 9 | 0 |
| LCPS | 2 | SAFE | 1816 | 9 | 0 | 0 | 36.0 | 1.0 | 9 | 0 |
| LCPS | 4 | SAFE | 1818 | 9 | 0 | 0 | 36.0 | 1.0 | 9 | 0 |
| LCPS | 8 | SAFE | 1786 | 9 | 0 | 0 | 36.0 | 1.0 | 9 | 0 |
| LCPS | 16 | SAFE | 1754 | 9 | 0 | 0 | 36.0 | 1.0 | 9 | 0 |
| PAPER | 1 | SAFE | 1796 | 9 | 0 | 0 | 36.0 | 1.0 | 9 | 0 |
| PAPER | 2 | SAFE | 1829 | 9 | 0 | 0 | 36.0 | 1.0 | 9 | 0 |
| PAPER | 4 | SAFE | 1822 | 9 | 0 | 0 | 36.0 | 1.0 | 9 | 0 |
| PAPER | 8 | SAFE | 1789 | 9 | 0 | 0 | 36.0 | 1.0 | 9 | 0 |
| PAPER | 16 | SAFE | 1813 | 9 | 0 | 0 | 36.0 | 1.0 | 9 | 0 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 1t: +15, 2t: -13, 4t: -4, 8t: -3, 16t: -59 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 1t: +0, 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 1t: +0, 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 1t: +0, 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).

## Interpretation Notes

- Negative LCPS - PAPER runtime, checked_paths, or stale_paths deltas are improvements for that metric.
- LCPS does not need the highest avg divergence to be useful; compare runtime, stale_paths, checked_paths, and result consistency.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log`.
