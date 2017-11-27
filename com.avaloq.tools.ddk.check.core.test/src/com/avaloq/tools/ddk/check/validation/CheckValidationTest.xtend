/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check.validation

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider
import com.avaloq.tools.ddk.check.check.CheckCatalog
import com.avaloq.tools.ddk.check.core.test.util.CheckModelUtil
import com.google.common.collect.Lists
import com.google.inject.Inject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.eclipse.xtext.xbase.XbasePackage$Literals
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import com.avaloq.tools.ddk.check.validation.IssueCodes
import com.avaloq.tools.ddk.check.check.CheckPackage

/*
 * Tests for various check validations as implemented in the validation classes
 * <ul>
 * <li>com.avaloq.tools.ddk.check.validation.CheckJavaValidator
 * <li>com.avaloq.tools.ddk.check.validation.ClasspathBasedChecks
 * </ul>
 */
@InjectWith(typeof(CheckUiInjectorProvider))
@RunWith(typeof(XtextRunner))
class CheckValidationTest {

  @Inject
  ValidationTestHelper helper

  @Inject
  ParseHelper<CheckCatalog> parser

  @Inject
  CheckModelUtil modelUtil

  /* Tests checkReturnExpressions(XReturnExpression) */
  @Test
  def void testReturnExpressions() {
    val model = parser.parse(modelUtil.modelWithGrammar + "def SomeDef for X { return null;")
    helper.assertError(model, XbasePackage$Literals::XRETURN_EXPRESSION, IssueCodes::RETURN_IN_IMPL)
  }

  /* Tests checkIssuedCheck(XIssueExpression) */
  @Test
  def void testIssuedCheck() {
    val model = parser.parse(modelUtil.modelWithGrammar + "def SomeDef for X { issue on")
    helper.assertError(model, CheckPackage$Literals::XISSUE_EXPRESSION, IssueCodes::ISSUED_CHECK)
  }

  /* Tests checkImplicitIssuedCheck(XIssueExpression) */
  @Test
  def void testImplicitIssuedCheck() {
    val model = parser.parse(modelUtil.modelWithCheck("A") + "for X { issue A")
    helper.assertWarning(model, CheckPackage$Literals::XISSUE_EXPRESSION, IssueCodes::IMPLICIT_ISSUED_CHECK)
    val model2 = parser.parse(modelUtil.modelWithCheck("A") + "for X { issue A")
    helper.assertWarning(model2, CheckPackage$Literals::XISSUE_EXPRESSION, IssueCodes::IMPLICIT_ISSUED_CHECK)
  }

//TODO check included catalogs

  /* Tests checkFileNamingConventions(CheckCatalog) */
  @Test
  def void testFileNamingConventions() {
    val model = parser.parse("package p catalog c  ")
    helper.assertError(model, CheckPackage$Literals::CHECK_CATALOG, IssueCodes::WRONG_FILE)
    helper.assertError(model, CheckPackage$Literals::CHECK_CATALOG, IssueCodes::WRONG_PACKAGE)
  }

  /* Tests checkPackageName(CheckCatalog). All given package names are valid. */
  @Test
  def void testPackageNameIsValid() {
    helper.assertNoError(parser.parse("package p "), IssueCodes::INVALID_PACKAGE_NAME)
    helper.assertNoError(parser.parse("package p.q.r "), IssueCodes::INVALID_PACKAGE_NAME)
  }

  /* Tests checkPackageName(CheckCatalog). All given package names are invalid. */
  @Test
  def void testPackageNameIsInvalid() {
    // Cannot check package names such as '.p' or 'p..q' because they will cause a parsing error preventing the Java
    // check from being executed
    helper.assertError(parser.parse("package P.p.P. "), CheckPackage$Literals::CHECK_CATALOG, IssueCodes::INVALID_PACKAGE_NAME)
    helper.assertError(parser.parse("package P.package.P. "), CheckPackage$Literals::CHECK_CATALOG, IssueCodes::INVALID_PACKAGE_NAME)
  }

