# Reproducing The SV-COMP ReachSafety Subset Experiment

This guide describes how to reproduce the checked-path divergence SV-COMP ReachSafety subset experiment from a fresh
checkout. The selected benchmark subset is tracked in the repository root as `benchmarks_run.json`.

## Local Layout

Use this layout:

```text
~/
  ultimate/
  sv-benchmarks/
```

`benchmarks_run.json` contains 189 ReachSafety tasks. Each task path is relative to the SV-COMP repository's `c/`
directory, for example `eca-rers2012/Problem01_label20.c`.

## Environment

Install the required host tools. Ultimate currently requires Java 21 or newer.

```bash
sudo apt update
sudo apt install -y git python3 openjdk-21-jdk maven
java -version
mvn -version
```

Fetch the SV-COMP benchmark repository and check out the SV-COMP 2025 benchmark state:

```bash
cd ~
git clone https://gitlab.com/sosy-lab/benchmarking/sv-benchmarks.git sv-benchmarks
git -C sv-benchmarks checkout svcomp25-final
```

Build or point to a runnable Ultimate product. A full product build can be created with:

```bash
cd ~/ultimate/releaseScripts/default
./makeFresh.sh
```

Then set the launcher path. If you already have a working Ultimate product, use that launcher instead.

```bash
cd ~/ultimate
export SVCOMP_ROOT="$HOME/sv-benchmarks"
export ULTIMATE_CMD="$PWD/releaseScripts/default/UAutomizer-linux/Ultimate"
test -x "$ULTIMATE_CMD"
```

## Quick Code Checks

These checks validate the affected scripts and Java modules without creating a full product:

```bash
cd ~/ultimate
python3 -m py_compile \
  trunk/examples/experiments/checked-path-divergence/run_checked_path_divergence_experiment.py \
  trunk/examples/experiments/checked-path-divergence/run_svcomp_reachsafety_subset.py \
  trunk/examples/experiments/checked-path-divergence/discover_divergence_benchmarks.py \
  trunk/examples/experiments/checked-path-divergence/plot_mode_volatility.py
(cd trunk/source/TraceAbstraction && mvn -q -DskipTests package)
(cd trunk/source/Library-Automata && mvn -q -DskipTests package)
(cd trunk/source/Library-AutomataTest && mvn -q package)
```

## Dry Run

The SV-COMP wrapper expands `benchmarks_run.json` into a generated benchmark CSV and then calls
`run_checked_path_divergence_experiment.py`. For C ReachSafety tasks it uses:

- toolchain: `trunk/examples/toolchains/AutomizerC.xml`
- settings: `trunk/examples/Interactive/settings/SVCOMP2017/svcomp-Reach-32bit-Automizer_Default.epf`

Start with a dry-run. It verifies that every selected task exists under `$SVCOMP_ROOT/c/`, writes the generated CSV,
and prints the runner command without launching Ultimate.

```bash
cd ~/ultimate
python3 trunk/examples/experiments/checked-path-divergence/run_svcomp_reachsafety_subset.py \
  --benchmarks-json benchmarks_run.json \
  --svcomp-root "$SVCOMP_ROOT" \
  --ultimate-cmd "$ULTIMATE_CMD" \
  --threads 4 \
  --jobs 1 \
  --timeout 150 \
  --output-dir trunk/examples/experiments/checked-path-divergence/results/reachsafety189-t150-w4 \
  --dry-run
```

## Full 4-Worker Run

Run the maintained mode set with 4 workers:

```bash
cd ~/ultimate
python3 trunk/examples/experiments/checked-path-divergence/run_svcomp_reachsafety_subset.py \
  --benchmarks-json benchmarks_run.json \
  --svcomp-root "$SVCOMP_ROOT" \
  --ultimate-cmd "$ULTIMATE_CMD" \
  --modes PAPER,LCPS,BATCH_LCPS,ADAPTIVE_BATCH_LCPS \
  --threads 4 \
  --jobs 1 \
  --repeat 1 \
  --timeout 150 \
  --output-dir trunk/examples/experiments/checked-path-divergence/results/reachsafety189-t150-w4
```

The wrapper writes:

- `trunk/examples/experiments/checked-path-divergence/results/reachsafety189-t150-w4/generated/reachsafety-subset-benchmarks.csv`
- `trunk/examples/experiments/checked-path-divergence/results/reachsafety189-t150-w4/checked-path-divergence-results.csv`
- `trunk/examples/experiments/checked-path-divergence/results/reachsafety189-t150-w4/checked-path-divergence-results.enriched.csv`
- `trunk/examples/experiments/checked-path-divergence/results/reachsafety189-t150-w4/svcomp-reachsafety-subset-summary.md`

Use the summary to compare `ADAPTIVE_BATCH_LCPS` against `PAPER`. Use the enriched CSV for per-category or
per-subcategory analysis.

## Direct Runner Invocation

After the wrapper has generated the benchmark CSV, the underlying runner can be called directly:

```bash
python3 trunk/examples/experiments/checked-path-divergence/run_checked_path_divergence_experiment.py \
  --ultimate-cmd "$ULTIMATE_CMD" \
  --benchmark-file trunk/examples/experiments/checked-path-divergence/results/reachsafety189-t150-w4/generated/reachsafety-subset-benchmarks.csv \
  --modes PAPER,LCPS,BATCH_LCPS,ADAPTIVE_BATCH_LCPS \
  --threads 4 \
  --repeat 1 \
  --jobs 1 \
  --timeout 150 \
  --output-dir trunk/examples/experiments/checked-path-divergence/results/reachsafety189-t150-w4
```
