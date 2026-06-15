# DOI: Refinement-Aware Parallel CEGAR in Ultimate Automizer

Group members: r14921035 Huang Shao-Chi, r14943074 Hsu Chen-Yun

This document is the consolidated project DOI. It combines the original project description and the
parallel CEGAR report into one maintained artifact. Reproduction instructions live in
[`REPRODUCTION.md`](REPRODUCTION.md).

## 1. Goal

Formal verification can prove the absence of bugs, but full software model checking is often too slow for
continuous integration or an interactive development loop. Modern machines have many cores, so the project asks:

> How can trace-abstraction-based CEGAR use parallel hardware to reduce wall-clock verification time without changing
> the safety result?

The work builds on Barth and Jakobs, "Multi-Threaded Software Model Checking via Parallel Trace Abstraction
Refinement" (arXiv:2509.13699), implemented in Ultimate Automizer. Their design exposes parallelism with a
coordinator/worker architecture:

- the coordinator owns the evolving abstraction automaton;
- worker threads run trace checking and interpolation;
- returned interpolant automata are applied as refinements by the coordinator.

The final conclusion is that diverse trace selection alone is not enough for stable speedup. The stable win comes from
targeting the actual critical path: refinement application, minimization, and recurring loop trace checks.

## 2. Baseline

Trace abstraction maintains an automaton `A` whose language is the current set of candidate error traces. The
sequential CEGAR loop is:

1. Select an error trace `pi` from `L(A)`.
2. Check the trace formula `phi_pi` with SMT.
3. If the trace is feasible, report `UNSAFE`.
4. If the trace is infeasible, build an interpolant automaton `A_pi`.
5. Refine with `A := A \ A_pi`.
6. If `L(A)` becomes empty, report `SAFE`.

In the parallel loop, the coordinator dispatches accepted runs from `A` to workers. A worker may check a trace selected
from an older abstraction while another worker's refinement has already removed it. This stale work can waste CPU, but
it is sound: older abstractions accept supersets of later abstractions. `SAFE` is only reported when the current
abstraction is empty and pending refinement work has been accounted for.

## 3. Trace Selection Investigation

The `divergence-strategy` work investigates whether better trace selection can turn worker parallelism into wall-time
speedup. The maintained modes are:

- `PAPER`: the paper-style active-continuation selector.
- `LCPS`: Least-Covered Prefix Search, a local prefix-cache tie breaker.
- `BATCH_LCPS`: an always-batch baseline that fills idle worker slots from a candidate pool.
- `ADAPTIVE_BATCH_LCPS`: a fixed first-fill-or-stale batch policy.
- `BFS` and `DFS`: basic baselines only.

These modes only change the order in which accepted runs are selected. Prefix caches are diagnostics and ordering
signals; they never prove `SAFE`, prove `UNSAFE`, skip SMT checking, or skip refinement.

### 3.1 LCPS

LCPS ranks successors lexicographically:

```text
(activeContinuationCount,
 checkedPrefixCount,
 stalePrefixCount,
 distanceToAccepting)
```

The paper's active-continuation count remains first. Checked/stale prefix coverage only breaks ties or near-ties.

### 3.2 Batch LCPS

BATCH_LCPS fills several idle worker slots in one decision:

```text
slots := threadLimit - runningWorkers
pool := candidateTraces(A)
rank pool by:
  (maxSimilarityToActiveOrSelected,
   sumCheckedPrefixCoverage,
   sumStalePrefixCoverage,
   pathLength,
   wordHash)
dispatch top slots
```

The adaptive variant uses batch at the first worker-fill dispatch of each abstraction/refinement phase and later only
after stale work is observed. Otherwise it falls back to `PAPER`.

### 3.3 Selection Result

Trace selection helped individual cases but was not stable enough to be the main algorithmic claim. In the presentation
comparison against the paper baseline, category-level CPU-time changes were:

| category | LCPS / Paper | BATCH_LCPS / Paper | BFS / Paper | DFS / Paper |
|---|---:|---:|---:|---:|
| ECA | +0.63% | +7.33% | +19.26% | +26.59% |
| ControlFlow | -1.64% | -0.03% | -15.26% | -0.76% |
| Loops | -0.57% | -0.95% | +6.77% | +0.81% |
| All | -0.01% | +2.16% | +7.78% | +10.59% |

