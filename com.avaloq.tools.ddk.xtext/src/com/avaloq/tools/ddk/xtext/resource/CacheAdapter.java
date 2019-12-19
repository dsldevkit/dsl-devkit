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
package com.avaloq.tools.ddk.xtext.resource;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;

import com.avaloq.tools.ddk.caching.CacheConfiguration;
import com.avaloq.tools.ddk.caching.CacheManager;
import com.avaloq.tools.ddk.caching.MapCache;


/**
 * A generic resource set cache adapter.
 *
 * @param <V>
 *          the type of the stored values
 */
public class CacheAdapter<V> extends AdapterImpl implements IResourceSetCache<V> {
  private final MapCache<Object, V> cache = CacheManager.getInstance().createMapCache("CacheAdapter#cache", new CacheConfiguration().useSoftValues()); //$NON-NLS-1$

  /** {@inheritDoc} */
  @Override
  public V get(final Object key) {
    return cache.get(key);
  }

  /** {@inheritDoc} */
  @Override
  public void put(final Object key, final V scope) {
    cache.put(key, scope);
  }

  /** {@inheritDoc} */
  @Override
  public void clear() {
    cache.clear();
  }

  /** {@inheritDoc} */
  @Override
  public void setTarget(final Notifier newTarget) {
    clear();
    super.setTarget(newTarget);
  }

  /** {@inheritDoc} */
  @Override
  public boolean isAdapterForType(final Object type) {
    if (type instanceof Class<?>) {
      return ((Class<?>) type).isAssignableFrom(getClass());
    }
    return type.getClass().isAssignableFrom(getClass());
  }
}
