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
package com.avaloq.tools.ddk.xtext.resource;

import java.util.Map;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.avaloq.tools.ddk.caching.CacheConfiguration;


/**
 * A cache adapter for services.
 */
public class ServiceCacheAdapter extends CacheAdapter<Map<Class<?>, Object>> implements IResourceSetServiceCache {

  public ServiceCacheAdapter(final CacheConfiguration configuration) {
    super(configuration);
  }

  /**
   * Returns the {@link ServiceCacheAdapter} attached to a {@link ResourceSet} if it is supported by the given resource set.
   *
   * @param resourceSet
   *          the {@link ResourceSet}, must not be {@code null}
   * @return the resource set cache adapter attached to the resource set if there is one, never {@code null}
   */
  public static IResourceSetServiceCache getServiceCacheAdapter(final ResourceSet resourceSet) {
    final IResourceSetServiceCache cache = (IResourceSetServiceCache) EcoreUtil.getAdapter(resourceSet.eAdapters(), IResourceSetServiceCache.class);
    if (cache == null) {
      final ServiceCacheAdapter adapter = new ServiceCacheAdapter(new CacheConfiguration().useSoftValues());
      resourceSet.eAdapters().add(adapter);
      return adapter;
    }
    return cache;
  }

}
