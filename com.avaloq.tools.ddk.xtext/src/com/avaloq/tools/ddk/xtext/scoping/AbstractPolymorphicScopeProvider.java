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
package com.avaloq.tools.ddk.xtext.scoping; // NOPMD ExcessiveImports

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.AbstractScopeProvider;

import com.avaloq.tools.ddk.caching.CacheManager;
import com.avaloq.tools.ddk.caching.ResourceCache;
import com.avaloq.tools.ddk.xtext.naming.QualifiedNames;
import com.avaloq.tools.ddk.xtext.scoping.impl.BranchingScope;
import com.avaloq.tools.ddk.xtext.scoping.impl.DataFilteringScope;
import com.avaloq.tools.ddk.xtext.util.EObjectUtil;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;


/**
 * Abstract base class for<i>Scope DSL</i> based scope provider implementations.
 */
// CHECKSTYLE:COUPLING-OFF
@SuppressWarnings("PMD.ExcessiveClassLength")
public abstract class AbstractPolymorphicScopeProvider extends AbstractScopeProvider {
  // CHECKSTYLE:COUPLING-ON
  private static final String SCOPE_STRING = "scope"; //$NON-NLS-1$
  private static final String NAMED_ELEMENT_MUST_BE_OF_TYPE_E_REFERENCE_OR_E_CLASS = "Named element must be of type EReference or EClass."; //$NON-NLS-1$
  private static final String DELEGATE_SCOPE_RESOURCE_ERR_MSG = "Delegate scope cannot delegate to another resource"; //$NON-NLS-1$

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(AbstractPolymorphicScopeProvider.class);

  /** Guice injected name provider to determine names for a given type. */
  @Inject
  private IScopeNameProvider nameProvider;

  @Inject
  private IQualifiedNameConverter nameConverter;

  @Inject
  private IDomain.Mapper domainMapper;

  @Inject
  private ResourceDescriptionsProvider resourceDescriptionsProvider;

  @Inject
  private IScopeCacheKeyComputer cacheKeyComputer;

  /**
   * Returns the index to use for the given resource.
   *
   * @param resource
   *          the resource
   * @return the index
   */
  public IResourceDescriptions getResourceDescriptions(final Resource resource) {
    return resourceDescriptionsProvider.getResourceDescriptions(resource);
  }

  /**
   * Single instance to cache instead of null, to be able to distinguish from previous unsuccessful dispatch attempts via
   * EReference. Note that we don't need this for the EClass dispatches, since these never return null but NULL_SCOPE.
   */
  private static final IScope NO_SCOPE = NullScope.getInstance();

  /**
   * Register an imported name. We may have gotten the context object through navigation, not through an index lookup, and
   * moreover we may use it only transiently during a delegating scope chain, so we'll never register the dependency otherwise.
   * But the dependency must be registered for the builder's dependency calculation to correctly handle the case where an
   * inheritance hierarchy changes.
   *
   * @param context
   *          The foreign context object
   * @param contextResource
   *          Its resource
   * @param originalResource
   *          Our resource
   */
  protected void registerForeignObject(final EObject context, final XtextResource contextResource, final Resource originalResource) {
    ImplicitReferencesAdapter.findOrCreate(originalResource).addImplicitReference(contextResource.getURI());
  }

  // "Exported" operation (from IScopeProvider). As this is the only operation known from the interface, this is
  // the sole entry point that is used by all clients, except our own generated code, which *knows* that the
  // scope provider is an AbstractPolymorphicScopeProvider. This also means that scoping always is rooted at
  // a normal scope; the scope expression then may, however, delegate to an auxiliary scope. Note that there
  // may be back-references from auxiliary scopes to the main scope (named "scope"), and thus loops are
  // possible.

  /** {@inheritDoc} */
  @Override
  public IScope getScope(final EObject context, final EReference reference) {
    return getScope(context, reference, null, context.eResource());
  }

  /**
   * Returns a scope for a given context object and EReference, using a named scope definition. If none is found
   * tries to find a scope (using the same name) for the type of the reference.
   *
   * @param context
   *          the context
   * @param reference
   *          the reference
   * @param scopeName
   *          the scope name
   * @param originalResource
   *          the original resource
   * @return the scope
   */
  public IScope getScope(final EObject context, final EReference reference, final String scopeName, final Resource originalResource) {
    if (context.eResource() != originalResource && context.eResource() instanceof XtextResource) {
      registerForeignObject(context, (XtextResource) context.eResource(), originalResource);
    }
    String nameToFind = scopeName == null ? SCOPE_STRING : scopeName;
    IScope scope = internalGetScope(context, reference, nameToFind, originalResource);
    if (scope == null) {
      scope = internalGetScope(context, reference.getEReferenceType(), nameToFind, originalResource);
    }
    return (scope == null) ? IScope.NULLSCOPE : scope;
  }

  /**
   * Check whether the scope should be cached for the given lookup.
   *
   * @param context
   *          the context, must not be {@code null}
   * @param reference
   *          the reference, must not be {@code null}
   * @param scopeName
   *          the scope name, must not be {@code null}
   * @param originalResource
   *          the original resource, must not be {@code null}
   * @return true if it should be cached
   */
  protected boolean doCache(final EObject context, final EReference reference, final String scopeName, final Resource originalResource) {
    return !context.eContents().isEmpty();
  }

