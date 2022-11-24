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
package com.avaloq.tools.ddk.test.ui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.WrappedException;


/**
 * Test Helper Class Test Configuration.
 */
public final class TestConfiguration {

  private TestConfiguration() {
    // utility class
  }

  private static final String SEPARATOR = System.getProperty("file.separator");
  private static final String FILE_NAME = "test.properties";

  private static final Properties PROPERTIES = new Properties();
  private static final Properties SYSTEM_PROPERTIES = System.getProperties();

  static {
    loadProperties();
  }

  /**
   * Load properties.
   */
  private static void loadProperties() {
    URL pluginUrl = null;
    String absolutePath;
    String completePath;
    BufferedInputStream stream;
    try {
      pluginUrl = FileLocator.toFileURL(Activator.getInstallURL());

      absolutePath = new File(pluginUrl.getPath()).getAbsolutePath();
      completePath = absolutePath + SEPARATOR + FILE_NAME;

      stream = new BufferedInputStream(new FileInputStream(completePath));
      PROPERTIES.load(stream);
      stream.close();

    } catch (IOException e) {
      throw new WrappedException("Error during loading properties", e);
    }
  }

  /**
   * Gets the property.
   *
   * @param propertyName
   *          the property name
   * @return the property
   */
  public static String getProperty(final String propertyName) {
    String property;
    if (SYSTEM_PROPERTIES.contains(propertyName)) {
      property = SYSTEM_PROPERTIES.getProperty(propertyName);
    } else {
      property = PROPERTIES.getProperty(propertyName);
    }
    return property;
  }
}
