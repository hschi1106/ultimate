# Checked Path Divergence Results

## Purpose

This experiment compares PAPER, LCPS, BATCH_LCPS, and optional BFS/DFS path selection under parallel TraceAbstraction.

Each unordered checked-path pair contributes normalized prefix-LCA divergence `1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.

## Summary

### Correctness

- Modes present: ADAPTIVE_FIRST_FILL.
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
- BatchLcpsInvocations: 1.
- BatchLcpsCandidatesGenerated/Selected: 12 / 3.
- BatchLcpsCandidateGenerationFailures: 0.
- BatchLcpsEffectiveBatchDecisions: 1.

### Performance

- ADAPTIVE_FIRST_FILL - PAPER runtime_ms: not available.

### Work

- ADAPTIVE_FIRST_FILL - PAPER checked_paths: not available.
- ADAPTIVE_FIRST_FILL - PAPER stale_paths: not available.
- ADAPTIVE_FIRST_FILL - PAPER duplicate freshness failures: not available.
- ADAPTIVE_FIRST_FILL - PAPER search_failed: not available.

### Batch Quality

- Average candidate pool size across BATCH_LCPS rows: 12.00.
- Average selected batch size across BATCH_LCPS rows: 3.00.
- Total effective batch decisions: 1.

### Adaptive Trigger Comparison

- ADAPTIVE_FIRST_FILL: adaptive invocations/fallbacks 1/2, triggers dup/stale/failed/idle/first/first-or-stale/threads-ge4-first 0/0/0/0/1/0/0, candidates 12, effective batch decisions 1, candidates/invocation 12.00, generation/selection time 21ms/1ms, vs PAPER runtime [n/a], vs BATCH_LCPS runtime [n/a], vs PAPER checked [n/a], vs PAPER stale [n/a].

### Divergence

- ADAPTIVE_FIRST_FILL - PAPER avg divergence: not available.

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
| ADAPTIVE_FIRST_FILL | FIRST_FILL_ONLY | 0 | true | true | 4 | SAFE | 3406 | 6 | 1 | 1 | 12.4 | 0.8266666666666667 | 3 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 1 | 21 | 1 | 1 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | true | 2 |

#### Interpretation

- PAPER vs ADAPTIVE_FIRST_FILL runtime_ms: not available for the selected modes.
- PAPER vs ADAPTIVE_FIRST_FILL checked_paths: not available for the selected modes.
- PAPER vs ADAPTIVE_FIRST_FILL stale_paths: not available for the selected modes.
- PAPER vs ADAPTIVE_FIRST_FILL duplicate freshness failures: not available for the selected modes.
- PAPER vs ADAPTIVE_FIRST_FILL avg divergence: not available for the selected modes.
- PAPER vs ADAPTIVE_FIRST_FILL lcps_effective_priority_decisions: not available for the selected modes.

## Interpretation Notes

- Negative LCPS - PAPER runtime, checked_paths, stale_paths, and duplicate-failure deltas are improvements for that metric.
- LCPS cache activation requires positive LCPS search invocations and positive checked/stale prefix queries. Hits show that the query keys matched cached run prefixes.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log` or `*-<mode>-stale-<on|off>-threads-*.log`.
