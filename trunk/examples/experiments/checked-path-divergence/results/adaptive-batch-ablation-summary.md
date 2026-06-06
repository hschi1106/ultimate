# Adaptive Batch LCPS Ablation Summary

## Experiment Sets

- Large Top10: `results/adaptive-batch-top10/checked-path-divergence-results.csv`
- High-stale subset: derived from the Large Top10 rows for `eureka_05`, `invert_string`, `interleave_bits`, `diamond2`, and `count_up_down`.
- count_up_down repeat: `results/adaptive-batch-count-up-down-repeat/checked-path-divergence-results.csv`

## Correctness

- Large Top10: 240 rows; 214 SAFE, 24 UNKNOWN, 2 TIMEOUT.
- The only TIMEOUT rows were `ADAPTIVE_DUPLICATE` on `jain_6` at 4 and 8 threads.
- No result mismatch was observed between PAPER, BATCH_LCPS, and adaptive modes on completed rows.
- High-stale subset and count_up_down repeat had no ERROR and no TIMEOUT.

## Activation

Large Top10:

| Mode | Rows | Timeouts | Adaptive invocations | Candidates generated | Effective batch decisions |
|---|---:|---:|---:|---:|---:|
| BATCH_LCPS | 30 | 0 | n/a | 1364 | 106 |
| ADAPTIVE_ALWAYS | 30 | 0 | 153 | 1324 | 102 |
| ADAPTIVE_DUPLICATE | 30 | 2 | 7 | 144 | 7 |
| ADAPTIVE_STALE | 30 | 0 | 11 | 180 | 10 |
| ADAPTIVE_SEARCH_FAILED | 30 | 0 | 0 | 0 | 0 |
| ADAPTIVE_IDLE | 30 | 0 | 0 | 0 | 0 |
| ADAPTIVE_NEVER | 30 | 0 | 0 | 0 | 0 |

High-stale subset:

| Mode | Rows | Adaptive invocations | Candidates generated | Effective batch decisions |
|---|---:|---:|---:|---:|
| BATCH_LCPS | 15 | n/a | 928 | 97 |
| ADAPTIVE_ALWAYS | 15 | 118 | 884 | 92 |
| ADAPTIVE_DUPLICATE | 15 | 7 | 144 | 7 |
| ADAPTIVE_STALE | 15 | 11 | 180 | 10 |
| ADAPTIVE_SEARCH_FAILED | 15 | 0 | 0 | 0 |
| ADAPTIVE_IDLE | 15 | 0 | 0 | 0 |

count_up_down repeat:

- BATCH_LCPS generated 496 candidates and made 49 effective batch decisions.
- DUPLICATE_ONLY, STALE_ONLY, SEARCH_FAILED_ONLY, and IDLE_SLOT_ONLY never invoked batch on any repeat.

## Runtime

Large Top10, median runtime delta:

| Mode | vs PAPER | vs BATCH_LCPS |
|---|---:|---:|
| BATCH_LCPS | +93 ms | n/a |
| ADAPTIVE_ALWAYS | +85.5 ms | +9.5 ms |
| ADAPTIVE_DUPLICATE | +32 ms | -13 ms |
| ADAPTIVE_STALE | -2 ms | -143 ms |
| ADAPTIVE_SEARCH_FAILED | +10.5 ms | -67.5 ms |
| ADAPTIVE_IDLE | -2.5 ms | -43.5 ms |
| ADAPTIVE_NEVER | -7 ms | -48 ms |

High-stale subset, median runtime delta:

| Mode | vs PAPER | vs BATCH_LCPS |
|---|---:|---:|
| BATCH_LCPS | +634 ms | n/a |
| ADAPTIVE_ALWAYS | +246 ms | +12 ms |
| ADAPTIVE_DUPLICATE | -6 ms | -504 ms |
| ADAPTIVE_STALE | -3 ms | -515 ms |
| ADAPTIVE_SEARCH_FAILED | +35 ms | -284 ms |
| ADAPTIVE_IDLE | -3 ms | -618 ms |

count_up_down repeat median runtime:

| Mode | 4 threads | 8 threads | 16 threads |
|---|---:|---:|---:|
| PAPER | 122067 ms | 5150 ms | 7564 ms |
| BATCH_LCPS | 3046 ms | 4857 ms | 7243 ms |
| ADAPTIVE_DUPLICATE | 122035 ms | 5136 ms | 7588 ms |
| ADAPTIVE_STALE | 122020 ms | 5162 ms | 7585 ms |
| ADAPTIVE_SEARCH_FAILED | 122083 ms | 5119 ms | 7756 ms |
| ADAPTIVE_IDLE | 121977 ms | 5123 ms | 7589 ms |

## Work

- On count_up_down repeat, BATCH_LCPS reduced median checked paths by 1 and stale paths by 1 versus PAPER, and reduced duplicate freshness failures by 4.33 on average.
- The four single-trigger adaptive modes did not reduce count_up_down work because none of them triggered batch.
- In Large Top10, ADAPTIVE_STALE had the best non-trivial activation profile without timeout, but its work deltas were small: checked path median delta 0 and stale path median delta 0 versus PAPER.
- ADAPTIVE_ALWAYS matched BATCH_LCPS activation but also inherited the cases where batch generated candidates without useful effective decisions.

## Interpretation

- BATCH_LCPS changes path choice much more often than LCPS-style priority alone, but always batching is not stable across the full top10.
- The simple trigger signals are too late or absent for count_up_down: the large 4-thread improvement requires early unconditional batch selection, while DUPLICATE_ONLY, STALE_ONLY, SEARCH_FAILED_ONLY, and IDLE_SLOT_ONLY all remain on the PAPER path.
- STALE_ONLY is the best single trigger among the tested modes because it has non-zero activation, no timeout, low overhead, and avoids the `jain_6` DUPLICATE_ONLY timeout. However, it does not preserve the main count_up_down improvement.
- DUPLICATE_ONLY is not acceptable as the best trigger because it produced two jain_6 timeouts despite low activation.
- SEARCH_FAILED_ONLY and IDLE_SLOT_ONLY are not useful in this implementation because they never activated in the tested suites.

## Recommendation

Keep ADAPTIVE_BATCH_LCPS as an experimental mode, with STALE_ONLY as the best current single-trigger default for experiments. Do not make it the verifier default. PAPER should remain the default. If the goal is to preserve the count_up_down improvement, single-trigger adaptive batch is insufficient; BATCH_LCPS should stay as a manual experimental mode or the next algorithm should use an earlier activation condition rather than adding arbitrary weights.
