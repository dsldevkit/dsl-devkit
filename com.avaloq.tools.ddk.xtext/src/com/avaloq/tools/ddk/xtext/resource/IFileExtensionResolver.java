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

package com.avaloq.tools.ddk.xtext.resource;

import org.eclipse.emf.common.util.URI;

import com.avaloq.tools.ddk.xtext.resource.IFileExtensionResolver.DefaultFileExtensionResolver;
import com.google.inject.ImplementedBy;


/**
 * Allows overriding logic how file extension is extracted from the URI.
 */
@ImplementedBy(DefaultFileExtensionResolver.class)
public interface IFileExtensionResolver {

  /**
   * Resolve file extension for the given URI.
   *
   * @param uri
   *          the URI of a resource, must not be {@code null}
   * @return the string representing file extension used to detect the language
   */
  String resolveExtension(URI uri);

  /**
   * Default implementation for {@link IFileExtensionResolver}.
   */
  class DefaultFileExtensionResolver implements IFileExtensionResolver {

    @Override
    public String resolveExtension(final URI uri) {
      return uri.fileExtension();
    }

  }

}
