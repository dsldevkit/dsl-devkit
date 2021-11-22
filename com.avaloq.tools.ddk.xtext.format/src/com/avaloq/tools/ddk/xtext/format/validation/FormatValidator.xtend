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
package com.avaloq.tools.ddk.xtext.format.validation

import com.avaloq.tools.ddk.xtext.format.format.Constant
import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration
import com.avaloq.tools.ddk.xtext.format.format.GrammarElementLookup
import com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference
import com.avaloq.tools.ddk.xtext.format.format.GrammarRule
import com.avaloq.tools.ddk.xtext.format.format.IntValue
import com.avaloq.tools.ddk.xtext.format.format.KeywordPair
import com.avaloq.tools.ddk.xtext.format.format.Matcher
import com.avaloq.tools.ddk.xtext.format.format.MatcherType
import com.avaloq.tools.ddk.xtext.format.format.Rule
import com.avaloq.tools.ddk.xtext.format.format.SpecificDirective
import com.avaloq.tools.ddk.xtext.format.format.StringValue
import com.avaloq.tools.ddk.xtext.format.format.WildcardRule
import com.avaloq.tools.ddk.xtext.util.EObjectUtil
import com.google.common.collect.Iterables
import com.google.common.collect.Iterators
import com.google.common.collect.Lists
import com.google.common.collect.Maps
import com.google.common.collect.Sets
import java.util.Iterator
import org.eclipse.emf.ecore.EObject
import org.eclipse.osgi.util.NLS
import org.eclipse.xtext.AbstractRule
import org.eclipse.xtext.EnumRule
import org.eclipse.xtext.Grammar
import org.eclipse.xtext.GrammarUtil
import org.eclipse.xtext.ParserRule
import org.eclipse.xtext.TerminalRule
import org.eclipse.xtext.validation.Check
import com.avaloq.tools.ddk.xtext.format.format.FormatPackage
import com.google.common.base.Predicate
import com.google.common.base.Function
import com.google.common.base.Predicates

