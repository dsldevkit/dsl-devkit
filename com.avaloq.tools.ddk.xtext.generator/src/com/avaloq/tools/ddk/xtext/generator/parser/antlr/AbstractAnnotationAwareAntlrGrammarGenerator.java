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
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
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

// CHECKSTYLE:CONSTANTS-OFF

@SuppressWarnings({"PMD.UnusedFormalParameter", "nls"})
public abstract class AbstractAnnotationAwareAntlrGrammarGenerator extends AbstractAntlrGrammarWithActionsGenerator {

  // CHECKSTYLE:CHECK-OFF VisibilityModifier
  @Inject
  protected GrammarRuleAnnotations annotations;

  @Inject
  protected PredicatesNaming predicatesNaming;
  // CHECKSTYLE:CHECK-ON VisibilityModifier

  @Inject
  private CodeConfig codeConfig;

  private Grammar originalGrammar;

  @Override
  public void generate(final Grammar it, final AntlrOptions options, final IXtextGeneratorFileSystemAccess fsa) {
    this.keywordHelper = KeywordHelper.getHelper(it);
    this.originalGrammar = it;
    final RuleFilter filter = new RuleFilter();
    filter.setDiscardUnreachableRules(true); // options.skipUnusedRules
    filter.setDiscardTerminalRules(false); // options.skipUnusedRules
    final RuleNames ruleNames = RuleNames.getRuleNames(it, true);
    final Grammar flattened = new FlattenedGrammarAccess(ruleNames, filter).getFlattenedGrammar();
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
  protected CharSequence compileLexer(final Grammar it, final AntlrOptions options) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append(codeConfig.getFileHeader());
    builder.newLineIfNotEmpty();
    builder.append("lexer grammar " + getGrammarNaming().getLexerGrammar(it).getSimpleName() + ";");
    builder.newLine();
    builder.append(compileLexerOptions(it, options));
    builder.newLineIfNotEmpty();
    builder.append(compileTokens(it, options));
    builder.newLineIfNotEmpty();
    builder.append(compileLexerHeader(it, options));
    builder.newLineIfNotEmpty();
    builder.append(compileLexerMembers(it, options));
    builder.newLineIfNotEmpty();
    builder.append(compileKeywordRules(it, options));
    builder.newLineIfNotEmpty();
    builder.append(compileTerminalRules(it, options));
    builder.newLineIfNotEmpty();
    return builder;
  }

  protected CharSequence compileLexerMembers(final Grammar it, final AntlrOptions options) {
    return """
        @members {
          protected int getSingleLineCommentRule() {
            return RULE_SL_COMMENT;
          }

          protected int getMultiLineCommentRule() {
            return RULE_ML_COMMENT;
          }

          protected int getEndOfFileRule() {
            return EOF;
          }
        }
        """;
  }

  @Override
  protected String compileParserImports(final Grammar it, final AntlrOptions options) {
    return """
        import %s;
        import com.avaloq.tools.ddk.xtext.parser.antlr.ParserContext;
        """.formatted(predicatesNaming.getSemanticPredicatesFullName(GrammarUtil.getGrammar(it)));
  }

  protected CharSequence compileParserMemberDeclarations(final Grammar it, final String access) {
    return """
        %s %s grammarAccess;
        %s %s predicates;
        %s ParserContext parserContext;
        """.formatted(access, _grammarAccessExtensions.getGrammarAccess(it).getSimpleName(), access, predicatesNaming.getSemanticPredicatesSimpleName(it), access);
  }

  protected CharSequence compileParserSetTokenStreamMethod() {
    return """
        /**
         * Set token stream in parser context.
         * @param input Token stream
         */
        @Override
        public void setTokenStream(TokenStream input) {
          super.setTokenStream(input);
          if(parserContext != null){
            parserContext.setTokenStream(input);
          }
        }
        """;
  }

  @Override
  protected CharSequence compileKeywordRules(final Grammar it, final AntlrOptions options) {
    // implementation from xtext, but keywords are from the given grammar only (which has been flattened and filtered correctly)
    final Set<String> allKeywords = getAllKeywords(it, options);
    final List<TerminalRule> allTerminalRules = GrammarUtil.allTerminalRules(it);

    final List<String> syntheticKwAlternatives = new ArrayList<>();
    for (final String kw : allKeywords) {
      final String ruleName = keywordHelper.getRuleName(kw);
      syntheticKwAlternatives.add("(FRAGMENT_" + ruleName + ")=> FRAGMENT_" + ruleName + " {$type = " + ruleName + "; }");
    }
    for (final TerminalRule terminalRule : allTerminalRules) {
      if (!_syntheticTerminalDetector.isSyntheticTerminalRule(terminalRule) && !terminalRule.isFragment()) {
        final String ruleName = _grammarAccessExtensions.ruleName(terminalRule);
        syntheticKwAlternatives.add("(FRAGMENT_" + ruleName + ")=> FRAGMENT_" + ruleName + " {$type = " + ruleName + "; }");
      }
    }

    final StringConcatenation builder = new StringConcatenation();
    if (options.isBacktrackLexer()) {
      builder.append("SYNTHETIC_ALL_KEYWORDS :");
      builder.newLine();
      boolean hasElements = false;
      for (final String kw : syntheticKwAlternatives) {
        if (!hasElements) {
          hasElements = true;
        } else {
          builder.appendImmediate(" |", "  ");
        }
        builder.append("  ");
        builder.append(kw, "  ");
        builder.newLineIfNotEmpty();
      }
      builder.append(";");
      builder.newLine();
      for (final String kw : allKeywords) {
        builder.append("fragment FRAGMENT_%s : '%s';".formatted(keywordHelper.getRuleName(kw), AntlrGrammarGenUtil.toAntlrString(kw)));
        builder.newLine();
      }
    } else {
      for (final String rule : allKeywords) {
        builder.append(compileRule(rule, it, options));
        builder.newLineIfNotEmpty();
      }
    }
    return builder;
  }

  private static Set<String> getAllKeywords(final Grammar flattenedGrammar, final AntlrOptions options) {
    final Set<String> result = new TreeSet<>(KeywordHelper.keywordComparator);
    final List<ParserRule> parserRules = GrammarUtil.allParserRules(flattenedGrammar);
    final List<EnumRule> enumRules = GrammarUtil.allEnumRules(flattenedGrammar);
    final Iterator<EObject> iter = Iterators.concat(EcoreUtil.<EObject>getAllContents(parserRules), EcoreUtil.<EObject>getAllContents(enumRules));
    Iterators.addAll(result, Iterators.transform(
        Iterators.filter(iter, Keyword.class),
        kw -> options.isIgnoreCase() ? kw.getValue().toLowerCase(Locale.ENGLISH) : kw.getValue()));
    return Collections.unmodifiableSet(result);
  }

}
// CHECKSTYLE:CONSTANTS-ON
