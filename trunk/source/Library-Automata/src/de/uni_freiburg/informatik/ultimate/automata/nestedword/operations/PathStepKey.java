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

import java.util.Objects;

import de.uni_freiburg.informatik.ultimate.lib.modelcheckerutils.smt.predicates.ISLPredicate;

/**
 * Key for one transition in a run prefix.
 *
 * Letter comparison intentionally uses object identity, matching the existing IsEmptyParallel active-counterexample
 * scoring. Predicate states are compared by program point to ignore fresh serial numbers.
 */
public final class PathStepKey<LETTER, STATE> {
	private final LETTER mLetter;
	private final Object mStateKey;
	private final int mHashCode;

	public PathStepKey(final LETTER letter, final STATE succState) {
		mLetter = letter;
		mStateKey = extractStateKey(succState);
		mHashCode = 31 * System.identityHashCode(mLetter) + Objects.hashCode(mStateKey);
	}

	private static Object extractStateKey(final Object state) {
		if (state instanceof ISLPredicate) {
			return ((ISLPredicate) state).getProgramPoint();
		}
		return state;
	}

	@Override
	public int hashCode() {
		return mHashCode;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PathStepKey<?, ?>)) {
			return false;
		}
		final PathStepKey<?, ?> other = (PathStepKey<?, ?>) obj;
		return mLetter == other.mLetter && Objects.equals(mStateKey, other.mStateKey);
	}

	@Override
	public String toString() {
		return "PathStepKey[" + mStateKey + ", " + mLetter + "]";
	}
}
