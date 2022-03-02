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
import com.avaloq.tools.ddk.xtext.generator.parser.common.GrammarRuleAnnotations.SemanticPredicate
import com.avaloq.tools.ddk.xtext.generator.parser.common.PredicatesNaming
import com.avaloq.tools.ddk.xtext.parser.ISemanticPredicates
import com.avaloq.tools.ddk.xtext.parser.antlr.AbstractContextualAntlrParser
import com.avaloq.tools.ddk.xtext.parser.antlr.ParserContext
import com.google.common.base.Joiner
import com.google.common.collect.ImmutableSet
import com.google.inject.Inject
import com.google.inject.Singleton
import com.google.inject.name.Names
import java.util.Arrays
import java.util.Set
import java.util.stream.Collectors
import org.antlr.runtime.CharStream
import org.antlr.runtime.Token
import org.antlr.runtime.TokenSource
import org.eclipse.xtext.AbstractElement
import org.eclipse.xtext.GrammarUtil
import org.eclipse.xtext.parser.antlr.AntlrTokenDefProvider
import org.eclipse.xtext.parser.antlr.ITokenDefProvider
import org.eclipse.xtext.parser.antlr.Lexer
import org.eclipse.xtext.parser.antlr.LexerProvider
import org.eclipse.xtext.parser.antlr.XtextTokenStream
import org.eclipse.xtext.xtext.FlattenedGrammarAccess
import org.eclipse.xtext.xtext.RuleFilter
import org.eclipse.xtext.xtext.RuleNames
import org.eclipse.xtext.xtext.generator.grammarAccess.GrammarAccessExtensions
import org.eclipse.xtext.xtext.generator.model.FileAccessFactory
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess
import org.eclipse.xtext.xtext.generator.model.IXtextGeneratorFileSystemAccess
import org.eclipse.xtext.xtext.generator.model.JavaFileAccess
import org.eclipse.xtext.xtext.generator.model.TypeReference
import org.eclipse.xtext.xtext.generator.parser.antlr.ContentAssistGrammarNaming
import org.eclipse.xtext.xtext.generator.parser.antlr.GrammarNaming
import org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2

import static extension org.eclipse.xtext.GrammarUtil.*
import static extension org.eclipse.xtext.xtext.generator.model.TypeReference.*
import static extension org.eclipse.xtext.xtext.generator.parser.antlr.AntlrGrammarGenUtil.*

class AnnotationAwareXtextAntlrGeneratorFragment2 extends XtextAntlrGeneratorFragment2 {

  static val ADDITIONAL_CA_REQUIRED_BUNDLE = "com.avaloq.tools.ddk.xtext"

  @Inject AnnotationAwareAntlrGrammarGenerator productionGenerator
  @Inject AnnotationAwareAntlrContentAssistGrammarGenerator contentAssistGenerator
  @Inject GrammarNaming productionNaming
  @Inject FileAccessFactory fileFactory
  @Inject ContentAssistGrammarNaming contentAssistNaming

  @Inject extension PredicatesNaming predicatesNaming
  @Inject extension GrammarAccessExtensions grammarUtil
  @Inject extension GrammarRuleAnnotations annotations

  boolean removeBacktrackingGuards
  int lookaheadThreshold
  boolean partialParsing

  Set<String> reservedWords = ImmutableSet.of();
  Set<String> keywords = ImmutableSet.of();
  Set<String> identifierRules = ImmutableSet.of();
  String lexerSuperClassName = "";

  /**
   * Suffix used in the naming convention for the classes responsible for semantic predicates.
   */
  static val CLASS_SUFFIX = "SemanticPredicates";

  override setRemoveBacktrackingGuards(boolean removeBacktrackingGuards) {
    this.removeBacktrackingGuards = removeBacktrackingGuards
    super.setRemoveBacktrackingGuards(removeBacktrackingGuards)
  }

  override setLookaheadThreshold(String lookaheadThreshold) {
    this.lookaheadThreshold = Integer.parseInt(lookaheadThreshold)
    super.setLookaheadThreshold(lookaheadThreshold)
  }

  override setPartialParsing(boolean partialParsing) {
    this.partialParsing = partialParsing
    super.setPartialParsing(partialParsing)
  }

  def setReservedWords(String words) {
    reservedWords = toSet(words);
  }

  def setIdentifierRules(String rules) {
    identifierRules = toSet(rules);
  }

  def setKeywords(String words) {
    keywords = toSet(words);
  }

  def setLexerSuperClassName (String className) {
    lexerSuperClassName = className
  }

