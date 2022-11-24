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
package com.avaloq.tools.ddk.xtext.ui.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.widgets.Display;


/**
 * Dispatches a runnable to the UI thread.
 */
public final class UiThreadDispatcher {
  /**
   * Runs the specified runnable on the UI thread.
   *
   * @param runnable
   *          the runnable to run on the UI thread
   */
  public static void dispatch(final Runnable runnable) {
    Display.getDefault().asyncExec(runnable);
  }

  /**
   * Runs the specified runnable on the UI thread. This method waits until the
   * runnable has been executed.
   *
   * @param runnable
   *          the runnable to invoke on the UI thread
   */
  public static void dispatchAndWait(final Runnable runnable) {
    Display.getDefault().syncExec(runnable);
  }

  /**
   * Runs the specified runnable on the UI thread. This method waits until the
   * runnable has been executed and returns with a result of the specified
   * type.
   *
   * @param runnable
   *          the runnable to invoke on the UI thread
   * @return the result of the runnable
   * @param <T>
   *          the type of the result
   */
  @SuppressWarnings("PMD.AvoidFinalLocalVariable")
  public static <T> T dispatchAndWait(final Function<T> runnable) {
    final List<T> result = new ArrayList<T>();
    Display.getDefault().syncExec(new Runnable() {
      @Override
      public void run() {
        result.add(runnable.run());
      }
    });
    Assert.isTrue(!result.isEmpty(), "Result is empty."); //$NON-NLS-1$
    return result.get(0);
  }

  /**
   * Private constructor to prevent instantiation.
   */
  private UiThreadDispatcher() {
    // never called
  }
}
