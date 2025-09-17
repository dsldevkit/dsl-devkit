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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.xtext.generator.grammarAccess.GrammarAccessExtensions;

import com.google.common.base.Strings;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;


/**
 * Helper class performing analysis of keywords in the grammar.
 */
@SuppressWarnings({"nls", "PMD.NPathComplexity"})
public final class KeywordAnalysisHelper {

  private static final int WORDS_PADDING = 20;
  private static final int WORDS_PER_ROW = 4;

  private static final Logger LOGGER = LogManager.getLogger(KeywordAnalysisHelper.class);

  private final String parserGrammarFileName;
  private final Grammar grammar;
  private final List<ParserRule> parserRules;
  private final List<EnumRule> enumRules;
  private final Map<String, Set<AbstractRule>> keywords;
  private final List<String> sortedKeywords;
  private final GrammarAnalysisReportBuilder report;
  private final Set<String> identifierRules;
  private final Set<String> reservedWords;
  private final Set<String> keywordsSpec;
  private final boolean checkReservedWords;
  private final GrammarAccessExtensions grammarExtensions;

  // diagnostic report
  private final Map<String, Set<String>> keywordsViolatingSpec = Maps.newHashMap();
  private final Map<String, Set<String>> keywordsSofterThanSpec = Maps.newHashMap();
  private final Set<String> uncheckedRules = Sets.newHashSet();

  /**
   * Creates a new instance of {@link KeywordAnalysisHelper}.
   *
   * @param parserGrammarFileName
   *          Parser grammar filename used in generating reports, must not be {@code null}
   * @param grammar
   *          Grammar, must not be {@code null}
   * @param identifierRules
   *          rules to check for compliance with language specification, must not be {@code null}
   * @param reservedWords
   *          reserved words in the language specification, must not be {@code null}
   * @param keywordsSpec
   *          keywords in the language specification, must not be {@code null}
   * @param grammarExtensions
   *          extensions used to create Combined Grammar Report, must not be {@code null}
   */
  public KeywordAnalysisHelper(final String parserGrammarFileName, final Grammar grammar, final Set<String> identifierRules, final Set<String> reservedWords, final Set<String> keywordsSpec, final GrammarAccessExtensions grammarExtensions) {
    this.parserGrammarFileName = parserGrammarFileName;
    this.grammar = grammar;
    this.identifierRules = identifierRules;
    this.reservedWords = reservedWords;
    this.keywordsSpec = keywordsSpec;
    this.checkReservedWords = reservedWords != null && identifierRules != null && !reservedWords.isEmpty();
    this.grammarExtensions = grammarExtensions;
    parserRules = GrammarUtil.allParserRules(grammar);
    enumRules = GrammarUtil.allEnumRules(grammar);
    Iterator<EObject> iter = Iterators.concat(EcoreUtil.<EObject> getAllContents(parserRules), EcoreUtil.<EObject> getAllContents(enumRules));
    Iterator<Keyword> filtered = Iterators.filter(iter, Keyword.class);
    keywords = Maps.newHashMap();
    while (filtered.hasNext()) {
      Keyword keyword = filtered.next();
      String text = keyword.getValue().toLowerCase(Locale.ENGLISH);
      AbstractRule rule = GrammarUtil.containingRule(keyword);
      Set<AbstractRule> rules = keywords.get(text);
      if (rules == null) {
        rules = Sets.newLinkedHashSet();
        keywords.put(text, rules);
      }
      rules.add(rule);
    }
    sortedKeywords = Ordering.natural().sortedCopy(keywords.keySet());
    report = new GrammarAnalysisReportBuilder(GrammarUtil.getSimpleName(grammar), getDocFileSimpleName(), sortedKeywords, keywords, getAllGrammars());
    reportOnKeywords();
    reportOnIdentifierRules();
    reportOnKeywordsInIdentifierRulesOnly();
  }

