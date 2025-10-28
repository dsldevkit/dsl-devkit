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

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;


/**
 * Junit5 version of test suites. does not implement the logic in our DiscerningSuite.
 */
@Suite
@SelectPackages({
// @Format-Off
  "com.avaloq.tools.ddk.check.runtime.context",
  "com.avaloq.tools.ddk.check.runtime.core.registry",
  "com.avaloq.tools.ddk.check.runtime.core.validation",
  "com.avaloq.tools.ddk.check.runtime.label"
  // @Format-On
})
public class CheckRuntimeTestSuite {

}
