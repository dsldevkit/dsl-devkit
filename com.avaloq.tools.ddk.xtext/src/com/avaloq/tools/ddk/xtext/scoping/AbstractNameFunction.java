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

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;


/**
 * A name function maps IEObjectDescriptions or EObjects to names.
 */
public abstract class AbstractNameFunction implements INameFunction {

  private static final Logger LOG = LogManager.getLogger(AbstractNameFunction.class);

  /**
   * String value used in {@link #toString()} representation and {@link #hashCode()}. Used to support comparing NameFunctions when
   * used as a key in a Map. If <code>null</code> the default Object {@link Object#toString()} and {@link Object#hashCode()} implementations will be used.
   */
  private final String representation;

  /**
   * Creates a name function with {@link #representation} as <code>null</code>.
   */
  public AbstractNameFunction() {
    representation = null;
  }

  /**
   * Creates a name function with the given value for {@link #representation}.
   *
   * @param rep
   *          String representation of NameFunction
   */
  public AbstractNameFunction(final String representation) {
    this.representation = representation;
  }

  /** {@inheritDoc} */
  @Override
  public QualifiedName apply(final IEObjectDescription from) {
    LOG.warn("No explicit name function for description " + from.getEObjectURI() + " of type " + EcoreUtil.getURI(from.getEClass())); //$NON-NLS-1$ //$NON-NLS-2$
    return from.getName();
  }

  @Override
  public String toString() {
    return representation == null ? super.toString() : "NameFunction(" + representation + ')'; //$NON-NLS-1$
  }
}
