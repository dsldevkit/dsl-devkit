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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

import com.avaloq.tools.ddk.xtext.caching.CacheManager;
import com.avaloq.tools.ddk.xtext.caching.MapCache;
import com.avaloq.tools.ddk.xtext.naming.QualifiedNamePattern;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;


/**
 * A scope whose contents are given by a ContainerQuery on some container.
 */
public class ContainerBasedScope extends AbstractRecursiveScope {

  /** The container. */
  private final IContainer container;

  /** The query. */
  private final ContainerQuery criteria;

  private final Collection<INameFunction> nameFunctions;

  private final MapCache<QualifiedName, IEObjectDescription> contentByNameCache = CacheManager.getInstance().createMapCache("ContainerBasedScope#contentByNameCache"); //$NON-NLS-1$

  /**
   * Create a new scope with id, parent, container, query, and case-sensitivity.
   *
   * @param id
   *          The id of the scope.
   * @param parent
   *          The parent scope.
   * @param container
   *          The container to run the query in.
   * @param query
   *          The query.
   * @param nameFunctions
   *          The name functions to apply
   * @param caseInsensitive
   *          The scope's case-sensitivity.
   */
  public ContainerBasedScope(final String id, final IScope parent, final IContainer container, final ContainerQuery query, final Iterable<INameFunction> nameFunctions, final boolean caseInsensitive) {
    super(id, parent, caseInsensitive);
    this.container = container;
    this.criteria = query;
    this.nameFunctions = nameFunctions instanceof Collection ? (Collection<INameFunction>) nameFunctions : ImmutableList.copyOf(nameFunctions);
  }

  // Using QualifiedName#toLowerCase() not String#toLowerCase()
  @SuppressWarnings("PMD.UseLocaleWithCaseConversions")
  @Override
  public synchronized IEObjectDescription getSingleElement(final QualifiedName name) {
    if (nameFunctions != null && nameFunctions.contains(NameFunctions.exportNameFunction())) {
      final QualifiedName lookupName = isIgnoreCase() ? name.toLowerCase() : name;
      final IEObjectDescription result = contentByNameCache.get(lookupName);
      if (result != null) {
        if (result != NULL_DESCRIPTION) {
          return result;
        }
        // Otherwise check for aliasing and if yes revert to normal behavior or delegate or parent otherwise
      } else {
        QualifiedName namePattern = criteria.getNamePattern();
        if (namePattern != null && isIgnoreCase()) {
          namePattern = namePattern.toLowerCase();
        }
        if (namePattern == null || namePattern.equals(lookupName)
            || (namePattern instanceof QualifiedNamePattern && ((QualifiedNamePattern) namePattern).matches(lookupName))) {
          final ContainerQuery copy = ((ContainerQuery.Builder) criteria).copy().name(lookupName).cache(false);
          final Iterable<IEObjectDescription> res = copy.execute(container);
          for (IEObjectDescription desc : res) {
            contentByNameCache.put(lookupName, desc);
            return desc;
          }
          contentByNameCache.put(lookupName, NULL_DESCRIPTION);
        }
      }
      // in case of aliasing revert to normal behavior
      return nameFunctions.size() > 1 ? super.getSingleElement(name) : getParent().getSingleElement(name);
    } else {
      return super.getSingleElement(name);
    }
  }

  // Using QualifiedName#toLowerCase() not String#toLowerCase()
  @SuppressWarnings("PMD.UseLocaleWithCaseConversions")
  @Override
  protected Iterable<IEObjectDescription> getLocalElementsByName(final QualifiedName name) {
    if (nameFunctions != null && nameFunctions.size() == 1 && nameFunctions.contains(NameFunctions.exportNameFunction())) {
      final QualifiedName lookupName = isIgnoreCase() ? name.toLowerCase() : name;
      QualifiedName namePattern = criteria.getNamePattern();
      if (namePattern != null && isIgnoreCase()) {
        namePattern = namePattern.toLowerCase();
      }
      if (namePattern == null || namePattern.equals(lookupName)
          || (namePattern instanceof QualifiedNamePattern && ((QualifiedNamePattern) namePattern).matches(lookupName))) {
        final ContainerQuery copy = ((ContainerQuery.Builder) criteria).copy().name(lookupName).cache(false);
        return copy.execute(container);
      }
      return Collections.<IEObjectDescription> emptyList();
    } else {
      return super.getLocalElementsByName(name);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected Iterable<IEObjectDescription> getAllLocalElements() {
    Iterable<IEObjectDescription> result = criteria.execute(container);
    if (nameFunctions != null && !Iterables.isEmpty(nameFunctions)) {
      result = EObjectDescriptions.map(result, nameFunctions);
    }
    return result;
  }

  @SuppressWarnings("nls")
  @Override
  public String toString() {
    final StringBuilder result = new StringBuilder(getClass().getName());
    result.append('@');
    result.append(Integer.toHexString(hashCode()));

    result.append(" (id: ");
    result.append(getId());

    result.append(", query: ");
    result.append(criteria);

    result.append(", container: ");
    result.append(container);

    result.append(')');

    final IScope parent = getParent();
    if (parent != IScope.NULLSCOPE) {
      result.append("\n  >> ");
      result.append(parent.toString().replaceAll("\\\n", "\n  "));
    }
    return result.toString();
  }
}
