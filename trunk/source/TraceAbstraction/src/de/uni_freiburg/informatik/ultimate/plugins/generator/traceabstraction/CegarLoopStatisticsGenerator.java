/*
 * Copyright (C) 2014-2015 Matthias Heizmann (heizmann@informatik.uni-freiburg.de)
 * Copyright (C) 2015 University of Freiburg
 *
 * This file is part of the ULTIMATE TraceAbstraction plug-in.
 *
 * The ULTIMATE TraceAbstraction plug-in is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ULTIMATE TraceAbstraction plug-in is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ULTIMATE TraceAbstraction plug-in. If not, see <http://www.gnu.org/licenses/>.
 *
 * Additional permission under GNU GPL version 3 section 7:
 * If you modify the ULTIMATE TraceAbstraction plug-in, or any covered work, by linking
 * or combining it with Eclipse RCP (or a modified version of Eclipse RCP),
 * containing parts covered by the terms of the Eclipse Public License, the
 * licensors of the ULTIMATE TraceAbstraction plug-in grant you additional permission
 * to convey the resulting work.
 */
package de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction;

import java.util.Collection;
import java.util.List;

import de.uni_freiburg.informatik.ultimate.lib.tracecheckerutils.CoverageAnalysis.BackwardCoveringInformation;
import de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction.CegarStatisticsType.SizeIterationPair;
import de.uni_freiburg.informatik.ultimate.util.statistics.IStatisticsDataProvider;
import de.uni_freiburg.informatik.ultimate.util.statistics.IStatisticsType;
import de.uni_freiburg.informatik.ultimate.util.statistics.StatisticsAggregator;
import de.uni_freiburg.informatik.ultimate.util.statistics.StatisticsData;
import de.uni_freiburg.informatik.ultimate.util.statistics.StatisticsGeneratorWithStopwatches;

