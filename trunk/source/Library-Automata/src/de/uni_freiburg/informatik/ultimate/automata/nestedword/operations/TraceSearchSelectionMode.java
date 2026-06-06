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

/**
 * Selection modes for counterexample search in the parallel trace-abstraction CEGAR loop.
 */
public enum TraceSearchSelectionMode {
	/**
	 * Plain breadth-first IsEmpty search. The parallel loop still rejects active duplicate traces.
	 */
	BFS,
	/**
	 * Plain depth-first IsEmpty search. The parallel loop still rejects active duplicate traces.
	 */
	DFS,
	/**
	 * Existing IsEmptyParallel priority: prefer successors covered by fewer active counterexamples.
	 */
	PAPER,
	/**
	 * Least-Covered Prefix Search: PAPER priority extended by checked/stale prefix coverage.
	 */
	LCPS,
	/**
	 * LCPS that keeps cache-guided successor ordering after all active traces diverged, until the cache no longer covers
	 * any successor.
	 */
	LCPS_FULL,
	/**
	 * LCPS ablation that prioritizes stale prefix coverage before checked prefix coverage.
	 */
	LCPS_STALE_FIRST,
	/**
	 * LCPS_FULL ablation that prioritizes stale prefix coverage before checked prefix coverage.
	 */
	LCPS_FULL_STALE_FIRST,
	/**
	 * Batch LCPS selects a group of fresh runs for currently idle workers. It does not change successor ordering inside
	 * IsEmptyParallel and uses prefix coverage only as an outer-loop batch selection signal.
	 */
	BATCH_LCPS,
	/**
	 * Adaptive wrapper around BATCH_LCPS. A single configured trigger decides whether a dispatch uses BATCH_LCPS or
	 * falls back to PAPER-style one-by-one search.
	 */
	ADAPTIVE_BATCH_LCPS;

	public boolean usesPrefixCoverage() {
		return this == LCPS || this == LCPS_FULL || this == LCPS_STALE_FIRST || this == LCPS_FULL_STALE_FIRST;
	}

	public boolean continuesAfterActiveDivergence() {
		return this == LCPS_FULL || this == LCPS_FULL_STALE_FIRST;
	}

	public boolean staleCoverageFirst() {
		return this == LCPS_STALE_FIRST || this == LCPS_FULL_STALE_FIRST;
	}

	public boolean usesBatchSelection() {
		return this == BATCH_LCPS || this == ADAPTIVE_BATCH_LCPS;
	}
}
