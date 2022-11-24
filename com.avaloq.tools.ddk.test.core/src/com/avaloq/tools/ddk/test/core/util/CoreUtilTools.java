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
package com.avaloq.tools.ddk.test.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

import org.apache.commons.lang.RandomStringUtils;
import org.eclipse.core.runtime.Assert;


/**
 * Test Helper Class CoreUtilTools.
 */
public final class CoreUtilTools {

  private static final String HOST_NAME;

  static {
    String hostname = "host";
    try {
      hostname = InetAddress.getLocalHost().getHostName().replace('.', '_');
    } catch (UnknownHostException e) {
      hostname = "unknownhost" + System.currentTimeMillis();
    } finally {
      HOST_NAME = hostname;
    }
  }

  private static final int MILLISEC_ONE_DAY = 86400000;

  private CoreUtilTools() {
    // Utility Class
  }

  /**
   * Generates a unique name to use in tests.
   *
   * @return the unique name
   */
  public static String genName() {
    return "Bot" + System.currentTimeMillis();
  }

  /**
   * Generates a unique name to use in tests.
   *
   * @param namePrefix
   *          a prefix for the name
   * @return the unique name
   */
  public static String generateUniqueName(final String namePrefix) {
    return namePrefix + "_" + HOST_NAME + "_" + System.currentTimeMillis();
  }

  /**
   * Creates a random alphanumeric (single case) string whose length is the number of characters specified.
   * The first character is ensured to be alphabetic.
   *
   * @param count
   *          the length of random string to create
   * @return the random string
   */
  public static String randomAlphanumericString(final int count) {
    StringBuilder stringBuilder = new StringBuilder(RandomStringUtils.randomAlphabetic(1));
    if (count > 1) {
      stringBuilder.append(RandomStringUtils.randomAlphanumeric(count - 1));

    }
    String string = stringBuilder.toString();
    Assert.isNotNull(string, "string"); //$NON-NLS-1$
    return string.toUpperCase(Locale.ENGLISH);
  }

  /**
   * Returns the amount of millis of one day.
   *
   * @return the millis of one day
   */
  public static int oneDay() {
    return MILLISEC_ONE_DAY; // 1 day
  }

  /**
   * Waiting.
   *
   * @param milliSeconds
   *          the milli seconds
   */
  public static void waiting(final int milliSeconds) {
    long t0;
    long t1;
    t0 = System.currentTimeMillis();

    do {
      t1 = System.currentTimeMillis();
    } while (t1 - t0 < milliSeconds);
  }

}
