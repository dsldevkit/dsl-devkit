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
package com.avaloq.tools.ddk.xtext.scoping;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopeProvider;

import com.avaloq.tools.ddk.xtext.util.EObjectUtil;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;


/**
 * This scope does not contain its scoped elements directly. Instead it has a list of delegate objects for which the scope is
 * evaluated using the scope provider. The result of all these scopes is then concatenated into a single scope (this scope).
 */
public class DelegatingScope extends AbstractRecursiveScope {

  private static final Logger LOGGER = Logger.getLogger(DelegatingScope.class);
  private final AbstractPolymorphicScopeProvider scopeProvider;

  private final IContextSupplier contexts;

  private final EReference eReference;

  private final EClass eClass;

  private final String scopeName;

  private final Resource originalResource;

  /** Cache the delegate scopes, once evaluated. */
  private Iterable<IScope> delegates;

  /**
   * Instantiates a new delegating scope.
   *
   * @param id
   *          the id
   * @param scopeProvider
   *          the scope provider
   * @param parent
   *          the outer
   * @param contexts
   *          the contexts
   * @param eReference
   *          the e reference
   * @param scopeName
   *          the scope name
   * @param originalResource
   *          the original resource
   */
  public DelegatingScope(final String id, final IScopeProvider scopeProvider, final IScope parent, final IContextSupplier contexts, final EReference eReference, final String scopeName, final Resource originalResource) {
    super(id, parent);
    this.scopeProvider = (AbstractPolymorphicScopeProvider) scopeProvider;
    this.contexts = contexts;
    this.eReference = eReference;
    this.eClass = null; // NOPMD NullAssignment
    this.scopeName = scopeName;
    this.originalResource = originalResource;
  }

  /**
   * Instantiates a new delegating scope.
   *
   * @param id
   *          the id
   * @param scopeProvider
   *          the scope provider
   * @param parent
   *          the outer
   * @param contexts
   *          the contexts
   * @param eClass
   *          the e class
   * @param scopeName
   *          the scope name
   * @param originalResource
   *          the original resource
   */
  public DelegatingScope(final String id, final IScopeProvider scopeProvider, final IScope parent, final IContextSupplier contexts, final EClass eClass, final String scopeName, final Resource originalResource) {
    super(id, parent);
    this.scopeProvider = (AbstractPolymorphicScopeProvider) scopeProvider;
    this.contexts = contexts;
    this.eClass = eClass;
    this.eReference = null; // NOPMD NullAssignment
    this.scopeName = scopeName;
    this.originalResource = originalResource;
  }

  /**
   * Evaluate the delegate scopes.
   *
   * @return The delegate scopes.
   */
  protected Iterable<IScope> getDelegates() {
    if (delegates == null) {
      delegates = Iterables.transform(contexts.get(), new Function<EObject, IScope>() {
        @Override
        public IScope apply(final EObject from) {
          IScope scope = eReference != null ? scopeProvider.getScope(from, eReference, scopeName, originalResource)
              : scopeProvider.getScope(from, eClass, scopeName, originalResource);
          if (scope == DelegatingScope.this) { // NOPMD
            LOGGER.error("Cyclic delegate detected in scope \"" + getId() + "\" while computing scope for " + EObjectUtil.getLocationString(from)); //$NON-NLS-1$ //$NON-NLS-2$
            return IScope.NULLSCOPE;
          }
          return scope;
        }
      });
    }
    return delegates;
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<IEObjectDescription> getAllLocalElements() {
    return Iterables.concat(Iterables.transform(getDelegates(), new Function<IScope, Iterable<IEObjectDescription>>() {
      @Override
      public Iterable<IEObjectDescription> apply(final IScope from) {
        return from.getAllElements();
      }
    }));
  }

  /** {@inheritDoc} */
  @Override
  public IEObjectDescription getSingleElement(final QualifiedName name) {
    // We don't use caching here because we typically have only one delegate which does the caching itself, so caching
    // here would just introduce double caching for no or very little benefit.
    if (name == null) {
      throw new IllegalArgumentException("Null name in getContentByName"); //$NON-NLS-1$
    }
    for (final IScope scope : getDelegates()) {
      final IEObjectDescription elem = scope.getSingleElement(name);
      if (elem != null) {
        // ScopeTrace.addTrace(elem, getId());
        return elem;
      }
    }
    return getParent().getSingleElement(name);
  }

  @Override
  protected Iterable<IEObjectDescription> getLocalElementsByName(final QualifiedName name) {
    return Iterables.concat(Iterables.transform(getDelegates(), new Function<IScope, Iterable<IEObjectDescription>>() {
      @Override
      public Iterable<IEObjectDescription> apply(final IScope from) {
        return from.getElements(name);
      }
    }));
  }

  @SuppressWarnings("nls")
  @Override
  public String toString() {
    final StringBuilder result = new StringBuilder(getClass().getName());
    result.append('@');
    result.append(Integer.toHexString(hashCode()));

    result.append(" (id: ");
    result.append(getId());

    final Iterable<IScope> delegateScopes = getDelegates();
    if (delegateScopes != null && !Iterables.isEmpty(delegateScopes)) {
      result.append(", delegates: ");
      result.append(Iterables.toString(delegateScopes));
    }
    result.append(')');

    final IScope outerScope = getParent();
    if (outerScope != IScope.NULLSCOPE) {
      result.append("\n  >> ");
      result.append(outerScope.toString().replaceAll("\\\n", "\n  "));
    }

    return result.toString();
  }
}
