# Checked Path Divergence Results

## Purpose

This experiment compares PAPER, LCPS, BATCH_LCPS, and optional BFS/DFS path selection under parallel TraceAbstraction.

Each unordered checked-path pair contributes normalized prefix-LCA divergence `1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.

## Summary

### Correctness

- Modes present: PAPER, LCPS, BATCH_LCPS.
- Result mismatch groups across modes: 0.
- ERROR rows: 0; TIMEOUT rows: 0.
- BFS/DFS were not part of this run.

### Activation

- LCPS-priority rows with search invocations > 0: 15 / 15.
- LCPS-priority rows with checked prefix queries > 0: 15 / 15.
- LCPS-priority rows with stale prefix queries > 0: 15 / 15.
- LCPS-priority rows with checked prefix hits > 0: 15 / 15.
- LCPS-priority rows with stale prefix hits > 0: 14 / 15.
- Total LCPS effective priority decisions: 53.
- Total LCPS_FULL cache-suffix invocations/fallbacks: 0 / 0.
- BatchLcpsInvocations: 112.
- BatchLcpsCandidatesGenerated/Selected: 856 / 256.
- BatchLcpsCandidateGenerationFailures: 7.
- BatchLcpsEffectiveBatchDecisions: 83.

### Performance

- LCPS - PAPER runtime_ms: wins/losses/ties 8/7/0, mean 98.87, median -21.00.
- BATCH_LCPS - PAPER runtime_ms: wins/losses/ties 6/9/0, mean -7813.40, median 177.00.
- BATCH_LCPS - LCPS runtime_ms: wins/losses/ties 6/9/0, mean -7912.27, median 162.00.

### Work

- LCPS - PAPER checked_paths: wins/losses/ties 1/0/14, mean -0.53, median 0.00.
- BATCH_LCPS - PAPER checked_paths: wins/losses/ties 6/6/3, mean -0.67, median 0.00.
- BATCH_LCPS - LCPS checked_paths: wins/losses/ties 5/7/3, mean -0.13, median 0.00.
- LCPS - PAPER stale_paths: wins/losses/ties 3/1/11, mean -0.67, median 0.00.
- BATCH_LCPS - PAPER stale_paths: wins/losses/ties 7/7/1, mean -0.40, median 0.00.
- BATCH_LCPS - LCPS stale_paths: wins/losses/ties 6/8/1, mean 0.27, median 1.00.
- LCPS - PAPER duplicate freshness failures: wins/losses/ties 2/3/10, mean -0.13, median 0.00.
- BATCH_LCPS - PAPER duplicate freshness failures: wins/losses/ties 15/0/0, mean -6.00, median -5.00.
- BATCH_LCPS - LCPS duplicate freshness failures: wins/losses/ties 15/0/0, mean -5.87, median -5.00.
- LCPS - PAPER search_failed: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- BATCH_LCPS - PAPER search_failed: wins/losses/ties 0/3/12, mean 0.47, median 0.00.
- BATCH_LCPS - LCPS search_failed: wins/losses/ties 0/3/12, mean 0.47, median 0.00.

### Batch Quality

- Average candidate pool size across BATCH_LCPS rows: 9.03.
- Average selected batch size across BATCH_LCPS rows: 2.96.
- Total effective batch decisions: 83.
- BATCH_LCPS - PAPER checked_paths: wins/losses/ties 6/6/3, mean -0.67, median 0.00.
- BATCH_LCPS - PAPER stale_paths: wins/losses/ties 7/7/1, mean -0.40, median 0.00.

### Divergence

- LCPS - PAPER avg divergence: wins/losses/ties 4/2/9, mean -0.01, median 0.00.
- BATCH_LCPS - PAPER avg divergence: wins/losses/ties 7/6/2, mean -0.02, median 0.00.
- BATCH_LCPS - LCPS avg divergence: wins/losses/ties 6/7/2, mean -0.00, median 0.00.

### Interpretation

- Cache hits and effective priority decisions are both present; compare work/runtime deltas to see whether changed ordering helped.
- BATCH_LCPS changed at least one dispatched batch relative to naive first-k candidate dispatch.
- LCPS does not need higher divergence to be useful; interpret divergence together with runtime, checked_paths, stale_paths, duplicate freshness failures, and effective priority decisions.

## Benchmark Selection

- `trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/eureka_05.i_5.bpl.
- `k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/invert_string.i_4.bpl.
- `examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/interleave_bits.i_3.bpl.
- `trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-diamond2.i_4-22ecac6d`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/diamond2.i_4.bpl.
- `k-examples-programs-20170304-DifficultPathPrograms-resultKnown-count_up_down.i_3-aa4a8d5f`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/count_up_down.i_3.bpl.

