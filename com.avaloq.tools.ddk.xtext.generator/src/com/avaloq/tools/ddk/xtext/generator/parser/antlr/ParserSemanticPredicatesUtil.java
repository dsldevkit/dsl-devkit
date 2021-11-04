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

package com.avaloq.tools.ddk.xtext.generator.parser.antlr;

import static org.eclipse.xtext.EcoreUtil2.typeSelect;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.generator.Naming;
import org.eclipse.xtext.generator.grammarAccess.GrammarAccessUtil;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


/**
 * Utility for semantic predicates in Xtext grammars.
 */
@SuppressWarnings("deprecation")
public final class ParserSemanticPredicatesUtil {

  /**
   * Pattern to deactivate backtracking for single rule.
   */
  private static final Pattern NO_BACKTRACK_ANNOTATION_PATTERN = Pattern.compile("@NoBacktrack", Pattern.MULTILINE); //$NON-NLS-1$

  /**
   * Pattern to search for keyword rule annotations and extracting list of comma-separated keywords.
   */
  private static final Pattern KEYWORD_RULE_ANNOTATION_PATTERN = Pattern.compile("@KeywordRule\\(([\\w\\s,]+)\\)", Pattern.MULTILINE); //$NON-NLS-1$

  /**
   * Pattern to search for semantic predicate rule annotation that enables the given rule.
   */
  private static final Pattern SEMANTIC_PREDICATE_PATTERN = Pattern.compile("@SemanticPredicate\\(([\\s]*[\\w]+)[\\s]*\\)", Pattern.MULTILINE); //$NON-NLS-1$

  /**
   * Splits comma separated list of keywords.
   */
  private static final Splitter KEYWORDS_SPLITTER = Splitter.on(',').trimResults().omitEmptyStrings();

  /**
   * Suffix used in the naming convention for the classes responsible for semantic predicates.
   */
  private static final String CLASS_SUFFIX = "SemanticPredicates"; //$NON-NLS-1$

  /**
   * Prevents instantiation of {@link AcfAntlrGeneratorFragmentUtil}.
   */
  private ParserSemanticPredicatesUtil() {
    // Prevents creating an instance
  }

  /**
   * Returns disambiguating semantic predicate if the given rule is a keyword rule.
   *
   * @param rule
   *          Xtext grammar rule
   * @return A string containing the semantic predicate or an empty string
   */
  public static String getPredicate(final AbstractRule rule) {
    return getRulePredicate(rule, false);
  }

  /**
   * Returns gated semantic predicate if the given rule is a keyword rule.
   *
   * @param ruleCall
   *          Rule call grammar element
   * @return A string containing the semantic predicate or an empty string
   */
  public static String getGatedPredicate(final AbstractElement ruleCall) {
    return getRulePredicate(findPredicatedRule(ruleCall), true);
  }

