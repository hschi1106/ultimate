# Checked Path Divergence Results

## Purpose

This experiment compares PAPER, LCPS, and optional BFS/DFS path selection under parallel TraceAbstraction.

Each unordered checked-path pair contributes normalized prefix-LCA divergence `1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.

## Summary

### Correctness

- PAPER/LCPS comparable pairs: 32.
- Result mismatches: 0.
- ERROR rows: 0; TIMEOUT rows: 0.
- BFS/DFS were not part of this run.

### LCPS Activation

- LCPS rows with search invocations > 0: 32 / 32.
- LCPS rows with checked prefix queries > 0: 32 / 32.
- LCPS rows with stale prefix queries > 0: 32 / 32.
- LCPS rows with checked prefix hits > 0: 31 / 32.
- LCPS rows with stale prefix hits > 0: 18 / 32.
- LCPS cache signal did not activate for 1 rows: k-examples-programs-20170304-DifficultPathPrograms-resultKnown-count_up_down.i_3-aa4a8d5f:2t.

### Performance

- Runtime wins/losses/ties for LCPS vs PAPER: 12/20/0.
- Average runtime delta: 215.2ms; median delta: 15.0ms.

### Work Reduction

- Average checked_paths delta: -0.09.
- Average stale_paths delta: -0.16.
- Average duplicate freshness failures delta: 0.00.

### Divergence

- Average avg-divergence delta: -0.0016; median delta: 0.0000.
- LCPS does not need higher divergence to be useful; interpret divergence together with runtime, checked_paths, stale_paths, and duplicate freshness failures.

## Benchmark Selection

- `trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/eureka_05.i_5.bpl.
- `k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/invert_string.i_4.bpl.
- `examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/interleave_bits.i_3.bpl.
- `trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-diamond2.i_4-22ecac6d`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/diamond2.i_4.bpl.
- `k-examples-programs-20170304-DifficultPathPrograms-resultKnown-count_up_down.i_3-aa4a8d5f`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/count_up_down.i_3.bpl.
- `trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-gauss_sum.i_3-f2583875`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/gauss_sum.i_3.bpl.
- `trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_1.i_2-db2cf3f1`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/jain_1.i_2.bpl.
- `trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_2.i_2-b9aa1d3f`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/jain_2.i_2.bpl.

## Results

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8

| mode | stale_tracking | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations |
|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | 2 | SAFE | 3169 | 11 | 6 | 2 | 54.464285714285715 | 0.9902597402597403 | 10 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 4 | SAFE | 3864 | 12 | 5 | 5 | 71.50992063492063 | 1.0834836459836459 | 10 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 8 | SAFE | 6166 | 26 | 15 | 6 | 325.5856227106227 | 1.0018019160326852 | 20 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 16 | SAFE | 7841 | 37 | 20 | 3 | 611.4853976905449 | 0.918146242778596 | 23 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | 2 | SAFE | 3180 | 10 | 4 | 2 | 44.464285714285715 | 0.9880952380952381 | 9 | 0 | 15 | 15 | 10 | 7 | 3 |
| LCPS | true | 4 | SAFE | 3889 | 13 | 5 | 6 | 83.50992063492063 | 1.070640008140008 | 11 | 0 | 73 | 73 | 59 | 19 | 10 |
| LCPS | true | 8 | SAFE | 5733 | 22 | 11 | 3 | 215.0233238983239 | 0.9308368999927441 | 15 | 0 | 133 | 133 | 114 | 56 | 18 |
| LCPS | true | 16 | SAFE | 8070 | 37 | 19 | 3 | 585.3341533466536 | 0.8788801101301105 | 22 | 0 | 366 | 366 | 321 | 130 | 34 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 2t: +11, 4t: +25, 8t: -433, 16t: +229 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 2t: -1, 4t: +1, 8t: -4, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 2t: -2, 4t: +0, 8t: -4, 16t: -1 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 2t: +0, 4t: +1, 8t: -3, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 2t: -0.0021645, 4t: -0.0128436, 8t: -0.070965, 16t: -0.0392661 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_queries: 2t: +15, 4t: +73, 8t: +133, 16t: +366 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_queries: 2t: +15, 4t: +73, 8t: +133, 16t: +366 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_hits: 2t: +10, 4t: +59, 8t: +114, 16t: +321 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_hits: 2t: +7, 4t: +19, 8t: +56, 16t: +130 (LCPS - PAPER).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb

