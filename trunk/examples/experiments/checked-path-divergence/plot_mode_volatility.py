#!/usr/bin/env python3
"""Plot per-task CPU-time changes relative to PAPER."""

from __future__ import annotations

import argparse
import csv
import math
from collections import defaultdict
from pathlib import Path

import matplotlib.pyplot as plt


MODES = ["BFS", "DFS", "LCPS", "BATCH_LCPS", "ADAPTIVE_BATCH_LCPS"]


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument(
        "--main-results",
        type=Path,
        default=Path("trunk/examples/experiments/checked-path-divergence/results/reachsafety189-t150-w4/checked-path-divergence-results.csv"),
    )
    parser.add_argument(
        "--basic-results",
        type=Path,
        default=Path("trunk/examples/experiments/checked-path-divergence/results/reachsafety189-t150-w4-bfs-dfs/checked-path-divergence-results.enriched.csv"),
    )
    parser.add_argument(
        "--out-prefix",
        type=Path,
        default=Path("trunk/examples/experiments/checked-path-divergence/results/reachsafety189-t150-w4-bfs-dfs/mode_volatility_vs_paper"),
    )
    parser.add_argument("--clip-min", type=float, default=-4.0, help="Minimum plotted log2 ratio.")
    parser.add_argument("--clip-max", type=float, default=4.0, help="Maximum plotted log2 ratio.")
    return parser.parse_args()


def load_rows(path: Path) -> list[dict[str, str]]:
    with path.open(newline="", encoding="utf-8") as handle:
        return list(csv.DictReader(handle))


def cpu_ms(row: dict[str, str]) -> float:
    return float(row["runtime_ms"]) * int(row["threads"])


def main() -> None:
    args = parse_args()
    main_rows = load_rows(args.main_results)
    basic_rows = load_rows(args.basic_results)

    rows_by_mode: dict[str, dict[str, dict[str, str]]] = defaultdict(dict)
    for row in main_rows:
        if row["mode"] in {"PAPER", "LCPS", "BATCH_LCPS", "ADAPTIVE_BATCH_LCPS"}:
            rows_by_mode[row["mode"]][row["benchmark"]] = row
    for row in basic_rows:
        if row["mode"] in {"BFS", "DFS"}:
            rows_by_mode[row["mode"]][row["benchmark"]] = row

    paper = rows_by_mode["PAPER"]
    if not paper:
        raise SystemExit("No PAPER rows found")

    aggregate = {}
    points: dict[str, list[float]] = {}
    outliers: dict[str, tuple[int, int]] = {}
    for mode in MODES:
        values = []
        paper_total = 0.0
        mode_total = 0.0
        clipped_low = 0
        clipped_high = 0
        for benchmark, paper_row in paper.items():
            mode_row = rows_by_mode[mode].get(benchmark)
            if mode_row is None:
                continue
            paper_time = cpu_ms(paper_row)
            mode_time = cpu_ms(mode_row)
            if paper_time <= 0:
                continue
            log_ratio = math.log2(mode_time / paper_time)
            if log_ratio < args.clip_min:
                clipped_low += 1
                log_ratio = args.clip_min
            elif log_ratio > args.clip_max:
                clipped_high += 1
                log_ratio = args.clip_max
            values.append(log_ratio)
            paper_total += paper_time
            mode_total += mode_time
        points[mode] = values
        outliers[mode] = (clipped_low, clipped_high)
        aggregate[mode] = (mode_total / paper_total - 1.0) * 100.0 if paper_total else 0.0

    fig, ax = plt.subplots(figsize=(10.5, 5.2), constrained_layout=True)
    colors = {
        "BFS": "#4C78A8",
        "DFS": "#F58518",
        "LCPS": "#54A24B",
        "BATCH_LCPS": "#B279A2",
        "ADAPTIVE_BATCH_LCPS": "#E45756",
    }
    y_positions = {mode: len(MODES) - 1 - index for index, mode in enumerate(MODES)}
    for mode in MODES:
        y = y_positions[mode]
        values = points[mode]
        jitter = [((index % 17) - 8) * 0.014 for index, _ in enumerate(values)]
        ax.scatter(
            values,
            [y + offset for offset in jitter],
            s=18,
            alpha=0.58,
            color=colors[mode],
            edgecolors="white",
            linewidths=0.25,
            zorder=3,
        )

    ax.axvline(0, color="#222222", linewidth=1.2)
    ax.axvspan(args.clip_min, 0, color="#EAF3EA", alpha=0.45, zorder=0)
    ax.axvspan(0, args.clip_max, color="#F8ECEC", alpha=0.45, zorder=0)
    for y in y_positions.values():
        ax.axhline(y, color="#D7D7D7", linewidth=0.6, zorder=0)
    ax.set_yticks([y_positions[mode] for mode in MODES])
    ax.set_yticklabels([f"{mode}  ({aggregate[mode]:+.2f}%)" for mode in MODES])
    ax.set_xlim(args.clip_min, args.clip_max)
    ax.set_ylim(-0.55, len(MODES) - 0.45)
    ax.set_xlabel("Per-task log2 CPU-time ratio vs PAPER")
    ax.set_title("Per-Task CPU-Time Ratio vs PAPER", pad=12)
    ax.grid(axis="x", color="#BBBBBB", linewidth=0.7, alpha=0.5)
    tick_values = list(range(int(args.clip_min), int(args.clip_max) + 1))
    tick_labels = {
        -4: "1/16x",
        -3: "1/8x",
        -2: "1/4x",
        -1: "1/2x",
        0: "1x",
        1: "2x",
        2: "4x",
        3: "8x",
        4: "16x",
    }
    ax.set_xticks(tick_values)
    ax.set_xticklabels([tick_labels.get(tick, f"2^{tick}x") for tick in tick_values], fontsize=9)
    ax.tick_params(axis="y", pad=8)

    args.out_prefix.parent.mkdir(parents=True, exist_ok=True)
    png = args.out_prefix.with_suffix(".png")
    pdf = args.out_prefix.with_suffix(".pdf")
    fig.savefig(png, dpi=220, bbox_inches="tight", pad_inches=0.12)
    fig.savefig(pdf, bbox_inches="tight", pad_inches=0.12)
    print(png)
    print(pdf)


if __name__ == "__main__":
    main()
