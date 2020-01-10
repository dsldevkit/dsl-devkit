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

import java.util.function.Function;


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
   * If the specified key is not already associated with a value (or is mapped
   * to {@code null}) associates it with the given value and returns
   * {@code null}, else returns the current value.
   *
   * @param key
   *          key with which the specified value is to be associated
   * @param value
   *          value to be associated with the specified key
   * @return the previous value associated with the specified key, or
   *         {@code null} if there was no mapping for the key.
   *         (A {@code null} return can also indicate that the map
   *         previously associated {@code null} with the key,
   *         if the implementation supports null values.)
   */
  V putIfAbsent(final Object key, final V value);

  /**
   * If the specified key is not already associated with a value (or is mapped
   * to {@code null}), attempts to compute its value using the given mapping
   * function and enters it into this map unless {@code null}.
   * <p>
   * If the function returns {@code null} no mapping is recorded. If
   * the function itself throws an (unchecked) exception, the
   * exception is rethrown, and no mapping is recorded.
   *
   * @param key
   *          key with which the specified value is to be associated
   * @param mappingFunction
   *          the function to compute a value
   * @return the current (existing or computed) value associated with
   *         the specified key, or null if the computed value is null
   */
  V computeIfAbsent(Object key, Function<? super Object, ? extends V> mappingFunction);

  /**
   * Clears the cache.
   */
  void clear();
}
