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
package com.avaloq.tools.ddk.xtext.grammar;

import java.util.Locale;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.util.XtextSwitch;

import com.google.common.collect.Sets;


/**
 * Collector which finds all the keywords for a given {@link AbstractRule} of a {@link org.eclipse.xtext.Grammar}.
 * <p>
 * <em>Note</em>: Only data type rules are supported at the moment.
 * </p>
 */
public class KeywordCollector extends XtextSwitch<Boolean> {
  private static final Logger LOGGER = Logger.getLogger(KeywordCollector.class);
  private final boolean ignoreCase;
  private final AbstractRule rule;
  private Set<String> ruleKeywords;
  private Set<String> allKeywords;
  private Set<String> otherKeywords;

  /**
   * Creates a new instance of {@link KeywordCollector}.
   * <p>
   * <em>Note</em>: By default, keywords are not case sensitive.
   * </p>
   */
  public KeywordCollector(final AbstractRule rule) {
    this(rule, true);
  }

  /**
   * Creates a new instance of {@link KeywordCollector}.
   *
   * @param rule
   *          the {@link AbstractRule}, must not be {@code null}
   * @param ignoreCase
   *          whether character case of keywords is ignored (i.e. keywords are not case sensitive)
   */
  public KeywordCollector(final AbstractRule rule, final boolean ignoreCase) {
    this.rule = rule;
    this.ignoreCase = ignoreCase;
  }

  /**
   * Returns the collected keywords of the {@link AbstractRule}.
   *
   * @return the collected keywords, never {@code null}
   */
  public Set<String> getKeywords() {
    if (ruleKeywords == null) {
      ruleKeywords = Sets.newHashSet();
      final Boolean result = doSwitch(rule);
      if (!result) {
        final String message = NLS.bind("Could not collect keywords for rule ''{0}''.", rule.getName()); //$NON-NLS-1$
        LOGGER.error(message);
        throw new IllegalStateException(message);
      }
    }
    return ruleKeywords;
  }

  /**
   * Returns all other keywords of the grammar.
   *
   * @return all other keywords of the grammar, never {@code null}
   */
  public Set<String> getOtherKeywords() {
    if (otherKeywords == null) {
      otherKeywords = Sets.difference(getAllKeywords(), getKeywords());
    }
    return otherKeywords;
  }

  /**
   * Returns all keywords of the grammar.
   *
   * @return all keywords of the grammar, never {@code null}
   */
  public Set<String> getAllKeywords() {
    if (allKeywords == null) {
      allKeywords = GrammarUtil.getAllKeywords(GrammarUtil.getGrammar(rule));
      if (ignoreCase) {
        final Set<String> lowerCaseKeywords = Sets.newHashSet();
        for (final String keyword : allKeywords) {
          lowerCaseKeywords.add(keyword.toLowerCase(Locale.ENGLISH));
        }
        allKeywords = lowerCaseKeywords;
      }
    }
    return allKeywords;
  }

  /** {@inheritDoc} */
  @Override
  public Boolean caseGroup(final Group group) {
    for (final AbstractElement element : group.getElements()) {
      if (!doSwitch(element)) {
        return false;
      }
    }
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public Boolean caseParserRule(final ParserRule object) {
    if (!GrammarUtil.isDatatypeRule(object)) {
      return false;
    }
    if (object.getType().getClassifier() == EcorePackage.Literals.ESTRING) {
      return doSwitch(object.getAlternatives());
    }
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public Boolean caseTerminalRule(final TerminalRule object) {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public Boolean caseAlternatives(final Alternatives object) {
    for (final AbstractElement element : object.getElements()) {
      if (!doSwitch(element)) {
        return false;
      }
    }
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public Boolean caseRuleCall(final RuleCall object) {
    return doSwitch(object.getRule());
  }

  /** {@inheritDoc} */
  @Override
  public Boolean caseKeyword(final Keyword keyword) {
    final String keywordText = keyword.getValue();
    ruleKeywords.add(ignoreCase ? keywordText.toLowerCase(Locale.ENGLISH) : keywordText);
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public Boolean defaultCase(final EObject object) {
    return false;
  }
}
