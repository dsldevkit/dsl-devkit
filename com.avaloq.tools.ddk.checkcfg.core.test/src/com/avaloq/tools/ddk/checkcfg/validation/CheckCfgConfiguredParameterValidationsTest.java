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

package com.avaloq.tools.ddk.checkcfg.validation;

import static com.avaloq.tools.ddk.checkcfg.CheckCfgConstants.PROPERTY_EXECUTABLE_EXTENSION_ATTRIBUTE;
import static com.avaloq.tools.ddk.checkcfg.CheckCfgConstants.PROPERTY_EXTENSION_POINT;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.checkcfg.util.CheckCfgTestUtil;
import com.avaloq.tools.ddk.test.checkcfg.TestPropertySpecificationWithExpectedValues;
import com.avaloq.tools.ddk.test.checkcfg.TestPropertySpecificationWithOutExpectedValues;
import com.avaloq.tools.ddk.test.core.mock.ExtensionRegistryMock;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractValidationTest;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTestUtil;

public class CheckCfgConfiguredParameterValidationsTest extends AbstractValidationTest {

  // CPD-OFF — sibling test-class scaffolding, kept explicit (#1339)
  @Override
  protected AbstractXtextTestUtil getXtextTestUtil() {
    return CheckCfgTestUtil.getInstance();
  }

  @Override
  protected List<String> getRequiredSourceFileNames() {
    return new ArrayList<String>(0);
  }

  @Override
  protected void beforeAllTests() {
    ExtensionRegistryMock.mockExecutableExtension(ExtensionRegistryMock.mockConfigurationElement(PROPERTY_EXTENSION_POINT), PROPERTY_EXECUTABLE_EXTENSION_ATTRIBUTE, TestPropertySpecificationWithExpectedValues.INSTANCE);
    ExtensionRegistryMock.mockExecutableExtension(ExtensionRegistryMock.mockConfigurationElement(PROPERTY_EXTENSION_POINT), PROPERTY_EXECUTABLE_EXTENSION_ATTRIBUTE, TestPropertySpecificationWithOutExpectedValues.INSTANCE);
    super.beforeAllTests();
  }

  @Override
  protected void afterAllTests() {
    super.afterAllTests();
    ExtensionRegistryMock.unMock(PROPERTY_EXTENSION_POINT);
  }

  @Test
  public void testConfiguredParameterValues() {
  // CPD-ON
    final TestPropertySpecificationWithExpectedValues allowedOnly = TestPropertySpecificationWithExpectedValues.INSTANCE;
    final TestPropertySpecificationWithOutExpectedValues acceptsAny = TestPropertySpecificationWithOutExpectedValues.INSTANCE;
    final String source = """
        check configuration Test
          %s = %s"notAllowed"
          for com.avaloq.tools.ddk.^check.TestLanguage {
              %s = %s"%s"
              %s = %s"whatever"
          }
        """.formatted(
        allowedOnly.getName(), error(IssueCodes.PARAMETER_VALUE_NOT_ALLOWED),
        allowedOnly.getName(), noDiagnostic(IssueCodes.PARAMETER_VALUE_NOT_ALLOWED), allowedOnly.getExpectedValues()[0],
        acceptsAny.getName(), noDiagnostic(IssueCodes.PARAMETER_VALUE_NOT_ALLOWED));
    validateKernelSourceStrictly("ConfiguredParameterValues.checkcfg", source);
  }

}
