# Xtend-to-Java Migration Tracker

## Decisions

- **Target**: Java 21 (pattern matching, switch expressions, text blocks, records)
- **Template strategy**: `StringBuilder` (pure Java, no Xtend runtime dependency)
- **No `var` keyword**: Always use explicit types (`final ExplicitType`, not `final var`)
- **Conversion prompt**: [`docs/xtend-to-java-conversion-prompt.md`](xtend-to-java-conversion-prompt.md)

## Summary

| Metric | Value |
|--------|-------|
| Total Xtend source files | 94 |
| Already migrated (Batch 1–4) | 49 |
| Remaining | 45 |
| Total remaining lines | ~10,404 |
| Modules with remaining Xtend | 14 |

---

## Module Overview

| Module | Files | Lines | Status |
|--------|-------|-------|--------|
| `check.core` | 8 | ~1,848 | **DONE** (Batch 1) |
| `check.core.test` | 8 | 1,508 | 7 done (Batch 2–4), 1 pending |
| `check.test.runtime` | 1 | 22 | **DONE** (Batch 2) |
| `check.test.runtime.tests` | 3 | 202 | **DONE** (Batch 3–4) |
| `check.ui` | 2 | 113 | **DONE** (Batch 3) |
| `check.ui.test` | 1 | 200 | Pending |
| `checkcfg.core` | 4 | 303 | **DONE** (Batch 2–4) |
| `checkcfg.core.test` | 7 | 460 | **DONE** (Batch 2–4) |
| `sample.helloworld.ui.test` | 3 | 203 | **DONE** (Batch 3–4) |
| `xtext.check.generator` | 2 | 113 | **DONE** (Batch 2–3) |
| `xtext.export` | 9 | 1,027 | Pending |
| `xtext.export.generator` | 1 | 86 | **DONE** (Batch 4) |
| `xtext.expression` | 5 | 679 | 2 done (Batch 2), 3 pending |
| `xtext.format` | 6 | 1,623 | 1 done (Batch 2), 5 pending |
| `xtext.format.generator` | 1 | 239 | Pending |
| `xtext.format.ide` | 2 | 31 | **DONE** (Batch 2) |
| `xtext.format.test` | 1 | 40 | **DONE** (Batch 3) |
| `xtext.format.ui` | 1 | 47 | **DONE** (Batch 2) |
| `xtext.generator` | 18 | 3,450 | 2 done (Batch 2), 16 pending |
| `xtext.generator.test` | 1 | 200 | Pending |
| `xtext.scope` | 4 | 852 | Pending |
| `xtext.scope.generator` | 1 | 47 | **DONE** (Batch 4) |
| `xtext.test.core` | 2 | 221 | 1 done (Batch 4), 1 pending |
| `xtext.ui` | 1 | 82 | Pending |
| `xtext.ui.test` | 1 | 265 | Pending |

All module names are prefixed with `com.avaloq.tools.ddk.` (omitted for brevity).

---

## Batch 1 — `check.core` (8 files) — DONE

- [x] `CheckGeneratorConfig.xtend` (20 lines) — Trivial
- [x] `CheckGeneratorNaming.xtend` (~80 lines) — Easy
- [x] `CheckTypeComputer.xtend` (~80 lines) — Easy
- [x] `CheckScopeProvider.xtend` (~100 lines) — Easy
- [x] `CheckGenerator.xtend` (216 lines) — Hard — templates, `@Inject extension`
- [x] `CheckGeneratorExtensions.xtend` (268 lines) — Hard — dispatch, templates
- [x] `CheckFormatter.xtend` (302 lines) — Hard — dispatch
- [x] `CheckJvmModelInferrer.xtend` (647 lines) — Very Hard — dispatch, templates, extensions

---

## Batch 2 — Trivial files ≤50 lines (~14 files) — DONE

Small setup classes, empty modules, simple overrides.

### `check.test.runtime` (1 file)
- [x] `TestLanguageGenerator.xtend` (22 lines) — Trivial — override

### `xtext.format.ide` (2 files)
- [x] `FormatIdeModule.xtend` (11 lines) — Trivial — no complex features
- [x] `FormatIdeSetup.xtend` (20 lines) — Trivial — override

### `xtext.format` (1 file)
- [x] `FormatStandaloneSetup.xtend` (15 lines) — Trivial — extension

### `xtext.expression` (2 files)
- [x] `GeneratorUtilX.xtend` (29 lines) — Trivial — no complex features
- [x] `Naming.xtend` (30 lines) — Trivial — no complex features

### `xtext.check.generator` (1 file)
- [x] `CheckValidatorFragment2.xtend` (31 lines) — Trivial — extension, !==, override

### `checkcfg.core.test` (2 files)
- [x] `CheckCfgTestUtil.xtend` (32 lines) — Trivial — override
- [x] `CheckCfgModelUtil.xtend` (42 lines) — Trivial — templates

