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

package com.avaloq.tools.ddk.xtext.extension;

import java.util.Map;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Injector;


/**
 * Default implementation providing no extensions.
 */
public abstract class AbstractLanguageExtensions implements ILanguageExtensions {

  @Inject
  private Injector injector;

  private final Map<Class<? extends ILanguageFeatureExtension>, Class<? extends ILanguageFeatureExtension>> extensions = Maps.newHashMap();

  /**
   * Adds an extension to one of the language features that support extension mechanism.
   *
   * @param <T>
   *          the type of the key, must be a subtype of the {@link ILanguageFeatureExtension}
   * @param <V>
   *          the actual implementation type, must be a subtype of the key type
   * @param key
   *          the key class, must not be {@code null}
   * @param implementation
   *          the implementation class, must not be {@code null}
   */
  protected <T extends ILanguageFeatureExtension, V extends T> void add(final Class<T> key, final Class<V> implementation) {
    extensions.put(key, implementation);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends ILanguageFeatureExtension> T get(final Class<T> clazz) {
    Class<? extends ILanguageFeatureExtension> extension = extensions.get(clazz);
    if (extension != null) {
      return (T) injector.getInstance(extension);
    }
    return null;
  }

}
