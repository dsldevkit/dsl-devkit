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
package com.avaloq.tools.ddk.xtext.test.export;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.avaloq.tools.ddk.xtext.export.exporting.ExportExportingTest;
import com.avaloq.tools.ddk.xtext.export.formatting.ExportFormattingTest;
import com.avaloq.tools.ddk.xtext.export.scoping.ExportScopingTest;
import com.avaloq.tools.ddk.xtext.export.validation.ExportValidationTest;


/**
 * Empty class serving only as holder for JUnit4 annotations.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ExportFormattingTest.class, ExportValidationTest.class, ExportScopingTest.class, ExportExportingTest.class})
public class ExportTestSuite {}

