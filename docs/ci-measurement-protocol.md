# CI timing-measurement protocol

How to get **trustworthy** numbers for a CI-shape change. Written because the
earlier experiment program (2026-05) produced numbers that can't be trusted:
single samples, taken during an Eclipse p2 mirror-flaky window, against a CI
shape that has since changed. Don't repeat that.

## What we're measuring

- **Wall-clock** per run = the slowest job (what a developer waits for).
- **Per-job duration** = the analysis bottleneck (settles e.g. spotbugs-vs-maven-verify).
- **Failure latency** = time-to-red on a *planted* lint violation — the metric the
  early-fail goal actually cares about. Measure it separately.

Wall-clock is the headline, but per-job + failure-latency are what tell you *why*.

## Noise floor (observed on this repo's `verify` runs)

Per-job spread across recent runs — any change must beat this to be real signal:

| Job | median | spread (±) |
|---|---|---|
| line-endings | ~5s | ±3s |
| checkstyle (old shape) | ~115s | ±~104s (≈90%!) |
| pmd (old shape) | ~191s | ±63s |
| maven-verify | ~869s (~14.5m) | ±~93s (≈11%) |

**Decision rule:** accept candidate B as faster than A only if
`median(B) < median(A) − 2 × IQR(A)`. For maven-verify a real win must exceed ~100s;
for checkstyle, ~200s. Anything smaller is noise.

## Protocol

1. **Pre-flight — confirm mirrors are healthy.** Run one throwaway build; if
   target-platform resolution stalls or errors, **stop** — the window is bad
   (this is what voided the 2026-05-09 numbers). Measure only on a clean window.
2. **Warm the caches.** Run 3–5 discard builds first so `~/.m2` + `.cache/tycho`
   are populated; cold-cache runs have a different (network-bound) profile.
3. **Sample.** N = 15–20 `workflow_dispatch` runs per candidate, back-to-back in a
   ≤30-minute window. Run A and B **interleaved within ≤10 minutes** of each other
   so they see the same mirror weather and runner-pool load.
4. **Report medians + IQR**, per job *and* total wall-clock. Never a single sample,
   never the mean.
5. **Pin `ubuntu-24.04`** (not a matrix) for consistent runner hardware.
6. **Record per-sample metadata:** cache hit/miss, run start time, headSha.
7. **Failure latency:** plant a synthetic PMD/Checkstyle violation, measure how long
   until the `lint` check goes red (independent of the build job).

## Why not just trust the old experiment numbers

- Single sample each (no repetition).
- 2026-05-09 mirror-flaky window → resolution time is contaminated and varies per job
  even within one dispatch.
- Numbers disagree across rounds (sequential measured 21m one round, 33m another).
- `#1369` reshaped master's CI after the experiments ran, so their baseline is stale.

## What is *not* a timing lever (validated)

- **Local p2 mirror**: with a warm `~/.m2`/`.cache/tycho`, offline resolution is
  ~equal to online (measured 5s vs 6s) — a mirror's steady-state speedup is ~0.
  Its only value is cold-cache + flaky-mirror insurance; deferred per the rare-flake
  rule.
- **`-Dtycho.mode=maven` on a gate pass**: marginal on a warm cache (resolution is
  already seconds); and it *breaks* PMD type-resolving rules (strips the classpath →
  false positives). Not used.
