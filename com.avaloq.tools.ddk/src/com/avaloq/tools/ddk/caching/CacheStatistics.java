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
 * Represents useful statistics of a cache.
 */
public class CacheStatistics {
  private long entries;
  private long hits;
  private long misses;

  public CacheStatistics() {
    // Start with empty statistics
  }

  public CacheStatistics(final long entries, final long hits, final long misses) {
    this.entries = entries;
    this.hits = hits;
    this.misses = misses;
  }

  public long getEntries() {
    return entries;
  }

  public long getHits() {
    return hits;
  }

  public long getMisses() {
    return misses;
  }

  /**
   * Returns the hit ratio of the cache.
   *
   * @return the hit ratio, or zero if no hits or misses were reported.
   */
  public double getRatio() {
    double total = misses + hits;
    if (total == 0) {
      return 0;
    }
    return hits / total;
  }

  /**
   * Aggregates the given statistics into the current instance.
   *
   * @param statistics
   *          the other statistics, must not be {@code null}
   */
  void aggregate(final CacheStatistics statistics) {
    this.entries += statistics.entries;
    this.hits += statistics.hits;
    this.misses += statistics.misses;
  }
}
