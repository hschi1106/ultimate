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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.uni_freiburg.informatik.ultimate.automata.nestedword.NestedRun;

/**
 * Prefix trie used by LCPS as a read-only ordering signal during search.
 *
 * The cache never decides acceptance and never suppresses candidates. It only records how often checked or stale runs
 * have visited each transition prefix.
 */
public final class PrefixCoverageCache<LETTER, STATE> {
	private final Node<LETTER, STATE> mRoot = new Node<>();
	private int mCheckedRunCount;
	private int mStaleRunCount;
	private int mCheckedPrefixQueries;
	private int mStalePrefixQueries;
	private int mCheckedPrefixHits;
	private int mStalePrefixHits;

	public synchronized void recordCheckedRun(final NestedRun<LETTER, ?> run) {
		recordRun(run, true);
		mCheckedRunCount++;
	}

	public synchronized void recordStaleRun(final NestedRun<LETTER, ?> run) {
		recordRun(run, false);
		mStaleRunCount++;
	}

	public synchronized int getCheckedPrefixCount(final List<PathStepKey<LETTER, STATE>> prefix) {
		mCheckedPrefixQueries++;
		final Node<LETTER, STATE> node = findNode(prefix);
		final int count = node == null ? 0 : node.mCheckedCount;
		if (count > 0) {
			mCheckedPrefixHits++;
		}
		return count;
	}

	public synchronized int getStalePrefixCount(final List<PathStepKey<LETTER, STATE>> prefix) {
		mStalePrefixQueries++;
		final Node<LETTER, STATE> node = findNode(prefix);
		final int count = node == null ? 0 : node.mStaleCount;
		if (count > 0) {
			mStalePrefixHits++;
		}
		return count;
	}

	public synchronized int getCheckedCoverageForRun(final NestedRun<LETTER, ?> run) {
		return getCoverageForRun(run, true);
	}

	public synchronized int getStaleCoverageForRun(final NestedRun<LETTER, ?> run) {
		return getCoverageForRun(run, false);
	}

	public synchronized int getCheckedRunCount() {
		return mCheckedRunCount;
	}

	public synchronized int getStaleRunCount() {
		return mStaleRunCount;
	}

	public synchronized int getCheckedPrefixQueries() {
		return mCheckedPrefixQueries;
	}

	public synchronized int getStalePrefixQueries() {
		return mStalePrefixQueries;
	}

	public synchronized int getCheckedPrefixHits() {
		return mCheckedPrefixHits;
	}

	public synchronized int getStalePrefixHits() {
		return mStalePrefixHits;
	}

	private void recordRun(final NestedRun<LETTER, ?> run, final boolean checked) {
		Node<LETTER, STATE> current = mRoot;
		for (int i = 0; i < run.getLength() - 1; i++) {
			final PathStepKey<LETTER, STATE> key = makeKey(run, i);
			current = current.mChildren.computeIfAbsent(key, unused -> new Node<>());
			if (checked) {
				current.mCheckedCount++;
			} else {
				current.mStaleCount++;
			}
		}
	}

	private int getCoverageForRun(final NestedRun<LETTER, ?> run, final boolean checked) {
		int coverage = 0;
		Node<LETTER, STATE> current = mRoot;
		for (int i = 0; i < run.getLength() - 1; i++) {
			final PathStepKey<LETTER, STATE> key = makeKey(run, i);
			current = current.mChildren.get(key);
			if (current == null) {
				return coverage;
			}
			coverage += checked ? current.mCheckedCount : current.mStaleCount;
		}
		return coverage;
	}

	@SuppressWarnings("unchecked")
	private PathStepKey<LETTER, STATE> makeKey(final NestedRun<LETTER, ?> run, final int transitionPosition) {
		return new PathStepKey<>(run.getSymbol(transitionPosition),
				(STATE) run.getStateAtPosition(transitionPosition + 1));
	}

	private Node<LETTER, STATE> findNode(final List<PathStepKey<LETTER, STATE>> prefix) {
		Node<LETTER, STATE> current = mRoot;
		for (final PathStepKey<LETTER, STATE> key : prefix) {
			current = current.mChildren.get(key);
			if (current == null) {
				return null;
			}
		}
		return current;
	}

	private static final class Node<LETTER, STATE> {
		private final Map<PathStepKey<LETTER, STATE>, Node<LETTER, STATE>> mChildren = new HashMap<>();
		private int mCheckedCount;
		private int mStaleCount;
	}
}
