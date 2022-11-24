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
package com.avaloq.tools.ddk.xtext.valid.generator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Utility classes for the generation of Check Documentation.
 */
public class DocumentationUtil {

  protected DocumentationUtil() {
    // to keep Checkstyle happy..
  }

  /**
   * Returns a human-readable version of the given message in which all binding placeholders are replaced by "...".
   *
   * @param message
   *          an error message
   * @return the human-readable version
   */
  public static String toPrintableMessage(final String message) {
    return replace(message, "\\{[0-9]+\\}", "..."); //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * Replace the localization patterns ({0}, {1}, ...) with "..." inside the
   * source string.
   *
   * @param source
   *          the source
   * @param searchPattern
   *          the search pattern
   * @param replacementPattern
   *          the replacement pattern
   * @return the string
   */
  private static String replace(final String source, final String searchPattern, final String replacementPattern) {
    // Compile regular expression
    final Pattern pattern = Pattern.compile(searchPattern);

    // Replace all occurrences of pattern in input
    final Matcher matcher = pattern.matcher(source);
    return matcher.replaceAll(replacementPattern);
  }
}
