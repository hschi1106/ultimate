# Checked Path Divergence Results

## Purpose

This experiment compares PAPER, LCPS, and optional BFS/DFS path selection under parallel TraceAbstraction.

Each unordered checked-path pair contributes normalized prefix-LCA divergence `1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.

## Summary

### Correctness

- PAPER/LCPS comparable pairs: 6.
- Result mismatches: 0.
- ERROR rows: 0; TIMEOUT rows: 0.
- BFS/DFS were not part of this run.

### LCPS Activation

- LCPS rows with search invocations > 0: 12 / 12.
- LCPS rows with checked prefix queries > 0: 12 / 12.
- LCPS rows with stale prefix queries > 0: 12 / 12.
- LCPS rows with checked prefix hits > 0: 12 / 12.
- LCPS rows with stale prefix hits > 0: 5 / 12.

### Performance

- Runtime wins/losses/ties for LCPS vs PAPER: 4/2/0.
- Average runtime delta: -122.2ms; median delta: -36.5ms.

### Work Reduction

- Average checked_paths delta: -0.50.
- Average stale_paths delta: -0.17.
- Average duplicate freshness failures delta: -0.83.

### Divergence

- Average avg-divergence delta: -0.0009; median delta: -0.0006.
- LCPS does not need higher divergence to be useful; interpret divergence together with runtime, checked_paths, stale_paths, and duplicate freshness failures.

## Benchmark Selection

- `trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/eureka_05.i_5.bpl.
- `k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/invert_string.i_4.bpl.
- `examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/interleave_bits.i_3.bpl.

## Results

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8

| mode | stale_tracking | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations |
|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | 8 | SAFE | 4898 | 19 | 6 | 2 | 148.64898434898433 | 0.8692923061344113 | 12 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 16 | SAFE | 7844 | 35 | 20 | 6 | 610.8123584748587 | 1.0265753923947205 | 23 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | false | 8 | SAFE | 4881 | 19 | 0 | 3 | 149.1035298035298 | 0.8719504666873088 | 12 | 0 | 141 | 141 | 115 | 0 | 16 |
| LCPS | false | 16 | SAFE | 7833 | 35 | 0 | 4 | 546.345691808192 | 0.9182280534591462 | 21 | 0 | 349 | 349 | 299 | 0 | 33 |
| LCPS | true | 8 | SAFE | 4648 | 17 | 6 | 1 | 114.10352980352978 | 0.838996542673013 | 10 | 0 | 114 | 114 | 92 | 42 | 14 |
| LCPS | true | 16 | SAFE | 8086 | 37 | 20 | 5 | 618.9172415819478 | 0.9293051675404621 | 23 | 0 | 383 | 383 | 333 | 140 | 35 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t: -250, 16t: +242 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t: -2, 16t: +2 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t: -1, 16t: -1 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t: -0.0302958, 16t: -0.0972702 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_queries: 8t: +114, 16t: +383 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_queries: 8t: +114, 16t: +383 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_hits: 8t: +92, 16t: +333 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_hits: 8t: +42, 16t: +140 (LCPS - PAPER).
- LCPS stale-off vs stale-on runtime_ms: trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8 8t: +233, trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8 16t: -253 (off - on).
- LCPS stale-off vs stale-on checked_paths: trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8 8t: +2, trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8 16t: -2 (off - on).
- LCPS stale-off vs stale-on stale_paths: trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8 8t: -6, trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8 16t: -20 (off - on).
- LCPS stale-off vs stale-on checked prefix hits: trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8 8t: +23, trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8 16t: -34 (off - on).
- LCPS stale-off vs stale-on stale prefix hits: trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8 8t: -42, trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8 16t: -140 (off - on).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb

| mode | stale_tracking | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations |
|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | 8 | SAFE | 7126 | 25 | 13 | 12 | 284.9718253968254 | 0.9499060846560847 | 18 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 16 | SAFE | 8786 | 35 | 15 | 12 | 539.1660173160174 | 0.9061613736403653 | 20 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | false | 8 | SAFE | 7110 | 24 | 0 | 12 | 260.9718253968254 | 0.9455500920174834 | 17 | 0 | 214 | 214 | 175 | 0 | 23 |
| LCPS | false | 16 | SAFE | 8815 | 35 | 0 | 12 | 539.1660173160174 | 0.9061613736403653 | 20 | 0 | 493 | 493 | 428 | 0 | 34 |
| LCPS | true | 8 | SAFE | 6472 | 22 | 12 | 11 | 247.9718253968254 | 1.0734711056139628 | 17 | 0 | 214 | 214 | 175 | 88 | 23 |
| LCPS | true | 16 | SAFE | 8766 | 35 | 15 | 10 | 538.4971861471862 | 0.9050372876423297 | 20 | 0 | 496 | 496 | 425 | 108 | 34 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t: -654, 16t: -20 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t: -3, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t: -1, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t: -1, 16t: -2 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t: +0.123565, 16t: -0.00112409 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_queries: 8t: +214, 16t: +496 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_queries: 8t: +214, 16t: +496 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_hits: 8t: +175, 16t: +425 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_hits: 8t: +88, 16t: +108 (LCPS - PAPER).
- LCPS stale-off vs stale-on runtime_ms: k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb 8t: +638, k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb 16t: +49 (off - on).
- LCPS stale-off vs stale-on checked_paths: k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb 8t: +2, k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb 16t: +0 (off - on).
- LCPS stale-off vs stale-on stale_paths: k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb 8t: -12, k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb 16t: -15 (off - on).
- LCPS stale-off vs stale-on checked prefix hits: k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb 8t: +0, k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb 16t: +3 (off - on).
- LCPS stale-off vs stale-on stale prefix hits: k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb 8t: -88, k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb 16t: -108 (off - on).

### examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20

| mode | stale_tracking | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations |
|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | 8 | UNKNOWN | 6233 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | 16 | UNKNOWN | 5032 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | false | 8 | UNKNOWN | 6217 | 15 | 0 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 227 | 227 | 189 | 0 | 14 |
| LCPS | false | 16 | UNKNOWN | 4806 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 463 | 463 | 340 | 0 | 15 |
| LCPS | true | 8 | UNKNOWN | 6180 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 227 | 227 | 189 | 25 | 14 |
| LCPS | true | 16 | UNKNOWN | 5034 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 463 | 463 | 340 | 0 | 15 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t: -53, 16t: +2 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_queries: 8t: +227, 16t: +463 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_queries: 8t: +227, 16t: +463 (LCPS - PAPER).
- PAPER vs LCPS lcps_checked_prefix_hits: 8t: +189, 16t: +340 (LCPS - PAPER).
- PAPER vs LCPS lcps_stale_prefix_hits: 8t: +25, 16t: +0 (LCPS - PAPER).
- LCPS stale-off vs stale-on runtime_ms: examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20 8t: +37, examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20 16t: -228 (off - on).
- LCPS stale-off vs stale-on checked_paths: examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20 8t: +0, examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20 16t: +0 (off - on).
- LCPS stale-off vs stale-on stale_paths: examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20 8t: -5, examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20 16t: +0 (off - on).
- LCPS stale-off vs stale-on checked prefix hits: examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20 8t: +0, examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20 16t: +0 (off - on).
- LCPS stale-off vs stale-on stale prefix hits: examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20 8t: -25, examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20 16t: +0 (off - on).

## Interpretation Notes

- Negative LCPS - PAPER runtime, checked_paths, stale_paths, and duplicate-failure deltas are improvements for that metric.
- LCPS cache activation requires positive LCPS search invocations and positive checked/stale prefix queries. Hits show that the query keys matched cached run prefixes.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log` or `*-<mode>-stale-<on|off>-threads-*.log`.
