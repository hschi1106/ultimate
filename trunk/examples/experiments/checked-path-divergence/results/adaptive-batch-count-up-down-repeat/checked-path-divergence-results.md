# Checked Path Divergence Results

## Purpose

This experiment compares PAPER, LCPS, BATCH_LCPS, and optional BFS/DFS path selection under parallel TraceAbstraction.

Each unordered checked-path pair contributes normalized prefix-LCA divergence `1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.

## Summary

### Correctness

- Modes present: PAPER, BATCH_LCPS, ADAPTIVE_DUPLICATE, ADAPTIVE_STALE, ADAPTIVE_SEARCH_FAILED, ADAPTIVE_IDLE.
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
- BatchLcpsInvocations: 49.
- BatchLcpsCandidatesGenerated/Selected: 496 / 159.
- BatchLcpsCandidateGenerationFailures: 0.
- BatchLcpsEffectiveBatchDecisions: 49.

### Performance

- BATCH_LCPS - PAPER runtime_ms: wins/losses/ties 13/2/0, mean -39922.27, median -359.00.
- ADAPTIVE_DUPLICATE - PAPER runtime_ms: wins/losses/ties 7/8/0, mean -93.33, median 5.00.
- ADAPTIVE_STALE - PAPER runtime_ms: wins/losses/ties 9/6/0, mean -67.73, median -5.00.
- ADAPTIVE_SEARCH_FAILED - PAPER runtime_ms: wins/losses/ties 7/8/0, mean -98.60, median 10.00.
- ADAPTIVE_IDLE - PAPER runtime_ms: wins/losses/ties 8/7/0, mean -166.33, median -15.00.

### Work

- BATCH_LCPS - PAPER checked_paths: wins/losses/ties 12/1/2, mean -2.73, median -1.00.
- ADAPTIVE_DUPLICATE - PAPER checked_paths: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_STALE - PAPER checked_paths: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_SEARCH_FAILED - PAPER checked_paths: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_IDLE - PAPER checked_paths: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- BATCH_LCPS - PAPER stale_paths: wins/losses/ties 12/1/2, mean -2.40, median -1.00.
- ADAPTIVE_DUPLICATE - PAPER stale_paths: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_STALE - PAPER stale_paths: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_SEARCH_FAILED - PAPER stale_paths: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_IDLE - PAPER stale_paths: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- BATCH_LCPS - PAPER duplicate freshness failures: wins/losses/ties 15/0/0, mean -4.33, median -4.00.
- ADAPTIVE_DUPLICATE - PAPER duplicate freshness failures: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_STALE - PAPER duplicate freshness failures: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_SEARCH_FAILED - PAPER duplicate freshness failures: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_IDLE - PAPER duplicate freshness failures: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- BATCH_LCPS - PAPER search_failed: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_DUPLICATE - PAPER search_failed: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_STALE - PAPER search_failed: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_SEARCH_FAILED - PAPER search_failed: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_IDLE - PAPER search_failed: wins/losses/ties 0/0/15, mean 0.00, median 0.00.

### Batch Quality

- Average candidate pool size across BATCH_LCPS rows: 2.02.
- Average selected batch size across BATCH_LCPS rows: 0.63.
- Total effective batch decisions: 49.
- BATCH_LCPS - PAPER checked_paths: wins/losses/ties 12/1/2, mean -2.73, median -1.00.
- BATCH_LCPS - PAPER stale_paths: wins/losses/ties 12/1/2, mean -2.40, median -1.00.

### Adaptive Trigger Comparison

- ADAPTIVE_DUPLICATE: adaptive invocations/fallbacks 0/90, triggers dup/stale/failed/idle 0/0/0/0, candidates 0, effective batch decisions 0, candidates/invocation 0.00, generation/selection time 0ms/0ms, vs PAPER runtime [wins/losses/ties 7/8/0, mean -93.33, median 5.00], vs BATCH_LCPS runtime [wins/losses/ties 2/13/0, mean 39828.93, median 360.00], vs PAPER checked [wins/losses/ties 0/0/15, mean 0.00, median 0.00], vs PAPER stale [wins/losses/ties 0/0/15, mean 0.00, median 0.00].
- ADAPTIVE_STALE: adaptive invocations/fallbacks 0/90, triggers dup/stale/failed/idle 0/0/0/0, candidates 0, effective batch decisions 0, candidates/invocation 0.00, generation/selection time 0ms/0ms, vs PAPER runtime [wins/losses/ties 9/6/0, mean -67.73, median -5.00], vs BATCH_LCPS runtime [wins/losses/ties 2/13/0, mean 39854.53, median 319.00], vs PAPER checked [wins/losses/ties 0/0/15, mean 0.00, median 0.00], vs PAPER stale [wins/losses/ties 0/0/15, mean 0.00, median 0.00].
- ADAPTIVE_SEARCH_FAILED: adaptive invocations/fallbacks 0/90, triggers dup/stale/failed/idle 0/0/0/0, candidates 0, effective batch decisions 0, candidates/invocation 0.00, generation/selection time 0ms/0ms, vs PAPER runtime [wins/losses/ties 7/8/0, mean -98.60, median 10.00], vs BATCH_LCPS runtime [wins/losses/ties 2/13/0, mean 39823.67, median 396.00], vs PAPER checked [wins/losses/ties 0/0/15, mean 0.00, median 0.00], vs PAPER stale [wins/losses/ties 0/0/15, mean 0.00, median 0.00].
- ADAPTIVE_IDLE: adaptive invocations/fallbacks 0/90, triggers dup/stale/failed/idle 0/0/0/0, candidates 0, effective batch decisions 0, candidates/invocation 0.00, generation/selection time 0ms/0ms, vs PAPER runtime [wins/losses/ties 8/7/0, mean -166.33, median -15.00], vs BATCH_LCPS runtime [wins/losses/ties 2/13/0, mean 39755.93, median 346.00], vs PAPER checked [wins/losses/ties 0/0/15, mean 0.00, median 0.00], vs PAPER stale [wins/losses/ties 0/0/15, mean 0.00, median 0.00].

### Divergence

- BATCH_LCPS - PAPER avg divergence: wins/losses/ties 12/1/2, mean -0.07, median -0.06.
- ADAPTIVE_DUPLICATE - PAPER avg divergence: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_STALE - PAPER avg divergence: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_SEARCH_FAILED - PAPER avg divergence: wins/losses/ties 0/0/15, mean 0.00, median 0.00.
- ADAPTIVE_IDLE - PAPER avg divergence: wins/losses/ties 0/0/15, mean 0.00, median 0.00.

### Interpretation

- Cache hits occurred only as instrumentation; effective priority decisions stayed at 0, so ordering did not change.
- BATCH_LCPS changed at least one dispatched batch relative to naive first-k candidate dispatch.
- LCPS does not need higher divergence to be useful; interpret divergence together with runtime, checked_paths, stale_paths, duplicate freshness failures, and effective priority decisions.

## Benchmark Selection

- `k-examples-programs-20170304-DifficultPathPrograms-resultKnown-count_up_down.i_3-aa4a8d5f`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/count_up_down.i_3.bpl.

## Results

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-count_up_down.i_3-aa4a8d5f

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | n/a | 0 | true | true | 4 | SAFE | 122001 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 8 | SAFE | 5102 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 0 | true | true | 16 | SAFE | 7564 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 1 | true | true | 4 | SAFE | 124028 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 1 | true | true | 8 | SAFE | 5172 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 1 | true | true | 16 | SAFE | 7559 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 2 | true | true | 4 | SAFE | 122067 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 2 | true | true | 8 | SAFE | 5205 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 2 | true | true | 16 | SAFE | 7590 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 3 | true | true | 4 | SAFE | 121874 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 3 | true | true | 8 | SAFE | 5150 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 3 | true | true | 16 | SAFE | 7521 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 4 | true | true | 4 | SAFE | 123229 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 4 | true | true | 8 | SAFE | 5140 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| PAPER | n/a | 4 | true | true | 16 | SAFE | 7586 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 4 | SAFE | 3025 | 6 | 1 | 0 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 5 | 20 | 5 | 0 | 6.666666666666667 | 1.6666666666666667 | 3 | 25 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 8 | SAFE | 4846 | 10 | 1 | 0 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 9 | 36 | 9 | 0 | 12.0 | 3.0 | 3 | 55 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 0 | true | true | 16 | SAFE | 7243 | 18 | 1 | 0 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 17 | 40 | 17 | 0 | 13.333333333333334 | 5.666666666666667 | 3 | 77 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 1 | true | true | 4 | SAFE | 3047 | 6 | 1 | 0 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 5 | 20 | 5 | 0 | 6.666666666666667 | 1.6666666666666667 | 3 | 26 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 1 | true | true | 8 | SAFE | 4860 | 10 | 1 | 0 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 9 | 36 | 9 | 0 | 12.0 | 3.0 | 3 | 57 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 1 | true | true | 16 | SAFE | 7056 | 19 | 2 | 0 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 18 | 44 | 18 | 0 | 11.0 | 4.5 | 4 | 87 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 2 | true | true | 4 | SAFE | 3281 | 6 | 1 | 0 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 5 | 20 | 5 | 0 | 6.666666666666667 | 1.6666666666666667 | 3 | 27 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 2 | true | true | 8 | SAFE | 4846 | 10 | 1 | 0 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 9 | 36 | 9 | 0 | 12.0 | 3.0 | 3 | 53 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 2 | true | true | 16 | SAFE | 7008 | 18 | 1 | 0 | 72.82389081506727 | 0.475973142582139 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 17 | 40 | 17 | 0 | 13.333333333333334 | 5.666666666666667 | 3 | 69 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 3 | true | true | 4 | SAFE | 3046 | 6 | 1 | 0 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 5 | 20 | 5 | 0 | 6.666666666666667 | 1.6666666666666667 | 3 | 25 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 3 | true | true | 8 | SAFE | 4857 | 10 | 1 | 0 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 9 | 36 | 9 | 0 | 12.0 | 3.0 | 3 | 60 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 3 | true | true | 16 | SAFE | 8746 | 20 | 3 | 0 | 109.82389081506727 | 0.5780204779740383 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 5 | 19 | 48 | 19 | 0 | 9.6 | 3.8 | 5 | 70 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 4 | true | true | 4 | SAFE | 3037 | 6 | 1 | 0 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 5 | 20 | 5 | 0 | 6.666666666666667 | 1.6666666666666667 | 3 | 29 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 4 | true | true | 8 | SAFE | 4878 | 10 | 1 | 0 | 29.579365079365083 | 0.6573192239858907 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 3 | 9 | 36 | 9 | 0 | 12.0 | 3.0 | 3 | 58 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| BATCH_LCPS | n/a | 4 | true | true | 16 | SAFE | 8178 | 19 | 2 | 0 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 18 | 44 | 18 | 0 | 11.0 | 4.5 | 4 | 76 | 5 | 0 | 0 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 4 | SAFE | 121848 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 8 | SAFE | 5066 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 0 | true | true | 16 | SAFE | 7603 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 1 | true | true | 4 | SAFE | 123324 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 1 | true | true | 8 | SAFE | 5136 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 1 | true | true | 16 | SAFE | 7588 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 2 | true | true | 4 | SAFE | 123005 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 2 | true | true | 8 | SAFE | 5129 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 2 | true | true | 16 | SAFE | 7581 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 3 | true | true | 4 | SAFE | 122035 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 3 | true | true | 8 | SAFE | 5155 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 3 | true | true | 16 | SAFE | 7587 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 4 | true | true | 4 | SAFE | 121580 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 4 | true | true | 8 | SAFE | 5146 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_DUPLICATE | DUPLICATE_ONLY | 4 | true | true | 16 | SAFE | 7605 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 4 | SAFE | 121870 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 8 | SAFE | 5160 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 0 | true | true | 16 | SAFE | 7562 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 1 | true | true | 4 | SAFE | 123973 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 1 | true | true | 8 | SAFE | 5167 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 1 | true | true | 16 | SAFE | 7600 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 2 | true | true | 4 | SAFE | 121676 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 2 | true | true | 8 | SAFE | 5144 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 2 | true | true | 16 | SAFE | 7585 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 3 | true | true | 4 | SAFE | 122525 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 3 | true | true | 8 | SAFE | 5162 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 3 | true | true | 16 | SAFE | 7590 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 4 | true | true | 4 | SAFE | 122020 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 4 | true | true | 8 | SAFE | 5177 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_STALE | STALE_ONLY | 4 | true | true | 16 | SAFE | 7561 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 4 | SAFE | 122375 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 8 | SAFE | 5096 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 0 | true | true | 16 | SAFE | 7639 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 1 | true | true | 4 | SAFE | 121862 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 1 | true | true | 8 | SAFE | 5183 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 1 | true | true | 16 | SAFE | 7809 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 2 | true | true | 4 | SAFE | 122083 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 2 | true | true | 8 | SAFE | 5119 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 2 | true | true | 16 | SAFE | 7565 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 3 | true | true | 4 | SAFE | 121835 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 3 | true | true | 8 | SAFE | 5160 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 3 | true | true | 16 | SAFE | 7817 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 4 | true | true | 4 | SAFE | 122918 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 4 | true | true | 8 | SAFE | 5092 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_SEARCH_FAILED | SEARCH_FAILED_ONLY | 4 | true | true | 16 | SAFE | 7756 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 4 | SAFE | 121838 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 8 | SAFE | 5123 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 0 | true | true | 16 | SAFE | 7589 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 1 | true | true | 4 | SAFE | 121977 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 1 | true | true | 8 | SAFE | 5100 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 1 | true | true | 16 | SAFE | 7602 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 2 | true | true | 4 | SAFE | 123263 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 2 | true | true | 8 | SAFE | 5190 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 2 | true | true | 16 | SAFE | 7595 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 3 | true | true | 4 | SAFE | 121547 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 3 | true | true | 8 | SAFE | 5168 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 3 | true | true | 16 | SAFE | 7564 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 4 | true | true | 4 | SAFE | 122084 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 10 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 4 | true | true | 8 | SAFE | 5092 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |
| ADAPTIVE_IDLE | IDLE_SLOT_ONLY | 4 | true | true | 16 | SAFE | 7561 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 | 2 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: 4t: -118976, 4t: -120981, 4t: -118786, 4t: -118828, 4t: -120192, 8t: -256, 8t: -312, 8t: -359, 8t: -293, 8t: -262, 16t: -321, 16t: -503, 16t: -582, 16t: +1225, 16t: +592 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: -7, 4t: -7, 4t: -7, 4t: -7, 4t: -7, 8t: -1, 8t: -1, 8t: -1, 8t: -1, 8t: -1, 16t: -1, 16t: +0, 16t: -1, 16t: +1, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: -6, 4t: -6, 4t: -6, 4t: -6, 4t: -6, 8t: -1, 8t: -1, 8t: -1, 8t: -1, 8t: -1, 16t: -1, 16t: +0, 16t: -1, 16t: +1, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -5, 4t: -5, 4t: -5, 4t: -5, 4t: -5, 8t: -4, 8t: -4, 8t: -4, 8t: -4, 8t: -4, 16t: -4, 16t: -4, 16t: -4, 16t: -4, 16t: -4 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: -0.14, 4t: -0.14, 4t: -0.14, 4t: -0.14, 4t: -0.14, 8t: -0.0623056, 8t: -0.0623056, 8t: -0.0623056, 8t: -0.0623056, 8t: -0.0623056, 16t: -0.0551607, 16t: +0, 16t: -0.0551607, 16t: +0.0468866, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +20, 4t: +20, 4t: +20, 4t: +20, 4t: +20, 8t: +36, 8t: +36, 8t: +36, 8t: +36, 8t: +36, 16t: +40, 16t: +44, 16t: +40, 16t: +48, 16t: +44 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +5, 4t: +5, 4t: +5, 4t: +5, 4t: +5, 8t: +9, 8t: +9, 8t: +9, 8t: +9, 8t: +9, 16t: +17, 16t: +18, 16t: +17, 16t: +19, 16t: +18 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +3, 4t: +3, 4t: +3, 4t: +3, 4t: +3, 8t: +3, 8t: +3, 8t: +3, 8t: +3, 8t: +3, 16t: +3, 16t: +4, 16t: +3, 16t: +5, 16t: +4 (BATCH_LCPS - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE runtime_ms: 4t: -153, 4t: -704, 4t: +938, 4t: +161, 4t: -1649, 8t: -36, 8t: -36, 8t: -76, 8t: +5, 8t: +6, 16t: +39, 16t: +29, 16t: -9, 16t: +66, 16t: +19 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE checked_paths: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE stale_paths: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE duplicate freshness failures: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE avg divergence: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_DUPLICATE lcps_effective_priority_decisions: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_DUPLICATE - PAPER).
- PAPER vs ADAPTIVE_STALE runtime_ms: 4t: -131, 4t: -55, 4t: -391, 4t: +651, 4t: -1209, 8t: +58, 8t: -5, 8t: -61, 8t: +12, 8t: +37, 16t: -2, 16t: +41, 16t: -5, 16t: +69, 16t: -25 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE checked_paths: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE stale_paths: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE duplicate freshness failures: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE avg divergence: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_STALE lcps_effective_priority_decisions: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_STALE - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED runtime_ms: 4t: +374, 4t: -2166, 4t: +16, 4t: -39, 4t: -311, 8t: -6, 8t: +11, 8t: -86, 8t: +10, 8t: -48, 16t: +75, 16t: +250, 16t: -25, 16t: +296, 16t: +170 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED checked_paths: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED stale_paths: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED duplicate freshness failures: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED avg divergence: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_SEARCH_FAILED lcps_effective_priority_decisions: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_SEARCH_FAILED - PAPER).
- PAPER vs ADAPTIVE_IDLE runtime_ms: 4t: -163, 4t: -2051, 4t: +1196, 4t: -327, 4t: -1145, 8t: +21, 8t: -72, 8t: -15, 8t: +18, 8t: -48, 16t: +25, 16t: +43, 16t: +5, 16t: +43, 16t: -25 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE checked_paths: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE stale_paths: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE duplicate freshness failures: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE avg divergence: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).
- PAPER vs ADAPTIVE_IDLE lcps_effective_priority_decisions: 4t: +0, 4t: +0, 4t: +0, 4t: +0, 4t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 8t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0, 16t: +0 (ADAPTIVE_IDLE - PAPER).

## Interpretation Notes

- Negative LCPS - PAPER runtime, checked_paths, stale_paths, and duplicate-failure deltas are improvements for that metric.
- LCPS cache activation requires positive LCPS search invocations and positive checked/stale prefix queries. Hits show that the query keys matched cached run prefixes.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log` or `*-<mode>-stale-<on|off>-threads-*.log`.
