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
package com.avaloq.tools.ddk.xtext.generator.expression;

import org.eclipse.xtend.XtendFacade;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;


/**
 * A wrapper class around the CodeGeneration.ext extension which implements the partial Xtend to Java source code generation.
 */
public class Compiler {

  private final CompilationContext context;
  private final XtendFacade facade;

  /**
   * Creates a new compiler with the given compilation context.
   *
   * @param context
   *          the compilation context
   */
  public Compiler(final CompilationContext context) {
    this.context = context;
    this.facade = XtendFacade.create(context.getExecutionContext(), new String[] {"com::avaloq::tools::ddk::xtext::generator::expression::CodeGeneration"}); //$NON-NLS-1$
  }

  /**
   * Compiles the given expression to an equivalent Java expression.
   *
   * @param expression
   *          expression to compile
   * @return Java expression as a string
   */
  public String compile(final Expression expression) {
    Object res = facade.call("javaExpression", expression, context); //$NON-NLS-1$
    return res != null ? res.toString() : null;
  }

}
