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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.UnorderedGroup;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xtext.generator.parser.antlr.AntlrGrammarGenUtil;
import org.eclipse.xtext.xtext.generator.parser.antlr.AntlrOptions;
import org.eclipse.xtext.xtext.generator.parser.antlr.ContentAssistGrammarNaming;
import org.eclipse.xtext.xtext.generator.parser.antlr.GrammarNaming;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;

// CHECKSTYLE:CONSTANTS-OFF

/**
 * This implementation is strongly based on AntlrContentAssistGrammarGenerator but with a different base class.
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
@SuppressWarnings({"checkstyle:MethodName", "PMD.UnusedFormalParameter"})
public class AnnotationAwareAntlrContentAssistGrammarGenerator extends AbstractAnnotationAwareAntlrGrammarGenerator {

  @Inject
  private ContentAssistGrammarNaming naming;

  @Override
  protected GrammarNaming getGrammarNaming() {
    return this.naming;
  }

  @Override
  protected boolean isParserBackTracking(final Grammar it, final AntlrOptions options) {
    return super.isParserBackTracking(it, options) || !GrammarUtil.getAllPredicatedElements(it).isEmpty();
  }

  @Override
  protected String compileParserImports(final Grammar it, final AntlrOptions options) {
    final StringConcatenation builder = new StringConcatenation();
    if (!this.isCombinedGrammar()) {
      builder.append("import java.util.Map;");
      builder.newLine();
      builder.append("import java.util.HashMap;");
      builder.newLine();
    }
    builder.newLine();
    builder.append("import java.io.InputStream;");
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
    builder.append("import org.eclipse.xtext.parser.antlr.XtextTokenStream;");
    builder.newLine();
    builder.append("import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;");
    builder.newLine();
    builder.append("import ");
    builder.append(this.getGrammarNaming().getInternalParserSuperClass(it).getName());
    builder.append(';');
    builder.newLineIfNotEmpty();
    builder.append("import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;");
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
    builder.append('@');
    if (this.isCombinedGrammar()) {
      builder.append("parser::");
    }
    builder.append("members {");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append(this.compileParserMemberDeclarations(it, "protected"), "  ");
    builder.newLineIfNotEmpty();
    if (!this.isCombinedGrammar()) {
      builder.append("  ");
      builder.append("private final Map<String, String> tokenNameToValue = new HashMap<String, String>();");
      builder.newLine();
      builder.newLine();
      builder.append("  ");
      builder.append('{');
      builder.newLine();
      for (final String kw : IterableExtensions.sortBy(IterableExtensions.sort(GrammarUtil.getAllKeywords(it)), (String it1) -> Integer.valueOf(it1.length()))) {
        builder.append("  ");
        builder.append("  ");
        builder.append("tokenNameToValue.put(\"");
        builder.append(this.keywordHelper.getRuleName(kw), "    ");
        builder.append("\", \"\'");
        builder.append(AntlrGrammarGenUtil.toStringInAntlrAction(kw).replace("$", "\\u0024"), "    ");
        builder.append("\'\");");
        builder.newLineIfNotEmpty();
      }
      builder.append("  ");
      builder.append('}');
      builder.newLine();
    }
    builder.newLine();
    builder.append("  ");
    builder.append(this.compileParserSetTokenStreamMethod(), "  ");
    builder.newLineIfNotEmpty();
    builder.newLine();
    builder.append("  ");
    builder.append("public void setPredicates(");
    builder.append(this.predicatesNaming.getSemanticPredicatesSimpleName(it), "  ");
    builder.append(" predicates) {");
    builder.newLineIfNotEmpty();
    builder.append("    ");
    builder.append("this.predicates = predicates;");
    builder.newLine();
    builder.append("  ");
    builder.append('}');
    builder.newLine();
    builder.newLine();
    builder.append("  ");
    builder.append("public void setGrammarAccess(");
    builder.append(this._grammarAccessExtensions.getGrammarAccess(it).getSimpleName(), "  ");
    builder.append(" grammarAccess) {");
    builder.newLineIfNotEmpty();
    builder.append("    ");
    builder.append("this.grammarAccess = grammarAccess;");
    builder.newLine();
    builder.append("  ");
    builder.append('}');
    builder.newLine();
    builder.newLine();
    builder.append("public void setParserContext(ParserContext parserContext) {");
    builder.newLine();
    builder.append("  ");
    builder.append("this.parserContext = parserContext;");
    builder.newLine();
    builder.append('}');
    builder.newLine();
    builder.newLine();
    builder.append("  ");
    builder.append("@Override");
    builder.newLine();
    builder.append("  ");
    builder.append("protected Grammar getGrammar() {");
    builder.newLine();
    builder.append("    ");
    builder.append("return grammarAccess.getGrammar();");
    builder.newLine();
    builder.append("  ");
    builder.append('}');
    builder.newLine();
    builder.newLine();
    builder.append("  ");
    builder.append("@Override");
    builder.newLine();
    builder.append("  ");
    builder.append("protected String getValueForTokenName(String tokenName) {");
    builder.newLine();
    if (this.isCombinedGrammar()) {
      builder.append("    ");
      builder.append("return tokenName;");
      builder.newLine();
    } else {
      builder.append("    ");
      builder.append("String result = tokenNameToValue.get(tokenName);");
      builder.newLine();
      builder.append("    ");
      builder.append("if (result == null)");
      builder.newLine();
      builder.append("    ");
      builder.append("  ");
      builder.append("result = tokenName;");
      builder.newLine();
      builder.append("    ");
      builder.append("return result;");
      builder.newLine();
    }
    builder.append("  ");
    builder.append('}');
    builder.newLine();
    builder.append('}');
    builder.newLine();
    return builder.toString();
  }

  @Override
  protected CharSequence compileRules(final Grammar g, final AntlrOptions options) {
    final StringConcatenation builder = new StringConcatenation();
    final Iterable<EObject> allRulesAndElements = IterableExtensions.filter(
        Iterables.concat(
            Iterables.concat(
                Iterables.concat(
                    Iterables.concat(
                        Iterables.<AbstractRule>concat(GrammarUtil.allParserRules(g), GrammarUtil.allEnumRules(g)),
                        GrammarUtil.getAllAlternatives(g)),
                    GrammarUtil.getAllGroups(g)),
                GrammarUtil.getAllUnorderedGroups(g)),
            GrammarUtil.getAllAssignments(g)),
        (EObject it) -> this._grammarAccessExtensions.isCalled(GrammarUtil.containingRule(it), g));
    for (final EObject rule : allRulesAndElements) {
      builder.newLine();
      builder.append(this.compileRule(rule, g, options));
      builder.newLineIfNotEmpty();
    }
    if (this.isCombinedGrammar()) {
      builder.append(this.compileTerminalRules(g, options));
      builder.newLineIfNotEmpty();
    }
    return builder;
  }

  @Override
  protected CharSequence _compileRule(final ParserRule it, final Grammar grammar, final AntlrOptions options) {
    final StringConcatenation builder = new StringConcatenation();
    if (AntlrGrammarGenUtil.isValidEntryRule(it)) {
      builder.append("// Entry rule ");
      builder.append(this._grammarAccessExtensions.entryRuleName(it));
      builder.newLineIfNotEmpty();
      builder.append(this._grammarAccessExtensions.entryRuleName(it));
      builder.newLineIfNotEmpty();
      if (it.isDefinesHiddenTokens()) {
        builder.append("@init {");
        builder.newLine();
        builder.append("  ");
        builder.append(this.compileInitHiddenTokens(it, options), "  ");
        builder.newLineIfNotEmpty();
        builder.append('}');
        builder.newLine();
      }
      builder.append(':');
      builder.newLine();
      builder.append("{ before(grammarAccess.");
      builder.append(this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<ParserRule>getOriginalElement(it)));
      builder.append("); }");
      builder.newLineIfNotEmpty();
      builder.append("   ");
      builder.append(this._grammarAccessExtensions.ruleName(it), "   ");
      builder.newLineIfNotEmpty();
      builder.append("{ after(grammarAccess.");
      builder.append(this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<ParserRule>getOriginalElement(it)));
      builder.append("); }");
      builder.newLineIfNotEmpty();
      builder.append("   ");
      builder.append("EOF");
      builder.newLine();
      builder.append(';');
      builder.newLine();
      if (it.isDefinesHiddenTokens()) {
        builder.append("finally {");
        builder.newLine();
        builder.append("  ");
        builder.append(this.compileRestoreHiddenTokens(it, options), "  ");
        builder.newLineIfNotEmpty();
        builder.append('}');
        builder.newLine();
      }
    }
    builder.newLine();
    builder.append("// Rule ");
    builder.append(AntlrGrammarGenUtil.<ParserRule>getOriginalElement(it).getName());
    builder.newLineIfNotEmpty();
    builder.append(this._grammarAccessExtensions.ruleName(it));
    builder.newLineIfNotEmpty();
    if (this.annotations.hasNoBacktrackAnnotation(it)) {
      builder.append("  ");
      builder.append("// Enclosing rule was annotated with @NoBacktrack");
      builder.newLine();
      builder.append("  ");
      builder.append("options { backtrack=false; }");
      builder.newLine();
    }
    builder.append("  ");
    builder.append("@init {");
    builder.newLine();
    builder.append("    ");
    builder.append(this.compileInitHiddenTokens(it, options), "    ");
    builder.newLineIfNotEmpty();
    builder.append("    ");
    builder.append("int stackSize = keepStackSize();");
    builder.newLine();
    builder.append("  ");
    builder.append('}');
    builder.newLine();
    builder.append("  ");
    builder.append(':');
    builder.newLine();
    builder.append("  ");
    if (this.annotations.hasValidatingPredicate(it)) {
      builder.append(this.annotations.generateValidatingPredicate(it), "  ");
    }
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append(this.ebnf(it.getAlternatives(), options, false), "  ");
    builder.newLineIfNotEmpty();
    builder.append(';');
    builder.newLine();
    builder.append("finally {");
    builder.newLine();
    builder.append("  ");
    builder.append("restoreStackSize(stackSize);");
    builder.newLine();
    builder.append("  ");
    builder.append(this.compileRestoreHiddenTokens(it, options), "  ");
    builder.newLineIfNotEmpty();
    builder.append('}');
    builder.newLine();
    return builder;
  }

  @Override
  protected CharSequence _compileRule(final EnumRule it, final Grammar grammar, final AntlrOptions options) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append("// Rule ");
    builder.append(AntlrGrammarGenUtil.<EnumRule>getOriginalElement(it).getName());
    builder.newLineIfNotEmpty();
    builder.append(this._grammarAccessExtensions.ruleName(it));
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("@init {");
    builder.newLine();
    builder.append("    ");
    builder.append("int stackSize = keepStackSize();");
    builder.newLine();
    builder.append("  ");
    builder.append('}');
    builder.newLine();
    builder.append(':');
    builder.newLine();
    builder.append("  ");
    builder.append(this.ebnf(it.getAlternatives(), options, false), "  ");
    builder.newLineIfNotEmpty();
    builder.append(';');
    builder.newLine();
    builder.append("finally {");
    builder.newLine();
    builder.append("  ");
    builder.append("restoreStackSize(stackSize);");
    builder.newLine();
    builder.append('}');
    builder.newLine();
    return builder;
  }

  protected CharSequence _compileRule(final Alternatives it, final Grammar grammar, final AntlrOptions options) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)));
    builder.append("__");
    builder.append(this._grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.<Alternatives>getOriginalElement(it)));
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("@init {");
    builder.newLine();
    builder.append("    ");
    builder.append("int stackSize = keepStackSize();");
    builder.newLine();
    builder.append("  ");
    builder.append('}');
    builder.newLine();
    builder.append(':');
    builder.newLine();
    builder.append("  ");
    boolean hasElements = false;
    for (final AbstractElement element : it.getElements()) {
      if (!hasElements) {
        hasElements = true;
      } else {
        builder.appendImmediate("\n|", "  ");
      }
      builder.append(this.ebnf(element, options, false), "  ");
    }
    builder.newLineIfNotEmpty();
    builder.append(';');
    builder.newLine();
    builder.append("finally {");
    builder.newLine();
    builder.append("  ");
    builder.append("restoreStackSize(stackSize);");
    builder.newLine();
    builder.append('}');
    builder.newLine();
    return builder;
  }

  protected CharSequence _compileRule(final Assignment it, final Grammar grammar, final AntlrOptions options) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)));
    builder.append("__");
    builder.append(this._grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.<Assignment>getOriginalElement(it)));
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("@init {");
    builder.newLine();
    builder.append("    ");
    builder.append("int stackSize = keepStackSize();");
    builder.newLine();
    builder.append("  ");
    builder.append('}');
    builder.newLine();
    builder.append(':');
    builder.newLine();
    builder.append("  ");
    builder.append(this.assignmentEbnf(it.getTerminal(), it, options, false), "  ");
    builder.newLineIfNotEmpty();
    builder.append(';');
    builder.newLine();
    builder.append("finally {");
    builder.newLine();
    builder.append("  ");
    builder.append("restoreStackSize(stackSize);");
    builder.newLine();
    builder.append('}');
    builder.newLine();
    return builder;
  }

  protected CharSequence _compileRule(final UnorderedGroup it, final Grammar grammar, final AntlrOptions options) {
    final boolean hasMandatoryContent = IterableExtensions.exists(it.getElements(), (AbstractElement it1) -> !GrammarUtil.isOptionalCardinality(it1));
    final StringConcatenation builder = new StringConcatenation();
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)));
    builder.append("__");
    builder.append(this._grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it)));
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("@init {");
    builder.newLine();
    builder.append("    ");
    builder.append("int stackSize = keepStackSize();");
    builder.newLine();
    builder.append("    ");
    builder.append("getUnorderedGroupHelper().enter(grammarAccess.");
    builder.append(this._grammarAccessExtensions.gaRuleElementAccessor(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it)), "    ");
    builder.append(");");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append('}');
    builder.newLine();
    builder.append(':');
    builder.newLine();
    builder.append("  ");
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)), "  ");
    builder.append("__");
    builder.append(this._grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it)), "  ");
    builder.append("__0");
    builder.newLineIfNotEmpty();
    if (hasMandatoryContent) {
      builder.append("  ");
      builder.append("{getUnorderedGroupHelper().canLeave(grammarAccess.");
      builder.append(this._grammarAccessExtensions.gaRuleElementAccessor(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it)), "  ");
      builder.append(")}?");
      builder.newLineIfNotEmpty();
    } else {
      builder.append("  ");
      builder.append('?');
      builder.newLine();
    }
    builder.append(';');
    builder.newLine();
    builder.append("finally {");
    builder.newLine();
    builder.append("  ");
    builder.append("getUnorderedGroupHelper().leave(grammarAccess.");
    builder.append(this._grammarAccessExtensions.gaRuleElementAccessor(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it)), "  ");
    builder.append(");");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("restoreStackSize(stackSize);");
    builder.newLine();
    builder.append('}');
    builder.newLine();
    builder.newLine();
    builder.append(this.ruleImpl(it, grammar, options));
    builder.newLineIfNotEmpty();
    builder.newLine();
    builder.append(this.ruleImpl(it, grammar, options, 0));
    builder.newLineIfNotEmpty();
    return builder;
  }

  protected CharSequence _compileRule(final Group it, final Grammar grammar, final AntlrOptions options) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append(this.ruleImpl(it, grammar, options, 0));
    builder.newLineIfNotEmpty();
    return builder;
  }

  /**
   * Dispatch method for compileRule.
   */
  @Override
  protected CharSequence compileRule(final Object it, final Grammar grammar, final AntlrOptions options) {
    if (it instanceof ParserRule) {
      return _compileRule((ParserRule) it, grammar, options);
    } else if (it instanceof EnumRule) {
      return _compileRule((EnumRule) it, grammar, options);
    } else if (it instanceof Alternatives) {
      return _compileRule((Alternatives) it, grammar, options);
    } else if (it instanceof Assignment) {
      return _compileRule((Assignment) it, grammar, options);
    } else if (it instanceof UnorderedGroup) {
      return _compileRule((UnorderedGroup) it, grammar, options);
    } else if (it instanceof Group) {
      return _compileRule((Group) it, grammar, options);
    } else {
      return super.compileRule(it, grammar, options);
    }
  }

  protected CharSequence ruleImpl(final UnorderedGroup it, final Grammar grammar, final AntlrOptions options) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)));
    builder.append("__");
    builder.append(this._grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it)));
    builder.append("__Impl");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("@init {");
    builder.newLine();
    builder.append("    ");
    builder.append("int stackSize = keepStackSize();");
    builder.newLine();
    builder.append("    ");
    builder.append("boolean selected = false;");
    builder.newLine();
    builder.append("  ");
    builder.append('}');
    builder.newLine();
    builder.append(':');
    builder.newLine();
    builder.append("    ");
    builder.append('(');
    builder.newLine();
    boolean hasElements = false;
    for (final Pair<Integer, AbstractElement> element : IterableExtensions.indexed(it.getElements())) {
      if (!hasElements) {
        hasElements = true;
      } else {
        builder.appendImmediate("|", "    ");
      }
      final String originalAccessor = this._grammarAccessExtensions.gaRuleElementAccessor(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it));
      final String originalElementAccess = this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<AbstractElement>getOriginalElement(element.getValue()));
      builder.append("    ");
      builder.append('(');
      builder.newLine();
      builder.append("    ");
      builder.append("  ");
      builder.append("{getUnorderedGroupHelper().canSelect(grammarAccess.");
      builder.append(originalAccessor, "      ");
      builder.append(", ");
      builder.append(element.getKey(), "      ");
      builder.append(")}?=>(");
      builder.newLineIfNotEmpty();
      builder.append("    ");
      builder.append("    ");
      builder.append('{');
      builder.newLine();
      builder.append("    ");
      builder.append("      ");
      builder.append("getUnorderedGroupHelper().select(grammarAccess.");
      builder.append(originalAccessor, "          ");
      builder.append(", ");
      builder.append(element.getKey(), "          ");
      builder.append(");");
      builder.newLineIfNotEmpty();
      builder.append("    ");
      builder.append("    ");
      builder.append('}');
      builder.newLine();
      builder.append("    ");
      builder.append("    ");
      builder.append('{');
      builder.newLine();
      builder.append("    ");
      builder.append("      ");
      builder.append("selected = true;");
      builder.newLine();
      builder.append("    ");
      builder.append("    ");
      builder.append('}');
      builder.newLine();
      builder.append("    ");
      builder.append("    ");
      builder.append('(');
      builder.newLine();
      if (GrammarUtil.isMultipleCardinality(element.getValue())) {
        builder.append("    ");
        builder.append("      ");
        builder.append('(');
        builder.newLine();
        builder.append("    ");
        builder.append("      ");
        builder.append("  ");
        builder.append("{ before(grammarAccess.");
        builder.append(originalElementAccess, "            ");
        builder.append("); }");
        builder.newLineIfNotEmpty();
        builder.append("    ");
        builder.append("      ");
        builder.append("  ");
        builder.append('(');
        builder.append(this.ebnf2(element.getValue(), options, false), "            ");
        builder.append(')');
        builder.newLineIfNotEmpty();
        builder.append("    ");
        builder.append("      ");
        builder.append("  ");
        builder.append("{ after(grammarAccess.");
        builder.append(originalElementAccess, "            ");
        builder.append("); }");
        builder.newLineIfNotEmpty();
        builder.append("    ");
        builder.append("      ");
        builder.append(')');
        builder.newLine();
        builder.append("    ");
        builder.append("      ");
        builder.append('(');
        builder.newLine();
        builder.append("    ");
        builder.append("      ");
        builder.append("  ");
        builder.append("{ before(grammarAccess.");
        builder.append(originalElementAccess, "            ");
        builder.append("); }");
        builder.newLineIfNotEmpty();
        builder.append("    ");
        builder.append("      ");
        builder.append("  ");
        builder.append("((");
        builder.append(this.ebnf2(element.getValue(), options, false), "            ");
        builder.append(")=>");
        builder.append(this.ebnf2(element.getValue(), options, false), "            ");
        builder.append(")*");
        builder.newLineIfNotEmpty();
        builder.append("    ");
        builder.append("      ");
        builder.append("  ");
        builder.append("{ after(grammarAccess.");
        builder.append(originalElementAccess, "            ");
        builder.append("); }");
        builder.newLineIfNotEmpty();
        builder.append("    ");
        builder.append("      ");
        builder.append(')');
        builder.newLine();
      } else {
        builder.append("    ");
        builder.append("      ");
        builder.append("{ before(grammarAccess.");
        builder.append(originalElementAccess, "          ");
        builder.append("); }");
        builder.newLineIfNotEmpty();
        builder.append("    ");
        builder.append("      ");
        builder.append('(');
        builder.append(this.ebnf2(element.getValue(), options, false), "          ");
        builder.append(')');
        builder.newLineIfNotEmpty();
        builder.append("    ");
        builder.append("      ");
        builder.append("{ after(grammarAccess.");
        builder.append(originalElementAccess, "          ");
        builder.append("); }");
        builder.newLineIfNotEmpty();
      }
      builder.append("    ");
      builder.append("    ");
      builder.append(')');
      builder.newLine();
      builder.append("    ");
      builder.append("  ");
      builder.append(')');
      builder.newLine();
      builder.append("    ");
      builder.append(')');
      builder.newLine();
    }
    builder.append("    ");
    builder.append(')');
    builder.newLine();
    builder.append(';');
    builder.newLine();
    builder.append("finally {");
    builder.newLine();
    builder.append("  ");
    builder.append("if (selected)");
    builder.newLine();
    builder.append("    ");
    builder.append("getUnorderedGroupHelper().returnFromSelection(grammarAccess.");
    builder.append(this._grammarAccessExtensions.gaRuleElementAccessor(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it)), "    ");
    builder.append(");");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("restoreStackSize(stackSize);");
    builder.newLine();
    builder.append('}');
    builder.newLine();
    return builder;
  }

  protected CharSequence ruleImpl(final UnorderedGroup it, final Grammar grammar, final AntlrOptions options, final int index) {
    final StringConcatenation builder = new StringConcatenation();
    final String ruleName = AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it));
    final String elementId = this._grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it));
    builder.append(ruleName);
    builder.append("__");
    builder.append(elementId);
    builder.append("__");
    builder.append(index);
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("@init {");
    builder.newLine();
    builder.append("    ");
    builder.append("int stackSize = keepStackSize();");
    builder.newLine();
    builder.append("  ");
    builder.append('}');
    builder.newLine();
    builder.append(':');
    builder.newLine();
    builder.append("  ");
    builder.append(ruleName, "  ");
    builder.append("__");
    builder.append(elementId, "  ");
    builder.append("__Impl");
    builder.newLineIfNotEmpty();
    if (it.getElements().size() > (index + 1)) {
      builder.append("  ");
      builder.append(ruleName, "  ");
      builder.append("__");
      builder.append(elementId, "  ");
      builder.append("__");
      builder.append((index + 1), "  ");
      builder.append('?');
      builder.newLineIfNotEmpty();
    }
    builder.append(';');
    builder.newLine();
    builder.append("finally {");
    builder.newLine();
    builder.append("  ");
    builder.append("restoreStackSize(stackSize);");
    builder.newLine();
    builder.append('}');
    builder.newLine();
    builder.newLine();
    if (it.getElements().size() > (index + 1)) {
      builder.append(this.ruleImpl(it, grammar, options, (index + 1)));
      builder.newLineIfNotEmpty();
    }
    return builder;
  }

  protected CharSequence ruleImpl(final Group it, final Grammar grammar, final AntlrOptions options, final int index) {
    final StringConcatenation builder = new StringConcatenation();
    final String ruleName = AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it));
    final String elementId = this._grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.<Group>getOriginalElement(it));
    builder.append(ruleName);
    builder.append("__");
    builder.append(elementId);
    builder.append("__");
    builder.append(index);
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("@init {");
    builder.newLine();
    builder.append("    ");
    builder.append("int stackSize = keepStackSize();");
    builder.newLine();
    builder.append("  ");
    builder.append('}');
    builder.newLine();
    builder.append(':');
    builder.newLine();
    builder.append("  ");
    builder.append(ruleName, "  ");
    builder.append("__");
    builder.append(elementId, "  ");
    builder.append("__");
    builder.append(index, "  ");
    builder.append("__Impl");
    builder.newLineIfNotEmpty();
    if (it.getElements().size() > (index + 1)) {
      builder.append("  ");
      builder.append(ruleName, "  ");
      builder.append("__");
      builder.append(elementId, "  ");
      builder.append("__");
      builder.append((index + 1), "  ");
      builder.newLineIfNotEmpty();
    }
    builder.append(';');
    builder.newLine();
    builder.append("finally {");
    builder.newLine();
    builder.append("  ");
    builder.append("restoreStackSize(stackSize);");
    builder.newLine();
    builder.append('}');
    builder.newLine();
    builder.newLine();
    builder.append(ruleName);
    builder.append("__");
    builder.append(elementId);
    builder.append("__");
    builder.append(index);
    builder.append("__Impl");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("@init {");
    builder.newLine();
    builder.append("    ");
    builder.append("int stackSize = keepStackSize();");
    builder.newLine();
    builder.append("  ");
    builder.append('}');
    builder.newLine();
    builder.append(':');
    builder.newLine();
    builder.append(this.ebnf(it.getElements().get(index), options, false));
    builder.newLineIfNotEmpty();
    builder.append(';');
    builder.newLine();
    builder.append("finally {");
    builder.newLine();
    builder.append("  ");
    builder.append("restoreStackSize(stackSize);");
    builder.newLine();
    builder.append('}');
    builder.newLine();
    builder.newLine();
    if (it.getElements().size() > (index + 1)) {
      builder.append(this.ruleImpl(it, grammar, options, (index + 1)));
      builder.newLineIfNotEmpty();
    }
    return builder;
  }

  @Override
  protected String ebnf(final AbstractElement it, final AntlrOptions options, final boolean supportsActions) {
    final StringConcatenation builder = new StringConcatenation();
    final String elementAccess = this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<AbstractElement>getOriginalElement(it));
    final CharSequence paramCfg = this.paramConfig(it);
    if ((!GrammarUtil.isOptionalCardinality(it)) && GrammarUtil.isMultipleCardinality(it)) {
      builder.append('(');
      builder.newLine();
      builder.append("  ");
      builder.append('(');
      builder.newLine();
      builder.append("    ");
      builder.append("{ before(grammarAccess.");
      builder.append(elementAccess, "    ");
      builder.append(paramCfg, "    ");
      builder.append("); }");
      builder.newLineIfNotEmpty();
      builder.append("    ");
      builder.append('(');
      builder.append(this.ebnf2(it, options, supportsActions), "    ");
      builder.append(')');
      builder.newLineIfNotEmpty();
      builder.append("    ");
      builder.append("{ after(grammarAccess.");
      builder.append(elementAccess, "    ");
      builder.append(paramCfg, "    ");
      builder.append("); }");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append(')');
      builder.newLine();
      builder.append("  ");
      builder.append('(');
      builder.newLine();
      builder.append("    ");
      builder.append("{ before(grammarAccess.");
      builder.append(elementAccess, "    ");
      builder.append(paramCfg, "    ");
      builder.append("); }");
      builder.newLineIfNotEmpty();
      builder.append("    ");
      builder.append('(');
      builder.append(this.ebnf2(it, options, supportsActions), "    ");
      builder.append(")*");
      builder.newLineIfNotEmpty();
      builder.append("    ");
      builder.append("{ after(grammarAccess.");
      builder.append(elementAccess, "    ");
      builder.append(paramCfg, "    ");
      builder.append("); }");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append(')');
      builder.newLine();
      builder.append(')');
      builder.newLine();
    } else {
      builder.append('(');
      builder.newLine();
      builder.append("  ");
      builder.append("{ before(grammarAccess.");
      builder.append(elementAccess, "  ");
      builder.append(paramCfg, "  ");
      builder.append("); }");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      if (this.mustBeParenthesized(it)) {
        builder.append('(');
        builder.append(this.ebnf2(it, options, supportsActions), "  ");
        builder.append(')');
      } else {
        builder.append(this.ebnf2(it, options, supportsActions), "  ");
      }
      builder.append(it.getCardinality(), "  ");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append("{ after(grammarAccess.");
      builder.append(elementAccess, "  ");
      builder.append(paramCfg, "  ");
      builder.append("); }");
      builder.newLineIfNotEmpty();
      builder.append(')');
      builder.newLine();
    }
    return builder.toString();
  }

  protected CharSequence paramConfig(final AbstractElement it) {
    final StringConcatenation builder = new StringConcatenation();
    if (((GrammarUtil.containingRule(it).getAlternatives() == it) && ParserRule.class.isInstance(GrammarUtil.containingRule(it)) && (!((ParserRule) AntlrGrammarGenUtil.<AbstractRule>getOriginalElement(GrammarUtil.containingRule(it))).getParameters().isEmpty()))) {
      builder.append(", ");
      builder.append(AntlrGrammarGenUtil.getParameterConfig((ParserRule) GrammarUtil.containingRule(it)));
      builder.newLineIfNotEmpty();
    }
    return builder;
  }

  @Override
  protected String _assignmentEbnf(final AbstractElement it, final Assignment assignment, final AntlrOptions options, final boolean supportsActions) {
    final StringConcatenation builder = new StringConcatenation();
    final String elementAccess = this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<AbstractElement>getOriginalElement(it));
    builder.append('(');
    builder.newLine();
    builder.append("  ");
    builder.append("{ before(grammarAccess.");
    builder.append(elementAccess, "  ");
    builder.append("); }");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append(this.ebnf(it, options, supportsActions), "  ");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("{ after(grammarAccess.");
    builder.append(elementAccess, "  ");
    builder.append("); }");
    builder.newLineIfNotEmpty();
    builder.append(')');
    builder.newLine();
    return builder.toString();
  }

  @Override
  protected String _assignmentEbnf(final CrossReference it, final Assignment assignment, final AntlrOptions options, final boolean supportsActions) {
    final StringConcatenation builder = new StringConcatenation();
    final String elementAccess = this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<CrossReference>getOriginalElement(it));
    builder.append('(');
    builder.newLine();
    builder.append("  ");
    builder.append("{ before(grammarAccess.");
    builder.append(elementAccess, "  ");
    builder.append("); }");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append(this.crossrefEbnf(it.getTerminal(), it, supportsActions), "  ");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("{ after(grammarAccess.");
    builder.append(elementAccess, "  ");
    builder.append("); }");
    builder.newLineIfNotEmpty();
    builder.append(')');
    builder.newLine();
    return builder.toString();
  }

  @Override
  protected String _assignmentEbnf(final Alternatives it, final Assignment assignment, final AntlrOptions options, final boolean supportsActions) {
    final StringConcatenation builder = new StringConcatenation();
    final String elementAccess = this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<Alternatives>getOriginalElement(it));
    builder.append('(');
    builder.newLine();
    builder.append("  ");
    builder.append("{ before(grammarAccess.");
    builder.append(elementAccess, "  ");
    builder.append("); }");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append('(');
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)), "  ");
    builder.append("__");
    builder.append(this._grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.<Alternatives>getOriginalElement(it)), "  ");
    builder.append(')');
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("{ after(grammarAccess.");
    builder.append(elementAccess, "  ");
    builder.append("); }");
    builder.newLineIfNotEmpty();
    builder.append(')');
    builder.newLine();
    return builder.toString();
  }

  @Override
  protected String _assignmentEbnf(final RuleCall it, final Assignment assignment, final AntlrOptions options, final boolean supportsActions) {
    final StringConcatenation builder = new StringConcatenation();
    final String elementAccess = this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<RuleCall>getOriginalElement(it));
    builder.append('(');
    builder.newLine();
    builder.append("  ");
    builder.append("{ before(grammarAccess.");
    builder.append(elementAccess, "  ");
    builder.append("); }");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append(this._grammarAccessExtensions.ruleName(it.getRule()), "  ");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("{ after(grammarAccess.");
    builder.append(elementAccess, "  ");
    builder.append("); }");
    builder.newLineIfNotEmpty();
    builder.append(')');
    builder.newLine();
    return builder.toString();
  }

  @Override
  protected String _crossrefEbnf(final RuleCall it, final CrossReference ref, final boolean supportActions) {
    final StringConcatenation builder = new StringConcatenation();
    final String elementAccess = this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<RuleCall>getOriginalElement(it));
    builder.append('(');
    builder.newLine();
    builder.append("  ");
    builder.append("{ before(grammarAccess.");
    builder.append(elementAccess, "  ");
    builder.append("); }");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append(this.crossrefEbnf(it.getRule(), it, ref, supportActions), "  ");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("{ after(grammarAccess.");
    builder.append(elementAccess, "  ");
    builder.append("); }");
    builder.newLineIfNotEmpty();
    builder.append(')');
    builder.newLine();
    return builder.toString();
  }

  @Override
  protected String _crossrefEbnf(final Keyword it, final CrossReference ref, final boolean supportActions) {
    final StringConcatenation builder = new StringConcatenation();
    final String elementAccess = this._grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.<Keyword>getOriginalElement(it));
    builder.append('(');
    builder.newLine();
    builder.append("  ");
    builder.append("{ before(grammarAccess.");
    builder.append(elementAccess, "  ");
    builder.append("); }");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append(super._crossrefEbnf(it, ref, supportActions), "  ");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("{ after(grammarAccess.");
    builder.append(elementAccess, "  ");
    builder.append("); }");
    builder.newLineIfNotEmpty();
    builder.append(')');
    builder.newLine();
    return builder.toString();
  }

  protected String crossrefEbnf(final TerminalRule it, final RuleCall call, final CrossReference ref, final boolean supportActions) {
    return this._grammarAccessExtensions.ruleName(it);
  }

  protected String crossrefEbnf(final EnumRule it, final RuleCall call, final CrossReference ref, final boolean supportActions) {
    return this._grammarAccessExtensions.ruleName(it);
  }

  @Override
  protected String crossrefEbnf(final AbstractRule it, final RuleCall call, final CrossReference ref, final boolean supportActions) {
    if (GrammarUtil.isDatatypeRule(AntlrGrammarGenUtil.<AbstractRule>getOriginalElement(it))) {
      return this._grammarAccessExtensions.ruleName(it);
    }
    throw new IllegalArgumentException(it.getName() + " is not a datatype rule");
  }

  @Override
  protected String _ebnf2(final Alternatives it, final AntlrOptions options, final boolean supportActions) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)));
    builder.append("__");
    builder.append(this._grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.<Alternatives>getOriginalElement(it)));
    return builder.toString();
  }

  @Override
  protected String _ebnf2(final Assignment it, final AntlrOptions options, final boolean supportActions) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)));
    builder.append("__");
    builder.append(this._grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.<Assignment>getOriginalElement(it)));
    return builder.toString();
  }

  @Override
  protected String _ebnf2(final Group it, final AntlrOptions options, final boolean supportActions) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)));
    builder.append("__");
    builder.append(this._grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.<Group>getOriginalElement(it)));
    builder.append("__0");
    return builder.toString();
  }

  @Override
  protected String _ebnf2(final UnorderedGroup it, final AntlrOptions options, final boolean supportActions) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)));
    builder.append("__");
    builder.append(this._grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.<UnorderedGroup>getOriginalElement(it)));
    return builder.toString();
  }

  @Override
  protected String _ebnf2(final RuleCall it, final AntlrOptions options, final boolean supportActions) {
    return this._grammarAccessExtensions.ruleName(it.getRule());
  }

  @Override
  protected boolean shouldBeSkipped(final TerminalRule it, final Grammar grammar) {
    return false;
  }
}
// CHECKSTYLE:CONSTANTS-ON
