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
package com.avaloq.tools.ddk.xtext.scoping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.util.Tuples;
import org.junit.jupiter.api.Test;


/**
 * Tests the cache keys of {@link DefaultScopeCacheKeyComputer}.
 */
@SuppressWarnings("nls")
public class DefaultScopeCacheKeyComputerTest {

  private static final String SCOPE_NAME = "scope";
  private static final EReference REFERENCE = EcorePackage.Literals.ECLASS__ESUPER_TYPES;
  private static final EClass TYPE = EcorePackage.Literals.ECLASS;

  private final DefaultScopeCacheKeyComputer computer = new DefaultScopeCacheKeyComputer() {
    @Override
    protected Object getGlobalCacheContext(final EObject context) {
      return Tuples.pair("domain", "language");
    }
  };

  @Test
  public void testHashCodeOfKeys() {
    EObject context = EcoreFactory.eINSTANCE.createEClass();
    assertEquals(Arrays.hashCode(new Object[] {REFERENCE, SCOPE_NAME, context}), computer.getCacheKey(context, REFERENCE, SCOPE_NAME).hashCode());
    assertEquals(Arrays.hashCode(new Object[] {TYPE, SCOPE_NAME, context}), computer.getCacheKey(context, TYPE, SCOPE_NAME).hashCode());
    Object globalContext = Tuples.pair("domain", "language");
    assertEquals(Arrays.hashCode(new Object[] {REFERENCE, SCOPE_NAME, globalContext}), computer.getGlobalCacheKey(context, REFERENCE, SCOPE_NAME).hashCode());
    assertEquals(Arrays.hashCode(new Object[] {TYPE, SCOPE_NAME, globalContext}), computer.getGlobalCacheKey(context, TYPE, SCOPE_NAME).hashCode());
  }

  @Test
  public void testEqualityOfKeys() {
    EObject context = EcoreFactory.eINSTANCE.createEClass();
    EObject otherContext = EcoreFactory.eINSTANCE.createEClass();
    Object key = computer.getCacheKey(context, REFERENCE, SCOPE_NAME);
    assertEquals(key, computer.getCacheKey(context, REFERENCE, SCOPE_NAME));
    assertNotEquals(key, computer.getCacheKey(otherContext, REFERENCE, SCOPE_NAME));
    assertNotEquals(key, computer.getCacheKey(context, TYPE, SCOPE_NAME));
    assertNotEquals(key, computer.getCacheKey(context, REFERENCE, "other"));
    assertEquals(computer.getGlobalCacheKey(context, REFERENCE, SCOPE_NAME), computer.getGlobalCacheKey(otherContext, REFERENCE, SCOPE_NAME));
  }

}
