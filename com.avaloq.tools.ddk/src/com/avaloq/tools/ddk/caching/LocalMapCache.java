/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.caching;

import java.util.HashMap;


/**
 * A {@link HashMap} which counts the hits and misses of {@link #get(Object)} for the {@link CacheManager} statistics.
 * <p>
 * It is not thread-safe. {@link CacheManager#createLocalMapCache(String)} creates it when monitoring is enabled.
 * </p>
 *
 * @param <K>
 *          the key type
 * @param <V>
 *          the value type
 */
public class LocalMapCache<K, V> extends HashMap<K, V> implements ICache<K, V> {

  private static final long serialVersionUID = 1L;

  private long hits;
  private long misses;

  @Override
  public V get(final Object key) {
    V value = super.get(key);
    if (value == null) {
      misses++;
    } else {
      hits++;
    }
    return value;
  }

  @Override
  public CacheStatistics getStatistics() {
    return new CacheStatistics(size(), hits, misses);
  }

}
