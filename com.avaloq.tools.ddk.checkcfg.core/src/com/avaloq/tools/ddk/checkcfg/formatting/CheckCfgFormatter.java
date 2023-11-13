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
package com.avaloq.tools.ddk.checkcfg.formatting;

import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.xbase.formatting.XbaseFormatter;

import com.avaloq.tools.ddk.checkcfg.services.CheckCfgGrammarAccess;
import com.avaloq.tools.ddk.checkcfg.services.CheckCfgGrammarAccess.CheckConfigurationElements;
import com.avaloq.tools.ddk.checkcfg.services.CheckCfgGrammarAccess.ConfiguredCatalogElements;
import com.avaloq.tools.ddk.checkcfg.services.CheckCfgGrammarAccess.ConfiguredCheckElements;
import com.avaloq.tools.ddk.checkcfg.services.CheckCfgGrammarAccess.ConfiguredLanguageValidatorElements;


/**
 * Configures the Check Configuration formatter.
 */
@SuppressWarnings("deprecation")
public class CheckCfgFormatter extends XbaseFormatter {

  private static final int LINE_WRAP_LENGTH = 132;

  @Override
  protected void configureFormatting(final FormattingConfig c) {
    configure(c, checkConfigGrammarAccess());
  }

  /**
   * Gets the Check grammar access.
   *
   * @return the check grammar access
   */
  protected CheckCfgGrammarAccess checkConfigGrammarAccess() {
    return (CheckCfgGrammarAccess) super.getGrammarAccess();
  }

  /**
   * Entry point for Check Configuration formatting.
   *
   * @param c
   *          the formatting configuration
   * @param g
   *          the grammar access
   */
  private void configure(final FormattingConfig c, final CheckCfgGrammarAccess g) {
    super.configure(c, g.getXbaseGrammarAccess());

    // Comments
    c.setLinewrap(0, 1, 2).before(g.getSL_COMMENTRule());
    c.setLinewrap(0, 1, 2).before(g.getML_COMMENTRule());
    c.setLinewrap(0, 1, 1).after(g.getML_COMMENTRule());

    // AutoLineWrap
    c.setAutoLinewrap(LINE_WRAP_LENGTH);

    configureCheckConfiguration(c, g.getCheckConfigurationAccess());
    configureLanguageValidatorConfiguration(c, g.getConfiguredLanguageValidatorAccess());
    configureConfiguredCatalog(c, g.getConfiguredCatalogAccess());
    configureConfiguredCheck(c, g.getConfiguredCheckAccess());
    // configureConfiguredParameter(c, g.getConfiguredParameterAccess());
  }

  /**
   * Configures formatting for Check Configuration.
   *
   * @param c
   *          the formatting configuration
   * @param elements
   *          the Check Configuration elements
   */
  private void configureCheckConfiguration(final FormattingConfig c, final CheckConfigurationElements elements) {
    c.setIndentationIncrement().after(elements.getLeftCurlyBracketKeyword_5_0());
    c.setIndentationDecrement().before(elements.getRightCurlyBracketKeyword_5_2());
    c.setLinewrap(2).after(elements.getLeftCurlyBracketKeyword_5_0());
    c.setLinewrap(2).before(elements.getRightCurlyBracketKeyword_5_2());
  }

  /**
   * Configures formatting for Check Configuration.
   *
   * @param c
   *          the formatting configuration
   * @param elements
   *          the Check Configuration elements
   */
  private void configureLanguageValidatorConfiguration(final FormattingConfig c, final ConfiguredLanguageValidatorElements elements) {
    c.setIndentationIncrement().after(elements.getLeftCurlyBracketKeyword_2());
    c.setIndentationDecrement().before(elements.getRightCurlyBracketKeyword_5());
    c.setLinewrap(2).before(elements.getForKeyword_0());
    c.setLinewrap(2).after(elements.getLeftCurlyBracketKeyword_2());
    c.setLinewrap(2).before(elements.getRightCurlyBracketKeyword_5());
  }

  /**
   * Configures formatting for Configured Catalog.
   *
   * @param c
   *          the formatting configuration
   * @param elements
   *          the Configured Catalog elements
   */
  private void configureConfiguredCatalog(final FormattingConfig c, final ConfiguredCatalogElements elements) {
    c.setLinewrap(2).before(elements.getCatalogKeyword_1());
    c.setIndentationIncrement().after(elements.getLeftCurlyBracketKeyword_3());
    c.setIndentationDecrement().before(elements.getRightCurlyBracketKeyword_6());
    c.setLinewrap().before(elements.getCatalogKeyword_1());

    c.setLinewrap().after(elements.getLeftCurlyBracketKeyword_3());
    c.setLinewrap().before(elements.getRightCurlyBracketKeyword_6());
  }

  /**
   * Configures formatting for Configured Check.
   *
   * @param c
   *          the formatting configuration
   * @param elements
   *          the Configured Check elements
   */
  private void configureConfiguredCheck(final FormattingConfig c, final ConfiguredCheckElements elements) {
    c.setLinewrap().before(elements.getSeverityAssignment_1());
    c.setNoSpace().after(elements.getLeftParenthesisKeyword_3_0());
    c.setNoSpace().before(elements.getRightParenthesisKeyword_3_3());
    c.setNoSpace().before(elements.getCommaKeyword_3_2_0());

    c.setLinewrap(0, 0, 1).after(elements.getCommaKeyword_3_2_0());
    c.setIndentation(elements.getLeftParenthesisKeyword_3_0(), elements.getRightParenthesisKeyword_3_3());
  }

  // /**
  // * Configures formatting for Configured Parameter.
  // *
  // * @param c
  // * the formatting configuration
  // * @param elements
  // * the Configured Parameter elements
  // */
  // private void configureConfiguredParameter(final FormattingConfig c, final ConfiguredParameterElements elements) {}
}
