# Checked Path Divergence Results

## Purpose

This experiment compares PAPER, LCPS, BATCH_LCPS, and optional BFS/DFS path selection under parallel TraceAbstraction.

Each unordered checked-path pair contributes normalized prefix-LCA divergence `1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.

## Summary

### Correctness

- Modes present: BFS, DFS, PAPER, LCPS, BATCH_LCPS.
- Result mismatch groups across modes: 0.
- ERROR rows: 0; TIMEOUT rows: 0.
- BFS/DFS duplicate freshness failures: 72; search_failed rows total: 72.

### Activation

- LCPS-priority rows with search invocations > 0: 6 / 6.
- LCPS-priority rows with checked prefix queries > 0: 6 / 6.
- LCPS-priority rows with stale prefix queries > 0: 6 / 6.
- LCPS-priority rows with checked prefix hits > 0: 6 / 6.
- LCPS-priority rows with stale prefix hits > 0: 5 / 6.
- Total LCPS effective priority decisions: 42.
- Total LCPS_FULL cache-suffix invocations/fallbacks: 0 / 0.
- BatchLcpsInvocations: 54.
- BatchLcpsCandidatesGenerated/Selected: 492 / 152.
- BatchLcpsCandidateGenerationFailures: 0.
- BatchLcpsEffectiveBatchDecisions: 49.

### Performance

- LCPS - PAPER runtime_ms: wins/losses/ties 3/3/0, mean 168.83, median 93.50.
- BATCH_LCPS - PAPER runtime_ms: wins/losses/ties 0/6/0, mean 829.83, median 494.00.
- BATCH_LCPS - LCPS runtime_ms: wins/losses/ties 0/6/0, mean 661.00, median 438.50.

### Work

- LCPS - PAPER checked_paths: wins/losses/ties 0/2/4, mean 0.50, median 0.00.
- BATCH_LCPS - PAPER checked_paths: wins/losses/ties 0/5/1, mean 2.67, median 1.00.
- BATCH_LCPS - LCPS checked_paths: wins/losses/ties 2/4/0, mean 2.17, median 1.00.
- LCPS - PAPER stale_paths: wins/losses/ties 1/0/5, mean -0.17, median 0.00.
- BATCH_LCPS - PAPER stale_paths: wins/losses/ties 2/3/1, mean 0.50, median 1.00.
- BATCH_LCPS - LCPS stale_paths: wins/losses/ties 2/3/1, mean 0.67, median 1.00.
- LCPS - PAPER duplicate freshness failures: wins/losses/ties 1/0/5, mean -0.17, median 0.00.
- BATCH_LCPS - PAPER duplicate freshness failures: wins/losses/ties 6/0/0, mean -6.67, median -7.00.
- BATCH_LCPS - LCPS duplicate freshness failures: wins/losses/ties 6/0/0, mean -6.50, median -7.00.
- LCPS - PAPER search_failed: wins/losses/ties 0/0/6, mean 0.00, median 0.00.
- BATCH_LCPS - PAPER search_failed: wins/losses/ties 0/0/6, mean 0.00, median 0.00.
- BATCH_LCPS - LCPS search_failed: wins/losses/ties 0/0/6, mean 0.00, median 0.00.

### Batch Quality

- Average candidate pool size across BATCH_LCPS rows: 10.74.
- Average selected batch size across BATCH_LCPS rows: 3.78.
- Total effective batch decisions: 49.
- BATCH_LCPS - PAPER checked_paths: wins/losses/ties 0/5/1, mean 2.67, median 1.00.
- BATCH_LCPS - PAPER stale_paths: wins/losses/ties 2/3/1, mean 0.50, median 1.00.

### Divergence

- LCPS - PAPER avg divergence: wins/losses/ties 2/0/4, mean -0.04, median 0.00.
- BATCH_LCPS - PAPER avg divergence: wins/losses/ties 1/5/0, mean -0.00, median 0.03.
- BATCH_LCPS - LCPS avg divergence: wins/losses/ties 1/5/0, mean 0.03, median 0.05.

### Interpretation

- Cache hits and effective priority decisions are both present; compare work/runtime deltas to see whether changed ordering helped.
- BATCH_LCPS changed at least one dispatched batch relative to naive first-k candidate dispatch.
- LCPS does not need higher divergence to be useful; interpret divergence together with runtime, checked_paths, stale_paths, duplicate freshness failures, and effective priority decisions.

## Benchmark Selection

- `trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/eureka_05.i_5.bpl.
- `k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/invert_string.i_4.bpl.
- `examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/interleave_bits.i_3.bpl.

