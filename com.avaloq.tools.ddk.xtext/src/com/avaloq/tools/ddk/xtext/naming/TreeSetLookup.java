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
package com.avaloq.tools.ddk.xtext.naming;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;

import org.eclipse.xtext.naming.QualifiedName;

import com.avaloq.tools.ddk.xtext.caching.CacheStatistics;
import com.avaloq.tools.ddk.xtext.util.ArrayUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


/**
 * This is a very straight-forward implementation which internally uses a {@link java.util.TreeSet} to store the mappings.
 *
 * @param <T>
 *          value element type
 */
public class TreeSetLookup<T> implements QualifiedNameLookup<T> {

  private final ArrayUtils<T> arrayUtils;
  private final SortedMap<QualifiedName, T[]> lookupMap = Maps.newTreeMap(new QualifiedNamePattern.Comparator());

  private long hits;
  private long misses;

  public TreeSetLookup(final Class<T> elementType) {
    arrayUtils = ArrayUtils.of(elementType);
  }

  /** {@inheritDoc} */
  @Override
  public void putAll(final QualifiedName name, final Collection<T> values) {
    lookupMap.put(name, arrayUtils.addAll(lookupMap.get(name), values.toArray(arrayUtils.newArray(values.size()))));
  }

  /** {@inheritDoc} */
  @Override
  public void clear() {
    lookupMap.clear();
  }

  /** {@inheritDoc} */
  @Override
  public Collection<T> get(final QualifiedName name) {
    T[] values = lookupMap.get(name);
    if (values != null) {
      hits++;
      return Lists.newArrayList(values);
    } else {
      misses++;
      return null;
    }
  }

  /** {@inheritDoc} */
  @Override
  public Collection<T> get(final QualifiedNamePattern pattern, final boolean excludeDuplicates) {
    Collection<T> result = pattern.findNestedArrayMatches(lookupMap, excludeDuplicates);
    if (result.isEmpty()) {
      misses++;
    } else {
      hits++;
    }
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public void removeMappings(final T value) {
    Iterator<Map.Entry<QualifiedName, T[]>> iter = lookupMap.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry<QualifiedName, T[]> entry = iter.next();
      T[] values = entry.getValue();
      T[] removed = arrayUtils.remove(values, value);
      if (removed == null) {
        // No need to keep around names that don't occur anywhere.
        iter.remove();
      } else if (removed != values) {
        // Updating the map in this way is safe even during an iteration: since the key is guaranteed to exist in the map, this does not modify the map
        // but only updates the entry's value. The javadoc on TreeMap states that the entries returned by a TreeMap did not support entry.setValue();
        // otherwise I'd have used that. Interestingly, looking at the code of TreeMap, it does appear as if the entries returned by a TreeMap's entry set
        // would indeed support that operation...
        lookupMap.put(entry.getKey(), removed);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public void put(final QualifiedName name, final T value) {
    lookupMap.put(name, arrayUtils.add(lookupMap.get(name), value));
  }

  /** {@inheritDoc} */
  @Override
  public void remove(final QualifiedName name, final T value) {
    T[] values = lookupMap.get(name);
    if (values != null) {
      values = arrayUtils.remove(values, value);
      if (values != null) {
        lookupMap.put(name, values);
      } else {
        lookupMap.remove(name);
      }
    }
  }

  @Override
  public CacheStatistics getStatistics() {
    return new CacheStatistics(lookupMap.size(), hits, misses);
  }

}
