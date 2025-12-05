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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;

import org.eclipse.xtext.naming.QualifiedName;

import com.avaloq.tools.ddk.caching.CacheStatistics;
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

  private final SortedMap<QualifiedName, Object[]> lookupMap = Maps.newTreeMap(new QualifiedNamePattern.Comparator());

  private long hits;
  private long misses;

  @Override
  public void putAll(final QualifiedName name, final Collection<T> values) {
    lookupMap.put(name, ArrayUtils.addAll(lookupMap.get(name), values.toArray(ArrayUtils.newArray(values.size()))));
  }

  @Override
  public void clear() {
    lookupMap.clear();
  }

  @SuppressWarnings("unchecked")
  @Override
  public Collection<T> get(final QualifiedName name) {
    Object[] values = lookupMap.get(name);
    if (values != null) {
      hits++;
      return (Collection<T>) Lists.newArrayList(values);
    } else {
      misses++;
      return null; // NOPMD ReturnEmptyCollectionRatherThanNull
    }
  }

  @Override
  public Collection<T> get(final QualifiedNamePattern pattern, final boolean excludeDuplicates) {
    @SuppressWarnings("unchecked")
    Collection<T> result = (Collection<T>) pattern.findNestedArrayMatches(lookupMap, excludeDuplicates);
    if (result.isEmpty()) {
      misses++;
    } else {
      hits++;
    }
    return result;
  }

  @Override
  public Collection<QualifiedName> getMappings(final T value) {
    Collection<QualifiedName> mappings = new ArrayList<>();

    for (Map.Entry<QualifiedName, Object[]> entry : lookupMap.entrySet()) {
      if (ArrayUtils.find(entry.getValue(), value) >= 0) {
        mappings.add(entry.getKey());
      }
    }

    return mappings;
  }

  @Override
  public void removeMappings(final T value) {
    Iterator<Map.Entry<QualifiedName, Object[]>> iter = lookupMap.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry<QualifiedName, Object[]> entry = iter.next();
      Object[] values = entry.getValue();
      Object[] removed = ArrayUtils.remove(values, value);
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

  @Override
  public void put(final QualifiedName name, final T value) {
    lookupMap.put(name, ArrayUtils.add(lookupMap.get(name), value));
  }

  @Override
  public void remove(final QualifiedName name, final T value) {
    Object[] values = lookupMap.get(name);
    if (values != null) {
      values = ArrayUtils.remove(values, value);
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

  @Override
  public void initializeFrom(final QualifiedNameLookup<T> source) {
    throw new UnsupportedOperationException();
  }

}
