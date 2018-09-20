/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.xtext.generator.parser.common

import com.google.common.base.Splitter
import com.google.common.collect.Lists
import java.util.List
import java.util.regex.Pattern
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtext.AbstractElement
import org.eclipse.xtext.AbstractRule
import org.eclipse.xtext.Action
import org.eclipse.xtext.Alternatives
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.CrossReference
import org.eclipse.xtext.Grammar
import org.eclipse.xtext.GrammarUtil
import org.eclipse.xtext.Group
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.generator.grammarAccess.GrammarAccessUtil
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.xtext.util.internal.EmfAdaptable
import org.eclipse.xtext.xtext.RuleWithParameterValues
import java.util.stream.Collectors

class GrammarRuleAnnotations {

    /**
   * Pattern to deactivate backtracking for single rule.
   */
  static final Pattern NO_BACKTRACK_ANNOTATION_PATTERN = Pattern.compile("@NoBacktrack", Pattern.MULTILINE);	// $NON-NLS-1$
  /**
   * Pattern to search for keyword rule annotations and extracting list of comma-separated keywords.
   */
  static final Pattern KEYWORD_RULE_ANNOTATION_PATTERN = Pattern.compile("@KeywordRule\\(([\\w\\s,]+)\\)",
    Pattern.MULTILINE);	// $NON-NLS-1$
  /**
   * Pattern to search for semantic predicate rule annotation that enables the given rule.
   */
  static final Pattern SEMANTIC_PREDICATE_PATTERN = Pattern.compile("@SemanticPredicate\\(([\\s]*[\\w]+)[\\s]*\\)",
    Pattern.MULTILINE);	// $NON-NLS-1$
  /**
   * Splits comma separated list of keywords.
   */
  static final Splitter KEYWORDS_SPLITTER = Splitter.on(',').trimResults().omitEmptyStrings();


  @EmfAdaptable
  @Data
  static class NoBacktrack {
  }

/**
 * Gated predicate: {condition}?=>
 * Validating predicate: {condition message}?
 */
  @EmfAdaptable
  @Data
  static class SemanticPredicate {
    val String name
    val String message
    val List<String> keywords
  }

  @EmfAdaptable
  @Data
  static class GrammarAnnotations {
    val List<SemanticPredicate> predicates
  }

  def boolean hasNoBacktrackAnnotation(AbstractRule rule){
    return getNoBacktrackAnnotation(rule) !== null
  }

  /**
   * Checks if the given element is contained in a rule with a NoBacktrack annotation.
   *
   * @param element
   *          Grammar element
   * @return {@code true} if there is an annotation on the enclosing rule, {@code false} otherwise
   */
  def boolean hasNoBacktrackAnnotation(AbstractElement element){
    return hasNoBacktrackAnnotation(GrammarUtil.containingRule(element))
  }

  def boolean hasSemanticPredicate(AbstractElement element){
    return findPredicate(element) !== null
  }

  def boolean hasValidatingPredicate(AbstractRule rule){
    return getSemanticPredicateAnnotation(rule) !== null
  }

  /**
   * Returns disambiguating/validating semantic predicate.
   *
   * @param element
   *          Xtext grammar element
   * @return A string containing the semantic predicate or an empty string
   */
  def String generateValidatingPredicate(AbstractRule rule){
    val predicate = getSemanticPredicateAnnotation(rule)
    if(predicate !== null) return generateValidatingPredicate(predicate)
    return "";
  }

  def boolean isGatedPredicateRequired(AbstractElement element){
    return isStartingWithPredicatedRule(element) && isAlternativeAvailable(element);
  }

  /**
   * Returns gated semantic predicate.
   *
   * @param element
   *          Xtext grammar element
   * @return A string containing the semantic predicate or an empty string
   */
  def String generateGatedPredicate(AbstractElement element){
    val predicate = findPredicate(element)
    if(predicate !== null) return generateGatedPredicate(predicate)
    return "";
  }

  def GrammarAnnotations annotateGrammar(Grammar grammar){
    var annotations = GrammarAnnotations.findInEmfObject(grammar)
    if(annotations === null){
      annotations = new GrammarAnnotations(grammar.rules.stream().map[r | annotateRule(r)].filter([a | a !== null]).collect(Collectors.toList))
      annotations.attachToEmfObject(grammar)
    }
    return annotations
  }

  def List<SemanticPredicate> predicates(Grammar grammar){
    return grammar.annotateGrammar.predicates
  }

  /**
   * Checks whether this abstract element leads directly to a keyword rule.
   *
   * @param element
   *          Element call
   * @return {@code true} if the rule leads to a keyword rule
   */
  def boolean isStartingWithPredicatedRule(AbstractElement element) {
    if (element instanceof CrossReference) {
      return findPredicate(element.getTerminal()) !== null;
    }
    return findPredicate(element) !== null;
  }

  /**
   * Finds the predicated rule the given element leads to.
   *
   * @param element
   *          Current element
   * @return Keyword rule or {@code null} if the given element does not lead to a keyword rule
   */
  def SemanticPredicate findPredicate(AbstractElement element) {
    if (element instanceof RuleCall) {
      val predicate = getSemanticPredicateAnnotation(element.getRule())
      if (predicate !== null) {
        return predicate;
      }
      return findPredicate(element.getRule().getAlternatives());
    }
    if (GrammarUtil.isOptionalCardinality(element)) {
      return null;
    }
    if (element instanceof CrossReference) {
      return findPredicate(element.getTerminal());
    }
    if (element instanceof Group) {
      val firstNonActionElement = getFirstNonActionElement(element);
      if (firstNonActionElement !== null) {
        return findPredicate(firstNonActionElement);
      }
    }
    if (element instanceof Assignment) {
      return findPredicate((element).getTerminal());
    }
    return null;
  }

