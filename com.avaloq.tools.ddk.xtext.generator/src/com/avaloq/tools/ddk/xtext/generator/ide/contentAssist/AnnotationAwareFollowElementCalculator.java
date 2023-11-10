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

package com.avaloq.tools.ddk.xtext.generator.ide.contentAssist; // NOPMD PackageCase

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.ide.editor.contentassist.antlr.FollowElementCalculator;

import com.avaloq.tools.ddk.xtext.generator.util.ParserRuleUtil;
import com.avaloq.tools.ddk.xtext.parser.ISemanticPredicates;
import com.google.common.collect.Sets;
import com.google.inject.Inject;


public class AnnotationAwareFollowElementCalculator extends FollowElementCalculator {
  @Inject
  private ISemanticPredicates keywordPredicates;

  @Override
  public Boolean caseParserRule(final ParserRule object) {
    // copy of super class, but also descend into rules that contain keyword rules, rules that are just KeyWords, and rules that only delegate to other rules.
    if (GrammarUtil.isDatatypeRule(object) && !canContainKeywordRule(object) && !ParserRuleUtil.isEnumLikeDatatypeRule(object)
        && !ParserRuleUtil.isDelegatingDatatypeRule(object)) {
      return Boolean.FALSE;
    }
    return doSwitch(object.getAlternatives());
  }

  private boolean canContainKeywordRule(final ParserRule rule) {
    Set<AbstractRule> visitedRules = Sets.newHashSet(rule);
    Deque<RuleCall> ruleCallsToVisit = new ArrayDeque<>(EcoreUtil2.getAllContentsOfType(rule, RuleCall.class));
    while (!ruleCallsToVisit.isEmpty()) {
      AbstractRule referencedRule = ruleCallsToVisit.pollFirst().getRule();
      if (visitedRules.add(referencedRule)) {
        if (keywordPredicates.isKeywordRule(referencedRule.getName())) {
          return true;
        }
        ruleCallsToVisit.addAll(EcoreUtil2.getAllContentsOfType(referencedRule, RuleCall.class));
      }
    }
    return false;
  }

}
