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

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.generator.ExpressionExtensions;
import com.avaloq.tools.ddk.xtext.generator.test.util.GeneratorTestUtil;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextTest;


public class ExpressionsExtentionsTest extends AbstractXtextTest {

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
  public final void serialize() throws IOException {
    Expression e = (Expression) getXtextTestUtil().getModel("test.expression." + getXtextTestUtil().getFileExtension(), "let x = 1 : 0");
    assertEquals("Simple serialization works", "let x = 1 : 0", ExpressionExtensions.serialize(e));
  }
}
