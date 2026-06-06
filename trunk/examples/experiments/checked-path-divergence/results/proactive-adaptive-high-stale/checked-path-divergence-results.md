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
- BatchLcpsInvocations: 324.
- BatchLcpsCandidatesGenerated/Selected: 3604 / 1114.
- BatchLcpsCandidateGenerationFailures: 19.
- BatchLcpsEffectiveBatchDecisions: 267.

### Performance

- BATCH_LCPS - PAPER runtime_ms: wins/losses/ties 5/10/0, mean -7494.53, median 493.00.
- ADAPTIVE_STALE - PAPER runtime_ms: wins/losses/ties 6/9/0, mean 170.27, median 6.00.
- ADAPTIVE_FIRST_FILL - PAPER runtime_ms: wins/losses/ties 4/11/0, mean -7423.00, median 506.00.
- ADAPTIVE_FIRST_FILL_OR_STALE - PAPER runtime_ms: wins/losses/ties 4/11/0, mean -7559.20, median 241.00.
- ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER runtime_ms: wins/losses/ties 5/10/0, mean -7494.27, median 221.00.
- ADAPTIVE_ALWAYS - PAPER runtime_ms: wins/losses/ties 4/11/0, mean -7537.13, median 335.00.
- ADAPTIVE_NEVER - PAPER runtime_ms: wins/losses/ties 6/9/0, mean 110.00, median 12.00.

### Work

- BATCH_LCPS - PAPER checked_paths: wins/losses/ties 4/9/2, mean 0.33, median 1.00.
- ADAPTIVE_STALE - PAPER checked_paths: wins/losses/ties 2/3/10, mean 0.40, median 0.00.
- ADAPTIVE_FIRST_FILL - PAPER checked_paths: wins/losses/ties 4/8/3, mean 0.27, median 1.00.
- ADAPTIVE_FIRST_FILL_OR_STALE - PAPER checked_paths: wins/losses/ties 7/7/1, mean -0.07, median 0.00.
- ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER checked_paths: wins/losses/ties 4/7/4, mean 0.20, median 0.00.
- ADAPTIVE_ALWAYS - PAPER checked_paths: wins/losses/ties 5/6/4, mean -0.27, median 0.00.
- ADAPTIVE_NEVER - PAPER checked_paths: wins/losses/ties 1/2/12, mean 0.27, median 0.00.
- BATCH_LCPS - PAPER stale_paths: wins/losses/ties 5/7/3, mean 0.60, median 0.00.
- ADAPTIVE_STALE - PAPER stale_paths: wins/losses/ties 4/4/7, mean 0.33, median 0.00.
- ADAPTIVE_FIRST_FILL - PAPER stale_paths: wins/losses/ties 5/7/3, mean 0.33, median 0.00.
- ADAPTIVE_FIRST_FILL_OR_STALE - PAPER stale_paths: wins/losses/ties 7/6/2, mean -0.13, median 0.00.
- ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER stale_paths: wins/losses/ties 6/7/2, mean 0.67, median 0.00.
- ADAPTIVE_ALWAYS - PAPER stale_paths: wins/losses/ties 4/8/3, mean 0.33, median 1.00.
- ADAPTIVE_NEVER - PAPER stale_paths: wins/losses/ties 1/4/10, mean 0.40, median 0.00.
- BATCH_LCPS - PAPER duplicate freshness failures: wins/losses/ties 15/0/0, mean -5.93, median -6.00.
- ADAPTIVE_STALE - PAPER duplicate freshness failures: wins/losses/ties 6/2/7, mean -0.53, median 0.00.
- ADAPTIVE_FIRST_FILL - PAPER duplicate freshness failures: wins/losses/ties 12/2/1, mean -1.87, median -2.00.
- ADAPTIVE_FIRST_FILL_OR_STALE - PAPER duplicate freshness failures: wins/losses/ties 11/2/2, mean -2.07, median -2.00.
- ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER duplicate freshness failures: wins/losses/ties 11/2/2, mean -2.07, median -2.00.
- ADAPTIVE_ALWAYS - PAPER duplicate freshness failures: wins/losses/ties 15/0/0, mean -5.60, median -5.00.
- ADAPTIVE_NEVER - PAPER duplicate freshness failures: wins/losses/ties 3/2/10, mean -0.07, median 0.00.
- BATCH_LCPS - PAPER search_failed: wins/losses/ties 0/3/12, mean 0.47, median 0.00.
- ADAPTIVE_STALE - PAPER search_failed: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_FIRST_FILL - PAPER search_failed: wins/losses/ties 0/3/12, mean 0.60, median 0.00.
- ADAPTIVE_FIRST_FILL_OR_STALE - PAPER search_failed: wins/losses/ties 0/2/13, mean 0.27, median 0.00.
- ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER search_failed: wins/losses/ties 0/3/12, mean 0.60, median 0.00.
- ADAPTIVE_ALWAYS - PAPER search_failed: wins/losses/ties 0/3/12, mean 0.67, median 0.00.
- ADAPTIVE_NEVER - PAPER search_failed: wins/losses/ties 0/0/15, mean 0.00, median 0.00.

### Batch Quality

