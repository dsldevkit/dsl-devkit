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

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.avaloq.tools.ddk.checkcfg.contentassist.CheckCfgContentAssistTest;
import com.avaloq.tools.ddk.checkcfg.scoping.CheckCfgScopeProviderTest;
import com.avaloq.tools.ddk.checkcfg.syntax.CheckCfgSyntaxTest;
import com.avaloq.tools.ddk.checkcfg.validation.CheckCfgConfiguredParameterValidationsTest;
import com.avaloq.tools.ddk.checkcfg.validation.CheckCfgTest;
import com.avaloq.tools.ddk.checkcfg.validation.CheckCfgValidationTest;


/**
 * Empty class serving only as holder for JUnit5 annotations.
 */
@Suite
@SelectClasses({
// @Format-Off
  CheckCfgConfiguredParameterValidationsTest.class,
  CheckCfgContentAssistTest.class,
  CheckCfgScopeProviderTest.class,
  CheckCfgSyntaxTest.class,
  CheckCfgTest.class,
  CheckCfgValidationTest.class
// @Format-On
})

public class CheckCfgTestSuite {

}
