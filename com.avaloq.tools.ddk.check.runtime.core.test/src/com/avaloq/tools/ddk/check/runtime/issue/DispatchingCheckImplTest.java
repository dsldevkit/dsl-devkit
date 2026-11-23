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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.xtext.validation.CheckMode;
import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Guice;


/**
 * Tests the execution of context methods by {@link DispatchingCheckImpl}.
 */
public class DispatchingCheckImplTest {

  private static final int VALIDATIONS = 3;

  /**
   * Check catalog implementation with a context method that always fails, next to healthy ones, in the form generated for catalogs.
   */
  public static final class FailingCheckImpl extends DispatchingCheckImpl {
    private final AtomicInteger failingChecks = new AtomicInteger();
    private final List<EClass> healthyTargets = new ArrayList<>();
    private final AtomicInteger legacyChecks = new AtomicInteger();
    private final AtomicInteger loggedFailures = new AtomicInteger();

    @Override
    @SuppressWarnings("deprecation")
    protected void validate(final CheckMode checkMode, final EObject object, final DiagnosticCollector diagnosticCollector) {
      if (object instanceof final EClass castObject) {
        validate("failingEClass", "Failing.failingEClass", object, castObject, //$NON-NLS-1$ //$NON-NLS-2$
            (catalog, target, collector) -> ((FailingCheckImpl) catalog).failingEClass(target, collector), diagnosticCollector);
        validate("healthyEClass", "Failing.healthyEClass", object, castObject, //$NON-NLS-1$ //$NON-NLS-2$
            (catalog, target, collector) -> ((FailingCheckImpl) catalog).healthyEClass(target, collector), diagnosticCollector);
      }
      validate("legacyEObject", "Failing.legacyEObject", object, legacyChecks::incrementAndGet, diagnosticCollector); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @SuppressWarnings("PMD.UnusedFormalParameter")
    private void failingEClass(final EClass target, final DiagnosticCollector diagnosticCollector) {
      failingChecks.incrementAndGet();
      throw new IllegalStateException("Failing check"); //$NON-NLS-1$
    }

    @SuppressWarnings("PMD.UnusedFormalParameter")
    private void healthyEClass(final EClass target, final DiagnosticCollector diagnosticCollector) {
      healthyTargets.add(target);
    }

    @Override
    protected void logCheckMethodFailure(final String rule, final EObject object, final Exception e) {
      loggedFailures.incrementAndGet();
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

  @Test
  public void testFailingContextMethodIsDisabledAndLoggedOnce() {
    FailingCheckImpl impl = new FailingCheckImpl();
    Guice.createInjector().injectMembers(impl);
    List<EClass> objects = new ArrayList<>();
    for (int i = 0; i < VALIDATIONS; i++) {
      EClass object = EcoreFactory.eINSTANCE.createEClass();
      objects.add(object);
      assertTrue(impl.validate(object.eClass(), object, null, new HashMap<>()), "No check reported an error"); //$NON-NLS-1$
    }
    assertEquals(1, impl.failingChecks.get(), "The failing check ran once"); //$NON-NLS-1$
    assertEquals(1, impl.loggedFailures.get(), "The failure was logged once"); //$NON-NLS-1$
    assertEquals(objects, impl.healthyTargets, "The healthy check ran on every object"); //$NON-NLS-1$
    assertEquals(VALIDATIONS, impl.legacyChecks.get(), "The check in the form of older catalogs ran in every validation"); //$NON-NLS-1$
  }

}
