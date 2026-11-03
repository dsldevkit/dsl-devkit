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
package com.avaloq.tools.ddk.xtext.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.xtext.util.OnChangeEvictingCache;
import org.junit.jupiter.api.Test;


/**
 * Tests {@link ResourceCache}.
 */
@SuppressWarnings("nls")
public class ResourceCacheTest {

  private static final String CACHE_NAME = "ResourceCacheTest#cache";
  private static final String KEY = "key";

  private static long countCacheAdapters(final Resource resource) {
    return resource.eAdapters().stream().filter(OnChangeEvictingCache.CacheAdapter.class::isInstance).count();
  }

  @Test
  public void testCachesOfSameResourceShareOneAdapter() {
    Resource resource = new ResourceImpl(URI.createURI("test.resource"));
    Object value = new Object();
    ResourceCache<String, Object> cache = ResourceCache.getOrCreateResourceCache(CACHE_NAME, resource);
    cache.set(KEY, value);

    assertSame(value, ResourceCache.<String, Object> getOrCreateResourceCache(CACHE_NAME, resource).get(KEY));
    assertSame(value, new ResourceCache<String, Object>(resource, false).get(KEY));
    assertEquals(1, countCacheAdapters(resource));
  }

  @Test
  public void testCachesOfDifferentResourcesAreIndependent() {
    Resource resource = new ResourceImpl(URI.createURI("test.resource"));
    Resource otherResource = new ResourceImpl(URI.createURI("other.resource"));
    ResourceCache.<String, Object> getOrCreateResourceCache(CACHE_NAME, resource).set(KEY, new Object());

    assertNull(ResourceCache.<String, Object> getOrCreateResourceCache(CACHE_NAME, otherResource).get(KEY));
    assertEquals(1, countCacheAdapters(otherResource));
  }

  @Test
  public void testSemanticChangeEvictsValues() {
    Resource resource = new ResourceImpl(URI.createURI("test.resource"));
    ResourceCache<String, Object> cache = ResourceCache.getOrCreateResourceCache(CACHE_NAME, resource);
    cache.set(KEY, new Object());

    resource.getContents().add(EcoreFactory.eINSTANCE.createEClass());

    assertNull(cache.get(KEY));
  }

}
