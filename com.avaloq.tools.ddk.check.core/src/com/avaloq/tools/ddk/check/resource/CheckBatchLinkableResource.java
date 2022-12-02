/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.check.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.NavigableSet;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.xtext.xbase.resource.BatchLinkableResource;

import com.avaloq.tools.ddk.check.runtime.configuration.IModelLocation;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckCatalogRegistry;


public class CheckBatchLinkableResource extends BatchLinkableResource {
  @Override
  protected URIConverter getURIConverter() {
    return new ExtensibleURIConverterImpl() {
      @Override
      public InputStream createInputStream(final URI uri, final Map<?, ?> options) throws IOException {
        try {
          IModelLocation modelLocation = getModelLocation();

          if (modelLocation == null) {
            return super.createInputStream(uri, options);
          }
          return modelLocation.getCatalogStream();
        } catch (IllegalArgumentException e) {
          return super.createInputStream(uri, options);
        }
      }
    };
  }

  @Override
  public URI getURI() {
    IModelLocation modelLocation = getModelLocation();

    if (modelLocation == null || modelLocation.getCatalogUri() == null) {
      return super.getURI();
    }

    return URI.createURI(modelLocation.getCatalogUri().toString());
  }

  private IModelLocation getModelLocation() {
    NavigableSet<IModelLocation> checkModelLocations = ICheckCatalogRegistry.INSTANCE.getAllCheckModelLocations();
    String modelLocationQuery = uri.toString().replace("platform:/resource/", ""); //$NON-NLS-1$ //$NON-NLS-2$

    IModelLocation modelLocation = checkModelLocations.stream() //
        .filter(ml -> ml.getCatalogPath().contains(modelLocationQuery)) //
        .findFirst().orElse(null);
    return modelLocation;
  }
}