  /**
   * Returns a string with a gated semantic predicate for rules that replace a keyword or an alternative of keywords.
   * Works for data type rules only.
   * <p>
   * <em>Note:</em> If the comment before the rule contains {@code  @KeywordRule(lookup) } then we generate a predicate in the rule so the rule looks like
   *
   * @param rule
   *          Xtext grammar rule
   * @param gated
   *          Whether the inserted predicate should be a gated predicate
   * @return A string containing the semantic predicate or an empty string {@code parserContext.getInput().LT(1).getText().equals(“lookup")}?=> } Multiple
   *         keywords are also supported. Such as: {@code @KeywordRule(hide,show,show_per_bu) }
   *         </p>
   *         <p>
   *         <em>Gated vs. Disambiguating predicates</em> On the first look a disambiguating semantic predicate would be a right choice. And indeed this is
   *         true, however inserting them would be much more difficult. The reason is that we need not only insert them in the beginning of the current rule,
   *         but also propagate before actions into alternatives that call this rule. We have to do the same for gated predicated, however there is one
   *         important difference. If we have an alternative like {@code rule: (Keyword1 | Keyword2) Keyword3 ;} then we need to insert the predicate only
   *         before the first alternative (Keyword1). If we insert disambiguating semantic predicate before both the exception is will throw will damage the
   *         recovery for the entire rule i.e. will not try going to Keyword3. Clearly we need validating predicated in the beginning of each rule Keyword2,
   *         Keyword3. This requires an extra analysis which might be a next step. Gated predicates we can safely insert before Keyword1 and Keyword2. This will
   *         have no negative impact. We still need validating predicates in Keyword rules themselves.
   *         </p>
   */
  public static String getRulePredicate(final AbstractRule rule, final boolean gated) {
    String text = getText(rule);
    if (text != null) {
      String condition = null;
      Matcher matcher = KEYWORD_RULE_ANNOTATION_PATTERN.matcher(text);
      if (matcher.find()) {
        condition = "predicates.is" + rule.getName() + "Enabled(parserContext)"; //$NON-NLS-1$ //$NON-NLS-2$
      } else {
        matcher = SEMANTIC_PREDICATE_PATTERN.matcher(text);
        if (matcher.find()) {
          condition = "predicates." + matcher.group(1) + "(parserContext)"; //$NON-NLS-1$ //$NON-NLS-2$
        }
      }
      if (condition != null) {
        StringBuilder predicate = new StringBuilder();
        predicate.append('{');
        predicate.append(condition);
        if (!gated) { // This does not bring anything in gated predicates
          predicate.append("/* @ErrorMessage("); //$NON-NLS-1$
          predicate.append(getRulePredicateMessageName(rule));
          predicate.append(") */"); //$NON-NLS-1$
        }
        predicate.append("}?"); //$NON-NLS-1$
        if (gated) {
          predicate.append("=>"); //$NON-NLS-1$
        }
        return predicate.toString();
      }
    }
    return ""; //$NON-NLS-1$
  }

  private static String getText(final AbstractRule rule) {
    INode node = NodeModelUtils.getNode(rule);
    return node != null ? node.getText() : GrammarAccessUtil.serialize(rule, ""); //$NON-NLS-1$
  }

  /**
   * Returns name for the predicate.
   *
   * @param rule
   *          Grammar rule
   * @return Name for the predicate
   */
  public static String getPredicateName(final AbstractRule rule) {
    String text = getText(rule);
    String condition = "<undefined>"; //$NON-NLS-1$
    if (text != null) {
      Matcher matcher = KEYWORD_RULE_ANNOTATION_PATTERN.matcher(text);
      if (matcher.find()) {
        condition = "is" + rule.getName() + "Enabled"; //$NON-NLS-1$ //$NON-NLS-2$
      } else {
        matcher = SEMANTIC_PREDICATE_PATTERN.matcher(text);
        if (matcher.find()) {
          condition = matcher.group(1);
        }
      }
    }
    return condition;
  }

  /**
   * Returns name for the predicate message method.
   *
   * @param rule
   *          Grammar rule
   * @return Name for the predicate message method
   */
  public static String getRulePredicateMessageName(final AbstractRule rule) {
    String text = getText(rule);
    String condition = "<undefined>"; //$NON-NLS-1$
    if (text != null) {
      Matcher matcher = KEYWORD_RULE_ANNOTATION_PATTERN.matcher(text);
      if (matcher.find()) {
        condition = "get" + rule.getName() + "EnabledMessage"; //$NON-NLS-1$ //$NON-NLS-2$
      } else {
        matcher = SEMANTIC_PREDICATE_PATTERN.matcher(text);
        if (matcher.find()) {
          condition = "get" + matcher.group(1) + "Message"; //$NON-NLS-1$ //$NON-NLS-2$
        }
      }
    }
    return condition;
  }

  /**
   * Returns name for the predicate message method.
   *
   * @param rule
   *          Grammar rule
   * @return Name for the predicate message method
   */
  public static String getRulePredicateMessage(final AbstractRule rule) {
    String text = getText(rule);
    if (text != null) {
      Matcher matcher = KEYWORD_RULE_ANNOTATION_PATTERN.matcher(text);
      if (matcher.find()) {
        StringBuilder predicate = new StringBuilder();
        predicate.append("return \"Unexpected: \" + token.getText() + \". Expected: '"); //$NON-NLS-1$
        predicate.append(Joiner.on("', '").join(KEYWORDS_SPLITTER.split(matcher.group(1)))); //$NON-NLS-1$
        predicate.append("'\";\n"); //$NON-NLS-1$
        return predicate.toString();
      } else {
        matcher = SEMANTIC_PREDICATE_PATTERN.matcher(text);
        if (matcher.find()) {
          return "/* Default message. Intended to be overridden. */\n    return \"Condition " + matcher.group(1) + " is not fullfilled \";\n"; //$NON-NLS-1$ //$NON-NLS-2$
        }
      }
    }
    return "return null;\n"; //$NON-NLS-1$
  }