## Results

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| BFS | true | true | 8 | SAFE | 4061 | 7 | 0 | 7 | 21.0 | 1.0 | 7 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BFS | true | true | 16 | SAFE | 5723 | 7 | 0 | 7 | 21.0 | 1.0 | 7 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| DFS | true | true | 8 | SAFE | 4029 | 7 | 0 | 7 | 21.0 | 1.0 | 7 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| DFS | true | true | 16 | SAFE | 5692 | 7 | 0 | 7 | 21.0 | 1.0 | 7 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | SAFE | 4850 | 19 | 7 | 3 | 149.1035298035298 | 0.8719504666873088 | 12 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | SAFE | 7823 | 33 | 20 | 6 | 604.3748584748587 | 1.144649353172081 | 23 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | SAFE | 4833 | 19 | 7 | 3 | 149.1035298035298 | 0.8719504666873088 | 12 | 0 | 141 | 141 | 115 | 34 | 16 | 0 | 0 | 6 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | SAFE | 8027 | 35 | 19 | 6 | 577.9748584748587 | 0.9713863167644684 | 22 | 0 | 366 | 366 | 319 | 128 | 34 | 0 | 0 | 16 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 8 | SAFE | 5043 | 20 | 9 | 0 | 177.44148629148629 | 0.9339025594288752 | 13 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 8 | 19 | 76 | 19 | 0 | 9.5 | 2.375 | 7 |
| BATCH_LCPS | true | true | 16 | SAFE | 8204 | 34 | 16 | 0 | 508.26358086358107 | 0.90599568781387 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 6 | 34 | 80 | 34 | 0 | 13.333333333333334 | 5.666666666666667 | 6 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t: -17, 16t: +204 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t: +0, 16t: +2 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t: +0, 16t: -1 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t: +0, 16t: -0.173263 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 8t: +6, 16t: +16 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 8t: +193, 16t: +381 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 8t: +1, 16t: +1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 8t: +2, 16t: -4 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 8t: -3, 16t: -6 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 8t: +0.0619521, 16t: -0.238654 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 8t: +76, 16t: +80 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 8t: +19, 16t: +34 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 8t: +7, 16t: +6 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 8t: +210, 16t: +177 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 8t: +1, 16t: -1 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 8t: +2, 16t: -3 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 8t: -3, 16t: -6 (BATCH_LCPS - LCPS).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| BFS | true | true | 8 | SAFE | 4653 | 6 | 0 | 6 | 15.0 | 1.0 | 6 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BFS | true | true | 16 | SAFE | 6347 | 6 | 0 | 6 | 15.0 | 1.0 | 6 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| DFS | true | true | 8 | SAFE | 4657 | 6 | 0 | 6 | 15.0 | 1.0 | 6 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| DFS | true | true | 16 | SAFE | 6338 | 6 | 0 | 6 | 15.0 | 1.0 | 6 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | SAFE | 6012 | 23 | 11 | 11 | 237.9718253968254 | 0.9406001003827091 | 16 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | SAFE | 8947 | 34 | 15 | 11 | 536.8932900432901 | 0.9570290375103211 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | SAFE | 6865 | 23 | 11 | 11 | 237.9718253968254 | 0.9406001003827091 | 16 | 0 | 200 | 200 | 162 | 68 | 22 | 0 | 0 | 7 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | SAFE | 8753 | 35 | 15 | 10 | 537.8023809523811 | 0.9038695478191279 | 20 | 0 | 494 | 494 | 426 | 107 | 34 | 0 | 0 | 11 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 8 | SAFE | 8840 | 33 | 15 | 0 | 508.5647824397824 | 0.9631908758329212 | 26 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 16 | 32 | 128 | 32 | 0 | 8.0 | 2.0 | 16 |
| BATCH_LCPS | true | true | 16 | SAFE | 9554 | 34 | 13 | 0 | 539.4204545454543 | 0.961533787068546 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 11 | 34 | 104 | 34 | 0 | 9.454545454545455 | 3.090909090909091 | 9 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t: +853, 16t: -194 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t: +0, 16t: +1 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t: +0, 16t: -1 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t: +0, 16t: -0.0531595 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 8t: +7, 16t: +11 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 8t: +2828, 16t: +607 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 8t: +10, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 8t: +4, 16t: -2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 8t: -11, 16t: -11 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 8t: +0.0225908, 16t: +0.00450475 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 8t: +128, 16t: +104 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 8t: +32, 16t: +34 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 8t: +16, 16t: +9 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 8t: +1975, 16t: +801 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 8t: +10, 16t: -1 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 8t: +4, 16t: -2 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 8t: -11, 16t: -10 (BATCH_LCPS - LCPS).

### examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| BFS | true | true | 8 | UNKNOWN | 4193 | 5 | 0 | 5 | 10.0 | 1.0 | 4 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BFS | true | true | 16 | UNKNOWN | 5877 | 5 | 0 | 5 | 10.0 | 1.0 | 4 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| DFS | true | true | 8 | UNKNOWN | 4204 | 5 | 0 | 5 | 10.0 | 1.0 | 4 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| DFS | true | true | 16 | UNKNOWN | 5862 | 5 | 0 | 5 | 10.0 | 1.0 | 4 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | UNKNOWN | 6132 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | UNKNOWN | 4776 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | UNKNOWN | 6094 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 227 | 227 | 189 | 25 | 14 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | UNKNOWN | 4981 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 463 | 463 | 340 | 0 | 15 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 8 | UNKNOWN | 6230 | 18 | 8 | 0 | 137.5793650793651 | 0.8992115364664385 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 11 | 17 | 68 | 17 | 0 | 6.181818181818182 | 1.5454545454545454 | 9 |
| BATCH_LCPS | true | true | 16 | UNKNOWN | 5648 | 17 | 0 | 0 | 55.82389081506727 | 0.4104697854049064 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 2 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t: -38, 16t: +205 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 8t: +1, 16t: +1 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 8t: +98, 16t: +872 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 8t: +3, 16t: +1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 8t: +3, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 8t: -8, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 8t: +0.0460747, 16t: +0.078604 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 8t: +68, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 8t: +17, 16t: +16 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 8t: +9, 16t: +2 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 8t: +136, 16t: +667 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 8t: +3, 16t: +1 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 8t: +3, 16t: +0 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 8t: -8, 16t: -1 (BATCH_LCPS - LCPS).

## Interpretation Notes

- Negative LCPS - PAPER runtime, checked_paths, stale_paths, and duplicate-failure deltas are improvements for that metric.
- LCPS cache activation requires positive LCPS search invocations and positive checked/stale prefix queries. Hits show that the query keys matched cached run prefixes.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log` or `*-<mode>-stale-<on|off>-threads-*.log`.
