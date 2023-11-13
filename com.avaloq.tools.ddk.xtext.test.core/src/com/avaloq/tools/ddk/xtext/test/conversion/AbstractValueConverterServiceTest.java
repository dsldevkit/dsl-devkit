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
package com.avaloq.tools.ddk.xtext.test.conversion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.conversion.ValueConverterException;

import com.avaloq.tools.ddk.xtext.test.AbstractXtextTest;
import com.avaloq.tools.ddk.xtext.grammar.KeywordCollector;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


/**
 * Base class for testing a language {@link IValueConverterService} implementation.
 */
public abstract class AbstractValueConverterServiceTest extends AbstractXtextTest {
  /** ID regular expression does not allow digits as first character. */
  public static final String INVALID_ID_NUMBER = "1something";
  /** ID regular expression does not allow #. */
  public static final String INVALID_ID_NONALPHANUM = "#";
  /** ID regular expression does not allow any '.'. */
  public static final String INVALID_ID_WITHDOT = "someValidIdentifier.something";
  protected static final ImmutableSet<String> INVALID_IDENTIFIERS = ImmutableSet.of(
// @Format-Off
    INVALID_ID_NUMBER,
    INVALID_ID_NONALPHANUM,
    INVALID_ID_WITHDOT
// @Format-On
  );

  private final Map<AbstractRule, KeywordCollector> keywordCollectors = Maps.newHashMap();

  /**
   * Returns the {@link IGrammarAccess}.
   * 
   * @return the {@link IGrammarAccess}, never {@code null}
   */
  protected IGrammarAccess getGrammarAccess() {
    final IGrammarAccess grammarAccess = getXtextTestUtil().get(IGrammarAccess.class);
    assertNotNull("The IGrammarAccess must be registered in order to test the IValueConverterService.", grammarAccess);
    return grammarAccess;
  }

  /**
   * Returns whether keyword case is ignored.
   * <p>
   * <em>Note</em>: The default is {@code true}, i.e. keyword case is ignored.
   * </p>
   * 
   * @return whether keyword case is ignored
   */
  protected boolean isIgnoreCase() {
    return true;
  }

  /**
   * Gets or creates the {@link KeywordCollector} instance for the given {@link AbstractRule}.
   * 
   * @param rule
   *          the {@link AbstractRule}, must not be {@code null}
   * @return the {@link KeywordCollector} instance for the given {@link AbstractRule}, never {@code null}
   */
  protected KeywordCollector getKeywordCollector(final AbstractRule rule) {
    KeywordCollector keywordCollector = keywordCollectors.get(rule);
    if (keywordCollector == null) {
      keywordCollector = new KeywordCollector(rule, isIgnoreCase());
      keywordCollectors.put(rule, keywordCollector);
    }
    return keywordCollector;
  }

  /**
   * Returns the {@link IValueConverterService}.
   * 
   * @return the {@link IValueConverterService}, never {@code null}
   */
  protected IValueConverterService getValueConverterService() {
    final IValueConverterService valueConverterService = getXtextTestUtil().get(IValueConverterService.class);
    assertNotNull("The IValueConverterService must be registered in order to test it.", valueConverterService);
    return valueConverterService;
  }

  /**
   * Asserts that the value-to-string conversion resulted in a string that is equal to the specified input value.
   * 
   * @param rule
   *          the rule used for the value-to-string conversion, must not be {@code null}
   * @param value
   *          the value to be converted, must not be {@code null}
   */
  protected void assertToStringConvertedEquals(final AbstractRule rule, final String value) {
    final String actual = getValueConverterService().toString(value, rule.getName());
    assertEquals("Converted value must be equal to input value.", value, actual);
  }

  /**
   * Asserts that the value-to-string conversion resulted in a string that is equal to the specified input value surrounded by double-quotes.
   * 
   * @param rule
   *          the rule used for the value-to-string conversion, must not be {@code null}
   * @param value
   *          the value to be converted, must not be {@code null}
   */
  protected void assertToStringConvertedQuoted(final AbstractRule rule, final String value) {
    final String actual = getValueConverterService().toString(value, rule.getName());
    assertEquals("Quotes expected", '"' + value + '"', actual);
  }

  /**
   * Asserts that the value-to-string conversion for all the specified values results in strings that are equal to the specified input values surrounded by
   * double-quotes.
   * 
   * @param rule
   *          the rule used for the value-to-string conversion, must not be {@code null}
   * @param values
   *          values to be converted, must not be {@code null}
   */
  protected void assertToStringConvertedQuoted(final AbstractRule rule, final Iterable<String> values) {
    List<String> failures = Lists.newArrayList();
    for (String value : values) {
      String actual = getValueConverterService().toString(value, rule.getName());
      if (!actual.equals('"' + value + '"')) {
        failures.add(value);
      }
    }
    if (!failures.isEmpty()) {
      fail("All specified values must be quoted. The following invalid identifiers were not enquoted: " + Joiner.on(", ").join(failures));
    }
  }

