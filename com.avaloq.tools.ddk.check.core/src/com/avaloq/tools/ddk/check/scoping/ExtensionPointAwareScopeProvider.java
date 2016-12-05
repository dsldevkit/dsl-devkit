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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.common.types.xtext.TypesAwareDefaultGlobalScopeProvider;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.containers.FilterUriContainer;
import org.eclipse.xtext.scoping.IScope;

import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;


/**
 * Plugin extension aware global scope provider.
 */
@SuppressWarnings("restriction")
public class ExtensionPointAwareScopeProvider extends TypesAwareDefaultGlobalScopeProvider {

  @Inject
  private CatalogFromExtensionPointScopeHelper helper;

  @Override
  protected IScope getScope(final IScope parent, final Resource context, final boolean ignoreCase, final EClass type, final Predicate<IEObjectDescription> filter) {
    IScope result = parent;
    if (type == CheckPackage.Literals.CHECK_CATALOG) {
      result = helper.createExtensionScope(result, context);
    }
    return super.getScope(result, context, ignoreCase, type, filter);
  }

  @Override
  protected IScope createContainerScopeWithContext(final Resource eResource, final IScope parent, final IContainer container, final Predicate<IEObjectDescription> filter, final EClass type, final boolean ignoreCase) {
    IContainer theContainer = container;
    if (eResource != null) {
      URI uriToFilter = eResource.getURI();
      if (container.hasResourceDescription(uriToFilter)) {
        theContainer = new MyFilterUriContainer(uriToFilter, container);
      }
    }
    return createContainerScope(parent, theContainer, filter, type, ignoreCase);
  }

  /**
   * Fixes the performance problem https://bugs.eclipse.org/bugs/show_bug.cgi?id=369690.
   */
  private static class MyFilterUriContainer extends FilterUriContainer {
    private final URI filterMe;
    private final IContainer delegate;

    MyFilterUriContainer(final URI uriToFilter, final IContainer container) {
      super(uriToFilter, container);
      filterMe = uriToFilter;
      delegate = container;
    }

    @Override
    public Iterable<IEObjectDescription> getExportedObjectsByType(final EClass type) {
      Iterable<IEObjectDescription> unfiltered = delegate.getExportedObjectsByType(type);
      return Iterables.filter(unfiltered, new Predicate<IEObjectDescription>() {
        @Override
        public boolean apply(final IEObjectDescription input) {
          URI resourceURI = input.getEObjectURI().trimFragment();
          return !resourceURI.equals(filterMe);
        }
      });
    }
  }
}
