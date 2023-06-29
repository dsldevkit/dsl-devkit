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
package com.avaloq.tools.ddk.check.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import com.avaloq.tools.ddk.check.runtime.registry.ICheckValidatorStandaloneSetup;


/**
 * Utility Class for Check.
 */
public final class CheckUtil {

  private CheckUtil() {
    // private initializer
  }

  /**
   * Converts a string into an identifier by removing all characters that are not
   * allowed.
   *
   * @param source
   *          any string
   * @return a string stripped from illegal characters
   */
  public static String toIdentifier(final String source) {
    if (source == null) {
      return null;
    } else if (source.length() == 0 || !Character.isJavaIdentifierStart(source.charAt(0))) {
      return ""; //$NON-NLS-1$
    }
    StringBuilder result = new StringBuilder();
    result.append(source.charAt(0));
    boolean space = false;
    for (Character c : source.substring(1).toCharArray()) {
      if (Character.isJavaIdentifierPart(c)) {
        result.append((space) ? Character.toUpperCase(c) : c);
      }
      space = Character.isWhitespace(c);
    }
    return result.toString();
  }

  /**
   * Gets the service registry class name used to create a text file in META-INF/services.
   *
   * @return the service registry class name
   */
  public static String serviceRegistryClassName() {
    return ICheckValidatorStandaloneSetup.class.getName();
  }

  /**
   * Convert the given issue name into an issue code name by removing any underscores and
   * making camel case.
   *
   * @param issueName
   *          the name of the issue to convert.
   * @return the issue code name for the given issue name.
   */
  public static String toIssueCodeName(final String issueName) {
    return StringUtils.remove(WordUtils.capitalizeFully(issueName, new char[] {'_'}), '_');
  }
}
