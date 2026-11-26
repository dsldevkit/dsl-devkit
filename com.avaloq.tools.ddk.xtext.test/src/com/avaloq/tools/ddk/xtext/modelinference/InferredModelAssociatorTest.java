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
package com.avaloq.tools.ddk.xtext.modelinference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.DerivedStateAwareResource;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.modelinference.InferredModelAssociator.Adapter;


@SuppressWarnings("nls")
public class InferredModelAssociatorTest {

  /** Associator whose {@link #getOrInstall(Resource)} returns an adapter that is not installed on the given resource. */
  private static final class RedirectingAssociator extends InferredModelAssociator {
    private final Adapter redirected;

    RedirectingAssociator(final Adapter redirected) {
      this.redirected = redirected;
    }

    @Override
    protected Adapter getOrInstall(final Resource resource) {
      return redirected;
    }
  }

  private static void fill(final Adapter adapter) {
    EClass source = EcoreFactory.eINSTANCE.createEClass();
    EClass inferred = EcoreFactory.eINSTANCE.createEClass();
    adapter.getSourceToInferredModelMap().computeIfAbsent(source, k -> new ArrayDeque<EObject>()).add(inferred);
    adapter.getInferredModelToSourceMap().computeIfAbsent(inferred, k -> new ArrayDeque<EObject>()).add(source);
  }

  @Test
  void discardKeepsAssociationsOfAnotherResourcesAdapter() {
    Adapter redirected = new Adapter();
    fill(redirected);
    DerivedStateAwareResource resource = new DerivedStateAwareResource();

    new RedirectingAssociator(redirected).discardDerivedState(resource);

    assertEquals(1, redirected.getSourceToInferredModelMap().size());
    assertEquals(1, redirected.getInferredModelToSourceMap().size());
  }

  @Test
  void discardClearsAdapterInstalledOnResource() {
    DerivedStateAwareResource resource = new DerivedStateAwareResource();
    Adapter own = new Adapter();
    fill(own);
    resource.eAdapters().add(own);

    new InferredModelAssociator().discardDerivedState(resource);

    assertTrue(own.getSourceToInferredModelMap().isEmpty());
    assertTrue(own.getInferredModelToSourceMap().isEmpty());
  }
}
