/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check.runtime.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.osgi.util.NLS;


/**
 * A common model location implementation.
 */
public abstract class AbstractModelLocation implements IModelLocation {

  private static final Logger LOG = LogManager.getLogger(AbstractModelLocation.class);

  private final URL catalogUrl;

  public AbstractModelLocation(final URL catalogUrl) {
    if (catalogUrl == null) {
      throw new IllegalArgumentException("Catalog path URL may not be null"); //$NON-NLS-1$
    }
    this.catalogUrl = catalogUrl;
  }

  /** {@inheritDoc} */
  @Override
  public InputStream getCatalogStream() {
    InputStream stream = null;
    try {
      stream = catalogUrl.openStream();
    } catch (IOException e) {
      LOG.error("Could not open stream for " + catalogUrl, e); //$NON-NLS-1$
    }
    return stream;
  }

  /** {@inheritDoc} */
  @Override
  public String getCatalogPath() {
    return catalogUrl.getPath();
  }

  /** {@inheritDoc} */
  @Override
  public URL getCatalogUrl() {
    return catalogUrl;
  }

  /** {@inheritDoc} */
  @Override
  public URI getCatalogUri() {
    try {
      return catalogUrl.toURI();
    } catch (URISyntaxException e) {
      throw new IllegalStateException(NLS.bind("URL of catalog {0} cannot be converted to URI", catalogUrl.toString())); //$NON-NLS-1$
    }
  }

}
