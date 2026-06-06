# Proactive Adaptive Batch Trigger Ablation

Date: 2026-06-06

## Correctness
- Large Top10: mismatch groups=0, ERROR rows=0, TIMEOUT rows=0, result counts: SAFE=216, UNKNOWN=24.
- High-stale subset: mismatch groups=0, ERROR rows=0, TIMEOUT rows=0, result counts: SAFE=96, UNKNOWN=24.
- count_up_down repeat: mismatch groups=0, ERROR rows=0, TIMEOUT rows=0, result counts: SAFE=90.

No result mismatches, ERROR rows, or TIMEOUT rows occurred in these runs. The UNKNOWN rows are the same interleave_bits benchmark result across all modes/threads, so they are not cross-mode mismatches.

## Large Top10 Activation And Overhead
| mode | invocations | fallbacks | stale | first-fill | first-fill-or-stale | threads>=4 first-fill | candidates | gen ms | selected | selected/decision |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| BATCH_LCPS | 0 | 0 | 0 | 0 | 0 | 0 | 1308 | 1653 | 404 | 4.00 |
| ADAPTIVE_STALE | 11 | 132 | 11 | 0 | 0 | 0 | 176 | 77 | 51 | 5.10 |
| ADAPTIVE_FIRST_FILL | 46 | 113 | 0 | 46 | 0 | 0 | 940 | 1422 | 310 | 9.69 |
| ADAPTIVE_FIRST_FILL_OR_STALE | 48 | 107 | 0 | 0 | 48 | 0 | 948 | 1454 | 314 | 9.52 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | 49 | 104 | 0 | 0 | 0 | 49 | 964 | 1497 | 316 | 9.29 |
| ADAPTIVE_ALWAYS | 142 | 6 | 0 | 0 | 0 | 0 | 1312 | 1634 | 406 | 4.32 |
| ADAPTIVE_NEVER | 0 | 141 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.00 |

## Large Top10 Runtime Vs PAPER
| mode | wins | losses | mean delta ms | median delta ms | mean delta % | median delta % |
|---|---:|---:|---:|---:|---:|---:|
| BATCH_LCPS | 11 | 19 | -2131.5 | 58.5 | 6.38 | 0.71 |
| ADAPTIVE_STALE | 16 | 13 | 674.1 | -1.0 | 2.30 | -0.01 |
| ADAPTIVE_FIRST_FILL | 8 | 19 | -3964.9 | 61.5 | 0.55 | 0.21 |
| ADAPTIVE_FIRST_FILL_OR_STALE | 10 | 20 | -3715.8 | 60.5 | 1.00 | 0.36 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | 12 | 18 | -4017.6 | 21.0 | 0.31 | 0.16 |
| ADAPTIVE_ALWAYS | 8 | 22 | -2881.8 | 71.0 | 2.97 | 0.66 |
| ADAPTIVE_NEVER | 14 | 16 | 497.7 | 4.0 | 2.06 | 0.02 |

## Large Top10 Runtime Vs BATCH_LCPS
| mode | wins | losses | mean delta ms | median delta ms | mean delta % | median delta % |
|---|---:|---:|---:|---:|---:|---:|
| PAPER | 19 | 11 | 2131.5 | -58.5 | 116.24 | -0.71 |
| ADAPTIVE_STALE | 20 | 10 | 2805.6 | -91.0 | 118.11 | -1.00 |
| ADAPTIVE_FIRST_FILL | 17 | 13 | -1833.4 | -2.0 | -1.94 | -0.05 |
| ADAPTIVE_FIRST_FILL_OR_STALE | 15 | 15 | -1584.3 | -2.0 | -1.62 | 0.01 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | 14 | 16 | -1886.1 | 2.0 | -2.43 | 0.03 |
| ADAPTIVE_ALWAYS | 12 | 18 | -750.3 | 16.5 | -0.58 | 0.13 |
| ADAPTIVE_NEVER | 21 | 9 | 2629.2 | -88.0 | 117.79 | -1.98 |

## Large Top10 Work Delta Vs PAPER
| mode | mean checked delta | median checked delta | mean stale delta | median stale delta | mean duplicate delta | mean search_failed delta |
|---|---:|---:|---:|---:|---:|---:|
| BATCH_LCPS | -0.1 | 0.0 | 0.0 | 0.0 | -4.1 | 0.1 |
| ADAPTIVE_STALE | -0.0 | 0.0 | -0.0 | 0.0 | -0.3 | 0.0 |
| ADAPTIVE_FIRST_FILL | 0.5 | 0.0 | 0.4 | 0.0 | -1.2 | 0.2 |
| ADAPTIVE_FIRST_FILL_OR_STALE | 0.4 | 0.0 | 0.3 | 0.0 | -1.3 | 0.2 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | 0.4 | 0.0 | 0.3 | 0.0 | -1.4 | 0.2 |
| ADAPTIVE_ALWAYS | 0.1 | 0.0 | 0.0 | 0.0 | -3.9 | 0.4 |
| ADAPTIVE_NEVER | 0.1 | 0.0 | 0.1 | 0.0 | 0.0 | 0.0 |

