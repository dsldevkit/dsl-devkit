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
package com.avaloq.tools.ddk.xtext.modelcache;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;


/**
 * Extension of the {@link IModelCache} interface which allows for bulk store and fetch operations.
 */
public interface IBatchModelCache extends IModelCache {

  /**
   * Initializes all data required to start querying the contents of the Model Cache.
   * <p>
   * <em>Note:</em> Calling any other methods before opening the Model Cache results in undefined behaviour.
   * </p>
   */
  void open();

  /**
   * Frees up all resources held localy by the Model Cache.
   */
  void close();

  /**
   * Fetches the models for all given URIs from cache.
   *
   * @param uris
   *          the list of resource URIs, must not be {@code null} and must not contain {@code null}
   * @param modelsToLoad
   *          the list of models to load
   * @return the list of loaded resource models that could be found in cache
   */
  Collection<ResourceModels> fetchModels(Collection<URI> uris, ResourceModelType... modelsToLoad);

  /**
   * Stores the models of a set of given URIs in cache.
   *
   * @param models
   *          the resource models to store, must not be {@code null} and must not contain {@code null}
   */
  void storeModels(Collection<ResourceModels> models);
}
