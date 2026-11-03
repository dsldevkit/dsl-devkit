/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.avaloq.tools.ddk.caching.test.LocalMapCacheTest;
import com.avaloq.tools.ddk.caching.test.MapCacheTest;


/**
 * Empty class serving only as holder for JUnit 5 suite annotations.
 */
@Suite
@SelectClasses({
// @Format-Off
  LocalMapCacheTest.class,
  MapCacheTest.class
  // @Format-On
})
public class DdkTestSuite {

}
