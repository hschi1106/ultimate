# DOI: Refinement-aware Diverse Trace Selection for Parallel CEGAR

Group members: r14921035 Huang Shao-Chi, r14943074 Hsu Chen-Yun

This document summarizes the final project story from `formal_method_ppt.pdf` and records the intended
state before merging `divergence-strategy` into this branch.  Here, DOI is used as the project-level
description of implementation, design decisions, and experimental interpretation.

## 1. Project Goal

Formal verification is valuable because it can prove the absence of bugs, but full software model
checking is often too slow for continuous integration or a fast development loop. Modern machines have
many cores, so a natural question is whether trace-abstraction-based CEGAR can use those cores to reduce
wall-clock verification time without changing the safety result.

We build on Barth and Jakobs, "Multi-Threaded Software Model Checking via Parallel Trace Abstraction
Refinement" (arXiv:2509.13699), implemented in Ultimate Automizer. Their paper exposes parallelism by
running trace checks and interpolation on worker threads, while a coordinator owns the abstraction
automaton and applies refinements.

The main research question is:

> How do we turn available parallelism into real wall-time speedup, without sacrificing soundness?

## 2. Baseline: Trace Abstraction as CEGAR

Trace abstraction maintains an automaton `A` whose language is the current set of candidate error traces.
The sequential loop is:

1. Select an error trace `pi` from `L(A)`.
2. Check the trace formula `phi_pi` with SMT.
3. If the trace is feasible, report `UNSAFE`.
4. If the trace is infeasible, build an interpolant automaton `A_pi`.
5. Refine the abstraction with `A := A \ A_pi`.
6. If `L(A)` becomes empty, report `SAFE`.

In parallel trace abstraction, the coordinator selects accepted runs from `A` and dispatches them to
workers. Workers perform trace checking and interpolation and return an interpolant automaton. The
coordinator then applies the refinement.

The key soundness fact is stale-but-sound execution: a worker may check a trace selected from an older
abstraction because an older abstraction accepts a superset of the traces accepted by a later abstraction.
This can waste work, but it cannot produce an incorrect verdict. `SAFE` is only valid when the current
abstraction is empty and all pending refinement work has been accounted for.

## 3. Initial Hypothesis: Better Trace Selection

The paper's diverse selector prefers traces that diverge early from already active traces. This spreads
workers across different regions of the abstraction but does not know whether a region has already caused
expensive checked work, stale work, or a coordinator bottleneck.

The `divergence-strategy` branch implements this trace-selection investigation. The maintained modes are:

- `PAPER`: the paper-style active-continuation selector.
- `LCPS`: Least-Covered Prefix Search, a local prefix-cache tie breaker.
- `BATCH_LCPS`: an always-batch baseline that fills idle worker slots from a candidate pool.
- `ADAPTIVE_BATCH_LCPS`: a fixed first-fill-or-stale batch policy.
- `BFS` / `DFS`: basic baselines only.

These modes only change trace order. They do not use cache data to prove `SAFE` or `UNSAFE`, and they do
not skip trace checking or refinement.

### 3.1 LCPS

LCPS augments the paper selector with a prefix coverage cache:

```text
rank successor by:
  (activeContinuationCount,
   checkedPrefixCount,
   stalePrefixCount,
   distanceToAccepting)
```

The active-continuation count remains first to preserve the paper heuristic. The checked/stale prefix
counts only break ties or near-ties and are used only as ordering signals.

### 3.2 BATCH_LCPS

The paper loop dispatches traces one by one. BATCH_LCPS instead fills several idle slots in one decision:

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

The intended benefit is to prevent several workers from immediately reclustering in nearby trace regions.

### 3.3 Result of Trace Selection

Trace selection helped locally but was not stable enough to become the default algorithm. In the
presentation's comparison against the paper baseline, the category-level CPU-time changes were:

| category | LCPS / Paper | BATCH_LCPS / Paper | BFS / Paper | DFS / Paper |
|---|---:|---:|---:|---:|
| ECA | +0.63% | +7.33% | +19.26% | +26.59% |
| ControlFlow | -1.64% | -0.03% | -15.26% | -0.76% |
| Loops | -0.57% | -0.95% | +6.77% | +0.81% |
| All | -0.01% | +2.16% | +7.78% | +10.59% |

The conclusion is that selection order can rescue individual high-stale cases, but it does not reliably
move the overall wall-time bottleneck. Therefore, after merging `divergence-strategy`, these modes should
remain default-off ablation baselines and diagnostics, not the main algorithmic claim.

## 4. Reframing: From "Which Trace?" to "Which Refinement?"

