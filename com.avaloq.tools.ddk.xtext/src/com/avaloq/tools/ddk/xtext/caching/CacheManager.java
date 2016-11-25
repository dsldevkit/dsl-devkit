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
package com.avaloq.tools.ddk.xtext.caching;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;

import com.avaloq.tools.ddk.xtext.naming.QualifiedNameLookup;
import com.avaloq.tools.ddk.xtext.naming.QualifiedNameSegmentTreeLookup;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;


/**
 * Instantiates and keeps track of Caches, allowing statistics to be collected and reported.
 */
public final class CacheManager {
  private static final Logger LOGGER = Logger.getLogger(CacheManager.class);

  private static final CacheManager INSTANCE = new CacheManager();

  private static final int CLEANUP_DELAY = 5;
  private static final int REPORT_DELAY = 10;

  private static final int PERCENT = 100;

  private static final String KEY_SEPARATOR = "§"; //$NON-NLS-1$

  private final ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1, new ThreadFactoryBuilder().setDaemon(true).build());
  private final ArrayListMultimap<String, WeakReference<ICache<?, ?>>> caches = ArrayListMultimap.create();
  private final boolean monitoringEnabled;

  private CacheManager() {
    threadPool.scheduleWithFixedDelay(referenceCleaner, CLEANUP_DELAY, CLEANUP_DELAY, TimeUnit.SECONDS);

    monitoringEnabled = Boolean.parseBoolean(System.getProperty("com.avaloq.tools.ddk.caching.EnableMonitor", "false")); //$NON-NLS-1$ //$NON-NLS-2$
    if (monitoringEnabled) {
      threadPool.scheduleWithFixedDelay(reportPrinter, REPORT_DELAY, REPORT_DELAY, TimeUnit.SECONDS);
    }
  }

  public static CacheManager getInstance() {
    return INSTANCE;
  }

  /**
   * Creates a new cache with the default configuration.
   *
   * @param <K>
   *          the key type
   * @param <V>
   *          the value type
   * @param name
   *          the name of the cache, must not be {@code null}
   * @return a new Cache instance, never {@code null}
   */
  public <K, V> MapCache<K, V> createMapCache(final String name) {
    return createMapCache(name, new CacheConfiguration());
  }

  /**
   * Creates a new cache with the given configuration.
   *
   * @param <K>
   *          the key type
   * @param <V>
   *          the value type
   * @param name
   *          the name of the cache, must not be {@code null}
   * @param configuration
   *          the configuration of the cache instance to be created, must not be {@code null}
   * @return a new cache instance, never {@code null}
   */
  public <K, V> MapCache<K, V> createMapCache(final String name, final CacheConfiguration configuration) {
    if (monitoringEnabled) {
      configuration.enableStatistics();
    }

    MapCache<K, V> cache = new MapCache<K, V>(name, configuration);
    synchronized (caches) {
      caches.put(name, new WeakReference<ICache<?, ?>>(cache));
    }
    return cache;
  }

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
  public <K, V> ResourceCache<K, V> getOrCreateResourceCache(final String name, final Resource resource) {
    // Encode the resource object hash into the multimap key, so that caches with the same name coming from different resources
    // are much less likely to collide (since the hash is not guaranteed to be unique per object, collisions are still possible)
    final String key = name + KEY_SEPARATOR + Integer.toHexString(resource.hashCode());
    synchronized (caches) {
      for (WeakReference<ICache<?, ?>> matchReference : caches.get(key)) {
        ICache<?, ?> match = matchReference.get();
        if ((match instanceof ResourceCache) && ((ResourceCache<?, ?>) match).handles(resource)) {
          return (ResourceCache<K, V>) match;
        }
      }
      ResourceCache<K, V> cache = new ResourceCache<K, V>(resource);
      caches.put(key, new WeakReference<ICache<?, ?>>(cache));
      return cache;
    }
  }

  /**
   * Creates a new cache to lookup qualified names.
   *
   * @param <K>
   *          the key type
   * @param <V>
   *          the value type
   * @param name
   *          the name of the cache, must not be {@code null}
   * @param clazz
   *          the value class
   * @param shareValues
   *          whether the value array of a node should be shared with its parent if they're equal
   * @return the qualified name lookup, never {@code null}
   */
  public <K, V> QualifiedNameLookup<V> createNameLookupCache(final String name, final Class<V> clazz, final boolean shareValues) {
    QualifiedNameLookup<V> cache = new QualifiedNameSegmentTreeLookup<V>(clazz, shareValues);
    synchronized (caches) {
      caches.put(name, new WeakReference<ICache<?, ?>>((ICache<?, ?>) cache));
    }
    return cache;
  }

  /**
   * Returns the current statistics for the cache with the given name.
   *
   * @param name
   *          name of the cache, must not be {@code null}
   * @return statistics, never {@code null}
   */
  public CacheStatistics getStatistics(final String name) {
    CacheStatistics result = new MultiCacheStatistics();
    synchronized (caches) {
      for (WeakReference<ICache<?, ?>> reference : caches.get(name)) {
        ICache<?, ?> value = reference.get();
        if (value != null) {
          result.aggregate(value.getStatistics());
        }
      }
    }
    return result;
  }

  // Task to print the a cache usage report
  @SuppressWarnings("nls")
  private final Runnable reportPrinter = new Runnable() {
    @Override
    public void run() {
      Map<String, MultiCacheStatistics> allStatistics = Maps.newLinkedHashMap();
      synchronized (caches) {
        for (String key : new TreeSet<String>(caches.keySet())) {
          String name = getCacheNameFromKey(key);
          if (!allStatistics.containsKey(name)) {
            allStatistics.put(name, new MultiCacheStatistics());
          }
          CacheStatistics statistics = allStatistics.get(name);
          for (WeakReference<ICache<?, ?>> reference : caches.get(key)) {
            ICache<?, ?> value = reference.get();
            if (value != null) {
              statistics.aggregate(value.getStatistics());
            }
          }
        }
      }
      printStatistics(allStatistics);
    }

    /**
     * Extract the cache name from a (possibly composite) key.
     *
     * @param key
     *          the key
     * @return the cache name
     */
    private String getCacheNameFromKey(final String key) {
      int endIndex = key.indexOf(KEY_SEPARATOR);
      if (endIndex == -1) {
        return key;
      }
      return key.substring(0, endIndex);
    }

    private void printStatistics(final Map<String, MultiCacheStatistics> allStatistics) {
      StringBuilder builder = new StringBuilder();
      builder.append("Active caches:\n");
      builder.append(String.format("%70s | %6s | %9s | %9s | %9s | %4s\n", "name", "caches", "items", "hit", "miss", "rate"));

      for (Entry<String, MultiCacheStatistics> entry : allStatistics.entrySet()) {
        final MultiCacheStatistics statistics = entry.getValue();
        builder.append(String.format("%70s | %,6d | %,9d | %,9d | %,9d | %3.0f%%\n", entry.getKey(), statistics.getCacheCounter(), statistics.getEntries(), statistics.getHits(), statistics.getMisses(), statistics.getRatio()
            * PERCENT));
      }

      builder.append('\n');
      LOGGER.info(builder.toString());
    }
  };

  // Task to clean the weak references that have been garbage collected
  private final Runnable referenceCleaner = new Runnable() {
    @Override
    public void run() {
      synchronized (caches) {
        for (Iterator<WeakReference<ICache<?, ?>>> iterator = caches.values().iterator(); iterator.hasNext();) {
          WeakReference<ICache<?, ?>> reference = iterator.next();
          if (reference.get() == null) {
            iterator.remove();
          }
        }
      }
    }
  };

  /**
   * {@link CacheStatistics} representing the statistics coming from multiple caches.
   */
  private class MultiCacheStatistics extends CacheStatistics {
    private int counter;

    @Override
    void aggregate(final CacheStatistics statistics) {
      counter++;
      super.aggregate(statistics);
    }

    public int getCacheCounter() {
      return counter;
    }
  }
}
