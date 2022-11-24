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
package com.avaloq.tools.ddk.check.runtime.registry.impl;

import java.util.Collection;

import com.avaloq.tools.ddk.check.runtime.quickfix.ICoreQuickfixProvider;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckImplDescriptor;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckQuickfixRegistry;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Provider;


/**
 * Provides a registry of Check quickfix providers. Is required for the standalone builder
 * since extension points cannot be used in a non-Eclipse environment.
 */
public class CheckQuickfixImplRegistryImpl extends AbstractCheckImplDescriptorRegistry implements ICheckQuickfixRegistry {

  /** {@inheritDoc} */
  @SuppressWarnings("unchecked")
  public Collection<ICoreQuickfixProvider> getCoreQuickfixProviders(final String language) {
    Collection<ICoreQuickfixProvider> providers = Lists.newArrayList();
    for (ICheckImplDescriptor v : Sets.newHashSet(getDescriptors(language))) {
      if (v instanceof Provider<?>) {
        final ICoreQuickfixProvider provider = ((Provider<ICoreQuickfixProvider>) v).get();
        if (provider == null) { // may be null if specified target class could not be found
          removeLanguageDescriptor(language, v);
        } else {
          providers.add(provider);
        }
      }
    }
    return providers;
  }

}