## High-Stale Subset Activation And Overhead
| mode | invocations | fallbacks | stale | first-fill | first-fill-or-stale | threads>=4 first-fill | candidates | gen ms | selected | selected/decision |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| BATCH_LCPS | 0 | 0 | 0 | 0 | 0 | 0 | 884 | 787 | 260 | 2.89 |
| ADAPTIVE_STALE | 12 | 98 | 12 | 0 | 0 | 0 | 188 | 80 | 53 | 4.42 |
| ADAPTIVE_FIRST_FILL | 29 | 86 | 0 | 29 | 0 | 0 | 548 | 612 | 179 | 7.16 |
| ADAPTIVE_FIRST_FILL_OR_STALE | 26 | 81 | 0 | 0 | 26 | 0 | 536 | 613 | 178 | 7.12 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | 33 | 80 | 0 | 0 | 0 | 33 | 584 | 650 | 189 | 6.52 |
| ADAPTIVE_ALWAYS | 108 | 5 | 0 | 0 | 0 | 0 | 864 | 753 | 255 | 2.97 |
| ADAPTIVE_NEVER | 0 | 107 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.00 |

## High-Stale Runtime Vs PAPER
| mode | wins | losses | mean delta ms | median delta ms | mean delta % | median delta % |
|---|---:|---:|---:|---:|---:|---:|
| BATCH_LCPS | 5 | 10 | -7494.5 | 493.0 | -0.05 | 6.38 |
| ADAPTIVE_STALE | 6 | 9 | 170.3 | 6.0 | 1.76 | 0.15 |
| ADAPTIVE_FIRST_FILL | 4 | 11 | -7423.0 | 506.0 | 1.51 | 8.46 |
| ADAPTIVE_FIRST_FILL_OR_STALE | 4 | 11 | -7559.2 | 241.0 | -0.73 | 4.99 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | 5 | 10 | -7494.3 | 221.0 | 0.21 | 5.41 |
| ADAPTIVE_ALWAYS | 4 | 11 | -7537.1 | 335.0 | -1.45 | 5.09 |
| ADAPTIVE_NEVER | 6 | 9 | 110.0 | 12.0 | 1.28 | 0.20 |

## High-Stale Runtime Vs BATCH_LCPS
| mode | wins | losses | mean delta ms | median delta ms | mean delta % | median delta % |
|---|---:|---:|---:|---:|---:|---:|
| PAPER | 10 | 5 | 7494.5 | -493.0 | 234.97 | -5.99 |
| ADAPTIVE_STALE | 10 | 5 | 7664.8 | -289.0 | 238.20 | -4.99 |
| ADAPTIVE_FIRST_FILL | 8 | 7 | 71.5 | -6.0 | 1.36 | -0.14 |
| ADAPTIVE_FIRST_FILL_OR_STALE | 9 | 6 | -64.7 | -17.0 | -0.31 | -0.39 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | 6 | 8 | 0.3 | 2.0 | 0.10 | 0.04 |
| ADAPTIVE_ALWAYS | 10 | 5 | -42.6 | -7.0 | -0.60 | -0.11 |
| ADAPTIVE_NEVER | 10 | 5 | 7604.5 | -489.0 | 237.27 | -5.80 |

## High-Stale Work Delta Vs PAPER
| mode | mean checked delta | median checked delta | mean stale delta | median stale delta | mean duplicate delta | mean search_failed delta |
|---|---:|---:|---:|---:|---:|---:|
| BATCH_LCPS | 0.3 | 1.0 | 0.6 | 0.0 | -5.9 | 0.5 |
| ADAPTIVE_STALE | 0.4 | 0.0 | 0.3 | 0.0 | -0.5 | 0.0 |
| ADAPTIVE_FIRST_FILL | 0.3 | 1.0 | 0.3 | 0.0 | -1.9 | 0.6 |
| ADAPTIVE_FIRST_FILL_OR_STALE | -0.1 | 0.0 | -0.1 | 0.0 | -2.1 | 0.3 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | 0.2 | 0.0 | 0.7 | 0.0 | -2.1 | 0.6 |
| ADAPTIVE_ALWAYS | -0.3 | 0.0 | 0.3 | 1.0 | -5.6 | 0.7 |
| ADAPTIVE_NEVER | 0.3 | 0.0 | 0.4 | 0.0 | -0.1 | 0.0 |

