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
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.naming.QualifiedName;
import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;


/**
 * Tests the name functions cached by {@link AbstractScopeNameProvider#getNameFunctions(EClass)}.
 */
@SuppressWarnings("nls")
// CHECKSTYLE:CHECK-OFF MultipleStringLiteralsCheck
public class AbstractScopeNameProviderTest {

  private static final List<EStructuralFeature> FEATURES = List.of(EcorePackage.Literals.ENAMED_ELEMENT__NAME, EcorePackage.Literals.ECLASSIFIER__INSTANCE_CLASS_NAME);

  /** Returns a lazy result for {@code ENamedElement} and delegates other types to their first super type, as generated name providers do. */
  private static final class LazyNameProvider extends AbstractScopeNameProvider {
    @Override
    protected Iterable<INameFunction> internalGetNameFunctions(final EClass type) {
      if (EcorePackage.Literals.ENAMED_ELEMENT.equals(type)) {
        return Iterables.transform(FEATURES, NameFunctions::fromFeature);
      }
      return !type.getESuperTypes().isEmpty() ? getNameFunctions(type.getESuperTypes().get(0)) : null;
    }
  }

  /** Has no name functions of its own, so that every type uses the default names. */
  private static final class DefaultNameProvider extends AbstractScopeNameProvider {
    @Override
    protected Iterable<INameFunction> internalGetNameFunctions(final EClass type) {
      return null;
    }
  }

  private static List<INameFunction> nameFunctions(final AbstractScopeNameProvider provider, final EClass type) {
    return ImmutableList.copyOf(provider.getNameFunctions(type));
  }

  @Test
  public void testLazyResultGivesSameFunctionsOnEachCall() {
    AbstractScopeNameProvider provider = new LazyNameProvider();
    List<INameFunction> first = nameFunctions(provider, EcorePackage.Literals.ENAMED_ELEMENT);
    List<INameFunction> second = nameFunctions(provider, EcorePackage.Literals.ENAMED_ELEMENT);
    assertEquals(2, first.size());
    assertSame(first.get(0), second.get(0));
    assertSame(first.get(1), second.get(1));
  }

  @Test
  public void testDefaultNamesGiveSameFunctionsOnEachCall() {
    AbstractScopeNameProvider provider = new DefaultNameProvider();
    List<INameFunction> first = nameFunctions(provider, EcorePackage.Literals.ENAMED_ELEMENT);
    List<INameFunction> second = nameFunctions(provider, EcorePackage.Literals.ENAMED_ELEMENT);
    assertEquals(1, first.size());
    assertSame(first.get(0), second.get(0));
  }

  @Test
  public void testSuperTypeLookupGivesFunctionsOfSuperType() {
    AbstractScopeNameProvider provider = new LazyNameProvider();
    List<INameFunction> subType = nameFunctions(provider, EcorePackage.Literals.ECLASS);
    List<INameFunction> superType = nameFunctions(provider, EcorePackage.Literals.ENAMED_ELEMENT);
    assertEquals(2, subType.size());
    assertSame(superType.get(0), subType.get(0));
    assertSame(superType.get(1), subType.get(1));
  }

  @Test
  public void testNamesAreComputedInOrder() {
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName("Foo");
    eClass.setInstanceClassName("a.b.Foo");
    List<INameFunction> functions = nameFunctions(new LazyNameProvider(), EcorePackage.Literals.ENAMED_ELEMENT);
    assertEquals(QualifiedName.create("Foo"), functions.get(0).apply(eClass));
    assertEquals(QualifiedName.create("a", "b", "Foo"), functions.get(1).apply(eClass));
  }

  @Test
  public void testDefaultNamesUseNameFeature() {
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName("Foo");
    List<INameFunction> functions = nameFunctions(new DefaultNameProvider(), EcorePackage.Literals.ENAMED_ELEMENT);
    assertEquals(QualifiedName.create("Foo"), functions.get(0).apply(eClass));
  }

}
