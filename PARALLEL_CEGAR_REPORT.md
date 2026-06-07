# Parallel Trace Abstraction — improving the coordinator over the paper baseline

Reproduction and extension of **Barth & Jakobs, "Multi-Threaded Software Model Checking via Parallel
Trace Abstraction Refinement"** (arXiv:2509.13699) in Ultimate Automizer. The work adds several
**flag-gated** improvements to the parallel-CEGAR coordinator, all **default OFF** so the paper baseline
is preserved bit-for-bit when no flag is set. It spans two branches, each a self-contained layer:

- **`r1-staleness-prefilter`** (commit `d8861b9ac6`) — the **loop-aware minimization** win (§2–§5).
- **`parallel-cegar-async`** (off `d8861b9ac6`) — **async / pipelined refinement** on top of loop-aware
  (§6, this session).

**Goal:** at the **same thread count (PAR-4 vs PAR-4)**, beat the paper-faithful `dev`-branch parallel
CEGAR on wall-clock and CPU time, with the same solved set and zero wrong verdicts — across the
ReachSafety **ECA, ControlFlow, and Loops** categories.

**Headline result (layer 1, vs `dev`-PAR-4):** a single config — **loop-aware minimization** — beats
`dev`-PAR-4 on wall time in **all three** categories: **ECA −20.7%, ControlFlow −33.7%, Loops −7.7%**,
0 incorrect, solved set same-or-better. Measured with BenchExec `runexec` on a 16-core node, 120 s wall
limit.

**Headline result (layer 2, vs loop-aware itself):** **async refinement** further cuts the coordinator's
serial `Difference` cost on **ControlFlow by −8.7 % wall / −5.9 % CPU**, while leaving ECA and Loops
exactly at the loop-aware baseline (the adaptive gate engages only where it stably wins) — 0 incorrect,
identical solved set, no task lost. See §6.

---

## 1. The baseline (original `dev` code)

The parallel CEGAR loop is a **coordinator + workers** design
(`ParallelNwaCegarLoop` + `CegarNwaWorkerThread`):

- The **coordinator** (main thread) owns the single evolving abstraction automaton. Its loop:
  search for an error trace (`IsEmpty`/`IsEmptyParallel`) → hand the counterexample to a worker →
  when a worker returns an interpolant automaton, apply the refinement
  (`mAbstraction = Difference(mAbstraction, subtrahend)`) and then **minimize** the abstraction.
- **Workers** (N threads) each own a fresh SMT script; they run trace-check + interpolation in parallel
  and return an interpolant automaton. The abstraction is transferred to each worker once at construction.
- **Alg. 4** is the coordinator's trace-selection heuristic; in the code it returns the next
  BFS/`IsEmptyParallel` error trace not already in flight.
- Minimization runs in the **per-worker** path (`minimizeAbstractionPerWorker` defaults true), i.e. after
  each refinement, using `Minimization of abstraction = MINIMIZE_SEVPA`.

**Key structural fact** (verified, and it drives every result below): with 16 cores and 4 workers, the
workers sit on otherwise-idle cores, so **wall time is determined by the coordinator's serial critical
path: `Difference` + minimization + emptiness search.** Worker work is "free" until the coordinator
blocks waiting for a result.

---

## 2. What this branch adds (difference from baseline)

All additions are new **preferences** (TraceAbstraction plugin), **default OFF**. Diff vs `dev`:
**9 files, +1481 / −18 lines**
(`ParallelNwaCegarLoop`, `CegarNwaWorkerThread`, `WorkerTask`, `WorkerThreadResult`,
`StaleCancellationToken/Point`, `SharedPredicatePool`, `TAPreferences`,
`TraceAbstractionPreferenceInitializer`, `StrategyFactory`).

### The winning lever — Loop-aware minimization (ships ON in `UA-LOOPAWARE`)
Pref **`Loop-aware minimization for Parallel CEGAR`** (+ `repeat threshold`, default 2).
Minimize a refinement **only when its counterexample's path program has recurred ≥ threshold times** — a
loop-unrolling signal (the same loop body refined repeatedly). Diverse-trace programs (ECA/ControlFlow)
keep the count ~1, so they **skip** minimization and gain the wall-time win of not minimizing; loop-
unrolling programs **trigger** it, so the abstraction blowup stays bounded. One per-trace-adaptive policy
resolves the tension "ECA wants no minimization / loops need it." Implemented in the per-worker path
(`minimizeGivenLoopAware`). Sound: minimization is language-preserving, so skipping/deferring never
changes a verdict.

