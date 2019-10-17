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
import java.util.Map;

import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

import com.avaloq.tools.ddk.caching.CacheManager;
import com.avaloq.tools.ddk.xtext.naming.QualifiedNamePattern;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;


/**
 * Works like a ContainerBasedScope but will apply a given prefix when {@link #getSingleElement(QualifiedName) finding a single element} in the underlying
 * ContainerQuery result.
 */
public class PrefixedContainerBasedScope extends ContainerBasedScope {

  /** The container. */
  private final IContainer container;

  /** The query. */
  private final ContainerQuery criteria;

  private final Collection<INameFunction> nameFunctions;

  private final Map<QualifiedName, IEObjectDescription> contentByNameCache = CacheManager.getInstance().createMapCache("PrefixedContainerBasedScope#contentByNameCache"); //$NON-NLS-1$

  private final QualifiedName prefix;

  /**
   * Create a new scope with id, parent, container, query, prefix, and case-sensitivity.
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
   *          The name functions to apply.
   * @param prefix
   *          The prefix to apply for single element lookups.
   * @param recursive
   *          whether the qualified name pattern used to search the index should be {@link QualifiedNamePattern#RECURSIVE_WILDCARD_SEGMENT recursive}
   * @param caseInsensitive
   *          The scope's case-sensitivity.
   */
  // Using QualifiedName#toLowerCase() not String#toLowerCase()
  @SuppressWarnings("PMD.UseLocaleWithCaseConversions")
  public PrefixedContainerBasedScope(final String id, final IScope parent, final IContainer container, final ContainerQuery.Builder query, final Iterable<INameFunction> nameFunctions, final QualifiedName prefix, final boolean recursive, final boolean caseInsensitive) {
    super(id, parent, container, query.name(QualifiedNamePattern.create(prefix.append(recursive ? QualifiedNamePattern.RECURSIVE_WILDCARD_SEGMENT
        : "*"))), nameFunctions, caseInsensitive); //$NON-NLS-1$
    this.container = container;
    this.criteria = query;
    this.nameFunctions = nameFunctions instanceof Collection ? (Collection<INameFunction>) nameFunctions : ImmutableList.copyOf(nameFunctions);
    this.prefix = caseInsensitive ? prefix.toLowerCase() : prefix;
  }

  // Using QualifiedName#toLowerCase() not String#toLowerCase()
  @SuppressWarnings("PMD.UseLocaleWithCaseConversions")
  @Override
  public synchronized IEObjectDescription getSingleElement(final QualifiedName name) {
    final boolean ignoreCase = isIgnoreCase();
    final QualifiedName lookupName = ignoreCase ? name.toLowerCase() : name;
    final IEObjectDescription result = contentByNameCache.get(lookupName);
    if (result != null && result != NULL_DESCRIPTION) {
      return result;
    } else if (result == null) {
      final ContainerQuery copy = ((ContainerQuery.Builder) criteria).copy().name(prefix.append(lookupName)).ignoreCase(ignoreCase);
      final Iterable<IEObjectDescription> res = copy.execute(container);
      IEObjectDescription desc = Iterables.getFirst(res, null);
      if (desc != null) {
        IEObjectDescription aliased = new AliasingEObjectDescription(name, desc);
        contentByNameCache.put(lookupName, aliased);
        return aliased;
      }
      contentByNameCache.put(lookupName, NULL_DESCRIPTION);
    }

    // in case of aliasing revert to normal ContainerBasedScope behavior (using name pattern)
    if (nameFunctions.size() > 1) {
      return super.getSingleElement(name);
    }
    return getParent().getSingleElement(name);
  }

  // Using QualifiedName#toLowerCase() not String#toLowerCase()
  @SuppressWarnings("PMD.UseLocaleWithCaseConversions")
  @Override
  protected Iterable<IEObjectDescription> getLocalElementsByName(final QualifiedName name) {
    if (nameFunctions.size() == 1) {
      final boolean ignoreCase = isIgnoreCase();
      final QualifiedName lookupName = ignoreCase ? name.toLowerCase() : name;
      final ContainerQuery copy = ((ContainerQuery.Builder) criteria).copy().name(prefix.append(lookupName)).ignoreCase(ignoreCase);
      return copy.execute(container);
    }
    return super.getLocalElementsByName(name);
  }

  @Override
  protected Iterable<IEObjectDescription> getAllLocalElements() {
    final int prefixLength = prefix.getSegmentCount();
    boolean ignoreCase = isIgnoreCase();
    return Iterables.transform(super.getAllLocalElements(), new Function<IEObjectDescription, IEObjectDescription>() {
      @Override
      public IEObjectDescription apply(final IEObjectDescription input) {
        QualifiedName prefixedName = input.getName();
        if (ignoreCase) {
          return prefixedName.startsWithIgnoreCase(prefix) ? new AliasingEObjectDescription(prefixedName.skipFirst(prefixLength), input) : input;
        }
        return prefixedName.startsWith(prefix) ? new AliasingEObjectDescription(prefixedName.skipFirst(prefixLength), input) : input;
      }
    });
  }

  @SuppressWarnings("nls")
  @Override
  public String toString() {
    final StringBuilder result = new StringBuilder(getClass().getName());
    result.append('@');
    result.append(Integer.toHexString(hashCode()));

    result.append(" (id: ");
    result.append(getId());

    result.append(", prefix: ");
    result.append(prefix);

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