Therefore, the trace-selection modes remain default-off ablation baselines and diagnostics.

## 4. Bottleneck Reframing

Profiling changed the project direction. With four workers on a 16-core machine, the worker pool is usually saturated.
Reordering or cancelling worker work can save CPU, but it often does not reduce wall time because another worker task
immediately fills the slot.

The wall-clock critical path is usually coordinator work:

```text
wall time ~= Difference + minimization + emptiness search
```

The exception is loop-heavy programs, where the coordinator mostly waits for expensive worker SMT checks.

A complete profile of the loop-aware baseline on solved tasks gave:

| category | Difference | minimize | emptiness | worker-wait | fixed/JVM |
|---|---:|---:|---:|---:|---:|
| ECA | 34% | 0% | 8% | 36% | 22% |
| ControlFlow | 33% | 2% | 2% | 33% | 30% |
| Loops | 0% | 0% | 0% | 45% | 55% |

This explains why coordinator-only ideas have limited reach and why loops need a worker-side lever.

## 5. Final Algorithmic Levers

The final implementation uses three independent, default-off levers on top of the paper-faithful parallel CEGAR
baseline.

### 5.1 Loop-Aware Minimization

Minimization can dominate the coordinator path, but it is not always useful. On diverse-trace programs such as ECA and
ControlFlow, minimization often costs time while reducing no CEGAR iterations. On loop-unrolling programs, it remains
important because it keeps the abstraction from blowing up.

The implemented rule is:

```text
if pathProgramRecurrenceCount >= 2:
    minimize
else:
    skip minimization
```

The recurrence count is used as a loop-unrolling signal. This single per-trace gate resolves the conflict between
ECA/ControlFlow, where minimization is often overhead, and Loops, where minimization remains load-bearing.

Presentation result, three-repetition medians vs dev-PAR-4:

| category | wall vs dev | mechanism |
|---|---:|---|
| ECA | -20.7% | recurrence around 1, so minimization is skipped |
| ControlFlow | -33.7% | recurrence around 1, so minimization is skipped |
| Loops | -7.7% | recurrence at least 2, so minimization is kept |

### 5.2 Async / Pipelined Refinement

After loop-aware minimization, `Difference` still dominates short-trace ControlFlow/locks tasks:

| task | wall | Difference | Difference / wall |
|---|---:|---:|---:|
| CF locks_15-2 | 37.2 s | 27.1 s | 73% |
| CF locks_14-1 | 19.2 s | 11.9 s | 62% |
| ECA Problem13_l07 | 77.7 s | 45.3 s | 58% |

The async implementation moves `Difference` and optional minimization to one apply-helper thread. The coordinator
continues search and dispatch on the latest published abstraction stored in an `AtomicReference`.

The helper applies refinements sequentially, preserving refinement order. `SAFE` is declared only after the apply queue
drains.

The adaptive gate is:

```text
latch async when:
  Difference >= 200 ms twice
  and average trace length <= 150
otherwise stay synchronous
```

Trace length separates the categories: ControlFlow/locks traces are around 30 steps, while ECA traces are often around
700-1000 steps. Async engages where overlap is stable and stays off for long-trace ECA cases where a uniform async
policy would create too much stale search.

Result on top of loop-aware minimization:

- ControlFlow: -8.7% wall, -5.9% CPU.
- ECA and Loops: baseline-by-construction for this lever.
- 0 incorrect, no task lost.

### 5.3 Guarded Loop-Targeted Acceleration

Loops are worker-SMT-bound rather than coordinator-bound, so the loop lever targets trace checking itself. Recurring
loop traces are routed to Ultimate's accelerated trace-checking strategy, which uses Jordan loop acceleration to refute
many loop unrollings in one check when possible.

Naive acceleration is too risky: it can greatly help linear loops but inflate downstream abstraction costs on nonlinear,
polynomial, or array-heavy loops. The implementation therefore uses two guards:

1. A fire-count window. A path program is accelerated only while its recurrence is inside a bounded window.
   `maxFires = 1` captures the main win while limiting downstream damage.
2. A per-path-program time-budget blacklist. If an accelerated check exceeds the budget, that path program is not
   accelerated again.

