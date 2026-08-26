/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.generator.expression;

import org.eclipse.xtext.xbase.XExpression;

import com.avaloq.tools.ddk.xtext.export.ExportStandaloneSetup;
import com.avaloq.tools.ddk.xtext.export.jvmmodel.ExportExpressionCompiler;
import com.avaloq.tools.ddk.xtext.export.jvmmodel.ExportExpressionTranslator;
import com.avaloq.tools.ddk.xtext.export.jvmmodel.ExportTranslationContext;
import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.google.inject.Injector;


/**
 * Tests Java source generation for expressions used by the Export DSL.
 */
public class ExportExpressionCodeGenerationTest extends AbstractExpressionCodeGenerationTest {

  @Override
  protected void beforeAllTests() {
    super.beforeAllTests();
    final Injector injector = new ExportStandaloneSetup().createInjector();
    getTestInformation().putTestObject(ExportExpressionCompiler.class, injector.getInstance(ExportExpressionCompiler.class));
    getTestInformation().putTestObject(ExportExpressionTranslator.class, injector.getInstance(ExportExpressionTranslator.class));
  }

  @Override
  protected String compile(final Expression expression) {
    final ExportTranslationContext context = new ExportTranslationContext();
    context.setSourceElement(expression);
    final ExportExpressionCompiler compiler = (ExportExpressionCompiler) getTestInformation().getTestObject(ExportExpressionCompiler.class);
    return compiler.javaExpression(expression, context);
  }

  @Override
  protected XExpression translate(final Expression expression) {
    final ExportTranslationContext context = new ExportTranslationContext();
    context.setSourceElement(expression);
    final ExportExpressionTranslator translator = (ExportExpressionTranslator) getTestInformation().getTestObject(ExportExpressionTranslator.class);
    return translator.translate(expression, context);
  }
}
