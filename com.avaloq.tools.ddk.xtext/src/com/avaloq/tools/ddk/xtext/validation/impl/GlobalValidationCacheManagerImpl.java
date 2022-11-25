/*******************************************************************************
 * Copyright (c) 2017 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.validation.impl;

import java.util.Set;

import com.avaloq.tools.ddk.xtext.validation.IGlobalValidationCache;
import com.avaloq.tools.ddk.xtext.validation.IGlobalValidationCacheManager;
import com.google.common.collect.Sets;


/** The global validation cache manager implementation. */
public class GlobalValidationCacheManagerImpl implements IGlobalValidationCacheManager {

  private final Set<IGlobalValidationCache> caches = Sets.newHashSet();

  @Override
  public void invalidateCaches() {
    for (IGlobalValidationCache cache : caches) {
      cache.invalidate();
    }
  }

  @Override
  public void addGlobalCache(final IGlobalValidationCache cache) {
    caches.add(cache);
  }

  @Override
  public boolean removeGlobalCache(final IGlobalValidationCache cache) {
    return caches.remove(cache);
  }

}
