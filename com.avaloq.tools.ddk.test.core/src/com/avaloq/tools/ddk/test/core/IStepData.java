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
 * Representation of test step data.
 * 
 * @param <T>
 *          the type of the data value
 */
public interface IStepData<T> {
  /**
   * Returns the value of this {@link IStepData}.
   * 
   * @return the value of this {@link IStepData}
   */
  T getValue();

  /**
   * Returns {@code true} if the value has been set.
   * 
   * @return {@code true} if the value has been set, {@code false} otherwise
   */
  boolean isValueSet();
}

