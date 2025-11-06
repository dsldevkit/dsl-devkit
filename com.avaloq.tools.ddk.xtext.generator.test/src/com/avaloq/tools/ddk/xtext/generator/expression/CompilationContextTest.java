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

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.eclipse.internal.xtend.xtend.ast.ExtensionFile;
import org.eclipse.internal.xtend.xtend.parser.ParseFacade;
import org.eclipse.xtend.expression.ExecutionContextImpl;
import org.eclipse.xtend.type.impl.java.JavaBeansMetaModel;
import org.eclipse.xtend.typesystem.Type;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext;


@SuppressWarnings({"nls", "PMD.SignatureDeclareThrowsException"})
public class CompilationContextTest {

  @Test
  void isExtension() {
    ExecutionContextImpl executionContext = new ExecutionContextImpl();
    executionContext.registerMetaModel(new JavaBeansMetaModel());
    ExtensionFile extensionFile = ParseFacade.file(new InputStreamReader(getClass().getResourceAsStream("/com/avaloq/tools/ddk/xtext/generator/expression/TestExtensions.ext"), StandardCharsets.UTF_8), "TestExtensions.ext");
    executionContext = (ExecutionContextImpl) executionContext.cloneWithResource(extensionFile);
    final CompilationContext context = new CompilationContext(executionContext, null);

    assertTrue(context.isExtension("test"), "test extension not identified");
  }

  @Test
  void analyze() {
    ExecutionContextImpl executionContext = new ExecutionContextImpl();
    executionContext.registerMetaModel(new JavaBeansMetaModel());
    final CompilationContext context = new CompilationContext(executionContext, null);

    Type expectedType = executionContext.getTypeForName("Integer");
    assertSame(expectedType, context.analyze("1 + 3"), "Cannot analyze Integer");

    expectedType = executionContext.getTypeForName("Real");
    assertSame(expectedType, context.analyze("1 + 3.33"), "Cannot analyze Real");

    expectedType = executionContext.getTypeForName("String");
    assertSame(expectedType, context.analyze("\'foo\'"), "Cannot analyse String 'foo'");
    assertSame(expectedType, context.analyze("\"foo\""), "Cannot analyse String \"foo \" ");
    assertSame(expectedType, context.analyze("\"foo\" + \'bar\'"), "Cannot analyse String \"foo\" + \'bar\'");
  }

}
