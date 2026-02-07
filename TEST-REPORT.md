# CI Test Report — All Test Modules (Non-UI + UI)

Results from enabling `tycho-surefire-plugin` for all 11 test modules (7 non-UI + 4 UI).

CI Run: https://github.com/dsldevkit/dsl-devkit/actions/runs/21770332592

## Summary

### UI Test Modules (new in this PR)

| Module | Tests | Failures | Errors | Status |
|--------|------:|:--------:|:------:|:------:|
| `xtext.ui.test` | 40 | 0 | 0 | PASS |
| `check.ui.test` | 0 | 0 | 0 | PASS *(no tests found)* |
| `sample.helloworld.ui.test` | 0 | 0 | 0 | PASS *(no tests found)* |
| `test.ui.test` | 4 | 0 | 3 | FAIL |

### Non-UI Test Modules (same as #1263)

| Module | Tests | Failures | Errors | Status |
|--------|------:|:--------:|:------:|:------:|
| `xtext.export.test` | 14 | 0 | 0 | PASS |
| `typesystem.test` | 55 | 0 | 0 | PASS |
| `check.runtime.core.test` | 16 | 0 | 0 | PASS |
| `xtext.test` *(existing)* | 74 | 0 | 0 | PASS |
| `xtext.format.test` | 22 | 6 | 7 | FAIL |
| `xtext.generator.test` | 27 | 0 | 2 | FAIL |
| `checkcfg.core.test` | 10 | 5 | 1 | FAIL |
| `check.core.test` | 65 | 0 | 63 | FAIL |
| **Total** | **327** | **11** | **76** | |

## Root Cause Analysis

### `test.ui.test` — 3 errors (SWTBot widget not found)

All 3 failures are `WidgetNotFoundException` — SWTBot cannot find expected widgets in the headless CI environment.

**`SwtBotRadioTest.testSwtRadioButtonClick`:**
```
org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException:
  Could not find widget matching: (of type 'Button' and with mnemonic 'OK' and with style 'SWT.PUSH')
  at SwtBotRadioTest.java:65
```

**`SwtBotRadioTest.testSWTRadioButtonClick`:**
```
org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException:
  Could not find widget matching: (of type 'Button' and with mnemonic 'OK' and with style 'SWT.PUSH')
  at SwtBotRadioTest.java:76
```

**`DeChKeyboardLayoutTest.testDeChKeyboardLayout`:**
```
org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException:
  Could not find menu bar for shell: Shell with text {Preferences}
  at DeChKeyboardLayoutTest.java:39
```

**Fix:** These tests rely on specific Eclipse dialogs being open. In the CI headless Xvfb environment, the dialogs may not render or the workbench may not fully initialize. Needs investigation of test setup (missing `@BeforeEach` to open the expected dialog/shell).

### `xtext.ui.test` — 40 tests, all pass

All 40 tests pass cleanly:
- `WorkbenchResolutionAdaptorRunTest` — 2 tests
- `WorkbenchResolutionAdaptorTest` — 4 tests
- `TemplateProposalProviderHelperTest` — 24 tests
- `SimpleEnumTemplateVariableResolverTest` — 3 tests
- `ResourceNameTemplateVariableResolverTest` — 7 tests

### `check.core.test` — 63 errors (Guice injector needs UI)

Nearly all tests fail with `com.google.inject.CreationException`. Tests use `CheckUiInjectorProvider`, which requires the Eclipse UI workbench. Despite being in a "core" test module, these tests depend on the UI injector.

**Fix:** Run with `useUIHarness=true`, or move tests to `check.ui.test`.

### `xtext.format.test` — 6 failures, 7 errors (injector + resource loading)

Two distinct failure modes:

1. **`FormatScopingTest` (6 errors):** `NullPointerException` — grammar fields are null because `FormatUiInjectorProvider` (UI injector) fails to initialize without Eclipse workbench.
2. **`FormatValidationTest` (5 failures, 1 error):** Assertion failures and a `ClassCastException` (`XMIResourceImpl` cannot be cast to `XtextResource`).
3. **`FormatParsingTest` (1 failure):** `loadModel` assertion failure.

**Fix:** Run with `useUIHarness=true`.

### `checkcfg.core.test` — 5 failures, 1 error (language registration + linking)

