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
import static org.junit.jupiter.api.Assertions.assertNotSame;
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
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.xtext.builder.resourceloader.IResourceLoader.LoadOperation;
import org.eclipse.xtext.builder.resourceloader.IResourceLoader.LoadOperationException;
import org.eclipse.xtext.builder.resourceloader.IResourceLoader.Sorter;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextPlatformResourceURIHandler;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.resource.persistence.SourceLevelURIsAdapter;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.junit.jupiter.api.Test;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.inject.Guice;


/**
 * Tests for {@link ParallelResourceLoader}.
 */
@SuppressWarnings({"restriction", "nls"})
public class ParallelResourceLoaderTest {

  private static final URI SLOW_URI = URI.createURI("platform:/resource/project/slow.test");
  private static final URI OTHER_URI = URI.createURI("platform:/resource/project/other.test");
  private static final long TIMEOUT_MILLIS = 50;
  private static final long GENEROUS_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(10);
  private static final long SETTLE_MILLIS = 200;
  /** Unbounded result queue, and the synchronous hand-off used in production. */
  private static final int[] QUEUE_SIZES = {-1, 0};
  private static final String OTHER_OPTION = "other.option";
  private static final String LATE_OPTION = "late.option";

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
  public void interruptedWaitAbandonsEveryOutstandingLoad() {
    for (int queueSize : QUEUE_SIZES) {
      CountDownLatch loadReleased = new CountDownLatch(1);
      LoadOperation operation = load(List.of(SLOW_URI, OTHER_URI), queueSize, GENEROUS_TIMEOUT_MILLIS, uri -> {
        Uninterruptibles.awaitUninterruptibly(loadReleased);
        return new ResourceImpl(uri);
      });
      try {
        Thread.currentThread().interrupt();
        assertTimesOut(operation);
        assertTrue(Thread.interrupted(), "the interrupt must be preserved");
        assertFalse(operation.hasNext(), "an interrupted wait must abandon all outstanding loads, or the builder loops on it");
      } finally {
        Thread.interrupted();
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

  @Test
  public void workerKeepsItsOwnUriHandlerWhenParentUsesTheStockHandler() {
    ResourceSet parent = new XtextResourceSet();
    parent.getLoadOptions().put(OTHER_OPTION, Boolean.TRUE);
    parent.getLoadOptions().put(ResourceDescriptionsProvider.NAMED_BUILDER_SCOPE, Boolean.TRUE);

    ResourceSet worker = workerResourceSet(parent, XtextResourceSet::new);

    Object handler = worker.getLoadOptions().get(XMLResource.OPTION_URI_HANDLER);
    assertNotSame(parent.getLoadOptions().get(XMLResource.OPTION_URI_HANDLER), handler, "workers must not share the parent's handler, which holds the base URI of the load in progress");
    assertSame(worker, assertInstanceOf(XtextPlatformResourceURIHandler.class, handler).getResourceSet(), "the worker's handler must be bound to the worker's resource set");
    assertSame(Boolean.TRUE, worker.getLoadOptions().get(OTHER_OPTION), "the other load options must still be copied");
    assertFalse(worker.getLoadOptions().containsKey(ResourceDescriptionsProvider.NAMED_BUILDER_SCOPE), "the builder scope option must still be removed");
  }

  @Test
  public void workerGetsTheParentUriHandlerWhenItIsNotTheStockHandler() {
    ResourceSet parent = new XtextResourceSet();
    XMLResource.URIHandler customHandler = mock(XMLResource.URIHandler.class);
    parent.getLoadOptions().put(XMLResource.OPTION_URI_HANDLER, customHandler);
    XMLResource.URIHandler customSubclassHandler = new XtextPlatformResourceURIHandler() {
    };

    assertSame(customHandler, workerResourceSet(parent, XtextResourceSet::new).getLoadOptions().get(XMLResource.OPTION_URI_HANDLER), "a handler set on the parent on purpose must reach the workers");

    parent.getLoadOptions().put(XMLResource.OPTION_URI_HANDLER, customSubclassHandler);
    assertSame(customSubclassHandler, workerResourceSet(parent, XtextResourceSet::new).getLoadOptions().get(XMLResource.OPTION_URI_HANDLER), "a subclass of the stock handler is not the stock handler");
  }

  @Test
  public void workerWithoutOwnUriHandlerGetsTheParentHandler() {
    ResourceSet parent = new XtextResourceSet();

    ResourceSet worker = workerResourceSet(parent, ResourceSetImpl::new);

    assertSame(parent.getLoadOptions().get(XMLResource.OPTION_URI_HANDLER), worker.getLoadOptions().get(XMLResource.OPTION_URI_HANDLER), "a worker with no handler of its own gets the parent's");
  }

  @Test
  public void workerGetsTheOptionsTheParentHadWhenTheOperationWasCreated() {
    ResourceSet parent = new XtextResourceSet();
    parent.getLoadOptions().put(OTHER_OPTION, Boolean.TRUE);

    ResourceSet worker = workerResourceSet(parent, () -> {
      // the builder changes the parent's options while the workers start
      parent.getLoadOptions().put(LATE_OPTION, Boolean.TRUE);
      return new XtextResourceSet();
    });

    assertSame(Boolean.TRUE, worker.getLoadOptions().get(OTHER_OPTION), "the options set before the operation was created must be copied");
    assertFalse(worker.getLoadOptions().containsKey(LATE_OPTION), "workers must not read the parent's options while the builder changes them");
  }

  /**
   * Loads one resource and returns the resource set of the worker that loaded it.
   *
   * @param parent
   *          the parent resource set, must not be {@code null}
   * @param workerResourceSets
   *          creates the worker resource sets, must not be {@code null}
   * @return the worker's resource set, never {@code null}
   */
  private static ResourceSet workerResourceSet(final ResourceSet parent, final Supplier<ResourceSet> workerResourceSets) {
    AtomicReference<ResourceSet> worker = new AtomicReference<>();
    LoadOperation operation = load(parent, workerResourceSets, List.of(SLOW_URI), -1, GENEROUS_TIMEOUT_MILLIS, (uri, localResourceSet) -> {
      worker.set(localResourceSet);
      return new ResourceImpl(uri);
    });
    try {
      operation.next();
      return worker.get();
    } finally {
      operation.cancel();
    }
  }

  private static LoadOperation load(final List<URI> uris, final int queueSize, final long timeoutMillis, final Function<URI, Resource> loadFunction) {
    return load(new ResourceSetImpl(), ResourceSetImpl::new, uris, queueSize, timeoutMillis, (uri, localResourceSet) -> loadFunction.apply(uri));
  }

  private static LoadOperation load(final ResourceSet parent, final Supplier<ResourceSet> workerResourceSets, final List<URI> uris, final int queueSize, final long timeoutMillis, final BiFunction<URI, ResourceSet, Resource> loadFunction) {
    ParallelResourceLoader loader = new ParallelResourceLoader(resourceSetProvider(workerResourceSets), new Sorter.NoSorting(), 1, queueSize) {
      @Override
      protected Resource loadResource(final URI uri, final ResourceSet localResourceSet, final ResourceSet parentResourceSet) {
        return loadFunction.apply(uri, localResourceSet);
      }
    };
    IResourceServiceProvider.Registry registry = mock(IResourceServiceProvider.Registry.class);
    Guice.createInjector(binder -> binder.bind(IResourceServiceProvider.Registry.class).toInstance(registry)).injectMembers(loader);
    loader.setTimeout(timeoutMillis, TimeUnit.MILLISECONDS);

    SourceLevelURIsAdapter.setSourceLevelUris(parent, Collections.emptySet());
    LoadOperation operation = loader.create(parent, null);
    operation.load(uris);
    return operation;
  }

  private static void assertTimesOut(final LoadOperation operation) {
    LoadOperationException timeout = assertThrows(LoadOperationException.class, operation::next);
    assertInstanceOf(TimeoutException.class, timeout.getCause());
  }

  private static IResourceSetProvider resourceSetProvider(final Supplier<ResourceSet> workerResourceSets) {
    IResourceSetProvider provider = mock(IResourceSetProvider.class);
    when(provider.get(any())).thenAnswer(invocation -> workerResourceSets.get());
    return provider;
  }

}
