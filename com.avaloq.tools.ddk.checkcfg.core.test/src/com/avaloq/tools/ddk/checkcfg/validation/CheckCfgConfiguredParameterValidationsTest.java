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

import java.util.List;

import com.avaloq.tools.ddk.checkcfg.util.CheckCfgTestUtil;
import com.avaloq.tools.ddk.test.checkcfg.TestPropertySpecificationWithExpectedValues;
import com.avaloq.tools.ddk.test.checkcfg.TestPropertySpecificationWithOutExpectedValues;
import com.avaloq.tools.ddk.test.core.mock.ExtensionRegistryMock;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractValidationTest;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTestUtil;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

public class CheckCfgConfiguredParameterValidationsTest extends AbstractValidationTest {

  @Override
  protected AbstractXtextTestUtil getXtextTestUtil() {
    return CheckCfgTestUtil.getInstance();
  }

  @Override
  protected List<String> getRequiredSourceFileNames() {
    return Lists.newArrayListWithCapacity(0);
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
    final TestPropertySpecificationWithExpectedValues allowedOnly = TestPropertySpecificationWithExpectedValues.INSTANCE;
    final TestPropertySpecificationWithOutExpectedValues acceptsAny = TestPropertySpecificationWithOutExpectedValues.INSTANCE;
    final StringBuilder builder = new StringBuilder(512);
    builder.append("check configuration Test\n");
    builder.append("  ").append(allowedOnly.getName()).append(" = ").append(error(IssueCodes.PARAMETER_VALUE_NOT_ALLOWED)).append("\"notAllowed\"\n");
    builder.append("  for com.avaloq.tools.ddk.^check.TestLanguage {\n");
    builder.append("      ").append(allowedOnly.getName()).append(" = ").append(noDiagnostic(IssueCodes.PARAMETER_VALUE_NOT_ALLOWED)).append('"').append(allowedOnly.getExpectedValues()[0]).append("\"\n");
    builder.append("      ").append(acceptsAny.getName()).append(" = ").append(noDiagnostic(IssueCodes.PARAMETER_VALUE_NOT_ALLOWED)).append("\"whatever\"\n");
    builder.append("  }\n");
    validateKernelSourceStrictly("ConfiguredParameterValues.checkcfg", builder);
  }

}
