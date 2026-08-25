# Xtend → Java migration roadmap

Living document. Lives on `feature/xtend-to-java-migration`. Updated each week as slices are cut and merged.

## Scope

94 `.xtend` files → 87 `.java` files on this branch, with 2122 supporting edits (POMs, MANIFEST.MFs, feature.xmls, `.project`, `.classpath`). The migration is complete on this branch; the remaining work is **merging it into master in reviewable slices**, one per week, with each slice manually vetted against the Java best-practices checklist below.

## Process for each weekly slice

1. **Cut a fresh branch from master**, not from this branch:
   ```bash
   SLICE=migrate/xtend-to-java/<slice-name>
   git fetch origin
   git checkout -b "$SLICE" origin/master
   ```
2. **Grab just this slice's files from the migration branch**:
   ```bash
   git checkout feature/xtend-to-java-migration -- <path1> <path2> ...
   ```
3. **Manually vet each file** against the review checklist below. Do not trust that the migration branch is already clean — the goal of the slice review is to catch any residual issues.
4. **Build and test the affected plugins**:
   ```bash
   mvn -pl <module1>,<module2> -am verify -f ./ddk-parent/pom.xml
   ```
   Then run the full CI-equivalent check before pushing:
   ```bash
   xvfb-run mvn clean verify checkstyle:check pmd:pmd pmd:cpd pmd:check pmd:cpd-check spotbugs:check -f ./ddk-parent/pom.xml --batch-mode --fail-at-end
   ```
5. **Commit, push, open PR** against master.
6. **Once merged**, update the status column in this file.

## Review checklist (applied to every slice)

For each migrated `.java` file, verify:

- [ ] **No `val`** — Xtend's `val` must not leak via pretend-typed variables. Use an explicit type or `var`; prefer explicit type for fields, method returns, and any variable where the type is not obvious from the RHS.
- [ ] **String handling is idiomatic**:
  - simple `+` concatenation for a single dynamic insertion,
  - `String.format` for multi-value templates,
  - text blocks for multi-line strings,
  - `StringBuilder` only when building in a loop or branch,
  - never `String.valueOf(x) + "..."` when `x + "..."` works.
- [ ] **Preserved stack traces in catch blocks** (PMD `PreserveStackTrace`): every `throw new WrapperException(...)` in a catch must pass the caught exception as cause.
- [ ] **Parameterized SLF4J logging**: `logger.info("x={}", x)` — never `logger.info("x=" + x)`, never `logger.info(String.format(...))`.
- [ ] **try-with-resources** for every `AutoCloseable` (streams, scanners, writers, JDBC).
- [ ] **No unnecessary boxing** — `Integer.valueOf(i)` only when the method signature demands it.
- [ ] **`@Override` on every override**, including interface methods.
- [ ] **No wildcard imports**.
- [ ] **PMD, Checkstyle, SpotBugs clean** — the slice must pass `verify.yml`'s full command without `--fail-at-end` tolerating anything.
- [ ] **Behavior preserved** — sanity-diff the Xtend source against the Java output for any non-mechanical transformation (lambda captures, operator overloading → method calls, extension methods, elvis `?:`, safe-nav `?.`, list/map literal syntax).
- [ ] **Each plugin still builds standalone**, and each plugin's tests still pass on their own.

## Slice order

Ordered leaves → trunk so that rollback of a single slice doesn't cascade. DSL families move as one unit (core + ide + ui + test + generator) so that each slice is independently shippable.

| # | Week | Slice | Modules | Xtend files | Risk | Status |
|---|------|-------|---------|-------------|------|--------|
| 1 | TBD  | Warmup — samples & leaf tests | `sample.helloworld.ui.test`, `check.ui.test`, `xtext.ui.test`, `xtext.generator.test` | 6 | Low | Not started |
| 2 | TBD  | `xtext.format` DSL family | `xtext.format`, `.format.ide`, `.format.test`, `.format.ui`, `.format.generator` | 11 | Low | Not started |
| 3 | TBD  | `xtext.scope` DSL family | `xtext.scope`, `.scope.generator` | 5 | Low | Not started |
| 4 | TBD  | `xtext.expression` DSL | `xtext.expression` | 5 | Low | Not started |
| 5 | TBD  | `xtext.export` DSL family | `xtext.export`, `.export.generator` | 10 | Medium | Not started |
| 6 | TBD  | `checkcfg` DSL + tests | `checkcfg.core`, `checkcfg.core.test` | 11 | Medium | Not started |
| 7 | TBD  | `check.core` (production DSL) | `check.core` | 8 | **High** — production check framework | Not started |
| 8 | TBD  | Check tests & runtime | `check.core.test`, `check.test.runtime`, `check.test.runtime.tests` | 15 | Low (tests only) | Not started |
| 9 | TBD  | Xtext test utilities + UI helpers | `xtext.test.core`, `xtext.ui`, `xtext.check.generator` | 5 | Medium | Not started |
| 10 | TBD | `xtext.generator` — parser group | `xtext.generator` (Antlr / annotation-aware fragments + `BundleVersionStripperFragment`, `DefaultFragmentWithOverride`) | 8 | Medium (build-time only) | Not started |
| 11 | TBD | `xtext.generator` — builder + misc | `xtext.generator` (builder/LSP fragments, formatter, language constants, model inference, project config, resource factory, compare, content-assist) | 10 | Medium (build-time only) | Not started |
| 12 | TBD | Cleanup — remove Xtend build infrastructure | POMs (`xtend-maven-plugin`), MANIFEST.MFs (`org.eclipse.xtend` imports), `feature.xml` (xtend bundles), `.classpath` / `.project` (Xtend nature), `xtend-gen/` directories, PMD config references | 0 (infrastructure only) | Low — final confirmation that nothing imports Xtend anymore | Not started |

