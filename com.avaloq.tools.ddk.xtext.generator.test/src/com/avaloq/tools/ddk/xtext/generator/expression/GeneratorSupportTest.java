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
package com.avaloq.tools.ddk.xtext.generator.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorSupport;


/**
 * Tests the lifetime of the values memoized through {@link GeneratorSupport#memoize(Object, java.util.function.Supplier)}.
 */
@SuppressWarnings("nls")
public class GeneratorSupportTest {

  private static final String KEY = "key";
  private static final String NULL_KEY = "null";

  private final GeneratorSupport generatorSupport = new GeneratorSupport();

  /** A resource outside of the workspace, so that no project and no class loader for its classpath are involved. */
  private final Resource resource = new ResourceImpl(URI.createURI("memory:/model.scope"));

  private final AtomicInteger computations = new AtomicInteger();

  private Integer compute() {
    return computations.incrementAndGet();
  }

  private Integer computeNull() {
    computations.incrementAndGet();
    return null;
  }

  @Test
  void testMemoizeOutsideOfResourceLoaderComputesOnEveryRequest() {
    assertEquals(1, generatorSupport.memoize(KEY, this::compute));
    assertEquals(2, generatorSupport.memoize(KEY, this::compute));
  }

  @Test
  void testMemoizeWithinResourceLoaderComputesOnce() {
    generatorSupport.executeWithProjectResourceLoaderOf(resource, () -> {
      assertEquals(1, generatorSupport.memoize(KEY, this::compute));
      assertEquals(1, generatorSupport.memoize(KEY, this::compute));
      assertNull(generatorSupport.memoize(NULL_KEY, this::computeNull));
      assertNull(generatorSupport.memoize(NULL_KEY, this::computeNull));
    });
    assertEquals(2, computations.get());
  }

  @Test
  void testMemoizedValuesAreDroppedWhenResourceLoaderCallReturns() {
    generatorSupport.executeWithProjectResourceLoaderOf(resource, () -> generatorSupport.memoize(KEY, this::compute));
    generatorSupport.executeWithProjectResourceLoaderOf(resource, () -> assertEquals(2, generatorSupport.memoize(KEY, this::compute)));
    assertEquals(3, generatorSupport.memoize(KEY, this::compute));
  }

  @Test
  void testNestedResourceLoaderCallForSameProjectSharesMemoizedValues() {
    generatorSupport.executeWithProjectResourceLoaderOf(resource, () -> {
      generatorSupport.memoize(KEY, this::compute);
      generatorSupport.executeWithProjectResourceLoaderOf(resource, () -> assertEquals(1, generatorSupport.memoize(KEY, this::compute)));
      assertEquals(1, generatorSupport.memoize(KEY, this::compute));
    });
    assertEquals(1, computations.get());
  }

}