  def private Set<String> toSet(String words) {
    Arrays.stream(words.split(",")).map(str | str.trim()).filter(str | !str.isEmpty()).collect(Collectors.toSet());
  }

  protected override void checkGrammar() {
    super.checkGrammar();
    getGrammar().annotateGrammar
  }

  protected override doGenerate() {
    super.doGenerate()
    // if there is no ide plugin, write the content assist parser to the ui plugin.
    if (projectConfig.genericIde.srcGen === null) {
      generateUiContentAssistGrammar()
      generateContentAssistParser().writeTo(projectConfig.eclipsePlugin.srcGen)
      if (hasSyntheticTerminalRule()) {
        generateContentAssistTokenSource().writeTo(projectConfig.eclipsePlugin.src)
      }
      addIdeUiBindingsAndImports()
    }

    generateAbstractSemanticPredicate().writeTo(projectConfig.runtime.srcGen)
  }

  override protected addRuntimeBindingsAndImports() {
    super.addRuntimeBindingsAndImports
    if (projectConfig.runtime.manifest !== null) {
      projectConfig.runtime.manifest=>[
        exportedPackages += #[
          grammar.semanticPredicatesPackageName
        ]
      ]
    }
  }

  protected override generateContentAssistGrammar() {
    generateContentAssistGrammar(projectConfig.genericIde.srcGen)
  }

  protected def generateContentAssistGrammar(IXtextGeneratorFileSystemAccess fsa) {
    val extension naming = contentAssistNaming

    contentAssistGenerator.generate(grammar, options, fsa)

    runAntlr(grammar.parserGrammar, grammar.lexerGrammar, fsa)

    simplifyUnorderedGroupPredicatesIfRequired(grammar, fsa, grammar.internalParserClass)
    splitParserAndLexerIfEnabled(fsa, grammar.internalParserClass, grammar.lexerClass)
    normalizeTokens(fsa, grammar.lexerGrammar.tokensFileName)
    suppressWarnings(fsa, grammar.internalParserClass, grammar.lexerClass)
    normalizeLineDelimiters(fsa, grammar.lexerClass, grammar.internalParserClass)
    if (removeBacktrackingGuards) {
      removeBackTrackingGuards(fsa, grammar.internalParserClass, lookaheadThreshold)
    }
  }

  override protected void addIdeBindingsAndImports() {
    super.addIdeBindingsAndImports
    if (projectConfig.genericIde.manifest !== null) {
      projectConfig.genericIde.manifest=>[
        requiredBundles += ADDITIONAL_CA_REQUIRED_BUNDLE
      ]
    }
  }

  protected def generateUiContentAssistGrammar() {
    generateContentAssistGrammar(projectConfig.eclipsePlugin.srcGen)
  }

  def protected void addIdeUiBindingsAndImports() {
    val extension naming = contentAssistNaming
    if (projectConfig.eclipsePlugin.manifest !== null) {
      projectConfig.eclipsePlugin.manifest=>[
        exportedPackages += #[
          grammar.lexerClass.packageName,
          grammar.parserClass.packageName,
          grammar.internalParserClass.packageName
        ]
        requiredBundles += #[
          "org.antlr.runtime;bundle-version=\"[3.2.0,3.2.1)\"",
          ADDITIONAL_CA_REQUIRED_BUNDLE
        ]
      ]
    }
    val ideBindings = new GuiceModuleAccess.BindingFactory()
      .addConfiguredBinding("ContentAssistLexer", '''
        binder.bind(«grammar.lexerSuperClass».class)
          .annotatedWith(«Names».named(«"org.eclipse.xtext.ide.LexerIdeBindings".typeRef».CONTENT_ASSIST))
          .to(«grammar.lexerClass».class);
      ''')
      .addTypeToType('org.eclipse.xtext.ide.editor.contentassist.antlr.IContentAssistParser'.typeRef, grammar.parserClass)
      .addConfiguredBinding("IProposalConflictHelper", '''
        binder.bind(org.eclipse.xtext.ide.editor.contentassist.IProposalConflictHelper.class)
          .to(org.eclipse.xtext.ide.editor.contentassist.antlr.AntlrProposalConflictHelper.class);
      ''')
    if (partialParsing) {
      ideBindings.addTypeToType(
        "org.eclipse.xtext.ide.editor.contentassist.antlr.ContentAssistContextFactory".typeRef,
        "org.eclipse.xtext.ide.editor.contentassist.antlr.PartialContentAssistContextFactory".typeRef
      )
    }
    if (hasSyntheticTerminalRule) {
      ideBindings.addTypeToType(
        "org.eclipse.xtext.ide.editor.contentassist.CompletionPrefixProvider".typeRef,
        "org.eclipse.xtext.ide.editor.contentassist.IndentationAwareCompletionPrefixProvider".typeRef
      )
    }
    ideBindings.contributeTo(language.eclipsePluginGenModule)
  }

  override JavaFileAccess generateContentAssistParser() {
    val extension naming = contentAssistNaming
    val file = fileFactory.createGeneratedJavaFile(grammar.parserClass)
    file.content = '''
      public class «grammar.parserClass.simpleName» extends «grammar.getParserSuperClass(partialParsing)» {

        «grammar.initNameMappings()»

        @«Inject»
        private «grammar.grammarAccess» grammarAccess;

        @«Inject»
        private «ISemanticPredicates.typeRef» predicates;

        /**
         * Creates compilation context.
         *
         * @param Input
         *          Stream
         * @return Compilation context
         */
        protected «ParserContext.typeRef» createParserContext() {
          return new ParserContext();
        }

        @Override
        protected «grammar.internalParserClass» createParser() {
          «grammar.internalParserClass» result = new «grammar.internalParserClass»(null);
          result.setGrammarAccess(grammarAccess);
          result.setParserContext(createParserContext());
          result.setPredicates((«grammar.semanticPredicatesFullName.typeRef»)predicates);
          return result;
        }

       «IF hasSyntheticTerminalRule»
          @Override
          protected «TokenSource» createLexer(«CharStream» stream) {
            return new «grammar.tokenSourceClass»(super.createLexer(stream));
          }

        «ENDIF»
        @Override
        protected String getRuleName(«AbstractElement» element) {
          return nameMappings.getRuleName(element);
        }

        @Override
        protected String[] getInitialHiddenTokens() {
          return new String[] { «FOR hidden : grammar.initialHiddenTokens SEPARATOR ", "»"«hidden»"«ENDFOR» };
        }

        public «grammar.grammarAccess» getGrammarAccess() {
          return this.grammarAccess;
        }

        public void setGrammarAccess(«grammar.grammarAccess» grammarAccess) {
          this.grammarAccess = grammarAccess;
        }

        public NameMappings getNameMappings() {
          return nameMappings;
        }

        public void setNameMappings(NameMappings nameMappings) {
          this.nameMappings = nameMappings;
        }
      }
    '''
    return file
  }

  protected override generateProductionGrammar() {
    val extension naming = productionNaming
    val fsa = projectConfig.runtime.srcGen
    productionGenerator.lexerSuperClassName = lexerSuperClassName;
    productionGenerator.generate(grammar, options, fsa)

    runAntlr(grammar.parserGrammar, grammar.lexerGrammar, fsa)

    simplifyUnorderedGroupPredicatesIfRequired(grammar, fsa, grammar.internalParserClass)
    splitParserAndLexerIfEnabled(fsa, grammar.internalParserClass, grammar.lexerClass)
    normalizeTokens(fsa, grammar.lexerGrammar.tokensFileName)
    suppressWarnings(fsa, grammar.internalParserClass, grammar.lexerClass)
    normalizeLineDelimiters(fsa, grammar.internalParserClass, grammar.lexerClass)

    /* filter and ruleNames for flattened grammar */
    val RuleFilter filter = new RuleFilter();
    filter.discardUnreachableRules = true
    filter.discardTerminalRules = false
    val RuleNames ruleNames = RuleNames.getRuleNames(grammar, true);

    val keywordAnalysisHelper = new KeywordAnalysisHelper(productionGenerator.grammarNaming.getParserGrammar(grammar).grammarFileName, new FlattenedGrammarAccess(ruleNames, filter).getFlattenedGrammar(), identifierRules, reservedWords, keywords, grammarUtil)
    keywordAnalysisHelper.printReport(fsa.path);
    keywordAnalysisHelper.printViolations(fsa.path);
  }

  def JavaFileAccess generateAbstractSemanticPredicate() {
    val file = fileFactory.createGeneratedJavaFile(TypeReference.typeRef(grammar.getSemanticPredicatesFullName))
    file.importType(TypeReference.typeRef(grammar.semanticPredicatesFullName))
    file.importType(TypeReference.typeRef(ISemanticPredicates.AbstractSemanticPredicates))
    if(!getGrammar().predicates.isEmpty){
      file.importType(TypeReference.typeRef(ParserContext))
      file.importType(TypeReference.typeRef(Token))
      for (inheritedGrammar : annotations.allInheritedGrammars(getGrammar())) {
        file.importType(TypeReference.typeRef(GrammarUtil.getNamespace(inheritedGrammar) + ".grammar." + GrammarUtil.getSimpleName(inheritedGrammar) + CLASS_SUFFIX))
      }
    }
    file.importType(TypeReference.typeRef(Singleton))
    file.content = '''
      /**
       *  Provides semantic predicates as specified in the grammar. Language may need to override
       *  this class in order to provide concrete implementations for predicates.
       */
      @Singleton
      public class «grammar.getSemanticPredicatesSimpleName()» extends AbstractSemanticPredicates {
        «FOR element : getGrammar().predicates»

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

        «FOR element : getGrammar().predicates»

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
   *          Semantic predicate, must not be {@code null}
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
   *          Semantic predicate, must not be {@code null}
   * @return A string containing the condition for the semantic predicate or {@code null}
   */
  def String getRulePredicateCondition(SemanticPredicate predicate) {
    if (predicate.keywords !== null) {
      val condition = predicate.keywords.stream().
        map([s | '''"«s»".equalsIgnoreCase(text)''']).collect(Collectors.joining(" || "))
        '''
        String text = parserContext.getInput().LT(1).getText();
        return «condition»;
        '''
    } else {
      return "return " + predicate.grammar + CLASS_SUFFIX + "." + predicate.name + "(parserContext);\n"
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

  override protected addUiBindingsAndImports() {
    /* Overridden to remove the binding for IContentAssistParser, which we bind at a different place.
     * If we would register it here, we would have conflicting bindings for IContentAssistParser.
     */
    val extension naming = contentAssistNaming
    val caLexerClass = grammar.lexerClass

    if (projectConfig.genericIde.manifest !== null) {
      projectConfig.genericIde.manifest=>[
        exportedPackages += #[
          caLexerClass.packageName,
          grammar.parserClass.packageName,
          grammar.internalParserClass.packageName
        ]
      ]
    }
    val uiBindings = new GuiceModuleAccess.BindingFactory()
      .addTypeToType(
        "org.eclipse.xtext.ui.editor.contentassist.IProposalConflictHelper".typeRef,
        "org.eclipse.xtext.ui.editor.contentassist.antlr.AntlrProposalConflictHelper".typeRef
      )
      .addConfiguredBinding("ContentAssistLexer", '''
        binder.bind(«grammar.lexerSuperClass».class)
          .annotatedWith(«Names».named(«"org.eclipse.xtext.ide.LexerIdeBindings".typeRef».CONTENT_ASSIST))
          .to(«caLexerClass».class);
      ''')
      // registration of the 'ContentAssistLexer' is put in front of the 'HighlightingLexer'
      //  in order to let 'caLexerClass' get added to the imports, since it is referenced
      //  several times and the lexer classes' simple names are usually identical
      .addConfiguredBinding("HighlightingLexer", '''
        binder.bind(«Lexer».class)
          .annotatedWith(«Names».named(«"org.eclipse.xtext.ide.LexerIdeBindings".typeRef».HIGHLIGHTING))
          .to(«productionNaming.getLexerClass(grammar)».class);
      ''')
      .addConfiguredBinding("HighlightingTokenDefProvider", '''
        binder.bind(«ITokenDefProvider».class)
          .annotatedWith(«Names».named(«"org.eclipse.xtext.ide.LexerIdeBindings".typeRef».HIGHLIGHTING))
          .to(«AntlrTokenDefProvider».class);
      ''')
      .addTypeToType(
        new TypeReference("org.eclipse.xtext.ui.editor.contentassist", "ContentAssistContext.Factory"),
        "org.eclipse.xtext.ui.editor.contentassist.antlr.DelegatingContentAssistContextFactory".typeRef
      )
      .addConfiguredBinding("ContentAssistLexerProvider", '''
        binder.bind(«caLexerClass».class).toProvider(«LexerProvider».create(«caLexerClass».class));
      ''')

    if (projectConfig.genericIde.srcGen !== null) {
      uiBindings.addTypeToType("org.eclipse.xtext.ide.editor.contentassist.antlr.IContentAssistParser".typeRef, grammar.parserClass)
    }

    if (hasSyntheticTerminalRule) {
      uiBindings.addTypeToType(
        "org.eclipse.xtext.ide.editor.contentassist.CompletionPrefixProvider".typeRef,
        "org.eclipse.xtext.ide.editor.contentassist.IndentationAwareCompletionPrefixProvider".typeRef
      )
    }
    uiBindings.contributeTo(language.eclipsePluginGenModule)
  }
}