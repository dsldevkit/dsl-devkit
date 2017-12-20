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
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.avaloq.tools.ddk.xtext.linking.ILazyLinkingResource2;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


/**
 * Model Cache Manager implementation using Binary Model Cache.
 */
public class BinaryModelCacheManager implements IModelCacheManager {
  /**
   * Enumerates the possible load status of a Model.
   */
  public enum ModelStatus {
    LOADED,
    PROXIED,
    UNLOADED
  }

  private static final Logger LOGGER = Logger.getLogger(BinaryModelCacheManager.class);
  private static final ResourceModelType[] MODEL_VALUES = ResourceModelType.values();

  private final IModelCache modelCache;

  private final ILazyLinkingResource2 resource;
  private final Map<ResourceModelType, ModelStatus> modelStatus = Maps.newEnumMap(ResourceModelType.class);
  private final Map<ResourceModelType, IBinaryModelHandler> modelHandlers = Maps.newEnumMap(ResourceModelType.class);

  public BinaryModelCacheManager(final IModelCache modelCache, final ILazyLinkingResource2 resource) {
    this.modelCache = modelCache;
    this.resource = resource;
    initializeModelHandlers();
  }

  /**
   * Initialize a map containing all available model handlers.
   */
  private void initializeModelHandlers() {
    if (modelHandlers.isEmpty()) {
      BinaryModelHandlerFactory handlerFactory = new BinaryModelHandlerFactory();
      for (ResourceModelType model : MODEL_VALUES) {
        modelHandlers.put(model, handlerFactory.createHandler(model, resource));
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public void setAllModelsToStatus(final ModelStatus status) {
    synchronized (modelStatus) {
      for (ResourceModelType model : MODEL_VALUES) {
        modelStatus.put(model, status);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public boolean loadBinaryModels(final ResourceModelType... modelsToLoad) {
    synchronized (modelStatus) {
      ResourceModelType[] filteredModels = filterLoadedModels(modelsToLoad);

      // If all the requested models have already been loaded, report load success immediatly
      if (filteredModels.length == 0) {
        return true;
      }

      try {
        // Otherwise attempt to load the remaining unloaded models
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug(String.format("Loading %s (%s)", resource.getURI(), Joiner.on(", ").join(filteredModels))); //$NON-NLS-1$ //$NON-NLS-2$
        }
        ResourceModels models = modelCache.fetchModels(resource.getURI(), filteredModels);
        if (models != null) {
          insertModels(models);
          LOGGER.debug("Loaded " + resource.getURI() + " from cache"); //$NON-NLS-1$ //$NON-NLS-2$
          return true;
        }
        LOGGER.debug("Models for " + resource.getURI() + " not found in cache"); //$NON-NLS-1$ //$NON-NLS-2$
        return false;
      } catch (IOException e) {
        LOGGER.warn("Failed to load binary models", e); //$NON-NLS-1$
        return false;
      } catch (IllegalStateException e) {
        LOGGER.warn("Loadel model does not match loaded text", e); //$NON-NLS-1$
        return false;
      }
    }
  }

  /**
   * Filters the Models that have already been loaded from the given Model list.
   *
   * @param modelsToLoad
   *          a list of models to be loaded, must not be {@code null}
   * @return the filtered modelsToLoad, without any models that are already loaded, never {@code null}
   */
  private ResourceModelType[] filterLoadedModels(final ResourceModelType... modelsToLoad) {
    List<ResourceModelType> filteredModels = Lists.newArrayList();
    for (ResourceModelType model : modelsToLoad) {
      if (modelStatus.get(model) != ModelStatus.LOADED) {
        filteredModels.add(model);
      }
    }
    return filteredModels.toArray(new ResourceModelType[filteredModels.size()]);
  }

  /**
   * Takes the models that were loaded from cache and deserializes/proxies all models as appropriate.
   *
   * @param resourceModels
   *          the resource's models, loaded from cache, must not be {@code null}
   * @throws IOException
   *           when loading the models from the cache fails
   */
  private void insertModels(final ResourceModels resourceModels) throws IOException {
    try {
      resource.setLoading(true);

      for (ResourceModelType model : MODEL_VALUES) {
        if (resourceModels.hasModel(model)) {
          switch (modelStatus.get(model)) {
          case LOADED:
            // No action when the model is already loaded
            break;
          case PROXIED:
            removeProxyModel(model);
            insertModel(model, resourceModels.getModel(model));
            break;
          case UNLOADED:
            insertModel(model, resourceModels.getModel(model));
            break;
          }
        } else if (modelStatus.get(model) == ModelStatus.UNLOADED) {
          insertProxyModel(model);
        }
      }
    } finally {
      resource.setLoading(false);
    }
  }

  /**
   * Create a proxy for the given model.
   *
   * @param model
   *          the model to create the proxy for, must not be {@code null}
   */
  private void insertProxyModel(final ResourceModelType model) {
    modelHandlers.get(model).insertProxyModel();
    modelStatus.put(model, ModelStatus.PROXIED);
  }

  /**
   * Removes the proxy the given model.
   *
   * @param model
   *          the model to remove the proxy from, must not be {@code null}
   */
  private void removeProxyModel(final ResourceModelType model) {
    modelHandlers.get(model).removeProxyModel();
    modelStatus.put(model, ModelStatus.UNLOADED);
  }

  /**
   * Deserializes and recreates the given model.
   *
   * @param model
   *          the model to insert, must not be {@code null}
   * @param data
   *          the serialized model data, must not be {@code null}
   * @throws IOException
   *           when the manipulation of the given data fails
   */
  private void insertModel(final ResourceModelType model, final byte[] data) throws IOException {
    modelHandlers.get(model).insertModel(data);
    modelStatus.put(model, ModelStatus.LOADED);
  }

  /** {@inheritDoc} */
  @Override
  public void saveBinaryModels() throws IOException {
    synchronized (modelStatus) {
      LOGGER.debug("Saving " + resource.getURI()); //$NON-NLS-1$
      Map<ResourceModelType, byte[]> models = Maps.newEnumMap(ResourceModelType.class);
      for (ResourceModelType model : MODEL_VALUES) {
        models.put(model, modelHandlers.get(model).extractModel());
      }
      modelCache.storeModels(new ResourceModels(resource.getURI(), models));
    }
  }

  /** {@inheritDoc} */
  @Override
  public boolean allModelsLoaded() {
    synchronized (modelStatus) {
      for (ModelStatus status : modelStatus.values()) {
        if (status != ModelStatus.LOADED) {
          return false;
        }
      }
      return true;
    }
  }
}