### Simpler companion — `Minimization of abstraction = NONE` (`UA-P4N`)
A stock preference (no code), shipped as the `UA-P4N` config: never minimize. Wins ECA + ControlFlow,
ties Loops. Strong and trivially simple, but slightly worse than loop-aware on loop-heavy programs (the
un-minimized abstraction is larger).

### Explored and **rejected** (flag-gated, default OFF — kept for the record)
| pref / lever | idea | result |
|---|---|---|
| `Stale worker cancellation` (COOPERATIVE) (S2) | cancel in-flight traces no longer accepted after a refinement | wins control-flow, **loses ECA** (the `Accepts` re-check is costly on long traces) |
| `Asynchronous stale sweep` (R6) | move the S2 re-check off the coordinator critical path | **rejected** — proves the regression is the *cancellation* (cancels useful diverse work), not the check cost |
| `Adaptive worker scaling` (S3), `Trace selection = DPPI` | gate / reorder by path-program overlap | neutral |
| `Path-program staleness pre-filter` (R1) | cheap subtrahend staleness check | dead (cancels 0 — staleness is cumulative) |
| `Cross-worker predicate sharing` (R4) | seed workers' unifiers with harvested interpolants | flat / slightly worse |
| `Worker strategy portfolio` (diversify) | pin each worker to a different solver/interpolation strategy | **rejected** — loses 11 tasks (a critical trace gets a weaker-than-default strategy) |
| `Race bottleneck trace` | re-dispatch the longest in-flight trace to idle workers, race strategies | **inert** — workers are never idle (always ≥4 fresh traces) |
| `Relative-growth minimization` | minimize on relative abstraction growth | superseded by loop-aware |
| `Trace selection = DIVERSITY` | IDF-weighted path-program novelty ranking (see §5) | **does not beat Alg.4** — the metric is sound but selection can't pay off here |

---

## 3. Results

Benchmarks: SV-COMP ReachSafety, ILP32, `unreach-call.prp`. Enlarged set = 139 tasks
(ECA 50 / ControlFlow 39 / Loops 50) + 50 CEGAR-bound loop tasks (loop-invgen / loop-lit / loop-invariants
/ nla-digbench / loops-crafted-1). `runexec`, 120 s wall limit.

### Win-all-3 (loop-aware vs dev-PAR-4), per category
| category | wall | CPU | solved |
|---|---|---|---|
| **ECA** | **−20.7%** (18W/5L) | +1.8% (tie) | 29 = 29 |
| **ControlFlow** | **−33.7%** (13W/2L) | **−23.4%** | 28 = 28 |
| **Loops** | **−7.7%** (median **−11.6%** on tasks with room) | **−7.1%** | **+1** |

**0 incorrect** everywhere; solved set same-or-better. The Loops win is 3×-median-confirmed and concentrated
in CEGAR-bound loops (egcd2-ll −22.9%, string_concat-noarr −16.2%, invert_string-3 −13.1%).

### `UA-P4N` (Minimization=NONE) vs dev-PAR-4 (enlarged set)
Overall **wall −21.7% / CPU −6.6%**, 97 = 97 solved, 0 incorrect (ECA −21.1%, ControlFlow −33.2%, Loops
−1.1% tie). 3-rep medians on the original 22-task mix: −11.7% wall / −7.1% CPU.

### Why this works, not the alternatives
The serial critical path is minimization (wasteful on diverse-trace programs, load-bearing on loop
unrolling) — so a **per-trace minimization policy** wins. Cancellation/selection/scaling levers
(S2/S3/DPPI/R1/R4/R6/portfolio/race/diversity — 7 attempts) do **not** beat the baseline, because the
implementation is worker-saturated (idle cores make wasted-work reduction free in CPU but invisible in
wall) and selection-ranking pays a per-candidate emptiness-search tax.

---

## 4. The diversity metric (derivation), and why selection still can't beat Alg.4

Requested as a principled replacement for Alg.4's heuristic. Two prior metrics were flawed: DPPI's raw
edge-overlap (the shared structural core — entry/error/loop-heads — makes everything look similar), and
the teammate's predicate-state tree-distance (`BasicPredicate` identity changes every iteration). The
derived metric fixes both:

