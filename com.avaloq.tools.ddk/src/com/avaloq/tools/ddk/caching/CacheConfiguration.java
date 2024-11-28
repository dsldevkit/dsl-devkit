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

/**
 * Allows the behaviour of a cache to be configured.
 */
public class CacheConfiguration {
  static final int UNSET_INT = -1;

  private boolean arraySize;
  private boolean softValues;
  private boolean statistics;

  private long cacheSize = UNSET_INT;
  private int initialCapacity = UNSET_INT;
  private int concurrencyLevel = UNSET_INT;

  /**
   * All values stored in the cache are wrapped as {@link java.lang.ref.SoftReference SoftReference}, allowing them to be garbage collected as necessary.
   *
   * @return the cache configuration
   */
  public CacheConfiguration useSoftValues() {
    softValues = true;
    return this;
  }

  /**
   * If enabled, the cache will treat the values as arrays and count their total entries when determining the maximum number of allowed entries.
   *
   * @return the cache configuration
   */
  public CacheConfiguration useArraySize() {
    arraySize = true;
    return this;
  }

  /**
   * Enables the collection of statistics for the cache, with a possible performance penalty.
   *
   * @return the cache configuration
   */
  public CacheConfiguration enableStatistics() {
    statistics = true;
    return this;
  }

  /**
   * Sets the maximum number of entries that can be stored in the cache, before old entries get evicted.
   *
   * @param size
   *          the maximum size
   * @return the cache configuration
   */
  public CacheConfiguration setMaximumSize(final long size) {
    cacheSize = size;
    return this;
  }

  public boolean isSoftValuesEnabled() {
    return softValues;
  }

  public boolean isStatisticsEnabled() {
    return statistics;
  }

  public long getMaximumSize() {
    return cacheSize;
  }

  public boolean isArraySizeEnabled() {
    return arraySize;
  }

  /**
   * Sets the initial size for the cache.
   *
   * @param capacity
   *          the initial capacity
   * @return the cache configuration
   */
  public CacheConfiguration setInitialCapacity(final int capacity) {
    initialCapacity = capacity;
    return this;
  }

  public int getInitialCapacity() {
    return initialCapacity;
  }

  public int getConcurrencyLevel() {
    return concurrencyLevel;
  }

  /**
   * Setsthe concurrency level for the cache. Guava concurrent maps creates as many segments
   * as the specified concurrency level, each segment having its own lock.
   * If not set, a reasonable default is deternimed by the library according to the number of CPUs.
   */
  public void setConcurrencyLevel(final int concurrencyLevel) {
    this.concurrencyLevel = concurrencyLevel;
  }
}
