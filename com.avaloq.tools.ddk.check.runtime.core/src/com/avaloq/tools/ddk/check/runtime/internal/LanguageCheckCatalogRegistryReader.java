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
package com.avaloq.tools.ddk.check.runtime.internal;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;

import com.avaloq.tools.ddk.check.runtime.registry.ICheckCatalogRegistry;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckImplDescriptor;


/**
 * Provides a RegistryReader for check catalogs. Check extensions providing a valid value
 * for the {@code catalog} attribute, are used when activating check configuration models
 * gathered from the database. That model may then link against check models provided by
 * this reader.
 */
class LanguageCheckCatalogRegistryReader extends AbstractCheckRegistryReader {

  /** The logger. */
  private static final Logger LOGGER = LogManager.getLogger(LanguageCheckCatalogRegistryReader.class);

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  protected String topLevelElementName() {
    return "validator"; //$NON-NLS-1$
  }

  @Override
  protected ICheckImplDescriptor newDescriptor(final IConfigurationElement element, final String attClass) {
    return new CheckCatalogDescriptor(element);
  }

  @Override
  String getAttribute() {
    return "catalog"; //$NON-NLS-1$
  }

  /**
   * Gets the map of languages mapped to catalog descriptor providers.
   *
   * @see CheckCatalogDescriptor
   * @return the language to catalog descriptor map
   */
  @Override
  protected ICheckCatalogRegistry getRegistry() {
    return ICheckCatalogRegistry.INSTANCE;
  }

  /**
   * Instantiates a new Check language registry reader. TODO Document this method.
   *
   * @param extensionRegistry
   *          the extension registry
   * @param symbolicBundleName
   *          the symbolic bundle name
   */
  LanguageCheckCatalogRegistryReader(final IExtensionRegistry extensionRegistry, final String symbolicBundleName) {
    super(extensionRegistry, symbolicBundleName, "check"); //$NON-NLS-1$
  }

  @Override
  protected void logMissingAttribute(final IConfigurationElement element, final String attributeName) {
    if ("catalog".equals(attributeName)) { //$NON-NLS-1$
      LOGGER.debug("The optional attribute 'catalog' is not defined"); //$NON-NLS-1$
      return; // attribute is currently optional, message "The required attribute 'catalog' not defined" is inappropriate
    }
    super.logMissingAttribute(element, attributeName);
  }

  @Override
  protected boolean readElement(final IConfigurationElement element, final boolean add) {
    if (element.getAttribute(getAttribute()) == null) {
      return true; // the element is optional, no need to log an error
    }
    return super.readElement(element, add);
  }

}
