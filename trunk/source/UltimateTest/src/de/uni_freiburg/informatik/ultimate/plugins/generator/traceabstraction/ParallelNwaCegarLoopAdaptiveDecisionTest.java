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
package de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction.ParallelNwaCegarLoop.AdaptiveBatchDecision;

public class ParallelNwaCegarLoopAdaptiveDecisionTest {
	@Test
	public void testFirstFillUsesBatchWhenEnoughSlotsAreAvailable() {
		final AdaptiveBatchDecision decision = ParallelNwaCegarLoop.decideAdaptiveBatch(true, false, 2, 2);

		assertTrue(decision.shouldUseBatch());
		assertTrue(decision.triggeredByFirstFill());
		assertFalse(decision.triggeredByStale());
	}

	@Test
	public void testStaleTriggerUsesBatchAfterFirstFill() {
		final AdaptiveBatchDecision decision = ParallelNwaCegarLoop.decideAdaptiveBatch(false, true, 3, 2);

		assertTrue(decision.shouldUseBatch());
		assertFalse(decision.triggeredByFirstFill());
		assertTrue(decision.triggeredByStale());
	}

	@Test
	public void testNoFirstFillOrStaleTriggerFallsBackToPaper() {
		final AdaptiveBatchDecision decision = ParallelNwaCegarLoop.decideAdaptiveBatch(false, false, 4, 2);

		assertFalse(decision.shouldUseBatch());
		assertFalse(decision.triggeredByFirstFill());
		assertFalse(decision.triggeredByStale());
	}
}
