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
package com.avaloq.tools.ddk.test.core;

/**
 * Representation of a step result.
 *
 * @param <T>
 *          the type of the result value
 */
public final class StepResult<T> implements IStepData<T> {
  private T value;
  private boolean valueSet;

  /**
   * Creates and returns a {@link StepResult}.
   *
   * @param <T>
   *          the type of the step result value
   * @return the newly created {@link StepResult}
   */
  public static <T> StepResult<T> newResult() {
    return new StepResult<T>();
  }

  /**
   * Creates a new instance of {@link StepResult}.
   */
  private StepResult() {
  }

  @Override
  public T getValue() {
    return value;
  }

  /**
   * Sets the value of this {@link StepResult}. This can only be done by the test framework.
   *
   * @param value
   *          the new value for this {@link StepResult}
   */
  void setValue(final T value) {
    // currently we set the result multiple time until the postconditions succeed. Hence we cannot enforce this anymore.
    // XXX : think about refactoring so that we can check the following assertion again, or decide to remove it for good.
    // Assert.assertFalse("Step result value must not have been set yet.", valueSet);
    this.value = value;
    valueSet = true;
  }

  @Override
  public boolean isValueSet() {
    return valueSet;
  }

  @Override
  @SuppressWarnings("nls")
  public String toString() {
    if (!isValueSet()) {
      return "<value not set>";
    } else {
      return value.toString();
    }
  }
}
