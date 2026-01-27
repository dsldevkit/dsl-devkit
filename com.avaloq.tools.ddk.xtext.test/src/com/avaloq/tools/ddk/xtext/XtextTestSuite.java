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
package com.avaloq.tools.ddk.xtext;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;


/**
 * Test suite for core Xtext tests.
 */
// @Format-Off
@Suite
@SelectPackages({
  "com.avaloq.tools.ddk.xtext.builder",
  "com.avaloq.tools.ddk.xtext.jupiter.formatter",
  "com.avaloq.tools.ddk.xtext.linking",
  "com.avaloq.tools.ddk.xtext.naming",
  "com.avaloq.tools.ddk.xtext.resource",
  "com.avaloq.tools.ddk.xtext.util"
})
// @Format-On
class XtextTestSuite {
}
