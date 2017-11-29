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
package com.avaloq.tools.ddk.test.core;

/**
 * Indicates a violation of a precondition.
 */
public class PreconditionViolation extends ContractViolation {
  private static final long serialVersionUID = 1L;

  /**
   * Creates a new instance of {@link PreconditionViolation}.
   * 
   * @param cause
   *          the cause of the violation, may be {@code null}
   */
  public PreconditionViolation(final Throwable cause) {
    super(cause);
  }
}

