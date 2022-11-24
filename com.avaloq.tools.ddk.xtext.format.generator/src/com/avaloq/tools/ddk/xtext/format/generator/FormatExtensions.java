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
package com.avaloq.tools.ddk.xtext.format.generator;

import java.util.List;
import java.util.Set;

import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.TerminalRule;

import com.avaloq.tools.ddk.xtext.format.format.Constant;
import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.format.format.GrammarRule;
import com.avaloq.tools.ddk.xtext.format.format.Rule;
import com.avaloq.tools.ddk.xtext.format.format.WildcardRule;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


/**
 * Utility methods used by the FormatFragment generator.
 */
public final class FormatExtensions {
  private FormatExtensions() {
    // prevent instantiation
  }

  /**
   * Gets the all GrammarRules in the inheritance hierarchy excluding overridden rules.
   *
   * @param model
   *          the model
   * @param targetClass
   *          the target class
   * @return all unique grammar rules
   */
  private static List<GrammarRule> getAllUniqueGrammarRules(final FormatConfiguration model, final Class<?> targetClass) {
    final Set<AbstractRule> grammarElements = Sets.newHashSet();
    final Set<String> grammarElementNames = Sets.newHashSet(); // ensure that identically named rules in a parent grammar are not returned
    List<GrammarRule> allRules = getAllGrammarRules(model);
    List<GrammarRule> result = Lists.newArrayList();
    for (int i = allRules.size() - 1; i > -1; i--) {
      GrammarRule rule = allRules.get(i);
      if (grammarElements.add(rule.getTargetRule()) && targetClass.isInstance(rule.getTargetRule())
          && grammarElementNames.add(rule.getTargetRule().getName())) {
        result.add(0, rule);
      }
    }
    return result;
  }

  /**
   * Gets all GrammarRules in the inheritance hierarchy, including overridden rules.
   *
   * @param model
   *          the model
   * @return the all grammar rules
   */
  private static List<GrammarRule> getAllGrammarRules(final FormatConfiguration model) {
    List<GrammarRule> allRules = Lists.newArrayList();
    if (model.getExtendedFormatConfiguration() != null && !model.getExtendedFormatConfiguration().eIsProxy()) {
      allRules.addAll(getAllGrammarRules(model.getExtendedFormatConfiguration()));
    }
    Predicate<Rule> resolvedGrammarRules = new Predicate<Rule>() {
      @Override
      public boolean apply(final Rule input) {
        return input instanceof GrammarRule && ((GrammarRule) input).getTargetRule() != null && !((GrammarRule) input).getTargetRule().eIsProxy();
      }
    };
    for (Rule rule : Iterables.filter(model.getRules(), resolvedGrammarRules)) {
      allRules.add((GrammarRule) rule);
    }
    return allRules;
  }

  /**
   * Gets the ParserRule GrammarRules, excluding overridden ones.
   *
   * @param model
   *          the model
   * @return the parser rules
   */
  public static List<GrammarRule> getParserRules(final FormatConfiguration model) {
    return getAllUniqueGrammarRules(model, ParserRule.class);
  }

  /**
   * Gets the TerminalRule GrammarRules, excluding overridden ones.
   *
   * @param model
   *          the model
   * @return the terminal rules
   */
  public static List<GrammarRule> getTerminalRules(final FormatConfiguration model) {
    return getAllUniqueGrammarRules(model, TerminalRule.class);
  }

  /**
   * Gets the EnumRule GrammarRules, excluding overridden ones.
   *
   * @param model
   *          the model
   * @return the enum rules
   */
  public static List<GrammarRule> getEnumRules(final FormatConfiguration model) {
    return getAllUniqueGrammarRules(model, EnumRule.class);
  }

  /**
   * Gets the wildcard rule.
   *
   * @param model
   *          the model
   * @return the wildcard rule
   */
  public static WildcardRule getWildcardRule(final FormatConfiguration model) {
    Iterable<WildcardRule> filteredRules = Iterables.filter(model.getRules(), WildcardRule.class);
    if (!Iterables.isEmpty(filteredRules)) {
      return filteredRules.iterator().next();
    } else if (model.getExtendedFormatConfiguration() != null && !model.getExtendedFormatConfiguration().eIsProxy()) {
      return getWildcardRule(model.getExtendedFormatConfiguration());
    } else {
      return null;
    }
  }

  /**
   * Gets all constants found in the inheritance hierarchy.
   *
   * @param model
   *          the model
   * @return the all constants
   */
  public static List<Constant> getAllConstants(final FormatConfiguration model) {
    List<Constant> result = Lists.newArrayList();
    if (model.getExtendedFormatConfiguration() != null && !model.getExtendedFormatConfiguration().eIsProxy()) {
      result.addAll(getAllConstants(model.getExtendedFormatConfiguration()));
    }
    result.addAll(model.getConstants());
    return result;
  }
}
