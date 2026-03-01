/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check.validation;

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.avaloq.tools.ddk.check.core.test.util.CheckModelUtil;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

import java.util.ArrayList;

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.XbasePackage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/*
 * Tests for various check validations as implemented in the validation classes
 * <ul>
 * <li>com.avaloq.tools.ddk.check.validation.CheckJavaValidator
 * <li>com.avaloq.tools.ddk.check.validation.ClasspathBasedChecks
 * </ul>
 */
@InjectWith(CheckUiInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
@SuppressWarnings("nls")
public class CheckValidationTest {

  @Inject
  private ValidationTestHelper helper;

  @Inject
  private ParseHelper<CheckCatalog> parser;

  @Inject
  private CheckModelUtil modelUtil;

  /* Tests checkReturnExpressions(XReturnExpression) */
  @Test
  public void testReturnExpressions() throws Exception {
    final CheckCatalog model = parser.parse(modelUtil.modelWithGrammar() + "def SomeDef for X { return null;");
    helper.assertError(model, XbasePackage.Literals.XRETURN_EXPRESSION, IssueCodes.RETURN_IN_IMPL);
  }

  /* Tests checkIssuedCheck(XIssueExpression) */
  @Test
  public void testIssuedCheck() throws Exception {
    final CheckCatalog model = parser.parse(modelUtil.modelWithGrammar() + "def SomeDef for X { issue on");
    helper.assertError(model, CheckPackage.Literals.XISSUE_EXPRESSION, IssueCodes.ISSUED_CHECK);
  }

  /* Tests checkImplicitIssuedCheck(XIssueExpression) */
  @Test
  public void testImplicitIssuedCheck() throws Exception {
    final CheckCatalog model = parser.parse(modelUtil.modelWithCheck("A") + "for X { issue A");
    helper.assertWarning(model, CheckPackage.Literals.XISSUE_EXPRESSION, IssueCodes.IMPLICIT_ISSUED_CHECK);
    final CheckCatalog model2 = parser.parse(modelUtil.modelWithCheck("A") + "for X { issue A");
    helper.assertWarning(model2, CheckPackage.Literals.XISSUE_EXPRESSION, IssueCodes.IMPLICIT_ISSUED_CHECK);
  }

  // TODO check included catalogs

  /* Tests checkFileNamingConventions(CheckCatalog) */
  @Test
  public void testFileNamingConventions() throws Exception {
    final CheckCatalog model = parser.parse("package p catalog c  ");
    helper.assertError(model, CheckPackage.Literals.CHECK_CATALOG, IssueCodes.WRONG_FILE);
    helper.assertError(model, CheckPackage.Literals.CHECK_CATALOG, IssueCodes.WRONG_PACKAGE);
  }

  /* Tests checkPackageName(CheckCatalog). All given package names are valid. */
  @Test
  public void testPackageNameIsValid() throws Exception {
    helper.assertNoError(parser.parse("package p "), IssueCodes.INVALID_PACKAGE_NAME);
    helper.assertNoError(parser.parse("package p.q.r "), IssueCodes.INVALID_PACKAGE_NAME);
  }

  /* Tests checkPackageName(CheckCatalog). All given package names are invalid. */
  @Test
  public void testPackageNameIsInvalid() throws Exception {
    // Cannot check package names such as '.p' or 'p..q' because they will cause a parsing error preventing the Java
    // check from being executed
    helper.assertError(parser.parse("package P.p.P. "), CheckPackage.Literals.CHECK_CATALOG, IssueCodes.INVALID_PACKAGE_NAME);
    helper.assertError(parser.parse("package P.package.P. "), CheckPackage.Literals.CHECK_CATALOG, IssueCodes.INVALID_PACKAGE_NAME);
  }

  /* Tests checkContextTypeIsUnique(Check) */
  @Test
  @Disabled("Tests do not work because of scoping issues at run-time")
  public void testContextTypeIsUnique() throws Exception {
    // should fail
    ArrayList<String> contexts = Lists.newArrayList("for C c {issue}", "for C d {issue}");
    CheckCatalog model = parser.parse(modelUtil.modelWithContexts(contexts));
    helper.assertError(model, CheckPackage.Literals.CONTEXT, IssueCodes.CONTEXT_TYPES_NOT_UNIQUE);

    // should fail
    contexts = Lists.newArrayList("for C c {issue}", "for D d {issue}", "for C e {issue}");
    model = parser.parse(modelUtil.modelWithContexts(contexts));
    helper.assertError(model, CheckPackage.Literals.CONTEXT, IssueCodes.CONTEXT_TYPES_NOT_UNIQUE);

    // should not fail
    contexts = Lists.newArrayList("for org.eclipse.emf.ecore.EObject e {issue}", "for E e {issue}");
    model = parser.parse(modelUtil.modelWithContexts(contexts));
    helper.assertNoError(model, IssueCodes.CONTEXT_TYPES_NOT_UNIQUE);
  }

  /* Tests checkGuardsFirstInBlockExpression(Context) */
  @Test
  public void testGuardsPrecedeIssues() throws Exception {
    // basic case: should not fail
    CheckCatalog model = parser.parse(modelUtil.modelWithContext() + "guard(false) issue");
    helper.assertNoError(model, IssueCodes.GUARDS_COME_FIRST);

    // multiple guards: should not fail
    model = parser.parse(modelUtil.modelWithContext() + "guard(false) guard(true) if(false) {null} issue");
    helper.assertNoError(model, IssueCodes.GUARDS_COME_FIRST);

    // should not fail
    model = parser.parse(modelUtil.modelWithContext() + "guard(false) val x = 1 var y = 2 issue");
    helper.assertNoError(model, IssueCodes.GUARDS_COME_FIRST);

    // guard not first: should fail
    model = parser.parse(modelUtil.modelWithContext() + "issue guard(false)");
    helper.assertError(model, CheckPackage.Literals.XGUARD_EXPRESSION, IssueCodes.GUARDS_COME_FIRST);

    // should fail
    model = parser.parse(modelUtil.modelWithContext() + "guard(true) issue guard(false)");
    helper.assertError(model, CheckPackage.Literals.XGUARD_EXPRESSION, IssueCodes.GUARDS_COME_FIRST);

    // should fail
    model = parser.parse(modelUtil.modelWithContext() + "guard(true) if(true){issue} guard(true)");
    helper.assertError(model, CheckPackage.Literals.XGUARD_EXPRESSION, IssueCodes.GUARDS_COME_FIRST);
  }

  /* Tests org.eclipse.xtext.xbase.validation.EarlyExitValidator.checkDeadCode(XBlockExpression) */
  @Test
  public void testDeadCode() throws Exception {
    // should not fail
    CheckCatalog model = parser.parse(modelUtil.modelWithContext() + "issue");
    helper.assertNoError(model, IssueCodes.DEAD_CODE);

    // should fail
    model = parser.parse(modelUtil.modelWithContext() + "if(false) {return} else {return} issue");
    helper.assertError(model, XbasePackage.Literals.XEXPRESSION, org.eclipse.xtext.xbase.validation.IssueCodes.UNREACHABLE_CODE);
  }

  /* Tests checkIssueExpressionExists(Context) */
  @Test
  public void testIssueExpressionExists() throws Exception {
    CheckCatalog model = parser.parse(modelUtil.modelWithContext() + "guard(false)");
    helper.assertError(model, CheckPackage.Literals.CONTEXT, IssueCodes.MISSING_ISSUE_EXPRESSION);

    // should not fail
    model = parser.parse(modelUtil.modelWithContext() + "if (false) { null } else { issue");
    helper.assertNoError(model, IssueCodes.MISSING_ISSUE_EXPRESSION);

    // should fail
    model = parser.parse(modelUtil.modelWithContext() + "if (false) { null } else { null } ");
    helper.assertError(model, CheckPackage.Literals.CONTEXT, IssueCodes.MISSING_ISSUE_EXPRESSION);

    // should not fail
    model = parser.parse(modelUtil.modelWithContext() + "if (false) { null } else { null } issue");
    helper.assertNoError(model, IssueCodes.MISSING_ISSUE_EXPRESSION);

    // should not fail
    model = parser.parse(modelUtil.modelWithContext() + "if (false) { issue ");
    helper.assertNoError(model, IssueCodes.MISSING_ISSUE_EXPRESSION);
  }

  /* Test checkCheckName(Check). ID is missing. */
  @Test
  public void testCheckIDIsMissing() throws Exception {
    final CheckCatalog model = parser.parse(modelUtil.modelWithCheck(""));
    helper.assertError(model, CheckPackage.Literals.CHECK, IssueCodes.MISSING_ID_ON_CHECK);
    helper.assertNoErrors(model, CheckPackage.Literals.CHECK, IssueCodes.INVALID_CHECK_NAME);
    helper.assertNoWarnings(model, CheckPackage.Literals.CHECK, IssueCodes.INVALID_CHECK_NAME);
  }

  /* Test checkCheckName(Check). ID is valid. */
  @Test
  public void testCheckIDIsValid() throws Exception {
    final CheckCatalog model = parser.parse(modelUtil.modelWithCheck("ID"));
    helper.assertNoErrors(model, CheckPackage.Literals.CHECK, IssueCodes.MISSING_ID_ON_CHECK);
    helper.assertNoErrors(model, CheckPackage.Literals.CHECK, IssueCodes.INVALID_CHECK_NAME);
    helper.assertNoWarnings(model, CheckPackage.Literals.CHECK, IssueCodes.INVALID_CHECK_NAME);
  }

  // Cannot test with an invalid ID because that would cause a syntax error.
  // But CheckJavaValidatorUtilTest.checkNameIsInvalid() tests the relevant logic in CheckJavaValidatorUtil.checkCheckName(String).

  /* Test checkCheckName(Check). ID is discouraged. */
  @Test
  public void testCheckIDIsDiscouraged() throws Exception {
    final CheckCatalog model = parser.parse(modelUtil.modelWithCheck("iD"));
    helper.assertNoErrors(model, CheckPackage.Literals.CHECK, IssueCodes.MISSING_ID_ON_CHECK);
    helper.assertNoErrors(model, CheckPackage.Literals.CHECK, IssueCodes.INVALID_CHECK_NAME);
    helper.assertWarning(model, CheckPackage.Literals.CHECK, IssueCodes.INVALID_CHECK_NAME);
  }

  /* Test checkCheckNamesAreUnique(CheckCatalog). IDs are unique. */
  @Test
  public void testCheckIDsAreUnique() throws Exception {
    final CheckCatalog model = parser.parse(modelUtil.modelWithCategory() + modelUtil.emptyCheck("ID1") + modelUtil.emptyCheck("ID2"));
    helper.assertNoErrors(model, CheckPackage.Literals.CHECK, IssueCodes.DUPLICATE_CHECK);
  }

  /* Test checkCheckNamesAreUnique(CheckCatalog). IDs are not unique. */
  @Test
  public void testCheckIDsAreNotUnique() throws Exception {
    final CheckCatalog model = parser.parse(modelUtil.modelWithCategory() + modelUtil.emptyCheck("ID1") + modelUtil.emptyCheck("ID1"));
    helper.assertError(model, CheckPackage.Literals.CHECK, IssueCodes.DUPLICATE_CHECK);
  }

  /* Test checkCategoryNamesAreUnique(CheckCatalog). IDs are unique. */
  @Test
  public void testCategoryIDsAreUnique() throws Exception {
    final CheckCatalog model = parser.parse(modelUtil.modelWithGrammar() + modelUtil.emptyCategory("ID1", "Label") + modelUtil.emptyCategory("ID2", "Label"));
    helper.assertNoErrors(model, CheckPackage.Literals.CATEGORY, IssueCodes.DUPLICATE_CATEGORY);
  }

  /* Test checkCategoryNamesAreUnique(CheckCatalog). IDs are not unique. */
  @Test
  public void testCategoryIDsAreNotUnique() throws Exception {
    final CheckCatalog model = parser.parse(modelUtil.modelWithCategory() + modelUtil.emptyCategory("ID1", "Label") + modelUtil.emptyCategory("ID1", "Label"));
    helper.assertError(model, CheckPackage.Literals.CATEGORY, IssueCodes.DUPLICATE_CATEGORY);
  }

  /* Test checkCategoryNamesAreUnique(CheckCatalog). IDs are not present; labels are unique. */
  @Test
  public void testCategoryLabelsAreUnique() throws Exception {
    final CheckCatalog model = parser.parse(modelUtil.modelWithGrammar() + modelUtil.emptyCategory("", "Label1") + modelUtil.emptyCategory("", "Label2"));
    helper.assertNoErrors(model, CheckPackage.Literals.CATEGORY, IssueCodes.DUPLICATE_CATEGORY);
  }

  /* Test checkCategoryNamesAreUnique(CheckCatalog). IDs are not present; labels are not unique. */
  @Test
  public void testCategoryLabelsAreNotUnique() throws Exception {
    final CheckCatalog model = parser.parse(modelUtil.modelWithGrammar() + modelUtil.emptyCategory("", "Label1") + modelUtil.emptyCategory("", "Label1"));
    helper.assertError(model, CheckPackage.Literals.CATEGORY, IssueCodes.DUPLICATE_CATEGORY);
  }

  /* Test checkCategoryNamesAreUnique(CheckCatalog). IDs are not present; labels are unique but create non-unique names. */
  @Test
  public void testCategoryLabelsAreNotUniqueOnceConverted() throws Exception {
    final CheckCatalog model = parser.parse(modelUtil.modelWithGrammar() + modelUtil.emptyCategory("", "LabelOne") + modelUtil.emptyCategory("", "Label one"));
    helper.assertError(model, CheckPackage.Literals.CATEGORY, IssueCodes.DUPLICATE_CATEGORY);
  }

  /* Tests checkSeverityRangeOrder(Check) */
  @Test
  public void testSeverityRangeOrder_1() throws Exception {
    helper.assertNoError(parser.parse(modelUtil.modelWithSeverityRange("warning", "error")),
      IssueCodes.ILLEGAL_SEVERITY_RANGE_ORDER);
  }

  /* Tests checkSeverityRangeOrder(Check) */
  @Test
  public void testSeverityRangeOrder_2() throws Exception {
    helper.assertNoError(parser.parse(modelUtil.modelWithSeverityRange("ignore", "warning")),
      IssueCodes.ILLEGAL_SEVERITY_RANGE_ORDER);
  }

  /* Tests checkSeverityRangeOrder(Check) */
  @Test
  public void testSeverityRangeOrder_3() throws Exception {
    helper.assertNoError(parser.parse(modelUtil.modelWithSeverityRange("info", "info")),
      IssueCodes.ILLEGAL_SEVERITY_RANGE_ORDER);
  }

  /* Tests checkSeverityRangeOrder(Check) */
  @Test
  public void testSeverityRangeOrder_4() throws Exception {
    helper.assertError(parser.parse(modelUtil.modelWithSeverityRange("info", "ignore")),
      CheckPackage.Literals.SEVERITY_RANGE, IssueCodes.ILLEGAL_SEVERITY_RANGE_ORDER);
  }

  /* Tests checkSeverityRangeOrder(Check) */
  @Test
  public void testSeverityRangeOrder_5() throws Exception {
    helper.assertError(parser.parse(modelUtil.modelWithSeverityRange("error", "info")),
      CheckPackage.Literals.SEVERITY_RANGE, IssueCodes.ILLEGAL_SEVERITY_RANGE_ORDER);
  }

  /* Tests checkDefaultSeverityInRange(Check) */
  @Test
  public void testDefaultSeverityInRange_1() throws Exception {
    helper.assertError(parser.parse(modelUtil.modelWithSeverityRange("warning", "error", "info")),
      CheckPackage.Literals.CHECK, IssueCodes.DEFAULT_SEVERITY_NOT_IN_RANGE);
  }

  /* Tests checkDefaultSeverityInRange(Check) */
  @Test
  public void testDefaultSeverityInRange_2() throws Exception {
    helper.assertError(parser.parse(modelUtil.modelWithSeverityRange("error", "info", "ignore")),
      CheckPackage.Literals.CHECK, IssueCodes.DEFAULT_SEVERITY_NOT_IN_RANGE);
  }

  /* Tests checkDefaultSeverityInRange(Check) */
  @Test
  public void testDefaultSeverityInRange_3() throws Exception {
    helper.assertError(parser.parse(modelUtil.modelWithSeverityRange("error", "error", "ignore")),
      CheckPackage.Literals.CHECK, IssueCodes.DEFAULT_SEVERITY_NOT_IN_RANGE);
  }

  /* Tests checkDefaultSeverityInRange(Check) */
  @Test
  public void testDefaultSeverityInRange_4() throws Exception {
    helper.assertNoError(parser.parse(modelUtil.modelWithSeverityRange("error", "error", "error")),
      IssueCodes.DEFAULT_SEVERITY_NOT_IN_RANGE);
  }

  /* Tests checkDefaultSeverityInRange(Check) */
  @Test
  public void testDefaultSeverityInRange_5() throws Exception {
    helper.assertNoError(parser.parse(modelUtil.modelWithSeverityRange("info", "error", "warning")),
      IssueCodes.DEFAULT_SEVERITY_NOT_IN_RANGE);
  }

}
