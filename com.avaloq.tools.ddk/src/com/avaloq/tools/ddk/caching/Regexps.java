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
package com.avaloq.tools.ddk.caching;

import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

import com.google.common.collect.MapMaker;


/**
 * Class containing utility functions for regular expressions.
 */
@SuppressWarnings("nls")
public final class Regexps {

  /** Our "glob"-expressions have two wildcard characters, signifying zero or more (*) or zero or one (?) occurrence(s) of any character. */
  public static final char WILDCARD_ANY = '*';
  public static final char WILDCARD_ONE = '?';

  /** Precompiled patterns for turning a glob expression into a regular expression. */
  private static final Pattern GLOB_ESCAPE_PATTERN = Pattern.compile("([{}()|.+^$]|\\[|\\]|\\\\)"); //$NON-NLS-1$
  private static final Pattern GLOB_WILDCARD_PATTERN = Pattern.compile("\\" + WILDCARD_ANY); //$NON-NLS-1$

  /**
   * Private constructor to prevent instantiation.
   */
  private Regexps() {
    // prevent instantiation
  }

  private record Pair(String glob, Boolean ignoreCase) {
  }

  /** Cache of already translated glob patterns. */
  private static final ConcurrentMap<Pair, Pattern> GLOB_REGEXP_CACHE = new MapMaker().weakKeys().makeMap();

  /**
   * Returns a regular expression corresponding to the given glob pattern. Case insensitive.
   *
   * @param glob
   *          glob to get regexp for
   * @return regexp matching glob
   */
  public static Pattern fromGlob(final String glob) {
    return fromGlob(glob, true);
  }

  /**
   * Returns a regular expression corresponding to the given glob pattern.
   *
   * @param glob
   *          glob to get regexp for
   * @param ignoreCase
   *          ignore case
   * @return regexp matching glob
   */
  public static Pattern fromGlob(final String glob, final boolean ignoreCase) {
    return GLOB_REGEXP_CACHE.computeIfAbsent(new Pair(glob, ignoreCase), from -> {
      // Escape any special regexp characters except our own WILDCARD characters '*' and '?'
      String pattern = GLOB_ESCAPE_PATTERN.matcher(from.glob()).replaceAll("\\\\$1");
      pattern = pattern.replace(WILDCARD_ONE, '.');
      pattern = GLOB_WILDCARD_PATTERN.matcher(pattern).replaceAll(".*");
      return from.ignoreCase() ? Pattern.compile('^' + pattern + '$', Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE) : Pattern.compile('^' + pattern + '$');
    });
  }

}
