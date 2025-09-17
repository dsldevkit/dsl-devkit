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

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.eclipse.internal.xtend.xtend.ast.ExtensionFile;
import org.eclipse.internal.xtend.xtend.parser.ParseFacade;
import org.eclipse.xtend.expression.ExecutionContextImpl;
import org.eclipse.xtend.type.impl.java.JavaBeansMetaModel;
import org.eclipse.xtend.typesystem.Type;
import org.junit.Test;

import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext;


public class CompilationContextTest {

  @Test
  public void isExtension() {
    ExecutionContextImpl executionContext = new ExecutionContextImpl();
    executionContext.registerMetaModel(new JavaBeansMetaModel());
    ExtensionFile extensionFile = ParseFacade.file(new InputStreamReader(getClass().getResourceAsStream("/com/avaloq/tools/ddk/xtext/generator/expression/TestExtensions.ext"), StandardCharsets.UTF_8), "TestExtensions.ext");
    executionContext = (ExecutionContextImpl) executionContext.cloneWithResource(extensionFile);
    final CompilationContext context = new CompilationContext(executionContext, null);

    assertTrue("test extension not identified", context.isExtension("test"));
  }

  @Test
  public void analyze() {
    ExecutionContextImpl executionContext = new ExecutionContextImpl();
    executionContext.registerMetaModel(new JavaBeansMetaModel());
    final CompilationContext context = new CompilationContext(executionContext, null);

    Type expectedType = executionContext.getTypeForName("Integer");
    assertSame("Cannot analyze Integer", expectedType, context.analyze("1 + 3"));

    expectedType = executionContext.getTypeForName("Real");
    assertSame("Cannot analyze Real", expectedType, context.analyze("1 + 3.33"));

    expectedType = executionContext.getTypeForName("String");
    assertSame("Cannot analyse String 'foo'", expectedType, context.analyze("\'foo\'"));
    assertSame("Cannot analyse String \"foo \" ", expectedType, context.analyze("\"foo\""));
    assertSame("Cannot analyse String \"foo\" + \'bar\'", expectedType, context.analyze("\"foo\" + \'bar\'"));
  }

}
