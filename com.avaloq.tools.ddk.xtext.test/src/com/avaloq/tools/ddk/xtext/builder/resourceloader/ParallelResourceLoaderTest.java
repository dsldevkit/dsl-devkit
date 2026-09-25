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

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.eclipse.emf.common.util.URI;
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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.inject.Guice;


/**
 * Tests for {@link ParallelResourceLoader}.
 */
@SuppressWarnings("nls")
public class ParallelResourceLoaderTest {

  private static final URI SLOW_URI = URI.createURI("platform:/resource/project/slow.test");
  private static final long TIMEOUT_MILLIS = 50;

  @Disabled("Documents LDR-1, see formal/BUGS.md; enable with the fix")
  @Test
  public void timeoutKeepsPendingResultAvailable() {
    CountDownLatch loadReleased = new CountDownLatch(1);
    Resource slowResource = new ResourceImpl(SLOW_URI);
    ParallelResourceLoader loader = new ParallelResourceLoader(resourceSetProvider(), new Sorter.NoSorting(), 1, -1) {
      @Override
      protected Resource loadResource(final URI uri, final ResourceSet localResourceSet, final ResourceSet parentResourceSet) {
        Uninterruptibles.awaitUninterruptibly(loadReleased);
        return slowResource;
      }
    };
    IResourceServiceProvider.Registry registry = mock(IResourceServiceProvider.Registry.class);
    Guice.createInjector(binder -> binder.bind(IResourceServiceProvider.Registry.class).toInstance(registry)).injectMembers(loader);
    loader.setTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);

    ResourceSet parent = new ResourceSetImpl();
    SourceLevelURIsAdapter.setSourceLevelUris(parent, Collections.emptySet());
    LoadOperation operation = loader.create(parent, null);
    try {
      operation.load(List.of(SLOW_URI));

      LoadOperationException timeout = assertThrows(LoadOperationException.class, operation::next);
      assertInstanceOf(TimeoutException.class, timeout.getCause());
      assertTrue(operation.hasNext(), "a timed-out poll must not consume the pending result");

      loadReleased.countDown();
      assertSame(slowResource, operation.next().getResource());
    } finally {
      loadReleased.countDown();
      operation.cancel();
    }
  }

  private static IResourceSetProvider resourceSetProvider() {
    IResourceSetProvider provider = mock(IResourceSetProvider.class);
    when(provider.get(any())).thenAnswer(invocation -> new ResourceSetImpl());
    return provider;
  }

}
