# Checked Path Divergence Results

## Purpose

This experiment compares PAPER, LCPS, and optional BFS/DFS path selection under parallel TraceAbstraction.

Each unordered checked-path pair contributes normalized prefix-LCA divergence `1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.

## Summary

### Correctness

- PAPER/LCPS comparable pairs: 6.
- Result mismatches: 0.
- ERROR rows: 0; TIMEOUT rows: 0.
- BFS/DFS duplicate freshness failures: 72; search_failed rows total: 72.

### LCPS Activation

- LCPS rows with search invocations > 0: 6 / 6.
- LCPS rows with checked prefix queries > 0: 6 / 6.
- LCPS rows with stale prefix queries > 0: 6 / 6.
- LCPS rows with checked prefix hits > 0: 6 / 6.
- LCPS rows with stale prefix hits > 0: 5 / 6.

### Performance

- Runtime wins/losses/ties for LCPS vs PAPER: 3/3/0.
- Average runtime delta: 291.3ms; median delta: 116.5ms.

### Work Reduction

- Average checked_paths delta: 0.50.
- Average stale_paths delta: 1.17.
- Average duplicate freshness failures delta: 1.33.

### Divergence

- Average avg-divergence delta: 0.0497; median delta: 0.0279.
- LCPS does not need higher divergence to be useful; interpret divergence together with runtime, checked_paths, stale_paths, and duplicate freshness failures.

## Benchmark Selection

- `trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/eureka_05.i_5.bpl.
- `k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/invert_string.i_4.bpl.
- `examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/interleave_bits.i_3.bpl.

## Results

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8

| mode | stale_tracking | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations |
|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| BFS | true | 8 | SAFE | 4091 | 7 | 0 | 7 | 21.0 | 1.0 | 7 | 7 | 0 | 0 | 0 | 0 | 0 |
| BFS | true | 16 | SAFE | 5768 | 7 | 0 | 7 | 21.0 | 1.0 | 7 | 7 | 0 | 0 | 0 | 0 | 0 |
| DFS | true | 8 | SAFE | 4089 | 7 | 0 | 7 | 21.0 | 1.0 | 7 | 7 | 0 | 0 | 0 | 0 | 0 |
| DFS | true | 16 | SAFE | 5759 | 7 | 0 | 7 | 21.0 | 1.0 | 7 | 7 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 8 | SAFE | 4868 | 19 | 7 | 3 | 149.1035298035298 | 0.8719504666873088 | 12 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 16 | SAFE | 8707 | 39 | 21 | 5 | 696.4238592290064 | 0.939843264816473 | 25 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | 8 | SAFE | 6593 | 28 | 16 | 8 | 362.29197191697193 | 0.9584443701507194 | 21 | 0 | 195 | 195 | 175 | 87 | 23 |
| LCPS | true | 16 | SAFE | 7904 | 32 | 19 | 4 | 543.3748584748587 | 1.0955138275702796 | 22 | 0 | 365 | 365 | 309 | 90 | 34 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t: +1725, 16t: -803 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t: +9, 16t: -7 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t: +9, 16t: -2 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t: +5, 16t: -1 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t: +0.0864939, 16t: +0.155671 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_queries: 8t: +195, 16t: +365 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_queries: 8t: +195, 16t: +365 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_hits: 8t: +175, 16t: +309 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_hits: 8t: +87, 16t: +90 (LCPS - PAPER).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb

| mode | stale_tracking | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations |
|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| BFS | true | 8 | SAFE | 4718 | 6 | 0 | 6 | 15.0 | 1.0 | 6 | 6 | 0 | 0 | 0 | 0 | 0 |
| BFS | true | 16 | SAFE | 6431 | 6 | 0 | 6 | 15.0 | 1.0 | 6 | 6 | 0 | 0 | 0 | 0 | 0 |
| DFS | true | 8 | SAFE | 4675 | 6 | 0 | 6 | 15.0 | 1.0 | 6 | 6 | 0 | 0 | 0 | 0 | 0 |
| DFS | true | 16 | SAFE | 6397 | 6 | 0 | 6 | 15.0 | 1.0 | 6 | 6 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 8 | SAFE | 7146 | 26 | 14 | 12 | 309.6384920634921 | 0.9527338217338218 | 19 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 16 | SAFE | 8756 | 34 | 14 | 8 | 503.711471861472 | 0.8978814115177753 | 19 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | 8 | SAFE | 7402 | 26 | 13 | 14 | 324.6384920634921 | 0.998887667887668 | 20 | 0 | 262 | 262 | 220 | 119 | 26 |
| LCPS | true | 16 | SAFE | 9400 | 35 | 15 | 10 | 539.9938672438673 | 0.9075527180569198 | 20 | 0 | 486 | 486 | 417 | 97 | 34 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t: +256, 16t: +644 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t: +0, 16t: +1 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t: -1, 16t: +1 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t: +2, 16t: +2 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t: +0.0461538, 16t: +0.00967131 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_queries: 8t: +262, 16t: +486 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_queries: 8t: +262, 16t: +486 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_hits: 8t: +220, 16t: +417 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_hits: 8t: +119, 16t: +97 (LCPS - PAPER).

### examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20

| mode | stale_tracking | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations |
|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| BFS | true | 8 | UNKNOWN | 4268 | 5 | 0 | 5 | 10.0 | 1.0 | 4 | 5 | 0 | 0 | 0 | 0 | 0 |
| BFS | true | 16 | UNKNOWN | 5958 | 5 | 0 | 5 | 10.0 | 1.0 | 4 | 5 | 0 | 0 | 0 | 0 | 0 |
| DFS | true | 8 | UNKNOWN | 4235 | 5 | 0 | 5 | 10.0 | 1.0 | 4 | 5 | 0 | 0 | 0 | 0 | 0 |
| DFS | true | 16 | UNKNOWN | 5929 | 5 | 0 | 5 | 10.0 | 1.0 | 4 | 5 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 8 | UNKNOWN | 6195 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 16 | UNKNOWN | 5065 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | 8 | UNKNOWN | 6172 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 227 | 227 | 189 | 25 | 14 |
| LCPS | true | 16 | UNKNOWN | 5014 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 463 | 463 | 340 | 0 | 15 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t: -23, 16t: -51 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_queries: 8t: +227, 16t: +463 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_queries: 8t: +227, 16t: +463 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_hits: 8t: +189, 16t: +340 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_hits: 8t: +25, 16t: +0 (LCPS - PAPER).

## Interpretation Notes

- Negative LCPS - PAPER runtime, checked_paths, stale_paths, and duplicate-failure deltas are improvements for that metric.
- LCPS cache activation requires positive LCPS search invocations and positive checked/stale prefix queries. Hits show that the query keys matched cached run prefixes.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log` or `*-<mode>-stale-<on|off>-threads-*.log`.
