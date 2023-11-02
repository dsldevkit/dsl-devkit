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
package com.avaloq.tools.ddk.xtext.linking;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.linking.impl.DefaultLinkingService;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.ClasspathUriResolutionException;
import org.eclipse.xtext.resource.ClasspathUriUtil;
import org.eclipse.xtext.resource.DerivedStateAwareResource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;


/**
 * Specialized linking service that can find grammars quickly. Using scoping to do so loads all installed language bundles, which may take a long time if there
 * are many languages installed.
 */
public abstract class AbstractFastLinkingService extends DefaultLinkingService {

  /**
   * Tries to find a grammar.
   *
   * @param resourceSet
   *          to use for loading
   * @param grammarName
   *          qualified grammar name
   * @return A singleton list containing the grammar, or an empty list if not found.
   */
  protected List<EObject> getUsedGrammar(final ResourceSet resourceSet, final String grammarName) {
    // copied from XtextLinkingService#getUsedGrammar()
    try {
      if (grammarName != null) {
        List<Resource> resources = resourceSet.getResources();
        for (int i = 0; i < resources.size(); i++) {
          Resource resource = resources.get(i);
          EObject rootElement = null;
          if (resource instanceof XtextResource) {
            IParseResult parseResult = ((XtextResource) resource).getParseResult();
            if (parseResult != null) {
              rootElement = parseResult.getRootASTElement();
            }
          } else if (!resource.getContents().isEmpty()) {
            rootElement = resource.getContents().get(0);
          }
          if (rootElement instanceof Grammar) {
            Grammar otherGrammar = (Grammar) rootElement;
            if (grammarName.equals(otherGrammar.getName())) {
              if (resource instanceof DerivedStateAwareResource) {
                resource.getContents();
              }
              return Collections.<EObject> singletonList(otherGrammar);
            }
          }
        }
        URI classpathURI = URI.createURI(ClasspathUriUtil.CLASSPATH_SCHEME + ":/" + grammarName.replace('.', '/') + ".xtext"); //$NON-NLS-1$ //$NON-NLS-2$
        URI normalizedURI;
        if (resourceSet instanceof XtextResourceSet) {
          XtextResourceSet set = (XtextResourceSet) resourceSet;
          normalizedURI = set.getClasspathUriResolver().resolve(set.getClasspathURIContext(), classpathURI);
        } else {
          normalizedURI = resourceSet.getURIConverter().normalize(classpathURI);
        }
        final Resource resource = resourceSet.getResource(normalizedURI, true);
        if (!resource.getContents().isEmpty()) {
          final Grammar usedGrammar = (Grammar) resource.getContents().get(0);
          if (grammarName.equals(usedGrammar.getName())) {
            return Collections.<EObject> singletonList(usedGrammar);
          }
        }
      }
      return Collections.emptyList();
    } catch (ClasspathUriResolutionException e) {
      return Collections.emptyList();
    } catch (ValueConverterException e) {
      return Collections.emptyList();
    }
  }

}

