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

import com.avaloq.tools.ddk.xtext.generator.parser.antlr.GrammarRuleAnnotations.SemanticPredicate
import com.avaloq.tools.ddk.xtext.parser.ISemanticPredicates
import com.avaloq.tools.ddk.xtext.parser.antlr.AbstractContextualAntlrParser
import com.avaloq.tools.ddk.xtext.parser.antlr.ParserContext
import com.google.common.base.Joiner
import com.google.inject.Inject
import java.util.stream.Collectors
import org.antlr.runtime.CharStream
import org.antlr.runtime.Token
import org.antlr.runtime.TokenSource
import org.eclipse.xtext.parser.antlr.XtextTokenStream
import org.eclipse.xtext.xtext.generator.grammarAccess.GrammarAccessExtensions
import org.eclipse.xtext.xtext.generator.model.FileAccessFactory
import org.eclipse.xtext.xtext.generator.model.JavaFileAccess
import org.eclipse.xtext.xtext.generator.model.TypeReference
import org.eclipse.xtext.xtext.generator.parser.antlr.GrammarNaming
import org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2

import static extension org.eclipse.xtext.GrammarUtil.*
import static extension org.eclipse.xtext.xtext.generator.parser.antlr.AntlrGrammarGenUtil.*
import com.google.inject.Singleton
import java.util.List

class AnnotationAwareXtextAntlrGeneratorFragment2 extends XtextAntlrGeneratorFragment2 {

  @Inject AnnotationAwareAntlrGrammarGenerator productionGenerator
  @Inject GrammarNaming productionNaming
  @Inject FileAccessFactory fileFactory

  @Inject extension PredicatesNaming predicatesNaming
  @Inject extension GrammarAccessExtensions grammarUtil

  List<SemanticPredicate> predicates

  protected override generateProductionGrammar() {
    val extension naming = productionNaming
    val fsa = projectConfig.runtime.srcGen

    predicates = productionGenerator.generate2(grammar, options, fsa)

    runAntlr(grammar.parserGrammar, grammar.lexerGrammar, fsa)

    simplifyUnorderedGroupPredicatesIfRequired(grammar, fsa, grammar.internalParserClass)
    splitParserAndLexerIfEnabled(fsa, grammar.internalParserClass, grammar.lexerClass)
    normalizeTokens(fsa, grammar.lexerGrammar.tokensFileName)
    suppressWarnings(fsa, grammar.internalParserClass, grammar.lexerClass)
    normalizeLineDelimiters(fsa, grammar.internalParserClass, grammar.lexerClass)
  }

  override protected doGenerate() {
      super.doGenerate()
      generateAbstractSemanticPredicate().writeTo(projectConfig.runtime.srcGen)

  }

  def JavaFileAccess generateAbstractSemanticPredicate() {
    val file = fileFactory.createGeneratedJavaFile(TypeReference.typeRef(grammar.getSemanticPredicatesFullName))
    file.importType(TypeReference.typeRef(grammar.semanticPredicatesFullName))
    file.importType(TypeReference.typeRef(ISemanticPredicates.AbstractSemanticPredicates))
    if(!predicates.isEmpty){
      file.importType(TypeReference.typeRef(ParserContext))
      file.importType(TypeReference.typeRef(Token))
    }
    file.importType(TypeReference.typeRef(Singleton))

    file.content = '''
      /**
       *  Provides semantic predicates as specified in the grammar. Language may need to override
       *  this class in order to provide concrete implementations for predicates.
       */
      @Singleton
      public class «grammar.getSemanticPredicatesSimpleName()» extends AbstractSemanticPredicates {
        «FOR element : predicates»

          /**
           * Predicate for grammar rule «element.name».
           *
           * @param input
           *          Input from Lexer
           * @return {@code true} if the grammar rule is enabled, {@code false} otherwise
           */
          public boolean «element.name»(ParserContext parserContext) {
            «element.getRulePredicateCondition»
          }
        «ENDFOR»

        «FOR element : predicates»

         /**
           * Message for «element.name» predicate.
           *
           * @param input
           *          Input from Lexer
           * @return {@code true} if the grammar rule is enabled, {@code false} otherwise
           */
          public String «element.message»(Token token) {
            «element.getRulePredicateMessage»
          }
        «ENDFOR»
      }
    '''
    file
  }

   /**
   * Returns name for the predicate message method.
   *
   * @param predicate
   *          Semantic predicate
   * @return Content for the predicate message method
   */
  def String getRulePredicateMessage(SemanticPredicate predicate){
    if(predicate.keywords!==null)
      '''
      return "Unexpected: " + token.getText() + ". Expected: '«Joiner.on("', '").join(predicate.keywords)»'";
      '''
  else
    '''
    /* Default message. Intended to be overridden. */
    return "Condition «predicate.name» is not fullfilled ";
    '''
  }

  /**
   * Returns predicate condition (default condition).
   *
   * @param predicate
   *          Semantic predicate
   * @return A string containing the condition for the semantic predicate or {@code null}
   */
  def String getRulePredicateCondition(SemanticPredicate predicate){
      if (predicate.keywords !== null) {
        val condition = predicate.keywords.stream().
        map([s | '''"«s»".equalsIgnoreCase(text)''']).collect(Collectors.joining(" || "))
        '''
        String text = parserContext.getInput().LT(1).getText();
        return «condition»;
        '''
      } else {
        "return true; /* Intended to be overridden. */\n";
     }
  }

  override JavaFileAccess generateProductionParser() {
    val extension naming = productionNaming
    val file = fileFactory.createGeneratedJavaFile(grammar.parserClass)
    file.importType(TypeReference.typeRef(grammar.semanticPredicatesFullName))
    file.importType(TypeReference.typeRef(ISemanticPredicates))

    file.content = '''
      public class «grammar.parserClass.simpleName» extends «AbstractContextualAntlrParser» {

        @«Inject»
        private «grammar.grammarAccess» grammarAccess;

        @«Inject»
        private ISemanticPredicates predicates;

        @Override
        protected void setInitialHiddenTokens(«XtextTokenStream» tokenStream) {
          tokenStream.setInitialHiddenTokens(«FOR hidden : grammar.initialHiddenTokens SEPARATOR ", "»"«hidden»"«ENDFOR»);
        }

        «IF hasSyntheticTerminalRule»
          @Override
          protected «TokenSource» createLexer(«CharStream» stream) {
            return new «grammar.tokenSourceClass»(super.createLexer(stream));
          }

          /**
           * Indentation aware languages do not support partial parsing since the lexer is inherently stateful.
           * Override and return {@code true} if your terminal splitting is stateless.
           */
          @Override
          protected boolean isReparseSupported() {
            return false;
          }
        «ENDIF»

        @Override
        protected «grammar.internalParserClass» createParser(«XtextTokenStream» stream) {
          return new «grammar.internalParserClass»(stream, getGrammarAccess(), createParserContext(), («grammar.semanticPredicatesSimpleName») predicates);
        }

        @Override
        protected String getDefaultRuleName() {
          return "«grammar.allParserRules.head.originalElement.name»";
        }

        public «grammar.grammarAccess» getGrammarAccess() {
          return this.grammarAccess;
        }

        public void setGrammarAccess(«grammar.grammarAccess» grammarAccess) {
          this.grammarAccess = grammarAccess;
        }
      }
    '''
    file
  }
}
