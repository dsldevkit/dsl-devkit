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
package com.avaloq.tools.ddk.workflow;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.xtext.generator.XtextGeneratorLanguage;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


/**
 * Registers EPackages for referenced GenModls. Supports content types.
 */
public class ExtendedLanguageConfig extends XtextGeneratorLanguage {

  private List<String> contentTypes;
  private String languageName;
  private String preferencePagesCategory;

  private static final Splitter SPLITTER = Splitter.on(',').trimResults().omitEmptyStrings();

  public void setContentTypes(final String contentTypes) {
    this.contentTypes = Lists.newArrayList(SPLITTER.split(contentTypes));
  }

  public List<String> getContentTypes() {
    return contentTypes;
  }

  public void setLanguageName(final String languageName) {
    this.languageName = languageName;
  }

  public String getLanguageName() {
    return languageName;
  }

  public void setPreferencePagesCategory(final String preferencePagesCategory) {
    this.preferencePagesCategory = preferencePagesCategory;
  }

  public String getPreferencePagesCategory() {
    return preferencePagesCategory;
  }

  @SuppressWarnings("nls")
  public static Resource getGenModelResource(final String nsURI, final ResourceSet resourceSet) {
    URI genModelURI = EcorePlugin.getEPackageNsURIToGenModelLocationMap(false).get(nsURI);
    if (genModelURI == null) {
      if (EcorePackage.eNS_URI.equals(nsURI)) {
        return null;
      }

      // look into the resource set to find a genpackage for the given URI
      for (Resource res : resourceSet.getResources()) {

        // we only look into the first level, as genmodels are usually among the top level elements.
        // this just to avoid traversing all eobjects in the resource set.
        for (EObject obj : res.getContents()) {
          if (obj instanceof GenModel) {
            for (GenPackage genPackage : ((GenModel) obj).getGenPackages()) {
              if (genPackage.getNSURI().equals(nsURI)) {
                return genPackage.eResource();
              }
            }
          }
        }
      }

      // CHECKSTYLE:OFF MagicNumber
      StringBuilder buf = new StringBuilder(100);
      // CHECKSTYLE:ON
      buf.append("Could not find a GenModel for EPackage '").append(nsURI).append('\'').append('\n');
      throw new RuntimeException(buf.toString());
    }
    if (resourceSet == null) {
      throw new RuntimeException("There is no ResourceSet for EPackage '" + nsURI + "'. " + "Please make sure the EPackage has been loaded from a .ecore file "
          + "and not from the generated Java file.");
    }
    Resource genModelResource = resourceSet.getResource(genModelURI, true);
    if (genModelResource == null) {
      throw new RuntimeException("Error loading GenModel " + genModelURI);
    }
    for (EObject content : genModelResource.getContents()) {
      if (content instanceof GenModel) {
        ((GenModel) content).reconcile();
      }
    }
    return genModelResource;
  }

  /**
   * {@inheritDoc}
   * <p>
   * Registers all EPackages (transitively) referenced by registered GenModels prior to calling {@link XtextGeneratorLanguage#setGrammarUri(String)}.
   */
  @Override
  public void setGrammarUri(final String uri) {
    ResourceSet rs = new ResourceSetImpl();
    Set<URI> result = Sets.newHashSet();
    Map<String, URI> genModelLocationMap = EcorePlugin.getEPackageNsURIToGenModelLocationMap(false);
    for (Map.Entry<String, URI> entry : genModelLocationMap.entrySet()) {
      Resource resource = getGenModelResource(entry.getKey(), rs);
      if (resource != null) {
        for (EObject model : resource.getContents()) {
          if (model instanceof GenModel) {
            GenModel genModel = (GenModel) model;
            result.addAll(getReferencedEPackages(genModel));
          }
        }
      }
    }
    for (URI u : result) {
      addReferencedResource(u.toString());
    }
    super.setGrammarUri(uri);
  }

  /**
   * Returns the set of all EPackage resource URIs referenced by the given GenModel.
   *
   * @param genModel
   *          genModel
   * @return set of URIs
   */
  private Set<URI> getReferencedEPackages(final GenModel genModel) {
    Set<URI> result = Sets.newHashSet();
    for (GenPackage genPackage : genModel.getGenPackages()) {
      EPackage ePackage = genPackage.getEcorePackage();
      result.add(ePackage.eResource().getURI());
    }
    for (GenPackage genPackage : genModel.getUsedGenPackages()) {
      result.addAll(getReferencedEPackages(genPackage.getGenModel()));
    }
    return result;
  }
}
