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

import java.util.Map;

import org.eclipse.emf.common.util.URI;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;


/**
 * Class that holds the binary models of a Resource.
 * <p>
 * This class simply holds the given arrays. No copies will be made when setting or getting the model arrays.
 * </p>
 */
@SuppressWarnings({"PMD.ArrayIsStoredDirectly", "PMD.MethodReturnsInternalArray"})
// For performance reasons we do not want to copy the provided model arrays for every resource
public class ResourceModels {

  private final URI uri;
  private final Map<ResourceModelType, byte[]> models = Maps.newEnumMap(ResourceModelType.class);

  public ResourceModels(final URI uri, final Map<ResourceModelType, byte[]> models) {
    this.uri = uri;
    this.models.putAll(models);
  }

  public ResourceModels(final URI uri) {
    this(uri, ImmutableMap.<ResourceModelType, byte[]> of());
  }

  public URI getUri() {
    return uri;
  }

  /**
   * Stores the given model.
   * <p>
   * <em>Note</em>: this method does not create a copy of the given data array.
   * </p>
   *
   * @param model
   *          the model to save, must not be {@code null}
   * @param data
   *          the data of the model, may be {@code null}
   */
  public void setModel(final ResourceModelType model, final byte[] data) {
    models.put(model, data);
  }

  /**
   * Fetches the model.
   * <p>
   * <em>Note</em>: this method returns its internal copy of the array.
   * </p>
   *
   * @param model
   *          the model to load, must not be {@code null}
   * @return the loaded model, may be {@code null}
   */
  public byte[] getModel(final ResourceModelType model) {
    return models.get(model);
  }

  /**
   * Checks if the given model exists.
   *
   * @param model
   *          the model to check, must not be {@code null}
   * @return true, if the model exists
   */
  public boolean hasModel(final ResourceModelType model) {
    return models.containsKey(model);
  }
}
