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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.EnumLiteralDeclaration;
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
 *   /
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
// CHECKSTYLE:CONSTANTS-OFF
@SuppressWarnings({"PMD.UnusedFormalParameter", "nls"})
public class AnnotationAwareAntlrContentAssistGrammarGenerator extends AbstractAnnotationAwareAntlrGrammarGenerator {

  // CPD-OFF — migrated Xtend generator code, kept faithful

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
    StringConcatenation builder = new StringConcatenation();
    if (!isCombinedGrammar()) {
      builder.append("""
          import java.util.Map;
          import java.util.HashMap;
          """);
    }
    builder.append("""

    import java.io.InputStream;
    import org.eclipse.xtext.*;
    import org.eclipse.xtext.parser.*;
    import org.eclipse.xtext.parser.impl.*;
    import org.eclipse.emf.ecore.util.EcoreUtil;
    import org.eclipse.emf.ecore.EObject;
    import org.eclipse.xtext.parser.antlr.XtextTokenStream;
    import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
    import %s;
    import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
    import %s;
    """.formatted(getGrammarNaming().getInternalParserSuperClass(it).getName(), _grammarAccessExtensions.getGrammarAccess(it).getName()));
    builder.append(super.compileParserImports(it, options));
    builder.newLineIfNotEmpty();
    builder.newLine();
    return builder.toString();
  }

  @Override
  protected String compileParserMembers(final Grammar it, final AntlrOptions options) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("@");
    if (isCombinedGrammar()) {
      builder.append("parser::");
    }
    builder.append("members {");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append(compileParserMemberDeclarations(it, "protected"), "  ");
    builder.newLineIfNotEmpty();
    if (!isCombinedGrammar()) {
      builder.append("""
        private final Map<String, String> tokenNameToValue = new HashMap<String, String>();

        {
      """);
      List<String> sortedKeywords = IterableExtensions.sortBy(IterableExtensions.sort(GrammarUtil.getAllKeywords(it)), String::length);
      for (final String kw : sortedKeywords) {
        builder.append("  ");
        builder.append("  ");
        builder.append("tokenNameToValue.put(\"");
        builder.append(this.keywordHelper.getRuleName(kw), "    ");
        builder.append("\", \"\'");
        builder.append(AntlrGrammarGenUtil.toStringInAntlrAction(kw).replace("$", "\\u0024"), "    ");
        builder.append("\'\");");
        builder.newLineIfNotEmpty();
      }
      builder.append("  }");
      builder.newLine();
    }
    builder.newLine();
    builder.append("  ");
    builder.append(compileParserSetTokenStreamMethod(), "  ");
    builder.newLineIfNotEmpty();
    builder.newLine();
    builder.append("  ");
    builder.append("public void setPredicates(");
    builder.append(this.predicatesNaming.getSemanticPredicatesSimpleName(it), "  ");
    builder.append(" predicates) {");
    builder.newLineIfNotEmpty();
    builder.append("""
        this.predicates = predicates;
      }

    """);
    builder.append("  ");
    builder.append("public void setGrammarAccess(");
    builder.append(_grammarAccessExtensions.getGrammarAccess(it).getSimpleName(), "  ");
    builder.append(" grammarAccess) {");
    builder.newLineIfNotEmpty();
    builder.append("""
        this.grammarAccess = grammarAccess;
      }

    public void setParserContext(ParserContext parserContext) {
      this.parserContext = parserContext;
    }

      @Override
      protected Grammar getGrammar() {
        return grammarAccess.getGrammar();
      }

      @Override
      protected String getValueForTokenName(String tokenName) {
    """);
    if (isCombinedGrammar()) {
      builder.append("    ");
      builder.append("return tokenName;");
      builder.newLine();
    } else {
      builder.append("""
              String result = tokenNameToValue.get(tokenName);
              if (result == null)
                result = tokenName;
              return result;
          """);
    }
    builder.append("""
            }
          }
          """);
    return builder.toString();
  }

  @Override
  protected CharSequence compileRules(final Grammar g, final AntlrOptions options) {
    StringConcatenation builder = new StringConcatenation();
    Iterable<AbstractRule> rules = Iterables.concat(GrammarUtil.allParserRules(g), GrammarUtil.allEnumRules(g));
    Iterable<EObject> elements = Iterables.concat(rules, GrammarUtil.getAllAlternatives(g));
    elements = Iterables.concat(elements, GrammarUtil.getAllGroups(g));
    elements = Iterables.concat(elements, GrammarUtil.getAllUnorderedGroups(g));
    Iterable<EObject> filtered = IterableExtensions.filter(Iterables.concat(elements, GrammarUtil.getAllAssignments(g)),
        it -> _grammarAccessExtensions.isCalled(GrammarUtil.containingRule(it), g));
    for (final EObject rule : filtered) {
      builder.newLine();
      builder.append(compileRule(rule, g, options));
      builder.newLineIfNotEmpty();
    }
    if (isCombinedGrammar()) {
      builder.append(compileTerminalRules(g, options));
      builder.newLineIfNotEmpty();
    }
    return builder;
  }

  @Override
  @SuppressWarnings("checkstyle:MethodName")
  protected CharSequence _compileRule(final ParserRule it, final Grammar grammar, final AntlrOptions options) {
    StringConcatenation builder = new StringConcatenation();
    if (AntlrGrammarGenUtil.isValidEntryRule(it)) {
      builder.append("// Entry rule ");
      builder.append(_grammarAccessExtensions.entryRuleName(it));
      builder.newLineIfNotEmpty();
      builder.append(_grammarAccessExtensions.entryRuleName(it));
      builder.newLineIfNotEmpty();
      if (it.isDefinesHiddenTokens()) {
        builder.append("@init {");
        builder.newLine();
        builder.append("  ");
        builder.append(compileInitHiddenTokens(it, options), "  ");
        builder.newLineIfNotEmpty();
        builder.append("}");
        builder.newLine();
      }
      builder.append(":");
      builder.newLine();
      builder.append("{ before(grammarAccess.");
      builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)));
      builder.append("); }");
      builder.newLine();
      builder.append("   ");
      builder.append(_grammarAccessExtensions.ruleName(it), "   ");
      builder.newLineIfNotEmpty();
      builder.append("{ after(grammarAccess.");
      builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)));
      builder.append("); }");
      builder.newLine();
      builder.append("   ");
      builder.append("EOF");
      builder.newLine();
      builder.append(";");
      builder.newLine();
      if (it.isDefinesHiddenTokens()) {
        builder.append("finally {");
        builder.newLine();
        builder.append("  ");
        builder.append(compileRestoreHiddenTokens(it, options), "  ");
        builder.newLineIfNotEmpty();
        builder.append("}");
        builder.newLine();
      }
    }
    builder.newLine();
    builder.append("// Rule ");
    builder.append(AntlrGrammarGenUtil.getOriginalElement(it).getName());
    builder.newLineIfNotEmpty();
    builder.append(_grammarAccessExtensions.ruleName(it));
    builder.newLineIfNotEmpty();
    if (this.annotations.hasNoBacktrackAnnotation(it)) {
      builder.append("""
        // Enclosing rule was annotated with @NoBacktrack
        options { backtrack=false; }
      """);
    }
    builder.append("  @init {");
    builder.newLine();
    builder.append("    ");
    builder.append(compileInitHiddenTokens(it, options), "    ");
    builder.newLineIfNotEmpty();
    builder.append("""
        int stackSize = keepStackSize();
      }
      :
    """);
    builder.append("  ");
    if (this.annotations.hasValidatingPredicate(it)) {
      builder.append(this.annotations.generateValidatingPredicate(it), "  ");
    }
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append(ebnf(it.getAlternatives(), options, false), "  ");
    builder.newLineIfNotEmpty();
    builder.append("""
    ;
    finally {
      restoreStackSize(stackSize);
    """);
    builder.append("  ");
    builder.append(compileRestoreHiddenTokens(it, options), "  ");
    builder.newLineIfNotEmpty();
    builder.append("}");
    builder.newLine();
    return builder;
  }

  @Override
  @SuppressWarnings("checkstyle:MethodName")
  protected CharSequence _compileRule(final EnumRule it, final Grammar grammar, final AntlrOptions options) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("// Rule ");
    builder.append(AntlrGrammarGenUtil.getOriginalElement(it).getName());
    builder.newLineIfNotEmpty();
    builder.append(_grammarAccessExtensions.ruleName(it));
    builder.newLineIfNotEmpty();
    builder.append("""
          @init {
            int stackSize = keepStackSize();
          }
        :
        """);
    builder.append("  ");
    builder.append(ebnf(it.getAlternatives(), options, false), "  ");
    builder.newLineIfNotEmpty();
    builder.append(";");
    builder.newLine();
    builder.append("""
        finally {
          restoreStackSize(stackSize);
        }
        """);
    return builder;
  }

  @SuppressWarnings("checkstyle:MethodName")
  protected CharSequence _compileRule(final Alternatives it, final Grammar grammar, final AntlrOptions options) {
    StringConcatenation builder = new StringConcatenation();
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)));
    builder.append("__");
    builder.append(_grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.getOriginalElement(it)));
    builder.newLineIfNotEmpty();
    builder.append("""
          @init {
            int stackSize = keepStackSize();
          }
        :
        """);
    builder.append("  ");
    boolean hasElements = false;
    for (final AbstractElement element : it.getElements()) {
      if (!hasElements) {
        hasElements = true;
      } else {
        builder.appendImmediate("\n|", "  ");
      }
      builder.append(ebnf(element, options, false), "  ");
    }
    builder.newLineIfNotEmpty();
    builder.append(";");
    builder.newLine();
    builder.append("""
        finally {
          restoreStackSize(stackSize);
        }
        """);
    return builder;
  }

  @SuppressWarnings("checkstyle:MethodName")
  protected CharSequence _compileRule(final Assignment it, final Grammar grammar, final AntlrOptions options) {
    StringConcatenation builder = new StringConcatenation();
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)));
    builder.append("__");
    builder.append(_grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.getOriginalElement(it)));
    builder.newLineIfNotEmpty();
    builder.append("""
          @init {
            int stackSize = keepStackSize();
          }
        :
        """);
    builder.append("  ");
    builder.append(assignmentEbnf(it.getTerminal(), it, options, false), "  ");
    builder.newLineIfNotEmpty();
    builder.append(";");
    builder.newLine();
    builder.append("""
        finally {
          restoreStackSize(stackSize);
        }
        """);
    return builder;
  }

  @SuppressWarnings("checkstyle:MethodName")
  protected CharSequence _compileRule(final UnorderedGroup it, final Grammar grammar, final AntlrOptions options) {
    final boolean hasMandatoryContent = IterableExtensions.exists(it.getElements(), element -> !GrammarUtil.isOptionalCardinality(element));
    StringConcatenation builder = new StringConcatenation();
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)));
    builder.append("__");
    builder.append(_grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.getOriginalElement(it)));
    builder.newLineIfNotEmpty();
    builder.append("""
      @init {
        int stackSize = keepStackSize();
        getUnorderedGroupHelper().enter(grammarAccess.\
    """);
    builder.append(_grammarAccessExtensions.gaRuleElementAccessor(AntlrGrammarGenUtil.getOriginalElement(it)), "    ");
    builder.append(");");
    builder.newLine();
    builder.append("""
      }
    :
    """);
    builder.append("  ");
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)), "  ");
    builder.append("__");
    builder.append(_grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
    builder.append("__0");
    builder.newLine();
    if (hasMandatoryContent) {
      builder.append("  ");
      builder.append("{getUnorderedGroupHelper().canLeave(grammarAccess.");
      builder.append(_grammarAccessExtensions.gaRuleElementAccessor(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
      builder.append(")}?");
      builder.newLine();
    } else {
      builder.append("  ?");
      builder.newLine();
    }
    builder.append("""
    ;
    finally {
      getUnorderedGroupHelper().leave(grammarAccess.\
    """);
    builder.append(_grammarAccessExtensions.gaRuleElementAccessor(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
    builder.append(");");
    builder.newLine();
    builder.append("""
      restoreStackSize(stackSize);
    }

    """);
    builder.append(ruleImpl(it, grammar, options));
    builder.newLineIfNotEmpty();
    builder.newLine();
    builder.append(ruleImpl(it, grammar, options, 0));
    builder.newLineIfNotEmpty();
    return builder;
  }

  @SuppressWarnings("checkstyle:MethodName")
  protected CharSequence _compileRule(final Group it, final Grammar grammar, final AntlrOptions options) {
    StringConcatenation builder = new StringConcatenation();
    builder.append(ruleImpl(it, grammar, options, 0));
    builder.newLineIfNotEmpty();
    return builder;
  }

  protected CharSequence ruleImpl(final UnorderedGroup it, final Grammar grammar, final AntlrOptions options) {
    StringConcatenation builder = new StringConcatenation();
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)));
    builder.append("__");
    builder.append(_grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.getOriginalElement(it)));
    builder.append("__Impl");
    builder.newLineIfNotEmpty();
    builder.append("""
          @init {
            int stackSize = keepStackSize();
            boolean selected = false;
          }
        :
            (
        """);
    boolean hasElements = false;
    for (final Pair<Integer, AbstractElement> element : IterableExtensions.indexed(it.getElements())) {
      if (!hasElements) {
        hasElements = true;
      } else {
        builder.appendImmediate("|", "    ");
      }
      builder.append("""
          (
            {getUnorderedGroupHelper().canSelect(grammarAccess.\
      """);
      builder.append(_grammarAccessExtensions.gaRuleElementAccessor(AntlrGrammarGenUtil.getOriginalElement(it)), "      ");
      builder.append(", ");
      builder.append(element.getKey(), "      ");
      builder.append(")}?=>(");
      builder.newLine();
      builder.append("""
              {
                getUnorderedGroupHelper().select(grammarAccess.\
      """);
      builder.append(_grammarAccessExtensions.gaRuleElementAccessor(AntlrGrammarGenUtil.getOriginalElement(it)), "          ");
      builder.append(", ");
      builder.append(element.getKey(), "          ");
      builder.append(");");
      builder.newLine();
      builder.append("""
              }
              {
                selected = true;
              }
              (
      """);
      if (GrammarUtil.isMultipleCardinality(element.getValue())) {
        builder.append("          (");
        builder.newLine();
        builder.append("            { before(grammarAccess.");
        builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(element.getValue())), "            ");
        builder.append("); }");
        builder.newLine();
        builder.append("            (");
        builder.append(ebnf2(element.getValue(), options, false), "            ");
        builder.append(")");
        builder.newLineIfNotEmpty();
        builder.append("            { after(grammarAccess.");
        builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(element.getValue())), "            ");
        builder.append("); }");
        builder.newLine();
        builder.append("          )");
        builder.newLine();
        builder.append("          (");
        builder.newLine();
        builder.append("            { before(grammarAccess.");
        builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(element.getValue())), "            ");
        builder.append("); }");
        builder.newLine();
        builder.append("            ((");
        builder.append(ebnf2(element.getValue(), options, false), "            ");
        builder.append(")=>");
        builder.append(ebnf2(element.getValue(), options, false), "            ");
        builder.append(")*");
        builder.newLine();
        builder.append("            { after(grammarAccess.");
        builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(element.getValue())), "            ");
        builder.append("); }");
        builder.newLine();
        builder.append("          )");
        builder.newLine();
      } else {
        builder.append("          { before(grammarAccess.");
        builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(element.getValue())), "          ");
        builder.append("); }");
        builder.newLine();
        builder.append("          (");
        builder.append(ebnf2(element.getValue(), options, false), "          ");
        builder.append(")");
        builder.newLineIfNotEmpty();
        builder.append("          { after(grammarAccess.");
        builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(element.getValue())), "          ");
        builder.append("); }");
        builder.newLine();
      }
      builder.append("""
              )
            )
          )
      """);
    }
    builder.append("""
        )
    ;
    finally {
      if (selected)
        getUnorderedGroupHelper().returnFromSelection(grammarAccess.\
    """);
    builder.append(_grammarAccessExtensions.gaRuleElementAccessor(AntlrGrammarGenUtil.getOriginalElement(it)), "    ");
    builder.append(");");
    builder.newLine();
    builder.append("""
      restoreStackSize(stackSize);
    }
    """);
    return builder;
  }

  protected CharSequence ruleImpl(final UnorderedGroup it, final Grammar grammar, final AntlrOptions options, final int index) {
    StringConcatenation builder = new StringConcatenation();
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)));
    builder.append("__");
    builder.append(_grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.getOriginalElement(it)));
    builder.append("__");
    builder.append(index);
    builder.newLineIfNotEmpty();
    builder.append("""
          @init {
            int stackSize = keepStackSize();
          }
        :
        """);
    builder.append("  ");
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)), "  ");
    builder.append("__");
    builder.append(_grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
    builder.append("__Impl");
    builder.newLineIfNotEmpty();
    if (it.getElements().size() > index + 1) {
      builder.append("  ");
      builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)), "  ");
      builder.append("__");
      builder.append(_grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
      builder.append("__");
      builder.append(index + 1, "  ");
      builder.append("?");
      builder.newLineIfNotEmpty();
    }
    builder.append(";");
    builder.newLine();
    builder.append("""
        finally {
          restoreStackSize(stackSize);
        }
        """);
    builder.newLine();
    if (it.getElements().size() > index + 1) {
      builder.append(ruleImpl(it, grammar, options, index + 1));
      builder.newLineIfNotEmpty();
    }
    return builder;
  }

  protected CharSequence ruleImpl(final Group it, final Grammar grammar, final AntlrOptions options, final int index) {
    StringConcatenation builder = new StringConcatenation();
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)));
    builder.append("__");
    builder.append(_grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.getOriginalElement(it)));
    builder.append("__");
    builder.append(index);
    builder.newLineIfNotEmpty();
    builder.append("""
          @init {
            int stackSize = keepStackSize();
          }
        :
        """);
    builder.append("  ");
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)), "  ");
    builder.append("__");
    builder.append(_grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
    builder.append("__");
    builder.append(index, "  ");
    builder.append("__Impl");
    builder.newLineIfNotEmpty();
    if (it.getElements().size() > index + 1) {
      builder.append("  ");
      builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)), "  ");
      builder.append("__");
      builder.append(_grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
      builder.append("__");
      builder.append(index + 1, "  ");
      builder.newLineIfNotEmpty();
    }
    builder.append(";");
    builder.newLine();
    builder.append("""
        finally {
          restoreStackSize(stackSize);
        }
        """);
    builder.newLine();
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)));
    builder.append("__");
    builder.append(_grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.getOriginalElement(it)));
    builder.append("__");
    builder.append(index);
    builder.append("__Impl");
    builder.newLineIfNotEmpty();
    builder.append("""
          @init {
            int stackSize = keepStackSize();
          }
        :
        """);
    builder.append(ebnf(it.getElements().get(index), options, false));
    builder.newLineIfNotEmpty();
    builder.append(";");
    builder.newLine();
    builder.append("""
        finally {
          restoreStackSize(stackSize);
        }
        """);
    builder.newLine();
    if (it.getElements().size() > index + 1) {
      builder.append(ruleImpl(it, grammar, options, index + 1));
      builder.newLineIfNotEmpty();
    }
    return builder;
  }

  @Override
  protected String ebnf(final AbstractElement it, final AntlrOptions options, final boolean supportsActions) {
    StringConcatenation builder = new StringConcatenation();
    if (!GrammarUtil.isOptionalCardinality(it) && GrammarUtil.isMultipleCardinality(it)) {
      builder.append("(");
      builder.newLine();
      builder.append("  (");
      builder.newLine();
      builder.append("    { before(grammarAccess.");
      builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)), "    ");
      builder.append(paramConfig(it), "    ");
      builder.append("); }");
      builder.newLine();
      builder.append("    (");
      builder.append(ebnf2(it, options, supportsActions), "    ");
      builder.append(")");
      builder.newLineIfNotEmpty();
      builder.append("    { after(grammarAccess.");
      builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)), "    ");
      builder.append(paramConfig(it), "    ");
      builder.append("); }");
      builder.newLine();
      builder.append("  )");
      builder.newLine();
      builder.append("  (");
      builder.newLine();
      builder.append("    { before(grammarAccess.");
      builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)), "    ");
      builder.append(paramConfig(it), "    ");
      builder.append("); }");
      builder.newLine();
      builder.append("    (");
      builder.append(ebnf2(it, options, supportsActions), "    ");
      builder.append(")*");
      builder.newLine();
      builder.append("    { after(grammarAccess.");
      builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)), "    ");
      builder.append(paramConfig(it), "    ");
      builder.append("); }");
      builder.newLine();
      builder.append("  )");
      builder.newLine();
      builder.append(")");
      builder.newLine();
    } else {
      builder.append("(");
      builder.newLine();
      builder.append("  { before(grammarAccess.");
      builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
      builder.append(paramConfig(it), "  ");
      builder.append("); }");
      builder.newLine();
      builder.append("  ");
      if (mustBeParenthesized(it)) {
        builder.append("(");
        builder.append(ebnf2(it, options, supportsActions), "  ");
        builder.append(")");
      } else {
        builder.append(ebnf2(it, options, supportsActions), "  ");
      }
      builder.append(it.getCardinality(), "  ");
      builder.newLineIfNotEmpty();
      builder.append("  { after(grammarAccess.");
      builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
      builder.append(paramConfig(it), "  ");
      builder.append("); }");
      builder.newLine();
      builder.append(")");
      builder.newLine();
    }
    return builder.toString();
  }

  protected CharSequence paramConfig(final AbstractElement it) {
    StringConcatenation builder = new StringConcatenation();
    if (GrammarUtil.containingRule(it).getAlternatives() == it
        && ParserRule.class.isInstance(GrammarUtil.containingRule(it))
        && !((ParserRule) AntlrGrammarGenUtil.getOriginalElement(GrammarUtil.containingRule(it))).getParameters().isEmpty()) {
      builder.append(", ");
      builder.append(AntlrGrammarGenUtil.getParameterConfig((ParserRule) GrammarUtil.containingRule(it)));
      builder.newLineIfNotEmpty();
    }
    return builder;
  }

  @Override
  @SuppressWarnings("checkstyle:MethodName")
  protected String _assignmentEbnf(final AbstractElement it, final Assignment assignment, final AntlrOptions options, final boolean supportsActions) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("(");
    builder.newLine();
    builder.append("  { before(grammarAccess.");
    builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
    builder.append("); }");
    builder.newLine();
    builder.append("  ");
    builder.append(ebnf(it, options, supportsActions), "  ");
    builder.newLineIfNotEmpty();
    builder.append("  { after(grammarAccess.");
    builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
    builder.append("); }");
    builder.newLine();
    builder.append(")");
    builder.newLine();
    return builder.toString();
  }

  @Override
  @SuppressWarnings("checkstyle:MethodName")
  protected String _assignmentEbnf(final CrossReference it, final Assignment assignment, final AntlrOptions options, final boolean supportsActions) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("(");
    builder.newLine();
    builder.append("  { before(grammarAccess.");
    builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
    builder.append("); }");
    builder.newLine();
    builder.append("  ");
    builder.append(crossrefEbnf(it.getTerminal(), it, supportsActions), "  ");
    builder.newLineIfNotEmpty();
    builder.append("  { after(grammarAccess.");
    builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
    builder.append("); }");
    builder.newLine();
    builder.append(")");
    builder.newLine();
    return builder.toString();
  }

  @Override
  @SuppressWarnings("checkstyle:MethodName")
  protected String _assignmentEbnf(final Alternatives it, final Assignment assignment, final AntlrOptions options, final boolean supportsActions) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("(");
    builder.newLine();
    builder.append("  { before(grammarAccess.");
    builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
    builder.append("); }");
    builder.newLine();
    builder.append("  (");
    builder.append(AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)), "  ");
    builder.append("__");
    builder.append(_grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
    builder.append(")");
    builder.newLineIfNotEmpty();
    builder.append("  { after(grammarAccess.");
    builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
    builder.append("); }");
    builder.newLine();
    builder.append(")");
    builder.newLine();
    return builder.toString();
  }

  @Override
  @SuppressWarnings("checkstyle:MethodName")
  protected String _assignmentEbnf(final RuleCall it, final Assignment assignment, final AntlrOptions options, final boolean supportsActions) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("(");
    builder.newLine();
    builder.append("  { before(grammarAccess.");
    builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
    builder.append("); }");
    builder.newLine();
    builder.append("  ");
    builder.append(_grammarAccessExtensions.ruleName(it.getRule()), "  ");
    builder.newLineIfNotEmpty();
    builder.append("  { after(grammarAccess.");
    builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
    builder.append("); }");
    builder.newLine();
    builder.append(")");
    builder.newLine();
    return builder.toString();
  }

  @Override
  @SuppressWarnings("checkstyle:MethodName")
  protected String _crossrefEbnf(final RuleCall it, final CrossReference ref, final boolean supportActions) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("(");
    builder.newLine();
    builder.append("  { before(grammarAccess.");
    builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
    builder.append("); }");
    builder.newLine();
    builder.append("  ");
    builder.append(crossrefEbnf(it.getRule(), it, ref, supportActions), "  ");
    builder.newLineIfNotEmpty();
    builder.append("  { after(grammarAccess.");
    builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
    builder.append("); }");
    builder.newLine();
    builder.append(")");
    builder.newLine();
    return builder.toString();
  }

  @Override
  @SuppressWarnings("checkstyle:MethodName")
  protected String _crossrefEbnf(final Keyword it, final CrossReference ref, final boolean supportActions) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("(");
    builder.newLine();
    builder.append("  { before(grammarAccess.");
    builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
    builder.append("); }");
    builder.newLine();
    builder.append("  ");
    builder.append(super._crossrefEbnf(it, ref, supportActions), "  ");
    builder.newLineIfNotEmpty();
    builder.append("  { after(grammarAccess.");
    builder.append(_grammarAccessExtensions.grammarElementAccess(AntlrGrammarGenUtil.getOriginalElement(it)), "  ");
    builder.append("); }");
    builder.newLine();
    builder.append(")");
    builder.newLine();
    return builder.toString();
  }

  @SuppressWarnings("checkstyle:MethodName")
  protected String _crossrefEbnf(final TerminalRule it, final RuleCall call, final CrossReference ref, final boolean supportActions) {
    return _grammarAccessExtensions.ruleName(it);
  }

  @SuppressWarnings("checkstyle:MethodName")
  protected String _crossrefEbnf(final EnumRule it, final RuleCall call, final CrossReference ref, final boolean supportActions) {
    return _grammarAccessExtensions.ruleName(it);
  }

  @SuppressWarnings("checkstyle:MethodName")
  protected String _crossrefEbnf(final AbstractRule it, final RuleCall call, final CrossReference ref, final boolean supportActions) {
    if (GrammarUtil.isDatatypeRule(AntlrGrammarGenUtil.getOriginalElement(it))) {
      return _grammarAccessExtensions.ruleName(it);
    }
    throw new IllegalArgumentException(it.getName() + " is not a datatype rule");
  }

  @Override
  @SuppressWarnings("checkstyle:MethodName")
  protected String _ebnf2(final Alternatives it, final AntlrOptions options, final boolean supportActions) {
    return AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)) + "__" + _grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.getOriginalElement(it));
  }

  @Override
  @SuppressWarnings("checkstyle:MethodName")
  protected String _ebnf2(final Assignment it, final AntlrOptions options, final boolean supportActions) {
    return AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)) + "__" + _grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.getOriginalElement(it));
  }

  @Override
  @SuppressWarnings("checkstyle:MethodName")
  protected String _ebnf2(final Group it, final AntlrOptions options, final boolean supportActions) {
    return AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)) + "__" + _grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.getOriginalElement(it)) + "__0";
  }

  @Override
  @SuppressWarnings("checkstyle:MethodName")
  protected String _ebnf2(final UnorderedGroup it, final AntlrOptions options, final boolean supportActions) {
    return AntlrGrammarGenUtil.getContentAssistRuleName(GrammarUtil.containingRule(it)) + "__" + _grammarAccessExtensions.gaElementIdentifier(AntlrGrammarGenUtil.getOriginalElement(it));
  }

  @Override
  @SuppressWarnings("checkstyle:MethodName")
  protected String _ebnf2(final RuleCall it, final AntlrOptions options, final boolean supportActions) {
    return _grammarAccessExtensions.ruleName(it.getRule());
  }

  @Override
  protected boolean shouldBeSkipped(final TerminalRule it, final Grammar grammar) {
    return false;
  }

  @Override
  protected CharSequence compileRule(final Object it, final Grammar grammar, final AntlrOptions options) {
    if (it instanceof Alternatives) {
      return _compileRule((Alternatives) it, grammar, options);
    } else if (it instanceof Group) {
      return _compileRule((Group) it, grammar, options);
    } else if (it instanceof UnorderedGroup) {
      return _compileRule((UnorderedGroup) it, grammar, options);
    } else if (it instanceof Assignment) {
      return _compileRule((Assignment) it, grammar, options);
    } else if (it instanceof EnumRule) {
      return _compileRule((EnumRule) it, grammar, options);
    } else if (it instanceof ParserRule) {
      return _compileRule((ParserRule) it, grammar, options);
    } else if (it instanceof TerminalRule) {
      return _compileRule((TerminalRule) it, grammar, options);
    } else if (it instanceof String) {
      return _compileRule((String) it, grammar, options);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
          + Arrays.<Object> asList(it, grammar, options).toString());
    }
  }

  @Override
  protected String assignmentEbnf(final AbstractElement it, final Assignment assignment, final AntlrOptions options, final boolean supportsActions) {
    if (it instanceof Alternatives) {
      return _assignmentEbnf((Alternatives) it, assignment, options, supportsActions);
    } else if (it instanceof Group) {
      return _assignmentEbnf((Group) it, assignment, options, supportsActions);
    } else if (it instanceof Action) {
      return _assignmentEbnf((Action) it, assignment, options, supportsActions);
    } else if (it instanceof Assignment) {
      return _assignmentEbnf((Assignment) it, assignment, options, supportsActions);
    } else if (it instanceof CrossReference) {
      return _assignmentEbnf((CrossReference) it, assignment, options, supportsActions);
    } else if (it instanceof RuleCall) {
      return _assignmentEbnf((RuleCall) it, assignment, options, supportsActions);
    } else if (it != null) {
      return _assignmentEbnf(it, assignment, options, supportsActions);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
          + Arrays.<Object> asList(it, assignment, options, supportsActions).toString());
    }
  }

  @Override
  protected String crossrefEbnf(final AbstractElement it, final CrossReference ref, final boolean supportActions) {
    if (it instanceof Alternatives) {
      return _crossrefEbnf((Alternatives) it, ref, supportActions);
    } else if (it instanceof Keyword) {
      return _crossrefEbnf((Keyword) it, ref, supportActions);
    } else if (it instanceof RuleCall) {
      return _crossrefEbnf((RuleCall) it, ref, supportActions);
    } else if (it != null) {
      return _crossrefEbnf(it, ref, supportActions);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
          + Arrays.<Object> asList(it, ref, supportActions).toString());
    }
  }

  @Override
  protected String crossrefEbnf(final AbstractRule it, final RuleCall call, final CrossReference ref, final boolean supportActions) {
    if (it instanceof EnumRule) {
      return _crossrefEbnf((EnumRule) it, call, ref, supportActions);
    } else if (it instanceof TerminalRule) {
      return _crossrefEbnf((TerminalRule) it, call, ref, supportActions);
    } else if (it != null) {
      return _crossrefEbnf(it, call, ref, supportActions);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
          + Arrays.<Object> asList(it, call, ref, supportActions).toString());
    }
  }

  @Override
  protected String ebnf2(final AbstractElement it, final AntlrOptions options, final boolean supportActions) {
    if (it instanceof Alternatives) {
      return _ebnf2((Alternatives) it, options, supportActions);
    } else if (it instanceof Group) {
      return _ebnf2((Group) it, options, supportActions);
    } else if (it instanceof UnorderedGroup) {
      return _ebnf2((UnorderedGroup) it, options, supportActions);
    } else if (it instanceof Action) {
      return _ebnf2((Action) it, options, supportActions);
    } else if (it instanceof Assignment) {
      return _ebnf2((Assignment) it, options, supportActions);
    } else if (it instanceof EnumLiteralDeclaration) {
      return _ebnf2((EnumLiteralDeclaration) it, options, supportActions);
    } else if (it instanceof Keyword) {
      return _ebnf2((Keyword) it, options, supportActions);
    } else if (it instanceof RuleCall) {
      return _ebnf2((RuleCall) it, options, supportActions);
    } else if (it != null) {
      return _ebnf2(it, options, supportActions);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
          + Arrays.<Object> asList(it, options, supportActions).toString());
    }
  }
}
// CHECKSTYLE:CONSTANTS-ON
