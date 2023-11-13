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
package com.avaloq.tools.ddk.xtext.scoping;

import java.util.Collections;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopeProvider;

import com.google.common.collect.Iterables;


/**
 * This scope does not contain its scoped elements directly. Instead it has a list of delegate objects for which the scope is
 * evaluated using the scope provider. The result of all these scopes is then concatenated into a single scope (this scope).
 */
public class DelegatingScope extends AbstractRecursiveScope {

  private static final Logger LOGGER = LogManager.getLogger(DelegatingScope.class);

  /**
   * Stores the scope ID to be logged by {@link #STACK_OVERFLOW_EOBJECT_DESCRIPTION}.
   * <p>
   * If multiple threads were to concurrently run into {@link StackOverflowError} it is possible that the threads would log the wrong scope IDs. But this is
   * very unlikely to happen and the scope ID is only logged to assist the developer in addressing the programming error.
   */
  private static String stackOverflowScopeId;

  /**
   * @see #STACK_OVERFLOW_SCOPE
   */
  private static final IEObjectDescription STACK_OVERFLOW_EOBJECT_DESCRIPTION = new IEObjectDescription() {
    @Override
    public QualifiedName getName() {
      logError();
      return QualifiedName.create("##stackoverflow##"); //$NON-NLS-1$
    }

    @Override
    public QualifiedName getQualifiedName() {
      logError();
      return getName();
    }

    @Override
    public EObject getEObjectOrProxy() {
      logError();
      return EcorePackage.Literals.EOBJECT;
    }

    @Override
    public URI getEObjectURI() {
      logError();
      return EcoreUtil.getURI(getEObjectOrProxy());
    }

    @Override
    public EClass getEClass() {
      logError();
      return EcorePackage.Literals.ECLASS;
    }

    @Override
    public String getUserData(final String key) {
      logError();
      return null;
    }

    @Override
    public String[] getUserDataKeys() {
      logError();
      return new String[0];
    }

    private void logError() {
      LOGGER.error("Cyclic delegate detected in scope \"" + stackOverflowScopeId + "\"."); //$NON-NLS-1$ //$NON-NLS-2$
    }
  };

  /**
   * This object will be returned by the transformation in {@link #getDelegates()} whenever a {@link StackOverflowError} is detected.
   * <p>
   * Because the stack overflow will occur in that method itself or only very few stack frames deeper, it is unfortunately not possible to simply log an error
   * directly in the exception handler, as that would again result in a {@link StackOverflowError}!. To solve this problem this instance will be returned and it
   * in turn will return the {@link #STACK_OVERFLOW_EOBJECT_DESCRIPTION} object in its method. The error will then get logged when calling a method on that
   * object. At this point the stack has been "unwound" so far that the error logging won't be an issue anymore.
   */
  private static final IScope STACK_OVERFLOW_SCOPE = new IScope() {
    @Override
    public IEObjectDescription getSingleElement(final EObject object) {
      return STACK_OVERFLOW_EOBJECT_DESCRIPTION;
    }

    @Override
    public IEObjectDescription getSingleElement(final QualifiedName name) {
      return STACK_OVERFLOW_EOBJECT_DESCRIPTION;
    }

    @Override
    public Iterable<IEObjectDescription> getElements(final EObject object) {
      return Collections.singleton(STACK_OVERFLOW_EOBJECT_DESCRIPTION);
    }

    @Override
    public Iterable<IEObjectDescription> getElements(final QualifiedName name) {
      return Collections.singleton(STACK_OVERFLOW_EOBJECT_DESCRIPTION);
    }

    @Override
    public Iterable<IEObjectDescription> getAllElements() {
      return Collections.singleton(STACK_OVERFLOW_EOBJECT_DESCRIPTION);
    }
  };

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
      delegates = Iterables.transform(contexts.get(), o -> {
        try {
          return eReference != null ? scopeProvider.getScope(o, eReference, scopeName, originalResource)
              : scopeProvider.getScope(o, eClass, scopeName, originalResource);
        } catch (StackOverflowError e) {
          stackOverflowScopeId = DelegatingScope.this.getId();
          return STACK_OVERFLOW_SCOPE;
        }
      });
    }
    return delegates;
  }

  @Override
  public Iterable<IEObjectDescription> getAllLocalElements() {
    return Iterables.concat(Iterables.transform(getDelegates(), IScope::getAllElements));
  }

  @Override
  public IEObjectDescription getSingleElement(final QualifiedName name) {
    // We don't use caching here because we typically have only one delegate which does the caching itself, so caching
    // here would just introduce double caching for no or very little benefit.
    if (name == null) {
      throw new IllegalArgumentException("Null name in getContentByName"); //$NON-NLS-1$
    }

    try {
      for (final IScope scope : getDelegates()) {
        final IEObjectDescription elem = scope.getSingleElement(name);
        if (elem != null) {
          // ScopeTrace.addTrace(elem, getId());
          return elem;
        }
      }
      return getParent().getSingleElement(name);
    } catch (StackOverflowError e) {
      return STACK_OVERFLOW_EOBJECT_DESCRIPTION;
    }
  }

  @Override
  protected Iterable<IEObjectDescription> getLocalElementsByName(final QualifiedName name) {
    return Iterables.concat(Iterables.transform(getDelegates(), s -> s.getElements(name)));
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
