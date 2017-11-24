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
package com.avaloq.tools.ddk.xtext.expression.generator;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;


/**
 * A wrapper class around the {@link CodeGenerationX} class which implements the partial Xtend to Java source code generation.
 */
public class CompilerX {

  private final CompilationContext context;

  /**
   * Creates a new compiler with the given compilation context.
   *
   * @param context
   *          the compilation context
   */
  public CompilerX(final CompilationContext context) {
    this.context = context;
  }

  /**
   * Compiles the given expression to an equivalent Java expression.
   *
   * @param expression
   *          expression to compile
   * @return Java expression as a string
   */
  public String compile(final Expression expression) {
    Object res = new CodeGenerationX().javaExpression(expression, context);
    return res != null ? res.toString() : null;
  }

}
