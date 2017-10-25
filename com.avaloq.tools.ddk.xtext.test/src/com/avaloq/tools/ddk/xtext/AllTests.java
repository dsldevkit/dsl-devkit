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
package com.avaloq.tools.ddk.xtext;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.avaloq.tools.ddk.check.runtime.test.core.CheckRuntimeTestSuite;
import com.avaloq.tools.ddk.check.test.core.CheckCoreTestSuite;
import com.avaloq.tools.ddk.check.test.runtime.tests.CheckExecutionEnvironmentTestSuite;
import com.avaloq.tools.ddk.check.test.runtime.tests.CheckLibraryChecksTestSuite;
import com.avaloq.tools.ddk.check.ui.test.CheckUiTestSuite;
import com.avaloq.tools.ddk.checkcfg.test.CheckCfgTestSuite;
import com.avaloq.tools.ddk.checkcfg.ui.test.CheckCfgUiTestSuite;
import com.avaloq.tools.ddk.typesystem.test.TypeSystemTestSuite;
import com.avaloq.tools.ddk.xtext.generator.test.generator.GeneratorTestSuite;
import com.avaloq.tools.ddk.xtext.test.TargetDefinitionSetup;
import com.avaloq.tools.ddk.xtext.test.export.ExportTestSuite;
import com.avaloq.tools.ddk.xtext.test.format.FormatTestSuite;
import com.avaloq.tools.ddk.xtext.test.valid.ValidTestSuite;
import com.avaloq.tools.ddk.xtext.ui.test.XtextUiTestSuite;
import com.avaloq.tools.ddk.xtextspy.test.XtextSpyTestSuite;


/**
 * Empty class serving only as holder for JUnit4 annotations.
 */
// CHECKSTYLE:OFF HideUtilityClassConstructor

// @Format-Off
@RunWith(Suite.class)
@Suite.SuiteClasses({
  XtextTestSuite.class,
  XtextUiTestSuite.class,
  GeneratorTestSuite.class,
  ValidTestSuite.class,
  FormatTestSuite.class,
  ExportTestSuite.class,
  CheckRuntimeTestSuite.class,
  CheckCoreTestSuite.class,
  CheckExecutionEnvironmentTestSuite.class,
  CheckLibraryChecksTestSuite.class,
  CheckUiTestSuite.class,
  CheckCfgUiTestSuite.class,
  CheckCfgTestSuite.class,
  XtextSpyTestSuite.class,
  TypeSystemTestSuite.class
})
// @Format-On
@SuppressWarnings("PMD.SystemPrintln")
// I don't want to use a logger here; I want this to be printed always, no matter what logger config we have.
public class AllTests {

  @BeforeClass
  public static void setUp() throws IOException {
    if (Boolean.getBoolean("tychotest")) { //$NON-NLS-1$
      // This system property is set in ddk-parent/pom.xml, tycho-surefire section.
      System.err.println("Running on tycho; setting target platform."); //$NON-NLS-1$
      // Make sure PDE can deal with plugin projects we may create in our tests.
      TargetDefinitionSetup.initializeTargetPlatform();
      System.err.println("Target platform set."); //$NON-NLS-1$
    } else {
      System.err.println("Not running on tycho; target platform unchanged."); //$NON-NLS-1$
    }
  }
}
