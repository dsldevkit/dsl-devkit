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
package com.avaloq.tools.ddk.xtext.validation;

import com.avaloq.tools.ddk.xtext.validation.impl.GlobalValidationCacheManagerImpl;


/** The global validation cache manager. */
public interface IGlobalValidationCacheManager {

  /** The Constant INSTANCE. */
  IGlobalValidationCacheManager INSTANCE = new GlobalValidationCacheManagerImpl();

  /**
   * Invalidate all managed validation caches.
   */
  void invalidateCaches();

  /**
   * Add the given global validation cache instance to the manager.
   *
   * @param cache
   *          the global validation cache instance to manage
   */
  void addGlobalCache(IGlobalValidationCache cache);

  /**
   * Remove the global validation cache instance from the manager.
   *
   * @param cache
   *          the global validation cache instance no longer manage
   * @return {@code true} if the cache was managed by the manager
   */
  boolean removeGlobalCache(IGlobalValidationCache cache);

}