### `checkcfg.core` (1 file)
- [x] `CheckCfgJvmModelInferrer.xtend` (45 lines) — Trivial — templates, extension, @Inject

### `xtext.format.test` (1 file)
- [x] `FormatParsingTest.xtend` (40 lines) — Trivial — templates, @Inject

### `xtext.format.ui` (1 file)
- [x] `FormatUiModule.xtend` (47 lines) — Trivial — override

### `xtext.generator` (2 files)
- [x] `BundleVersionStripperFragment.xtend` (47 lines) — Trivial — typeof, @Accessors
- [x] `ProjectConfig.xtend` (48 lines) — Trivial — templates, @Accessors, switch

---

## Batch 3 — Easy files 50–100 lines (~14 files) — DONE

Simple test files, utilities, small production code.

### `check.core.test` (3 files)
- [x] `BugAig830.xtend` (56 lines) — Easy — templates, @Inject
- [x] `CheckTestUtil.xtend` (72 lines) — Easy — ===, !==
- [x] `CheckScopingTest.xtend` (81 lines) — Easy — extension, typeof, @Inject

### `check.test.runtime.tests` (2 files)
- [x] `IssueLabelTest.xtend` (56 lines) — Easy — #{, override
- [x] `CheckConfigurationIsAppliedTest.xtend` (64 lines) — Easy — extension, typeof, @Inject, override

### `check.ui` (2 files)
- [x] `CheckNewProject.xtend` (50 lines) — Easy — templates, !==
- [x] `CheckQuickfixProvider.xtend` (63 lines) — Easy — templates

### `checkcfg.core` (2 files)
- [x] `CheckCfgGenerator.xtend` (53 lines) — Easy — templates, typeof, @Inject, override
- [x] `ConfiguredParameterChecks.xtend` (66 lines) — Easy — templates, ===, !==, ?.

### `checkcfg.core.test` (2 files)
- [x] `CheckCfgConfiguredParameterValidationsTest.xtend` (63 lines) — Easy — templates, extension, override
- [x] `CheckCfgTest.xtend` (63 lines) — Easy — templates, typeof, @Inject

### `sample.helloworld.ui.test` (2 files)
- [x] `IssueLabelTest.xtend` (56 lines) — Easy — #{, override
- [x] `CheckConfigurationIsAppliedTest.xtend` (64 lines) — Easy — extension, typeof, @Inject, override

### `xtext.check.generator` (1 file)
- [x] `CheckQuickfixProviderFragment2.xtend` (82 lines) — Easy — templates, extension, @Inject

---

## Batch 4 — Medium prod + test files 80–140 lines (13 files) — DONE

### `check.core.test` (4 files)
- [x] `ProjectBasedTests.xtend` (88 lines) — Easy — extension, typeof, @Inject, override
- [x] `IssueCodeValueTest.xtend` (104 lines) — Medium — templates, #{, switch
- [x] `CheckApiAccessValidationsTest.xtend` (61 lines) — Easy — templates, @Inject
- [x] `BasicModelTest.xtend` (116 lines) — Medium — extension, typeof, @Inject

### `check.test.runtime.tests` (1 file)
- [x] `CheckExecutionEnvironmentProjectTest.xtend` (82 lines) — Easy — extension, typeof, @Inject, override

### `checkcfg.core` (1 file)
- [x] `PropertiesInferenceHelper.xtend` (139 lines) — Medium — typeof, ===, !==, switch, create

### `checkcfg.core.test` (3 files)
- [x] `CheckCfgContentAssistTest.xtend` (84 lines) — Easy — templates, extension, @Inject, override
- [x] `CheckCfgScopeProviderTest.xtend` (77 lines) — Easy — templates, ===, #[, override
- [x] `CheckCfgSyntaxTest.xtend` (99 lines) — Easy — templates, #[, override

### `sample.helloworld.ui.test` (1 file)
- [x] `CheckExecutionEnvironmentProjectTest.xtend` (83 lines) — Easy — extension, typeof, @Inject, override

### `xtext.export.generator` (1 file)
- [x] `ExportFragment2.xtend` (86 lines) — Medium — templates, extension, !==, @Inject, override

### `xtext.scope.generator` (1 file)
- [x] `ScopingFragment2.xtend` (47 lines) — Trivial — extension, !==, override

### `xtext.test.core` (1 file)
- [x] `Tag.xtend` (23 lines) — Trivial — typeof

---

## Batch 5 — `xtext.export` module (9 files, 1,027 lines)

Code generators with templates and some dispatch methods.