  /* Tests checkContextTypeIsUnique(Check) */
  @Test
  @Ignore("Tests do not work because of scoping issues at run-time")
  def void testContextTypeIsUnique() {
    // should fail
    var contexts = Lists::newArrayList("for C c {issue}", "for C d {issue}")
    var model = parser.parse(modelUtil.modelWithContexts(contexts))
    helper.assertError(model, CheckPackage$Literals::CONTEXT, IssueCodes::CONTEXT_TYPES_NOT_UNIQUE)

    // should fail
    contexts = Lists::newArrayList("for C c {issue}", "for D d {issue}", "for C e {issue}")
    model = parser.parse(modelUtil.modelWithContexts(contexts))
    helper.assertError(model, CheckPackage$Literals::CONTEXT, IssueCodes::CONTEXT_TYPES_NOT_UNIQUE)

    // should not fail
    contexts = Lists::newArrayList("for org.eclipse.emf.ecore.EObject e {issue}", "for E e {issue}")
    model = parser.parse(modelUtil.modelWithContexts(contexts))
    helper.assertNoError(model, IssueCodes::CONTEXT_TYPES_NOT_UNIQUE)
  }

  /* Tests checkCircularDependency(CheckCatalog) */
  @Test
  def void testCatalogMayNotIncludeItself() {
    val model = parser.parse("package p catalog c for grammar g with c ")
    helper.assertError(model, CheckPackage$Literals::CHECK_CATALOG, IssueCodes::INCLUDED_CATALOGS_WITH_CIRCULAR_DEPENDENCIES)
  }

  /* Tests checkGuardsFirstInBlockExpression(Context) */
  @Test
  def void testGuardsPrecedeIssues() {
    // basic case: should not fail
    var model = parser.parse(modelUtil.modelWithContext + "guard(false) issue")
    helper.assertNoError(model, IssueCodes::GUARDS_COME_FIRST)

    // multiple guards: should not fail
    model = parser.parse(modelUtil.modelWithContext + "guard(false) guard(true) if(false) {null} issue")
    helper.assertNoError(model, IssueCodes::GUARDS_COME_FIRST)

    // should not fail
    model = parser.parse(modelUtil.modelWithContext + "guard(false) val x = 1 var y = 2 issue")
    helper.assertNoError(model, IssueCodes::GUARDS_COME_FIRST)

    // guard not first: should fail
    model = parser.parse(modelUtil.modelWithContext + "issue guard(false)")
    helper.assertError(model, CheckPackage$Literals::XGUARD_EXPRESSION, IssueCodes::GUARDS_COME_FIRST)

    // should fail
    model = parser.parse(modelUtil.modelWithContext + "guard(true) issue guard(false)")
    helper.assertError(model, CheckPackage$Literals::XGUARD_EXPRESSION, IssueCodes::GUARDS_COME_FIRST)

    // should fail
    model = parser.parse(modelUtil.modelWithContext + "guard(true) if(true){issue} guard(true)")
    helper.assertError(model, CheckPackage$Literals::XGUARD_EXPRESSION, IssueCodes::GUARDS_COME_FIRST)
  }

  /* Tests org.eclipse.xtext.xbase.validation.EarlyExitValidator.checkDeadCode(XBlockExpression) */
  @Test
  def void testDeadCode() {
    // should not fail
    var model = parser.parse(modelUtil.modelWithContext + "issue")
    helper.assertNoError(model, IssueCodes::DEAD_CODE)

    // should fail
    model = parser.parse(modelUtil.modelWithContext + "if(false) {return} else {return} issue")
    helper.assertError(model, XbasePackage$Literals::XEXPRESSION, org::eclipse::xtext::xbase::validation::IssueCodes::UNREACHABLE_CODE)
  }



  /* Tests checkIssueExpressionExists(Context) */
  @Test
  def void testIssueExpressionExists() {
    var model = parser.parse(modelUtil.modelWithContext + "guard(false)")
    helper.assertError(model, CheckPackage$Literals::CONTEXT, IssueCodes::MISSING_ISSUE_EXPRESSION)

    // should not fail
    model = parser.parse(modelUtil.modelWithContext + "if (false) { null } else { issue")
    helper.assertNoError(model, IssueCodes::MISSING_ISSUE_EXPRESSION)

    // should fail
    model = parser.parse(modelUtil.modelWithContext + "if (false) { null } else { null } ")
    helper.assertError(model, CheckPackage$Literals::CONTEXT, IssueCodes::MISSING_ISSUE_EXPRESSION)

    // should not fail
    model = parser.parse(modelUtil.modelWithContext + "if (false) { null } else { null } issue")
    helper.assertNoError(model, IssueCodes::MISSING_ISSUE_EXPRESSION)

    // should not fail
    model = parser.parse(modelUtil.modelWithContext + "if (false) { issue ")
    helper.assertNoError(model, IssueCodes::MISSING_ISSUE_EXPRESSION)
  }