| mode | stale_tracking | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations |
|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | 2 | SAFE | 3198 | 7 | 1 | 3 | 20.75 | 0.9880952380952381 | 6 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 4 | SAFE | 4123 | 14 | 4 | 7 | 88.35 | 0.9708791208791209 | 11 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 8 | SAFE | 6025 | 23 | 11 | 11 | 237.9718253968254 | 0.9406001003827091 | 16 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 16 | SAFE | 9050 | 35 | 15 | 12 | 539.1660173160174 | 0.9061613736403653 | 20 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | 2 | SAFE | 3167 | 7 | 1 | 3 | 20.75 | 0.9880952380952381 | 6 | 0 | 11 | 11 | 8 | 6 | 3 |
| LCPS | true | 4 | SAFE | 3917 | 13 | 5 | 8 | 75.93333333333334 | 0.9735042735042736 | 10 | 0 | 68 | 68 | 57 | 30 | 11 |
| LCPS | true | 8 | SAFE | 6741 | 24 | 12 | 10 | 260.63849206349204 | 0.9443423625488842 | 17 | 0 | 203 | 203 | 166 | 70 | 22 |
| LCPS | true | 16 | SAFE | 8607 | 35 | 15 | 12 | 539.1660173160174 | 0.9061613736403653 | 20 | 0 | 487 | 487 | 419 | 102 | 34 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 2t: -31, 4t: -206, 8t: +716, 16t: -443 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 2t: +0, 4t: -1, 8t: +1, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 2t: +0, 4t: +1, 8t: +1, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 2t: +0, 4t: +1, 8t: -1, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 2t: +0, 4t: +0.00262515, 8t: +0.00374226, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_queries: 2t: +11, 4t: +68, 8t: +203, 16t: +487 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_queries: 2t: +11, 4t: +68, 8t: +203, 16t: +487 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_hits: 2t: +8, 4t: +57, 8t: +166, 16t: +419 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_hits: 2t: +6, 4t: +30, 8t: +70, 16t: +102 (LCPS - PAPER).

### examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20

| mode | stale_tracking | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations |
|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | 2 | UNKNOWN | 3382 | 7 | 2 | 2 | 20.666666666666668 | 0.9841269841269842 | 5 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 4 | UNKNOWN | 4347 | 11 | 5 | 4 | 51.55384615384615 | 0.9373426573426573 | 7 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 8 | UNKNOWN | 6198 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 16 | UNKNOWN | 5045 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | 2 | UNKNOWN | 3384 | 7 | 2 | 2 | 20.666666666666668 | 0.9841269841269842 | 5 | 0 | 12 | 12 | 8 | 2 | 2 |
| LCPS | true | 4 | UNKNOWN | 4358 | 11 | 5 | 5 | 52.4 | 0.9527272727272728 | 7 | 0 | 58 | 58 | 45 | 8 | 7 |
| LCPS | true | 8 | UNKNOWN | 6217 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 227 | 227 | 189 | 25 | 14 |
| LCPS | true | 16 | UNKNOWN | 5037 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 463 | 463 | 340 | 0 | 15 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 2t: +2, 4t: +11, 8t: +19, 16t: -8 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 2t: +0, 4t: +1, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 2t: +0, 4t: +0.0153846, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_queries: 2t: +12, 4t: +58, 8t: +227, 16t: +463 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_queries: 2t: +12, 4t: +58, 8t: +227, 16t: +463 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_hits: 2t: +8, 4t: +45, 8t: +189, 16t: +340 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_hits: 2t: +2, 4t: +8, 8t: +25, 16t: +0 (LCPS - PAPER).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-diamond2.i_4-22ecac6d

| mode | stale_tracking | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations |
|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | 2 | SAFE | 3398 | 7 | 3 | 2 | 20.666666666666668 | 0.9841269841269842 | 6 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 4 | SAFE | 4126 | 9 | 2 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 8 | SAFE | 5354 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 16 | SAFE | 8634 | 22 | 4 | 6 | 149.90722414840062 | 0.6489490222874486 | 7 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | 2 | SAFE | 3435 | 7 | 2 | 2 | 20.666666666666668 | 0.9841269841269842 | 6 | 0 | 13 | 13 | 9 | 5 | 2 |
| LCPS | true | 4 | SAFE | 4131 | 9 | 2 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 59 | 59 | 44 | 4 | 7 |
| LCPS | true | 8 | SAFE | 5303 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 223 | 223 | 184 | 20 | 13 |
| LCPS | true | 16 | SAFE | 8815 | 22 | 5 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 711 | 711 | 580 | 20 | 21 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 2t: +37, 4t: +5, 8t: -51, 16t: +181 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 2t: -1, 4t: +0, 8t: +0, 16t: +1 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 2t: +0, 4t: +0, 8t: +0, 16t: +1 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 2t: +0, 4t: +0, 8t: +0, 16t: +0.00396825 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_queries: 2t: +13, 4t: +59, 8t: +223, 16t: +711 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_queries: 2t: +13, 4t: +59, 8t: +223, 16t: +711 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_hits: 2t: +9, 4t: +44, 8t: +184, 16t: +580 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_hits: 2t: +5, 4t: +4, 8t: +20, 16t: +20 (LCPS - PAPER).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-count_up_down.i_3-aa4a8d5f

