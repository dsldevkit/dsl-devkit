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
package com.avaloq.tools.ddk.check.test.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.avaloq.tools.ddk.check.core.test.BasicModelTest;
import com.avaloq.tools.ddk.check.core.test.BugAig1314;
import com.avaloq.tools.ddk.check.core.test.BugAig830;
import com.avaloq.tools.ddk.check.core.test.BugDsl27;
import com.avaloq.tools.ddk.check.core.test.CheckScopingTest;
import com.avaloq.tools.ddk.check.core.test.IssueCodeToLabelMapGenerationTest;
import com.avaloq.tools.ddk.check.core.test.ProjectBasedTests;
import com.avaloq.tools.ddk.check.imports.test.CheckRewritableImportSectionFactoryTest;
import com.avaloq.tools.ddk.check.validation.CheckApiAccessValidationsTest;
import com.avaloq.tools.ddk.check.validation.CheckJavaValidatorUtilTest;
import com.avaloq.tools.ddk.check.validation.CheckValidationTest;


/**
 * Empty class serving only as holder for JUnit4 annotations.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
// @Format-Off
  BasicModelTest.class,
  CheckScopingTest.class,
  CheckValidationTest.class,
  CheckJavaValidatorUtilTest.class,
  CheckRewritableImportSectionFactoryTest.class,
  BugAig830.class,
  BugAig1314.class,
  BugDsl27.class,
  ProjectBasedTests.class,
  CheckApiAccessValidationsTest.class,
  IssueCodeToLabelMapGenerationTest.class
// @Format-On
})
public class CheckCoreTestSuite {

}