  /**
   * Prepares a report on identifier rules.
   */
  private void reportOnIdentifierRules() {
    List<ParserRule> suspectIdentifierRules = Lists.newArrayList();
    for (ParserRule rule : parserRules) {
      if (isIdentifierRule(rule, Sets.<AbstractRule> newHashSet())) {
        suspectIdentifierRules.add(rule);
      }
    }
    Map<ParserRule, Set<AbstractRule>> calledRules = Maps.newHashMap();
    Map<ParserRule, List<String>> notAcceptedKeywordsPerGrammar = Maps.newHashMap();
    for (ParserRule rule : suspectIdentifierRules) {
      Set<String> keywordsWithLetters = sortedKeywords.stream().filter(this::hasLetters).collect(Collectors.toSet());
      List<String> notAcceptedKeywords = Ordering.natural().sortedCopy(getNotAcceptedKeywords(rule, keywordsWithLetters, Sets.<AbstractRule> newHashSet()));
      notAcceptedKeywordsPerGrammar.put(rule, notAcceptedKeywords);
      if (checkReservedWords) {
        String ruleName = rule.getName();
        if (identifierRules.contains(ruleName)) {
          Set<String> violations = keywordsViolatingSpec.computeIfAbsent(ruleName, v -> Sets.newHashSet());
          for (String keyword : notAcceptedKeywords) {
            if (!reservedWords.contains(keyword)) {
              violations.add(keyword);
            }
          }
          keywordsSofterThanSpec.put(ruleName, Sets.difference(reservedWords, Sets.newHashSet(notAcceptedKeywords)));
        } else {
          uncheckedRules.add(ruleName);
        }
      }
      Set<AbstractRule> calledRulesSet = Sets.newLinkedHashSet();
      for (AbstractRule calledRule : getCalledRules(rule)) {
        calledRulesSet.add(calledRule);
      }
      calledRules.put(rule, calledRulesSet);
    }
    report.reportOnIdentifierRules(suspectIdentifierRules, calledRules, notAcceptedKeywordsPerGrammar, uncheckedRules, keywordsViolatingSpec);
  }

  private boolean hasLetters(final String keyword) {
    return keyword.chars().anyMatch(Character::isLetter);
  }

  /**
   * Prints the violations.
   *
   * @param srcGenPath
   *          path to src-gen folder of the plugin being generated
   */
  public void printViolations(final String srcGenPath) {
    try {
      String fileName = getKeywordsDiagnosticReportFileName(srcGenPath);
      PrintWriter writer = new PrintWriter(new File(fileName), StandardCharsets.UTF_8);
      writer.println("Please check in this file, so a diff can be used to detect unexpected changes");
      writer.println();
      writer.println("  identifiers rejected    - are not listed in MWE2 file as reserved words");
      writer.println("                            (or keywords), but are not accepted by the rule.");
      writer.println("  reserved words accepted - listed in MWE2 as reserved, but accepted.");
      writer.println();
      writer.println("Read more on https://ddk.tools.avaloq.com/keywords.html");
      writer.println();
      writer.println("Specification");
      writer.println();
      writer.println("RESERVED WORDS");
      int count = 0;
      String indent = "        ";
      for (String word : reservedWords) {
        if (count++ % WORDS_PER_ROW == 0) {
          writer.println();
          writer.print(indent);
        }
        writer.print(Strings.padEnd(word, WORDS_PADDING, ' '));
      }
      writer.println();
      writer.println();
      writer.println("KEYWORDS");
      count = 0;
      for (String word : keywordsSpec) {
        if (count++ % WORDS_PER_ROW == 0) {
          writer.println();
          writer.print(indent);
        }
        writer.print(Strings.padEnd(word, WORDS_PADDING, ' '));
      }
      writer.println();
      boolean problemsReported = false;
      for (String ruleName : keywordsViolatingSpec.keySet()) {
        writer.println();
        writer.print("RULE: ");
        writer.println(ruleName);
        Set<String> kwSet = keywordsViolatingSpec.get(ruleName);
        writer.println("    Identifiers rejected (PROBLEM if non empty): ");
        for (String kw : Sets.difference(kwSet, keywordsSpec)) {
          problemsReported = true; // only rejected are real problems
          writer.print(indent);
          writer.println(kw);
        }
        writer.println();
        Set<String> rwSet = keywordsSofterThanSpec.get(ruleName);
        writer.println("    Reserved words rejected: ");
        for (String kw : Sets.difference(reservedWords, rwSet)) {
          writer.print(indent);
          writer.println(kw);
        }
        writer.println();
        writer.println("    Reserved words accepted: ");
        for (String kw : rwSet) {
          writer.print(indent);
          writer.println(kw);
        }
        writer.println();
        writer.println("    Keywords rejected: ");
        for (String kw : Sets.intersection(keywordsSpec, kwSet)) {
          writer.print(indent);
          writer.println(kw);
        }
        writer.println();
        writer.println("    Keywords accepted: ");
        for (String kw : Sets.difference(keywordsSpec, kwSet)) {
          writer.print(indent);
          writer.println(kw);
        }
        writer.println();
      }
      if (problemsReported) {
        LOGGER.error("REJECTED KEYWORDS BY ID RULES DETECTED.");
        LOGGER.error("Read " + fileName + " !");
        writer.println();
        writer.println("(!) Problems were detected: neither reserved words nor keywords, but rejected by identifier rules");
        writer.println("Use " + Path.fromPortableString(getReportFileName(srcGenPath)).lastSegment() + " to find out why these words are keywords.");
      }
      writer.println();
      writer.println("The following rules were not checked, but might also be relevant");
      for (String ruleName : uncheckedRules) {
        writer.print(indent);
        writer.println(ruleName);
      }
      writer.println("if any of them is used to parse identifiers, add them to identifierRules in MWE2 file");
      writer.close();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }

  }

