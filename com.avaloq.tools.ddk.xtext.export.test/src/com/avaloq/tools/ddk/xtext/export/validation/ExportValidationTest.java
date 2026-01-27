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
package com.avaloq.tools.ddk.xtext.export.validation;

import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.test.export.util.ExportTestUtil;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractValidationTest;


/**
 * Tests validation of Export sources.
 */
@SuppressWarnings("nls")
class ExportValidationTest extends AbstractValidationTest {

  @Override
  protected ExportTestUtil getXtextTestUtil() {
    return ExportTestUtil.getInstance();
  }

  @Test
  final void testCheckExtensions() {
    // TODO cannot test as unable to load resource in test environment assertDiagnosticMessage("Extension 'XYZ' not found");
  }

  @Test
  final void testCheckInterfaceAndExportUniqueness() {
    assertDiagnosticMessage("declaration duplicate found: ecore::EClass");
  }

  @Test
  final void testCheckExportFieldUniqueness() {
    assertDiagnosticMessage("duplicate found: instanceClassName");
  }

  @Test
  final void testCheckUserDataNameAsFeature() {
    assertDiagnosticMessage("instanceClassName is already defined as field");
    // TODO assertDiagnosticMessage("xxx has the same name as an existing feature");
  }

  @Test
  final void testCheckOverlap() {
    // TODO assertDiagnosticMessage("");
  }

}
