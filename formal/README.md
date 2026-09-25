# Formal verification of DDK: analysis and status

> **Reference only, not for merge.** This directory records a lightweight formal-methods campaign on DDK: models, results, reproducible checks, and a catalogue of the defects found, with fix plans. No production code is changed; fixes are planned as separate PRs in [BUGS.md](BUGS.md).

## Method

The approach is targeted model-checking used as a bug finder, not full verification.

1. **Pick a target.** Choose a small, well-bounded, stateful or algorithmic part of the code, such as a race-prone handoff, an index structure or the release pipeline.
2. **Model it blind.** Independent agents write a faithful model in **TLA+** (checked with TLC) and in **Lean 4** (exhaustive bounded search plus proofs). The agents are told only generic expectations, never a suspected bug. The model mirrors the code as written; it does not fix it.
3. **Check it.** Each property is checked and each counterexample is mapped back to `file:line`.
4. **Check the model can catch bugs.** A fixed model must pass, a planted bug must be caught, and witness states must be reachable. Ablations show that each fix is needed.
5. **Verify adversarially.** Every finding goes to 3 independent skeptics, each looking at code-path correctness, the threading or environment assumptions, and the real impact. A finding is kept if at least 2 of 3 uphold it; severity is set by the impact that DDK or its downstream consumers can actually reach.
6. **Confirm in Java.** Where a deterministic failing-first test is possible, one is written against the real classes and run under the Tycho aggregator.

## Targets and status

| # | Target | Code | Tools | Difficulty | Findings (confirmed high / med / low) |
|---|---|---|---|---|---|
| 1 | Parallel resource loader and builder cluster loop | `ParallelResourceLoader`, `MonitoredClusteringBuilderState` | TLA+, Lean | easy | LDR: 0 / 1 / 2 |
| 3 | Find-references UI batching | `FastReferenceSearchResultContentProvider` | TLA+, Lean | easy | REF: 0 / 3 / 6 |
| 5 | Binary-model storage executor vs the shared `sources` set | `MonitoredClusteringBuilderState` | TLA+, Lean | medium | STO: 0 / 2 / 4 (+1 plausible, 1 refuted) |
| 2 | Qualified-name segment trie and pattern bounds | `QualifiedNameSegmentTreeLookup`, `QualifiedNamePattern`, `PatternAwareEObjectDescriptionLookUp` | Lean (primary), TLA+ | medium-hard | TRIE: 2 / 0 / 14 |
| 4 | Release and snapshot publish pipeline | `.github/workflows/{release,snapshot}.yml`, `.github/scripts/*` | TLA+ (primary), Lean | hard | PIPE: 3 / 2 / 2 |
| – | Code-read findings (formatter comparator, orphaned tests) | `ExtendedFormattingConfigBasedStream`, test aggregator | code read + scripts | – | RO: 0 / 1 / 7 |

**Total: 51 catalogued findings.** 49 are confirmed (5 high, 9 medium, 35 low), 1 is plausible and 1 was refuted. A further 9 adjacent observations were verified by three skeptics and are counted separately: all 9 confirmed (1 medium, 7 low, 1 not a defect). The full catalogue, with traces, verification votes and fix plans, is in [BUGS.md](BUGS.md).

