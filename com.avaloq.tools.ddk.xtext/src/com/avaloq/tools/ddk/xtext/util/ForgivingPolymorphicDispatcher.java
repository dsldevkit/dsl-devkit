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

import java.util.Collections;

import org.eclipse.xtext.util.PolymorphicDispatcher;


/**
 * A {@link PolymorphicDispatcher} for a fixed number of arguments (default is one). "Forgives" missing methods by returning
 * a default value when the method is not found. The default default value is "null".
 * {@link ForgivingPolymorphicDispatcher#ForgivingSingleParameterPolymorphicDispatcher(String, Object)}
 *
 * @param <T>
 *          type this dispatcher works on
 */
public class ForgivingPolymorphicDispatcher<T> extends PolymorphicDispatcher<T> {
  private final T returnValue;

  public ForgivingPolymorphicDispatcher(final String methodName, final Object target) {
    this(methodName, target, null);
  }

  public ForgivingPolymorphicDispatcher(final String methodName, final Object target, final int parameterCount) {
    this(methodName, target, parameterCount, null);
  }

  public ForgivingPolymorphicDispatcher(final String methodName, final Object target, final T returnValue) {
    this(methodName, target, 1, returnValue);
  }

  public ForgivingPolymorphicDispatcher(final String methodName, final Object target, final int parameterCount, final T returnValue) {
    super(methodName, parameterCount, parameterCount, Collections.singletonList(target), PolymorphicDispatcher.NullErrorHandler.<T> get());
    this.returnValue = returnValue;
  }

  @Override
  protected T handleNoSuchMethod(final Object... params) {
    return returnValue;
  }
}