## Results

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 4027 | 13 | 6 | 5 | 87.50992063492063 | 1.1219220594220594 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | SAFE | 5304 | 25 | 15 | 5 | 293.47195085616136 | 0.9782398361872046 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | SAFE | 7799 | 36 | 20 | 5 | 611.6954467101527 | 0.9709451535081789 | 23 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 4 | SAFE | 3837 | 13 | 5 | 6 | 83.50992063492063 | 1.070640008140008 | 11 | 0 | 73 | 73 | 59 | 19 | 10 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | SAFE | 4596 | 17 | 6 | 1 | 114.10352980352978 | 0.838996542673013 | 10 | 0 | 114 | 114 | 92 | 42 | 14 | 0 | 0 | 6 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | SAFE | 7988 | 36 | 19 | 4 | 579.4844172983882 | 0.9198165353942669 | 22 | 0 | 366 | 366 | 317 | 128 | 34 | 0 | 0 | 15 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 4 | SAFE | 3590 | 14 | 7 | 0 | 87.7436507936508 | 0.9642159427873714 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 8 | 13 | 52 | 13 | 0 | 6.5 | 1.625 | 5 |
| BATCH_LCPS | true | true | 8 | SAFE | 4825 | 20 | 10 | 0 | 176.97481962481962 | 0.931446419077998 | 13 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 7 | 19 | 76 | 19 | 0 | 10.857142857142858 | 2.7142857142857144 | 6 |
| BATCH_LCPS | true | true | 16 | SAFE | 8150 | 33 | 15 | 0 | 443.26358086358107 | 0.8395143576961762 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 32 | 72 | 32 | 0 | 18.0 | 8.0 | 4 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: -190, 8t: -708, 16t: +189 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: -8, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: -1, 8t: -9, 16t: -1 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +1, 8t: -4, 16t: -1 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: -0.0512821, 8t: -0.139243, 16t: -0.0511286 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +2, 8t: +6, 16t: +15 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 4t: -437, 8t: -479, 16t: +351 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +1, 8t: -5, 16t: -3 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +1, 8t: -5, 16t: -5 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -5, 8t: -5, 16t: -5 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: -0.157706, 8t: -0.0467934, 16t: -0.131431 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +52, 8t: +76, 16t: +72 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +13, 8t: +19, 16t: +32 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +5, 8t: +6, 16t: +4 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 4t: -247, 8t: +229, 16t: +162 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 4t: +1, 8t: +3, 16t: -3 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 4t: +2, 8t: +4, 16t: -4 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 4t: -6, 8t: -1, 16t: -4 (BATCH_LCPS - LCPS).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 4076 | 14 | 4 | 8 | 88.68333333333334 | 0.9745421245421246 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | SAFE | 7080 | 25 | 13 | 12 | 284.9718253968254 | 0.9499060846560847 | 18 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | SAFE | 8757 | 35 | 15 | 10 | 532.6532745032744 | 0.8952155874004611 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 4 | SAFE | 4042 | 14 | 4 | 8 | 88.68333333333334 | 0.9745421245421246 | 11 | 0 | 98 | 98 | 86 | 43 | 12 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | SAFE | 7059 | 25 | 14 | 12 | 284.6384920634921 | 0.9487949735449737 | 18 | 0 | 227 | 227 | 187 | 96 | 24 | 0 | 0 | 5 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | SAFE | 9563 | 35 | 15 | 11 | 539.812049062049 | 0.9072471412807547 | 20 | 0 | 489 | 489 | 417 | 100 | 34 | 0 | 0 | 15 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 4 | SAFE | 5063 | 17 | 8 | 0 | 131.59285714285716 | 0.9675945378151262 | 14 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 11 | 16 | 64 | 16 | 0 | 5.818181818181818 | 1.4545454545454546 | 7 |
| BATCH_LCPS | true | true | 8 | SAFE | 6200 | 23 | 8 | 0 | 253.43333333333334 | 1.0017127799736496 | 17 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 12 | 23 | 92 | 23 | 0 | 7.666666666666667 | 1.9166666666666667 | 10 |
| BATCH_LCPS | true | true | 16 | SAFE | 9375 | 35 | 16 | 0 | 601.8083333333332 | 1.011442577030812 | 22 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 12 | 36 | 116 | 36 | 0 | 9.666666666666666 | 3.0 | 9 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: -34, 8t: -21, 16t: +806 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +1, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +0, 8t: +0, 16t: +1 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: -0.00111111, 16t: +0.0120316 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +1, 8t: +5, 16t: +15 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 4t: +987, 8t: -880, 16t: +618 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +3, 8t: -2, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +4, 8t: -5, 16t: +1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -8, 8t: -12, 16t: -10 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: -0.00694759, 8t: +0.0518067, 16t: +0.116227 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +64, 8t: +92, 16t: +116 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +16, 8t: +23, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +7, 8t: +10, 16t: +9 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 4t: +1021, 8t: -859, 16t: -188 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 4t: +3, 8t: -2, 16t: +0 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 4t: +4, 8t: -6, 16t: +1 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 4t: -8, 8t: -12, 16t: -11 (BATCH_LCPS - LCPS).

### examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | UNKNOWN | 4271 | 11 | 5 | 4 | 51.55384615384615 | 0.9373426573426573 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | UNKNOWN | 6107 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | UNKNOWN | 5212 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 4 | UNKNOWN | 4313 | 11 | 5 | 5 | 52.4 | 0.9527272727272728 | 7 | 0 | 58 | 58 | 45 | 8 | 7 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | UNKNOWN | 6150 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 227 | 227 | 189 | 25 | 14 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | UNKNOWN | 4983 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 463 | 463 | 340 | 0 | 15 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 4 | UNKNOWN | 4750 | 12 | 6 | 0 | 63.4 | 0.9606060606060606 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 11 | 44 | 11 | 0 | 4.888888888888889 | 1.2222222222222223 | 3 |
| BATCH_LCPS | true | true | 8 | UNKNOWN | 6212 | 17 | 8 | 0 | 120.57936507936509 | 0.8866129785247433 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 10 | 16 | 64 | 16 | 0 | 6.4 | 1.6 | 8 |
| BATCH_LCPS | true | true | 16 | UNKNOWN | 6073 | 17 | 0 | 0 | 55.82389081506727 | 0.4104697854049064 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 2 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: +42, 8t: +43, 16t: -229 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +1, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0.0153846, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +1, 8t: +1, 16t: +1 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 4t: +479, 8t: +105, 16t: +861 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +1, 8t: +2, 16t: +1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +1, 8t: +3, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -4, 8t: -8, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0.0232634, 8t: +0.0334762, 16t: +0.078604 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +44, 8t: +64, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +11, 8t: +16, 16t: +16 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +3, 8t: +8, 16t: +2 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 4t: +437, 8t: +62, 16t: +1090 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 4t: +1, 8t: +2, 16t: +1 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 4t: +1, 8t: +3, 16t: +0 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 4t: -5, 8t: -8, 16t: -1 (BATCH_LCPS - LCPS).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-diamond2.i_4-22ecac6d

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 4072 | 9 | 2 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | SAFE | 5259 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | SAFE | 8758 | 22 | 4 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 4 | SAFE | 4039 | 9 | 2 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 59 | 59 | 44 | 4 | 7 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | SAFE | 5277 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 223 | 223 | 184 | 20 | 13 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | SAFE | 9137 | 22 | 4 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 700 | 700 | 569 | 19 | 21 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 4 | SAFE | 4249 | 10 | 4 | 0 | 42.4 | 0.9422222222222222 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 8 | 10 | 36 | 9 | 1 | 4.5 | 1.125 | 6 |
| BATCH_LCPS | true | true | 8 | SAFE | 5928 | 14 | 4 | 0 | 75.57936507936509 | 0.8305424733996164 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 8 | 14 | 52 | 13 | 1 | 6.5 | 1.625 | 7 |
| BATCH_LCPS | true | true | 16 | SAFE | 9418 | 22 | 10 | 0 | 150.82389081506727 | 0.6529172762557025 | 13 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 12 | 40 | 56 | 21 | 5 | 4.666666666666667 | 1.75 | 7 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: -33, 8t: +18, 16t: +379 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +1, 8t: +1, 16t: +1 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 4t: +177, 8t: +669, 16t: +660 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +1, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +2, 8t: -1, 16t: +6 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -5, 8t: -7, 16t: -7 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0.0144444, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +36, 8t: +52, 16t: +56 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +9, 8t: +13, 16t: +21 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +6, 8t: +7, 16t: +7 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 4t: +210, 8t: +651, 16t: +281 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 4t: +1, 8t: +0, 16t: +0 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 4t: +2, 8t: -1, 16t: +6 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 4t: -5, 8t: -7, 16t: -7 (BATCH_LCPS - LCPS).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-count_up_down.i_3-aa4a8d5f

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 122444 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | SAFE | 5218 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | SAFE | 7771 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 4 | SAFE | 123971 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 58 | 58 | 45 | 8 | 7 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | SAFE | 5090 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 162 | 162 | 128 | 3 | 10 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | SAFE | 7593 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 594 | 594 | 468 | 3 | 18 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 4 | SAFE | 3265 | 6 | 1 | 0 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 5 | 20 | 5 | 0 | 6.666666666666667 | 1.6666666666666667 | 3 |
| BATCH_LCPS | true | true | 8 | SAFE | 4641 | 10 | 1 | 0 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 9 | 36 | 9 | 0 | 12.0 | 3.0 | 3 |
| BATCH_LCPS | true | true | 16 | SAFE | 7215 | 18 | 1 | 0 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 17 | 40 | 17 | 0 | 13.333333333333334 | 5.666666666666667 | 3 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: +1527, 8t: -128, 16t: -178 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +1, 8t: +1, 16t: +1 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 4t: -119179, 8t: -577, 16t: -556 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: -7, 8t: -1, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: -6, 8t: -1, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -5, 8t: -4, 16t: -4 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: -0.14, 8t: -0.0623056, 16t: -0.0551607 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +20, 8t: +36, 16t: +40 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +5, 8t: +9, 16t: +17 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +3, 8t: +3, 16t: +3 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 4t: -120706, 8t: -449, 16t: -378 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 4t: -7, 8t: -1, 16t: -1 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 4t: -6, 8t: -1, 16t: -1 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 4t: -5, 8t: -4, 16t: -4 (BATCH_LCPS - LCPS).

## Interpretation Notes

- Negative LCPS - PAPER runtime, checked_paths, stale_paths, and duplicate-failure deltas are improvements for that metric.
- LCPS cache activation requires positive LCPS search invocations and positive checked/stale prefix queries. Hits show that the query keys matched cached run prefixes.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log` or `*-<mode>-stale-<on|off>-threads-*.log`.
