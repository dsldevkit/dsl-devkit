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
package com.avaloq.tools.ddk.check.test.core;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.avaloq.tools.ddk.check.core.generator.IssueCodeValueTest;
import com.avaloq.tools.ddk.check.core.test.BasicModelTest;
import com.avaloq.tools.ddk.check.core.test.BugAig1314;
import com.avaloq.tools.ddk.check.core.test.BugAig830;
import com.avaloq.tools.ddk.check.core.test.BugDsl27;
import com.avaloq.tools.ddk.check.core.test.CheckScopingTest;
import com.avaloq.tools.ddk.check.core.test.IssueCodeToLabelMapGenerationTest;
import com.avaloq.tools.ddk.check.core.test.ProjectBasedTests;
import com.avaloq.tools.ddk.check.formatting.CheckFormattingTest;


/**
 * Junit5 version of test suites. does not implement the logic in our DiscerningSuite.
 */
@Suite
@SelectClasses({
// @Format-Off
  IssueCodeValueTest.class,
  BasicModelTest.class,
  BugAig830.class,
  CheckScopingTest.class,
  IssueCodeToLabelMapGenerationTest.class,
  ProjectBasedTests.class,
  BugAig1314.class,
  BugDsl27.class,
  CheckFormattingTest.class
  // @Format-On
})
public class CheckCoreTestSuite {

}