  /**
   * Returns predicate condition (default condition).
   *
   * @param rule
   *          Xtext grammar rule
   * @return A string containing the condition for the semantic predicate or {@code null}
   */
  public static String getRulePredicateCondition(final AbstractRule rule) {
    String text = getText(rule);
    if (text != null) {
      Matcher matcher = KEYWORD_RULE_ANNOTATION_PATTERN.matcher(text);
      if (matcher.find()) {
        StringBuilder predicate = new StringBuilder();
        predicate.append("String text = parserContext.getInput().LT(1).getText();"); //$NON-NLS-1$
        predicate.append('\n');
        predicate.append("    return "); //$NON-NLS-1$
        boolean passedFirst = false;
        for (String kw : KEYWORDS_SPLITTER.split(matcher.group(1))) {
          if (passedFirst) {
            predicate.append(" || "); //$NON-NLS-1$
          }
          predicate.append('"');
          predicate.append(kw);
          predicate.append('"');
          predicate.append(".equalsIgnoreCase(text)"); //$NON-NLS-1$
          passedFirst = true;
        }
        predicate.append(";\n"); //$NON-NLS-1$
        return predicate.toString();
      } else {
        return "return " + getSemanticPredicatesName(GrammarUtil.getGrammar(rule)) + "." + getPredicateName(rule) + "(parserContext);\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      }
    }
    return "return true; \n"; //$NON-NLS-1$
  }

  /**
   * Checks whether the gated semantic predicates is required before this rule call.
   * <p>
   * <em>Note:</em> Currently we decide that a gated semantic predicate should be inserted if
   * <ul>
   * <li>There is an alternative to this rule that is not gated</li>
   * <li>This rule calls lead to a keyword rule</li>
   * </ul>
   * </p>
   *
   * @param rule
   *          Rule call
   * @return {@code true} if the gated semantic predicate should be inserted
   */
  public static boolean isGatedPredicateRequired(final AbstractElement rule) {
    return isStartingWithPredicatedRule(rule) && isAlternativeAvailable(rule);
  }

  /**
   * Checks whether this abstract element leads directly to a keyword rule.
   *
   * @param element
   *          Element call
   * @return {@code true} if the rule leads to a keyword rule
   */
  private static boolean isStartingWithPredicatedRule(final AbstractElement element) {
    if (element instanceof CrossReference) {
      CrossReference reference = (CrossReference) element;
      return findPredicatedRule(reference.getTerminal()) != null;
    }
    return findPredicatedRule(element) != null;
  }

  /**
   * Finds the keyword rule the given element leads to.
   *
   * @param element
   *          Current element
   * @return Keyword rule or {@code null} if the given element does not lead to a keyword rule
   */
  private static AbstractRule findPredicatedRule(final AbstractElement element) {
    if (element instanceof RuleCall) {
      RuleCall ruleCall = (RuleCall) element;
      AbstractRule rule = ruleCall.getRule();
      if (isKeywordRulePredicate(rule) || isSemanticPredicate(rule)) {
        return rule;
      }
      return findPredicatedRule(rule.getAlternatives());
    }
    if (GrammarUtil.isOptionalCardinality(element)) {
      return null;
    }
    if (element instanceof CrossReference) {
      CrossReference reference = (CrossReference) element;
      return findPredicatedRule(reference.getTerminal());
    }
    if (element instanceof Group) {
      Group group = (Group) element;
      AbstractElement firstNonActionElement = getFirstNonActionElement(group);
      if (firstNonActionElement != null) {
        return findPredicatedRule(firstNonActionElement);
      }
    }
    if (element instanceof Assignment) {
      return findPredicatedRule(((Assignment) element).getTerminal());
    }
    return null;
  }

  /**
   * Gets the first non action element of the group.
   *
   * @param group
   *          from which the first element is needed
   * @return the first non action element or {@code null} if group is empty or if the only element is an action
   */
  private static AbstractElement getFirstNonActionElement(final Group group) {
    for (AbstractElement groupElement : group.getElements()) {
      if (groupElement instanceof Action) {
        continue;
      }
      return groupElement; // NOPMD AvoidBranchingStatementAsLastInLoop
    }
    return null;
  }

  /**
   * Checks whether there is a viable alternative (i.e. not potentially gated by another gated semantic predicate) for the current element.
   * If there is no alternative we should not insert gated semantic predicates, but validating semantic predicates, so we get a better error recovery and
   * a correct message. We should not also propagate validating predicates in the callers. If we do so this will damage error recovery in the callers.
   *
   * @param element
   *          Grammar element
   * @return {@code true} if there is an alternative available and the gated semantic predicate can be inserted
   */
  public static boolean isAlternativeAvailable(final AbstractElement element) {
    if (GrammarUtil.isOptionalCardinality(element)) {
      return true;
    }
    EObject container = element.eContainer();
    if (container instanceof Assignment) {
      return isAlternativeAvailable((Assignment) container);
    } else if (container instanceof CrossReference) {
      return isAlternativeAvailable((CrossReference) container);
    } else if (container instanceof Group) {
      Group group = (Group) container;
      if (isFirstNonActionElement(group, element)) {
        return isAlternativeAvailable(group);
      }
    } else if (container instanceof Alternatives) {
      Alternatives alternatives = (Alternatives) container;
      for (AbstractElement alternative : alternatives.getElements()) {
        if (alternative.equals(element)) {
          continue;
        }
        return true; // NOPMD AvoidBranchingStatementAsLastInLoop
      }
    }
    return false;
  }

  /**
   * Checks if the given grammar element is the first non action element in the given group.
   *
   * @param group
   *          where the position is checked, must not be {@code null}
   * @param element
   *          to check, must not be {@code null}
   * @return {@code true}, if is first non action element is the given element, {@code false} otherwise
   */
  private static boolean isFirstNonActionElement(final Group group, final AbstractElement element) {
    for (AbstractElement groupElement : group.getElements()) {
      if (groupElement instanceof Action) {
        continue;
      }
      return Objects.equal(groupElement, element); // NOPMD AvoidBranchingStatementAsLastInLoop
    }
    return false;
  }

  /**
   * Keyword rule proposals.
   *
   * @param rule
   *          Xtext grammar rule
   * @return A string containing the proposals for abstract content assist
   */
  public static String keywordRuleProposals(final AbstractRule rule) {
    String text = getText(rule);
    if (text != null) {
      Matcher matcher = KEYWORD_RULE_ANNOTATION_PATTERN.matcher(text);
      if (matcher.find()) {
        StringBuilder predicate = new StringBuilder();
        boolean passedFirst = false;
        for (String kw : KEYWORDS_SPLITTER.split(matcher.group(1))) {
          if (passedFirst) {
            predicate.append("    "); //$NON-NLS-1$
          }
          predicate.append("propose("); //$NON-NLS-1$
          predicate.append('"');
          predicate.append(kw);
          predicate.append('"');
          predicate.append(", "); //$NON-NLS-1$
          predicate.append('"');
          predicate.append(kw);
          predicate.append('"');
          predicate.append(", getImage(model), context, acceptor);"); //$NON-NLS-1$
          predicate.append('\n');
          passedFirst = true;
        }
        if (passedFirst) {
          return predicate.toString();
        }
      }
    }
    return ""; //$NON-NLS-1$
  }

  /**
   * Checks if the given rule contains a keyword rule annotation.
   *
   * @param rule
   *          Grammar rule
   * @return {@code true} if there is an annotation, {@code false} otherwise
   */
  public static boolean isKeywordRulePredicate(final AbstractRule rule) {
    return matches(rule, KEYWORD_RULE_ANNOTATION_PATTERN);
  }

  /**
   * Checks if the given rule contains a keyword rule annotation.
   *
   * @param rule
   *          Grammar rule
   * @return {@code true} if there is an annotation, {@code false} otherwise
   */
  public static boolean isSemanticPredicate(final AbstractRule rule) {
    return matches(rule, SEMANTIC_PREDICATE_PATTERN);
  }

  /**
   * Checks if the given rule contains a NoBacktrack annotation.
   *
   * @param rule
   *          Grammar rule
   * @return {@code true} if there is an annotation, {@code false} otherwise
   */
  public static boolean isNoBacktrack(final AbstractRule rule) {
    return matches(rule, NO_BACKTRACK_ANNOTATION_PATTERN);
  }

  private static boolean matches(final AbstractRule rule, final Pattern pattern) {
    String text = getText(rule);
    if (text != null) {
      return pattern.matcher(text).find();
    }
    return false;
  }

  /**
   * Checks if the given element is contained in a rule with a NoBacktrack annotation.
   *
   * @param element
   *          Grammar element
   * @return {@code true} if there is an annotation on the enclosing rule, {@code false} otherwise
   */
  public static boolean isNoBacktrack(final AbstractElement element) {
    return isNoBacktrack(GrammarUtil.containingRule(element));
  }

  /**
   * Checks if the given rule contains a predicate annotation or a keyword rule.
   *
   * @param rule
   *          Grammar rule
   * @return {@code true} if there is an annotation, {@code false} otherwise
   */
  public static boolean isWithPredicate(final AbstractRule rule) {
    return isKeywordRulePredicate(rule) || isSemanticPredicate(rule);
  }

  /**
   * Returns the name of the grammar predicates class that implements the predicates.
   *
   * @param grammar
   *          Grammar
   * @return Name of the predicates class
   */
  public static String getSemanticPredicatesName(final Grammar grammar) {
    return GrammarUtil.getSimpleName(grammar) + CLASS_SUFFIX;
  }

  /**
   * Returns the name of the grammar predicates class.
   *
   * @param grammar
   *          Grammar
   * @param naming
   *          Naming
   * @return Name of the predicates class
   */
  public static String getSemanticPredicatesFullName(final Grammar grammar, final Naming naming) {
    return naming.basePackageRuntime(grammar) + ".grammar.Abstract" + GrammarUtil.getSimpleName(grammar) + "SemanticPredicates"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * Finds reachable parser rules.
   *
   * @param grammar
   *          Root grammar
   * @return Reachable parser rules
   */
  public static List<ParserRule> allReachableParserRules(final Grammar grammar) {
    return typeSelect(allReachableRules(grammar), ParserRule.class);
  }

  /**
   * Finds all classes that implement a semantic predicate which got inherited from another language.
   *
   * @param grammar
   *          Root grammar
   * @param naming
   *          naming
   * @return Full name of the classes that implement an inherited semantic predicate
   */
  public static Set<String> allInheritedSemanticPredicates(final Grammar grammar, final Naming naming) {
    final Set<String> result = Sets.newHashSet();
    for (AbstractRule rule : GrammarUtil.allParserRules(grammar)) {
      final Grammar ruleGrammar = GrammarUtil.getGrammar(rule);
      if (isSemanticPredicate(rule) && !ruleGrammar.equals(grammar)) {
        result.add(naming.basePackageRuntime(ruleGrammar) + ".grammar." + getSemanticPredicatesName(ruleGrammar)); //$NON-NLS-1$
      }
    }
    return result;
  }

  /**
   * Finds reachable enumeration rules.
   *
   * @param grammar
   *          Root grammar
   * @return Reachable enumeration rules
   */
  public static List<EnumRule> allReachableEnumRules(final Grammar grammar) {
    return typeSelect(allReachableRules(grammar), EnumRule.class);
  }

  /**
   * Finds rules reachable from the first rule of the given grammar element.
   * The search includes rules in referenced grammars as well.
   * Intended as a replacement to search in Xtext which takes all rules of all grammars involved when generating the input for ANTLR.
   *
   * @param grammar
   *          Root grammar
   * @return Reachable rules
   */
  public static List<AbstractRule> allReachableRules(final Grammar grammar) {
    final List<AbstractRule> result = Lists.newArrayList();
    List<AbstractRule> rulesToVisit = Lists.newLinkedList();
    rulesToVisit.add(grammar.getRules().get(0));
    while (!rulesToVisit.isEmpty()) {
      AbstractRule rule = rulesToVisit.remove(0);
      if (!result.contains(rule)) {
        result.add(rule);
        for (RuleCall call : GrammarUtil.containedRuleCalls(rule)) {
          AbstractRule callee = call.getRule();
          rulesToVisit.add(callee);
        }
      }
    }
    return result;
  }

}

/* Copyright (c) Avaloq Evolution AG */