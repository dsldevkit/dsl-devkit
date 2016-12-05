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
package com.avaloq.tools.ddk.checkcfg;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.IResourceServiceProvider.Registry;

import com.avaloq.tools.ddk.xtext.resource.AbstractCachingResourceDescriptionManager;
import com.google.inject.ConfigurationException;


/**
 * Common helper methods for check configuration language.
 */
public class CheckCfgUtil {

  /**
   * Gets the all languages available in the workbench.
   * 
   * @return set of all languages
   */
  public Set<String> getAllLanguages() {
    Set<String> languages = new HashSet<String>();
    for (String extension : Registry.INSTANCE.getExtensionToFactoryMap().keySet()) {
      final URI dummyUri = URI.createURI("foo:/foo." + extension);
      IResourceServiceProvider resourceServiceProvider = Registry.INSTANCE.getResourceServiceProvider(dummyUri);
      // By checking that description manager is AbstractCachingResourceDescriptionManager we exclude technical languages of the framework
      if (resourceServiceProvider != null && resourceServiceProvider.getResourceDescriptionManager() instanceof AbstractCachingResourceDescriptionManager) {
        try {
          IGrammarAccess grammarAccess = resourceServiceProvider.<IGrammarAccess> get(IGrammarAccess.class);
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
}