  /**
   * Check whether the scope should be globally cached for the given lookup.
   *
   * @param context
   *          the context, must not be {@code null}
   * @param reference
   *          the reference, must not be {@code null}
   * @param scopeName
   *          the scope name, must not be {@code null}
   * @param originalResource
   *          the original resource, must not be {@code null}
   * @return true if it should be globally cached
   */
  protected boolean doGlobalCache(final EObject context, final EReference reference, final String scopeName, final Resource originalResource) {
    return false;
  }

  /**
   * Called by {@link #internalGetScope(EObject, EReference, String, Resource)} if no cached result is available.
   *
   * @param context
   *          the context
   * @param reference
   *          the reference
   * @param scopeName
   *          the scope name
   * @param originalResource
   *          the original resource
   * @return the scope
   */
  protected abstract IScope doGetScope(final EObject context, final EReference reference, final String scopeName, final Resource originalResource);

  /**
   * Gets the scope given a context object and an expected type.
   *
   * @param context
   *          the context
   * @param type
   *          the type
   * @return the scope
   */
  public IScope getScope(final EObject context, final EClass type) {
    return getScope(context, type, null, context.eResource());
  }

  /**
   * Gets the scope given a context object and an expected type, using the given scope name.
   *
   * @param context
   *          the context
   * @param type
   *          the type
   * @param scopeName
   *          the scope name
   * @param originalResource
   *          the original resource
   * @return the scope
   */
  public IScope getScope(final EObject context, final EClass type, final String scopeName, final Resource originalResource) {
    if (context.eResource() != originalResource && context.eResource() instanceof XtextResource) {
      registerForeignObject(context, (XtextResource) context.eResource(), originalResource);
    }
    final IScope result = internalGetScope(context, type, scopeName == null ? SCOPE_STRING : scopeName, originalResource);
    return (result == null) ? IScope.NULLSCOPE : result;
  }

  /**
   * Check whether the scope should be cached for the given lookup.
   *
   * @param context
   *          the context
   * @param type
   *          the type
   * @param scopeName
   *          the scope name
   * @param originalResource
   *          the original resource
   * @return true if it should be cached
   */
  protected boolean doCache(final EObject context, final EClass type, final String scopeName, final Resource originalResource) {
    return !context.eContents().isEmpty();
  }

  /**
   * Check whether the scope should be globally cached for the given lookup.
   *
   * @param context
   *          the context, must not be {@code null}
   * @param type
   *          the type, must not be {@code null}
   * @param scopeName
   *          the scope name, must not be {@code null}
   * @param originalResource
   *          the original resource, must not be {@code null}
   * @return true if it should be globally cached
   */
  protected boolean doGlobalCache(final EObject context, final EClass type, final String scopeName, final Resource originalResource) {
    return false;
  }

  /**
   * Called by {@link #internalGetScope(EObject, ENamedElement, String, Resource)} if no cached result is available.
   *
   * @param context
   *          the context, must not be {@code null}
   * @param type
   *          the type, must not be {@code null}
   * @param scopeName
   *          the scope name, must not be {@code null}
   * @param originalResource
   *          the original resource, must not be {@code null}
   * @return the scope, or {@code null}
   */
  protected abstract IScope doGetScope(final EObject context, final EClass type, final String scopeName, final Resource originalResource);

  /**
   * Return the visible containers given a context object.
   *
   * @param context
   *          The context object
   * @param originalResource
   *          the original resource
   * @return The list of visible containers.
   */
  protected List<IContainer> getVisibleContainers(final EObject context, final Resource originalResource) { // NOPMD by WTH on 26.01.11 09:26 (NPath
                                                                                                            // complexity...)
    final Resource ctxRsc = originalResource == null ? context.eResource() : originalResource;
    if (!(ctxRsc instanceof XtextResource)) {
      LOGGER.error(MessageFormat.format("Context {0} is not in an Xtext resource: {1}", context, ctxRsc != null ? ctxRsc.getURI() : "null")); //$NON-NLS-1$ //$NON-NLS-2$
      throw new IllegalStateException();
    }
    final XtextResource rsc = (XtextResource) ctxRsc;
    // Cache these container lists, they're expensive to create
    URI uri = rsc.getURI();
    List<IContainer> result = null;
    final ResourceCache<URI, List<IContainer>> cache = CacheManager.getInstance().getOrCreateResourceCache("AbstractPolymorphicScopeProvider#visibleContainers", rsc); //$NON-NLS-1$
    if (cache != null) {
      result = cache.get(uri);
      if (result != null) {
        return result;
      }
    }

    final EObject ctx = ctxRsc != context.eResource() ? ctxRsc.getContents().get(0) : context;
    // We need to get the container manager dynamically, otherwise we may end up using the wrong ResourceDescriptions if
    // the context object in actually from another resource.
    final IResourceServiceProvider resourceServiceProvider = rsc.getResourceServiceProvider();
    final IResourceDescription.Manager descriptionManager = resourceServiceProvider.getResourceDescriptionManager();
    final IContainer.Manager containerManager = resourceServiceProvider.getContainerManager();

    final IResourceDescription description = descriptionManager.getResourceDescription(ctx.eResource());
    final IResourceDescriptions resourceDescriptions = getResourceDescriptions(ctx.eResource());
    result = containerManager.getVisibleContainers(description, resourceDescriptions);
    if (cache != null) {
      cache.set(uri, result);
    }
    return result;
  }

