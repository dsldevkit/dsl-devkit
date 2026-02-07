# CI Test Report — Non-UI Test Modules

Results from enabling `tycho-surefire-plugin` (`skip=false`) for 7 non-UI test modules.

CI Run: https://github.com/dsldevkit/dsl-devkit/actions/runs/21770300335

## Summary

| Module | Tests | Failures | Errors | Status |
|--------|------:|:--------:|:------:|:------:|
| `xtext.export.test` | 14 | 0 | 0 | PASS |
| `typesystem.test` | 55 | 0 | 0 | PASS |
| `check.runtime.core.test` | 16 | 0 | 0 | PASS |
| `xtext.test` *(existing)* | — | — | — | SKIPPED |
| `xtext.format.test` | 22 | 6 | 7 | FAIL |
| `xtext.generator.test` | 27 | 0 | 2 | FAIL |
| `checkcfg.core.test` | 10 | 5 | 1 | FAIL |
| `check.core.test` | 65 | 0 | 63 | FAIL |
| **Total** | **209** | **11** | **73** | |

> **Note:** `xtext.test` was SKIPPED by Maven due to earlier module failures (`--fail-at-end` mode). It normally runs 74 tests and passes on `master`.

## Root Cause Analysis

### `check.core.test` — 63 errors (Guice injector needs UI)

Nearly all tests fail with `com.google.inject.CreationException`. Tests use `CheckUiInjectorProvider`, which requires the Eclipse UI workbench. Despite being in a "core" test module, these tests depend on the UI injector.

**Fix:** Run with `useUIHarness=true`, or move tests to `check.ui.test`.

### `xtext.format.test` — 6 failures, 7 errors (mixed: injector + resource loading)

Two distinct failure modes:

1. **`FormatScopingTest` (6 errors):** `NullPointerException` — grammar fields are null because `FormatUiInjectorProvider` (UI injector) fails to initialize without Eclipse workbench.
2. **`FormatValidationTest` (5 failures, 1 error):** Assertion failures and a `ClassCastException` (`XMIResourceImpl` cannot be cast to `XtextResource`) — also caused by incorrect injector initialization.
3. **`FormatParsingTest` (1 failure):** `loadModel` assertion failure.

**Fix:** Run with `useUIHarness=true`.

### `checkcfg.core.test` — 5 failures, 1 error (language registration + linking)

1. **`CheckCfgScopeProviderTest` (1 error):** `java.lang.Exception: Scope has no elements` — the scope provider cannot find any check catalogs.
2. **`CheckCfgSyntaxTest` (3 failures):** Unexpected issues `unknown_language` and `Diagnostic.Linking` — the `com.avaloq.tools.ddk.check.TestLanguage` is not registered in the runtime, so references cannot be resolved.
3. **`CheckCfgConfiguredParameterValidationsTest` (1 failure):** Same `unknown_language` issue.
4. **`CheckCfgTest` (1 failure):** `testValidLanguageOk` — `Expected no issues, but got: ERROR (unknown_language) 'Unknown language'`.

**Fix:** These tests require the Check language runtime to be registered. Likely needs `useUIHarness=true` or explicit language registration in test setup.

### `xtext.generator.test` — 2 errors (test setup bug)

**`XbaseGeneratorFragmentTest` (2 errors):**
- `testUsesXImportSectionWhenNotUsed` and `testUsesXImportSectionWhenUsed` both fail with `NullPointerException: Cannot invoke "org.eclipse.emf.ecore.resource.Resource.getResourceSet()" because the return value of "org.eclipse.xtext.AbstractRule.eResource()" is null`.

**Fix:** This is a genuine test setup bug — the grammar rule is not attached to a resource. Needs investigation of test fixture creation in `XbaseGeneratorFragmentTest`.

## Detailed Failures

### `check.core.test`

All 63 errors share the same root cause:
```
java.lang.RuntimeException: Failed to create injector for com.avaloq.tools.ddk.check.Check
Caused by: com.google.inject.CreationException: Unable to create injector, see the following errors:
```

