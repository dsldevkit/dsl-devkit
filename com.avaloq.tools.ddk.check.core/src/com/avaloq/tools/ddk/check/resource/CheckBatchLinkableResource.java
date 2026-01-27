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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.xtext.xbase.resource.BatchLinkableResource;

import com.avaloq.tools.ddk.check.runtime.configuration.IModelLocation;


public class CheckBatchLinkableResource extends BatchLinkableResource {
  public static final String MAYBE_LOCATION_DATA = "maybeLocationData"; //$NON-NLS-1$

  private IModelLocation modelLocation;

  public IModelLocation getModelLocation() {
    return modelLocation;
  }

  @Override
  protected URIConverter getURIConverter() {
    return new ExtensibleURIConverterImpl() {
      @Override
      public InputStream createInputStream(final URI uri, final Map<?, ?> options) throws IOException {
        try {
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
  public void load(final Map<?, ?> options) throws IOException {
    modelLocation = (IModelLocation) options.get(MAYBE_LOCATION_DATA);
    super.load(options);
  }
}