public class CegarLoopStatisticsGenerator extends StatisticsGeneratorWithStopwatches
		implements IStatisticsDataProvider {

	private final StatisticsData mReuseStats = new StatisticsData();
	private final StatisticsAggregator mEcData = new StatisticsAggregator();
	private final StatisticsData mPredicateUnifierData = new StatisticsData();
	private final StatisticsData mTcData = new StatisticsData();
	private final StatisticsData mTiData = new StatisticsData();
	private final StatisticsData mAmData = new StatisticsData();
	private final StatisticsData mHoareAnnotationData = new StatisticsData();
	private final StatisticsData mInterpolantConsolidationBenchmarks = new StatisticsData();
	private final StatisticsData mPathInvariantsStatistics = new StatisticsData();
	private final StatisticsData mRefinementEngineStatistics = new StatisticsData();
	private final StatisticsData mConComCheckerStatistics = new StatisticsData();
	private final CheckedPathPrefixLcaDivergenceTracker mCheckedPathPrefixLcaDivergenceTracker =
			new CheckedPathPrefixLcaDivergenceTracker();

	private int mIterations = 0;
	private SizeIterationPair mBiggestAbstraction = new SizeIterationPair(-1, -1);
	private BackwardCoveringInformation mBCI = new BackwardCoveringInformation(0, 0);
	private int mTraceHistogramMaximum = 0;
	private int mInterpolantAutomatonStates = 0;
	private int mPathProgramHistogramMaximum = 0;
	private String mParallelSearchMode = "n/a";
	private int mParallelDuplicateFreshnessFailures = 0;
	private int mParallelFailedToFindCounterexamples = 0;
	private int mParallelStalePaths = 0;
	private int mLcpsCheckedPrefixQueries = 0;
	private int mLcpsStalePrefixQueries = 0;
	private int mLcpsCheckedPrefixHits = 0;
	private int mLcpsStalePrefixHits = 0;
	private int mLcpsSearchInvocations = 0;
	private int mLcpsEffectivePriorityDecisions = 0;
	private int mBatchLcpsInvocations = 0;
	private int mBatchLcpsAvailableSlotsTotal = 0;
	private int mBatchLcpsCandidatesGenerated = 0;
	private int mBatchLcpsCandidatesSelected = 0;
	private int mBatchLcpsCandidateGenerationFailures = 0;
	private double mBatchLcpsAvgCandidatePoolSize = 0.0;
	private double mBatchLcpsAvgSelectedBatchSize = 0.0;
	private int mBatchLcpsEffectiveBatchDecisions = 0;
	private long mBatchLcpsCandidateGenerationTimeMs = 0;
	private long mBatchLcpsSelectionTimeMs = 0;
	private int mAdaptiveBatchInvocations = 0;
	private int mAdaptiveBatchFallbacks = 0;
	private int mAdaptiveTriggeredByStale = 0;
	private int mAdaptiveTriggeredByFirstFill = 0;
	private int mAdaptiveMinAvailableSlots = 0;

	@Override
	public Collection<String> getKeys() {
		return getBenchmarkType().getKeys();
	}

	public void addReuseStats(final IStatisticsDataProvider reuseStats) {
		mReuseStats.aggregateBenchmarkData(reuseStats);
	}

	public void addEdgeCheckerData(final IStatisticsDataProvider ecbd) {
		mEcData.aggregateBenchmarkData(ecbd);
	}

	public void addPredicateUnifierData(final IStatisticsDataProvider pubd) {
		mPredicateUnifierData.aggregateBenchmarkData(pubd);
	}

	public void addTraceCheckData(final IStatisticsDataProvider tcbd) {
		mTcData.aggregateBenchmarkData(tcbd);
	}

	public void addRefinementEngineStatistics(final IStatisticsDataProvider res) {
		mRefinementEngineStatistics.aggregateBenchmarkData(res);
	}

	public void addTotalInterpolationData(final IStatisticsDataProvider tibd) {
		mTiData.aggregateBenchmarkData(tibd);
	}

	public void addBackwardCoveringInformation(final BackwardCoveringInformation bci) {
		mBCI = new BackwardCoveringInformation(mBCI, bci);
	}

	public void announceNextIteration() {
		mIterations++;
	}

	public void addAutomataMinimizationData(final IStatisticsDataProvider tcbd) {
		mAmData.aggregateBenchmarkData(tcbd);
	}

	public void addHoareAnnotationData(final IStatisticsDataProvider hasp) {
		mHoareAnnotationData.aggregateBenchmarkData(hasp);
	}

	public void addConComCheckerData(final IStatisticsDataProvider cccd) {
		mConComCheckerStatistics.aggregateBenchmarkData(cccd);
	}

	/**
	 * @return true iff size is the new maximum
	 */
	public boolean reportAbstractionSize(final int size, final int iteration) {
		if (size > mBiggestAbstraction.getSize()) {
			mBiggestAbstraction = new SizeIterationPair(size, iteration);
			return true;
		}
		return false;
	}

	public void reportTraceHistogramMaximum(final int maxCurrentTrace) {
		if (maxCurrentTrace > mTraceHistogramMaximum) {
			mTraceHistogramMaximum = maxCurrentTrace;
		}
	}

	public void reportPathProgramHistogramMaximum(final int pathProgramHistogramMax) {
		if (pathProgramHistogramMax > mPathProgramHistogramMaximum) {
			mPathProgramHistogramMaximum = pathProgramHistogramMax;
		}
	}

	public void reportCheckedPath(final List<?> rootToNodePath) {
		mCheckedPathPrefixLcaDivergenceTracker.recordCheckedPath(rootToNodePath);
	}

	public void reportInterpolantAutomatonStates(final int count) {
		mInterpolantAutomatonStates += count;
	}

	public void reportParallelTraceSearchStatistics(final String searchMode, final int duplicateFreshnessFailures,
			final int failedToFindCounterexamples, final int stalePaths, final int lcpsCheckedPrefixQueries,
			final int lcpsStalePrefixQueries, final int lcpsCheckedPrefixHits, final int lcpsStalePrefixHits,
			final int lcpsSearchInvocations, final int lcpsEffectivePriorityDecisions,
			final int batchLcpsInvocations, final int batchLcpsAvailableSlotsTotal,
			final int batchLcpsCandidatesGenerated, final int batchLcpsCandidatesSelected,
			final int batchLcpsCandidateGenerationFailures, final double batchLcpsAvgCandidatePoolSize,
			final double batchLcpsAvgSelectedBatchSize, final int batchLcpsEffectiveBatchDecisions,
			final long batchLcpsCandidateGenerationTimeMs, final long batchLcpsSelectionTimeMs,
			final int adaptiveBatchInvocations, final int adaptiveBatchFallbacks,
			final int adaptiveTriggeredByFirstFill, final int adaptiveTriggeredByStale,
			final int adaptiveMinAvailableSlots) {
		mParallelSearchMode = searchMode;
		mParallelDuplicateFreshnessFailures = duplicateFreshnessFailures;
		mParallelFailedToFindCounterexamples = failedToFindCounterexamples;
		mParallelStalePaths = stalePaths;
		mLcpsCheckedPrefixQueries = lcpsCheckedPrefixQueries;
		mLcpsStalePrefixQueries = lcpsStalePrefixQueries;
		mLcpsCheckedPrefixHits = lcpsCheckedPrefixHits;
		mLcpsStalePrefixHits = lcpsStalePrefixHits;
		mLcpsSearchInvocations = lcpsSearchInvocations;
		mLcpsEffectivePriorityDecisions = lcpsEffectivePriorityDecisions;
		mBatchLcpsInvocations = batchLcpsInvocations;
		mBatchLcpsAvailableSlotsTotal = batchLcpsAvailableSlotsTotal;
		mBatchLcpsCandidatesGenerated = batchLcpsCandidatesGenerated;
		mBatchLcpsCandidatesSelected = batchLcpsCandidatesSelected;
		mBatchLcpsCandidateGenerationFailures = batchLcpsCandidateGenerationFailures;
		mBatchLcpsAvgCandidatePoolSize = batchLcpsAvgCandidatePoolSize;
		mBatchLcpsAvgSelectedBatchSize = batchLcpsAvgSelectedBatchSize;
		mBatchLcpsEffectiveBatchDecisions = batchLcpsEffectiveBatchDecisions;
		mBatchLcpsCandidateGenerationTimeMs = batchLcpsCandidateGenerationTimeMs;
		mBatchLcpsSelectionTimeMs = batchLcpsSelectionTimeMs;
		mAdaptiveBatchInvocations = adaptiveBatchInvocations;
		mAdaptiveBatchFallbacks = adaptiveBatchFallbacks;
		mAdaptiveTriggeredByFirstFill = adaptiveTriggeredByFirstFill;
		mAdaptiveTriggeredByStale = adaptiveTriggeredByStale;
		mAdaptiveMinAvailableSlots = adaptiveMinAvailableSlots;
	}

	@Override
	public Object getValue(final String key) {
		final CegarLoopStatisticsDefinitions keyEnum = CegarLoopStatisticsDefinitions.valueOf(key);
		return switch (keyEnum) {
		case OverallTime, EmptinessCheckTime, AutomataDifference, DeadEndRemovalTime, HoareAnnotationTime,
				BasicInterpolantAutomatonTime, InitialAbstractionConstructionTime, DumpTime -> {
			try {
				yield getElapsedTime(key);
			} catch (final StopwatchStillRunningException e) {
				throw new AssertionError("clock still running: " + key);
			}
		}
		case HoareTripleCheckerStatistics -> mEcData;
		case ReuseStatistics -> mReuseStats;
		case PredicateUnifierStatistics -> mPredicateUnifierData;
		case traceCheckStatistics -> mTcData;
		case InterpolantConsolidationStatistics -> mInterpolantConsolidationBenchmarks;
		case PathInvariantsStatistics -> mPathInvariantsStatistics;
		case TotalInterpolationStatistics -> mTiData;
		case OverallIterations -> mIterations;
		case TraceHistogramMax -> mTraceHistogramMaximum;
		case PathProgramHistogramMax -> mPathProgramHistogramMaximum;
		case CheckedPaths -> mCheckedPathPrefixLcaDivergenceTracker.getCheckedPathCount();
		case TotalPairwisePrefixLcaDivergence ->
			mCheckedPathPrefixLcaDivergenceTracker.getTotalPairwisePrefixLcaDivergence();
		case AvgPairwisePrefixLcaDivergence -> mCheckedPathPrefixLcaDivergenceTracker.getSummary();
		case SearchMode -> mParallelSearchMode;
		case DuplicateFreshnessFailures -> mParallelDuplicateFreshnessFailures;
		case FailedToFindCounterexamples -> mParallelFailedToFindCounterexamples;
		case StalePaths -> mParallelStalePaths;
		case LcpsCheckedPrefixQueries -> mLcpsCheckedPrefixQueries;
		case LcpsStalePrefixQueries -> mLcpsStalePrefixQueries;
		case LcpsCheckedPrefixHits -> mLcpsCheckedPrefixHits;
		case LcpsStalePrefixHits -> mLcpsStalePrefixHits;
		case LcpsSearchInvocations -> mLcpsSearchInvocations;
		case LcpsEffectivePriorityDecisions -> mLcpsEffectivePriorityDecisions;
		case BatchLcpsInvocations -> mBatchLcpsInvocations;
		case BatchLcpsAvailableSlotsTotal -> mBatchLcpsAvailableSlotsTotal;
		case BatchLcpsCandidatesGenerated -> mBatchLcpsCandidatesGenerated;
		case BatchLcpsCandidatesSelected -> mBatchLcpsCandidatesSelected;
		case BatchLcpsCandidateGenerationFailures -> mBatchLcpsCandidateGenerationFailures;
		case BatchLcpsAvgCandidatePoolSize -> mBatchLcpsAvgCandidatePoolSize;
		case BatchLcpsAvgSelectedBatchSize -> mBatchLcpsAvgSelectedBatchSize;
		case BatchLcpsEffectiveBatchDecisions -> mBatchLcpsEffectiveBatchDecisions;
		case BatchLcpsCandidateGenerationTimeMs -> mBatchLcpsCandidateGenerationTimeMs;
		case BatchLcpsSelectionTimeMs -> mBatchLcpsSelectionTimeMs;
		case AdaptiveBatchInvocations -> mAdaptiveBatchInvocations;
		case AdaptiveBatchFallbacks -> mAdaptiveBatchFallbacks;
		case AdaptiveTriggeredByStale -> mAdaptiveTriggeredByStale;
		case AdaptiveTriggeredByFirstFill -> mAdaptiveTriggeredByFirstFill;
		case AdaptiveMinAvailableSlots -> mAdaptiveMinAvailableSlots;
		case BiggestAbstraction -> mBiggestAbstraction;
		case InterpolantAutomatonStates -> mInterpolantAutomatonStates;
		case InterpolantCoveringCapability -> mBCI;
		case AutomataMinimizationStatistics -> mAmData;
		case HoareAnnotationStatistics -> mHoareAnnotationData;
		case RefinementEngineStatistics -> mRefinementEngineStatistics;
		case ConComCheckerStatistics -> mConComCheckerStatistics;
		};
	}

	@Override
	public IStatisticsType getBenchmarkType() {
		return CegarStatisticsType.getInstance();
	}

	@Override
	public String[] getStopwatches() {
		return new String[] { CegarLoopStatisticsDefinitions.OverallTime.toString(),
				CegarLoopStatisticsDefinitions.EmptinessCheckTime.toString(),
				CegarLoopStatisticsDefinitions.AutomataDifference.toString(),
				CegarLoopStatisticsDefinitions.DeadEndRemovalTime.toString(),
				CegarLoopStatisticsDefinitions.HoareAnnotationTime.toString(),
				CegarLoopStatisticsDefinitions.BasicInterpolantAutomatonTime.toString(),
				CegarLoopStatisticsDefinitions.DumpTime.toString(),
				CegarLoopStatisticsDefinitions.InitialAbstractionConstructionTime.toString() };
	}
}
