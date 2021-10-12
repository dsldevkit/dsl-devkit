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

package com.avaloq.tools.ddk.xtext.generator.parser.antlr;

import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.ParserRule;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;


/**
 * Helper class to construct report on grammar analysis.
 */
@SuppressWarnings("nls")
public class GrammarAnalysisReportBuilder {

  private static final String COMMA_SPACE_DELIMITER = ", ";

  private static final String HTML_SPACE = "&nbsp;";

  private static final int REPORT_BUFFER_INITIAL_SIZE = 2000;
  private static final int SMALL_BUFFER_INITIAL_SIZE = 100;

  /** The report. */
  private final StringWriter report = new StringWriter(REPORT_BUFFER_INITIAL_SIZE);
  private final String xtextDocFileSimpleName;
  private final List<String> sortedKeywords;
  private final Map<String, Set<AbstractRule>> keywords;
  private final List<Grammar> allGrammars;

  /**
   * Instantiates a new grammar analysis report builder.
   */
  public GrammarAnalysisReportBuilder(final String mainGrammarName, final String xtextDocFileSimpleName, final List<String> sortedKeywords, final Map<String, Set<AbstractRule>> keywords, final List<Grammar> allGrammars) {
    this.xtextDocFileSimpleName = xtextDocFileSimpleName;
    this.sortedKeywords = sortedKeywords;
    this.keywords = keywords;
    this.allGrammars = allGrammars;
    startHtml(mainGrammarName);
  }

  /**
   * Prepares a report on identifier rules.
   *
   * @param identifierRules
   *          the identifier rules, must not be {@code null}
   * @param calledRules
   *          the called rules, must not be {@code null}
   * @param notAcceptedKeywordsPerGrammar
   *          the not accepted keywords per grammar, must not be {@code null}
   * @param uncheckedRules
   *          the unchecked rules, must not be {@code null}
   * @param keywordsViolatingSpec
   *          the keywords violating spec, must not be {@code null}
   */
  public void reportOnIdentifierRules(final List<ParserRule> identifierRules, final Map<ParserRule, Set<AbstractRule>> calledRules, final Map<ParserRule, List<String>> notAcceptedKeywordsPerGrammar, final Set<String> uncheckedRules, final Map<String, Set<String>> keywordsViolatingSpec) {
    appendHeader("<a id=\"rules\">Identifier Rules</a>");
    for (ParserRule rule : identifierRules) {
      report.append("\n");
      report.append(getLinkToIdentifierRule(rule));
    }
    appendHeader("Details for Identifier Rules");
    startTable("Rule", "Keywords that are not accepted", true);
    for (ParserRule rule : identifierRules) {
      StringBuilder usedRules = new StringBuilder(SMALL_BUFFER_INITIAL_SIZE);
      StringBuilder keywordsReport = new StringBuilder(SMALL_BUFFER_INITIAL_SIZE);
      if (!uncheckedRules.contains(rule.getName())) {
        usedRules.append("<b>CHECKED ID RULE</b><br/>");

        Set<String> violations = keywordsViolatingSpec.get(rule.getName());
        if (violations == null) {
          keywordsReport.append("<h2>No violations</h2>");
        } else {
          keywordsReport.append("<h2>Violations</h2>");
          keywordsReport.append(reportKeywordListGroupedPerGrammar(violations));
        }
        keywordsReport.append("<h2>Full list of keywords not accepted</h2>");
      }
      keywordsReport.append(reportKeywordListGroupedPerGrammar(notAcceptedKeywordsPerGrammar.get(rule)));
      usedRules.append("Uses \n<ul>");
      for (AbstractRule calledRule : calledRules.get(rule)) {
        usedRules.append("\n<li>");
        usedRules.append(getLinkToIdentifierRule(calledRule));
        usedRules.append("</li>");
      }
      usedRules.append("\n</ul>");
      appendRow("<a id=\"" + rule.getName() + "\"></a>" + getLinkToRule(rule) + "<br/>" + usedRules.toString(), keywordsReport.toString());
    }
    closeTable();
  }

  /**
   * Prepares a report on identifier rules.
   *
   * @param keyWordsInIDRules
   *          keywords per ID rule, must not be {@code null}
   */
  public void reportOnKeywordsInIdentifierRulesOnly(final Map<String, Set<AbstractRule>> keyWordsInIDRules) {
    appendHeader("<a id=\"diagnostic\">Diagnostic of grammar</a>");
    StringBuilder messages = new StringBuilder(SMALL_BUFFER_INITIAL_SIZE);
    messages.append("<ul>");
    for (Entry<String, Set<AbstractRule>> keyword : keyWordsInIDRules.entrySet()) {
      messages.append("<li><font color=\"red\">Keyword \"");
      messages.append(getLinkToKeyword(keyword.getKey()));
      messages.append("\" is only declared in: ");
      for (AbstractRule rule : keyword.getValue()) {
        messages.append(getLinkToIdentifierRule(rule));
      }
      messages.append("</font></li>");
    }
    messages.append("</ul>");
    if (!keyWordsInIDRules.isEmpty()) {
      report.append("<font color=\"red\">We have found some keywords that are used only in identifier rules:</font>");
      report.append(messages.toString());
    } else {
      report.append("<ul><li>OK</li></ul>");
    }
  }

