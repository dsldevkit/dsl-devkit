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
package com.avaloq.tools.ddk.xtext.resource;

import static com.avaloq.tools.ddk.xtext.resource.AbstractCachingResourceDescriptionManagerTest.description;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.containers.IAllContainersState;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionDelta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescriptions2;
import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescriptions2.ReferenceMatchPolicy;


/**
 * Tests {@link AbstractCachingResourceDescriptionManager#getAffectedResources} for added resources and objects.
 */
@SuppressWarnings("nls")
public class AbstractCachingResourceDescriptionManagerAffectedResourcesTest {

  private static final String CHANGED_URI = "foo:/changed.foo";

  private final TestManager manager = new TestManager();

  private final IResourceDescription referencing = description("foo:/referencing.foo");
  private final IResourceDescriptions2 index = mock(IResourceDescriptions2.class);
  private final IContainer.Manager containerManager = mock(IContainer.Manager.class);
  private final IContainer notContaining = mock(IContainer.class);
  private final IContainer containing = mock(IContainer.class);
  private final IContainer alsoContaining = mock(IContainer.class);

  @BeforeEach
  public void setUp() throws ReflectiveOperationException {
    IAllContainersState containersState = mock(IAllContainersState.class);
    when(containersState.getContainerHandle(any())).thenReturn("handle");
    IAllContainersState.Provider containersStateProvider = mock(IAllContainersState.Provider.class);
    when(containersStateProvider.get(index)).thenReturn(containersState);
    Field field = AbstractCachingResourceDescriptionManager.class.getDeclaredField("containersStateProvider");
    field.setAccessible(true);
    field.set(manager, containersStateProvider);
    manager.setContainerManager(containerManager);

    when(index.findAllReferencingResources(any(), eq(ReferenceMatchPolicy.REFERENCES))).thenReturn(List.of());
    when(index.findExactReferencingResources(any(), eq(ReferenceMatchPolicy.REFERENCES))).thenReturn(List.of());
    when(index.findAllReferencingResources(any(), eq(ReferenceMatchPolicy.ALL))).thenReturn(List.of());
    when(index.findExactReferencingResources(any(), eq(ReferenceMatchPolicy.ALL))).thenReturn(List.of());
    when(containing.getResourceDescription(referencing.getURI())).thenReturn(referencing);
    when(alsoContaining.getResourceDescription(referencing.getURI())).thenReturn(referencing);
    when(containerManager.getVisibleContainers(referencing, index)).thenReturn(List.of(notContaining, containing, alsoContaining));
  }

  @Test
  public void testAddedResourceStopsAtFirstContainingContainer() {
    Delta delta = new DefaultResourceDescriptionDelta(null, description(CHANGED_URI, "a"));
    when(index.findAllReferencingResources(any(), eq(ReferenceMatchPolicy.ALL))).thenReturn(List.of(referencing));

    assertEquals(Set.of(referencing.getURI()), manager.affectedResources(List.of(delta), uri -> true, index));
    verifyContainersQueriedUntilFirstMatch();
  }

  @Test
  public void testAddedObjectStopsAtFirstContainingContainer() {
    Delta delta = new ResourceDescriptionDelta(description(CHANGED_URI, "a:1"), description(CHANGED_URI, "a:1", "b:2"), null);
    when(index.findExactReferencingResources(any(), eq(ReferenceMatchPolicy.ALL))).thenReturn(List.of(referencing));

    assertEquals(Set.of(referencing.getURI()), manager.affectedResources(List.of(delta), uri -> true, index));
    verifyContainersQueriedUntilFirstMatch();
  }

  @Test
  public void testResourceInNoContainerIsNotAffected() {
    Delta delta = new DefaultResourceDescriptionDelta(null, description(CHANGED_URI, "a"));
    when(index.findAllReferencingResources(any(), eq(ReferenceMatchPolicy.ALL))).thenReturn(List.of(referencing));
    when(containerManager.getVisibleContainers(referencing, index)).thenReturn(List.of(notContaining));

    assertEquals(Set.of(), manager.affectedResources(List.of(delta), uri -> true, index));
  }

  private void verifyContainersQueriedUntilFirstMatch() {
    verify(notContaining).getResourceDescription(referencing.getURI());
    verify(containing).getResourceDescription(referencing.getURI());
    verify(alsoContaining, never()).getResourceDescription(any());
  }

  /**
   * Manager exposing {@link AbstractCachingResourceDescriptionManager#getAffectedResources} to the test.
   */
  private static final class TestManager extends AbstractCachingResourceDescriptionManager {
    Set<URI> affectedResources(final Collection<Delta> deltas, final Predicate<URI> filter, final IResourceDescriptions2 context) {
      return getAffectedResources(deltas, filter, context);
    }
  }

}
