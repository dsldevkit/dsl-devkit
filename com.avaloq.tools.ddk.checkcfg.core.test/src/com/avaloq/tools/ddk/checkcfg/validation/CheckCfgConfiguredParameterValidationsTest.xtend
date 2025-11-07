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

package com.avaloq.tools.ddk.checkcfg.validation

import com.avaloq.tools.ddk.checkcfg.util.CheckCfgTestUtil
import com.avaloq.tools.ddk.test.checkcfg.TestPropertySpecificationWithExpectedValues
import com.avaloq.tools.ddk.test.checkcfg.TestPropertySpecificationWithOutExpectedValues
import com.google.common.collect.Lists

import static com.avaloq.tools.ddk.checkcfg.CheckCfgConstants.PROPERTY_EXECUTABLE_EXTENSION_ATTRIBUTE
import static com.avaloq.tools.ddk.checkcfg.CheckCfgConstants.PROPERTY_EXTENSION_POINT

import static extension com.avaloq.tools.ddk.test.core.mock.ExtensionRegistryMock.mockConfigurationElement
import static extension com.avaloq.tools.ddk.test.core.mock.ExtensionRegistryMock.mockExecutableExtension
import static extension com.avaloq.tools.ddk.test.core.mock.ExtensionRegistryMock.unMock
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractValidationTest
import org.junit.jupiter.api.Test

class CheckCfgConfiguredParameterValidationsTest extends AbstractValidationTest {

  override protected getXtextTestUtil() {
    return CheckCfgTestUtil.instance
  }

  override protected getRequiredSourceFileNames() {
    Lists.newArrayListWithCapacity(0)
  }

  override protected beforeAllTests() {
    PROPERTY_EXTENSION_POINT.mockConfigurationElement.mockExecutableExtension(PROPERTY_EXECUTABLE_EXTENSION_ATTRIBUTE, TestPropertySpecificationWithExpectedValues.INSTANCE)
    PROPERTY_EXTENSION_POINT.mockConfigurationElement.mockExecutableExtension(PROPERTY_EXECUTABLE_EXTENSION_ATTRIBUTE, TestPropertySpecificationWithOutExpectedValues.INSTANCE)
    super.beforeAllTests()
  }

  override protected afterAllTests() {
    super.afterAllTests()
    PROPERTY_EXTENSION_POINT.unMock
  }

  @Test
  def testConfiguredParameterValues() {
    val allowedOnly = TestPropertySpecificationWithExpectedValues.INSTANCE
    val acceptsAny = TestPropertySpecificationWithOutExpectedValues.INSTANCE
    validateKernelSourceStrictly("ConfiguredParameterValues.checkcfg", '''
      check configuration Test
        «allowedOnly.name» = «error(IssueCodes.PARAMETER_VALUE_NOT_ALLOWED)»"notAllowed"
        for com.avaloq.tools.ddk.^check.TestLanguage {
            «allowedOnly.name» = «noDiagnostic(IssueCodes.PARAMETER_VALUE_NOT_ALLOWED)»"«allowedOnly.expectedValues.head»"
            «acceptsAny.name» = «noDiagnostic(IssueCodes.PARAMETER_VALUE_NOT_ALLOWED)»"whatever"
        }
    ''')
  }

}