  /**
   * Returns the root object (outermost container) of any given object.
   *
   * @param context
   *          context object
   * @return root object of context
   */
  protected EObject getRootObject(final EObject context) {
    EObject obj = context;
    while (obj.eContainer() != null) {
      obj = obj.eContainer();
    }
    return obj;
  }

  /**
   * Gets the scope given a context object and an expected type, using the given scope name.
   *
   * @param context
   *          the context, must not be {@code null}
   * @param namedElement
   *          the named element (either EClass or EReference), must not be {@code null}
   * @param scopeName
   *          the scope name, must not be {@code null}
   * @param originalResource
   *          the original resource, must not be {@code null}
   * @return the scope, or {@code null} if none
   */
  @SuppressWarnings({"PMD.CompareObjectsWithEquals", "PMD.NPathComplexity"})
  protected IScope internalGetScope(final EObject context, final ENamedElement namedElement, final String scopeName, final Resource originalResource) {
    IScope result = null;
    IResourceSetScopeCache resourceSetScopeCache = null;
    ResourceCache<Object, IScope> resourceScopeCache = null;
    Object cacheKey = null;
    Object globalCacheKey = null;

    if (originalResource != null) {
      final ResourceSet resourceSet = originalResource.getResourceSet();
      if (resourceSet != null) {
        final Adapter scopeCacheAdapter = EcoreUtil.getAdapter(resourceSet.eAdapters(), IResourceSetScopeCache.class);
        if (scopeCacheAdapter instanceof IResourceSetScopeCache && doGlobalCache(context, namedElement, scopeName, originalResource)) {
          resourceSetScopeCache = (IResourceSetScopeCache) scopeCacheAdapter;
          globalCacheKey = getGlobalCacheKey(context, namedElement, scopeName);
          result = resourceSetScopeCache.get(globalCacheKey);
        }
      }
      if (result == null && doCache(context, namedElement, scopeName, originalResource)) {
        resourceScopeCache = CacheManager.getInstance().getOrCreateResourceCache("AbstractPolymorphicScopeProvider#resourceScopeCache", originalResource); //$NON-NLS-1$
        cacheKey = getCacheKey(context, namedElement, scopeName);
        result = resourceScopeCache.get(cacheKey);
      }
      if (result != null) {
        return result == NO_SCOPE ? null : result;
      }
    }

    result = doGetScope(context, namedElement, scopeName, originalResource);

    if (resourceSetScopeCache != null) {
      resourceSetScopeCache.put(globalCacheKey, result == null ? NO_SCOPE : result);
    } else if (resourceScopeCache != null) {
      resourceScopeCache.set(cacheKey, result == null ? NO_SCOPE : result);
    }
    return result;
  }

  /**
   * Check whether the scope should be cached for the given lookup.
   *
   * @param context
   *          the context, must not be {@code null}
   * @param namedElement
   *          the named element (either {@link EClass} or {@link EReference}), must not be {@code null}
   * @param scopeName
   *          the scope name, must not be {@code null}
   * @param originalResource
   *          the original resource, must not be {@code null}
   * @return {@code true} if {@code context} is contained in a resource and the scope should be cached
   */
  private boolean doCache(final EObject context, final ENamedElement namedElement, final String scopeName, final Resource originalResource) {
    if (context.eResource() == null) {
      return false;
    } else if (namedElement instanceof EReference) {
      return doCache(context, (EReference) namedElement, scopeName, originalResource);
    } else if (namedElement instanceof EClass) {
      return doCache(context, (EClass) namedElement, scopeName, originalResource);
    }
    throw new IllegalArgumentException(NAMED_ELEMENT_MUST_BE_OF_TYPE_E_REFERENCE_OR_E_CLASS);
  }

  /**
   * Check whether the scope should be globally cached for the given lookup.
   *
   * @param context
   *          the context, must not be {@code null}
   * @param namedElement
   *          the namedElement (either {@link EClass} or {@link EReference}), must not be {@code null}
   * @param scopeName
   *          the scope name, must not be {@code null}
   * @param originalResource
   *          the original resource, must not be {@code null}
   * @return {@code true} if {@code context} is contained in a resource and the scope should be globally cached
   */
  private boolean doGlobalCache(final EObject context, final ENamedElement namedElement, final String scopeName, final Resource originalResource) {
    if (context.eResource() == null) {
      return false;
    } else if (namedElement instanceof EReference) {
      return doGlobalCache(context, (EReference) namedElement, scopeName, originalResource);
    } else if (namedElement instanceof EClass) {
      return doGlobalCache(context, (EClass) namedElement, scopeName, originalResource);
    }
    throw new IllegalArgumentException(NAMED_ELEMENT_MUST_BE_OF_TYPE_E_REFERENCE_OR_E_CLASS);
  }

