# Checked Path Divergence Results

## Purpose

This experiment compares PAPER, LCPS, BATCH_LCPS, and optional BFS/DFS path selection under parallel TraceAbstraction.

Each unordered checked-path pair contributes normalized prefix-LCA divergence `1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.

## Summary

### Correctness

- Modes present: BATCH_LCPS.
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
- BatchLcpsInvocations: 9.
- BatchLcpsCandidatesGenerated/Selected: 44 / 11.
- BatchLcpsCandidateGenerationFailures: 0.
- BatchLcpsEffectiveBatchDecisions: 3.

### Performance

- BATCH_LCPS - PAPER runtime_ms: not available.

### Work

- BATCH_LCPS - PAPER checked_paths: not available.
- BATCH_LCPS - PAPER stale_paths: not available.
- BATCH_LCPS - PAPER duplicate freshness failures: not available.
- BATCH_LCPS - PAPER search_failed: not available.

### Batch Quality

- Average candidate pool size across BATCH_LCPS rows: 4.89.
- Average selected batch size across BATCH_LCPS rows: 1.22.
- Total effective batch decisions: 3.

### Divergence

- BATCH_LCPS - PAPER avg divergence: not available.

### Interpretation

- Cache hits occurred only as instrumentation; effective priority decisions stayed at 0, so ordering did not change.
- BATCH_LCPS changed at least one dispatched batch relative to naive first-k candidate dispatch.
- LCPS does not need higher divergence to be useful; interpret divergence together with runtime, checked_paths, stale_paths, duplicate freshness failures, and effective priority decisions.

## Benchmark Selection

- `hidden-inequality`: Harder loop-invariant testcase with substantially more checked paths than the baseline cases.

## Results

### hidden-inequality

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| BATCH_LCPS | true | true | 4 | SAFE | 3733 | 12 | 6 | 0 | 63.4 | 0.9606060606060606 | 9 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 11 | 44 | 11 | 0 | 4.888888888888889 | 1.2222222222222223 | 3 |

#### Interpretation

- PAPER vs BATCH_LCPS runtime_ms: not available for the selected modes.
- PAPER vs BATCH_LCPS checked_paths: not available for the selected modes.
- PAPER vs BATCH_LCPS stale_paths: not available for the selected modes.
- PAPER vs BATCH_LCPS duplicate freshness failures: not available for the selected modes.
- PAPER vs BATCH_LCPS avg divergence: not available for the selected modes.
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: not available for the selected modes.
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: not available for the selected modes.
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: not available for the selected modes.
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: not available for the selected modes.

## Interpretation Notes

- Negative LCPS - PAPER runtime, checked_paths, stale_paths, and duplicate-failure deltas are improvements for that metric.
- LCPS cache activation requires positive LCPS search invocations and positive checked/stale prefix queries. Hits show that the query keys matched cached run prefixes.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log` or `*-<mode>-stale-<on|off>-threads-*.log`.
