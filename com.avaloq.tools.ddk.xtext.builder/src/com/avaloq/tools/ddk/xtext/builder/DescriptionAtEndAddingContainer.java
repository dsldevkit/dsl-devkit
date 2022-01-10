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

import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.impl.AbstractContainer;

import com.google.common.collect.Iterables;


/**
 * Like DescriptionAddingContainer, but add the description at the end of the wrapped container.
 */
public class DescriptionAtEndAddingContainer extends AbstractContainer {

  private final IResourceDescription description;
  private final IContainer delegate;

  /**
   * Wrap a given container and add a new resource description to it.
   * 
   * @param addMe
   *          the resource description.
   * @param delegate
   *          the container to be wrapped.
   */
  public DescriptionAtEndAddingContainer(final IResourceDescription addMe, final IContainer delegate) {
    this.description = addMe;
    this.delegate = delegate;
  }

  /** {@inheritDoc} */
  public Iterable<IResourceDescription> getResourceDescriptions() {
    return Iterables.concat(delegate.getResourceDescriptions(), Collections.singleton(description));
  }

  /** {@inheritDoc} */
  public IResourceDescription getResourceDescription(final URI uri) {
    if (description.getURI().equals(uri)) {
      return description;
    }
    return delegate.getResourceDescription(uri);
  }

}

