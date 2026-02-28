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

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.parser.antlr.AntlrTokenDefProvider;
import org.eclipse.xtext.parser.antlr.ITokenDefProvider;
import org.eclipse.xtext.parser.antlr.Lexer;
import org.eclipse.xtext.parser.antlr.LexerProvider;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xtext.FlattenedGrammarAccess;
import org.eclipse.xtext.xtext.RuleFilter;
import org.eclipse.xtext.xtext.RuleNames;
import org.eclipse.xtext.xtext.generator.grammarAccess.GrammarAccessExtensions;
import org.eclipse.xtext.xtext.generator.model.FileAccessFactory;
import org.eclipse.xtext.xtext.generator.model.GeneratedJavaFileAccess;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.IXtextGeneratorFileSystemAccess;
import org.eclipse.xtext.xtext.generator.model.JavaFileAccess;
import org.eclipse.xtext.xtext.generator.model.ManifestAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtext.xtext.generator.parser.antlr.AntlrGrammarGenUtil;
import org.eclipse.xtext.xtext.generator.parser.antlr.ContentAssistGrammarNaming;
import org.eclipse.xtext.xtext.generator.parser.antlr.GrammarNaming;
import org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2;

import com.avaloq.tools.ddk.xtext.generator.parser.common.GrammarRuleAnnotations;
import com.avaloq.tools.ddk.xtext.generator.parser.common.GrammarRuleAnnotations.SemanticPredicate;
import com.avaloq.tools.ddk.xtext.generator.parser.common.PredicatesNaming;
import com.avaloq.tools.ddk.xtext.parser.ISemanticPredicates;
import com.avaloq.tools.ddk.xtext.parser.antlr.AbstractContextualAntlrParser;
import com.avaloq.tools.ddk.xtext.parser.antlr.ParserContext;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Names;


public class AnnotationAwareXtextAntlrGeneratorFragment2 extends XtextAntlrGeneratorFragment2 {

  private static final String ADDITIONAL_CA_REQUIRED_BUNDLE = "com.avaloq.tools.ddk.xtext";

  @Inject
  private AnnotationAwareAntlrGrammarGenerator productionGenerator;

  @Inject
  private AnnotationAwareAntlrContentAssistGrammarGenerator contentAssistGenerator;

  @Inject
  private GrammarNaming productionNaming;

  @Inject
  private FileAccessFactory fileFactory;

  @Inject
  private ContentAssistGrammarNaming contentAssistNaming;

  @Inject
  private PredicatesNaming predicatesNaming;

  @Inject
  private GrammarAccessExtensions grammarUtil;

  @Inject
  private GrammarRuleAnnotations annotations;

  private boolean generateContentAssistIfIdeMissing;

  private boolean removeBacktrackingGuards;

  private int lookaheadThreshold;

  private boolean partialParsing;

  private Set<String> reservedWords = ImmutableSet.of();

  private Set<String> keywords = ImmutableSet.of();

  private Set<String> identifierRules = ImmutableSet.of();

  private String lexerSuperClassName = "";

  /**
   * Suffix used in the naming convention for the classes responsible for semantic predicates.
   */
  private static final String CLASS_SUFFIX = "SemanticPredicates";

  @Override
  public void setRemoveBacktrackingGuards(final boolean removeBacktrackingGuards) {
    this.removeBacktrackingGuards = removeBacktrackingGuards;
    super.setRemoveBacktrackingGuards(removeBacktrackingGuards);
  }

  @Override
  public void setLookaheadThreshold(final String lookaheadThreshold) {
    this.lookaheadThreshold = Integer.parseInt(lookaheadThreshold);
    super.setLookaheadThreshold(lookaheadThreshold);
  }

  @Override
  public void setPartialParsing(final boolean partialParsing) {
    this.partialParsing = partialParsing;
    super.setPartialParsing(partialParsing);
  }

  public void setReservedWords(final String words) {
    this.reservedWords = toSet(words);
  }

  public void setIdentifierRules(final String rules) {
    this.identifierRules = toSet(rules);
  }

  public void setKeywords(final String words) {
    this.keywords = toSet(words);
  }

  public void setLexerSuperClassName(final String className) {
    this.lexerSuperClassName = className;
  }

  private Set<String> toSet(final String words) {
    return Arrays.stream(words.split(",")).map(String::trim).filter(str -> !str.isEmpty()).collect(Collectors.toSet());
  }

  public boolean isGenerateContentAssistIfIdeMissing() {
    return this.generateContentAssistIfIdeMissing;
  }