If a week is particularly quiet or a slice is particularly small, adjacent small slices (e.g., #3 + #4) can combine. Don't combine across risk tiers.

## File inventory per slice

<details>
<summary>Slice 1 — Warmup — samples & leaf tests (6 files)</summary>

- `com.avaloq.tools.ddk.sample.helloworld.ui.test/src/.../CheckConfigurationIsAppliedTest.xtend`
- `com.avaloq.tools.ddk.sample.helloworld.ui.test/src/.../CheckExecutionEnvironmentProjectTest.xtend`
- `com.avaloq.tools.ddk.sample.helloworld.ui.test/src/.../IssueLabelTest.xtend`
- `com.avaloq.tools.ddk.check.ui.test/src/.../CheckQuickfixTest.xtend`
- `com.avaloq.tools.ddk.xtext.ui.test/src/.../TemplateProposalProviderHelperTest.xtend`
- `com.avaloq.tools.ddk.xtext.generator.test/src/.../XbaseGeneratorFragmentTest.xtend`
</details>

<details>
<summary>Slice 2 — xtext.format DSL family (11 files)</summary>

- `xtext.format/src/.../FormatRuntimeModule.xtend`
- `xtext.format/src/.../FormatStandaloneSetup.xtend`
- `xtext.format/src/.../generator/FormatGenerator.xtend`
- `xtext.format/src/.../jvmmodel/FormatJvmModelInferrer.xtend`
- `xtext.format/src/.../scoping/FormatScopeProvider.xtend`
- `xtext.format/src/.../validation/FormatValidator.xtend`
- `xtext.format.ide/src/.../FormatIdeModule.xtend`
- `xtext.format.ide/src/.../FormatIdeSetup.xtend`
- `xtext.format.test/src/.../FormatParsingTest.xtend`
- `xtext.format.ui/src/.../FormatUiModule.xtend`
- `xtext.format.generator/src/.../FormatFragment2.xtend`
</details>

<details>
<summary>Slice 3 — xtext.scope DSL family (5 files)</summary>

- `xtext.scope/src/.../generator/ScopeGenerator.xtend`
- `xtext.scope/src/.../generator/ScopeNameProviderGenerator.xtend`
- `xtext.scope/src/.../generator/ScopeProviderGenerator.xtend`
- `xtext.scope/src/.../generator/ScopeProviderX.xtend`
- `xtext.scope.generator/src/.../ScopingFragment2.xtend`
</details>

<details>
<summary>Slice 4 — xtext.expression DSL (5 files)</summary>

- `xtext.expression/src/.../generator/CodeGenerationX.xtend`
- `xtext.expression/src/.../generator/ExpressionExtensionsX.xtend`
- `xtext.expression/src/.../generator/GeneratorUtilX.xtend`
- `xtext.expression/src/.../generator/GenModelUtilX.xtend`
- `xtext.expression/src/.../generator/Naming.xtend`
</details>

<details>
<summary>Slice 5 — xtext.export DSL family (10 files)</summary>

- `xtext.export/src/.../generator/ExportedNamesProviderGenerator.xtend`
- `xtext.export/src/.../generator/ExportFeatureExtensionGenerator.xtend`
- `xtext.export/src/.../generator/ExportGenerator.xtend`
- `xtext.export/src/.../generator/ExportGeneratorX.xtend`
- `xtext.export/src/.../generator/FingerprintComputerGenerator.xtend`
- `xtext.export/src/.../generator/FragmentProviderGenerator.xtend`
- `xtext.export/src/.../generator/ResourceDescriptionConstantsGenerator.xtend`
- `xtext.export/src/.../generator/ResourceDescriptionManagerGenerator.xtend`
- `xtext.export/src/.../generator/ResourceDescriptionStrategyGenerator.xtend`
- `xtext.export.generator/src/.../ExportFragment2.xtend`
</details>

<details>
<summary>Slice 6 — checkcfg DSL + tests (11 files)</summary>

- `checkcfg.core/src/.../generator/CheckCfgGenerator.xtend`
- `checkcfg.core/src/.../jvmmodel/CheckCfgJvmModelInferrer.xtend`
- `checkcfg.core/src/.../util/PropertiesInferenceHelper.xtend`
- `checkcfg.core/src/.../validation/ConfiguredParameterChecks.xtend`
- `checkcfg.core.test/src/.../contentassist/CheckCfgContentAssistTest.xtend`
- `checkcfg.core.test/src/.../scoping/CheckCfgScopeProviderTest.xtend`
- `checkcfg.core.test/src/.../syntax/CheckCfgSyntaxTest.xtend`
- `checkcfg.core.test/src/.../util/CheckCfgModelUtil.xtend`
- `checkcfg.core.test/src/.../util/CheckCfgTestUtil.xtend`
- `checkcfg.core.test/src/.../validation/CheckCfgConfiguredParameterValidationsTest.xtend`
- `checkcfg.core.test/src/.../validation/CheckCfgTest.xtend`
</details>

<details>
<summary>Slice 7 — check.core production DSL (8 files)</summary>

- `check.core/src/.../compiler/CheckGeneratorConfig.xtend`
- `check.core/src/.../formatting2/CheckFormatter.xtend`
- `check.core/src/.../generator/CheckGenerator.xtend`
- `check.core/src/.../generator/CheckGeneratorExtensions.xtend`
- `check.core/src/.../generator/CheckGeneratorNaming.xtend`
- `check.core/src/.../jvmmodel/CheckJvmModelInferrer.xtend`
- `check.core/src/.../scoping/CheckScopeProvider.xtend`
- `check.core/src/.../typing/CheckTypeComputer.xtend`

**Review extra-carefully:** this is the runtime Check framework used by downstream consumers. Diff every file against its Xtend original line-by-line, not just for style.
</details>

<details>
<summary>Slice 8 — Check tests & runtime (15 files)</summary>

- `check.core.test/src/.../generator/IssueCodeValueTest.xtend`
- `check.core.test/src/.../test/BasicModelTest.xtend`
- `check.core.test/src/.../test/BugAig830.xtend`
- `check.core.test/src/.../test/CheckScopingTest.xtend`
- `check.core.test/src/.../test/IssueCodeToLabelMapGenerationTest.xtend`
- `check.core.test/src/.../test/ProjectBasedTests.xtend`
- `check.core.test/src/.../test/util/CheckModelUtil.xtend`
- `check.core.test/src/.../test/util/CheckTestUtil.xtend`
- `check.core.test/src/.../formatting/CheckFormattingTest.xtend`
- `check.core.test/src/.../validation/CheckApiAccessValidationsTest.xtend`
- `check.core.test/src/.../validation/CheckValidationTest.xtend`
- `check.test.runtime/src/.../generator/TestLanguageGenerator.xtend`
- `check.test.runtime.tests/src/.../CheckConfigurationIsAppliedTest.xtend`
- `check.test.runtime.tests/src/.../CheckExecutionEnvironmentProjectTest.xtend`
- `check.test.runtime.tests/src/.../label/IssueLabelTest.xtend`
</details>

<details>
<summary>Slice 9 — Xtext test utilities + UI helpers (5 files)</summary>

- `xtext.test.core/src/.../resource/AbstractResourceDescriptionManagerTest.xtend`
- `xtext.test.core/src/.../Tag.xtend`
- `xtext.ui/src/.../templates/TemplateProposalProviderHelper.xtend`
- `xtext.check.generator/src/.../CheckValidatorFragment2.xtend`
- `xtext.check.generator/src/.../quickfix/CheckQuickfixProviderFragment2.xtend`
</details>

<details>
<summary>Slice 10 — xtext.generator parser group (8 files)</summary>

- `xtext.generator/src/.../parser/antlr/AbstractAnnotationAwareAntlrGrammarGenerator.xtend`
- `xtext.generator/src/.../parser/antlr/AnnotationAwareAntlrContentAssistGrammarGenerator.xtend`
- `xtext.generator/src/.../parser/antlr/AnnotationAwareAntlrGrammarGenerator.xtend`
- `xtext.generator/src/.../parser/antlr/AnnotationAwareXtextAntlrGeneratorFragment2.xtend`
- `xtext.generator/src/.../parser/common/GrammarRuleAnnotations.xtend`
- `xtext.generator/src/.../parser/common/PredicatesNaming.xtend`
- `xtext.generator/src/.../BundleVersionStripperFragment.xtend`
- `xtext.generator/src/.../DefaultFragmentWithOverride.xtend`
</details>

<details>
<summary>Slice 11 — xtext.generator builder & misc (10 files)</summary>

- `xtext.generator/src/.../builder/BuilderIntegrationFragment2.xtend`
- `xtext.generator/src/.../builder/LspBuilderIntegrationFragment2.xtend`
- `xtext.generator/src/.../builder/StandaloneBuilderIntegrationFragment2.xtend`
- `xtext.generator/src/.../formatting/FormatterFragment2.xtend`
- `xtext.generator/src/.../languageconstants/LanguageConstantsFragment2.xtend`
- `xtext.generator/src/.../modelinference/ModelInferenceFragment2.xtend`
- `xtext.generator/src/.../model/project/ProjectConfig.xtend`
- `xtext.generator/src/.../resourceFactory/ResourceFactoryFragment2.xtend`
- `xtext.generator/src/.../ui/compare/CompareFragment2.xtend`
- `xtext.generator/src/.../ui/contentAssist/AnnotationAwareContentAssistFragment2.xtend`
</details>

<details>
<summary>Slice 12 — Cleanup (infrastructure only, 0 xtend files)</summary>

This slice removes every remaining trace of Xtend tooling now that no `.xtend` sources exist:

- `ddk-parent/pom.xml` — remove `xtend-maven-plugin`, `xtend-gen` source directory config, Xtend dependency versions
- Per-plugin `pom.xml` — remove any residual Xtend plugin blocks
- Per-plugin `MANIFEST.MF` — remove `Import-Package: org.eclipse.xtend.*` / `org.eclipse.xtext.xbase.lib` entries that are no longer used
- Per-plugin `.classpath` — remove `xtend-gen` source folder entries
- Per-plugin `.project` — remove Xtend nature, Xtend incremental project builder
- `releng/**/feature.xml` — remove `org.eclipse.xtend.lib` and related bundles from the product features
- Delete every `xtend-gen/` directory
- `ddk-configuration/pmd/ruleset.xml` — remove `.*/xtend-gen/.*` exclude pattern (line 11 today)
- `ddk-configuration/checkstyle/**` — same cleanup if any xtend-gen exclusions exist
- Verify no remaining reference to `org.eclipse.xtend` or `xtend-gen` in the tree:
  ```bash
  rg -t xml -t java -t properties 'org\.eclipse\.xtend|xtend-gen'
  ```
</details>

## Known pitfalls from the migration work

Documented here so each reviewer doesn't have to rediscover them:

- **`CoreException` handling** — Xtend silently wraps checked exceptions; Java doesn't. Several files needed explicit `try`/`catch` added (see commit `a5deb4e3c`).
- **PMD `UseCollectionIsEmpty`** — Xtend's `.isEmpty` translates to `.isEmpty()` but chained differently in a few places. Watch for `.size() == 0` patterns that should be `.isEmpty()`.
- **PMD `UnnecessaryBoxing`** — Xtend auto-boxes aggressively. The converter sometimes leaves `Integer.valueOf(i)` where a primitive works.
- **PMD `StringToString`**, **`UnnecessaryCast`**, **`MissingOverride`**, **`LooseCoupling`** — all hit during migration cleanup. Make sure any new violations get caught in slice review, not after merge.
- **`BasicEList` compilation errors in test code** — `XbaseGeneratorFragmentTest` needed a type-parameter fix (commit `3517ba896`). Test code that does generic-heavy collection building needs extra attention.
- **Text blocks vs. `StringBuilder`** — the later commits in this branch converted `StringBuilder`-heavy generators to text blocks / `String.format` (`da8c8d91b`, `71afbe9db`, `a5cb80dec`). Make sure any `StringBuilder` that survives is actually necessary (loop/branch).
- **`val` leaks** — Xtend's `val` always converts to `final Type`, but the converter sometimes uses a pseudo-general type. Enforce explicit types or `var`.
- **Non-parameterized SLF4J logging** — mostly cleaned up project-wide, but migration-era Xtend files had `logger.info("msg" + x)` patterns that should now be `logger.info("msg {}", x)`.

## Rollback plan

If a merged slice turns out to break a downstream consumer, revert the slice's merge commit (`git revert -m 1 <merge-sha>`) and reopen the slice branch for rework. Do **not** revert files individually — each slice is one coherent unit by module.

## Branch hygiene

- This file lives on `feature/xtend-to-java-migration`; update the **status** column on master-merge days by pushing a commit here.
- The slice branches themselves (`migrate/xtend-to-java/<name>`) can be deleted after merge.
- Once slice 12 ships, this branch itself is redundant — keep it around until the roadmap says all slices are ✅ merged, then delete.