## count_up_down Repeat Stability
| mode | 4t median ms | 8t median ms | 16t median ms | 4t median invocations | 4t median candidates |
|---|---:|---:|---:|---:|---:|
| PAPER | 122445 | 5128 | 7606 | 0 | 0 |
| BATCH_LCPS | 3047 | 5414 | 7049 | 0 | 20 |
| ADAPTIVE_STALE | 122328 | 5149 | 7585 | 0 | 0 |
| ADAPTIVE_FIRST_FILL | 3050 | 4845 | 7237 | 1 | 12 |
| ADAPTIVE_FIRST_FILL_OR_STALE | 3037 | 4870 | 7022 | 1 | 12 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | 3029 | 4846 | 7007 | 1 | 12 |

## count_up_down Repeat Activation And Overhead
| mode | invocations | fallbacks | stale | first-fill | first-fill-or-stale | threads>=4 first-fill | candidates | gen ms | selected | selected/decision |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| BATCH_LCPS | 0 | 0 | 0 | 0 | 0 | 0 | 496 | 814 | 159 | 3.24 |
| ADAPTIVE_STALE | 0 | 89 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0.00 |
| ADAPTIVE_FIRST_FILL | 15 | 33 | 0 | 15 | 0 | 0 | 360 | 700 | 125 | 8.33 |
| ADAPTIVE_FIRST_FILL_OR_STALE | 15 | 36 | 0 | 0 | 15 | 0 | 360 | 738 | 125 | 8.33 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | 15 | 33 | 0 | 0 | 0 | 15 | 360 | 717 | 125 | 8.33 |

## count_up_down Runtime Vs PAPER
| mode | wins | losses | mean delta ms | median delta ms | mean delta % | median delta % |
|---|---:|---:|---:|---:|---:|---:|
| BATCH_LCPS | 12 | 3 | -39923.8 | -501.0 | -33.95 | -6.64 |
| ADAPTIVE_STALE | 8 | 7 | -15.3 | -7.0 | 0.11 | -0.10 |
| ADAPTIVE_FIRST_FILL | 12 | 3 | -39842.3 | -535.0 | -33.64 | -9.66 |
| ADAPTIVE_FIRST_FILL_OR_STALE | 11 | 4 | -39869.8 | -541.0 | -33.39 | -7.36 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | 12 | 3 | -39940.2 | -573.0 | -34.33 | -7.56 |

## count_up_down Runtime Vs BATCH_LCPS
| mode | wins | losses | mean delta ms | median delta ms | mean delta % | median delta % |
|---|---:|---:|---:|---:|---:|---:|
| PAPER | 3 | 12 | 39923.8 | 501.0 | 1276.22 | 7.11 |
| ADAPTIVE_STALE | 3 | 12 | 39908.5 | 545.0 | 1275.44 | 7.77 |
| ADAPTIVE_FIRST_FILL | 7 | 8 | 81.5 | 3.0 | 0.84 | 0.09 |
| ADAPTIVE_FIRST_FILL_OR_STALE | 11 | 4 | 54.0 | -40.0 | -0.02 | -0.66 |
| ADAPTIVE_THREADS_GE_4_FIRST_FILL | 9 | 6 | -16.4 | -19.0 | -0.62 | -0.63 |

## Recommendation

Recommended experimental default: `FIRST_FILL_OR_STALE`.

- It had no mismatch, ERROR, or TIMEOUT rows, including jain_6.
- It activated on count_up_down repeat in every 4t run: median invocations=1, median candidates=12, median runtime=3037 ms versus PAPER 122445 ms and BATCH_LCPS 3047 ms.
- Large Top10 median runtime delta versus PAPER was small: +60.5 ms (+0.36%). Mean delta was strongly negative because it captures the count_up_down 4t win.
- High-stale subset was better than pure FIRST_FILL_ONLY on median runtime versus PAPER (+241 ms versus +506 ms) and slightly better than BATCH_LCPS on median paired runtime (-17 ms).
- Candidate overhead was far below ALWAYS_BATCH on high-stale subset: 536 candidates / 613 ms generation versus 864 candidates / 753 ms. On Large Top10 it generated 948 candidates versus ALWAYS_BATCH 1312.
- Logic remains one sentence: use batch at the first worker-fill dispatch of each abstraction, and later only after stale work is observed; otherwise use PAPER.

`FIRST_FILL_ONLY` is the minimal proactive alternative if the next experiment wants the simplest possible trigger. It also preserves count_up_down 4t, but it gives up the already useful stale-reactive path.
