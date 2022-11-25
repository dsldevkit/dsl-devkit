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

import com.avaloq.tools.ddk.check.runtime.registry.ICheckImplDescriptor;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckImplDescriptorRegistry;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;


/**
 * Implementation of {@link ICheckImplDescriptorRegistry}.
 */
public abstract class AbstractCheckImplDescriptorRegistry implements ICheckImplDescriptorRegistry {

  private final Multimap<String, ICheckImplDescriptor> map = HashMultimap.create();

  /** {@inheritDoc} */
  public Multimap<String, ICheckImplDescriptor> getLanguageToDescriptorMap() {
    return HashMultimap.create(map);
  }

  // TODO Remove Prodiver to Descriptor and inherit from ICheckImplDescriptor

  /** {@inheritDoc} */
  public Collection<ICheckImplDescriptor> getDescriptors(final String language) {
    return map.get(language);
  }

  /** {@inheritDoc} */
  public Collection<ICheckImplDescriptor> getDescriptors() {
    return map.values();
  }

  /** {@inheritDoc} */
  public void registerCatalogDescriptor(final String language, final ICheckImplDescriptor descriptor) {
    map.put(language, descriptor);
  }

  /** {@inheritDoc} */
  public boolean removeLanguageDescriptor(final String language, final ICheckImplDescriptor descriptor) {
    return map.remove(language, descriptor);
  }

  /** {@inheritDoc} */
  public boolean removeLanguageDescriptor(final ICheckImplDescriptor descriptor) {
    return map.remove(null, descriptor);
  }

}

