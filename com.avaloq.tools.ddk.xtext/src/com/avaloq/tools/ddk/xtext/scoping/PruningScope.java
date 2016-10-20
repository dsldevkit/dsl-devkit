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

import java.util.Collections;

import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

import com.google.common.base.Predicate;


/**
 * A scope that decides based on a user-supplied predicate whether parent scopes should be searched.
 */
public class PruningScope extends AbstractRecursiveScope {

  private final Predicate<QualifiedName> predicate;

  /**
   * Instantiates a new pruning scope.
   *
   * @param id
   *          the id
   * @param parent
   *          the parent scope
   * @param predicate
   *          the predicate
   */
  public PruningScope(final String id, final IScope parent, final Predicate<QualifiedName> predicate) {
    super(id, parent);
    this.predicate = predicate;
  }

  /** {@inheritDoc} */
  @Override
  public IEObjectDescription getSingleElement(final QualifiedName name) {
    if (name == null) {
      throw new IllegalArgumentException("null name in getSingleElement()"); //$NON-NLS-1$
    }
    if (predicate.apply(name)) {
      return null;
    }
    return getParent().getSingleElement(name);
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<IEObjectDescription> getAllLocalElements() {
    return Collections.emptyList();
  }

}