**The most important findings:**
- **TRIE-1** ([#1551](https://github.com/dsldevkit/dsl-devkit/pull/1551)): case-sensitive pattern queries in `PatternAwareEObjectDescriptionLookUp` always return nothing, because the pattern is matched against itself. This is proved for all inputs in Lean.
- **TRIE-2:** a top-level `*`/`**` pattern misses almost every name.
- **LDR-1** ([#1553](https://github.com/dsldevkit/dsl-devkit/pull/1553)): a load poll that waits the full 300 s timeout with no result aborts the whole build as if the user had cancelled it. Lowered to medium after the skeptics: the trigger is rare.
- **PIPE-1/2/3** (PIPE-2: [#1550](https://github.com/dsldevkit/dsl-devkit/pull/1550)): maintenance lines cannot be released, the next version is derived from the wrong tag line, and a release tag can be left permanently without a release.
- **REF-1:** a UI deadlock between the Reset handler's `syncExec` and `removeListener`. Its severity was lowered to medium because it needs a narrow timing window.

**Fix PRs opened** (drafts):
- [#1550](https://github.com/dsldevkit/dsl-devkit/pull/1550) PIPE-2
- [#1551](https://github.com/dsldevkit/dsl-devkit/pull/1551) TRIE-1
- [#1552](https://github.com/dsldevkit/dsl-devkit/pull/1552) REF-2
- [#1553](https://github.com/dsldevkit/dsl-devkit/pull/1553) LDR-1

## Models

| Target | TLA+ (lines) | Lean (lines) | Lean proofs for all sizes |
|---|---|---|---|
| parallel-loader | 620 | 686 | `P1_fixTimeout`: the counter invariant holds for all sizes after the fix |
| find-refs | 483 | 723 | `no_lost_root`: no lost update in the fixed design |
| binary-storage | 504 | 617 | P1+P3: a URI is binary-loadable only once its binary is written (fixed model) |
| trie | 701 | 1,679 | the root causes of TRIE-1/2/3/4/5 and the correctness of the `'\u0000'` successor fix |
| pipeline | 517 | 792 | none; bounded `native_decide` results only |

Each directory's `NOTES.md` covers the modelled assumptions, the properties, how each was checked (a proof or a bounded check), the state counts, and the traces. The notes sometimes cite run outputs (`*.out`, `logs/`, `out/`, `matrix*.log`). Those files are regenerated by `check.sh` and not committed; the committed `tla/expected.txt` files hold the verdicts.

## How to reproduce

```sh
# once: TLA+ tools (v1.7.4, SHA-256 936a262061c914694dfd669a543be24573c45d5aa0ff20a8b96b23d01e050e88)
gh release download v1.7.4 -R tlaplus/tlaplus -p tla2tools.jar -D formal/.tools
# Lean: elan with toolchain leanprover/lean4:v4.35.0-rc2 (no Mathlib, no dependencies)

formal/check.sh            # every model; about 12-15 min
formal/check.sh --full     # adds the slow liveness and large runs (about 45 min more)
formal/check.sh --only=trie
```

What `check.sh` does:
- **TLA+:** runs each target's matrix and compares every run's verdict with `tla/expected.txt`. For the code as written, the expected verdict is VIOLATED for each confirmed finding. Fixed models must pass. Ablations, planted bugs and witnesses must be violated.
- **Lean:** builds each project, rejects any `sorry` or `admit`, and runs `#print axioms` on every theorem listed in `lean/theorems.txt`. Kernel proofs may use only `propext`, `Classical.choice` and `Quot.sound`. `native_decide` results may also use the compiler-trust axioms.
- **Orphaned tests:** runs `readonly/orphans/check-test-reachability.sh`, which is expected to report the test classes that never run.

**Java tests.** Each failing-first test is registered in `XtextTestSuite` or `XtextUiTestSuite` and annotated `@Disabled("Documents <ID>…")`, so CI stays green. To see a bug, remove the annotation and run the aggregator. A fix PR removes the annotation together with the fix. Reference fixes are in `f1-fix.patch` (LDR-1), `find-refs/fix-cme.patch` (REF-2) and `trie/lean/fix-plan.patch` (TRIE).

## Local validation done for this PR

- `formal/check.sh --clean` passes: 180 TLC runs across 5 models, 5 Lean builds, 70 kernel proofs and 54 `native_decide` results, and the orphan check.
- **Tests enabled on master:** all 21 `@Disabled` methods fail exactly as documented, and the 4 guard methods pass.
- **With the reference patches and bundle bumps applied:** every test passes except TRIE-9 (by design) and RO-1 (no patch yet). Five `QualifiedNamePatternTest` methods that assert the old `"!"` bound fail and must be updated by the TRIE fix PRs.
- **Tests disabled:** the full `mvn clean verify` matches master exactly, including 4 pre-existing environment-specific failures on the local JDK 27 build that CI (Java 21) does not show. `checkstyle:check`, `pmd:check` and the line-ending check pass.

## Limits

- **Models are abstractions.** Every counterexample was checked against the real code, and the Java tests confirm the deterministic ones. Races marked CONFIRMED rely on the model plus code or bytecode reading.
- **Bounds are small.** TLC and the Lean searches cover small configurations exhaustively, typically 1–3 threads and 2–5 names or URIs. The all-sizes claims are only the Lean proofs listed above.
- **STO is downstream-only.** DDK's own target platforms don't enable binary-model storage, so those findings affect downstream products only; their severities are capped accordingly.
- **Campaign log.** [REPORT.md](REPORT.md) is the chronological log of rounds 1–2, kept as it was written. [BUGS.md](BUGS.md) is authoritative wherever the two differ, for example on severities.
