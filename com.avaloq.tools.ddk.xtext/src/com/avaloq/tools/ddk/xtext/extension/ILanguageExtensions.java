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

/**
 * A collection of feature extensions for one specific language.
 * <p>
 * Each language feature (service) that supports extensions can have zero or one extension
 * contributed by this collection. If multiple aspects need to be contributed to the same language service,
 * then several {@link ILanguageExtensions} should be used.
 * </p>
 */
public interface ILanguageExtensions {

  /**
   * Gets the full qualified name of the language supported.
   *
   * @return the language name
   */
  String getTargetLanguageName();

  /**
   * Returns the fully initialized extensions for specific language feature given the feature extension interface,
   * or {@code null} if this language extensions package does not contain an extension for the given language feature.
   *
   * @param <T>
   *          the extension interface type for a language feature (export, inference etc.)
   * @param clazz
   *          a Java interface, typically a direct subtype of {@link ILanguageFeatureExtension}
   *          specifying for which feature an extension is looked for
   * @return a concrete feature extension implementation, may be {@code null}
   */
  <T extends ILanguageFeatureExtension> T get(Class<T> clazz);

}