Profiling changed the project direction. With 16 cores and 4 workers, the worker pool is usually
saturated. Reordering or cancelling worker work can save CPU, but it often does not reduce wall time
because another worker task immediately fills the slot.

The wall-clock critical path is usually the coordinator's serial work:

```text
wall time ~= Difference + minimization + emptiness search
```

The exception is loop-heavy programs, where the coordinator mostly waits for expensive worker SMT checks.

This gives a more useful question:

> What is on the critical path for this benchmark region, and can we remove or overlap it?

The final implementation uses three default-off, independently sound levers.

## 5. Lever 1: Stale-work Early Stop Was Not Enough

The first attempt was to cancel in-progress traces that became excluded by a later refinement. This is
sound because those traces no longer contribute to the current abstraction, but it did not produce a
wall-time win.

Presentation result at PAR-4:

| category | CPU time ours / paper |
|---|---:|
| ECA | +1.17% |
| ControlFlow | +0.26% |
| Loops | +1.06% |
| All | +0.99% |

The reason is worker saturation. With four workers on a 16-core machine, saved worker CPU does not
necessarily shorten the coordinator's wall-clock critical path.

## 6. Lever 2: Loop-aware Minimization

Minimization can dominate the coordinator path, but it is not always useful. On diverse-trace programs
such as ECA and ControlFlow, minimization often costs time while reducing zero CEGAR iterations. On loop
unrolling programs, it is still important because it keeps the abstraction from blowing up.

The implemented rule is:

```text
if pathProgramRecurrenceCount >= 2:
    minimize
else:
    skip minimization
```

The recurrence count is used as a loop-unrolling signal. This single per-trace gate resolves the conflict
between ECA/ControlFlow, where minimization is often overhead, and Loops, where minimization remains
load-bearing.

Presentation result, three-repetition medians vs dev-PAR-4:

| category | wall vs dev | mechanism |
|---|---:|---|
| ECA | -20.7% | recurrence around 1, so minimization is skipped |
| ControlFlow | -33.7% | recurrence around 1, so minimization is skipped |
| Loops | -7.7% | recurrence at least 2, so minimization is kept |

This is the foundation for the final result.

## 7. Lever 3: Async / Pipelined Refinement

After loop-aware minimization, profiling showed that `Difference` still dominates short-trace
ControlFlow/locks tasks:

| task | wall | Difference | Difference / wall |
|---|---:|---:|---:|
| CF locks_15-2 | 37.2 s | 27.1 s | 73% |
| CF locks_14-1 | 19.2 s | 11.9 s | 62% |
| ECA Problem13_l07 | 77.7 s | 45.3 s | 58% |

The async implementation moves `Difference` and optional minimization to one apply-helper thread. The
coordinator continues search and dispatch on the latest published abstraction stored in an
`AtomicReference`.

The helper preserves refinement order by applying refinements sequentially. `SAFE` is declared only after
the apply queue drains.

The important design point is the adaptive gate:

```text
latch async when:
  Difference >= 200 ms twice
  and average trace length <= 150
otherwise stay synchronous
```

Trace length separates the categories: ControlFlow/locks traces are around 30 steps, while ECA traces are
often around 700-1000 steps. This means async engages where overlap is stable and stays off for long-trace
ECA cases where a uniform async policy would create too much stale search.

Presentation result on top of loop-aware minimization:

- ControlFlow: -8.7% wall, -5.9% CPU.
- ECA and Loops: baseline-by-construction.
- 0 incorrect, no task lost.

## 8. Lever 4: Safe Loop Acceleration

Loops are worker-SMT-bound rather than coordinator-bound. Coordinator-side changes cannot reduce the
dominant cost there, so the final loop lever targets the worker trace-check itself.

The implementation routes recurring loop traces to Ultimate's accelerated trace-checking strategy, using
Jordan loop acceleration to collapse multiple loop unrollings into one check when possible.

Naive acceleration is unsafe as a performance optimization: it can greatly help linear loops but can
inflate downstream abstraction costs on nonlinear, polynomial, or array-heavy loops. The implementation
therefore keeps acceleration guarded:

1. Use a fire-count window. A path program is accelerated only while its recurrence is inside a bounded
   window. `maxFires = 1` captures the main win while limiting downstream damage.
2. Use a per-path-program time-budget blacklist. If an accelerated check exceeds the budget, that path
   program is not accelerated again.

Presentation examples:

| task | dev | ours | delta |
|---|---:|---:|---:|
| string_concat-noarr | 31.7 s | 10.0 s | -68% |
| egcd2-ll | 31.9 s | 22.8 s | -28% |
| cohencu-ll | 11.1 s | 10.4 s | -6% |
| nested_delay_nd | 8.7 s | 15.5 s | +78% bounded loss |
| discover_list | timeout | 61.3 s | newly solved |

