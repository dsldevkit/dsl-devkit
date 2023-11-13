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
package com.avaloq.tools.ddk.test.core.data;

/**
 * The Class TestData.
 */
public final class TestData {

  /** The Bot prefix. */
  public static final String BOT_PREFIX = "Bot";

  /** The Constant PROPERTY_PREFIX. */
  private static final String PROPERTY_PREFIX = "systemtest";

  /**
   * {@link TestData} should NOT be constructed.
   */
  private TestData() {
  }

  /**
   * Gets the system property indicated by the specified key. The method returns the
   * default value argument if the property is not found.
   * 
   * @param defaultValue
   *          the default value
   * @param propertyKey
   *          the property path name
   * @return the string
   */
  public static String getString(final String defaultValue, final String... propertyKey) {
    StringBuilder propertyName = new StringBuilder(PROPERTY_PREFIX);
    for (String property : propertyKey) {
      propertyName.append('.');
      propertyName.append(property);
    }
    return System.getProperty(propertyName.toString(), defaultValue);
  }

}
