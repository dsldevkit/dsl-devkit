/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.builder;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.containers.IContainerState;
import org.eclipse.xtext.resource.containers.StateBasedContainer;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;


/**
 * StateBasedContainer implementation which exposes the container handle it's associated with.
 */
public class DdkStateBasedContainer extends StateBasedContainer {

  private final IContainerState state;
  private final String handle;

  public DdkStateBasedContainer(final IResourceDescriptions descriptions, final IContainerState state, final String handle) {
    super(descriptions, state);
    this.state = state;
    this.handle = handle;
  }

  public String getHandle() {
    return handle;
  }

  @Override
  protected Iterable<IEObjectDescription> filterByURI(final Iterable<IEObjectDescription> unfiltered) {
    return Iterables.filter(unfiltered, new Predicate<IEObjectDescription>() {
      private final Collection<URI> contents = state.getContents();

      @Override
      public boolean apply(final IEObjectDescription input) {
        return contents.contains(input.getEObjectURI().trimFragment());
      }
    });
  }

  @Override
  public String toString() {
    return "DdkStateBasedContainer: " + handle; //$NON-NLS-1$
  }
}
