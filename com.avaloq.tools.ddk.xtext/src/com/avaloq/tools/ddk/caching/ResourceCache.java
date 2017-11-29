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
package com.avaloq.tools.ddk.caching;

import java.lang.reflect.Field;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.util.OnChangeEvictingCache;
import org.eclipse.xtext.util.OnChangeEvictingCache.CacheAdapter;


/**
 * A cache whose lifecycle is associated to that of a resource.
 *
 * @param <K>
 *          the key type
 * @param <V>
 *          the value type
 */
public class ResourceCache<K, V> implements ICache<K, V>, OnChangeEvictingCache.Listener {

  private final OnChangeEvictingCache.CacheAdapter backend;
  private final Resource resource;

  ResourceCache(final Resource resource, final boolean addAsListener) {
    this.resource = resource;
    backend = new OnChangeEvictingCache().getOrCreate(resource);
    if (addAsListener) {
      backend.addCacheListener(this);
    }
  }

  /**
   * Returns a value from the cache.
   *
   * @param key
   *          the key, must not be {@code null}
   * @return the value, or {@code null} if not found
   */
  public V get(final K key) {
    return backend.get(key);
  }

  /**
   * Stores a value in the cache.
   *
   * @param key
   *          the key, must not be {@code null}
   * @param value
   *          the value, may be {@code null}
   */
  public void set(final K key, final V value) {
    backend.set(key, value);
  }

  @Override
  @SuppressWarnings("unchecked")
  public CacheStatistics getStatistics() {
    try {
      Field hitsField = backend.getClass().getDeclaredField("hits"); //$NON-NLS-1$
      hitsField.setAccessible(true);
      int hits = hitsField.getInt(backend);

      Field missesField = backend.getClass().getDeclaredField("misses"); //$NON-NLS-1$
      missesField.setAccessible(true);
      int misses = missesField.getInt(backend);

      Field valuesField = backend.getClass().getDeclaredField("values"); //$NON-NLS-1$
      valuesField.setAccessible(true);
      Map<Object, Object> values = (Map<Object, Object>) valuesField.get(backend);

      return new CacheStatistics(values.size(), hits, misses);
      // CHECKSTYLE:OFF If getting statistics fails, we should just return an empty result
    } catch (Exception e) {
      // CHECKSTYLE:ON
      return new CacheStatistics();
    }
  }

  @Override
  public void onEvict(final CacheAdapter cache) {
    // This is needed to make the backend hold a reference to this cache, thus making their lifecycles match
  }

  /**
   * Checks whether this cache is attached to the given resource.
   *
   * @param newResource
   *          the resource to check against, must not me {@code null}
   * @return true, if the cache is attached to the resource
   */
  public boolean handles(final Resource newResource) {
    return resource.equals(newResource);
  }
}
