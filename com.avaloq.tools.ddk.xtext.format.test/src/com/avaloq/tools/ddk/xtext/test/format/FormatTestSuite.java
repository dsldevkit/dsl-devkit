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
package com.avaloq.tools.ddk.xtext.test.format;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;


/**
 * Empty class serving only as holder for JUnit5 annotations.
 */
@Suite
@SelectPackages({
    "com.avaloq.tools.ddk.xtext.format.builder",
    "com.avaloq.tools.ddk.xtext.format.formatting",
    "com.avaloq.tools.ddk.xtext.format.scoping",
    "com.avaloq.tools.ddk.xtext.format.validation"
})
@IncludeClassNamePatterns(".*Test.*")
class FormatTestSuite {

}
