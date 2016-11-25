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

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.util.Arrays;

import com.avaloq.tools.ddk.xtext.naming.QualifiedNameConverter;
import com.avaloq.tools.ddk.xtext.naming.QualifiedNamePattern;
import com.avaloq.tools.ddk.xtext.resource.DetachableEObjectDescription;
import com.avaloq.tools.ddk.xtext.util.EObjectUtil;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


/**
 * Index query run, to be executed on a container or a set of containers. With caching.
 */
public class ContainerQuery {

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(ContainerQuery.class);

  /** Our "glob"-expression have only one wildcard character, signifying zero or more occurrences of any character. */
  public static final char WILDCARD = '*';

  /**
   * Any query is for a type, and may optionally have a uriPattern (matched against the last segment of a resource's URI), a
   * namePattern (which matches against the index names), and user data criteria.
   */
  private EClass type;
  /** The name pattern. */
  private QualifiedName namePattern;
  /** User data to match. */
  private Map<String, String> userData;
  /** The domains in which this query applies. */
  private List<String> domains;

  private boolean doIgnoreCase = true;

  private final IDomain.Mapper domainMapper;

  public ContainerQuery(final IDomain.Mapper domainMapper) {
    this.domainMapper = domainMapper;
  }

  /**
   * Gets the domain mapper.
   *
   * @return the domain mapper
   */
  public IDomain.Mapper getDomainMapper() {
    return domainMapper;
  }

  /**
   * Gets the type.
   *
   * @return the type
   */
  public EClass getType() {
    return type;
  }

  /**
   * Sets the type.
   *
   * @param type
   *          the new type
   */
  public void setType(final Object type) {
    if (type instanceof EReference) {
      this.type = ((EReference) type).getEReferenceType();
    } else if (type instanceof EClass) {
      this.type = (EClass) type;
    } else {
      throw new IllegalArgumentException("Cannot convert type to EClass: " + type); //$NON-NLS-1$
    }
  }

  /**
   * Sets the name pattern.
   *
   * @param pattern
   *          the name pattern
   */
  public void setNamePattern(final QualifiedName pattern) {
    this.namePattern = pattern;
  }

  /**
   * Set the domains this query shall apply to.
   *
   * @param domainHandles
   *          The names of the domains.
   */
  public void setDomains(final String... domainHandles) {
    if (domainHandles.length > 0) {
      domains = Lists.newArrayList(domainHandles);
    }
  }

  /**
   * Gets the domains.
   *
   * @return The list of domains this query shall apply to. An empty list signifies that the query applies to all domains.
   */
  public List<String> getDomains() {
    return domains == null ? ImmutableList.<String> of() : domains;
  }

  /**
   * Sets the domains for this query.
   *
   * @param domains
   *          domains to use
   */
  public void setDomains(final List<String> domains) {
    this.domains = domains;
  }

  /**
   * Sets whether the query should be case insensitive.
   *
   * @param ignoreCase
   *          true if case sensitive lookup is desired
   */
  public void setDoIgnoreCase(final boolean ignoreCase) {
    this.doIgnoreCase = ignoreCase;
  }

  /**
   * Gets the name pattern.
   *
   * @return the name pattern
   */
  public QualifiedName getNamePattern() {
    return namePattern;
  }

  public void setUserData(final Map<String, String> userData) {
    this.userData = userData;
  }

  /**
   * Gets the user data match conditions.
   *
   * @return the user data
   */
  public Map<String, String> getUserData() {
    return userData;
  }

  /**
   * Execute the query on containers visible from a certain object's resource. The results will grouped by
   * container and in the order of
   * {@link IContainer.Manager#getVisibleContainers(org.eclipse.xtext.resource.IResourceDescription, org.eclipse.xtext.resource.IResourceDescriptions)}. The
   * result does <em>not</em> apply
   * any name shadowing.
   *
   * @param context
   *          An object in the context resource.
   * @return The query results
   */
  public Iterable<IEObjectDescription> execute(final EObject context) {
    return execute(context, context.eResource());
  }

  /**
   * Execute the query on containers visible from a certain resource. The results will grouped by container and in the order of
   * {@link IContainer.Manager#getVisibleContainers(org.eclipse.xtext.resource.IResourceDescription, org.eclipse.xtext.resource.IResourceDescriptions)}. The
   * result does <em>not</em> apply any name shadowing.
   *
   * @param context
   *          The context resource.
   * @param originalResource
   *          The original resource.
   * @return The query results
   */
  public Iterable<IEObjectDescription> execute(final Resource context, final Resource originalResource) {
    return execute(context.getContents().get(0), originalResource);
  }

