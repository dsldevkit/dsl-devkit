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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.xtext.FlattenedGrammarAccess;
import org.eclipse.xtext.xtext.RuleFilter;
import org.eclipse.xtext.xtext.RuleNames;
import org.eclipse.xtext.xtext.generator.CodeConfig;
import org.eclipse.xtext.xtext.generator.model.IXtextGeneratorFileSystemAccess;
import org.eclipse.xtext.xtext.generator.parser.antlr.AbstractAntlrGrammarWithActionsGenerator;
import org.eclipse.xtext.xtext.generator.parser.antlr.AntlrGrammarGenUtil;
import org.eclipse.xtext.xtext.generator.parser.antlr.AntlrOptions;
import org.eclipse.xtext.xtext.generator.parser.antlr.CombinedGrammarMarker;
import org.eclipse.xtext.xtext.generator.parser.antlr.KeywordHelper;

import com.avaloq.tools.ddk.xtext.generator.parser.common.GrammarRuleAnnotations;
import com.avaloq.tools.ddk.xtext.generator.parser.common.PredicatesNaming;
import com.google.common.collect.Iterators;
import com.google.inject.Inject;


public abstract class AbstractAnnotationAwareAntlrGrammarGenerator extends AbstractAntlrGrammarWithActionsGenerator {

  @Inject
  protected GrammarRuleAnnotations annotations;

  @Inject
  protected PredicatesNaming predicatesNaming;

  @Inject
  private CodeConfig codeConfig;

  private Grammar originalGrammar;

  @Override
  public void generate(Grammar it, AntlrOptions options, IXtextGeneratorFileSystemAccess fsa) {
    this.keywordHelper = KeywordHelper.getHelper(it);
    this.originalGrammar = it;
    RuleFilter filter = new RuleFilter();
    filter.setDiscardUnreachableRules(true); // options.skipUnusedRules
    filter.setDiscardTerminalRules(false); // options.skipUnusedRules
    RuleNames ruleNames = RuleNames.getRuleNames(it, true);
    Grammar flattened = new FlattenedGrammarAccess(ruleNames, filter).getFlattenedGrammar();
    new CombinedGrammarMarker(isCombinedGrammar()).attachToEmfObject(flattened);
    fsa.generateFile(getGrammarNaming().getParserGrammar(it).getGrammarFileName(), compileParser(flattened, options));
    if (!isCombinedGrammar()) {
      fsa.generateFile(getGrammarNaming().getLexerGrammar(it).getGrammarFileName(), compileLexer(flattened, options));
    }
  }

  @Override
  protected boolean isCombinedGrammar() {
    return getGrammarNaming().isCombinedGrammar(originalGrammar);
  }

  @Override
  protected CharSequence compileLexer(Grammar it, AntlrOptions options) {
    StringConcatenation sb = new StringConcatenation();
    sb.append(codeConfig.getFileHeader());
    sb.newLineIfNotEmpty();
    sb.append("lexer grammar ");
    sb.append(getGrammarNaming().getLexerGrammar(it).getSimpleName());
    sb.append(";");
    sb.newLineIfNotEmpty();
    sb.append(compileLexerOptions(it, options));
    sb.newLineIfNotEmpty();
    sb.append(compileTokens(it, options));
    sb.newLineIfNotEmpty();
    sb.append(compileLexerHeader(it, options));
    sb.newLineIfNotEmpty();
    sb.append(compileLexerMembers(it, options));
    sb.newLineIfNotEmpty();
    sb.append(compileKeywordRules(it, options));
    sb.newLineIfNotEmpty();
    sb.append(compileTerminalRules(it, options));
    sb.newLineIfNotEmpty();
    return sb;
  }

  protected CharSequence compileLexerMembers(Grammar it, AntlrOptions options) {
    StringConcatenation sb = new StringConcatenation();
    sb.append("@members {");
    sb.newLine();
    sb.append("  ");
    sb.append("protected int getSingleLineCommentRule() {");
    sb.newLine();
    sb.append("    ");
    sb.append("return RULE_SL_COMMENT;");
    sb.newLine();
    sb.append("  ");
    sb.append("}");
    sb.newLine();
    sb.newLine();
    sb.append("  ");
    sb.append("protected int getMultiLineCommentRule() {");
    sb.newLine();
    sb.append("    ");
    sb.append("return RULE_ML_COMMENT;");
    sb.newLine();
    sb.append("  ");
    sb.append("}");
    sb.newLine();
    sb.newLine();
    sb.append("  ");
    sb.append("protected int getEndOfFileRule() {");
    sb.newLine();
    sb.append("    ");
    sb.append("return EOF;");
    sb.newLine();
    sb.append("  ");
    sb.append("}");
    sb.newLine();
    sb.append("}");
    sb.newLine();
    return sb;
  }

