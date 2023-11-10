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
package com.avaloq.tools.ddk.xtext.scoping.impl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

import com.google.common.collect.Iterables;


/**
 * A scope that uses some other scope for its local contents, useful for implementing branches in scope hierarchies.
 */
public class BranchingScope implements IScope {

  private final IScope contents;
  private final IScope parent;

  public BranchingScope(final IScope contents, final IScope parent) {
    this.contents = contents;
    this.parent = parent;
  }

  @Override
  public Iterable<IEObjectDescription> getAllElements() {
    return Iterables.concat(contents.getAllElements(), parent.getAllElements());
  }

  
  @Override
  public IEObjectDescription getSingleElement(final QualifiedName name) {
    IEObjectDescription result = contents.getSingleElement(name);
    if (result == null) {
      result = parent.getSingleElement(name);
    }
    return result;
  }

  
  @Override
  public IEObjectDescription getSingleElement(final EObject object) {
    IEObjectDescription result = contents.getSingleElement(object);
    if (result == null) {
      result = parent.getSingleElement(object);
    }
    return result;
  }

  
  @Override
  public Iterable<IEObjectDescription> getElements(final QualifiedName name) {
    return Iterables.concat(contents.getElements(name), parent.getElements(name));
  }

  
  @Override
  public Iterable<IEObjectDescription> getElements(final EObject object) {
    return Iterables.concat(contents.getElements(object), parent.getElements(object));
  }
}
