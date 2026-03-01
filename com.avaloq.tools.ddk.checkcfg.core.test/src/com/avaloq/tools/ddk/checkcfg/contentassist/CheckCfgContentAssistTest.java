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

package com.avaloq.tools.ddk.checkcfg.contentassist;

import com.avaloq.tools.ddk.checkcfg.CheckCfgUiInjectorProvider;
import com.avaloq.tools.ddk.checkcfg.util.CheckCfgTestUtil;
import com.avaloq.tools.ddk.test.checkcfg.TestPropertySpecificationWithExpectedValues;
import com.avaloq.tools.ddk.test.checkcfg.TestPropertySpecificationWithOutExpectedValues;
import com.avaloq.tools.ddk.test.core.jupiter.BugTest;
import com.avaloq.tools.ddk.test.core.mock.ExtensionRegistryMock;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractAcfContentAssistTest;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTestUtil;
import com.google.common.collect.Lists;
import java.util.List;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.avaloq.tools.ddk.checkcfg.CheckCfgConstants.PROPERTY_EXECUTABLE_EXTENSION_ATTRIBUTE;
import static com.avaloq.tools.ddk.checkcfg.CheckCfgConstants.PROPERTY_EXTENSION_POINT;

@ExtendWith(InjectionExtension.class)
@InjectWith(CheckCfgUiInjectorProvider.class)
// CHECKSTYLE:CONSTANTS-OFF
public class CheckCfgContentAssistTest extends AbstractAcfContentAssistTest {

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
  public void testConfiguredParameterProposals() {
    final StringBuilder builder = new StringBuilder(512);
    builder.append("check configuration Test {\n");
    builder.append("  catalog TestChecks {\n");
    builder.append("    default Test (\n");
    builder.append("      ").append(TestPropertySpecificationWithExpectedValues.INSTANCE.getName()).append(" = ").append(expected(TestPropertySpecificationWithExpectedValues.INSTANCE.getExpectedValues())).append("\"banana\"\n");
    builder.append("    )\n");
    builder.append("  }\n");
    builder.append("}\n");
    assertKernelSourceProposals("ConfiguredParameterProposals.checkcfg", builder);
  }

  @BugTest(value = "DSL-1811", unresolved = true)
  public void testNoTypeMismatchedParameterValueProposals() {
    final StringBuilder builder = new StringBuilder(512);
    builder.append("check configuration Test {\n");
    builder.append("  catalog TestChecks {\n");
    builder.append("    default Test (\n");
    builder.append("      ").append(TestPropertySpecificationWithExpectedValues.INSTANCE.getName()).append(" = ").append(expectedExactly(TestPropertySpecificationWithExpectedValues.INSTANCE.getExpectedValues())).append("\"banana\"\n");
    builder.append("    )\n");
    builder.append("  }\n");
    builder.append("}\n");
    assertKernelSourceProposals("NoTypeMismatchedParameterValueProposals.checkcfg", builder);
  }
}
// CHECKSTYLE:CONSTANTS-ON