  /* Test checkCheckName(Check). ID is missing. */
  @Test
  def void testCheckIDIsMissing() {
    val model = parser.parse(modelUtil.modelWithCheck(""))
    helper.assertError(model, CheckPackage$Literals::CHECK, IssueCodes::MISSING_ID_ON_CHECK)
    helper.assertNoErrors(model, CheckPackage$Literals::CHECK, IssueCodes::INVALID_CHECK_NAME)
    helper.assertNoWarnings(model, CheckPackage$Literals::CHECK, IssueCodes::INVALID_CHECK_NAME)
  }

  /* Test checkCheckName(Check). ID is valid. */
  @Test
  def void testCheckIDIsValid() {
    val model = parser.parse(modelUtil.modelWithCheck("ID"))
    helper.assertNoErrors(model, CheckPackage$Literals::CHECK, IssueCodes::MISSING_ID_ON_CHECK)
    helper.assertNoErrors(model, CheckPackage$Literals::CHECK, IssueCodes::INVALID_CHECK_NAME)
    helper.assertNoWarnings(model, CheckPackage$Literals::CHECK, IssueCodes::INVALID_CHECK_NAME)
  }

  // Cannot test with an invalid ID because that would cause a syntax error.
  // But CheckJavaValidatorUtilTest.checkNameIsInvalid() tests the relevant logic in CheckJavaValidatorUtil.checkCheckName(String).

  /* Test checkCheckName(Check). ID is discouraged. */
  @Test
  def void testCheckIDIsDiscouraged() {
    val model = parser.parse(modelUtil.modelWithCheck("iD"))
    helper.assertNoErrors(model, CheckPackage$Literals::CHECK, IssueCodes::MISSING_ID_ON_CHECK)
    helper.assertNoErrors(model, CheckPackage$Literals::CHECK, IssueCodes::INVALID_CHECK_NAME)
    helper.assertWarning(model, CheckPackage$Literals::CHECK, IssueCodes::INVALID_CHECK_NAME)
  }

  /* Test checkCheckNamesAreUnique(CheckCatalog). IDs are unique. */
  @Test
  def void testCheckIDsAreUnique() {
    val model = parser.parse(modelUtil.modelWithCategory + modelUtil.emptyCheck("ID1") + modelUtil.emptyCheck("ID2"))
    helper.assertNoErrors(model, CheckPackage$Literals::CHECK, IssueCodes::DUPLICATE_CHECK)
  }

  /* Test checkCheckNamesAreUnique(CheckCatalog). IDs are not unique. */
  @Test
  def void testCheckIDsAreNotUnique() {
    val model = parser.parse(modelUtil.modelWithCategory + modelUtil.emptyCheck("ID1") + modelUtil.emptyCheck("ID1"))
    helper.assertError(model, CheckPackage$Literals::CHECK, IssueCodes::DUPLICATE_CHECK)
  }

  /* Test checkCategoryNamesAreUnique(CheckCatalog). IDs are unique. */
  @Test
  def void testCategoryIDsAreUnique() {
    val model = parser.parse(modelUtil.modelWithGrammar + modelUtil.emptyCategory("ID1", "Label") + modelUtil.emptyCategory("ID2", "Label"))
    helper.assertNoErrors(model, CheckPackage$Literals::CATEGORY, IssueCodes::DUPLICATE_CATEGORY)
  }

  /* Test checkCategoryNamesAreUnique(CheckCatalog). IDs are not unique. */
  @Test
  def void testCategoryIDsAreNotUnique() {
    val model = parser.parse(modelUtil.modelWithCategory + modelUtil.emptyCategory("ID1", "Label") + modelUtil.emptyCategory("ID1", "Label"))
    helper.assertError(model, CheckPackage$Literals::CATEGORY, IssueCodes::DUPLICATE_CATEGORY)
  }

  /* Test checkCategoryNamesAreUnique(CheckCatalog). IDs are not present; labels are unique. */
  @Test
  def void testCategoryLabelsAreUnique() {
    val model = parser.parse(modelUtil.modelWithGrammar + modelUtil.emptyCategory("", "Label1") + modelUtil.emptyCategory("", "Label2"))
    helper.assertNoErrors(model, CheckPackage$Literals::CATEGORY, IssueCodes::DUPLICATE_CATEGORY)
  }

