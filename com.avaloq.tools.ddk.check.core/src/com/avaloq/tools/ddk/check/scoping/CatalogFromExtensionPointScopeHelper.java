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
package com.avaloq.tools.ddk.check.scoping;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.scoping.IScope;

import com.avaloq.tools.ddk.check.runtime.configuration.IModelLocation;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckCatalogRegistry;
import com.google.common.collect.Iterables;


/**
 * Creates scopes for check catalogs registered with the check extension point.
 */
public class CatalogFromExtensionPointScopeHelper {

  /**
   * Creates an extension scope for each registered check catalog.
   * 
   * @param parent
   *          the parent scope
   * @param context
   *          the context object
   * @return the check catalog scope
   */
  public IScope createExtensionScope(final IScope parent, final Resource context) {
    IScope result = parent;
    Iterable<IModelLocation> locations = ICheckCatalogRegistry.INSTANCE.getAllCheckModelLocations();
    if (locations != null && !Iterables.isEmpty(locations)) {
      for (IModelLocation locationData : locations) {
        result = new CatalogFromExtensionPointScope(result, locationData, (XtextResourceSet) context.getResourceSet());
      }
    }
    return result;
  }
}
