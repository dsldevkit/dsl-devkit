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

package com.avaloq.tools.ddk.checkcfg.contentassist

import com.avaloq.tools.ddk.checkcfg.util.CheckCfgTestUtil
import com.avaloq.tools.ddk.test.checkcfg.TestPropertySpecificationWithExpectedValues
import com.avaloq.tools.ddk.test.checkcfg.TestPropertySpecificationWithOutExpectedValues
import com.avaloq.tools.ddk.xtext.test.contentassist.AbstractAcfContentAssistTest
import com.google.common.collect.Lists
import org.junit.Test

import static com.avaloq.tools.ddk.checkcfg.CheckCfgConstants.PROPERTY_EXECUTABLE_EXTENSION_ATTRIBUTE
import static com.avaloq.tools.ddk.checkcfg.CheckCfgConstants.PROPERTY_EXTENSION_POINT

import static extension com.avaloq.tools.ddk.test.core.mock.ExtensionRegistryMock.mockConfigurationElement
import static extension com.avaloq.tools.ddk.test.core.mock.ExtensionRegistryMock.mockExecutableExtension
import static extension com.avaloq.tools.ddk.test.core.mock.ExtensionRegistryMock.unMock
import com.avaloq.tools.ddk.test.core.BugTest

class CheckCfgContentAssistTest extends AbstractAcfContentAssistTest {

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
  def testConfiguredParameterProposals() {
    assertKernelSourceProposals("ConfiguredParameterProposals.checkcfg", '''
      check configuration Test {
        catalog TestChecks {
          default Test (
            «TestPropertySpecificationWithExpectedValues.INSTANCE.name» = «expected(TestPropertySpecificationWithExpectedValues.INSTANCE.expectedValues)»"banana"
          )
        }
      }
    ''')
  }

  @BugTest(value="DSL-1811", unresolved=true)
  def testNoTypeMismatchedParameterValueProposals() {
    assertKernelSourceProposals("NoTypeMismatchedParameterValueProposals.checkcfg", '''
      check configuration Test {
        catalog TestChecks {
          default Test (
            «TestPropertySpecificationWithExpectedValues.INSTANCE.name» = «expectedExactly(TestPropertySpecificationWithExpectedValues.INSTANCE.expectedValues)»"banana"
          )
        }
      }
    ''')
  }



}
