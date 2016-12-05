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

import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;


/**
 * Activates the Check runtime plugin.
 */
public class Activator extends Plugin {

  /** {@inheritDoc} */
  @Override
  public void start(final BundleContext context) throws Exception { // NOPMD
    super.start(context);
    initializeResourceServiceProviderRegistry(context);
  }

  /**
   * Initializes the resource service provider registry.
   * 
   * @param context
   *          the bundle context
   */
  private void initializeResourceServiceProviderRegistry(final BundleContext context) {
    IExtensionRegistry eReg = Platform.getExtensionRegistry();
    String symbolicName = context.getBundle().getSymbolicName();
    new LanguageCheckValidatorRegistryReader(eReg, symbolicName).readRegistry();
    new LanguageCheckCatalogRegistryReader(eReg, symbolicName).readRegistry();
    new LanguageCheckQuickfixRegistryReader(eReg, symbolicName).readRegistry();
  }

}
