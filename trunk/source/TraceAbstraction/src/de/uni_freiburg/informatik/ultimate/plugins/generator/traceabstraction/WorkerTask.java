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

import de.uni_freiburg.informatik.ultimate.automata.IRun;
import de.uni_freiburg.informatik.ultimate.lib.modelcheckerutils.cfg.structure.IIcfgTransition;
import de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction.preferences.TraceAbstractionPreferenceInitializer.RefinementStrategy;

final class WorkerTask<L extends IIcfgTransition<?>> {
	private final IRun<L, ?> mCounterexample;
	private final StaleCancellationToken mCancellationToken;
	// Portfolio race: an alternative refinement strategy for this (racer) task, or null to use the worker default.
	private final RefinementStrategy mStrategyOverride;

	WorkerTask(final IRun<L, ?> counterexample, final StaleCancellationToken cancellationToken) {
		this(counterexample, cancellationToken, null);
	}

	WorkerTask(final IRun<L, ?> counterexample, final StaleCancellationToken cancellationToken,
			final RefinementStrategy strategyOverride) {
		mCounterexample = counterexample;
		mCancellationToken = cancellationToken;
		mStrategyOverride = strategyOverride;
	}

	IRun<L, ?> getCounterexample() {
		return mCounterexample;
	}

	StaleCancellationToken getCancellationToken() {
		return mCancellationToken;
	}

	RefinementStrategy getStrategyOverride() {
		return mStrategyOverride;
	}
}
