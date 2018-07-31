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
package com.avaloq.tools.ddk.check.formatting;

import java.util.List;

import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.impl.GrammarImpl;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.xbase.formatting.XbaseFormatter;
import org.eclipse.xtext.xbase.services.XbaseGrammarAccess.XIfExpressionElements;
import org.eclipse.xtext.xbase.services.XbaseGrammarAccess.XMemberFeatureCallElements;

import com.avaloq.tools.ddk.check.CheckConstants;
import com.avaloq.tools.ddk.check.services.CheckGrammarAccess;
import com.avaloq.tools.ddk.check.services.CheckGrammarAccess.CheckCatalogElements;
import com.avaloq.tools.ddk.check.services.CheckGrammarAccess.CheckElements;
import com.avaloq.tools.ddk.check.services.CheckGrammarAccess.ContextElements;
import com.avaloq.tools.ddk.check.services.CheckGrammarAccess.SeverityRangeElements;
import com.avaloq.tools.ddk.check.services.CheckGrammarAccess.XIssueExpressionElements;


/**
 * Configures formatting for the Check editor.
 */
@SuppressWarnings("deprecation")
public class CheckFormatter extends XbaseFormatter {

  private static final int LINE_WRAP_LENGTH = 132;

  /**
   * General formatting configuration. Delegates formatting configuration to individual grammar elements.
   *
   * @param configuration
   *          formating configuration
   * @param access
   *          grammarAccess
   */
  private void configure(final FormattingConfig configuration, final CheckGrammarAccess access) {

    super.configure(configuration, access.getXbaseWithAnnotationsGrammarAccess());

    // Common curly brackets handling (for check grammar only)
    List<Pair<Keyword, Keyword>> curlyPairs = access.findKeywordPairs("{", "}"); //$NON-NLS-1$ //$NON-NLS-2$
    for (Pair<Keyword, Keyword> pair : curlyPairs) {
      if (EcoreUtil2.getContainerOfType(pair.getFirst(), GrammarImpl.class).getName().equals(CheckConstants.GRAMMAR)) {
        configuration.setLinewrap().after(pair.getFirst());
        configuration.setIndentation(pair.getFirst(), pair.getSecond());
        configuration.setLinewrap().before(pair.getSecond());
      }
    }

    // AutoLineWrap
    configuration.setAutoLinewrap(LINE_WRAP_LENGTH);

    // Comments
    configuration.setLinewrap(0, 1, 2).before(access.getSL_COMMENTRule());
    configuration.setLinewrap(1, 1, 2).before(access.getML_COMMENTRule());
    configuration.setLinewrap(1, 1, 1).after(access.getML_COMMENTRule());

    // Rules
    configuration.setLinewrap(1, 1, 2).before(access.getXImportDeclarationRule());
    configuration.setLinewrap(1, 2, 2).before(access.getCategoryRule());
    configuration.setLinewrap(1, 2, 2).before(access.getImplementationRule());
    configuration.setLinewrap(1, 2, 2).before(access.getCheckRule());
    configuration.setLinewrap(1, 1, 2).before(access.getXGuardExpressionRule());
    configuration.setLinewrap(1, 1, 2).before(access.getXIssueExpressionRule());
    configuration.setLinewrap(1, 1, 2).before(access.getXIfExpressionRule());
    configuration.setLinewrap(1, 1, 2).before(access.getXVariableDeclarationRule());

    // Rule elements
    configureFeatureCall(configuration, access.getXMemberFeatureCallAccess());
    configureCheckCatalog(configuration, access.getCheckCatalogAccess());
    configureSeverityRange(configuration, access.getSeverityRangeAccess());
    configureCheck(configuration, access.getCheckAccess());
    configureXIssueExpression(configuration, access.getXIssueExpressionAccess());
    configureCheckContext(configuration, access.getContextAccess());
  }

  @Override
  protected void configureFormatting(final FormattingConfig c) {
    configure(c, checkGrammarAccess());
  }

  /**
   * (Re-) Configure feature call formatting.
   *
   * @param c
   *          the formatting configuration
   * @param featureCallElements
   *          the feature call elements
   */
  private void configureFeatureCall(final FormattingConfig c, final XMemberFeatureCallElements featureCallElements) {
    // set no space after '::' in CheckUtil::hasQualifiedName(..., and also not after plain "." or "?."
    c.setNoSpace().after(featureCallElements.getFullStopKeyword_1_0_0_0_1_0());
    c.setNoSpace().after(featureCallElements.getFullStopKeyword_1_1_0_0_1_0());
    c.setNoSpace().after(featureCallElements.getNullSafeAssignment_1_1_0_0_1_1());
    c.setNoSpace().after(featureCallElements.getExplicitStaticAssignment_1_0_0_0_1_1());
    c.setNoSpace().after(featureCallElements.getExplicitStaticAssignment_1_1_0_0_1_2());
  }

