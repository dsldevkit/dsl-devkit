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
package com.avaloq.tools.ddk.xtext.format.validation;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.test.format.util.FormatTestUtil;
import com.avaloq.tools.ddk.xtext.test.validation.AbstractValidationTest;
import com.google.common.collect.Lists;


public class FormatValidationTest extends AbstractValidationTest {
  private static final String INT_EXP_RULE = "INT_EXP {}";
  private static final String WILDCARD_RULE = "* {}";
  private static final String OVERRIDE_INT_EXP_RULE = "override " + INT_EXP_RULE;
  private static final String OVERRIDE_WILDCARD_RULE = "override " + WILDCARD_RULE;
  private static final String PARENT_MODEL_NAME = "Base";
  private static final String PARENT_GRAMMAR = PARENT_MODEL_NAME + ".xtext";
  private static final String PARENT_FORMAT = PARENT_MODEL_NAME + ".format";
  private static final String EXTENDING_MODEL_NAME = "Extending";
  private static final String EXTENDING_GRAMMAR = EXTENDING_MODEL_NAME + ".xtext";
  private static final String EXTENDING_FORMAT = EXTENDING_MODEL_NAME + ".format";
  private FormatConfiguration parentFormat;
  private FormatConfiguration extendingFormat;

  @Override
  protected FormatTestUtil getXtextTestUtil() {
    return FormatTestUtil.getInstance();
  }

  @Override
  protected String getTestSourceModelName() {
    return PARENT_MODEL_NAME;
  }

  @Override
  protected List<String> getRequiredSourceFileNames() {
    return Lists.newArrayList(PARENT_GRAMMAR, PARENT_FORMAT, EXTENDING_GRAMMAR, EXTENDING_FORMAT);
  }

  @Override
  protected void beforeEachTest() {
    super.beforeEachTest();
    getXtextTestUtil().resetResourceSet();
    parentFormat = (FormatConfiguration) getTestSource(PARENT_FORMAT).getModel();
    extendingFormat = (FormatConfiguration) getTestSource(EXTENDING_FORMAT).getModel();
  }

  /**
   * Reparse {@link #parentFormat} and {@link #extendingFormat} to include the provided rules.
   *
   * @param extendRules
   *          the extend rules
   * @param baseRules
   *          the base rules
   */
  private void setFormattingRules(final String[] extendRules, final String... baseRules) {
    parentFormat = createModel(PARENT_MODEL_NAME, null, baseRules);
    extendingFormat = createModel(EXTENDING_MODEL_NAME, PARENT_MODEL_NAME, extendRules);
  }

  /**
   * Create a FormatConfiguration source, parse it and return the model.
   *
   * @param formatName
   *          the name of the format configuration (equal to the name of the target grammar)
   * @param extendedFormatName
   *          name of the extended format configuration (may be null)
   * @param rules
   *          the rules
   * @return the FormatConfiguration
   */
  @SuppressWarnings("PMD.InsufficientStringBufferDeclaration")
  private FormatConfiguration createModel(final String formatName, final String extendedFormatName, final String... rules) {
    StringBuilder modelContent = new StringBuilder("formatter for ");
    modelContent.append("com.avaloq.tools.ddk.xtext.format.validation.");
    modelContent.append(formatName);
    if (extendedFormatName != null) {
      modelContent.append(" with ").append("com.avaloq.tools.ddk.xtext.format.validation.").append(extendedFormatName);
    }
    modelContent.append(FormatTestUtil.NEW_LINE);
    for (String rule : rules) {
      modelContent.append(rule).append(FormatTestUtil.NEW_LINE);
    }
    return getXtextTestUtil().getModelFromString(modelContent.toString(), formatName);
  }

  /**
   * Tests that non-'rule' directives are invalid in a terminal rule context.
   */
  @Test
  public void testNegativeACF1000() {
    setFormattingRules(new String[0], new String[] {"INT_EXP { \"e\" : no_space around;}"});
    assertDiagnostic(parentFormat, FormatValidator.ILLEGAL_DIRECTIVE_CODE);
  }

  /**
   * Tests that 'rule' directives are valid in a terminal rule context.
   */
  @Test
  public void testPositiveACF1000() {
    setFormattingRules(new String[0], new String[] {"INT_EXP { rule : no_space around;}"});
    assertNoDiagnostic(parentFormat, FormatValidator.ILLEGAL_DIRECTIVE_CODE);
  }

