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
package com.avaloq.tools.ddk.xtext.valid.validation;

import org.junit.Test;

import com.avaloq.tools.ddk.xtext.test.valid.util.ValidTestUtil;
import com.avaloq.tools.ddk.xtext.test.validation.AbstractValidationTest;


/**
 * Tests validation of Valid sources.
 */
public class ValidValidationOkTest extends AbstractValidationTest {

  @Override
  protected ValidTestUtil getXtextTestUtil() {
    return ValidTestUtil.getInstance();
  }

  @Test
  public final void testCheckAll() {
    assertNoDiagnostics();
  }

  @Test
  public final void testCheckNoSizeRule() {
    assertNoDiagnostic(ValidJavaValidator.NO_PREDEFINED_RULE);
  }

  @Test
  public final void testCheckCategoryFirstUpperName() {
    assertNoDiagnostic(ValidJavaValidator.CATEGORY_FIRST_UPPER_NAME);
  }

  @Test
  public final void testCheckRuleFirstUpperName() {
    assertNoDiagnostic(ValidJavaValidator.RULE_FIRST_UPPER_NAME);
  }

  @Test
  public final void testCheckQuickFixFirstUpperName() {
    assertNoDiagnostic(ValidJavaValidator.QUICK_FIX_FIRST_UPPER_NAME);
  }

  @Test
  public final void testCheckNativeContextFirstUpperName() {
    assertNoDiagnostic(ValidJavaValidator.NATIVE_CONTEXT_FIRST_UPPER_NAME);
  }

  @Test
  public final void testCheckCategoryEmpty() {
    assertNoDiagnostic(ValidJavaValidator.CATEGORY_EMPTY);
  }

  @Test
  public final void testCheckNativeContextContextFeature() {
    assertNoDiagnostic(ValidJavaValidator.NATIVE_CONTEXT_CONTEXT_FEATURE);
  }

  @Test
  public final void testCheckCategoryLabel() {
    assertNoDiagnostic(ValidJavaValidator.CATEGORY_LABEL);
  }

  @Test
  public final void testCheckRuleLabel() {
    assertNoDiagnostic(ValidJavaValidator.RULE_LABEL);
  }

  @Test
  public final void testCheckQuickFixLabel() {
    assertNoDiagnostic(ValidJavaValidator.QUICK_FIX_LABEL);
  }

  @Test
  public final void testCheckCategoryDescription() {
    assertNoDiagnostic(ValidJavaValidator.CATEGORY_DESCRIPTION);
  }

  @Test
  public final void testCheckCheckDescriptionRule() {
    assertNoDiagnostic(ValidJavaValidator.CHECK_DESCRIPTION_RULE);
  }

  @Test
  public final void testCheckUniqueNativeContextName() {
    assertNoDiagnostic(ValidJavaValidator.UNIQUE_NATIVE_CONTEXT_NAME);
  }

  @Test
  public final void testCheckCategoryDescriptionEndsWithDot() {
    assertNoDiagnostic(ValidJavaValidator.CATEGORY_DESCRIPTION_ENDS_WITH_DOT);
  }

  @Test
  public final void testCheckRuleDescriptionEndsWithDot() {
    assertNoDiagnostic(ValidJavaValidator.RULE_DESCRIPTION_ENDS_WITH_DOT);
  }

  @Test
  public final void testCheckCategoryLabelEndsWithDot() {
    assertNoDiagnostic(ValidJavaValidator.CATEGORY_LABEL_ENDS_WITH_DOT);
  }

  @Test
  public final void testCheckRuleLabelEndsWithDot() {
    assertNoDiagnostic(ValidJavaValidator.RULE_LABEL_ENDS_WITH_DOT);
  }

  @Test
  public final void testCheckQuickFixLabelEndsWithDot() {
    assertNoDiagnostic(ValidJavaValidator.QUICK_FIX_LABEL_ENDS_WITH_DOT);
  }

}


