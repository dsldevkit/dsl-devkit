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
package com.avaloq.tools.ddk.caching;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.Weigher;


/**
 * Represents a simple map-like cache.
 * <p>
 * <em>Note: </em> new instances should be created through {@link CacheManager}
 * </p>
 *
 * @param <K>
 *          the key type
 * @param <V>
 *          the value type
 */
public class MapCache<K, V> implements ICache<K, V>, Map<K, V> {

  private final Cache<K, V> backend;
  private final String name;

  MapCache(final String name, final CacheConfiguration config) {
    this.name = name;
    backend = configure(config).build();
  }

  MapCache(final String name, final CacheConfiguration config, final CacheLoader<? super K, V> loader) {
    this.name = name;
    backend = configure(config).build(loader);
  }

  private CacheBuilder<Object, Object> configure(final CacheConfiguration config) {
    CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder();
    if (config.isStatisticsEnabled()) {
      cacheBuilder.recordStats();
    }
    if (config.isSoftValuesEnabled()) {
      cacheBuilder.softValues();
    } else if (config.isWeakValuesEnabled()) {
      cacheBuilder.weakValues();
    }

    if (config.isWeakKeysEnabled()) {
      cacheBuilder.weakKeys();
    }

    if (config.getInitialCapacity() >= 0) {
      cacheBuilder.initialCapacity(config.getInitialCapacity());
    }
    if (config.getMaximumSize() >= 0) {
      if (config.isArraySizeEnabled()) {
        cacheBuilder.maximumWeight(config.getMaximumSize());
        cacheBuilder.weigher(new Weigher<K, V>() {
          @Override
          public int weigh(final K key, final V value) {
            if (value instanceof byte[]) {
              return ((byte[]) value).length;
            }
            throw new IllegalStateException("Using array size is only supported for byte arrays"); //$NON-NLS-1$
          }
        });
      } else {
        cacheBuilder.maximumSize(config.getMaximumSize());
      }
    }
    if (config.getConcurrencyLevel() >= 0) {
      cacheBuilder.concurrencyLevel(config.getConcurrencyLevel());
    }
    return cacheBuilder;
  }

  public String getName() {
    return name;
  }

  @Override
  public CacheStatistics getStatistics() {
    CacheStats stats = backend.stats();
    return new CacheStatistics(backend.size(), stats.hitCount(), stats.missCount());
  }

  @Override
  public void clear() {
    backend.invalidateAll();
  }

  @Override
  public V putIfAbsent(final K key, final V value) {
    return backend.asMap().putIfAbsent(key, value);
  }

  @Override
  public Set<K> keySet() {
    return backend.asMap().keySet();
  }

  @Override
  public Collection<V> values() {
    return backend.asMap().values();
  }

  @Override
  public boolean isEmpty() {
    return backend.size() == 0;
  }

  @Override
  public int size() {
    return (int) backend.size();
  }

  @Override
  public boolean containsKey(final Object key) {
    return backend.getIfPresent(key) != null;
  }

  @Override
  public boolean containsValue(final Object value) {
    return backend.asMap().containsValue(value);
  }

  @Override
  public V get(final Object key) {
    return backend.getIfPresent(key);
  }

  @Override
  public V put(final K key, final V value) {
    return backend.asMap().put(key, value);
  }

  @Override
  public V remove(final Object key) {
    return backend.asMap().remove(key);
  }

  @Override
  public void putAll(final Map<? extends K, ? extends V> m) {
    backend.putAll(m);
  }

  @Override
  public Set<Map.Entry<K, V>> entrySet() {
    return backend.asMap().entrySet();
  }

  @Override
  public V computeIfAbsent(final K key, final Function<? super K, ? extends V> mappingFunction) {
    return backend.asMap().computeIfAbsent(key, mappingFunction);
  }

  @Override
  public V getOrDefault(final Object key, final V defaultValue) {
    return backend.asMap().getOrDefault(key, defaultValue);
  }

  @Override
  public void forEach(final BiConsumer<? super K, ? super V> action) {
    backend.asMap().forEach(action);
  }

  @Override
  public void replaceAll(final BiFunction<? super K, ? super V, ? extends V> function) {
    backend.asMap().replaceAll(function);
  }

  @Override
  public boolean remove(final Object key, final Object value) {
    return backend.asMap().remove(key, value);
  }

  @Override
  public boolean replace(final K key, final V oldValue, final V newValue) {
    return backend.asMap().replace(key, oldValue, newValue);
  }

  @Override
  public V replace(final K key, final V value) {
    return backend.asMap().replace(key, value);
  }

  @Override
  public V computeIfPresent(final K key, final BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
    return backend.asMap().computeIfPresent(key, remappingFunction);
  }

  @Override
  public V compute(final K key, final BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
    return backend.asMap().compute(key, remappingFunction);
  }

  @Override
  public V merge(final K key, final V value, final BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
    return backend.asMap().merge(key, value, remappingFunction);
  }

}
