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

import org.eclipse.emf.common.util.URI;


/**
 * An interface representing a Cache that accepts a Resource and can load and store its EMF and Node models.
 */
public interface IModelCache {

  /**
   * Fetches the models of a given URI from cache.
   *
   * @param uri
   *          the resource URI, must not be {@code null}
   * @param modelsToLoad
   *          the list of models to load
   * @return the loaded resource models, or {@code null} if they can not be found in cache
   */
  ResourceModels fetchModels(URI uri, ResourceModelType... modelsToLoad);

  /**
   * Stores the models of a given URI in cache.
   *
   * @param resourceModels
   *          the resource models to store, must not be {@code null}
   */
  void storeModels(ResourceModels resourceModels);
}
