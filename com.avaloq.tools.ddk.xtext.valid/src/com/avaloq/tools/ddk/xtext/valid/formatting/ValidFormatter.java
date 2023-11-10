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
package com.avaloq.tools.ddk.xtext.valid.formatting;

import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;

import com.avaloq.tools.ddk.xtext.valid.services.ValidGrammarAccess;
import com.avaloq.tools.ddk.xtext.valid.services.ValidGrammarAccess.CategoryElements;
import com.avaloq.tools.ddk.xtext.valid.services.ValidGrammarAccess.ImportElements;
import com.avaloq.tools.ddk.xtext.valid.services.ValidGrammarAccess.NativeContextElements;
import com.avaloq.tools.ddk.xtext.valid.services.ValidGrammarAccess.NativeRuleElements;
import com.avaloq.tools.ddk.xtext.valid.services.ValidGrammarAccess.QuickFixElements;


/**
 * This class contains custom formatting description. see :
 * http://wiki.eclipse.org/Xtext/Documentation#Formatting on how and when to use
 * it Also see {@link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer} as
 * an example
 */
public class ValidFormatter extends AbstractDeclarativeFormatter {

  /**
   * The number of lines to be wrapped before or after a new block of code.
   */
  private static final int NUM_WRAP_LINES = 2;

  /**
   * The maximum number of characters on one line in the editor.
   */
  private static final int MAX_LINE_LENGTH = 140;

  
  @Override
  protected void configureFormatting(final FormattingConfig config) {
    final ValidGrammarAccess grammarAccess = (ValidGrammarAccess) getGrammarAccess();
    config.setAutoLinewrap(MAX_LINE_LENGTH);

    // Comments
    config.setLinewrap(0, 1, 2).before(grammarAccess.getSL_COMMENTRule());
    config.setLinewrap(0, 1, 2).before(grammarAccess.getML_COMMENTRule());
    config.setLinewrap(0, 1, 1).after(grammarAccess.getML_COMMENTRule());

    configureCategoryFormatting(config, grammarAccess.getCategoryAccess());
    configureNativeRuleFormatting(config, grammarAccess.getNativeRuleAccess());
    configureNativeContextFormatting(config, grammarAccess.getNativeContextAccess());
    configureQuickFixFormatting(config, grammarAccess.getQuickFixAccess());
    configureImportFormatting(config, grammarAccess.getImportAccess());
  }

  /**
   * Configure Import formatting.
   * 
   * @param config
   *          the formatter configuration
   * @param importElements
   *          the element to format
   */
  protected void configureImportFormatting(final FormattingConfig config, final ImportElements importElements) {
    config.setLinewrap().after(importElements.getGroup());
  }

  /**
   * Configure QuickFix formatting.
   * 
   * @param config
   *          the formatter configuration
   * @param quickFixElements
   *          the element to format
   */
  protected void configureQuickFixFormatting(final FormattingConfig config, final QuickFixElements quickFixElements) {
    config.setIndentation(quickFixElements.getFixKeyword_1(), quickFixElements.getSemicolonKeyword_7());
    config.setLinewrap().after(quickFixElements.getSemicolonKeyword_7());
    config.setLinewrap().before(quickFixElements.getLabelKeyword_3());
    config.setLinewrap().before(quickFixElements.getMessageKeyword_5());
    config.setNoSpace().before(quickFixElements.getSemicolonKeyword_7());
  }

  /**
   * Configure NativeContext formatting.
   * 
   * @param config
   *          the formatter configuration
   * @param nativeContextElements
   *          the element to format
   */
  protected void configureNativeContextFormatting(final FormattingConfig config, final NativeContextElements nativeContextElements) {
    config.setNoLinewrap().before(nativeContextElements.getMarkerKeyword_3_0());
    config.setNoSpace().around(nativeContextElements.getNumberSignKeyword_1_0());
    config.setNoSpace().around(nativeContextElements.getNumberSignKeyword_3_2_0());
    config.setNoLinewrap().before(nativeContextElements.getLeftCurlyBracketKeyword_4_1());
    config.setNoLinewrap().before(nativeContextElements.getQuickfixesKeyword_4_0());
    config.setLinewrap().after(nativeContextElements.getLeftCurlyBracketKeyword_4_1());
    config.setLinewrap().before(nativeContextElements.getRightCurlyBracketKeyword_4_3());
    config.setIndentation(nativeContextElements.getLeftCurlyBracketKeyword_4_1(), nativeContextElements.getRightCurlyBracketKeyword_4_3());
    config.setNoSpace().before(nativeContextElements.getSemicolonKeyword_5());
    config.setLinewrap().after(nativeContextElements.getGroup());
  }

  /**
   * Configure NativeRule formatting.
   * 
   * @param config
   *          the formatter configuration
   * @param nativeRuleElements
   *          the element to format
   */
  protected void configureNativeRuleFormatting(final FormattingConfig config, final NativeRuleElements nativeRuleElements) {
    config.setIndentation(nativeRuleElements.getNameAssignment_2(), nativeRuleElements.getContextKeyword_8());

    config.setLinewrap().before(nativeRuleElements.getLabelKeyword_3());
    config.setLinewrap().before(nativeRuleElements.getDescriptionKeyword_5_0());
    config.setLinewrap().before(nativeRuleElements.getContextKeyword_8());
    config.setLinewrap().before(nativeRuleElements.getMessageKeyword_6());
    config.setNoLinewrap().before(nativeRuleElements.getLeftCurlyBracketKeyword_9());
    config.setLinewrap().after(nativeRuleElements.getLeftCurlyBracketKeyword_9());
    config.setLinewrap().before(nativeRuleElements.getRightCurlyBracketKeyword_11());
    config.setIndentation(nativeRuleElements.getLeftCurlyBracketKeyword_9(), nativeRuleElements.getRightCurlyBracketKeyword_11());
  }

  /**
   * Configure Category formatting.
   * 
   * @param config
   *          the formatter configuration
   * @param categoryElements
   *          the element to format
   */
  protected void configureCategoryFormatting(final FormattingConfig config, final CategoryElements categoryElements) {
    config.setIndentation(categoryElements.getCategoryKeyword_0(), categoryElements.getLeftCurlyBracketKeyword_5());
    config.setIndentation(categoryElements.getLeftCurlyBracketKeyword_5(), categoryElements.getRightCurlyBracketKeyword_7());
    config.setLinewrap(NUM_WRAP_LINES).before(categoryElements.getCategoryKeyword_0());
    config.setLinewrap().before(categoryElements.getLeftCurlyBracketKeyword_5());
    config.setLinewrap(NUM_WRAP_LINES).after(categoryElements.getLeftCurlyBracketKeyword_5());
    config.setLinewrap().before(categoryElements.getLabelKeyword_2());
    config.setLinewrap().before(categoryElements.getDescriptionKeyword_4_0());
    config.setLinewrap(NUM_WRAP_LINES).before(categoryElements.getRightCurlyBracketKeyword_7());

    config.setLinewrap(2).before(categoryElements.getRulesAssignment_6());
  }
}