  /**
   * Verify that IllegalOverride validation issues error for WildcardRules.
   */
  @Test
  public void illegalWildcardRuleOverride() {
    setFormattingRules(new String[] {OVERRIDE_WILDCARD_RULE}, new String[] {});
    assertDiagnostic(extendingFormat, FormatValidator.OVERRIDE_ILLEGAL_CODE);
  }

  /**
   * Verify that IllegalOverride validation issues error for GrammarRules.
   */
  @Test
  public void illegalGrammarRuleOverride() {
    setFormattingRules(new String[] {OVERRIDE_INT_EXP_RULE}, new String[] {});
    assertDiagnostic(extendingFormat, FormatValidator.OVERRIDE_ILLEGAL_CODE);
  }

  /**
   * Verify that OverrideMissing validation issues error for WildcardRules.
   */
  @Test
  public void missingWildcardRuleOverride() {
    setFormattingRules(new String[] {WILDCARD_RULE}, new String[] {WILDCARD_RULE});

    assertDiagnostic(extendingFormat, FormatValidator.OVERRIDE_MISSING_CODE);
  }

  /**
   * Verify that OverrideMissing validation issues error for GrammarRules.
   */
  @Test
  public void missingGrammarRuleOverride() {
    setFormattingRules(new String[] {INT_EXP_RULE}, new String[] {INT_EXP_RULE});
    assertDiagnostic(extendingFormat, FormatValidator.OVERRIDE_MISSING_CODE);
  }

  /**
   * Verify that OverrideMissing nor IllegalOverride validations issue no error.
   */
  @Test
  public void wildcardRuleOverrideOK() {
    setFormattingRules(new String[] {OVERRIDE_WILDCARD_RULE}, new String[] {WILDCARD_RULE});
    assertNoDiagnostic(extendingFormat, FormatValidator.OVERRIDE_MISSING_CODE);
    assertNoDiagnostic(extendingFormat, FormatValidator.OVERRIDE_ILLEGAL_CODE);
  }

  /**
   * Verify that OverrideMissing nor IllegalOverride validations issue no error.
   */
  @Test
  public void grammarRuleOverrideOK() {
    setFormattingRules(new String[] {OVERRIDE_INT_EXP_RULE}, new String[] {INT_EXP_RULE});
    assertNoDiagnostic(extendingFormat, FormatValidator.OVERRIDE_MISSING_CODE);
    assertNoDiagnostic(extendingFormat, FormatValidator.OVERRIDE_ILLEGAL_CODE);
  }

  /**
   * Verify that ExtendedGrammarCompatible validation issues error when grammars of the Format models are incompatible.
   */
  @Test
  public void extendedGrammarCompatible() {
    try {
      getXtextTestUtil().getModel("MyDsl.xtext", "grammar com.avaloq.tools.ddk.xtext.format.validation.MyDsl\nimport \"http://www.eclipse.org/emf/2002/Ecore\" as ecore\nRule: 'rule';");
    } catch (IOException e) {
      fail("Could not create MyDsl model: " + e.getMessage());
    }
    FormatConfiguration myDslModel = createModel("MyDsl", PARENT_MODEL_NAME, new String[] {});
    assertDiagnostic(myDslModel, FormatValidator.EXTENDED_GRAMMAR_INCOMPATIBLE_CODE);
  }

  /**
   * Verify that ExtendedGrammarCompatible validation issues no error.
   */
  @Test
  public void extendedGrammarCompatibleOK() {
    createModel(PARENT_MODEL_NAME, null, new String[] {});
    FormatConfiguration extendModel = createModel(EXTENDING_MODEL_NAME, PARENT_MODEL_NAME, new String[] {});
    assertNoDiagnostic(extendModel, FormatValidator.EXTENDED_GRAMMAR_INCOMPATIBLE_CODE);
  }

  /**
   * Verify that if there are rules in the inheriting grammar sharing the same name, a corresponding
   * formatting rule must be defined in the extending configuration if it is defined in the parent.
   */
  @Test
  public void requiredRulesImplemented() {
    setFormattingRules(new String[0], new String[] {"Rule {}"});
    assertDiagnostic(extendingFormat, FormatValidator.GRAMMAR_RULE_MISSING_CODE);
  }
}