  /**
   * Returns the global cache key for caching dispatchers and scope results.
   *
   * @param context
   *          context object contained in a resource, must not be {@code null}
   * @param namedElement
   *          named element of scope (either {@link EClass} or {@link EReference}), must not be {@code null}
   * @param scopeName
   *          name of scope, must not be {@code null}
   * @return global scope cache key, never {@code null}
   */
  private Object getGlobalCacheKey(final EObject context, final ENamedElement namedElement, final String scopeName) {
    if (namedElement instanceof EReference) {
      return cacheKeyComputer.getGlobalCacheKey(context, (EReference) namedElement, scopeName);
    } else if (namedElement instanceof EClass) {
      return cacheKeyComputer.getGlobalCacheKey(context, (EClass) namedElement, scopeName);
    }
    throw new IllegalArgumentException(NAMED_ELEMENT_MUST_BE_OF_TYPE_E_REFERENCE_OR_E_CLASS);
  }

  /**
   * Returns a pooled string used as key when caching dispatchers and scope results.
   *
   * @param context
   *          context object, must not be {@code null}
   * @param namedElement
   *          named element of scope (either {@link EClass} or {@link EReference}), must not be {@code null}
   * @param scopeName
   *          name of scope, must not be {@code null}
   * @return pooled cache key, never {@code null}
   */
  private Object getCacheKey(final EObject context, final ENamedElement namedElement, final String scopeName) {
    if (namedElement instanceof EReference) {
      return cacheKeyComputer.getCacheKey(context, (EReference) namedElement, scopeName);
    } else if (namedElement instanceof EClass) {
      return cacheKeyComputer.getCacheKey(context, (EClass) namedElement, scopeName);
    }
    throw new IllegalArgumentException(NAMED_ELEMENT_MUST_BE_OF_TYPE_E_REFERENCE_OR_E_CLASS);
  }

  /**
   * Called by {@link #internalGetScope(EObject, ENamedElement, String, Resource)} if no cached result is available.
   *
   * @param context
   *          the context, must not be {@code null}
   * @param namedElement
   *          the named element (either {@link EClass} or {@link EReference}), must not be {@code null}
   * @param scopeName
   *          the scope name, must not be {@code null}
   * @param originalResource
   *          the original resource, must not be {@code null}
   * @return the scope, or {@code null}
   */
  protected IScope doGetScope(final EObject context, final ENamedElement namedElement, final String scopeName, final Resource originalResource) {
    if (namedElement instanceof EReference) {
      return doGetScope(context, (EReference) namedElement, scopeName, originalResource);
    } else if (namedElement instanceof EClass) {
      return doGetScope(context, (EClass) namedElement, scopeName, originalResource);
    }
    throw new IllegalArgumentException(NAMED_ELEMENT_MUST_BE_OF_TYPE_E_REFERENCE_OR_E_CLASS);
  }

  /**
   * Special delegate scope for scopeof (this), skipping the delegate, if possible.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param parent
   *          The parent scope
   * @param context
   *          The context object
   * @param reference
   *          The reference
   * @param scopeName
   *          The scope name
   * @param originalResource
   *          The original resource
   * @return a scope for the context object
   */
  protected IScope newSameScope(final String id, final IScope parent, final EObject context, final EReference reference, final String scopeName, final Resource originalResource) {
    if (context == null) {
      return parent;
    }
    if (parent == IScope.NULLSCOPE) {
      return getScope(context, reference, scopeName, originalResource);
    } else {
      return newDelegateScope(id, parent, context, reference, scopeName, originalResource);
    }
  }

  /**
   * Special delegate scope for scopeof (this), skipping the delegate, if possible.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param parent
   *          The parent scope
   * @param context
   *          The context object
   * @param type
   *          The type
   * @param scopeName
   *          The scope name
   * @param originalResource
   *          The original resource
   * @return a scope for the context object
   */
  protected IScope newSameScope(final String id, final IScope parent, final EObject context, final EClass type, final String scopeName, final Resource originalResource) {
    if (context == null) {
      return parent;
    }
    if (parent == IScope.NULLSCOPE) {
      return getScope(context, type, scopeName, originalResource);
    } else {
      return newDelegateScope(id, parent, context, type, scopeName, originalResource);
    }
  }

  /**
   * Special delegate scope for scopeof (this), skipping the delegate, if possible.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param parent
   *          The parent scope
   * @param context
   *          The context resource
   * @param ref
   *          The reference
   * @param scopeName
   *          The scope name
   * @param originalResource
   *          The original resource
   * @return a scope for the context object
   */
  protected IScope newSameScope(final String id, final IScope parent, final Resource context, final EReference ref, final String scopeName, final Resource originalResource) {
    if (context == null) {
      return parent;
    }
    if (parent == IScope.NULLSCOPE) {
      return getScope(context.getContents().get(0), ref, scopeName, originalResource);
    } else {
      return newDelegateScope(id, parent, context, ref, scopeName, originalResource);
    }
  }

  /**
   * Special delegate scope for scopeof (this), skipping the delegate, if possible.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param parent
   *          The parent scope
   * @param context
   *          The context resource
   * @param type
   *          The type
   * @param scopeName
   *          The scope name
   * @param originalResource
   *          The original resource
   * @return a scope for the context object
   */
  protected IScope newSameScope(final String id, final IScope parent, final Resource context, final EClass type, final String scopeName, final Resource originalResource) {
    if (context == null) {
      return parent;
    }
    if (parent == IScope.NULLSCOPE) {
      return getScope(context.getContents().get(0), type, scopeName, originalResource);
    } else {
      return newDelegateScope(id, parent, context, type, scopeName, originalResource);
    }
  }

