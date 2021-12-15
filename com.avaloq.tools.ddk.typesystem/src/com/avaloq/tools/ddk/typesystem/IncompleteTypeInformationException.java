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
package com.avaloq.tools.ddk.typesystem;

/**
 * This exception indicates that a type component cannot perform its task because of incomplete type information.
 * <p>
 * An example of incomplete type information is an instance of a type that contains a null field or a reference to an unresolved proxy.
 * </p>
 */
public class IncompleteTypeInformationException extends Exception {

  private static final long serialVersionUID = 5032053653991144734L;

  /**
   * Creates a new instance of {@link IncompleteTypeInformationException} with the given message.
   */
  public IncompleteTypeInformationException(final String message) {
    super(message);
  }

  /**
   * Creates a new instance of {@link IncompleteTypeInformationException} by wrapping {@code wrappedException}.
   */
  public IncompleteTypeInformationException(final Throwable wrappedException) {
    super(wrappedException);
  }

}

