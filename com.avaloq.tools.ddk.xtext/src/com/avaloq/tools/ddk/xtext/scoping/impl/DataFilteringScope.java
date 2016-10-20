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
package com.avaloq.tools.ddk.xtext.scoping.impl;

import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.FilteringScope;

import com.google.common.base.Predicate;


/**
 * A scope that can filter by user data. Note that the filtering applies to the whole scope chain of the delegate.
 */
public class DataFilteringScope extends FilteringScope {

  private final IScope delegate;

  /**
   * Create a scope that filters the results of some other scope based on the conjunction of the given filters.
   *
   * @param delegate
   *          scope to filter. Note that this scope may have outer scopes, so this DataFilteringScope filters the whole scope chain.
   * @param filters
   *          defining the condition for filtering
   */
  public DataFilteringScope(final IScope delegate, final Iterable<Predicate<IEObjectDescription>> filters) {
    // We set this up with a filter ignoring the name. In many scope operations we don't know what name we're looking for
    // (e.g., in content assist). In the case where we *do* know that (typically when linking), we use a second predicate
    // in getElements() below that *does* pass on that name. Filters that depend on the name therefore always must be able
    // to deal with null names.
    super(delegate, new Predicate<IEObjectDescription>() {
      @Override
      public boolean apply(final IEObjectDescription desc) {
        for (Predicate<IEObjectDescription> f : filters) {
          if (!f.apply(desc)) {
            return false;
          }
        }
        return true;
      }
    });
    this.delegate = delegate;
  }

  protected IScope getDelegate() {
    return delegate;
  }

}
