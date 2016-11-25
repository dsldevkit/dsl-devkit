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

import com.avaloq.tools.ddk.xtext.linking.ILazyLinkingResource2;
import com.google.inject.Inject;


/**
 * A factory for creating ModelCacheManager instances for a given resource.
 */
public class ModelCacheManagerFactory {
  @Inject(optional = true)
  private IModelCache modelCache;

  /**
   * Creates a new Model Cache Manager instance, the specific implementation being dependent on
   * whether a Model Cache implementation is available or not.
   *
   * @param resource
   *          the resource to be managed, must not be {@code null}
   * @return the specific {@link IModelCacheManager} implementation, never {@code null}
   */
  public IModelCacheManager createModelCacheManager(final ILazyLinkingResource2 resource) {
    if (modelCache != null) {
      return new BinaryModelCacheManager(modelCache, resource);
    }
    return new NullModelCacheManager();
  }
}
