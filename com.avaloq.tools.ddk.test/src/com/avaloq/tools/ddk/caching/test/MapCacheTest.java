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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.caching.CacheConfiguration;
import com.avaloq.tools.ddk.caching.CacheManager;
import com.avaloq.tools.ddk.caching.CacheStatistics;
import com.avaloq.tools.ddk.caching.MapCache;


/**
 * Tests {@link MapCache#computeIfAbsent(Object, java.util.function.Function)}.
 */
@SuppressWarnings({"nls", "PMD.LooseCoupling"})
public class MapCacheTest {

  private static final String KEY = "a";
  private static final String OTHER_KEY = "b";
  private static final long TIMEOUT_SECONDS = 5;

  @Test
  public void testComputeIfAbsentComputesOnceAndReturnsCachedValue() {
    MapCache<String, Object> cache = CacheManager.getInstance().createMapCache("MapCacheTest#compute", new CacheConfiguration());
    AtomicInteger calls = new AtomicInteger();

    Object value = cache.computeIfAbsent(KEY, k -> {
      calls.incrementAndGet();
      return new Object();
    });
    assertNotNull(value);
    assertSame(value, cache.computeIfAbsent(KEY, k -> {
      calls.incrementAndGet();
      return new Object();
    }));
    assertSame(value, cache.get(KEY));
    assertEquals(1, calls.get());
  }

  @Test
  public void testComputeIfAbsentWithNullValueAddsNoMapping() {
    MapCache<String, Object> cache = CacheManager.getInstance().createMapCache("MapCacheTest#null", new CacheConfiguration());

    assertNull(cache.computeIfAbsent(KEY, k -> null));
    assertFalse(cache.containsKey(KEY));
  }

  @Test
  public void testComputeIfAbsentHitWithNullFunctionThrows() {
    MapCache<String, Object> cache = CacheManager.getInstance().createMapCache("MapCacheTest#nullFunction", new CacheConfiguration());
    cache.put(KEY, new Object());

    assertThrows(NullPointerException.class, () -> cache.computeIfAbsent(KEY, null));
  }

  @Test
  public void testComputeIfAbsentRecordsNoHitsOrMisses() {
    MapCache<String, Object> cache = CacheManager.getInstance().createMapCache("MapCacheTest#statistics", new CacheConfiguration().enableStatistics());
    cache.computeIfAbsent(KEY, k -> new Object());
    cache.computeIfAbsent(KEY, k -> new Object());

    CacheStatistics statistics = cache.getStatistics();
    assertEquals(1, statistics.getEntries());
    assertEquals(0, statistics.getHits());
    assertEquals(0, statistics.getMisses());
  }

  @Test
  public void testComputeIfAbsentHitDoesNotWaitForConcurrentComputation() throws InterruptedException, ExecutionException {
    CacheConfiguration configuration = new CacheConfiguration();
    configuration.setConcurrencyLevel(1); // a single segment, so both keys share one lock
    MapCache<String, Object> cache = CacheManager.getInstance().createMapCache("MapCacheTest#concurrent", configuration);
    Object value = new Object();
    cache.put(KEY, value);

    CountDownLatch computing = new CountDownLatch(1);
    CountDownLatch release = new CountDownLatch(1);
    AtomicBoolean timedOut = new AtomicBoolean();
    ExecutorService executor = Executors.newSingleThreadExecutor();
    try {
      final Future<Object> other = executor.submit(() -> cache.computeIfAbsent(OTHER_KEY, k -> {
        computing.countDown();
        try {
          timedOut.set(!release.await(TIMEOUT_SECONDS, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
        return new Object();
      }));
      assertTrue(computing.await(TIMEOUT_SECONDS, TimeUnit.SECONDS));

      assertSame(value, cache.computeIfAbsent(KEY, k -> new Object()));
      assertFalse(timedOut.get());

      release.countDown();
      assertNotNull(other.get());
    } finally {
      executor.shutdownNow();
    }
  }

}
