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
    assertNoDiagnostic(ValidValidator.NO_PREDEFINED_RULE);
  }

  @Test
  public final void testCheckCategoryFirstUpperName() {
    assertNoDiagnostic(ValidValidator.CATEGORY_FIRST_UPPER_NAME);
  }

  @Test
  public final void testCheckRuleFirstUpperName() {
    assertNoDiagnostic(ValidValidator.RULE_FIRST_UPPER_NAME);
  }

  @Test
  public final void testCheckQuickFixFirstUpperName() {
    assertNoDiagnostic(ValidValidator.QUICK_FIX_FIRST_UPPER_NAME);
  }

  @Test
  public final void testCheckNativeContextFirstUpperName() {
    assertNoDiagnostic(ValidValidator.NATIVE_CONTEXT_FIRST_UPPER_NAME);
  }

  @Test
  public final void testCheckCategoryEmpty() {
    assertNoDiagnostic(ValidValidator.CATEGORY_EMPTY);
  }

  @Test
  public final void testCheckNativeContextContextFeature() {
    assertNoDiagnostic(ValidValidator.NATIVE_CONTEXT_CONTEXT_FEATURE);
  }

  @Test
  public final void testCheckCategoryLabel() {
    assertNoDiagnostic(ValidValidator.CATEGORY_LABEL);
  }

  @Test
  public final void testCheckRuleLabel() {
    assertNoDiagnostic(ValidValidator.RULE_LABEL);
  }

  @Test
  public final void testCheckQuickFixLabel() {
    assertNoDiagnostic(ValidValidator.QUICK_FIX_LABEL);
  }

  @Test
  public final void testCheckCategoryDescription() {
    assertNoDiagnostic(ValidValidator.CATEGORY_DESCRIPTION);
  }

  @Test
  public final void testCheckCheckDescriptionRule() {
    assertNoDiagnostic(ValidValidator.CHECK_DESCRIPTION_RULE);
  }

  @Test
  public final void testCheckUniqueNativeContextName() {
    assertNoDiagnostic(ValidValidator.UNIQUE_NATIVE_CONTEXT_NAME);
  }

  @Test
  public final void testCheckCategoryDescriptionEndsWithDot() {
    assertNoDiagnostic(ValidValidator.CATEGORY_DESCRIPTION_ENDS_WITH_DOT);
  }

  @Test
  public final void testCheckRuleDescriptionEndsWithDot() {
    assertNoDiagnostic(ValidValidator.RULE_DESCRIPTION_ENDS_WITH_DOT);
  }

  @Test
  public final void testCheckCategoryLabelEndsWithDot() {
    assertNoDiagnostic(ValidValidator.CATEGORY_LABEL_ENDS_WITH_DOT);
  }

  @Test
  public final void testCheckRuleLabelEndsWithDot() {
    assertNoDiagnostic(ValidValidator.RULE_LABEL_ENDS_WITH_DOT);
  }

  @Test
  public final void testCheckQuickFixLabelEndsWithDot() {
    assertNoDiagnostic(ValidValidator.QUICK_FIX_LABEL_ENDS_WITH_DOT);
  }

}
