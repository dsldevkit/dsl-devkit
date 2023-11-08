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
package com.avaloq.tools.ddk.check.lib;

import org.eclipse.emf.ecore.EObject;

import com.avaloq.tools.ddk.check.lib.internal.PerResourceCache;
import com.google.inject.ImplementedBy;


/**
 * A per-resource cache that remains valid as long as the resource is not changed. Whenever the resource changes, the cache is cleared.
 * In particular, this cache remains valid throughout the validation of a single resource, over multiple checks.
 * <p>
 * <b>Usage:</b>
 * </p>
 * <p>
 * <code>@Inject IResourceCache cache;</code>
 * </p>
 */
@ImplementedBy(PerResourceCache.class)
public interface IResourceCache {

  /**
   * Stores a value under a given key in the cache of the resource containing the given context object.
   * <p>
   * <em>Note:</em> throws
   * </p>
   * <ul>
   * <li>{@link IllegalArgumentException} if {@code context} or {@code key} are {@©ode null}.</li>
   * <li>{@link IllegalStateException} if the resource of {@code context} is not an Xtext resource.</li>
   * </ul>
   *
   * @param <T>
   *          type of the object to be stored in the cache
   * @param context
   *          object determining the resource; must not be {@code null}.
   * @param key
   *          to store the value under; must not be {@code null}.
   * @param value
   *          to store under the key; may be {@code null}.
   * @return the previous value stored under that key, if any, or {@code null} if none or if {@code null} was the previous value.
   */
  <T> Object put(EObject context, Object key, T value);

  /**
   * Retrieves the value stored under the given key from the cache of the resource identified by the context object.
   * <p>
   * <em>Note:</em> throws
   * </p>
   * <ul>
   * <li>{@link IllegalArgumentException} if {@code context} or {@code key} are {@©ode null}.</li>
   * <li>{@link IllegalStateException} if the resource of {@code context} is not an Xtext resource.</li>
   * <li>{@link ClassCastException} if the value cannot be converted to type T.</li>
   * </ul>
   *
   * @param <T>
   *          expected type of the stored value
   * @param context
   *          object determining the resource; must not be {@code null}.
   * @param key
   *          to look up; must not be {@code null}.
   * @return the value stored under the given key, or {@code null} if none or if {@code null} is stored under that key.
   */
  <T extends Object> T get(EObject context, Object key);

  /**
   * Determines whether there is any value stored under the given key in the cache of the resource containing the context object.
   * <p>
   * <em>Note:</em> throws
   * </p>
   * <ul>
   * <li>{@link IllegalArgumentException} if {@code context} or {@code key} are {@©ode null}.</li>
   * <li>{@link IllegalStateException} if the resource of {@code context} is not an Xtext resource.</li>
   * </ul>
   *
   * @param context
   *          object determining the resource; must not be {@code null}.
   * @param key
   *          to look up; must not be {@code null}.
   * @return {@code true}, if a value is stored under that key, or {@code false} otherwise
   */
  boolean containsKey(EObject context, Object key);

  /**
   * Removes all entries stored in the cache of the resource containing the context object.
   * <p>
   * <em>Note:</em> throws
   * </p>
   * <ul>
   * <li>{@link IllegalArgumentException} if {@code context} is {@©ode null}.</li>
   * <li>{@link IllegalStateException} if the resource of {@code context} is not an Xtext resource.</li>
   * </ul>
   *
   * @param context
   *          object determining the resource; must not be {@code null}.
   */
  void clear(EObject context);

}
