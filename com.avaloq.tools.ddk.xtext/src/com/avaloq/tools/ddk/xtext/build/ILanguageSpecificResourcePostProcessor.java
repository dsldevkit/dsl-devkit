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
package com.avaloq.tools.ddk.xtext.build;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.google.inject.ImplementedBy;


/**
 * A language-specific resource post-processor. Called by the builder when it determines that a resource was deleted, or
 * after a changed resource has been completely processed (including validation) in the second build phase.
 */
@ImplementedBy(ILanguageSpecificResourcePostProcessor.NullResourcePostProcessor.class)
public interface ILanguageSpecificResourcePostProcessor {

  /**
   * Process a changed resource.
   *
   * @param uri
   *          of the resource
   * @param resource
   *          that changed
   * @param resourceSet
   *          to use for loading other resources, if needed. Equal to resource.getResourceSet().
   * @param monitor
   *          to report progress and to use for cancellation.
   */
  void processChanged(URI uri, Resource resource, ResourceSet resourceSet, IProgressMonitor monitor);

  /**
   * Process a deleted resource.
   *
   * @param uri
   *          of the resource that was deleted
   * @param resourceSet
   *          to use for loading other resources, if needed. May be {@code null}.
   * @param monitor
   *          to report progress and to use for cancellation.
   */
  void processDeleted(URI uri, ResourceSet resourceSet, IProgressMonitor monitor);

  /**
   * A language specific resource post processor that does nothing.
   */
  class NullResourcePostProcessor implements ILanguageSpecificResourcePostProcessor {

    /** {@inheritDoc} */
    @Override
    public void processChanged(final URI uri, final Resource resource, final ResourceSet resourceSet, final IProgressMonitor monitor) {
      // Nothing to do.
    }

    /** {@inheritDoc} */
    @Override
    public void processDeleted(final URI uri, final ResourceSet resourceSet, final IProgressMonitor monitor) {
      // Nothing to do.
    }

  }
}