  @Override
  protected String compileParserImports(Grammar it, AntlrOptions options) {
    StringConcatenation sb = new StringConcatenation();
    sb.append("import ");
    sb.append(predicatesNaming.getSemanticPredicatesFullName(it));
    sb.append(";");
    sb.newLineIfNotEmpty();
    sb.append("import com.avaloq.tools.ddk.xtext.parser.antlr.ParserContext;");
    sb.newLine();
    return sb.toString();
  }

  protected CharSequence compileParserMemberDeclarations(Grammar it, String access) {
    StringConcatenation sb = new StringConcatenation();
    sb.append(access);
    sb.append(" ");
    sb.append(_grammarAccessExtensions.getGrammarAccess(it).getSimpleName());
    sb.append(" grammarAccess;");
    sb.newLineIfNotEmpty();
    sb.append(access);
    sb.append(" ");
    sb.append(predicatesNaming.getSemanticPredicatesSimpleName(it));
    sb.append(" predicates;");
    sb.newLineIfNotEmpty();
    sb.append(access);
    sb.append(" ParserContext parserContext;");
    sb.newLineIfNotEmpty();
    return sb;
  }

  protected CharSequence compileParserSetTokenStreamMethod() {
    StringConcatenation sb = new StringConcatenation();
    sb.append("/**");
    sb.newLine();
    sb.append(" * Set token stream in parser context.");
    sb.newLine();
    sb.append(" * @param input Token stream");
    sb.newLine();
    sb.append(" */");
    sb.newLine();
    sb.append("@Override");
    sb.newLine();
    sb.append("public void setTokenStream(TokenStream input) {");
    sb.newLine();
    sb.append("  ");
    sb.append("super.setTokenStream(input);");
    sb.newLine();
    sb.append("  ");
    sb.append("if(parserContext != null){");
    sb.newLine();
    sb.append("    ");
    sb.append("parserContext.setTokenStream(input);");
    sb.newLine();
    sb.append("  ");
    sb.append("}");
    sb.newLine();
    sb.append("}");
    sb.newLine();
    return sb;
  }

  @Override
  protected CharSequence compileKeywordRules(Grammar it, AntlrOptions options) {
    // implementation from xtext, but keywords are from the given grammar only (which has been flattened and filtered correctly)
    Set<String> allKeywords = getAllKeywords(it, options);
    List<TerminalRule> allTerminalRules = GrammarUtil.allTerminalRules(it);

    List<String> syntheticKwAlternatives = new ArrayList<>();
    for (String kw : allKeywords) {
      String ruleName = keywordHelper.getRuleName(kw);
      syntheticKwAlternatives.add("(FRAGMENT_" + ruleName + ")=> FRAGMENT_" + ruleName + " {$type = " + ruleName + "; }");
    }
    for (AbstractRule rule : allTerminalRules) {
      if (rule instanceof TerminalRule) {
        TerminalRule terminalRule = (TerminalRule) rule;
        if (!_syntheticTerminalDetector.isSyntheticTerminalRule(terminalRule) && !terminalRule.isFragment()) {
          syntheticKwAlternatives.add("(FRAGMENT_" + AntlrGrammarGenUtil.getRuleName(rule) + ")=> FRAGMENT_" + AntlrGrammarGenUtil.getRuleName(rule) + " {$type = " + AntlrGrammarGenUtil.getRuleName(rule) + "; }");
        }
      }
    }

    StringConcatenation sb = new StringConcatenation();
    if (options.isBacktrackLexer()) {
      sb.append("SYNTHETIC_ALL_KEYWORDS :");
      sb.newLine();
      for (int i = 0; i < syntheticKwAlternatives.size(); i++) {
        sb.append("  ");
        if (i > 0) {
          sb.append("| ");
        }
        sb.append(syntheticKwAlternatives.get(i));
        sb.newLine();
      }
      sb.append(";");
      sb.newLine();
      for (String kw : allKeywords) {
        sb.append("fragment FRAGMENT_");
        sb.append(keywordHelper.getRuleName(kw));
        sb.append(" : \'");
        sb.append(AntlrGrammarGenUtil.toAntlrString(kw));
        sb.append("\';");
        sb.newLine();
      }
    } else {
      for (String rule : allKeywords) {
        sb.append(compileRule(rule, it, options));
        sb.newLineIfNotEmpty();
      }
    }
    return sb;
  }

  private static Set<String> getAllKeywords(Grammar flattenedGrammar, AntlrOptions options) {
    Set<String> result = new TreeSet<>(KeywordHelper.keywordComparator);
    List<? extends AbstractRule> parserRules = GrammarUtil.allParserRules(flattenedGrammar);
    List<? extends AbstractRule> enumRules = GrammarUtil.allEnumRules(flattenedGrammar);
    Iterator<EObject> iter = Iterators.concat(EcoreUtil.<EObject>getAllContents(parserRules), EcoreUtil.<EObject>getAllContents(enumRules));
    Iterators.addAll(result, Iterators.transform(
      Iterators.filter(iter, Keyword.class),
      kw -> options.isIgnoreCase() ? kw.getValue().toLowerCase(Locale.ENGLISH) : kw.getValue()
    ));
    return Collections.unmodifiableSet(result);
  }

}
