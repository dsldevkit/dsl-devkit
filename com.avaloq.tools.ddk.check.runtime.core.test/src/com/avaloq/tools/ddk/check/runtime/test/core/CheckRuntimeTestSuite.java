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
package com.avaloq.tools.ddk.check.runtime.test.core;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.avaloq.tools.ddk.check.runtime.context.CheckContextTest;
import com.avaloq.tools.ddk.check.runtime.core.registry.CheckExtensionPointTests;
import com.avaloq.tools.ddk.check.runtime.core.validation.CheckValidatorTest;
import com.avaloq.tools.ddk.check.runtime.label.CheckRuleLabelProviderTest;


/**
 * Junit5 version of test suites. does not implement the logic in our DiscerningSuite.
 */
@Suite
@SelectClasses({
// @Format-Off
  CheckContextTest.class,
  CheckExtensionPointTests.class,
  CheckValidatorTest.class,
  CheckRuleLabelProviderTest.class
  // @Format-On
})
public class CheckRuntimeTestSuite {

}