  def boolean isRuleWithPredicate(AbstractRule rule){
    return getSemanticPredicateAnnotation(rule) !== null
  }

  def AbstractElement getFirstNonActionElement(Group group){
    for (AbstractElement groupElement : group.getElements()) {
      if (!(groupElement instanceof Action)) {
        return groupElement;
      }
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
  def boolean isAlternativeAvailable(AbstractElement element) {
    if (GrammarUtil.isOptionalCardinality(element)) {
      return true;
    }
    val container = element.eContainer();
    if (container instanceof Assignment) {
      return isAlternativeAvailable(container);
    } else if (container instanceof CrossReference) {
      return isAlternativeAvailable(container);
    } else if (container instanceof Group) {
      if (isFirstNonActionElement(container, element)) {
        return isAlternativeAvailable(container);
      }
    } else if (container instanceof Alternatives) {
      for (AbstractElement alternative : container.getElements()) {
        if (alternative != element /*&& !isGated(alternative)*/) {
          return true;
        }
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
  def boolean isFirstNonActionElement(Group group, AbstractElement element) {
    return element == getFirstNonActionElement(group);
  }


  def String generateGatedPredicate(SemanticPredicate predicate)
    '''{predicates.«predicate.name»(parserContext)}?=>'''

  def String generateValidatingPredicate(SemanticPredicate predicate)
    '''{predicates.«predicate.name»(parserContext) /* @ErrorMessage(«predicate.message») */}?'''


  /**
   * Translate annotations in comments into predicate annotations.
   *
   * <p>
   * <em>Gated vs. Disambiguating predicates</em> On the first look a disambiguating semantic predicate
   * would be a right choice. However inserting them would be much more difficult. The reason is that we need
   * not only insert them in the beginning of the current rule, but also propagate before actions into alternatives
   * that call this rule. We have to do the same for gated predicated, however there is one important difference.
   * If we have an alternative like {@code rule: (Keyword1 | Keyword2) Keyword3 ;} then we need to insert the predicate
   * only before the first alternative (Keyword1). If we insert disambiguating semantic predicate before both
   * the exception the predicate may throw can destroy recovery for the entire rule i.e. will not try going to Keyword3.
   * We, however, need validating predicate in the beginning of each rule Keyword2, Keyword3. This requires an
   * extra analysis which might be a next step. Gated predicates we can safely insert before Keyword1 and Keyword2.
   * This will have no negative impact. We still need validating predicates in Keyword rules themselves.
   * </p>
   */
  def SemanticPredicate annotateRule(AbstractRule rule) {
    val text = getText(rule);
    var SemanticPredicate predicate = null;
    if (text !== null) {
      predicate = getKeywordRulePredicate(text, rule.getName())
      if(predicate !== null){
        predicate.attachToEmfObject(rule)
      }
      val semanticPredicate = getSemanticPredicate(text)
      if(semanticPredicate !== null){
        if(predicate !== null)
          throw new IllegalArgumentException("You may not combine keyword annotations with semantic predicate annotations on one rule: " + rule.name)
        semanticPredicate.attachToEmfObject(rule)
        predicate = semanticPredicate
      }
      val noBacktrack = getNoBacktrack(text)
      noBacktrack?.attachToEmfObject(rule)
    }
    return predicate
  }

  def SemanticPredicate getSemanticPredicateAnnotation(AbstractRule rule){
      var original = RuleWithParameterValues.findInEmfObject(rule)?.getOriginal();
      if(original === null) original = rule;
      return SemanticPredicate.findInEmfObject(original)
  }

  def NoBacktrack getNoBacktrackAnnotation(AbstractRule rule){
      var original = RuleWithParameterValues.findInEmfObject(rule).getOriginal();
      if(original === null) original = rule;
      return NoBacktrack.findInEmfObject(original)
  }
  /**
   * Checks if the given rule contains {@code @KeywordRule(kw1,kw2)} annotation.
   */
  def SemanticPredicate getKeywordRulePredicate(String text, String ruleName){
      val matcher = KEYWORD_RULE_ANNOTATION_PATTERN.matcher(text);
      if (matcher.find()) {
        return new SemanticPredicate(
          "is" + ruleName + "Enabled",
          "get" + ruleName + "EnabledMessage",
           Lists.newArrayList(KEYWORDS_SPLITTER.split(matcher.group(1)))
        )
      }
  }

  /**
   * Checks if the given rule contains {@code @SemanticPredicate(condition)} annotation.
   */
  def SemanticPredicate getSemanticPredicate(String text){
        val matcher = SEMANTIC_PREDICATE_PATTERN.matcher(text);
        if (matcher.find()) {
         return new SemanticPredicate(
           matcher.group(1),
           "get" + matcher.group(1) + "Message",
          null
         )
        }
  }

  /**
   * Checks if the given rule contains a NoBacktrack annotation.
   */
  def NoBacktrack getNoBacktrack(String text){
      val matcher = NO_BACKTRACK_ANNOTATION_PATTERN.matcher(text);
      if (matcher.find()) {
         return new NoBacktrack
      }
  }

  def String getText(AbstractRule rule) {
    val node = NodeModelUtils.getNode(rule);
    return if(node !== null) node.getText() else GrammarAccessUtil.serialize(rule, "");
  }

}
