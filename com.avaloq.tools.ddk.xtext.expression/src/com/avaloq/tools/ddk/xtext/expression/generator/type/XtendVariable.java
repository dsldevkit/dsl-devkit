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
package com.avaloq.tools.ddk.xtext.expression.generator.type;


/**
 * Local replacement for {@code org.eclipse.xtend.expression.Variable}.
 * Represents a variable in the execution context.
 */
public class XtendVariable {

  private final String name;
  private final Object value;

  public XtendVariable(final String name, final Object value) {
    this.name = name;
    this.value = value;
  }

  /**
   * Returns the name of this variable.
   *
   * @return the variable name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the value of this variable.
   *
   * @return the variable value
   */
  public Object getValue() {
    return value;
  }
}
