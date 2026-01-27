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
 * Main test suite for all DDK tests. Uses package scanning to discover tests.
 */
// CHECKSTYLE:OFF HideUtilityClassConstructor

// @Format-Off
@Suite
@SelectPackages({
  "com.avaloq.tools.ddk.xtext",
  "com.avaloq.tools.ddk.check",
  "com.avaloq.tools.ddk.checkcfg",
  "com.avaloq.tools.ddk.typesystem",
  "com.avaloq.tools.ddk.sample.helloworld"
})
// @Format-On
class AllTests {
}
