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
package com.avaloq.tools.ddk.xtext.ui.util;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.widgets.Display;


/**
 * Provides helper methods to validate whether the current statement is invoked
 * on or off the UI thread.
 */
public final class UiAssert {
  /**
   * Ensures that the current statement is <b>not</b> executed within the UI
   * thread.
   */
  public static void isNotUiThread() {
    Assert.isTrue(Display.getCurrent() == null, "Current statement is invoked on the UI thread."); //$NON-NLS-1$
  }

  /**
   * Ensures that the current statement is executed within the UI thread.
   */
  public static void isUiThread() {
    Assert.isTrue(Display.getCurrent() != null, "Current statement is not invoked on the UI thread."); //$NON-NLS-1$
  }

  /**
   * Private constructor to prevent instantiation.
   */
  private UiAssert() {
    // never called
  }
}
