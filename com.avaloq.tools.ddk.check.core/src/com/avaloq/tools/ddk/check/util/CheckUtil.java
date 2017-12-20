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
package com.avaloq.tools.ddk.check.util;

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
    StringBuilder result = new StringBuilder(source.charAt(0));
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
}
