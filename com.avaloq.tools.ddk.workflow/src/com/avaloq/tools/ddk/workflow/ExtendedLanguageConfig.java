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
package com.avaloq.tools.ddk.workflow;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.generator.GenModelAccess;
import org.eclipse.xtext.generator.LanguageConfig;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


/**
 * Registers EPackages for referenced GenModls. Supports content types.
 */
public class ExtendedLanguageConfig extends LanguageConfig {

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

  @Override
  protected boolean isCheckFileExtension() {
    return false;
  }

  /**
   * {@inheritDoc}
   * <p>
   * Registers all EPackages (transitively) referenced by registered GenModels prior to calling {@link LanguageConfig#setUri(String)}.
   */
  @Override
  public void setUri(final String uri) {
    ResourceSet rs = new ResourceSetImpl();
    Set<URI> result = Sets.newHashSet();
    @SuppressWarnings("deprecation")
    Map<String, URI> genModelLocationMap = EcorePlugin.getEPackageNsURIToGenModelLocationMap();
    for (Map.Entry<String, URI> entry : genModelLocationMap.entrySet()) {
      Resource resource = GenModelAccess.getGenModelResource(null, entry.getKey(), rs);
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
      addLoadedResource(u.toString());
    }
    super.setUri(uri);
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
