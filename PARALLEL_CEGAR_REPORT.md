# Improving Parallel Trace Abstraction over the paper baseline

Reproduction and extension of **Barth & Jakobs, "Multi-Threaded Software Model Checking via Parallel
Trace Abstraction Refinement"** (arXiv:2509.13699) in **Ultimate Automizer**.

We add **three coordinator/worker improvements** to the parallel-CEGAR loop, all **flag-gated and default
OFF** so the paper-faithful `dev` baseline is preserved bit-for-bit when no flag is set. Goal: at the
**same thread count (PAR-4 vs PAR-4)**, beat the `dev` parallel CEGAR on wall-clock time across the
ReachSafety **ECA, ControlFlow, and Loops** categories, with the same-or-better solved set and **zero
wrong verdicts**.

## Headline result — final config vs `dev`-PAR-4

One config (`dev` + the three flags below) beats the baseline in every category, with **0 incorrect** and
**no task lost** anywhere:

| category | wall vs `dev` | solved | measurement |
|---|---|---|---|
| **ECA** | **−25.0 %** | **+2** (27 → 29) | 139-task set |
| **ControlFlow** | **−37.4 %** | 31 = 31 | 139-task set |
| **Loops** (CEGAR-bound) | **−18.5 %** | 9 = 9, no loss | hard-loop set, 3-rep medians |
| Loops (trivial `loops/` set) | ≈ neutral | 37 = 37 | floor-bound — see §6 |

