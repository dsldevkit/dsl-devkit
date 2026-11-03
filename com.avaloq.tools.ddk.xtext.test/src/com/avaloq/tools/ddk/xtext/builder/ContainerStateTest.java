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
package com.avaloq.tools.ddk.xtext.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.containers.IAllContainersState;
import org.junit.jupiter.api.Test;


/**
 * Tests that the containers created by {@link CachingStateBasedContainerManager} answer membership through {@link IAllContainersState#containsURI(String, URI)}.
 */
@SuppressWarnings("nls")
public class ContainerStateTest {

  private static final String ROOT = "root";
  private static final URI CONTAINED = URI.createURI("platform:/resource/project/a.test");
  private static final URI NOT_CONTAINED = URI.createURI("platform:/resource/project/b.test");

  @Test
  public void testContainsMatchesContainedUris() {
    CountingState state = new CountingState(false);
    IContainer container = createContainer(state);
    assertTrue(container.hasResourceDescription(CONTAINED));
    assertFalse(container.hasResourceDescription(NOT_CONTAINED));
  }

  @Test
  public void testContainsDelegatesToContainsUri() {
    CountingState state = new CountingState(true);
    IContainer container = createContainer(state);
    assertTrue(container.hasResourceDescription(CONTAINED));
    assertFalse(container.hasResourceDescription(NOT_CONTAINED));
    assertEquals(2, state.containsUriCalls);
    assertEquals(0, state.containedUrisCalls);
  }

  private static IContainer createContainer(final IAllContainersState state) {
    TestContainerManager manager = new TestContainerManager();
    manager.setStateProvider(descriptions -> state);
    return manager.create(ROOT, mock(IResourceDescriptions.class));
  }

  /**
   * Exposes the protected container factory method.
   */
  private static final class TestContainerManager extends CachingStateBasedContainerManager {
    IContainer create(final String handle, final IResourceDescriptions descriptions) {
      return createContainer(handle, descriptions);
    }
  }

  /**
   * Container state with one contained URI which counts the membership queries it receives.
   */
  private static final class CountingState implements IAllContainersState {
    private final boolean overrideContainsUri;
    private int containsUriCalls;
    private int containedUrisCalls;

    CountingState(final boolean overrideContainsUri) {
      this.overrideContainsUri = overrideContainsUri;
    }

    @Override
    public boolean isEmpty(final String containerHandle) {
      return !ROOT.equals(containerHandle);
    }

    @Override
    public List<String> getVisibleContainerHandles(final String handle) {
      return List.of(ROOT);
    }

    @Override
    public Collection<URI> getContainedURIs(final String containerHandle) {
      containedUrisCalls++;
      return ROOT.equals(containerHandle) ? Set.of(CONTAINED) : Set.of();
    }

    @Override
    public String getContainerHandle(final URI uri) {
      return ROOT;
    }

    @Override
    public boolean containsURI(final String containerHandle, final URI candidateURI) {
      if (!overrideContainsUri) {
        return IAllContainersState.super.containsURI(containerHandle, candidateURI);
      }
      containsUriCalls++;
      return ROOT.equals(containerHandle) && CONTAINED.equals(candidateURI);
    }
  }

}
