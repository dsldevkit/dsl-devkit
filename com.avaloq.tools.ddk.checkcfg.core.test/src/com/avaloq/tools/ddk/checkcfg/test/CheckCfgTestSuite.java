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
package com.avaloq.tools.ddk.checkcfg.test;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;


/**
 * Empty class serving only as holder for JUnit5 annotations.
 */
@Suite
@SelectPackages({
    "com.avaloq.tools.ddk.checkcfg.contentassist",
    "com.avaloq.tools.ddk.checkcfg.scoping",
    "com.avaloq.tools.ddk.checkcfg.syntax",
    "com.avaloq.tools.ddk.checkcfg.validation"
})
@IncludeClassNamePatterns(".*Test.*")
class CheckCfgTestSuite {

}
