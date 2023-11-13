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
package com.avaloq.tools.ddk.xtext.valid.formatting;

import org.junit.Test;

import com.avaloq.tools.ddk.xtext.test.TestSource;
import com.avaloq.tools.ddk.xtext.test.formatting.AbstractFormattingTest;
import com.avaloq.tools.ddk.xtext.test.valid.util.ValidTestUtil;


/**
 * Tests formatting of Valid source fragments.
 */
public class ValidFormattingTest extends AbstractFormattingTest {

  @Override
  protected ValidTestUtil getXtextTestUtil() {
    return ValidTestUtil.getInstance();
  }

  @Override
  protected String getResourceContent(final String sourceFileName) {
    // return content with line terminators as will be produced by formatter
    return TestSource.normalizeLineSeparators(super.getResourceContent(sourceFileName), System.getProperty("line.separator"));
  }

  @Override
  @Test
  public final void formattedParseTreeConstructor() {
    super.formattedParseTreeConstructor();
  }

  @Override
  @Test
  public final void preservedNodeModel() {
    super.preservedNodeModel();
  }
}
