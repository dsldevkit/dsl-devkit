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
package com.avaloq.tools.ddk.xtext.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * String utilities.
 */
public final class Strings {

  private static final int EXPECTED_MAXIMUM_SEGMENTS = 4;

  private Strings() {
    // nothing to do
  }

  /**
   * Splits a string around matches of the given delimiter string.
   * <p>
   * This method works as if by invoking the {@link com.google.common.base.Splitter#split(CharSequence) split} method on a Splitter created using
   * {@link com.google.common.base.Splitter#on(String)} for {@code delimiter}. In contrast this method returns an up-front filled List instead of a lazily
   * populated Iterable, which makes it perform better when all elements need to be accessed.
   * <p>
   * For delimiters of length 1 it is preferred to use {@link #split(String, char)} instead.
   * <p>
   * TODO remove once https://bugs.eclipse.org/bugs/show_bug.cgi?id=369411 is available (Xtext 2.3)
   *
   * @param value
   *          the string to split
   * @param delimiter
   *          the delimiting string (e.g. "::")
   * @return the list of strings computed by splitting the string around matches of the given delimiter
   */
  public static List<String> split(final String value, final String delimiter) {
    List<String> result = new ArrayList<String>(EXPECTED_MAXIMUM_SEGMENTS);
    int lastIndex = 0;
    int index = value.indexOf(delimiter, lastIndex);
    while (index != -1) {
      result.add(value.substring(lastIndex, index));
      lastIndex = index + delimiter.length();
      index = value.indexOf(delimiter, lastIndex);
    }
    result.add(value.substring(lastIndex));
    return result;
  }

  /**
   * Splits a string around matches of the given delimiter character.
   * <p>
   * This method works as if by invoking the {@link com.google.common.base.Splitter#split(CharSequence) split} method on a Splitter created using
   * {@link com.google.common.base.Splitter#on(char)} for {@code delimiter}. In contrast this method returns an up-front filled List instead of a lazily
   * populated Iterable, which makes it perform better when all elements need to be accessed.
   * <p>
   * TODO remove once https://bugs.eclipse.org/bugs/show_bug.cgi?id=369411 is available (Xtext 2.3)
   *
   * @param value
   *          the string to split
   * @param delimiter
   *          the delimiting character (e.g. '.')
   * @return the list of strings computed by splitting the string around matches of the given delimiter
   */
  public static List<String> split(final String value, final char delimiter) {
    List<String> result = new ArrayList<String>(EXPECTED_MAXIMUM_SEGMENTS);
    int lastIndex = 0;
    int index = value.indexOf(delimiter, lastIndex);
    while (index != -1) {
      result.add(value.substring(lastIndex, index));
      lastIndex = index + 1;
      index = value.indexOf(delimiter, lastIndex);
    }
    result.add(value.substring(lastIndex));
    return result;
  }

  /**
   * Returns the {@link String} {@code s} with an {@link Character#isUpperCase(char) upper case} first character. This
   * function is null-safe.
   *
   * @param s
   *          the string that should get an upper case first character. May be {@code null}.
   * @param locale
   *          the {@link Locale} that determines the case transformation rules for this locale. must not be {@code null}.
   * @return the {@link String} {@code s} with an upper case first character or {@code null} if the input
   *         {@link String} {@code s} was {@code null}.
   */
  public static String toFirstUpper(final String s, final Locale locale) {
    if (s == null || s.length() == 0) {
      return s;
    }
    if (Character.isUpperCase(s.charAt(0))) {
      return s;
    }
    if (s.length() == 1) {
      return s.toUpperCase(locale);
    }
    return s.substring(0, 1).toUpperCase(locale) + s.substring(1);
  }

  /**
   * Returns the {@link String} {@code s} with an {@link Character#isLowerCase(char) lower case} first character. This
   * function is null-safe.
   *
   * @param s
   *          the string that should get an lower case first character. May be {@code null}.
   * @param locale
   *          the {@link Locale} that determines the case transformation rules for this locale. must not be {@code null}.
   * @return the {@link String} {@code s} with an lower case first character or {@code null} if the input
   *         {@link String} {@code s} was {@code null}.
   */
  public static String toFirstLower(final String s, final Locale locale) {
    if (s == null || s.length() == 0) {
      return s;
    }
    if (Character.isLowerCase(s.charAt(0))) {
      return s;
    }
    if (s.length() == 1) {
      return s.toLowerCase(locale);
    }
    return s.substring(0, 1).toLowerCase(locale) + s.substring(1);
  }

}

