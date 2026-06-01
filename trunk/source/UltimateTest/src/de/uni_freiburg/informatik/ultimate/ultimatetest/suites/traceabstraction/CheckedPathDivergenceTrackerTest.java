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

import java.util.List;

import org.junit.Test;

import de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction.CheckedPathDivergenceTracker;

public class CheckedPathDivergenceTrackerTest {
	@Test
	public void averageIsZeroForFewerThanTwoCheckedPaths() {
		final CheckedPathDivergenceTracker tracker = new CheckedPathDivergenceTracker();

		assertEquals(0, tracker.getCheckedPathCount());
		assertEquals(0L, tracker.getTotalPairwiseTreeDistance());
		assertEquals(0.0, tracker.getSummary().getAveragePairwiseTreeDistance(), 0.0);

		tracker.recordCheckedPath(List.of("root", "left"));

		assertEquals(1, tracker.getCheckedPathCount());
		assertEquals(0L, tracker.getTotalPairwiseTreeDistance());
		assertEquals(0.0, tracker.getSummary().getAveragePairwiseTreeDistance(), 0.0);
	}

	@Test
	public void computesPairwiseDistanceFromLongestCommonPrefix() {
		final CheckedPathDivergenceTracker tracker = new CheckedPathDivergenceTracker();

		tracker.recordCheckedPath(List.of("root", "left", "left-left"));
		tracker.recordCheckedPath(List.of("root", "left", "left-right"));
		tracker.recordCheckedPath(List.of("root", "right"));

		assertEquals(3, tracker.getCheckedPathCount());
		assertEquals(8L, tracker.getTotalPairwiseTreeDistance());
		assertEquals(8.0 / 3.0, tracker.getSummary().getAveragePairwiseTreeDistance(), 0.0);
	}
}