  /**
   * Create a new container scope using the results of a given query as its contents.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param outer
   *          The outer scope of the new scope.
   * @param context
   *          The context
   * @param query
   *          The query that defines the scope's contents
   * @param nameFunctions
   *          The name functions to apply
   * @param originalResource
   *          The original resource
   * @param caseInsensitive
   *          indicates whether the new scope shall be case insensitive
   * @return The new scope
   */
  protected IScope newContainerScope(final String id, final IScope outer, final EObject context, final ContainerQuery query, final Resource originalResource, final Iterable<INameFunction> nameFunctions, final boolean caseInsensitive) {
    IScope result = outer;
    final List<String> domains = query.getDomains();
    for (final IContainer container : Lists.reverse(getVisibleContainers(context, originalResource))) {
      if (!domains.isEmpty()) {
        final IDomain domain = domainMapper.map(container);
        if (domain != null && !domains.contains(domain.getName())) {
          continue; // Query not applicable to this container.
        }
      }
      result = new ContainerBasedScope(id, result, container, query, nameFunctions, caseInsensitive);
    }
    return result;
  }

  /**
   * Create a new container scope using the results of a given query as its contents, using the root object of the given resource
   * as its context.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param outer
   *          The outer scope of the new scope.
   * @param rsc
   *          The context resource
   * @param query
   *          The query that defines the scope's contents
   * @param originalResource
   *          The original resource
   * @param nameFunctions
   *          The name functions to apply
   * @param caseInsensitive
   *          indicates whether the new scope shall be case insensitive
   * @return The new scope
   */
  protected IScope newContainerScope(final String id, final IScope outer, final Resource rsc, final ContainerQuery query, final Resource originalResource, final Iterable<INameFunction> nameFunctions, final boolean caseInsensitive) {
    IScope result = outer;
    // We always need a context. Default to the top element of the resource.
    final EList<EObject> contents = rsc.getContents();
    if (contents.size() > 0) {
      result = newContainerScope(id, result, contents.get(0), query, originalResource, nameFunctions, caseInsensitive);
    }
    return result;
  }

  /**
   * Create a new prefixed container scope using the results of a given query as its contents.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param outer
   *          The outer scope of the new scope.
   * @param context
   *          The context
   * @param query
   *          The query that defines the scope's contents
   * @param originalResource
   *          The original resource
   * @param prefix
   *          prefix to apply for single element lookups
   * @param recursive
   *          whether the qualified name pattern used to search the index should be
   *          {@link com.avaloq.tools.ddk.xtext.naming.QualifiedNamePattern#RECURSIVE_WILDCARD_SEGMENT recursive}
   * @param nameFunctions
   *          The name functions to apply
   * @param caseInsensitive
   *          indicates whether the new scope shall be case insensitive
   * @return The new scope
   */
  protected IScope newPrefixedContainerScope(final String id, final IScope outer, final EObject context, final ContainerQuery.Builder query, final Resource originalResource, final String prefix, final boolean recursive, final Iterable<INameFunction> nameFunctions, final boolean caseInsensitive) {
    IScope result = outer;
    final List<String> domains = query.getDomains();
    for (final IContainer container : Lists.reverse(getVisibleContainers(context, originalResource))) {
      if (!domains.isEmpty()) {
        final IDomain domain = domainMapper.map(container);
        if (domain != null && !domains.contains(domain.getName())) {
          continue; // Query not applicable to this container.
        }
      }
      result = new PrefixedContainerBasedScope(id, result, container, query, nameFunctions, QualifiedNames.safeQualifiedName(prefix), recursive, caseInsensitive);
    }
    return result;
  }

  /**
   * Create a new prefix container scope using the results of a given query as its contents, using the root object of the given resource
   * as its context.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param outer
   *          The outer scope of the new scope.
   * @param rsc
   *          The context resource
   * @param query
   *          The query that defines the scope's contents
   * @param originalResource
   *          The original resource
   * @param prefix
   *          prefix to apply for single element lookups
   * @param recursive
   *          whether the qualified name pattern used to search the index should be
   *          {@link com.avaloq.tools.ddk.xtext.naming.QualifiedNamePattern#RECURSIVE_WILDCARD_SEGMENT recursive}
   * @param nameFunctions
   *          The name functions to apply
   * @param caseInsensitive
   *          indicates whether the new scope shall be case insensitive
   * @return The new scope
   */
  protected IScope newPrefixedContainerScope(final String id, final IScope outer, final Resource rsc, final ContainerQuery.Builder query, final Resource originalResource, final String prefix, final boolean recursive, final Iterable<INameFunction> nameFunctions, final boolean caseInsensitive) {
    IScope result = outer;
    // We always need a context. Default to the top element of the resource.
    final EList<EObject> contents = rsc.getContents();
    if (contents.size() > 0) {
      result = newPrefixedContainerScope(id, result, contents.get(0), query, originalResource, prefix, recursive, nameFunctions, caseInsensitive);
    }
    return result;
  }

