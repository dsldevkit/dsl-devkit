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

import java.util.Map;

import org.eclipse.xtext.resource.IResourceDescription.Delta;

import com.google.common.collect.Maps;


/**
 * Support for extensions on resource description delta.
 */
public abstract class AbstractResourceDescriptionDelta implements Delta {

  private final Map<Class<? extends Object>, Object> extensions = Maps.newHashMap();

  /**
   * Adds the extension data.
   *
   * @param data
   *          the data
   */
  public void addExtensionData(final Object data) {
    extensions.put(data.getClass(), data);
  }

  /**
   * Gets the extension data.
   *
   * @param <T>
   *          the generic type
   * @param clazz
   *          the clazz of the extension data type
   * @return the extension data, or {@code null} if not available
   */
  @SuppressWarnings("unchecked")
  public <T> T getExtensionData(final Class<T> clazz) {
    return (T) extensions.get(clazz);
  }

}
