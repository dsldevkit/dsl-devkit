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
package com.avaloq.tools.ddk.check.runtime.registry.impl;

import java.util.List;

import com.avaloq.tools.ddk.check.runtime.configuration.BundleAwareModelLocation;
import com.avaloq.tools.ddk.check.runtime.configuration.IModelLocation;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckCatalogRegistry;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.inject.Provider;


/**
 * Provides a registry of Check validators. Is required for the standalone builder
 * since extension points cannot be used in a non-Eclipse environment.
 */
public class CheckCatalogRegistryImpl extends AbstractCheckImplDescriptorRegistry implements ICheckCatalogRegistry {

  private final Multimap<String, IModelLocation> concreteModelLocations = HashMultimap.create();

  /**
   * Gets all check model locations from the registry. The plug-in registry queries all deployed
   * catalog plug-ins (i.e. the check plug-in extensions) and inspects the {@code catalog} attribute
   * of each extension.
   * 
   * @return all check model location instances
   */
  @SuppressWarnings("unchecked")
  public Iterable<IModelLocation> getAllCheckModelLocations() {
    List<IModelLocation> result = Lists.<IModelLocation> newArrayList(concreteModelLocations.values());
    for (Object v : getLanguageToDescriptorMap().values()) {
      if (v instanceof Provider<?>) {
        final BundleAwareModelLocation location = ((Provider<BundleAwareModelLocation>) v).get();
        if (location != null) { // may be null
          result.add(location);
        }
      } else if (v instanceof IModelLocation) {
        result.add((IModelLocation) v);
      }
    }
    return result;
  }

  /** {@inheritDoc} */
  public void registerCatalog(final String language, final IModelLocation modelLocation) {
    concreteModelLocations.put(language, modelLocation);
  }

}

