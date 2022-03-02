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

package com.avaloq.tools.ddk.xtext.generator.parser.antlr

import com.avaloq.tools.ddk.xtext.generator.parser.common.GrammarRuleAnnotations
import com.avaloq.tools.ddk.xtext.generator.parser.common.PredicatesNaming
import com.google.common.collect.Iterators
import com.google.inject.Inject
import java.util.Collections
import java.util.Locale
import java.util.TreeSet
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.Grammar
import org.eclipse.xtext.GrammarUtil
import org.eclipse.xtext.Keyword
import org.eclipse.xtext.xtext.FlattenedGrammarAccess
import org.eclipse.xtext.xtext.RuleFilter
import org.eclipse.xtext.xtext.RuleNames
import org.eclipse.xtext.xtext.generator.CodeConfig
import org.eclipse.xtext.xtext.generator.model.IXtextGeneratorFileSystemAccess
import org.eclipse.xtext.xtext.generator.parser.antlr.AbstractAntlrGrammarWithActionsGenerator
import org.eclipse.xtext.xtext.generator.parser.antlr.AntlrOptions
import org.eclipse.xtext.xtext.generator.parser.antlr.CombinedGrammarMarker
import org.eclipse.xtext.xtext.generator.parser.antlr.KeywordHelper

import static extension org.eclipse.xtext.GrammarUtil.*
import static extension org.eclipse.xtext.xtext.generator.parser.antlr.AntlrGrammarGenUtil.*

abstract class AbstractAnnotationAwareAntlrGrammarGenerator extends AbstractAntlrGrammarWithActionsGenerator {
  @Inject protected extension GrammarRuleAnnotations annotations
  @Inject protected extension PredicatesNaming predicatesNaming
  @Inject CodeConfig codeConfig

  Grammar originalGrammar

  override generate(Grammar it, AntlrOptions options, IXtextGeneratorFileSystemAccess fsa) {
    this.keywordHelper = KeywordHelper.getHelper(it)
    this.originalGrammar = it
    val RuleFilter filter = new RuleFilter();
    filter.discardUnreachableRules = true // options.skipUnusedRules
    filter.discardTerminalRules = false // options.skipUnusedRules
    val RuleNames ruleNames = RuleNames.getRuleNames(it, true);
    val Grammar flattened = new FlattenedGrammarAccess(ruleNames, filter).getFlattenedGrammar();
    new CombinedGrammarMarker(combinedGrammar).attachToEmfObject(flattened)
    fsa.generateFile(grammarNaming.getParserGrammar(it).grammarFileName, flattened.compileParser(options))
    if (!isCombinedGrammar) {
      fsa.generateFile(grammarNaming.getLexerGrammar(it).grammarFileName, flattened.compileLexer(options))
    }
  }

  protected override isCombinedGrammar() {
    grammarNaming.isCombinedGrammar(originalGrammar)
  }

  protected override compileLexer(Grammar it, AntlrOptions options) '''
    «codeConfig.fileHeader»
    lexer grammar «grammarNaming.getLexerGrammar(it).simpleName»;
    «compileLexerOptions(options)»
    «compileTokens(options)»
    «compileLexerHeader(options)»
    «compileLexerMembers(options)»
    «compileKeywordRules(options)»
    «compileTerminalRules(options)»
  '''

  protected def compileLexerMembers(Grammar it, AntlrOptions options) '''
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
  '''

  protected override compileParserImports(Grammar it, AntlrOptions options) '''
    import «grammar.semanticPredicatesFullName»;
    import com.avaloq.tools.ddk.xtext.parser.antlr.ParserContext;
  '''

  protected def compileParserMemberDeclarations(Grammar it, String access) '''
    «access» «grammarAccess.simpleName» grammarAccess;
    «access» «getSemanticPredicatesSimpleName()» predicates;
    «access» ParserContext parserContext;
  '''

  protected def compileParserSetTokenStreamMethod() '''
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
  '''

  protected override compileKeywordRules(Grammar it, AntlrOptions options) {
    // implementation from xtext, but keywords are from the given grammar only (which has been flattened and filtered correctly)
    val allKeywords = getAllKeywords(options)
    val allTerminalRules = allTerminalRules

    val synthetic_kw_alternatives = newArrayList
    synthetic_kw_alternatives.addAll(allKeywords.indexed.map[
      val ruleName = keywordHelper.getRuleName(value)
      return '''(FRAGMENT_«ruleName»)=> FRAGMENT_«ruleName» {$type = «ruleName»; }'''
    ])
    synthetic_kw_alternatives.addAll(allTerminalRules.indexed.map[
      if (!isSyntheticTerminalRule(value) && !value.fragment) {
        return '''(FRAGMENT_«value.ruleName»)=> FRAGMENT_«value.ruleName» {$type = «value.ruleName»; }'''
      }
    ].filterNull.toList)
    '''
      «IF options.isBacktrackLexer»
        SYNTHETIC_ALL_KEYWORDS :
          «FOR kw: synthetic_kw_alternatives SEPARATOR ' |'»
            «kw»
          «ENDFOR»
        ;
        «FOR kw:  allKeywords»
          fragment FRAGMENT_«keywordHelper.getRuleName(kw)» : '«kw.toAntlrString()»';
        «ENDFOR»
      «ELSE»
        «FOR rule:allKeywords»
          «rule.compileRule(it, options)»
        «ENDFOR»
      «ENDIF»
    '''
  }

  private static def getAllKeywords(Grammar flattenedGrammar, AntlrOptions options) {
    val result = new TreeSet<String>(KeywordHelper.keywordComparator)
    val parserRules=GrammarUtil.allParserRules(flattenedGrammar)
    val enumRules=GrammarUtil.allEnumRules(flattenedGrammar)
    val iter=Iterators.concat(EcoreUtil.<EObject>getAllContents(parserRules), EcoreUtil.<EObject>getAllContents(enumRules))
    Iterators.addAll(result, iter.filter(Keyword).map[if (options.ignoreCase) value.toLowerCase(Locale.ENGLISH) else value])
    Collections.unmodifiableSet(result)
  }

}
