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

- LCPS-priority rows with search invocations > 0: 30 / 30.
- LCPS-priority rows with checked prefix queries > 0: 30 / 30.
- LCPS-priority rows with stale prefix queries > 0: 30 / 30.
- LCPS-priority rows with checked prefix hits > 0: 30 / 30.
- LCPS-priority rows with stale prefix hits > 0: 14 / 30.
- Total LCPS effective priority decisions: 54.
- Total LCPS_FULL cache-suffix invocations/fallbacks: 0 / 0.
- BatchLcpsInvocations: 150.
- BatchLcpsCandidatesGenerated/Selected: 1336 / 409.
- BatchLcpsCandidateGenerationFailures: 7.
- BatchLcpsEffectiveBatchDecisions: 103.

### Performance

- LCPS - PAPER runtime_ms: wins/losses/ties 10/20/0, mean 1910.53, median 24.00.
- BATCH_LCPS - PAPER runtime_ms: wins/losses/ties 12/18/0, mean -3340.17, median 53.50.
- BATCH_LCPS - LCPS runtime_ms: wins/losses/ties 12/17/1, mean -5250.70, median 59.50.

### Work

- LCPS - PAPER checked_paths: wins/losses/ties 3/0/27, mean -0.10, median 0.00.
- BATCH_LCPS - PAPER checked_paths: wins/losses/ties 6/9/15, mean 0.13, median 0.00.
- BATCH_LCPS - LCPS checked_paths: wins/losses/ties 6/9/15, mean 0.23, median 0.00.
- LCPS - PAPER stale_paths: wins/losses/ties 1/1/28, mean 0.03, median 0.00.
- BATCH_LCPS - PAPER stale_paths: wins/losses/ties 6/9/15, mean 0.40, median 0.00.
- BATCH_LCPS - LCPS stale_paths: wins/losses/ties 7/9/14, mean 0.37, median 0.00.
- LCPS - PAPER duplicate freshness failures: wins/losses/ties 4/2/24, mean -0.07, median 0.00.
- BATCH_LCPS - PAPER duplicate freshness failures: wins/losses/ties 30/0/0, mean -4.00, median -2.50.
- BATCH_LCPS - LCPS duplicate freshness failures: wins/losses/ties 30/0/0, mean -3.93, median -3.00.
- LCPS - PAPER search_failed: wins/losses/ties 0/0/30, mean 0.00, median 0.00.
- BATCH_LCPS - PAPER search_failed: wins/losses/ties 0/3/27, mean 0.23, median 0.00.
- BATCH_LCPS - LCPS search_failed: wins/losses/ties 0/3/27, mean 0.23, median 0.00.

### Batch Quality

- Average candidate pool size across BATCH_LCPS rows: 10.95.
- Average selected batch size across BATCH_LCPS rows: 3.55.
- Total effective batch decisions: 103.
- BATCH_LCPS - PAPER checked_paths: wins/losses/ties 6/9/15, mean 0.13, median 0.00.
- BATCH_LCPS - PAPER stale_paths: wins/losses/ties 6/9/15, mean 0.40, median 0.00.

### Divergence

- LCPS - PAPER avg divergence: wins/losses/ties 4/2/24, mean 0.00, median 0.00.
- BATCH_LCPS - PAPER avg divergence: wins/losses/ties 6/10/14, mean -0.00, median 0.00.
- BATCH_LCPS - LCPS avg divergence: wins/losses/ties 7/9/14, mean -0.01, median 0.00.

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
- `trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-gauss_sum.i_3-f2583875`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/gauss_sum.i_3.bpl.
- `trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_1.i_2-db2cf3f1`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/jain_1.i_2.bpl.
- `trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_2.i_2-b9aa1d3f`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/jain_2.i_2.bpl.
- `trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_4.i_2-d2b55c6a`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/jain_4.i_2.bpl.
- `trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_6.i_2-f35d9452`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/jain_6.i_2.bpl.

