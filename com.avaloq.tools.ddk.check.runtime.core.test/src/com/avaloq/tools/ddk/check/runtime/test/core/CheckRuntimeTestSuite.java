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
package com.avaloq.tools.ddk.check.runtime.test.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.avaloq.tools.ddk.check.runtime.context.CheckContextTest;
import com.avaloq.tools.ddk.check.runtime.core.registry.CheckExtensionPointTests;
import com.avaloq.tools.ddk.check.runtime.core.validation.CheckValidatorTest;
import com.avaloq.tools.ddk.check.runtime.label.CheckRuleLabelProviderTest;


/**
 * The test suite for Check core tests.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({CheckExtensionPointTests.class, CheckValidatorTest.class, CheckContextTest.class, CheckRuleLabelProviderTest.class})
public class CheckRuntimeTestSuite {

}
