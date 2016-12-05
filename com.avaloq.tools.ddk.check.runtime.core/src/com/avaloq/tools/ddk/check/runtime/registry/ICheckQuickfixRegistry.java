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
package com.avaloq.tools.ddk.check.runtime.registry;

import java.util.Collection;

import com.avaloq.tools.ddk.check.runtime.quickfix.ICoreQuickfixProvider;
import com.avaloq.tools.ddk.check.runtime.registry.impl.CheckQuickfixImplRegistryImpl;


/**
 * The Check quickfix provider registry.
 */
public interface ICheckQuickfixRegistry extends ICheckImplDescriptorRegistry {

  /** The Constant INSTANCE. */
  ICheckQuickfixRegistry INSTANCE = new CheckQuickfixImplRegistryImpl();

  /**
   * Get quickfix providers for a given language.
   * 
   * @param language
   *          language name
   * @return
   *         collection of quickfix providers
   */
  Collection<ICoreQuickfixProvider> getCoreQuickfixProviders(final String language);

}