Affected test classes:
- `BasicModelTest` — 9 errors (all methods)
- `ProjectBasedTests` — 1 error (`ExceptionInInitializerError`)
- `IssueCodeToLabelMapGenerationTest` — 1 error (`ExceptionInInitializerError`)
- `BugDsl27Test` — 1 error (`ExceptionInInitializerError`)
- `CheckScopingTest` — 1 error (`ExceptionInInitializerError`)
- `IssueCodeValueTest` — 1 error (`ExceptionInInitializerError`)
- `CheckFormattingTest` — 3 errors (`testFormattedSource`, `testWSAdded`, `testWSRemoved`)
- `CheckJavaValidatorUtilTest` — 13 errors (all methods)
- Remaining ~34 errors follow the same pattern across other test classes

Note: `BugAig1314Test` (2 tests) PASSES — it does not depend on the UI injector.

### `xtext.format.test`

```
FormatScopingTest.allGrammarsScoped:107 — NullPointerException: Cannot invoke "Grammar.getRules()" because "this.grammarC" is null
FormatScopingTest.ruleSelfScoped:152 — NullPointerException: Cannot invoke "Grammar.getRules()" because "this.grammarA" is null
FormatScopingTest.assignmentScoped:129 — NullPointerException: Cannot invoke "Grammar.getRules()" because "this.grammarA" is null
FormatScopingTest.groupScoped:158 — NullPointerException: Cannot invoke "Grammar.getRules()" because "this.grammarA" is null
FormatScopingTest.ruleCallScoped:141 — NullPointerException: Cannot invoke "Grammar.getRules()" because "this.grammarA" is null
FormatScopingTest.keywordScoped:113 — NullPointerException: Cannot invoke "Grammar.getRules()" because "this.grammarA" is null

FormatValidationTest.extendedGrammarCompatible — ClassCastException: XMIResourceImpl cannot be cast to XtextResource
FormatValidationTest.missingGrammarRuleOverride — assertion failure
FormatValidationTest.grammarRuleOverrideOK — assertion failure
FormatValidationTest.requiredRulesImplemented — assertion failure
FormatValidationTest.extendedGrammarCompatibleOK — assertion failure
FormatValidationTest.testNegativeACF1000 — assertion failure

FormatParsingTest.loadModel — assertion failure
```

### `checkcfg.core.test`

```
CheckCfgScopeProviderTest.testCatalogsAreInCorrectPackage — java.lang.Exception: Scope has no elements

CheckCfgSyntaxTest.testPropertiesOnAllLevels — Unexpected issue: 'unknown_language' + Diagnostic.Linking
CheckCfgSyntaxTest.testSyntax — Unexpected diagnostic: Diagnostic.Linking
CheckCfgSyntaxTest.testSyntaxConfiguredLanguage — Unexpected issue: 'unknown_language' + Diagnostic.Linking

CheckCfgConfiguredParameterValidationsTest.testConfiguredParameterValues — Unexpected issue: 'unknown_language'

CheckCfgTest.testValidLanguageOk — Expected no issues, but got: ERROR (unknown_language) 'Unknown language' on ConfiguredLanguageValidator
```

### `xtext.generator.test`

```
XbaseGeneratorFragmentTest.testUsesXImportSectionWhenNotUsed:162 — NullPointerException: Cannot invoke "Resource.getResourceSet()" because the return value of "AbstractRule.eResource()" is null
XbaseGeneratorFragmentTest.testUsesXImportSectionWhenUsed:180 — NullPointerException: Cannot invoke "Resource.getResourceSet()" because the return value of "AbstractRule.eResource()" is null
```

## Recommendations

1. **Enable immediately** (3 new modules, +85 tests): `xtext.export.test`, `typesystem.test`, `check.runtime.core.test`
2. **Move to UI harness** (3 modules): `check.core.test`, `xtext.format.test`, `checkcfg.core.test` — these are misclassified as non-UI but depend on UI injectors
3. **Fix test setup** (1 module): `xtext.generator.test` — `XbaseGeneratorFragmentTest` has 2 genuine test bugs
