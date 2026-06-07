/*
 * Copyright (C) 2025 University of Freiburg
 * Copyright (C) 2025 LMU Munich
 * Copyright (C) 2025 Max Barth (Max.Barth@lmu.de)
 *
 * This file is part of the ULTIMATE Automata Library.
 *
 * The ULTIMATE Automata Library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ULTIMATE Automata Library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ULTIMATE Automata Library. If not, see <http://www.gnu.org/licenses/>.
 *
 * Additional permission under GNU GPL version 3 section 7:
 * If you modify the ULTIMATE Automata Library, or any covered work, by linking
 * or combining it with Eclipse RCP (or a modified version of Eclipse RCP),
 * containing parts covered by the terms of the Eclipse Public License, the
 * licensors of the ULTIMATE Automata Library grant you additional permission
 * to convey the resulting work.
 */
package de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

import de.uni_freiburg.informatik.ultimate.automata.AutomataLibraryException;
import de.uni_freiburg.informatik.ultimate.automata.AutomataLibraryServices;
import de.uni_freiburg.informatik.ultimate.automata.AutomataOperationCanceledException;
import de.uni_freiburg.informatik.ultimate.automata.IAutomaton;
import de.uni_freiburg.informatik.ultimate.automata.IRun;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.IDoubleDeckerAutomaton;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.INestedWordAutomaton;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.INwaOutgoingLetterAndTransitionProvider;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.NestedRun;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.NestedWord;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.NestedWordAutomaton;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.operations.Accepts;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.operations.Difference;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.operations.IsEmpty;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.operations.IsEmptyParallel;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.operations.PowersetDeterminizer;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.operations.oldapi.IOpWithDelayedDeadEndRemoval;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.senwa.DifferenceSenwa;
import de.uni_freiburg.informatik.ultimate.core.lib.exceptions.ToolchainCanceledException;
import de.uni_freiburg.informatik.ultimate.core.model.preferences.IPreferenceProvider;
import de.uni_freiburg.informatik.ultimate.core.model.services.IUltimateServiceProvider;
import de.uni_freiburg.informatik.ultimate.lib.modelcheckerutils.cfg.CfgSmtToolkit;
import de.uni_freiburg.informatik.ultimate.lib.modelcheckerutils.cfg.structure.IIcfg;
import de.uni_freiburg.informatik.ultimate.lib.modelcheckerutils.cfg.structure.IIcfgTransition;
import de.uni_freiburg.informatik.ultimate.lib.modelcheckerutils.cfg.structure.IcfgLocation;
import de.uni_freiburg.informatik.ultimate.lib.modelcheckerutils.cfg.structure.debugidentifiers.DebugIdentifier;
import de.uni_freiburg.informatik.ultimate.lib.modelcheckerutils.hoaretriple.IHoareTripleChecker;
import de.uni_freiburg.informatik.ultimate.lib.modelcheckerutils.smt.predicates.IMLPredicate;
import de.uni_freiburg.informatik.ultimate.lib.modelcheckerutils.smt.predicates.IPredicate;
import de.uni_freiburg.informatik.ultimate.lib.modelcheckerutils.smt.predicates.IPredicateUnifier;
import de.uni_freiburg.informatik.ultimate.lib.modelcheckerutils.smt.predicates.ISLPredicate;
import de.uni_freiburg.informatik.ultimate.lib.modelcheckerutils.smt.predicates.PredicateFactory;
import de.uni_freiburg.informatik.ultimate.lib.proofs.floydhoare.NwaHoareProofProducer;
import de.uni_freiburg.informatik.ultimate.lib.smtlibutils.ManagedScript;
import de.uni_freiburg.informatik.ultimate.lib.smtlibutils.solverbuilder.SolverBuilder;
import de.uni_freiburg.informatik.ultimate.lib.smtlibutils.solverbuilder.SolverBuilder.SolverMode;
import de.uni_freiburg.informatik.ultimate.lib.smtlibutils.solverbuilder.SolverBuilder.SolverSettings;
import de.uni_freiburg.informatik.ultimate.lib.tracecheckerutils.singletracecheck.InterpolationTechnique;
import de.uni_freiburg.informatik.ultimate.logic.Logics;
import de.uni_freiburg.informatik.ultimate.plugins.generator.rcfgbuilder.Activator;
import de.uni_freiburg.informatik.ultimate.plugins.generator.rcfgbuilder.preferences.RcfgPreferenceInitializer;
import de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction.automataminimization.AutomataMinimization;
import de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction.automataminimization.AutomataMinimization.AutomataMinimizationTimeout;
import de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction.preferences.TAPreferences;
import de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction.preferences.TAPreferences.InterpolantAutomatonEnhancement;
import de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction.preferences.TraceAbstractionPreferenceInitializer.Minimization;
import de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction.preferences.TraceAbstractionPreferenceInitializer.RefinementStrategy;
import de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction.preferences.TraceAbstractionPreferenceInitializer.RelevanceAnalysisMode;
import de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction.preferences.TraceAbstractionPreferenceInitializer.TraceSelectionStrategy;
import de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction.tracehandling.TaCheckAndRefinementPreferences;

