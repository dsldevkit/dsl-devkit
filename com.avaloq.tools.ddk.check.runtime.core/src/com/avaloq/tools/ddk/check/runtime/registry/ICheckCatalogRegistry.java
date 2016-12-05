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

import com.avaloq.tools.ddk.check.runtime.configuration.IModelLocation;
import com.avaloq.tools.ddk.check.runtime.registry.impl.CheckCatalogRegistryImpl;


/**
 * The check catalog registry. May be used to query all registered check catalogs.
 */
public interface ICheckCatalogRegistry extends ICheckImplDescriptorRegistry {

  /** The Constant INSTANCE. */
  ICheckCatalogRegistry INSTANCE = new CheckCatalogRegistryImpl();

  /**
   * Gets all model location instances used by the global plugin extension aware scope provider.
   * 
   * @return the all model location instances
   */
  Iterable<IModelLocation> getAllCheckModelLocations();

  /**
   * Add model location instance for a given language.
   * 
   * @param language
   *          language to add model location instance for
   * @param modelLocation
   *          model location instance
   */
  void registerCatalog(final String language, final IModelLocation modelLocation);

}

