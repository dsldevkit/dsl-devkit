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
package com.avaloq.tools.ddk.xtext.export.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.test.export.util.ExportTestUtil;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTest;


/**
 * Regression tests for URI-based package derivation in {@link ExportGeneratorX}.
 */
@SuppressWarnings("nls")
public class ExportGeneratorXTest extends AbstractXtextTest {

  private final ExportGeneratorX exportGeneratorX = getXtextTestUtil().get(ExportGeneratorX.class);

  @Override
  protected ExportTestUtil getXtextTestUtil() {
    return ExportTestUtil.getInstance();
  }

  @Test
  public void testShallowProjectUriFallsBackToProjectPackage() {
    final ExportModel model = (ExportModel) getTestSource().getModel();

    assertEquals("test.naming.ExportGeneratorXTestExportedNamesProvider", exportGeneratorX.getExportedNamesProvider(model));
    assertEquals("test.resource.ExportGeneratorXTestResourceDescriptionManager", exportGeneratorX.getResourceDescriptionManager(model));
    assertEquals("test.resource.ExportGeneratorXTestResourceDescriptionStrategy", exportGeneratorX.getResourceDescriptionStrategy(model));
    assertEquals("test.resource.ExportGeneratorXTestResourceDescriptionConstants", exportGeneratorX.getResourceDescriptionConstants(model));
    assertEquals("test.resource.ExportGeneratorXTestFingerprintComputer", exportGeneratorX.getFingerprintComputer(model));
    assertEquals("test.resource.ExportGeneratorXTestFragmentProvider", exportGeneratorX.getFragmentProvider(model));
  }
}
