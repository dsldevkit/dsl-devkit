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

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;

import com.avaloq.tools.ddk.check.runtime.registry.ICheckImplDescriptor;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckQuickfixRegistry;


/**
 * Provides a RegistryReader for the Check language. TODO Document this class.
 */
class LanguageCheckQuickfixRegistryReader extends AbstractCheckRegistryReader {

  /** The logger. */
  private static final Logger LOGGER = LogManager.getLogger(LanguageCheckQuickfixRegistryReader.class);

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  protected String topLevelElementName() {
    return "provider"; //$NON-NLS-1$
  }

  @Override
  String getAttribute() {
    return "targetClass"; //$NON-NLS-1$
  }

  @Override
  protected ICheckImplDescriptor newDescriptor(final IConfigurationElement element, final String attClass) {
    return new CheckQuickfixImplDescriptor(element, attClass);
  }

  /**
   * Gets the map of languages mapped to quickfix providers.
   *
   * @return the language to quickfix provider map
   */
  @Override
  protected ICheckQuickfixRegistry getRegistry() {
    return ICheckQuickfixRegistry.INSTANCE;
  }

  /**
   * Instantiates a new Check language registry reader. TODO Document this method.
   *
   * @param extensionRegistry
   *          the extension registry
   * @param symbolicBundleName
   *          the symbolic bundle name
   */
  LanguageCheckQuickfixRegistryReader(final IExtensionRegistry extensionRegistry, final String symbolicBundleName) {
    super(extensionRegistry, symbolicBundleName, "checkquickfix"); //$NON-NLS-1$
  }

}

