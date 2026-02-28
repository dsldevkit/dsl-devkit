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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.EnumLiteralDeclaration;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.UnorderedGroup;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.eclipse.xtext.xtext.generator.parser.antlr.AntlrGrammarGenUtil;
import org.eclipse.xtext.xtext.generator.parser.antlr.AntlrOptions;
import org.eclipse.xtext.xtext.generator.parser.antlr.GrammarNaming;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * This implementation is strongly based on AntlrGrammarGenerator but with a different base class.
 * The following extension is supported:
 *
 *   A datatype grammar rule containing only one ID terminal rule can be annotated
 *   with @KeywordRule annotation provided a list of words so only these words can
 *   be accepted by this rule.
 *
 *   Example:
 *
 *   /**
 *     * @KeywordRule(visible, invisible)
 *     * /
 *   VisibleKind returns VisibleKind:
 *     ID
 *   ;
 *
 *   The above rule will accept only 'visible' and 'invisible' identifiers.
 *   This rule in ASMD is called a keyword rule because it is intended to replace
 *   usages of keywords which shall not be reserved words in the language.
 *   Reserved words are words that are not allowed to be used in identifiers.
 *
 *   The above example can therefore replace the following enumeration:
 *
 *   enum VisibleKind :
 *       VISIBLE   = "visible"
 *     | INVISIBLE = "invisible"
 *   ;
 *
 *   Please note that a corresponding value converter is needed.
 *
 *   Implementation remark:
 *     - This template will insert validating semantic predicates in the rule
 *     - If the rule is used from an alternative a gated semantic predicate will
 *       be used in the alternative
 *     - Error messages will be adjusted correspondingly
 */
@Singleton
public class AnnotationAwareAntlrGrammarGenerator extends AbstractAnnotationAwareAntlrGrammarGenerator {

  @Inject
  private GrammarNaming naming;

  private String lexerSuperClassName = "";

  public void setLexerSuperClassName(final String lexerSuperClassName) {
    this.lexerSuperClassName = lexerSuperClassName;
  }

  @Override
  protected GrammarNaming getGrammarNaming() {
    return this.naming;
  }

