# Migration Verification Ledger

Tracks the byte-by-byte / faithfulness verification status of every Xtend→Java migrated file.
Local working doc — **never commit** (lives under untracked `.claude/`). Last updated 2026-06-25.

## Why two kinds of "vetted"
These migrations include **code generators** whose emitted text becomes committed `src-gen`; for those, output must be **byte-identical** to what the Xtend compiler produced (`xtend-gen`) — CI cannot catch generator drift (no regen in the build). Non-generator files (tests, plain logic) emit no generated text, so byte-vetting is N/A; they need only **behavioural faithfulness**.

## Verification tiers (strongest → weakest)
- **HARNESS** — executable `StringConcatenation` byte-equality harness vs `xtend-gen` (gold standard; `.claude/coalesce-verify/*.java`)
- **APPEND-DIFF** — append-for-append comparison of migrated `.java` vs `xtend-gen` (authoritative for emitted bytes)
- **FAITHFULNESS** — behavioural read of logic/test files (emit no generated text → byte-vet N/A)
- **SWEEP** — 51-agent ultracode faithfulness review (the merged batch, read-based)
- **NONE** — not yet vetted

---

## A. Generators (emit committed text) — BYTE-VETTED

| File | PR | Tier | Result |
|------|----|------|--------|
| `LspBuilderIntegrationFragment2` | #1430 | HARNESS (`Probe`) | IDENTICAL |
| `StandaloneBuilderIntegrationFragment2` | #1430 | HARNESS (`SB`) | IDENTICAL |
| `FormatterFragment2` | #1430 | HARNESS (`FF`) | IDENTICAL |
| `LanguageConstantsFragment2` | #1430 | HARNESS (`LC`) | IDENTICAL |
| `AnnotationAwareContentAssistFragment2` | #1430 | HARNESS (`AACA`) | **DRIFT found → fixed in #1446** |
| `ResourceFactoryFragment2` | #1430 | APPEND-DIFF | IDENTICAL |
| `BuilderIntegrationFragment2` | #1430 | APPEND-DIFF (per-pair) | IDENTICAL |
| `BundleVersionStripperFragment`, `DefaultFragmentWithOverride`, `ProjectConfig`, `ModelInferenceFragment2`, `CompareFragment2` | #1430 | APPEND-DIFF / per-pair read | IDENTICAL |
| `FormatGenerator` | #1428 | APPEND-DIFF (Phase 2) | IDENTICAL |
| `FormatJvmModelInferrer` | #1428 | APPEND-DIFF + deep-dive | `getDirectiveName` Group0 bug → fixed via A0/#1443; `List.of`→`Arrays.asList` → #1446 |
| `FormatFragment2` (format.generator) | #1428-era | APPEND-DIFF (Phase 2) | IDENTICAL |
| `CheckQuickfixProviderFragment2` (check.generator) | merged | APPEND-DIFF (Phase 2) | IDENTICAL |
| `CheckCfgGenerator` (checkcfg.core) | merged | APPEND-DIFF (Phase 2) | IDENTICAL |
| `AbstractAnnotationAwareAntlrGrammarGenerator` | #1429 | APPEND-DIFF (manual) | IDENTICAL |
| `AnnotationAwareAntlrGrammarGenerator` | #1429 | HARNESS (`AAGG1`/`AAGG2`) | IDENTICAL |
| `AnnotationAwareAntlrContentAssistGrammarGenerator` | #1429 | HARNESS (`CA1`) + APPEND-DIFF (agent, empirical StringConcatenation) | IDENTICAL (incl. uncommitted batch-1) |
| `AnnotationAwareXtextAntlrGeneratorFragment2` | #1429 | APPEND-DIFF (agent) | IDENTICAL |

**Every byte-output generator across the campaign is byte-vetted.** The only confirmed generator drift (`AnnotationAware`) is fixed in #1446; the only generator behavioural defect (`getDirectiveName`) was split out and merged as #1443.

## B. Logic / test files — FAITHFULNESS-VETTED (byte-vet N/A)

- **#1429**: `GrammarRuleAnnotations`, `PredicatesNaming` — workflow agents, logic faithful.
- **#1427 check.core.test (11)**: per-pair read by me — all OK (1 nit, §D).
- **Merged batch (~31)** — checkcfg.core(.test), sample.helloworld, xtext.ui, xtext.scope/export generators, format.ide/ui, check.test.runtime, check/format .test, xtext.check.generator — **SWEEP** (51-agent ultracode), all CLEAR (1 nit, §D). Generators within this batch were additionally APPEND-DIFF'd (§A).

