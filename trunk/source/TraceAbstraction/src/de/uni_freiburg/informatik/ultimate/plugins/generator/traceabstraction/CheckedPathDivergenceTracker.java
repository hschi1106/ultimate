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
 * Tracks how far apart the actually checked root-to-node paths are in the current exploration tree.
 *
 * The metric is intentionally observational: callers record a path only after it reaches the real trace-checking step.
 * Generated, stale, or skipped paths must not be recorded here. The average pairwise distance therefore measures how
 * dispersed the verifier's checked paths were, not how many paths the emptiness search happened to enumerate.
 */
public final class CheckedPathDivergenceTracker {
	private final List<List<?>> mCheckedPaths = new ArrayList<>();
	private long mTotalPairwiseTreeDistance;

	public synchronized void recordCheckedPath(final List<?> rootToNodePath) {
		final List<?> immutablePath = Collections.unmodifiableList(new ArrayList<>(rootToNodePath));
		for (final List<?> previousPath : mCheckedPaths) {
			mTotalPairwiseTreeDistance += computeTreeDistance(previousPath, immutablePath);
		}
		mCheckedPaths.add(immutablePath);
	}

	public synchronized int getCheckedPathCount() {
		return mCheckedPaths.size();
	}

	public synchronized long getTotalPairwiseTreeDistance() {
		return mTotalPairwiseTreeDistance;
	}

	public synchronized Summary getSummary() {
		return new Summary(getCheckedPathCount(), getTotalPairwiseTreeDistance());
	}

	static int computeTreeDistance(final List<?> firstPath, final List<?> secondPath) {
		final int firstDepth = depth(firstPath);
		final int secondDepth = depth(secondPath);
		final int commonPrefixLength = commonPrefixLength(firstPath, secondPath);
		if (commonPrefixLength == 0) {
			return firstDepth + secondDepth;
		}
		final int lcaDepth = commonPrefixLength - 1;
		return firstDepth + secondDepth - 2 * lcaDepth;
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
		private final long mTotalPairwiseTreeDistance;
		private final long mPairCount;

		public Summary(final int checkedPathCount, final long totalPairwiseTreeDistance) {
			this(checkedPathCount, totalPairwiseTreeDistance, pairCount(checkedPathCount));
		}

		private Summary(final int checkedPathCount, final long totalPairwiseTreeDistance, final long pairCount) {
			mCheckedPathCount = checkedPathCount;
			mTotalPairwiseTreeDistance = totalPairwiseTreeDistance;
			mPairCount = pairCount;
		}

		public int getCheckedPathCount() {
			return mCheckedPathCount;
		}

		public long getTotalPairwiseTreeDistance() {
			return mTotalPairwiseTreeDistance;
		}

		public double getAveragePairwiseTreeDistance() {
			if (mPairCount == 0) {
				return 0.0;
			}
			return (double) mTotalPairwiseTreeDistance / mPairCount;
		}

		private static Summary aggregate(final Summary lhs, final Summary rhs) {
			return new Summary(lhs.mCheckedPathCount + rhs.mCheckedPathCount,
					lhs.mTotalPairwiseTreeDistance + rhs.mTotalPairwiseTreeDistance,
					lhs.mPairCount + rhs.mPairCount);
		}

		private static long pairCount(final int checkedPathCount) {
			return (long) checkedPathCount * (checkedPathCount - 1) / 2;
		}

		@Override
		public String toString() {
			return Double.toString(getAveragePairwiseTreeDistance());
		}
	}
}
