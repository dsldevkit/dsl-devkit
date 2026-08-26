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

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.scope.ScopeStandaloneSetup;
import com.avaloq.tools.ddk.xtext.scope.jvmmodel.ScopeExpressionCompiler;
import com.avaloq.tools.ddk.xtext.scope.jvmmodel.ScopeExpressionTranslator;
import com.avaloq.tools.ddk.xtext.scope.jvmmodel.ScopeTranslationContext;
import com.google.inject.Injector;


/**
 * Tests Java source generation for expressions used by the Scope DSL.
 */
public class ScopeExpressionCodeGenerationTest extends AbstractExpressionCodeGenerationTest {

  @Override
  protected void beforeAllTests() {
    super.beforeAllTests();
    final Injector injector = new ScopeStandaloneSetup().createInjector();
    getTestInformation().putTestObject(ScopeExpressionCompiler.class, injector.getInstance(ScopeExpressionCompiler.class));
    getTestInformation().putTestObject(ScopeExpressionTranslator.class, injector.getInstance(ScopeExpressionTranslator.class));
  }

  @Override
  protected String compile(final Expression expression) {
    final ScopeTranslationContext context = new ScopeTranslationContext();
    context.setSourceElement(expression);
    final ScopeExpressionCompiler compiler = (ScopeExpressionCompiler) getTestInformation().getTestObject(ScopeExpressionCompiler.class);
    return compiler.javaExpression(expression, context);
  }

  @Override
  protected XExpression translate(final Expression expression) {
    final ScopeTranslationContext context = new ScopeTranslationContext();
    context.setSourceElement(expression);
    final ScopeExpressionTranslator translator = (ScopeExpressionTranslator) getTestInformation().getTestObject(ScopeExpressionTranslator.class);
    return translator.translate(expression, context);
  }
}
