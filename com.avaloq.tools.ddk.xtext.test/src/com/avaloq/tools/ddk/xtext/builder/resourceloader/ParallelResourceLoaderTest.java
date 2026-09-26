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
package com.avaloq.tools.ddk.xtext.builder.resourceloader;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.builder.resourceloader.IResourceLoader.LoadOperation;
import org.eclipse.xtext.builder.resourceloader.IResourceLoader.LoadOperationException;
import org.eclipse.xtext.builder.resourceloader.IResourceLoader.Sorter;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.persistence.SourceLevelURIsAdapter;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.junit.jupiter.api.Test;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.inject.Guice;


/**
 * Tests for {@link ParallelResourceLoader}.
 */
@SuppressWarnings("nls")
public class ParallelResourceLoaderTest {

  private static final URI SLOW_URI = URI.createURI("platform:/resource/project/slow.test");
  private static final URI OTHER_URI = URI.createURI("platform:/resource/project/other.test");
  private static final long TIMEOUT_MILLIS = 50;
  private static final long GENEROUS_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(10);
  private static final long SETTLE_MILLIS = 200;
  /** Unbounded result queue, and the synchronous hand-off used in production. */
  private static final int[] QUEUE_SIZES = {-1, 0};

  @Test
  public void timeoutAbandonsEveryOutstandingLoad() {
    for (int queueSize : QUEUE_SIZES) {
      CountDownLatch loadReleased = new CountDownLatch(1);
      Set<URI> loaded = ConcurrentHashMap.newKeySet();
      LoadOperation operation = load(List.of(SLOW_URI, OTHER_URI), queueSize, TIMEOUT_MILLIS, uri -> {
        loaded.add(uri);
        Uninterruptibles.awaitUninterruptibly(loadReleased);
        return new ResourceImpl(uri);
      });
      try {
        assertTimesOut(operation);
        assertFalse(operation.hasNext(), "one timeout must abandon all outstanding loads, not just one");

        loadReleased.countDown();
        Uninterruptibles.sleepUninterruptibly(SETTLE_MILLIS, TimeUnit.MILLISECONDS);
        assertFalse(loaded.contains(OTHER_URI), "queued loads must not start after the operation was abandoned");
      } finally {
        loadReleased.countDown();
        operation.cancel();
      }
    }
  }

  @Test
  public void failedLoadDoesNotAbandonTheOperation() {
    for (int queueSize : QUEUE_SIZES) {
      LoadOperation operation = load(List.of(SLOW_URI, OTHER_URI), queueSize, GENEROUS_TIMEOUT_MILLIS, uri -> {
        if (SLOW_URI.equals(uri)) {
          throw new WrappedException(new IOException("cannot read"));
        }
        return new ResourceImpl(uri);
      });
      try {
        LoadOperationException failure = assertThrows(LoadOperationException.class, operation::next);
        assertSame(SLOW_URI, failure.getUri());
        assertInstanceOf(IOException.class, failure.getCause());
        assertTrue(operation.hasNext(), "a failed load must not abandon the remaining loads");
        assertSame(OTHER_URI, operation.next().getResource().getURI());
      } finally {
        operation.cancel();
      }
    }
  }

  @Test
  public void resultWithinTimeoutIsDelivered() {
    for (int queueSize : QUEUE_SIZES) {
      LoadOperation operation = load(List.of(SLOW_URI), queueSize, GENEROUS_TIMEOUT_MILLIS, ResourceImpl::new);
      try {
        assertSame(SLOW_URI, operation.next().getResource().getURI());
        assertFalse(operation.hasNext(), "the single result has been delivered");
      } finally {
        operation.cancel();
      }
    }
  }

  private static LoadOperation load(final List<URI> uris, final int queueSize, final long timeoutMillis, final Function<URI, Resource> loadFunction) {
    ParallelResourceLoader loader = new ParallelResourceLoader(resourceSetProvider(), new Sorter.NoSorting(), 1, queueSize) {
      @Override
      protected Resource loadResource(final URI uri, final ResourceSet localResourceSet, final ResourceSet parentResourceSet) {
        return loadFunction.apply(uri);
      }
    };
    IResourceServiceProvider.Registry registry = mock(IResourceServiceProvider.Registry.class);
    Guice.createInjector(binder -> binder.bind(IResourceServiceProvider.Registry.class).toInstance(registry)).injectMembers(loader);
    loader.setTimeout(timeoutMillis, TimeUnit.MILLISECONDS);

    ResourceSet parent = new ResourceSetImpl();
    SourceLevelURIsAdapter.setSourceLevelUris(parent, Collections.emptySet());
    LoadOperation operation = loader.create(parent, null);
    operation.load(uris);
    return operation;
  }

  private static void assertTimesOut(final LoadOperation operation) {
    LoadOperationException timeout = assertThrows(LoadOperationException.class, operation::next);
    assertInstanceOf(TimeoutException.class, timeout.getCause());
  }

  private static IResourceSetProvider resourceSetProvider() {
    IResourceSetProvider provider = mock(IResourceSetProvider.class);
    when(provider.get(any())).thenAnswer(invocation -> new ResourceSetImpl());
    return provider;
  }

}
