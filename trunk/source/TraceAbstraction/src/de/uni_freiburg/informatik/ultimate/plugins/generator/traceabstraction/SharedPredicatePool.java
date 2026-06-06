/*
 * R4: cross-worker predicate sharing for Parallel CEGAR.
 */
package de.uni_freiburg.informatik.ultimate.plugins.generator.traceabstraction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.uni_freiburg.informatik.ultimate.core.model.services.ILogger;
import de.uni_freiburg.informatik.ultimate.lib.modelcheckerutils.smt.predicates.BasicPredicateFactory;
import de.uni_freiburg.informatik.ultimate.lib.modelcheckerutils.smt.predicates.IPredicate;
import de.uni_freiburg.informatik.ultimate.lib.modelcheckerutils.smt.scripttransfer.TermTransferrer;
import de.uni_freiburg.informatik.ultimate.lib.smtlibutils.ManagedScript;
import de.uni_freiburg.informatik.ultimate.lib.smtlibutils.SmtUtils;
import de.uni_freiburg.informatik.ultimate.logic.Term;

/**
 * A bounded, run-scoped pool of interpolant predicates shared across the workers of a parallel CEGAR run (R4).
 * <p>
 * Predicates produced by one worker (the non-trivial states of its infeasibility-proof automaton) are harvested into
 * this pool, stored as {@link Term}s in the coordinator's <em>main</em> SMT script, and later transferred into a
 * consumer worker's own script to seed that worker's {@code PredicateUnifier}. This lets workers reuse infeasibility
 * reasons discovered by other workers instead of re-deriving them.
 * <p>
 * All SMT-script access happens on the coordinator thread (harvest while processing a returned worker result; seed
 * while constructing the next worker, before its thread runs) and is additionally serialised on this object's monitor,
 * so the shared main script is touched single-threadedly. <b>Soundness:</b> the pool only supplies <em>candidate</em>
 * initial predicates; every verdict still rests on the worker's own trace check / interpolation, so a stale, missing,
 * or imprecise pool entry can never cause an incorrect result.
 */
public final class SharedPredicatePool {
	private final ILogger mLogger;
	private final int mPoolCap;
	/** key = term string (dedup) -> value = the term in the main script. Insertion-ordered. */
	private final Map<String, Term> mMainTerms = new LinkedHashMap<>();
	private long mHarvested;
	private long mSeeded;

	public SharedPredicatePool(final ILogger logger, final int poolCap) {
		mLogger = logger;
		mPoolCap = poolCap;
	}

	/**
	 * Harvest the non-trivial predicates labelling {@code states} (which live in {@code workerScript}) into the pool,
	 * transferring their formulas into {@code mainScript}. Must be called on the coordinator thread with the producing
	 * worker idle (its result already returned).
	 */
	public synchronized void harvest(final Iterable<IPredicate> states, final ManagedScript workerScript,
			final ManagedScript mainScript) {
		if (mPoolCap > 0 && mMainTerms.size() >= mPoolCap) {
			return;
		}
		final TermTransferrer worker2main = new TermTransferrer(workerScript.getScript(), mainScript.getScript());
		for (final IPredicate state : states) {
			if (mPoolCap > 0 && mMainTerms.size() >= mPoolCap) {
				break;
			}
			if (state == null) {
				continue;
			}
			final Term workerFormula = state.getFormula();
			if (workerFormula == null || SmtUtils.isTrueLiteral(workerFormula)
					|| SmtUtils.isFalseLiteral(workerFormula)) {
				continue;
			}
			try {
				final Term mainTerm = worker2main.transform(workerFormula);
				final String key = mainTerm.toString();
				if (mMainTerms.putIfAbsent(key, mainTerm) == null) {
					mHarvested++;
				}
			} catch (final RuntimeException e) {
				mLogger.warn("CrossWorkerPredicateSharing: failed to harvest a predicate: " + e);
			}
		}
	}

	/**
	 * Transfer up to {@code cap} pooled predicates into {@code workerScript} and wrap them with {@code workerFactory},
	 * yielding initial predicates for a new worker's unifier. Must be called on the coordinator thread with a freshly
	 * created, not-yet-running {@code workerScript}.
	 */
	public synchronized List<IPredicate> seedFor(final ManagedScript mainScript, final ManagedScript workerScript,
			final BasicPredicateFactory workerFactory, final int cap) {
		final List<IPredicate> result = new ArrayList<>();
		if (mMainTerms.isEmpty()) {
			return result;
		}
		final TermTransferrer main2worker = new TermTransferrer(mainScript.getScript(), workerScript.getScript());
		for (final Term mainTerm : mMainTerms.values()) {
			if (cap > 0 && result.size() >= cap) {
				break;
			}
			try {
				final Term workerTerm = main2worker.transform(mainTerm);
				result.add(workerFactory.newPredicate(workerTerm));
			} catch (final RuntimeException e) {
				mLogger.warn("CrossWorkerPredicateSharing: failed to seed a predicate: " + e);
			}
		}
		mSeeded += result.size();
		return result;
	}

	public synchronized int size() {
		return mMainTerms.size();
	}

	public synchronized String stats() {
		return "poolSize=" + mMainTerms.size() + " harvested=" + mHarvested + " seeded=" + mSeeded;
	}
}
