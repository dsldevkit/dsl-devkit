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

import java.io.IOException;

import com.avaloq.tools.ddk.xtext.modelcache.BinaryModelCacheManager.ModelStatus;


/**
 * {@link IModelCacheManager} implementations are the entry point for Binary Model Cache.
 * They are responsible for bridging the {@link Resource} and the {@link IModelCache} implementations.
 * <p>
 * To achieve this, {@link IModelCacheManager} keeps track of which models have been loaded, proxied or have not been loaded yet in the given {@link Resource}.
 * When new models need to be loaded, it fetches the binary data from {@link IModelCache} and decides what actions need to be taken for each model, such as
 * loading or creating a proxy. It then delegates the actual handling of binary data for the models to instances of type {@link IBinaryModelHandler}.
 * </p>
 */
public interface IModelCacheManager {

  /**
   * Sets the status of all Models to be the given status.
   *
   * @param status
   *          the new status, must not be {@code null}
   */
  void setAllModelsToStatus(ModelStatus status);

  /**
   * Loads the binary models of this resource, with the possibility to choose which models to load.
   *
   * @param modelsToLoad
   *          the list of models that should be loaded, must not be {@code null}
   * @return {@code true} if the models were found in the cache and successfully loaded
   */
  boolean loadBinaryModels(ResourceModelType... modelsToLoad);

  /**
   * Saves the binary models of this resource.
   *
   * @throws IOException
   *           when saving the models to the cache fails
   */
  void saveBinaryModels() throws IOException;

  /**
   * Checks if all models of this resource were fully loaded.
   *
   * @return {@code true} if all models are fully loaded
   */
  boolean allModelsLoaded();
}
