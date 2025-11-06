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
package com.avaloq.tools.ddk.xtext.format.formatting;

import com.avaloq.tools.ddk.xtext.test.TestSource;
import com.avaloq.tools.ddk.xtext.test.format.util.FormatTestUtil;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractFormattingTest;


public class FormatFormattingTest extends AbstractFormattingTest {

  @Override
  protected FormatTestUtil getXtextTestUtil() {
    return FormatTestUtil.getInstance();
  }

  @Override
  protected String getResourceContent(final String sourceFileName) {
    // return content with line terminators as will be produced by formatter
    return TestSource.normalizeLineSeparators(super.getResourceContent(sourceFileName), System.getProperty("line.separator")); //$NON-NLS-1$
  }

}
