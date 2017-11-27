/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.checkcfg.contentassist;

import com.avaloq.tools.ddk.checkcfg.CheckCfgConstants;
import com.avaloq.tools.ddk.checkcfg.util.CheckCfgTestUtil;
import com.avaloq.tools.ddk.test.checkcfg.TestPropertySpecificationWithExpectedValues;
import com.avaloq.tools.ddk.test.checkcfg.TestPropertySpecificationWithOutExpectedValues;
import com.avaloq.tools.ddk.test.core.BugTest;
import com.avaloq.tools.ddk.test.core.mock.ExtensionRegistryMock;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextTestUtil;
import com.avaloq.tools.ddk.xtext.test.contentassist.AbstractAcfContentAssistTest;
import com.google.common.collect.Lists;
import java.util.List;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.junit.Test;

@SuppressWarnings("all")
public class CheckCfgContentAssistTest extends AbstractAcfContentAssistTest {
  @Override
  protected AbstractXtextTestUtil getXtextTestUtil() {
    return CheckCfgTestUtil.getInstance();
  }
  
  @Override
  protected List<String> getRequiredSourceFileNames() {
    return Lists.<String>newArrayListWithCapacity(0);
  }
  
  @Override
  protected void beforeAllTests() {
    IConfigurationElement _mockConfigurationElement = ExtensionRegistryMock.mockConfigurationElement(CheckCfgConstants.PROPERTY_EXTENSION_POINT);
    ExtensionRegistryMock.mockExecutableExtension(_mockConfigurationElement, CheckCfgConstants.PROPERTY_EXECUTABLE_EXTENSION_ATTRIBUTE, TestPropertySpecificationWithExpectedValues.INSTANCE);
    IConfigurationElement _mockConfigurationElement_1 = ExtensionRegistryMock.mockConfigurationElement(CheckCfgConstants.PROPERTY_EXTENSION_POINT);
    ExtensionRegistryMock.mockExecutableExtension(_mockConfigurationElement_1, CheckCfgConstants.PROPERTY_EXECUTABLE_EXTENSION_ATTRIBUTE, TestPropertySpecificationWithOutExpectedValues.INSTANCE);
    super.beforeAllTests();
  }
  
  @Override
  protected void afterAllTests() {
    super.afterAllTests();
    ExtensionRegistryMock.unMock(CheckCfgConstants.PROPERTY_EXTENSION_POINT);
  }
  
  @Test
  public void testConfiguredParameterProposals() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("check configuration Test {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("catalog TestChecks {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("default Test (");
    _builder.newLine();
    _builder.append("      ");
    String _name = TestPropertySpecificationWithExpectedValues.INSTANCE.getName();
    _builder.append(_name, "      ");
    _builder.append(" = ");
    String[] _expectedValues = TestPropertySpecificationWithExpectedValues.INSTANCE.getExpectedValues();
    String _expected = this.expected(_expectedValues);
    _builder.append(_expected, "      ");
    _builder.append("\"banana\"");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append(")");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.assertKernelSourceProposals("ConfiguredParameterProposals.checkcfg", _builder);
  }
  
  @BugTest(value = "DSL-1811", unresolved = true)
  public void testNoTypeMismatchedParameterValueProposals() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("check configuration Test {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("catalog TestChecks {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("default Test (");
    _builder.newLine();
    _builder.append("      ");
    String _name = TestPropertySpecificationWithExpectedValues.INSTANCE.getName();
    _builder.append(_name, "      ");
    _builder.append(" = ");
    String[] _expectedValues = TestPropertySpecificationWithExpectedValues.INSTANCE.getExpectedValues();
    String _expectedExactly = this.expectedExactly(_expectedValues);
    _builder.append(_expectedExactly, "      ");
    _builder.append("\"banana\"");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append(")");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.assertKernelSourceProposals("NoTypeMismatchedParameterValueProposals.checkcfg", _builder);
  }
}