- Average candidate pool size across BATCH_LCPS rows: 12.56.
- Average selected batch size across BATCH_LCPS rows: 4.08.
- Total effective batch decisions: 267.
- BATCH_LCPS - PAPER checked_paths: wins/losses/ties 4/9/2, mean 0.33, median 1.00.
- BATCH_LCPS - PAPER stale_paths: wins/losses/ties 5/7/3, mean 0.60, median 0.00.

### Adaptive Trigger Comparison

- ADAPTIVE_STALE: adaptive invocations/fallbacks 12/98, triggers dup/stale/failed/idle/first/first-or-stale/threads-ge4-first 0/12/0/0/0/0/0, candidates 188, effective batch decisions 12, candidates/invocation 15.67, generation/selection time 80ms/22ms, vs PAPER runtime [wins/losses/ties 6/9/0, mean 170.27, median 6.00], vs BATCH_LCPS runtime [wins/losses/ties 10/5/0, mean 7664.80, median -289.00], vs PAPER checked [wins/losses/ties 2/3/10, mean 0.40, median 0.00], vs PAPER stale [wins/losses/ties 4/4/7, mean 0.33, median 0.00].
- ADAPTIVE_FIRST_FILL: adaptive invocations/fallbacks 29/86, triggers dup/stale/failed/idle/first/first-or-stale/threads-ge4-first 0/0/0/0/29/0/0, candidates 548, effective batch decisions 25, candidates/invocation 18.90, generation/selection time 612ms/46ms, vs PAPER runtime [wins/losses/ties 4/11/0, mean -7423.00, median 506.00], vs BATCH_LCPS runtime [wins/losses/ties 8/7/0, mean 71.53, median -6.00], vs PAPER checked [wins/losses/ties 4/8/3, mean 0.27, median 1.00], vs PAPER stale [wins/losses/ties 5/7/3, mean 0.33, median 0.00].
- ADAPTIVE_FIRST_FILL_OR_STALE: adaptive invocations/fallbacks 26/81, triggers dup/stale/failed/idle/first/first-or-stale/threads-ge4-first 0/0/0/0/0/26/0, candidates 536, effective batch decisions 25, candidates/invocation 20.62, generation/selection time 613ms/54ms, vs PAPER runtime [wins/losses/ties 4/11/0, mean -7559.20, median 241.00], vs BATCH_LCPS runtime [wins/losses/ties 9/6/0, mean -64.67, median -17.00], vs PAPER checked [wins/losses/ties 7/7/1, mean -0.07, median 0.00], vs PAPER stale [wins/losses/ties 7/6/2, mean -0.13, median 0.00].
- ADAPTIVE_THREADS_GE_4_FIRST_FILL: adaptive invocations/fallbacks 33/80, triggers dup/stale/failed/idle/first/first-or-stale/threads-ge4-first 0/0/0/0/0/0/33, candidates 584, effective batch decisions 29, candidates/invocation 17.70, generation/selection time 650ms/49ms, vs PAPER runtime [wins/losses/ties 5/10/0, mean -7494.27, median 221.00], vs BATCH_LCPS runtime [wins/losses/ties 6/8/1, mean 0.27, median 2.00], vs PAPER checked [wins/losses/ties 4/7/4, mean 0.20, median 0.00], vs PAPER stale [wins/losses/ties 6/7/2, mean 0.67, median 0.00].
- ADAPTIVE_ALWAYS: adaptive invocations/fallbacks 108/5, triggers dup/stale/failed/idle/first/first-or-stale/threads-ge4-first 0/0/0/0/0/0/0, candidates 864, effective batch decisions 86, candidates/invocation 8.00, generation/selection time 753ms/40ms, vs PAPER runtime [wins/losses/ties 4/11/0, mean -7537.13, median 335.00], vs BATCH_LCPS runtime [wins/losses/ties 10/5/0, mean -42.60, median -7.00], vs PAPER checked [wins/losses/ties 5/6/4, mean -0.27, median 0.00], vs PAPER stale [wins/losses/ties 4/8/3, mean 0.33, median 1.00].
- ADAPTIVE_NEVER: adaptive invocations/fallbacks 0/107, triggers dup/stale/failed/idle/first/first-or-stale/threads-ge4-first 0/0/0/0/0/0/0, candidates 0, effective batch decisions 0, candidates/invocation 0.00, generation/selection time 0ms/0ms, vs PAPER runtime [wins/losses/ties 6/9/0, mean 110.00, median 12.00], vs BATCH_LCPS runtime [wins/losses/ties 10/5/0, mean 7604.53, median -489.00], vs PAPER checked [wins/losses/ties 1/2/12, mean 0.27, median 0.00], vs PAPER stale [wins/losses/ties 1/4/10, mean 0.40, median 0.00].

### Divergence

- BATCH_LCPS - PAPER avg divergence: wins/losses/ties 6/8/1, mean -0.01, median 0.00.
- ADAPTIVE_STALE - PAPER avg divergence: wins/losses/ties 6/3/6, mean -0.00, median 0.00.
- ADAPTIVE_FIRST_FILL - PAPER avg divergence: wins/losses/ties 7/6/2, mean -0.01, median 0.00.
- ADAPTIVE_FIRST_FILL_OR_STALE - PAPER avg divergence: wins/losses/ties 7/7/1, mean -0.01, median 0.00.
- ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER avg divergence: wins/losses/ties 5/9/1, mean 0.01, median 0.00.
- ADAPTIVE_ALWAYS - PAPER avg divergence: wins/losses/ties 5/8/2, mean 0.01, median 0.00.
- ADAPTIVE_NEVER - PAPER avg divergence: wins/losses/ties 3/3/9, mean 0.00, median 0.00.

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

