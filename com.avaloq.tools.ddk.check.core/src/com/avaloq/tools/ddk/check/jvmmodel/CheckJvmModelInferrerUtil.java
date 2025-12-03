/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.check.jvmmodel;

/**
 * A class to avoid multi-line strings in Xtend which can be constant strings: See https://github.com/eclipse/xtext/issues/3091.
 */
public final class CheckJvmModelInferrerUtil {

  private CheckJvmModelInferrerUtil() {
    // empty
  }

  /**
   * The documentation for the Get Message Method.
   */
  public static final String GET_MESSAGE_DOCUMENTATION = """
      Gets the message associated with a violation of this check.

      @param bindings
                the message bindings
      @return the message associated with a violation of this check"""; //$NON-NLS-1$
}
