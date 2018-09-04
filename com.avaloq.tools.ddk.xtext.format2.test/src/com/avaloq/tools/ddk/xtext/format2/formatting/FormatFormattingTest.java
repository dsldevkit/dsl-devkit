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
package com.avaloq.tools.ddk.xtext.format2.formatting;

import com.avaloq.tools.ddk.xtext.format2.test.FormatTestUtil;
import com.avaloq.tools.ddk.xtext.test.TestSource;
import com.avaloq.tools.ddk.xtext.test.formatting.AbstractFormattingTest;


public class FormatFormattingTest extends AbstractFormattingTest {

  @Override
  protected FormatTestUtil getXtextTestUtil() {
    return FormatTestUtil.getInstance();
  }

  @Override
  protected String getResourceContent(final String sourceFileName) {
    // return content with line terminators as will be produced by formatter
    return TestSource.normalizeLineSeparators(super.getResourceContent(sourceFileName), System.getProperty("line.separator"));
  }

}
