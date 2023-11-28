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

import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IResourceServiceProvider;


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
    return IResourceServiceProvider.Registry.INSTANCE.getExtensionToFactoryMap().keySet().stream().map(URI::decode).collect(Collectors.toSet());
  }

  /**
   * Creates a new instance of {@link ResourceServiceProviderUtil}.
   */
  private ResourceServiceProviderUtil() {
  }
}
