# Checked Path Divergence Results

## Purpose

This experiment compares PAPER, LCPS, and optional BFS/DFS path selection under parallel TraceAbstraction.

Each unordered checked-path pair contributes normalized prefix-LCA divergence `1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.

## Summary

### Correctness

- Modes present: PAPER, LCPS, LCPS_FULL.
- Result mismatch groups across modes: 0.
- ERROR rows: 0; TIMEOUT rows: 0.
- BFS/DFS were not part of this run.

### Activation

- Cache-guided rows with search invocations > 0: 20 / 20.
- Cache-guided rows with checked prefix queries > 0: 20 / 20.
- Cache-guided rows with stale prefix queries > 0: 20 / 20.
- Cache-guided rows with checked prefix hits > 0: 20 / 20.
- Cache-guided rows with stale prefix hits > 0: 18 / 20.
- Total LCPS effective priority decisions: 89.
- Total LCPS_FULL cache-suffix invocations/fallbacks: 9 / 597.

### Performance

- LCPS - PAPER runtime_ms: wins/losses/ties 4/6/0, mean 33.50, median 3.50.
- LCPS_FULL - PAPER runtime_ms: wins/losses/ties 4/6/0, mean -0.30, median 51.00.
- LCPS_FULL - LCPS runtime_ms: wins/losses/ties 5/5/0, mean -33.80, median -8.00.

### Work

- LCPS - PAPER checked_paths: wins/losses/ties 3/1/6, mean -0.30, median 0.00.
- LCPS_FULL - PAPER checked_paths: wins/losses/ties 2/1/7, mean -0.10, median 0.00.
- LCPS_FULL - LCPS checked_paths: wins/losses/ties 0/2/8, mean 0.20, median 0.00.
- LCPS - PAPER stale_paths: wins/losses/ties 3/0/7, mean -0.50, median 0.00.
- LCPS_FULL - PAPER stale_paths: wins/losses/ties 2/1/7, mean -0.40, median 0.00.
- LCPS_FULL - LCPS stale_paths: wins/losses/ties 1/2/7, mean 0.10, median 0.00.
- LCPS - PAPER duplicate freshness failures: wins/losses/ties 0/0/10, mean 0.00, median 0.00.
- LCPS_FULL - PAPER duplicate freshness failures: wins/losses/ties 0/0/10, mean 0.00, median 0.00.
- LCPS_FULL - LCPS duplicate freshness failures: wins/losses/ties 0/0/10, mean 0.00, median 0.00.
- LCPS - PAPER search_failed: wins/losses/ties 0/0/10, mean 0.00, median 0.00.
- LCPS_FULL - PAPER search_failed: wins/losses/ties 0/0/10, mean 0.00, median 0.00.
- LCPS_FULL - LCPS search_failed: wins/losses/ties 0/0/10, mean 0.00, median 0.00.

### Divergence

- LCPS - PAPER avg divergence: wins/losses/ties 2/3/5, mean 0.00, median 0.00.
- LCPS_FULL - PAPER avg divergence: wins/losses/ties 2/3/5, mean -0.00, median 0.00.
- LCPS_FULL - LCPS avg divergence: wins/losses/ties 1/2/7, mean -0.01, median 0.00.

### Interpretation

- Cache hits and effective priority decisions are both present; compare work/runtime deltas to see whether changed ordering helped.
- LCPS_FULL continued cache-guided suffix exploration after active divergence; fallback count shows how often it reached uncovered history.
- LCPS does not need higher divergence to be useful; interpret divergence together with runtime, checked_paths, stale_paths, duplicate freshness failures, and effective priority decisions.

## Benchmark Selection

- `trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/eureka_05.i_5.bpl.
- `k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/invert_string.i_4.bpl.
- `examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/interleave_bits.i_3.bpl.
- `trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-diamond2.i_4-22ecac6d`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/diamond2.i_4.bpl.
- `k-examples-programs-20170304-DifficultPathPrograms-resultKnown-count_up_down.i_3-aa4a8d5f`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/count_up_down.i_3.bpl.