  /**
   * Create a new container scope using the results of a given query as its contents.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param outer
   *          The outer scope of the new scope.
   * @param context
   *          The context
   * @param query
   *          The query that defines the scope's contents
   * @param originalResource
   *          The original resource
   * @param filters
   *          to apply
   * @param nameFunctions
   *          The name functions to apply
   * @param caseInsensitive
   *          indicates whether the new scope shall be case insensitive
   * @return The new scope
   */
  protected IScope newDataMatchScope(final String id, final IScope outer, final EObject context, final ContainerQuery query, final Resource originalResource, final Iterable<Predicate<IEObjectDescription>> filters, final Iterable<INameFunction> nameFunctions, final boolean caseInsensitive) {
    IScope result = outer;
    final List<String> domains = query.getDomains();
    for (final IContainer container : Lists.reverse(getVisibleContainers(context, originalResource))) {
      if (!domains.isEmpty()) {
        final IDomain domain = domainMapper.map(container);
        if (domain != null && !domains.contains(domain.getName())) {
          continue; // Query not applicable to this container.
        }
      }
      // Build a branch in the scope chain so that we can filter each container query separately.
      IScope contents = new ContainerBasedScope(id, IScope.NULLSCOPE, container, query, nameFunctions, caseInsensitive);
      contents = new DataFilteringScope(contents, filters);
      result = new BranchingScope(contents, result);
    }
    return result;
  }

  /**
   * Create a new container scope using the results of a given query as its contents, using the root object of the given resource
   * as its context.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param outer
   *          The outer scope of the new scope.
   * @param rsc
   *          The context resource
   * @param query
   *          The query that defines the scope's contents
   * @param originalResource
   *          The original resource
   * @param filters
   *          to apply
   * @param nameFunctions
   *          The name functions to apply
   * @param caseInsensitive
   *          indicates whether the new scope shall be case insensitive
   * @return The new scope
   */
  protected IScope newDataMatchScope(final String id, final IScope outer, final Resource rsc, final ContainerQuery query, final Resource originalResource, final Iterable<Predicate<IEObjectDescription>> filters, final Iterable<INameFunction> nameFunctions, final boolean caseInsensitive) {
    IScope result = outer;
    // We always need a context. Default to the top element of the resource.
    final EList<EObject> contents = rsc.getContents();
    if (contents.size() > 0) {
      result = newDataMatchScope(id, result, contents.get(0), query, originalResource, filters, nameFunctions, caseInsensitive);
    }
    return result;
  }

  /**
   * Creates a new {@link MultiScope} with the given elements and names.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param parent
   *          parent scope of new scope
   * @param elements
   *          The elements in the scope
   * @param nameFunctions
   *          name functions to apply to each element
   * @param caseInsensitive
   *          indicates whether the new scope shall be case insensitive
   * @return resulting scope
   */
  protected IScope newSimpleScope(final String id, final IScope parent, final Iterable<? extends EObject> elements, final Iterable<INameFunction> nameFunctions, final boolean caseInsensitive) {
    if (elements == null || Iterables.isEmpty(elements)) {
      return parent;
    }
    return new MultiScope(id, parent, elements, nameFunctions, caseInsensitive);
  }

  /**
   * Creates a new {@link MultiScope} with the given elements and names.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param parent
   *          parent scope of new scope
   * @param element
   *          The element in the scope
   * @param nameFunctions
   *          name functions to apply to each element
   * @param caseInsensitive
   *          indicates whether the new scope shall be case insensitive
   * @return resulting scope
   */
  protected IScope newSimpleScope(final String id, final IScope parent, final EObject element, final Iterable<INameFunction> nameFunctions, final boolean caseInsensitive) {
    if (element == null || element.eIsProxy()) {
      return parent;
    }
    return new MultiScope(id, parent, Collections.singleton(element), nameFunctions, caseInsensitive);
  }

  /**
   * Creates a new {@link DelegatingScope} with the given elements as delegates, omitting non-resolvable proxies from the
   * descriptions.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param parent
   *          parent scope of new scope
   * @param delegates
   *          delegate object descriptions
   * @param type
   *          context type of originating scope
   * @param originalResource
   *          the original Resource this scope provider belongs to
   * @return resulting scope
   */
  protected IScope newExternalDelegateScope(final String id, final IScope parent, final Iterable<? extends IEObjectDescription> delegates, final EClass type, final Resource originalResource) {
    return newExternalDelegateScope(id, parent, delegates, type, SCOPE_STRING, originalResource);
  }

  /**
   * Creates a new {@link DelegatingScope} with the given elements as delegates, omitting non-resolvable proxies from the
   * descriptions.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param parent
   *          parent scope of new scope
   * @param delegates
   *          delegate object descriptions
   * @param ref
   *          context reference of originating scope
   * @param originalResource
   *          the original Resource this scope provider belongs to
   * @return resulting scope
   */
  protected IScope newExternalDelegateScope(final String id, final IScope parent, final Iterable<? extends IEObjectDescription> delegates, final EReference ref, final Resource originalResource) {
    return newExternalDelegateScope(id, parent, delegates, ref, SCOPE_STRING, originalResource);
  }

  /**
   * Creates a new {@link DelegatingScope} with the given element as delegate.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param parent
   *          parent scope of new scope
   * @param delegate
   *          delegate object
   * @param type
   *          context type of originating scope
   * @param scopeName
   *          the scope name
   * @param originalResource
   *          the original Resource this scope provider belongs to
   * @return resulting scope
   */
  protected IScope newDelegateScope(final String id, final IScope parent, final EObject delegate, final EClass type, final String scopeName, final Resource originalResource) {
    if (delegate == null || delegate.eIsProxy()) {
      return parent;
    }
    final IContextSupplier supplier = new IContextSupplier() {
      @Override
      public Iterable<? extends EObject> get() {
        return Collections.singleton(delegate);
      }
    };
    return new DelegatingScope(id, this, parent, supplier, type, scopeName, originalResource);
  }

