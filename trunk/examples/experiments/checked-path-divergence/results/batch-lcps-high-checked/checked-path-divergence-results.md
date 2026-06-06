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

- LCPS-priority rows with search invocations > 0: 6 / 6.
- LCPS-priority rows with checked prefix queries > 0: 6 / 6.
- LCPS-priority rows with stale prefix queries > 0: 6 / 6.
- LCPS-priority rows with checked prefix hits > 0: 6 / 6.
- LCPS-priority rows with stale prefix hits > 0: 6 / 6.
- Total LCPS effective priority decisions: 43.
- Total LCPS_FULL cache-suffix invocations/fallbacks: 0 / 0.
- BatchLcpsInvocations: 51.
- BatchLcpsCandidatesGenerated/Selected: 476 / 141.
- BatchLcpsCandidateGenerationFailures: 0.
- BatchLcpsEffectiveBatchDecisions: 45.

### Performance

- LCPS - PAPER runtime_ms: wins/losses/ties 2/4/0, mean 309.33, median 122.50.
- BATCH_LCPS - PAPER runtime_ms: wins/losses/ties 3/3/0, mean 412.33, median 293.00.
- BATCH_LCPS - LCPS runtime_ms: wins/losses/ties 4/2/0, mean 103.00, median -217.50.

### Work

- LCPS - PAPER checked_paths: wins/losses/ties 1/3/2, mean 0.67, median 0.50.
- BATCH_LCPS - PAPER checked_paths: wins/losses/ties 2/3/1, mean 1.33, median 0.50.
- BATCH_LCPS - LCPS checked_paths: wins/losses/ties 2/3/1, mean 0.67, median 0.50.
- LCPS - PAPER stale_paths: wins/losses/ties 1/3/2, mean 0.33, median 0.50.
- BATCH_LCPS - PAPER stale_paths: wins/losses/ties 3/2/1, mean 0.50, median -0.50.
- BATCH_LCPS - LCPS stale_paths: wins/losses/ties 4/2/0, mean 0.17, median -1.00.
- LCPS - PAPER duplicate freshness failures: wins/losses/ties 2/3/1, mean 0.33, median 0.50.
- BATCH_LCPS - PAPER duplicate freshness failures: wins/losses/ties 6/0/0, mean -6.67, median -6.50.
- BATCH_LCPS - LCPS duplicate freshness failures: wins/losses/ties 6/0/0, mean -7.00, median -6.50.
- LCPS - PAPER search_failed: wins/losses/ties 0/0/6, mean 0.00, median 0.00.
- BATCH_LCPS - PAPER search_failed: wins/losses/ties 0/0/6, mean 0.00, median 0.00.
- BATCH_LCPS - LCPS search_failed: wins/losses/ties 0/0/6, mean 0.00, median 0.00.

### Batch Quality

- Average candidate pool size across BATCH_LCPS rows: 11.17.
- Average selected batch size across BATCH_LCPS rows: 3.52.
- Total effective batch decisions: 45.
- BATCH_LCPS - PAPER checked_paths: wins/losses/ties 2/3/1, mean 1.33, median 0.50.
- BATCH_LCPS - PAPER stale_paths: wins/losses/ties 3/2/1, mean 0.50, median -0.50.

### Divergence

- LCPS - PAPER avg divergence: wins/losses/ties 3/3/0, mean 0.01, median 0.00.
- BATCH_LCPS - PAPER avg divergence: wins/losses/ties 2/4/0, mean -0.02, median 0.00.
- BATCH_LCPS - LCPS avg divergence: wins/losses/ties 4/2/0, mean -0.03, median -0.01.

### Interpretation

- Cache hits and effective priority decisions are both present; compare work/runtime deltas to see whether changed ordering helped.
- BATCH_LCPS changed at least one dispatched batch relative to naive first-k candidate dispatch.
- LCPS does not need higher divergence to be useful; interpret divergence together with runtime, checked_paths, stale_paths, duplicate freshness failures, and effective priority decisions.

## Benchmark Selection