  public void setGenerateContentAssistIfIdeMissing(final boolean generateContentAssistIfIdeMissing) {
    this.generateContentAssistIfIdeMissing = generateContentAssistIfIdeMissing;
  }

  @Override
  protected void checkGrammar() {
    super.checkGrammar();
    this.annotations.annotateGrammar(getGrammar());
  }

  @Override
  protected void doGenerate() {
    super.doGenerate();
    // if there is no ide plugin, write the content assist parser to the ui plugin.
    if (this.generateContentAssistIfIdeMissing && getProjectConfig().getGenericIde().getSrcGen() == null) {
      generateUiContentAssistGrammar();
      generateContentAssistParser().writeTo(getProjectConfig().getEclipsePlugin().getSrcGen());
      if (hasSyntheticTerminalRule()) {
        generateContentAssistTokenSource().writeTo(getProjectConfig().getEclipsePlugin().getSrc());
      }
      addIdeUiBindingsAndImports();
    }
    generateAbstractSemanticPredicate().writeTo(getProjectConfig().getRuntime().getSrcGen());
  }

  @Override
  protected void addRuntimeBindingsAndImports() {
    super.addRuntimeBindingsAndImports();
    ManifestAccess manifest = getProjectConfig().getRuntime().getManifest();
    if (manifest != null) {
      Set<String> exportedPackages = manifest.getExportedPackages();
      String semanticPredicatesPackageName = this.predicatesNaming.getSemanticPredicatesPackageName(getGrammar());
      exportedPackages.add(semanticPredicatesPackageName);
    }
  }

  @Override
  protected void generateContentAssistGrammar() {
    generateContentAssistGrammar(getProjectConfig().getGenericIde().getSrcGen());
  }

  protected void generateContentAssistGrammar(final IXtextGeneratorFileSystemAccess fsa) {
    final ContentAssistGrammarNaming naming = this.contentAssistNaming;
    this.contentAssistGenerator.generate(getGrammar(), getOptions(), fsa);
    runAntlr(naming.getParserGrammar(getGrammar()), naming.getLexerGrammar(getGrammar()), fsa);
    simplifyUnorderedGroupPredicatesIfRequired(getGrammar(), fsa, naming.getInternalParserClass(getGrammar()));
    splitParserAndLexerIfEnabled(fsa, naming.getInternalParserClass(getGrammar()), naming.getLexerClass(getGrammar()));
    normalizeTokens(fsa, naming.getLexerGrammar(getGrammar()).getTokensFileName());
    suppressWarnings(fsa, naming.getInternalParserClass(getGrammar()), naming.getLexerClass(getGrammar()));
    normalizeLineDelimiters(fsa, naming.getLexerClass(getGrammar()), naming.getInternalParserClass(getGrammar()));
    if (this.removeBacktrackingGuards) {
      removeBackTrackingGuards(fsa, naming.getInternalParserClass(getGrammar()), this.lookaheadThreshold);
    }
  }

  @Override
  protected void addIdeBindingsAndImports() {
    super.addIdeBindingsAndImports();
    ManifestAccess manifest = getProjectConfig().getGenericIde().getManifest();
    if (manifest != null) {
      Set<String> requiredBundles = manifest.getRequiredBundles();
      requiredBundles.add(ADDITIONAL_CA_REQUIRED_BUNDLE);
    }
  }

  protected void generateUiContentAssistGrammar() {
    generateContentAssistGrammar(getProjectConfig().getEclipsePlugin().getSrcGen());
  }

