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
package com.avaloq.tools.ddk.check.runtime.internal;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;

import com.avaloq.tools.ddk.check.runtime.configuration.BundleAwareModelLocation;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckImplDescriptor;
import com.google.inject.Provider;


/**
 * A catalog descriptor is a provider which will return a resource referred to by the {@code catalog} attribute of the check extension. If this attribute is not
 * defined in an extension or no valid value is provided, {@code null} is returned.
 */
public class CheckCatalogDescriptor implements Provider<BundleAwareModelLocation>, ICheckImplDescriptor {

  private static final Logger LOG = Logger.getLogger(CheckCatalogDescriptor.class);

  private static final String CATALOG_ATTRIBUTE = "catalog"; //$NON-NLS-1$
  private final IConfigurationElement element;
  private BundleAwareModelLocation catalog;

  public CheckCatalogDescriptor(final IConfigurationElement element) {
    this.element = element;
  }

  public IConfigurationElement getElement() {
    return element;
  }

  /**
   * Gets the check model location object to be associated with this plugin extension
   * as specified in the {@code plugin.xml} file.
   * <p>
   * Will log error and return {@code null} if no implementation could be found.
   * </p>
   * 
   * @return the model location or {@code null} (may happen if the plugin
   *         extension has not been correctly configured)
   */
  public synchronized BundleAwareModelLocation get() {
    if (this.catalog == null && element.isValid()) { // the element may have become invalid if the plugin was updated or uninstalled
      this.catalog = getLocation();
    }
    return this.catalog;
  }

  /**
   * Gets the check model location instance.
   * 
   * @return the check model location instance or {@code null} if none could be defined
   */
  private BundleAwareModelLocation getLocation() {
    final String bundleName = element.getDeclaringExtension().getContributor().getName();
    final Bundle bundle = Platform.getBundle(bundleName);
    final String catalogPath = element.getAttribute(CATALOG_ATTRIBUTE);
    if (bundleName != null && bundle != null) {
      return new BundleAwareModelLocation(bundle.getResource(catalogPath), bundle);
    }
    LOG.error(NLS.bind("Could not find a catalog resource in {0} of bundle {1}", catalogPath, bundleName)); //$NON-NLS-1$
    return null;
  }

}