- `trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/eureka_05.i_5.bpl.
- `k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb`: Discovered .bpl candidate from trunk/examples/programs/20170304-DifficultPathPrograms/resultKnown/invert_string.i_4.bpl.

## Results

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 3804 | 13 | 5 | 3 | 73.5188492063492 | 0.9425493487993487 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | SAFE | 5078 | 20 | 9 | 3 | 168.1035298035298 | 0.8847554200185779 | 13 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | SAFE | 7799 | 37 | 21 | 5 | 650.3603788368496 | 0.976517085340615 | 24 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 4 | SAFE | 3841 | 13 | 5 | 6 | 83.50992063492063 | 1.070640008140008 | 11 | 0 | 73 | 73 | 59 | 19 | 10 | 0 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | SAFE | 5050 | 21 | 10 | 4 | 188.1035298035298 | 0.8957310943025228 | 14 | 0 | 156 | 156 | 131 | 47 | 17 | 0 | 0 | 5 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | SAFE | 8007 | 36 | 19 | 5 | 584.4758200133203 | 0.9277393968465403 | 22 | 0 | 366 | 366 | 319 | 127 | 34 | 0 | 0 | 16 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 4 | SAFE | 3633 | 14 | 6 | 0 | 88.07698412698413 | 0.9678789464503751 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 13 | 52 | 13 | 0 | 5.777777777777778 | 1.4444444444444444 | 7 |
| BATCH_LCPS | true | true | 8 | SAFE | 4818 | 19 | 8 | 0 | 152.15615218115218 | 0.8898020595389017 | 12 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 5 | 18 | 72 | 18 | 0 | 14.4 | 3.6 | 5 |
| BATCH_LCPS | true | true | 16 | SAFE | 7780 | 36 | 18 | 0 | 543.8491272616276 | 0.8632525829549644 | 21 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 | 35 | 84 | 35 | 0 | 21.0 | 8.75 | 4 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: +37, 8t: -28, 16t: +208 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +1, 16t: -1 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +1, 16t: -2 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +3, 8t: +1, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0.128091, 8t: +0.0109757, 16t: -0.0487777 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +2, 8t: +5, 16t: +16 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 4t: -171, 8t: -260, 16t: -19 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +1, 8t: -1, 16t: -1 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +1, 8t: -1, 16t: -3 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -3, 8t: -3, 16t: -5 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0.0253296, 8t: +0.00504664, 16t: -0.113265 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +52, 8t: +72, 16t: +84 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +13, 8t: +18, 16t: +35 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +7, 8t: +5, 16t: +4 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 4t: -208, 8t: -232, 16t: -227 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 4t: +1, 8t: -2, 16t: +0 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 4t: +1, 8t: -2, 16t: -1 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 4t: -6, 8t: -4, 16t: -5 (BATCH_LCPS - LCPS).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions | batch_lcps_invocations | batch_lcps_available_slots_total | batch_lcps_candidates_generated | batch_lcps_candidates_selected | batch_lcps_candidate_generation_failures | batch_lcps_avg_candidate_pool_size | batch_lcps_avg_selected_batch_size | batch_lcps_effective_batch_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 4058 | 14 | 4 | 8 | 88.68333333333334 | 0.9745421245421246 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 8 | SAFE | 5774 | 21 | 10 | 10 | 209.9718253968254 | 0.9998658352229781 | 15 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| PAPER | true | true | 16 | SAFE | 8748 | 34 | 14 | 11 | 505.1660173160174 | 0.9004741841640239 | 19 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 4 | SAFE | 4053 | 14 | 4 | 7 | 88.35 | 0.9708791208791209 | 11 | 0 | 94 | 94 | 82 | 42 | 12 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 8 | SAFE | 7038 | 24 | 12 | 11 | 260.6384920634921 | 0.9443423625488844 | 17 | 0 | 224 | 224 | 186 | 92 | 23 | 0 | 0 | 5 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| LCPS | true | true | 16 | SAFE | 9128 | 35 | 15 | 9 | 538.2665945165945 | 0.9046497386833522 | 20 | 0 | 488 | 488 | 416 | 99 | 34 | 0 | 0 | 14 | 0 | 0 | 0 | 0 | 0 | 0.0 | 0.0 | 0 |
| BATCH_LCPS | true | true | 4 | SAFE | 5537 | 19 | 11 | 0 | 167.59285714285716 | 0.9800751879699249 | 16 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 14 | 18 | 72 | 18 | 0 | 5.142857142857143 | 1.2857142857142858 | 10 |
| BATCH_LCPS | true | true | 8 | SAFE | 6614 | 25 | 9 | 0 | 281.6472222222222 | 0.938824074074074 | 18 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 10 | 24 | 96 | 24 | 0 | 9.6 | 2.4 | 10 |
| BATCH_LCPS | true | true | 16 | SAFE | 9353 | 34 | 14 | 0 | 507.5926767676766 | 0.9047997803345394 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 9 | 33 | 100 | 33 | 0 | 11.11111111111111 | 3.6666666666666665 | 9 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: -5, 8t: +1264, 16t: +380 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +3, 16t: +1 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +2, 16t: +1 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: -1, 8t: +1, 16t: -2 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: -0.003663, 8t: -0.0555235, 16t: +0.00417555 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +1, 8t: +5, 16t: +14 (LCPS - PAPER).
- PAPER vs BATCH_LCPS runtime_ms: 4t: +1479, 8t: +840, 16t: +605 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS checked_paths: 4t: +5, 8t: +4, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS stale_paths: 4t: +7, 8t: -1, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS duplicate freshness failures: 4t: -8, 8t: -10, 16t: -11 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS avg divergence: 4t: +0.00553306, 8t: -0.0610418, 16t: +0.0043256 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_generated: 4t: +72, 8t: +96, 16t: +100 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_candidates_selected: 4t: +18, 8t: +24, 16t: +33 (BATCH_LCPS - PAPER).
- PAPER vs BATCH_LCPS batch_lcps_effective_batch_decisions: 4t: +10, 8t: +10, 16t: +9 (BATCH_LCPS - PAPER).
- BATCH_LCPS vs LCPS runtime_ms: 4t: +1484, 8t: -424, 16t: +225 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS checked_paths: 4t: +5, 8t: +1, 16t: -1 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS stale_paths: 4t: +7, 8t: -3, 16t: -1 (BATCH_LCPS - LCPS).
- BATCH_LCPS vs LCPS duplicate freshness failures: 4t: -7, 8t: -11, 16t: -9 (BATCH_LCPS - LCPS).

## Interpretation Notes

- Negative LCPS - PAPER runtime, checked_paths, stale_paths, and duplicate-failure deltas are improvements for that metric.
- LCPS cache activation requires positive LCPS search invocations and positive checked/stale prefix queries. Hits show that the query keys matched cached run prefixes.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log` or `*-<mode>-stale-<on|off>-threads-*.log`.
