/*
 * Copyright (C) 2026 University of Freiburg
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
package de.uni_freiburg.informatik.ultimate.automata.nestedword.operations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests the heuristic priority key used by PAPER and LCPS successor ordering.
 */
public class IsEmptyParallelPriorityKeyTest {
	@Test
	public void testActiveCountDominatesCheckedCount() {
		final IsEmptyParallel.PriorityKey lowerActive =
				IsEmptyParallel.makePriorityKey(TraceSearchSelectionMode.LCPS, 0, 99, 99);
		final IsEmptyParallel.PriorityKey higherActive =
				IsEmptyParallel.makePriorityKey(TraceSearchSelectionMode.LCPS, 1, 0, 0);

		assertTrue(lowerActive.compareTo(higherActive) < 0);
	}

	@Test
	public void testCheckedCountDominatesStaleCount() {
		final IsEmptyParallel.PriorityKey lowerChecked =
				IsEmptyParallel.makePriorityKey(TraceSearchSelectionMode.LCPS, 0, 0, 99);
		final IsEmptyParallel.PriorityKey higherChecked =
				IsEmptyParallel.makePriorityKey(TraceSearchSelectionMode.LCPS, 0, 1, 0);

		assertTrue(lowerChecked.compareTo(higherChecked) < 0);
	}

	@Test
	public void testPaperModeIgnoresCheckedAndStaleCacheCounts() {
		final IsEmptyParallel.PriorityKey noCache =
				IsEmptyParallel.makePriorityKey(TraceSearchSelectionMode.PAPER, 0, 0, 0);
		final IsEmptyParallel.PriorityKey withCache =
				IsEmptyParallel.makePriorityKey(TraceSearchSelectionMode.PAPER, 0, 99, 99);

		assertEquals(0, noCache.compareTo(withCache));
	}

	@Test
	public void testLcpsModeUsesCheckedAndStaleCacheCounts() {
		final IsEmptyParallel.PriorityKey baseline =
				IsEmptyParallel.makePriorityKey(TraceSearchSelectionMode.LCPS, 0, 0, 0);
		final IsEmptyParallel.PriorityKey checkedHit =
				IsEmptyParallel.makePriorityKey(TraceSearchSelectionMode.LCPS, 0, 1, 0);
		final IsEmptyParallel.PriorityKey staleHit =
				IsEmptyParallel.makePriorityKey(TraceSearchSelectionMode.LCPS, 0, 0, 1);

		assertTrue(baseline.compareTo(checkedHit) < 0);
		assertTrue(baseline.compareTo(staleHit) < 0);
	}
}