  /**
   * Asserts that the value-to-string conversion for the keywords listed by the specified rule (or one of rules it calls) leaves them unchanged.
   * 
   * @param rule
   *          the rule used for the value-to-string conversion and whose keywords are tested, must not be {@code null}
   */
  protected void assertToStringLeavesRuleKeywordsUnchanged(final AbstractRule rule) {
    assertToStringLeavesRuleKeywordsUnchanged(rule, null);
  }

  /**
   * Asserts that the value-to-string conversion for the keywords listed by the specified rule (or one of rules it calls) leaves them unchanged.
   * 
   * @param rule
   *          the rule used for the value-to-string conversion and whose keywords are tested, must not be {@code null}
   * @param exceptions
   *          the keywords used in the rule that are not valid by themselves (e.g. '.' or other special characters that cannot stand alone), may be {@code null}
   */
  protected void assertToStringLeavesRuleKeywordsUnchanged(final AbstractRule rule, final Collection<String> exceptions) {
    final Collection<String> keywords = getKeywordCollector(rule).getKeywords();
    if (exceptions != null) {
      keywords.removeAll(exceptions);
    }
    for (final String keyword : keywords) {
      assertToStringConvertedEquals(rule, keyword);
    }
  }

  /**
   * Asserts that the value-to-string conversion for values that do not match the ID terminal rule regular expression results in the values surrounded by
   * double-quotes.
   * 
   * @param rule
   *          the rule used for the value-to-string conversion, must not be {@code null}
   */
  protected void assertToStringNonIDQuoted(final AbstractRule rule) {
    assertToStringConvertedQuoted(rule, INVALID_IDENTIFIERS);
  }

  /**
   * Asserts that the value-to-string conversion for all of the specified values results in a {@link ValueConverterException} thrown.
   * 
   * @param rule
   *          the rule used for the value-to-string conversion, must not be {@code null}
   * @param valueList
   *          the list of values to be converted, must not be {@code null}
   */
  protected void assertToStringFails(final AbstractRule rule, final Iterable<String> valueList) {
    List<String> failures = Lists.newArrayList(valueList);
    for (String value : valueList) {
      try {
        getValueConverterService().toString(value, rule.getName());
      } catch (ValueConverterException e) {
        failures.remove(value);
      }
    }
    if (!failures.isEmpty()) {
      fail("The following invalid identifiers did not cause a ValueConverterException: " + Joiner.on(", ").join(failures));
    }
  }

  /**
   * Asserts that the value-to-string conversion for values that do not match the ID terminal rule regular expression results in a
   * {@link ValueConverterException} thrown.
   * 
   * @param rule
   *          the rule used for the value-to-string conversion, must not be {@code null}
   */
  protected void assertToStringNonIDFails(final AbstractRule rule) {
    assertToStringFails(rule, INVALID_IDENTIFIERS);
  }

  /**
   * Asserts that the value-to-string conversion for values that are part of the rule language but are not listed by the rule results in a
   * {@link ValueConverterException} thrown.
   * 
   * @param rule
   *          the rule used for the value-to-string conversion, must not be {@code null}
   */
  protected void assertToStringOtherKeywordsFail(final AbstractRule rule) {
    assertToStringFails(rule, getKeywordCollector(rule).getOtherKeywords());
  }

  /**
   * Asserts that the value-to-string conversion for values that are part of the rule language but are not listed by the rule results in quoted strings.
   * 
   * @param rule
   *          the rule used for the value-to-string conversion, must not be {@code null}
   */
  protected void assertToStringOtherKeywordsQuoted(final AbstractRule rule) {
    assertToStringConvertedQuoted(rule, getKeywordCollector(rule).getOtherKeywords());
  }

  /**
   * Asserts that the value-to-string conversion for values that keywords listed by the rule leaves them unchanged while other language keywords or invalid IDs
   * result in a {@link ValueConverterException} thrown.
   * 
   * @param rule
   *          the rule used for the value-to-string conversion, must not be {@code null}
   */
  protected void assertToStringOnlyListedKeywordsAllowed(final AbstractRule rule) {
    assertToStringLeavesRuleKeywordsUnchanged(rule);
    assertToStringOtherKeywordsFail(rule);
    assertToStringNonIDFails(rule);
  }

  /**
   * Asserts that boolean values convert to "true" for {@link Boolean#TRUE} and "false" for {@link Boolean#FALSE}.
   * 
   * @param rule
   *          the rule used for the value-to-string conversion, must not be {@code null}
   */
  protected void assertBooleanValueToString(final AbstractRule rule) {
    final String actualTrue = getValueConverterService().toString(Boolean.TRUE, rule.getName());
    assertEquals("Converted value must be \"true\".", Boolean.TRUE.toString(), actualTrue);
    final String actualFalse = getValueConverterService().toString(Boolean.FALSE, rule.getName());
    assertEquals("Converted value must be \"false\".", Boolean.FALSE.toString(), actualFalse);
    // Check that the converter is for the correct type
    try {
      getValueConverterService().toString("false", rule.getName());
      throw new AssertionError("Converter must not accept String.");
    } catch (final ClassCastException expected) {
      // expected; do nothing
    } catch (final ValueConverterException expected) {
      // expected; do nothing
    }
  }
}
