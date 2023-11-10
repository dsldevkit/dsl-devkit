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

import java.util.Collection;

import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.ide.editor.contentassist.IFollowElementAcceptor;
import org.eclipse.xtext.ide.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.FollowElementCalculator;
import org.eclipse.xtext.ide.editor.contentassist.antlr.FollowElementComputer;

import com.avaloq.tools.ddk.xtext.generator.util.ParserRuleUtil;
import com.avaloq.tools.ddk.xtext.parser.ISemanticPredicates;
import com.google.inject.Inject;
import com.google.inject.Provider;


public class AnnotationAwareFollowElementComputer extends FollowElementComputer {
  @Inject
  private Provider<FollowElementCalculator> feCalculatorProvider;
  @Inject
  private ISemanticPredicates keywordPredicates;

  @Override
  public void computeFollowElements(final Collection<FollowElement> followElements, final IFollowElementAcceptor followElementAcceptor) {
    // copy of super class but initialisation of calculator moved to protected method.
    FollowElementCalculator calculator = feCalculatorProvider.get();
    initialiseCalculator(calculator, followElementAcceptor);
    for (FollowElement element : followElements) {
      computeFollowElements(calculator, element);
    }
  }

  protected void initialiseCalculator(final FollowElementCalculator calculator, final IFollowElementAcceptor followElementAcceptor) {
    // copied from super class but calls to keyword rules are also accepted.
    calculator.setAcceptor(element -> {
      ParserRule rule = GrammarUtil.containingParserRule(element);
      if (rule == null || !GrammarUtil.isDatatypeRule(rule) || isKeywordRuleCall(element) || ParserRuleUtil.isEnumLikeDatatypeRule(rule)) {
        followElementAcceptor.accept(element);
      }
    });
  }

  private boolean isKeywordRuleCall(final AbstractElement element) {
    if (element instanceof RuleCall) {
      RuleCall ruleCall = (RuleCall) element;
      return keywordPredicates.isKeywordRule(ruleCall.getRule().getName());
    }
    return false;
  }

}