## Results

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_triggered_by_first_fill | adaptive_triggered_by_first_fill_or_stale | adaptive_triggered_by_threads_ge_4_first_fill | first_dispatch_in_current_abstraction | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 4224 | 14 | 6 | 4 | 88.05537518037518 | 0.9676414854986284 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 4851 | 19 | 7 | 3 | 149.1035298035298 | 0.8719504666873088 | 12 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 8045 | 37 | 19 | 6 | 619.1133200133202 | 0.9295995795995798 | 23 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 3604 | 15 | 7 | 0 | 102.07698412698413 | 0.9721617535903251 | 12 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 10 | 14 | 56 | 14 | 0 | 5.6 | 1.4 | 7 | 27 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 4420 | 20 | 11 | 0 | 171.14438747526984 | 0.9007599340803676 | 13 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 6 | 19 | 76 | 19 | 0 | 12.666666666666666 | 3.1666666666666665 | 6 | 32 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 8558 | 35 | 16 | 0 | 540.0229603729604 | 0.9076016140722023 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 7 | 34 | 92 | 34 | 0 | 13.142857142857142 | 4.857142857142857 | 7 | 46 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 3828 | 14 | 5 | 2 | 87.91094402673349 | 0.9660543299641042 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 5 | 20 | 5 | 0 | 10.0 | 2.5 | 2 | 8 | 1 | 2 | 6 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 5747 | 25 | 14 | 10 | 294.00624375624375 | 0.9800208125208125 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 8 | 32 | 8 | 0 | 32.0 | 8.0 | 1 | 10 | 3 | 1 | 11 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 8233 | 34 | 17 | 4 | 514.8746420246421 | 0.9177801105608593 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 14 | 32 | 14 | 0 | 32.0 | 14.0 | 1 | 12 | 5 | 1 | 6 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 4 | SAFE | 3775 | 13 | 6 | 1 | 75.07698412698413 | 0.9625254375254375 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 6 | 24 | 6 | 0 | 12.0 | 3.0 | 2 | 18 | 1 | 2 | 6 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 8 | SAFE | 5055 | 20 | 9 | 2 | 171.55615218115219 | 0.9029271167429063 | 13 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 14 | 56 | 14 | 0 | 28.0 | 7.0 | 2 | 30 | 4 | 2 | 5 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 16 | SAFE | 7742 | 33 | 17 | 0 | 444.60607170607204 | 0.8420569539887728 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 30 | 64 | 30 | 0 | 32.0 | 15.0 | 2 | 31 | 6 | 2 | 2 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 4 | SAFE | 3793 | 13 | 5 | 1 | 75.07698412698413 | 0.9625254375254375 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 6 | 24 | 6 | 0 | 12.0 | 3.0 | 2 | 15 | 1 | 2 | 6 | 0 | 0 | 0 | 0 | 0 | 2 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 8 | SAFE | 4854 | 21 | 11 | 2 | 190.88948551448553 | 0.9089975500689788 | 14 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 16 | 64 | 16 | 0 | 21.333333333333332 | 5.333333333333333 | 3 | 30 | 3 | 3 | 4 | 0 | 0 | 0 | 0 | 0 | 3 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 16 | SAFE | 8193 | 35 | 16 | 0 | 509.73416909887544 | 0.8566960825191184 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 32 | 72 | 32 | 0 | 24.0 | 10.666666666666666 | 3 | 34 | 8 | 3 | 2 | 0 | 0 | 0 | 0 | 0 | 3 | 0 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 4 | SAFE | 3795 | 14 | 6 | 0 | 87.42284878863826 | 0.9606906460289919 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 10 | 40 | 10 | 0 | 10.0 | 2.5 | 4 | 21 | 1 | 4 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 8 | SAFE | 4845 | 21 | 12 | 1 | 206.09340708311294 | 0.981397176586252 | 15 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 17 | 68 | 17 | 0 | 22.666666666666668 | 5.666666666666667 | 3 | 35 | 3 | 3 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 16 | SAFE | 8188 | 35 | 18 | 2 | 574.2635808635812 | 0.9651488754009768 | 22 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 32 | 72 | 32 | 0 | 24.0 | 10.666666666666666 | 3 | 32 | 10 | 3 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 3896 | 16 | 8 | 0 | 114.25324675324674 | 0.9521103896103895 | 13 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 15 | 60 | 15 | 0 | 6.666666666666667 | 1.6666666666666667 | 7 | 26 | 1 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 4622 | 18 | 8 | 0 | 150.15615218115215 | 0.9814127593539357 | 12 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 5 | 18 | 72 | 18 | 0 | 14.4 | 3.6 | 5 | 31 | 2 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 8380 | 35 | 20 | 0 | 540.0229603729604 | 0.9076016140722023 | 23 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 7 | 34 | 92 | 34 | 0 | 13.142857142857142 | 4.857142857142857 | 7 | 51 | 5 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 4246 | 14 | 6 | 4 | 87.8670634920635 | 0.965572126286412 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 5657 | 22 | 11 | 5 | 216.47204184704182 | 0.9371084062642503 | 15 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 7820 | 37 | 20 | 4 | 613.885397690545 | 0.9217498463821997 | 23 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: -620, 8t: -431, 16t: +513 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +1, 8t: +1, 16t: -2 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +1, 8t: +4, 16t: -3 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -4, 8t: -3, 16t: -6 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0.00452027, 8t: +0.0288095, 16t: -0.021998 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +56, 8t: +76, 16t: +92 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +14, 8t: +19, 16t: +34 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +7, 8t: +6, 16t: +7 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: -396, 8t: +896, 16t: +188 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +6, 16t: -3 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: -1, 8t: +7, 16t: -2 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: -2, 8t: +7, 16t: -2 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: -0.00158716, 8t: +0.10807, 16t: -0.0118195 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL runtime_ms: 4t: -449, 8t: +204, 16t: -303 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL checked_paths: 4t: -1, 8t: +1, 16t: -4 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL stale_paths: 4t: +0, 8t: +2, 16t: -2 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL duplicate freshness failures: 4t: -3, 8t: -1, 16t: -6 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL avg divergence: 4t: -0.00511605, 8t: +0.0309767, 16t: -0.0875426 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE runtime_ms: 4t: -431, 8t: +3, 16t: +148 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE checked_paths: 4t: -1, 8t: +2, 16t: -2 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE stale_paths: 4t: -1, 8t: +4, 16t: -3 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE duplicate freshness failures: 4t: -3, 8t: -1, 16t: -6 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE avg divergence: 4t: -0.00511605, 8t: +0.0370471, 16t: -0.0729035 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL runtime_ms: 4t: -429, 8t: -6, 16t: +143 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL checked_paths: 4t: +0, 8t: +2, 16t: -2 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL stale_paths: 4t: +0, 8t: +5, 16t: -1 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL duplicate freshness failures: 4t: -4, 8t: -2, 16t: -4 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL avg divergence: 4t: -0.00695084, 8t: +0.109447, 16t: +0.0355493 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: -328, 8t: -229, 16t: +335 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +2, 8t: -1, 16t: -2 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +2, 8t: +1, 16t: +1 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -4, 8t: -3, 16t: -6 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: -0.0155311, 8t: +0.109462, 16t: -0.021998 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: +22, 8t: +806, 16t: -225 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +3, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +0, 8t: +4, 16t: +1 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +0, 8t: +2, 16t: -2 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: -0.00206936, 8t: +0.0651579, 16t: -0.00784973 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_triggered_by_first_fill | adaptive_triggered_by_first_fill_or_stale | adaptive_triggered_by_threads_ge_4_first_fill | first_dispatch_in_current_abstraction | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 4296 | 14 | 3 | 9 | 88.93333333333334 | 0.9772893772893774 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 6003 | 23 | 11 | 11 | 237.9718253968254 | 0.9406001003827091 | 16 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 8489 | 33 | 15 | 10 | 535.3478354978356 | 1.0139163551095371 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 5574 | 17 | 10 | 0 | 132.59285714285716 | 0.9749474789915967 | 14 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 12 | 16 | 64 | 16 | 0 | 5.333333333333333 | 1.3333333333333333 | 7 | 29 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 7281 | 26 | 11 | 0 | 309.85833333333335 | 0.9534102564102565 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 13 | 25 | 100 | 25 | 0 | 7.6923076923076925 | 1.9230769230769231 | 12 | 50 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 9555 | 36 | 15 | 0 | 578.4416666666666 | 0.9181613756613756 | 21 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 11 | 35 | 112 | 35 | 0 | 10.181818181818182 | 3.1818181818181817 | 11 | 86 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 4121 | 13 | 5 | 5 | 75.43333333333334 | 0.9670940170940171 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 2 | 8 | 2 | 0 | 8.0 | 2.0 | 1 | 2 | 1 | 1 | 7 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 6620 | 26 | 12 | 11 | 309.4974386724387 | 0.9522998112998114 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 8 | 32 | 8 | 0 | 16.0 | 4.0 | 2 | 14 | 2 | 2 | 11 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 8920 | 33 | 13 | 5 | 474.23124098124094 | 0.8981652291311382 | 18 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 12 | 48 | 12 | 0 | 16.0 | 4.0 | 3 | 27 | 6 | 3 | 5 | 0 | 3 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 4 | SAFE | 5287 | 17 | 8 | 7 | 132.7 | 0.9757352941176469 | 14 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 6 | 24 | 6 | 0 | 12.0 | 3.0 | 2 | 19 | 1 | 2 | 10 | 0 | 0 | 0 | 0 | 2 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 8 | SAFE | 7036 | 26 | 12 | 9 | 307.0805555555556 | 0.9448632478632479 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 16 | 64 | 16 | 0 | 21.333333333333332 | 5.333333333333333 | 3 | 35 | 3 | 3 | 9 | 0 | 0 | 0 | 0 | 3 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 16 | SAFE | 9795 | 36 | 14 | 7 | 578.4871212121211 | 0.9182335257335256 | 21 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 28 | 84 | 28 | 0 | 21.0 | 7.0 | 4 | 60 | 5 | 4 | 7 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 4 | SAFE | 5021 | 15 | 6 | 7 | 101.59285714285714 | 0.9675510204081632 | 12 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 6 | 24 | 6 | 0 | 12.0 | 3.0 | 2 | 21 | 1 | 2 | 8 | 0 | 0 | 0 | 0 | 0 | 2 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 8 | SAFE | 6849 | 26 | 12 | 10 | 307.8583333333333 | 0.9472564102564102 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 14 | 56 | 14 | 0 | 28.0 | 7.0 | 2 | 40 | 3 | 2 | 11 | 0 | 0 | 0 | 0 | 0 | 2 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 16 | SAFE | 9365 | 32 | 12 | 5 | 465.1851010101008 | 0.9378731875203645 | 18 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 27 | 72 | 27 | 0 | 24.0 | 9.0 | 3 | 56 | 9 | 3 | 5 | 0 | 0 | 0 | 0 | 0 | 3 | 0 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 4 | SAFE | 4909 | 15 | 8 | 7 | 112.59285714285714 | 1.072312925170068 | 13 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 6 | 24 | 6 | 0 | 12.0 | 3.0 | 2 | 19 | 1 | 2 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 8 | SAFE | 7244 | 26 | 13 | 6 | 305.84722222222223 | 0.9410683760683761 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 19 | 76 | 19 | 0 | 19.0 | 4.75 | 4 | 44 | 2 | 4 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 16 | SAFE | 9762 | 33 | 13 | 7 | 505.1638888888887 | 0.9567497895622893 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 26 | 72 | 26 | 0 | 24.0 | 8.666666666666666 | 3 | 51 | 6 | 3 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 4447 | 14 | 9 | 0 | 96.59285714285716 | 1.0614599686028259 | 12 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 14 | 56 | 14 | 0 | 6.222222222222222 | 1.5555555555555556 | 6 | 21 | 1 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 6614 | 24 | 11 | 0 | 275.85833333333335 | 0.9994867149758455 | 18 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 12 | 24 | 96 | 24 | 0 | 8.0 | 2.0 | 10 | 50 | 2 | 12 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 9760 | 32 | 11 | 0 | 476.14166666666654 | 0.9599630376344084 | 18 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 10 | 32 | 100 | 32 | 0 | 10.0 | 3.2 | 10 | 71 | 6 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 4042 | 14 | 4 | 9 | 88.93333333333334 | 0.9772893772893774 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 5990 | 22 | 11 | 10 | 230.60818903318903 | 0.998303848628524 | 16 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 8727 | 35 | 15 | 10 | 538.0621212121213 | 0.9043060860707921 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: +1278, 8t: +1278, 16t: +1066 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +3, 8t: +3, 16t: +3 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +7, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -9, 8t: -11, 16t: -10 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: -0.0023419, 8t: +0.0128102, 16t: -0.095755 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +64, 8t: +100, 16t: +112 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +16, 8t: +25, 16t: +35 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +7, 8t: +12, 16t: +11 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: -175, 8t: +617, 16t: +431 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: -1, 8t: +3, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +2, 8t: +1, 16t: -2 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: -4, 8t: +0, 16t: -5 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: -0.0101954, 8t: +0.0116997, 16t: -0.115751 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL runtime_ms: 4t: +991, 8t: +1033, 16t: +1306 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL checked_paths: 4t: +3, 8t: +3, 16t: +3 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL stale_paths: 4t: +5, 8t: +1, 16t: -1 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL duplicate freshness failures: 4t: -2, 8t: -2, 16t: -3 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL avg divergence: 4t: -0.00155408, 8t: +0.00426315, 16t: -0.0956828 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE runtime_ms: 4t: +725, 8t: +846, 16t: +876 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE checked_paths: 4t: +1, 8t: +3, 16t: -1 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE stale_paths: 4t: +3, 8t: +1, 16t: -3 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE duplicate freshness failures: 4t: -2, 8t: -1, 16t: -5 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE avg divergence: 4t: -0.00973836, 8t: +0.00665631, 16t: -0.0760432 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL runtime_ms: 4t: +613, 8t: +1241, 16t: +1273 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL checked_paths: 4t: +1, 8t: +3, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL stale_paths: 4t: +5, 8t: +2, 16t: -2 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL duplicate freshness failures: 4t: -2, 8t: -5, 16t: -3 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL avg divergence: 4t: +0.0950235, 8t: +0.000468276, 16t: -0.0571666 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: +151, 8t: +611, 16t: +1271 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +0, 8t: +1, 16t: -1 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +6, 8t: +0, 16t: -4 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -9, 8t: -11, 16t: -10 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: +0.0841706, 8t: +0.0588866, 16t: -0.0539533 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: -254, 8t: -13, 16t: +238 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: -1, 16t: +2 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +1, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +0, 8t: -1, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0, 8t: +0.0577037, 16t: -0.10961 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_triggered_by_first_fill | adaptive_triggered_by_first_fill_or_stale | adaptive_triggered_by_threads_ge_4_first_fill | first_dispatch_in_current_abstraction | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|---:|
| PAPER | n/a | 0 | true | true | 4 | UNKNOWN | 4288 | 11 | 5 | 6 | 52.4 | 0.9527272727272728 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 8 | UNKNOWN | 6111 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 16 | UNKNOWN | 4998 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | UNKNOWN | 4781 | 12 | 7 | 0 | 63.4 | 0.9606060606060606 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 11 | 44 | 11 | 0 | 4.888888888888889 | 1.2222222222222223 | 3 | 30 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | UNKNOWN | 6186 | 17 | 7 | 0 | 120.57936507936509 | 0.8866129785247433 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 10 | 16 | 64 | 16 | 0 | 6.4 | 1.6 | 8 | 72 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | UNKNOWN | 6080 | 17 | 0 | 0 | 55.82389081506728 | 0.4104697854049065 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 2 | 69 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | UNKNOWN | 4492 | 12 | 6 | 5 | 62.55384615384615 | 0.9477855477855478 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 2 | 8 | 2 | 0 | 8.0 | 2.0 | 1 | 3 | 1 | 1 | 7 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | UNKNOWN | 6110 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | UNKNOWN | 5000 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 4 | UNKNOWN | 4794 | 13 | 7 | 2 | 75.4 | 0.9666666666666668 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 22 | 1 | 1 | 9 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | false | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 8 | UNKNOWN | 6177 | 17 | 7 | 6 | 120.57936507936509 | 0.8866129785247433 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 58 | 2 | 1 | 9 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | false | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 16 | UNKNOWN | 6539 | 18 | 0 | 2 | 72.82389081506727 | 0.475973142582139 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 64 | 5 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | false | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 4 | UNKNOWN | 4756 | 13 | 7 | 2 | 75.4 | 0.9666666666666668 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 20 | 1 | 1 | 9 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | false | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 8 | UNKNOWN | 6352 | 17 | 7 | 5 | 119.68462823725983 | 0.8800340311563223 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 9 | 36 | 9 | 0 | 18.0 | 4.5 | 2 | 53 | 2 | 2 | 7 | 0 | 0 | 0 | 0 | 0 | 2 | 0 | false | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 16 | UNKNOWN | 6561 | 18 | 0 | 2 | 72.82389081506727 | 0.475973142582139 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 68 | 6 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | false | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 4 | UNKNOWN | 4786 | 14 | 8 | 3 | 88.4 | 0.9714285714285715 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 23 | 1 | 1 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | false | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 8 | UNKNOWN | 6182 | 17 | 7 | 5 | 119.68462823725983 | 0.8800340311563223 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 9 | 36 | 9 | 0 | 18.0 | 4.5 | 2 | 55 | 3 | 2 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | false | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 16 | UNKNOWN | 6511 | 18 | 0 | 2 | 72.82389081506727 | 0.475973142582139 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 68 | 5 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | false | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | UNKNOWN | 4735 | 12 | 6 | 0 | 63.4 | 0.9606060606060606 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 11 | 44 | 11 | 0 | 4.888888888888889 | 1.2222222222222223 | 3 | 31 | 1 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | UNKNOWN | 6179 | 17 | 7 | 0 | 120.57936507936509 | 0.8866129785247433 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 10 | 16 | 64 | 16 | 0 | 6.4 | 1.6 | 8 | 67 | 2 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | UNKNOWN | 5682 | 17 | 0 | 0 | 55.82389081506727 | 0.4104697854049064 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 16 | 36 | 16 | 0 | 18.0 | 8.0 | 2 | 78 | 4 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | UNKNOWN | 4281 | 11 | 5 | 5 | 52.4 | 0.9527272727272728 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | UNKNOWN | 6123 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | UNKNOWN | 4994 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: +493, 8t: +75, 16t: +1082 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +1, 8t: +2, 16t: +1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +2, 8t: +2, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -6, 8t: -8, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0.00787879, 8t: +0.0334762, 16t: +0.078604 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +44, 8t: +64, 16t: +36 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +11, 8t: +16, 16t: +16 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +3, 8t: +8, 16t: +2 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: +204, 8t: -1, 16t: +2 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +1, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +1, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: -1, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: -0.00494172, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL runtime_ms: 4t: +506, 8t: +66, 16t: +1541 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL checked_paths: 4t: +2, 8t: +2, 16t: +2 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL stale_paths: 4t: +2, 8t: +2, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL duplicate freshness failures: 4t: -4, 8t: -2, 16t: +1 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL avg divergence: 4t: +0.0139394, 8t: +0.0334762, 16t: +0.144107 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE runtime_ms: 4t: +468, 8t: +241, 16t: +1563 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE checked_paths: 4t: +2, 8t: +2, 16t: +2 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE stale_paths: 4t: +2, 8t: +2, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE duplicate freshness failures: 4t: -4, 8t: -3, 16t: +1 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE avg divergence: 4t: +0.0139394, 8t: +0.0268972, 16t: +0.144107 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL runtime_ms: 4t: +498, 8t: +71, 16t: +1513 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL checked_paths: 4t: +3, 8t: +2, 16t: +2 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL stale_paths: 4t: +3, 8t: +2, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL duplicate freshness failures: 4t: -3, 8t: -3, 16t: +1 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL avg divergence: 4t: +0.0187013, 8t: +0.0268972, 16t: +0.144107 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: +447, 8t: +68, 16t: +684 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +1, 8t: +2, 16t: +1 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +1, 8t: +2, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -6, 8t: -8, 16t: -1 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: +0.00787879, 8t: +0.0334762, 16t: +0.078604 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: -7, 8t: +12, 16t: -4 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: -1, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-diamond2.i_4-22ecac6d

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_triggered_by_first_fill | adaptive_triggered_by_first_fill_or_stale | adaptive_triggered_by_threads_ge_4_first_fill | first_dispatch_in_current_abstraction | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 4088 | 9 | 2 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 5266 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 8517 | 22 | 5 | 6 | 149.91479990597637 | 0.6489818177747895 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 4309 | 10 | 4 | 0 | 42.4 | 0.9422222222222222 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 8 | 10 | 36 | 9 | 1 | 4.5 | 1.125 | 6 | 30 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 5943 | 14 | 4 | 0 | 75.57936507936509 | 0.8305424733996164 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 8 | 14 | 52 | 13 | 1 | 6.5 | 1.625 | 7 | 61 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 9424 | 22 | 8 | 0 | 149.91912891030537 | 0.6490005580532701 | 12 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 11 | 36 | 56 | 21 | 5 | 5.090909090909091 | 1.9090909090909092 | 5 | 84 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 4094 | 9 | 2 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 5079 | 14 | 4 | 5 | 74.74603174603175 | 0.8213849642421072 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 2 | 8 | 2 | 0 | 8.0 | 2.0 | 1 | 4 | 3 | 1 | 5 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 8714 | 22 | 5 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 4 | SAFE | 4303 | 10 | 4 | 2 | 40.8040404040404 | 0.90675645342312 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 7 | 28 | 7 | 0 | 9.333333333333334 | 2.3333333333333335 | 2 | 26 | 1 | 3 | 3 | 0 | 0 | 0 | 0 | 3 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 8 | SAFE | 5933 | 14 | 4 | 7 | 75.57936507936509 | 0.8305424733996164 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 52 | 3 | 1 | 7 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 16 | SAFE | 9406 | 22 | 7 | 10 | 150.82389081506727 | 0.6529172762557025 | 11 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 24 | 32 | 15 | 3 | 8.0 | 3.75 | 1 | 60 | 6 | 4 | 10 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 4 | SAFE | 4292 | 10 | 4 | 5 | 42.4 | 0.9422222222222222 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 19 | 1 | 1 | 7 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 8 | SAFE | 5976 | 14 | 5 | 8 | 75.57936507936509 | 0.8305424733996164 | 9 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 | 9 | 28 | 7 | 1 | 14.0 | 3.5 | 1 | 53 | 3 | 2 | 8 | 0 | 0 | 0 | 0 | 0 | 2 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 16 | SAFE | 8964 | 21 | 4 | 6 | 149.82389081506727 | 0.713447099119368 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 63 | 5 | 1 | 6 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 4 | SAFE | 4309 | 10 | 4 | 5 | 42.4 | 0.9422222222222222 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 23 | 1 | 1 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 8 | SAFE | 5949 | 14 | 4 | 7 | 75.57936507936509 | 0.8305424733996164 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 55 | 2 | 1 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 16 | SAFE | 9455 | 22 | 8 | 8 | 149.91912891030537 | 0.6490005580532701 | 11 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 5 | 26 | 40 | 17 | 3 | 8.0 | 3.4 | 1 | 82 | 5 | 5 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 5 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 4296 | 10 | 4 | 1 | 42.4 | 0.9422222222222222 | 8 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 8 | 10 | 36 | 9 | 1 | 4.5 | 1.125 | 6 | 25 | 1 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 5938 | 14 | 4 | 1 | 75.57936507936509 | 0.8305424733996164 | 8 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 8 | 14 | 52 | 13 | 1 | 6.5 | 1.625 | 7 | 64 | 2 | 8 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 9407 | 22 | 7 | 3 | 149.91912891030537 | 0.6490005580532701 | 11 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 27 | 56 | 21 | 3 | 6.222222222222222 | 2.3333333333333335 | 5 | 84 | 4 | 9 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 4059 | 9 | 3 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 5297 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 8935 | 22 | 4 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: +221, 8t: +677, 16t: +907 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +1, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +2, 8t: -1, 16t: +3 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -5, 8t: -7, 16t: -6 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0.0144444, 8t: +0, 16t: +1.87403e-05 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +36, 8t: +52, 16t: +56 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +9, 8t: +13, 16t: +21 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +6, 8t: +7, 16t: +5 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: +6, 8t: -187, 16t: +197 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 8t: -1, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 8t: -2, 16t: +1 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 8t: -0.00915751, 16t: +0.00393546 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL runtime_ms: 4t: +215, 8t: +667, 16t: +889 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL checked_paths: 4t: +1, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL stale_paths: 4t: +2, 8t: -1, 16t: +2 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL duplicate freshness failures: 4t: -3, 8t: +0, 16t: +4 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL avg divergence: 4t: -0.0210213, 8t: +0, 16t: +0.00393546 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE runtime_ms: 4t: +204, 8t: +710, 16t: +447 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE checked_paths: 4t: +1, 8t: +0, 16t: -1 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE stale_paths: 4t: +2, 8t: +0, 16t: -1 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE duplicate freshness failures: 4t: +0, 8t: +1, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE avg divergence: 4t: +0.0144444, 8t: +0, 16t: +0.0644653 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL runtime_ms: 4t: +221, 8t: +683, 16t: +938 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL checked_paths: 4t: +1, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL stale_paths: 4t: +2, 8t: -1, 16t: +3 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL duplicate freshness failures: 4t: +0, 8t: +0, 16t: +2 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL avg divergence: 4t: +0.0144444, 8t: +0, 16t: +1.87403e-05 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: +208, 8t: +672, 16t: +890 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: +1, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: +2, 8t: -1, 16t: +2 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -4, 8t: -6, 16t: -3 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: +0.0144444, 8t: +0, 16t: +1.87403e-05 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: -29, 8t: +31, 16t: +418 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER stale_paths: 4t: +1, 8t: +0, 16t: -1 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER duplicate freshness failures: 4t: +0, 8t: +0, 16t: +1 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER avg divergence: 4t: +0, 8t: +0, 16t: +0.00393546 (ADAPTIVE_NEVER - PAPER).
- PAPER vs ADAPTIVE_NEVER lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_NEVER - PAPER).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-count_up_down.i_3-aa4a8d5f

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_triggered_by_first_fill | adaptive_triggered_by_first_fill_or_stale | adaptive_triggered_by_threads_ge_4_first_fill | first_dispatch_in_current_abstraction | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 121395 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 5149 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 7569 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 3284 | 6 | 1 | 0 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 5 | 20 | 5 | 0 | 6.666666666666667 | 1.6666666666666667 | 3 | 27 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 4832 | 10 | 1 | 0 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 9 | 36 | 9 | 0 | 12.0 | 3.0 | 3 | 71 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 7040 | 18 | 1 | 0 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 17 | 40 | 17 | 0 | 13.333333333333334 | 5.666666666666667 | 3 | 73 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 122201 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 5121 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 7563 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 4 | SAFE | 3042 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 23 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 8 | SAFE | 4851 | 10 | 1 | 2 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 52 | 2 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 16 | SAFE | 8209 | 19 | 2 | 3 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 62 | 5 | 1 | 3 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 4 | SAFE | 3303 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 20 | 3 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 8 | SAFE | 4830 | 10 | 1 | 2 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 54 | 2 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 16 | SAFE | 6792 | 18 | 1 | 2 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 67 | 6 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 4 | SAFE | 3075 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 20 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 8 | SAFE | 4834 | 10 | 1 | 2 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 57 | 3 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 16 | SAFE | 7031 | 18 | 1 | 2 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 65 | 5 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 3281 | 6 | 1 | 0 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 5 | 20 | 5 | 0 | 6.666666666666667 | 1.6666666666666667 | 3 | 23 | 1 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 8 | SAFE | 4836 | 10 | 1 | 0 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 9 | 36 | 9 | 0 | 12.0 | 3.0 | 3 | 63 | 3 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 16 | SAFE | 8159 | 19 | 2 | 0 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 18 | 44 | 18 | 0 | 11.0 | 4.5 | 4 | 68 | 5 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 4 | SAFE | 121825 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 8 | SAFE | 5157 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_NEVER | NEVER_BATCH | 0 | true | true | 16 | SAFE | 7786 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: -118111, 8t: -317, 16t: -529 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: -7, 8t: -1, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: -6, 8t: -1, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -5, 8t: -4, 16t: -4 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: -0.14, 8t: -0.0623056, 16t: -0.0551607 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +20, 8t: +36, 16t: +40 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +5, 8t: +9, 16t: +17 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +3, 8t: +3, 16t: +3 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: +806, 8t: -28, 16t: -6 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL runtime_ms: 4t: -118353, 8t: -298, 16t: +640 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL checked_paths: 4t: -7, 8t: -1, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL stale_paths: 4t: -6, 8t: -1, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL duplicate freshness failures: 4t: -4, 8t: -2, 16t: -1 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL avg divergence: 4t: -0.14, 8t: -0.0623056, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE runtime_ms: 4t: -118092, 8t: -319, 16t: -777 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE checked_paths: 4t: -7, 8t: -1, 16t: -1 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE stale_paths: 4t: -6, 8t: -1, 16t: -1 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE duplicate freshness failures: 4t: -4, 8t: -2, 16t: -2 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE avg divergence: 4t: -0.14, 8t: -0.0623056, 16t: -0.0551607 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL runtime_ms: 4t: -118320, 8t: -315, 16t: -538 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL checked_paths: 4t: -7, 8t: -1, 16t: -1 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL stale_paths: 4t: -6, 8t: -1, 16t: -1 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL duplicate freshness failures: 4t: -4, 8t: -2, 16t: -2 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL avg divergence: 4t: -0.14, 8t: -0.0623056, 16t: -0.0551607 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_ALWAYS runtime_ms: 4t: -118114, 8t: -313, 16t: +590 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS checked_paths: 4t: -7, 8t: -1, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS stale_paths: 4t: -6, 8t: -1, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: 4t: -5, 8t: -4, 16t: -4 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS avg divergence: 4t: -0.14, 8t: -0.0623056, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (ADAPTIVE_ALWAYS - PAPER).
- PAPER vs ADAPTIVE_NEVER runtime_ms: 4t: +430, 8t: +8, 16t: +217 (ADAPTIVE_NEVER - PAPER).
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
