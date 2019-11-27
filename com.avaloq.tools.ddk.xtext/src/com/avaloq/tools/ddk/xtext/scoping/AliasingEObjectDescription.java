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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.AbstractEObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;


/**
 * Like the parent class, but giving access to the delegate.
 */
public class AliasingEObjectDescription extends AbstractEObjectDescription {

  /** The aliased description. */
  private final IEObjectDescription delegate;
  /** The alias. */
  private final QualifiedName alias;

  /**
   * Creator. Creates a new IEObjectDescription with a new name.
   *
   * @param name
   *          The new name
   * @param delegate
   *          The original, now aliased, IEObjectDescription.
   */
  public AliasingEObjectDescription(final QualifiedName name, final IEObjectDescription delegate) {
    super();
    this.delegate = delegate;
    this.alias = name;
  }

  /**
   * @return the original name, this element can be accessed by.
   */
  public QualifiedName getOriginalName() {
    return delegate.getName();
  }

  /**
   * @return the original qualified name of the element.
   */
  public QualifiedName getOriginalQualifiedName() {
    return delegate.getQualifiedName();
  }

  /** {@inheritDoc} */
  @Override
  public QualifiedName getName() {
    return alias;
  }

  /** {@inheritDoc} */
  @Override
  public QualifiedName getQualifiedName() {
    return alias;
  }

  /** {@inheritDoc} */
  @Override
  public EObject getEObjectOrProxy() {
    return delegate.getEObjectOrProxy();
  }

  /** {@inheritDoc} */
  @Override
  public URI getEObjectURI() {
    return delegate.getEObjectURI();
  }

  /** {@inheritDoc} */
  @Override
  public String getUserData(final String name) {
    return delegate.getUserData(name);
  }

  /** {@inheritDoc} */
  @Override
  public String[] getUserDataKeys() {
    return delegate.getUserDataKeys();
  }

  /** {@inheritDoc} */
  @Override
  public EClass getEClass() {
    return delegate.getEClass();
  }

}
