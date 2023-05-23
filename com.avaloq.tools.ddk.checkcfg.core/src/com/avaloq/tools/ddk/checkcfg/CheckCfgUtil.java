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
package com.avaloq.tools.ddk.checkcfg;

import static com.avaloq.tools.ddk.checkcfg.CheckCfgConstants.PROPERTY_EXECUTABLE_EXTENSION_ATTRIBUTE;
import static com.avaloq.tools.ddk.checkcfg.CheckCfgConstants.PROPERTY_EXTENSION_POINT;

import java.util.Collection;
import java.util.HashSet;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.IResourceServiceProvider.Registry;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

import com.avaloq.tools.ddk.xtext.resource.AbstractCachingResourceDescriptionManager;
import com.google.common.collect.Sets;
import com.google.inject.ConfigurationException;


/**
 * Common helper methods for check configuration language.
 */
public class CheckCfgUtil {

  private static final Logger LOGGER = LogManager.getLogger(CheckCfgUtil.class);

  /**
   * Gets the all languages available in the workbench.
   *
   * @return set of all languages
   */
  public Set<String> getAllLanguages() {
    Set<String> languages = new HashSet<String>();
    for (String extension : Registry.INSTANCE.getExtensionToFactoryMap().keySet()) {
      final URI dummyUri = URI.createURI("foo:/foo." + extension); //$NON-NLS-1$
      IResourceServiceProvider resourceServiceProvider = Registry.INSTANCE.getResourceServiceProvider(dummyUri);
      // By checking that description manager is AbstractCachingResourceDescriptionManager we exclude technical languages of the framework
      if (resourceServiceProvider != null && resourceServiceProvider.getResourceDescriptionManager() instanceof AbstractCachingResourceDescriptionManager) {
        try {
          IGrammarAccess grammarAccess = resourceServiceProvider.get(IGrammarAccess.class);
          if (grammarAccess != null && grammarAccess.getGrammar() != null) {
            languages.add(grammarAccess.getGrammar().getName());
          }
        } catch (ConfigurationException e) {
          // Will happen if no binding for IGrammarAccess was present.
        }
      }
    }
    return languages;
  }

  /**
   * Retrieves all property contributions from the checkcfg property extension point.
   *
   * @return the collection of all available property contributions, never {@code null}
   */
  public static Collection<ICheckCfgPropertySpecification> getAllPropertyContributions() {
    Set<ICheckCfgPropertySpecification> contributions = Sets.newHashSet();
    IExtensionRegistry registry = Platform.getExtensionRegistry();
    if (registry != null) { // registry is null in the standalone builder...
      IConfigurationElement[] elements = registry.getConfigurationElementsFor(PROPERTY_EXTENSION_POINT);
      for (IConfigurationElement element : elements) {
        try {
          contributions.add((ICheckCfgPropertySpecification) element.createExecutableExtension(PROPERTY_EXECUTABLE_EXTENSION_ATTRIBUTE));
        } catch (CoreException e) {
          LOGGER.warn("Failed to instantiate property from " + element.getContributor(), e); //$NON-NLS-1$
        }
      }
    } else {
      // if there is no extension registry, try finding the contributions with the service loader
      try {
        for (ICheckCfgPropertySpecification contribution : ServiceLoader.load(ICheckCfgPropertySpecification.class)) {
          contributions.add(contribution);
        }
      } catch (ServiceConfigurationError e) {
        LOGGER.warn("Failed to instantiate property from loaded service.", e); //$NON-NLS-1$
      }
    }
    return contributions;
  }

  /**
   * Retrieves an {@link ICheckCfgPropertySpecification} contribution from the checkcfg property extension point by name.
   *
   * @param propertyName
   *          the name of the property, must not be {@code null}
   * @return the {@link ICheckCfgPropertySpecification} or {@code null}
   */
  public static ICheckCfgPropertySpecification getPropertySpecification(final String propertyName) {
    return IterableExtensions.findFirst(getAllPropertyContributions(), contribution -> propertyName.equalsIgnoreCase(contribution.getName()));
  }

}
