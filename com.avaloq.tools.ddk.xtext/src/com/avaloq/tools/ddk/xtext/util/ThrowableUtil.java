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

package com.avaloq.tools.ddk.xtext.util;

public final class ThrowableUtil {

  public static final int STACK_TRACE_LIMIT = 10;

  /**
   * Modify the argument StackOverflowError so that it only retains
   * the first and last {@link #STACK_TRACE_LIMIT} stack trace elements of {@code ex}
   * if it has more than that.
   *
   * @param ex
   *          the StackOverflowError; may get modified
   */
  @SuppressWarnings("nls")
  public static void trimStackOverflowErrorStackTrace(final StackOverflowError ex) {
    int stackTraceLength = ex.getStackTrace().length;
    if (stackTraceLength > STACK_TRACE_LIMIT * 2 + 1) {
      StackTraceElement[] stackTraceElements = new StackTraceElement[(STACK_TRACE_LIMIT * 2) + 1];
      System.arraycopy(ex.getStackTrace(), 0, stackTraceElements, 0, STACK_TRACE_LIMIT);
      stackTraceElements[STACK_TRACE_LIMIT] = new StackTraceElement("", "\n\t\t\t <Skipped multiple lines> \n", null, -1);
      System.arraycopy(ex.getStackTrace(), stackTraceLength - STACK_TRACE_LIMIT, stackTraceElements, STACK_TRACE_LIMIT + 1, STACK_TRACE_LIMIT);
      ex.setStackTrace(stackTraceElements);
    }
  }

  private ThrowableUtil() {
  }

}