- [ ] `ResourceDescriptionConstantsGenerator.xtend` (54 lines) — Easy — templates, extension, @Inject
- [ ] `ExportFeatureExtensionGenerator.xtend` (77 lines) — Easy — templates, extension, @Inject
- [ ] `ResourceDescriptionManagerGenerator.xtend` (59 lines) — Easy — templates, extension, !==, @Inject
- [ ] `ExportedNamesProviderGenerator.xtend` (96 lines) — Medium — templates, extension, !==, ?., switch
- [ ] `FragmentProviderGenerator.xtend` (94 lines) — Medium — templates, extension, !==, switch
- [ ] `FingerprintComputerGenerator.xtend` (134 lines) — Medium — **dispatch**, templates, extension, @Inject
- [ ] `ExportGenerator.xtend` (136 lines) — Medium — templates, extension, ===, !==, @Inject, override
- [ ] `ExportGeneratorX.xtend` (187 lines) — Hard — **dispatch**, extension, !==, ?., @Inject
- [ ] `ResourceDescriptionStrategyGenerator.xtend` (190 lines) — Hard — templates, extension, ===, !==, @Inject

---

## Batch 6 — `xtext.expression` + `xtext.scope` + remaining small files (~12 files)

### `xtext.expression` (3 files)
- [ ] `ExpressionExtensionsX.xtend` (87 lines) — Medium — **dispatch**, ===
- [ ] `GenModelUtilX.xtend` (160 lines) — Hard — **dispatch**, extension, !==, create
- [ ] `CodeGenerationX.xtend` (373 lines) — Hard — **dispatch**, extension, ===, !==, #[

### `xtext.scope` (4 files)
- [ ] `ScopeGenerator.xtend` (83 lines) — Medium — extension, ===, !==, @Inject, override
- [ ] `ScopeNameProviderGenerator.xtend` (136 lines) — Medium — **dispatch**, templates, extension, switch
- [ ] `ScopeProviderX.xtend` (247 lines) — Hard — **dispatch**, extension, ===, !==, @Inject
- [ ] `ScopeProviderGenerator.xtend` (386 lines) — Hard — **dispatch**, templates, extension, ===, !==, #[, switch

### `xtext.test.core` (1 file)
- [ ] `AbstractResourceDescriptionManagerTest.xtend` (198 lines) — Medium — ===, override, create

### `xtext.ui` (1 file)
- [ ] `TemplateProposalProviderHelper.xtend` (82 lines) — Medium — templates, #[

### `xtext.ui.test` (1 file)
- [ ] `TemplateProposalProviderHelperTest.xtend` (265 lines) — Medium — templates, #[

---

## Batch 7 — `xtext.format` module (10 files, 1,980 lines)

Includes the largest file in the project. Heavy use of dispatch, templates, create methods.

### `xtext.format` (4 files)
- [ ] `FormatRuntimeModule.xtend` (115 lines) — Medium — extension, override
- [ ] `FormatGenerator.xtend` (93 lines) — Medium — **dispatch**, templates, extension, typeof, @Inject, override
- [ ] `FormatScopeProvider.xtend` (258 lines) — Hard — **dispatch**, typeof, ===, !==, create
- [ ] `FormatValidator.xtend` (376 lines) — Hard — ===, !==, override
- [ ] **`FormatJvmModelInferrer.xtend` (766 lines) — Very Hard** — dispatch, templates, extension, typeof, ===, !==, ?., #[, switch, create

### `xtext.format.generator` (1 file)
- [ ] `FormatFragment2.xtend` (239 lines) — Hard — templates, extension, typeof, !==, ?., @Inject, override

### Remaining test/ui files (already covered in other batches)

---

## Batch 8 — `xtext.generator` module (18 files, 3,450 lines)

The largest module. Includes ANTLR grammar generators — the hardest files in the project.

### Simple (4 files)
- [ ] `PredicatesNaming.xtend` (35 lines) — Trivial — extension, @Inject
- [ ] `ModelInferenceFragment2.xtend` (49 lines) — Trivial — extension, !==, override
- [ ] `DefaultFragmentWithOverride.xtend` (54 lines) — Easy — ?., override, @Accessors
- [ ] `BuilderIntegrationFragment2.xtend` (60 lines) — Easy — templates, extension, !==, override

### Medium (5 files)
- [ ] `ResourceFactoryFragment2.xtend` (76 lines) — Medium — templates, extension, !==, ?., @Accessors
- [ ] `CompareFragment2.xtend` (99 lines) — Medium — templates, extension, !==, @Inject
- [ ] `LanguageConstantsFragment2.xtend` (144 lines) — Medium — templates, extension, !==, ?., @Accessors
- [ ] `FormatterFragment2.xtend` (147 lines) — Medium — templates, extension, typeof, !==, ?., @Inject
- [ ] `XbaseGeneratorFragmentTest.xtend` (200 lines) — Medium — extension

