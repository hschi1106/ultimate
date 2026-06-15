/*
 * Copyright (C) 2026 University of Freiburg
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Tracks the normalized prefix-LCA divergence of actually checked root-to-node paths.
 *
 * The metric is intentionally observational: callers record a path only after it reaches the real trace-checking step.
 * Generated, stale, or skipped paths must not be recorded here. Pairwise normalized prefix-LCA divergence measures the
 * unshared fraction of the shorter path, so pairwise divergence and its average are in [0, 1].
 */
public final class CheckedPathPrefixLcaDivergenceTracker {
	private final List<List<?>> mCheckedPaths = new ArrayList<>();
	private double mTotalPairwisePrefixLcaDivergence;

	public synchronized void recordCheckedPath(final List<?> rootToNodePath) {
		final List<?> immutablePath = Collections.unmodifiableList(new ArrayList<>(rootToNodePath));
		for (final List<?> previousPath : mCheckedPaths) {
			mTotalPairwisePrefixLcaDivergence += computePrefixLcaDivergence(previousPath, immutablePath);
		}
		mCheckedPaths.add(immutablePath);
	}

	public synchronized int getCheckedPathCount() {
		return mCheckedPaths.size();
	}

	public synchronized double getTotalPairwisePrefixLcaDivergence() {
		return mTotalPairwisePrefixLcaDivergence;
	}

	public synchronized Summary getSummary() {
		return new Summary(getCheckedPathCount(), getTotalPairwisePrefixLcaDivergence());
	}

	static double computePrefixLcaDivergence(final List<?> firstPath, final List<?> secondPath) {
		final int firstDepth = depth(firstPath);
		final int secondDepth = depth(secondPath);
		final int shorterDepth = Math.min(firstDepth, secondDepth);
		if (shorterDepth == 0) {
			return 0.0;
		}
		final int commonPrefixLength = commonPrefixLength(firstPath, secondPath);
		final int lcaDepth = Math.max(0, commonPrefixLength - 1);
		return 1.0 - (double) lcaDepth / shorterDepth;
	}

	private static int depth(final List<?> path) {
		return Math.max(0, path.size() - 1);
	}

	private static int commonPrefixLength(final List<?> firstPath, final List<?> secondPath) {
		final int commonLength = Math.min(firstPath.size(), secondPath.size());
		for (int i = 0; i < commonLength; i++) {
			if (!Objects.equals(firstPath.get(i), secondPath.get(i))) {
				return i;
			}
		}
		return commonLength;
	}

	public static final class Summary {
		public static final Function<Object, Function<Object, Object>> SUMMARY_AGGREGATION =
				x -> y -> aggregate((Summary) x, (Summary) y);

		private final int mCheckedPathCount;
		private final double mTotalPairwisePrefixLcaDivergence;
		private final long mPairCount;

		public Summary(final int checkedPathCount, final double totalPairwisePrefixLcaDivergence) {
			this(checkedPathCount, totalPairwisePrefixLcaDivergence, pairCount(checkedPathCount));
		}

		private Summary(final int checkedPathCount, final double totalPairwisePrefixLcaDivergence, final long pairCount) {
			mCheckedPathCount = checkedPathCount;
			mTotalPairwisePrefixLcaDivergence = totalPairwisePrefixLcaDivergence;
			mPairCount = pairCount;
		}

		public int getCheckedPathCount() {
			return mCheckedPathCount;
		}

		public double getTotalPairwisePrefixLcaDivergence() {
			return mTotalPairwisePrefixLcaDivergence;
		}

		public double getAveragePairwisePrefixLcaDivergence() {
			if (mPairCount == 0) {
				return 0.0;
			}
			return mTotalPairwisePrefixLcaDivergence / mPairCount;
		}

		private static Summary aggregate(final Summary lhs, final Summary rhs) {
			return new Summary(lhs.mCheckedPathCount + rhs.mCheckedPathCount,
					lhs.mTotalPairwisePrefixLcaDivergence + rhs.mTotalPairwisePrefixLcaDivergence,
					lhs.mPairCount + rhs.mPairCount);
		}

		private static long pairCount(final int checkedPathCount) {
			return (long) checkedPathCount * (checkedPathCount - 1) / 2;
		}

		@Override
		public String toString() {
			return Double.toString(getAveragePairwisePrefixLcaDivergence());
		}
	}
}
