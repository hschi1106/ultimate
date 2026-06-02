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
	private long mWorkerSetUpTime = 0;
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
	private final int mExceptionInWorker = 0;

	private long mRefinementTime = 0;
	private long mStaleCancellationRequests = 0;
	private long mStaleCancellationAcceptsFailures = 0;
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

			/*
			 * In the first iteration we search via BFS, then we use IsEmptyParallel
			 */
			boolean firstIteration = true;
			while (mRunningThreads < mThreadLimit && !didntFindCexLastIteration) {
				assert mRunningThreads >= 0;
				mCounterexample = searchForErrorTrace(!firstIteration);
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
			updateAndPrintStatistics(false);
		}
		mExec.shutdownNow();
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
			mLogger.info("SearchTime: " + mSearchTime + " s");
			mLogger.info("WorkerSetUpTime: " + mWorkerSetUpTime + " s");
			mLogger.info("ExceptionInWorker: " + mExceptionInWorker);
			mLogger.info("mRefinementTime: " + mRefinementTime);
			mLogger.info("StaleCancellationRequests: " + mStaleCancellationRequests);
			mLogger.info("StaleCancellationAcceptsFailures: " + mStaleCancellationAcceptsFailures);
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
		final StaleCancellationToken cancellationToken =
				mPref.isStaleWorkerCancellationEnabled() ? new StaleCancellationToken(traceHash) : null;
		// add mCounterexample to list such that we dont get it twice in our search
		addCounterexampleToSet(counterexample);
		if (cancellationToken != null) {
			mActiveCancellationTokens.put(traceHash, cancellationToken);
		}
		mWorkerTaskQueue.add(new WorkerTask<>(mCounterexample, cancellationToken));
		mProgramCache.addRun(mCounterexample.getWord());
		final long time = System.nanoTime() / 1000000000;
		mLogger.info("Main: Starting Thread");
		final IcfgLocation currentErrorLoc = getErrorLocFromCounterexample();
		final IUltimateServiceProvider iterationServices = createIterationTimer(currentErrorLoc);
		mServices = iterationServices;
		mRunningThreads += 1;
		mCounterexamplesChecked += 1;
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
		cancelStaleActiveCounterexamples();

		if (mPref.minimizeAbstractionPerWorker()) {
			minimizeAbstractionIfEnabled(stateFactoryForRefinement,
					new PredicateFactoryResultChecking(mPredicateFactory));
		}
		mRunningThreads -= 1;
		mLogger.info("Main: Refinement done.");
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

	private void cancelStaleActiveCounterexamples() {
		if (!mPref.isStaleWorkerCancellationEnabled()) {
			return;
		}
		final AutomataLibraryServices automataServices = new AutomataLibraryServices(getServices());
		for (final Map.Entry<Integer, NestedRun<L, ?>> activeCounterexample : mActiveCounterexamples.entrySet()) {
			final Integer activeTraceHash = activeCounterexample.getKey();
			final NestedRun<L, ?> activeRun = activeCounterexample.getValue();
			if (activeRun == null) {
				continue;
			}
			try {
				if (!new Accepts<>(automataServices, mAbstraction, activeRun.getWord()).getResult()) {
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
