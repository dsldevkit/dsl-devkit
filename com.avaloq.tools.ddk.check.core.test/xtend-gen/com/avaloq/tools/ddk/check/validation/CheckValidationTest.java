/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.check.validation;

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.avaloq.tools.ddk.check.core.test.util.CheckModelUtil;
import com.avaloq.tools.ddk.check.validation.IssueCodes;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import java.util.ArrayList;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.XbasePackage;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests for various check validations as implemented in the validation classes
 * <ul>
 * <li>com.avaloq.tools.ddk.check.validation.CheckJavaValidator
 * <li>com.avaloq.tools.ddk.check.validation.ClasspathBasedChecks
 * </ul>
 */
@InjectWith(CheckUiInjectorProvider.class)
@RunWith(XtextRunner.class)
@SuppressWarnings("all")
public class CheckValidationTest {
  @Inject
  private ValidationTestHelper helper;
  
  @Inject
  private ParseHelper<CheckCatalog> parser;
  
  @Inject
  private CheckModelUtil modelUtil;
  
  /**
   * Tests checkReturnExpressions(XReturnExpression)
   */
  @Test
  public void testReturnExpressions() {
    try {
      String _modelWithGrammar = this.modelUtil.modelWithGrammar();
      String _plus = (_modelWithGrammar + "def SomeDef for X { return null;");
      final CheckCatalog model = this.parser.parse(_plus);
      this.helper.assertError(model, XbasePackage.Literals.XRETURN_EXPRESSION, IssueCodes.RETURN_IN_IMPL);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkIssuedCheck(XIssueExpression)
   */
  @Test
  public void testIssuedCheck() {
    try {
      String _modelWithGrammar = this.modelUtil.modelWithGrammar();
      String _plus = (_modelWithGrammar + "def SomeDef for X { issue on");
      final CheckCatalog model = this.parser.parse(_plus);
      this.helper.assertError(model, CheckPackage.Literals.XISSUE_EXPRESSION, IssueCodes.ISSUED_CHECK);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkImplicitIssuedCheck(XIssueExpression)
   */
  @Test
  public void testImplicitIssuedCheck() {
    try {
      String _modelWithCheck = this.modelUtil.modelWithCheck("A");
      String _plus = (_modelWithCheck + "for X { issue A");
      final CheckCatalog model = this.parser.parse(_plus);
      this.helper.assertWarning(model, CheckPackage.Literals.XISSUE_EXPRESSION, IssueCodes.IMPLICIT_ISSUED_CHECK);
      String _modelWithCheck_1 = this.modelUtil.modelWithCheck("A");
      String _plus_1 = (_modelWithCheck_1 + "for X { issue A");
      final CheckCatalog model2 = this.parser.parse(_plus_1);
      this.helper.assertWarning(model2, CheckPackage.Literals.XISSUE_EXPRESSION, IssueCodes.IMPLICIT_ISSUED_CHECK);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkFileNamingConventions(CheckCatalog)
   */
  @Test
  public void testFileNamingConventions() {
    try {
      final CheckCatalog model = this.parser.parse("package p catalog c  ");
      this.helper.assertError(model, CheckPackage.Literals.CHECK_CATALOG, IssueCodes.WRONG_FILE);
      this.helper.assertError(model, CheckPackage.Literals.CHECK_CATALOG, IssueCodes.WRONG_PACKAGE);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkPackageName(CheckCatalog). All given package names are valid.
   */
  @Test
  public void testPackageNameIsValid() {
    try {
      CheckCatalog _parse = this.parser.parse("package p ");
      this.helper.assertNoError(_parse, IssueCodes.INVALID_PACKAGE_NAME);
      CheckCatalog _parse_1 = this.parser.parse("package p.q.r ");
      this.helper.assertNoError(_parse_1, IssueCodes.INVALID_PACKAGE_NAME);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkPackageName(CheckCatalog). All given package names are invalid.
   */
  @Test
  public void testPackageNameIsInvalid() {
    try {
      CheckCatalog _parse = this.parser.parse("package P.p.P. ");
      this.helper.assertError(_parse, CheckPackage.Literals.CHECK_CATALOG, IssueCodes.INVALID_PACKAGE_NAME);
      CheckCatalog _parse_1 = this.parser.parse("package P.package.P. ");
      this.helper.assertError(_parse_1, CheckPackage.Literals.CHECK_CATALOG, IssueCodes.INVALID_PACKAGE_NAME);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkContextTypeIsUnique(Check)
   */
  @Test
  @Ignore("Tests do not work because of scoping issues at run-time")
  public void testContextTypeIsUnique() {
    try {
      ArrayList<String> contexts = Lists.<String>newArrayList("for C c {issue}", "for C d {issue}");
      String _modelWithContexts = this.modelUtil.modelWithContexts(contexts);
      CheckCatalog model = this.parser.parse(_modelWithContexts);
      this.helper.assertError(model, CheckPackage.Literals.CONTEXT, IssueCodes.CONTEXT_TYPES_NOT_UNIQUE);
      ArrayList<String> _newArrayList = Lists.<String>newArrayList("for C c {issue}", "for D d {issue}", "for C e {issue}");
      contexts = _newArrayList;
      String _modelWithContexts_1 = this.modelUtil.modelWithContexts(contexts);
      CheckCatalog _parse = this.parser.parse(_modelWithContexts_1);
      model = _parse;
      this.helper.assertError(model, CheckPackage.Literals.CONTEXT, IssueCodes.CONTEXT_TYPES_NOT_UNIQUE);
      ArrayList<String> _newArrayList_1 = Lists.<String>newArrayList("for org.eclipse.emf.ecore.EObject e {issue}", "for E e {issue}");
      contexts = _newArrayList_1;
      String _modelWithContexts_2 = this.modelUtil.modelWithContexts(contexts);
      CheckCatalog _parse_1 = this.parser.parse(_modelWithContexts_2);
      model = _parse_1;
      this.helper.assertNoError(model, IssueCodes.CONTEXT_TYPES_NOT_UNIQUE);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkCircularDependency(CheckCatalog)
   */
  @Test
  public void testCatalogMayNotIncludeItself() {
    try {
      final CheckCatalog model = this.parser.parse("package p catalog c for grammar g with c ");
      this.helper.assertError(model, CheckPackage.Literals.CHECK_CATALOG, IssueCodes.INCLUDED_CATALOGS_WITH_CIRCULAR_DEPENDENCIES);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkGuardsFirstInBlockExpression(Context)
   */
  @Test
  public void testGuardsPrecedeIssues() {
    try {
      String _modelWithContext = this.modelUtil.modelWithContext();
      String _plus = (_modelWithContext + "guard(false) issue");
      CheckCatalog model = this.parser.parse(_plus);
      this.helper.assertNoError(model, IssueCodes.GUARDS_COME_FIRST);
      String _modelWithContext_1 = this.modelUtil.modelWithContext();
      String _plus_1 = (_modelWithContext_1 + "guard(false) guard(true) if(false) {null} issue");
      CheckCatalog _parse = this.parser.parse(_plus_1);
      model = _parse;
      this.helper.assertNoError(model, IssueCodes.GUARDS_COME_FIRST);
      String _modelWithContext_2 = this.modelUtil.modelWithContext();
      String _plus_2 = (_modelWithContext_2 + "guard(false) val x = 1 var y = 2 issue");
      CheckCatalog _parse_1 = this.parser.parse(_plus_2);
      model = _parse_1;
      this.helper.assertNoError(model, IssueCodes.GUARDS_COME_FIRST);
      String _modelWithContext_3 = this.modelUtil.modelWithContext();
      String _plus_3 = (_modelWithContext_3 + "issue guard(false)");
      CheckCatalog _parse_2 = this.parser.parse(_plus_3);
      model = _parse_2;
      this.helper.assertError(model, CheckPackage.Literals.XGUARD_EXPRESSION, IssueCodes.GUARDS_COME_FIRST);
      String _modelWithContext_4 = this.modelUtil.modelWithContext();
      String _plus_4 = (_modelWithContext_4 + "guard(true) issue guard(false)");
      CheckCatalog _parse_3 = this.parser.parse(_plus_4);
      model = _parse_3;
      this.helper.assertError(model, CheckPackage.Literals.XGUARD_EXPRESSION, IssueCodes.GUARDS_COME_FIRST);
      String _modelWithContext_5 = this.modelUtil.modelWithContext();
      String _plus_5 = (_modelWithContext_5 + "guard(true) if(true){issue} guard(true)");
      CheckCatalog _parse_4 = this.parser.parse(_plus_5);
      model = _parse_4;
      this.helper.assertError(model, CheckPackage.Literals.XGUARD_EXPRESSION, IssueCodes.GUARDS_COME_FIRST);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests org.eclipse.xtext.xbase.validation.EarlyExitValidator.checkDeadCode(XBlockExpression)
   */
  @Test
  public void testDeadCode() {
    try {
      String _modelWithContext = this.modelUtil.modelWithContext();
      String _plus = (_modelWithContext + "issue");
      CheckCatalog model = this.parser.parse(_plus);
      this.helper.assertNoError(model, IssueCodes.DEAD_CODE);
      String _modelWithContext_1 = this.modelUtil.modelWithContext();
      String _plus_1 = (_modelWithContext_1 + "if(false) {return} else {return} issue");
      CheckCatalog _parse = this.parser.parse(_plus_1);
      model = _parse;
      this.helper.assertError(model, XbasePackage.Literals.XEXPRESSION, org.eclipse.xtext.xbase.validation.IssueCodes.UNREACHABLE_CODE);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkIssueExpressionExists(Context)
   */
  @Test
  public void testIssueExpressionExists() {
    try {
      String _modelWithContext = this.modelUtil.modelWithContext();
      String _plus = (_modelWithContext + "guard(false)");
      CheckCatalog model = this.parser.parse(_plus);
      this.helper.assertError(model, CheckPackage.Literals.CONTEXT, IssueCodes.MISSING_ISSUE_EXPRESSION);
      String _modelWithContext_1 = this.modelUtil.modelWithContext();
      String _plus_1 = (_modelWithContext_1 + "if (false) { null } else { issue");
      CheckCatalog _parse = this.parser.parse(_plus_1);
      model = _parse;
      this.helper.assertNoError(model, IssueCodes.MISSING_ISSUE_EXPRESSION);
      String _modelWithContext_2 = this.modelUtil.modelWithContext();
      String _plus_2 = (_modelWithContext_2 + "if (false) { null } else { null } ");
      CheckCatalog _parse_1 = this.parser.parse(_plus_2);
      model = _parse_1;
      this.helper.assertError(model, CheckPackage.Literals.CONTEXT, IssueCodes.MISSING_ISSUE_EXPRESSION);
      String _modelWithContext_3 = this.modelUtil.modelWithContext();
      String _plus_3 = (_modelWithContext_3 + "if (false) { null } else { null } issue");
      CheckCatalog _parse_2 = this.parser.parse(_plus_3);
      model = _parse_2;
      this.helper.assertNoError(model, IssueCodes.MISSING_ISSUE_EXPRESSION);
      String _modelWithContext_4 = this.modelUtil.modelWithContext();
      String _plus_4 = (_modelWithContext_4 + "if (false) { issue ");
      CheckCatalog _parse_3 = this.parser.parse(_plus_4);
      model = _parse_3;
      this.helper.assertNoError(model, IssueCodes.MISSING_ISSUE_EXPRESSION);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Test checkCheckName(Check). ID is missing.
   */
  @Test
  public void testCheckIDIsMissing() {
    try {
      String _modelWithCheck = this.modelUtil.modelWithCheck("");
      final CheckCatalog model = this.parser.parse(_modelWithCheck);
      this.helper.assertError(model, CheckPackage.Literals.CHECK, IssueCodes.MISSING_ID_ON_CHECK);
      this.helper.assertNoErrors(model, CheckPackage.Literals.CHECK, IssueCodes.INVALID_CHECK_NAME);
      this.helper.assertNoWarnings(model, CheckPackage.Literals.CHECK, IssueCodes.INVALID_CHECK_NAME);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Test checkCheckName(Check). ID is valid.
   */
  @Test
  public void testCheckIDIsValid() {
    try {
      String _modelWithCheck = this.modelUtil.modelWithCheck("ID");
      final CheckCatalog model = this.parser.parse(_modelWithCheck);
      this.helper.assertNoErrors(model, CheckPackage.Literals.CHECK, IssueCodes.MISSING_ID_ON_CHECK);
      this.helper.assertNoErrors(model, CheckPackage.Literals.CHECK, IssueCodes.INVALID_CHECK_NAME);
      this.helper.assertNoWarnings(model, CheckPackage.Literals.CHECK, IssueCodes.INVALID_CHECK_NAME);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Test checkCheckName(Check). ID is discouraged.
   */
  @Test
  public void testCheckIDIsDiscouraged() {
    try {
      String _modelWithCheck = this.modelUtil.modelWithCheck("iD");
      final CheckCatalog model = this.parser.parse(_modelWithCheck);
      this.helper.assertNoErrors(model, CheckPackage.Literals.CHECK, IssueCodes.MISSING_ID_ON_CHECK);
      this.helper.assertNoErrors(model, CheckPackage.Literals.CHECK, IssueCodes.INVALID_CHECK_NAME);
      this.helper.assertWarning(model, CheckPackage.Literals.CHECK, IssueCodes.INVALID_CHECK_NAME);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Test checkCheckNamesAreUnique(CheckCatalog). IDs are unique.
   */
  @Test
  public void testCheckIDsAreUnique() {
    try {
      String _modelWithCategory = this.modelUtil.modelWithCategory();
      String _emptyCheck = this.modelUtil.emptyCheck("ID1");
      String _plus = (_modelWithCategory + _emptyCheck);
      String _emptyCheck_1 = this.modelUtil.emptyCheck("ID2");
      String _plus_1 = (_plus + _emptyCheck_1);
      final CheckCatalog model = this.parser.parse(_plus_1);
      this.helper.assertNoErrors(model, CheckPackage.Literals.CHECK, IssueCodes.DUPLICATE_CHECK);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Test checkCheckNamesAreUnique(CheckCatalog). IDs are not unique.
   */
  @Test
  public void testCheckIDsAreNotUnique() {
    try {
      String _modelWithCategory = this.modelUtil.modelWithCategory();
      String _emptyCheck = this.modelUtil.emptyCheck("ID1");
      String _plus = (_modelWithCategory + _emptyCheck);
      String _emptyCheck_1 = this.modelUtil.emptyCheck("ID1");
      String _plus_1 = (_plus + _emptyCheck_1);
      final CheckCatalog model = this.parser.parse(_plus_1);
      this.helper.assertError(model, CheckPackage.Literals.CHECK, IssueCodes.DUPLICATE_CHECK);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Test checkCategoryNamesAreUnique(CheckCatalog). IDs are unique.
   */
  @Test
  public void testCategoryIDsAreUnique() {
    try {
      String _modelWithGrammar = this.modelUtil.modelWithGrammar();
      String _emptyCategory = this.modelUtil.emptyCategory("ID1", "Label");
      String _plus = (_modelWithGrammar + _emptyCategory);
      String _emptyCategory_1 = this.modelUtil.emptyCategory("ID2", "Label");
      String _plus_1 = (_plus + _emptyCategory_1);
      final CheckCatalog model = this.parser.parse(_plus_1);
      this.helper.assertNoErrors(model, CheckPackage.Literals.CATEGORY, IssueCodes.DUPLICATE_CATEGORY);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Test checkCategoryNamesAreUnique(CheckCatalog). IDs are not unique.
   */
  @Test
  public void testCategoryIDsAreNotUnique() {
    try {
      String _modelWithCategory = this.modelUtil.modelWithCategory();
      String _emptyCategory = this.modelUtil.emptyCategory("ID1", "Label");
      String _plus = (_modelWithCategory + _emptyCategory);
      String _emptyCategory_1 = this.modelUtil.emptyCategory("ID1", "Label");
      String _plus_1 = (_plus + _emptyCategory_1);
      final CheckCatalog model = this.parser.parse(_plus_1);
      this.helper.assertError(model, CheckPackage.Literals.CATEGORY, IssueCodes.DUPLICATE_CATEGORY);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Test checkCategoryNamesAreUnique(CheckCatalog). IDs are not present; labels are unique.
   */
  @Test
  public void testCategoryLabelsAreUnique() {
    try {
      String _modelWithGrammar = this.modelUtil.modelWithGrammar();
      String _emptyCategory = this.modelUtil.emptyCategory("", "Label1");
      String _plus = (_modelWithGrammar + _emptyCategory);
      String _emptyCategory_1 = this.modelUtil.emptyCategory("", "Label2");
      String _plus_1 = (_plus + _emptyCategory_1);
      final CheckCatalog model = this.parser.parse(_plus_1);
      this.helper.assertNoErrors(model, CheckPackage.Literals.CATEGORY, IssueCodes.DUPLICATE_CATEGORY);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Test checkCategoryNamesAreUnique(CheckCatalog). IDs are not present; labels are not unique.
   */
  @Test
  public void testCategoryLabelsAreNotUnique() {
    try {
      String _modelWithGrammar = this.modelUtil.modelWithGrammar();
      String _emptyCategory = this.modelUtil.emptyCategory("", "Label1");
      String _plus = (_modelWithGrammar + _emptyCategory);
      String _emptyCategory_1 = this.modelUtil.emptyCategory("", "Label1");
      String _plus_1 = (_plus + _emptyCategory_1);
      final CheckCatalog model = this.parser.parse(_plus_1);
      this.helper.assertError(model, CheckPackage.Literals.CATEGORY, IssueCodes.DUPLICATE_CATEGORY);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Test checkCategoryNamesAreUnique(CheckCatalog). IDs are not present; labels are unique but create non-unique names.
   */
  @Test
  public void testCategoryLabelsAreNotUniqueOnceConverted() {
    try {
      String _modelWithGrammar = this.modelUtil.modelWithGrammar();
      String _emptyCategory = this.modelUtil.emptyCategory("", "LabelOne");
      String _plus = (_modelWithGrammar + _emptyCategory);
      String _emptyCategory_1 = this.modelUtil.emptyCategory("", "Label one");
      String _plus_1 = (_plus + _emptyCategory_1);
      final CheckCatalog model = this.parser.parse(_plus_1);
      this.helper.assertError(model, CheckPackage.Literals.CATEGORY, IssueCodes.DUPLICATE_CATEGORY);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkSeverityRangeOrder(Check)
   */
  @Test
  public void testSeverityRangeOrder_1() {
    try {
      String _modelWithSeverityRange = this.modelUtil.modelWithSeverityRange("warning", "error");
      CheckCatalog _parse = this.parser.parse(_modelWithSeverityRange);
      this.helper.assertNoError(_parse, 
        IssueCodes.ILLEGAL_SEVERITY_RANGE_ORDER);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkSeverityRangeOrder(Check)
   */
  @Test
  public void testSeverityRangeOrder_2() {
    try {
      String _modelWithSeverityRange = this.modelUtil.modelWithSeverityRange("ignore", "warning");
      CheckCatalog _parse = this.parser.parse(_modelWithSeverityRange);
      this.helper.assertNoError(_parse, 
        IssueCodes.ILLEGAL_SEVERITY_RANGE_ORDER);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkSeverityRangeOrder(Check)
   */
  @Test
  public void testSeverityRangeOrder_3() {
    try {
      String _modelWithSeverityRange = this.modelUtil.modelWithSeverityRange("info", "info");
      CheckCatalog _parse = this.parser.parse(_modelWithSeverityRange);
      this.helper.assertNoError(_parse, 
        IssueCodes.ILLEGAL_SEVERITY_RANGE_ORDER);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkSeverityRangeOrder(Check)
   */
  @Test
  public void testSeverityRangeOrder_4() {
    try {
      String _modelWithSeverityRange = this.modelUtil.modelWithSeverityRange("info", "ignore");
      CheckCatalog _parse = this.parser.parse(_modelWithSeverityRange);
      this.helper.assertError(_parse, 
        CheckPackage.Literals.SEVERITY_RANGE, IssueCodes.ILLEGAL_SEVERITY_RANGE_ORDER);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkSeverityRangeOrder(Check)
   */
  @Test
  public void testSeverityRangeOrder_5() {
    try {
      String _modelWithSeverityRange = this.modelUtil.modelWithSeverityRange("error", "info");
      CheckCatalog _parse = this.parser.parse(_modelWithSeverityRange);
      this.helper.assertError(_parse, 
        CheckPackage.Literals.SEVERITY_RANGE, IssueCodes.ILLEGAL_SEVERITY_RANGE_ORDER);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkDefaultSeverityInRange(Check)
   */
  @Test
  public void testDefaultSeverityInRange_1() {
    try {
      String _modelWithSeverityRange = this.modelUtil.modelWithSeverityRange("warning", "error", "info");
      CheckCatalog _parse = this.parser.parse(_modelWithSeverityRange);
      this.helper.assertError(_parse, 
        CheckPackage.Literals.CHECK, IssueCodes.DEFAULT_SEVERITY_NOT_IN_RANGE);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkDefaultSeverityInRange(Check)
   */
  @Test
  public void testDefaultSeverityInRange_2() {
    try {
      String _modelWithSeverityRange = this.modelUtil.modelWithSeverityRange("error", "info", "ignore");
      CheckCatalog _parse = this.parser.parse(_modelWithSeverityRange);
      this.helper.assertError(_parse, 
        CheckPackage.Literals.CHECK, IssueCodes.DEFAULT_SEVERITY_NOT_IN_RANGE);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkDefaultSeverityInRange(Check)
   */
  @Test
  public void testDefaultSeverityInRange_3() {
    try {
      String _modelWithSeverityRange = this.modelUtil.modelWithSeverityRange("error", "error", "ignore");
      CheckCatalog _parse = this.parser.parse(_modelWithSeverityRange);
      this.helper.assertError(_parse, 
        CheckPackage.Literals.CHECK, IssueCodes.DEFAULT_SEVERITY_NOT_IN_RANGE);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkDefaultSeverityInRange(Check)
   */
  @Test
  public void testDefaultSeverityInRange_4() {
    try {
      String _modelWithSeverityRange = this.modelUtil.modelWithSeverityRange("error", "error", "error");
      CheckCatalog _parse = this.parser.parse(_modelWithSeverityRange);
      this.helper.assertNoError(_parse, 
        IssueCodes.DEFAULT_SEVERITY_NOT_IN_RANGE);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests checkDefaultSeverityInRange(Check)
   */
  @Test
  public void testDefaultSeverityInRange_5() {
    try {
      String _modelWithSeverityRange = this.modelUtil.modelWithSeverityRange("info", "error", "warning");
      CheckCatalog _parse = this.parser.parse(_modelWithSeverityRange);
      this.helper.assertNoError(_parse, 
        IssueCodes.DEFAULT_SEVERITY_NOT_IN_RANGE);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
