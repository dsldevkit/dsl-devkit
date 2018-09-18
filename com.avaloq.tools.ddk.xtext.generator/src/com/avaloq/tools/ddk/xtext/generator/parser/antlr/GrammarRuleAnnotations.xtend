/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.xtext.generator.parser.antlr

import com.google.common.base.Joiner
import com.google.common.base.Splitter
import java.util.regex.Pattern
import java.util.stream.Collectors
import java.util.stream.StreamSupport
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtext.AbstractElement
import org.eclipse.xtext.AbstractRule
import org.eclipse.xtext.Grammar
import org.eclipse.xtext.generator.grammarAccess.GrammarAccessUtil
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.xtext.util.XtextSwitch
import org.eclipse.xtext.util.internal.EmfAdaptable
import org.eclipse.xtext.xtext.RuleWithParameterValues

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
    val String keywords
  }

  def boolean hasNoBacktrackAnnotation(AbstractRule rule){
    return false
  }

  def boolean hasNoBacktrackAnnotation(AbstractElement element){
    return false
  }

  def boolean hasSemanticPredicate(AbstractElement element){
    return false
  }

  def String generateSemanticPredicate(AbstractElement element){
    return ""
  }

  def boolean isGatedPredicateRequired(AbstractElement element){
    return false;
  }

  def String generateGatedPredicate(AbstractElement element){
    return "";
  }

  def void annotateGrammar(Grammar grammar){

  }

  def String generateGatedPredicate(SemanticPredicate predicate)
    '''{predicates.«predicate.name»(parserContext)}?=>'''

  def String generateValidatingPredicate(SemanticPredicate predicate)
    '''{predicates.«predicate.name»(parserContext) /* @ErrorMessage(«predicate.message») */}?'''

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
      return "Unexpected: " + token.getText() + ". Expected: '«Joiner.on("', '").join(KEYWORDS_SPLITTER.split(predicate.keywords))»'";
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
        val condition = StreamSupport.stream(KEYWORDS_SPLITTER.split(predicate.keywords).spliterator(),false).
        map([s | '''"«s»".equalsIgnoreCase(text)''']).collect(Collectors.joining(" || "))
        '''
        String text = parserContext.getInput().LT(1).getText();
        return «condition»;
        '''
      } else {
        "return true; /* Intended to be overridden. */\n";
     }
  }

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
static class GrammarAnnotationVisitor extends XtextSwitch<Boolean> {


  override Boolean caseAbstractRule(AbstractRule object) {
    val original = RuleWithParameterValues.findInEmfObject(object).getOriginal();
    attachRulePredicates(original, object)
    return Boolean.TRUE;
  }



  /**
   * Checks comments of the original grammar rule and, if present, turns them into
   * adapters on the derived rule.
   */
  def void attachRulePredicates(AbstractRule rule, AbstractRule derivedRule) {
    val text = getText(rule);
    if (text !== null) {
      val keywordPredicate = getKeywordRulePredicate(text, rule.getName())
      keywordPredicate?.attachToEmfObject(derivedRule)
      val semanticPredicate = getSemanticPredicate(text)
      semanticPredicate?.attachToEmfObject(derivedRule)
      val noBacktrack = getNoBacktrack(text)
      noBacktrack?.attachToEmfObject(derivedRule)
    }
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
           matcher.group(1)
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
}
