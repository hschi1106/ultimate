# Checked Path Divergence Results

## Purpose

This experiment compares PAPER, LCPS, BATCH_LCPS, and optional BFS/DFS path selection under parallel TraceAbstraction.

Each unordered checked-path pair contributes normalized prefix-LCA divergence `1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.

## Summary

### Correctness

- Modes present: PAPER, BATCH_LCPS, ADAPTIVE_DUPLICATE, ADAPTIVE_STALE, ADAPTIVE_SEARCH_FAILED, ADAPTIVE_IDLE, ADAPTIVE_ALWAYS, ADAPTIVE_NEVER.
- Result mismatch groups across modes: 2.
- ERROR rows: 0; TIMEOUT rows: 2.
- BFS/DFS were not part of this run.

### Activation

- LCPS-priority rows with search invocations > 0: 0 / 0.
- LCPS-priority rows with checked prefix queries > 0: 0 / 0.
- LCPS-priority rows with stale prefix queries > 0: 0 / 0.
- LCPS-priority rows with checked prefix hits > 0: 0 / 0.
- LCPS-priority rows with stale prefix hits > 0: 0 / 0.
- Total LCPS effective priority decisions: 0.
- Total LCPS_FULL cache-suffix invocations/fallbacks: 0 / 0.
- BatchLcpsInvocations: 325.
- BatchLcpsCandidatesGenerated/Selected: 3012 / 919.
- BatchLcpsCandidateGenerationFailures: 13.
- BatchLcpsEffectiveBatchDecisions: 225.

### Performance

- BATCH_LCPS - PAPER runtime_ms: wins/losses/ties 12/18/0, mean -4560.30, median 93.00.
- ADAPTIVE_DUPLICATE - PAPER runtime_ms: wins/losses/ties 12/17/1, mean 78905.60, median 49.50.
- ADAPTIVE_STALE - PAPER runtime_ms: wins/losses/ties 17/13/0, mean -269.50, median -2.00.
- ADAPTIVE_SEARCH_FAILED - PAPER runtime_ms: wins/losses/ties 14/16/0, mean -825.10, median 10.50.
- ADAPTIVE_IDLE - PAPER runtime_ms: wins/losses/ties 16/13/1, mean -1128.07, median -2.50.
- ADAPTIVE_ALWAYS - PAPER runtime_ms: wins/losses/ties 12/18/0, mean -4485.27, median 85.50.
- ADAPTIVE_NEVER - PAPER runtime_ms: wins/losses/ties 17/13/0, mean -826.30, median -7.00.

### Work

- BATCH_LCPS - PAPER checked_paths: wins/losses/ties 6/9/15, mean 0.40, median 0.00.
- ADAPTIVE_DUPLICATE - PAPER checked_paths: wins/losses/ties 6/2/22, mean -0.40, median 0.00.
- ADAPTIVE_STALE - PAPER checked_paths: wins/losses/ties 4/3/23, mean -0.10, median 0.00.
- ADAPTIVE_SEARCH_FAILED - PAPER checked_paths: wins/losses/ties 2/3/25, mean 0.20, median 0.00.
- ADAPTIVE_IDLE - PAPER checked_paths: wins/losses/ties 1/2/27, mean 0.03, median 0.00.
- ADAPTIVE_ALWAYS - PAPER checked_paths: wins/losses/ties 7/6/17, mean 0.10, median 0.00.
- ADAPTIVE_NEVER - PAPER checked_paths: wins/losses/ties 2/0/28, mean -0.10, median 0.00.
- BATCH_LCPS - PAPER stale_paths: wins/losses/ties 6/9/15, mean 0.53, median 0.00.
- ADAPTIVE_DUPLICATE - PAPER stale_paths: wins/losses/ties 3/3/24, mean 0.13, median 0.00.
- ADAPTIVE_STALE - PAPER stale_paths: wins/losses/ties 3/4/23, mean -0.07, median 0.00.
- ADAPTIVE_SEARCH_FAILED - PAPER stale_paths: wins/losses/ties 3/4/23, mean 0.20, median 0.00.
- ADAPTIVE_IDLE - PAPER stale_paths: wins/losses/ties 1/2/27, mean 0.13, median 0.00.
- ADAPTIVE_ALWAYS - PAPER stale_paths: wins/losses/ties 8/7/15, mean 0.17, median 0.00.
- ADAPTIVE_NEVER - PAPER stale_paths: wins/losses/ties 4/3/23, mean -0.13, median 0.00.
- BATCH_LCPS - PAPER duplicate freshness failures: wins/losses/ties 30/0/0, mean -4.03, median -3.50.
- ADAPTIVE_DUPLICATE - PAPER duplicate freshness failures: wins/losses/ties 5/2/23, mean -0.13, median 0.00.
- ADAPTIVE_STALE - PAPER duplicate freshness failures: wins/losses/ties 6/3/21, mean -0.43, median 0.00.
- ADAPTIVE_SEARCH_FAILED - PAPER duplicate freshness failures: wins/losses/ties 5/1/24, mean 0.03, median 0.00.
- ADAPTIVE_IDLE - PAPER duplicate freshness failures: wins/losses/ties 1/4/25, mean 0.10, median 0.00.
- ADAPTIVE_ALWAYS - PAPER duplicate freshness failures: wins/losses/ties 30/0/0, mean -3.80, median -2.50.
- ADAPTIVE_NEVER - PAPER duplicate freshness failures: wins/losses/ties 3/3/24, mean -0.03, median 0.00.
- BATCH_LCPS - PAPER search_failed: wins/losses/ties 0/3/27, mean 0.20, median 0.00.
- ADAPTIVE_DUPLICATE - PAPER search_failed: wins/losses/ties 0/0/30, mean 0.00, median 0.00.
- ADAPTIVE_STALE - PAPER search_failed: wins/losses/ties 0/0/30, mean 0.00, median 0.00.
- ADAPTIVE_SEARCH_FAILED - PAPER search_failed: wins/losses/ties 0/0/30, mean 0.00, median 0.00.
- ADAPTIVE_IDLE - PAPER search_failed: wins/losses/ties 0/0/30, mean 0.00, median 0.00.
- ADAPTIVE_ALWAYS - PAPER search_failed: wins/losses/ties 0/3/27, mean 0.47, median 0.00.
- ADAPTIVE_NEVER - PAPER search_failed: wins/losses/ties 0/0/30, mean 0.00, median 0.00.

### Batch Quality

- Average candidate pool size across BATCH_LCPS rows: 4.26.
- Average selected batch size across BATCH_LCPS rows: 1.34.
- Total effective batch decisions: 225.
- BATCH_LCPS - PAPER checked_paths: wins/losses/ties 6/9/15, mean 0.40, median 0.00.
- BATCH_LCPS - PAPER stale_paths: wins/losses/ties 6/9/15, mean 0.53, median 0.00.

### Adaptive Trigger Comparison

- ADAPTIVE_DUPLICATE: adaptive invocations/fallbacks 7/139, triggers dup/stale/failed/idle 7/0/0/0, candidates 144, effective batch decisions 7, candidates/invocation 20.57, generation/selection time 61ms/23ms, vs PAPER runtime [wins/losses/ties 12/17/1, mean 78905.60, median 49.50], vs BATCH_LCPS runtime [wins/losses/ties 15/15/0, mean 83465.90, median 5.50], vs PAPER checked [wins/losses/ties 6/2/22, mean -0.40, median 0.00], vs PAPER stale [wins/losses/ties 3/3/24, mean 0.13, median 0.00].
- ADAPTIVE_STALE: adaptive invocations/fallbacks 11/129, triggers dup/stale/failed/idle 0/11/0/0, candidates 180, effective batch decisions 10, candidates/invocation 16.36, generation/selection time 77ms/22ms, vs PAPER runtime [wins/losses/ties 17/13/0, mean -269.50, median -2.00], vs BATCH_LCPS runtime [wins/losses/ties 20/10/0, mean 4290.80, median -143.00], vs PAPER checked [wins/losses/ties 4/3/23, mean -0.10, median 0.00], vs PAPER stale [wins/losses/ties 3/4/23, mean -0.07, median 0.00].
- ADAPTIVE_SEARCH_FAILED: adaptive invocations/fallbacks 0/142, triggers dup/stale/failed/idle 0/0/0/0, candidates 0, effective batch decisions 0, candidates/invocation 0.00, generation/selection time 0ms/0ms, vs PAPER runtime [wins/losses/ties 14/16/0, mean -825.10, median 10.50], vs BATCH_LCPS runtime [wins/losses/ties 21/9/0, mean 3735.20, median -67.50], vs PAPER checked [wins/losses/ties 2/3/25, mean 0.20, median 0.00], vs PAPER stale [wins/losses/ties 3/4/23, mean 0.20, median 0.00].
- ADAPTIVE_IDLE: adaptive invocations/fallbacks 0/144, triggers dup/stale/failed/idle 0/0/0/0, candidates 0, effective batch decisions 0, candidates/invocation 0.00, generation/selection time 0ms/0ms, vs PAPER runtime [wins/losses/ties 16/13/1, mean -1128.07, median -2.50], vs BATCH_LCPS runtime [wins/losses/ties 20/10/0, mean 3432.23, median -43.50], vs PAPER checked [wins/losses/ties 1/2/27, mean 0.03, median 0.00], vs PAPER stale [wins/losses/ties 1/2/27, mean 0.13, median 0.00].
- ADAPTIVE_ALWAYS: adaptive invocations/fallbacks 153/7, triggers dup/stale/failed/idle 0/0/0/0, candidates 1324, effective batch decisions 102, candidates/invocation 8.65, generation/selection time 1676ms/101ms, vs PAPER runtime [wins/losses/ties 12/18/0, mean -4485.27, median 85.50], vs BATCH_LCPS runtime [wins/losses/ties 13/17/0, mean 75.03, median 9.50], vs PAPER checked [wins/losses/ties 7/6/17, mean 0.10, median 0.00], vs PAPER stale [wins/losses/ties 8/7/15, mean 0.17, median 0.00].
- ADAPTIVE_NEVER: adaptive invocations/fallbacks 0/139, triggers dup/stale/failed/idle 0/0/0/0, candidates 0, effective batch decisions 0, candidates/invocation 0.00, generation/selection time 0ms/0ms, vs PAPER runtime [wins/losses/ties 17/13/0, mean -826.30, median -7.00], vs BATCH_LCPS runtime [wins/losses/ties 20/10/0, mean 3734.00, median -48.00], vs PAPER checked [wins/losses/ties 2/0/28, mean -0.10, median 0.00], vs PAPER stale [wins/losses/ties 4/3/23, mean -0.13, median 0.00].

### Divergence

- BATCH_LCPS - PAPER avg divergence: wins/losses/ties 8/10/12, mean -0.00, median 0.00.
- ADAPTIVE_DUPLICATE - PAPER avg divergence: wins/losses/ties 5/3/22, mean -0.03, median 0.00.
- ADAPTIVE_STALE - PAPER avg divergence: wins/losses/ties 5/3/22, mean 0.00, median 0.00.
- ADAPTIVE_SEARCH_FAILED - PAPER avg divergence: wins/losses/ties 6/1/23, mean 0.00, median 0.00.
- ADAPTIVE_IDLE - PAPER avg divergence: wins/losses/ties 2/4/24, mean 0.00, median 0.00.
- ADAPTIVE_ALWAYS - PAPER avg divergence: wins/losses/ties 6/10/14, mean -0.00, median 0.00.
- ADAPTIVE_NEVER - PAPER avg divergence: wins/losses/ties 4/3/23, mean 0.00, median 0.00.

### Interpretation

- Cache hits occurred only as instrumentation; effective priority decisions stayed at 0, so ordering did not change.
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

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 4288 | 15 | 8 | 6 | 102.50992063492063 | 0.9762849584278156 | 12 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 5066 | 20 | 9 | 2 | 167.64898434898433 | 0.8823630755209702 | 13 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 8008 | 37 | 20 | 5 | 615.9520643572116 | 0.9248529494853027 | 23 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 3847 | 16 | 9 | 0 | 116.63253968253969 | 0.9719378306878307 | 13 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 10 | 15 | 60 | 15 | 0 | 6.0 | 1.5 | 7 | 27 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 4620 | 19 | 9 | 0 | 152.55615218115216 | 0.8921412408254512 | 12 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 6 | 18 | 72 | 18 | 0 | 12.0 | 3.0 | 6 | 36 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 9880 | 42 | 27 | 0 | 802.7079619726679 | 0.9322972845211009 | 31 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 10 | 41 | 120 | 41 | 0 | 12.0 | 4.1 | 8 | 66 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 4 | SAFE | 3619 | 14 | 6 | 3 | 88.27936507936508 | 0.9701029129600558 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 4 | 1 | 1 | 8 | 1 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 8 | SAFE | 5707 | 25 | 15 | 5 | 304.4791847041847 | 1.0149306156806157 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 8 | 3 | 1 | 10 | 1 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 16 | SAFE | 8593 | 33 | 16 | 2 | 481.4996420246422 | 0.911931140198186 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 40 | 16 | 0 | 20.0 | 8.0 | 2 | 19 | 7 | 2 | 3 | 2 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 3402 | 13 | 5 | 2 | 75.27936507936508 | 0.9651200651200651 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 5 | 1 | 1 | 7 | 0 | 1 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 5722 | 24 | 14 | 4 | 275.9045815295815 | 0.9996542809042808 | 18 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 10 | 40 | 10 | 0 | 20.0 | 5.0 | 2 | 15 | 2 | 2 | 8 | 0 | 2 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 8217 | 34 | 16 | 1 | 484.1467008481716 | 0.8630065968773112 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 18 | 48 | 18 | 0 | 16.0 | 6.0 | 3 | 20 | 6 | 3 | 1 | 0 | 3 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 4 | SAFE | 3777 | 13 | 5 | 5 | 75.50992063492063 | 0.9680759055759055 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 8 | SAFE | 5928 | 27 | 17 | 9 | 354.91039296960344 | 1.011140720711121 | 21 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 13 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 16 | SAFE | 7815 | 37 | 21 | 4 | 613.885397690545 | 0.9217498463821997 | 23 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 6 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 4 | SAFE | 4042 | 13 | 7 | 5 | 87.50992063492063 | 1.1219220594220594 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 9 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 8 | SAFE | 5063 | 20 | 9 | 3 | 168.1035298035298 | 0.8847554200185779 | 13 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 6 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 16 | SAFE | 7798 | 37 | 20 | 6 | 617.0954467101528 | 0.9265697398050343 | 23 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 3829 | 14 | 6 | 0 | 88.07698412698413 | 0.9678789464503751 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 13 | 52 | 13 | 0 | 5.777777777777778 | 1.4444444444444444 | 7 | 24 | 1 | 9 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 5046 | 20 | 10 | 0 | 176.97481962481962 | 0.931446419077998 | 13 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 7 | 19 | 76 | 19 | 0 | 10.857142857142858 | 2.7142857142857144 | 6 | 32 | 2 | 7 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 8196 | 33 | 16 | 0 | 507.26358086358124 | 0.9607264789082978 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 5 | 34 | 80 | 34 | 0 | 16.0 | 6.8 | 5 | 37 | 8 | 5 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 4065 | 13 | 6 | 4 | 86.8670634920635 | 1.1136803011803011 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 4847 | 19 | 7 | 3 | 149.1035298035298 | 0.8719504666873088 | 12 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 5 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 7850 | 37 | 19 | 5 | 616.4469172983881 | 0.9255959719195017 | 23 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 7 | 0 | 0 | 0 | 0 | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: -441, 8t: -446, 16t: +1872 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +1, 8t: -1, 16t: +5 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +1, 8t: +0, 16t: +7 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -6, 8t: -2, 16t: -5 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: -0.00434713, 8t: +0.00977817, 16t: +0.00744434 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +60, 8t: +72, 16t: +120 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +15, 8t: +18, 16t: +41 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +7, 8t: +6, 16t: +8 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE runtime_ms: 4t: -669, 8t: +641, 16t: +585 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE checked_paths: 4t: -1, 8t: +5, 16t: -4 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE stale_paths: 4t: -2, 8t: +6, 16t: -4 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE duplicate freshness failures: 4t: -3, 8t: +3, 16t: -3 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE avg divergence: 4t: -0.00618205, 8t: +0.132568, 16t: -0.0129218 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: -886, 8t: +656, 16t: +209 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: -2, 8t: +4, 16t: -3 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: -3, 8t: +5, 16t: -4 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: -4, 8t: +2, 16t: -4 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: -0.0111649, 8t: +0.117291, 16t: -0.0618464 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED runtime_ms: 4t: -511, 8t: +862, 16t: -193 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED checked_paths: 4t: -2, 8t: +7, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED stale_paths: 4t: -3, 8t: +8, 16t: +1 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED duplicate freshness failures: 4t: -1, 8t: +7, 16t: -1 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED avg divergence: 4t: -0.00820905, 8t: +0.128778, 16t: -0.0031031 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_IDLE runtime_ms: 4t: -246, 8t: -3, 16t: -210 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE checked_paths: 4t: -2, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE stale_paths: 4t: -1, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE duplicate freshness failures: 4t: -1, 8t: +1, 16t: +1 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE avg divergence: 4t: +0.145637, 8t: +0.00239234, 16t: +0.00171679 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: -459, 8t: -20, 16t: +188 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: -1, 8t: +0, 16t: -4 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: -2, 8t: +1, 16t: -4 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -6, 8t: -2, 16t: -5 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: -0.00840601, 8t: +0.0490833, 16t: +0.0358735 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: -223, 8t: -219, 16t: -158 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: -2, 8t: -1, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: -2, 8t: -2, 16t: -1 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: -2, 8t: +1, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0.137395, 8t: -0.0104126, 16t: +0.000743022 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 4057 | 14 | 3 | 7 | 88.28333333333333 | 0.9701465201465201 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 5754 | 21 | 11 | 10 | 209.9718253968254 | 0.9998658352229781 | 16 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 8922 | 35 | 15 | 10 | 538.4946886446888 | 0.9050330901591409 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 5538 | 19 | 11 | 0 | 167.59285714285716 | 0.9800751879699249 | 16 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 14 | 18 | 72 | 18 | 0 | 5.142857142857143 | 1.2857142857142858 | 10 | 26 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 6620 | 26 | 12 | 0 | 306.80782828282827 | 0.944024087024087 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 11 | 25 | 100 | 25 | 0 | 9.090909090909092 | 2.272727272727273 | 11 | 53 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 9764 | 34 | 13 | 0 | 509.4871212121211 | 0.9081766866526223 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 33 | 104 | 33 | 0 | 11.555555555555555 | 3.6666666666666665 | 9 | 82 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 4 | SAFE | 3851 | 12 | 3 | 7 | 63.83333333333333 | 0.9671717171717171 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 2 | 8 | 2 | 0 | 8.0 | 2.0 | 1 | 2 | 1 | 1 | 7 | 1 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 8 | SAFE | 7489 | 28 | 16 | 14 | 382.49743867243865 | 1.0118979859059223 | 22 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 6 | 24 | 6 | 0 | 24.0 | 6.0 | 1 | 13 | 3 | 1 | 15 | 1 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 16 | SAFE | 8528 | 32 | 13 | 9 | 470.56847041847027 | 0.9487267548759482 | 18 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 9 | 32 | 9 | 0 | 32.0 | 9.0 | 1 | 15 | 8 | 1 | 9 | 1 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 4081 | 13 | 5 | 5 | 75.43333333333334 | 0.9670940170940171 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 2 | 8 | 2 | 0 | 8.0 | 2.0 | 1 | 3 | 1 | 1 | 7 | 0 | 1 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 6221 | 24 | 12 | 9 | 278.5252164502165 | 1.0091493349645524 | 18 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 2 | 15 | 3 | 2 | 10 | 0 | 2 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 8497 | 30 | 11 | 6 | 379.56847041847027 | 0.8725711963642995 | 16 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 9 | 32 | 9 | 0 | 32.0 | 9.0 | 1 | 15 | 7 | 1 | 6 | 0 | 1 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 4 | SAFE | 4069 | 14 | 3 | 7 | 88.28333333333333 | 0.9701465201465201 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 8 | SAFE | 5985 | 22 | 10 | 9 | 215.19404761904764 | 0.9315759637188209 | 15 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 9 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 16 | SAFE | 8532 | 34 | 14 | 8 | 497.4994283494282 | 0.8868082501772339 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 4 | SAFE | 4057 | 14 | 5 | 7 | 88.28333333333333 | 0.9701465201465201 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 8 | SAFE | 6002 | 23 | 11 | 11 | 237.9718253968254 | 0.9406001003827091 | 16 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 11 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 16 | SAFE | 8755 | 35 | 15 | 10 | 532.6532745032744 | 0.8952155874004611 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 5572 | 18 | 9 | 0 | 149.59285714285716 | 0.9777310924369749 | 15 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 13 | 17 | 68 | 17 | 0 | 5.230769230769231 | 1.3076923076923077 | 8 | 35 | 1 | 13 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 8217 | 31 | 18 | 0 | 474.7728632478632 | 1.0210169102104585 | 25 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 17 | 31 | 124 | 31 | 0 | 7.294117647058823 | 1.8235294117647058 | 17 | 53 | 5 | 17 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 9578 | 34 | 13 | 0 | 508.0093434343433 | 0.9055425016654961 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 10 | 33 | 100 | 33 | 0 | 10.0 | 3.3 | 9 | 78 | 5 | 10 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 4050 | 14 | 4 | 8 | 88.68333333333334 | 0.9745421245421246 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 9 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 5943 | 21 | 9 | 9 | 194.9718253968254 | 0.9284372637944067 | 14 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 9 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 9355 | 35 | 15 | 11 | 538.3478354978356 | 0.9047862781476229 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 11 | 0 | 0 | 0 | 0 | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: +1481, 8t: +866, 16t: +842 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +5, 8t: +5, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +8, 8t: +1, 16t: -2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -7, 8t: -10, 16t: -10 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0.00992867, 8t: -0.0558417, 16t: +0.0031436 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +72, 8t: +100, 16t: +104 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +18, 8t: +25, 16t: +33 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +10, 8t: +11, 16t: +9 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE runtime_ms: 4t: -206, 8t: +1735, 16t: -394 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE checked_paths: 4t: -2, 8t: +7, 16t: -3 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE stale_paths: 4t: +0, 8t: +5, 16t: -2 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE duplicate freshness failures: 4t: +0, 8t: +4, 16t: -1 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE avg divergence: 4t: -0.0029748, 8t: +0.0120322, 16t: +0.0436937 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: +24, 8t: +467, 16t: -425 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: -1, 8t: +3, 16t: -5 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +2, 8t: +1, 16t: -4 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: -2, 8t: -1, 16t: -4 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: -0.0030525, 8t: +0.0092835, 16t: -0.0324619 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED runtime_ms: 4t: +12, 8t: +231, 16t: -390 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED checked_paths: 4t: +0, 8t: +1, 16t: -1 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED stale_paths: 4t: +0, 8t: -1, 16t: -1 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED duplicate freshness failures: 4t: +0, 8t: -1, 16t: -2 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED avg divergence: 4t: +0, 8t: -0.0682899, 16t: -0.0182248 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_IDLE runtime_ms: 4t: +0, 8t: +248, 16t: -167 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE checked_paths: 4t: +0, 8t: +2, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE stale_paths: 4t: +2, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE duplicate freshness failures: 4t: +0, 8t: +1, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE avg divergence: 4t: +0, 8t: -0.0592657, 16t: -0.0098175 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: +1515, 8t: +2463, 16t: +656 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +4, 8t: +10, 16t: -1 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +6, 8t: +7, 16t: -2 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -7, 8t: -10, 16t: -10 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: +0.00758457, 8t: +0.0211511, 16t: +0.000509412 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: -7, 8t: +189, 16t: +433 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +1, 8t: -2, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +1, 8t: -1, 16t: +1 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0.0043956, 8t: -0.0714286, 16t: -0.000246812 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | n/a | 0 | true | true | 4 | UNKNOWN | 4296 | 11 | 5 | 5 | 52.4 | 0.9527272727272728 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 8 | UNKNOWN | 6104 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 16 | UNKNOWN | 4987 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | UNKNOWN | 4782 | 12 | 6 | 0 | 63.4 | 0.9606060606060606 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 11 | 44 | 11 | 0 | 4.888888888888889 | 1.2222222222222223 | 3 | 29 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | UNKNOWN | 6194 | 18 | 8 | 0 | 137.5793650793651 | 0.8992115364664385 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 11 | 17 | 68 | 17 | 0 | 6.181818181818182 | 1.5454545454545454 | 9 | 62 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | UNKNOWN | 5879 | 17 | 0 | 0 | 55.82389081506727 | 0.4104697854049064 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 2 | 73 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 4 | UNKNOWN | 4278 | 11 | 5 | 5 | 52.4 | 0.9527272727272728 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 8 | UNKNOWN | 6146 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 16 | UNKNOWN | 4981 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | UNKNOWN | 4267 | 11 | 5 | 6 | 52.4 | 0.9527272727272728 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | UNKNOWN | 6154 | 16 | 6 | 9 | 104.57936507936509 | 0.871494708994709 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 9 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | UNKNOWN | 4984 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 4 | UNKNOWN | 4534 | 12 | 6 | 5 | 62.50526315789474 | 0.9470494417862839 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 8 | UNKNOWN | 6139 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 16 | UNKNOWN | 5195 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 4 | UNKNOWN | 4294 | 11 | 5 | 6 | 52.4 | 0.9527272727272728 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 8 | UNKNOWN | 6147 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 16 | UNKNOWN | 5012 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | UNKNOWN | 4792 | 12 | 6 | 0 | 63.4 | 0.9606060606060606 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 11 | 44 | 11 | 0 | 4.888888888888889 | 1.2222222222222223 | 3 | 29 | 1 | 9 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | UNKNOWN | 6168 | 17 | 7 | 0 | 120.57936507936509 | 0.8866129785247433 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 10 | 16 | 64 | 16 | 0 | 6.4 | 1.6 | 8 | 67 | 3 | 10 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | UNKNOWN | 5868 | 17 | 0 | 0 | 55.82389081506727 | 0.4104697854049064 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 2 | 67 | 5 | 2 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | UNKNOWN | 4287 | 11 | 5 | 5 | 52.4 | 0.9527272727272728 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | UNKNOWN | 6165 | 15 | 6 | 8 | 89.57936507936509 | 0.8531368102796675 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | UNKNOWN | 5198 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: +486, 8t: +90, 16t: +892 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +1, 8t: +3, 16t: +1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +1, 8t: +3, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -5, 8t: -8, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0.00787879, 8t: +0.0460747, 16t: +0.078604 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +44, 8t: +68, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +11, 8t: +17, 16t: +16 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +3, 8t: +9, 16t: +2 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE runtime_ms: 4t: -18, 8t: +42, 16t: -6 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: -29, 8t: +50, 16t: -3 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +1, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 8t: +1, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +1, 8t: +1, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 8t: +0.0183579, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED runtime_ms: 4t: +238, 8t: +35, 16t: +208 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED checked_paths: 4t: +1, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED stale_paths: 4t: +1, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED avg divergence: 4t: -0.00567783, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_IDLE runtime_ms: 4t: -2, 8t: +43, 16t: +25 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE duplicate freshness failures: 4t: +1, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: +496, 8t: +64, 16t: +881 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +1, 8t: +2, 16t: +1 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +1, 8t: +2, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -5, 8t: -8, 16t: -1 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: +0.00787879, 8t: +0.0334762, 16t: +0.078604 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: -9, 8t: +61, 16t: +211 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +0, 8t: +1, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-diamond2.i_4-22ecac6d

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 4071 | 9 | 2 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 5310 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 8724 | 22 | 4 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 4279 | 10 | 4 | 0 | 41.62222222222222 | 0.9249382716049382 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 7 | 10 | 36 | 9 | 1 | 5.142857142857143 | 1.2857142857142858 | 6 | 26 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 5944 | 14 | 4 | 0 | 75.57936507936509 | 0.8305424733996164 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 8 | 14 | 52 | 13 | 1 | 6.5 | 1.625 | 7 | 62 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 9408 | 22 | 7 | 0 | 150.82389081506727 | 0.6529172762557025 | 11 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 11 | 31 | 56 | 21 | 4 | 5.090909090909091 | 1.9090909090909092 | 7 | 83 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 4 | SAFE | 4061 | 9 | 3 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 6 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 8 | SAFE | 5304 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 7 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 16 | SAFE | 8700 | 22 | 4 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 7 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 4088 | 9 | 2 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 6 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 5291 | 14 | 5 | 5 | 74.72222222222223 | 0.8211233211233212 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 2 | 8 | 2 | 0 | 8.0 | 2.0 | 0 | 4 | 2 | 1 | 5 | 0 | 1 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 8720 | 22 | 4 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 7 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 4 | SAFE | 4080 | 9 | 2 | 4 | 32.599999999999994 | 0.9055555555555554 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 5 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 8 | SAFE | 5469 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 7 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 16 | SAFE | 9124 | 22 | 5 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 7 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 4 | SAFE | 4047 | 10 | 5 | 5 | 42.4 | 0.9422222222222222 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 7 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 8 | SAFE | 5284 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 7 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 16 | SAFE | 8715 | 22 | 4 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 7 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 4317 | 10 | 4 | 1 | 42.4 | 0.9422222222222222 | 8 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 8 | 10 | 36 | 9 | 1 | 4.5 | 1.125 | 6 | 30 | 1 | 8 | 1 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 5956 | 14 | 4 | 1 | 74.72222222222223 | 0.8211233211233212 | 8 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 7 | 14 | 52 | 13 | 1 | 7.428571428571429 | 1.8571428571428572 | 5 | 74 | 2 | 7 | 1 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 9440 | 22 | 8 | 5 | 150.82389081506727 | 0.6529172762557025 | 12 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 12 | 36 | 56 | 21 | 5 | 4.666666666666667 | 1.75 | 7 | 90 | 5 | 12 | 5 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 4092 | 9 | 3 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 6 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 5272 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 7 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 8717 | 22 | 4 | 6 | 149.91479990597637 | 0.6489818177747895 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 6 | 0 | 0 | 0 | 0 | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: +208, 8t: +634, 16t: +684 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +1, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +2, 8t: -1, 16t: +3 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -5, 8t: -7, 16t: -7 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: -0.00283951, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +36, 8t: +52, 16t: +56 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +9, 8t: +13, 16t: +21 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +6, 8t: +7, 16t: +7 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE runtime_ms: 4t: -10, 8t: -6, 16t: -24 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE stale_paths: 4t: +1, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: +17, 8t: -19, 16t: -4 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 8t: -2, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 8t: -0.00941915, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED runtime_ms: 4t: +9, 8t: +159, 16t: +400 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED stale_paths: 4t: +0, 8t: +0, 16t: +1 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED duplicate freshness failures: 4t: -1, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED avg divergence: 4t: -0.0222222, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_IDLE runtime_ms: 4t: -24, 8t: -26, 16t: -9 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE checked_paths: 4t: +1, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE stale_paths: 4t: +3, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE avg divergence: 4t: +0.0144444, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: +246, 8t: +646, 16t: +716 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +1, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +2, 8t: -1, 16t: +4 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -4, 8t: -6, 16t: -2 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: +0.0144444, 8t: -0.00941915, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: +21, 8t: -38, 16t: -7 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +1, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +0, 8t: +0, 16t: -1 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0, 8t: +0, 16t: -0.00393546 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-count_up_down.i_3-aa4a8d5f

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 121958 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 5113 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 7607 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 3059 | 6 | 1 | 0 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 5 | 20 | 5 | 0 | 6.666666666666667 | 1.6666666666666667 | 3 | 24 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 6203 | 13 | 4 | 0 | 62.57936507936508 | 0.8022995522995523 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 6 | 12 | 48 | 12 | 0 | 8.0 | 2.0 | 6 | 62 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 6802 | 18 | 1 | 0 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 17 | 40 | 17 | 0 | 13.333333333333334 | 5.666666666666667 | 3 | 74 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 4 | SAFE | 122443 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 8 | SAFE | 5113 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 16 | SAFE | 7984 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 121843 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 5093 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 7606 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 4 | SAFE | 121913 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 8 | SAFE | 5150 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 16 | SAFE | 7589 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 4 | SAFE | 122359 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 8 | SAFE | 5124 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 16 | SAFE | 7603 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 3299 | 6 | 1 | 0 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 5 | 20 | 5 | 0 | 6.666666666666667 | 1.6666666666666667 | 3 | 27 | 1 | 3 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 4632 | 10 | 1 | 0 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 9 | 36 | 9 | 0 | 12.0 | 3.0 | 3 | 56 | 2 | 3 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 7234 | 18 | 1 | 0 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 17 | 40 | 17 | 0 | 13.333333333333334 | 5.666666666666667 | 3 | 81 | 6 | 3 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 121494 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 5128 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 7547 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: -118899, 8t: +1090, 16t: -805 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: -7, 8t: +2, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: -6, 8t: +2, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -5, 8t: -4, 16t: -4 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: -0.14, 8t: +0.0826747, 16t: -0.0551607 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +20, 8t: +48, 16t: +40 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +5, 8t: +12, 16t: +17 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +3, 8t: +6, 16t: +3 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE runtime_ms: 4t: +485, 8t: +0, 16t: +377 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: -115, 8t: -20, 16t: -1 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED runtime_ms: 4t: -45, 8t: +37, 16t: -18 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_IDLE runtime_ms: 4t: +401, 8t: +11, 16t: -4 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: -118659, 8t: -481, 16t: -373 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: -7, 8t: -1, 16t: -1 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: -6, 8t: -1, 16t: -1 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -5, 8t: -4, 16t: -4 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: -0.14, 8t: -0.0623056, 16t: -0.0551607 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: -464, 8t: +15, 16t: -60 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-gauss_sum.i_3-f2583875

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 3467 | 7 | 1 | 3 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 5187 | 11 | 1 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 8582 | 19 | 1 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 3563 | 7 | 1 | 0 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 6 | 24 | 6 | 0 | 6.0 | 1.5 | 3 | 30 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 4948 | 10 | 0 | 0 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 9 | 36 | 9 | 0 | 12.0 | 3.0 | 3 | 73 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 8378 | 18 | 0 | 0 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 17 | 40 | 17 | 0 | 13.333333333333334 | 5.666666666666667 | 3 | 67 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 4 | SAFE | 3423 | 7 | 1 | 3 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 8 | SAFE | 5157 | 11 | 1 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 16 | SAFE | 8566 | 19 | 1 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 3468 | 7 | 1 | 3 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 5186 | 11 | 1 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 8571 | 19 | 1 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 4 | SAFE | 3480 | 7 | 1 | 3 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 8 | SAFE | 5199 | 11 | 1 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 16 | SAFE | 8568 | 19 | 1 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 4 | SAFE | 3505 | 7 | 1 | 3 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 8 | SAFE | 5195 | 11 | 1 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 16 | SAFE | 8583 | 19 | 1 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 3567 | 7 | 1 | 0 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 6 | 24 | 6 | 0 | 6.0 | 1.5 | 3 | 23 | 1 | 4 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 4969 | 10 | 0 | 0 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 9 | 36 | 9 | 0 | 12.0 | 3.0 | 3 | 62 | 2 | 3 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 8585 | 19 | 1 | 0 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 18 | 44 | 18 | 0 | 11.0 | 4.5 | 4 | 78 | 4 | 4 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 3432 | 7 | 1 | 3 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 5170 | 11 | 1 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 8575 | 19 | 1 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: +96, 8t: -239, 16t: -204 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +0, 8t: -1, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +0, 8t: -1, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -3, 8t: -4, 16t: -4 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0, 8t: -0.0623056, 16t: -0.0551607 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +24, 8t: +36, 16t: +40 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +6, 8t: +9, 16t: +17 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +3, 8t: +3, 16t: +3 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE runtime_ms: 4t: -44, 8t: -30, 16t: -16 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: +1, 8t: -1, 16t: -11 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED runtime_ms: 4t: +13, 8t: +12, 16t: -14 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_IDLE runtime_ms: 4t: +38, 8t: +8, 16t: +1 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: +100, 8t: -218, 16t: +3 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +0, 8t: -1, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +0, 8t: -1, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -3, 8t: -4, 16t: -4 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: +0, 8t: -0.0623056, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: -35, 8t: -17, 16t: -7 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_1.i_2-db2cf3f1

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 39166 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 52814 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 56349 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 39150 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 | 25 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 40827 | 9 | 0 | 0 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 | 67 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 56279 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 | 82 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 4 | SAFE | 39232 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 8 | SAFE | 52836 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 16 | SAFE | 56541 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 51099 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 52366 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 44256 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 4 | SAFE | 39199 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 8 | SAFE | 52780 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 16 | SAFE | 56283 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 4 | SAFE | 39145 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 8 | SAFE | 41625 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 16 | SAFE | 56239 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 51184 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 | 28 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 40837 | 9 | 0 | 0 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 | 62 | 3 | 2 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 44319 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 | 86 | 5 | 2 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 39141 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 52842 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 45267 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: -16, 8t: -11987, 16t: -70 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +16, 8t: +32, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +4, 8t: +8, 16t: +16 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE runtime_ms: 4t: +66, 8t: +22, 16t: +192 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: +11933, 8t: -448, 16t: -12093 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED runtime_ms: 4t: +33, 8t: -34, 16t: -66 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_IDLE runtime_ms: 4t: -21, 8t: -11189, 16t: -110 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: +12018, 8t: -11977, 16t: -12030 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: -25, 8t: +28, 16t: -11082 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_2.i_2-b9aa1d3f

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 40350 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 53190 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 56490 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 51283 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 | 25 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 53083 | 9 | 0 | 0 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 | 74 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 57261 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 | 77 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 4 | SAFE | 51270 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 8 | SAFE | 53247 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 16 | SAFE | 68521 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 51290 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 47263 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 56510 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 4 | SAFE | 39519 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 8 | SAFE | 53069 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 16 | SAFE | 56540 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 4 | SAFE | 51267 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 8 | SAFE | 53071 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 16 | SAFE | 47806 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 51267 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 | 24 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 53097 | 9 | 0 | 0 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 | 69 | 4 | 2 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 56604 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 | 95 | 6 | 2 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 51291 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 53042 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 57064 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: +10933, 8t: -107, 16t: +771 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +16, 8t: +32, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +4, 8t: +8, 16t: +16 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE runtime_ms: 4t: +10920, 8t: +57, 16t: +12031 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: +10940, 8t: -5927, 16t: +20 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED runtime_ms: 4t: -831, 8t: -121, 16t: +50 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_IDLE runtime_ms: 4t: +10917, 8t: -119, 16t: -8684 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: +10917, 8t: -93, 16t: +114 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: +10941, 8t: -148, 16t: +574 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_4.i_2-d2b55c6a

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 39577 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 29016 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 32520 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 27245 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 | 23 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 29019 | 9 | 0 | 0 | 16.46071428571429 | 0.4572420634920636 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 | 71 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 32623 | 17 | 0 | 0 | 41.47239288489288 | 0.30494406533009466 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 | 80 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 4 | SAFE | 27269 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 8 | SAFE | 41376 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 16 | SAFE | 32610 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 27222 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 28983 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 32575 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 4 | SAFE | 27210 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 8 | SAFE | 28954 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 16 | SAFE | 32646 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 4 | SAFE | 27289 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 8 | SAFE | 29041 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 16 | SAFE | 32549 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 27254 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 | 26 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 29014 | 9 | 0 | 0 | 16.46071428571429 | 0.4572420634920636 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 | 74 | 6 | 2 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 32591 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 | 83 | 6 | 2 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 27204 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 29052 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 32568 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: -12332, 8t: +3, 16t: +103 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0, 8t: +1.11022e-16, 16t: +5.55112e-17 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +16, 8t: +32, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +4, 8t: +8, 16t: +16 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE runtime_ms: 4t: -12308, 8t: +12360, 16t: +90 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: -12355, 8t: -33, 16t: +55 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED runtime_ms: 4t: -12367, 8t: -62, 16t: +126 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_IDLE runtime_ms: 4t: -12288, 8t: +25, 16t: +29 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: -12323, 8t: -2, 16t: +71 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: +0, 8t: +1.11022e-16, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: -12373, 8t: +36, 16t: +48 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_6.i_2-f35d9452

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 39895 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 28992 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 32319 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 27269 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 | 22 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 29014 | 9 | 0 | 0 | 16.460714285714282 | 0.45724206349206337 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 | 64 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 32609 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 | 71 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 4 | TIMEOUT | 1205052 | 0 | 0 | 0 | 0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 8 | TIMEOUT | 1205039 | 0 | 0 | 0 | 0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 16 | SAFE | 32411 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 39745 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 29023 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 32351 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 4 | SAFE | 27213 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 8 | SAFE | 28984 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 16 | SAFE | 32483 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 4 | SAFE | 27262 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 8 | SAFE | 29075 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 16 | SAFE | 32383 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 27265 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 | 26 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 32422 | 9 | 0 | 0 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 | 68 | 3 | 2 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 32502 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 | 92 | 9 | 2 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 27257 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 29062 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 32413 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: -12626, 8t: +22, 16t: +290 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0, 8t: -1.11022e-16, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +16, 8t: +32, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +4, 8t: +8, 16t: +16 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE runtime_ms: 4t: +1.16516e+06, 8t: +1.17605e+06, 16t: +92 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE checked_paths: 4t: -5, 8t: -9, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE duplicate freshness failures: 4t: -2, 8t: -2, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE avg divergence: 4t: -0.641667, 8t: -0.457242, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: -150, 8t: +31, 16t: +32 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED runtime_ms: 4t: -12682, 8t: -8, 16t: +164 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_IDLE runtime_ms: 4t: -12633, 8t: +83, 16t: +64 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: -12630, 8t: +3430, 16t: +183 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: -12638, 8t: +70, 16t: +94 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

## Interpretation Notes

- Negative LCPS - PAPER runtime, checked_paths, stale_paths, and duplicate-failure deltas are improvements for that metric.
- LCPS cache activation requires positive LCPS search invocations and positive checked/stale prefix queries. Hits show that the query keys matched cached run prefixes.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log` or `*-<mode>-stale-<on|off>-threads-*.log`.
