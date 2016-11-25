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
package com.avaloq.tools.ddk.xtext.format.formatting;

import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.util.Pair;

import com.avaloq.tools.ddk.xtext.format.services.FormatGrammarAccess;
import com.avaloq.tools.ddk.xtext.format.services.FormatGrammarAccess.FormatConfigurationElements;
import com.avaloq.tools.ddk.xtext.format.services.FormatGrammarAccess.GrammarRuleElements;
import com.avaloq.tools.ddk.xtext.format.services.FormatGrammarAccess.KeywordPairElements;
import com.avaloq.tools.ddk.xtext.format.services.FormatGrammarAccess.WildcardRuleElements;


/**
 * This class contains custom formatting description.
 * see : http://www.eclipse.org/Xtext/documentation/latest/xtext.html#formatting
 * on how and when to use it
 * Also see {@link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer} as an example
 */
public class FormatFormatter extends AbstractDeclarativeFormatter {

  private static final int LINE_LENGTH = 120;

  @Override
  protected void configureFormatting(final FormattingConfig config) {
    FormatGrammarAccess grammarAccess = (FormatGrammarAccess) getGrammarAccess();

    config.setAutoLinewrap(LINE_LENGTH);

    config.setLinewrap().before(grammarAccess.getFormatConfigurationAccess().getGroup_4());

    for (Pair<Keyword, Keyword> pair : grammarAccess.findKeywordPairs("{", "}")) {
      config.setIndentationIncrement().after(pair.getFirst());
      config.setLinewrap().after(pair.getFirst());
      config.setIndentationDecrement().before(pair.getSecond());
      config.setLinewrap().after(pair.getSecond());
    }

    for (Pair<Keyword, Keyword> pair : grammarAccess.findKeywordPairs("[", "]")) {
      config.setNoSpace().after(pair.getFirst());
      config.setNoSpace().before(pair.getSecond());
    }

    for (Keyword keyword : grammarAccess.findKeywords(";")) {
      config.setLinewrap().after(keyword);
      config.setNoSpace().before(keyword);
    }

    for (Keyword keyword : grammarAccess.findKeywords(",")) {
      config.setNoSpace().before(keyword);
    }

    for (Keyword keyword : grammarAccess.findKeywords("=", "@")) {
      config.setSpace("").after(keyword);
    }

    configComments(config, grammarAccess.getML_COMMENTRule(), grammarAccess.getSL_COMMENTRule());
    configConstants(config, grammarAccess);
    configGrammarRule(config, grammarAccess.getGrammarRuleAccess());
    configWildcardRule(config, grammarAccess.getWildcardRuleAccess());
    configKeywordPair(config, grammarAccess.getKeywordPairAccess());
  }

  /**
   * Configure formatting for ML and SL comments.
   *
   * @param config
   *          the FormattingConfig
   * @param mlRule
   *          the ML terminal access
   * @param slRule
   *          the SL terminal access
   */
  private void configComments(final FormattingConfig config, final TerminalRule mlRule, final TerminalRule slRule) {
    config.setLinewrap(0, 1, 2).before(slRule);
    config.setLinewrap(0, 1, 2).before(mlRule);
    config.setLinewrap().after(mlRule);
  }

  /**
   * Configure formatting for constant declarations.
   *
   * @param config
   *          the FormattingConfig
   * @param grammarAccess
   *          the grammar access
   */
  private void configConstants(final FormattingConfig config, final FormatGrammarAccess grammarAccess) {
    FormatConfigurationElements formatConfigurationAccess = grammarAccess.getFormatConfigurationAccess();
    config.setLinewrap(2).between(formatConfigurationAccess.getTargetGrammarAssignment_2(), formatConfigurationAccess.getGroup_5());
    config.setLinewrap().before(formatConfigurationAccess.getConstKeyword_5_0());
    config.setSpace(" ").around(grammarAccess.getConstantAccess().getEqualsSignKeyword_2());
  }

  /**
   * Configure formatting for GrammarRule.
   *
   * @param config
   *          the FormattingConfig
   * @param elements
   *          the GrammarRule access
   */
  private void configGrammarRule(final FormattingConfig config, final GrammarRuleElements elements) {
    config.setLinewrap(2).before(elements.getOverrideOverrideKeyword_0_0());
    config.setSpace(" ").between(elements.getOverrideOverrideKeyword_0_0(), elements.getTargetRuleAssignment_1()); // prevent linewrap between override and rule
    config.setLinewrap(2).before(elements.getTargetRuleAssignment_1());
  }

  /**
   * Configure formatting for WildcardRule.
   *
   * @param config
   *          the FormattingConfig
   * @param elements
   *          the WildcardRule access
   */
  private void configWildcardRule(final FormattingConfig config, final WildcardRuleElements elements) {
    config.setLinewrap(2).before(elements.getOverrideOverrideKeyword_1_0());
    config.setSpace(" ").between(elements.getOverrideOverrideKeyword_1_0(), elements.getAsteriskKeyword_2()); // prevent linewrap between override and *
    config.setLinewrap(2).before(elements.getAsteriskKeyword_2());
  }

  /**
   * Configure formatting for KeywordPair.
   *
   * @param config
   *          the FormattingConfig
   * @param elements
   *          the KeywordPair access
   */
  private void configKeywordPair(final FormattingConfig config, final KeywordPairElements elements) {
    for (Keyword keyword : elements.findKeywords(".")) {
      config.setNoSpace().around(keyword);
    }
    config.setNoSpace().after(elements.getLeftParenthesisKeyword_0());
    config.setNoSpace().before(elements.getRightParenthesisKeyword_3());
  }
}
