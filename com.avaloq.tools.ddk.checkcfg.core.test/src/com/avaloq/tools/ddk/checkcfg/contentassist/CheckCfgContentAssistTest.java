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

import static com.avaloq.tools.ddk.checkcfg.CheckCfgConstants.PROPERTY_EXECUTABLE_EXTENSION_ATTRIBUTE;
import static com.avaloq.tools.ddk.checkcfg.CheckCfgConstants.PROPERTY_EXTENSION_POINT;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.checkcfg.CheckCfgUiInjectorProvider;
import com.avaloq.tools.ddk.checkcfg.util.CheckCfgTestUtil;
import com.avaloq.tools.ddk.test.checkcfg.TestPropertySpecificationWithExpectedValues;
import com.avaloq.tools.ddk.test.checkcfg.TestPropertySpecificationWithOutExpectedValues;
import com.avaloq.tools.ddk.test.core.jupiter.BugTest;
import com.avaloq.tools.ddk.test.core.mock.ExtensionRegistryMock;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractAcfContentAssistTest;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTestUtil;

@ExtendWith(InjectionExtension.class)
@InjectWith(CheckCfgUiInjectorProvider.class)
public class CheckCfgContentAssistTest extends AbstractAcfContentAssistTest {

  private static final String SOURCE_TEMPLATE = """
      check configuration Test {
        catalog TestChecks {
          default Test (
            %s = %s"banana"
          )
        }
      }
      """;

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
  public void testConfiguredParameterProposals() {
  // CPD-ON
    final String source = SOURCE_TEMPLATE.formatted(TestPropertySpecificationWithExpectedValues.INSTANCE.getName(), expected(TestPropertySpecificationWithExpectedValues.INSTANCE.getExpectedValues()));
    assertKernelSourceProposals("ConfiguredParameterProposals.checkcfg", source);
  }

  @BugTest(value = "DSL-1811", unresolved = true)
  public void testNoTypeMismatchedParameterValueProposals() {
    final String source = SOURCE_TEMPLATE.formatted(TestPropertySpecificationWithExpectedValues.INSTANCE.getName(), expectedExactly(TestPropertySpecificationWithExpectedValues.INSTANCE.getExpectedValues()));
    assertKernelSourceProposals("NoTypeMismatchedParameterValueProposals.checkcfg", source);
  }
}
