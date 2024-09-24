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
package com.avaloq.tools.ddk.xtext.resource;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.util.OnChangeEvictingCache;
import org.eclipse.xtext.util.OnChangeEvictingCache.CacheAdapter;

import com.avaloq.tools.ddk.caching.CacheManager;
import com.avaloq.tools.ddk.caching.CacheStatistics;
import com.avaloq.tools.ddk.caching.ICache;
import com.google.common.collect.ArrayListMultimap;


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

  /**
   * Creates a new cache or retrieves an existing one associated with the given resource.
   *
   * @param <K>
   *          the key type
   * @param <V>
   *          the value type
   * @param name
   *          the name of the cache, must not be {@code null}
   * @param resource
   *          the resource to associate the cache with, must not be {@code null}
   * @return an existing or a new resource cache instance, never {@code null}
   */
  @SuppressWarnings("unchecked")
  public static <K, V> ResourceCache<K, V> getOrCreateResourceCache(final String name, final Resource resource) {
    CacheManager cacheManager = CacheManager.getInstance();
    if (!cacheManager.isMonitoringEnabled()) {
      return new ResourceCache<K, V>(resource, false);
    }

    // Encode the resource object hash into the multimap key, so that caches with the same name coming from different resources
    // are much less likely to collide (since the hash is not guaranteed to be unique per object, collisions are still possible)
    final String key = name + CacheManager.KEY_SEPARATOR + Integer.toHexString(resource.hashCode());
    ArrayListMultimap<String, WeakReference<ICache<?, ?>>> caches = cacheManager.getCaches();
    synchronized (caches) {
      for (WeakReference<ICache<?, ?>> matchReference : caches.get(key)) {
        ICache<?, ?> match = matchReference.get();
        if ((match instanceof ResourceCache) && ((ResourceCache<?, ?>) match).handles(resource)) {
          return (ResourceCache<K, V>) match;
        }
      }
      ResourceCache<K, V> cache = new ResourceCache<K, V>(resource, true);
      caches.put(key, new WeakReference<ICache<?, ?>>(cache));
      return cache;
    }
  }

  public ResourceCache(final Resource resource, final boolean addAsListener) {
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
