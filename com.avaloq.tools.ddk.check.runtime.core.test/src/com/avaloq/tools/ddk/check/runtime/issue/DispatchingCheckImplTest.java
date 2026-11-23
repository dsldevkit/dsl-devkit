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

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
   * Check catalog implementation with a context method that always fails, next to a healthy one.
   */
  public static final class FailingCheckImpl extends DispatchingCheckImpl {
    private final AtomicInteger failingChecks = new AtomicInteger();
    private final AtomicInteger healthyChecks = new AtomicInteger();
    private final AtomicInteger loggedFailures = new AtomicInteger();

    @Override
    protected void validate(final CheckMode checkMode, final EObject object, final DiagnosticCollector diagnosticCollector) {
      validate("failingEObject", "Failing.failingEObject", object, () -> { //$NON-NLS-1$ //$NON-NLS-2$
        failingChecks.incrementAndGet();
        throw new IllegalStateException("Failing check"); //$NON-NLS-1$
      }, diagnosticCollector);
      validate("healthyEObject", "Failing.healthyEObject", object, healthyChecks::incrementAndGet, diagnosticCollector); //$NON-NLS-1$ //$NON-NLS-2$
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
    for (int i = 0; i < VALIDATIONS; i++) {
      EObject object = EcoreFactory.eINSTANCE.createEClass();
      assertTrue(impl.validate(object.eClass(), object, null, new HashMap<>()), "No check reported an error"); //$NON-NLS-1$
    }
    assertEquals(1, impl.failingChecks.get(), "The failing check ran once"); //$NON-NLS-1$
    assertEquals(1, impl.loggedFailures.get(), "The failure was logged once"); //$NON-NLS-1$
    assertEquals(VALIDATIONS, impl.healthyChecks.get(), "The healthy check ran in every validation"); //$NON-NLS-1$
  }

}