/**
 * This class contains custom validation rules.
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class FormatValidator extends AbstractFormatValidator {

  /**
   * Validation code for illegal format directives for Terminal or Datatype rules.
   */
  public static val ILLEGAL_DIRECTIVE_CODE = "com.avaloq.tools.ddk.xtext.format.validation.IllegalDirective"
  /**
   * Validation code for override missing.
   */
  public static val OVERRIDE_MISSING_CODE = "com.avaloq.tools.ddk.xtext.format.validation.MissingOverride"
  /**
   * Validation code for illegal override.
   */
  public static val OVERRIDE_ILLEGAL_CODE = "com.avaloq.tools.ddk.xtext.format.validation.IllegalOverride"
  /**
   * Validation code for incompatible grammar of extended format.
   */
  public static val EXTENDED_GRAMMAR_INCOMPATIBLE_CODE = "com.avaloq.tools.ddk.xtext.format.validation.ExtendedGrammarIncompatible"
  /**
   * Validation code for required grammar rule missing.
   */
  public static val GRAMMAR_RULE_MISSING_CODE = "com.avaloq.tools.ddk.xtext.format.validation.GrammarRuleMissing"
  static val OVERRIDE_MISSING_MESSAGE = "Override missing"
  static val OVERRIDE_ILLEGAL_MESSAGE = "Override illegal"
  static val IS_OVERRIDE = new Predicate<Rule>() {
    override apply(Rule input) {
      return input.isOverride()
    }

  };
  static val TARGET_RULE = new Function<GrammarRule, AbstractRule>() {
    override apply(GrammarRule from) {
      return from.getTargetRule()
    }
  };

  /**
   * Verify that only rule self directives are used for terminal, enum and data type rules.
   *
   * @param model
   *          the GrammarRule
   */
  @Check
  def void checkDataTypeOrEnumRule(GrammarRule model) {
    if (model.getTargetRule() instanceof TerminalRule || model.getTargetRule() instanceof EnumRule
        || (model.getTargetRule() instanceof ParserRule && GrammarUtil.isDatatypeRule(model.getTargetRule() as ParserRule))) {
      val grammarElementAccessors = collectGrammarElementAccessors(model)
      val selfAccessOnly = Iterators.all(grammarElementAccessors, new Predicate<EObject>() {
        override apply(EObject input) {
          return input instanceof GrammarElementReference && (input as GrammarElementReference).getSelf() !== null
        }
      });
      if (!selfAccessOnly) {
        error(NLS.bind("For data type, enum or terminal rule {0} only ''rule'' directive may be used", model.getTargetRule().getName()), FormatPackage::Literals::GRAMMAR_RULE__DIRECTIVES, ILLEGAL_DIRECTIVE_CODE)
      }
    }
  }

  /**
   * Verify that there are exactly two grammar elements if "between" is used.
   *
   * @param matcher
   *          the Matcher
   */
  @Check
  def void checkBetweenArguments(Matcher matcher) {
    checkTwoArgumentMatcherType(matcher, MatcherType::BETWEEN)
  }

  /**
   * Verify that there are exactly two grammar elements if "range" is used.
   *
   * @param matcher
   *          the Matcher
   */
  @Check
  def void checkRangeArguments(Matcher matcher) {
    checkTwoArgumentMatcherType(matcher, MatcherType::RANGE)
  }

  /**
   * Verify that references to other grammar rules at most occur once and that "between" or "range" is used.
   *
   * @param elementReference
   *          reference to a grammar element
   */
  @Check
  def void checkRuleReference(GrammarElementReference elementReference) {
    if (elementReference.getRule() === null) {
      return
    }
    val directive = EObjectUtil.eContainer(elementReference, SpecificDirective);
    if (directive.getMatcherList() !== null) {
      val twoArgumentMatcherTypesOnly = Iterables.all(directive.getMatcherList().getMatchers(), new Predicate<Matcher>() {
        override apply(Matcher input) {
          return input.getType() == MatcherType::BETWEEN || input.getType() == MatcherType::RANGE
        }
      });
      if (!twoArgumentMatcherTypesOnly) {
        error(NLS.bind("Grammar rules may only be used with \"{0}\" or \"{1}\"", MatcherType::RANGE.getName(), MatcherType::BETWEEN.getName()), FormatPackage::Literals::GRAMMAR_ELEMENT_REFERENCE__RULE)
      }
      val ruleGrammarElements = Iterables.filter(directive.getGrammarElements(), new Predicate<GrammarElementReference>() {
        override apply(GrammarElementReference input) {
          return input.getRule() !== null
        }
      })
      if (Iterables.size(ruleGrammarElements) != 1) {
        error(NLS.bind("Only one grammar rule may be referenced with \"{0}\" and \"{1}\"", MatcherType::RANGE.getName(), MatcherType::BETWEEN.getName()), FormatPackage::Literals::GRAMMAR_ELEMENT_REFERENCE__RULE)
      }
    }
  }

  /**
   * Checks that rules declare overrides when there is a corresponding inherited rule.
   *
   * @param model
   *          the model
   */
  @Check
  def void checkOverrideMissing(FormatConfiguration model) {
    val extendedModel = model.getExtendedFormatConfiguration();
    if (extendedModel === null || extendedModel.eIsProxy()) {
      return
    }
    val nonOverrideRules = Iterables.filter(model.getRules(), Predicates.not(IS_OVERRIDE))
    val overriddenRules = collectRules(extendedModel)
    val localAbstractRuleMap = Maps.newHashMap()
    for (GrammarRule rule : Iterables.filter(nonOverrideRules, GrammarRule)) {
      localAbstractRuleMap.put(TARGET_RULE.apply(rule), rule)
    }
    // Check GrammarRules
    for (GrammarRule overriddenRule : Iterables.filter(overriddenRules, GrammarRule)) {
      if (localAbstractRuleMap.containsKey(TARGET_RULE.apply(overriddenRule))) {
        val localRule = localAbstractRuleMap.get(TARGET_RULE.apply(overriddenRule))
        error(OVERRIDE_MISSING_MESSAGE, localRule, FormatPackage::Literals::GRAMMAR_RULE__TARGET_RULE, OVERRIDE_MISSING_CODE)
      }
    }
    // Check WildcardRule
    if (!Iterables.isEmpty(Iterables.filter(nonOverrideRules, WildcardRule))
        && !Iterables.isEmpty(Iterables.filter(overriddenRules, WildcardRule))) {
      error(OVERRIDE_MISSING_MESSAGE, Iterables.filter(nonOverrideRules, WildcardRule).iterator().next(), null, OVERRIDE_MISSING_CODE)
    }
  }

  /**
   * Checks that no rule declares override when there is no corresponding inherited rule.
   *
   * @param model
   *          the model
   */
  @Check
  def void checkIllegalOverride(FormatConfiguration model) {
    val overrideRules = Iterables.filter(model.getRules(), IS_OVERRIDE)
    var Iterable<Rule> overrideableRules = Lists.newArrayList()
    val extendedModel = model.getExtendedFormatConfiguration()
    if (extendedModel !== null && !extendedModel.eIsProxy()) {
      overrideableRules = collectRules(extendedModel)
    }
    val overrideableAbstractRuleMap = Maps.newHashMap()
    for (GrammarRule rule : Iterables.filter(overrideableRules, GrammarRule)) {
      overrideableAbstractRuleMap.put(TARGET_RULE.apply(rule), rule)
    }
    // Check GrammarRules
    for (GrammarRule overrideRule : Iterables.filter(overrideRules, GrammarRule)) {
      if (!overrideableAbstractRuleMap.containsKey(TARGET_RULE.apply(overrideRule))) {
        error(OVERRIDE_ILLEGAL_MESSAGE, overrideRule, FormatPackage::Literals::GRAMMAR_RULE__TARGET_RULE, OVERRIDE_ILLEGAL_CODE)
      }
    }
    // Check WildcardRule
    if (!Iterables.isEmpty(Iterables.filter(overrideRules, WildcardRule)) && Iterables.isEmpty(Iterables.filter(overrideableRules, WildcardRule))) {
      error(OVERRIDE_ILLEGAL_MESSAGE, Iterables.filter(overrideRules, WildcardRule).iterator().next(), null, OVERRIDE_ILLEGAL_CODE)
    }
  }

  /**
   * Check that extended configuration's grammar is compatible.
   *
   * @param model
   *          the model
   */
  @Check
  def void checkExtendedGrammarCompatible(FormatConfiguration model) {
    val extendedModel = model.getExtendedFormatConfiguration()
    if (extendedModel === null || extendedModel.eIsProxy()) {
      return
    }
    if (!extendedModel.getTargetGrammar().eIsProxy()) {
      val grammars = Lists.newArrayList(model.getTargetGrammar())
      grammars.addAll(model.getTargetGrammar().getUsedGrammars())
      for (Grammar grammar : grammars) {
        if (extendedModel.getTargetGrammar().getName().equals(grammar.getName())) {
          return
        }
      }
    }
    error("Extended format configuration has incompatible grammar", FormatPackage::Literals::FORMAT_CONFIGURATION__EXTENDED_FORMAT_CONFIGURATION, EXTENDED_GRAMMAR_INCOMPATIBLE_CODE)
  }

  /**
   * Check that identically named parent grammar rules are implemented in the model if the parent configuration implements the rule.
   * If both grammars have an identically named rule then the extending FormatConfiguration must declare formatting if the parent FormatConfiguration does.
   *
   * @param model
   *          the model
   */
  @Check
  def void checkRequiredRulesImplemented(FormatConfiguration model) {
    val extendedModel = model.getExtendedFormatConfiguration();
    if (extendedModel === null || extendedModel.eIsProxy() || model.getTargetGrammar() == extendedModel.getTargetGrammar()) {// NOPMD
      return
    }
    val inheritedRules = Iterables.filter(collectRules(extendedModel), GrammarRule);
    val ruleNames = Sets.newHashSet(Iterables.transform(inheritedRules, new Function<GrammarRule, String>() {
      override apply(GrammarRule from) {
        return from.getTargetRule().getName()
      }
    }))
    for (GrammarRule rule : Iterables.filter(model.getRules(), GrammarRule)) {
      if (rule.getTargetRule() !== null && !rule.getTargetRule().eIsProxy()) {
        ruleNames.remove(rule.getTargetRule().getName())
      }
    }
    for (AbstractRule rule : model.getTargetGrammar().getRules()) {
      if (ruleNames.contains(rule.getName())) {
        error(NLS.bind("Required formatting for rule \"{0}\" missing", rule.getName()), FormatPackage::Literals::FORMAT_CONFIGURATION__TARGET_GRAMMAR, GRAMMAR_RULE_MISSING_CODE, rule.getName())
      }
    }
  }

  /**
   * Check that referenced value is of integer type.
   *
   * @param value
   *          the value
   */
  @Check
  def void checkIntValueConstantReference(IntValue value) {
    if (value.getReference() === null || value.getReference().eIsProxy()) {
      return
    }
    if (value.getReference().getIntValue() === null) {
      error(NLS.bind("Referenced const \"{0}\" is not an integer", value.getReference().getName()), FormatPackage::Literals::INT_VALUE__REFERENCE)
    }
  }

  /**
   * Check that referenced value is of string type.
   *
   * @param value
   *          the value
   */
  @Check
  def void checkStringValueConstantReference(StringValue value) {
    if (value.getReference() === null || value.getReference().eIsProxy()) {
      return
    }
    if (value.getReference().getIntValue() === null) {
      error(NLS.bind("Referenced const \"{0}\" is not a string", value.getReference().getName()), FormatPackage::Literals::STRING_VALUE__REFERENCE)
    }
  }

  /**
   * Check that value is consistent with the (optional) declared type.
   *
   * @param value
   *          the value
   */
  @Check
  def void checkConstantOptionalType(Constant value) {
    if (value.isIntType() && value.getIntValue() === null) {
      error("Value is not an integer", FormatPackage::Literals::CONSTANT__NAME)
    } else if (value.isStringType() && value.getStringValue() === null) {
      error("Value is not a string", FormatPackage::Literals::CONSTANT__NAME)
    }
  }

  /**
   * Collect all rules contained by the given model (including any of its extended configurations).
   *
   * @param model
   *          the model
   * @return all rules
   */
  private def Iterable<Rule> collectRules(FormatConfiguration model) {
    var Iterable<Rule> result = model.getRules()
    val extendedModel = model.getExtendedFormatConfiguration()
    if (extendedModel !== null && !extendedModel.eIsProxy()) {
      result = Iterables.concat(result, collectRules(extendedModel))
    }
    return result;
  }

  /**
   * Verify that there are exactly two grammar elements used in the containing directive if the type of the matcher equals the given MatcherType.
   *
   * @param matcher
   *          the Matcher
   * @param matcherType
   *          the MatcherType
   */
  private def void checkTwoArgumentMatcherType(Matcher matcher, MatcherType matcherType) {
    if (matcher.getType() == matcherType) {
      val directive = EObjectUtil.eContainer(matcher, SpecificDirective)
      if (directive === null || directive.getGrammarElements().size() != 2) {
        error(NLS.bind("\"{0}\" may only be used with exactly two elements", matcherType.getName()), FormatPackage::Literals::MATCHER__TYPE)
      }
    }
  }

  /**
   * Collects all GramarElementReferences, GrammarElementLookups and KeywordPairs contained by a GrammarRule.
   *
   * @param rule
   *          the grammar rule
   * @return Iterator with all grammar element accessors
   */
  private def static Iterator<EObject> collectGrammarElementAccessors(GrammarRule rule) {
    return Iterators.filter(rule.eAllContents(), new Predicate<EObject>() {
      override apply(EObject input) {
        return input instanceof KeywordPair || input instanceof GrammarElementReference || input instanceof GrammarElementLookup
      }
    })
  }

}