  @Override
  protected String compileParserImports(final Grammar it, final AntlrOptions options) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("import org.eclipse.xtext.*;");
    _builder.newLine();
    _builder.append("import org.eclipse.xtext.parser.*;");
    _builder.newLine();
    _builder.append("import org.eclipse.xtext.parser.impl.*;");
    _builder.newLine();
    _builder.append("import org.eclipse.emf.ecore.util.EcoreUtil;");
    _builder.newLine();
    _builder.append("import org.eclipse.emf.ecore.EObject;");
    _builder.newLine();
    {
      boolean _isEmpty = GrammarUtil.allEnumRules(it).isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append("import org.eclipse.emf.common.util.Enumerator;");
        _builder.newLine();
      }
    }
    _builder.append("import ");
    String _name = this.getGrammarNaming().getInternalParserSuperClass(it).getName();
    _builder.append(_name);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("import org.eclipse.xtext.parser.antlr.XtextTokenStream;");
    _builder.newLine();
    _builder.append("import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;");
    _builder.newLine();
    {
      if ((!IterableExtensions.isEmpty(Iterables.filter(Iterables.concat(ListExtensions.map(GrammarUtil.allParserRules(it), (ParserRule it_1) -> EcoreUtil2.eAllContentsAsList(it_1))), UnorderedGroup.class))) && options.isBacktrack()) {
        _builder.append("import org.eclipse.xtext.parser.antlr.IUnorderedGroupHelper.UnorderedGroupState;");
        _builder.newLine();
      }
    }
    _builder.append("import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;");
    _builder.newLine();
    _builder.append("import ");
    String _name_1 = this._grammarAccessExtensions.getGrammarAccess(it).getName();
    _builder.append(_name_1);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    String _compileParserImports = super.compileParserImports(it, options);
    _builder.append(_compileParserImports);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    return _builder.toString();
  }

  @Override
  protected String compileParserMembers(final Grammar it, final AntlrOptions options) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("@");
    {
      boolean _isCombinedGrammar = this.isCombinedGrammar();
      if (_isCombinedGrammar) {
        _builder.append("parser::");
      }
    }
    _builder.append("members {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    {
      boolean _isBacktrack = options.isBacktrack();
      if (_isBacktrack) {
        _builder.append("/*");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("This grammar contains a lot of empty actions to work around a bug in ANTLR.");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.");
        _builder.newLine();
        _builder.append("*/");
        _builder.newLine();
        _builder.newLine();
      }
    }
    _builder.append("    ");
    CharSequence _compileParserMemberDeclarations = this.compileParserMemberDeclarations(it, "private");
    _builder.append(_compileParserMemberDeclarations, "    ");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public ");
    String _simpleName = this.naming.getInternalParserClass(it).getSimpleName();
    _builder.append(_simpleName, "    ");
    _builder.append("(TokenStream input, ");
    String _simpleName_1 = this._grammarAccessExtensions.getGrammarAccess(it).getSimpleName();
    _builder.append(_simpleName_1, "    ");
    _builder.append(" grammarAccess, ParserContext parserContext, ");
    String _semanticPredicatesSimpleName = this.predicatesNaming.getSemanticPredicatesSimpleName(it);
    _builder.append(_semanticPredicatesSimpleName, "    ");
    _builder.append(" predicates) {");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("this(input);");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("this.grammarAccess = grammarAccess;");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("this.predicates = predicates;");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("this.parserContext = parserContext;");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("parserContext.setTokenStream(input);");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("registerRules(grammarAccess.getGrammar());");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    CharSequence _compileParserSetTokenStreamMethod = this.compileParserSetTokenStreamMethod();
    _builder.append(_compileParserSetTokenStreamMethod, "    ");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("protected String getFirstRuleName() {");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("return \"");
    String _name2 = AntlrGrammarGenUtil.<ParserRule>getOriginalElement(IterableExtensions.head(GrammarUtil.allParserRules(it))).getName();
    _builder.append(_name2, "      ");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("protected ");
    String _simpleName_2 = this._grammarAccessExtensions.getGrammarAccess(it).getSimpleName();
    _builder.append(_simpleName_2, "    ");
    _builder.append(" getGrammarAccess() {");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("return grammarAccess;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }

  @Override
  protected String compileRuleCatch(final Grammar it, final AntlrOptions options) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("@rulecatch {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("catch (RecognitionException re) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("recover(input,re);");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("appendSkippedTokens();");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }

  @Override
  protected boolean shouldBeSkipped(final TerminalRule it, final Grammar grammar) {
    return false;
  }

  @Override
  protected CharSequence _compileRule(final ParserRule it, final Grammar grammar, final AntlrOptions options) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isValidEntryRule = AntlrGrammarGenUtil.isValidEntryRule(it);
      if (_isValidEntryRule) {
        String _compileEntryRule = this.compileEntryRule(it, grammar, options);
        _builder.append(_compileEntryRule);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    String _compileEBNF = this.compileEBNF(it, options);
    _builder.append(_compileEBNF);
    _builder.newLineIfNotEmpty();
    return _builder;
  }

  protected String compileEntryRule(final ParserRule it, final Grammar grammar, final AntlrOptions options) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("// Entry rule ");
    String _entryRuleName = this._grammarAccessExtensions.entryRuleName(AntlrGrammarGenUtil.<ParserRule>getOriginalElement(it));
    _builder.append(_entryRuleName);
    _builder.newLineIfNotEmpty();
    String _entryRuleName_1 = this._grammarAccessExtensions.entryRuleName(AntlrGrammarGenUtil.<ParserRule>getOriginalElement(it));
    _builder.append(_entryRuleName_1);
    _builder.append(" returns ");
    String _compileEntryReturns = this.compileEntryReturns(it, options);
    _builder.append(_compileEntryReturns);
    CharSequence _compileEntryInit = this.compileEntryInit(it, options);
    _builder.append(_compileEntryInit);
    _builder.append(":");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("{ ");
    CharSequence _newCompositeNode = this.newCompositeNode(it);
    _builder.append(_newCompositeNode, "  ");
    _builder.append(" }");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("iv_");
    String _ruleName = this._grammarAccessExtensions.ruleName(AntlrGrammarGenUtil.<ParserRule>getOriginalElement(it));
    _builder.append(_ruleName, "  ");
    _builder.append("=");
    String _ruleName_1 = this._grammarAccessExtensions.ruleName(it);
    _builder.append(_ruleName_1, "  ");
    String _defaultArgumentList = AntlrGrammarGenUtil.getDefaultArgumentList(it);
    _builder.append(_defaultArgumentList, "  ");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("{ $current=$iv_");
    String _ruleName_2 = this._grammarAccessExtensions.ruleName(it);
    _builder.append(_ruleName_2, "  ");
    _builder.append(".current");
    {
      boolean _isDatatypeRule = GrammarUtil.isDatatypeRule(AntlrGrammarGenUtil.<ParserRule>getOriginalElement(it));
      if (_isDatatypeRule) {
        _builder.append(".getText()");
      }
    }
    _builder.append("; }");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("EOF;");
    _builder.newLine();
    CharSequence _compileEntryFinally = this.compileEntryFinally(it, options);
    _builder.append(_compileEntryFinally);
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }

  protected String compileEntryReturns(final ParserRule it, final AntlrOptions options) {
    boolean _isDatatypeRule = GrammarUtil.isDatatypeRule(AntlrGrammarGenUtil.<ParserRule>getOriginalElement(it));
    if (_isDatatypeRule) {
      return "[String current=null]";
    } else {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("[");
      String _currentType = this.getCurrentType();
      _builder.append(_currentType);
      _builder.append(" current=null]");
      return _builder.toString();
    }
  }

  @Override
  protected String compileInit(final AbstractRule it, final AntlrOptions options) {
    StringConcatenation _builder = new StringConcatenation();
    {
      if (it instanceof ParserRule) {
        boolean _isPassCurrentIntoFragment = this.isPassCurrentIntoFragment();
        boolean _not = (!_isPassCurrentIntoFragment);
        String _parameterList = AntlrGrammarGenUtil.getParameterList((ParserRule) it, Boolean.valueOf(_not), this.getCurrentType());
        _builder.append(_parameterList);
      }
    }
    _builder.append(" returns ");
    CharSequence _compileReturns = this.compileReturns(it, options);
    _builder.append(_compileReturns);
    _builder.newLineIfNotEmpty();
    {
      boolean _hasNoBacktrackAnnotation = this.annotations.hasNoBacktrackAnnotation(it);
      if (_hasNoBacktrackAnnotation) {
        _builder.append("// Enclosing rule was annotated with @NoBacktrack");
        _builder.newLine();
        _builder.append("options { backtrack=false; }");
        _builder.newLine();
      }
    }
    _builder.append("@init {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("enterRule();");
    _builder.newLine();
    _builder.append("  ");
    CharSequence _compileInitHiddenTokens = this.compileInitHiddenTokens(it, options);
    _builder.append(_compileInitHiddenTokens, "  ");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    CharSequence _compileInitUnorderedGroups = this.compileInitUnorderedGroups(it, options);
    _builder.append(_compileInitUnorderedGroups, "  ");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.append("@after {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("leaveRule();");
    _builder.newLine();
    _builder.append("}");
    return _builder.toString();
  }

  protected CharSequence compileReturns(final AbstractRule it, final AntlrOptions options) {
    if (it instanceof EnumRule) {
      return "[Enumerator current=null]";
    }
    if (it instanceof ParserRule) {
      if (GrammarUtil.isDatatypeRule(AntlrGrammarGenUtil.<ParserRule>getOriginalElement((ParserRule) it))) {
        return "[AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()]";
      }
      if (GrammarUtil.isEObjectFragmentRule(AntlrGrammarGenUtil.<ParserRule>getOriginalElement((ParserRule) it))) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("[");
        _builder.append(this.getCurrentType());
        _builder.append(" current=in_current]");
        return _builder;
      }
      StringConcatenation _builder2 = new StringConcatenation();
      _builder2.append("[");
      _builder2.append(this.getCurrentType());
      _builder2.append(" current=null]");
      return _builder2;
    }
    throw new IllegalStateException("Unexpected rule: " + it);
  }

  @Override
  protected String _dataTypeEbnf2(final Keyword it, final boolean supportActions) {
    if (supportActions) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("kw=");
      String __dataTypeEbnf2 = super._dataTypeEbnf2(it, supportActions);
      _builder.append(__dataTypeEbnf2);
      _builder.newLineIfNotEmpty();
      _builder.append("{");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("$current.merge(kw);");
      _builder.newLine();
      _builder.append("  ");
      CharSequence _newLeafNode = this.newLeafNode(it, "kw");
      _builder.append(_newLeafNode, "  ");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      return _builder.toString();
    } else {
      return super._dataTypeEbnf2(it, supportActions);
    }
  }

  @Override
  protected String _ebnf2(final Action it, final AntlrOptions options, final boolean supportActions) {
    if (supportActions) {
      StringConcatenation _builder = new StringConcatenation();
      {
        boolean _isBacktrack = options.isBacktrack();
        if (_isBacktrack) {
          _builder.append("{");
          _builder.newLine();
          _builder.append("  ");
          _builder.append("/* */");
          _builder.newLine();
          _builder.append("}");
          _builder.newLine();
        }
      }
      _builder.append("{");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("$current = forceCreateModelElement");
      {
        String _feature = it.getFeature();
        if (_feature != null) {
          _builder.append("And");
          String _firstUpper = StringExtensions.toFirstUpper(this._grammarAccessExtensions.setOrAdd(it));
          _builder.append(_firstUpper, "  ");
        }
      }
      _builder.append("(");
      _builder.newLineIfNotEmpty();
      _builder.append("    ");
      _builder.append("grammarAccess.");
      String _grammarElementAccess = this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<Action>getOriginalElement(it));
      _builder.append(_grammarElementAccess, "    ");
      _builder.append(",");
      _builder.newLineIfNotEmpty();
      _builder.append("    ");
      _builder.append("$current);");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      return _builder.toString();
    } else {
      return super._ebnf2(it, options, supportActions);
    }
  }

  @Override
  protected String _ebnf2(final Keyword it, final AntlrOptions options, final boolean supportActions) {
    if (!supportActions) {
      return super._ebnf2(it, options, supportActions);
    } else if (GrammarUtil.isAssigned(it)) {
      StringConcatenation _builder = new StringConcatenation();
      String __ebnf2 = super._ebnf2(it, options, supportActions);
      _builder.append(__ebnf2);
      _builder.newLineIfNotEmpty();
      _builder.append("{");
      _builder.newLine();
      _builder.append("  ");
      CharSequence _newLeafNode = this.newLeafNode(it, this._grammarAccessExtensions.localVar(GrammarUtil.containingAssignment(it), it));
      _builder.append(_newLeafNode, "  ");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      return _builder.toString();
    } else {
      StringConcatenation _builder_1 = new StringConcatenation();
      String _localVar = this._grammarAccessExtensions.localVar(it);
      _builder_1.append(_localVar);
      _builder_1.append("=");
      String __ebnf2_1 = super._ebnf2(it, options, supportActions);
      _builder_1.append(__ebnf2_1);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("{");
      _builder_1.newLine();
      _builder_1.append("  ");
      CharSequence _newLeafNode_1 = this.newLeafNode(it, this._grammarAccessExtensions.localVar(it));
      _builder_1.append(_newLeafNode_1, "  ");
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("}");
      _builder_1.newLine();
      return _builder_1.toString();
    }
  }

  @Override
  protected String _ebnf2(final EnumLiteralDeclaration it, final AntlrOptions options, final boolean supportActions) {
    if (!supportActions) {
      return super._ebnf2(it, options, supportActions);
    } else {
      StringConcatenation _builder = new StringConcatenation();
      String _localVar = this._grammarAccessExtensions.localVar(it);
      _builder.append(_localVar);
      _builder.append("=");
      String __ebnf2 = super._ebnf2(it, options, supportActions);
      _builder.append(__ebnf2);
      _builder.newLineIfNotEmpty();
      _builder.append("{");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("$current = grammarAccess.");
      String _grammarElementAccess = this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<EnumLiteralDeclaration>getOriginalElement(it));
      _builder.append(_grammarElementAccess, "  ");
      _builder.append(".getEnumLiteral().getInstance();");
      _builder.newLineIfNotEmpty();
      _builder.append("  ");
      CharSequence _newLeafNode = this.newLeafNode(it, this._grammarAccessExtensions.localVar(it));
      _builder.append(_newLeafNode, "  ");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      return _builder.toString();
    }
  }

  @Override
  protected String crossrefEbnf(final AbstractRule it, final RuleCall call, final CrossReference ref, final boolean supportActions) {
    if (supportActions) {
      if (it instanceof EnumRule || it instanceof ParserRule) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("{");
        _builder.newLine();
        _builder.append("  ");
        CharSequence _newCompositeNode = this.newCompositeNode(ref);
        _builder.append(_newCompositeNode, "  ");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        String _ruleName = this._grammarAccessExtensions.ruleName(it);
        _builder.append(_ruleName);
        String _argumentList = AntlrGrammarGenUtil.getArgumentList(call, this.isPassCurrentIntoFragment(), (!supportActions));
        _builder.append(_argumentList);
        _builder.newLineIfNotEmpty();
        _builder.append("{");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("afterParserOrEnumRuleCall();");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        return _builder.toString();
      } else if (it instanceof TerminalRule) {
        StringConcatenation _builder_1 = new StringConcatenation();
        String _localVar = this._grammarAccessExtensions.localVar(GrammarUtil.containingAssignment(ref));
        _builder_1.append(_localVar);
        _builder_1.append("=");
        String _ruleName_1 = this._grammarAccessExtensions.ruleName(it);
        _builder_1.append(_ruleName_1);
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("{");
        _builder_1.newLine();
        _builder_1.append("  ");
        CharSequence _newLeafNode = this.newLeafNode(ref, this._grammarAccessExtensions.localVar(GrammarUtil.containingAssignment(ref)));
        _builder_1.append(_newLeafNode, "  ");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("}");
        _builder_1.newLine();
        return _builder_1.toString();
      } else {
        throw new IllegalStateException("crossrefEbnf is not supported for " + it);
      }
    } else {
      return super.crossrefEbnf(it, call, ref, supportActions);
    }
  }

  @Override
  protected String _assignmentEbnf(final AbstractElement it, final Assignment assignment, final AntlrOptions options, final boolean supportActions) {
    if (supportActions) {
      StringConcatenation _builder = new StringConcatenation();
      String _localVar = this._grammarAccessExtensions.localVar(assignment, it);
      _builder.append(_localVar);
      _builder.append("=");
      String __assignmentEbnf = super._assignmentEbnf(it, assignment, options, supportActions);
      _builder.append(__assignmentEbnf);
      _builder.newLineIfNotEmpty();
      _builder.append("{");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("if ($current==null) {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("$current = ");
      CharSequence _createModelElement = this.createModelElement(assignment);
      _builder.append(_createModelElement, "    ");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("  ");
      String _setOrAdd = this._grammarAccessExtensions.setOrAdd(assignment);
      _builder.append(_setOrAdd, "  ");
      _builder.append("WithLastConsumed($current, \"");
      String _feature = assignment.getFeature();
      _builder.append(_feature, "  ");
      _builder.append("\", ");
      String _localVar_1 = this._grammarAccessExtensions.localVar(assignment, it);
      _builder.append(_localVar_1, "  ");
      {
        boolean _isBooleanAssignment = GrammarUtil.isBooleanAssignment(assignment);
        if (_isBooleanAssignment) {
          _builder.append(" != null");
        }
      }
      _builder.append(", ");
      CharSequence _stringLiteral = this._grammarAccessExtensions.toStringLiteral(assignment.getTerminal());
      _builder.append(_stringLiteral, "  ");
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      return _builder.toString();
    } else {
      return super._assignmentEbnf(it, assignment, options, supportActions);
    }
  }

  @Override
  protected boolean isPassCurrentIntoFragment() {
    return true;
  }

  protected CharSequence createModelElement(final EObject grammarElement) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("createModelElement(grammarAccess.");
    String _grammarElementAccess = this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<ParserRule>getOriginalElement(GrammarUtil.containingParserRule(grammarElement)));
    _builder.append(_grammarElementAccess);
    _builder.append(")");
    return _builder;
  }

  protected CharSequence createModelElementForParent(final EObject grammarElement) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("createModelElementForParent(grammarAccess.");
    String _grammarElementAccess = this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<ParserRule>getOriginalElement(GrammarUtil.containingParserRule(grammarElement)));
    _builder.append(_grammarElementAccess);
    _builder.append(")");
    return _builder;
  }

  protected CharSequence newCompositeNode(final EObject it) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("newCompositeNode(grammarAccess.");
    String _grammarElementAccess = this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<EObject>getOriginalElement(it));
    _builder.append(_grammarElementAccess);
    _builder.append(");");
    return _builder;
  }

  protected CharSequence newLeafNode(final EObject it, final String token) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("newLeafNode(");
    _builder.append(token);
    _builder.append(", grammarAccess.");
    String _grammarElementAccess = this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<EObject>getOriginalElement(it));
    _builder.append(_grammarElementAccess);
    _builder.append(");");
    return _builder;
  }

  /**
   * Add gated predicate, if necessary, before the action preceding the rule call.
   */
  @Override
  protected String _dataTypeEbnf2(final RuleCall it, final boolean supportActions) {
    if (supportActions) {
      AbstractRule _rule = it.getRule();
      if ((_rule instanceof EnumRule && GrammarUtil.isAssigned(it)) || (_rule instanceof ParserRule && GrammarUtil.isAssigned(it))) {
        return super._dataTypeEbnf2(it, supportActions);
      } else if (_rule instanceof EnumRule || _rule instanceof ParserRule) {
        StringConcatenation _builder = new StringConcatenation();
        {
          boolean _isGatedPredicateRequired = this.annotations.isGatedPredicateRequired(it);
          if (_isGatedPredicateRequired) {
            _builder.append(this.annotations.generateGatedPredicate(it));
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append("{");
        _builder.newLine();
        _builder.append("  ");
        _builder.append(this.newCompositeNode(it), "  ");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.append(this._grammarAccessExtensions.localVar(it));
        _builder.append("=");
        _builder.append(super._dataTypeEbnf2(it, supportActions));
        _builder.append(AntlrGrammarGenUtil.getArgumentList(it, this.isPassCurrentIntoFragment(), (!supportActions)));
        _builder.newLineIfNotEmpty();
        _builder.append("{");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("$current.merge(");
        _builder.append(this._grammarAccessExtensions.localVar(it), "  ");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.append("{");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("afterParserOrEnumRuleCall();");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        return _builder.toString();
      } else if (_rule instanceof TerminalRule) {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(this._grammarAccessExtensions.localVar(it));
        _builder_1.append("=");
        _builder_1.append(super._dataTypeEbnf2(it, supportActions));
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("{");
        _builder_1.newLine();
        _builder_1.append("  ");
        _builder_1.append("$current.merge(");
        _builder_1.append(this._grammarAccessExtensions.localVar(it), "  ");
        _builder_1.append(");");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.append("{");
        _builder_1.newLine();
        _builder_1.append("  ");
        _builder_1.append(this.newLeafNode(it, this._grammarAccessExtensions.localVar(it)), "  ");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("}");
        _builder_1.newLine();
        return _builder_1.toString();
      } else {
        return super._dataTypeEbnf2(it, supportActions);
      }
    } else {
      return super._dataTypeEbnf2(it, supportActions);
    }
  }

  /**
   * Inserts validating predicate only. Gated predicates will be inserted in alternatives if needed.
   */
  @Override
  protected String compileEBNF(final AbstractRule it, final AntlrOptions options) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("// Rule ");
    String _name = AntlrGrammarGenUtil.<AbstractRule>getOriginalElement(it).getName();
    _builder.append(_name);
    _builder.newLineIfNotEmpty();
    String _ruleName = this._grammarAccessExtensions.ruleName(it);
    _builder.append(_ruleName);
    String _compileInit = this.compileInit(it, options);
    _builder.append(_compileInit);
    _builder.append(":");
    _builder.newLineIfNotEmpty();
    {
      if ((it instanceof ParserRule) && GrammarUtil.isDatatypeRule(AntlrGrammarGenUtil.<AbstractRule>getOriginalElement(it))) {
        _builder.append("  ");
        {
          boolean _hasValidatingPredicate = this.annotations.hasValidatingPredicate(it);
          if (_hasValidatingPredicate) {
            _builder.append(this.annotations.generateValidatingPredicate(it), "  ");
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        String _dataTypeEbnf = this.dataTypeEbnf(it.getAlternatives(), true);
        _builder.append(_dataTypeEbnf, "  ");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("  ");
        String _ebnf = this.ebnf(it.getAlternatives(), options, true);
        _builder.append(_ebnf, "  ");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append(";");
    _builder.newLine();
    String _compileFinally = this.compileFinally(it, options);
    _builder.append(_compileFinally);
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }

  @Override
  protected String dataTypeEbnf(final AbstractElement it, final boolean supportActions) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _mustBeParenthesized = this.mustBeParenthesized(it);
      if (_mustBeParenthesized) {
        _builder.append("(");
        _builder.newLineIfNotEmpty();
        {
          boolean _hasNoBacktrackAnnotation = this.annotations.hasNoBacktrackAnnotation(it);
          if (_hasNoBacktrackAnnotation) {
            _builder.append("  ");
            _builder.append("// Enclosing rule was annotated with @NoBacktrack");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("options { backtrack=false; }:");
            _builder.newLine();
          }
        }
        _builder.append("  ");
        String _dataTypeEbnfPredicate = this.dataTypeEbnfPredicate(it);
        _builder.append(_dataTypeEbnfPredicate, "  ");
        String _dataTypeEbnf2 = this.dataTypeEbnf2(it, supportActions);
        _builder.append(_dataTypeEbnf2, "  ");
        _builder.newLineIfNotEmpty();
        _builder.append(")");
      } else {
        String _dataTypeEbnf2_1 = this.dataTypeEbnf2(it, supportActions);
        _builder.append(_dataTypeEbnf2_1);
      }
    }
    String _cardinality = it.getCardinality();
    _builder.append(_cardinality);
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }

  @Override
  protected String ebnf(final AbstractElement it, final AntlrOptions options, final boolean supportActions) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _mustBeParenthesized = this.mustBeParenthesized(it);
      if (_mustBeParenthesized) {
        _builder.append("(");
        _builder.newLineIfNotEmpty();
        {
          boolean _hasNoBacktrackAnnotation = this.annotations.hasNoBacktrackAnnotation(it);
          if (_hasNoBacktrackAnnotation) {
            _builder.append("  ");
            _builder.append("// Enclosing rule was annotated with @NoBacktrack");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("options { backtrack=false; }:");
            _builder.newLine();
          }
        }
        _builder.append("  ");
        String _ebnfPredicate = this.ebnfPredicate(it, options);
        _builder.append(_ebnfPredicate, "  ");
        String _ebnf2 = this.ebnf2(it, options, supportActions);
        _builder.append(_ebnf2, "  ");
        _builder.newLineIfNotEmpty();
        _builder.append(")");
      } else {
        String _ebnf2_1 = this.ebnf2(it, options, supportActions);
        _builder.append(_ebnf2_1);
      }
    }
    String _cardinality = it.getCardinality();
    _builder.append(_cardinality);
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }

  /**
   * Add gated predicate, if necessary, before the action preceding the assignment.
   */
  @Override
  protected String _assignmentEbnf(final RuleCall it, final Assignment assignment, final AntlrOptions options, final boolean supportActions) {
    if (supportActions) {
      AbstractRule _rule = it.getRule();
      if (_rule instanceof EnumRule || _rule instanceof ParserRule) {
        StringConcatenation _builder = new StringConcatenation();
        {
          boolean _isGatedPredicateRequired = this.annotations.isGatedPredicateRequired(it);
          if (_isGatedPredicateRequired) {
            _builder.append(this.annotations.generateGatedPredicate(it));
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append("{");
        _builder.newLine();
        _builder.append("  ");
        _builder.append(this.newCompositeNode(it), "  ");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.append(this._grammarAccessExtensions.localVar(assignment, it));
        _builder.append("=");
        _builder.append(super._assignmentEbnf(it, assignment, options, supportActions));
        _builder.newLineIfNotEmpty();
        _builder.append("{");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("if ($current==null) {");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("$current = ");
        _builder.append(this.createModelElementForParent(assignment), "    ");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("  ");
        _builder.append(this._grammarAccessExtensions.setOrAdd(assignment), "  ");
        _builder.append("(");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("$current,");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("\"");
        _builder.append(assignment.getFeature(), "    ");
        _builder.append("\",");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append(this._grammarAccessExtensions.localVar(assignment, it), "    ");
        {
          boolean _isBooleanAssignment = GrammarUtil.isBooleanAssignment(assignment);
          if (_isBooleanAssignment) {
            _builder.append(" != null");
          }
        }
        _builder.append(",");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append(this._grammarAccessExtensions.toStringLiteral(it), "    ");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        _builder.append("afterParserOrEnumRuleCall();");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        return _builder.toString();
      } else if (_rule instanceof TerminalRule) {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(this._grammarAccessExtensions.localVar(assignment, it));
        _builder_1.append("=");
        _builder_1.append(super._assignmentEbnf(it, assignment, options, supportActions));
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("{");
        _builder_1.newLine();
        _builder_1.append("  ");
        _builder_1.append(this.newLeafNode(it, this._grammarAccessExtensions.localVar(assignment, it)), "  ");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.append("{");
        _builder_1.newLine();
        _builder_1.append("  ");
        _builder_1.append("if ($current==null) {");
        _builder_1.newLine();
        _builder_1.append("    ");
        _builder_1.append("$current = ");
        _builder_1.append(this.createModelElement(assignment), "    ");
        _builder_1.append(";");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("  ");
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.append("  ");
        _builder_1.append(this._grammarAccessExtensions.setOrAdd(assignment), "  ");
        _builder_1.append("WithLastConsumed(");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("    ");
        _builder_1.append("$current,");
        _builder_1.newLine();
        _builder_1.append("    ");
        _builder_1.append("\"");
        _builder_1.append(assignment.getFeature(), "    ");
        _builder_1.append("\",");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("    ");
        _builder_1.append(this._grammarAccessExtensions.localVar(assignment, it), "    ");
        {
          boolean _isBooleanAssignment2 = GrammarUtil.isBooleanAssignment(assignment);
          if (_isBooleanAssignment2) {
            _builder_1.append(" != null");
          }
        }
        _builder_1.append(",");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("    ");
        _builder_1.append(this._grammarAccessExtensions.toStringLiteral(it), "    ");
        _builder_1.append(");");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("}");
        _builder_1.newLine();
        return _builder_1.toString();
      } else {
        throw new IllegalStateException("assignmentEbnf is not supported for " + it);
      }
    } else {
      return super._assignmentEbnf(it, assignment, options, supportActions);
    }
  }

  /**
   * Add gated predicate, if necessary, before the action preceding the cross reference.
   */
  @Override
  protected String _assignmentEbnf(final CrossReference it, final Assignment assignment, final AntlrOptions options, final boolean supportActions) {
    if (supportActions) {
      StringConcatenation _builder = new StringConcatenation();
      {
        boolean _isGatedPredicateRequired = this.annotations.isGatedPredicateRequired(it);
        if (_isGatedPredicateRequired) {
          _builder.append(this.annotations.generateGatedPredicate(it));
        }
      }
      _builder.newLineIfNotEmpty();
      {
        boolean _isBacktrack = options.isBacktrack();
        if (_isBacktrack) {
          _builder.append("{");
          _builder.newLine();
          _builder.append("  ");
          _builder.append("/* */");
          _builder.newLine();
          _builder.append("}");
          _builder.newLine();
        }
      }
      _builder.append("{");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("if ($current==null) {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("$current = ");
      _builder.append(this.createModelElement(assignment), "    ");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append(super._assignmentEbnf(it, assignment, options, supportActions));
      return _builder.toString();
    } else {
      return super._assignmentEbnf(it, assignment, options, supportActions);
    }
  }

  /**
   * Add gated predicate, if necessary, before the action preceding the rule call.
   */
  @Override
  protected String _ebnf2(final RuleCall it, final AntlrOptions options, final boolean supportActions) {
    if (!supportActions) {
      return super._ebnf2(it, options, supportActions);
    } else {
      AbstractRule rule = it.getRule();
      if ((rule instanceof EnumRule && GrammarUtil.isAssigned(it)) || (rule instanceof ParserRule && GrammarUtil.isAssigned(it))) {
        return super._ebnf2(it, options, supportActions);
      } else if (rule instanceof EnumRule || (rule instanceof ParserRule && GrammarUtil.isDatatypeRule(AntlrGrammarGenUtil.<ParserRule>getOriginalElement((ParserRule) rule)))) {
        StringConcatenation _builder = new StringConcatenation();
        {
          if (this.annotations.isGatedPredicateRequired(it)) {
            _builder.append(this.annotations.generateGatedPredicate(it));
          }
        }
        _builder.newLineIfNotEmpty();
        {
          if (options.isBacktrack()) {
            _builder.append("{");
            _builder.newLine();
            _builder.append("  ");
            _builder.append("/* */");
            _builder.newLine();
            _builder.append("}");
            _builder.newLine();
          }
        }
        _builder.append("{");
        _builder.newLine();
        _builder.append("  ");
        _builder.append(this.newCompositeNode(it), "  ");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.append(super._ebnf2(it, options, supportActions));
        _builder.newLineIfNotEmpty();
        _builder.append("{");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("afterParserOrEnumRuleCall();");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        return _builder.toString();
      } else if (rule instanceof ParserRule) {
        StringConcatenation _builder_1 = new StringConcatenation();
        {
          if (this.annotations.isGatedPredicateRequired(it)) {
            _builder_1.append(this.annotations.generateGatedPredicate(it));
          }
        }
        _builder_1.newLineIfNotEmpty();
        {
          if (options.isBacktrack()) {
            _builder_1.append("{");
            _builder_1.newLine();
            _builder_1.append("  ");
            _builder_1.append("/* */");
            _builder_1.newLine();
            _builder_1.append("}");
            _builder_1.newLine();
          }
        }
        _builder_1.append("{");
        _builder_1.newLine();
        {
          if (GrammarUtil.isEObjectFragmentRuleCall(it)) {
            _builder_1.append("  ");
            _builder_1.append("if ($current==null) {");
            _builder_1.newLine();
            _builder_1.append("    ");
            _builder_1.append("$current = ");
            _builder_1.append(this.createModelElement(it), "    ");
            _builder_1.append(";");
            _builder_1.newLineIfNotEmpty();
            _builder_1.append("  ");
            _builder_1.append("}");
            _builder_1.newLine();
          }
        }
        _builder_1.append("  ");
        _builder_1.append(this.newCompositeNode(it), "  ");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("}");
        _builder_1.newLine();
        String _localVar = this._grammarAccessExtensions.localVar(it);
        _builder_1.append(_localVar);
        _builder_1.append("=");
        _builder_1.append(super._ebnf2(it, options, supportActions));
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("{");
        _builder_1.newLine();
        _builder_1.append("  ");
        _builder_1.append("$current = $");
        _builder_1.append(_localVar, "  ");
        _builder_1.append(".current;");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("  ");
        _builder_1.append("afterParserOrEnumRuleCall();");
        _builder_1.newLine();
        _builder_1.append("}");
        _builder_1.newLine();
        return _builder_1.toString();
      } else if (rule instanceof TerminalRule) {
        StringConcatenation _builder_2 = new StringConcatenation();
        String _localVar2 = this._grammarAccessExtensions.localVar(it);
        _builder_2.append(_localVar2);
        _builder_2.append("=");
        _builder_2.append(super._ebnf2(it, options, supportActions));
        _builder_2.newLineIfNotEmpty();
        _builder_2.append("{");
        _builder_2.newLine();
        _builder_2.append("  ");
        _builder_2.append(this.newLeafNode(it, _localVar2), "  ");
        _builder_2.newLineIfNotEmpty();
        _builder_2.append("}");
        _builder_2.newLine();
        return _builder_2.toString();
      } else {
        return super._ebnf2(it, options, supportActions);
      }
    }
  }

  @Override
  protected String compileLexerImports(final Grammar it, final AntlrOptions options) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("// Hack: Use our own Lexer superclass by means of import.");
    _builder.newLine();
    _builder.append("// Currently there is no other way to specify the superclass for the lexer.");
    _builder.newLine();
    {
      if (!this.lexerSuperClassName.isEmpty()) {
        _builder.append("import ");
        _builder.append(this.lexerSuperClassName);
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("import ");
        _builder.append(this.getGrammarNaming().getLexerSuperClass(it));
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder.toString();
  }
}