  protected void addIdeUiBindingsAndImports() {
    final ContentAssistGrammarNaming naming = this.contentAssistNaming;
    ManifestAccess manifest = getProjectConfig().getEclipsePlugin().getManifest();
    if (manifest != null) {
      Set<String> exportedPackages = manifest.getExportedPackages();
      Iterables.<String>addAll(exportedPackages, List.of(
        naming.getLexerClass(getGrammar()).getPackageName(),
        naming.getParserClass(getGrammar()).getPackageName(),
        naming.getInternalParserClass(getGrammar()).getPackageName()));
      Set<String> requiredBundles = manifest.getRequiredBundles();
      Iterables.<String>addAll(requiredBundles, List.of(
        "org.antlr.runtime;bundle-version=\"[3.2.0,3.2.1)\"",
        ADDITIONAL_CA_REQUIRED_BUNDLE));
    }
    GuiceModuleAccess.BindingFactory ideBindings = new GuiceModuleAccess.BindingFactory()
      .addConfiguredBinding("ContentAssistLexer", new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("binder.bind(");
          TypeReference _lexerSuperClass = naming.getLexerSuperClass(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
          _builder.append(_lexerSuperClass);
          _builder.append(".class)");
          _builder.newLineIfNotEmpty();
          _builder.append("  ");
          _builder.append(".annotatedWith(");
          _builder.append(Names.class, "  ");
          _builder.append(".named(");
          TypeReference _typeRef = TypeReference.typeRef("org.eclipse.xtext.ide.LexerIdeBindings");
          _builder.append(_typeRef, "  ");
          _builder.append(".CONTENT_ASSIST))");
          _builder.newLineIfNotEmpty();
          _builder.append("  ");
          _builder.append(".to(");
          TypeReference _lexerClass = naming.getLexerClass(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
          _builder.append(_lexerClass, "  ");
          _builder.append(".class);");
          _builder.newLineIfNotEmpty();
        }
      })
      .addTypeToType(TypeReference.typeRef("org.eclipse.xtext.ide.editor.contentassist.antlr.IContentAssistParser"), naming.getParserClass(getGrammar()))
      .addConfiguredBinding("IProposalConflictHelper", new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("binder.bind(org.eclipse.xtext.ide.editor.contentassist.IProposalConflictHelper.class)");
          _builder.newLine();
          _builder.append("  ");
          _builder.append(".to(org.eclipse.xtext.ide.editor.contentassist.antlr.AntlrProposalConflictHelper.class);");
          _builder.newLine();
        }
      });
    if (this.partialParsing) {
      ideBindings.addTypeToType(
        TypeReference.typeRef("org.eclipse.xtext.ide.editor.contentassist.antlr.ContentAssistContextFactory"),
        TypeReference.typeRef("org.eclipse.xtext.ide.editor.contentassist.antlr.PartialContentAssistContextFactory"));
    }
    if (hasSyntheticTerminalRule()) {
      ideBindings.addTypeToType(
        TypeReference.typeRef("org.eclipse.xtext.ide.editor.contentassist.CompletionPrefixProvider"),
        TypeReference.typeRef("org.eclipse.xtext.ide.editor.contentassist.IndentationAwareCompletionPrefixProvider"));
    }
    ideBindings.contributeTo(getLanguage().getEclipsePluginGenModule());
  }

  @Override
  public JavaFileAccess generateContentAssistParser() {
    final ContentAssistGrammarNaming naming = this.contentAssistNaming;
    final GeneratedJavaFileAccess file = this.fileFactory.createGeneratedJavaFile(naming.getParserClass(getGrammar()));
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        _builder.append("public class ");
        String _simpleName = naming.getParserClass(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar()).getSimpleName();
        _builder.append(_simpleName);
        _builder.append(" extends ");
        TypeReference _parserSuperClass = naming.getParserSuperClass(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar(), AnnotationAwareXtextAntlrGeneratorFragment2.this.partialParsing);
        _builder.append(_parserSuperClass);
        _builder.append(" {");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("  ");
        StringConcatenationClient _initNameMappings = AnnotationAwareXtextAntlrGeneratorFragment2.this.initNameMappings(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
        _builder.append(_initNameMappings, "  ");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("  ");
        _builder.append("@");
        _builder.append(Inject.class, "  ");
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        _builder.append("private ");
        TypeReference _grammarAccess = AnnotationAwareXtextAntlrGeneratorFragment2.this.grammarUtil.getGrammarAccess(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
        _builder.append(_grammarAccess, "  ");
        _builder.append(" grammarAccess;");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("  ");
        _builder.append("@");
        _builder.append(Inject.class, "  ");
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        _builder.append("private ");
        TypeReference _typeRef = TypeReference.typeRef(ISemanticPredicates.class);
        _builder.append(_typeRef, "  ");
        _builder.append(" predicates;");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("  ");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("* Creates compilation context.");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("*");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("* @param Input");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("*          Stream");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("* @return Compilation context");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("protected ");
        TypeReference _typeRef_1 = TypeReference.typeRef(ParserContext.class);
        _builder.append(_typeRef_1, "  ");
        _builder.append(" createParserContext() {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("return new ParserContext();");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("  ");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("protected ");
        TypeReference _internalParserClass = naming.getInternalParserClass(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
        _builder.append(_internalParserClass, "  ");
        _builder.append(" createParser() {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        TypeReference _internalParserClass_1 = naming.getInternalParserClass(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
        _builder.append(_internalParserClass_1, "    ");
        _builder.append(" result = new ");
        TypeReference _internalParserClass_2 = naming.getInternalParserClass(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
        _builder.append(_internalParserClass_2, "    ");
        _builder.append("(null);");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("result.setGrammarAccess(grammarAccess);");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("result.setParserContext(createParserContext());");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("result.setPredicates((");
        TypeReference _typeRef_2 = TypeReference.typeRef(AnnotationAwareXtextAntlrGeneratorFragment2.this.predicatesNaming.getSemanticPredicatesFullName(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar()));
        _builder.append(_typeRef_2, "    ");
        _builder.append(")predicates);");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("return result;");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        {
          boolean _hasSyntheticTerminalRule = AnnotationAwareXtextAntlrGeneratorFragment2.this.hasSyntheticTerminalRule();
          if (_hasSyntheticTerminalRule) {
            _builder.append(" ");
            _builder.append("@Override");
            _builder.newLine();
            _builder.append(" ");
            _builder.append("protected ");
            _builder.append(TokenSource.class, " ");
            _builder.append(" createLexer(");
            _builder.append(CharStream.class, " ");
            _builder.append(" stream) {");
            _builder.newLineIfNotEmpty();
            _builder.append(" ");
            _builder.append("  ");
            _builder.append("return new ");
            TypeReference _tokenSourceClass = naming.getTokenSourceClass(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
            _builder.append(_tokenSourceClass, "   ");
            _builder.append("(super.createLexer(stream));");
            _builder.newLineIfNotEmpty();
            _builder.append(" ");
            _builder.append("}");
            _builder.newLine();
            _builder.newLine();
          }
        }
        _builder.append("  ");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("protected String getRuleName(");
        _builder.append(AbstractElement.class, "  ");
        _builder.append(" element) {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("return nameMappings.getRuleName(element);");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("  ");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("protected String[] getInitialHiddenTokens() {");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("return new String[] { ");
        {
          List<String> _initialHiddenTokens = AnnotationAwareXtextAntlrGeneratorFragment2.this.grammarUtil.initialHiddenTokens(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
          boolean _hasElements = false;
          for (final String hidden : _initialHiddenTokens) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(", ", "    ");
            }
            _builder.append("\"");
            _builder.append(hidden, "    ");
            _builder.append("\"");
          }
        }
        _builder.append(" };");
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("  ");
        _builder.append("public ");
        TypeReference _grammarAccess_1 = AnnotationAwareXtextAntlrGeneratorFragment2.this.grammarUtil.getGrammarAccess(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
        _builder.append(_grammarAccess_1, "  ");
        _builder.append(" getGrammarAccess() {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("return this.grammarAccess;");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("  ");
        _builder.append("public void setGrammarAccess(");
        TypeReference _grammarAccess_2 = AnnotationAwareXtextAntlrGeneratorFragment2.this.grammarUtil.getGrammarAccess(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
        _builder.append(_grammarAccess_2, "  ");
        _builder.append(" grammarAccess) {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("this.grammarAccess = grammarAccess;");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("  ");
        _builder.append("public NameMappings getNameMappings() {");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("return nameMappings;");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("  ");
        _builder.append("public void setNameMappings(NameMappings nameMappings) {");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("this.nameMappings = nameMappings;");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
      }
    };
    file.setContent(_client);
    return file;
  }

  @Override
  protected void generateProductionGrammar() {
    final GrammarNaming naming = this.productionNaming;
    final IXtextGeneratorFileSystemAccess fsa = getProjectConfig().getRuntime().getSrcGen();
    this.productionGenerator.setLexerSuperClassName(this.lexerSuperClassName);
    this.productionGenerator.generate(getGrammar(), getOptions(), fsa);
    runAntlr(naming.getParserGrammar(getGrammar()), naming.getLexerGrammar(getGrammar()), fsa);
    simplifyUnorderedGroupPredicatesIfRequired(getGrammar(), fsa, naming.getInternalParserClass(getGrammar()));
    splitParserAndLexerIfEnabled(fsa, naming.getInternalParserClass(getGrammar()), naming.getLexerClass(getGrammar()));
    normalizeTokens(fsa, naming.getLexerGrammar(getGrammar()).getTokensFileName());
    suppressWarnings(fsa, naming.getInternalParserClass(getGrammar()), naming.getLexerClass(getGrammar()));
    normalizeLineDelimiters(fsa, naming.getInternalParserClass(getGrammar()), naming.getLexerClass(getGrammar()));

    /* filter and ruleNames for flattened grammar */
    final RuleFilter filter = new RuleFilter();
    filter.setDiscardUnreachableRules(true);
    filter.setDiscardTerminalRules(false);
    final RuleNames ruleNames = RuleNames.getRuleNames(getGrammar(), true);

    String grammarFileName = this.productionGenerator.getGrammarNaming().getParserGrammar(getGrammar()).getGrammarFileName();
    Grammar flattenedGrammar = new FlattenedGrammarAccess(ruleNames, filter).getFlattenedGrammar();
    final KeywordAnalysisHelper keywordAnalysisHelper = new KeywordAnalysisHelper(grammarFileName, flattenedGrammar, this.identifierRules, this.reservedWords, this.keywords, this.grammarUtil);
    keywordAnalysisHelper.printReport(fsa.getPath());
    keywordAnalysisHelper.printViolations(fsa.getPath());
  }

  public JavaFileAccess generateAbstractSemanticPredicate() {
    final GeneratedJavaFileAccess file = this.fileFactory.createGeneratedJavaFile(TypeReference.typeRef(this.predicatesNaming.getSemanticPredicatesFullName(getGrammar())));
    file.importType(TypeReference.typeRef(this.predicatesNaming.getSemanticPredicatesFullName(getGrammar())));
    file.importType(TypeReference.typeRef(ISemanticPredicates.AbstractSemanticPredicates.class));
    if (!this.annotations.predicates(getGrammar()).isEmpty()) {
      file.importType(TypeReference.typeRef(ParserContext.class));
      file.importType(TypeReference.typeRef(Token.class));
      for (final Grammar inheritedGrammar : this.annotations.allInheritedGrammars(getGrammar())) {
        file.importType(TypeReference.typeRef(GrammarUtil.getNamespace(inheritedGrammar) + ".grammar." + GrammarUtil.getSimpleName(inheritedGrammar) + CLASS_SUFFIX));
      }
    }
    file.importType(TypeReference.typeRef(Singleton.class));
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        _builder.append("/**");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("*  Provides semantic predicates as specified in the grammar. Language may need to override");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("*  this class in order to provide concrete implementations for predicates.");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("@Singleton");
        _builder.newLine();
        _builder.append("public class ");
        String _semanticPredicatesSimpleName = AnnotationAwareXtextAntlrGeneratorFragment2.this.predicatesNaming.getSemanticPredicatesSimpleName(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
        _builder.append(_semanticPredicatesSimpleName);
        _builder.append(" extends AbstractSemanticPredicates {");
        _builder.newLineIfNotEmpty();
        {
          List<GrammarRuleAnnotations.SemanticPredicate> _predicates = AnnotationAwareXtextAntlrGeneratorFragment2.this.annotations.predicates(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
          for (final GrammarRuleAnnotations.SemanticPredicate element : _predicates) {
            _builder.newLine();
            _builder.append("  ");
            _builder.append("/**");
            _builder.newLine();
            _builder.append("  ");
            _builder.append(" ");
            _builder.append("* Predicate for grammar rule ");
            String _name = element.getName();
            _builder.append(_name, "   ");
            _builder.append(".");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append(" ");
            _builder.append("*");
            _builder.newLine();
            _builder.append("  ");
            _builder.append(" ");
            _builder.append("* @param input");
            _builder.newLine();
            _builder.append("  ");
            _builder.append(" ");
            _builder.append("*          Input from Lexer");
            _builder.newLine();
            _builder.append("  ");
            _builder.append(" ");
            _builder.append("* @return {@code true} if the grammar rule is enabled, {@code false} otherwise");
            _builder.newLine();
            _builder.append("  ");
            _builder.append(" ");
            _builder.append("*/");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("public boolean ");
            String _name_1 = element.getName();
            _builder.append(_name_1, "  ");
            _builder.append("(ParserContext parserContext) {");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("  ");
            String _rulePredicateCondition = AnnotationAwareXtextAntlrGeneratorFragment2.this.getRulePredicateCondition(element);
            _builder.append(_rulePredicateCondition, "    ");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("}");
            _builder.newLine();
          }
        }
        _builder.newLine();
        {
          List<GrammarRuleAnnotations.SemanticPredicate> _predicates_1 = AnnotationAwareXtextAntlrGeneratorFragment2.this.annotations.predicates(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
          for (final GrammarRuleAnnotations.SemanticPredicate element_1 : _predicates_1) {
            _builder.newLine();
            _builder.append("  ");
            _builder.append("/**");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("  ");
            _builder.append("* Message for ");
            String _name_2 = element_1.getName();
            _builder.append(_name_2, "    ");
            _builder.append(" predicate.");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("  ");
            _builder.append("*");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("  ");
            _builder.append("* @param input");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("  ");
            _builder.append("*          Input from Lexer");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("  ");
            _builder.append("* @return {@code true} if the grammar rule is enabled, {@code false} otherwise");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("  ");
            _builder.append("*/");
            _builder.newLine();
            _builder.append("  ");
            _builder.append(" ");
            _builder.append("public String ");
            String _message = element_1.getMessage();
            _builder.append(_message, "   ");
            _builder.append("(Token token) {");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("   ");
            String _rulePredicateMessage = AnnotationAwareXtextAntlrGeneratorFragment2.this.getRulePredicateMessage(element_1);
            _builder.append(_rulePredicateMessage, "     ");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append(" ");
            _builder.append("}");
            _builder.newLine();
          }
        }
        _builder.append("}");
        _builder.newLine();
      }
    };
    file.setContent(_client);
    return file;
  }

  /**
   * Returns name for the predicate message method.
   *
   * @param predicate
   *          Semantic predicate, must not be {@code null}
   * @return Content for the predicate message method
   */
  public String getRulePredicateMessage(final SemanticPredicate predicate) {
    if (predicate.getKeywords() != null) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("return \"Unexpected: \" + token.getText() + \". Expected: \'");
      String _join = Joiner.on("\', \'").join(predicate.getKeywords());
      _builder.append(_join);
      _builder.append("\'\";");
      _builder.newLineIfNotEmpty();
      return _builder.toString();
    } else {
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("/* Default message. Intended to be overridden. */");
      _builder_1.newLine();
      _builder_1.append("return \"Condition ");
      String _name = predicate.getName();
      _builder_1.append(_name);
      _builder_1.append(" is not fullfilled \";");
      _builder_1.newLineIfNotEmpty();
      return _builder_1.toString();
    }
  }

  /**
   * Returns predicate condition (default condition).
   *
   * @param predicate
   *          Semantic predicate, must not be {@code null}
   * @return A string containing the condition for the semantic predicate or {@code null}
   */
  public String getRulePredicateCondition(final SemanticPredicate predicate) {
    if (predicate.getKeywords() != null) {
      final String condition = predicate.getKeywords().stream().map(s -> {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("\"");
        _builder.append(s);
        _builder.append("\".equalsIgnoreCase(text)");
        return _builder.toString();
      }).collect(Collectors.joining(" || "));
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("String text = parserContext.getInput().LT(1).getText();");
      _builder.newLine();
      _builder.append("return ");
      _builder.append(condition);
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      return _builder.toString();
    } else {
      return "return " + predicate.getGrammar() + CLASS_SUFFIX + "." + predicate.getName() + "(parserContext);\n";
    }
  }

  @Override
  public JavaFileAccess generateProductionParser() {
    final GrammarNaming naming = this.productionNaming;
    final GeneratedJavaFileAccess file = this.fileFactory.createGeneratedJavaFile(naming.getParserClass(getGrammar()));
    file.importType(TypeReference.typeRef(this.predicatesNaming.getSemanticPredicatesFullName(getGrammar())));
    file.importType(TypeReference.typeRef(ISemanticPredicates.class));
    StringConcatenationClient _client = new StringConcatenationClient() {
      @Override
      protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
        _builder.append("public class ");
        String _simpleName = naming.getParserClass(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar()).getSimpleName();
        _builder.append(_simpleName);
        _builder.append(" extends ");
        _builder.append(AbstractContextualAntlrParser.class);
        _builder.append(" {");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("  ");
        _builder.append("@");
        _builder.append(Inject.class, "  ");
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        _builder.append("private ");
        TypeReference _grammarAccess = AnnotationAwareXtextAntlrGeneratorFragment2.this.grammarUtil.getGrammarAccess(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
        _builder.append(_grammarAccess, "  ");
        _builder.append(" grammarAccess;");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("  ");
        _builder.append("@");
        _builder.append(Inject.class, "  ");
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        _builder.append("private ISemanticPredicates predicates;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("  ");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("protected void setInitialHiddenTokens(");
        _builder.append(XtextTokenStream.class, "  ");
        _builder.append(" tokenStream) {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("tokenStream.setInitialHiddenTokens(");
        {
          List<String> _initialHiddenTokens = AnnotationAwareXtextAntlrGeneratorFragment2.this.grammarUtil.initialHiddenTokens(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
          boolean _hasElements = false;
          for (final String hidden : _initialHiddenTokens) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(", ", "    ");
            }
            _builder.append("\"");
            _builder.append(hidden, "    ");
            _builder.append("\"");
          }
        }
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        {
          boolean _hasSyntheticTerminalRule = AnnotationAwareXtextAntlrGeneratorFragment2.this.hasSyntheticTerminalRule();
          if (_hasSyntheticTerminalRule) {
            _builder.append("  ");
            _builder.append("@Override");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("protected ");
            _builder.append(TokenSource.class, "  ");
            _builder.append(" createLexer(");
            _builder.append(CharStream.class, "  ");
            _builder.append(" stream) {");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("  ");
            _builder.append("return new ");
            TypeReference _tokenSourceClass = naming.getTokenSourceClass(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
            _builder.append(_tokenSourceClass, "    ");
            _builder.append("(super.createLexer(stream));");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("}");
            _builder.newLine();
            _builder.newLine();
            _builder.append("  ");
            _builder.append("/**");
            _builder.newLine();
            _builder.append("  ");
            _builder.append(" ");
            _builder.append("* Indentation aware languages do not support partial parsing since the lexer is inherently stateful.");
            _builder.newLine();
            _builder.append("  ");
            _builder.append(" ");
            _builder.append("* Override and return {@code true} if your terminal splitting is stateless.");
            _builder.newLine();
            _builder.append("  ");
            _builder.append(" ");
            _builder.append("*/");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("@Override");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("protected boolean isReparseSupported() {");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("  ");
            _builder.append("return false;");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("}");
            _builder.newLine();
          }
        }
        _builder.newLine();
        _builder.append("  ");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("protected ");
        TypeReference _internalParserClass = naming.getInternalParserClass(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
        _builder.append(_internalParserClass, "  ");
        _builder.append(" createParser(");
        _builder.append(XtextTokenStream.class, "  ");
        _builder.append(" stream) {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("return new ");
        TypeReference _internalParserClass_1 = naming.getInternalParserClass(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
        _builder.append(_internalParserClass_1, "    ");
        _builder.append("(stream, getGrammarAccess(), createParserContext(), (");
        String _semanticPredicatesSimpleName = AnnotationAwareXtextAntlrGeneratorFragment2.this.predicatesNaming.getSemanticPredicatesSimpleName(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
        _builder.append(_semanticPredicatesSimpleName, "    ");
        _builder.append(") predicates);");
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("  ");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("protected String getDefaultRuleName() {");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("return \"");
        String _name = AntlrGrammarGenUtil.<ParserRule>getOriginalElement(IterableExtensions.<ParserRule>head(GrammarUtil.allParserRules(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar()))).getName();
        _builder.append(_name, "    ");
        _builder.append("\";");
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("  ");
        _builder.append("public ");
        TypeReference _grammarAccess_1 = AnnotationAwareXtextAntlrGeneratorFragment2.this.grammarUtil.getGrammarAccess(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
        _builder.append(_grammarAccess_1, "  ");
        _builder.append(" getGrammarAccess() {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("return this.grammarAccess;");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("  ");
        _builder.append("public void setGrammarAccess(");
        TypeReference _grammarAccess_2 = AnnotationAwareXtextAntlrGeneratorFragment2.this.grammarUtil.getGrammarAccess(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
        _builder.append(_grammarAccess_2, "  ");
        _builder.append(" grammarAccess) {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("this.grammarAccess = grammarAccess;");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
      }
    };
    file.setContent(_client);
    return file;
  }

  @Override
  protected void addUiBindingsAndImports() {
    /* Overridden to remove the binding for IContentAssistParser, which we bind at a different place.
     * If we would register it here, we would have conflicting bindings for IContentAssistParser.
     */
    final ContentAssistGrammarNaming naming = this.contentAssistNaming;
    final TypeReference caLexerClass = naming.getLexerClass(getGrammar());

    ManifestAccess manifest = getProjectConfig().getGenericIde().getManifest();
    if (manifest != null) {
      Set<String> exportedPackages = manifest.getExportedPackages();
      Iterables.<String>addAll(exportedPackages, List.of(
        caLexerClass.getPackageName(),
        naming.getParserClass(getGrammar()).getPackageName(),
        naming.getInternalParserClass(getGrammar()).getPackageName()));
    }
    GuiceModuleAccess.BindingFactory uiBindings = new GuiceModuleAccess.BindingFactory()
      .addTypeToType(
        TypeReference.typeRef("org.eclipse.xtext.ui.editor.contentassist.IProposalConflictHelper"),
        TypeReference.typeRef("org.eclipse.xtext.ui.editor.contentassist.antlr.AntlrProposalConflictHelper"))
      .addConfiguredBinding("ContentAssistLexer", new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("binder.bind(");
          TypeReference _lexerSuperClass = naming.getLexerSuperClass(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
          _builder.append(_lexerSuperClass);
          _builder.append(".class)");
          _builder.newLineIfNotEmpty();
          _builder.append("  ");
          _builder.append(".annotatedWith(");
          _builder.append(Names.class, "  ");
          _builder.append(".named(");
          TypeReference _typeRef = TypeReference.typeRef("org.eclipse.xtext.ide.LexerIdeBindings");
          _builder.append(_typeRef, "  ");
          _builder.append(".CONTENT_ASSIST))");
          _builder.newLineIfNotEmpty();
          _builder.append("  ");
          _builder.append(".to(");
          _builder.append(caLexerClass, "  ");
          _builder.append(".class);");
          _builder.newLineIfNotEmpty();
        }
      })
      // registration of the 'ContentAssistLexer' is put in front of the 'HighlightingLexer'
      //  in order to let 'caLexerClass' get added to the imports, since it is referenced
      //  several times and the lexer classes' simple names are usually identical
      .addConfiguredBinding("HighlightingLexer", new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("binder.bind(");
          _builder.append(Lexer.class);
          _builder.append(".class)");
          _builder.newLineIfNotEmpty();
          _builder.append("  ");
          _builder.append(".annotatedWith(");
          _builder.append(Names.class, "  ");
          _builder.append(".named(");
          TypeReference _typeRef = TypeReference.typeRef("org.eclipse.xtext.ide.LexerIdeBindings");
          _builder.append(_typeRef, "  ");
          _builder.append(".HIGHLIGHTING))");
          _builder.newLineIfNotEmpty();
          _builder.append("  ");
          _builder.append(".to(");
          TypeReference _lexerClass = AnnotationAwareXtextAntlrGeneratorFragment2.this.productionNaming.getLexerClass(AnnotationAwareXtextAntlrGeneratorFragment2.this.getGrammar());
          _builder.append(_lexerClass, "  ");
          _builder.append(".class);");
          _builder.newLineIfNotEmpty();
        }
      })
      .addConfiguredBinding("HighlightingTokenDefProvider", new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("binder.bind(");
          _builder.append(ITokenDefProvider.class);
          _builder.append(".class)");
          _builder.newLineIfNotEmpty();
          _builder.append("  ");
          _builder.append(".annotatedWith(");
          _builder.append(Names.class, "  ");
          _builder.append(".named(");
          TypeReference _typeRef = TypeReference.typeRef("org.eclipse.xtext.ide.LexerIdeBindings");
          _builder.append(_typeRef, "  ");
          _builder.append(".HIGHLIGHTING))");
          _builder.newLineIfNotEmpty();
          _builder.append("  ");
          _builder.append(".to(");
          _builder.append(AntlrTokenDefProvider.class, "  ");
          _builder.append(".class);");
          _builder.newLineIfNotEmpty();
        }
      })
      .addTypeToType(
        new TypeReference("org.eclipse.xtext.ui.editor.contentassist", "ContentAssistContext.Factory"),
        TypeReference.typeRef("org.eclipse.xtext.ui.editor.contentassist.antlr.DelegatingContentAssistContextFactory"))
      .addConfiguredBinding("ContentAssistLexerProvider", new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("binder.bind(");
          _builder.append(caLexerClass);
          _builder.append(".class).toProvider(");
          _builder.append(LexerProvider.class);
          _builder.append(".create(");
          _builder.append(caLexerClass);
          _builder.append(".class));");
          _builder.newLineIfNotEmpty();
        }
      });

    if (getProjectConfig().getGenericIde().getSrcGen() != null) {
      uiBindings.addTypeToType(TypeReference.typeRef("org.eclipse.xtext.ide.editor.contentassist.antlr.IContentAssistParser"), naming.getParserClass(getGrammar()));
    }

    if (hasSyntheticTerminalRule()) {
      uiBindings.addTypeToType(
        TypeReference.typeRef("org.eclipse.xtext.ide.editor.contentassist.CompletionPrefixProvider"),
        TypeReference.typeRef("org.eclipse.xtext.ide.editor.contentassist.IndentationAwareCompletionPrefixProvider"));
    }
    uiBindings.contributeTo(getLanguage().getEclipsePluginGenModule());
  }
}
