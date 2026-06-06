# Checked Path Divergence Results

## Purpose

This experiment compares PAPER, LCPS, and optional BFS/DFS path selection under parallel TraceAbstraction.

Each unordered checked-path pair contributes normalized prefix-LCA divergence `1 - depth(LCA(u, v)) / min(depth(u), depth(v))`. A pair contributes `0.0` when its minimum endpoint depth is zero.

## Summary

### Correctness

- Modes present: PAPER, LCPS, LCPS_FULL, LCPS_STALE_FIRST, LCPS_FULL_STALE_FIRST.
- Result mismatch groups across modes: 0.
- ERROR rows: 0; TIMEOUT rows: 0.
- BFS/DFS were not part of this run.

### Activation

- Cache-guided rows with search invocations > 0: 40 / 40.
- Cache-guided rows with checked prefix queries > 0: 40 / 40.
- Cache-guided rows with stale prefix queries > 0: 40 / 40.
- Cache-guided rows with checked prefix hits > 0: 40 / 40.
- Cache-guided rows with stale prefix hits > 0: 36 / 40.
- Total LCPS effective priority decisions: 184.
- Total LCPS_FULL cache-suffix invocations/fallbacks: 11 / 1194.

### Performance

- LCPS - PAPER runtime_ms: wins/losses/ties 4/6/0, mean 144.40, median 11.00.
- LCPS_FULL - PAPER runtime_ms: wins/losses/ties 5/5/0, mean 242.10, median -0.50.
- LCPS_STALE_FIRST - PAPER runtime_ms: wins/losses/ties 4/6/0, mean 117.10, median 92.50.
- LCPS_FULL_STALE_FIRST - PAPER runtime_ms: wins/losses/ties 6/4/0, mean -35.10, median -15.50.
- LCPS_FULL - LCPS runtime_ms: wins/losses/ties 5/5/0, mean 97.70, median 6.00.
- LCPS_FULL_STALE_FIRST - LCPS_STALE_FIRST runtime_ms: wins/losses/ties 7/3/0, mean -152.20, median -109.00.

### Work

- LCPS - PAPER checked_paths: wins/losses/ties 1/1/8, mean -0.30, median 0.00.
- LCPS_FULL - PAPER checked_paths: wins/losses/ties 1/2/7, mean 0.70, median 0.00.
- LCPS_STALE_FIRST - PAPER checked_paths: wins/losses/ties 1/1/8, mean -0.20, median 0.00.
- LCPS_FULL_STALE_FIRST - PAPER checked_paths: wins/losses/ties 3/1/6, mean -0.60, median 0.00.
- LCPS_FULL - LCPS checked_paths: wins/losses/ties 1/2/7, mean 1.00, median 0.00.
- LCPS - PAPER stale_paths: wins/losses/ties 3/1/6, mean -0.70, median 0.00.
- LCPS_FULL - PAPER stale_paths: wins/losses/ties 1/2/7, mean 0.50, median 0.00.
- LCPS_STALE_FIRST - PAPER stale_paths: wins/losses/ties 2/0/8, mean -0.40, median 0.00.
- LCPS_FULL_STALE_FIRST - PAPER stale_paths: wins/losses/ties 4/0/6, mean -0.70, median 0.00.
- LCPS_FULL - LCPS stale_paths: wins/losses/ties 0/4/6, mean 1.20, median 0.00.
- LCPS - PAPER duplicate freshness failures: wins/losses/ties 2/0/8, mean -0.30, median 0.00.
- LCPS_FULL - PAPER duplicate freshness failures: wins/losses/ties 1/1/8, mean 0.30, median 0.00.
- LCPS_STALE_FIRST - PAPER duplicate freshness failures: wins/losses/ties 3/1/6, mean -0.40, median 0.00.
- LCPS_FULL_STALE_FIRST - PAPER duplicate freshness failures: wins/losses/ties 5/0/5, mean -0.70, median -0.50.
- LCPS_FULL - LCPS duplicate freshness failures: wins/losses/ties 1/3/6, mean 0.60, median 0.00.
- LCPS - PAPER search_failed: wins/losses/ties 0/0/10, mean 0.00, median 0.00.
- LCPS_FULL - PAPER search_failed: wins/losses/ties 0/0/10, mean 0.00, median 0.00.
- LCPS_STALE_FIRST - PAPER search_failed: wins/losses/ties 0/0/10, mean 0.00, median 0.00.
- LCPS_FULL_STALE_FIRST - PAPER search_failed: wins/losses/ties 0/0/10, mean 0.00, median 0.00.
- LCPS_FULL - LCPS search_failed: wins/losses/ties 0/0/10, mean 0.00, median 0.00.

