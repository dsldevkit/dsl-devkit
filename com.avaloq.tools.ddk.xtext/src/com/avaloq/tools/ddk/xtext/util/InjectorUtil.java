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
package com.avaloq.tools.ddk.xtext.util;

import org.eclipse.xtext.resource.IResourceServiceProvider;

import com.avaloq.tools.ddk.xtext.resource.ResourceServiceProviderLocator;


/**
 * Utility for language specific injection of classes.
 */
public final class InjectorUtil {

  /**
   * A language specific way to obtain an implementation for a certain type.
   *
   * @param <T>
   *          the type for which to get the implementation instance for, or a subtype of that
   * @param languageId
   *          the language id of the {@link IResourceServiceProvider} to use, must not be {@code null}
   * @param clazz
   *          the type clazz, must not be {@code null}
   * @return implementation, or {@code null} if no implementation of the given type can be provided
   */
  public static <T> T get(final String languageId, final Class<? extends T> clazz) {
    final IResourceServiceProvider resourceServiceProvider = new ResourceServiceProviderLocator().getResourceServiceProviderById(languageId);
    if (resourceServiceProvider != null) {
      return resourceServiceProvider.get(clazz);
    }
    return null;
  }

  /**
   * A file extension specific way to obtain an implementation for a certain type.
   *
   * @param <T>
   *          the type for which to get the implementation instance for, or a subtype of that
   * @param fileExtension
   *          the file extension of the {@link IResourceServiceProvider} to use, must not be {@code null}
   * @param clazz
   *          the type clazz, must not be {@code null}
   * @return implementation, or {@code null} if no implementation of the given type can be provided
   */
  public static <T> T getByExtension(final String fileExtension, final Class<? extends T> clazz) {
    final IResourceServiceProvider resourceServiceProvider = new ResourceServiceProviderLocator().getResourceServiceProviderByExtension(fileExtension);
    if (resourceServiceProvider != null) {
      return resourceServiceProvider.get(clazz);
    }
    return null;
  }

  /**
   * Creates a new instance of {@link InjectorUtil}.
   */
  private InjectorUtil() {}
}
