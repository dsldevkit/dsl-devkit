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
package com.avaloq.tools.ddk.xtext.export.exporting;

import org.junit.Test;

import com.avaloq.tools.ddk.xtext.test.AbstractXtextTest;
import com.avaloq.tools.ddk.xtext.test.export.util.ExportTestUtil;


/**
 * Tests exporting of Ddic sources.
 */

public class ExportExportingTest extends AbstractXtextTest { // AbstractExportingTest { // TODO - AbstractExportingTest like AbstractExportingTest?

  @Override
  protected ExportTestUtil getXtextTestUtil() {
    return ExportTestUtil.getInstance();
  }

  @Test
  public final void testExport() {
    // TODO - what should we test?
  }

}