  /**
   * Prepares a report on identifier rules.
   */
  private void reportOnKeywordsInIdentifierRulesOnly() {
    Map<String, Set<AbstractRule>> keyWordsInIDRules = Maps.newHashMap();
    for (Entry<String, Set<AbstractRule>> keyword : keywords.entrySet()) {
      if (hasLetters(keyword.getKey())) {
        boolean idRulesOnly = true;
        for (AbstractRule rule : keyword.getValue()) {
          if (!isIdentifierRule(rule, Sets.<AbstractRule> newHashSet())) {
            idRulesOnly = false;
            break;
          }
        }
        if (idRulesOnly) {
          keyWordsInIDRules.put(keyword.getKey(), keyword.getValue());
        }
      }
    }
    report.reportOnKeywordsInIdentifierRulesOnly(keyWordsInIDRules);
  }

  /**
   * Searches for keywords from the given set that are not accepted by the data rule.
   * <p>
   * We do not do a exact analysis yet, but simply look at all keywords mentioned in the reachable rule.
   * </p>
   *
   * @param rule
   *          Rule
   * @param originalKeywords
   *          Original set of keywords
   * @param visitedRules
   *          Loop prevention
   * @return Remaining keywords
   */
  private Set<String> getNotAcceptedKeywords(final AbstractRule rule, final Set<String> originalKeywords, final Set<AbstractRule> visitedRules) {
    if (visitedRules.contains(rule) || rule instanceof TerminalRule) {
      return originalKeywords;
    }
    Set<String> remaining = Sets.newHashSet(originalKeywords);
    for (Keyword keyword : GrammarUtil.containedKeywords(rule)) {
      remaining.remove(keyword.getValue().toLowerCase(Locale.ENGLISH));
    }
    final Set<AbstractRule> visited = Sets.newHashSet(visitedRules);
    visited.add(rule);
    for (RuleCall call : GrammarUtil.containedRuleCalls(rule)) {
      AbstractRule calledRule = call.getRule();
      remaining = Sets.intersection(remaining, getNotAcceptedKeywords(calledRule, originalKeywords, visited));
    }
    return remaining;
  }

  /**
   * Finds all rules called indirectly from this rule.
   *
   * @param startRule
   *          Rule, must not be {@code null}
   * @return Remaining keywords
   */
  private Set<AbstractRule> getCalledRules(final AbstractRule startRule) {
    Set<AbstractRule> found = Sets.newLinkedHashSet();
    List<AbstractRule> toVisit = Lists.newLinkedList();
    toVisit.add(startRule);
    while (!toVisit.isEmpty()) {
      AbstractRule rule = toVisit.remove(0);
      for (RuleCall call : GrammarUtil.containedRuleCalls(rule)) {
        AbstractRule calledRule = call.getRule();
        if (found.add(calledRule)) {
          toVisit.add(calledRule);
        }
      }
    }
    return found;
  }