  /**
   * Execute the query on containers visible from an object in a certain resource, but cache the results on originalResource. The results will grouped by
   * container and in the order of
   * {@link IContainer.Manager#getVisibleContainers(org.eclipse.xtext.resource.IResourceDescription, org.eclipse.xtext.resource.IResourceDescriptions)}. The
   * result does <em>not</em> apply
   * any name shadowing.
   *
   * @param context
   *          An object in the context resource.
   * @param originalResource
   *          The original resource.
   * @return The query results
   */
  @SuppressWarnings("nls")
  public Iterable<IEObjectDescription> execute(final EObject context, final Resource originalResource) {
    if (!(originalResource instanceof LazyLinkingResource)) {
      throw new IllegalStateException("Resource is not a LazyLinkingResource.");
    }
    final IScopeProvider scopeProvider = EObjectUtil.getScopeProviderByResource((LazyLinkingResource) originalResource);
    if (!(scopeProvider instanceof AbstractPolymorphicScopeProvider)) {
      throw new IllegalStateException("Scope provider is not an AbstractPolymorphicScopeProvider scope provider.");
    }
    return execute(((AbstractPolymorphicScopeProvider) scopeProvider).getVisibleContainers(originalResource.getContents().get(0), originalResource));
  }

  /**
   * Execute the query on a list of containers, in the order given.
   *
   * @param containers
   *          The containers.
   * @return The query result.
   */
  public Iterable<IEObjectDescription> execute(final Iterable<IContainer> containers) {
    if (Iterables.size(containers) == 1) {
      return execute(containers.iterator().next());
    }

    return Iterables.concat(Iterables.transform(containers, new Function<IContainer, Iterable<IEObjectDescription>>() {
      @Override
      public Iterable<IEObjectDescription> apply(final IContainer container) {
        return execute(container);
      }
    }));
  }

