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
package com.avaloq.tools.ddk.caching.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.caching.CacheManager;
import com.avaloq.tools.ddk.caching.CacheStatistics;
import com.avaloq.tools.ddk.caching.ICache;
import com.avaloq.tools.ddk.caching.LocalMapCache;


/**
 * Tests {@link LocalMapCache} and {@link CacheManager#createLocalMapCache(String)}.
 */
@SuppressWarnings("nls")
public class LocalMapCacheTest {

  private static final String KEY = "a";
  private static final String VALUE = "1";

  @Test
  @SuppressWarnings("PMD.LooseCoupling")
  public void testStatisticsCountHitsAndMisses() {
    LocalMapCache<String, String> cache = new LocalMapCache<>();
    cache.put(KEY, VALUE);
    cache.put("b", "2");

    assertEquals(VALUE, cache.get(KEY));
    assertEquals(VALUE, cache.get(KEY));
    assertNull(cache.get("c"));

    CacheStatistics statistics = cache.getStatistics();
    assertEquals(2, statistics.getEntries());
    assertEquals(2, statistics.getHits());
    assertEquals(1, statistics.getMisses());
  }

  @Test
  public void testPlainMapWithoutMonitoring() {
    assumeFalse(CacheManager.getInstance().isMonitoringEnabled());
    Map<String, String> cache = CacheManager.getInstance().createLocalMapCache("LocalMapCacheTest#cache");
    cache.put(KEY, VALUE);

    assertEquals(VALUE, cache.get(KEY));
    assertFalse(cache instanceof ICache);
  }

}