- Represent each trace by its **path program** P(t) = the set of CFG edges (the infeasibility-reason proxy).
- Weight each edge by **inverse document frequency** `idf(e) = log((1+N)/(1+df(e)))` over dispatched path
  programs: the structural core (in every trace) gets `idf ≈ 0`; rare reason-bearing edges dominate.
- **Novelty** of a candidate vs the in-flight set = fraction of its idf-weight on edges not already
  covered = how much *new* infeasibility reason it brings. Select the max-novelty trace (fairness floor
  preserves soundness/termination).

Implemented as `Trace selection strategy = DIVERSITY`. The metric **fires and ranks correctly**, but
measured against Alg.4 it is **neutral-to-worse** (ECA +2.7% wall / −3 solved, ControlFlow +0.5%, Loops
−1.5%). The reason is structural, not the metric's quality: (1) ranking requires K extra emptiness
searches per dispatch; (2) with 4 workers and always ≥4 findable traces, selection can only reorder (no
gain) or defer (reduces parallelism). Beating Alg.4 would require **cheap multi-candidate generation**
(one automaton traversal yielding several accepting runs — an out-of-plugin Library-Automata change), not
a better metric.

---

## 5. Build & reproduce

```bash
source /home/cycloud/toolchain/env.sh           # Temurin JDK 21 + Maven 3.9
cd ultimate/trunk/source/BA_MavenParentUltimate
mvn -T 1C -pl ../TraceAbstraction -am install -Dmaven.test.skip=true   # ~1 min, incremental
# the OSGi bundle is trunk/source/TraceAbstraction/target/...traceabstraction-0.3.1.jar;
# copy it into a packaged Automizer's plugins/ dir and set the prefs in
# config/svcomp-Reach-32bit-Automizer_Default.epf
```
Winning config (`UA-LOOPAWARE`): `Use CEGAR loop for Parallel Trace Abstraction=true`,
`Threadlimit=4`, `Loop-aware minimization for Parallel CEGAR=true`.
Baseline (`UAutomizer-dev`): the same minus the loop-aware flag.

Raw data: `run/results/results_{big,hardloops,loopaware,loopmedian,sweep,diversity}.csv`; analysis scripts
`run/analyze_*.py`; full experimental log `run/comparison_summary.md`.

---

## 6. Async / pipelined refinement — beating `UA-LOOPAWARE` on the coordinator serial path

A second improvement, on branch **`parallel-cegar-async`** (off `d8861b9ac6`), targets the coordinator's
remaining serial cost. New pref **`Async refinement (Parallel CEGAR)`**, **default OFF**, baseline
preserved bit-for-bit.

**Baseline for this section is `UA-LOOPAWARE` = `r1-staleness-prefilter` @ `d8861b9ac6` with loop-aware
ON** (the layer-1 winner). So every number below is the *additional* gain over r1-staleness-prefilter,
not over `dev`.

### N0 — profiling first (the discipline that drove this)
With loop-aware ON, millisecond instrumentation of the coordinator serial path (`run/N0_PROFILE.md`)
shows, on smoke tasks per category:
- **`Difference` dominates** the serial path on ECA (up to 58 % of wall) and ControlFlow (up to 73 %).
- Minimization ≈ 0 (loop-aware already skips it); emptiness ≤ 2.1 s (so an incremental-emptiness lever
  is **not** justified).
- **Loops are worker-SMT-bound** — the coordinator serial path is < 150 ms while wall is 11–39 s; there
  is nothing on the coordinator to overlap.

### The lever
A single dedicated **apply-helper thread** applies `Difference` (+ loop-aware minimize when it fires)
off the coordinator's critical path and publishes each new abstraction via an `AtomicReference`; the
coordinator keeps searching/dispatching on the latest published version and adopts newer ones as they
arrive. Soundness is the paper's §3.1 (a stale abstraction for emptiness/trace-search is sound;
refinement order is preserved by the single sequential helper, so no commutative aggregation is needed).
SAFE is declared only after the apply queue drains and the fully-refined abstraction is empty.

Four mechanisms make it correct and safe:
1. **Master managed script** for the off-thread Difference (the worker's script is reused per task;
   the master script is not), so a deferred apply never races a worker.
