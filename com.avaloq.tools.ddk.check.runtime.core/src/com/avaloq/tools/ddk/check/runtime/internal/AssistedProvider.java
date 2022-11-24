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
package com.avaloq.tools.ddk.check.runtime.internal;

/**
 * Provider using the assisted injection pattern. Solves the reinjection pattern by
 * providing an instance of {@code C} when querying a type of type {@code T}.
 * 
 * @param <T>
 *          the generic type T
 * @param <C>
 *          the generic type C
 */
public interface AssistedProvider<T, C> {

  /**
   * Gets the instance of generic type T.
   * 
   * @param context
   *          the context type of type C
   * @return the instance of type T
   */
  T get(C context);

}

