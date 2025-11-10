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
package com.avaloq.tools.ddk.check.ui.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.avaloq.tools.ddk.check.ui.test.builder.CheckContextsExtensionTest;
import com.avaloq.tools.ddk.check.ui.test.builder.CheckMarkerHelpExtensionTest;
import com.avaloq.tools.ddk.check.ui.test.builder.CheckTocExtensionTest;
import com.avaloq.tools.ddk.check.ui.test.contentassist.BugAig931Test;
import com.avaloq.tools.ddk.check.ui.test.quickfix.CheckQuickfixTest;


/**
 * Empty class serving only as holder for JUnit5 annotations.
 */
// @Format-Off
@Suite
@SelectClasses({
  CheckQuickfixTest.class,
  CheckProjectWizardTest.class,
  CheckCatalogWizardTest.class,
  CheckMarkerHelpExtensionTest.class,
  CheckContextsExtensionTest.class,
  CheckTocExtensionTest.class,
  BugAig931Test.class
})
// @Format-On
public class CheckUiTestSuite {
}
