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
package com.avaloq.tools.ddk.test.core.util;

import org.eclipse.emf.common.util.WrappedException;


/**
 * The Class Reflect.
 */
public final class Reflect {

  private Reflect() {
    // Utility Class
  }

  /**
   * Retrieves the name of a calling method A that called a method B.
   * This method must be called from method B.
   *
   * @return the calling method
   */
  public static String getCallingMethod() {
    return getCaller(1).getMethodName();
  }

  /**
   * Retrieves the name of a calling class A that called a method on class B.
   * This method must be called from class B.
   *
   * @return the calling class
   */
  public static String getCallingClass() {
    return getCaller(1).getClassName();
  }

  /**
   * Retrieves the caller A of a called method B.
   * This method must be directly called from method B.
   *
   * @return the caller
   */
  public static StackTraceElement getCaller() {
    return getCaller(1);
  }

  /**
   * Gets the caller.
   *
   * @param offset
   *          the offset
   * @return the caller
   */
  // CHECKSTYLE:OFF
  public static StackTraceElement getCaller(final int offset) {
    if (offset < 0) {
      throw new IllegalArgumentException("Offset must be >= 0");
    }
    StackTraceElement[] elms = Thread.currentThread().getStackTrace();
    if (elms.length < 4 + offset) {
      throw new WrappedException("Caller could not be identified", null);
    }
    // elms[0] = Thread.getStackTrace()
    // elms[1] = Me
    // elms[2] = Calling Method
    // elms[3] = Caller of Calling Method
    return elms[3 + offset];
  }
  // CHECKSTYLE:ON
}
