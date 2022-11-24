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

package com.avaloq.tools.ddk.test.ui.swtbot.condition;

import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;


/**
 * A condition checking if two returned objects are equal.
 * If they are both null, they are considered equal.
 *
 * @param <T>
 *          The type of the returned objects
 */
public class WaitForEquals<T> extends DefaultCondition {
  private final String failureMessage;
  private final Supplier<T> expectedSupplier;
  private final Supplier<T> actualSupplier;
  private T expected;
  private T actual;

  /**
   * Creates a new instance of {@link WaitForEquals}.
   *
   * @param failureMessage
   *          the failure message when the returned values are not equal, must not be {@code null}
   * @param expectedSupplier
   *          interface to supply the expected value, must not be {@code null}
   * @param actualSupplier
   *          interface to supply the actual value, must not be {@code null}
   */
  public WaitForEquals(final String failureMessage, final Supplier<T> expectedSupplier, final Supplier<T> actualSupplier) {
    this.failureMessage = failureMessage;
    this.expectedSupplier = expectedSupplier;
    this.actualSupplier = actualSupplier;
  }

  @Override
  public boolean test() {
    expected = expectedSupplier.get();
    actual = actualSupplier.get();
    return Objects.equals(expected, actual);
  }

  @Override
  public String getFailureMessage() {
    return failureMessage + " expected:<" + expected + "> but was:<" + actual + ">"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }
}