  /**
   * Creates a new {@link DelegatingScope} with the given element as delegate.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param parent
   *          parent scope of new scope
   * @param delegate
   *          delegate resource, must be the same as the originalResource
   * @param type
   *          context type of originating scope
   * @param scopeName
   *          the scope name
   * @param originalResource
   *          the original Resource this scope provider belongs to
   * @return resulting scope
   */
  protected IScope newDelegateScope(final String id, final IScope parent, final Resource delegate, final EClass type, final String scopeName, final Resource originalResource) {
    if (delegate != originalResource) { // NOPMD CompareObjectsWithEquals by WTH on 24.11.10 06:07
      throw new IllegalStateException(DELEGATE_SCOPE_RESOURCE_ERR_MSG);
    }
    return newDelegateScope(id, parent, originalResource.getContents().get(0), type, scopeName, originalResource);
  }

  /**
   * Creates a new {@link DelegatingScope} with the given elements as delegates.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param parent
   *          parent scope of new scope
   * @param delegates
   *          delegate objects
   * @param type
   *          context type of originating scope
   * @param scopeName
   *          the scope name
   * @param originalResource
   *          the original Resource this scope provider belongs to
   * @return resulting scope
   */
  protected IScope newDelegateScope(final String id, final IScope parent, final Iterable<? extends EObject> delegates, final EClass type, final String scopeName, final Resource originalResource) {
    if (delegates == null) {
      return parent;
    }
    final Iterable<? extends EObject> contexts = EObjectUtil.filterProxies(delegates);
    if (Iterables.isEmpty(contexts)) {
      return parent;
    }
    final IContextSupplier func = new IContextSupplier() {
      @Override
      public Iterable<? extends EObject> get() {
        return contexts;
      }
    };
    return new DelegatingScope(id, this, parent, func, type, scopeName, originalResource);
  }

  /**
   * Creates a new {@link DelegatingScope} using a given context supplier.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param parent
   *          parent scope of new scope
   * @param contexts
   *          The context supplier supplying the delegate contexts
   * @param ref
   *          The reference
   * @param scopeName
   *          the scope name
   * @param originalResource
   *          the original Resource this scope provider belongs to
   * @return resulting scope
   */
  protected IScope newDelegateScope(final String id, final IScope parent, final IContextSupplier contexts, final EReference ref, final String scopeName, final Resource originalResource) {
    return new DelegatingScope(id, this, parent, contexts, ref, scopeName, originalResource);
  }

  /**
   * Creates a new {@link DelegatingScope} using a given context supplier.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param parent
   *          parent scope of new scope
   * @param contexts
   *          The context supplier supplying the delegate contexts
   * @param type
   *          context type of originating scope
   * @param scopeName
   *          the scope name
   * @param originalResource
   *          the original Resource this scope provider belongs to
   * @return resulting scope
   */
  protected IScope newDelegateScope(final String id, final IScope parent, final IContextSupplier contexts, final EClass type, final String scopeName, final Resource originalResource) {
    return new DelegatingScope(id, this, parent, contexts, type, scopeName, originalResource);
  }

  /**
   * Context supplier based on an iterable of IEObjectDescriptions, resolving proxies as needed. Used for
   * "external delegating scopes", i.e., scopeof(find(...)).
   */
  private static class ResolvingContextSupplier implements IContextSupplier {

    private final Iterable<? extends IEObjectDescription> items;
    private final Resource originalResource;

    /**
     * Create a new resolving context supplier based on the given iterable.
     *
     * @param items
     *          The iterable to use.
     * @param originalResource
     *          The original resource context.
     */
    ResolvingContextSupplier(final Iterable<? extends IEObjectDescription> items, final Resource originalResource) {
      this.items = items;
      this.originalResource = originalResource;
    }

    /** {@inheritDoc} */
    @Override
    public Iterable<? extends EObject> get() {
      return EObjectUtil.filterProxies(Iterables.transform(items, new Function<IEObjectDescription, EObject>() {
        @Override
        public EObject apply(final IEObjectDescription from) {
          EObject result = from.getEObjectOrProxy();
          if (result.eIsProxy()) {
            result = EcoreUtil.resolve(result, originalResource);
          }
          return result;
        }
      }));
    }

  }

  /**
   * Creates a new {@link DelegatingScope} with the given elements as delegates, omitting non-resolvable proxies from the
   * descriptions.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param parent
   *          parent scope of new scope
   * @param delegates
   *          delegate object descriptions
   * @param type
   *          context type of originating scope
   * @param scopeName
   *          the scope name
   * @param originalResource
   *          the original Resource this scope provider belongs to
   * @return resulting scope
   */
  protected IScope newExternalDelegateScope(final String id, final IScope parent, final Iterable<? extends IEObjectDescription> delegates, final EClass type, final String scopeName, final Resource originalResource) {
    if (delegates == null || Iterables.isEmpty(delegates)) {
      return parent;
    }
    final IContextSupplier contexts = new ResolvingContextSupplier(delegates, originalResource);
    return newDelegateScope(id, parent, contexts, type, scopeName, originalResource);
  }