## Results

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 4043 | 14 | 6 | 4 | 88.05537518037518 | 0.9676414854986284 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | SAFE | 4847 | 19 | 7 | 2 | 148.64898434898433 | 0.8692923061344113 | 12 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | SAFE | 7799 | 38 | 20 | 2 | 618.0238592290066 | 0.8791235550910478 | 23 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 4 | SAFE | 4237 | 14 | 6 | 3 | 87.53373015873017 | 0.9619091226234084 | 11 | 0 | 57 | 57 | 43 | 16 | 9 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | SAFE | 4854 | 18 | 7 | 3 | 147.1035298035298 | 0.9614609791080378 | 12 | 0 | 141 | 141 | 115 | 34 | 16 | 0 | 0 | 6 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | SAFE | 7803 | 37 | 20 | 4 | 586.6008200133201 | 0.8807820120320122 | 23 | 0 | 365 | 365 | 315 | 87 | 34 | 0 | 0 | 15 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 4 | SAFE | 3817 | 15 | 7 | 0 | 102.07698412698413 | 0.9721617535903251 | 12 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 10 | 14 | 56 | 14 | 0 | 5.6 | 1.4 | 7 |
| BATCH_LCPS | true | true | 8 | SAFE | 5247 | 23 | 13 | 0 | 239.33196248196245 | 0.9459761362923417 | 16 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 22 | 88 | 22 | 0 | 9.777777777777779 | 2.4444444444444446 | 9 |
| BATCH_LCPS | true | true | 16 | SAFE | 9675 | 42 | 26 | 0 | 836.28658008658 | 0.971296840983252 | 29 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 12 | 42 | 124 | 42 | 0 | 10.333333333333334 | 3.5 | 11 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: +194, 8t: +7, 16t: +4 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: -1, 16t: -1 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: -1, 8t: +1, 16t: +2 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: -0.00573236, 8t: +0.0921687, 16t: +0.00165846 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +1, 8t: +6, 16t: +15 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 4t: -226, 8t: +400, 16t: +1876 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +1, 8t: +4, 16t: +4 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +1, 8t: +6, 16t: +6 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -4, 8t: -2, 16t: -2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0.00452027, 8t: +0.0766838, 16t: +0.0921733 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +56, 8t: +88, 16t: +124 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +14, 8t: +22, 16t: +42 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +7, 8t: +9, 16t: +11 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 4t: -420, 8t: +393, 16t: +1872 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 4t: +1, 8t: +5, 16t: +5 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 4t: +1, 8t: +6, 16t: +6 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 4t: -3, 8t: -3, 16t: -4 (BATCH_LCPS - LCPS).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 4052 | 14 | 3 | 9 | 88.93333333333334 | 0.9772893772893774 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | SAFE | 5962 | 23 | 11 | 11 | 237.9718253968254 | 0.9406001003827091 | 16 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | SAFE | 8731 | 35 | 15 | 11 | 538.8023809523811 | 0.9055502200880354 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 4 | SAFE | 4260 | 14 | 5 | 7 | 88.28333333333333 | 0.9701465201465201 | 11 | 0 | 90 | 90 | 76 | 43 | 12 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | SAFE | 6002 | 22 | 10 | 10 | 215.97182539682538 | 0.9349429670858241 | 15 | 0 | 198 | 198 | 161 | 65 | 21 | 0 | 0 | 9 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | SAFE | 8908 | 35 | 15 | 10 | 538.1335497835498 | 0.9044261340899997 | 20 | 0 | 495 | 495 | 431 | 107 | 34 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 4 | SAFE | 3875 | 11 | 4 | 0 | 51.7 | 0.9400000000000001 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 6 | 10 | 40 | 10 | 0 | 6.666666666666667 | 1.6666666666666667 | 4 |
| BATCH_LCPS | true | true | 8 | SAFE | 6211 | 26 | 11 | 0 | 307.673717948718 | 0.9466883629191323 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 12 | 25 | 100 | 25 | 0 | 8.333333333333334 | 2.0833333333333335 | 12 |
| BATCH_LCPS | true | true | 16 | SAFE | 9938 | 37 | 17 | 0 | 610.1002525252524 | 0.9160664452331116 | 22 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 10 | 36 | 112 | 36 | 0 | 11.2 | 3.6 | 10 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: +208, 8t: +40, 16t: +177 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: -1, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +2, 8t: -1, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: -2, 8t: -1, 16t: -1 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: -0.00714286, 8t: -0.00565713, 16t: -0.00112409 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +1, 8t: +9, 16t: +10 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 4t: -177, 8t: +249, 16t: +1207 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: -3, 8t: +3, 16t: +2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +1, 8t: +0, 16t: +2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -9, 8t: -11, 16t: -11 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: -0.0372894, 8t: +0.00608826, 16t: +0.0105162 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +40, 8t: +100, 16t: +112 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +10, 8t: +25, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +4, 8t: +12, 16t: +10 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 4t: -385, 8t: +209, 16t: +1030 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 4t: -3, 8t: +4, 16t: +2 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 4t: -1, 8t: +1, 16t: +2 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 4t: -7, 8t: -10, 16t: -10 (BATCH_LCPS - LCPS).

### examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | UNKNOWN | 4274 | 11 | 5 | 5 | 52.4 | 0.9527272727272728 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | UNKNOWN | 6116 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | UNKNOWN | 5198 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 4 | UNKNOWN | 4287 | 11 | 5 | 5 | 52.4 | 0.9527272727272728 | 7 | 0 | 58 | 58 | 45 | 8 | 7 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | UNKNOWN | 6128 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 227 | 227 | 189 | 25 | 14 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | UNKNOWN | 5015 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 463 | 463 | 340 | 0 | 15 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 4 | UNKNOWN | 4757 | 12 | 6 | 0 | 63.4 | 0.9606060606060606 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 11 | 44 | 11 | 0 | 4.888888888888889 | 1.2222222222222223 | 3 |
| BATCH_LCPS | true | true | 8 | UNKNOWN | 6175 | 16 | 6 | 0 | 104.57936507936509 | 0.871494708994709 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 15 | 60 | 15 | 0 | 6.666666666666667 | 1.6666666666666667 | 7 |
| BATCH_LCPS | true | true | 16 | UNKNOWN | 5634 | 17 | 0 | 0 | 55.82389081506727 | 0.4104697854049064 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 2 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: +13, 8t: +12, 16t: -183 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +1, 8t: +1, 16t: +1 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 4t: +483, 8t: +59, 16t: +436 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +1, 8t: +1, 16t: +1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +1, 8t: +1, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -5, 8t: -8, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0.00787879, 8t: +0.0183579, 16t: +0.078604 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +44, 8t: +60, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +11, 8t: +15, 16t: +16 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +3, 8t: +7, 16t: +2 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 4t: +470, 8t: +47, 16t: +619 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 4t: +1, 8t: +1, 16t: +1 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 4t: +1, 8t: +1, 16t: +0 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 4t: -5, 8t: -8, 16t: -1 (BATCH_LCPS - LCPS).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-diamond2.i_4-22ecac6d

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 4083 | 9 | 2 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | SAFE | 5274 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | SAFE | 8502 | 22 | 5 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 4 | SAFE | 4075 | 9 | 2 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 59 | 59 | 44 | 4 | 7 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | SAFE | 5305 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 223 | 223 | 184 | 20 | 13 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | SAFE | 8909 | 22 | 5 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 700 | 700 | 569 | 19 | 21 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 4 | SAFE | 4295 | 10 | 4 | 0 | 42.4 | 0.9422222222222222 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 8 | 10 | 36 | 9 | 1 | 4.5 | 1.125 | 6 |
| BATCH_LCPS | true | true | 8 | SAFE | 5955 | 14 | 4 | 0 | 75.57936507936509 | 0.8305424733996164 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 8 | 14 | 52 | 13 | 1 | 6.5 | 1.625 | 7 |
| BATCH_LCPS | true | true | 16 | SAFE | 9413 | 22 | 8 | 0 | 150.82389081506727 | 0.6529172762557025 | 12 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 12 | 36 | 56 | 21 | 5 | 4.666666666666667 | 1.75 | 7 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: -8, 8t: +31, 16t: +407 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +1, 8t: +1, 16t: +1 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 4t: +212, 8t: +681, 16t: +911 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +1, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +2, 8t: -1, 16t: +3 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -5, 8t: -7, 16t: -7 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0.0144444, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +36, 8t: +52, 16t: +56 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +9, 8t: +13, 16t: +21 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +6, 8t: +7, 16t: +7 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 4t: +220, 8t: +650, 16t: +504 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 4t: +1, 8t: +0, 16t: +0 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 4t: +2, 8t: -1, 16t: +3 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 4t: -5, 8t: -7, 16t: -7 (BATCH_LCPS - LCPS).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-count_up_down.i_3-aa4a8d5f

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 122581 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | SAFE | 5083 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | SAFE | 7615 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 4 | SAFE | 122682 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 58 | 58 | 45 | 8 | 7 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | SAFE | 5158 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 162 | 162 | 128 | 3 | 10 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | SAFE | 7599 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 594 | 594 | 468 | 3 | 18 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 4 | SAFE | 3060 | 6 | 1 | 0 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 5 | 20 | 5 | 0 | 6.666666666666667 | 1.6666666666666667 | 3 |
| BATCH_LCPS | true | true | 8 | SAFE | 4815 | 10 | 1 | 0 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 9 | 36 | 9 | 0 | 12.0 | 3.0 | 3 |
| BATCH_LCPS | true | true | 16 | SAFE | 7228 | 18 | 1 | 0 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 17 | 40 | 17 | 0 | 13.333333333333334 | 5.666666666666667 | 3 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: +101, 8t: +75, 16t: -16 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +1, 8t: +1, 16t: +1 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 4t: -119521, 8t: -268, 16t: -387 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: -7, 8t: -1, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: -6, 8t: -1, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -5, 8t: -4, 16t: -4 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: -0.14, 8t: -0.0623056, 16t: -0.0551607 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +20, 8t: +36, 16t: +40 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +5, 8t: +9, 16t: +17 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +3, 8t: +3, 16t: +3 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 4t: -119622, 8t: -343, 16t: -371 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 4t: -7, 8t: -1, 16t: -1 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 4t: -6, 8t: -1, 16t: -1 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 4t: -5, 8t: -4, 16t: -4 (BATCH_LCPS - LCPS).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-gauss_sum.i_3-f2583875

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 3455 | 7 | 1 | 3 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | SAFE | 5168 | 11 | 1 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | SAFE | 8597 | 19 | 1 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 4 | SAFE | 3481 | 7 | 1 | 3 | 18.4 | 0.8761904761904761 | 4 | 0 | 40 | 40 | 29 | 0 | 5 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | SAFE | 5199 | 11 | 1 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 168 | 168 | 134 | 0 | 10 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | SAFE | 8573 | 19 | 1 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 600 | 600 | 474 | 0 | 18 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 4 | SAFE | 3553 | 7 | 1 | 0 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 6 | 24 | 6 | 0 | 6.0 | 1.5 | 3 |
| BATCH_LCPS | true | true | 8 | SAFE | 4958 | 10 | 0 | 0 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 9 | 36 | 9 | 0 | 12.0 | 3.0 | 3 |
| BATCH_LCPS | true | true | 16 | SAFE | 8358 | 18 | 0 | 0 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 17 | 40 | 17 | 0 | 13.333333333333334 | 5.666666666666667 | 3 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: +26, 8t: +31, 16t: -24 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +1, 8t: +1, 16t: +1 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 4t: +98, 8t: -210, 16t: -239 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +0, 8t: -1, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +0, 8t: -1, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -3, 8t: -4, 16t: -4 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0, 8t: -0.0623056, 16t: -0.0551607 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +24, 8t: +36, 16t: +40 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +6, 8t: +9, 16t: +17 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +3, 8t: +3, 16t: +3 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 4t: +72, 8t: -241, 16t: -215 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 4t: +0, 8t: -1, 16t: -1 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 4t: +0, 8t: -1, 16t: -1 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 4t: -3, 8t: -4, 16t: -4 (BATCH_LCPS - LCPS).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_1.i_2-db2cf3f1

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 39124 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | SAFE | 40948 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | SAFE | 44400 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 4 | SAFE | 45237 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 36 | 36 | 25 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | SAFE | 40846 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 150 | 150 | 121 | 0 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | SAFE | 44270 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 618 | 618 | 505 | 0 | 16 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 4 | SAFE | 39172 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 |
| BATCH_LCPS | true | true | 8 | SAFE | 41648 | 9 | 0 | 0 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 |
| BATCH_LCPS | true | true | 16 | SAFE | 56336 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: +6113, 8t: -102, 16t: -130 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 4t: +48, 8t: +700, 16t: +11936 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +16, 8t: +32, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +4, 8t: +8, 16t: +16 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 4t: -6065, 8t: +802, 16t: +12066 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (BATCH_LCPS - LCPS).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_2.i_2-b9aa1d3f

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 51329 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | SAFE | 53027 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | SAFE | 56510 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 4 | SAFE | 40721 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 36 | 36 | 25 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | SAFE | 52984 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 150 | 150 | 121 | 0 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | SAFE | 56506 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 618 | 618 | 505 | 0 | 16 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 4 | SAFE | 51290 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 |
| BATCH_LCPS | true | true | 8 | SAFE | 53058 | 9 | 0 | 0 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 |
| BATCH_LCPS | true | true | 16 | SAFE | 45235 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: -10608, 8t: -43, 16t: -4 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 4t: -39, 8t: +31, 16t: -11275 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +16, 8t: +32, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +4, 8t: +8, 16t: +16 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 4t: +10569, 8t: +74, 16t: -11271 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (BATCH_LCPS - LCPS).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_4.i_2-d2b55c6a

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 27205 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | SAFE | 29064 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | SAFE | 32479 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 4 | SAFE | 27239 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 36 | 36 | 25 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | SAFE | 77207 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 150 | 150 | 121 | 0 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | SAFE | 45259 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 618 | 618 | 505 | 0 | 16 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 4 | SAFE | 27239 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 |
| BATCH_LCPS | true | true | 8 | SAFE | 29036 | 9 | 0 | 0 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 |
| BATCH_LCPS | true | true | 16 | SAFE | 32388 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: +34, 8t: +48143, 16t: +12780 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 4t: +34, 8t: -28, 16t: -91 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +16, 8t: +32, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +4, 8t: +8, 16t: +16 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 4t: +0, 8t: -48171, 16t: -12871 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (BATCH_LCPS - LCPS).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_6.i_2-f35d9452

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 27237 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | SAFE | 29112 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | SAFE | 32384 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 4 | SAFE | 27259 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 36 | 36 | 25 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | SAFE | 29065 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 150 | 150 | 121 | 0 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | SAFE | 32447 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 618 | 618 | 505 | 0 | 16 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 4 | SAFE | 40088 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 |
| BATCH_LCPS | true | true | 8 | SAFE | 29048 | 9 | 0 | 0 | 16.46071428571429 | 0.4572420634920636 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 |
| BATCH_LCPS | true | true | 16 | SAFE | 32492 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: +22, 8t: -47, 16t: +63 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 4t: +12851, 8t: -64, 16t: +108 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0, 8t: +1.11022e-16, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +16, 8t: +32, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +4, 8t: +8, 16t: +16 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 4t: +12829, 8t: -17, 16t: +45 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (BATCH_LCPS - LCPS).

## Interpretation Notes

- Negative LCPS - PAPER runtime, checked_paths, stale_paths, and duplicate-failure deltas are improvements for that metric.
- LCPS cache activation requires positive LCPS search invocations and positive checked/stale prefix queries. Hits show that the query keys matched cached run prefixes.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log` or `*-<mode>-stale-<on|off>-threads-*.log`.
