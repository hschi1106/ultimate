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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
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
import de.uni_freiburg.informatik.ultimate.automata.nestedword.NestedWordAutomaton;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.operations.Difference;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.operations.IsEmpty;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.operations.IsEmptyParallel;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.operations.PathStepKey;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.operations.PrefixCoverageCache;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.operations.PowersetDeterminizer;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.operations.TraceSearchSelectionMode;
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
import de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction.preferences.TraceAbstractionPreferenceInitializer.AdaptiveBatchTriggerMode;
import de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction.preferences.TraceAbstractionPreferenceInitializer.Minimization;
import de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction.preferences.TraceAbstractionPreferenceInitializer.RelevanceAnalysisMode;
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
	BlockingQueue<IRun<L, ?>> mWorkerTaskQueue = new LinkedBlockingQueue<>();
	BlockingQueue<WorkerThreadResult<L, A>> mWorkerResultQueue = new LinkedBlockingQueue<>();

	// need global program cache, but worker need to get copy otherwise we
	// synchronize
	private final PathProgramCache<L> mProgramCache = new PathProgramCache<>(mLogger);

	// Strategies
	public final HashMap<Integer, NestedRun<L, ?>> mActiveCounterexamples = new HashMap<>();
	private final Set<Integer> mCounterexamplesToBeRemovedFromActiveCexMap = new HashSet<>();
	private final PrefixCoverageCache<L, IPredicate> mPrefixCoverageCache = new PrefixCoverageCache<>();
	private final TraceSearchSelectionMode mTraceSearchSelectionMode;
	private final boolean mUseInitialBfsInParallelTraceSearch;
	private final boolean mTrackStalePrefixesInParallelTraceSearch;
	private final int mBatchLcpsCandidateMultiplier;
	private final int mBatchLcpsCandidateCap;
	private final AdaptiveBatchTriggerMode mAdaptiveBatchTriggerMode;
	private final int mAdaptiveBatchMinAvailableSlots;
	protected InterpolationTechnique mInterpolationTechnique;

	protected Class<L> mTransitionClazz;

	// Addtional Statistiks for Evaluation
	private Integer mCounterexamplesChecked = 0;
	private Integer mRefinementsDone = 0;
	private final Integer mCountTimeoutsInSearch = 0;
	private final Integer mCountFailedRunConstructions = 0;
	private Integer mCountFailedToFindCex = 0;
	private Integer mCountDuplicateFreshnessFailures = 0;
	private Integer mCountBfsFoundCex = 1;
	private Integer mCountIsEmptyParallel = 0;
	private Integer mLcpsSearchInvocations = 0;
	private Integer mLcpsFullCacheSuffixInvocations = 0;
	private Integer mLcpsFullCacheSuffixFallbacks = 0;
	private Integer mLcpsEffectivePriorityDecisions = 0;
	private Integer mBatchLcpsInvocations = 0;
	private Integer mBatchLcpsAvailableSlotsTotal = 0;
	private Integer mBatchLcpsCandidatesGenerated = 0;
	private Integer mBatchLcpsCandidatesSelected = 0;
	private Integer mBatchLcpsCandidateGenerationFailures = 0;
	private Integer mBatchLcpsEffectiveBatchDecisions = 0;
	private long mBatchLcpsCandidateGenerationTimeMs = 0;
	private long mBatchLcpsSelectionTimeMs = 0;
	private boolean mTriggerDuplicateSinceLastDispatch;
	private boolean mTriggerStaleSinceLastDispatch;
	private boolean mTriggerSearchFailedSinceLastDispatch;
	private boolean mTriggerIdleSlotSinceLastDispatch;
	private boolean mFirstDispatchInCurrentAbstraction = true;
	private Integer mAdaptiveBatchInvocations = 0;
	private Integer mAdaptiveBatchFallbacks = 0;
	private Integer mAdaptiveTriggeredByDuplicate = 0;
	private Integer mAdaptiveTriggeredByStale = 0;
	private Integer mAdaptiveTriggeredBySearchFailed = 0;
	private Integer mAdaptiveTriggeredByIdleSlot = 0;
	private Integer mAdaptiveTriggeredByFirstFill = 0;
	private Integer mAdaptiveTriggeredByFirstFillOrStale = 0;
	private Integer mAdaptiveTriggeredByThreadsGe4FirstFill = 0;
	private Integer maxActiveThreads = 0;
	private final Integer mActiveExecutors = 0;
	private long mSearchTime = 0;
	private long mWorkerSetUpTime = 0;
	private int mIterationsWithMaxThreads = 0;
	private int mIterationsWithOneThread = 0;
	private final int mExceptionInWorker = 0;

	private long mRefinementTime = 0;

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
		mTraceSearchSelectionMode = mPref.getParallelTraceSearchSelectionMode();
		mUseInitialBfsInParallelTraceSearch = mPref.useInitialBfsInParallelTraceSearch();
		mTrackStalePrefixesInParallelTraceSearch = mPref.trackStalePrefixesInParallelTraceSearch();
		mBatchLcpsCandidateMultiplier = mPref.getBatchLcpsCandidateMultiplier();
		mBatchLcpsCandidateCap = mPref.getBatchLcpsCandidateCap();
		mAdaptiveBatchTriggerMode = mPref.getAdaptiveBatchTriggerMode();
		mAdaptiveBatchMinAvailableSlots = mPref.getAdaptiveBatchMinAvailableSlots();
		// Start thread pool
		mThreadLimit = mPref.getThreadLimit();
		if (mThreadLimit == 0) { // maximum of available cores
			mThreadLimit = Runtime.getRuntime().availableProcessors();
			mThreadLimit -= 1; // one for main thread
		}

		mExec = Executors.newFixedThreadPool(mThreadLimit);
		Thread.currentThread().setName("Main Cegar Thread");
		getServices().getStorage().pushMarker(mDestroyEverything);
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

		// start worker for initial cex:
		startWorker();

		for (mIteration = 1; mIteration <= mPref.maxIterations(); mIteration++) {
			abortIfTimeout();
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
						// If Error automaton terminate immediately
						if (mPref.stopAfterFirstViolation()
								&& workerResult.getAutomatonType().equals(AutomatonType.ERROR)) {
							shutDownAndDestroy(mDestroyEverything);
							mLogger.info("Result: " + Result.UNSAFE);
							updateAndPrintStatistics(true);
							return;
						}

						mLogger.info("Worker Automaton Type: " + workerResult.getAutomatonType());
						recordStaleCounterexampleIfNoLongerAccepted(workerResult.getCounterexample());
						mLogger.info("Refining Abstraction");
						refinement(workerResult);
						mRefinementsDone += 1;
						abstractionWasRefined = true;
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
			if (abstractionWasRefined && !mPref.minimizeAbstractionPerWorker()) {
				// uses NWA CEGAR loop
				// When do we minimize how often?
				minimizeAbstractionIfEnabled();
			}
			if (abstractionWasRefined) {
				// If we didnt find one we wait until we refine the abstraction
				didntFindCexLastIteration = false;
			}

			if (!didntFindCexLastIteration) {
				if (mTraceSearchSelectionMode == TraceSearchSelectionMode.BATCH_LCPS) {
					didntFindCexLastIteration = dispatchBatchLcps();
				} else if (mTraceSearchSelectionMode == TraceSearchSelectionMode.ADAPTIVE_BATCH_LCPS) {
					didntFindCexLastIteration = dispatchAdaptiveBatchLcps();
				} else {
					didntFindCexLastIteration = dispatchOneByOne(mTraceSearchSelectionMode);
				}
			}
			updateAndPrintStatistics(false);
		}
		mExec.shutdownNow();
		mLogger.info("Result: " + Result.USER_LIMIT_ITERATIONS);
		updateAndPrintStatistics(true);
		mResultBuilder.addResultForAllRemaining(Result.USER_LIMIT_ITERATIONS);

	}

	private boolean dispatchBatchLcps() throws AutomataOperationCanceledException {
		final int availableSlots = mThreadLimit - mRunningThreads;
		if (availableSlots <= 0) {
			return false;
		}
		final List<NestedRun<L, IPredicate>> batch = searchBatchForErrorTraces(availableSlots);
		if (batch.isEmpty()) {
			recordIdleSlotTriggerIfAny();
			return true;
		}
		startWorkersForBatch(batch);
		recordIdleSlotTriggerIfAny();
		return false;
	}

	private boolean dispatchAdaptiveBatchLcps() throws AutomataOperationCanceledException {
		final int availableSlots = mThreadLimit - mRunningThreads;
		if (availableSlots <= 0) {
			return false;
		}
		if (shouldUseAdaptiveBatch(availableSlots)) {
			mAdaptiveBatchInvocations += 1;
			incrementAdaptiveTriggerCounter();
			resetAdaptiveTriggerFlagForMode();
			final List<NestedRun<L, IPredicate>> batch = searchBatchForErrorTraces(availableSlots);
			if (!batch.isEmpty()) {
				startWorkersForBatch(batch);
				recordIdleSlotTriggerIfAny();
				return false;
			}
			mAdaptiveBatchFallbacks += 1;
		} else {
			mAdaptiveBatchFallbacks += 1;
			resetAdaptiveTriggerFlagForMode();
		}
		final boolean didntFindCex = dispatchOneByOne(TraceSearchSelectionMode.PAPER);
		recordIdleSlotTriggerIfAny();
		return didntFindCex;
	}

	private boolean dispatchOneByOne(final TraceSearchSelectionMode searchMode) throws AutomataOperationCanceledException {
		/*
		 * Selection mode controls whether we use BFS/DFS directly or the parallel selector. PAPER/LCPS may keep one
		 * initial BFS attempt per abstraction for backwards-compatible experiments.
		 */
		boolean firstIteration = true;
		while (mRunningThreads < mThreadLimit) {
			assert mRunningThreads >= 0;
			final int availableSlots = mThreadLimit - mRunningThreads;
			mCounterexample = searchForErrorTrace(!firstIteration, searchMode);
			if (mCounterexample == null) {
				if (availableSlots > 0) {
					mTriggerSearchFailedSinceLastDispatch = true;
				}
				recordIdleSlotTriggerIfAny();
				return true;
			}
			startWorker();
			firstIteration = false;
		}
		recordIdleSlotTriggerIfAny();
		return false;
	}

	private void startWorkersForBatch(final List<NestedRun<L, IPredicate>> batch) {
		for (final NestedRun<L, IPredicate> run : batch) {
			mCounterexample = run;
			startWorker();
		}
	}

	private void recordIdleSlotTriggerIfAny() {
		if (mRunningThreads > 0 && mRunningThreads < mThreadLimit) {
			mTriggerIdleSlotSinceLastDispatch = true;
		}
	}

	private boolean shouldUseAdaptiveBatch(final int availableSlots) {
		if (mAdaptiveBatchTriggerMode == AdaptiveBatchTriggerMode.ALWAYS_BATCH) {
			return true;
		}
		if (mAdaptiveBatchTriggerMode == AdaptiveBatchTriggerMode.NEVER_BATCH) {
			return false;
		}
		if (availableSlots < mAdaptiveBatchMinAvailableSlots) {
			return false;
		}
		return switch (mAdaptiveBatchTriggerMode) {
		case DUPLICATE_ONLY -> mTriggerDuplicateSinceLastDispatch;
		case STALE_ONLY -> mTriggerStaleSinceLastDispatch;
		case SEARCH_FAILED_ONLY -> mTriggerSearchFailedSinceLastDispatch;
		case IDLE_SLOT_ONLY -> mTriggerIdleSlotSinceLastDispatch;
		case FIRST_FILL_ONLY -> mFirstDispatchInCurrentAbstraction;
		case FIRST_FILL_OR_STALE -> mFirstDispatchInCurrentAbstraction || mTriggerStaleSinceLastDispatch;
		case THREADS_GE_4_FIRST_FILL -> mThreadLimit >= 4 && mFirstDispatchInCurrentAbstraction;
		case ALWAYS_BATCH, NEVER_BATCH -> throw new AssertionError("Control mode should have returned above");
		};
	}

	private void incrementAdaptiveTriggerCounter() {
		switch (mAdaptiveBatchTriggerMode) {
		case DUPLICATE_ONLY -> mAdaptiveTriggeredByDuplicate += 1;
		case STALE_ONLY -> mAdaptiveTriggeredByStale += 1;
		case SEARCH_FAILED_ONLY -> mAdaptiveTriggeredBySearchFailed += 1;
		case IDLE_SLOT_ONLY -> mAdaptiveTriggeredByIdleSlot += 1;
		case FIRST_FILL_ONLY -> mAdaptiveTriggeredByFirstFill += 1;
		case FIRST_FILL_OR_STALE -> mAdaptiveTriggeredByFirstFillOrStale += 1;
		case THREADS_GE_4_FIRST_FILL -> mAdaptiveTriggeredByThreadsGe4FirstFill += 1;
		case ALWAYS_BATCH, NEVER_BATCH -> {
			// These modes are controls and are not attributed to a symptom trigger.
		}
		default -> throw new AssertionError("Unknown adaptive trigger mode: " + mAdaptiveBatchTriggerMode);
		}
	}

	private void resetAdaptiveTriggerFlagForMode() {
		switch (mAdaptiveBatchTriggerMode) {
		case DUPLICATE_ONLY -> mTriggerDuplicateSinceLastDispatch = false;
		case STALE_ONLY -> mTriggerStaleSinceLastDispatch = false;
		case SEARCH_FAILED_ONLY -> mTriggerSearchFailedSinceLastDispatch = false;
		case IDLE_SLOT_ONLY -> mTriggerIdleSlotSinceLastDispatch = false;
		case FIRST_FILL_ONLY -> mFirstDispatchInCurrentAbstraction = false;
		case FIRST_FILL_OR_STALE -> {
			mFirstDispatchInCurrentAbstraction = false;
			mTriggerStaleSinceLastDispatch = false;
		}
		case THREADS_GE_4_FIRST_FILL -> mFirstDispatchInCurrentAbstraction = false;
		case ALWAYS_BATCH, NEVER_BATCH -> {
			// Control modes do not consume a symptom trigger.
		}
		default -> throw new AssertionError("Unknown adaptive trigger mode: " + mAdaptiveBatchTriggerMode);
		}
	}

	private void updateAndPrintStatistics(final boolean printStatistics) {
		reportParallelTraceSearchStatistics();

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
			mLogger.info("SearchMode: " + mTraceSearchSelectionMode);
			mLogger.info("Refinements: " + mRefinementsDone);
			mLogger.info("Counterexamples: " + mCounterexamplesChecked);
			mLogger.info("SearchTimeout: " + mCountTimeoutsInSearch);
			mLogger.info("RunConstructionFailed: " + mCountFailedRunConstructions);
			mLogger.info("SearchFailed: " + mCountFailedToFindCex);
			mLogger.info("Duplicate freshness failures: " + mCountDuplicateFreshnessFailures);
			mLogger.info("DuplicateFreshnessFailures: " + mCountDuplicateFreshnessFailures);
			mLogger.info("BFS: " + mCountBfsFoundCex);
			mLogger.info("IsEmptyParallel: " + mCountIsEmptyParallel);
			mLogger.info("ActiveThreads: " + maxActiveThreads);
			mLogger.info("ActiveExecutorsForPathPrograms: " + mActiveExecutors);
			mLogger.info("IterationsWithMaxThreads: " + mIterationsWithMaxThreads);
			mLogger.info("IterationsWithONEThread: " + mIterationsWithOneThread);
			mLogger.info("SearchTime: " + mSearchTime + " s");
			mLogger.info("WorkerSetUpTime: " + mWorkerSetUpTime + " s");
			mLogger.info("ExceptionInWorker: " + mExceptionInWorker);
			mLogger.info("mRefinementTime: " + mRefinementTime);
			mLogger.info("Prefix cache checked paths: " + mPrefixCoverageCache.getCheckedRunCount());
			mLogger.info("Prefix cache stale paths: " + mPrefixCoverageCache.getStaleRunCount());
			mLogger.info("Stale paths: " + mPrefixCoverageCache.getStaleRunCount());
			mLogger.info("LcpsCheckedPrefixQueries: " + mPrefixCoverageCache.getCheckedPrefixQueries());
			mLogger.info("LcpsStalePrefixQueries: " + mPrefixCoverageCache.getStalePrefixQueries());
				mLogger.info("LcpsCheckedPrefixHits: " + mPrefixCoverageCache.getCheckedPrefixHits());
				mLogger.info("LcpsStalePrefixHits: " + mPrefixCoverageCache.getStalePrefixHits());
				mLogger.info("LcpsSearchInvocations: " + mLcpsSearchInvocations);
				mLogger.info("LcpsFullCacheSuffixInvocations: " + mLcpsFullCacheSuffixInvocations);
				mLogger.info("LcpsFullCacheSuffixFallbacks: " + mLcpsFullCacheSuffixFallbacks);
				mLogger.info("LcpsEffectivePriorityDecisions: " + mLcpsEffectivePriorityDecisions);
				mLogger.info("BatchLcpsInvocations: " + mBatchLcpsInvocations);
				mLogger.info("BatchLcpsAvailableSlotsTotal: " + mBatchLcpsAvailableSlotsTotal);
				mLogger.info("BatchLcpsCandidatesGenerated: " + mBatchLcpsCandidatesGenerated);
				mLogger.info("BatchLcpsCandidatesSelected: " + mBatchLcpsCandidatesSelected);
				mLogger.info("BatchLcpsCandidateGenerationFailures: " + mBatchLcpsCandidateGenerationFailures);
				mLogger.info("BatchLcpsAvgCandidatePoolSize: " + getBatchLcpsAvgCandidatePoolSize());
				mLogger.info("BatchLcpsAvgSelectedBatchSize: " + getBatchLcpsAvgSelectedBatchSize());
				mLogger.info("BatchLcpsEffectiveBatchDecisions: " + mBatchLcpsEffectiveBatchDecisions);
				mLogger.info("BatchLcpsCandidateGenerationTimeMs: " + mBatchLcpsCandidateGenerationTimeMs);
				mLogger.info("BatchLcpsSelectionTimeMs: " + mBatchLcpsSelectionTimeMs);
				mLogger.info("AdaptiveBatchTriggerMode: " + mAdaptiveBatchTriggerMode);
				mLogger.info("AdaptiveBatchInvocations: " + mAdaptiveBatchInvocations);
				mLogger.info("AdaptiveBatchFallbacks: " + mAdaptiveBatchFallbacks);
				mLogger.info("AdaptiveTriggeredByDuplicate: " + mAdaptiveTriggeredByDuplicate);
					mLogger.info("AdaptiveTriggeredByStale: " + mAdaptiveTriggeredByStale);
					mLogger.info("AdaptiveTriggeredBySearchFailed: " + mAdaptiveTriggeredBySearchFailed);
					mLogger.info("AdaptiveTriggeredByIdleSlot: " + mAdaptiveTriggeredByIdleSlot);
					mLogger.info("AdaptiveTriggeredByFirstFill: " + mAdaptiveTriggeredByFirstFill);
					mLogger.info("AdaptiveTriggeredByFirstFillOrStale: " + mAdaptiveTriggeredByFirstFillOrStale);
					mLogger.info("AdaptiveTriggeredByThreadsGe4FirstFill: "
							+ mAdaptiveTriggeredByThreadsGe4FirstFill);
					mLogger.info("FirstDispatchInCurrentAbstraction: " + mFirstDispatchInCurrentAbstraction);
					mLogger.info("AdaptiveMinAvailableSlots: " + mAdaptiveBatchMinAvailableSlots);
				final var checkedPathSummary = (CheckedPathPrefixLcaDivergenceTracker.Summary) mCegarLoopBenchmark
						.getValue(CegarLoopStatisticsDefinitions.AvgPairwisePrefixLcaDivergence.toString());
			mLogger.info("Checked paths: " + checkedPathSummary.getCheckedPathCount());
			mLogger.info("Total pairwise prefix-LCA divergence: "
					+ checkedPathSummary.getTotalPairwisePrefixLcaDivergence());
			mLogger.info("Avg pairwise prefix-LCA divergence: "
					+ checkedPathSummary.getAveragePairwisePrefixLcaDivergence());
		}
	}

	private void reportParallelTraceSearchStatistics() {
			mCegarLoopBenchmark.reportParallelTraceSearchStatistics(mTraceSearchSelectionMode.toString(),
					mCountDuplicateFreshnessFailures, mCountFailedToFindCex, mPrefixCoverageCache.getStaleRunCount(),
					mPrefixCoverageCache.getCheckedPrefixQueries(), mPrefixCoverageCache.getStalePrefixQueries(),
					mPrefixCoverageCache.getCheckedPrefixHits(), mPrefixCoverageCache.getStalePrefixHits(),
					mLcpsSearchInvocations, mLcpsFullCacheSuffixInvocations, mLcpsFullCacheSuffixFallbacks,
					mLcpsEffectivePriorityDecisions, mBatchLcpsInvocations, mBatchLcpsAvailableSlotsTotal,
					mBatchLcpsCandidatesGenerated, mBatchLcpsCandidatesSelected,
					mBatchLcpsCandidateGenerationFailures, getBatchLcpsAvgCandidatePoolSize(),
					getBatchLcpsAvgSelectedBatchSize(), mBatchLcpsEffectiveBatchDecisions,
					mBatchLcpsCandidateGenerationTimeMs, mBatchLcpsSelectionTimeMs,
						mAdaptiveBatchTriggerMode.toString(), mAdaptiveBatchInvocations, mAdaptiveBatchFallbacks,
						mAdaptiveTriggeredByDuplicate, mAdaptiveTriggeredByStale, mAdaptiveTriggeredBySearchFailed,
						mAdaptiveTriggeredByIdleSlot, mAdaptiveTriggeredByFirstFill,
						mAdaptiveTriggeredByFirstFillOrStale, mAdaptiveTriggeredByThreadsGe4FirstFill,
						mAdaptiveBatchMinAvailableSlots);
	}

	private double getBatchLcpsAvgCandidatePoolSize() {
		return mBatchLcpsInvocations == 0 ? 0.0 : (double) mBatchLcpsCandidatesGenerated / mBatchLcpsInvocations;
	}

	private double getBatchLcpsAvgSelectedBatchSize() {
		return mBatchLcpsInvocations == 0 ? 0.0 : (double) mBatchLcpsCandidatesSelected / mBatchLcpsInvocations;
	}

	private boolean isSafeThenTerminate() throws AutomataOperationCanceledException {
		// If IsEmpty says its empty, then we can terminate even if threads are still
		// running
		mLogger.info("Checking if program is safe");
		if (super.isAbstractionEmpty() || mAbstraction.size() == 0) {
			mResultBuilder.addResultForAllRemaining(Result.SAFE);
			mLogger.info("Result: " + Result.SAFE);
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
		mWorkerTaskQueue.add(mCounterexample);
		mProgramCache.addRun(mCounterexample.getWord());
		final long time = System.nanoTime() / 1000000000;
		mLogger.info("Main: Starting Thread");
		final IcfgLocation currentErrorLoc = getErrorLocFromCounterexample();
		final IUltimateServiceProvider iterationServices = createIterationTimer(currentErrorLoc);
		mServices = iterationServices;
		mRunningThreads += 1;
		mCounterexamplesChecked += 1;
		// add mCounterexample to list such that we dont get it twice in our search
		addCounterexampleToSet((NestedRun<L, ?>) mCounterexample);
		mWorkerSetUpTime += ((System.nanoTime() / 1000000000) - time);
	}

	private WorkerThreadResult<L, A> getWorkerResult(final boolean didntFindCexLastIteration)
			throws InterruptedException {
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

	private void shutDownAndDestroy(final Object marker) {
		mExec.shutdownNow();
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

		removeCounterexampleFromSet(threadResult.getCounterexample());

		final Set<IcfgLocation> hoareAnnotationLocs;
		// TODO support for HoareAnnotations
		hoareAnnotationLocs = Collections.emptySet();

		final PredicateFactoryRefinement stateFactoryForRefinement =
				new PredicateFactoryRefinement(getServices(), threadResult.getWorkerMgdScript(),
						threadResult.getPredicateFactory(), mComputeHoareAnnotation, hoareAnnotationLocs);
		mLogger.info("Difference in Main");
		final IOpWithDelayedDeadEndRemoval<L, IPredicate> diff =
				computeAutomataDifference(mAbstraction, threadResult, stateFactoryForRefinement);

			mAbstraction = diff.getResult();
			mFirstDispatchInCurrentAbstraction = true;

			if (mPref.minimizeAbstractionPerWorker()) {
			minimizeAbstractionIfEnabled(stateFactoryForRefinement,
					new PredicateFactoryResultChecking(mPredicateFactory));
		}
		mRunningThreads -= 1;
		mLogger.info("Main: Refinement done.");
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

	public void reportCheckedCounterexample(final IRun<L, ?> counterexample) {
		mCegarLoopBenchmark.reportCheckedPath(counterexample.getStateSequence());
		recordCheckedRunInPrefixCoverageCache(counterexample);
		reportParallelTraceSearchStatistics();
	}

	@SuppressWarnings("unchecked")
	private void recordCheckedRunInPrefixCoverageCache(final IRun<L, ?> counterexample) {
		if (counterexample instanceof NestedRun<?, ?>) {
			mPrefixCoverageCache.recordCheckedRun((NestedRun<L, ?>) counterexample);
		}
	}

	@SuppressWarnings("unchecked")
	private void recordStaleRunInPrefixCoverageCache(final IRun<L, ?> counterexample) {
		if (counterexample instanceof NestedRun<?, ?>) {
			mPrefixCoverageCache.recordStaleRun((NestedRun<L, ?>) counterexample);
			reportParallelTraceSearchStatistics();
		}
	}

	private void recordStaleCounterexampleIfNoLongerAccepted(final IRun<L, ?> counterexample)
			throws AutomataOperationCanceledException {
		if (!mTrackStalePrefixesInParallelTraceSearch || counterexample == null) {
			return;
		}
		final boolean stillAccepted = accepts(getServices(), mAbstraction, counterexample.getWord(), false);
		if (!stillAccepted) {
			recordStaleRunInPrefixCoverageCache(counterexample);
			mTriggerStaleSinceLastDispatch = true;
			mLogger.info("Recorded stale counterexample prefix coverage.");
		}
	}

	private IsEmpty<L, IPredicate> getSearch(final IsEmpty.SearchStrategy strategy,
			final Set<IPredicate> possibleEndPoints) throws AutomataOperationCanceledException {
		return getSearch(strategy, possibleEndPoints, mActiveCounterexamples, mTraceSearchSelectionMode);
	}

	private IsEmpty<L, IPredicate> getSearch(final IsEmpty.SearchStrategy strategy,
			final Set<IPredicate> possibleEndPoints, final HashMap<Integer, NestedRun<L, ?>> activeCounterexamples,
			final TraceSearchSelectionMode searchMode) throws AutomataOperationCanceledException {
		switch (strategy) {
		case PARALLEL:
			return new IsEmptyParallel<>(new AutomataLibraryServices(mServices), mAbstraction,
					mAbstraction.getInitialStates(), Collections.emptySet(), possibleEndPoints,
					possibleEndPoints == null, IsEmpty.SearchStrategy.BFS, activeCounterexamples,
					mPref.getSearchLoopBound(), searchMode, mPrefixCoverageCache);
		default:
			return new IsEmpty<>(new AutomataLibraryServices(getServices()), mAbstraction, strategy);
		}
	}

	private List<NestedRun<L, IPredicate>> searchBatchForErrorTraces(final int availableSlots)
			throws AutomataOperationCanceledException {
		final long time = System.nanoTime() / 1000000000;
		try {
			mBatchLcpsInvocations += 1;
			mBatchLcpsAvailableSlotsTotal += availableSlots;
			final long generationStart = System.nanoTime();
			final List<NestedRun<L, IPredicate>> candidates = generateCandidatePool(availableSlots,
					mBatchLcpsCandidateMultiplier, mBatchLcpsCandidateCap);
			mBatchLcpsCandidateGenerationTimeMs += (System.nanoTime() - generationStart) / 1_000_000;
			final long selectionStart = System.nanoTime();
			final List<NestedRun<L, IPredicate>> selected = selectBatchFromCandidates(candidates, availableSlots);
			mBatchLcpsSelectionTimeMs += (System.nanoTime() - selectionStart) / 1_000_000;
			mBatchLcpsCandidatesGenerated += candidates.size();
			mBatchLcpsCandidatesSelected += selected.size();
			if (isEffectiveBatchDecision(candidates, selected)) {
				mBatchLcpsEffectiveBatchDecisions += 1;
			}
			if (selected.isEmpty()) {
				mCountFailedToFindCex += 1;
				mLogger.info("BATCH_LCPS did not find a fresh counterexample batch.");
			}
			return selected;
		} finally {
			mSearchTime += ((System.nanoTime() / 1000000000) - time);
			reportParallelTraceSearchStatistics();
		}
	}

	private List<NestedRun<L, IPredicate>> generateCandidatePool(final int availableSlots,
			final int candidateMultiplier, final int candidateCap) throws AutomataOperationCanceledException {
		final int targetCandidates = Math.min(candidateCap, availableSlots * candidateMultiplier);
		final HashMap<Integer, NestedRun<L, ?>> virtualActiveCounterexamples = new HashMap<>(mActiveCounterexamples);
		final Set<Integer> candidateHashes = new HashSet<>();
		final List<NestedRun<L, IPredicate>> candidates = new ArrayList<>();
		final Set<IPredicate> possibleEndPoints = null;
		for (int i = 0; i < targetCandidates; i++) {
			final IsEmpty<L, IPredicate> search = getSearch(IsEmpty.SearchStrategy.PARALLEL, possibleEndPoints,
					virtualActiveCounterexamples, TraceSearchSelectionMode.PAPER);
			final SearchValidationResult validationResult =
					validateSearch(search, virtualActiveCounterexamples, candidateHashes);
			if (validationResult != SearchValidationResult.FRESH) {
				mBatchLcpsCandidateGenerationFailures += 1;
				break;
			}
			final NestedRun<L, IPredicate> run = search.getNestedRun();
			final int traceHash = run.getWord().asList().hashCode();
			candidates.add(run);
			candidateHashes.add(traceHash);
			virtualActiveCounterexamples.put(traceHash, run);
		}
		return candidates;
	}

	private List<NestedRun<L, IPredicate>> selectBatchFromCandidates(final List<NestedRun<L, IPredicate>> candidates,
			final int availableSlots) {
		final List<NestedRun<L, IPredicate>> remaining = new ArrayList<>(candidates);
		final List<NestedRun<L, IPredicate>> selected = new ArrayList<>();
		while (selected.size() < availableSlots && !remaining.isEmpty()) {
			final NestedRun<L, IPredicate> best = remaining.stream()
					.min(Comparator.comparing(candidate -> makeBatchPriority(candidate, selected))).orElseThrow();
			selected.add(best);
			remaining.remove(best);
		}
		return selected;
	}

	private BatchPriority makeBatchPriority(final NestedRun<L, IPredicate> candidate,
			final List<NestedRun<L, IPredicate>> selected) {
		return new BatchPriority(maxSimilarityToActiveOrSelected(candidate, selected),
				mPrefixCoverageCache.getCheckedCoverageForRun(candidate),
				mPrefixCoverageCache.getStaleCoverageForRun(candidate), candidate.getLength(),
				candidate.getWord().asList().hashCode());
	}

	private double maxSimilarityToActiveOrSelected(final NestedRun<L, IPredicate> candidate,
			final List<NestedRun<L, IPredicate>> selected) {
		double maxSimilarity = 0.0;
		boolean hasReferenceRun = false;
		for (final NestedRun<L, ?> activeRun : mActiveCounterexamples.values()) {
			hasReferenceRun = true;
			maxSimilarity = Math.max(maxSimilarity, prefixLcaSimilarity(candidate, activeRun));
		}
		for (final NestedRun<L, IPredicate> selectedRun : selected) {
			hasReferenceRun = true;
			maxSimilarity = Math.max(maxSimilarity, prefixLcaSimilarity(candidate, selectedRun));
		}
		return hasReferenceRun ? maxSimilarity : 0.0;
	}

	private double prefixLcaSimilarity(final NestedRun<L, ?> first, final NestedRun<L, ?> second) {
		final int firstDepth = Math.max(0, first.getLength() - 1);
		final int secondDepth = Math.max(0, second.getLength() - 1);
		final int minDepth = Math.min(firstDepth, secondDepth);
		if (minDepth == 0) {
			// Empty/root-only paths have no meaningful divergence signal, so treat them as maximally similar.
			return 1.0;
		}
		int lcaDepth = 0;
		for (int i = 0; i < minDepth; i++) {
			final PathStepKey<L, IPredicate> firstKey =
					new PathStepKey<>(first.getSymbol(i), (IPredicate) first.getStateAtPosition(i + 1));
			final PathStepKey<L, IPredicate> secondKey =
					new PathStepKey<>(second.getSymbol(i), (IPredicate) second.getStateAtPosition(i + 1));
			if (!Objects.equals(firstKey, secondKey)) {
				break;
			}
			lcaDepth++;
		}
		return (double) lcaDepth / minDepth;
	}

	private boolean isEffectiveBatchDecision(final List<NestedRun<L, IPredicate>> candidates,
			final List<NestedRun<L, IPredicate>> selected) {
		final int selectedCount = selected.size();
		if (selectedCount == 0 || candidates.size() <= selectedCount) {
			return false;
		}
		final Set<Integer> naiveFirstKHashes = new HashSet<>();
		for (int i = 0; i < selectedCount; i++) {
			naiveFirstKHashes.add(candidates.get(i).getWord().asList().hashCode());
		}
		final Set<Integer> selectedHashes = selected.stream().map(run -> run.getWord().asList().hashCode())
				.collect(Collectors.toSet());
		return !Objects.equals(naiveFirstKHashes, selectedHashes);
	}

	private enum SearchValidationResult {
		FRESH, DUPLICATE, NO_RUN, INCORRECT
	}

	private SearchValidationResult validateSearch(final IsEmpty<L, IPredicate> search) {
		return validateSearch(search, mActiveCounterexamples, Collections.emptySet());
	}

	private SearchValidationResult validateSearch(final IsEmpty<L, IPredicate> search,
			final Map<Integer, NestedRun<L, ?>> activeCounterexamples, final Set<Integer> candidateHashes) {
		boolean correct = false;
		try {
			correct = search.checkResult(mStateFactoryForRefinement);
		} catch (final AutomataLibraryException e) {
			e.printStackTrace();
			assert false;
		}

		final NestedRun<L, IPredicate> run = search.getNestedRun();
		if (run == null) {
			return SearchValidationResult.NO_RUN;
		}
		final List<L> trace = run.getWord().asList();
		final int traceHash = trace.hashCode();
		if (activeCounterexamples.containsKey(traceHash) || candidateHashes.contains(traceHash)) {
			return SearchValidationResult.DUPLICATE;
		}
		return correct ? SearchValidationResult.FRESH : SearchValidationResult.INCORRECT;
	}

	private static final class BatchPriority implements Comparable<BatchPriority> {
		private final double mMaxSimilarityToActiveOrSelected;
		private final int mCheckedPrefixCoverage;
		private final int mStalePrefixCoverage;
		private final int mPathLength;
		private final int mWordHash;

		BatchPriority(final double maxSimilarityToActiveOrSelected, final int checkedPrefixCoverage,
				final int stalePrefixCoverage, final int pathLength, final int wordHash) {
			mMaxSimilarityToActiveOrSelected = maxSimilarityToActiveOrSelected;
			mCheckedPrefixCoverage = checkedPrefixCoverage;
			mStalePrefixCoverage = stalePrefixCoverage;
			mPathLength = pathLength;
			mWordHash = wordHash;
		}

		@Override
		public int compareTo(final BatchPriority other) {
			return Comparator.comparingDouble((BatchPriority priority) -> priority.mMaxSimilarityToActiveOrSelected)
					.thenComparingInt(priority -> priority.mCheckedPrefixCoverage)
					.thenComparingInt(priority -> priority.mStalePrefixCoverage)
					.thenComparingInt(priority -> priority.mPathLength)
					.thenComparingInt(priority -> priority.mWordHash).compare(this, other);
		}
	}

	/*
	 * Search for an error trace in the current mAbstraction. PAPER/LCPS may try BFS once per abstraction first, matching
	 * the previous parallel behavior.
	 */
	private NestedRun<L, IPredicate> searchForErrorTrace(final boolean skipInitialBfs)
			throws AutomataOperationCanceledException {
		return searchForErrorTrace(skipInitialBfs, mTraceSearchSelectionMode);
	}

	private NestedRun<L, IPredicate> searchForErrorTrace(final boolean skipInitialBfs,
			final TraceSearchSelectionMode searchMode) throws AutomataOperationCanceledException {
		final long time = System.nanoTime() / 1000000000;
		final Set<IPredicate> possibleEndPoints = null;
		try {
			final NestedRun<L, IPredicate> run = switch (searchMode) {
			case BFS -> searchWithFreshnessCheck(IsEmpty.SearchStrategy.BFS, possibleEndPoints, "BFS");
			case DFS -> searchWithFreshnessCheck(IsEmpty.SearchStrategy.DFS, possibleEndPoints, "DFS");
			case PAPER, LCPS, LCPS_FULL, LCPS_STALE_FIRST, LCPS_FULL_STALE_FIRST, BATCH_LCPS,
					ADAPTIVE_BATCH_LCPS ->
				searchForErrorTraceWithParallelSelector(skipInitialBfs, possibleEndPoints, searchMode);
			};
			if (run != null) {
				return run;
			}
			mLogger.info("Did not Find a Counterexample!");
			mCountFailedToFindCex += 1;
			assert mRunningThreads > 0;

			return null;
		} finally {
			mSearchTime += ((System.nanoTime() / 1000000000) - time);
			reportParallelTraceSearchStatistics();
		}
	}

	private NestedRun<L, IPredicate> searchForErrorTraceWithParallelSelector(final boolean skipInitialBfs,
			final Set<IPredicate> possibleEndPoints, final TraceSearchSelectionMode searchMode)
			throws AutomataOperationCanceledException {
		if (!skipInitialBfs && mUseInitialBfsInParallelTraceSearch) {
			final NestedRun<L, IPredicate> bfsRun =
					searchWithFreshnessCheck(IsEmpty.SearchStrategy.BFS, possibleEndPoints, "BFS");
			if (bfsRun != null) {
				return bfsRun;
			}
		}
		return searchWithFreshnessCheck(IsEmpty.SearchStrategy.PARALLEL, possibleEndPoints,
				"IsEmptyParallel " + searchMode, searchMode);
	}

	private NestedRun<L, IPredicate> searchWithFreshnessCheck(final IsEmpty.SearchStrategy strategy,
			final Set<IPredicate> possibleEndPoints, final String searchDescription)
			throws AutomataOperationCanceledException {
		return searchWithFreshnessCheck(strategy, possibleEndPoints, searchDescription, mTraceSearchSelectionMode);
	}

	private NestedRun<L, IPredicate> searchWithFreshnessCheck(final IsEmpty.SearchStrategy strategy,
			final Set<IPredicate> possibleEndPoints, final String searchDescription,
			final TraceSearchSelectionMode searchMode) throws AutomataOperationCanceledException {
		if (strategy == IsEmpty.SearchStrategy.PARALLEL && searchMode.usesPrefixCoverage()) {
			mLcpsSearchInvocations += 1;
		}
		final IsEmpty<L, IPredicate> search = getSearch(strategy, possibleEndPoints, mActiveCounterexamples, searchMode);
		if (search instanceof IsEmptyParallel<?, ?> parallelSearch) {
			mLcpsFullCacheSuffixInvocations += parallelSearch.getLcpsFullCacheSuffixInvocations();
			mLcpsFullCacheSuffixFallbacks += parallelSearch.getLcpsFullCacheSuffixFallbacks();
			mLcpsEffectivePriorityDecisions += parallelSearch.getLcpsEffectivePriorityDecisions();
		}
		final SearchValidationResult validationResult = validateSearch(search);
		switch (validationResult) {
		case FRESH:
			if (strategy == IsEmpty.SearchStrategy.BFS) {
				mCountBfsFoundCex += 1;
			} else if (strategy == IsEmpty.SearchStrategy.PARALLEL) {
				mCountIsEmptyParallel += 1;
			}
			mLogger.info("Found new Counterexample via " + searchDescription + "!");
			return search.getNestedRun();
		case DUPLICATE:
			mCountDuplicateFreshnessFailures += 1;
			mTriggerDuplicateSinceLastDispatch = true;
			mLogger.info(searchDescription + " found an active duplicate counterexample.");
			return null;
		case INCORRECT:
			mLogger.warn(searchDescription + " produced an incorrect search result.");
			return null;
		case NO_RUN:
			return null;
		default:
			throw new AssertionError("Unknown search validation result: " + validationResult);
		}
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