  /**
   * Creates a new {@link DelegatingScope} with the given element as delegate.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param parent
   *          parent scope of new scope
   * @param delegate
   *          delegate object
   * @param ref
   *          context reference of originating scope
   * @param scopeName
   *          the scope name
   * @param originalResource
   *          the original Resource this scope provider belongs to
   * @return resulting scope
   */
  protected IScope newDelegateScope(final String id, final IScope parent, final EObject delegate, final EReference ref, final String scopeName, final Resource originalResource) {
    if (delegate == null || delegate.eIsProxy()) {
      return parent;
    }
    final IContextSupplier supplier = new IContextSupplier() {
      @Override
      public Iterable<? extends EObject> get() {
        return Collections.singleton(delegate);
      }
    };
    return new DelegatingScope(id, this, parent, supplier, ref, scopeName, originalResource);
  }

  /**
   * Creates a new {@link DelegatingScope} with the given element as delegate.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param parent
   *          parent scope of new scope
   * @param delegate
   *          delegate resource, must be the same as the originalResource
   * @param ref
   *          context reference of originating scope
   * @param scopeName
   *          the scope name
   * @param originalResource
   *          the original Resource this scope provider belongs to
   * @return resulting scope
   */
  protected IScope newDelegateScope(final String id, final IScope parent, final Resource delegate, final EReference ref, final String scopeName, final Resource originalResource) {
    if (delegate != originalResource) { // NOPMD CompareObjectsWithEquals by WTH on 24.11.10 06:07
      throw new IllegalStateException(DELEGATE_SCOPE_RESOURCE_ERR_MSG);
    }
    return newDelegateScope(id, parent, originalResource.getContents().get(0), ref, scopeName, originalResource);
  }

  /**
   * Creates a new {@link DelegatingScope} with the given elements as
   * delegates.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param parent
   *          parent scope of new scope
   * @param delegates
   *          delegate objects
   * @param ref
   *          context reference of originating scope
   * @param scopeName
   *          the scope name
   * @param originalResource
   *          the original Resource this scope provider belongs to
   * @return resulting scope
   */
  protected IScope newDelegateScope(final String id, final IScope parent, final Iterable<? extends EObject> delegates, final EReference ref, final String scopeName, final Resource originalResource) {
    if (delegates == null) {
      return parent;
    }
    final Iterable<? extends EObject> contexts = EObjectUtil.filterProxies(delegates);
    if (Iterables.isEmpty(contexts)) {
      return parent;
    }
    final IContextSupplier func = new IContextSupplier() {
      @Override
      public Iterable<? extends EObject> get() {
        return contexts;
      }
    };
    return new DelegatingScope(id, this, parent, func, ref, scopeName, originalResource);
  }

  /**
   * Creates a new {@link DelegatingScope} with the given elements as delegates, omitting non-resolvable proxies from the
   * descriptions.
   *
   * @param id
   *          Human-readable name of the scope, typically used to identify where the scope was created. Useful for debugging.
   * @param parent
   *          parent scope of new scope
   * @param delegates
   *          delegate object descriptions
   * @param ref
   *          context reference of originating scope
   * @param scopeName
   *          the scope name
   * @param originalResource
   *          the original Resource this scope provider belongs to
   * @return resulting scope
   */
  protected IScope newExternalDelegateScope(final String id, final IScope parent, final Iterable<? extends IEObjectDescription> delegates, final EReference ref, final String scopeName, final Resource originalResource) {
    if (delegates == null || Iterables.isEmpty(delegates)) {
      return parent;
    }
    final IContextSupplier contexts = new ResolvingContextSupplier(delegates, originalResource);
    return newDelegateScope(id, parent, contexts, ref, scopeName, originalResource);
  }

  /**
   * Return a new query.
   *
   * @param type
   *          the type of objects to look for
   * @return The new query
   */
  protected ContainerQuery.Builder newQuery(final EClass type) {
    return ContainerQuery.newBuilder(domainMapper, type);
  }

  /**
   * Determines name functions to be used for scopes of a given context reference. Implementation delegates to {@link #getNameFunctions(EClass)}.
   *
   * @param ref
   *          context reference
   * @return name functions
   */
  public Iterable<INameFunction> getNameFunctions(final EReference ref) {
    final EClass type = ref.getEReferenceType();
    return getNameFunctions(type);
  }

  /**
   * Determines name functions to be used for scopes of a given context type.
   *
   * @param type
   *          context type
   * @return name functions
   */
  public Iterable<INameFunction> getNameFunctions(final EClass type) {
    return nameProvider.getNameFunctions(type);
  }

  /**
   * Returns a qualified name for the given object.
   *
   * @param o
   *          string to convert
   * @return converted string or null
   */
  protected QualifiedName toQualifiedName(final Object o) {
    return o != null ? nameConverter.toQualifiedName(o.toString()) : null;
  }

  /**
   * Returns a qualified name for the given object.
   *
   * @param s
   *          string to convert
   * @return converted string or null
   */
  protected QualifiedName toQualifiedName(final String s) {
    return s != null ? nameConverter.toQualifiedName(s) : null;
  }
}
