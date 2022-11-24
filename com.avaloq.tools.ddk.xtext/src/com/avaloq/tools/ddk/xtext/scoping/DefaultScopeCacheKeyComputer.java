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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.util.Tuples;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.google.inject.name.Named;


/**
 * Default implementation of IScopeCacheKeyComputer interface.
 */
public class DefaultScopeCacheKeyComputer implements IScopeCacheKeyComputer {

  /**
   * Cache key object for the caching of {@link org.eclipse.xtext.scoping.IScope scopes}.
   */
  protected static final class CacheKey {
    private final Object context;
    private final ENamedElement referenceOrType;
    private final String scopeName;

    private CacheKey(final Object context, final ENamedElement referenceOrType, final String scopeName) {
      this.context = context;
      this.referenceOrType = referenceOrType;
      this.scopeName = scopeName;
    }

    /**
     * Factory method for reference-specific scopes.
     *
     * @param context
     *          context object, must not be {@code null}
     * @param reference
     *          cross-reference, must not be {@code null}
     * @param scopeName
     *          scope name, must not be {@code null}
     * @return new {@link CacheKey}, never {@code null}
     */
    public static CacheKey of(final Object context, final EReference reference, final String scopeName) { // NOPMD - Short name 'of'
      return new CacheKey(context, reference, scopeName);
    }

    /**
     * Factory method for type-specific scopes.
     *
     * @param context
     *          context object, must not be {@code null}
     * @param type
     *          scope element type, must not be {@code null}
     * @param scopeName
     *          scope name, must not be {@code null}
     * @return new {@link CacheKey}, never {@code null}
     */
    public static CacheKey of(final Object context, final EClass type, final String scopeName) { // NOPMD - Short name 'of'
      return new CacheKey(context, type, scopeName);
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(referenceOrType, scopeName, context);
    }

    @Override
    public boolean equals(final Object obj) {
      if (!(obj instanceof CacheKey)) {
        return false;
      }
      CacheKey other = (CacheKey) obj;
      return referenceOrType.equals(other.referenceOrType) && scopeName.equals(other.scopeName) && context.equals(other.context);
    }
  }

  @Inject
  private IDomain.Mapper domainMapper;

  @Inject
  @Named(Constants.LANGUAGE_NAME)
  private String languageName;

  @Override
  public Object getGlobalCacheKey(final EObject context, final EReference reference, final String scopeName) {
    return CacheKey.of(getGlobalCacheContext(context), reference, scopeName);
  }

  @Override
  public Object getGlobalCacheKey(final EObject context, final EClass type, final String scopeName) {
    return CacheKey.of(getGlobalCacheContext(context), type, scopeName);
  }

  /**
   * Returns the context object forming part of the global cache key.
   *
   * @param context
   *          context object contained in a resource, must not be {@code null}
   * @return global cache key context object, must not be {@code null} and should not reference any EObjects
   */
  protected Object getGlobalCacheContext(final EObject context) {
    return Tuples.pair(getGlobalDomain(context), getLanguageName());
  }

  protected String getLanguageName() {
    return languageName;
  }

  /**
   * Returns the domain name of the context.
   *
   * @param context
   *          the context, must not be {@code null}
   * @return the domain, never {@code null}
   */
  protected IDomain getGlobalDomain(final EObject context) {
    return domainMapper.map(context.eResource().getURI());
  }

  @Override
  public Object getCacheKey(final EObject context, final EReference reference, final String scopeName) {
    return CacheKey.of(context, reference, scopeName);
  }

  @Override
  public Object getCacheKey(final EObject context, final EClass type, final String scopeName) {
    return CacheKey.of(context, type, scopeName);
  }

}
