# Checked Path Divergence Results

## Purpose

This experiment compares PAPER, LCPS, BATCH_LCPS, and optional BFS/DFS path selection under parallel TraceAbstraction.

Each unordered checked-path pair contributes normalized prefix-LCA divergence `1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.

## Summary

### Correctness

- Modes present: ADAPTIVE_ALWAYS.
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
- BatchLcpsEffectiveBatchDecisions: 0.

### Performance

- ADAPTIVE_ALWAYS - PAPER runtime_ms: not available.

### Work

- ADAPTIVE_ALWAYS - PAPER checked_paths: not available.
- ADAPTIVE_ALWAYS - PAPER stale_paths: not available.
- ADAPTIVE_ALWAYS - PAPER duplicate freshness failures: not available.
- ADAPTIVE_ALWAYS - PAPER search_failed: not available.

### Batch Quality

- Average candidate pool size across BATCH_LCPS rows: 12.00.
- Average selected batch size across BATCH_LCPS rows: 3.00.
- Total effective batch decisions: 0.

### Adaptive Trigger Comparison

- ADAPTIVE_ALWAYS: adaptive invocations/fallbacks 1/8, triggers dup/stale/failed/idle 0/0/0/0, candidates 12, effective batch decisions 0, candidates/invocation 12.00, generation/selection time 20ms/1ms, vs PAPER runtime [n/a], vs BATCH_LCPS runtime [n/a], vs PAPER checked [n/a], vs PAPER stale [n/a].

### Divergence

- ADAPTIVE_ALWAYS - PAPER avg divergence: not available.

### Interpretation

- Cache hits occurred only as instrumentation; effective priority decisions stayed at 0, so ordering did not change.
- BATCH_LCPS generated candidates but selected the same first-k set as the naive generator in this run.
- LCPS does not need higher divergence to be useful; interpret divergence together with runtime, checked_paths, stale_paths, duplicate freshness failures, and effective priority decisions.

## Benchmark Selection

- `hidden-inequality`: Harder loop-invariant testcase with substantially more checked paths than the baseline cases.

## Results

### hidden-inequality

| mode | adaptive_trigger_mode | repeat_index | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions | batch_lcps_candidate_generation_time_ms | batch_lcps_selection_time_ms | adaptive_batch_invocations | adaptive_batch_fallbacks | adaptive_triggered_by_duplicate | adaptive_triggered_by_stale | adaptive_triggered_by_search_failed | adaptive_triggered_by_idle_slot | adaptive_min_available_slots |
|---|---|---:|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| ADAPTIVE_ALWAYS | ALWAYS_BATCH | 0 | true | true | 4 | SAFE | 3752 | 12 | 6 | 5 | 63.4 | 0.9606060606060606 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 3 | 12 | 3 | 0 | 12.0 | 3.0 | 0 | 20 | 1 | 1 | 8 | 0 | 0 | 0 | 0 | 2 |

#### Interpretation

- PAPER vs ADAPTIVE_ALWAYS runtime_ms: not available for the selected modes.
- PAPER vs ADAPTIVE_ALWAYS checked_paths: not available for the selected modes.
- PAPER vs ADAPTIVE_ALWAYS stale_paths: not available for the selected modes.
- PAPER vs ADAPTIVE_ALWAYS duplicate freshness failures: not available for the selected modes.
- PAPER vs ADAPTIVE_ALWAYS avg divergence: not available for the selected modes.
- PAPER vs ADAPTIVE_ALWAYS lcps_effective_priority_decisions: not available for the selected modes.

## Interpretation Notes

- Negative LCPS - PAPER runtime, checked_paths, stale_paths, and duplicate-failure deltas are improvements for that metric.
- LCPS cache activation requires positive LCPS search invocations and positive checked/stale prefix queries. Hits show that the query keys matched cached run prefixes.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log` or `*-<mode>-stale-<on|off>-threads-*.log`.