2. **FIFO avoid-set deferral** — a dispatched trace stays in the search avoid-set until its refinement
   is *applied*, so the coordinator never re-dispatches it on the stale abstraction.
3. **Helper-side stale-skip** — an `Accepts` check skips a refinement whose trace an earlier refinement
   already removed (sound; avoids wasted Difference work).
4. **Adaptive gate with a trace-length guard** — the run starts synchronous and latches into async only
   once Differences are expensive (≥ 200 ms, twice) **and** the average counterexample trace is short
   (≤ 150). Measured trace lengths separate cleanly: ControlFlow/locks ≈ 30, ECA ≈ 700–1000. So async
   engages only on short-trace, Difference-dominated programs, where the overlap is stable and the
   stale-skip check is cheap; long-trace ECA stays synchronous (where async would inflate dispatches and
   risk a timeout).

This **reverses the naive expectation** that ECA (most Difference-dominated) is the prime target: a
*uniform* async policy wins hard ECA but regresses ControlFlow/Loops and can time out on ECA. The gate
confines async to where it stably wins.

### Results — `UA-N1ASYNC` (loop-aware ON + async ON) vs `UA-LOOPAWARE`, enlarged set (139 tasks)
`runexec`, 120 s wall, ILP32, same session, wall/CPU summed over commonly-solved tasks:

| category | wall | CPU | solved |
|---|---|---|---|
| **ControlFlow** | **−8.7 %** | **−5.9 %** | 28 = 28 |
| ECA | −1.6 % | −3.4 % | 29 = 29 |
| Loops | −0.6 % | −2.0 % | 37 = 37 |

**0 incorrect**, solved set identical (97 = 97), **no task lost**. Only the 3 hard-`locks` tasks latched
into async (each **−19 % to −35 %** wall, 3×-rep stable); every ECA/Loops/other task ran the identical
synchronous baseline path, so their ties are baseline **by construction** (the small deltas are
measurement noise) — regression is structurally impossible. The ControlFlow category win is fully
attributable to those 3 locks tasks.

Config (`UA-N1ASYNC`): `UA-LOOPAWARE` + `Async refinement (Parallel CEGAR)=true`. Raw data:
`run/results/results_eval_{la,async}.csv`; design + per-task evidence: `run/N1_RESULT.md`,
`run/N0_PROFILE.md`.

### N3 (reduce iteration count) — investigated, **rejected** (no code shipped)
To help the worker-bound Loops (which N1 cannot), we tried to cut refinements-to-convergence by toggling
existing Ultimate generalization/acceleration options (per the brief: toggle before coding). All fail in
the parallel CEGAR: `Trace refinement strategy=ACCELERATED_INTERPOLATION` (loop acceleration) and
`Interpolants consolidation=true` **hang even trivial tasks** (setup-phase hang — the per-worker
transferred SMT-script/abstraction-snapshot does not provide the infrastructure these strategies need);
`Interpolant automaton enhancement=EAGER` explodes (timeouts, no iteration reduction). The current
`PREDICATE_ABSTRACTION` + `FPandBP` + `CAMEL` is the only working configuration. The custom
`ACCELERATED_TRACE_CHECK` hook is therefore not viable either. Also, the loops are slow from per-iteration
worker SMT on long traces, not from many iterations (already low: 17–31), so iteration reduction has
little headroom. Detail: `run/N3_RESULT.md`.

### Net change of this session vs `r1-staleness-prefilter`
| category | vs r1-staleness-prefilter | source |
|---|---|---|
| **ControlFlow** | **−8.7 % wall / −5.9 % CPU** (real, flag-gated) | N1 async, on 3 hard-`locks` tasks |
| ECA | unchanged (identical code path; N1 gate off, N3 rejected) | — |
| Loops | unchanged (worker-SMT-bound; N1 can't help, N3 hangs) | — |

0 incorrect, solved set unchanged (97 = 97). The session's deliverable is a **flag-gated ControlFlow
accelerator** that never regresses ECA/Loops. ECA/Loops were not improved beyond what loop-aware already
achieved — N0 explains why (Loops are bounded by per-iteration worker SMT, which no coordinator-side lever
touches) and N3 confirms the iteration-count route is closed in this parallel CEGAR.
