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
package com.avaloq.tools.ddk.test.core;

/**
 * Representation of a step data.
 * 
 * @param <T>
 *          the type of the data value
 */
public final class StepData<T> implements IStepData<T> {
  private final T value;

  /**
   * Creates and returns a {@link StepData}.
   * 
   * @param <T>
   *          the type of the step data value
   * @param value
   *          the value of the {@link StepData}
   * @return the newly created {@link StepData}
   */
  public static <T> StepData<T> newData(final T value) {
    return new StepData<T>(value);
  }

  /**
   * Creates a new instance of {@link StepData}.
   * 
   * @param value
   *          the value of this step data
   */
  private StepData(final T value) {
    super();
    this.value = value;
  }

  /** {@inheritDoc} */
  public T getValue() {
    return value;
  }

  /** {@inheritDoc} */
  public boolean isValueSet() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return value.toString();
  }
}