1. **`CheckCfgScopeProviderTest` (1 error):** `Scope has no elements` — cannot find check catalogs.
2. **`CheckCfgSyntaxTest` (3 failures):** `unknown_language` + `Diagnostic.Linking` — the `com.avaloq.tools.ddk.check.TestLanguage` is not registered.
3. **`CheckCfgConfiguredParameterValidationsTest` (1 failure):** Same `unknown_language` issue.
4. **`CheckCfgTest` (1 failure):** `Expected no issues, but got: ERROR (unknown_language) 'Unknown language'`.

**Fix:** Needs `useUIHarness=true` or explicit language registration in test setup.

### `xtext.generator.test` — 2 errors (test setup bug)

**`XbaseGeneratorFragmentTest`:** `testUsesXImportSectionWhenNotUsed` and `testUsesXImportSectionWhenUsed` — `NullPointerException` because the grammar rule is not attached to a resource.

**Fix:** Genuine test setup bug. Needs investigation of fixture creation.

## Detailed Failures

### `test.ui.test`

```
SwtBotRadioTest.testSwtRadioButtonClick:65 — WidgetNotFoundException: Could not find widget matching: (of type 'Button' and with mnemonic 'OK' and with style 'SWT.PUSH')
SwtBotRadioTest.testSWTRadioButtonClick:76 — WidgetNotFoundException: Could not find widget matching: (of type 'Button' and with mnemonic 'OK' and with style 'SWT.PUSH')
DeChKeyboardLayoutTest.testDeChKeyboardLayout:39 — WidgetNotFoundException: Could not find menu bar for shell: Shell with text {Preferences}
```

### `check.core.test`

All 63 errors share the same root cause:
```
java.lang.RuntimeException: Failed to create injector for com.avaloq.tools.ddk.check.Check
Caused by: com.google.inject.CreationException: Unable to create injector, see the following errors:
```

Affected test classes:
- `BasicModelTest` — 9 errors (all methods)
- `ProjectBasedTests` — 1 error
- `IssueCodeToLabelMapGenerationTest` — 1 error
- `BugDsl27Test` — 1 error
- `CheckScopingTest` — 1 error
- `IssueCodeValueTest` — 1 error
- `CheckFormattingTest` — 3 errors
- `CheckJavaValidatorUtilTest` — 13 errors
- Remaining ~34 errors follow the same pattern

Note: `BugAig1314Test` (2 tests) PASSES — it does not depend on the UI injector.

### `xtext.format.test`

```
FormatScopingTest.allGrammarsScoped:107 — NullPointerException: Cannot invoke "Grammar.getRules()" because "this.grammarC" is null
FormatScopingTest.ruleSelfScoped:152 — NullPointerException: "this.grammarA" is null
FormatScopingTest.assignmentScoped:129 — NullPointerException: "this.grammarA" is null
FormatScopingTest.groupScoped:158 — NullPointerException: "this.grammarA" is null
FormatScopingTest.ruleCallScoped:141 — NullPointerException: "this.grammarA" is null
FormatScopingTest.keywordScoped:113 — NullPointerException: "this.grammarA" is null

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

CheckCfgTest.testValidLanguageOk — Expected no issues, but got: ERROR (unknown_language) 'Unknown language'
```

### `xtext.generator.test`

```
XbaseGeneratorFragmentTest.testUsesXImportSectionWhenNotUsed:162 — NullPointerException: Cannot invoke "Resource.getResourceSet()" because the return value of "AbstractRule.eResource()" is null
XbaseGeneratorFragmentTest.testUsesXImportSectionWhenUsed:180 — NullPointerException: Cannot invoke "Resource.getResourceSet()" because the return value of "AbstractRule.eResource()" is null
```

## Recommendations

1. **Enable immediately** (4 modules, +125 tests): `xtext.export.test`, `typesystem.test`, `check.runtime.core.test`, `xtext.ui.test`
2. **Move to UI harness** (3 modules): `check.core.test`, `xtext.format.test`, `checkcfg.core.test` — misclassified as non-UI but depend on UI injectors
3. **Fix SWTBot setup** (1 module): `test.ui.test` — widget/dialog not found in headless CI
4. **Fix test setup bug** (1 module): `xtext.generator.test` — `XbaseGeneratorFragmentTest` has 2 errors
5. **No action needed** (2 modules): `check.ui.test`, `sample.helloworld.ui.test` — no test classes found