Presentation examples:

| task | dev | ours | delta |
|---|---:|---:|---:|
| string_concat-noarr | 31.7 s | 10.0 s | -68% |
| egcd2-ll | 31.9 s | 22.8 s | -28% |
| cohencu-ll | 11.1 s | 10.4 s | -6% |
| nested_delay_nd | 8.7 s | 15.5 s | +78% bounded loss |
| discover_list | timeout | 61.3 s | newly solved |

Combined loop result: -18.5% wall on the CEGAR-bound loop set, with no task lost and 0 incorrect.

## 6. Final Configuration

Final configuration is the paper-faithful parallel CEGAR baseline plus the three default-off refinement-aware levers:

```text
Use CEGAR loop for Parallel Trace Abstraction = true
Threadlimit for Parallel CEGAR              = 4
Loop-aware minimization for Parallel CEGAR  = true   (repeat threshold = 2)
Async refinement (Parallel CEGAR)           = true
Loop-targeted acceleration (Parallel CEGAR) = true   (recurrence threshold = 2,
                                                       time budget = 1000 ms,
                                                       max fires = 1)
```

The baseline is the same PAR-4 setup with those three flags off.

The checked-path divergence modes can be enabled separately for ablation:

```text
Parallel trace search selection mode = PAPER | LCPS | BATCH_LCPS | ADAPTIVE_BATCH_LCPS | BFS | DFS
```

They are not required for the final speedup claim.

## 7. Evaluation Summary

The presentation evaluates SV-COMP ReachSafety C programs with property `unreach-call`, focusing on ECA, ControlFlow,
and Loops. The main configuration is PAR-4: four workers on a 16-core machine, ILP32.

Final result against the paper-faithful/dev PAR-4 baseline:

| category | wall vs baseline | solved | best task | worst task |
|---|---:|---:|---|---|
| ECA | -25.0% | 27 -> 29 (+2) | Problem03_label26 -53.1% | Problem14_label48 +30.0% |
| ControlFlow | -37.4% | 31 = 31 | test_locks_15-2 -78.8% | unreach_branch2 +2.6% |
| Loops | -18.5% | 46 = 46 | string_concat-noarr -68% | nested_delay_nd +78% |

There were 0 incorrect results and no task lost in the final configuration.

Measurement note: the ECA/ControlFlow/trivial-Loops headline numbers are single-repetition measurements on the
139-task set; the Loops -18.5% and the loop-aware/async layer wins are three-repetition medians. The "0 incorrect / no
task lost" claim held across the reported runs.

## 8. Soundness

All implemented levers are performance-only changes:

- Trace-selection modes change only the order in which accepted traces are dispatched.
- Prefix caches are only ordering signals.
- Skipping minimization does not skip a refinement; minimization is an optimization of the abstraction representation.
- Async refinement preserves refinement order with one helper thread.
- `SAFE` waits for the helper queue to drain.
- Stale search is sound because an older abstraction accepts a superset of the later abstraction.
- Loop acceleration uses Ultimate's trace-checking machinery; guards only decide when to expose that strategy.

The implementation explicitly avoids using heuristic signals to skip SMT checking, skip refinement, or infer
`SAFE`/`UNSAFE`.

## 9. Limitations

- Trace selection remains high-variance and should stay an experimental mode.
- Async refinement is beneficial for short-trace Difference-heavy cases, not universally.
- Loop acceleration needs guards because naive acceleration can inflate downstream abstraction cost.
- Some tasks are dominated by fixed JVM/parsing/RCFG overhead and are not reachable by the current algorithmic levers.
- The trivial `loops/` set is near the JVM/parse floor for many tasks; large category-level gains are structurally
  difficult there.

## 10. Reproduction

The maintained SV-COMP subset is tracked as [`benchmarks_run.json`](benchmarks_run.json). Full environment setup and
reproduction commands are in [`REPRODUCTION.md`](REPRODUCTION.md).

The checked-path divergence wrapper produces:

- generated benchmark CSV;
- raw runner CSV;
- enriched CSV with `category`, `subcategory`, `task`, and `expected_result`;
- compact Markdown summary for comparing `PAPER`, `LCPS`, `BATCH_LCPS`, and `ADAPTIVE_BATCH_LCPS`.

