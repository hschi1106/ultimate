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
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import de.uni_freiburg.informatik.ultimate.automata.nestedword.NestedRun;
import de.uni_freiburg.informatik.ultimate.automata.nestedword.NestedWord;

/**
 * Tests the prefix-coverage cache used as an LCPS heuristic/statistics signal.
 */
public class PrefixCoverageCacheTest {
	private static NestedRun<String, String> run(final String[] letters, final String... states) {
		final int[] nestingRelation = new int[letters.length];
		Arrays.fill(nestingRelation, NestedWord.INTERNAL_POSITION);
		return new NestedRun<>(new NestedWord<>(letters, nestingRelation), Arrays.asList(states));
	}

	private static List<PathStepKey<String, String>> prefix(final String letter, final String state) {
		return List.of(new PathStepKey<>(letter, state));
	}

	private static List<PathStepKey<String, String>> prefix(final String letter1, final String state1,
			final String letter2, final String state2) {
		return List.of(new PathStepKey<>(letter1, state1), new PathStepKey<>(letter2, state2));
	}

	@Test
	public void testRecordOneCheckedRun() {
		final PrefixCoverageCache<String, String> cache = new PrefixCoverageCache<>();
		final String a = "a";
		final String b = "b";

		cache.recordCheckedRun(run(new String[] { a, b }, "s0", "s1", "s2"));

		assertEquals(1, cache.getCheckedPrefixCount(prefix(a, "s1")));
		assertEquals(1, cache.getCheckedPrefixCount(prefix(a, "s1", b, "s2")));
		assertEquals(0, cache.getCheckedPrefixCount(prefix(new String("a"), "s1")));
		assertEquals(1, cache.getCheckedRunCount());
	}

	@Test
	public void testSharedAndDivergedPrefixes() {
		final PrefixCoverageCache<String, String> cache = new PrefixCoverageCache<>();
		final String a = "a";
		final String b = "b";
		final String c = "c";

		cache.recordCheckedRun(run(new String[] { a, b }, "s0", "s1", "s2"));
		cache.recordCheckedRun(run(new String[] { a, c }, "s0", "s1", "s3"));

		assertEquals(2, cache.getCheckedPrefixCount(prefix(a, "s1")));
		assertEquals(1, cache.getCheckedPrefixCount(prefix(a, "s1", b, "s2")));
		assertEquals(1, cache.getCheckedPrefixCount(prefix(a, "s1", c, "s3")));
	}

	@Test
	public void testStaleCountsAreIndependentFromCheckedCounts() {
		final PrefixCoverageCache<String, String> cache = new PrefixCoverageCache<>();
		final String a = "a";

		cache.recordStaleRun(run(new String[] { a }, "s0", "s1"));

		assertEquals(0, cache.getCheckedPrefixCount(prefix(a, "s1")));
		assertEquals(1, cache.getStalePrefixCount(prefix(a, "s1")));
		assertEquals(0, cache.getCheckedRunCount());
		assertEquals(1, cache.getStaleRunCount());
	}

	@Test
	public void testPrefixHitCounters() {
		final PrefixCoverageCache<String, String> cache = new PrefixCoverageCache<>();
		final String a = "a";

		cache.recordCheckedRun(run(new String[] { a }, "s0", "s1"));
		cache.recordStaleRun(run(new String[] { a }, "s0", "s1"));

		assertEquals(0, cache.getCheckedPrefixHits());
		assertEquals(0, cache.getStalePrefixHits());
		assertEquals(1, cache.getCheckedPrefixCount(prefix(a, "s1")));
		assertEquals(1, cache.getCheckedPrefixHits());
		assertEquals(1, cache.getStalePrefixCount(prefix(a, "s1")));
		assertEquals(1, cache.getStalePrefixHits());
		assertEquals(0, cache.getCheckedPrefixCount(prefix(a, "other")));
		assertEquals(1, cache.getCheckedPrefixHits());
	}

	@Test
	public void testPathStepKeyUsesLetterIdentityAndStateEqualsFallback() {
		final String letter = new String("a");
		final PathStepKey<String, String> left = new PathStepKey<>(letter, "state");
		final PathStepKey<String, String> sameFallbackState = new PathStepKey<>(letter, new String("state"));
		final PathStepKey<String, String> differentLetterIdentity = new PathStepKey<>(new String("a"), "state");

		assertEquals(left, sameFallbackState);
		assertNotEquals(left, differentLetterIdentity);
	}
}
