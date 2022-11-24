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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.xtext.Constants;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;


/**
 * Common part of the global registry for contributions. Specific integration (IDE or standalone builder)
 * must extend with own mechanism for finding and loading extensions.
 */
public abstract class AbstractLanguageExtensionsService implements ILanguageExtensionsService {

  private final Map<Injector, Collection<ILanguageExtensions>> initializedExtensions = new HashMap<>();

  @Override
  public Collection<ILanguageExtensions> getExtensions(final Injector injector) {
    Collection<ILanguageExtensions> extensions = initializedExtensions.get(injector);
    if (extensions == null) {
      String languageName = injector.getInstance(Key.get(String.class, Names.named(Constants.LANGUAGE_NAME)));
      extensions = getLoadedExtensions(languageName);
      if (extensions == null) {
        extensions = Collections.emptySet();
      } else {
        extensions.forEach(injector::injectMembers);
      }
      initializedExtensions.put(injector, extensions);
    }
    return extensions;
  }

  /**
   * Loads extensions. Intended to be implemented by the framework and may have different versions for OSGi and non-OSGi environments.
   *
   * @param languageName
   *          the language name
   * @return the loaded collection of extensions
   */
  protected abstract Collection<ILanguageExtensions> getLoadedExtensions(String languageName);

}