| mode | stale_tracking | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations |
|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | 2 | SAFE | 6303 | 7 | 3 | 1 | 20.666666666666668 | 0.9841269841269842 | 6 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 4 | SAFE | 126391 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 8 | SAFE | 5275 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 16 | SAFE | 7675 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | 2 | SAFE | 6337 | 7 | 3 | 1 | 20.666666666666668 | 0.9841269841269842 | 6 | 0 | 3 | 3 | 0 | 0 | 1 |
| LCPS | true | 4 | SAFE | 127042 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 58 | 58 | 45 | 8 | 7 |
| LCPS | true | 8 | SAFE | 5479 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 162 | 162 | 128 | 3 | 10 |
| LCPS | true | 16 | SAFE | 8113 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 594 | 594 | 468 | 3 | 18 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 2t: +34, 4t: +651, 8t: +204, 16t: +438 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_queries: 2t: +3, 4t: +58, 8t: +162, 16t: +594 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_queries: 2t: +3, 4t: +58, 8t: +162, 16t: +594 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_hits: 2t: +0, 4t: +45, 8t: +128, 16t: +468 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_hits: 2t: +0, 4t: +8, 8t: +3, 16t: +3 (LCPS - PAPER).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-gauss_sum.i_3-f2583875

| mode | stale_tracking | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations |
|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | 2 | SAFE | 2652 | 4 | 1 | 2 | 8.666666666666668 | 1.4444444444444446 | 4 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 4 | SAFE | 3371 | 6 | 1 | 3 | 12.4 | 0.8266666666666667 | 4 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 8 | SAFE | 5311 | 11 | 1 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 16 | SAFE | 8701 | 19 | 1 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | 2 | SAFE | 2617 | 4 | 1 | 2 | 8.666666666666668 | 1.4444444444444446 | 4 | 0 | 8 | 8 | 4 | 0 | 2 |
| LCPS | true | 4 | SAFE | 3581 | 7 | 1 | 3 | 18.4 | 0.8761904761904761 | 4 | 0 | 40 | 40 | 29 | 0 | 5 |
| LCPS | true | 8 | SAFE | 5309 | 11 | 1 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 168 | 168 | 132 | 0 | 10 |
| LCPS | true | 16 | SAFE | 8744 | 19 | 1 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 600 | 600 | 474 | 0 | 18 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 2t: -35, 4t: +210, 8t: -2, 16t: +43 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 2t: +0, 4t: +1, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 2t: +0, 4t: +0.0495238, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_queries: 2t: +8, 4t: +40, 8t: +168, 16t: +600 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_queries: 2t: +8, 4t: +40, 8t: +168, 16t: +600 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_hits: 2t: +4, 4t: +29, 8t: +132, 16t: +474 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_hits: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_1.i_2-db2cf3f1

| mode | stale_tracking | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations |
|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | 2 | SAFE | 50422 | 3 | 0 | 2 | 2.5 | 0.8333333333333334 | 2 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 4 | SAFE | 39331 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 8 | SAFE | 41056 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 16 | SAFE | 44536 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | 2 | SAFE | 50459 | 3 | 0 | 2 | 2.5 | 0.8333333333333334 | 2 | 0 | 10 | 10 | 5 | 0 | 2 |
| LCPS | true | 4 | SAFE | 39271 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 36 | 36 | 25 | 0 | 4 |
| LCPS | true | 8 | SAFE | 40971 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 150 | 150 | 121 | 0 | 8 |
| LCPS | true | 16 | SAFE | 45947 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 618 | 618 | 505 | 0 | 16 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 2t: +37, 4t: -60, 8t: -85, 16t: +1411 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_queries: 2t: +10, 4t: +36, 8t: +150, 16t: +618 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_queries: 2t: +10, 4t: +36, 8t: +150, 16t: +618 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_hits: 2t: +5, 4t: +25, 8t: +121, 16t: +505 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_hits: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_2.i_2-b9aa1d3f

| mode | stale_tracking | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations |
|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | 2 | SAFE | 46564 | 3 | 0 | 2 | 2.5 | 0.8333333333333334 | 2 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 4 | SAFE | 51472 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 8 | SAFE | 53125 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 16 | SAFE | 56681 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | 2 | SAFE | 50505 | 3 | 0 | 2 | 2.5 | 0.8333333333333334 | 2 | 0 | 10 | 10 | 5 | 0 | 2 |
| LCPS | true | 4 | SAFE | 51392 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 36 | 36 | 25 | 0 | 4 |
| LCPS | true | 8 | SAFE | 53088 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 150 | 150 | 121 | 0 | 8 |
| LCPS | true | 16 | SAFE | 56832 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 618 | 618 | 505 | 0 | 16 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 2t: +3941, 4t: -80, 8t: -37, 16t: +151 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_queries: 2t: +10, 4t: +36, 8t: +150, 16t: +618 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_queries: 2t: +10, 4t: +36, 8t: +150, 16t: +618 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_hits: 2t: +5, 4t: +25, 8t: +121, 16t: +505 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_hits: 2t: +0, 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).

## Interpretation Notes

- Negative LCPS - PAPER runtime, checked_paths, stale_paths, and duplicate-failure deltas are improvements for that metric.
- LCPS cache activation requires positive LCPS search invocations and positive checked/stale prefix queries. Hits show that the query keys matched cached run prefixes.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log` or `*-<mode>-stale-<on|off>-threads-*.log`.
