# Checked Path Divergence Results

## Purpose

This experiment compares PAPER, LCPS, BATCH_LCPS, and optional BFS/DFS path selection under parallel TraceAbstraction.

Each unordered checked-path pair contributes normalized prefix-LCA divergence `1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.

## Summary

### Correctness

- Modes present: PAPER, BATCH_LCPS, ADAPTIVE_STALE, ADAPTIVE_FIRST_FILL, ADAPTIVE_FIRST_FILL_OR_STALE, ADAPTIVE_THREADS_GE_4_FIRST_FILL.
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
- BatchLcpsInvocations: 94.
- BatchLcpsCandidatesGenerated/Selected: 1576 / 534.
- BatchLcpsCandidateGenerationFailures: 0.
- BatchLcpsEffectiveBatchDecisions: 94.

### Performance

- BATCH_LCPS - PAPER runtime_ms: wins/losses/ties 12/3/0, mean -39923.80, median -501.00.
- ADAPTIVE_STALE - PAPER runtime_ms: wins/losses/ties 8/7/0, mean -15.33, median -7.00.
- ADAPTIVE_FIRST_FILL - PAPER runtime_ms: wins/losses/ties 12/3/0, mean -39842.27, median -535.00.
- ADAPTIVE_FIRST_FILL_OR_STALE - PAPER runtime_ms: wins/losses/ties 11/4/0, mean -39869.80, median -541.00.
- ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER runtime_ms: wins/losses/ties 12/3/0, mean -39940.20, median -573.00.

### Work

- BATCH_LCPS - PAPER checked_paths: wins/losses/ties 11/0/4, mean -2.73, median -1.00.
- ADAPTIVE_STALE - PAPER checked_paths: wins/losses/ties 1/0/14, mean -0.07, median 0.00.
- ADAPTIVE_FIRST_FILL - PAPER checked_paths: wins/losses/ties 12/0/3, mean -2.80, median -1.00.
- ADAPTIVE_FIRST_FILL_OR_STALE - PAPER checked_paths: wins/losses/ties 11/2/2, mean -2.60, median -1.00.
- ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER checked_paths: wins/losses/ties 12/0/3, mean -2.80, median -1.00.
- BATCH_LCPS - PAPER stale_paths: wins/losses/ties 14/0/1, mean -2.60, median -1.00.
- ADAPTIVE_STALE - PAPER stale_paths: wins/losses/ties 1/0/14, mean -0.13, median 0.00.
- ADAPTIVE_FIRST_FILL - PAPER stale_paths: wins/losses/ties 15/0/0, mean -2.67, median -1.00.
- ADAPTIVE_FIRST_FILL_OR_STALE - PAPER stale_paths: wins/losses/ties 12/2/1, mean -2.33, median -1.00.
- ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER stale_paths: wins/losses/ties 15/0/0, mean -2.67, median -1.00.
- BATCH_LCPS - PAPER duplicate freshness failures: wins/losses/ties 15/0/0, mean -4.33, median -4.00.
- ADAPTIVE_STALE - PAPER duplicate freshness failures: wins/losses/ties 1/0/14, mean -0.07, median 0.00.
- ADAPTIVE_FIRST_FILL - PAPER duplicate freshness failures: wins/losses/ties 15/0/0, mean -2.47, median -2.00.
- ADAPTIVE_FIRST_FILL_OR_STALE - PAPER duplicate freshness failures: wins/losses/ties 13/0/2, mean -2.27, median -2.00.
- ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER duplicate freshness failures: wins/losses/ties 15/0/0, mean -2.47, median -2.00.
- BATCH_LCPS - PAPER search_failed: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_STALE - PAPER search_failed: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_FIRST_FILL - PAPER search_failed: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_FIRST_FILL_OR_STALE - PAPER search_failed: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER search_failed: wins/losses/ties 0/0/15, mean 0.00, median 0.00.

### Batch Quality

- Average candidate pool size across BATCH_LCPS rows: 16.42.
- Average selected batch size across BATCH_LCPS rows: 5.65.
- Total effective batch decisions: 94.
- BATCH_LCPS - PAPER checked_paths: wins/losses/ties 11/0/4, mean -2.73, median -1.00.
- BATCH_LCPS - PAPER stale_paths: wins/losses/ties 14/0/1, mean -2.60, median -1.00.

### Adaptive Trigger Comparison

- ADAPTIVE_STALE: adaptive invocations/fallbacks 0/89, triggers dup/stale/failed/idle/first/first-or-stale/threads-ge4-first 0/0/0/0/0/0/0, candidates 0, effective batch decisions 0, candidates/invocation 0.00, generation/selection time 0ms/0ms, vs PAPER runtime [wins/losses/ties 8/7/0, mean -15.33, median -7.00], vs BATCH_LCPS runtime [wins/losses/ties 3/12/0, mean 39908.47, median 545.00], vs PAPER checked [wins/losses/ties 1/0/14, mean -0.07, median 0.00], vs PAPER stale [wins/losses/ties 1/0/14, mean -0.13, median 0.00].
- ADAPTIVE_FIRST_FILL: adaptive invocations/fallbacks 15/33, triggers dup/stale/failed/idle/first/first-or-stale/threads-ge4-first 0/0/0/0/15/0/0, candidates 360, effective batch decisions 15, candidates/invocation 24.00, generation/selection time 700ms/49ms, vs PAPER runtime [wins/losses/ties 12/3/0, mean -39842.27, median -535.00], vs BATCH_LCPS runtime [wins/losses/ties 7/8/0, mean 81.53, median 3.00], vs PAPER checked [wins/losses/ties 12/0/3, mean -2.80, median -1.00], vs PAPER stale [wins/losses/ties 15/0/0, mean -2.67, median -1.00].
- ADAPTIVE_FIRST_FILL_OR_STALE: adaptive invocations/fallbacks 15/36, triggers dup/stale/failed/idle/first/first-or-stale/threads-ge4-first 0/0/0/0/0/15/0, candidates 360, effective batch decisions 15, candidates/invocation 24.00, generation/selection time 738ms/47ms, vs PAPER runtime [wins/losses/ties 11/4/0, mean -39869.80, median -541.00], vs BATCH_LCPS runtime [wins/losses/ties 11/4/0, mean 54.00, median -40.00], vs PAPER checked [wins/losses/ties 11/2/2, mean -2.60, median -1.00], vs PAPER stale [wins/losses/ties 12/2/1, mean -2.33, median -1.00].
- ADAPTIVE_THREADS_GE_4_FIRST_FILL: adaptive invocations/fallbacks 15/33, triggers dup/stale/failed/idle/first/first-or-stale/threads-ge4-first 0/0/0/0/0/0/15, candidates 360, effective batch decisions 15, candidates/invocation 24.00, generation/selection time 717ms/46ms, vs PAPER runtime [wins/losses/ties 12/3/0, mean -39940.20, median -573.00], vs BATCH_LCPS runtime [wins/losses/ties 9/6/0, mean -16.40, median -19.00], vs PAPER checked [wins/losses/ties 12/0/3, mean -2.80, median -1.00], vs PAPER stale [wins/losses/ties 15/0/0, mean -2.67, median -1.00].

### Divergence

- BATCH_LCPS - PAPER avg divergence: wins/losses/ties 11/0/4, mean -0.07, median -0.06.
- ADAPTIVE_STALE - PAPER avg divergence: wins/losses/ties 1/0/14, mean -0.00, median 0.00.
- ADAPTIVE_FIRST_FILL - PAPER avg divergence: wins/losses/ties 12/0/3, mean -0.07, median -0.06.
- ADAPTIVE_FIRST_FILL_OR_STALE - PAPER avg divergence: wins/losses/ties 11/2/2, mean -0.06, median -0.06.
- ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER avg divergence: wins/losses/ties 12/0/3, mean -0.07, median -0.06.

### Interpretation

- Cache hits occurred only as instrumentation; effective priority decisions stayed at 0, so ordering did not change.
- BATCH_LCPS changed at least one dispatched batch relative to naive first-k candidate dispatch.
- LCPS does not need higher divergence to be useful; interpret divergence together with runtime, checked_paths, stale_paths, duplicate freshness failures, and effective priority decisions.

## Benchmark Selection

- `k-examples-programs-20170304-DifficultPathPrograms-resultKnown-count_up_down.i_3-aa4a8d5f`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/count_up_down.i_3.bpl.

## Results

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-count_up_down.i_3-aa4a8d5f

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_triggered_by_first_fill | adaptive_triggered_by_first_fill_or_stale | adaptive_triggered_by_threads_ge_4_first_fill | first_dispatch_in_current_abstraction | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 122965 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 5124 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 7606 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 1 | true | true | 4 | SAFE | 122401 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 1 | true | true | 8 | SAFE | 5138 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 1 | true | true | 16 | SAFE | 7580 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 2 | true | true | 4 | SAFE | 123007 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 2 | true | true | 8 | SAFE | 5105 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 2 | true | true | 16 | SAFE | 7807 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 3 | true | true | 4 | SAFE | 122445 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 3 | true | true | 8 | SAFE | 5163 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 3 | true | true | 16 | SAFE | 7550 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 4 | true | true | 4 | SAFE | 121757 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 4 | true | true | 8 | SAFE | 5128 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| PAPER | n/a | 4 | true | true | 16 | SAFE | 7771 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 3018 | 6 | 1 | 0 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 5 | 20 | 5 | 0 | 6.666666666666667 | 1.6666666666666667 | 3 | 24 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 5414 | 11 | 1 | 0 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 10 | 40 | 10 | 0 | 10.0 | 2.5 | 4 | 59 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 6988 | 18 | 1 | 0 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 17 | 40 | 17 | 0 | 13.333333333333334 | 5.666666666666667 | 3 | 76 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 1 | true | true | 4 | SAFE | 3047 | 6 | 1 | 0 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 5 | 20 | 5 | 0 | 6.666666666666667 | 1.6666666666666667 | 3 | 24 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 1 | true | true | 8 | SAFE | 4854 | 10 | 1 | 0 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 9 | 36 | 9 | 0 | 12.0 | 3.0 | 3 | 62 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 1 | true | true | 16 | SAFE | 7446 | 19 | 2 | 0 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 18 | 44 | 18 | 0 | 11.0 | 4.5 | 4 | 75 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 2 | true | true | 4 | SAFE | 3275 | 6 | 1 | 0 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 5 | 20 | 5 | 0 | 6.666666666666667 | 1.6666666666666667 | 3 | 23 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 2 | true | true | 8 | SAFE | 4875 | 10 | 1 | 0 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 9 | 36 | 9 | 0 | 12.0 | 3.0 | 3 | 60 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 2 | true | true | 16 | SAFE | 7429 | 18 | 1 | 0 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 17 | 40 | 17 | 0 | 13.333333333333334 | 5.666666666666667 | 3 | 75 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 3 | true | true | 4 | SAFE | 3039 | 6 | 1 | 0 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 5 | 20 | 5 | 0 | 6.666666666666667 | 1.6666666666666667 | 3 | 25 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 3 | true | true | 8 | SAFE | 5498 | 11 | 1 | 0 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 10 | 40 | 10 | 0 | 10.0 | 2.5 | 4 | 63 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 3 | true | true | 16 | SAFE | 7049 | 18 | 1 | 0 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 17 | 40 | 17 | 0 | 13.333333333333334 | 5.666666666666667 | 3 | 89 | 8 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 4 | true | true | 4 | SAFE | 3255 | 6 | 1 | 0 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 5 | 20 | 5 | 0 | 6.666666666666667 | 1.6666666666666667 | 3 | 24 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 4 | true | true | 8 | SAFE | 5488 | 11 | 1 | 0 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 10 | 40 | 10 | 0 | 10.0 | 2.5 | 4 | 51 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| BATCH_LCPS | n/a | 4 | true | true | 16 | SAFE | 7015 | 18 | 1 | 0 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 17 | 40 | 17 | 0 | 13.333333333333334 | 5.666666666666667 | 3 | 84 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 121899 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 5149 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 7590 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 1 | true | true | 4 | SAFE | 121804 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 1 | true | true | 8 | SAFE | 5145 | 10 | 0 | 3 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 1 | true | true | 16 | SAFE | 7581 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 2 | true | true | 4 | SAFE | 122565 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 2 | true | true | 8 | SAFE | 5146 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 2 | true | true | 16 | SAFE | 7585 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 3 | true | true | 4 | SAFE | 122328 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 3 | true | true | 8 | SAFE | 5156 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 3 | true | true | 16 | SAFE | 7979 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 4 | true | true | 4 | SAFE | 123671 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 4 | true | true | 8 | SAFE | 5159 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 4 | true | true | 16 | SAFE | 7560 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 4 | SAFE | 3035 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 19 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 8 | SAFE | 4629 | 10 | 1 | 2 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 53 | 2 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 16 | SAFE | 7033 | 18 | 1 | 2 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 68 | 7 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 1 | true | true | 4 | SAFE | 3036 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 20 | 3 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 1 | true | true | 8 | SAFE | 5509 | 11 | 1 | 3 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 51 | 2 | 1 | 3 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 1 | true | true | 16 | SAFE | 8765 | 19 | 1 | 3 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 58 | 5 | 1 | 3 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 2 | true | true | 4 | SAFE | 3278 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 28 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 2 | true | true | 8 | SAFE | 4868 | 10 | 1 | 2 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 56 | 2 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 2 | true | true | 16 | SAFE | 7017 | 18 | 1 | 2 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 67 | 5 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 3 | true | true | 4 | SAFE | 3281 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 21 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 3 | true | true | 8 | SAFE | 4845 | 10 | 1 | 2 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 57 | 4 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 3 | true | true | 16 | SAFE | 7237 | 18 | 1 | 2 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 65 | 5 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 4 | true | true | 4 | SAFE | 3050 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 19 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 4 | true | true | 8 | SAFE | 4593 | 10 | 1 | 2 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 57 | 3 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 4 | true | true | 16 | SAFE | 8737 | 19 | 1 | 3 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 61 | 7 | 1 | 3 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 4 | SAFE | 3072 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 25 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 8 | SAFE | 4870 | 10 | 2 | 2 | 29.579365079365083 | 0.6573192239858907 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 59 | 2 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 0 | true | true | 16 | SAFE | 8699 | 19 | 1 | 3 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 66 | 5 | 1 | 3 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 1 | true | true | 4 | SAFE | 3069 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 22 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 1 | true | true | 8 | SAFE | 4828 | 10 | 1 | 2 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 58 | 2 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 1 | true | true | 16 | SAFE | 7022 | 18 | 1 | 2 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 66 | 5 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 2 | true | true | 4 | SAFE | 3023 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 21 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 2 | true | true | 8 | SAFE | 4600 | 10 | 1 | 2 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 55 | 3 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 2 | true | true | 16 | SAFE | 8514 | 20 | 3 | 4 | 109.82389081506727 | 0.5780204779740383 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 74 | 8 | 1 | 4 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 3 | true | true | 4 | SAFE | 3037 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 24 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 3 | true | true | 8 | SAFE | 5301 | 11 | 1 | 3 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 54 | 3 | 1 | 3 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 3 | true | true | 16 | SAFE | 7009 | 18 | 1 | 2 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 72 | 6 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 4 | true | true | 4 | SAFE | 3036 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 23 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 4 | true | true | 8 | SAFE | 5451 | 12 | 3 | 4 | 50.57936507936508 | 0.7663540163540165 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 54 | 2 | 1 | 4 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | FIRST_FILL_OR_STALE | 4 | true | true | 16 | SAFE | 6969 | 18 | 1 | 2 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 65 | 6 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 4 | SAFE | 3029 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 21 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 8 | SAFE | 4846 | 10 | 1 | 2 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 56 | 2 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 0 | true | true | 16 | SAFE | 8775 | 19 | 1 | 3 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 62 | 5 | 1 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 1 | true | true | 4 | SAFE | 3053 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 22 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 1 | true | true | 8 | SAFE | 4844 | 10 | 1 | 2 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 53 | 3 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 1 | true | true | 16 | SAFE | 7007 | 18 | 1 | 2 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 64 | 6 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 2 | true | true | 4 | SAFE | 3310 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 25 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 2 | true | true | 8 | SAFE | 4836 | 10 | 1 | 2 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 52 | 2 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 2 | true | true | 16 | SAFE | 6835 | 18 | 1 | 2 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 58 | 5 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 3 | true | true | 4 | SAFE | 3020 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 25 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 3 | true | true | 8 | SAFE | 5527 | 11 | 1 | 3 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 54 | 4 | 1 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 3 | true | true | 16 | SAFE | 6819 | 18 | 1 | 2 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 72 | 6 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 4 | true | true | 4 | SAFE | 3019 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 24 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 4 | true | true | 8 | SAFE | 5285 | 11 | 1 | 3 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 7 | 28 | 7 | 0 | 28.0 | 7.0 | 1 | 62 | 3 | 1 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | THREADS_GE_4_FIRST_FILL | 4 | true | true | 16 | SAFE | 7239 | 18 | 1 | 2 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 15 | 32 | 15 | 0 | 32.0 | 15.0 | 1 | 67 | 5 | 1 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | true | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: -119947, 4t: -119354, 4t: -119732, 4t: -119406, 4t: -118502, 8t: +290, 8t: -284, 8t: -230, 8t: +335, 8t: +360, 16t: -618, 16t: -134, 16t: -378, 16t: -501, 16t: -756 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: -7, 4t: -7, 4t: -7, 4t: -7, 4t: -7, 8t: +0, 8t: -1, 8t: -1, 8t: +0, 8t: +0, 16t: -1, 16t: +0, 16t: -1, 16t: -1, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: -6, 4t: -6, 4t: -6, 4t: -6, 4t: -6, 8t: -1, 8t: -1, 8t: -1, 8t: -1, 8t: -1, 16t: -1, 16t: +0, 16t: -1, 16t: -1, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -5, 4t: -5, 4t: -5, 4t: -5, 4t: -5, 8t: -4, 8t: -4, 8t: -4, 8t: -4, 8t: -4, 16t: -4, 16t: -4, 16t: -4, 16t: -4, 16t: -4 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: -0.14, 4t: -0.14, 4t: -0.14, 4t: -0.14, 4t: -0.14, 8t: +0, 8t: -0.0623056, 8t: -0.0623056, 8t: +0, 8t: +0, 16t: -0.0551607, 16t: +0, 16t: -0.0551607, 16t: -0.0551607, 16t: -0.0551607 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +20, 4t: +20, 4t: +20, 4t: +20, 4t: +20, 8t: +40, 8t: +36, 8t: +36, 8t: +40, 8t: +40, 16t: +40, 16t: +44, 16t: +40, 16t: +40, 16t: +40 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +5, 4t: +5, 4t: +5, 4t: +5, 4t: +5, 8t: +10, 8t: +9, 8t: +9, 8t: +10, 8t: +10, 16t: +17, 16t: +18, 16t: +17, 16t: +17, 16t: +17 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +3, 4t: +3, 4t: +3, 4t: +3, 4t: +3, 8t: +4, 8t: +3, 8t: +3, 8t: +4, 8t: +4, 16t: +3, 16t: +4, 16t: +3, 16t: +3, 16t: +3 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: -1066, 4t: -597, 4t: -442, 4t: -117, 4t: +1914, 8t: +25, 8t: +7, 8t: +41, 8t: -7, 8t: +31, 16t: -16, 16t: +1, 16t: -222, 16t: +429, 16t: -211 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: -1, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: -2, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: -1, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: -0.0623056, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL runtime_ms: 4t: -119930, 4t: -119365, 4t: -119729, 4t: -119164, 4t: -118707, 8t: -495, 8t: +371, 8t: -237, 8t: -318, 8t: -535, 16t: -573, 16t: +1185, 16t: -790, 16t: -313, 16t: +966 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL checked_paths: 4t: -7, 4t: -7, 4t: -7, 4t: -7, 4t: -7, 8t: -1, 8t: +0, 8t: -1, 8t: -1, 8t: -1, 16t: -1, 16t: +0, 16t: -1, 16t: -1, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL stale_paths: 4t: -6, 4t: -6, 4t: -6, 4t: -6, 4t: -6, 8t: -1, 8t: -1, 8t: -1, 8t: -1, 8t: -1, 16t: -1, 16t: -1, 16t: -1, 16t: -1, 16t: -1 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL duplicate freshness failures: 4t: -4, 4t: -4, 4t: -4, 4t: -4, 4t: -4, 8t: -2, 8t: -1, 8t: -2, 8t: -2, 8t: -2, 16t: -2, 16t: -1, 16t: -2, 16t: -2, 16t: -1 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL avg divergence: 4t: -0.14, 4t: -0.14, 4t: -0.14, 4t: -0.14, 4t: -0.14, 8t: -0.0623056, 8t: +0, 8t: -0.0623056, 8t: -0.0623056, 8t: -0.0623056, 16t: -0.0551607, 16t: +0, 16t: -0.0551607, 16t: -0.0551607, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE runtime_ms: 4t: -119893, 4t: -119332, 4t: -119984, 4t: -119408, 4t: -118721, 8t: -254, 8t: -310, 8t: -505, 8t: +138, 8t: +323, 16t: +1093, 16t: -558, 16t: +707, 16t: -541, 16t: -802 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE checked_paths: 4t: -7, 4t: -7, 4t: -7, 4t: -7, 4t: -7, 8t: -1, 8t: -1, 8t: -1, 8t: +0, 8t: +1, 16t: +0, 16t: -1, 16t: +1, 16t: -1, 16t: -1 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE stale_paths: 4t: -6, 4t: -6, 4t: -6, 4t: -6, 4t: -6, 8t: +0, 8t: -1, 8t: -1, 8t: -1, 8t: +1, 16t: -1, 16t: -1, 16t: +1, 16t: -1, 16t: -1 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE duplicate freshness failures: 4t: -4, 4t: -4, 4t: -4, 4t: -4, 4t: -4, 8t: -2, 8t: -2, 8t: -2, 8t: -1, 8t: +0, 16t: -1, 16t: -2, 16t: +0, 16t: -2, 16t: -2 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE avg divergence: 4t: -0.14, 4t: -0.14, 4t: -0.14, 4t: -0.14, 4t: -0.14, 8t: -0.0623056, 8t: -0.0623056, 8t: -0.0623056, 8t: +0, 8t: +0.0467292, 16t: +0, 16t: -0.0551607, 16t: +0.0468866, 16t: -0.0551607, 16t: -0.0551607 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_FIRST_FILL_OR_STALE lcps_effective_priority_decisions: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_FIRST_FILL_OR_STALE - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL runtime_ms: 4t: -119936, 4t: -119348, 4t: -119697, 4t: -119425, 4t: -118738, 8t: -278, 8t: -294, 8t: -269, 8t: +364, 8t: +157, 16t: +1169, 16t: -573, 16t: -972, 16t: -731, 16t: -532 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL checked_paths: 4t: -7, 4t: -7, 4t: -7, 4t: -7, 4t: -7, 8t: -1, 8t: -1, 8t: -1, 8t: +0, 8t: +0, 16t: +0, 16t: -1, 16t: -1, 16t: -1, 16t: -1 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL stale_paths: 4t: -6, 4t: -6, 4t: -6, 4t: -6, 4t: -6, 8t: -1, 8t: -1, 8t: -1, 8t: -1, 8t: -1, 16t: -1, 16t: -1, 16t: -1, 16t: -1, 16t: -1 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL duplicate freshness failures: 4t: -4, 4t: -4, 4t: -4, 4t: -4, 4t: -4, 8t: -2, 8t: -2, 8t: -2, 8t: -1, 8t: -1, 16t: -1, 16t: -2, 16t: -2, 16t: -2, 16t: -2 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL avg divergence: 4t: -0.14, 4t: -0.14, 4t: -0.14, 4t: -0.14, 4t: -0.14, 8t: -0.0623056, 8t: -0.0623056, 8t: -0.0623056, 8t: +0, 8t: +0, 16t: +0, 16t: -0.0551607, 16t: -0.0551607, 16t: -0.0551607, 16t: -0.0551607 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).
- PAPER vs ADAPTIVE_THREADS_GE_4_FIRST_FILL lcps_effective_priority_decisions: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_THREADS_GE_4_FIRST_FILL - PAPER).

## Interpretation Notes

- Negative LCPS - PAPER runtime, checked_paths, stale_paths, and duplicate-failure deltas are improvements for that metric.
- LCPS cache activation requires positive LCPS search invocations and positive checked/stale prefix queries. Hits show that the query keys matched cached run prefixes.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log` or `*-<mode>-stale-<on|off>-threads-*.log`.