Each lever is independently sound (language-preserving or stale-but-sound per the paper's §3.1), so none
can change a verdict.

---

## 1. The `dev` baseline (what we started from)

Parallel CEGAR is a **coordinator + workers** design (`ParallelNwaCegarLoop` + `CegarNwaWorkerThread`):

- The **coordinator** (main thread) owns the single evolving abstraction automaton and loops:
  find an error trace (`IsEmpty`) → hand the counterexample to a worker → when the worker returns an
  interpolant automaton, **apply the refinement** `abstraction = Difference(abstraction, subtrahend)`
  and then **minimize** the abstraction.
- **Workers** (4 threads) each own a fresh SMT script, run trace-check + interpolation in parallel, and
  return an interpolant automaton.
- **Alg. 4** selects the next not-in-flight BFS error trace.

**The one structural fact that drives everything:** with 16 cores and 4 workers, the workers run on
otherwise-idle cores, so **wall-clock time is set by the coordinator's serial critical path**
(`Difference` + minimization + emptiness search), *except* on loop programs, which are bounded instead by
**per-iteration worker SMT cost**. We confirmed this with a full wall-time attribution (§5).

---

## 2. What is different from `dev` (summary)

Three new preferences in the TraceAbstraction plugin, all **default OFF**; our config turns them ON on top
of the standard PAR-4 settings (`Use CEGAR loop for Parallel Trace Abstraction=true`, `Threadlimit=4`):

| # | lever | what it changes vs `dev` | targets |
|---|---|---|---|
| 1 | **Loop-aware minimization** | minimize a refinement **only** when its path program is recurring (a loop signal); otherwise skip | ECA + ControlFlow (skip wasted minimization) **and** Loops (still bound blowup) |
| 2 | **Async / pipelined refinement** | apply `Difference`(+minimize) on a helper thread **off** the coordinator critical path; coordinator searches the latest published abstraction | ControlFlow (Difference-bound, short traces) |
| 3 | **Safe loop acceleration** | route recurring loop traces to **Jordan loop acceleration**, guarded so it only ever helps | hard CEGAR-bound Loops |

Each is detailed below with *what*, *why*, and *result*.

---

## 3. Lever 1 — Loop-aware minimization

**What.** Pref `Loop-aware minimization for Parallel CEGAR` (+ `repeat threshold`, default 2). Minimize a
refinement **only when its counterexample's path program has recurred ≥ threshold times** — a loop-
unrolling signal (the same loop body being refined again). Diverse-trace programs (ECA/ControlFlow) keep
the recurrence ~1 and therefore **skip** minimization; loop-unrolling programs **trigger** it.

**Why.** Minimization sits on the coordinator's serial critical path. On diverse-trace programs it does
**not** reduce CEGAR iterations — it is pure overhead. On loop unrolling it is **load-bearing** — it keeps
the abstraction from blowing up. `dev` applies it unconditionally; `NONE` removes it unconditionally (good
for ECA, unsafe for loops). One **per-trace-adaptive** policy resolves the tension.

**Result (vs `dev`-PAR-4):** wins all three categories — **ECA −20.7 %, ControlFlow −33.7 %, Loops −7.7 %**
(3-rep medians), 0 incorrect, solved same-or-better. This is the foundation the other two levers build on.

---

## 4. Lever 2 — Async / pipelined refinement

**What.** Pref `Async refinement (Parallel CEGAR)`. A single dedicated **apply-helper thread** runs
`Difference` (+ loop-aware minimize) off the coordinator's critical path and publishes each new abstraction
via an `AtomicReference`; the coordinator keeps searching and dispatching on the latest published version.
Soundness is the paper's §3.1 — searching a slightly stale abstraction is sound, and a *single* sequential
helper preserves refinement order. SAFE is declared only after the apply queue drains.

**Why.** Profiling (§5) with loop-aware ON shows `Difference` still dominates the coordinator serial path
on short-trace, Difference-heavy programs (ControlFlow/locks). Overlapping it with the search hides that
cost.

**Key design point — the adaptive gate.** The run starts synchronous and latches into async only when
Differences are expensive (≥ 200 ms, twice) **and** the average counterexample trace is short (≤ 150).
Measured trace lengths separate cleanly: ControlFlow/locks ≈ 30, ECA ≈ 700–1000. So async engages only
where the overlap is stable; long-trace ECA stays synchronous (where a uniform async policy would inflate
dispatches and risk timeouts). This **reverses** the naive guess that ECA — the most Difference-dominated
category — is the prime target.

**Result (added on top of loop-aware):** **ControlFlow −8.7 % wall / −5.9 % CPU**, ECA/Loops unchanged by
construction (the gate engages on only the 3 hard-`locks` tasks, each −19 % to −35 %; every other task runs
the identical synchronous path). 0 incorrect, no task lost.

---

## 5. Lever 3 — Safe loop acceleration (the Loops win)

This is the lever that finally moves **Loops**, which levers 1–2 cannot (loops are worker-SMT-bound, not
coordinator-bound — see the attribution table below).

**What.** Pref `Loop-targeted acceleration (Parallel CEGAR)`. When a counterexample's path program is
recurring (a deep loop unrolling), route it to Ultimate's `ACCELERATED_TRACE_CHECK`, which uses **Jordan
loop acceleration** to compute a loop's closed form and **refute many unrollings in one trace check**
instead of one per iteration.

**Why it is hard — and the core finding.** Jordan acceleration is a large **win on linear loops**
(`string_concat-noarr` 31.7 s → 10.0 s, **−68 %**) but a **loss or blow-up on nonlinear / polynomial /
array loops**: there the accelerated check is often *cheap*, yet the closed-form invariant it produces
**explodes the abstraction downstream** (`cohencu-ll`, `nested_delay_nd` → timeout). So neither recurrence
depth nor a per-check time budget alone can separate winners from losers — the damage is *downstream of the
worker*. **Two guards together** make it safe:

1. **Fire-count window `[threshold, threshold + maxFires)`** — accelerate a path program only while it is
   still recurring inside the window. A loop that acceleration *collapses* stops recurring within ~one step;
   one that keeps recurring is not being collapsed, so we stop. **`maxFires = 1` is the sweet spot**: a
   single acceleration captures the win and bounds the downside. (`maxFires ≥ 2` re-introduces the
   `cohencu`/`nested_delay` blow-ups.)
2. **Per-path-program time-budget blacklist** (1000 ms; a shared concurrent set in the coordinator) — if an
   accelerated check exceeds the budget, that path program is never accelerated again. This catches the rare
   single catastrophic Jordan computation (e.g. an 11.6 s check on `invert_string-3`).

**Result (combined config vs `dev`, CEGAR-bound loop set, 3-rep medians): −18.5 %** wall, no task lost,
0 incorrect. Driven by:

| task | `dev` | ours | Δ |
|---|---|---|---|
| string_concat-noarr | 31.7 s | 10.0 s | **−68 %** |
| egcd2-ll | 31.9 s | 22.8 s | **−28 %** |
| cohencu-ll | 11.1 s | 10.4 s | −6 % (was a **lost task** without the guard) |
| nested_delay_nd | 8.7 s | 15.5 s | +78 % — **bounded** (was +1223 % / timeout without the guard) |
| discover_list | timeout | 61.3 s | **newly solved** |

The guards convert what was a "one big win, several catastrophes" lever (Session-6 finding: a naive
recurrence gate is *not* shippable) into a net win that never loses a task.

---

## 6. Why nothing else helped — bottleneck attribution

A complete profile of the loop-aware baseline (worker-wait timer added; 20 solved tasks, % of wall):

| category | Difference | minimize | emptiness | **worker-wait** | **fixed/JVM** |
|---|---|---|---|---|---|
| ECA | 34 % | 0 % | 8 % | **36 %** | 22 % |
| ControlFlow | 33 % | 2 % | 2 % | **33 %** | 30 % |
| Loops | **0 %** | 0 % | 0 % | **45 %** | 55 % |

The real cost is **worker SMT (worker-wait)** + **fixed overhead** (JVM/parse/RCFG) — 58 % / 63 % / 100 %
of wall — none of it reachable by a coordinator-side lever. `Difference` is only ~⅓ on ECA/CF and **0 % on
Loops**. This is *why* the coordinator levers (loop-aware, async) target ECA/CF, and why Loops needed a
**worker-side** lever (acceleration cuts the number of expensive worker trace-checks).

It also explains the two honest caveats:

- **The trivial `loops/` benchmark stays ≈ neutral.** 27 of its 37 solved tasks finish at the ~4 s
  JVM/parse floor (irreducible, identical for every config), and the loops that *do* have CEGAR headroom
  (string_concat, egcd2, …) live in other families. A −10 % *category average* is structurally unattainable
  there; the −18.5 % above is on exactly the loops that can be optimized.
- **Coordinator selection/cancellation ideas do not beat the baseline.** Stale-cancellation, adaptive
  worker scaling, path-program trace selection (incl. a sound IDF-weighted novelty metric we derived),
  cross-worker predicate sharing, and worker strategy portfolios were all implemented and measured — none
  wins, because the implementation is **worker-saturated** (4 workers always have ≥ 4 findable traces, so
  reordering/cancelling work is free in CPU but invisible in wall). The lever that matters is the **serial
  critical-path / per-iteration cost**, not work selection.

---

## 7. Final configuration & reproduce

**Shipped config** = `dev` + (all default-OFF flags ON):

```
Use CEGAR loop for Parallel Trace Abstraction = true
Threadlimit for Parallel CEGAR              = 4
Loop-aware minimization for Parallel CEGAR  = true   (repeat threshold = 2)
Async refinement (Parallel CEGAR)           = true
Loop-targeted acceleration (Parallel CEGAR) = true   (recurrence threshold = 2,
                                                       time budget = 1000 ms, max fires = 1)
```

**Baseline** (`UAutomizer-dev`) = the same with all three flags OFF.

```bash
source /home/cycloud/toolchain/env.sh                 # Temurin JDK 21 + Maven 3.9
cd ultimate/trunk/source/BA_MavenParentUltimate
mvn -T 1C -pl ../TraceAbstraction -am install -Dmaven.test.skip=true   # ~1 min, incremental
# copy trunk/source/TraceAbstraction/target/...traceabstraction-0.3.1.jar into a packaged
# Automizer's plugins/ dir; set the prefs above in config/svcomp-Reach-32bit-Automizer_Default.epf
```

Branch `parallel-cegar-async` (commit `b419f5f795`). Measurement: BenchExec `runexec`, 16-core node,
120 s wall, ILP32, `unreach-call.prp`. Raw data `run/results/results_{big_accel,hlroom_accel_r{1,2,3},
eval_la,eval_async}.csv`; analyses `run/analyze_{big_accel,hlroom}.py`; full logs `run/LOOPACCEL_RESULT.md`,
`run/comparison_summary.md`, `run/N0_PROFILE.md`, `run/N1_RESULT.md`.

> Measurement note: the ECA/ControlFlow/trivial-Loops headline numbers are single-rep on the 139-task set;
> the Loops −18.5 % and the loop-aware/async layer wins are 3-rep medians. All "0 incorrect / no task lost"
> claims hold across every run.
