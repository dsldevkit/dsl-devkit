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

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.cache.CacheLoader;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;


/**
 * Instantiates and keeps track of Caches, allowing statistics to be collected and reported.
 */
public class CacheManager {
  private static final Logger LOGGER = LogManager.getLogger(CacheManager.class);

  private static final class SingletonHolder {
    private static CacheManager instance = new CacheManager();

    static CacheManager get() {
      return instance;
    }
  }

  private static final int CLEANUP_DELAY = 5;
  private static final int REPORT_DELAY = 10;

  private static final int PERCENT = 100;

  public static final String KEY_SEPARATOR = "§"; //$NON-NLS-1$

  private final ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1, new ThreadFactoryBuilder().setDaemon(true).build());
  private final ArrayListMultimap<String, WeakReference<ICache<?, ?>>> caches = ArrayListMultimap.create();
  private final boolean monitoringEnabled;

  protected CacheManager() {
    monitoringEnabled = Boolean.getBoolean("com.avaloq.tools.ddk.caching.EnableMonitor"); //$NON-NLS-1$
    if (isMonitoringEnabled()) {
      threadPool.scheduleWithFixedDelay(referenceCleaner, CLEANUP_DELAY, CLEANUP_DELAY, TimeUnit.SECONDS);
      threadPool.scheduleWithFixedDelay(reportPrinter, REPORT_DELAY, REPORT_DELAY, TimeUnit.SECONDS);
    }
  }

  public static CacheManager getInstance() {
    return SingletonHolder.get();
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
  @SuppressWarnings("PMD.LooseCoupling")
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
  @SuppressWarnings("PMD.LooseCoupling")
  public <K, V> MapCache<K, V> createMapCache(final String name, final CacheConfiguration configuration) {
    if (isMonitoringEnabled()) {
      configuration.enableStatistics();
    }

    MapCache<K, V> cache = new MapCache<K, V>(name, configuration);
    if (isMonitoringEnabled()) {
      synchronized (getCaches()) {
        getCaches().put(name, new WeakReference<ICache<?, ?>>(cache));
      }
    }
    return cache;
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
   * @param loader
   *          the cache loader used to obtain new values
   * @return a new cache instance, never {@code null}
   */
  @SuppressWarnings("PMD.LooseCoupling")
  public <K, V> MapCache<K, V> createMapCache(final String name, final CacheConfiguration configuration, final CacheLoader<? super K, V> loader) {
    if (isMonitoringEnabled()) {
      configuration.enableStatistics();
    }

    MapCache<K, V> cache = new MapCache<K, V>(name, configuration, loader);
    if (isMonitoringEnabled()) {
      synchronized (getCaches()) {
        getCaches().put(name, new WeakReference<ICache<?, ?>>(cache));
      }
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
    if (isMonitoringEnabled()) {
      synchronized (getCaches()) {
        for (WeakReference<ICache<?, ?>> reference : getCaches().get(name)) {
          ICache<?, ?> value = reference.get();
          if (value != null) {
            result.aggregate(value.getStatistics());
          }
        }
      }
    }
    return result;
  }

  public boolean isMonitoringEnabled() {
    return monitoringEnabled;
  }

  public ArrayListMultimap<String, WeakReference<ICache<?, ?>>> getCaches() {
    return caches;
  }

  // Task to print the a cache usage report
  @SuppressWarnings("nls")
  private final Runnable reportPrinter = new Runnable() {
    @Override
    public void run() {
      Map<String, MultiCacheStatistics> allStatistics = Maps.newLinkedHashMap();
      synchronized (getCaches()) {
        for (String key : new TreeSet<String>(getCaches().keySet())) {
          String name = getCacheNameFromKey(key);
          if (!allStatistics.containsKey(name)) {
            allStatistics.put(name, new MultiCacheStatistics());
          }
          CacheStatistics statistics = allStatistics.get(name);
          for (WeakReference<ICache<?, ?>> reference : getCaches().get(key)) {
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
      StringBuilder builder = new StringBuilder("Active caches:\n");
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
      synchronized (getCaches()) {
        for (Iterator<WeakReference<ICache<?, ?>>> iterator = getCaches().values().iterator(); iterator.hasNext();) {
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
  private final class MultiCacheStatistics extends CacheStatistics {
    private int counter;

    @Override
    void aggregate(final CacheStatistics statistics) {
      counter++;
      super.aggregate(statistics);
    }

    int getCacheCounter() {
      return counter;
    }
  }
}