## C. Pure deletions — N/A (no `.java` counterpart, not migrations)
- `AbstractResourceDescriptionManagerTest.xtend` (dead JUnit4 infra, `d920b3bad`)
- `CheckNewProject.xtend`, `CheckQuickfixProvider.xtend` (wizard removal, `06de65661`)

## D. Known divergences (all fixed or benign)
| File | Divergence | Status |
|------|-----------|--------|
| `AnnotationAwareContentAssistFragment2` | multi-line `Alternatives` continuation-indent drift | **fixed in #1446** |
| `FormatJvmModelInferrer.getDirectiveName` | `Group0` varargs-trap bug | **fixed, merged #1443** |
| `FormatJvmModelInferrer` dispatcher throws | `List.of` null-intolerant; empty-`[]` noise | **fixed in #1446** (9 → `Arrays.asList`, 3 empty simplified) |
| `CheckModelUtil.modelWithContexts` (#1427) | trailing `"\n    "` vs Xtend | **deferred** — only caller is `@Disabled`; near-zero impact |
| `CheckCfgScopeProviderTest` (checkcfg.core.test) | `NPE`→`IllegalStateException` (same msg/outcome) | **kept** — intentional quality improvement, documented |

## E. Not yet byte-vetted
- **No generator remains unvetted.** Non-generator merged files have FAITHFULNESS/SWEEP coverage only (byte-vet N/A — they emit no generated text).
- **#1429 is the last open migration** — all 6 of its files are now byte-/faithfulness-vetted (§A/§B); pending: Ruben re-review of the remaining "multi-line string" comments + the `:90` decision.

---

## F. Verification machinery — how to (re)trigger

### Ground truth: `xtend-gen`
The authoritative byte target is **`xtend-gen/`** — the Xtend compiler's own `.java` output. After a `.xtend` is migrated (deleted), its `xtend-gen` is gone from that branch, but **sibling worktrees on older commits still have it**:
- locate: `find /Users/joao/Git/Avaloq/dsl-devkit* -path '*xtend-gen*<ClassName>.java'`
- original `.xtend`: `git show <migration-commit>^:<path>` (or `upstream/master:<path>` while the PR is open)
- **validity gates (always check):** (a) the worktree's `.xtend` is byte-identical to the migrated original (`diff`), and (b) `xtend-gen` mtime ≥ `.xtend` mtime (fresh, i.e. built from that source).

### Method 1 — executable HARNESS (gold standard) — `.claude/coalesce-verify/*.java`
Lift BOTH the original append sequence (a real `new StringConcatenation("\n")`, dynamic values as params) and the candidate (text block `.formatted(...)`) into a tiny Java program; assert `old.toString().equals(neu)` over an input battery (empty / multi-line / single-line / quotes / `%` / unicode / stub-vs-non-stub / options-vs-single). Compile/run:
```
JAR=~/.m2/repository/org/eclipse/xtext/org.eclipse.xtext.xbase.lib/2.43.0/org.eclipse.xtext.xbase.lib-2.43.0.jar
javac -cp "$JAR" -d . X.java && java -cp "$JAR:." X
```
Existing harnesses: `Probe, SB, FF, LC, AACA, AAGG1, AAGG2, CA1`. Use real `StringConcatenation`/`StringConcatenationClient` (not a re-implementation) so two-arg `append(v,indent)` re-indent + `newLineIfNotEmpty` semantics are exact. `AACA` is the template that caught the only real drift (two-arg→single-arg on a multi-line value).

### Method 2 — ULTRACODE Opus workflow (scale) — `Workflow({scriptPath})`
Parallel Opus agents, one per file, each comparing migrated `.java` vs `xtend-gen` **append-for-append**, then an adversarial **refute-by-default** pass; schema-validated structured output; `pipeline(review → refute)`; `model: 'opus'`; **FIND-ONLY (read-only, no side effects).**
- `.claude/wf-sweep.js` — the 51-file merged-migration sweep (CFG `.claude/sweep-cfg.json`, built from `git log --diff-filter=D --name-only -- '*.xtend'`).
- `.claude/wf-p1429.js` — the 4 remaining #1429 generators/logic.
- re-run/iterate: `Workflow({scriptPath, resumeFromRunId})` (completed agents return cached results).

**The drift checklist baked into the agent prompt (and what to look for manually):**
1. **two-arg `append(value,indent)` vs single-arg** — single-arg drops re-indentation of *continuation lines* of a MULTI-LINE value (the `AnnotationAware` bug). For every two-arg in `xtend-gen`, confirm migrated matches (or value is provably single-line).
2. trailing whitespace from loop bodies (`«FOR»` strips last-element trailing; a `for` appending `"\n    "` does not — the `modelWithContexts` bug).
3. `newLine()` vs `newLineIfNotEmpty()` (differ on empty/whitespace-only lines).
4. `StringBuilder "\n"` vs `StringConcatenation` default delimiter.
5. Java text-block stripping (min indent incl. closing delim + trailing ws) vs Xtend rich-string min-content-indent stripping.
6. import-managed `append(TypeReference/Class)` folded into a `%s` text block (output/import drift).
7. `List.of` (null-intolerant) vs `Arrays.asList` (xtend-gen).
**Benign — NOT findings:** `xtend-gen` splits `append("  ")+append("x")` vs migrated combined `append("  x")`; `_xxx` temp vars vs inlined calls; source-style/whitespace that doesn't change emitted bytes.

### Adjudication rule
Agent CLEAR is not final for generators — the only proofs that *caught* drift were the executable harness + append-for-append. Treat agent verdicts as triage; **harness-confirm any non-trivial generator** (esp. multi-line two-arg cases) before trusting CLEAR.

---

## G. Complete file inventory (all 69 deleted `.xtend`, every file recorded)

*Auto-derived 2026-06-25 from `git log --diff-filter=D` on master + the open #1429 branch. 66 migrated + 3 deletions.*
Tier totals: APPEND-DIFF=14, DELETION (not a migration)=3, FAITHFULNESS (binding-only, no text)=3, FAITHFULNESS (per-pair)=11, FAITHFULNESS (workflow)=2, HARNESS=7, SWEEP=29


**com.avaloq.tools.ddk.check.core.test** (11)
- `IssueCodeValueTest.xtend` — FAITHFULNESS (per-pair)
- `BasicModelTest.xtend` — FAITHFULNESS (per-pair)
- `BugAig830.xtend` — FAITHFULNESS (per-pair)
- `CheckScopingTest.xtend` — FAITHFULNESS (per-pair)
- `IssueCodeToLabelMapGenerationTest.xtend` — FAITHFULNESS (per-pair)
- `ProjectBasedTests.xtend` — FAITHFULNESS (per-pair)
- `CheckModelUtil.xtend` — FAITHFULNESS (per-pair)
- `CheckTestUtil.xtend` — FAITHFULNESS (per-pair)
- `CheckFormattingTest.xtend` — FAITHFULNESS (per-pair)
- `CheckApiAccessValidationsTest.xtend` — FAITHFULNESS (per-pair)
- `CheckValidationTest.xtend` — FAITHFULNESS (per-pair)

**com.avaloq.tools.ddk.check.test.runtime** (1)
- `TestLanguageGenerator.xtend` — SWEEP

**com.avaloq.tools.ddk.check.test.runtime.tests** (3)
- `CheckConfigurationIsAppliedTest.xtend` — SWEEP
- `CheckExecutionEnvironmentProjectTest.xtend` — SWEEP
- `IssueLabelTest.xtend` — SWEEP

**com.avaloq.tools.ddk.check.ui** (2)
- `CheckNewProject.xtend` — DELETION (not a migration)
- `CheckQuickfixProvider.xtend` — DELETION (not a migration)

**com.avaloq.tools.ddk.check.ui.test** (1)
- `CheckQuickfixTest.xtend` — SWEEP

**com.avaloq.tools.ddk.checkcfg.core** (4)
- `CheckCfgGenerator.xtend` — APPEND-DIFF
- `CheckCfgJvmModelInferrer.xtend` — SWEEP
- `PropertiesInferenceHelper.xtend` — SWEEP
- `ConfiguredParameterChecks.xtend` — SWEEP

**com.avaloq.tools.ddk.checkcfg.core.test** (7)
- `CheckCfgContentAssistTest.xtend` — SWEEP
- `CheckCfgScopeProviderTest.xtend` — SWEEP
- `CheckCfgSyntaxTest.xtend` — SWEEP
- `CheckCfgModelUtil.xtend` — SWEEP
- `CheckCfgTestUtil.xtend` — SWEEP
- `CheckCfgConfiguredParameterValidationsTest.xtend` — SWEEP
- `CheckCfgTest.xtend` — SWEEP

**com.avaloq.tools.ddk.sample.helloworld.ui.test** (3)
- `CheckConfigurationIsAppliedTest.xtend` — SWEEP
- `CheckExecutionEnvironmentProjectTest.xtend` — SWEEP
- `IssueLabelTest.xtend` — SWEEP

**com.avaloq.tools.ddk.xtext.check.generator** (2)
- `CheckValidatorFragment2.xtend` — FAITHFULNESS (binding-only, no text)
- `CheckQuickfixProviderFragment2.xtend` — APPEND-DIFF

**com.avaloq.tools.ddk.xtext.export.generator** (1)
- `ExportFragment2.xtend` — FAITHFULNESS (binding-only, no text)

**com.avaloq.tools.ddk.xtext.format** (6)
- `FormatRuntimeModule.xtend` — SWEEP
- `FormatStandaloneSetup.xtend` — SWEEP
- `FormatGenerator.xtend` — APPEND-DIFF
- `FormatJvmModelInferrer.xtend` — APPEND-DIFF
- `FormatScopeProvider.xtend` — SWEEP
- `FormatValidator.xtend` — SWEEP

**com.avaloq.tools.ddk.xtext.format.generator** (1)
- `FormatFragment2.xtend` — APPEND-DIFF

**com.avaloq.tools.ddk.xtext.format.ide** (2)
- `FormatIdeModule.xtend` — SWEEP
- `FormatIdeSetup.xtend` — SWEEP

**com.avaloq.tools.ddk.xtext.format.test** (1)
- `FormatParsingTest.xtend` — SWEEP

**com.avaloq.tools.ddk.xtext.format.ui** (1)
- `FormatUiModule.xtend` — SWEEP

**com.avaloq.tools.ddk.xtext.generator** (18)
- `BundleVersionStripperFragment.xtend` — APPEND-DIFF
- `DefaultFragmentWithOverride.xtend` — APPEND-DIFF
- `BuilderIntegrationFragment2.xtend` — APPEND-DIFF
- `LspBuilderIntegrationFragment2.xtend` — HARNESS
- `StandaloneBuilderIntegrationFragment2.xtend` — HARNESS
- `FormatterFragment2.xtend` — HARNESS
- `LanguageConstantsFragment2.xtend` — HARNESS
- `ProjectConfig.xtend` — APPEND-DIFF
- `ModelInferenceFragment2.xtend` — APPEND-DIFF
- `AbstractAnnotationAwareAntlrGrammarGenerator.xtend` — APPEND-DIFF
- `AnnotationAwareAntlrContentAssistGrammarGenerator.xtend` — HARNESS
- `AnnotationAwareAntlrGrammarGenerator.xtend` — HARNESS
- `AnnotationAwareXtextAntlrGeneratorFragment2.xtend` — APPEND-DIFF
- `GrammarRuleAnnotations.xtend` — FAITHFULNESS (workflow)
- `PredicatesNaming.xtend` — FAITHFULNESS (workflow)
- `ResourceFactoryFragment2.xtend` — APPEND-DIFF
- `CompareFragment2.xtend` — APPEND-DIFF
- `AnnotationAwareContentAssistFragment2.xtend` — HARNESS

**com.avaloq.tools.ddk.xtext.generator.test** (1)
- `XbaseGeneratorFragmentTest.xtend` — SWEEP

**com.avaloq.tools.ddk.xtext.scope.generator** (1)
- `ScopingFragment2.xtend` — FAITHFULNESS (binding-only, no text)

**com.avaloq.tools.ddk.xtext.test.core** (1)
- `AbstractResourceDescriptionManagerTest.xtend` — DELETION (not a migration)

**com.avaloq.tools.ddk.xtext.ui** (1)
- `TemplateProposalProviderHelper.xtend` — SWEEP

**com.avaloq.tools.ddk.xtext.ui.test** (1)
- `TemplateProposalProviderHelperTest.xtend` — SWEEP

