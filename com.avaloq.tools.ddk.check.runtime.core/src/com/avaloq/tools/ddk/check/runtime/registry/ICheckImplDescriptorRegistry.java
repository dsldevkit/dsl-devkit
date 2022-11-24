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

import java.util.Collection;

import com.google.common.collect.Multimap;


/**
 * The registry of service providers for different languages.
 * Service can be quickfixes, catalogs & etc.
 */
public interface ICheckImplDescriptorRegistry {

  /**
   * Get a copy of the map of languages mapped to catalog descriptor providers.
   * 
   * @see com.avaloq.tools.ddk.check.runtime.internal.CheckCatalogDescriptor
   * @return the language to catalog descriptor map
   */
  Multimap<String, ICheckImplDescriptor> getLanguageToDescriptorMap();

  /**
   * Get catalog descriptor provider by language.
   * 
   * @param language
   *          the language to get descriptor provider for.
   * @return
   *         descriptor provider
   */
  Collection<ICheckImplDescriptor> getDescriptors(final String language);

  /**
   * Get catalog descriptor provider by language.
   * 
   * @return
   *         descriptor provider
   */
  Collection<ICheckImplDescriptor> getDescriptors();

  /**
   * Register a new catalog descriptor provider by language.
   * 
   * @param language
   *          the language
   * @param descriptor
   *          descriptor
   */
  void registerCatalogDescriptor(final String language, final ICheckImplDescriptor descriptor);

  /**
   * Remove a new catalog descriptor provider by language.
   * 
   * @param language
   *          the language
   * @param descriptor
   *          descriptor
   * @return {@code TRUE} if the provider for the given language was found in the list of providers and could be removed, {@code FALSE} otherwise.
   */
  boolean removeLanguageDescriptor(final String language, final ICheckImplDescriptor descriptor);

  /**
   * Remove a catalog descriptor provider.
   * <p>
   * <em>Note:</em> Only removes descriptors for catalogs that did not specify any particular language.
   * </p>
   * 
   * @param descriptor
   *          descriptor
   * @return {@code TRUE} if the provider for the given check catalog descriptor could be removed, {@code FALSE} otherwise.
   */
  boolean removeLanguageDescriptor(final ICheckImplDescriptor descriptor);

}

