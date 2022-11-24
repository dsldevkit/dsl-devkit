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
package com.avaloq.tools.ddk.xtext.test;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.IResourceServiceProvider;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * Factory for creating test sources.
 */
// CHECKSTYLE:OFF
@Singleton
public class XtextTestSourceFactory implements ITestSourceFactory {
  // CHECKSTYLE:ON

  private final List<String> xtextExtensions;
  private final ResourceSet resourceSet;

  @Inject
  public XtextTestSourceFactory(final IResourceServiceProvider.Registry resourceServiceProviderRegistry, final ResourceSet resourceSet) {
    final List<String> extensions = Lists.newArrayList(resourceServiceProviderRegistry.getExtensionToFactoryMap().keySet());
    this.xtextExtensions = Lists.transform(extensions, new Function<String, String>() {
      public String apply(final String from) {
        return URI.decode(from);
      }
    });
    this.resourceSet = resourceSet;
  }

  /** {@inheritDoc} */
  public boolean isFactoryFor(final String name) {
    return name.indexOf('.') != -1 && xtextExtensions.contains(name.substring(name.lastIndexOf('.') + 1));
  }

  /** {@inheritDoc} */
  public TestSource createTestSource(final String name, final String content) {
    return new XtextTestSource(name, content, resourceSet);
  }
}

