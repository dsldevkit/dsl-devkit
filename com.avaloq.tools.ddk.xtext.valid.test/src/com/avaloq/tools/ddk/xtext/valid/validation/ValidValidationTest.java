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
public class ValidValidationTest extends AbstractValidationTest {

  @Override
  protected ValidTestUtil getXtextTestUtil() {
    return ValidTestUtil.getInstance();
  }

  @Test
  public final void testCheckNoSizeRule() {
    assertDiagnostic(ValidJavaValidator.NO_PREDEFINED_RULE);
  }

  @Test
  public final void testCheckCategoryFirstUpperName() {
    assertDiagnostic(ValidJavaValidator.CATEGORY_FIRST_UPPER_NAME);
  }

  @Test
  public final void testCheckRuleFirstUpperName() {
    assertDiagnostic(ValidJavaValidator.RULE_FIRST_UPPER_NAME);
  }

  @Test
  public final void testCheckQuickFixFirstUpperName() {
    assertDiagnostic(ValidJavaValidator.QUICK_FIX_FIRST_UPPER_NAME);
  }

  @Test
  public final void testCheckNativeContextFirstUpperName() {
    assertDiagnostic(ValidJavaValidator.NATIVE_CONTEXT_FIRST_UPPER_NAME);
  }

  @Test
  public final void testCheckCategoryEmpty() {
    assertDiagnostic(ValidJavaValidator.CATEGORY_EMPTY);
  }

  @Test
  public final void testCheckNativeContextContextFeature() {
    assertDiagnostic(ValidJavaValidator.NATIVE_CONTEXT_CONTEXT_FEATURE);
  }

  @Test
  public final void testCheckCategoryLabel() {
    assertDiagnostic(ValidJavaValidator.CATEGORY_LABEL);
  }

  @Test
  public final void testCheckRuleLabel() {
    assertDiagnostic(ValidJavaValidator.RULE_LABEL);
  }

  @Test
  public final void testCheckQuickFixLabel() {
    assertDiagnostic(ValidJavaValidator.QUICK_FIX_LABEL);
  }

  @Test
  public final void testCheckCategoryDescription() {
    assertDiagnostic(ValidJavaValidator.CATEGORY_DESCRIPTION);
  }

  @Test
  public final void testCheckCheckDescriptionRule() {
    assertDiagnostic(ValidJavaValidator.CHECK_DESCRIPTION_RULE);
  }

  @Test
  public final void testCheckUniqueNativeContextName() {
    assertDiagnostic(ValidJavaValidator.UNIQUE_NATIVE_CONTEXT_NAME);
  }

  @Test
  public final void testCheckCategoryDescriptionEndsWithDot() {
    assertDiagnostic(ValidJavaValidator.CATEGORY_DESCRIPTION_ENDS_WITH_DOT);
  }

  @Test
  public final void testCheckRuleDescriptionEndsWithDot() {
    assertDiagnostic(ValidJavaValidator.RULE_DESCRIPTION_ENDS_WITH_DOT);
  }

  @Test
  public final void testCheckCategoryLabelEndsWithDot() {
    assertDiagnostic(ValidJavaValidator.CATEGORY_LABEL_ENDS_WITH_DOT);
  }

  @Test
  public final void testCheckRuleLabelEndsWithDot() {
    assertDiagnostic(ValidJavaValidator.RULE_LABEL_ENDS_WITH_DOT);
  }

  @Test
  public final void testCheckQuickFixLabelEndsWithDot() {
    assertDiagnostic(ValidJavaValidator.QUICK_FIX_LABEL_ENDS_WITH_DOT);
  }

}


