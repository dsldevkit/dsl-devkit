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

import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;


/**
 * Simple scope containing a given collection of IEObjectDescriptions.
 */
public class BasicScope extends AbstractRecursiveScope {

  /** This scope's contents. */
  private final Iterable<IEObjectDescription> elements;

  /**
   * Create a new scope with a given parent scope and given IEObjectDescriptions. The resulting scope is case-sensitive.
   *
   * @param id
   *          Human-readable identifier of the scope; useful for debugging.
   * @param parent
   *          The parent scope.
   * @param elements
   *          This scope's contents.
   */
  public BasicScope(final String id, final IScope parent, final Iterable<IEObjectDescription> elements) {
    this(id, parent, elements, false);
  }

  /**
   * Create a new scope with a given parent scope, given case-sensitivity, and given IEObjectDescriptions.
   *
   * @param id
   *          Human-readable identifier of the scope; useful for debugging.
   * @param parent
   *          The parent scope.
   * @param elements
   *          This scope's contents.
   * @param caseInsensitive
   *          the scope's case-sensitivity
   */
  public BasicScope(final String id, final IScope parent, final Iterable<IEObjectDescription> elements, final boolean caseInsensitive) {
    super(id, parent, caseInsensitive);
    this.elements = elements;
  }

  
  @Override
  protected Iterable<IEObjectDescription> getAllLocalElements() {
    return elements;
  }

}
