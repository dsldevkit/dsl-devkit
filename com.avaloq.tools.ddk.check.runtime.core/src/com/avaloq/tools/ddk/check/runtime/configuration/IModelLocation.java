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
package com.avaloq.tools.ddk.check.runtime.configuration;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;


/**
 * A model location instance corresponds to an entry in the check catalog registry,
 * which in turn is created for every validator. An instance provides the check catalog
 * plug-in relative path to the check file that was used to generate the validator (see {@link #getCatalogPath()}). Also, the check model contents can be
 * retrieved as an
 * input stream ({@link #getCatalogStream()}).
 */
public interface IModelLocation extends Comparable<IModelLocation> {

  /**
   * Gets the catalog stream.
   *
   * @return the catalog stream
   */
  InputStream getCatalogStream();

  /**
   * Gets the catalog path.
   *
   * @return the catalog path
   */
  String getCatalogPath();

  /**
   * Gets the catalog URL.
   *
   * @return the catalog URL
   */
  URL getCatalogUrl();

  /**
   * Gets the catalog URI.
   *
   * @return the catalog URI
   */
  URI getCatalogUri();

  @Override
  default int compareTo(final IModelLocation o) {
    return getCatalogPath().compareTo(o.getCatalogPath());
  }

}
