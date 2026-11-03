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
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.scoping.IScope;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.naming.QualifiedNamePattern;
import com.google.common.collect.ImmutableList;


/**
 * Tests {@link AbstractPolymorphicScopeProvider#newPrefixedContainerScope(String, IScope, EObject, ContainerQuery.Builder, Resource, String, boolean, Iterable, boolean)}.
 */
@SuppressWarnings("nls")
public class PrefixedContainerScopeTest {

  private static final Iterable<INameFunction> NAME_FUNCTIONS = ImmutableList.of(NameFunctions.exportNameFunction());

  /**
   * Scope provider seeing a fixed list of containers.
   */
  private static final class TestScopeProvider extends AbstractPolymorphicScopeProvider {

    private final List<IContainer> containers;

    TestScopeProvider(final List<IContainer> containers) {
      this.containers = containers;
    }

    /**
     * Creates a prefixed container scope with {@link IScope#NULLSCOPE} as outer scope.
     *
     * @param query
     *          the container query
     * @param prefix
     *          the prefix
     * @return the scope
     */
    IScope createScope(final ContainerQuery.Builder query, final String prefix) {
      return newPrefixedContainerScope("id", IScope.NULLSCOPE, (EObject) null, query, null, prefix, false, NAME_FUNCTIONS, false);
    }

    @Override
    protected List<IContainer> getVisibleContainers(final EObject context, final Resource originalResource) {
      return containers;
    }

    @Override
    protected IScope doGetScope(final EObject context, final EReference reference, final String scopeName, final Resource originalResource) {
      return null;
    }

    @Override
    protected IScope doGetScope(final EObject context, final EClass type, final String scopeName, final Resource originalResource) {
      return null;
    }
  }

  private static ContainerQuery.Builder newQuery() {
    return new ContainerQuery.Builder(new IDomain.NullMapper(), EcorePackage.Literals.ECLASS);
  }

  @Test
  public void testScopePerVisibleContainer() {
    ContainerQuery.Builder query = newQuery();
    TestScopeProvider provider = new TestScopeProvider(List.of(mock(IContainer.class), mock(IContainer.class)));
    IScope result = provider.createScope(query, "a.b");
    assertInstanceOf(PrefixedContainerBasedScope.class, result);
    assertEquals(QualifiedNamePattern.create(QualifiedName.create("a", "b", "*")), query.getNamePattern());
  }

  @Test
  public void testEmptyPrefixWithoutVisibleContainers() {
    ContainerQuery.Builder query = newQuery();
    TestScopeProvider provider = new TestScopeProvider(List.of());
    assertSame(IScope.NULLSCOPE, provider.createScope(query, ""));
    assertSame(IScope.NULLSCOPE, provider.createScope(query, null));
  }

}