  /**
   * Run a query on a single container.
   *
   * @param container
   *          The container
   * @return The query result.
   */
  public Iterable<IEObjectDescription> execute(final IContainer container) { // NOPMD NPathComplexity by WTH on 24.11.10 06:07
    if (domains != null && !domains.isEmpty() && domainMapper != null) {
      IDomain domain = domainMapper.map(container);
      if (domain != null && !domains.contains(domain.getName())) {
        // Query not applicable to this container.
        return ImmutableList.of();
      }
    }

    // Warning: we assume that our Containers and ResourceDescriptions from the index can handle name patterns.
    Iterable<IEObjectDescription> result = namePattern != null ? container.getExportedObjects(getType(), namePattern, doIgnoreCase)
        : container.getExportedObjectsByType(getType());

    if (getUserData() != null && !getUserData().isEmpty()) {
      final Map<String, String> userDataEquals = getUserData();
      final Predicate<IEObjectDescription> userDataPredicate = new Predicate<IEObjectDescription>() {
        @Override
        public boolean apply(final IEObjectDescription input) {
          for (final Entry<String, String> entry : userDataEquals.entrySet()) {
            if (!entry.getValue().equals(input.getUserData(entry.getKey()))) {
              return false;
            }
          }
          return true;
        }
      };
      result = Iterables.filter(result, userDataPredicate);
    }

    result = Iterables.transform(result, new Function<IEObjectDescription, IEObjectDescription>() {
      @Override
      public IEObjectDescription apply(final IEObjectDescription from) {
        String[] keys = from.getUserDataKeys();
        if (keys.length == 0 || !Arrays.contains(keys, DetachableEObjectDescription.ALLOW_LOOKUP)) {
          LOGGER.error("Found object description '" + from.getQualifiedName() + "' at " + from.getEObjectURI() + ", but lookup is not allowed!"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return keys.length == 0 ? from : new ProxyFactoryEObjectDescription(from);
      }
    });

    return result;
  }

  @SuppressWarnings("nls")
  @Override
  public String toString() {
    final StringBuilder result = new StringBuilder(getClass().getName());
    result.append('@');
    result.append(Integer.toHexString(hashCode()));

    result.append(" (type: ");
    result.append(getType().getEPackage().getName() + "::" + getType().getName());

    if (getNamePattern() != null) {
      result.append(", name: ");
      result.append(getNamePattern());
    }

    if (getUserData() != null && !getUserData().isEmpty()) {
      result.append(", data: ");
      result.append(getUserData());
    }

    if (getDomains() != null && !getDomains().isEmpty()) {
      result.append(", domains: ");
      result.append(getDomains());
    }

    result.append(')');

    return result.toString();
  }

  /**
   * Static wrapper for new Builder().
   *
   * @param domainMapper
   *          the domain mapper to use
   * @param type
   *          The type of objects to query.
   * @return A new Builder.
   */
  public static ContainerQuery.Builder newBuilder(final IDomain.Mapper domainMapper, final EClass type) {
    return new ContainerQuery.Builder(domainMapper, type);
  }

  /**
   * Builder for building queries.
   */
  public static class Builder extends ContainerQuery {

    private final IQualifiedNameConverter nameConverter = new QualifiedNameConverter();

    /**
     * Creator.
     *
     * @param type
     *          {@link ContainerQuery#getType() Type} of objects to query.
     */
    public Builder(final IDomain.Mapper domainMapper, final EClass type) {
      super(domainMapper);
      setType(type);
    }

    /**
     * Returns a copy of this builder.
     *
     * @return copy
     */
    public Builder copy() {
      Builder result = new Builder(getDomainMapper(), getType());
      result.setType(getType());
      result.setNamePattern(getNamePattern());
      if (getUserData() != null) {
        result.setUserData(Maps.newHashMap(getUserData()));
      }
      result.setDomains(Lists.newArrayList(getDomains()));
      return result;
    }

    /**
     * Set the URI pattern.
     *
     * @param uriPattern
     *          {@link ContainerQuery#getURIPattern() Resource pattern} (glob pattern) for queried objects. The URI pattern is
     *          matched against the last segment of a resource's URI.
     * @return The Builder itself.
     * @deprecated URI pattern matching of resources is no longer supported
     */
    @Deprecated
    public Builder resource(final String uriPattern) {
      LOGGER.error("URI pattern matching using ContainerQuery.Builder#resource() is no longer supported"); //$NON-NLS-1$
      return this;
    }

    /**
     * Set the name pattern.
     *
     * @param pattern
     *          {@link ContainerQuery#getNamePattern() Name pattern} (glob pattern) for queried objects.
     * @return The Builder itself.
     */
    public Builder name(final String pattern) {
      if (pattern != null && pattern.length() > 0) {
        int idx = pattern.indexOf(ContainerQuery.WILDCARD);
        if (idx < 0) {
          setNamePattern(nameConverter.toQualifiedName(pattern));
        } else if (idx == pattern.length() - 1 || pattern.endsWith(QualifiedNamePattern.RECURSIVE_WILDCARD_SEGMENT)) {
          setNamePattern(QualifiedNamePattern.create(nameConverter.toQualifiedName(pattern)));
          // setNamePattern(new QualifiedNamePattern(nameConverter.toQualifiedName(pattern.substring(0, idx))));
        } else {
          throw new IllegalArgumentException("Query name pattern invalid: wildcard not at end"); //$NON-NLS-1$
        }
      }
      return this;
    }

    /**
     * Set the name pattern.
     *
     * @param name
     *          {@link ContainerQuery#getNamePattern() Name pattern} (glob pattern) for queried objects.
     * @return The Builder itself.
     */
    public Builder name(final QualifiedName name) {
      if (name != null && !name.isEmpty()) {
        setNamePattern(name);
      }
      return this;
    }

    /**
     * Used to query a specific {@link ContainerQuery#getUserData() user data field} of the object.
     *
     * @param field
     *          The user data key.
     * @param value
     *          The user data value (literal string).
     * @return The Builder itself.
     */
    public Builder data(final String field, final String value) {
      Map<String, String> data = getUserData();
      if (data == null) {
        data = Maps.newHashMapWithExpectedSize(2);
        setUserData(data);
      }
      data.put(field, value);
      return this;
    }

    /**
     * Set the domains this query shall apply to.
     *
     * @param domainHandles
     *          The domain names
     * @return The Builder itself.
     */
    public Builder domains(final String... domainHandles) {
      this.setDomains(domainHandles);
      return this;
    }

    /**
     * Specifies whether the result of this query is to be cached or not. The default is to cache.
     *
     * @param doCache
     *          false if no caching is desired
     * @return the builder itself
     * @deprecated Caching of {@link ContainerQuery} is no longer supported
     */
    @Deprecated
    public Builder cache(final boolean doCache) {
      return this;
    }

    /**
     * Specifies whether the query must be case sensitive. The default is case insesitive.
     *
     * @param doIgnoreCase
     *          true if the lookup must be case insensitive, false otherwise
     * @return the builder object
     */
    public Builder ignoreCase(final boolean doIgnoreCase) {
      setDoIgnoreCase(doIgnoreCase);
      return this;
    }
  }

}
