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
package com.avaloq.tools.ddk.check.runtime.registry;

import java.util.NavigableSet;

import com.avaloq.tools.ddk.check.runtime.configuration.IModelLocation;
import com.avaloq.tools.ddk.check.runtime.registry.impl.CheckCatalogRegistryImpl;


/**
 * The check catalog registry. May be used to query all registered check catalogs.
 */
public interface ICheckCatalogRegistry extends ICheckImplDescriptorRegistry {

  /** The Constant INSTANCE. */
  ICheckCatalogRegistry INSTANCE = new CheckCatalogRegistryImpl();

  /**
   * Gets all check model locations from the registry.
   *
   * @return the check model locations as a {@link NavigableSet}, never {@code null}
   */
  NavigableSet<IModelLocation> getAllCheckModelLocations();

  /**
   * Add model location instance for a given language.
   *
   * @param language
   *          language to add model location instance for
   * @param modelLocation
   *          model location instance
   */
  void registerCatalog(final String language, final IModelLocation modelLocation);

  /**
   * Add model location instance.
   *
   * @param modelLocation
   *          model location instance
   */
  void registerCatalog(final IModelLocation modelLocation);

}