### Hard - Builder fragments (2 files)
- [ ] `StandaloneBuilderIntegrationFragment2.xtend` (165 lines) — Hard — templates, extension, @Inject
- [ ] `LspBuilderIntegrationFragment2.xtend` (174 lines) — Hard — templates, extension, @Inject

### Hard - Content assist (1 file)
- [ ] `AnnotationAwareContentAssistFragment2.xtend` (226 lines) — Hard — **dispatch**, templates, extension, !==, ?., @Accessors

### Very Hard - ANTLR generators (4 files)
- [ ] `AbstractAnnotationAwareAntlrGrammarGenerator.xtend` (159 lines) — Hard — templates, extension, @Inject
- [ ] `GrammarRuleAnnotations.xtend` (406 lines) — Very Hard — templates, ===, !==, ?., @Data
- [ ] `AnnotationAwareAntlrContentAssistGrammarGenerator.xtend` (489 lines) — Very Hard — **dispatch**, templates, extension, ===
- [ ] `AnnotationAwareAntlrGrammarGenerator.xtend` (543 lines) — Very Hard — **dispatch**, templates, extension, !==, @Accessors, switch, create
- [ ] `AnnotationAwareXtextAntlrGeneratorFragment2.xtend` (529 lines) — Very Hard — templates, extension, ===, !==, #[, @Accessors, create

---

## Batch 9 — Remaining test files (~4 files)

### `check.core.test` (2 files)
- [ ] `IssueCodeToLabelMapGenerationTest.xtend` (130 lines) — Medium — templates, #[, switch
- [ ] `CheckValidationTest.xtend` (342 lines) — Hard — extension, typeof, create

### `check.core.test` (1 file)
- [ ] `CheckFormattingTest.xtend` (554 lines) — Very Hard — templates, extension, typeof, !==, ?.

### `check.ui.test` (1 file)
- [ ] `CheckQuickfixTest.xtend` (200 lines) — Medium — templates, #[, override

---

## Build Config Cleanup (after all files migrated)

- [ ] Remove `xtend-maven-plugin` from `ddk-parent/pom.xml`
- [ ] Remove `xtend.version` property from `ddk-parent/pom.xml`
- [ ] Remove PMD `excludeRoot` for xtend-gen from `ddk-parent/pom.xml`
- [ ] Remove clean plugin xtend-gen fileset from `ddk-parent/pom.xml`
- [ ] Remove `org.eclipse.xtend.lib` from ~10 MANIFEST.MF files
- [ ] Remove `xtend-gen` source entries from ~31 `.classpath` files
- [ ] Remove `xtend-gen` from ~31 `build.properties` files
- [ ] Update `.gitignore` to remove xtend-gen patterns
- [ ] Delete all `xtend-gen/` directories
- [ ] Final full build + test verification

---

## Verification Protocol

After each batch:

1. **Compile**: `mvn clean compile -f ./ddk-parent/pom.xml --batch-mode` — must pass
2. **Test**: `mvn clean verify -f ./ddk-parent/pom.xml --batch-mode --fail-at-end` — must pass (except known UI test on macOS)
3. **Update** this checklist — mark converted files as done
4. **Commit** the batch

---

## Conversion Rules Quick Reference

| Xtend | Java |
|-------|------|
| `val x = expr` | `final ExplicitType x = expr;` |
| `var x = expr` | `ExplicitType x = expr;` |
| `def method()` | `public ReturnType method()` |
| `override method()` | `@Override public ReturnType method()` |
| `typeof(X)` | `X.class` |
| `===` / `!==` | `==` / `!=` |
| `obj?.method()` | null check or ternary |
| `[x \| body]` | `(x) -> body` |
| `dispatch method(T x)` | `_method(T x)` + dispatcher with `instanceof` |
| `@Inject extension Foo` | `@Inject private Foo foo;` + rewrite call sites |
| `'''template «expr»'''` | `StringBuilder` with `.append()` |
| `#[]` / `#{}` | `List.of()` / `Set.of()` |
| `obj.name` (property) | `obj.getName()` |
| `list += x` | `list.add(x)` |
| `a ?: b` | `a != null ? a : b` |
| `expr as Type` | `(Type) expr` or pattern matching |

Full conversion prompt: [`docs/xtend-to-java-conversion-prompt.md`](xtend-to-java-conversion-prompt.md)

---

## Complexity Legend

| Rating | Criteria |
|--------|----------|
| **Trivial** | ≤30 lines, no complex Xtend features |
| **Easy** | ≤100 lines, basic features (val, override, @Inject, simple templates) |
| **Medium** | 100–200 lines, or uses templates + extension methods + switch |
| **Hard** | 200–400 lines with dispatch/complex templates/extension methods |
| **Very Hard** | 400+ lines with dispatch + templates + extensions + create methods |
