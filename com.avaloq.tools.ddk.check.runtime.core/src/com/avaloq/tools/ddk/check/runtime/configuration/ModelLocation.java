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

import java.net.URL;


/**
 * A model location which is not bundle aware.
 */
public class ModelLocation extends AbstractModelLocation {

  private final String catalogPath;

  public ModelLocation(final URL catalogUrl, final String catalogPath) {
    super(catalogUrl);
    this.catalogPath = catalogPath;
  }

  @Override
  public String getCatalogPath() {
    return catalogPath;
  }

}
