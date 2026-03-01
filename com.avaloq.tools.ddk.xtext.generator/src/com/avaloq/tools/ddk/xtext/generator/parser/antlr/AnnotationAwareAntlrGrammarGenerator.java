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

// CHECKSTYLE:CONSTANTS-OFF

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
@SuppressWarnings({"checkstyle:MethodName", "PMD.UnusedFormalParameter"})
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
    final StringConcatenation builder = new StringConcatenation();
    builder.newLine();
    builder.append("import org.eclipse.xtext.*;");
    builder.newLine();
    builder.append("import org.eclipse.xtext.parser.*;");
    builder.newLine();
    builder.append("import org.eclipse.xtext.parser.impl.*;");
    builder.newLine();
    builder.append("import org.eclipse.emf.ecore.util.EcoreUtil;");
    builder.newLine();
    builder.append("import org.eclipse.emf.ecore.EObject;");
    builder.newLine();
    if (!GrammarUtil.allEnumRules(it).isEmpty()) {
      builder.append("import org.eclipse.emf.common.util.Enumerator;");
      builder.newLine();
    }
    builder.append("import ");
    builder.append(this.getGrammarNaming().getInternalParserSuperClass(it).getName());
    builder.append(';');
    builder.newLineIfNotEmpty();
    builder.append("import org.eclipse.xtext.parser.antlr.XtextTokenStream;");
    builder.newLine();
    builder.append("import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;");
    builder.newLine();
    if ((!IterableExtensions.isEmpty(Iterables.filter(Iterables.concat(ListExtensions.map(GrammarUtil.allParserRules(it), (ParserRule it1) -> EcoreUtil2.eAllContentsAsList(it1))), UnorderedGroup.class))) && options.isBacktrack()) {
      builder.append("import org.eclipse.xtext.parser.antlr.IUnorderedGroupHelper.UnorderedGroupState;");
      builder.newLine();
    }
    builder.append("import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;");
    builder.newLine();
    builder.append("import ");
    builder.append(this._grammarAccessExtensions.getGrammarAccess(it).getName());
    builder.append(';');
    builder.newLineIfNotEmpty();
    builder.append(super.compileParserImports(it, options));
    builder.newLineIfNotEmpty();
    builder.newLine();
    return builder.toString();
  }

  @Override
  protected String compileParserMembers(final Grammar it, final AntlrOptions options) {
    final StringConcatenation builder = new StringConcatenation();
    builder.newLine();
    builder.append('@');
    if (this.isCombinedGrammar()) {
      builder.append("parser::");
    }
    builder.append("members {");
    builder.newLineIfNotEmpty();
    builder.newLine();
    if (options.isBacktrack()) {
      builder.append("/*");
      builder.newLine();
      builder.append("  ");
      builder.append("This grammar contains a lot of empty actions to work around a bug in ANTLR.");
      builder.newLine();
      builder.append("  ");
      builder.append("Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.");
      builder.newLine();
      builder.append("*/");
      builder.newLine();
      builder.newLine();
    }
    builder.append("    ");
    builder.append(this.compileParserMemberDeclarations(it, "private"), "    ");
    builder.newLineIfNotEmpty();
    builder.newLine();
    builder.append("    ");
    builder.append("public ");
    builder.append(this.naming.getInternalParserClass(it).getSimpleName(), "    ");
    builder.append("(TokenStream input, ");
    builder.append(this._grammarAccessExtensions.getGrammarAccess(it).getSimpleName(), "    ");
    builder.append(" grammarAccess, ParserContext parserContext, ");
    builder.append(this.predicatesNaming.getSemanticPredicatesSimpleName(it), "    ");
    builder.append(" predicates) {");
    builder.newLineIfNotEmpty();
    builder.append("        ");
    builder.append("this(input);");
    builder.newLine();
    builder.append("        ");
    builder.append("this.grammarAccess = grammarAccess;");
    builder.newLine();
    builder.append("        ");
    builder.append("this.predicates = predicates;");
    builder.newLine();
    builder.append("        ");
    builder.append("this.parserContext = parserContext;");
    builder.newLine();
    builder.append("        ");
    builder.append("parserContext.setTokenStream(input);");
    builder.newLine();
    builder.append("        ");
    builder.append("registerRules(grammarAccess.getGrammar());");
    builder.newLine();
    builder.append("    ");
    builder.append('}');
    builder.newLine();
    builder.newLine();
    builder.append("    ");
    builder.append(this.compileParserSetTokenStreamMethod(), "    ");
    builder.newLineIfNotEmpty();
    builder.newLine();
    builder.append("    ");
    builder.append("@Override");
    builder.newLine();
    builder.append("    ");
    builder.append("protected String getFirstRuleName() {");
    builder.newLine();
    builder.append("      ");
    builder.append("return \"");
    builder.append(AntlrGrammarGenUtil.<ParserRule>getOriginalElement(IterableExtensions.head(GrammarUtil.allParserRules(it))).getName(), "      ");
    builder.append("\";");
    builder.newLineIfNotEmpty();
    builder.append("    ");
    builder.append('}');
    builder.newLine();
    builder.newLine();
    builder.append("    ");
    builder.append("@Override");
    builder.newLine();
    builder.append("    ");
    builder.append("protected ");
    builder.append(this._grammarAccessExtensions.getGrammarAccess(it).getSimpleName(), "    ");
    builder.append(" getGrammarAccess() {");
    builder.newLineIfNotEmpty();
    builder.append("      ");
    builder.append("return grammarAccess;");
    builder.newLine();
    builder.append("    ");
    builder.append('}');
    builder.newLine();
    builder.newLine();
    builder.append('}');
    builder.newLine();
    return builder.toString();
  }

  @Override
  protected String compileRuleCatch(final Grammar it, final AntlrOptions options) {
    final StringConcatenation builder = new StringConcatenation();
    builder.newLine();
    builder.append("@rulecatch {");
    builder.newLine();
    builder.append("    ");
    builder.append("catch (RecognitionException re) {");
    builder.newLine();
    builder.append("        ");
    builder.append("recover(input,re);");
    builder.newLine();
    builder.append("        ");
    builder.append("appendSkippedTokens();");
    builder.newLine();
    builder.append("    ");
    builder.append('}');
    builder.newLine();
    builder.append('}');
    builder.newLine();
    return builder.toString();
  }

  @Override
  protected boolean shouldBeSkipped(final TerminalRule it, final Grammar grammar) {
    return false;
  }

  @Override
  protected CharSequence _compileRule(final ParserRule it, final Grammar grammar, final AntlrOptions options) {
    final StringConcatenation builder = new StringConcatenation();
    if (AntlrGrammarGenUtil.isValidEntryRule(it)) {
      builder.append(this.compileEntryRule(it, grammar, options));
      builder.newLineIfNotEmpty();
    }
    builder.newLine();
    builder.append(this.compileEBNF(it, options));
    builder.newLineIfNotEmpty();
    return builder;
  }

  protected String compileEntryRule(final ParserRule it, final Grammar grammar, final AntlrOptions options) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append("// Entry rule ");
    builder.append(this._grammarAccessExtensions.entryRuleName(AntlrGrammarGenUtil.<ParserRule>getOriginalElement(it)));
    builder.newLineIfNotEmpty();
    builder.append(this._grammarAccessExtensions.entryRuleName(AntlrGrammarGenUtil.<ParserRule>getOriginalElement(it)));
    builder.append(" returns ");
    builder.append(this.compileEntryReturns(it, options));
    builder.append(this.compileEntryInit(it, options));
    builder.append(':');
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("{ ");
    builder.append(this.newCompositeNode(it), "  ");
    builder.append(" }");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("iv_");
    builder.append(this._grammarAccessExtensions.ruleName(AntlrGrammarGenUtil.<ParserRule>getOriginalElement(it)), "  ");
    builder.append('=');
    builder.append(this._grammarAccessExtensions.ruleName(it), "  ");
    builder.append(AntlrGrammarGenUtil.getDefaultArgumentList(it), "  ");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("{ $current=$iv_");
    builder.append(this._grammarAccessExtensions.ruleName(it), "  ");
    builder.append(".current");
    if (GrammarUtil.isDatatypeRule(AntlrGrammarGenUtil.<ParserRule>getOriginalElement(it))) {
      builder.append(".getText()");
    }
    builder.append("; }");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("EOF;");
    builder.newLine();
    builder.append(this.compileEntryFinally(it, options));
    builder.newLineIfNotEmpty();
    return builder.toString();
  }

  protected String compileEntryReturns(final ParserRule it, final AntlrOptions options) {
    if (GrammarUtil.isDatatypeRule(AntlrGrammarGenUtil.<ParserRule>getOriginalElement(it))) {
      return "[String current=null]";
    } else {
      final StringConcatenation builder = new StringConcatenation();
      builder.append('[');
      builder.append(this.getCurrentType());
      builder.append(" current=null]");
      return builder.toString();
    }
  }

  @Override
  protected String compileInit(final AbstractRule it, final AntlrOptions options) {
    final StringConcatenation builder = new StringConcatenation();
    if (it instanceof ParserRule) {
      builder.append(AntlrGrammarGenUtil.getParameterList((ParserRule) it, Boolean.valueOf(!this.isPassCurrentIntoFragment()), this.getCurrentType()));
    }
    builder.append(" returns ");
    builder.append(this.compileReturns(it, options));
    builder.newLineIfNotEmpty();
    if (this.annotations.hasNoBacktrackAnnotation(it)) {
      builder.append("// Enclosing rule was annotated with @NoBacktrack");
      builder.newLine();
      builder.append("options { backtrack=false; }");
      builder.newLine();
    }
    builder.append("@init {");
    builder.newLine();
    builder.append("  ");
    builder.append("enterRule();");
    builder.newLine();
    builder.append("  ");
    builder.append(this.compileInitHiddenTokens(it, options), "  ");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append(this.compileInitUnorderedGroups(it, options), "  ");
    builder.newLineIfNotEmpty();
    builder.append('}');
    builder.newLine();
    builder.append("@after {");
    builder.newLine();
    builder.append("  ");
    builder.append("leaveRule();");
    builder.newLine();
    builder.append('}');
    return builder.toString();
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
        final StringConcatenation builder = new StringConcatenation();
        builder.append('[');
        builder.append(this.getCurrentType());
        builder.append(" current=in_current]");
        return builder;
      }
      final StringConcatenation builder1 = new StringConcatenation();
      builder1.append('[');
      builder1.append(this.getCurrentType());
      builder1.append(" current=null]");
      return builder1;
    }
    throw new IllegalStateException("Unexpected rule: " + it);
  }

  @Override
  protected String _dataTypeEbnf2(final Keyword it, final boolean supportActions) {
    if (supportActions) {
      final StringConcatenation builder = new StringConcatenation();
      builder.append("kw=");
      builder.append(super._dataTypeEbnf2(it, supportActions));
      builder.newLineIfNotEmpty();
      builder.append('{');
      builder.newLine();
      builder.append("  ");
      builder.append("$current.merge(kw);");
      builder.newLine();
      builder.append("  ");
      builder.append(this.newLeafNode(it, "kw"), "  ");
      builder.newLineIfNotEmpty();
      builder.append('}');
      builder.newLine();
      return builder.toString();
    } else {
      return super._dataTypeEbnf2(it, supportActions);
    }
  }

  @Override
  protected String _ebnf2(final Action it, final AntlrOptions options, final boolean supportActions) {
    if (supportActions) {
      final StringConcatenation builder = new StringConcatenation();
      if (options.isBacktrack()) {
        builder.append('{');
        builder.newLine();
        builder.append("  ");
        builder.append("/* */");
        builder.newLine();
        builder.append('}');
        builder.newLine();
      }
      builder.append('{');
      builder.newLine();
      builder.append("  ");
      builder.append("$current = forceCreateModelElement");
      if (it.getFeature() != null) {
        builder.append("And");
        builder.append(StringExtensions.toFirstUpper(this._grammarAccessExtensions.setOrAdd(it)), "  ");
      }
      builder.append('(');
      builder.newLineIfNotEmpty();
      builder.append("    ");
      builder.append("grammarAccess.");
      builder.append(this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<Action>getOriginalElement(it)), "    ");
      builder.append(',');
      builder.newLineIfNotEmpty();
      builder.append("    ");
      builder.append("$current);");
      builder.newLine();
      builder.append('}');
      builder.newLine();
      return builder.toString();
    } else {
      return super._ebnf2(it, options, supportActions);
    }
  }

  @Override
  protected String _ebnf2(final Keyword it, final AntlrOptions options, final boolean supportActions) {
    if (!supportActions) {
      return super._ebnf2(it, options, supportActions);
    } else if (GrammarUtil.isAssigned(it)) {
      final StringConcatenation builder = new StringConcatenation();
      builder.append(super._ebnf2(it, options, supportActions));
      builder.newLineIfNotEmpty();
      builder.append('{');
      builder.newLine();
      builder.append("  ");
      builder.append(this.newLeafNode(it, this._grammarAccessExtensions.localVar(GrammarUtil.containingAssignment(it), it)), "  ");
      builder.newLineIfNotEmpty();
      builder.append('}');
      builder.newLine();
      return builder.toString();
    } else {
      final StringConcatenation builder1 = new StringConcatenation();
      builder1.append(this._grammarAccessExtensions.localVar(it));
      builder1.append('=');
      builder1.append(super._ebnf2(it, options, supportActions));
      builder1.newLineIfNotEmpty();
      builder1.append('{');
      builder1.newLine();
      builder1.append("  ");
      builder1.append(this.newLeafNode(it, this._grammarAccessExtensions.localVar(it)), "  ");
      builder1.newLineIfNotEmpty();
      builder1.append('}');
      builder1.newLine();
      return builder1.toString();
    }
  }

  @Override
  protected String _ebnf2(final EnumLiteralDeclaration it, final AntlrOptions options, final boolean supportActions) {
    if (!supportActions) {
      return super._ebnf2(it, options, supportActions);
    } else {
      final StringConcatenation builder = new StringConcatenation();
      builder.append(this._grammarAccessExtensions.localVar(it));
      builder.append('=');
      builder.append(super._ebnf2(it, options, supportActions));
      builder.newLineIfNotEmpty();
      builder.append('{');
      builder.newLine();
      builder.append("  ");
      builder.append("$current = grammarAccess.");
      builder.append(this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<EnumLiteralDeclaration>getOriginalElement(it)), "  ");
      builder.append(".getEnumLiteral().getInstance();");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append(this.newLeafNode(it, this._grammarAccessExtensions.localVar(it)), "  ");
      builder.newLineIfNotEmpty();
      builder.append('}');
      builder.newLine();
      return builder.toString();
    }
  }

  @Override
  protected String crossrefEbnf(final AbstractRule it, final RuleCall call, final CrossReference ref, final boolean supportActions) {
    if (supportActions) {
      if (it instanceof EnumRule || it instanceof ParserRule) {
        final StringConcatenation builder = new StringConcatenation();
        builder.append('{');
        builder.newLine();
        builder.append("  ");
        builder.append(this.newCompositeNode(ref), "  ");
        builder.newLineIfNotEmpty();
        builder.append('}');
        builder.newLine();
        builder.append(this._grammarAccessExtensions.ruleName(it));
        builder.append(AntlrGrammarGenUtil.getArgumentList(call, this.isPassCurrentIntoFragment(), (!supportActions)));
        builder.newLineIfNotEmpty();
        builder.append('{');
        builder.newLine();
        builder.append("  ");
        builder.append("afterParserOrEnumRuleCall();");
        builder.newLine();
        builder.append('}');
        builder.newLine();
        return builder.toString();
      } else if (it instanceof TerminalRule) {
        final StringConcatenation builder1 = new StringConcatenation();
        builder1.append(this._grammarAccessExtensions.localVar(GrammarUtil.containingAssignment(ref)));
        builder1.append('=');
        builder1.append(this._grammarAccessExtensions.ruleName(it));
        builder1.newLineIfNotEmpty();
        builder1.append('{');
        builder1.newLine();
        builder1.append("  ");
        builder1.append(this.newLeafNode(ref, this._grammarAccessExtensions.localVar(GrammarUtil.containingAssignment(ref))), "  ");
        builder1.newLineIfNotEmpty();
        builder1.append('}');
        builder1.newLine();
        return builder1.toString();
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
      final StringConcatenation builder = new StringConcatenation();
      builder.append(this._grammarAccessExtensions.localVar(assignment, it));
      builder.append('=');
      builder.append(super._assignmentEbnf(it, assignment, options, supportActions));
      builder.newLineIfNotEmpty();
      builder.append('{');
      builder.newLine();
      builder.append("  ");
      builder.append("if ($current==null) {");
      builder.newLine();
      builder.append("    ");
      builder.append("$current = ");
      builder.append(this.createModelElement(assignment), "    ");
      builder.append(';');
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append('}');
      builder.newLine();
      builder.append("  ");
      builder.append(this._grammarAccessExtensions.setOrAdd(assignment), "  ");
      builder.append("WithLastConsumed($current, \"");
      builder.append(assignment.getFeature(), "  ");
      builder.append("\", ");
      builder.append(this._grammarAccessExtensions.localVar(assignment, it), "  ");
      if (GrammarUtil.isBooleanAssignment(assignment)) {
        builder.append(" != null");
      }
      builder.append(", ");
      builder.append(this._grammarAccessExtensions.toStringLiteral(assignment.getTerminal()), "  ");
      builder.append(");");
      builder.newLineIfNotEmpty();
      builder.append('}');
      builder.newLine();
      return builder.toString();
    } else {
      return super._assignmentEbnf(it, assignment, options, supportActions);
    }
  }

  @Override
  protected boolean isPassCurrentIntoFragment() {
    return true;
  }

  protected CharSequence createModelElement(final EObject grammarElement) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append("createModelElement(grammarAccess.");
    builder.append(this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<ParserRule>getOriginalElement(GrammarUtil.containingParserRule(grammarElement))));
    builder.append(')');
    return builder;
  }

  protected CharSequence createModelElementForParent(final EObject grammarElement) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append("createModelElementForParent(grammarAccess.");
    builder.append(this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<ParserRule>getOriginalElement(GrammarUtil.containingParserRule(grammarElement))));
    builder.append(')');
    return builder;
  }

  protected CharSequence newCompositeNode(final EObject it) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append("newCompositeNode(grammarAccess.");
    builder.append(this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<EObject>getOriginalElement(it)));
    builder.append(");");
    return builder;
  }

  protected CharSequence newLeafNode(final EObject it, final String token) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append("newLeafNode(");
    builder.append(token);
    builder.append(", grammarAccess.");
    builder.append(this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<EObject>getOriginalElement(it)));
    builder.append(");");
    return builder;
  }

  /**
   * Add gated predicate, if necessary, before the action preceding the rule call.
   */
  @Override
  protected String _dataTypeEbnf2(final RuleCall it, final boolean supportActions) {
    if (supportActions) {
      final AbstractRule rule = it.getRule();
      if ((rule instanceof EnumRule && GrammarUtil.isAssigned(it)) || (rule instanceof ParserRule && GrammarUtil.isAssigned(it))) {
        return super._dataTypeEbnf2(it, supportActions);
      } else if (rule instanceof EnumRule || rule instanceof ParserRule) {
        final StringConcatenation builder = new StringConcatenation();
        if (this.annotations.isGatedPredicateRequired(it)) {
          builder.append(this.annotations.generateGatedPredicate(it));
        }
        builder.newLineIfNotEmpty();
        builder.append('{');
        builder.newLine();
        builder.append("  ");
        builder.append(this.newCompositeNode(it), "  ");
        builder.newLineIfNotEmpty();
        builder.append('}');
        builder.newLine();
        builder.append(this._grammarAccessExtensions.localVar(it));
        builder.append('=');
        builder.append(super._dataTypeEbnf2(it, supportActions));
        builder.append(AntlrGrammarGenUtil.getArgumentList(it, this.isPassCurrentIntoFragment(), (!supportActions)));
        builder.newLineIfNotEmpty();
        builder.append('{');
        builder.newLine();
        builder.append("  ");
        builder.append("$current.merge(");
        builder.append(this._grammarAccessExtensions.localVar(it), "  ");
        builder.append(");");
        builder.newLineIfNotEmpty();
        builder.append('}');
        builder.newLine();
        builder.append('{');
        builder.newLine();
        builder.append("  ");
        builder.append("afterParserOrEnumRuleCall();");
        builder.newLine();
        builder.append('}');
        builder.newLine();
        return builder.toString();
      } else if (rule instanceof TerminalRule) {
        final StringConcatenation builder1 = new StringConcatenation();
        builder1.append(this._grammarAccessExtensions.localVar(it));
        builder1.append('=');
        builder1.append(super._dataTypeEbnf2(it, supportActions));
        builder1.newLineIfNotEmpty();
        builder1.append('{');
        builder1.newLine();
        builder1.append("  ");
        builder1.append("$current.merge(");
        builder1.append(this._grammarAccessExtensions.localVar(it), "  ");
        builder1.append(");");
        builder1.newLineIfNotEmpty();
        builder1.append('}');
        builder1.newLine();
        builder1.append('{');
        builder1.newLine();
        builder1.append("  ");
        builder1.append(this.newLeafNode(it, this._grammarAccessExtensions.localVar(it)), "  ");
        builder1.newLineIfNotEmpty();
        builder1.append('}');
        builder1.newLine();
        return builder1.toString();
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
    final StringConcatenation builder = new StringConcatenation();
    builder.append("// Rule ");
    builder.append(AntlrGrammarGenUtil.<AbstractRule>getOriginalElement(it).getName());
    builder.newLineIfNotEmpty();
    builder.append(this._grammarAccessExtensions.ruleName(it));
    builder.append(this.compileInit(it, options));
    builder.append(':');
    builder.newLineIfNotEmpty();
    if ((it instanceof ParserRule) && GrammarUtil.isDatatypeRule(AntlrGrammarGenUtil.<AbstractRule>getOriginalElement(it))) {
      builder.append("  ");
      if (this.annotations.hasValidatingPredicate(it)) {
        builder.append(this.annotations.generateValidatingPredicate(it), "  ");
      }
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append(this.dataTypeEbnf(it.getAlternatives(), true), "  ");
      builder.newLineIfNotEmpty();
    } else {
      builder.append("  ");
      builder.append(this.ebnf(it.getAlternatives(), options, true), "  ");
      builder.newLineIfNotEmpty();
    }
    builder.append(';');
    builder.newLine();
    builder.append(this.compileFinally(it, options));
    builder.newLineIfNotEmpty();
    return builder.toString();
  }

  @Override
  protected String dataTypeEbnf(final AbstractElement it, final boolean supportActions) {
    final StringConcatenation builder = new StringConcatenation();
    if (this.mustBeParenthesized(it)) {
      builder.append('(');
      builder.newLineIfNotEmpty();
      if (this.annotations.hasNoBacktrackAnnotation(it)) {
        builder.append("  ");
        builder.append("// Enclosing rule was annotated with @NoBacktrack");
        builder.newLine();
        builder.append("  ");
        builder.append("options { backtrack=false; }:");
        builder.newLine();
      }
      builder.append("  ");
      builder.append(this.dataTypeEbnfPredicate(it), "  ");
      builder.append(this.dataTypeEbnf2(it, supportActions), "  ");
      builder.newLineIfNotEmpty();
      builder.append(')');
    } else {
      builder.append(this.dataTypeEbnf2(it, supportActions));
    }
    builder.append(it.getCardinality());
    builder.newLineIfNotEmpty();
    return builder.toString();
  }

  @Override
  protected String ebnf(final AbstractElement it, final AntlrOptions options, final boolean supportActions) {
    final StringConcatenation builder = new StringConcatenation();
    if (this.mustBeParenthesized(it)) {
      builder.append('(');
      builder.newLineIfNotEmpty();
      if (this.annotations.hasNoBacktrackAnnotation(it)) {
        builder.append("  ");
        builder.append("// Enclosing rule was annotated with @NoBacktrack");
        builder.newLine();
        builder.append("  ");
        builder.append("options { backtrack=false; }:");
        builder.newLine();
      }
      builder.append("  ");
      builder.append(this.ebnfPredicate(it, options), "  ");
      builder.append(this.ebnf2(it, options, supportActions), "  ");
      builder.newLineIfNotEmpty();
      builder.append(')');
    } else {
      builder.append(this.ebnf2(it, options, supportActions));
    }
    builder.append(it.getCardinality());
    builder.newLineIfNotEmpty();
    return builder.toString();
  }

  /**
   * Add gated predicate, if necessary, before the action preceding the assignment.
   */
  @Override
  protected String _assignmentEbnf(final RuleCall it, final Assignment assignment, final AntlrOptions options, final boolean supportActions) {
    if (supportActions) {
      final AbstractRule rule = it.getRule();
      if (rule instanceof EnumRule || rule instanceof ParserRule) {
        final StringConcatenation builder = new StringConcatenation();
        if (this.annotations.isGatedPredicateRequired(it)) {
          builder.append(this.annotations.generateGatedPredicate(it));
        }
        builder.newLineIfNotEmpty();
        builder.append('{');
        builder.newLine();
        builder.append("  ");
        builder.append(this.newCompositeNode(it), "  ");
        builder.newLineIfNotEmpty();
        builder.append('}');
        builder.newLine();
        builder.append(this._grammarAccessExtensions.localVar(assignment, it));
        builder.append('=');
        builder.append(super._assignmentEbnf(it, assignment, options, supportActions));
        builder.newLineIfNotEmpty();
        builder.append('{');
        builder.newLine();
        builder.append("  ");
        builder.append("if ($current==null) {");
        builder.newLine();
        builder.append("    ");
        builder.append("$current = ");
        builder.append(this.createModelElementForParent(assignment), "    ");
        builder.append(';');
        builder.newLineIfNotEmpty();
        builder.append("  ");
        builder.append('}');
        builder.newLine();
        builder.append("  ");
        builder.append(this._grammarAccessExtensions.setOrAdd(assignment), "  ");
        builder.append('(');
        builder.newLineIfNotEmpty();
        builder.append("    ");
        builder.append("$current,");
        builder.newLine();
        builder.append("    ");
        builder.append("\"");
        builder.append(assignment.getFeature(), "    ");
        builder.append("\",");
        builder.newLineIfNotEmpty();
        builder.append("    ");
        builder.append(this._grammarAccessExtensions.localVar(assignment, it), "    ");
        if (GrammarUtil.isBooleanAssignment(assignment)) {
          builder.append(" != null");
        }
        builder.append(',');
        builder.newLineIfNotEmpty();
        builder.append("    ");
        builder.append(this._grammarAccessExtensions.toStringLiteral(it), "    ");
        builder.append(");");
        builder.newLineIfNotEmpty();
        builder.append("  ");
        builder.append("afterParserOrEnumRuleCall();");
        builder.newLine();
        builder.append('}');
        builder.newLine();
        return builder.toString();
      } else if (rule instanceof TerminalRule) {
        final StringConcatenation builder1 = new StringConcatenation();
        builder1.append(this._grammarAccessExtensions.localVar(assignment, it));
        builder1.append('=');
        builder1.append(super._assignmentEbnf(it, assignment, options, supportActions));
        builder1.newLineIfNotEmpty();
        builder1.append('{');
        builder1.newLine();
        builder1.append("  ");
        builder1.append(this.newLeafNode(it, this._grammarAccessExtensions.localVar(assignment, it)), "  ");
        builder1.newLineIfNotEmpty();
        builder1.append('}');
        builder1.newLine();
        builder1.append('{');
        builder1.newLine();
        builder1.append("  ");
        builder1.append("if ($current==null) {");
        builder1.newLine();
        builder1.append("    ");
        builder1.append("$current = ");
        builder1.append(this.createModelElement(assignment), "    ");
        builder1.append(';');
        builder1.newLineIfNotEmpty();
        builder1.append("  ");
        builder1.append('}');
        builder1.newLine();
        builder1.append("  ");
        builder1.append(this._grammarAccessExtensions.setOrAdd(assignment), "  ");
        builder1.append("WithLastConsumed(");
        builder1.newLineIfNotEmpty();
        builder1.append("    ");
        builder1.append("$current,");
        builder1.newLine();
        builder1.append("    ");
        builder1.append("\"");
        builder1.append(assignment.getFeature(), "    ");
        builder1.append("\",");
        builder1.newLineIfNotEmpty();
        builder1.append("    ");
        builder1.append(this._grammarAccessExtensions.localVar(assignment, it), "    ");
        if (GrammarUtil.isBooleanAssignment(assignment)) {
          builder1.append(" != null");
        }
        builder1.append(',');
        builder1.newLineIfNotEmpty();
        builder1.append("    ");
        builder1.append(this._grammarAccessExtensions.toStringLiteral(it), "    ");
        builder1.append(");");
        builder1.newLineIfNotEmpty();
        builder1.append('}');
        builder1.newLine();
        return builder1.toString();
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
      final StringConcatenation builder = new StringConcatenation();
      if (this.annotations.isGatedPredicateRequired(it)) {
        builder.append(this.annotations.generateGatedPredicate(it));
      }
      builder.newLineIfNotEmpty();
      if (options.isBacktrack()) {
        builder.append('{');
        builder.newLine();
        builder.append("  ");
        builder.append("/* */");
        builder.newLine();
        builder.append('}');
        builder.newLine();
      }
      builder.append('{');
      builder.newLine();
      builder.append("  ");
      builder.append("if ($current==null) {");
      builder.newLine();
      builder.append("    ");
      builder.append("$current = ");
      builder.append(this.createModelElement(assignment), "    ");
      builder.append(';');
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append('}');
      builder.newLine();
      builder.append('}');
      builder.newLine();
      builder.append(super._assignmentEbnf(it, assignment, options, supportActions));
      return builder.toString();
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
      final AbstractRule rule = it.getRule();
      if ((rule instanceof EnumRule && GrammarUtil.isAssigned(it)) || (rule instanceof ParserRule && GrammarUtil.isAssigned(it))) {
        return super._ebnf2(it, options, supportActions);
      } else if (rule instanceof EnumRule || (rule instanceof ParserRule && GrammarUtil.isDatatypeRule(AntlrGrammarGenUtil.<ParserRule>getOriginalElement((ParserRule) rule)))) {
        final StringConcatenation builder = new StringConcatenation();
        if (this.annotations.isGatedPredicateRequired(it)) {
          builder.append(this.annotations.generateGatedPredicate(it));
        }
        builder.newLineIfNotEmpty();
        if (options.isBacktrack()) {
          builder.append('{');
          builder.newLine();
          builder.append("  ");
          builder.append("/* */");
          builder.newLine();
          builder.append('}');
          builder.newLine();
        }
        builder.append('{');
        builder.newLine();
        builder.append("  ");
        builder.append(this.newCompositeNode(it), "  ");
        builder.newLineIfNotEmpty();
        builder.append('}');
        builder.newLine();
        builder.append(super._ebnf2(it, options, supportActions));
        builder.newLineIfNotEmpty();
        builder.append('{');
        builder.newLine();
        builder.append("  ");
        builder.append("afterParserOrEnumRuleCall();");
        builder.newLine();
        builder.append('}');
        builder.newLine();
        return builder.toString();
      } else if (rule instanceof ParserRule) {
        final StringConcatenation builder1 = new StringConcatenation();
        if (this.annotations.isGatedPredicateRequired(it)) {
          builder1.append(this.annotations.generateGatedPredicate(it));
        }
        builder1.newLineIfNotEmpty();
        if (options.isBacktrack()) {
          builder1.append('{');
          builder1.newLine();
          builder1.append("  ");
          builder1.append("/* */");
          builder1.newLine();
          builder1.append('}');
          builder1.newLine();
        }
        builder1.append('{');
        builder1.newLine();
        if (GrammarUtil.isEObjectFragmentRuleCall(it)) {
          builder1.append("  ");
          builder1.append("if ($current==null) {");
          builder1.newLine();
          builder1.append("    ");
          builder1.append("$current = ");
          builder1.append(this.createModelElement(it), "    ");
          builder1.append(';');
          builder1.newLineIfNotEmpty();
          builder1.append("  ");
          builder1.append('}');
          builder1.newLine();
        }
        builder1.append("  ");
        builder1.append(this.newCompositeNode(it), "  ");
        builder1.newLineIfNotEmpty();
        builder1.append('}');
        builder1.newLine();
        final String localVar = this._grammarAccessExtensions.localVar(it);
        builder1.append(localVar);
        builder1.append('=');
        builder1.append(super._ebnf2(it, options, supportActions));
        builder1.newLineIfNotEmpty();
        builder1.append('{');
        builder1.newLine();
        builder1.append("  ");
        builder1.append("$current = $");
        builder1.append(localVar, "  ");
        builder1.append(".current;");
        builder1.newLineIfNotEmpty();
        builder1.append("  ");
        builder1.append("afterParserOrEnumRuleCall();");
        builder1.newLine();
        builder1.append('}');
        builder1.newLine();
        return builder1.toString();
      } else if (rule instanceof TerminalRule) {
        final StringConcatenation builder2 = new StringConcatenation();
        final String localVar = this._grammarAccessExtensions.localVar(it);
        builder2.append(localVar);
        builder2.append('=');
        builder2.append(super._ebnf2(it, options, supportActions));
        builder2.newLineIfNotEmpty();
        builder2.append('{');
        builder2.newLine();
        builder2.append("  ");
        builder2.append(this.newLeafNode(it, localVar), "  ");
        builder2.newLineIfNotEmpty();
        builder2.append('}');
        builder2.newLine();
        return builder2.toString();
      } else {
        return super._ebnf2(it, options, supportActions);
      }
    }
  }

  @Override
  protected String compileLexerImports(final Grammar it, final AntlrOptions options) {
    final StringConcatenation builder = new StringConcatenation();
    builder.newLine();
    builder.append("// Hack: Use our own Lexer superclass by means of import.");
    builder.newLine();
    builder.append("// Currently there is no other way to specify the superclass for the lexer.");
    builder.newLine();
    if (!this.lexerSuperClassName.isEmpty()) {
      builder.append("import ");
      builder.append(this.lexerSuperClassName);
      builder.append(';');
      builder.newLineIfNotEmpty();
    } else {
      builder.append("import ");
      builder.append(this.getGrammarNaming().getLexerSuperClass(it));
      builder.append(';');
      builder.newLineIfNotEmpty();
    }
    return builder.toString();
  }
}
// CHECKSTYLE:CONSTANTS-ON
