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
package com.avaloq.tools.ddk.xtext.naming;

import java.util.Collection;

import org.eclipse.xtext.naming.QualifiedName;

import com.avaloq.tools.ddk.caching.ICache;


/**
 * This is essentially very similar to a {@link com.google.common.collect.SortedSetMultimap} where the keys type is {@link QualifiedName} and the values
 * are elements of an arbitrary type. While none of the {@link java.util.SortedSet} methods are exposed there is a {@link #get(QualifiedNamePattern, boolean)}
 * method which matches all keys against a given pattern and returns a collection of all corresponding values. Additionally it is also possible to remove all
 * occurrences of a given value in all the mappings using {@link #removeMappings(Object)}.
 *
 * @param <T>
 *          value element type
 */
public interface QualifiedNameLookup<T> extends ICache<QualifiedName, T> {

  /**
   * Returns all values associated with the specified key.
   *
   * @param name
   *          qualified name to match, must not be {@code null}
   * @return collection of all values associated with the specified key or {@code null} if none
   */
  Collection<T> get(QualifiedName name);

  /**
   * Returns a collection of all values where the corresponding key {@link QualifiedNamePattern#matches(QualifiedName) matches} the given pattern.
   *
   * @param pattern
   *          pattern to match, must not be {@code null}
   * @param excludeDuplicates
   *          whether to exclude duplicates and return the result as a {@link java.util.Set}
   * @return a {@link java.util.Set} or {@link java.util.List} containing all values where the key matches the given pattern
   */
  Collection<T> get(QualifiedNamePattern pattern, boolean excludeDuplicates);

  /**
   * Adds a mapping for the given key and value. If there already are values associated with the given key, then the given value will be added (unless already
   * present).
   *
   * @param name
   *          key to associate the value with, must not be {@code null}
   * @param value
   *          value to associate, must not be {@code null}
   */
  void put(QualifiedName name, T value);

  /**
   * Adds a mapping for the given key and collection of values. If there already are values associated with the given key, then the given values will be added
   * (unless already present).
   *
   * @param name
   *          key to associate the value with, must not be {@code null}
   * @param values
   *          values to associate, must not be {@code null}
   */
  void putAll(QualifiedName name, Collection<T> values);

  /**
   * Removes the mapping for the given key and value. Other mapping for the given key remain unchanged.
   *
   * @param name
   *          key to remove mapping for, must not be {@code null}
   * @param value
   *          value to remove mapping for, must not be {@code null}
   */
  void remove(QualifiedName name, T value);

  /**
   * Removes all mappings for the given value. I.e. all keys associated with that value will be changed when calling this method.
   *
   * @param value
   *          value to remove mappings for, must not be {@code null}
   */
  void removeMappings(T value);

  /**
   * Clears this lookup by removing all mappings in it.
   */
  void clear();

  /**
   * Initializes this QualifiedNameLookup from source.
   * Performs a shallow copy, i.e. values are shared between the objects after the operation.
   * Only allowed if this object is empty.
   *
   * @param source
   *          QualifiedNameLookup to initialize from
   * @throws IllegalStateException
   *           if this object is not empty.
   */
  void initializeFrom(QualifiedNameLookup<T> source);

}