  /**
   * Rule is an identifier rule if it is data type rule and contains a call to an ID rule or to another datatype rule.
   *
   * @param rule
   *          Rule to check, must not be {@code null}
   * @param visitedRules
   *          Loop prevention
   * @return {@code true} if the rule is an identifier rule
   */
  private boolean isIdentifierRule(final AbstractRule rule, final Set<AbstractRule> visitedRules) {
    if (!GrammarUtil.isDatatypeRule(rule) || visitedRules.contains(rule)) {
      return false;
    }
    final Set<AbstractRule> visited = Sets.newHashSet(visitedRules);
    visited.add(rule);
    for (RuleCall call : GrammarUtil.containedRuleCalls(rule)) {
      AbstractRule calledRule = call.getRule();
      if (calledRule instanceof TerminalRule) {
        TerminalRule terminal = (TerminalRule) calledRule;
        String name = terminal.getName();
        if (name.endsWith("ID")) {
          return true;
        }
      }
      if (isIdentifierRule(calledRule, visited)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Generates the report part regarding keywords.
   */
  private void reportOnKeywords() {
    final Map<Grammar, Set<String>> keywordsPerGrammar = Maps.newHashMap();
    for (Grammar oneGrammar : getAllGrammars()) {
      Set<String> grammarKeywords = Sets.newHashSet();
      for (String keyword : sortedKeywords) {
        if (isKeywordDeclaredInGrammar(keyword, oneGrammar)) {
          grammarKeywords.add(keyword);
        }
      }
      keywordsPerGrammar.put(grammar, grammarKeywords);
    }
    report.reportOnKeywords();
  }

  /**
   * Checks whether a keyword is used in a reachable rule in the grammar specified.
   *
   * @param keyword
   *          Keyword, must not be {@code null}
   * @param expectedGrammar
   *          Grammar
   * @return {@code true} if keyword is used in the given grammar
   */
  private boolean isKeywordDeclaredInGrammar(final String keyword, final Grammar expectedGrammar) {
    for (AbstractRule rule : keywords.get(keyword)) {
      if (GrammarUtil.getGrammar(rule).equals(expectedGrammar)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the list of all grammars involved order according to inheritance. Current grammar is the first one.
   *
   * @return Ordered list of grammars
   */
  public List<Grammar> getAllGrammars() {
    final List<Grammar> allGrammars = Lists.newLinkedList();
    allGrammars.add(grammar);
    allGrammars.addAll(GrammarUtil.allUsedGrammars(grammar));
    return allGrammars;
  }

  /**
   * Prints report to a file.
   *
   * @param srcGenPath
   *          Location for generated sources
   */
  public void printReport(final String srcGenPath) {
    try {
      String fileName = getReportFileName(srcGenPath);
      PrintWriter writer = new PrintWriter(new File(fileName), StandardCharsets.UTF_8);
      writer.print(report.build());
      writer.close();
      String docuFileName = getDocFileName(srcGenPath);
      PrintWriter docuWriter = new PrintWriter(new File(docuFileName), StandardCharsets.UTF_8);
      docuWriter.print(new CombinedGrammarReportBuilder(grammarExtensions).getDocumentation(grammar, parserRules, enumRules));
      docuWriter.close();
      LOGGER.info("report on keywords is written into " + fileName);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  /**
   * Name of the file to write plain text report on usage of keywords.
   *
   * @param srcGenPath
   *          Folder with generated code
   * @return File name
   */
  private String getKeywordsDiagnosticReportFileName(final String srcGenPath) {
    return srcGenPath + "/" + Path.fromPortableString(getAntlrrFileName()).removeLastSegments(1).append("KeywordsReport").addFileExtension("txt");
  }

  private String getAntlrrFileName() {
    return parserGrammarFileName.replace(".g", "");
  }

  /**
   * Name of the file with the generated report.
   *
   * @param srcGenPath
   *          Folder with generated code
   * @return File name
   */
  private String getReportFileName(final String srcGenPath) {
    return srcGenPath + "/" + getAntlrrFileName() + "AnalysisReport.html";
  }

  /**
   * Name of the file with the generated documentation.
   *
   * @param srcGenPath
   *          Folder with generated docu
   * @return File name
   */
  private String getDocFileName(final String srcGenPath) {
    return srcGenPath + "/" + getDocFileRelativeName();
  }

  /**
   * Simple name of the file with the generated documentation.
   *
   * @return Simple file name
   */
  private String getDocFileSimpleName() {
    return Path.fromPortableString(getDocFileRelativeName()).lastSegment();
  }

  /**
   * Name of the file with the generated documentation without the gen folder prefix.
   *
   * @return File name
   */
  private String getDocFileRelativeName() {
    return getAntlrrFileName() + "CombinedGrammar.html";
  }
}

/* Copyright (c) Avaloq Group AG */
