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

import com.google.inject.Injector;


/**
 * Global service that manages all extensions to all languages.
 * <p>
 * This service is implemented as a global singleton, one instance for all DSLs.
 * Intended for use by feature extension services to discover extensions
 * for specific feature of the given language.
 * </p>
 */
public interface ILanguageExtensionsService {

  /**
   * Gets all extensions for the given language using the injector of the language.
   *
   * @param injector
   *          the injector of the language for which the contributions are queried
   * @return the collection of language extensions, may be empty, never {@code null}
   */
  Collection<ILanguageExtensions> getExtensions(Injector injector);

}