Combined loop result: -18.5% wall on the CEGAR-bound loop set, with no task lost and 0 incorrect.

## 9. Bottleneck Attribution

The final interpretation depends on where wall time is spent. Profiling the loop-aware baseline on solved
tasks gave:

| category | Difference | minimize | emptiness | worker-wait |
|---|---:|---:|---:|---:|
| ECA | 34% | 0% | 8% | 36% |
| ControlFlow | 33% | 2% | 2% | 33% |
| Loops | 0% | 0% | 0% | 45% |

Residual fixed overhead and worker SMT dominate a large fraction of total time. This explains why
coordinator-only ideas have limited reach and why loops require a worker-side accelerator.

## 10. Final Evaluation

The presentation evaluates SV-COMP ReachSafety C programs with property `unreach-call`, focusing on a
139-task subset across ECA, ControlFlow, and Loops. The main configuration is PAR-4: four workers on a
16-core machine, ILP32, 150 s wall-clock limit. Correctness is checked on every run.

Final result against the paper-faithful/dev PAR-4 baseline:

| category | wall vs baseline | solved | best task | worst task |
|---|---:|---:|---|---|
| ECA | -25.0% | 27 -> 29 (+2) | Problem03_label26 -53.1% | Problem14_label48 +30.0% |
| ControlFlow | -37.4% | 31 = 31 | test_locks_15-2 -78.8% | unreach_branch2 +2.6% |
| Loops | -18.5% | 46 = 46 | string_concat-noarr -68% | nested_delay_nd +78% |

There were 0 incorrect results and no task lost in the final configuration.

The main conclusion is:

> Diverse trace selection exposes parallelism, but it is not sufficient for speedup. The stable win comes
> from refinement-aware scheduling and guarded worker-side acceleration.

## 11. Implementation Components

The codebase now contains two complementary sets of changes.

### 11.1 Current `doi` / `parallel-cegar-async` line

This branch contains the final algorithmic story:

- loop-aware minimization,
- async / pipelined refinement with an adaptive gate,
- guarded loop-targeted acceleration,
- final report in `PARALLEL_CEGAR_REPORT.md`.

These are the levers that support the headline performance claim.

### 11.2 To be merged from `divergence-strategy`

The `divergence-strategy` branch adds trace-selection modes, prefix-cache instrumentation, benchmark
discovery, SV-COMP subset runners, and plotting/reporting utilities. After the merge, the intended scope
is:

- keep `PAPER` as the stable baseline/default;
- keep `LCPS`, `BATCH_LCPS`, and `ADAPTIVE_BATCH_LCPS` as default-off ablation baselines;
- keep checked-path divergence and prefix-cache statistics as diagnostics;
- do not present LCPS/BATCH_LCPS as the final speedup mechanism.

This is consistent with the presentation: selection is useful for explaining the search space and some
local wins, but the final speedup comes from targeting the actual critical path.

## 12. Soundness Summary

All implemented levers are intended to be performance-only changes:

- Trace-selection modes change only the order in which accepted traces are dispatched.
- Prefix caches are only ordering signals; they never prove emptiness or feasibility.
- Skipping minimization does not remove a necessary refinement; minimization is an optimization of the
  abstraction representation.
- Async refinement preserves refinement order with one helper thread, and `SAFE` waits for the queue to
  drain.
- Stale search is sound because an older abstraction accepts a superset of the later abstraction.
- Loop acceleration uses Ultimate's trace-checking machinery and is only enabled or disabled by guards;
  the guards affect performance exposure, not the logical interpretation of a trace result.

The project explicitly avoids using heuristic signals to skip SMT checking, skip refinement, or infer
`SAFE`/`UNSAFE`.

## 13. Limitations and Next Steps

The measured results are region-dependent:

- Trace selection remains high-variance and should stay an experimental mode.
- Async refinement is beneficial for short-trace Difference-heavy ControlFlow cases, not for all ECA
  cases.
- Loop acceleration needs guards because naive acceleration can inflate downstream abstraction cost.
- Some tasks are dominated by fixed JVM/parsing/RCFG overhead; these are not reachable by the current
  algorithmic levers.

After merging `divergence-strategy`, the next engineering step is to keep the reporting stack clean:

1. preserve raw diagnostics for selection and divergence experiments;
2. avoid committing large raw logs unless they are deliberately part of an artifact;
3. keep the final default configuration tied to the refinement-aware levers, not the trace-selection
   ablations;
4. rerun a focused final experiment only after resolving merge conflicts and confirming that all
   default-off preferences remain off in the paper baseline.

