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

/**
 * A name function maps IEObjectDescriptions or EObjects to names.
 */
public abstract class AbstractNameFunction implements INameFunction {

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

  @Override
  public String toString() {
    return representation == null ? super.toString() : "NameFunction(" + representation + ')'; //$NON-NLS-1$
  }
}
