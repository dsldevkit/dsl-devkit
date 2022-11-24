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
package com.avaloq.tools.ddk.check.lib.internal;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;

import com.avaloq.tools.ddk.check.lib.IResourceCache;
import com.google.common.collect.Maps;
import com.google.inject.Provider;
import com.google.inject.Singleton;


/**
 * A concrete implementation of IResourceCache.
 */
@Singleton
public final class PerResourceCache implements IResourceCache {

  protected PerResourceCache() {
    // Prevent explicit instantiations. All classes from this library shall be injected in check catalogs.
  }

  /** Cache creator. */
  private final Provider<Map<Object, Object>> cacheProvider = new Provider<Map<Object, Object>>() {
    public Map<Object, Object> get() {
      return Maps.newHashMap();
    }
  };

  /**
   * Retrieves the user-accessible cache for the resource the given context object is in.
   * <p>
   * <em>Note:</em> throws {@link IllegalStateException} if the resource of {@code context} is not an Xtext resource.
   * </p>
   * 
   * @param context
   *          determining the resource.
   * @return the cache, if any
   */
  private Map<Object, Object> getCache(final EObject context) {
    if (context == null) {
      throw new IllegalArgumentException(Messages.PerResourceCache_NullContext);
    }
    Resource res = context.eResource();
    if (!(res instanceof XtextResource)) {
      throw new IllegalStateException(Messages.PerResourceCache_NotAnXtextResource);
    }
    return ((XtextResource) res).getCache().get(this, res, cacheProvider);
  }

  /** {@inheritDoc} */
  public <T> Object put(final EObject context, final Object key, final T value) {
    if (key == null) {
      // Let's explicitly forbid null keys.
      throw new IllegalArgumentException(Messages.PerResourceCache_NullKey);
    }
    return getCache(context).put(key, value);
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unchecked")
  public <T extends Object> T get(final EObject context, final Object key) {
    if (key == null) {
      throw new IllegalArgumentException(Messages.PerResourceCache_NullKey);
    }
    return (T) getCache(context).get(key);
  }

  /** {@inheritDoc} */
  public boolean containsKey(final EObject context, final Object key) {
    if (key == null) {
      throw new IllegalArgumentException(Messages.PerResourceCache_NullKey);
    }
    return getCache(context).containsKey(key);
  }

  /** {@inheritDoc} */
  public void clear(final EObject context) {
    getCache(context).clear();
  }
}

