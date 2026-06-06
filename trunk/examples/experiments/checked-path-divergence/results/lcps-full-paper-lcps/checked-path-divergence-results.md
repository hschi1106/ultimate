# Checked Path Divergence Results

## Purpose

This experiment compares PAPER, LCPS, and optional BFS/DFS path selection under parallel TraceAbstraction.

Each unordered checked-path pair contributes normalized prefix-LCA divergence `1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.

## Summary

### Correctness

- Modes present: PAPER, LCPS, LCPS_FULL.
- Result mismatch groups across modes: 1.
- ERROR rows: 0; TIMEOUT rows: 1.
- BFS/DFS were not part of this run.

### Activation

- Cache-guided rows with search invocations > 0: 59 / 60.
- Cache-guided rows with checked prefix queries > 0: 59 / 60.
- Cache-guided rows with stale prefix queries > 0: 59 / 60.
- Cache-guided rows with checked prefix hits > 0: 59 / 60.
- Cache-guided rows with stale prefix hits > 0: 28 / 60.
- Total LCPS effective priority decisions: 110.
- Total LCPS_FULL cache-suffix invocations/fallbacks: 9 / 1236.

### Performance

- LCPS - PAPER runtime_ms: wins/losses/ties 16/14/0, mean -54.20, median -10.50.
- LCPS_FULL - PAPER runtime_ms: wins/losses/ties 13/17/0, mean 38867.27, median 23.50.
- LCPS_FULL - LCPS runtime_ms: wins/losses/ties 12/17/1, mean 38921.47, median 9.50.

### Work

- LCPS - PAPER checked_paths: wins/losses/ties 4/0/26, mean -0.20, median 0.00.
- LCPS_FULL - PAPER checked_paths: wins/losses/ties 4/2/24, mean -0.67, median 0.00.
- LCPS_FULL - LCPS checked_paths: wins/losses/ties 2/3/25, mean -0.47, median 0.00.
- LCPS - PAPER stale_paths: wins/losses/ties 3/1/26, mean -0.10, median 0.00.
- LCPS_FULL - PAPER stale_paths: wins/losses/ties 2/2/26, mean -0.07, median 0.00.
- LCPS_FULL - LCPS stale_paths: wins/losses/ties 2/4/24, mean 0.03, median 0.00.
- LCPS - PAPER duplicate freshness failures: wins/losses/ties 5/3/22, mean -0.10, median 0.00.
- LCPS_FULL - PAPER duplicate freshness failures: wins/losses/ties 5/4/21, mean -0.07, median 0.00.
- LCPS_FULL - LCPS duplicate freshness failures: wins/losses/ties 3/3/24, mean 0.03, median 0.00.
- LCPS - PAPER search_failed: wins/losses/ties 0/0/30, mean 0.00, median 0.00.
- LCPS_FULL - PAPER search_failed: wins/losses/ties 0/0/30, mean 0.00, median 0.00.
- LCPS_FULL - LCPS search_failed: wins/losses/ties 0/0/30, mean 0.00, median 0.00.

### Divergence

- LCPS - PAPER avg divergence: wins/losses/ties 4/3/23, mean -0.01, median 0.00.
- LCPS_FULL - PAPER avg divergence: wins/losses/ties 6/4/20, mean -0.02, median 0.00.
- LCPS_FULL - LCPS avg divergence: wins/losses/ties 4/4/22, mean -0.01, median 0.00.

### Interpretation

- Cache hits and effective priority decisions are both present; compare work/runtime deltas to see whether changed ordering helped.
- LCPS_FULL continued cache-guided suffix exploration after active divergence; fallback count shows how often it reached uncovered history.
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

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 4009 | 13 | 7 | 5 | 87.50992063492063 | 1.1219220594220594 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 8 | SAFE | 5647 | 21 | 9 | 4 | 195.05537518037517 | 0.9288351199065484 | 14 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 16 | SAFE | 7769 | 36 | 20 | 7 | 613.9998584748587 | 0.9746029499600931 | 23 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | true | 4 | SAFE | 3775 | 13 | 5 | 4 | 74.8670634920635 | 0.9598341473341474 | 10 | 0 | 62 | 62 | 47 | 17 | 9 | 0 | 0 | 1 |
| LCPS | true | true | 8 | SAFE | 4866 | 20 | 9 | 3 | 168.1035298035298 | 0.8847554200185779 | 13 | 0 | 141 | 141 | 115 | 34 | 16 | 0 | 0 | 6 |
| LCPS | true | true | 16 | SAFE | 7785 | 33 | 18 | 5 | 512.4898268398272 | 0.9706246720451273 | 21 | 0 | 335 | 335 | 284 | 68 | 32 | 0 | 0 | 14 |
| LCPS_FULL | true | true | 4 | SAFE | 4054 | 15 | 7 | 7 | 102.50992063492063 | 0.9762849584278156 | 12 | 0 | 103 | 103 | 68 | 22 | 11 | 3 | 11 | 3 |
| LCPS_FULL | true | true | 8 | SAFE | 4582 | 17 | 6 | 1 | 114.10352980352978 | 0.838996542673013 | 10 | 0 | 141 | 141 | 92 | 42 | 14 | 0 | 14 | 6 |
| LCPS_FULL | true | true | 16 | SAFE | 8019 | 36 | 20 | 5 | 581.0123584748587 | 0.9222418388489821 | 22 | 0 | 428 | 428 | 318 | 128 | 34 | 0 | 35 | 16 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: -234, 8t: -781, 16t: +16 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: -1, 16t: -3 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: -2, 8t: +0, 16t: -2 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: -1, 8t: -1, 16t: -2 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: -0.162088, 8t: -0.0440797, 16t: -0.00397828 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +1, 8t: +6, 16t: +14 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 4t: +45, 8t: -1065, 16t: +250 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 4t: +2, 8t: -4, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 4t: +0, 8t: -3, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 4t: +2, 8t: -3, 16t: -2 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 4t: -0.145637, 8t: -0.0898386, 16t: -0.0523611 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 4t: +3, 8t: +6, 16t: +16 (LCPS_FULL - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 4t: +279, 8t: -284, 16t: +234 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 4t: +2, 8t: -3, 16t: +3 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 4t: +2, 8t: -3, 16t: +2 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 4t: +2, 8t: +0, 16t: +2 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 4t: +3, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 4049 | 14 | 3 | 9 | 88.93333333333334 | 0.9772893772893774 | 11 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 8 | SAFE | 7085 | 25 | 13 | 10 | 284.19404761904764 | 0.9473134920634921 | 18 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 16 | SAFE | 8959 | 36 | 16 | 8 | 563.1577700077698 | 0.8939012222345553 | 21 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | true | 4 | SAFE | 3845 | 13 | 5 | 7 | 75.68333333333334 | 0.9702991452991453 | 10 | 0 | 68 | 68 | 57 | 36 | 11 | 0 | 0 | 1 |
| LCPS | true | true | 8 | SAFE | 7047 | 25 | 13 | 12 | 284.6384920634921 | 0.9487949735449737 | 18 | 0 | 227 | 227 | 187 | 96 | 24 | 0 | 0 | 5 |
| LCPS | true | true | 16 | SAFE | 8727 | 35 | 15 | 9 | 537.6205627705629 | 0.9035639710429628 | 20 | 0 | 486 | 486 | 417 | 108 | 34 | 0 | 0 | 13 |
| LCPS_FULL | true | true | 4 | SAFE | 4114 | 13 | 3 | 7 | 75.68333333333334 | 0.9702991452991453 | 10 | 0 | 103 | 103 | 72 | 41 | 11 | 3 | 11 | 2 |
| LCPS_FULL | true | true | 8 | SAFE | 6656 | 25 | 13 | 12 | 284.9940476190476 | 0.9499801587301586 | 18 | 0 | 260 | 260 | 186 | 88 | 23 | 2 | 25 | 6 |
| LCPS_FULL | true | true | 16 | SAFE | 8937 | 35 | 15 | 11 | 538.7660173160174 | 0.9054891047328023 | 20 | 0 | 552 | 552 | 426 | 109 | 34 | 1 | 57 | 13 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: -204, 8t: -38, 16t: -232 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: -1, 8t: +0, 16t: -1 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +2, 8t: +0, 16t: -1 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: -2, 8t: +2, 16t: +1 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: -0.00699023, 8t: +0.00148148, 16t: +0.00966275 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +1, 8t: +5, 16t: +13 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 4t: +65, 8t: -429, 16t: -22 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 4t: -1, 8t: +0, 16t: -1 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 4t: +0, 8t: +0, 16t: -1 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 4t: -2, 8t: +2, 16t: +3 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 4t: -0.00699023, 8t: +0.00266667, 16t: +0.0115879 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 4t: +2, 8t: +6, 16t: +13 (LCPS_FULL - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 4t: +269, 8t: -391, 16t: +210 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 4t: -2, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 4t: +1, 8t: +1, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 4t: +3, 8t: +2, 16t: +1 (LCPS_FULL - LCPS).

### examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | UNKNOWN | 4279 | 11 | 5 | 6 | 52.4 | 0.9527272727272728 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 8 | UNKNOWN | 6138 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 16 | UNKNOWN | 4788 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | true | 4 | UNKNOWN | 4294 | 11 | 5 | 5 | 52.4 | 0.9527272727272728 | 7 | 0 | 58 | 58 | 45 | 8 | 7 | 0 | 0 | 1 |
| LCPS | true | true | 8 | UNKNOWN | 6129 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 227 | 227 | 189 | 25 | 14 | 0 | 0 | 1 |
| LCPS | true | true | 16 | UNKNOWN | 4982 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 463 | 463 | 340 | 0 | 15 | 0 | 0 | 1 |
| LCPS_FULL | true | true | 4 | UNKNOWN | 4301 | 12 | 6 | 6 | 63.4 | 0.9606060606060606 | 8 | 0 | 92 | 92 | 63 | 21 | 8 | 0 | 10 | 1 |
| LCPS_FULL | true | true | 8 | UNKNOWN | 6120 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 252 | 252 | 189 | 25 | 14 | 0 | 34 | 1 |
| LCPS_FULL | true | true | 16 | UNKNOWN | 4972 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 493 | 493 | 340 | 0 | 15 | 0 | 119 | 1 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: +15, 8t: -9, 16t: +194 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: -1, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +1, 8t: +1, 16t: +1 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 4t: +22, 8t: -18, 16t: +184 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 4t: +1, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 4t: +1, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 4t: +0.00787879, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 4t: +1, 8t: +1, 16t: +1 (LCPS_FULL - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 4t: +7, 8t: -9, 16t: -10 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 4t: +1, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 4t: +1, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-diamond2.i_4-22ecac6d

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 4089 | 9 | 2 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 8 | SAFE | 5695 | 14 | 5 | 6 | 74.72222222222223 | 0.8211233211233212 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 16 | SAFE | 8740 | 22 | 4 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | true | 4 | SAFE | 4094 | 9 | 2 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 59 | 59 | 44 | 4 | 7 | 0 | 0 | 1 |
| LCPS | true | true | 8 | SAFE | 5264 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 220 | 220 | 181 | 19 | 13 | 0 | 0 | 1 |
| LCPS | true | true | 16 | SAFE | 8721 | 22 | 4 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 711 | 711 | 580 | 20 | 21 | 0 | 0 | 1 |
| LCPS_FULL | true | true | 4 | SAFE | 4080 | 9 | 3 | 5 | 33.4 | 0.9277777777777777 | 6 | 0 | 71 | 71 | 44 | 4 | 7 | 0 | 9 | 1 |
| LCPS_FULL | true | true | 8 | SAFE | 5276 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 245 | 245 | 184 | 20 | 13 | 0 | 33 | 1 |
| LCPS_FULL | true | true | 16 | SAFE | 8710 | 22 | 4 | 6 | 149.91479990597637 | 0.6489818177747895 | 7 | 0 | 737 | 737 | 567 | 20 | 21 | 0 | 125 | 1 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: +5, 8t: -431, 16t: -19 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +0, 8t: +1, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: +0.00941915, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +1, 8t: +1, 16t: +1 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 4t: -9, 8t: -419, 16t: -30 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 4t: +1, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 4t: +0, 8t: +1, 16t: -1 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 4t: +0, 8t: +0.00941915, 16t: -0.00393546 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 4t: +1, 8t: +1, 16t: +1 (LCPS_FULL - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 4t: -14, 8t: +12, 16t: -11 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 4t: +1, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-count_up_down.i_3-aa4a8d5f

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 123187 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 8 | SAFE | 5090 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 16 | SAFE | 7694 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | true | 4 | SAFE | 122717 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 58 | 58 | 44 | 8 | 7 | 0 | 0 | 1 |
| LCPS | true | true | 8 | SAFE | 5133 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 162 | 162 | 128 | 3 | 10 | 0 | 0 | 1 |
| LCPS | true | true | 16 | SAFE | 7560 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 594 | 594 | 468 | 3 | 18 | 0 | 0 | 1 |
| LCPS_FULL | true | true | 4 | SAFE | 122198 | 13 | 7 | 5 | 75.4 | 0.9666666666666668 | 10 | 0 | 71 | 71 | 45 | 8 | 7 | 0 | 9 | 1 |
| LCPS_FULL | true | true | 8 | SAFE | 5132 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 180 | 180 | 128 | 3 | 10 | 0 | 30 | 1 |
| LCPS_FULL | true | true | 16 | SAFE | 7560 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 628 | 628 | 468 | 3 | 18 | 0 | 122 | 1 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: -470, 8t: +43, 16t: -134 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +1, 8t: +1, 16t: +1 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 4t: -989, 8t: +42, 16t: -134 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 4t: +1, 8t: +1, 16t: +1 (LCPS_FULL - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 4t: -519, 8t: -1, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-gauss_sum.i_3-f2583875

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 3454 | 7 | 1 | 3 | 18.4 | 0.8761904761904761 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 8 | SAFE | 5169 | 11 | 1 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 16 | SAFE | 8569 | 19 | 1 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | true | 4 | SAFE | 3489 | 7 | 1 | 3 | 18.4 | 0.8761904761904761 | 4 | 0 | 40 | 40 | 29 | 0 | 5 | 0 | 0 | 1 |
| LCPS | true | true | 8 | SAFE | 5183 | 11 | 1 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 168 | 168 | 134 | 0 | 10 | 0 | 0 | 1 |
| LCPS | true | true | 16 | SAFE | 8557 | 19 | 1 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 600 | 600 | 473 | 0 | 18 | 0 | 0 | 1 |
| LCPS_FULL | true | true | 4 | SAFE | 3516 | 7 | 1 | 3 | 18.4 | 0.8761904761904761 | 4 | 0 | 49 | 49 | 29 | 0 | 5 | 0 | 7 | 1 |
| LCPS_FULL | true | true | 8 | SAFE | 5189 | 11 | 1 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 186 | 186 | 134 | 0 | 10 | 0 | 30 | 1 |
| LCPS_FULL | true | true | 16 | SAFE | 8594 | 19 | 1 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 634 | 634 | 474 | 0 | 18 | 0 | 122 | 1 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: +35, 8t: +14, 16t: -12 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +1, 8t: +1, 16t: +1 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 4t: +62, 8t: +20, 16t: +25 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 4t: +1, 8t: +1, 16t: +1 (LCPS_FULL - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 4t: +27, 8t: +6, 16t: +37 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_1.i_2-db2cf3f1

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 39186 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 8 | SAFE | 40881 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 16 | SAFE | 56272 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | true | 4 | SAFE | 39093 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 36 | 36 | 25 | 0 | 4 | 0 | 0 | 0 |
| LCPS | true | true | 8 | SAFE | 52808 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 150 | 150 | 121 | 0 | 8 | 0 | 0 | 0 |
| LCPS | true | true | 16 | SAFE | 56244 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 618 | 618 | 505 | 0 | 16 | 0 | 0 | 0 |
| LCPS_FULL | true | true | 4 | SAFE | 51175 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 40 | 40 | 25 | 0 | 4 | 0 | 5 | 0 |
| LCPS_FULL | true | true | 8 | SAFE | 41585 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 158 | 158 | 121 | 0 | 8 | 0 | 23 | 0 |
| LCPS_FULL | true | true | 16 | SAFE | 44232 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 634 | 634 | 505 | 0 | 16 | 0 | 107 | 0 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: -93, 8t: +11927, 16t: -28 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 4t: +11989, 8t: +704, 16t: -12040 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 4t: +12082, 8t: -11223, 16t: -12012 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_2.i_2-b9aa1d3f

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 44125 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 8 | SAFE | 41186 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 16 | SAFE | 56437 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | true | 4 | SAFE | 51291 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 36 | 36 | 25 | 0 | 4 | 0 | 0 | 0 |
| LCPS | true | true | 8 | SAFE | 47394 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 150 | 150 | 121 | 0 | 8 | 0 | 0 | 0 |
| LCPS | true | true | 16 | SAFE | 56533 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 618 | 618 | 505 | 0 | 16 | 0 | 0 | 0 |
| LCPS_FULL | true | true | 4 | SAFE | 51270 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 40 | 40 | 25 | 0 | 4 | 0 | 5 | 0 |
| LCPS_FULL | true | true | 8 | SAFE | 52997 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 158 | 158 | 121 | 0 | 8 | 0 | 23 | 0 |
| LCPS_FULL | true | true | 16 | SAFE | 57023 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 634 | 634 | 505 | 0 | 16 | 0 | 107 | 0 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: +7166, 8t: +6208, 16t: +96 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 4t: +7145, 8t: +11811, 16t: +586 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 4t: -21, 8t: +5603, 16t: +490 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_4.i_2-d2b55c6a

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 27217 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 8 | SAFE | 41405 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 16 | SAFE | 32559 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | true | 4 | SAFE | 27306 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 36 | 36 | 25 | 0 | 4 | 0 | 0 | 0 |
| LCPS | true | true | 8 | SAFE | 28989 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 150 | 150 | 121 | 0 | 8 | 0 | 0 | 0 |
| LCPS | true | true | 16 | SAFE | 32438 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 618 | 618 | 504 | 0 | 16 | 0 | 0 | 0 |
| LCPS_FULL | true | true | 4 | SAFE | 27383 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 40 | 40 | 25 | 0 | 4 | 0 | 5 | 0 |
| LCPS_FULL | true | true | 8 | SAFE | 29025 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 158 | 158 | 121 | 0 | 8 | 0 | 23 | 0 |
| LCPS_FULL | true | true | 16 | SAFE | 32527 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 634 | 634 | 505 | 0 | 16 | 0 | 107 | 0 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: +89, 8t: -12416, 16t: -121 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 4t: +166, 8t: -12380, 16t: -32 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 4t: +77, 8t: +36, 16t: +89 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-jain_6.i_2-f35d9452

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 4 | SAFE | 39682 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 8 | SAFE | 28982 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 16 | SAFE | 32346 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | true | 4 | SAFE | 27298 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 36 | 36 | 25 | 0 | 4 | 0 | 0 | 0 |
| LCPS | true | true | 8 | SAFE | 29069 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 150 | 150 | 121 | 0 | 8 | 0 | 0 | 0 |
| LCPS | true | true | 16 | SAFE | 32431 | 17 | 0 | 2 | 41.47239288489287 | 0.3049440653300946 | 2 | 0 | 618 | 618 | 505 | 0 | 16 | 0 | 0 | 0 |
| LCPS_FULL | true | true | 4 | SAFE | 27340 | 5 | 0 | 2 | 6.416666666666667 | 0.6416666666666667 | 2 | 0 | 40 | 40 | 25 | 0 | 4 | 0 | 5 | 0 |
| LCPS_FULL | true | true | 8 | SAFE | 29045 | 9 | 0 | 2 | 16.460714285714285 | 0.4572420634920635 | 2 | 0 | 158 | 158 | 121 | 0 | 8 | 0 | 23 | 0 |
| LCPS_FULL | true | true | 16 | TIMEOUT | 1205094 | 0 | 0 | 0 | 0 | 0.0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 4t: -12384, 8t: +87, 16t: +85 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 4t: -12342, 8t: +63, 16t: +1.17275e+06 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 4t: +0, 8t: +0, 16t: -17 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 4t: +0, 8t: +0, 16t: -2 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 4t: +0, 8t: +0, 16t: -0.304944 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 4t: +42, 8t: -24, 16t: +1.17266e+06 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 4t: +0, 8t: +0, 16t: -17 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 4t: +0, 8t: +0, 16t: +0 (LCPS_FULL - LCPS).

## Interpretation Notes

- Negative LCPS - PAPER runtime, checked_paths, stale_paths, and duplicate-failure deltas are improvements for that metric.
- LCPS cache activation requires positive LCPS search invocations and positive checked/stale prefix queries. Hits show that the query keys matched cached run prefixes.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log` or `*-<mode>-stale-<on|off>-threads-*.log`.
