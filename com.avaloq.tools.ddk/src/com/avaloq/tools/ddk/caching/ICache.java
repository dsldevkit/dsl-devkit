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
package com.avaloq.tools.ddk.caching;

/**
 * A basic cache capable of providing usage statistics.
 *
 * @param <K>
 *          the key type
 * @param <V>
 *          the value type
 */
public interface ICache<K, V> {

  /**
   * Returns the statistics of this cache's usage.
   * <p>
   * <em>Note:</em> may not return any useful results if statistics were not enabled in the {@link CacheConfiguration} when the cache was created.
   * </p>
   *
   * @return the statistics
   */
  CacheStatistics getStatistics();
}
