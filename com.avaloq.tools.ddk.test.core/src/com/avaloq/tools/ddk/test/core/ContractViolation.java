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
package com.avaloq.tools.ddk.test.core;

/**
 * Indicates a violation of a contract condition.
 */
public class ContractViolation extends AssertionError {
  private static final long serialVersionUID = 1L;

  /**
   * Creates a new instance of {@link ContractViolation}.
   * 
   * @param cause
   *          the cause of the violation, may be {@code null}
   */
  public ContractViolation(final Throwable cause) {
    super(cause);
  }
}
