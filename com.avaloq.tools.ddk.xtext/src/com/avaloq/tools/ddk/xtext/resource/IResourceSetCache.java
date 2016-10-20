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
package com.avaloq.tools.ddk.xtext.resource;

/**
 * A cache for generic objects that is intended to be used in adapters for resource sets.
 *
 * @param <V>
 *          the type of the stored values
 */
public interface IResourceSetCache<V> {

  /**
   * Returns the value associated with the given key.
   *
   * @param key
   *          the key, must not be {@code null}
   * @return the value that is associated with the given key, or {@code null} if none
   */
  V get(Object key);

  /**
   * Adds a value to the cache.
   *
   * @param key
   *          the key for the cached object, must not be {@code null}
   * @param value
   *          the value, may be {@code null}
   */
  void put(Object key, V value);

  /**
   * Clears the cache.
   */
  void clear();
}