public class ParallelNwaCegarLoop<L extends IIcfgTransition<?>, A extends IAutomaton<L, IPredicate>>
		extends NwaCegarLoop<L> {

	boolean mComputeHoareAnnotation;
	final String mDestroyEverything = "destroyEverything";

	// Parallel Setup
	private final ExecutorService mExec;
	private int mThreadLimit;
	private int mRunningThreads = 0;

	// private final CompletionService<WorkerThreadResult<L, A>> mECS;
	BlockingQueue<WorkerTask<L>> mWorkerTaskQueue = new LinkedBlockingQueue<>();
	BlockingQueue<WorkerThreadResult<L, A>> mWorkerResultQueue = new LinkedBlockingQueue<>();

	// need global program cache, but worker need to get copy otherwise we
	// synchronize
	private final PathProgramCache<L> mProgramCache = new PathProgramCache<>(mLogger);

	// Strategies
	public final HashMap<Integer, NestedRun<L, ?>> mActiveCounterexamples = new HashMap<>();
	private final Map<Integer, StaleCancellationToken> mActiveCancellationTokens = new HashMap<>();
	private final Set<Integer> mCounterexamplesToBeRemovedFromActiveCexMap = new HashSet<>();
	protected InterpolationTechnique mInterpolationTechnique;

	protected Class<L> mTransitionClazz;

	// Addtional Statistiks for Evaluation
	private Integer mCounterexamplesChecked = 0;
	private Integer mRefinementsDone = 0;
	private final Integer mCountTimeoutsInSearch = 0;
	private final Integer mCountFailedRunConstructions = 0;
	private Integer mCountFailedToFindCex = 0;
	private Integer mCountBfsFoundCex = 1;
	private final Integer mCountIsEmptyParallel = 0;
	private Integer maxActiveThreads = 0;
	private final Integer mActiveExecutors = 0;
	private long mSearchTime = 0;
	// N0 profiling: millisecond-granularity split of the coordinator serial critical path. The existing
	// mSearchTime/mRefinementTime are second-truncated and lump Difference+minimize together; these three
	// separate Difference, minimization, and emptiness search so the dominant remaining component (with
	// loop-aware minimization ON) can be identified. Overhead is two nanoTime() reads per phase (noise).
	// volatile: in async-refinement mode these are written by the apply-helper thread and read by the coordinator.
	private volatile long mDiffTimeMs = 0;
	private volatile long mMinimizeTimeMs = 0;
	private long mEmptinessTimeMs = 0;
	private long mWorkerSetUpTime = 0;

	// N1 (async refinement): apply a refinement (Difference + minimization) on a single dedicated helper thread off
	// the coordinator's serial critical path. The coordinator publishes/consumes the abstraction via
	// mPublishedAbstraction (an immutable reference handoff, like the R6 sweep snapshot); only the helper writes it,
	// the coordinator pulls the latest version at each iteration. Reading an automaton concurrently is already
	// library-safe (IsEmptyParallel reads it from many threads). The Difference is built with the MASTER managed
	// script (mCsToolkit) + mPredicateFactory so it is independent of the worker's reused script. SAFE is declared
	// only after the apply queue drains and the fully-refined abstraction is empty. Sound: paper §3.1.
	private ExecutorService mApplyExecutor;
	private final AtomicReference<INestedWordAutomaton<L, IPredicate>> mPublishedAbstraction = new AtomicReference<>();
	private final AtomicInteger mPendingApplies = new AtomicInteger(0);
	private volatile Throwable mApplyError;
	// volatile: incremented by the apply-helper, read by the coordinator to release avoid-set entries.
	private volatile long mAsyncRefinementsApplied = 0;
	// Coordinator-owned FIFO of counterexamples whose refinement is submitted but not yet applied. In async mode the
	// trace is NOT removed from the search avoid-set (mActiveCounterexamples) at submit time — if it were, the
	// coordinator would re-find and re-dispatch it on the still-stale abstraction (a redundant-dispatch storm). It is
	// released only once its refinement is applied (FIFO == single-helper apply order).
	private final java.util.ArrayDeque<IRun<L, ?>> mAsyncAppliedPending = new java.util.ArrayDeque<>();
	private long mAsyncCleanedUp = 0;
	// Pipeline-depth bound: the coordinator may run at most this many refinements ahead of the apply-helper. Bounding
	// the in-flight applies bounds how STALE the abstraction the coordinator searches/dispatches on can be, which
	// bounds (a) redundant dispatches that inflate the helper's sequential Difference chain and (b) the artificial
	// path-program recurrence that would otherwise trip loop-aware minimization on programs where sync skips it.
	private static final int ASYNC_MAX_PENDING_APPLIES = 2;
	private long mAsyncSubmitWaits = 0;
	// Adaptive gate: async only pays off when applying a refinement (the Difference) is expensive enough that
	// overlapping it outweighs the cost of dispatching on a slightly-stale abstraction. We therefore start
	// SYNCHRONOUS (measuring each Difference) and latch into async only once Differences prove expensive. On
	// Difference-cheap programs (control-flow/loops, where stale dispatch would inflate the refinement count) the
	// latch never fires, so behaviour is identical to the loop-aware baseline; on Difference-dominated programs
	// (hard ECA) it fires and overlaps the expensive Difference chain. One-way latch (never reverts).
	private static final long ASYNC_DIFF_THRESHOLD_MS = 200;
	// Trace-length guard: only latch into async on SHORT-trace programs. Measured separation is large — control-flow
	// (locks) averages ~30, where async overlaps the Difference chain stably and the stale-skip Accepts check is
	// cheap; ECA averages ~700-1000, where the long-trace Accepts checks are costly and the dispatch inflation can
	// spiral to a timeout (losing a task). 150 sits in the wide gap.
	private static final long ASYNC_MAX_CEX_LEN = 150;
	private volatile boolean mAsyncActive = false;
	private long mPrevSyncDiffMs = -1;
	// Redundant async refinements skipped because the trace was already removed by an earlier refinement.
	private volatile long mAsyncRedundantSkipped = 0;
	// Dispatched counterexample trace-length stats (to characterise the program: short-trace programs like
	// control-flow benefit stably from async; long-trace ECA does not). Used by the trace-length latch guard.
	private long mCexLenSum = 0;
	private long mCexLenCount = 0;
	private int mCexLenMax = 0;

	/** True once the adaptive gate has switched this run into async-refinement mode (requires the flag too). */
	private boolean asyncActive() {
		return mPref.asyncRefinementEnabled() && mAsyncActive;
	}
	private int mIterationsWithMaxThreads = 0;
	private int mIterationsWithOneThread = 0;
	// S3: number of times an idle worker was withheld because the selected trace
	// duplicated a path program already being analysed by an in-flight worker.
	private int mAdaptiveScalingGated = 0;
	// DPPI (A2) selection counters.
	private int mDppiNovelSelected = 0; // picked a candidate whose path program is not in flight
	private int mDppiFairnessFallback = 0; // no fresh-path-program candidate; fell back to the diverse trace
	private int mDppiCandidatesScanned = 0;
	// DPPI: how many diverse candidates to enumerate per selection before ranking by path-program overlap.
	// Kept small because each candidate costs one extra emptiness search (the costly step, paper §3.4); the
	// search also early-stops once a fully path-program-disjoint (overlap 0) candidate is found.
	private static final int DPPI_MAX_CANDIDATES = 4;
	// DIVERSITY selection: IDF-weighted novelty over path programs (the derived diversity metric). df = number of
	// dispatched path programs containing an edge; idf down-weights the shared structural core (entry/error/loop
	// heads) so overlap is measured only on reason-bearing statements. Picks the candidate bringing the most NEW
	// discriminative weight vs the in-flight set. Counters mirror DPPI's.
	private final Map<L, Integer> mEdgeDocFreq = new HashMap<>();
	private int mPathProgramsSeen = 0;
	private int mDiversityNovelSelected = 0;
	private int mDiversityFairnessFallback = 0;
	private int mDiversityCandidatesScanned = 0;
	private final int mExceptionInWorker = 0;

	private long mRefinementTime = 0;
	private long mStaleCancellationRequests = 0;
	private long mStaleCancellationAcceptsFailures = 0;
	// R1: number of staleness checks performed against the small subtrahend automaton instead of the
	// full, growing abstraction (path-program staleness pre-filter).
	private long mStaleCancellationPrefilterChecks = 0;
	// R4: cross-worker predicate sharing pool (null if disabled).
	private final SharedPredicatePool mPredicatePool;
	// Lazy minimization: number of refinements whose abstraction minimization was skipped (still below threshold).
	private long mLazyMinimizationSkips = 0;
	// R1b: number of stale re-checks skipped because the Accepts cost exceeded the work budget.
	private long mStaleCheckBudgetSkips = 0;
	// DIAGNOSTIC: subtrahend-check vs full-abstraction-check agreement.
	private long mStaleDiagDisagree = 0;
	private long mStaleDiagBothStale = 0;
	private long mStaleDiagFullStaleTotal = 0;
	private long mStaleDiagSubStaleTotal = 0;
	// DIAGNOSTIC: incremental-membership (removed-states-intersect-run) vs full check.
	private long mIncrDiagRunHit = 0;
	private long mIncrDiagFull = 0;
	private long mIncrDiagBoth = 0;
	private long mStaleWorkersCancelledBeforeTransfer = 0;
	private long mStaleWorkersCancelledBeforeTraceCheck = 0;
	private long mStaleWorkersCancelledBeforeAutomaton = 0;
	private long mStaleWorkersCancelledBeforeWorkerDifference = 0;
	private long mStaleWorkersCancelledBeforeReturn = 0;
	private long mStaleWorkersCancelledByImmediateInterrupt = 0;
	private long mStaleWorkerResultsSkippedAtCoordinator = 0;
	private long mStaleImmediateStopRequests = 0;
	private long mStaleThreadInterruptRequests = 0;
	private long mStaleImmediateStopNoThread = 0;
	private long mStaleImmediateStopFailures = 0;

	// R6: asynchronous off-critical-path staleness sweep. The S2 staleness re-check
	// (cancelStaleActiveCounterexamples) runs Accepts(abstraction, trace) for every in-flight counterexample on the
	// coordinator thread, i.e. on the critical path. On programs with long counterexamples (ECA) this dominates and
	// makes S2 lose, even though the cancellation it produces is beneficial. Accepts is a pure read-only automaton
	// traversal (no SMT) and the abstraction is immutable once built, so the sweep can run on an idle core against a
	// snapshot, off the critical path. Cancellation is sound regardless of timing: it only ever drops/defers
	// redundant worker effort (a missed/late cancellation just means a redundant refinement that the baseline would
	// have done anyway; a trace cancelled in error is still a real counterexample and is re-found by the search).
	private ExecutorService mStaleSweepExecutor;
	private Future<?> mPendingStaleSweep;
	private final AtomicLong mAsyncStaleCancellations = new AtomicLong();
	private long mAsyncStaleSweepsSubmitted = 0;
	private long mAsyncStaleSweepsCoalesced = 0;

	// Portfolio race: racers re-dispatched to idle workers, and duplicate results skipped.
	private long mRaceDispatched = 0;
	private long mRaceDuplicateSkipped = 0;
	// Relative-growth minimization trigger: abstraction size at the last minimization, and skips counter.
	private int mAbstractionSizeAtLastMinimization = -1;
	private long mGrowthMinimizationSkips = 0;
	// Loop-aware minimization counters (decision is per refinement in the per-worker minimization path).
	private long mLoopAwareMinimizations = 0;
	private long mLoopAwareSkips = 0;
	// Alternative strategies (NOT the tuned default) raced on the bottleneck trace by idle workers. The primary
	// worker for every trace keeps the default strategy, so racing can only finish a refinement sooner, never
	// remove a task the default would solve.
	private static final RefinementStrategy[] RACER_STRATEGIES =
			{ RefinementStrategy.CAMEL, RefinementStrategy.PENGUIN, RefinementStrategy.TAIPAN };

	/**
	 * Based on the @NwaCegarLoop. Given a ThreadLimit, creates a ExecutionerService that will manage the worker
	 * threads. Executes each tracecheck in a new thread called worker. This loop, only searches for counterexamples and
	 * updates the abstraction. The feasiblity check and generalization of interpolant automata is done by the workers.
	 *
	 * TODO option to save memory, measure heap, then dont spawn worker / kill a worker
	 *
	 * @author Max Barth (max.barth@lmu.de)
	 */

	public ParallelNwaCegarLoop(final DebugIdentifier name,
			final INestedWordAutomaton<L, IPredicate> initialAbstraction, final IIcfg<?> rootNode,
			final CfgSmtToolkit csToolkit, final PredicateFactory predicateFactory, final TAPreferences taPrefs,
			final Set<? extends IcfgLocation> errorLocs, final NwaHoareProofProducer<L> proofProducer,
			final IUltimateServiceProvider services, final Class<L> transitionClazz,
			final PredicateFactoryRefinement stateFactoryForRefinement) {
		super(name, initialAbstraction, rootNode, csToolkit, predicateFactory, taPrefs, errorLocs, proofProducer,
				services, transitionClazz, stateFactoryForRefinement);
		// Start thread pool
		mThreadLimit = mPref.getThreadLimit();
		if (mThreadLimit == 0) { // maximum of available cores
			mThreadLimit = Runtime.getRuntime().availableProcessors();
			mThreadLimit -= 1; // one for main thread
		}

		mExec = Executors.newFixedThreadPool(mThreadLimit);
		Thread.currentThread().setName("Main Cegar Thread");
		getServices().getStorage().pushMarker(mDestroyEverything);

		// R4: cross-worker predicate sharing pool (shared interpolant predicates), if enabled.
		mPredicatePool = mPref.crossWorkerPredicateSharingEnabled()
				? new SharedPredicatePool(mLogger, mPref.crossWorkerPredicateSharingCap() * mThreadLimit + 256) : null;
	}

	/*
	 * Sets up a worker with that communicates via blocking queues and stays alive after one counterexample check. Every
	 * transfer between controller and worker goes via @TransferBetweenMainAndWorker.
	 *
	 */
	private ICegarNwaWorkerThread<L, A> setUpContinuesWorker(final IUltimateServiceProvider iterationServices,
			final int id) throws InterruptedException {

		final TransferBetweenMainAndWorker<L, IPredicate> transferUtils = new TransferBetweenMainAndWorker<>(
				new AutomataLibraryServices(mServices), mLogger, mCsToolkit.getManagedScript(), iterationServices,
				getSolverSettings(iterationServices,
						getIteration() + mRunningThreads + mCounterexample.getWord().asList().hashCode() + "parallel"),
				mCsToolkit);

		final CfgSmtToolkit freshToolKit = transferUtils.getWorkerCfgSmtToolKit();

		// Create predicateFactory with worker script
		final PredicateFactory predicateFactory =
				new PredicateFactory(mServices, freshToolKit.getManagedScript(), freshToolKit.getSymbolTable());

		// Create PredicateFactoryForInterpolantAutomata with worker script
		final PredicateFactoryForInterpolantAutomata predicateFactoryInterpolantAutomata =
				new PredicateFactoryForInterpolantAutomata(freshToolKit.getManagedScript(), predicateFactory,
						mComputeHoareAnnotation);

		final Set<IcfgLocation> hoareAnnotationLocs = Collections.emptySet();
		if (mComputeHoareAnnotation) {
			// TODO need different hoareAnnotationLocs
			throw new AssertionError("Hoare Annotations not yet supported in Parallel cegar loop");
		}
		final PredicateFactoryRefinement stateFactoryForRefinement = new PredicateFactoryRefinement(mServices,
				freshToolKit.getManagedScript(), predicateFactory, mComputeHoareAnnotation, hoareAnnotationLocs);

		// make sure that mPref.getCfgSmtToolkit returns the worker toolkit
		final TaCheckAndRefinementPreferences<L> taCheckAndRefinementPrefs =
				new TaCheckAndRefinementPreferences<>(getServices(), mPref, mInterpolationTechnique,
						mSimplificationTechnique, freshToolKit, predicateFactory, mIcfg);
		// initialize worker
		return new CegarNwaWorkerThread<>(mLogger, mPref, id, mResultBuilder, iterationServices, freshToolKit,
				predicateFactory, taCheckAndRefinementPrefs, predicateFactoryInterpolantAutomata,
				stateFactoryForRefinement, mComputeHoareAnnotation, this, mWorkerResultQueue, mWorkerTaskQueue,
				transferUtils);
	}

	/** R4: the shared cross-worker predicate pool for this run, or {@code null} if disabled. */
	SharedPredicatePool getPredicatePool() {
		return mPredicatePool;
	}

	/** R4: the coordinator's (main) managed script, the canonical store for pooled predicates. */
	ManagedScript getMainManagedScript() {
		return mCsToolkit.getManagedScript();
	}

	/*
	 * Parallel CEGAR loop. In each iteration we pick a counterexample and put it into a blocking queue for a worker to
	 * check its feasibility
	 **
	 * As soon as we obtain a worker result via blocking queue, we refine our abstraction. If abstraction is not empty,
	 * we continue with the loop. If no worker is done, continue with the loop. If no thread is available and no worker
	 * is done we sleep.
	 */
	@Override
	protected void iterate() throws AutomataLibraryException {
		// TODO manage time and timeout
		boolean didntFindCexLastIteration = false;
		final IcfgLocation currentErrorLoc = getErrorLocFromCounterexample();
		final IUltimateServiceProvider iterationServices = createIterationTimer(currentErrorLoc);
		for (int i = 0; i < mThreadLimit; i++) {
			try {
				mExec.submit(setUpContinuesWorker(iterationServices, i));
			} catch (final InterruptedException e) {
				throw new AssertionError("Interrupted during worker setup " + e);
			}
		}

		// N1 (async refinement) uses an adaptive gate: the run starts synchronous and the apply-helper's starting
		// abstraction is published only when the gate latches into async mode (see refinement()).

		// start worker for initial cex:
		startWorker();

		for (mIteration = 1; mIteration <= mPref.maxIterations(); mIteration++) {
			abortIfTimeout();
			// N1: surface any helper-thread failure on the coordinator (fail loud), and adopt the latest abstraction
			// version the helper has published. A new version means the search must run again on it.
			if (asyncActive()) {
				rethrowApplyErrorIfAny();
				final INestedWordAutomaton<L, IPredicate> latest = mPublishedAbstraction.get();
				if (latest != mAbstraction) {
					mAbstraction = latest;
					didntFindCexLastIteration = false;
				}
				// Release avoid-set entries whose refinement the helper has now applied (FIFO == apply order), so the
				// search can again consider those traces (now removed from the abstraction) and so the set does not
				// grow unbounded.
				while (mAsyncCleanedUp < mAsyncRefinementsApplied && !mAsyncAppliedPending.isEmpty()) {
					removeCounterexampleFromSet(mAsyncAppliedPending.pollFirst());
					mAsyncCleanedUp++;
				}
			}
			boolean abstractionWasRefined = false;
			mLogger.info(String.format("=== Iteration %s ===", getIteration()));

			try {
				// we sleep if not: thread or counterexample is available
				WorkerThreadResult<L, A> workerResult = getWorkerResult(didntFindCexLastIteration);

				// go through all done workerResult
				while (workerResult != null) {
					final long time = System.nanoTime() / 1000000000;
					try {
						mLogger.info("Main: A Thread is Done");
						if (workerResult.workerCrashed()) {
							mLogger.error("Main: Worker Crashed! exiting CEGAR loop.");
							// TODO we can try to recover, and restart the worker.
							// It might be that just this counterexample crashes our worker and we can still prove the
							// program correct
							shutDownAndDestroy(mDestroyEverything);
							throw new AssertionError("Worker Crashed!, Exiting CEGAR loop!");
						}
						if (workerResult.wasStaleCancelled()) {
							handleStaleCancelledWorkerResult(workerResult);
							workerResult.garbageCollect();
							workerResult = mWorkerResultQueue.poll();
							continue;
						}
						// If Error automaton terminate immediately
						if (mPref.stopAfterFirstViolation()
								&& workerResult.getAutomatonType().equals(AutomatonType.ERROR)) {
							shutDownAndDestroy(mDestroyEverything);
							updateAndPrintStatistics(true);
							return;
						}
						// Portfolio race: a racer for this trace already refined it (trace no longer active), so
						// this is a duplicate result. Skip it (sound: refining the same trace twice is redundant).
						if (mPref.raceBottleneckTraceEnabled() && isDuplicateRaceResult(workerResult)) {
							handleRaceDuplicateResult(workerResult);
							workerResult.garbageCollect();
							workerResult = mWorkerResultQueue.poll();
							continue;
						}
						if (shouldSkipStaleInfeasibilityResult(workerResult)) {
							handleStaleSkippedWorkerResult(workerResult);
							workerResult.garbageCollect();
							workerResult = mWorkerResultQueue.poll();
							continue;
						}

						mLogger.info("Worker Automaton Type: " + workerResult.getAutomatonType());
						mLogger.info("Refining Abstraction");
						refinement(workerResult);
						mRefinementsDone += 1;
						abstractionWasRefined = true;
						if (asyncActive()) {
							// N1: the apply-helper now owns this result (it still needs the subtrahend), and the
							// abstraction is not yet updated, so do NOT garbage-collect it here and do NOT check
							// emptiness on the stale abstraction. The helper garbage-collects after applying, and SAFE
							// is checked after the apply queue drains (see below).
							workerResult = mWorkerResultQueue.poll();
							continue;
						}
						// Not sure if necessary
						workerResult.garbageCollect();
						// If new abstraction is empty terminate immediately
						if (isSafeThenTerminate()) {
							updateAndPrintStatistics(true);
							return;
						}

					} catch (final CancellationException e) {
						mLogger.warn("Worker was cancelled! " + e);
					} catch (final Exception e) {
						mLogger.warn("Worker Failed! " + e);
						throw e;
					} finally {

					}
					workerResult = mWorkerResultQueue.poll();
					mRefinementTime += ((System.nanoTime() / 1000000000) - time);
				}
				mLogger.info("No more worker results to process");
				assert workerResult == null;

			} catch (final ToolchainCanceledException e) {
				mLogger.warn("Worker Failed! " + e);
				throw e;
			} catch (final InterruptedException ie) {
				ie.printStackTrace();
				mLogger.warn("Worker was interrupted! " + ie);
			}
			if (abstractionWasRefined && !mPref.minimizeAbstractionPerWorker() && shouldMinimizeByGrowth()) {
				// uses NWA CEGAR loop
				// When do we minimize how often?
				minimizeAbstractionIfEnabled();
				mAbstractionSizeAtLastMinimization = mAbstraction.size();
			}
			// R6: launch the staleness sweep off the critical path against the just-refined (and minimized)
			// abstraction. Done here (once per iteration, after minimization) so the snapshot is the materialized
			// abstraction the next search will use, and so the sweep overhead is not on the coordinator's path.
			if (abstractionWasRefined && mPref.isStaleWorkerCancellationEnabled() && mPref.activeStaleRecheckEnabled()
					&& mPref.asyncStaleSweepEnabled()) {
				submitAsyncStaleSweep();
			}
			if (abstractionWasRefined) {
				// If we didnt find one we wait until we refine the abstraction
				didntFindCexLastIteration = false;
			}

			/*
			 * In the first iteration we search via BFS, then we use IsEmptyParallel
			 */
			boolean firstIteration = true;
			while (mRunningThreads < mThreadLimit && !didntFindCexLastIteration) {
				assert mRunningThreads >= 0;
				final long emptinessStart = System.nanoTime();
				mCounterexample = searchForErrorTrace(!firstIteration);
				mEmptinessTimeMs += (System.nanoTime() - emptinessStart) / 1000000;
				if (mCounterexample == null) {
					didntFindCexLastIteration = true;
					break;
				}
				// S3 (adaptive worker scaling): do not activate an additional worker when the selected
				// trace's path program is already being analysed by an in-flight worker. Spending a worker
				// on a duplicate infeasibility reason is the overhead behind the PAR-6 < PAR-4 regression.
				// The trace is only deferred (not added to the assigned set), so it is re-selected once the
				// duplicate worker finishes; soundness/termination are preserved (the verdict still rests on
				// L(A)=emptyset and every trace is eventually dispatched).
				if (mPref.isAdaptiveWorkerScalingEnabled() && mRunningThreads >= 1
						&& !addsDistinctInFlightPathProgram((NestedRun<L, ?>) mCounterexample)) {
					mAdaptiveScalingGated += 1;
					mLogger.info("AdaptiveScaling: withholding worker; selected trace duplicates an in-flight "
							+ "path program (runningThreads=" + mRunningThreads + ")");
					break;
				}
				if (mCounterexample != null) {
					startWorker();
				}
				firstIteration = false;
			}
			// Portfolio race: no fresh trace to dispatch but workers idle -> re-dispatch the bottleneck
			// (longest) in-flight trace to the idle workers so they race it with diverse strategies.
			if (mPref.raceBottleneckTraceEnabled() && mRunningThreads < mThreadLimit) {
				dispatchRacersForIdleWorkers();
			}
			// N1: with async refinement the per-refinement emptiness check is deferred (the abstraction is updated on
			// the helper). Once there is no error trace in the current abstraction, no worker is running, and the
			// apply queue has drained (so the abstraction is fully refined), the program is SAFE. Difference only
			// shrinks the language, so an empty abstraction stays empty under any pending refinement; requiring the
			// queue to be empty before declaring SAFE is therefore conservative and sound (paper §3.1).
			if (asyncActive() && didntFindCexLastIteration && mRunningThreads == 0
					&& mPendingApplies.get() == 0) {
				mAbstraction = mPublishedAbstraction.get();
				rethrowApplyErrorIfAny();
				if (isSafeThenTerminate()) {
					updateAndPrintStatistics(true);
					return;
				}
			}
			updateAndPrintStatistics(false);
		}
		mExec.shutdownNow();
		if (mStaleSweepExecutor != null) {
			mStaleSweepExecutor.shutdownNow();
		}
		if (mApplyExecutor != null) {
			mApplyExecutor.shutdownNow();
		}
		mResultBuilder.addResultForAllRemaining(Result.USER_LIMIT_ITERATIONS);

	}

	private void updateAndPrintStatistics(final boolean printStatistics) {

		if (mRunningThreads > maxActiveThreads) {
			maxActiveThreads = mRunningThreads;
		}
		if (mRunningThreads == mThreadLimit) {
			mIterationsWithMaxThreads += 1;
		}
		if (mRunningThreads == 1) {
			mIterationsWithOneThread += 1;
		}
		if (printStatistics) {
			mLogger.info("Iteration " + getIteration());
			mLogger.info("Refinements: " + mRefinementsDone);
			mLogger.info("Counterexamples: " + mCounterexamplesChecked);
			mLogger.info("SearchTimeout: " + mCountTimeoutsInSearch);
			mLogger.info("RunConstructionFailed: " + mCountFailedRunConstructions);
			mLogger.info("SearchFailed: " + mCountFailedToFindCex);
			mLogger.info("BFS: " + mCountBfsFoundCex);
			mLogger.info("IsEmptyParallel: " + mCountIsEmptyParallel);
			mLogger.info("ActiveThreads: " + maxActiveThreads);
			mLogger.info("ActiveExecutorsForPathPrograms: " + mActiveExecutors);
			mLogger.info("IterationsWithMaxThreads: " + mIterationsWithMaxThreads);
			mLogger.info("IterationsWithONEThread: " + mIterationsWithOneThread);
			mLogger.info("AdaptiveScalingGated: " + mAdaptiveScalingGated);
			mLogger.info("DppiNovelSelected: " + mDppiNovelSelected);
			mLogger.info("DppiFairnessFallback: " + mDppiFairnessFallback);
			mLogger.info("DppiCandidatesScanned: " + mDppiCandidatesScanned);
			mLogger.info("DiversityNovelSelected: " + mDiversityNovelSelected);
			mLogger.info("DiversityFairnessFallback: " + mDiversityFairnessFallback);
			mLogger.info("DiversityCandidatesScanned: " + mDiversityCandidatesScanned);
			mLogger.info("SearchTime: " + mSearchTime + " s");
			mLogger.info("WorkerSetUpTime: " + mWorkerSetUpTime + " s");
			mLogger.info("ExceptionInWorker: " + mExceptionInWorker);
			mLogger.info("mRefinementTime: " + mRefinementTime);
			mLogger.info("N0_DiffTimeMs: " + mDiffTimeMs);
			mLogger.info("N0_MinimizeTimeMs: " + mMinimizeTimeMs);
			mLogger.info("N0_EmptinessTimeMs: " + mEmptinessTimeMs);
			mLogger.info("AsyncRefinementsApplied: " + mAsyncRefinementsApplied);
			mLogger.info("AsyncRefinementPending: " + mPendingApplies.get());
			mLogger.info("AsyncSubmitWaits: " + mAsyncSubmitWaits);
			mLogger.info("AsyncGateLatched: " + mAsyncActive);
			mLogger.info("AsyncRedundantSkipped: " + mAsyncRedundantSkipped);
			mLogger.info("CexLenAvg: " + (mCexLenCount == 0 ? 0 : mCexLenSum / mCexLenCount) + " CexLenMax: "
					+ mCexLenMax + " CexLenCount: " + mCexLenCount);
			mLogger.info("StaleCancellationRequests: " + mStaleCancellationRequests);
			mLogger.info("AsyncStaleSweepsSubmitted: " + mAsyncStaleSweepsSubmitted);
			mLogger.info("AsyncStaleSweepsCoalesced: " + mAsyncStaleSweepsCoalesced);
			mLogger.info("AsyncStaleCancellations: " + mAsyncStaleCancellations.get());
			mLogger.info("RaceDispatched: " + mRaceDispatched);
			mLogger.info("RaceDuplicateSkipped: " + mRaceDuplicateSkipped);
			mLogger.info("GrowthMinimizationSkips: " + mGrowthMinimizationSkips);
			mLogger.info("LoopAwareMinimizations: " + mLoopAwareMinimizations);
			mLogger.info("LoopAwareSkips: " + mLoopAwareSkips);
			mLogger.info("StaleCancellationAcceptsFailures: " + mStaleCancellationAcceptsFailures);
			mLogger.info("StaleCancellationPrefilterChecks: " + mStaleCancellationPrefilterChecks);
			if (mPredicatePool != null) {
				mLogger.info("CrossWorkerPredicateSharing: " + mPredicatePool.stats());
			}
			mLogger.info("LazyMinimizationSkips: " + mLazyMinimizationSkips);
			mLogger.info("StaleCheckBudgetSkips: " + mStaleCheckBudgetSkips);
			mLogger.info("StaleDiag: subStaleTotal=" + mStaleDiagSubStaleTotal + " fullStaleTotal="
					+ mStaleDiagFullStaleTotal + " bothStale=" + mStaleDiagBothStale + " disagree=" + mStaleDiagDisagree);
			mLogger.info("IncrDiag: runHitRemoved=" + mIncrDiagRunHit + " fullStale=" + mIncrDiagFull
					+ " both=" + mIncrDiagBoth);
			mLogger.info("StaleWorkersCancelledBeforeTransfer: " + mStaleWorkersCancelledBeforeTransfer);
			mLogger.info("StaleWorkersCancelledBeforeTraceCheck: " + mStaleWorkersCancelledBeforeTraceCheck);
			mLogger.info("StaleWorkersCancelledBeforeAutomaton: " + mStaleWorkersCancelledBeforeAutomaton);
			mLogger.info("StaleWorkersCancelledBeforeWorkerDifference: "
					+ mStaleWorkersCancelledBeforeWorkerDifference);
			mLogger.info("StaleWorkersCancelledBeforeReturn: " + mStaleWorkersCancelledBeforeReturn);
			mLogger.info("StaleWorkersCancelledByImmediateInterrupt: " + mStaleWorkersCancelledByImmediateInterrupt);
			mLogger.info("StaleWorkerResultsSkippedAtCoordinator: " + mStaleWorkerResultsSkippedAtCoordinator);
			mLogger.info("StaleImmediateStopRequests: " + mStaleImmediateStopRequests);
			mLogger.info("StaleThreadInterruptRequests: " + mStaleThreadInterruptRequests);
			mLogger.info("StaleImmediateStopNoThread: " + mStaleImmediateStopNoThread);
			mLogger.info("StaleImmediateStopFailures: " + mStaleImmediateStopFailures);
		}
	}

	private boolean isSafeThenTerminate() throws AutomataOperationCanceledException {
		// If IsEmpty says its empty, then we can terminate even if threads are still
		// running
		mLogger.info("Checking if program is safe");
		if (super.isAbstractionEmpty() || mAbstraction.size() == 0) {
			mResultBuilder.addResultForAllRemaining(Result.SAFE);
			shutDownAndDestroy(mDestroyEverything);
			return true;
		}
		// set cex to null to be certain we dont check counterexamples from the old
		// abstraction (super.isAbstractionEmpty() will set mCounterexample)
		mCounterexample = null;
		return false;
	}

	/*
	 * When we reach this method, we will always start at least one new worker.
	 */
	private void startWorker() {
		final NestedRun<L, ?> counterexample = (NestedRun<L, ?>) mCounterexample;
		final int traceHash = counterexample.getWord().asList().hashCode();
		final int cexLen = counterexample.getWord().length();
		mCexLenSum += cexLen;
		mCexLenCount += 1;
		if (cexLen > mCexLenMax) {
			mCexLenMax = cexLen;
		}
		final StaleCancellationToken cancellationToken =
				mPref.isStaleWorkerCancellationEnabled() ? new StaleCancellationToken(traceHash) : null;
		// add mCounterexample to list such that we dont get it twice in our search
		addCounterexampleToSet(counterexample);
		if (cancellationToken != null) {
			mActiveCancellationTokens.put(traceHash, cancellationToken);
		}
		mWorkerTaskQueue.add(new WorkerTask<>(mCounterexample, cancellationToken));
		mProgramCache.addRun(mCounterexample.getWord());
		// DIVERSITY metric bookkeeping: this dispatched trace's path program is one more "document"; update the
		// edge document-frequencies used for the IDF weighting.
		for (final L edge : new HashSet<>(counterexample.getWord().asList())) {
			mEdgeDocFreq.merge(edge, 1, Integer::sum);
		}
		mPathProgramsSeen += 1;
		final long time = System.nanoTime() / 1000000000;
		mLogger.info("Main: Starting Thread");
		final IcfgLocation currentErrorLoc = getErrorLocFromCounterexample();
		final IUltimateServiceProvider iterationServices = createIterationTimer(currentErrorLoc);
		mServices = iterationServices;
		mRunningThreads += 1;
		mCounterexamplesChecked += 1;
		mWorkerSetUpTime += ((System.nanoTime() / 1000000000) - time);
	}

	/**
	 * Portfolio race: fill the idle worker slots by re-dispatching the longest in-flight counterexample (the most
	 * likely bottleneck, since interpolation cost grows with trace length). The racers are NOT added to
	 * mActiveCounterexamples (the original already is, so the search will not re-find it) and the program cache is
	 * not touched; combined with the worker strategy portfolio, the idle workers attack the same trace with
	 * different solver/interpolation backends. The first result for the trace is applied (refinement removes it from
	 * the active set); every later result for it is a duplicate and is dropped by isDuplicateRaceResult.
	 */
	private void dispatchRacersForIdleWorkers() {
		NestedRun<L, ?> bottleneck = null;
		int maxLen = -1;
		for (final NestedRun<L, ?> active : mActiveCounterexamples.values()) {
			if (active != null && active.getLength() > maxLen) {
				maxLen = active.getLength();
				bottleneck = active;
			}
		}
		if (bottleneck == null) {
			return;
		}
		int racerIdx = 0;
		while (mRunningThreads < mThreadLimit) {
			// Racers use no cancellation token (we dedup their results at the coordinator instead) and each gets a
			// DIFFERENT alternative strategy, so the idle workers attack the bottleneck trace with diverse solvers.
			final RefinementStrategy racerStrategy = RACER_STRATEGIES[racerIdx % RACER_STRATEGIES.length];
			mWorkerTaskQueue.add(new WorkerTask<>(bottleneck, null, racerStrategy));
			mRunningThreads += 1;
			mRaceDispatched += 1;
			racerIdx += 1;
		}
		mLogger.info("PortfolioRace: re-dispatched bottleneck trace (len " + maxLen + ") to idle workers");
	}

	/**
	 * Portfolio race: a returned infeasibility result is a duplicate iff its trace is no longer in the active set,
	 * i.e. another racer (or the original worker) already refined this trace away. Such a result must be skipped
	 * (re-applying its difference is redundant; dropping it is sound).
	 */
	private boolean isDuplicateRaceResult(final WorkerThreadResult<L, A> workerResult) {
		if (workerResult.getCounterexample() == null || workerResult.getAutomatonType() == AutomatonType.ERROR) {
			return false;
		}
		final int traceHash = workerResult.getCounterexample().getWord().asList().hashCode();
		return !mActiveCounterexamples.containsKey(traceHash);
	}

	private void handleRaceDuplicateResult(final WorkerThreadResult<L, A> workerResult) {
		mRunningThreads -= 1;
		mRaceDuplicateSkipped += 1;
		mLogger.info("PortfolioRace: dropped duplicate result for trace "
				+ workerResult.getCounterexample().getWord().asList().hashCode());
	}

	private WorkerThreadResult<L, A> getWorkerResult(final boolean didntFindCexLastIteration)
			throws InterruptedException {
		if (asyncActive()) {
			return getWorkerResultAsync(didntFindCexLastIteration);
		}
		WorkerThreadResult<L, A> doneFuture = null;

		if (mRunningThreads >= mThreadLimit || didntFindCexLastIteration) {
			assert mRunningThreads > 0;
			mLogger.info("All threads busy, going to sleep.");
			// No busy waiting via BlockingQueue
			doneFuture = mWorkerResultQueue.take();
			mLogger.info("Waking up, a worker is done.");
		} else {
			doneFuture = mWorkerResultQueue.poll();
		}
		return doneFuture;
	}

	/**
	 * N1: async-refinement variant of {@link #getWorkerResult}. The coordinator must wake on EITHER a worker result
	 * OR an apply-helper completing (which publishes a newer abstraction to re-search), and must NOT block forever
	 * when no worker is running but applies are still in flight (the sync code asserts a running thread; that
	 * invariant does not hold here). We therefore poll the result queue with a short timeout: returning a result
	 * when one is ready, or null when (a) the helper has published a newer abstraction (re-search it) or (b) there is
	 * no work left at all (let the coordinator's SAFE-after-drain check run). The 50 ms tick adds at most ~50 ms of
	 * latency per wake-up, negligible against the seconds-scale Difference it overlaps.
	 */
	private WorkerThreadResult<L, A> getWorkerResultAsync(final boolean didntFindCexLastIteration)
			throws InterruptedException {
		final boolean wouldBlock = mRunningThreads >= mThreadLimit || didntFindCexLastIteration;
		if (!wouldBlock) {
			return mWorkerResultQueue.poll();
		}
		while (true) {
			if (mRunningThreads == 0 && mPendingApplies.get() == 0) {
				// nothing in flight: do not block; let the coordinator re-search / declare SAFE.
				return null;
			}
			final WorkerThreadResult<L, A> r = mWorkerResultQueue.poll(50, TimeUnit.MILLISECONDS);
			if (r != null) {
				return r;
			}
			if (mApplyError != null) {
				return null;
			}
			if (mPublishedAbstraction.get() != mAbstraction) {
				// the helper published a newer abstraction; return so the coordinator adopts and re-searches it.
				return null;
			}
		}
	}

	private void shutDownAndDestroy(final Object marker) {
		mExec.shutdownNow();
		if (mStaleSweepExecutor != null) {
			mStaleSweepExecutor.shutdownNow();
		}
		if (mApplyExecutor != null) {
			mApplyExecutor.shutdownNow();
		}
		final Set<String> destroyedStorables = getServices().getStorage().destroyMarker(marker);
		if (!destroyedStorables.isEmpty()) {
			mLogger.warn("Destroyed unattended storables created during the last iteration: "
					+ destroyedStorables.stream().collect(Collectors.joining(",")));
		}
	}

	private void refinement(final WorkerThreadResult<L, A> threadResult)
			throws AutomataOperationCanceledException, AutomataLibraryException {
		// mInterations equals the amount of refinements
		mCegarLoopBenchmark.announceNextIteration();

		if (!asyncActive()) {
			// Sync (incl. the pre-latch measuring phase): the abstraction is refined inline immediately below, so the
			// trace can leave the avoid-set now.
			removeCounterexampleFromSet(threadResult.getCounterexample());
		}

		final Set<IcfgLocation> hoareAnnotationLocs;
		// TODO support for HoareAnnotations
		hoareAnnotationLocs = Collections.emptySet();

		if (asyncActive()) {
			// Keep this trace in the avoid-set until the helper has actually applied its refinement; otherwise the
			// coordinator would re-find it on the still-stale abstraction and re-dispatch it (a redundant-dispatch
			// storm). It is released in FIFO order at the loop top as applies complete.
			mAsyncAppliedPending.addLast(threadResult.getCounterexample());
			// N1: do the coordinator-owned bookkeeping inline (mProgramCache / abstraction-size reads must stay on
			// the coordinator thread), then hand the heavy Difference (+ optional minimize) to the helper. Compute
			// the loop-aware / lazy minimization DECISION here (it reads the shared mProgramCache and mAbstraction)
			// and pass it as a boolean so the helper touches no shared mutable coordinator state.
			final boolean doMinimize = mPref.minimizeAbstractionPerWorker() && shouldMinimizeNow()
					&& minimizeGivenLoopAware(threadResult);
			submitAsyncRefinement(threadResult, doMinimize);
			mRunningThreads -= 1;
			return;
		}

		final PredicateFactoryRefinement stateFactoryForRefinement =
				new PredicateFactoryRefinement(getServices(), threadResult.getWorkerMgdScript(),
						threadResult.getPredicateFactory(), mComputeHoareAnnotation, hoareAnnotationLocs);
		mLogger.info("Difference in Main");
		final long diffStart = System.nanoTime();
		final IOpWithDelayedDeadEndRemoval<L, IPredicate> diff =
				computeAutomataDifference(mAbstraction, threadResult, stateFactoryForRefinement);

		mAbstraction = diff.getResult();
		final long thisDiffMs = (System.nanoTime() - diffStart) / 1000000;
		mDiffTimeMs += thisDiffMs;
		diagnoseIncrementalMembership(diff);
		cancelStaleActiveCounterexamples(threadResult.getSubtrahend());
		harvestSharedPredicates(threadResult);

		if (mPref.minimizeAbstractionPerWorker() && shouldMinimizeNow()
				&& minimizeGivenLoopAware(threadResult)) {
			final long minStart = System.nanoTime();
			minimizeAbstractionIfEnabled(stateFactoryForRefinement,
					new PredicateFactoryResultChecking(mPredicateFactory));
			mMinimizeTimeMs += (System.nanoTime() - minStart) / 1000000;
		}
		mRunningThreads -= 1;
		mLogger.info("Main: Refinement done.");

		// Adaptive gate: if the flag is on and the last two Differences were both expensive, latch into async mode
		// for the remainder of the run. Requiring two consecutive expensive Differences avoids latching on a one-off
		// spike. mAbstraction is the just-refined-and-minimized abstraction, so it is the correct chain head to hand
		// to the helper. No applies are in flight yet (we are still synchronous), so the handoff is clean.
		final long avgCexLen = mCexLenCount == 0 ? 0 : mCexLenSum / mCexLenCount;
		if (mPref.asyncRefinementEnabled() && !mAsyncActive && thisDiffMs >= ASYNC_DIFF_THRESHOLD_MS
				&& mPrevSyncDiffMs >= ASYNC_DIFF_THRESHOLD_MS && avgCexLen <= ASYNC_MAX_CEX_LEN) {
			mAsyncActive = true;
			mPublishedAbstraction.set(mAbstraction);
			mLogger.info("N1 adaptive gate: switching to async refinement at iteration " + getIteration()
					+ " (Difference ~" + thisDiffMs + " ms/refinement >= " + ASYNC_DIFF_THRESHOLD_MS
					+ " ms, avg trace length " + avgCexLen + " <= " + ASYNC_MAX_CEX_LEN + ")");
		}
		mPrevSyncDiffMs = thisDiffMs;
	}

	/**
	 * N1: hand a refinement to the single apply-helper thread. All shared coordinator state needed to build the
	 * Difference is captured here (on the coordinator thread): the current abstraction as the minuend, a state
	 * factory built from the MASTER managed script (so the apply is independent of the worker's reused script), and
	 * the per-iteration services. The helper then touches only these captured locals, the result's own subtrahend,
	 * and the publish/pending atomics. {@code doMinimize} is the loop-aware/lazy decision computed on the coordinator
	 * (it reads the shared mProgramCache / abstraction size, which must not be touched from the helper).
	 */
	private void submitAsyncRefinement(final WorkerThreadResult<L, A> threadResult, final boolean doMinimize)
			throws AutomataOperationCanceledException, AutomataLibraryException {
		if (mApplyExecutor == null) {
			mApplyExecutor = Executors.newSingleThreadExecutor(r -> {
				final Thread t = new Thread(r, "ParallelCegar-ApplyRefinement");
				t.setDaemon(true);
				return t;
			});
		}
		// Pipeline-depth bound: do not get more than ASYNC_MAX_PENDING_APPLIES refinements ahead of the helper.
		// Paces the coordinator to the helper's apply rate and keeps the dispatched-on abstraction near-fresh.
		while (mPendingApplies.get() >= ASYNC_MAX_PENDING_APPLIES) {
			abortIfTimeout();
			rethrowApplyErrorIfAny();
			mAsyncSubmitWaits++;
			try {
				Thread.sleep(1);
			} catch (final InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}
		final PredicateFactoryRefinement stateFactory = new PredicateFactoryRefinement(getServices(),
				mCsToolkit.getManagedScript(), mPredicateFactory, mComputeHoareAnnotation, Collections.emptySet());
		final PredicateFactoryResultChecking resultCheckFactory = new PredicateFactoryResultChecking(mPredicateFactory);
		final IUltimateServiceProvider services = getServices();
		mPendingApplies.incrementAndGet();
		mApplyExecutor.submit(
				() -> applyRefinementAsync(threadResult, doMinimize, stateFactory, resultCheckFactory, services));
	}

	/**
	 * N1: the apply-helper body (runs on the single ParallelCegar-ApplyRefinement thread). Applies the Difference
	 * (and minimization when {@code doMinimize}) to the helper's running abstraction and publishes the new version.
	 * Because there is exactly one helper, refinements are applied in submission order — identical to the synchronous
	 * coordinator order, so no commutative aggregation is needed. The published reference is set BEFORE the pending
	 * counter is decremented, so when the coordinator observes pendingApplies==0 the published abstraction already
	 * reflects every applied refinement (needed for the SAFE-after-drain check). Any failure is recorded in
	 * mApplyError and re-surfaced on the coordinator (fail loud).
	 */
	private void applyRefinementAsync(final WorkerThreadResult<L, A> threadResult, final boolean doMinimize,
			final PredicateFactoryRefinement stateFactory, final PredicateFactoryResultChecking resultCheckFactory,
			final IUltimateServiceProvider services) {
		try {
			// Chain on the helper's own latest result. Because this is the single apply thread and it publishes after
			// each apply, mPublishedAbstraction holds the result of the immediately-preceding apply (or the initial
			// abstraction) — so each Difference is applied on top of all previously-applied refinements, exactly as
			// in the synchronous sequence. Capturing the minuend at submit time would instead drop earlier pending
			// refinements.
			final INestedWordAutomaton<L, IPredicate> minuend = mPublishedAbstraction.get();
			// Stale-skip: the trace was selected on a possibly-older abstraction; if an earlier (already-applied)
			// refinement has since removed it, re-refining it is pure waste (the Difference would change nothing) — and
			// such redundant work is exactly what inflates the helper's chain on programs with overlapping traces. A
			// read-only Accepts check on the chain head detects this; skip the Difference if the trace is gone. Sound:
			// refining a trace not in the abstraction removes nothing, so skipping it cannot change a verdict.
			final IRun<L, ?> cex = threadResult.getCounterexample();
			if (cex != null && !new Accepts<>(new AutomataLibraryServices(services), minuend,
					(NestedWord<L>) cex.getWord()).getResult()) {
				mAsyncRedundantSkipped += 1;
				return;
			}
			final long diffStart = System.nanoTime();
			final IOpWithDelayedDeadEndRemoval<L, IPredicate> diff =
					computeAutomataDifferenceAsync(minuend, threadResult, stateFactory, services);
			INestedWordAutomaton<L, IPredicate> next = diff.getResult();
			mDiffTimeMs += (System.nanoTime() - diffStart) / 1000000;
			if (doMinimize) {
				final long minStart = System.nanoTime();
				next = minimizeGivenAsync(next, stateFactory, resultCheckFactory, services);
				mMinimizeTimeMs += (System.nanoTime() - minStart) / 1000000;
			}
			mPublishedAbstraction.set(next);
		} catch (final Throwable t) {
			// Record and surface on the coordinator. A late cancellation/timeout is preserved (rethrowApplyErrorIfAny
			// re-throws cancellation as cancellation, not as a crash).
			mApplyError = t;
		} finally {
			threadResult.garbageCollect();
			// "Processed" count (applied OR stale-skipped): the coordinator uses it to release the matching avoid-set
			// entry in FIFO order. Incremented after any publish above, so when the coordinator sees the count advance
			// the published abstraction already reflects this step.
			mAsyncRefinementsApplied += 1;
			mPendingApplies.decrementAndGet();
		}
	}

	/**
	 * N1: master-script, off-coordinator variant of {@link #computeAutomataDifference}. Identical except that it uses
	 * the explicitly-captured {@code services} (the coordinator reassigns mServices per iteration, so the helper must
	 * not call getServices()). reportInterpolantAutomatonStates is an independent int accumulator, safe to call from
	 * the helper.
	 */
	private IOpWithDelayedDeadEndRemoval<L, IPredicate> computeAutomataDifferenceAsync(
			final INestedWordAutomaton<L, IPredicate> minuend, final WorkerThreadResult<L, A> workerResult,
			final PredicateFactoryRefinement stateFactory, final IUltimateServiceProvider services)
			throws AutomataLibraryException {
		final PowersetDeterminizer<L, IPredicate> psd =
				new PowersetDeterminizer<>(workerResult.getSubtrahend(), true, mPredicateFactoryInterpolantAutomata);
		final IOpWithDelayedDeadEndRemoval<L, IPredicate> diff;
		if (mPref.differenceSenwa()) {
			diff = new DifferenceSenwa<>(new AutomataLibraryServices(services), stateFactory, minuend,
					workerResult.getSubtrahend(), psd, false);
		} else {
			diff = new Difference<>(new AutomataLibraryServices(services), stateFactory, minuend,
					workerResult.getSubtrahend(), psd, workerResult.exploitSigmaStarConcatOfIa());
		}
		mCegarLoopBenchmark.reportInterpolantAutomatonStates(workerResult.getSubtrahend().size());
		if (REMOVE_DEAD_ENDS) {
			diff.removeDeadEnds();
		}
		return diff;
	}

	/**
	 * N1: pure, off-coordinator variant of {@link #minimizeAbstraction}. Minimizes the GIVEN automaton and RETURNS
	 * the result instead of mutating the mAbstraction field (which is owned by the coordinator). With the configured
	 * Minimization=NONE it is a no-op. Only this single helper thread calls it, so the (map-backed)
	 * addAutomataMinimizationData aggregation has no concurrent writer. Sound: minimization is language-preserving.
	 */
	private INestedWordAutomaton<L, IPredicate> minimizeGivenAsync(final INestedWordAutomaton<L, IPredicate> input,
			final PredicateFactoryRefinement stateFactory, final PredicateFactoryResultChecking resultCheckFactory,
			final IUltimateServiceProvider services)
			throws AutomataOperationCanceledException, AutomataLibraryException {
		final Minimization minimization = mPref.getMinimization();
		if (minimization == Minimization.NONE) {
			return input;
		}
		final Function<IPredicate, Set<IcfgLocation>> lcsProvider =
				x -> (x instanceof ISLPredicate ? Collections.singleton(((ISLPredicate) x).getProgramPoint())
						: new HashSet<>(Arrays.asList(((IMLPredicate) x).getProgramPoints())));
		final AutomataMinimization<Set<IcfgLocation>, IPredicate, L> am;
		try {
			am = new AutomataMinimization<>(services, input, minimization, mComputeHoareAnnotation, getIteration(),
					stateFactory, MINIMIZE_EVERY_KTH_ITERATION, mStoredRawInterpolantAutomata, mInterpolAutomaton,
					MINIMIZATION_TIMEOUT, resultCheckFactory, lcsProvider, true);
		} catch (final AutomataMinimizationTimeout e) {
			mCegarLoopBenchmark.addAutomataMinimizationData(e.getStatistics());
			throw e.getAutomataOperationCanceledException();
		}
		mCegarLoopBenchmark.addAutomataMinimizationData(am.getStatistics());
		if (am.newAutomatonWasBuilt()) {
			return am.getMinimizedAutomaton();
		}
		return input;
	}

	/**
	 * N1: re-surface a failure that occurred on the apply-helper thread, preserving cancellation/timeout semantics so
	 * a timed-out Difference is reported as a timeout rather than a crash.
	 */
	private void rethrowApplyErrorIfAny() throws AutomataOperationCanceledException, AutomataLibraryException {
		final Throwable t = mApplyError;
		if (t == null) {
			return;
		}
		if (t instanceof AutomataOperationCanceledException) {
			throw (AutomataOperationCanceledException) t;
		}
		if (t instanceof AutomataLibraryException) {
			throw (AutomataLibraryException) t;
		}
		if (t instanceof RuntimeException) {
			throw (RuntimeException) t;
		}
		if (t instanceof Error) {
			throw (Error) t;
		}
		throw new AssertionError("Parallel CEGAR async refinement helper failed", t);
	}

	private boolean hasInfeasibilityProof(final WorkerThreadResult<L, A> workerResult) {
		return workerResult != null && !workerResult.workerCrashed() && workerResult.getSubtrahend() != null
				&& workerResult.getCounterexample() != null
				&& workerResult.getAutomatonType() == AutomatonType.FLOYD_HOARE && !workerResult.useErrorAutomaton();
	}

	private boolean shouldSkipStaleInfeasibilityResult(final WorkerThreadResult<L, A> workerResult) {
		if (!mPref.isStaleWorkerCancellationEnabled() || !hasInfeasibilityProof(workerResult)) {
			return false;
		}
		final IRun<L, ?> workerCounterexample = workerResult.getCounterexample();
		final int workerTraceHash = workerCounterexample.getWord().asList().hashCode();
		try {
			if (!new Accepts<>(new AutomataLibraryServices(getServices()), mAbstraction,
					(NestedWord<L>) workerCounterexample.getWord()).getResult()) {
				mLogger.info("StaleCancellation: coordinator skips stale refinement for trace " + workerTraceHash);
				return true;
			}
		} catch (final AutomataLibraryException e) {
			mStaleCancellationAcceptsFailures += 1;
			mLogger.warn("StaleCancellation: failed to check returned worker trace " + workerTraceHash + ": " + e);
		}
		return false;
	}

	private void handleStaleCancelledWorkerResult(final WorkerThreadResult<L, A> workerResult) {
		removeCounterexampleFromSet(workerResult.getCounterexample());
		mRunningThreads -= 1;
		countStaleCancellationPoint(workerResult.getStaleCancellationPoint());
		mLogger.info("StaleCancellation: worker result for trace "
				+ workerResult.getCounterexample().getWord().asList().hashCode() + " cancelled at "
				+ workerResult.getStaleCancellationPoint() + " because " + workerResult.getStaleCancellationReason());
	}

	private void handleStaleSkippedWorkerResult(final WorkerThreadResult<L, A> workerResult) {
		removeCounterexampleFromSet(workerResult.getCounterexample());
		mRunningThreads -= 1;
		mStaleWorkerResultsSkippedAtCoordinator += 1;
	}

	private void countStaleCancellationPoint(final StaleCancellationPoint point) {
		switch (point) {
		case BEFORE_TRANSFER:
			mStaleWorkersCancelledBeforeTransfer += 1;
			break;
		case BEFORE_TRACE_CHECK:
			mStaleWorkersCancelledBeforeTraceCheck += 1;
			break;
		case BEFORE_AUTOMATON:
			mStaleWorkersCancelledBeforeAutomaton += 1;
			break;
		case BEFORE_WORKER_DIFFERENCE:
			mStaleWorkersCancelledBeforeWorkerDifference += 1;
			break;
		case BEFORE_RETURN:
			mStaleWorkersCancelledBeforeReturn += 1;
			break;
		case IMMEDIATE_INTERRUPT:
			mStaleWorkersCancelledByImmediateInterrupt += 1;
			break;
		default:
			throw new AssertionError("Unhandled cancellation point " + point);
		}
	}

	/**
	 * R4: harvest the interpolant predicates of the just-applied refinement (the non-trivial states of the
	 * subtrahend / infeasibility-proof automaton) into the shared pool. Runs on the coordinator thread with the
	 * producing worker idle, so reading its script and writing the main script is single-threaded.
	 */
	private void harvestSharedPredicates(final WorkerThreadResult<L, A> threadResult) {
		if (mPredicatePool == null) {
			return;
		}
		final INwaOutgoingLetterAndTransitionProvider<L, IPredicate> subtrahend = threadResult.getSubtrahend();
		if (!(subtrahend instanceof INestedWordAutomaton)) {
			return;
		}
		mPredicatePool.harvest(((INestedWordAutomaton<L, IPredicate>) subtrahend).getStates(),
				threadResult.getWorkerMgdScript(), mCsToolkit.getManagedScript());
	}

	/**
	 * DIAGNOSTIC for incremental-membership feasibility: a refinement removes states from the minuend; an active
	 * counterexample is stale iff its accepting run used a removed state. Compare that (cheap, O(len)) to the
	 * authoritative full Accepts. If they agree, incremental membership is viable; if the run-state intersection
	 * catches ~0 while full catches many, the in-flight runs are over older abstraction versions (state versioning
	 * blocks the approach).
	 */
	private void diagnoseIncrementalMembership(final IOpWithDelayedDeadEndRemoval<L, IPredicate> diff) {
		if (!mPref.isStaleWorkerCancellationEnabled()) {
			return;
		}
		final Set<IPredicate> removed = new HashSet<>();
		for (final IOpWithDelayedDeadEndRemoval.UpDownEntry<IPredicate> e : diff.getRemovedUpDownEntry()) {
			if (e.getUp() != null) {
				removed.add(e.getUp());
			}
		}
		final AutomataLibraryServices svc = new AutomataLibraryServices(getServices());
		for (final Map.Entry<Integer, NestedRun<L, ?>> ac : mActiveCounterexamples.entrySet()) {
			final NestedRun<L, ?> run = ac.getValue();
			if (run == null) {
				continue;
			}
			boolean runHitsRemoved = false;
			for (final Object s : run.getStateSequence()) {
				if (removed.contains(s)) {
					runHitsRemoved = true;
					break;
				}
			}
			boolean staleFull;
			try {
				staleFull = !new Accepts<>(svc, mAbstraction, run.getWord()).getResult();
			} catch (final AutomataLibraryException ex) {
				continue;
			}
			if (runHitsRemoved) {
				mIncrDiagRunHit += 1;
			}
			if (staleFull) {
				mIncrDiagFull += 1;
			}
			if (runHitsRemoved && staleFull) {
				mIncrDiagBoth += 1;
			}
		}
	}

	private void cancelStaleActiveCounterexamples(
			final INwaOutgoingLetterAndTransitionProvider<L, IPredicate> subtrahend) {
		if (!mPref.isStaleWorkerCancellationEnabled() || !mPref.activeStaleRecheckEnabled()) {
			// Passive-only mode keeps just shouldSkipStaleInfeasibilityResult (one Accepts per returned result),
			// avoiding the per-refinement re-check of every in-flight counterexample.
			return;
		}
		if (mPref.asyncStaleSweepEnabled()) {
			// R6: the active re-check is done off the critical path (submitAsyncStaleSweep, once per iteration after
			// minimization). Skip the synchronous coordinator-thread sweep entirely.
			return;
		}
		// R1 (path-program staleness pre-filter): the refinement just performed is A := A \ subtrahend, so a
		// trace that was a counterexample (accepted by A) becomes stale exactly when it is accepted by the
		// subtrahend automaton. Testing membership in the small subtrahend is much cheaper than re-running
		// Accepts against the full, growing abstraction for every active counterexample (the serialized
		// coordinator overhead behind S2's PAR-4 regression). Sound either way: a missed cancellation only
		// wastes worker effort (the trace stays in the abstraction and is re-found), never a wrong verdict.
		final boolean useSubtrahend = mPref.staleCancellationPrefilterEnabled() && subtrahend != null;
		// R1b: cap the per-counterexample cost of the stale re-check. The Accepts membership test costs roughly
		// abstraction.size() * trace.length(); on programs with long counterexamples and large abstractions (e.g.
		// ECA) this dominates and outweighs the cancellation benefit. Skip the check for a counterexample whose
		// estimated cost exceeds the budget. Sound: skipping forgoes a possible cancellation, never a verdict.
		final long workBudget = mPref.staleCheckWorkBudget();
		final int abstractionSize = mAbstraction.size();
		final AutomataLibraryServices automataServices = new AutomataLibraryServices(getServices());
		for (final Map.Entry<Integer, NestedRun<L, ?>> activeCounterexample : mActiveCounterexamples.entrySet()) {
			final Integer activeTraceHash = activeCounterexample.getKey();
			final NestedRun<L, ?> activeRun = activeCounterexample.getValue();
			if (activeRun == null) {
				continue;
			}
			if (!useSubtrahend && workBudget > 0
					&& (long) abstractionSize * activeRun.getLength() > workBudget) {
				mStaleCheckBudgetSkips += 1;
				continue;
			}
			try {
				final boolean stale;
				if (useSubtrahend) {
					final boolean staleSub =
							new Accepts<>(automataServices, subtrahend, activeRun.getWord()).getResult();
					mStaleCancellationPrefilterChecks += 1;
					// DIAGNOSTIC: compare the cheap subtrahend check to the authoritative full-abstraction check.
					final boolean staleFull =
							!new Accepts<>(automataServices, mAbstraction, activeRun.getWord()).getResult();
					if (staleSub != staleFull) {
						mStaleDiagDisagree += 1;
					} else if (staleFull) {
						mStaleDiagBothStale += 1;
					}
					if (staleFull) {
						mStaleDiagFullStaleTotal += 1;
					}
					if (staleSub) {
						mStaleDiagSubStaleTotal += 1;
					}
					stale = staleSub;
				} else {
					stale = !new Accepts<>(automataServices, mAbstraction, activeRun.getWord()).getResult();
				}
				if (stale) {
					requestStaleCancellation(activeTraceHash);
				}
			} catch (final AutomataLibraryException e) {
				mStaleCancellationAcceptsFailures += 1;
				mLogger.warn("StaleCancellation: failed to check active trace " + activeTraceHash + ": " + e);
			}
		}
	}

	private void requestStaleCancellation(final Integer activeTraceHash) {
		final StaleCancellationToken cancellationToken = mActiveCancellationTokens.get(activeTraceHash);
		if (cancellationToken != null
				&& cancellationToken.requestCancellation("removed from updated abstraction")) {
			mStaleCancellationRequests += 1;
			mLogger.info("StaleCancellation: requested cancellation for active trace " + activeTraceHash);
			if (mPref.isStaleWorkerImmediateCancellationEnabled()) {
				mStaleImmediateStopRequests += 1;
				try {
					if (cancellationToken.requestImmediateInterrupt()) {
						mStaleThreadInterruptRequests += 1;
						mLogger.info("StaleCancellation: immediate interrupt requested for active trace "
								+ activeTraceHash);
					} else {
						mStaleImmediateStopNoThread += 1;
						mLogger.info("StaleCancellation: immediate interrupt had no attached worker for active trace "
								+ activeTraceHash);
					}
				} catch (final SecurityException e) {
					mStaleImmediateStopFailures += 1;
					mLogger.warn("StaleCancellation: failed immediate interrupt for active trace " + activeTraceHash
							+ ": " + e);
				}
			}
		}
	}

	/**
	 * R6: snapshot the current abstraction and the in-flight counterexamples on the coordinator thread (the only
	 * thread that mutates these maps), then run the staleness sweep on a dedicated background thread so its cost is
	 * not on the coordinator's critical path. At most one sweep is in flight at a time; if the previous one has not
	 * finished, this one is coalesced away (sound: a skipped sweep only forgoes cancellations, and the next
	 * refinement submits a fresher sweep).
	 */
	private void submitAsyncStaleSweep() {
		if (mPendingStaleSweep != null && !mPendingStaleSweep.isDone()) {
			mAsyncStaleSweepsCoalesced += 1;
			return;
		}
		// Snapshot the immutable abstraction reference and the (run, token) pairs. The helper thread touches only
		// these locals, never the shared maps, so no synchronization on mActiveCounterexamples is needed.
		final INestedWordAutomaton<L, IPredicate> snapshot = mAbstraction;
		final List<NestedRun<L, ?>> runs = new ArrayList<>();
		final List<StaleCancellationToken> tokens = new ArrayList<>();
		for (final Map.Entry<Integer, NestedRun<L, ?>> e : mActiveCounterexamples.entrySet()) {
			final NestedRun<L, ?> run = e.getValue();
			if (run == null) {
				continue;
			}
			final StaleCancellationToken token = mActiveCancellationTokens.get(e.getKey());
			if (token == null) {
				continue;
			}
			runs.add(run);
			tokens.add(token);
		}
		if (runs.isEmpty()) {
			return;
		}
		if (mStaleSweepExecutor == null) {
			mStaleSweepExecutor = Executors.newSingleThreadExecutor(r -> {
				final Thread t = new Thread(r, "ParallelCegar-StaleSweep");
				t.setDaemon(true);
				return t;
			});
		}
		final AutomataLibraryServices svc = new AutomataLibraryServices(getServices());
		mAsyncStaleSweepsSubmitted += 1;
		mPendingStaleSweep = mStaleSweepExecutor.submit(() -> runAsyncStaleSweep(snapshot, runs, tokens, svc));
	}

	/**
	 * R6: the staleness sweep body, executed on the background thread. For each in-flight counterexample, a trace is
	 * stale iff the updated abstraction no longer accepts it; stale traces get a cooperative cancellation request
	 * (the worker honours it at its next StaleCancellationPoint). Only the COOPERATIVE token flag is used (no thread
	 * interrupt) since the token is flipped from another thread. Any failure forgoes cancellations for this round
	 * and degrades to the baseline behaviour (sound).
	 */
	private void runAsyncStaleSweep(final INestedWordAutomaton<L, IPredicate> snapshot,
			final List<NestedRun<L, ?>> runs, final List<StaleCancellationToken> tokens,
			final AutomataLibraryServices svc) {
		for (int i = 0; i < runs.size(); i++) {
			try {
				if (!new Accepts<>(svc, snapshot, runs.get(i).getWord()).getResult()) {
					if (tokens.get(i).requestCancellation("removed from updated abstraction (async sweep)")) {
						mAsyncStaleCancellations.incrementAndGet();
					}
				}
			} catch (final AutomataLibraryException | RuntimeException ex) {
				mLogger.warn("Async stale sweep failed for an in-flight trace: " + ex);
			}
		}
	}

	/*
	 * S3 (adaptive worker scaling): true iff the candidate's path program (the set of its trace letters, the
	 * same representative used by PathProgramCache) differs from every path program currently in flight. The
	 * in-flight set is mActiveCounterexamples (at most mThreadLimit entries), so this is computed on demand.
	 */
	private boolean addsDistinctInFlightPathProgram(final NestedRun<L, ?> candidate) {
		final Set<L> candidatePathProgram = new HashSet<>(candidate.getWord().asList());
		for (final NestedRun<L, ?> active : mActiveCounterexamples.values()) {
			if (candidatePathProgram.equals(new HashSet<>(active.getWord().asList()))) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Only add a counterexample if it is being checked by a thread otherwise we are unsound
	 */
	private void addCounterexampleToSet(final NestedRun<L, ?> counterexample) {
		final List<L> trace = counterexample.getWord().asList();
		final int traceHash = trace.hashCode();
		if (mActiveCounterexamples.containsKey(traceHash)) {
			throw new AssertionError("IsEmpty(Parallel) Found the same counterexample twice!");
		}
		mActiveCounterexamples.put(traceHash, counterexample);
	}

	/*
	 * OnlyActive means only countereamples actively being checked by workers. The alternative is all previously found
	 * counterexamples.
	 */
	private void removeCounterexampleFromSet(final IRun<L, ?> cex) {
		final List<L> trace = cex.getWord().asList();
		final int traceHash = trace.hashCode();
		mLogger.info("Subtrahend traceHash: " + traceHash);
		mActiveCancellationTokens.remove(traceHash);
		// Only remove after the counterexample is no longer in the abstraction
		if (mPref.considerOnlyActiveCounterexamplesInIsEmptyParallel()) {
			mActiveCounterexamples.remove(traceHash);
		} else {
			if (mCounterexamplesToBeRemovedFromActiveCexMap == null) {
				return;
			}
			mCounterexamplesToBeRemovedFromActiveCexMap.add(traceHash);
		}
	}

	/*
	 * The worker using this method to get the Abstraction need to ensure, they use the @TransferBetweenMainAndWorker To
	 * transfer the abstraction to their cfgscript. Worker may only use this to read-only access the abstraction!
	 */
	public INestedWordAutomaton<L, IPredicate> getAbstraction() {
		return mAbstraction;
	}

	private IsEmpty<L, IPredicate> getSearch(final IsEmpty.SearchStrategy strategy,
			final Set<IPredicate> possibleEndPoints) throws AutomataOperationCanceledException {
		switch (strategy) {
		case PARALLEL:
			return new IsEmptyParallel<>(new AutomataLibraryServices(mServices), mAbstraction,
					mAbstraction.getInitialStates(), Collections.emptySet(), possibleEndPoints,
					possibleEndPoints == null, IsEmpty.SearchStrategy.BFS, mActiveCounterexamples,
					mPref.getSearchLoopBound());
		default:
			return new IsEmpty<>(new AutomataLibraryServices(getServices()), mAbstraction, strategy);
		}
	}

	// If search was BFS, the counterexample might not be fresh.
	private boolean isSearchCorrectAndTraceFresh(final IsEmpty<L, IPredicate> search) {
		boolean correct = false;
		boolean fresh = true;
		try {
			correct = search.checkResult(mStateFactoryForRefinement);
		} catch (final AutomataLibraryException e) {
			e.printStackTrace();
			assert false;
		}

		final NestedRun<L, IPredicate> run = search.getNestedRun();
		if (run != null) {
			final List<L> trace = run.getWord().asList();
			final int traceHash = trace.hashCode();
			if (mActiveCounterexamples.containsKey(traceHash)) {
				fresh = false;
			}
			return correct && fresh;
		}
		return false;
	}

	/*
	 * Search for an error trace in the current mAbstraction. First time with a new abstraction we try BFS, then
	 * IsEmptyParallel
	 */
	private NestedRun<L, IPredicate> searchForErrorTrace(final boolean onlyDoIsEmptyParallel)
			throws AutomataOperationCanceledException {
		final long time = System.nanoTime() / 1000000000;
		final Set<IPredicate> possibleEndPoints = null;

		IsEmpty<L, IPredicate> search;
		if (!onlyDoIsEmptyParallel) {
			search = getSearch(IsEmpty.SearchStrategy.BFS, possibleEndPoints);
			if (isSearchCorrectAndTraceFresh(search)) {
				mCountBfsFoundCex += 1;
				mLogger.info("Found new Counterexample via BFS!");
				return search.getNestedRun();
			}
		}
		// A2: route trace selection to DPPI when configured. Alg. 4 (the ALG4_PREFIX path below) is left intact.
		if (mPref.getTraceSelectionStrategy() == TraceSelectionStrategy.DPPI) {
			final NestedRun<L, IPredicate> dppiRun = searchForErrorTraceDppi(possibleEndPoints);
			mSearchTime += ((System.nanoTime() / 1000000000) - time);
			if (dppiRun != null) {
				return dppiRun;
			}
			mLogger.info("Did not Find a Counterexample (DPPI)!");
			mCountFailedToFindCex += 1;
			return null;
		}
		// DIVERSITY: IDF-weighted-novelty trace selection (the derived diversity metric). Alg.4 path left intact.
		if (mPref.getTraceSelectionStrategy() == TraceSelectionStrategy.DIVERSITY) {
			final NestedRun<L, IPredicate> divRun = searchForErrorTraceDiversity(possibleEndPoints);
			mSearchTime += ((System.nanoTime() / 1000000000) - time);
			if (divRun != null) {
				return divRun;
			}
			mLogger.info("Did not Find a Counterexample (DIVERSITY)!");
			mCountFailedToFindCex += 1;
			return null;
		}
		search = getSearch(IsEmpty.SearchStrategy.PARALLEL, possibleEndPoints);
		if (isSearchCorrectAndTraceFresh(search)) {
			mLogger.info("Found new Counterexample via IsEmptyParallel!");
			return search.getNestedRun();
		}
		mLogger.info("Did not Find a Counterexample!");
		mCountFailedToFindCex += 1;
		assert mRunningThreads > 0;

		mSearchTime += ((System.nanoTime() / 1000000000) - time);
		return null;
	}

	/*
	 * A2 (DPPI): path-program-based trace selection, implemented as a SEPARATE function so the Alg. 4 code path
	 * (ALG4_PREFIX, the default) is left fully intact. It enumerates up to DPPI_MAX_CANDIDATES diverse traces by
	 * repeatedly running the existing diverse search (IsEmptyParallel) with an augmented avoid-set, then selects
	 * the candidate whose path program (its edge set, the representative PathProgramCache uses) is not already in
	 * flight and shares the fewest edges with in-flight tasks (ties broken by shorter trace = cheaper to check).
	 *
	 * Fairness floor: if every enumerated candidate's path program is already in flight, return the first diverse
	 * trace found anyway. This guarantees that whenever an unanalysed trace exists DPPI returns one, preserving
	 * progress/termination and L(A)=emptyset => SAFE (the verdict never depends on which trace is chosen).
	 */
	private NestedRun<L, IPredicate> searchForErrorTraceDppi(final Set<IPredicate> possibleEndPoints)
			throws AutomataOperationCanceledException {
		final HashMap<Integer, NestedRun<L, ?>> avoid = new HashMap<>(mActiveCounterexamples);
		final Set<L> inFlightEdges = inFlightEdgeUnion();
		NestedRun<L, IPredicate> firstFound = null;
		NestedRun<L, IPredicate> best = null;
		int bestOverlap = Integer.MAX_VALUE;
		int bestLength = Integer.MAX_VALUE;
		for (int k = 0; k < DPPI_MAX_CANDIDATES; k++) {
			final IsEmpty<L, IPredicate> search = new IsEmptyParallel<>(new AutomataLibraryServices(mServices),
					mAbstraction, mAbstraction.getInitialStates(), Collections.emptySet(), possibleEndPoints,
					possibleEndPoints == null, IsEmpty.SearchStrategy.BFS, avoid, mPref.getSearchLoopBound());
			if (!isSearchCorrectAndTraceFresh(search)) {
				break;
			}
			final NestedRun<L, IPredicate> cand = search.getNestedRun();
			if (cand == null) {
				break;
			}
			mDppiCandidatesScanned += 1;
			if (firstFound == null) {
				firstFound = cand;
			}
			final List<L> word = cand.getWord().asList();
			final Set<L> pathProgram = new HashSet<>(word);
			if (!isPathProgramInFlight(pathProgram)) {
				int overlap = 0;
				for (final L edge : pathProgram) {
					if (inFlightEdges.contains(edge)) {
						overlap += 1;
					}
				}
				final int length = word.size();
				if (overlap < bestOverlap || (overlap == bestOverlap && length < bestLength)) {
					best = cand;
					bestOverlap = overlap;
					bestLength = length;
				}
				if (overlap == 0) {
					// Already fully path-program-disjoint from in-flight tasks; cannot do better.
					break;
				}
			}
			// Exclude this candidate so the next search returns a different diverse trace.
			avoid.put(word.hashCode(), cand);
		}
		if (best == null) {
			// Fairness floor: only in-flight path programs are currently selectable; take the diverse trace so
			// progress/termination and L(A)=emptyset => SAFE are preserved.
			if (firstFound != null) {
				mDppiFairnessFallback += 1;
				mLogger.info("DPPI: fairness fallback to diverse trace (all candidate path programs in flight)");
			}
			return firstFound;
		}
		mDppiNovelSelected += 1;
		mLogger.info("DPPI: selected path-program-disjoint trace with in-flight edge overlap " + bestOverlap);
		return best;
	}

	/*
	 * DPPI helper: true iff the given path program (edge set) equals the path program of some in-flight trace.
	 */
	private boolean isPathProgramInFlight(final Set<L> pathProgram) {
		for (final NestedRun<L, ?> active : mActiveCounterexamples.values()) {
			if (pathProgram.equals(new HashSet<>(active.getWord().asList()))) {
				return true;
			}
		}
		return false;
	}

	/*
	 * DPPI helper: union of edges over all in-flight path programs (the Overlap reference set).
	 */
	private Set<L> inFlightEdgeUnion() {
		final Set<L> edges = new HashSet<>();
		for (final NestedRun<L, ?> active : mActiveCounterexamples.values()) {
			edges.addAll(active.getWord().asList());
		}
		return edges;
	}

	/**
	 * Inverse document frequency of a CFG edge over the dispatched path programs: log((1+N)/(1+df)). Edges in
	 * (almost) every path program — the structural core (entry, error location, loop heads) — get idf ~ 0 and so
	 * do not count toward overlap; rare, reason-bearing edges dominate. This is the correction that plain
	 * edge-overlap (DPPI) lacked.
	 */
	private double idf(final L edge) {
		final int df = mEdgeDocFreq.getOrDefault(edge, 0);
		return Math.log((1.0 + mPathProgramsSeen) / (1.0 + df));
	}

	/**
	 * DIVERSITY metric: the fraction of a candidate path program's IDF-weighted edges that are NOT already covered
	 * by the in-flight set — i.e. how much NEW (discriminative) infeasibility reason this trace would bring. 1 =
	 * entirely new reason, 0 = its discriminative edges are all already being worked on. O(|P(t)|).
	 */
	private double weightedNovelty(final Set<L> pathProgram, final Set<L> coveredEdges) {
		double num = 0.0;
		double den = 0.0;
		for (final L edge : pathProgram) {
			final double w = idf(edge);
			den += w;
			if (!coveredEdges.contains(edge)) {
				num += w;
			}
		}
		return den == 0.0 ? 0.0 : num / den;
	}

	/**
	 * DIVERSITY trace selection: enumerate up to DPPI_MAX_CANDIDATES fresh error traces (one extra emptiness search
	 * each, like DPPI) and dispatch the one with the highest IDF-weighted novelty vs the in-flight set. Fairness
	 * floor: if no candidate brings new discriminative weight, fall back to the first found trace so progress /
	 * termination / "L(A)=emptyset => SAFE" are preserved. Alg.4 is reason-blind; this prioritises the trace with
	 * the most novel infeasibility reason, spreading the workers over distinct reasons to cut redundant refinements.
	 */
	private NestedRun<L, IPredicate> searchForErrorTraceDiversity(final Set<IPredicate> possibleEndPoints)
			throws AutomataOperationCanceledException {
		final HashMap<Integer, NestedRun<L, ?>> avoid = new HashMap<>(mActiveCounterexamples);
		final Set<L> covered = inFlightEdgeUnion();
		NestedRun<L, IPredicate> firstFound = null;
		NestedRun<L, IPredicate> best = null;
		double bestNovelty = -1.0;
		for (int k = 0; k < DPPI_MAX_CANDIDATES; k++) {
			final IsEmpty<L, IPredicate> search = new IsEmptyParallel<>(new AutomataLibraryServices(mServices),
					mAbstraction, mAbstraction.getInitialStates(), Collections.emptySet(), possibleEndPoints,
					possibleEndPoints == null, IsEmpty.SearchStrategy.BFS, avoid, mPref.getSearchLoopBound());
			if (!isSearchCorrectAndTraceFresh(search)) {
				break;
			}
			final NestedRun<L, IPredicate> cand = search.getNestedRun();
			if (cand == null) {
				break;
			}
			mDiversityCandidatesScanned += 1;
			if (firstFound == null) {
				firstFound = cand;
			}
			final List<L> word = cand.getWord().asList();
			final double novelty = weightedNovelty(new HashSet<>(word), covered);
			if (novelty > bestNovelty) {
				bestNovelty = novelty;
				best = cand;
			}
			avoid.put(word.hashCode(), cand);
		}
		if (best != null && bestNovelty > 0.0) {
			mDiversityNovelSelected += 1;
			mLogger.info("DIVERSITY: selected trace with weighted novelty " + bestNovelty);
			return best;
		}
		if (firstFound != null) {
			mDiversityFairnessFallback += 1;
		}
		return firstFound;
	}

	@Override
	protected INwaOutgoingLetterAndTransitionProvider<L, IPredicate> enhanceInterpolantAutomaton(
			final InterpolantAutomatonEnhancement enhanceMode, final IPredicateUnifier predicateUnifier,
			final IHoareTripleChecker htc, final NestedWordAutomaton<L, IPredicate> interpolantAutomaton) {
		final INwaOutgoingLetterAndTransitionProvider<L, IPredicate> subtrahend;
		// Worker does the enhancement or nobody!
		subtrahend = interpolantAutomaton;
		return subtrahend;
	}

	/*
	 * Difference is calculated twice first in worker and then in master. All automata obtained from the worker need to
	 * be transferred to the master cfg script!
	 */
	private IOpWithDelayedDeadEndRemoval<L, IPredicate> computeAutomataDifference(
			final INestedWordAutomaton<L, IPredicate> minuend, final WorkerThreadResult<L, A> workerResult,
			final PredicateFactoryRefinement stateFactoryForRefinement)
			throws AutomataLibraryException, AssertionError {
		try {
			mLogger.debug("Start constructing difference");

			final PowersetDeterminizer<L, IPredicate> psd = new PowersetDeterminizer<>(workerResult.getSubtrahend(),
					true, mPredicateFactoryInterpolantAutomata);
			IOpWithDelayedDeadEndRemoval<L, IPredicate> diff;
			try {
				if (mPref.differenceSenwa()) {
					diff = new DifferenceSenwa<>(new AutomataLibraryServices(getServices()), stateFactoryForRefinement,
							minuend, workerResult.getSubtrahend(), psd, false);
				} else {
					diff = new Difference<>(new AutomataLibraryServices(getServices()), stateFactoryForRefinement,
							minuend, workerResult.getSubtrahend(), psd, workerResult.exploitSigmaStarConcatOfIa());
				}
				mCegarLoopBenchmark.reportInterpolantAutomatonStates(workerResult.getSubtrahend().size());

			} catch (final AutomataOperationCanceledException | ToolchainCanceledException tce) {
				throw tce;
			} finally {
				// We never enhance in main thread!
			}

			if (!workerResult.useErrorAutomaton()) {
				// TODO needs to get the worker counterexample
				// checkEnhancement(workerResult.getSubtrahendBeforeEnhancement(),
				// workerResult.getSubtrahend());
			}
			// Future work:
			assert !mPref.dumpOnlyReuseAutomata();
			assert mFaultLocalizationMode == RelevanceAnalysisMode.NONE;

			if (REMOVE_DEAD_ENDS) {
				diff.removeDeadEnds();
			}
			return diff;
		} finally {
		}
	}

	/**
	 * @param services
	 * @param filename
	 */
	private SolverSettings getSolverSettings(final IUltimateServiceProvider services, final String filename) {

		final IPreferenceProvider prefs = mServices.getPreferenceProvider(Activator.PLUGIN_ID);

		final SolverMode solverMode = prefs.getEnum(RcfgPreferenceInitializer.LABEL_SOLVER, SolverMode.class);

		final boolean fakeNonIncrementalScript =
				prefs.getBoolean(RcfgPreferenceInitializer.LABEL_FAKE_NON_INCREMENTAL_SCRIPT);

		final boolean dumpSmtScriptToFile = prefs.getBoolean(RcfgPreferenceInitializer.LABEL_DUMP_TO_FILE);
		final boolean compressSmtScript = prefs.getBoolean(RcfgPreferenceInitializer.LABEL_COMPRESS_SMT_DUMP_FILE);
		final String pathOfDumpedScript = prefs.getString(RcfgPreferenceInitializer.LABEL_DUMP_PATH);

		final String commandExternalSolver = prefs.getString(RcfgPreferenceInitializer.LABEL_EXT_SOLVER_COMMAND);

		final boolean dumpUnsatCoreTrackBenchmark =
				prefs.getBoolean(RcfgPreferenceInitializer.LABEL_DUMP_UNSAT_CORE_BENCHMARK);

		final boolean dumpMainTrackBenchmark =
				prefs.getBoolean(RcfgPreferenceInitializer.LABEL_DUMP_MAIN_TRACK_BENCHMARK);

		final Map<String, String> additionalSmtOptions =
				prefs.getKeyValueMap(RcfgPreferenceInitializer.LABEL_ADDITIONAL_SMT_OPTIONS);

		final Logics logicForExternalSolver =
				Logics.valueOf(prefs.getString(RcfgPreferenceInitializer.LABEL_EXT_SOLVER_LOGIC));
		final SolverSettings solverSettings =
				SolverBuilder.constructSolverSettings().setUseFakeIncrementalScript(fakeNonIncrementalScript)
						.setDumpSmtScriptToFile(dumpSmtScriptToFile, pathOfDumpedScript, filename, compressSmtScript)
						.setDumpUnsatCoreTrackBenchmark(dumpUnsatCoreTrackBenchmark)
						.setDumpMainTrackBenchmark(dumpMainTrackBenchmark)
						.setUseExternalSolver(true, commandExternalSolver, logicForExternalSolver)
						.setSolverMode(solverMode).setAdditionalOptions(additionalSmtOptions);

		return solverSettings;
	}

	/**
	 * Lazy-minimization gate (improvement): when enabled, skip minimizing a refined abstraction while it is still
	 * below the configured size threshold, minimizing only once it grows past it. On control-flow-heavy programs
	 * minimization frequently does not reduce the CEGAR iteration count, so it is pure overhead; skipping it there is
	 * a direct speedup. Sound: minimization produces a language-equivalent automaton, so skipping/deferring it never
	 * changes a verdict, and the threshold bounds the abstraction growth.
	 */
	private boolean shouldMinimizeNow() {
		if (!mPref.lazyMinimizationEnabled()) {
			return true;
		}
		final boolean minimize = mAbstraction.size() >= mPref.lazyMinimizationThreshold();
		if (!minimize) {
			mLazyMinimizationSkips += 1;
		}
		return minimize;
	}

	/**
	 * Relative-growth minimization trigger (the robust general version of Minimization=NONE): minimize only once the
	 * abstraction has grown by at least the configured percentage since the last minimization. On ECA/control-flow
	 * the abstraction grows slowly per refinement, so most minimizations are skipped (recovering NONE's wall win);
	 * on loop-unrolling programs the abstraction blows up fast, so the trigger fires and keeps it bounded (avoiding
	 * the un-minimized CPU/size penalty and the blowup risk that makes pure NONE unsafe). Sound: minimization is
	 * language-preserving, so skipping/deferring it never changes a verdict. OFF -> minimize every refined iteration
	 * (the paper's behaviour).
	 */
	/**
	 * Loop-aware minimization (per refinement, in the per-worker minimization path which is the active one): minimize
	 * this refinement only when its counterexample's path program has recurred at least the threshold number of
	 * times (loop unrolling — the same loop body refined repeatedly), which is exactly when the un-minimized
	 * abstraction blows up and minimization is load-bearing. Diverse error traces (ECA/control-flow) keep the count
	 * at ~1, so they skip minimization and get the wall win of NONE. Sound: minimization is language-preserving, so
	 * skipping it never changes a verdict. OFF -> always minimize (subject to the other gates).
	 */
	private boolean minimizeGivenLoopAware(final WorkerThreadResult<L, A> threadResult) {
		if (!mPref.loopAwareMinimizationEnabled()) {
			return true;
		}
		final int ppc = threadResult.getCounterexample() != null
				? mProgramCache.getPathProgramCount(threadResult.getCounterexample().getWord()) : 0;
		if (ppc >= mPref.loopAwareMinimizationThreshold()) {
			mLoopAwareMinimizations += 1;
			return true;
		}
		mLoopAwareSkips += 1;
		return false;
	}

	private boolean shouldMinimizeByGrowth() {
		if (!mPref.relativeGrowthMinimizationEnabled()) {
			return true;
		}
		if (mAbstractionSizeAtLastMinimization < 0) {
			mAbstractionSizeAtLastMinimization = mAbstraction.size();
		}
		if (mAbstractionSizeAtLastMinimization == 0) {
			return true;
		}
		final long trigger =
				(long) mAbstractionSizeAtLastMinimization * (100 + mPref.minimizationGrowthPercent()) / 100;
		if (mAbstraction.size() >= trigger) {
			return true;
		}
		mGrowthMinimizationSkips += 1;
		return false;
	}

	private void minimizeAbstractionIfEnabled(final PredicateFactoryRefinement stateFactoryForRefinement,
			final PredicateFactoryResultChecking predicateFactoryResultChecking)
			throws AutomataOperationCanceledException, AutomataLibraryException, AssertionError {
		final Minimization minimization = mPref.getMinimization();
		switch (minimization) {
		case NONE:
			// do not apply minimization
			break;
		case DFA_HOPCROFT_LISTS:
		case DFA_HOPCROFT_ARRAYS:
		case MINIMIZE_SEVPA:
		case SHRINK_NWA:
		case NWA_MAX_SAT:
		case NWA_MAX_SAT2:
		case RAQ_DIRECT_SIMULATION:
		case RAQ_DIRECT_SIMULATION_B:
		case NWA_COMBINATOR_PATTERN:
		case NWA_COMBINATOR_EVERY_KTH:
		case NWA_OVERAPPROXIMATION:
		case NWA_COMBINATOR_MULTI_DEFAULT:
		case NWA_COMBINATOR_MULTI_SIMULATION:
			// apply minimization
			minimizeAbstraction(stateFactoryForRefinement, predicateFactoryResultChecking, minimization);
			break;
		default:
			throw new AssertionError();
		}
	}

	/**
	 * Automata theoretic minimization of the automaton stored in mAbstraction. Expects that mAbstraction does not have
	 * dead ends.
	 *
	 * @param predicateFactoryRefinement
	 *            PredicateFactory for the construction of the new (minimized) abstraction.
	 * @param resultCheckPredFac
	 *            PredicateFactory used for auxiliary automata used for checking correctness of the result (if
	 *            assertions are enabled).
	 */
	@Override
	protected void minimizeAbstraction(final PredicateFactoryRefinement predicateFactoryRefinement,
			final PredicateFactoryResultChecking resultCheckPredFac, final Minimization minimization)
			throws AutomataOperationCanceledException, AutomataLibraryException, AssertionError {

		final Function<IPredicate, Set<IcfgLocation>> lcsProvider =
				x -> (x instanceof ISLPredicate ? Collections.singleton(((ISLPredicate) x).getProgramPoint())
						: new HashSet<>(Arrays.asList(((IMLPredicate) x).getProgramPoints())));
		AutomataMinimization<Set<IcfgLocation>, IPredicate, L> am;
		try {
			am = new AutomataMinimization<>(getServices(), mAbstraction, minimization, mComputeHoareAnnotation,
					getIteration(), predicateFactoryRefinement, MINIMIZE_EVERY_KTH_ITERATION,
					mStoredRawInterpolantAutomata, mInterpolAutomaton, MINIMIZATION_TIMEOUT, resultCheckPredFac,
					lcsProvider, true);
		} catch (final AutomataMinimizationTimeout e) {
			mCegarLoopBenchmark.addAutomataMinimizationData(e.getStatistics());
			throw e.getAutomataOperationCanceledException();
		}
		mCegarLoopBenchmark.addAutomataMinimizationData(am.getStatistics());
		final boolean newAutomatonWasBuilt = am.newAutomatonWasBuilt();

		if (newAutomatonWasBuilt) {
			// postprocessing after minimization
			final IDoubleDeckerAutomaton<L, IPredicate> newAbstraction = am.getMinimizedAutomaton();

			// extract Hoare annotation
			if (mComputeHoareAnnotation) {
				final Map<IPredicate, IPredicate> oldState2newState = am.getOldState2newStateMapping();
				if (oldState2newState == null) {
					throw new AssertionError("Hoare annotation and " + minimization + " incompatible");
				}
			}

			// statistics
			final int oldSize = mAbstraction.size();
			final int newSize = newAbstraction.size();
			assert oldSize == 0 || oldSize >= newSize : "Minimization increased state space";

			// use result
			mAbstraction = newAbstraction;
		}
	}

	// worker use this method to access the programcache shared across all workers + main
	public PathProgramCache<L> getCurrentProgramCache() {
		return mProgramCache;
	}

	public void reportFailedContinuesWorkerThread() {
		final IcfgLocation currentErrorLoc = getErrorLocFromCounterexample();
		final IUltimateServiceProvider iterationServices = createIterationTimer(currentErrorLoc);
		try {
			setUpContinuesWorker(iterationServices, 0);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}
}