  // Override Xbase default formatting:
  // Keep '{' on same line as 'if' keyword (instead of a line wrap before opening curly bracket)
  //
  /** {@inheritDoc} */
  @Override
  public void configureXIfExpression(final FormattingConfig c, final XIfExpressionElements elements) {
    c.setNoSpace().after(elements.getLeftParenthesisKeyword_2());
    c.setNoSpace().before(elements.getRightParenthesisKeyword_4());
  }

  /**
   * Configure XIssueExpressions formatting.
   *
   * @param c
   *          the formatting configuration
   * @param elements
   *          the accessible formattable parser elements
   */
  public void configureXIssueExpression(final FormattingConfig c, final XIssueExpressionElements elements) {
    c.setSpace(" ").after(elements.getOnKeyword_3_0()); //$NON-NLS-1$

    c.setNoSpace().around(elements.getNumberSignKeyword_3_1_0_0());
    c.setNoSpace().around(elements.getNumberSignKeyword_3_1_1_1_0());

    c.setNoSpace().around(elements.getLeftSquareBracketKeyword_3_2_0());
    c.setNoSpace().before(elements.getRightSquareBracketKeyword_3_2_2());

    c.setNoSpace().after(elements.getLeftParenthesisKeyword_5_1());
    c.setNoSpace().before(elements.getCommaKeyword_5_3_0());
    c.setNoSpace().before(elements.getRightParenthesisKeyword_5_4());

    c.setNoSpace().after(elements.getLeftParenthesisKeyword_6_2());
    c.setNoSpace().before(elements.getCommaKeyword_6_4_0());
    c.setNoSpace().before(elements.getRightParenthesisKeyword_6_5());
  }

  /**
   * Configure CheckCatalog formatting.
   *
   * @param c
   *          the formatting configuration
   * @param elements
   *          the accessible formattable parser elements
   */
  private void configureCheckCatalog(final FormattingConfig c, final CheckCatalogElements elements) {
    c.setLinewrap(0, 0, 0).after(elements.getFinalFinalKeyword_4_0());
    c.setLinewrap(1, 2, 2).before(elements.getFinalFinalKeyword_4_0());

    c.setLinewrap(1, 2, 2).after(elements.getPackageNameAssignment_2());

    c.setLinewrap(0, 1, 2).before(elements.getCatalogKeyword_5());
    c.setLinewrap(1, 1, 2).before(elements.getForKeyword_7_0());
  }

  /**
   * Configure Context formatting.
   *
   * @param c
   *          the formatting configuration
   * @param elements
   *          the accessible formattable parser elements
   */
  private void configureCheckContext(final FormattingConfig c, final ContextElements elements) {
    c.setLinewrap(1, 2, 2).before(elements.getForKeyword_0());
  }

  /**
   * Configures severity range elements. A default severity range should be configured as follows:
   * <p>
   * {@code @SeverityRange(error .. warning)}
   * </p>
   *
   * @param c
   *          the formatting configuration
   * @param elements
   *          the severity range elements
   */
  private void configureSeverityRange(final FormattingConfig c, final SeverityRangeElements elements) {
    c.setNoSpace().around(elements.getSeverityRangeKeyword_1());
    c.setNoSpace().after(elements.getLeftParenthesisKeyword_2());
    c.setNoSpace().before(elements.getRightParenthesisKeyword_6());
    c.setLinewrap().after(elements.getRightParenthesisKeyword_6());
  }

  /**
   * Configure check.
   *
   * @param c
   *          the formatting configuration
   * @param elements
   *          the accessible formattable parser elements
   */
  private void configureCheck(final FormattingConfig c, final CheckElements elements) {
    c.setNoSpace().between(elements.getLeftParenthesisKeyword_6_0(), elements.getRightParenthesisKeyword_6_2());
    c.setLinewrap(1, 1, 2).before(elements.getMessageKeyword_7_0());

    c.setNoSpace().after(elements.getLeftParenthesisKeyword_6_0());
    c.setNoSpace().before(elements.getRightParenthesisKeyword_6_2());
    c.setNoSpace().before(elements.getCommaKeyword_6_1_1_0());
    c.setLinewrap(0, 0, 1).after(elements.getCommaKeyword_6_1_1_0());
    c.setLinewrap(1, 2, 2).around(elements.getContextsAssignment_8_0_1());
  }

  /**
   * Gets the Check grammar access.
   *
   * @return the check grammar access
   */
  protected CheckGrammarAccess checkGrammarAccess() {
    return (CheckGrammarAccess) super.getGrammarAccess();
  }
}
