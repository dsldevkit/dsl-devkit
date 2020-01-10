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

import java.util.function.Function;

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
  private final MapCache<Object, V> cache;

  public CacheAdapter(final CacheConfiguration configuration) {
    cache = CacheManager.getInstance().createMapCache("CacheAdapter#cache", configuration); //$NON-NLS-1$ ;
  }

  @Override
  public V get(final Object key) {
    return cache.get(key);
  }

  @Override
  public void put(final Object key, final V scope) {
    cache.put(key, scope);
  }

  @Override
  public V putIfAbsent(final Object key, final V scope) {
    return cache.putIfAbsent(key, scope);
  }

  @Override
  public V computeIfAbsent(final Object key, final Function<? super Object, ? extends V> mappingFunction) {
    return cache.computeIfAbsent(key, mappingFunction);
  }

  @Override
  public void clear() {
    cache.clear();
  }

  @Override
  public void setTarget(final Notifier newTarget) {
    clear();
    super.setTarget(newTarget);
  }

  @Override
  public boolean isAdapterForType(final Object type) {
    if (type instanceof Class<?>) {
      return ((Class<?>) type).isAssignableFrom(getClass());
    }
    return type.getClass().isAssignableFrom(getClass());
  }
}
