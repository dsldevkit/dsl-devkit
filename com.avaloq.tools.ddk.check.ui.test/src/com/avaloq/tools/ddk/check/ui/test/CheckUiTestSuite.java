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

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;



/**
 * Empty class serving only as holder for JUnit5 annotations.
 */
// @Format-Off
@Suite
@SelectPackages({
  "com.avaloq.tools.ddk.check.ui.test.builder",
  "com.avaloq.tools.ddk.check.ui.test.contentassist",
  "com.avaloq.tools.ddk.check.ui.test.quickfix"
})
// @Format-On
class CheckUiTestSuite {
}
