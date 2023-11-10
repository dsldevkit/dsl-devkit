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

package com.avaloq.tools.ddk.xtext.ide.contentAssist; // NOPMD PACKAGE CASE

import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.ParserRule;


public final class ParserRuleUtil {
  private ParserRuleUtil() {
    // prevent construction of util class.
  }

  /**
   * Determines whether a ParserRule is a datatype rule containing keywords only.
   *
   * @param rule
   *          the rule to be tested
   * @return true if datatype rule contains keywords only
   */
  public static boolean isEnumLikeDatatypeRule(final ParserRule rule) {
    if (!GrammarUtil.isDatatypeRule(rule) || !GrammarUtil.containedRuleCalls(rule).isEmpty()) {
      return false;
    }
    return !GrammarUtil.containedKeywords(rule).isEmpty();
  }

  /**
   * Determines whether a ParserRule is a datatype rule containing RuleCalls only.
   *
   * @param rule
   *          the rule to be tested
   * @return true if datatype rule contains RuleCalls only
   */
  public static boolean isDelegatingDatatypeRule(final ParserRule rule) {
    if (!GrammarUtil.isDatatypeRule(rule) || !GrammarUtil.containedKeywords(rule).isEmpty()) {
      return false;
    }
    return !GrammarUtil.containedRuleCalls(rule).isEmpty();
  }

}
