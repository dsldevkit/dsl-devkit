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
package com.avaloq.tools.ddk.check.runtime.quickfix;

/**
 * Indicates the attempt to access a non-existing position. The attempt has been
 * performed on a text store such as a document or string.
 * <p>
 * This class is not intended to be serialized.
 * </p>
 * <p>
 * This class is UI-independent.
 */
public class BadLocationException extends Exception {

  /**
   * Serial version UID for this class.
   * <p>
   * Note: This class is not intended to be serialized.
   * </p>
   * 
   * @since 3.1
   */
  private static final long serialVersionUID = 3257281452776370224L;

  /**
   * Creates a new bad location exception.
   * 
   * @param message
   *          the exception message
   */
  public BadLocationException(final String message) {
    super(message);
  }

  /**
   * Creates a new bad location exception.
   *
   * @param wrappedException
   *          the cause for the exception
   */
  public BadLocationException(final Exception wrappedException) {
    super(wrappedException);
  }
}