## Results

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | false | 8 | SAFE | 5072 | 19 | 9 | 0 | 148.64898434898433 | 0.8692923061344113 | 13 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | false | 16 | SAFE | 7781 | 37 | 20 | 0 | 615.5395643572117 | 0.9242335801159335 | 23 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | false | 8 | SAFE | 4607 | 17 | 6 | 0 | 114.10352980352978 | 0.838996542673013 | 10 | 0 | 127 | 127 | 104 | 48 | 16 | 0 | 0 | 6 |
| LCPS | true | false | 16 | SAFE | 8013 | 36 | 19 | 0 | 580.695446710153 | 0.9217388043018301 | 22 | 0 | 387 | 387 | 341 | 132 | 36 | 0 | 0 | 16 |
| LCPS_FULL | true | false | 8 | SAFE | 4586 | 17 | 5 | 0 | 114.10352980352978 | 0.838996542673013 | 10 | 0 | 164 | 164 | 112 | 54 | 16 | 6 | 16 | 7 |
| LCPS_FULL | true | false | 16 | SAFE | 8018 | 36 | 19 | 0 | 581.0954467101528 | 0.9223737249367505 | 22 | 0 | 452 | 452 | 341 | 85 | 36 | 0 | 37 | 10 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t no-init: -465, 16t no-init: +232 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t no-init: -2, 16t no-init: -1 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t no-init: -3, 16t no-init: -1 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t no-init: +0, 16t no-init: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t no-init: -0.0302958, 16t no-init: -0.00249478 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 8t no-init: +6, 16t no-init: +16 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 8t no-init: -486, 16t no-init: +237 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 8t no-init: -2, 16t no-init: -1 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 8t no-init: -4, 16t no-init: -1 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 8t no-init: -0.0302958, 16t no-init: -0.00185986 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 8t no-init: +7, 16t no-init: +10 (LCPS_FULL - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 8t no-init: -21, 16t no-init: +5 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 8t no-init: -1, 16t no-init: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 8t no-init: +1, 16t no-init: -6 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 8t no-init: +6, 16t no-init: +0 (LCPS_FULL - LCPS).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | false | 8 | SAFE | 6387 | 23 | 11 | 0 | 237.19404761904764 | 0.9375258799171844 | 16 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | false | 16 | SAFE | 8531 | 33 | 15 | 0 | 471.89329004329016 | 0.8937372917486556 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | false | 8 | SAFE | 6669 | 22 | 11 | 0 | 232.9718253968254 | 1.008536040678898 | 16 | 0 | 200 | 200 | 162 | 68 | 22 | 0 | 0 | 7 |
| LCPS | true | false | 16 | SAFE | 8517 | 34 | 14 | 0 | 505.1660173160174 | 0.9004741841640239 | 19 | 0 | 477 | 477 | 412 | 101 | 33 | 0 | 0 | 8 |
| LCPS_FULL | true | false | 8 | SAFE | 5953 | 23 | 11 | 0 | 237.32738095238093 | 0.9380528891398456 | 16 | 0 | 259 | 259 | 187 | 79 | 22 | 2 | 24 | 11 |
| LCPS_FULL | true | false | 16 | SAFE | 9153 | 35 | 15 | 0 | 538.402380952381 | 0.9048779511804723 | 20 | 0 | 554 | 554 | 425 | 108 | 34 | 1 | 57 | 12 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t no-init: +282, 16t no-init: -14 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t no-init: -1, 16t no-init: +1 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t no-init: +0, 16t no-init: -1 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t no-init: +0, 16t no-init: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t no-init: +0.0710102, 16t no-init: +0.00673689 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 8t no-init: +7, 16t no-init: +8 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 8t no-init: -434, 16t no-init: +622 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 8t no-init: +0, 16t no-init: +2 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 8t no-init: +0.000527009, 16t no-init: +0.0111407 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 8t no-init: +11, 16t no-init: +12 (LCPS_FULL - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 8t no-init: -716, 16t no-init: +636 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 8t no-init: +1, 16t no-init: +1 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 8t no-init: +0, 16t no-init: +1 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 8t no-init: +4, 16t no-init: +4 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 8t no-init: +2, 16t no-init: +1 (LCPS_FULL - LCPS).

### examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | false | 8 | UNKNOWN | 6141 | 15 | 5 | 0 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | false | 16 | UNKNOWN | 4989 | 16 | 0 | 0 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | false | 8 | UNKNOWN | 6114 | 15 | 5 | 0 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 227 | 227 | 189 | 25 | 14 | 0 | 0 | 1 |
| LCPS | true | false | 16 | UNKNOWN | 4760 | 16 | 0 | 0 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 463 | 463 | 340 | 0 | 15 | 0 | 0 | 1 |
| LCPS_FULL | true | false | 8 | UNKNOWN | 6161 | 15 | 5 | 0 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 252 | 252 | 189 | 25 | 14 | 0 | 34 | 1 |
| LCPS_FULL | true | false | 16 | UNKNOWN | 5192 | 16 | 0 | 0 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 493 | 493 | 340 | 0 | 15 | 0 | 119 | 1 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t no-init: -27, 16t no-init: -229 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t no-init: +0, 16t no-init: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t no-init: +0, 16t no-init: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t no-init: +0, 16t no-init: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t no-init: +0, 16t no-init: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 8t no-init: +1, 16t no-init: +1 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 8t no-init: +20, 16t no-init: +203 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 8t no-init: +1, 16t no-init: +1 (LCPS_FULL - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 8t no-init: +47, 16t no-init: +432 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - LCPS).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-diamond2.i_4-22ecac6d

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | false | 8 | SAFE | 5492 | 14 | 5 | 0 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | false | 16 | SAFE | 8550 | 22 | 4 | 0 | 149.91479990597637 | 0.6489818177747895 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | false | 8 | SAFE | 5493 | 14 | 5 | 0 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 223 | 223 | 184 | 20 | 13 | 0 | 0 | 1 |
| LCPS | true | false | 16 | SAFE | 9082 | 22 | 4 | 0 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 711 | 711 | 580 | 20 | 21 | 0 | 0 | 1 |
| LCPS_FULL | true | false | 8 | SAFE | 5262 | 14 | 5 | 0 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 245 | 245 | 184 | 20 | 13 | 0 | 33 | 1 |
| LCPS_FULL | true | false | 16 | SAFE | 8721 | 22 | 5 | 0 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 749 | 749 | 580 | 20 | 21 | 0 | 125 | 1 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t no-init: +1, 16t no-init: +532 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t no-init: +0, 16t no-init: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t no-init: +0, 16t no-init: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t no-init: +0, 16t no-init: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t no-init: +0, 16t no-init: +0.00393546 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 8t no-init: +1, 16t no-init: +1 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 8t no-init: -230, 16t no-init: +171 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 8t no-init: +0, 16t no-init: +1 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 8t no-init: +0, 16t no-init: +0.00393546 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 8t no-init: +1, 16t no-init: +1 (LCPS_FULL - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 8t no-init: -231, 16t no-init: -361 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 8t no-init: +0, 16t no-init: +1 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - LCPS).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-count_up_down.i_3-aa4a8d5f

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | false | 8 | SAFE | 5142 | 11 | 2 | 0 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | false | 16 | SAFE | 7776 | 19 | 2 | 0 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | false | 8 | SAFE | 5148 | 11 | 2 | 0 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 162 | 162 | 128 | 3 | 10 | 0 | 0 | 1 |
| LCPS | true | false | 16 | SAFE | 7793 | 19 | 2 | 0 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 594 | 594 | 468 | 3 | 18 | 0 | 0 | 1 |
| LCPS_FULL | true | false | 8 | SAFE | 5224 | 11 | 2 | 0 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 180 | 180 | 128 | 3 | 10 | 0 | 30 | 1 |
| LCPS_FULL | true | false | 16 | SAFE | 7588 | 19 | 2 | 0 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 628 | 628 | 468 | 3 | 18 | 0 | 122 | 1 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t no-init: +6, 16t no-init: +17 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t no-init: +0, 16t no-init: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t no-init: +0, 16t no-init: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t no-init: +0, 16t no-init: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t no-init: +0, 16t no-init: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 8t no-init: +1, 16t no-init: +1 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 8t no-init: +82, 16t no-init: -188 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 8t no-init: +1, 16t no-init: +1 (LCPS_FULL - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 8t no-init: +76, 16t no-init: -205 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 8t no-init: +0, 16t no-init: +0 (LCPS_FULL - LCPS).

## Interpretation Notes

- Negative LCPS - PAPER runtime, checked_paths, stale_paths, and duplicate-failure deltas are improvements for that metric.
- LCPS cache activation requires positive LCPS search invocations and positive checked/stale prefix queries. Hits show that the query keys matched cached run prefixes.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log` or `*-<mode>-stale-<on|off>-threads-*.log`.
