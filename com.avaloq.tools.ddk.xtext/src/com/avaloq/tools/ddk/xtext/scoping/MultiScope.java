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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;


/**
 * Scope working on a given set of EObjects, applying multiple name functions.
 */
public class MultiScope extends AbstractRecursiveScope {

  private final Iterable<? extends EObject> elements;
  private final Iterable<INameFunction> names;

  /**
   * Instantiates a case-sensitive new multi scope with id, no parent scope.
   *
   * @param id
   *          the id
   * @param elements
   *          the elements
   * @param names
   *          the names
   */
  public MultiScope(final String id, final Iterable<? extends EObject> elements, final Iterable<INameFunction> names) {
    this(id, elements, names, false);
  }

  /**
   * Instantiates a new multi scope without parent scope, but with given id and case-sensitivity.
   *
   * @param id
   *          the id
   * @param elements
   *          the elements
   * @param names
   *          the names
   * @param caseInsensitive
   *          the scope's case sensitivity
   */
  public MultiScope(final String id, final Iterable<? extends EObject> elements, final Iterable<INameFunction> names, final boolean caseInsensitive) {
    this(id, IScope.NULLSCOPE, elements, names, caseInsensitive);
  }

  /**
   * Instantiates a new case-sensitive multi scope with given id and parent scope.
   *
   * @param id
   *          the id
   * @param parent
   *          the parent
   * @param elements
   *          the elements
   * @param names
   *          the names
   */
  public MultiScope(final String id, final IScope parent, final Iterable<? extends EObject> elements, final Iterable<INameFunction> names) {
    this(id, parent, elements, names, false);
  }

  /**
   * Instantiates a new multi scope with given id, parent scope, and case sensitivity.
   *
   * @param id
   *          the id
   * @param parent
   *          the parent
   * @param elements
   *          the elements
   * @param names
   *          the names
   * @param caseInsensitive
   *          the scope's case sensitivity
   */
  public MultiScope(final String id, final IScope parent, final Iterable<? extends EObject> elements, final Iterable<INameFunction> names, final boolean caseInsensitive) {
    super(id, parent, caseInsensitive);
    this.elements = elements;
    this.names = names;
  }

  /**
   * Get the name functions used by this scope.
   *
   * @return The name functions.
   */
  public Iterable<INameFunction> getNames() {
    return names;
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<IEObjectDescription> getAllLocalElements() {
    return EObjectDescriptions.all(elements, getNames());
  }

}
