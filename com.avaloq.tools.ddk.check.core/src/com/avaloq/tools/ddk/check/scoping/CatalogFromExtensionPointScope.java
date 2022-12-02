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
package com.avaloq.tools.ddk.check.scoping;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.scoping.impl.AbstractScope;

import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.runtime.configuration.BundleAwareModelLocation;
import com.avaloq.tools.ddk.check.runtime.configuration.IModelLocation;
import com.google.common.collect.ImmutableList;


/**
 * Defines a scope scope for check catalogs located in the plugin registry.
 */
public class CatalogFromExtensionPointScope extends AbstractScope {

  private final IModelLocation locationData;
  private XtextResourceSet resourceSet;

  private CheckCatalog loadedModel;

  protected CatalogFromExtensionPointScope(final IScope parent, final IModelLocation locationData, final XtextResourceSet resourceSet) {
    super(parent, false);
    this.locationData = locationData;
    this.resourceSet = resourceSet;
  }

  @Override
  protected Iterable<IEObjectDescription> getAllLocalElements() {
    loadDefinitions();

    if (loadedModel == null) {
      return ImmutableList.of();
    }
    XtextResource resource = (XtextResource) loadedModel.eResource();
    IQualifiedNameProvider nameProvider = resource.getResourceServiceProvider().get(IQualifiedNameProvider.class);
    return Scopes.scopedElementsFor(ImmutableList.of(loadedModel), nameProvider);
  }

  /**
   * Loads a resource using the scope's resourceSet from the given uri, which must be based on the scope's locationData.
   *
   * @param uri
   *          to load the resource with
   * @return the resource, if it could be loaded, or {@code null} if not.
   */
  private Resource loadResource(final URI uri) {
    if (resourceSet == null) {
      return null;
    }

    Resource resource = resourceSet.getResource(uri, false);

    if (resource == null) {
      resource = resourceSet.createResource(uri);
      try {
        resource.load(null);
      } catch (IOException e) {
        resource = null; // NOPMD
      }

      if (resource != null) {
        resourceSet.getURIResourceMap().put(uri, resource); // Neither createResource() nor load() registers the resource in the map
      }
    }

    return resource;
  }

  /**
   * Loads the check resource associated with current check model location. Stores the qualified name
   * calculated for the check resource in the location data object for later access.
   */
  private void loadDefinitions() {
    if (loadedModel != null || resourceSet == null) {
      // Either we already loaded the model, or we got no resource set to begin with, or we failed to load the model earlier.
      return;
    }

    URI uri;
    if (locationData instanceof BundleAwareModelLocation) {
      if (resourceSet.getClasspathURIContext() == null) {
        resourceSet.setClasspathURIContext(((BundleAwareModelLocation) locationData).getBundle());
      }
      uri = URI.createURI(locationData.getCatalogUri().toString(), true);
    } else {
      uri = URI.createURI("platform:/resource/" + locationData.getCatalogPath(), true);
    }

    Resource resource = loadResource(uri);
    if (resource != null && !resource.getContents().isEmpty()) {
      loadedModel = (CheckCatalog) resource.getContents().get(0);
    }
    resourceSet = null; // NOPMD: Not needed anymore: we tried to load it, and if it failed then that's it.
  }
}