  /* Test checkCategoryNamesAreUnique(CheckCatalog). IDs are not present; labels are not unique. */
  @Test
  def void testCategoryLabelsAreNotUnique() {
    val model = parser.parse(modelUtil.modelWithGrammar + modelUtil.emptyCategory("", "Label1") + modelUtil.emptyCategory("", "Label1"))
    helper.assertError(model, CheckPackage$Literals::CATEGORY, IssueCodes::DUPLICATE_CATEGORY)
  }

  /* Test checkCategoryNamesAreUnique(CheckCatalog). IDs are not present; labels are unique but create non-unique names. */
  @Test
  def void testCategoryLabelsAreNotUniqueOnceConverted() {
    val model = parser.parse(modelUtil.modelWithGrammar + modelUtil.emptyCategory("", "LabelOne") + modelUtil.emptyCategory("", "Label one"))
    helper.assertError(model, CheckPackage$Literals::CATEGORY, IssueCodes::DUPLICATE_CATEGORY)
  }

  /* Tests checkSeverityRangeOrder(Check) */
  @Test
  def void testSeverityRangeOrder_1() {
    helper.assertNoError(parser.parse(modelUtil.modelWithSeverityRange('warning', 'error')),
      IssueCodes::ILLEGAL_SEVERITY_RANGE_ORDER
    )
  }

  /* Tests checkSeverityRangeOrder(Check) */
  @Test
  def void testSeverityRangeOrder_2() {
    helper.assertNoError(parser.parse(modelUtil.modelWithSeverityRange('ignore', 'warning')),
      IssueCodes::ILLEGAL_SEVERITY_RANGE_ORDER
    )
  }

  /* Tests checkSeverityRangeOrder(Check) */
  @Test
  def void testSeverityRangeOrder_3() {
    helper.assertNoError(parser.parse(modelUtil.modelWithSeverityRange('info', 'info')),
      IssueCodes::ILLEGAL_SEVERITY_RANGE_ORDER
    )
  }

  /* Tests checkSeverityRangeOrder(Check) */
  @Test
  def void testSeverityRangeOrder_4() {
    helper.assertError(parser.parse(modelUtil.modelWithSeverityRange('info', 'ignore')),
      CheckPackage$Literals::SEVERITY_RANGE, IssueCodes::ILLEGAL_SEVERITY_RANGE_ORDER
    )
  }

  /* Tests checkSeverityRangeOrder(Check) */
  @Test
  def void testSeverityRangeOrder_5() {
    helper.assertError(parser.parse(modelUtil.modelWithSeverityRange('error', 'info')),
      CheckPackage$Literals::SEVERITY_RANGE, IssueCodes::ILLEGAL_SEVERITY_RANGE_ORDER
    )
  }

  /* Tests checkDefaultSeverityInRange(Check) */
  @Test
  def void testDefaultSeverityInRange_1() {
    helper.assertError(parser.parse(modelUtil.modelWithSeverityRange('warning', 'error', 'info')),
      CheckPackage$Literals::CHECK, IssueCodes::DEFAULT_SEVERITY_NOT_IN_RANGE
    )
  }

  /* Tests checkDefaultSeverityInRange(Check) */
  @Test
  def void testDefaultSeverityInRange_2() {
    helper.assertError(parser.parse(modelUtil.modelWithSeverityRange('error', 'info', 'ignore')),
      CheckPackage$Literals::CHECK, IssueCodes::DEFAULT_SEVERITY_NOT_IN_RANGE
    )
  }

  /* Tests checkDefaultSeverityInRange(Check) */
  @Test
  def void testDefaultSeverityInRange_3() {
    helper.assertError(parser.parse(modelUtil.modelWithSeverityRange('error', 'error', 'ignore')),
      CheckPackage$Literals::CHECK, IssueCodes::DEFAULT_SEVERITY_NOT_IN_RANGE
    )
  }

  /* Tests checkDefaultSeverityInRange(Check) */
  @Test
  def void testDefaultSeverityInRange_4() {
    helper.assertNoError(parser.parse(modelUtil.modelWithSeverityRange('error', 'error', 'error')),
      IssueCodes::DEFAULT_SEVERITY_NOT_IN_RANGE
    )
  }

  /* Tests checkDefaultSeverityInRange(Check) */
  @Test
  def void testDefaultSeverityInRange_5() {
    helper.assertNoError(parser.parse(modelUtil.modelWithSeverityRange('info', 'error', 'warning')),
      IssueCodes::DEFAULT_SEVERITY_NOT_IN_RANGE
    )
  }

}
