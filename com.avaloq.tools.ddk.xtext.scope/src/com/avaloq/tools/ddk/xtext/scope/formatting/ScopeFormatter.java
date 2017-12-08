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
package com.avaloq.tools.ddk.xtext.scope.formatting;

import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.util.Pair;

import com.avaloq.tools.ddk.xtext.scope.services.ScopeGrammarAccess;
import com.avaloq.tools.ddk.xtext.scope.services.ScopeGrammarAccess.NamedScopeExpressionElements;
import com.avaloq.tools.ddk.xtext.scope.services.ScopeGrammarAccess.ScopeDefinitionElements;
import com.avaloq.tools.ddk.xtext.scope.services.ScopeGrammarAccess.ScopeModelElements;
import com.avaloq.tools.ddk.xtext.scope.services.ScopeGrammarAccess.ScopeRuleElements;


/**
 * This class contains custom formatting description.
 * see : http://www.eclipse.org/Xtext/documentation/latest/xtext.html#formatting
 * on how and when to use it
 * Also see {@link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer} as an example
 */
public class ScopeFormatter extends AbstractDeclarativeFormatter {

  private static final int MAX_LINE_LENGTH = 140;

  @Override
  protected void configureFormatting(final FormattingConfig cfg) {
    final ScopeGrammarAccess g = (ScopeGrammarAccess) getGrammarAccess();

    cfg.setAutoLinewrap(MAX_LINE_LENGTH);

    // Comments
    cfg.setLinewrap(0, 1, 2).before(g.getSL_COMMENTRule());
    cfg.setLinewrap(0, 1, 2).before(g.getML_COMMENTRule());
    cfg.setLinewrap(0, 1, 1).after(g.getML_COMMENTRule());

    // general keywords
    for (final Pair<Keyword, Keyword> pair : g.findKeywordPairs("(", ")")) {
      cfg.setNoSpace().before(pair.getFirst());
      cfg.setNoSpace().after(pair.getFirst());
      cfg.setNoSpace().before(pair.getSecond());
    }
    for (final Pair<Keyword, Keyword> pair : g.findKeywordPairs("[", "]")) {
      cfg.setNoSpace().after(pair.getFirst());
      cfg.setNoSpace().before(pair.getSecond());
    }
    for (final Pair<Keyword, Keyword> pair : g.findKeywordPairs("{", "}")) {
      if (pair.getFirst() == g.getListLiteralAccess().getLeftCurlyBracketKeyword_1()) {
        continue;
      }
      cfg.setLinewrap().after(pair.getFirst());
      cfg.setIndentationIncrement().after(pair.getFirst());
      cfg.setLinewrap().before(pair.getSecond());
      cfg.setIndentationDecrement().before(pair.getSecond());
    }

    for (final Keyword delimiter : g.findKeywords(",")) {
      cfg.setNoSpace().before(delimiter);
    }
    for (final Keyword delimiter : g.findKeywords(";")) {
      cfg.setNoSpace().before(delimiter);
      cfg.setLinewrap().after(delimiter);
    }
    for (final Keyword delimiter : g.findKeywords("#", ".", "::")) {
      cfg.setNoSpace().around(delimiter);
    }

    // ScopeModel
    final ScopeModelElements sm = g.getScopeModelAccess();
    cfg.setLinewrap(2).between(sm.getNameAssignment_1(), sm.getImportsAssignment_3());
    cfg.setLinewrap(1).before(sm.getImportsAssignment_3());
    cfg.setLinewrap(2).between(sm.getImportsAssignment_3(), sm.getExtensionsAssignment_4());
    cfg.setLinewrap(1).before(sm.getExtensionsAssignment_4());
    cfg.setLinewrap(2).between(sm.getExtensionsAssignment_4(), sm.getInjectionsAssignment_5());
    cfg.setLinewrap(1).before(sm.getInjectionsAssignment_5());
    cfg.setLinewrap(2).before(sm.getNamingAssignment_6());
    cfg.setLinewrap(2).before(sm.getScopesAssignment_7());

    // ScopeDefinition
    final ScopeDefinitionElements sd = g.getScopeDefinitionAccess();
    cfg.setSpace(" ").before(sd.getLeftParenthesisKeyword_1_0());
    cfg.setLinewrap(1, 1, 2).before(sd.getRulesAssignment_4());

    // ScopeRule
    final ScopeRuleElements sr = g.getScopeRuleAccess();
    cfg.setIndentationIncrement().after(sr.getEqualsSignKeyword_2());
    cfg.setLinewrap(0, 0, 1).after(sr.getEqualsSignKeyword_2());
    cfg.setLinewrap(0, 0, 1).before(sr.getGreaterThanSignGreaterThanSignKeyword_4_0());
    cfg.setLinewrap(0, 0, 1).before(sr.getSemicolonKeyword_5());
    cfg.setIndentationDecrement().before(sr.getSemicolonKeyword_5());

    // NamedScopeExpression
    final NamedScopeExpressionElements nse = g.getNamedScopeExpressionAccess();
    cfg.setLinewrap(0, 0, 1).before(nse.getCaseDefCaseKeyword_1_0_0());
    cfg.setLinewrap(0, 0, 1).before(nse.getAsKeyword_2_0());
  }

}
