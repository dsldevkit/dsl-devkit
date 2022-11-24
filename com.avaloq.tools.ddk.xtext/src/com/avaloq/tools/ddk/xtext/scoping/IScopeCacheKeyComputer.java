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

package com.avaloq.tools.ddk.xtext.scoping;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import com.google.inject.ImplementedBy;


/**
 * Computes the keys to associate with cached scopes.
 */
@ImplementedBy(DefaultScopeCacheKeyComputer.class)
public interface IScopeCacheKeyComputer {

  /**
   * Returns the global cache key for caching dispatchers and scope results.
   *
   * @param context
   *          context object contained in a resource, must not be {@code null}
   * @param type
   *          type of scope, must not be {@code null}
   * @param scopeName
   *          name of scope, must not be {@code null}
   * @return global scope cache key, never {@code null}
   */
  Object getGlobalCacheKey(EObject context, EClass type, String scopeName);

  /**
   * Returns the global cache key for caching dispatchers and scope results.
   *
   * @param context
   *          context object contained in a resource, must not be {@code null}
   * @param reference
   *          reference of scope, must not be {@code null}
   * @param scopeName
   *          name of scope, must not be {@code null}
   * @return global scope cache key, never {@code null}
   */
  Object getGlobalCacheKey(EObject context, EReference reference, String scopeName);

  /**
   * Returns a pooled string used as key when caching dispatchers and scope results.
   *
   * @param context
   *          context object
   * @param type
   *          type of scope
   * @param scopeName
   *          name of scope
   * @return pooled cache key
   */
  Object getCacheKey(EObject context, EClass type, String scopeName);

  /**
   * Returns a pooled string used as key when caching dispatchers and scope results.
   *
   * @param context
   *          context object, must not be {@code null}
   * @param reference
   *          reference of scope, must not be {@code null}
   * @param scopeName
   *          name of scope, must not be {@code null}
   * @return pooled cache key, never {@code null}
   */
  Object getCacheKey(EObject context, EReference reference, String scopeName);

}
