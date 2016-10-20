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

import java.util.Collection;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IResourceServiceProvider;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;


/**
 * Utility for {@link IResourceServiceProvider}.
 */
public final class ResourceServiceProviderUtil {

  /**
   * Returns the registered resource extensions (URI-decoded).
   *
   * @return the registered resource extensions, never {@code null}
   */
  public static Collection<String> getExtensions() {
    final Set<String> extensions = IResourceServiceProvider.Registry.INSTANCE.getExtensionToFactoryMap().keySet();
    Collection<String> decodedExtensions = Collections2.transform(extensions, new Function<String, String>() {
      @Override
      public String apply(final String from) {
        return URI.decode(from);
      }
    });
    return decodedExtensions;
  }

  /**
   * Creates a new instance of {@link ResourceServiceProviderUtil}.
   */
  private ResourceServiceProviderUtil() {}
}
