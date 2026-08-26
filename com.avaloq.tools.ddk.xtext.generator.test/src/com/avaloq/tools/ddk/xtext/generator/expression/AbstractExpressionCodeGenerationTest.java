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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import org.eclipse.xtext.xbase.XExpression;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.generator.test.util.GeneratorTestUtil;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTest;


/**
 * Shared expression code-generation contract for DSL-specific compiler implementations.
 */
@SuppressWarnings("nls")
abstract class AbstractExpressionCodeGenerationTest extends AbstractXtextTest {

  @Override
  protected GeneratorTestUtil getXtextTestUtil() {
    return GeneratorTestUtil.getInstance();
  }

  /**
   * This test class does not have a test source file. {@inheritDoc}
   */
  @Override
  protected String getTestSourceFileName() {
    return null;
  }

  @Test
  @SuppressWarnings("PMD.SignatureDeclareThrowsException")
  void testNestedArithmetic() throws IOException {
    assertCompilesUnchanged("(4 + 2) * 3");
    assertCompilesUnchanged("(4 + 2) * 3 * 4");
  }

  @Test
  @SuppressWarnings("PMD.SignatureDeclareThrowsException")
  void testArithmeticControls() throws IOException {
    assertCompilesUnchanged("4 + 2");
    assertEquals("\"x\" + 2 + 3 + 4", compile("('x' + 2) + 3 + 4"));
  }

  @Test
  @SuppressWarnings("PMD.SignatureDeclareThrowsException")
  void testEqualityIsNotTranslated() throws IOException {
    assertNull(translate("1 == 2"));
    assertNull(translate("1 != 2"));
  }

  protected abstract String compile(Expression expression);

  protected abstract XExpression translate(Expression expression);

  private String compile(final String source) throws IOException {
    return compile(parse(source));
  }

  private void assertCompilesUnchanged(final String source) throws IOException {
    assertEquals(source, compile(source));
  }

  private XExpression translate(final String source) throws IOException {
    return translate(parse(source));
  }

  private Expression parse(final String source) throws IOException {
    return (Expression) getXtextTestUtil().getModel("test.expression", source);
  }
}
