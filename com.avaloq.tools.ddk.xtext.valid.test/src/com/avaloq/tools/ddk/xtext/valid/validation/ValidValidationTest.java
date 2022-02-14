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
    assertDiagnostic(ValidValidator.NO_PREDEFINED_RULE);
  }

  @Test
  public final void testCheckCategoryFirstUpperName() {
    assertDiagnostic(ValidValidator.CATEGORY_FIRST_UPPER_NAME);
  }

  @Test
  public final void testCheckRuleFirstUpperName() {
    assertDiagnostic(ValidValidator.RULE_FIRST_UPPER_NAME);
  }

  @Test
  public final void testCheckQuickFixFirstUpperName() {
    assertDiagnostic(ValidValidator.QUICK_FIX_FIRST_UPPER_NAME);
  }

  @Test
  public final void testCheckNativeContextFirstUpperName() {
    assertDiagnostic(ValidValidator.NATIVE_CONTEXT_FIRST_UPPER_NAME);
  }

  @Test
  public final void testCheckCategoryEmpty() {
    assertDiagnostic(ValidValidator.CATEGORY_EMPTY);
  }

  @Test
  public final void testCheckNativeContextContextFeature() {
    assertDiagnostic(ValidValidator.NATIVE_CONTEXT_CONTEXT_FEATURE);
  }

  @Test
  public final void testCheckCategoryLabel() {
    assertDiagnostic(ValidValidator.CATEGORY_LABEL);
  }

  @Test
  public final void testCheckRuleLabel() {
    assertDiagnostic(ValidValidator.RULE_LABEL);
  }

  @Test
  public final void testCheckQuickFixLabel() {
    assertDiagnostic(ValidValidator.QUICK_FIX_LABEL);
  }

  @Test
  public final void testCheckCategoryDescription() {
    assertDiagnostic(ValidValidator.CATEGORY_DESCRIPTION);
  }

  @Test
  public final void testCheckCheckDescriptionRule() {
    assertDiagnostic(ValidValidator.CHECK_DESCRIPTION_RULE);
  }

  @Test
  public final void testCheckUniqueNativeContextName() {
    assertDiagnostic(ValidValidator.UNIQUE_NATIVE_CONTEXT_NAME);
  }

  @Test
  public final void testCheckCategoryDescriptionEndsWithDot() {
    assertDiagnostic(ValidValidator.CATEGORY_DESCRIPTION_ENDS_WITH_DOT);
  }

  @Test
  public final void testCheckRuleDescriptionEndsWithDot() {
    assertDiagnostic(ValidValidator.RULE_DESCRIPTION_ENDS_WITH_DOT);
  }

  @Test
  public final void testCheckCategoryLabelEndsWithDot() {
    assertDiagnostic(ValidValidator.CATEGORY_LABEL_ENDS_WITH_DOT);
  }

  @Test
  public final void testCheckRuleLabelEndsWithDot() {
    assertDiagnostic(ValidValidator.RULE_LABEL_ENDS_WITH_DOT);
  }

  @Test
  public final void testCheckQuickFixLabelEndsWithDot() {
    assertDiagnostic(ValidValidator.QUICK_FIX_LABEL_ENDS_WITH_DOT);
  }

}
