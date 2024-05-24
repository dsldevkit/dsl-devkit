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

package com.avaloq.tools.ddk.xtext.generator.parser.antlr;

import java.util.List;

import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.xtext.RuleWithParameterValues;
import org.eclipse.xtext.xtext.generator.grammarAccess.GrammarAccessExtensions;


/**
 * Helper class to generate combined grammar as HTML.
 */
@SuppressWarnings("nls")
public class CombinedGrammarReportBuilder {

  private final GrammarAccessExtensions grammarExtensions;

  public CombinedGrammarReportBuilder(final GrammarAccessExtensions grammarExtensions) {
    this.grammarExtensions = grammarExtensions;
  }

  /**
   * Returns a simple documentation for the grammar, with analysis results.
   *
   * @param grammar
   *          grammar to generate report for, must not be {@code null}
   * @param parserRules
   *          list of parser, must not be {@code null}
   * @param enumRules
   *          list of enum rules, must not be {@code null}
   * @return Documentation
   */
  public String getDocumentation(final Grammar grammar, final List<ParserRule> parserRules, final List<EnumRule> enumRules) {
    // CHECKSTYLE:OFF MagicNumber
    StringBuilder doc = new StringBuilder(120);
    // CHECKSTYLE:ON
    doc.append("<!DOCTYPE html>\n<html>\n<body>");
    doc.append("<h1>Combined grammar for ");
    doc.append(GrammarUtil.getSimpleName(grammar));
    doc.append("</h1>");
    doc.append("<p><i>Reachable rules only</i></p>");
    for (ParserRule rule : parserRules) {
      doc.append(getLocalLinkToRule(rule));
    }
    for (EnumRule rule : enumRules) {
      doc.append(getLocalLinkToRule(rule));
    }
    for (ParserRule rule : parserRules) {
      addRuleToDoc(rule, doc);
    }
    for (EnumRule rule : enumRules) {
      addRuleToDoc(rule, doc);
    }
    doc.append("\n</body>\n</html>");
    return doc.toString();
  }

  /**
   * Returns hyperlink to rule within the documentation.
   *
   * @param rule
   *          Grammar Rule, must not be {@code null}
   * @return Name
   */
  private String getLocalLinkToRule(final AbstractRule rule) {
    String name = rule.getName();
    return " <a href=\"#" + name + "\"><kbd>" + name + "</kbd></a>";
  }

  /**
   * Adds rule to the documentation.
   *
   * @param rule
   *          Rule, must not be {@code null}
   * @param doc
   *          Document where the content should be added, must not be {@code null}
   */

  private void addRuleToDoc(final AbstractRule rule, final StringBuilder doc) {
    doc.append("\n\n<h2><a name=\"").append(rule.getName()).append("\">").append(rule.getName()).append("</a> (").append(GrammarUtil.getSimpleName(GrammarUtil.getGrammar(rule))).append(")</h2>");
    doc.append("<pre>");
    doc.append(getText(rule));
    doc.append("\n</pre>");
  }

  private String getText(final AbstractRule rule) {
    AbstractRule originalRule = RuleWithParameterValues.getOriginalRule(rule);
    INode node = NodeModelUtils.getNode(originalRule);
    return node != null ? node.getText() : grammarExtensions.grammarFragmentToString(originalRule, "");
  }
}

/* Copyright (c) Avaloq Group AG */