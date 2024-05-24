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

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.resource.IResourceServiceProvider;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.inject.ConfigurationException;


/**
 * Provides methods for locating ResourceServiceProviders.
 */
public class ResourceServiceProviderLocator {

  /**
   * Finds the {@link IResourceServiceProvider} for a language given its file extension.
   *
   * @param extension
   *          the language's file extension
   * @return the resource service provider for the {@code extension} or null if there is none
   */
  public IResourceServiceProvider getResourceServiceProviderByExtension(final String extension) {
    Object object = IResourceServiceProvider.Registry.INSTANCE.getExtensionToFactoryMap().get(extension);
    if (object instanceof IResourceServiceProvider) {
      return (IResourceServiceProvider) object;
    } else {
      URI fake = URI.createURI("fake:/foo." + extension); //$NON-NLS-1$
      return IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(fake, ContentHandler.UNSPECIFIED_CONTENT_TYPE);
    }
  }

  /**
   * Finds the {@link IResourceServiceProvider} for a language by given its id.
   *
   * @param languageId
   *          the language id (grammar name)
   * @return the {@link IResourceServiceProvider} for the given language id
   */
  public IResourceServiceProvider getResourceServiceProviderById(final String languageId) {
    for (Map.Entry<Map<String, Object>, ? extends Function<String, IResourceServiceProvider>> mapEntry : getProviderMaps().entrySet()) {
      Map<String, Object> map = mapEntry.getKey();
      for (Map.Entry<String, Object> entry : map.entrySet()) {
        try {
          IResourceServiceProvider resourceServiceProvider = mapEntry.getValue().apply(entry.getKey());
          if (resourceServiceProvider == null) {
            continue;
          }
          IGrammarAccess grammarAccess = resourceServiceProvider.get(IGrammarAccess.class);
          if (grammarAccess != null && grammarAccess.getGrammar().getName().equals(languageId)) {
            return resourceServiceProvider;
          }
          // CHECKSTYLE:OFF
        } catch (ConfigurationException ex) {
          // CHECKSTYLE:ON
          // ignore
        }
      }
    }
    return null;
  }

  /**
   * Gets the maps of the various resource service providers from the registry.
   *
   * @return the resource service provider's maps
   */
  private Map<Map<String, Object>, ? extends Function<String, IResourceServiceProvider>> getProviderMaps() {
    return //
    ImmutableMap.of(IResourceServiceProvider.Registry.INSTANCE.getExtensionToFactoryMap(), new Function<String, IResourceServiceProvider>() {
      @Override
      public IResourceServiceProvider apply(final String input) {
        URI fake = URI.createURI("fake:/foo." + input); //$NON-NLS-1$
        return IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(fake, ContentHandler.UNSPECIFIED_CONTENT_TYPE);
      }
    }, IResourceServiceProvider.Registry.INSTANCE.getContentTypeToFactoryMap(), new Function<String, IResourceServiceProvider>() {
      @Override
      public IResourceServiceProvider apply(final String input) {
        URI fake = URI.createURI("fake:/foo"); //$NON-NLS-1$
        return IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(fake, input);
      }
    }, IResourceServiceProvider.Registry.INSTANCE.getProtocolToFactoryMap(), new Function<String, IResourceServiceProvider>() {
      @Override
      public IResourceServiceProvider apply(final String input) {
        URI fake = URI.createURI(input + ":/foo"); //$NON-NLS-1$
        return IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(fake, ContentHandler.UNSPECIFIED_CONTENT_TYPE);
      }
    });
  }

}
