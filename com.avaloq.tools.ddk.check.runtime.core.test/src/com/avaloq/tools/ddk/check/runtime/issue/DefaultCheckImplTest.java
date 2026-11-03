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
package com.avaloq.tools.ddk.check.runtime.issue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.xtext.validation.Check;
import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Guice;
import com.google.inject.Injector;


/**
 * Tests the per-type check method cache of {@link DefaultCheckImpl}.
 */
public class DefaultCheckImplTest {

  private static final String NO_ERROR_REPORTED = "No check reported an error"; //$NON-NLS-1$
  private static final int THREADS = 8;
  private static final int ROUNDS = 50;
  private static final int CLASSIFIERS = 3;
  private static final int FEATURES = 2;
  private static final int NAMED_ELEMENTS = 10;
  private static final int VALIDATIONS_PER_THREAD = 20;

  private final Injector injector = Guice.createInjector();

  /**
   * Check catalog implementation counting the invocations of its check methods.
   */
  @SuppressWarnings("PMD.UnusedFormalParameter")
  public static final class CountingCheckImpl extends DefaultCheckImpl {
    private final AtomicInteger classifierChecks = new AtomicInteger();
    private final AtomicInteger featureChecks = new AtomicInteger();
    private final AtomicInteger namedElementChecks = new AtomicInteger();

    @Check
    public void checkClassifier(final EClassifier classifier) {
      classifierChecks.incrementAndGet();
    }

    @Check
    public void checkFeature(final EStructuralFeature feature) {
      featureChecks.incrementAndGet();
    }

    @Check
    public void checkNamedElement(final ENamedElement element) {
      namedElementChecks.incrementAndGet();
    }

    @Override
    public String getQualifiedCatalogName() {
      return "Counting"; //$NON-NLS-1$
    }

    @Override
    public ImmutableMap<String, String> getIssueCodeToLabelMap() {
      return ImmutableMap.of();
    }
  }

  /**
   * Check catalog implementation with a check method that always fails, next to one counting its invocations.
   */
  @SuppressWarnings("PMD.UnusedFormalParameter")
  public static final class FailingCheckImpl extends DefaultCheckImpl {
    private final AtomicInteger failingChecks = new AtomicInteger();
    private final AtomicInteger namedElementChecks = new AtomicInteger();

    @Check
    public void checkClassifier(final EClassifier classifier) {
      failingChecks.incrementAndGet();
      throw new IllegalStateException("Failing check"); //$NON-NLS-1$
    }

    @Check
    public void checkNamedElement(final ENamedElement element) {
      namedElementChecks.incrementAndGet();
    }

    @Override
    public String getQualifiedCatalogName() {
      return "Failing"; //$NON-NLS-1$
    }

    @Override
    public ImmutableMap<String, String> getIssueCodeToLabelMap() {
      return ImmutableMap.of();
    }
  }

  private static List<EObject> createObjects() {
    EcoreFactory factory = EcoreFactory.eINSTANCE;
    return List.of(factory.createEClass(), factory.createEDataType(), factory.createEEnum(), factory.createEAttribute(), factory.createEReference(), factory.createEPackage(), factory.createEOperation(), factory.createEParameter(), factory.createEEnumLiteral(), factory.createETypeParameter(), factory.createEAnnotation(), factory.createEGenericType());
  }

  @Test
  public void testConcurrentValidationOfDifferentTypes() throws Exception {
    List<EObject> objects = createObjects();
    ExecutorService executor = Executors.newFixedThreadPool(THREADS);
    try {
      for (int round = 0; round < ROUNDS; round++) {
        CountingCheckImpl impl = new CountingCheckImpl();
        injector.injectMembers(impl);
        CyclicBarrier barrier = new CyclicBarrier(THREADS);
        List<Callable<Boolean>> tasks = new ArrayList<>(THREADS);
        for (int thread = 0; thread < THREADS; thread++) {
          final int offset = thread;
          tasks.add(() -> {
            barrier.await();
            boolean valid = true;
            for (int i = 0; i < objects.size(); i++) {
              EObject object = objects.get((i + offset) % objects.size());
              valid &= impl.validate(object.eClass(), object, null, new HashMap<>());
            }
            return valid;
          });
        }
        for (Future<Boolean> result : executor.invokeAll(tasks)) {
          assertTrue(result.get(), NO_ERROR_REPORTED);
        }
        assertEquals(THREADS * CLASSIFIERS, impl.classifierChecks.get(), "Classifier checks");
        assertEquals(THREADS * FEATURES, impl.featureChecks.get(), "Feature checks");
        assertEquals(THREADS * NAMED_ELEMENTS, impl.namedElementChecks.get(), "Named element checks");
      }
    } finally {
      executor.shutdownNow();
    }
  }

  @Test
  public void testConcurrentRemovalOfFailingCheckMethod() throws Exception {
    ExecutorService executor = Executors.newFixedThreadPool(THREADS);
    try {
      for (int round = 0; round < ROUNDS; round++) {
        FailingCheckImpl impl = new FailingCheckImpl();
        injector.injectMembers(impl);
        CyclicBarrier barrier = new CyclicBarrier(THREADS);
        List<Callable<Boolean>> tasks = new ArrayList<>(THREADS);
        for (int thread = 0; thread < THREADS; thread++) {
          tasks.add(() -> {
            barrier.await();
            boolean valid = true;
            for (int i = 0; i < VALIDATIONS_PER_THREAD; i++) {
              EObject object = EcoreFactory.eINSTANCE.createEClass();
              valid &= impl.validate(object.eClass(), object, null, new HashMap<>());
            }
            return valid;
          });
        }
        for (Future<Boolean> result : executor.invokeAll(tasks)) {
          assertTrue(result.get(), NO_ERROR_REPORTED);
        }
        assertEquals(THREADS * VALIDATIONS_PER_THREAD, impl.namedElementChecks.get(), "Every validation ran the healthy check");
        int failures = impl.failingChecks.get();
        assertNotEquals(0, failures, "The failing check ran at least once");

        EObject object = EcoreFactory.eINSTANCE.createEClass();
        impl.validate(object.eClass(), object, null, new HashMap<>());
        assertEquals(failures, impl.failingChecks.get(), "The failing check was removed");
      }
    } finally {
      executor.shutdownNow();
    }
  }

}