### Divergence

- LCPS - PAPER avg divergence: wins/losses/ties 3/2/5, mean -0.01, median 0.00.
- LCPS_FULL - PAPER avg divergence: wins/losses/ties 3/1/6, mean -0.00, median 0.00.
- LCPS_STALE_FIRST - PAPER avg divergence: wins/losses/ties 3/1/6, mean -0.02, median 0.00.
- LCPS_FULL_STALE_FIRST - PAPER avg divergence: wins/losses/ties 3/2/5, mean -0.00, median 0.00.
- LCPS_FULL - LCPS avg divergence: wins/losses/ties 2/3/5, mean 0.01, median 0.00.

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

## Results

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-eureka_05.i_5-4aad16a8

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 8 | SAFE | 5055 | 20 | 9 | 3 | 168.1035298035298 | 0.8847554200185779 | 13 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 16 | SAFE | 7802 | 35 | 20 | 6 | 611.0219172983881 | 1.0269275920981311 | 23 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | true | 8 | SAFE | 4594 | 16 | 5 | 1 | 98.10352980352978 | 0.8175294150294149 | 9 | 0 | 114 | 114 | 95 | 42 | 14 | 0 | 0 | 6 |
| LCPS | true | true | 16 | SAFE | 8229 | 35 | 17 | 6 | 578.4708379855442 | 0.9722198957740239 | 22 | 0 | 363 | 363 | 310 | 87 | 34 | 0 | 0 | 15 |
| LCPS_FULL | true | true | 8 | SAFE | 5064 | 21 | 10 | 3 | 187.80941215647098 | 0.8943305340784332 | 14 | 0 | 189 | 189 | 130 | 45 | 17 | 0 | 17 | 6 |
| LCPS_FULL | true | true | 16 | SAFE | 8051 | 34 | 18 | 6 | 576.0002497502501 | 1.02673841310205 | 22 | 0 | 425 | 425 | 307 | 87 | 34 | 0 | 35 | 16 |
| LCPS_STALE_FIRST | true | true | 8 | SAFE | 4615 | 17 | 6 | 1 | 114.10352980352978 | 0.838996542673013 | 10 | 0 | 114 | 114 | 95 | 42 | 14 | 0 | 0 | 5 |
| LCPS_STALE_FIRST | true | true | 16 | SAFE | 8005 | 35 | 19 | 5 | 577.5552506317215 | 0.9706810934986916 | 22 | 0 | 366 | 366 | 320 | 130 | 34 | 0 | 0 | 14 |
| LCPS_FULL_STALE_FIRST | true | true | 8 | SAFE | 4594 | 17 | 6 | 1 | 114.10352980352978 | 0.838996542673013 | 10 | 0 | 141 | 141 | 92 | 42 | 14 | 0 | 14 | 6 |
| LCPS_FULL_STALE_FIRST | true | true | 16 | SAFE | 7808 | 36 | 19 | 5 | 580.8219172983881 | 0.9219395512672828 | 22 | 0 | 427 | 427 | 314 | 87 | 34 | 0 | 35 | 14 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t: -461, 16t: +427 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t: -4, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t: -4, 16t: -3 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t: -2, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t: -0.067226, 16t: -0.0547077 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 8t: +6, 16t: +15 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 8t: +9, 16t: +249 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 8t: +1, 16t: -1 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 8t: +1, 16t: -2 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 8t: +0.00957511, 16t: -0.000189179 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 8t: +6, 16t: +16 (LCPS_FULL - PAPER).
- PAPER vs LCPS_STALE_FIRST runtime_ms: 8t: -440, 16t: +203 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST checked_paths: 8t: -3, 16t: +0 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST stale_paths: 8t: -3, 16t: -1 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST duplicate freshness failures: 8t: -2, 16t: -1 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST avg divergence: 8t: -0.0457589, 16t: -0.0562465 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST lcps_effective_priority_decisions: 8t: +5, 16t: +14 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST runtime_ms: 8t: -461, 16t: +6 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST checked_paths: 8t: -3, 16t: +1 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST stale_paths: 8t: -3, 16t: -1 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST duplicate freshness failures: 8t: -2, 16t: -1 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST avg divergence: 8t: -0.0457589, 16t: -0.104988 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST lcps_effective_priority_decisions: 8t: +6, 16t: +14 (LCPS_FULL_STALE_FIRST - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 8t: +470, 16t: -178 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 8t: +5, 16t: -1 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 8t: +5, 16t: +1 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 8t: +0, 16t: +1 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 8t: +0, 16t: +0 (LCPS_FULL - LCPS).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-invert_string.i_4-75f9c6bb

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 8 | SAFE | 5785 | 22 | 11 | 10 | 230.51727994227994 | 0.9979103027804327 | 16 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 16 | SAFE | 8733 | 35 | 15 | 11 | 538.3478354978356 | 0.9047862781476229 | 20 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | true | 8 | SAFE | 6474 | 23 | 12 | 10 | 253.1940476190476 | 1.0007669866365518 | 17 | 0 | 199 | 199 | 157 | 70 | 22 | 0 | 0 | 12 |
| LCPS | true | true | 16 | SAFE | 9312 | 35 | 15 | 11 | 540.2665945165945 | 0.9080110832211673 | 20 | 0 | 485 | 485 | 416 | 97 | 34 | 0 | 0 | 16 |
| LCPS_FULL | true | true | 8 | SAFE | 7773 | 29 | 17 | 15 | 404.8304112554112 | 0.9971192395453478 | 23 | 0 | 373 | 373 | 278 | 156 | 28 | 7 | 30 | 9 |
| LCPS_FULL | true | true | 16 | SAFE | 9342 | 35 | 15 | 9 | 531.6216505716505 | 0.8934817656666396 | 20 | 0 | 602 | 602 | 477 | 101 | 34 | 1 | 58 | 12 |
| LCPS_STALE_FIRST | true | true | 8 | SAFE | 6207 | 23 | 11 | 8 | 236.07485569985565 | 0.9331021964421172 | 16 | 0 | 207 | 207 | 171 | 85 | 22 | 0 | 0 | 5 |
| LCPS_STALE_FIRST | true | true | 16 | SAFE | 9362 | 35 | 15 | 12 | 539.1660173160174 | 0.9061613736403653 | 20 | 0 | 495 | 495 | 431 | 108 | 34 | 0 | 0 | 7 |
| LCPS_FULL_STALE_FIRST | true | true | 8 | SAFE | 5750 | 19 | 9 | 8 | 185.9940476190476 | 1.0876844890002784 | 14 | 0 | 218 | 218 | 148 | 67 | 20 | 2 | 22 | 7 |
| LCPS_FULL_STALE_FIRST | true | true | 16 | SAFE | 8904 | 34 | 15 | 10 | 536.0387445887446 | 0.95550578358065 | 20 | 0 | 551 | 551 | 427 | 112 | 34 | 1 | 57 | 10 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t: +689, 16t: +579 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t: +1, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t: +1, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t: +0.00285668, 16t: +0.00322481 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 8t: +12, 16t: +16 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 8t: +1988, 16t: +609 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 8t: +7, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 8t: +6, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 8t: +5, 16t: -2 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 8t: -0.000791063, 16t: -0.0113045 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 8t: +9, 16t: +12 (LCPS_FULL - PAPER).
- PAPER vs LCPS_STALE_FIRST runtime_ms: 8t: +422, 16t: +629 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST checked_paths: 8t: +1, 16t: +0 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST stale_paths: 8t: +0, 16t: +0 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST duplicate freshness failures: 8t: -2, 16t: +1 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST avg divergence: 8t: -0.0648081, 16t: +0.0013751 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST lcps_effective_priority_decisions: 8t: +5, 16t: +7 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST runtime_ms: 8t: -35, 16t: +171 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST checked_paths: 8t: -3, 16t: -1 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST stale_paths: 8t: -2, 16t: +0 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST duplicate freshness failures: 8t: -2, 16t: -1 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST avg divergence: 8t: +0.0897742, 16t: +0.0507195 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST lcps_effective_priority_decisions: 8t: +7, 16t: +10 (LCPS_FULL_STALE_FIRST - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 8t: +1299, 16t: +30 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 8t: +6, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 8t: +5, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 8t: -3, 16t: -4 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 8t: +7, 16t: +1 (LCPS_FULL - LCPS).

### examples-programs-20170304-DifficultPathPrograms-resultKnown-interleave_bits.i_3-2d793c20

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 8 | UNKNOWN | 6155 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 16 | UNKNOWN | 4990 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | true | 8 | UNKNOWN | 6118 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 227 | 227 | 189 | 25 | 14 | 0 | 0 | 1 |
| LCPS | true | true | 16 | UNKNOWN | 4977 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 463 | 463 | 340 | 0 | 15 | 0 | 0 | 1 |
| LCPS_FULL | true | true | 8 | UNKNOWN | 6149 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 252 | 252 | 188 | 25 | 14 | 0 | 34 | 1 |
| LCPS_FULL | true | true | 16 | UNKNOWN | 4975 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 493 | 493 | 340 | 0 | 15 | 0 | 119 | 1 |
| LCPS_STALE_FIRST | true | true | 8 | UNKNOWN | 6131 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 227 | 227 | 189 | 25 | 14 | 0 | 0 | 1 |
| LCPS_STALE_FIRST | true | true | 16 | UNKNOWN | 5158 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 463 | 463 | 340 | 0 | 15 | 0 | 0 | 1 |
| LCPS_FULL_STALE_FIRST | true | true | 8 | UNKNOWN | 6139 | 15 | 5 | 8 | 89.57936507936509 | 0.8531368102796675 | 7 | 0 | 252 | 252 | 189 | 25 | 14 | 0 | 34 | 1 |
| LCPS_FULL_STALE_FIRST | true | true | 16 | UNKNOWN | 5181 | 16 | 0 | 1 | 39.82389081506727 | 0.3318657567922273 | 0 | 0 | 493 | 493 | 340 | 0 | 15 | 0 | 119 | 1 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t: -37, 16t: -13 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 8t: +1, 16t: +1 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 8t: -6, 16t: -15 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 8t: +1, 16t: +1 (LCPS_FULL - PAPER).
- PAPER vs LCPS_STALE_FIRST runtime_ms: 8t: -24, 16t: +168 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST checked_paths: 8t: +0, 16t: +0 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST stale_paths: 8t: +0, 16t: +0 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST duplicate freshness failures: 8t: +0, 16t: +0 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST avg divergence: 8t: +0, 16t: +0 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST lcps_effective_priority_decisions: 8t: +1, 16t: +1 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST runtime_ms: 8t: -16, 16t: +191 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST checked_paths: 8t: +0, 16t: +0 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST stale_paths: 8t: +0, 16t: +0 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST duplicate freshness failures: 8t: +0, 16t: +0 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST avg divergence: 8t: +0, 16t: +0 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST lcps_effective_priority_decisions: 8t: +1, 16t: +1 (LCPS_FULL_STALE_FIRST - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 8t: +31, 16t: -2 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 8t: +0, 16t: +0 (LCPS_FULL - LCPS).

### trunk-examples-programs-20170304-DifficultPathPrograms-resultKnown-diamond2.i_4-22ecac6d

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 8 | SAFE | 5289 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 16 | SAFE | 8716 | 22 | 5 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | true | 8 | SAFE | 5280 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 223 | 223 | 184 | 20 | 13 | 0 | 0 | 1 |
| LCPS | true | true | 16 | SAFE | 8732 | 22 | 4 | 6 | 149.91479990597637 | 0.6489818177747895 | 7 | 0 | 700 | 700 | 567 | 20 | 21 | 0 | 0 | 1 |
| LCPS_FULL | true | true | 8 | SAFE | 5294 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 245 | 245 | 184 | 20 | 13 | 0 | 33 | 1 |
| LCPS_FULL | true | true | 16 | SAFE | 8511 | 22 | 5 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 749 | 749 | 580 | 20 | 21 | 0 | 125 | 1 |
| LCPS_STALE_FIRST | true | true | 8 | SAFE | 5687 | 14 | 5 | 7 | 75.57936507936509 | 0.8305424733996164 | 7 | 0 | 223 | 223 | 184 | 20 | 13 | 0 | 0 | 1 |
| LCPS_STALE_FIRST | true | true | 16 | SAFE | 8733 | 22 | 5 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 711 | 711 | 580 | 20 | 21 | 0 | 0 | 1 |
| LCPS_FULL_STALE_FIRST | true | true | 8 | SAFE | 5274 | 14 | 5 | 6 | 74.72222222222223 | 0.8211233211233212 | 7 | 0 | 241 | 241 | 179 | 20 | 13 | 0 | 33 | 1 |
| LCPS_FULL_STALE_FIRST | true | true | 16 | SAFE | 8519 | 22 | 4 | 7 | 150.82389081506727 | 0.6529172762557025 | 7 | 0 | 737 | 737 | 569 | 19 | 21 | 0 | 125 | 1 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t: -9, 16t: +16 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t: +0, 16t: -1 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t: +0, 16t: -1 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t: +0, 16t: -0.00393546 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 8t: +1, 16t: +1 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 8t: +5, 16t: -205 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 8t: +1, 16t: +1 (LCPS_FULL - PAPER).
- PAPER vs LCPS_STALE_FIRST runtime_ms: 8t: +398, 16t: +17 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST checked_paths: 8t: +0, 16t: +0 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST stale_paths: 8t: +0, 16t: +0 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST duplicate freshness failures: 8t: +0, 16t: +0 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST avg divergence: 8t: +0, 16t: +0 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST lcps_effective_priority_decisions: 8t: +1, 16t: +1 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST runtime_ms: 8t: -15, 16t: -197 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST checked_paths: 8t: +0, 16t: +0 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST stale_paths: 8t: +0, 16t: -1 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST duplicate freshness failures: 8t: -1, 16t: +0 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST avg divergence: 8t: -0.00941915, 16t: +0 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST lcps_effective_priority_decisions: 8t: +1, 16t: +1 (LCPS_FULL_STALE_FIRST - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 8t: +14, 16t: -221 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 8t: +0, 16t: +1 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 8t: +0, 16t: +0 (LCPS_FULL - LCPS).

### k-examples-programs-20170304-DifficultPathPrograms-resultKnown-count_up_down.i_3-aa4a8d5f

| mode | stale_tracking | use_initial_bfs | threads | result | runtime_ms | checked_paths | stale_paths | duplicate_freshness_failures | total_pairwise_prefix_lca_divergence | avg_pairwise_prefix_lca_divergence | refinements | search_failed | lcps_checked_prefix_queries | lcps_stale_prefix_queries | lcps_checked_prefix_hits | lcps_stale_prefix_hits | lcps_search_invocations | lcps_full_cache_suffix_invocations | lcps_full_cache_suffix_fallbacks | lcps_effective_priority_decisions |
|---|---|---|---:|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| PAPER | true | true | 8 | SAFE | 5158 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| PAPER | true | true | 16 | SAFE | 7754 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| LCPS | true | true | 8 | SAFE | 5164 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 162 | 162 | 128 | 3 | 10 | 0 | 0 | 1 |
| LCPS | true | true | 16 | SAFE | 8001 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 594 | 594 | 468 | 3 | 18 | 0 | 0 | 1 |
| LCPS_FULL | true | true | 8 | SAFE | 5125 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 180 | 180 | 128 | 3 | 10 | 0 | 30 | 1 |
| LCPS_FULL | true | true | 16 | SAFE | 7574 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 628 | 628 | 468 | 3 | 18 | 0 | 122 | 1 |
| LCPS_STALE_FIRST | true | true | 8 | SAFE | 5142 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 162 | 162 | 128 | 3 | 10 | 0 | 0 | 1 |
| LCPS_STALE_FIRST | true | true | 16 | SAFE | 7568 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 594 | 594 | 468 | 3 | 18 | 0 | 0 | 1 |
| LCPS_FULL_STALE_FIRST | true | true | 8 | SAFE | 5138 | 11 | 2 | 4 | 39.57936507936508 | 0.7196248196248197 | 4 | 0 | 180 | 180 | 128 | 3 | 10 | 0 | 30 | 1 |
| LCPS_FULL_STALE_FIRST | true | true | 16 | SAFE | 7779 | 19 | 2 | 4 | 90.82389081506727 | 0.5311338644155981 | 4 | 0 | 628 | 628 | 468 | 3 | 18 | 0 | 122 | 1 |

#### Interpretation

- PAPER vs LCPS runtime_ms: 8t: +6, 16t: +247 (LCPS - PAPER).
- PAPER vs LCPS checked_paths: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS stale_paths: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS duplicate freshness failures: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS avg divergence: 8t: +0, 16t: +0 (LCPS - PAPER).
- PAPER vs LCPS lcps_effective_priority_decisions: 8t: +1, 16t: +1 (LCPS - PAPER).
- PAPER vs LCPS_FULL runtime_ms: 8t: -33, 16t: -180 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL checked_paths: 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL stale_paths: 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL duplicate freshness failures: 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL avg divergence: 8t: +0, 16t: +0 (LCPS_FULL - PAPER).
- PAPER vs LCPS_FULL lcps_effective_priority_decisions: 8t: +1, 16t: +1 (LCPS_FULL - PAPER).
- PAPER vs LCPS_STALE_FIRST runtime_ms: 8t: -16, 16t: -186 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST checked_paths: 8t: +0, 16t: +0 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST stale_paths: 8t: +0, 16t: +0 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST duplicate freshness failures: 8t: +0, 16t: +0 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST avg divergence: 8t: +0, 16t: +0 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_STALE_FIRST lcps_effective_priority_decisions: 8t: +1, 16t: +1 (LCPS_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST runtime_ms: 8t: -20, 16t: +25 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST checked_paths: 8t: +0, 16t: +0 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST stale_paths: 8t: +0, 16t: +0 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST duplicate freshness failures: 8t: +0, 16t: +0 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST avg divergence: 8t: +0, 16t: +0 (LCPS_FULL_STALE_FIRST - PAPER).
- PAPER vs LCPS_FULL_STALE_FIRST lcps_effective_priority_decisions: 8t: +1, 16t: +1 (LCPS_FULL_STALE_FIRST - PAPER).
- LCPS_FULL vs LCPS runtime_ms: 8t: -39, 16t: -427 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS checked_paths: 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS stale_paths: 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_effective_priority_decisions: 8t: +0, 16t: +0 (LCPS_FULL - LCPS).
- LCPS_FULL vs LCPS lcps_full_cache_suffix_invocations: 8t: +0, 16t: +0 (LCPS_FULL - LCPS).

## Interpretation Notes

- Negative LCPS - PAPER runtime, checked_paths, stale_paths, and duplicate-failure deltas are improvements for that metric.
- LCPS cache activation requires positive LCPS search invocations and positive checked/stale prefix queries. Hits show that the query keys matched cached run prefixes.
- Treat timeouts, crashes, and zero checked paths as inconclusive for the corresponding row.

## Raw Data

See `checked-path-divergence-results.csv` in this directory. Raw Ultimate logs are stored as `*-<mode>-threads-*.log` or `*-<mode>-stale-<on|off>-threads-*.log`.
