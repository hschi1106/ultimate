# Checked Path Divergence Results

## Purpose

This experiment compares PAPER, LCPS, BATCH_LCPS, and optional BFS/DFS path selection under parallel TraceAbstraction.

Each unordered checked-path pair contributes normalized prefix-LCA divergence `1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.

## Summary

### Correctness

- Modes present: PAPER, BATCH_LCPS, ADAPTIVE_STALE, ADAPTIVE_FIRST_FILL, ADAPTIVE_FIRST_FILL_OR_STALE, ADAPTIVE_THREADS_GE_4_FIRST_FILL, ADAPTIVE_ALWAYS, ADAPTIVE_NEVER.
- Result mismatch groups across modes: 0.
- ERROR rows: 0; TIMEOUT rows: 0.
- BFS/DFS were not part of this run.

### Activation

- LCPS-priority rows with search invocations > 0: 0 / 0.
- LCPS-priority rows with checked prefix queries > 0: 0 / 0.
- LCPS-priority rows with stale prefix queries > 0: 0 / 0.
- LCPS-priority rows with checked prefix hits > 0: 0 / 0.
- LCPS-priority rows with stale prefix hits > 0: 0 / 0.
- Total LCPS effective priority decisions: 0.
- Total LCPS_FULL cache-suffix invocations/fallbacks: 0 / 0.
- BatchLcpsInvocations: 442.
- BatchLcpsCandidatesGenerated/Selected: 5648 / 1801.
- BatchLcpsCandidateGenerationFailures: 16.
- BatchLcpsEffectiveBatchDecisions: 304.

### Performance

- BATCH_LCPS - PAPER runtime_ms: wins/losses/ties 11/19/0, mean -2131.50, median 58.50.
- ADAPTIVE_STALE - PAPER runtime_ms: wins/losses/ties 16/13/1, mean 674.07, median -1.00.
- ADAPTIVE_FIRST_FILL - PAPER runtime_ms: wins/losses/ties 8/19/3, mean -3964.87, median 61.50.
- ADAPTIVE_FIRST_FILL_OR_STALE - PAPER runtime_ms: wins/losses/ties 10/20/0, mean -3715.80, median 60.50.
- ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER runtime_ms: wins/losses/ties 12/18/0, mean -4017.60, median 21.00.
- ADAPTIVE_ALWAYS - PAPER runtime_ms: wins/losses/ties 8/22/0, mean -2881.83, median 71.00.
- ADAPTIVE_NEVER - PAPER runtime_ms: wins/losses/ties 14/16/0, mean 497.67, median 4.00.

### Work

- BATCH_LCPS - PAPER checked_paths: wins/losses/ties 4/9/17, mean -0.07, median 0.00.
- ADAPTIVE_STALE - PAPER checked_paths: wins/losses/ties 5/1/24, mean -0.03, median 0.00.
- ADAPTIVE_FIRST_FILL - PAPER checked_paths: wins/losses/ties 4/9/17, mean 0.50, median 0.00.
- ADAPTIVE_FIRST_FILL_OR_STALE - PAPER checked_paths: wins/losses/ties 3/11/16, mean 0.37, median 0.00.
- ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER checked_paths: wins/losses/ties 4/10/16, mean 0.40, median 0.00.
- ADAPTIVE_ALWAYS - PAPER checked_paths: wins/losses/ties 3/9/18, mean 0.13, median 0.00.
- ADAPTIVE_NEVER - PAPER checked_paths: wins/losses/ties 2/3/25, mean 0.13, median 0.00.
- BATCH_LCPS - PAPER stale_paths: wins/losses/ties 7/9/14, mean 0.00, median 0.00.
- ADAPTIVE_STALE - PAPER stale_paths: wins/losses/ties 5/1/24, mean -0.03, median 0.00.
- ADAPTIVE_FIRST_FILL - PAPER stale_paths: wins/losses/ties 8/7/15, mean 0.40, median 0.00.
- ADAPTIVE_FIRST_FILL_OR_STALE - PAPER stale_paths: wins/losses/ties 6/10/14, mean 0.30, median 0.00.
- ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER stale_paths: wins/losses/ties 6/9/15, mean 0.33, median 0.00.
- ADAPTIVE_ALWAYS - PAPER stale_paths: wins/losses/ties 6/8/16, mean 0.03, median 0.00.
- ADAPTIVE_NEVER - PAPER stale_paths: wins/losses/ties 3/4/23, mean 0.10, median 0.00.
- BATCH_LCPS - PAPER duplicate freshness failures: wins/losses/ties 30/0/0, mean -4.07, median -3.50.
- ADAPTIVE_STALE - PAPER duplicate freshness failures: wins/losses/ties 6/1/23, mean -0.33, median 0.00.
- ADAPTIVE_FIRST_FILL - PAPER duplicate freshness failures: wins/losses/ties 23/2/5, mean -1.17, median -1.00.
- ADAPTIVE_FIRST_FILL_OR_STALE - PAPER duplicate freshness failures: wins/losses/ties 25/3/2, mean -1.27, median -1.00.
- ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER duplicate freshness failures: wins/losses/ties 25/3/2, mean -1.40, median -1.00.
- ADAPTIVE_ALWAYS - PAPER duplicate freshness failures: wins/losses/ties 30/0/0, mean -3.87, median -3.00.
- ADAPTIVE_NEVER - PAPER duplicate freshness failures: wins/losses/ties 3/2/25, mean 0.00, median 0.00.
- BATCH_LCPS - PAPER search_failed: wins/losses/ties 0/3/27, mean 0.13, median 0.00.
- ADAPTIVE_STALE - PAPER search_failed: wins/losses/ties 0/0/30, mean 0.00, median 0.00.
- ADAPTIVE_FIRST_FILL - PAPER search_failed: wins/losses/ties 0/3/27, mean 0.23, median 0.00.
- ADAPTIVE_FIRST_FILL_OR_STALE - PAPER search_failed: wins/losses/ties 0/3/27, mean 0.23, median 0.00.
- ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER search_failed: wins/losses/ties 0/3/27, mean 0.23, median 0.00.
- ADAPTIVE_ALWAYS - PAPER search_failed: wins/losses/ties 0/3/27, mean 0.40, median 0.00.
- ADAPTIVE_NEVER - PAPER search_failed: wins/losses/ties 0/0/30, mean 0.00, median 0.00.

### Batch Quality

- Average candidate pool size across BATCH_LCPS rows: 13.06.
- Average selected batch size across BATCH_LCPS rows: 4.35.
- Total effective batch decisions: 304.
- BATCH_LCPS - PAPER checked_paths: wins/losses/ties 4/9/17, mean -0.07, median 0.00.
- BATCH_LCPS - PAPER stale_paths: wins/losses/ties 7/9/14, mean 0.00, median 0.00.

### Adaptive Trigger Comparison

- ADAPTIVE_STALE: adaptive invocations/fallbacks 11/132, triggers dup/stale/failed/idle/first/first-or-stale/threads-ge4-first 0/11/0/0/0/0/0, candidates 176, effective batch decisions 10, candidates/invocation 16.00, generation/selection time 77ms/26ms, vs PAPER runtime [wins/losses/ties 16/13/1, mean 674.07, median -1.00], vs BATCH_LCPS runtime [wins/losses/ties 20/10/0, mean 2805.57, median -91.00], vs PAPER checked [wins/losses/ties 5/1/24, mean -0.03, median 0.00], vs PAPER stale [wins/losses/ties 5/1/24, mean -0.03, median 0.00].
- ADAPTIVE_FIRST_FILL: adaptive invocations/fallbacks 46/113, triggers dup/stale/failed/idle/first/first-or-stale/threads-ge4-first 0/0/0/0/46/0/0, candidates 940, effective batch decisions 32, candidates/invocation 20.43, generation/selection time 1422ms/99ms, vs PAPER runtime [wins/losses/ties 8/19/3, mean -3964.87, median 61.50], vs BATCH_LCPS runtime [wins/losses/ties 17/13/0, mean -1833.37, median -2.00], vs PAPER checked [wins/losses/ties 4/9/17, mean 0.50, median 0.00], vs PAPER stale [wins/losses/ties 8/7/15, mean 0.40, median 0.00].
- ADAPTIVE_FIRST_FILL_OR_STALE: adaptive invocations/fallbacks 48/107, triggers dup/stale/failed/idle/first/first-or-stale/threads-ge4-first 0/0/0/0/0/48/0, candidates 948, effective batch decisions 33, candidates/invocation 19.75, generation/selection time 1454ms/99ms, vs PAPER runtime [wins/losses/ties 10/20/0, mean -3715.80, median 60.50], vs BATCH_LCPS runtime [wins/losses/ties 15/15/0, mean -1584.30, median -2.00], vs PAPER checked [wins/losses/ties 3/11/16, mean 0.37, median 0.00], vs PAPER stale [wins/losses/ties 6/10/14, mean 0.30, median 0.00].
- ADAPTIVE_THREADS_GE_4_FIRST_FILL: adaptive invocations/fallbacks 49/104, triggers dup/stale/failed/idle/first/first-or-stale/threads-ge4-first 0/0/0/0/0/0/49, candidates 964, effective batch decisions 34, candidates/invocation 19.67, generation/selection time 1497ms/102ms, vs PAPER runtime [wins/losses/ties 12/18/0, mean -4017.60, median 21.00], vs BATCH_LCPS runtime [wins/losses/ties 14/16/0, mean -1886.10, median 2.00], vs PAPER checked [wins/losses/ties 4/10/16, mean 0.40, median 0.00], vs PAPER stale [wins/losses/ties 6/9/15, mean 0.33, median 0.00].
- ADAPTIVE_ALWAYS: adaptive invocations/fallbacks 142/6, triggers dup/stale/failed/idle/first/first-or-stale/threads-ge4-first 0/0/0/0/0/0/0, candidates 1312, effective batch decisions 94, candidates/invocation 9.24, generation/selection time 1634ms/101ms, vs PAPER runtime [wins/losses/ties 8/22/0, mean -2881.83, median 71.00], vs BATCH_LCPS runtime [wins/losses/ties 12/18/0, mean -750.33, median 16.50], vs PAPER checked [wins/losses/ties 3/9/18, mean 0.13, median 0.00], vs PAPER stale [wins/losses/ties 6/8/16, mean 0.03, median 0.00].
- ADAPTIVE_NEVER: adaptive invocations/fallbacks 0/141, triggers dup/stale/failed/idle/first/first-or-stale/threads-ge4-first 0/0/0/0/0/0/0, candidates 0, effective batch decisions 0, candidates/invocation 0.00, generation/selection time 0ms/0ms, vs PAPER runtime [wins/losses/ties 14/16/0, mean 497.67, median 4.00], vs BATCH_LCPS runtime [wins/losses/ties 21/9/0, mean 2629.17, median -88.00], vs PAPER checked [wins/losses/ties 2/3/25, mean 0.13, median 0.00], vs PAPER stale [wins/losses/ties 3/4/23, mean 0.10, median 0.00].

### Divergence

- BATCH_LCPS - PAPER avg divergence: wins/losses/ties 7/8/15, mean 0.00, median 0.00.
- ADAPTIVE_STALE - PAPER avg divergence: wins/losses/ties 5/2/23, mean -0.00, median 0.00.
- ADAPTIVE_FIRST_FILL - PAPER avg divergence: wins/losses/ties 6/8/16, mean -0.00, median 0.00.
- ADAPTIVE_FIRST_FILL_OR_STALE - PAPER avg divergence: wins/losses/ties 7/10/13, mean 0.01, median 0.00.
- ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER avg divergence: wins/losses/ties 7/8/15, mean 0.00, median 0.00.
- ADAPTIVE_ALWAYS - PAPER avg divergence: wins/losses/ties 9/8/13, mean -0.01, median 0.00.
- ADAPTIVE_NEVER - PAPER avg divergence: wins/losses/ties 4/2/24, mean 0.00, median 0.00.

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

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_triggered_by_first_fill | adaptive_triggered_by_first_fill_or_stale | adaptive_triggered_by_threads_ge_4_first_fill | first_dispatch_in_current_abstraction | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 4250 | 14 | 6 | 4 | 88.1765873015873 | 0.9689734868306297 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 5048 | 19 | 7 | 3 | 149.1035298035298 | 0.8719504666873088 | 12 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 7806 | 36 | 20 | 5 | 611.809417298388 | 0.9711260592037905 | 23 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 3809 | 15 | 7 | 0 | 102.07698412698413 | 0.9721617535903251 | 12 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 10 | 14 | 56 | 14 | 0 | 5.6 | 1.4 | 7 | 26 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 4609 | 17 | 9 | 0 | 117.55615218115219 | 0.8643834719202367 | 12 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 16 | 64 | 16 | 0 | 16.0 | 4.0 | 4 | 32 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 8173 | 33 | 16 | 0 | 507.26358086358107 | 0.9607264789082974 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 5 | 34 | 80 | 34 | 0 | 16.0 | 6.8 | 5 | 37 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 3596 | 13 | 5 | 1 | 74.91094402673349 | 0.960396718291455 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 5 | 20 | 5 | 0 | 10.0 | 2.5 | 2 | 8 | 1 | 2 | 5 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 5907 | 27 | 15 | 5 | 335.61736348446874 | 0.9561748247420762 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 11 | 44 | 11 | 0 | 14.666666666666666 | 3.6666666666666665 | 3 | 14 | 3 | 3 | 9 | 0 | 3 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 8204 | 33 | 16 | 3 | 481.87464202464224 | 0.9126413674709133 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 14 | 32 | 14 | 0 | 32.0 | 14.0 | 1 | 13 | 7 | 1 | 5 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 4 | SAFE | 3598 | 13 | 5 | 0 | 74.70856307435255 | 0.9578020906968276 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 8 | 32 | 8 | 0 | 10.666666666666666 | 2.6666666666666665 | 3 | 19 | 1 | 3 | 4 | 0 | 0 | 0 | 0 | 3 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 8 | SAFE | 5220 | 22 | 13 | 1 | 216.3765740107845 | 0.9366951255878118 | 16 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 18 | 72 | 18 | 0 | 18.0 | 4.5 | 4 | 35 | 2 | 4 | 3 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 16 | SAFE | 9464 | 43 | 26 | 2 | 842.8126662553134 | 0.9333473601941455 | 29 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 5 | 37 | 104 | 37 | 0 | 20.8 | 7.4 | 5 | 49 | 7 | 5 | 5 | 0 | 0 | 0 | 0 | 5 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 4 | SAFE | 3586 | 14 | 6 | 1 | 87.70856307435255 | 0.9638303634544236 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 8 | 32 | 8 | 0 | 10.666666666666666 | 2.6666666666666665 | 3 | 19 | 1 | 3 | 5 | 0 | 0 | 0 | 0 | 0 | 3 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 8 | SAFE | 4842 | 21 | 11 | 2 | 190.88948551448553 | 0.9089975500689788 | 14 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 16 | 64 | 16 | 0 | 21.333333333333332 | 5.333333333333333 | 3 | 29 | 2 | 3 | 4 | 0 | 0 | 0 | 0 | 0 | 3 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 16 | SAFE | 7971 | 35 | 17 | 1 | 541.7635808635812 | 0.910527026661481 | 21 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 32 | 72 | 32 | 0 | 24.0 | 10.666666666666666 | 3 | 32 | 9 | 3 | 3 | 0 | 0 | 0 | 0 | 0 | 3 | 0 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 4 | SAFE | 3773 | 13 | 5 | 1 | 75.07698412698413 | 0.9625254375254375 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 6 | 24 | 6 | 0 | 12.0 | 3.0 | 2 | 21 | 1 | 2 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 8 | SAFE | 4843 | 20 | 10 | 0 | 176.56305491893727 | 0.9292792364154593 | 13 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 17 | 68 | 17 | 0 | 17.0 | 4.25 | 3 | 36 | 2 | 4 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 16 | SAFE | 9270 | 42 | 26 | 1 | 800.3576825951826 | 0.929567575604161 | 29 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 5 | 37 | 104 | 37 | 0 | 20.8 | 7.4 | 5 | 53 | 5 | 5 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 5 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 4289 | 15 | 6 | 0 | 99.48051948051948 | 0.9474335188620903 | 12 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 8 | 14 | 56 | 14 | 0 | 7.0 | 1.75 | 7 | 31 | 1 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 4833 | 20 | 9 | 0 | 176.97481962481962 | 0.931446419077998 | 13 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 7 | 19 | 76 | 19 | 0 | 10.857142857142858 | 2.7142857142857144 | 6 | 39 | 2 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 7769 | 36 | 17 | 0 | 545.7866272616276 | 0.8663279797803612 | 21 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 5 | 35 | 84 | 35 | 0 | 16.8 | 7.0 | 5 | 39 | 10 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 4013 | 13 | 6 | 5 | 87.50992063492063 | 1.1219220594220594 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 5664 | 23 | 12 | 5 | 237.36015373515372 | 0.9381824258306471 | 16 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 7835 | 39 | 21 | 5 | 660.490525895673 | 0.891350237376077 | 24 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: -441, 8t: -439, 16t: +367 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +1, 8t: -2, 16t: -3 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +1, 8t: +2, 16t: -4 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -4, 8t: -3, 16t: -5 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0.00318827, 8t: -0.00756699, 16t: -0.0103996 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +56, 8t: +64, 16t: +80 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +14, 8t: +16, 16t: +34 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +7, 8t: +4, 16t: +5 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: -654, 8t: +859, 16t: +398 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: -1, 8t: +8, 16t: -3 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: -1, 8t: +8, 16t: -4 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: -3, 8t: +2, 16t: -2 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: -0.00857677, 8t: +0.0842244, 16t: -0.0584847 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL runtime_ms: 4t: -652, 8t: +172, 16t: +1658 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL checked_paths: 4t: -1, 8t: +3, 16t: +7 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL stale_paths: 4t: -1, 8t: +6, 16t: +6 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL duplicate freshness failures: 4t: -4, 8t: -2, 16t: -3 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL avg divergence: 4t: -0.0111714, 8t: +0.0647447, 16t: -0.0377787 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE runtime_ms: 4t: -664, 8t: -206, 16t: +165 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE checked_paths: 4t: +0, 8t: +2, 16t: -1 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE stale_paths: 4t: +0, 8t: +4, 16t: -3 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE duplicate freshness failures: 4t: -3, 8t: -1, 16t: -4 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE avg divergence: 4t: -0.00514312, 8t: +0.0370471, 16t: -0.060599 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL runtime_ms: 4t: -477, 8t: -205, 16t: +1464 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL checked_paths: 4t: -1, 8t: +1, 16t: +6 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL stale_paths: 4t: -1, 8t: +3, 16t: +6 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL duplicate freshness failures: 4t: -3, 8t: -3, 16t: -4 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL avg divergence: 4t: -0.00644805, 8t: +0.0573288, 16t: -0.0415585 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: +39, 8t: -215, 16t: -37 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +1, 8t: +1, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +0, 8t: +2, 16t: -3 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -4, 8t: -3, 16t: -5 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: -0.02154, 8t: +0.059496, 16t: -0.104798 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: -237, 8t: +616, 16t: +29 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: -1, 8t: +4, 16t: +3 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +0, 8t: +5, 16t: +1 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +1, 8t: +2, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0.152949, 8t: +0.066232, 16t: -0.0797758 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_triggered_by_first_fill | adaptive_triggered_by_first_fill_or_stale | adaptive_triggered_by_threads_ge_4_first_fill | first_dispatch_in_current_abstraction | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 4053 | 14 | 4 | 8 | 88.68333333333334 | 0.9745421245421246 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 7073 | 25 | 13 | 10 | 284.6384920634921 | 0.9487949735449737 | 18 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 8515 | 33 | 14 | 10 | 503.3478354978356 | 0.9533102945034765 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 4969 | 16 | 8 | 0 | 123.59285714285716 | 1.0299404761904762 | 14 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 11 | 16 | 64 | 16 | 0 | 5.818181818181818 | 1.4545454545454546 | 8 | 27 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 7038 | 26 | 12 | 0 | 307.0805555555555 | 0.9448632478632477 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 12 | 25 | 100 | 25 | 0 | 8.333333333333334 | 2.0833333333333335 | 11 | 48 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 9558 | 34 | 16 | 0 | 540.9416666666666 | 0.964245395127748 | 22 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 11 | 34 | 108 | 34 | 0 | 9.818181818181818 | 3.090909090909091 | 11 | 90 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 3855 | 12 | 4 | 7 | 63.83333333333333 | 0.9671717171717171 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 2 | 8 | 2 | 0 | 8.0 | 2.0 | 1 | 2 | 1 | 1 | 7 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 6207 | 23 | 12 | 8 | 253.9418831168831 | 1.0037228581694984 | 17 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 2 | 14 | 3 | 2 | 9 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 8514 | 32 | 12 | 8 | 440.9573593073592 | 0.8890269340874177 | 17 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 9 | 32 | 9 | 0 | 32.0 | 9.0 | 1 | 17 | 7 | 1 | 8 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 4 | SAFE | 5529 | 18 | 10 | 8 | 149.59285714285716 | 0.9777310924369749 | 15 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 6 | 24 | 6 | 0 | 12.0 | 3.0 | 2 | 16 | 2 | 2 | 11 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 8 | SAFE | 6612 | 27 | 12 | 10 | 350.99401709401707 | 0.9999829546838093 | 21 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 17 | 68 | 17 | 0 | 17.0 | 4.25 | 4 | 45 | 3 | 4 | 10 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 16 | SAFE | 9764 | 35 | 13 | 10 | 542.7204545454543 | 0.9121352177234526 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 24 | 64 | 24 | 0 | 32.0 | 12.0 | 2 | 55 | 5 | 2 | 10 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 4 | SAFE | 5082 | 16 | 9 | 7 | 125.59285714285716 | 1.046607142857143 | 14 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 7 | 28 | 7 | 0 | 14.0 | 3.5 | 2 | 21 | 1 | 2 | 9 | 0 | 0 | 0 | 0 | 0 | 2 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 8 | SAFE | 6171 | 26 | 10 | 5 | 306.8884615384615 | 0.9442721893491124 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 5 | 20 | 80 | 20 | 0 | 16.0 | 4.0 | 5 | 44 | 3 | 5 | 5 | 0 | 0 | 0 | 0 | 0 | 5 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 16 | SAFE | 9577 | 34 | 13 | 7 | 541.9457070707069 | 0.9660351284682833 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 27 | 80 | 27 | 0 | 20.0 | 6.75 | 4 | 64 | 4 | 4 | 7 | 0 | 0 | 0 | 0 | 0 | 4 | 0 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 4 | SAFE | 5510 | 18 | 7 | 9 | 149.7 | 0.9784313725490196 | 15 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 6 | 24 | 6 | 0 | 12.0 | 3.0 | 2 | 23 | 1 | 2 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 8 | SAFE | 7053 | 27 | 12 | 7 | 332.66388888888883 | 0.9477603672048115 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 18 | 72 | 18 | 0 | 18.0 | 4.5 | 4 | 40 | 3 | 4 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 16 | SAFE | 9731 | 34 | 14 | 6 | 538.6638888888887 | 0.9601851851851848 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 28 | 80 | 28 | 0 | 20.0 | 7.0 | 4 | 58 | 5 | 4 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 5726 | 18 | 11 | 0 | 149.2595238095238 | 0.9755524431995021 | 15 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 12 | 17 | 68 | 17 | 0 | 5.666666666666667 | 1.4166666666666667 | 8 | 27 | 1 | 12 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 6412 | 25 | 9 | 0 | 284.725 | 0.9490833333333334 | 18 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 11 | 24 | 96 | 24 | 0 | 8.727272727272727 | 2.1818181818181817 | 10 | 50 | 3 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 9587 | 35 | 14 | 0 | 541.3457070707069 | 0.9098247177658939 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 34 | 104 | 34 | 0 | 11.555555555555555 | 3.7777777777777777 | 9 | 78 | 5 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 4072 | 14 | 4 | 7 | 88.35 | 0.9708791208791209 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 5946 | 21 | 10 | 9 | 194.9718253968254 | 0.9284372637944067 | 14 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 8946 | 35 | 15 | 9 | 537.3128704628706 | 0.9030468411140682 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: +916, 8t: -35, 16t: +1043 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +2, 8t: +1, 16t: +1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +4, 8t: -1, 16t: +2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -8, 8t: -10, 16t: -10 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0.0553984, 8t: -0.00393173, 16t: +0.0109351 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +64, 8t: +100, 16t: +108 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +16, 8t: +25, 16t: +34 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +8, 8t: +11, 16t: +11 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: -198, 8t: -866, 16t: -1 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: -2, 8t: -2, 16t: -1 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 8t: -1, 16t: -2 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: -1, 8t: -2, 16t: -2 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: -0.00737041, 8t: +0.0549279, 16t: -0.0642834 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL runtime_ms: 4t: +1476, 8t: -461, 16t: +1249 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL checked_paths: 4t: +4, 8t: +2, 16t: +2 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL stale_paths: 4t: +6, 8t: -1, 16t: -1 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL avg divergence: 4t: +0.00318897, 8t: +0.051188, 16t: -0.0411751 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE runtime_ms: 4t: +1029, 8t: -902, 16t: +1062 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE checked_paths: 4t: +2, 8t: +1, 16t: +1 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE stale_paths: 4t: +5, 8t: -3, 16t: -1 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE duplicate freshness failures: 4t: -1, 8t: -5, 16t: -3 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE avg divergence: 4t: +0.072065, 8t: -0.00452278, 16t: +0.0127248 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL runtime_ms: 4t: +1457, 8t: -20, 16t: +1216 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL checked_paths: 4t: +4, 8t: +2, 16t: +1 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL stale_paths: 4t: +3, 8t: -1, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL duplicate freshness failures: 4t: +1, 8t: -3, 16t: -4 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL avg divergence: 4t: +0.00388925, 8t: -0.00103461, 16t: +0.00687489 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: +1673, 8t: -661, 16t: +1072 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +4, 8t: +0, 16t: +2 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +7, 8t: -4, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -8, 8t: -10, 16t: -10 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: +0.00101032, 8t: +0.00028836, 16t: -0.0434856 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: +19, 8t: -1127, 16t: +431 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: -4, 16t: +2 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +0, 8t: -3, 16t: +1 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: -1, 8t: -1, 16t: -1 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: -0.003663, 8t: -0.0203577, 16t: -0.0502635 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_triggered_by_first_fill | adaptive_triggered_by_first_fill_or_stale | adaptive_triggered_by_threads_ge_4_first_fill | first_dispatch_in_current_abstraction | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|---:|
| PAPER | n/a | 0 | true | true | 4 | UNKNOWN | 4243 | 11 | 5 | 6 | 52.4 | 0.9527272727272728 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 8 | UNKNOWN | 6143 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 16 | UNKNOWN | 4981 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | UNKNOWN | 4762 | 12 | 6 | 0 | 63.4 | 0.9606060606060606 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 11 | 44 | 11 | 0 | 4.888888888888889 | 1.2222222222222223 | 3 | 32 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | UNKNOWN | 6196 | 17 | 7 | 0 | 120.57936507936509 | 0.8866129785247433 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 10 | 16 | 64 | 16 | 0 | 6.4 | 1.6 | 8 | 60 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | UNKNOWN | 5873 | 17 | 0 | 0 | 55.82389081506727 | 0.4104697854049064 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 2 | 68 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | UNKNOWN | 4271 | 11 | 5 | 6 | 52.4 | 0.9527272727272728 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | UNKNOWN | 6130 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | UNKNOWN | 5191 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 4 | UNKNOWN | 4751 | 13 | 7 | 2 | 75.4 | 0.9666666666666668 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 21 | 3 | 1 | 9 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | false | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 8 | UNKNOWN | 6161 | 17 | 7 | 6 | 120.57936507936509 | 0.8866129785247433 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 49 | 2 | 1 | 9 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | false | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 16 | UNKNOWN | 6534 | 18 | 0 | 2 | 72.82389081506727 | 0.475973142582139 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 60 | 4 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | false | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 4 | UNKNOWN | 4765 | 14 | 8 | 3 | 88.4 | 0.9714285714285715 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 21 | 1 | 1 | 10 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | false | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 8 | UNKNOWN | 6129 | 17 | 7 | 7 | 120.57936507936509 | 0.8866129785247433 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 54 | 2 | 1 | 9 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | false | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 16 | UNKNOWN | 6542 | 19 | 1 | 3 | 90.82389081506727 | 0.5311338644155981 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 61 | 6 | 1 | 3 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | false | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 4 | UNKNOWN | 4764 | 12 | 6 | 1 | 63.4 | 0.9606060606060606 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 26 | 1 | 1 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | false | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 8 | UNKNOWN | 6157 | 17 | 7 | 6 | 119.68462823725983 | 0.8800340311563223 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 9 | 36 | 9 | 0 | 18.0 | 4.5 | 2 | 63 | 3 | 2 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | false | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 16 | UNKNOWN | 6739 | 19 | 1 | 3 | 90.82389081506727 | 0.5311338644155981 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 65 | 5 | 1 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | false | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | UNKNOWN | 4777 | 12 | 6 | 0 | 63.4 | 0.9606060606060606 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 11 | 44 | 11 | 0 | 4.888888888888889 | 1.2222222222222223 | 3 | 29 | 1 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | UNKNOWN | 6199 | 16 | 6 | 0 | 104.57936507936509 | 0.871494708994709 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 15 | 60 | 15 | 0 | 6.666666666666667 | 1.6666666666666667 | 7 | 67 | 3 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | UNKNOWN | 5866 | 17 | 0 | 0 | 55.82389081506727 | 0.4104697854049064 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 2 | 67 | 6 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | UNKNOWN | 4304 | 11 | 5 | 6 | 52.4 | 0.9527272727272728 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | UNKNOWN | 6145 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | UNKNOWN | 4970 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: +519, 8t: +53, 16t: +892 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +1, 8t: +2, 16t: +1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +1, 8t: +2, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -6, 8t: -8, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0.00787879, 8t: +0.0334762, 16t: +0.078604 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +44, 8t: +64, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +11, 8t: +16, 16t: +16 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +3, 8t: +8, 16t: +2 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: +28, 8t: -13, 16t: +210 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL runtime_ms: 4t: +508, 8t: +18, 16t: +1553 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL checked_paths: 4t: +2, 8t: +2, 16t: +2 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL stale_paths: 4t: +2, 8t: +2, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL duplicate freshness failures: 4t: -4, 8t: -2, 16t: +1 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL avg divergence: 4t: +0.0139394, 8t: +0.0334762, 16t: +0.144107 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE runtime_ms: 4t: +522, 8t: -14, 16t: +1561 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE checked_paths: 4t: +3, 8t: +2, 16t: +3 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE stale_paths: 4t: +3, 8t: +2, 16t: +1 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE duplicate freshness failures: 4t: -3, 8t: -1, 16t: +2 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE avg divergence: 4t: +0.0187013, 8t: +0.0334762, 16t: +0.199268 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL runtime_ms: 4t: +521, 8t: +14, 16t: +1758 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL checked_paths: 4t: +1, 8t: +2, 16t: +3 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL stale_paths: 4t: +1, 8t: +2, 16t: +1 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL duplicate freshness failures: 4t: -5, 8t: -2, 16t: +2 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL avg divergence: 4t: +0.00787879, 8t: +0.0268972, 16t: +0.199268 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: +534, 8t: +56, 16t: +885 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +1, 8t: +1, 16t: +1 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +1, 8t: +1, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -6, 8t: -8, 16t: -1 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: +0.00787879, 8t: +0.0183579, 16t: +0.078604 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: +61, 8t: +2, 16t: -11 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-diamond2.i_4-22ecac6d

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_triggered_by_first_fill | adaptive_triggered_by_first_fill_or_stale | adaptive_triggered_by_threads_ge_4_first_fill | first_dispatch_in_current_abstraction | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 4101 | 9 | 3 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 5322 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 8705 | 22 | 4 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 4288 | 10 | 4 | 0 | 42.4 | 0.9422222222222222 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 8 | 10 | 36 | 9 | 1 | 4.5 | 1.125 | 6 | 30 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 5946 | 14 | 4 | 0 | 75.57936507936509 | 0.8305424733996164 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 8 | 14 | 52 | 13 | 1 | 6.5 | 1.625 | 7 | 75 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 9413 | 22 | 5 | 0 | 150.82389081506727 | 0.6529172762557025 | 9 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 24 | 56 | 21 | 2 | 6.222222222222222 | 2.3333333333333335 | 7 | 95 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 4101 | 9 | 2 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 5300 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 8720 | 22 | 4 | 5 | 149.91479990597637 | 0.6489818177747895 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 2 | 8 | 2 | 0 | 8.0 | 2.0 | 0 | 9 | 4 | 1 | 5 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 4 | SAFE | 4285 | 10 | 4 | 5 | 42.4 | 0.9422222222222222 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 21 | 1 | 1 | 7 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 8 | SAFE | 5945 | 14 | 4 | 7 | 75.57936507936509 | 0.8305424733996164 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 50 | 3 | 1 | 7 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 16 | SAFE | 9395 | 22 | 6 | 9 | 150.82389081506727 | 0.6529172762557025 | 10 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 20 | 32 | 15 | 2 | 10.666666666666666 | 5.0 | 1 | 64 | 4 | 3 | 9 | 0 | 0 | 0 | 0 | 3 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 4 | SAFE | 4275 | 10 | 4 | 3 | 41.6 | 0.9244444444444445 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 5 | 20 | 5 | 0 | 10.0 | 2.5 | 2 | 31 | 1 | 2 | 5 | 0 | 0 | 0 | 0 | 0 | 2 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 8 | SAFE | 5948 | 14 | 4 | 5 | 74.73321123321124 | 0.8212440794858378 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 9 | 36 | 9 | 0 | 18.0 | 4.5 | 1 | 54 | 2 | 2 | 5 | 0 | 0 | 0 | 0 | 0 | 2 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 16 | SAFE | 9417 | 22 | 7 | 9 | 150.82389081506727 | 0.6529172762557025 | 10 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 20 | 32 | 15 | 2 | 10.666666666666666 | 5.0 | 1 | 68 | 4 | 3 | 9 | 0 | 0 | 0 | 0 | 0 | 3 | 0 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 4 | SAFE | 4290 | 10 | 4 | 4 | 41.62222222222222 | 0.9249382716049382 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 5 | 20 | 5 | 0 | 10.0 | 2.5 | 2 | 26 | 1 | 2 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 8 | SAFE | 5949 | 14 | 4 | 7 | 75.57936507936509 | 0.8305424733996164 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 56 | 2 | 1 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 16 | SAFE | 9426 | 22 | 7 | 9 | 150.82389081506727 | 0.6529172762557025 | 11 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 21 | 32 | 15 | 2 | 10.666666666666666 | 5.0 | 1 | 61 | 5 | 3 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 4308 | 10 | 4 | 1 | 41.62222222222222 | 0.9249382716049382 | 8 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 7 | 10 | 36 | 9 | 1 | 5.142857142857143 | 1.2857142857142858 | 6 | 29 | 2 | 7 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 5935 | 14 | 4 | 1 | 74.73321123321124 | 0.8212440794858378 | 8 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 7 | 14 | 52 | 13 | 1 | 7.428571428571429 | 1.8571428571428572 | 5 | 61 | 2 | 7 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 9403 | 22 | 7 | 4 | 149.91479990597637 | 0.6489818177747895 | 11 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 10 | 31 | 56 | 21 | 4 | 5.6 | 2.1 | 5 | 90 | 4 | 10 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 4053 | 9 | 2 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 5291 | 14 | 4 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 8544 | 22 | 5 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: +187, 8t: +624, 16t: +708 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +1, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +1, 8t: -1, 16t: +1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -5, 8t: -7, 16t: -7 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0.0144444, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +36, 8t: +52, 16t: +56 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +9, 8t: +13, 16t: +21 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +6, 8t: +7, 16t: +7 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: +0, 8t: -22, 16t: +15 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: -1, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 8t: +0, 16t: -2 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 8t: +0, 16t: -0.00393546 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL runtime_ms: 4t: +184, 8t: +623, 16t: +690 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL checked_paths: 4t: +1, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL stale_paths: 4t: +1, 8t: -1, 16t: +2 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL duplicate freshness failures: 4t: +0, 8t: +0, 16t: +2 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL avg divergence: 4t: +0.0144444, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE runtime_ms: 4t: +174, 8t: +626, 16t: +712 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE checked_paths: 4t: +1, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE stale_paths: 4t: +1, 8t: -1, 16t: +3 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE duplicate freshness failures: 4t: -2, 8t: -2, 16t: +2 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE avg divergence: 4t: -0.00333333, 8t: -0.00929839, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL runtime_ms: 4t: +189, 8t: +627, 16t: +721 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL checked_paths: 4t: +1, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL stale_paths: 4t: +1, 8t: -1, 16t: +3 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL duplicate freshness failures: 4t: -1, 8t: +0, 16t: +2 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL avg divergence: 4t: -0.00283951, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: +207, 8t: +613, 16t: +698 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +1, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +1, 8t: -1, 16t: +3 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -4, 8t: -6, 16t: -3 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: -0.00283951, 8t: -0.00929839, 16t: -0.00393546 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: -48, 8t: -31, 16t: -161 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: -1, 8t: -1, 16t: +1 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-count_up_down.i_3-aa4a8d5f

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_triggered_by_first_fill | adaptive_triggered_by_first_fill_or_stale | adaptive_triggered_by_threads_ge_4_first_fill | first_dispatch_in_current_abstraction | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 122359 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 4874 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 7549 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 3277 | 6 | 1 | 0 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 5 | 20 | 5 | 0 | 6.666666666666667 | 1.6666666666666667 | 3 | 28 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 5471 | 11 | 1 | 0 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 10 | 40 | 10 | 0 | 10.0 | 2.5 | 4 | 58 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 8762 | 20 | 3 | 0 | 109.82389081506727 | 0.5780204779740383 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 5 | 19 | 48 | 19 | 0 | 9.6 | 3.8 | 5 | 97 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 122227 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 5127 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 7978 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 4 | SAFE | 3073 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 21 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 8 | SAFE | 4653 | 10 | 1 | 2 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 50 | 2 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 16 | SAFE | 7966 | 19 | 2 | 3 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 67 | 4 | 1 | 3 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 4 | SAFE | 3038 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 21 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 8 | SAFE | 4847 | 10 | 1 | 2 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 57 | 3 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 16 | SAFE | 10523 | 22 | 5 | 6 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 71 | 8 | 1 | 6 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 4 | SAFE | 3033 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 22 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 8 | SAFE | 4957 | 11 | 2 | 3 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 62 | 2 | 1 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 16 | SAFE | 7023 | 18 | 1 | 2 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 64 | 6 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 3068 | 6 | 1 | 0 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 5 | 20 | 5 | 0 | 6.666666666666667 | 1.6666666666666667 | 3 | 22 | 1 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 4832 | 10 | 1 | 0 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 9 | 36 | 9 | 0 | 12.0 | 3.0 | 3 | 55 | 2 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 8780 | 20 | 3 | 0 | 109.82389081506727 | 0.5780204779740383 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 5 | 19 | 48 | 19 | 0 | 9.6 | 3.8 | 5 | 83 | 6 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 121900 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 5132 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 8010 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: -119082, 8t: +597, 16t: +1213 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: -7, 8t: +0, 16t: +1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: -6, 8t: -1, 16t: +1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -5, 8t: -4, 16t: -4 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: -0.14, 8t: +0, 16t: +0.0468866 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +20, 8t: +40, 16t: +48 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +5, 8t: +10, 16t: +19 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +3, 8t: +4, 16t: +5 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: -132, 8t: +253, 16t: +429 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL runtime_ms: 4t: -119286, 8t: -221, 16t: +417 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL checked_paths: 4t: -7, 8t: -1, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL stale_paths: 4t: -6, 8t: -1, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL duplicate freshness failures: 4t: -4, 8t: -2, 16t: -1 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL avg divergence: 4t: -0.14, 8t: -0.0623056, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE runtime_ms: 4t: -119321, 8t: -27, 16t: +2974 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE checked_paths: 4t: -7, 8t: -1, 16t: +3 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE stale_paths: 4t: -6, 8t: -1, 16t: +3 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE duplicate freshness failures: 4t: -4, 8t: -2, 16t: +2 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE avg divergence: 4t: -0.14, 8t: -0.0623056, 16t: +0.121783 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL runtime_ms: 4t: -119326, 8t: +83, 16t: -526 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL checked_paths: 4t: -7, 8t: +0, 16t: -1 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL stale_paths: 4t: -6, 8t: +0, 16t: -1 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL duplicate freshness failures: 4t: -4, 8t: -1, 16t: -2 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL avg divergence: 4t: -0.14, 8t: +0, 16t: -0.0551607 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: -119291, 8t: -42, 16t: +1231 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: -7, 8t: -1, 16t: +1 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: -6, 8t: -1, 16t: +1 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -5, 8t: -4, 16t: -4 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: -0.14, 8t: -0.0623056, 16t: +0.0468866 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: -459, 8t: +258, 16t: +461 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-gauss_sum.i_3-f2583875

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_triggered_by_first_fill | adaptive_triggered_by_first_fill_or_stale | adaptive_triggered_by_threads_ge_4_first_fill | first_dispatch_in_current_abstraction | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 3477 | 7 | 1 | 3 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 5182 | 11 | 1 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 8570 | 19 | 1 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 3541 | 7 | 1 | 0 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 6 | 24 | 6 | 0 | 6.0 | 1.5 | 3 | 25 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 4946 | 10 | 0 | 0 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 9 | 36 | 9 | 0 | 12.0 | 3.0 | 3 | 56 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 8650 | 19 | 0 | 0 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 18 | 44 | 18 | 0 | 11.0 | 4.5 | 4 | 74 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 3440 | 7 | 1 | 3 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 5171 | 11 | 1 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 8569 | 19 | 1 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 4 | SAFE | 3536 | 7 | 1 | 2 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 23 | 1 | 1 | 3 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 8 | SAFE | 4945 | 10 | 0 | 2 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 59 | 2 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 16 | SAFE | 8652 | 19 | 0 | 3 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 64 | 5 | 1 | 3 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 4 | SAFE | 3498 | 7 | 1 | 2 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 25 | 1 | 1 | 3 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 8 | SAFE | 5414 | 12 | 2 | 4 | 50.57936507936508 | 0.7663540163540165 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 53 | 2 | 1 | 4 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 16 | SAFE | 8863 | 20 | 2 | 4 | 109.82389081506727 | 0.5780204779740383 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 65 | 4 | 1 | 4 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 4 | SAFE | 3483 | 7 | 1 | 2 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 22 | 1 | 1 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 8 | SAFE | 5427 | 12 | 2 | 4 | 50.57936507936508 | 0.7663540163540165 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 61 | 2 | 1 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 16 | SAFE | 8352 | 18 | 0 | 2 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 60 | 5 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 3507 | 7 | 1 | 0 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 6 | 24 | 6 | 0 | 6.0 | 1.5 | 3 | 25 | 1 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 5224 | 11 | 2 | 0 | 39.57936507936508 | 0.7196248196248197 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 10 | 40 | 10 | 0 | 10.0 | 2.5 | 4 | 68 | 2 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 8343 | 18 | 0 | 0 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 17 | 40 | 17 | 0 | 13.333333333333334 | 5.666666666666667 | 3 | 77 | 5 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 3430 | 7 | 1 | 3 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 5177 | 11 | 1 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 8598 | 19 | 1 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: +64, 8t: -236, 16t: +80 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +0, 8t: -1, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +0, 8t: -1, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -3, 8t: -4, 16t: -4 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0, 8t: -0.0623056, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +24, 8t: +36, 16t: +44 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +6, 8t: +9, 16t: +18 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +3, 8t: +3, 16t: +4 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: -37, 8t: -11, 16t: -1 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL runtime_ms: 4t: +59, 8t: -237, 16t: +82 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL checked_paths: 4t: +0, 8t: -1, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL stale_paths: 4t: +0, 8t: -1, 16t: -1 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL duplicate freshness failures: 4t: -1, 8t: -2, 16t: -1 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL avg divergence: 4t: +0, 8t: -0.0623056, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE runtime_ms: 4t: +21, 8t: +232, 16t: +293 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE checked_paths: 4t: +0, 8t: +1, 16t: +1 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE stale_paths: 4t: +0, 8t: +1, 16t: +1 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE duplicate freshness failures: 4t: -1, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE avg divergence: 4t: +0, 8t: +0.0467292, 16t: +0.0468866 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL runtime_ms: 4t: +6, 8t: +245, 16t: -218 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL checked_paths: 4t: +0, 8t: +1, 16t: -1 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL stale_paths: 4t: +0, 8t: +1, 16t: -1 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL duplicate freshness failures: 4t: -1, 8t: +0, 16t: -2 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL avg divergence: 4t: +0, 8t: +0.0467292, 16t: -0.0551607 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: +30, 8t: +42, 16t: -227 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +0, 8t: +0, 16t: -1 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +0, 8t: +1, 16t: -1 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -3, 8t: -4, 16t: -4 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: +0, 8t: +0, 16t: -0.0551607 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: -47, 8t: -5, 16t: +28 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_1.i_2-db2cf3f1

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_triggered_by_first_fill | adaptive_triggered_by_first_fill_or_stale | adaptive_triggered_by_threads_ge_4_first_fill | first_dispatch_in_current_abstraction | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 51086 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 40851 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 44245 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 39115 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 | 27 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 40839 | 9 | 0 | 0 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 | 70 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 44249 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 | 79 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 39146 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 40821 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 56307 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 4 | SAFE | 51185 | 5 | 0 | 1 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 0 | 22 | 1 | 1 | 1 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 8 | SAFE | 52899 | 9 | 0 | 1 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 0 | 59 | 3 | 1 | 1 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 16 | SAFE | 44306 | 17 | 0 | 1 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 0 | 77 | 9 | 1 | 1 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 4 | SAFE | 51121 | 5 | 0 | 1 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 0 | 22 | 2 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 8 | SAFE | 40801 | 9 | 0 | 1 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 0 | 58 | 3 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 16 | SAFE | 56324 | 17 | 0 | 1 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 0 | 77 | 5 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 4 | SAFE | 39121 | 5 | 0 | 1 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 0 | 24 | 1 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 8 | SAFE | 40831 | 9 | 0 | 1 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 0 | 71 | 4 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 16 | SAFE | 44227 | 17 | 0 | 1 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 0 | 76 | 6 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 40854 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 | 29 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 52806 | 9 | 0 | 0 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 | 66 | 3 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 44261 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 | 80 | 7 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 51111 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 52850 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 44252 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: -11971, 8t: -12, 16t: +4 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +16, 8t: +32, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +4, 8t: +8, 16t: +16 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: -11940, 8t: -30, 16t: +12062 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL runtime_ms: 4t: +99, 8t: +12048, 16t: +61 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL duplicate freshness failures: 4t: -1, 8t: -1, 16t: -1 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE runtime_ms: 4t: +35, 8t: -50, 16t: +12079 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE duplicate freshness failures: 4t: -1, 8t: -1, 16t: -1 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL runtime_ms: 4t: -11965, 8t: -20, 16t: -18 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL duplicate freshness failures: 4t: -1, 8t: -1, 16t: -1 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: -10232, 8t: +11955, 16t: +16 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: +25, 8t: +11999, 16t: +7 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_2.i_2-b9aa1d3f

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_triggered_by_first_fill | adaptive_triggered_by_first_fill_or_stale | adaptive_triggered_by_threads_ge_4_first_fill | first_dispatch_in_current_abstraction | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 51340 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 53033 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 56578 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 51294 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 | 29 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 53059 | 9 | 0 | 0 | 16.460714285714282 | 0.45724206349206337 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 | 74 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 56555 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 | 78 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 51278 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 53010 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 56569 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 4 | SAFE | 41516 | 5 | 0 | 1 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 0 | 25 | 1 | 1 | 1 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 8 | SAFE | 43722 | 9 | 0 | 1 | 16.46071428571429 | 0.4572420634920636 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 0 | 66 | 4 | 1 | 1 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 16 | SAFE | 56578 | 17 | 0 | 1 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 0 | 70 | 7 | 1 | 1 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 4 | SAFE | 40230 | 5 | 0 | 1 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 0 | 24 | 1 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 8 | SAFE | 53053 | 9 | 0 | 1 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 0 | 71 | 4 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 16 | SAFE | 55540 | 17 | 0 | 1 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 0 | 92 | 5 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 4 | SAFE | 51262 | 5 | 0 | 1 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 0 | 22 | 1 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 8 | SAFE | 53036 | 9 | 0 | 1 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 0 | 61 | 4 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 16 | SAFE | 60334 | 17 | 0 | 1 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 0 | 86 | 8 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 51319 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 | 22 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 53172 | 9 | 0 | 0 | 16.46071428571429 | 0.4572420634920636 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 | 62 | 4 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 68567 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 | 86 | 7 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 41741 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 52994 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 56505 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: -46, 8t: +26, 16t: -23 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0, 8t: -1.11022e-16, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +16, 8t: +32, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +4, 8t: +8, 16t: +16 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: -62, 8t: -23, 16t: -9 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL runtime_ms: 4t: -9824, 8t: -9311, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL duplicate freshness failures: 4t: -1, 8t: -1, 16t: -1 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL avg divergence: 4t: +0, 8t: +1.11022e-16, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE runtime_ms: 4t: -11110, 8t: +20, 16t: -1038 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE duplicate freshness failures: 4t: -1, 8t: -1, 16t: -1 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL runtime_ms: 4t: -78, 8t: +3, 16t: +3756 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL duplicate freshness failures: 4t: -1, 8t: -1, 16t: -1 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: -21, 8t: +139, 16t: +11989 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: +0, 8t: +1.11022e-16, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: -9599, 8t: -39, 16t: -73 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_4.i_2-d2b55c6a

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_triggered_by_first_fill | adaptive_triggered_by_first_fill_or_stale | adaptive_triggered_by_threads_ge_4_first_fill | first_dispatch_in_current_abstraction | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 27194 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 29039 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 32530 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 27347 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 | 24 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 77213 | 9 | 0 | 0 | 16.460714285714282 | 0.45724206349206337 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 | 65 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 32435 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 | 71 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 27245 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 29006 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 32541 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 4 | SAFE | 27239 | 5 | 0 | 1 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 0 | 25 | 1 | 1 | 1 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 8 | SAFE | 29039 | 9 | 0 | 1 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 0 | 63 | 3 | 1 | 1 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 16 | SAFE | 32592 | 17 | 0 | 1 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 0 | 83 | 7 | 1 | 1 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 4 | SAFE | 27317 | 5 | 0 | 1 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 0 | 20 | 1 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 8 | SAFE | 29125 | 9 | 0 | 1 | 16.46071428571429 | 0.4572420634920636 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 0 | 62 | 4 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 16 | SAFE | 32542 | 17 | 0 | 1 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 0 | 68 | 6 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 4 | SAFE | 27222 | 5 | 0 | 1 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 0 | 22 | 1 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 8 | SAFE | 29021 | 9 | 0 | 1 | 16.46071428571429 | 0.4572420634920636 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 0 | 76 | 4 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 16 | SAFE | 32579 | 17 | 0 | 1 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 0 | 74 | 9 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 27232 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 | 24 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 41696 | 9 | 0 | 0 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 | 62 | 4 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 32648 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 | 83 | 6 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 27345 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 29032 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 32611 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: +153, 8t: +48174, 16t: -95 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0, 8t: -1.11022e-16, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +16, 8t: +32, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +4, 8t: +8, 16t: +16 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: +51, 8t: -33, 16t: +11 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL runtime_ms: 4t: +45, 8t: +0, 16t: +62 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL duplicate freshness failures: 4t: -1, 8t: -1, 16t: -1 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE runtime_ms: 4t: +123, 8t: +86, 16t: +12 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE duplicate freshness failures: 4t: -1, 8t: -1, 16t: -1 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE avg divergence: 4t: +0, 8t: +1.11022e-16, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL runtime_ms: 4t: +28, 8t: -18, 16t: +49 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL duplicate freshness failures: 4t: -1, 8t: -1, 16t: -1 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL avg divergence: 4t: +0, 8t: +1.11022e-16, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: +38, 8t: +12657, 16t: +118 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: +151, 8t: -7, 16t: +81 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_6.i_2-f35d9452

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_triggered_by_first_fill | adaptive_triggered_by_first_fill_or_stale | adaptive_triggered_by_threads_ge_4_first_fill | first_dispatch_in_current_abstraction | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 27257 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 29027 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 32320 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 27204 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 | 24 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 41863 | 9 | 0 | 0 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 | 72 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 32352 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 | 82 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 27279 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 29040 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 52223 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 4 | SAFE | 27227 | 5 | 0 | 1 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 0 | 25 | 1 | 1 | 1 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 8 | SAFE | 29027 | 9 | 0 | 1 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 0 | 59 | 4 | 1 | 1 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 16 | SAFE | 32392 | 17 | 0 | 1 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 0 | 80 | 6 | 1 | 1 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 4 | SAFE | 27232 | 5 | 0 | 1 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 0 | 26 | 1 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 8 | SAFE | 29047 | 9 | 0 | 1 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 0 | 62 | 3 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 16 | SAFE | 32457 | 17 | 0 | 1 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 0 | 82 | 9 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 4 | SAFE | 27322 | 5 | 0 | 1 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 0 | 25 | 1 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 8 | SAFE | 29017 | 9 | 0 | 1 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 0 | 66 | 4 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 16 | SAFE | 32471 | 17 | 0 | 1 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 0 | 75 | 8 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 27283 | 5 | 0 | 0 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 4 | 16 | 4 | 0 | 8.0 | 2.0 | 0 | 25 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 29113 | 9 | 0 | 0 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 0 | 78 | 3 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 32487 | 17 | 0 | 0 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 0 | 80 | 6 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 39866 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 29033 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 32311 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: -53, 8t: +12836, 16t: +32 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +16, 8t: +32, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +4, 8t: +8, 16t: +16 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: +22, 8t: +13, 16t: +19903 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL runtime_ms: 4t: -30, 8t: +0, 16t: +72 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL duplicate freshness failures: 4t: -1, 8t: -1, 16t: -1 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE runtime_ms: 4t: -25, 8t: +20, 16t: +137 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE duplicate freshness failures: 4t: -1, 8t: -1, 16t: -1 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL runtime_ms: 4t: +65, 8t: -10, 16t: +151 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL duplicate freshness failures: 4t: -1, 8t: -1, 16t: -1 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: +26, 8t: +86, 16t: +167 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -2, 8t: -2, 16t: -2 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: +12609, 8t: +6, 16t: -9 (ADAPTIVE_NEVER - PAPER).
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
