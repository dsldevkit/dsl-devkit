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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceServiceProvider;

import com.avaloq.tools.ddk.xtext.build.ILanguageSpecificResourcePostProcessor;
import com.avaloq.tools.ddk.xtext.builder.tracing.ResourcePostProcessingEvent;
import com.avaloq.tools.ddk.xtext.tracing.IExecutionDataCollector;
import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * A default processor that uses the {@link ILanguageSpecificResourcePostProcessor} obtained via the {@link IResourceServiceProvider.Registry} to post-process a
 * resource.
 */
@Singleton
public class DefaultResourcePostProcessor implements IResourcePostProcessor {

  @Inject
  private IExecutionDataCollector dataCollector;

  @Inject
  private IResourceServiceProvider.Registry resourceServiceProviderRegistry;

  /** {@inheritDoc} */
  @Override
  public void process(final Delta delta, final ResourceSet resourceSet, final IProgressMonitor monitor) {
    URI uri = delta.getUri();
    ILanguageSpecificResourcePostProcessor postProcessor = getPostProcessor(uri);
    if (postProcessor != null) {
      try {
        dataCollector.started(ResourcePostProcessingEvent.class, uri);
        SubMonitor subMonitor = SubMonitor.convert(monitor, 1);
        if (delta.getNew() != null) {
          if (resourceSet == null) {
            throw new IllegalArgumentException("resourceSet may not be null for changed resources."); //$NON-NLS-1$
          }
          postProcessor.processChanged(uri, resourceSet.getResource(uri, true), resourceSet, subMonitor.newChild(1));
        } else {
          postProcessor.processDeleted(uri, resourceSet, subMonitor.newChild(1));
        }
      } finally {
        dataCollector.ended(ResourcePostProcessingEvent.class);
      }
    }
  }

  /**
   * Finds the language-specific resource post-processor, if any.
   *
   * @param uri
   *          to get the post processor for
   * @return the post processor or {@code null} if none found
   */
  protected ILanguageSpecificResourcePostProcessor getPostProcessor(final URI uri) {
    IResourceServiceProvider provider = resourceServiceProviderRegistry.getResourceServiceProvider(uri);
    if (provider != null) {
      return provider.get(ILanguageSpecificResourcePostProcessor.class);
    }
    return null;
  }
}

