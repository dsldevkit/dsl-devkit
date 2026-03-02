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
package com.avaloq.tools.ddk.xtext.generator.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext;
import com.avaloq.tools.ddk.xtext.expression.generator.type.DefaultXtendExecutionContext;
import com.avaloq.tools.ddk.xtext.expression.generator.type.PrimitiveXtendType;
import com.avaloq.tools.ddk.xtext.expression.generator.type.XtendExtension;
import com.avaloq.tools.ddk.xtext.expression.generator.type.XtendType;


@SuppressWarnings({"nls", "PMD.SignatureDeclareThrowsException"})
public class CompilationContextTest {

  @Test
  void isExtension() {
    DefaultXtendExecutionContext executionContext = new DefaultXtendExecutionContext();
    executionContext.addExtension(new XtendExtension("test"));
    final CompilationContext context = new CompilationContext(executionContext, null);

    assertEquals(true, context.isExtension("test"), "test extension not identified");
  }

  @Test
  void analyze() {
    DefaultXtendExecutionContext executionContext = new DefaultXtendExecutionContext();
    final CompilationContext context = new CompilationContext(executionContext, null);

    XtendType expectedType = executionContext.getTypeForName("Integer");
    assertNotNull(expectedType);
    assertEquals(expectedType, context.analyze("42"), "Cannot analyze Integer");

    expectedType = executionContext.getTypeForName("Real");
    assertNotNull(expectedType);
    assertEquals(expectedType, context.analyze("3.33"), "Cannot analyze Real");

    expectedType = executionContext.getTypeForName("String");
    assertNotNull(expectedType);
    assertEquals(expectedType, context.analyze("'foo'"), "Cannot analyse String 'foo'");
    assertEquals(expectedType, context.analyze("\"foo\""), "Cannot analyse String \"foo\"");
  }

}