  /**
   * Generates the report part regarding keywords.
   */
  public void reportOnKeywords() {
    report.append("\n<ul><li><a href=\"#kw\">All keywords</a></li><li><a href=\"#rules\">Identifier rules</a></li><li><a href=\"#diagnostic\">Diagnostic messages</a></li></ul>");
    appendHeader("<a id=\"kw\">All keywords</a>");
    report.append(reportKeywordListGroupedPerGrammar(sortedKeywords));

    appendHeader2("Details on keywords");
    startTable("Keyword", "Rules where used", false);

    for (String keyword : sortedKeywords) {
      String leftCell = "<a id=\"" + sortedKeywords.indexOf(keyword) + "\"><kbd>" + keyword + "</kbd></a>";
      for (AbstractRule rule : keywords.get(keyword)) {
        appendRow(leftCell, getLinkToRule(rule) + '(' + GrammarUtil.getSimpleName(GrammarUtil.getGrammar(rule)) + ")");
        leftCell = HTML_SPACE;
      }
    }
    closeTable();
  }

  /**
   * Orders the given list of grammars according to inheritance.
   *
   * @param grammars
   *          Grammars, must not be {@code null}
   * @return Ordered list
   */
  private List<Grammar> orderGrammars(final Set<Grammar> grammars) {
    List<Grammar> orderedGrammars = Lists.newArrayList();
    for (Grammar oneGrammar : allGrammars) {
      if (grammars.contains(oneGrammar)) {
        orderedGrammars.add(oneGrammar);
      }
    }
    return orderedGrammars;
  }

  private String getGrammarNamesForKeyword(final String keyword) {
    Set<Grammar> grammars = Sets.newHashSet();
    for (AbstractRule rule : keywords.get(keyword)) {
      grammars.add(GrammarUtil.getGrammar(rule));
    }
    return orderGrammars(grammars).stream().map(GrammarUtil::getSimpleName).collect(Collectors.joining(COMMA_SPACE_DELIMITER));
  }

  private String reportKeywordListGroupedPerGrammar(final Collection<String> allKeywords) {
    Map<String, Set<String>> grammarToKeyword = Maps.newHashMap();
    for (String kw : allKeywords) {
      String grammarNames = getGrammarNamesForKeyword(kw);
      Set<String> keywordsForGrammar = grammarToKeyword.get(grammarNames);
      if (keywordsForGrammar == null) {
        grammarToKeyword.put(grammarNames, Sets.newHashSet(kw));
      } else {
        keywordsForGrammar.add(kw);
      }
    }
    StringBuilder rejectedKeywords = new StringBuilder(SMALL_BUFFER_INITIAL_SIZE);
    for (String grammarAbbreviation : grammarToKeyword.keySet()) {
      Set<String> keywordsForGrammar = grammarToKeyword.get(grammarAbbreviation);
      rejectedKeywords.append("<br/><b>");
      rejectedKeywords.append(grammarAbbreviation);
      rejectedKeywords.append("</b><br/>");
      rejectedKeywords.append(keywordsForGrammar.stream().sorted().map(this::getLinkToKeyword).collect(Collectors.joining(COMMA_SPACE_DELIMITER)));
    }
    return rejectedKeywords.toString();
  }

  private String getLinkToIdentifierRule(final AbstractRule rule) {
    String name = rule.getName();
    String ruleLink = "\n<a href=\"#" + name + "\"><kbd>" + name + "</kbd></a>";
    String originLink;
    if (rule instanceof ParserRule || rule instanceof EnumRule) {
      originLink = " <a target=\"xtextDoc\" href=\"" + xtextDocFileSimpleName + "#" + name + "\"><sup>Xtext</sup></a>";
    } else {
      originLink = "";
    }
    return ruleLink + originLink;
  }

  private String getLinkToRule(final AbstractRule rule) {
    String name = rule.getName();
    String originLink;
    if (rule instanceof ParserRule || rule instanceof EnumRule) {
      originLink = " <a target=\"xtextDoc\" href=\"" + xtextDocFileSimpleName + "#" + name + "\">" + name + "</a>";
    } else {
      originLink = name;
    }
    return originLink;
  }

  /**
   * Returns a link to keyword details in the report.
   *
   * @param keyword
   *          Keyword text, must not be {@code null}
   * @return Href
   */
  private String getLinkToKeyword(final String keyword) {
    return "<a href=\"#" + sortedKeywords.indexOf(keyword) + "\"><kbd>" + keyword + "</kbd></a>";
  }

  private void startHtml(final String mainGrammarName) {
    report.append("<!DOCTYPE html>\n<html>\n<body>");
    report.append("\n<h1>Keywords report for grammar " + mainGrammarName + "</h1>");
  }

  private void appendHeader(final String header) {
    report.append("\n<h2>");
    report.append(header);
    report.append("</h2>");
  }

  private void appendHeader2(final String header) {
    report.append("\n<h3>");
    report.append(header);
    report.append("</h3>");
  }

  private void startTable(final String firstColumn, final String secondColumn, final boolean border) {
    report.append("\n<table");
    if (border) {
      report.append(" border=\"1\"");
    }
    report.append("><tr><td><b>");
    report.append(firstColumn);
    report.append("</b></td><td><b>");
    report.append(secondColumn);
    report.append("</b></td></tr>");
  }

  private void appendRow(final String firstColumn, final String secondColumn) {
    report.append("\n<tr valign=\"top\"><td>");
    report.append(firstColumn);
    report.append("</td>");
    report.append("<td><kbd>");
    report.append(secondColumn);
    report.append("</kbd></td></tr>");
  }

  private void closeTable() {
    report.append("\n</table>");
  }

  private void finalizeHtml() {
    report.append("\n</body>\n</html>");
  }

  /**
   * Creates the HTML report.
   *
   * @return the string
   */
  public String build() {
    finalizeHtml();
    return report.toString();
  }
}

/* Copyright (c) Avaloq Evolution AG */