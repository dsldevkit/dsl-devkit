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

import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;


/**
 * A null implementation of {@link IScope} that can be used instead of null.
 */
public final class NullScope implements IScope {

  private static final IScope INSTANCE = new NullScope();

  /**
   * Returns the {@link NullScope} singleton instance.
   *
   * @return the {@link NullScope} singleton instance
   */
  public static IScope getInstance() {
    return INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  public IEObjectDescription getSingleElement(final QualifiedName name) {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<IEObjectDescription> getElements(final QualifiedName name) {
    return Collections.emptyList();
  }

  /** {@inheritDoc} */
  @Override
  public IEObjectDescription getSingleElement(final EObject object) {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<IEObjectDescription> getElements(final EObject object) {
    return Collections.emptyList();
  }

  /** {@inheritDoc} */
  @Override
  public Iterable<IEObjectDescription> getAllElements() {
    return Collections.emptyList();
  }

  /**
   * Creates a new instance of {@link NullScope}.
   */
  private NullScope() {}
}
