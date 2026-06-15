/*
 * Copyright (C) 2026 University of Freiburg
 *
 * This file is part of the ULTIMATE Test Library.
 *
 * The ULTIMATE Test Library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ULTIMATE Test Library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the ULTIMATE Test Library. If not, see <http://www.gnu.org/licenses/>.
 *
 * Additional permission under GNU GPL version 3 section 7:
 * If you modify the ULTIMATE Test Library, or any covered work, by linking
 * or combining it with Eclipse RCP (or a modified version of Eclipse RCP),
 * containing parts covered by the terms of the Eclipse Public License, the
 * licensors of the ULTIMATE Test Library grant you additional permission
 * to convey the resulting work.
 */
package de.uni_freiburg.informatik.ultimate.ultimatetest.suites.traceabstraction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction.CheckedPathPrefixLcaDivergenceTracker;

public class CheckedPathPrefixLcaDivergenceTrackerTest {
	@Test
	public void averageIsZeroForFewerThanTwoCheckedPaths() {
		final CheckedPathPrefixLcaDivergenceTracker tracker = new CheckedPathPrefixLcaDivergenceTracker();

		assertEquals(0, tracker.getCheckedPathCount());
		assertEquals(0.0, tracker.getTotalPairwisePrefixLcaDivergence(), 0.0);
		assertEquals(0.0, tracker.getSummary().getAveragePairwisePrefixLcaDivergence(), 0.0);

		tracker.recordCheckedPath(List.of("root", "left"));

		assertEquals(1, tracker.getCheckedPathCount());
		assertEquals(0.0, tracker.getTotalPairwisePrefixLcaDivergence(), 0.0);
		assertEquals(0.0, tracker.getSummary().getAveragePairwisePrefixLcaDivergence(), 0.0);
	}

	@Test
	public void rootOnlyShorterPathHasZeroDivergence() {
		final CheckedPathPrefixLcaDivergenceTracker tracker = new CheckedPathPrefixLcaDivergenceTracker();

		tracker.recordCheckedPath(List.of("root"));
		tracker.recordCheckedPath(List.of("root", "left"));

		assertEquals(0.0, tracker.getTotalPairwisePrefixLcaDivergence(), 0.0);
		assertEquals(0.0, tracker.getSummary().getAveragePairwisePrefixLcaDivergence(), 0.0);
	}

	@Test
	public void shorterPathPrefixHasZeroDivergence() {
		final CheckedPathPrefixLcaDivergenceTracker tracker = new CheckedPathPrefixLcaDivergenceTracker();

		tracker.recordCheckedPath(List.of("root", "left"));
		tracker.recordCheckedPath(List.of("root", "left", "left-left"));

		assertEquals(0.0, tracker.getTotalPairwisePrefixLcaDivergence(), 0.0);
		assertEquals(0.0, tracker.getSummary().getAveragePairwisePrefixLcaDivergence(), 0.0);
	}

	@Test
	public void computesNormalizedPrefixLcaDivergence() {
		final CheckedPathPrefixLcaDivergenceTracker tracker = new CheckedPathPrefixLcaDivergenceTracker();

		tracker.recordCheckedPath(List.of("root", "left", "left-left", "left-left-left"));
		tracker.recordCheckedPath(List.of("root", "left", "left-right"));

		assertEquals(0.5, tracker.getTotalPairwisePrefixLcaDivergence(), 0.0);
		assertEquals(0.5, tracker.getSummary().getAveragePairwisePrefixLcaDivergence(), 0.0);
	}

	@Test
	public void disjointBranchesHaveMaximumNormalizedDivergence() {
		final CheckedPathPrefixLcaDivergenceTracker tracker = new CheckedPathPrefixLcaDivergenceTracker();

		tracker.recordCheckedPath(List.of("root", "left"));
		tracker.recordCheckedPath(List.of("root", "right"));

		assertEquals(1.0, tracker.getTotalPairwisePrefixLcaDivergence(), 0.0);
		assertEquals(1.0, tracker.getSummary().getAveragePairwisePrefixLcaDivergence(), 0.0);
	}

	@Test
	public void normalizedAverageStaysWithinUnitInterval() {
		final CheckedPathPrefixLcaDivergenceTracker tracker = new CheckedPathPrefixLcaDivergenceTracker();

		tracker.recordCheckedPath(List.of("root", "left", "left-left"));
		tracker.recordCheckedPath(List.of("root", "left", "left-right"));
		tracker.recordCheckedPath(List.of("root", "right"));

		assertEquals(3, tracker.getCheckedPathCount());
		assertEquals(2.5, tracker.getTotalPairwisePrefixLcaDivergence(), 0.0);
		assertEquals(5.0 / 6.0, tracker.getSummary().getAveragePairwisePrefixLcaDivergence(), 0.0);
		assertTrue(tracker.getSummary().getAveragePairwisePrefixLcaDivergence() >= 0.0);
		assertTrue(tracker.getSummary().getAveragePairwisePrefixLcaDivergence() <= 1.0);
	}
}
