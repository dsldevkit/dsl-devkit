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
package com.avaloq.tools.ddk.checkcfg.validation;

import com.avaloq.tools.ddk.checkcfg.CheckCfgConstants;
import com.avaloq.tools.ddk.checkcfg.util.CheckCfgTestUtil;
import com.avaloq.tools.ddk.checkcfg.validation.IssueCodes;
import com.avaloq.tools.ddk.test.checkcfg.TestPropertySpecificationWithExpectedValues;
import com.avaloq.tools.ddk.test.checkcfg.TestPropertySpecificationWithOutExpectedValues;
import com.avaloq.tools.ddk.test.core.mock.ExtensionRegistryMock;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextTestUtil;
import com.avaloq.tools.ddk.xtext.test.validation.AbstractValidationTest;
import com.google.common.collect.Lists;
import java.util.List;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Test;

@SuppressWarnings("all")
public class CheckCfgConfiguredParameterValidationsTest extends AbstractValidationTest {
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
  public void testConfiguredParameterValues() {
    final TestPropertySpecificationWithExpectedValues allowedOnly = TestPropertySpecificationWithExpectedValues.INSTANCE;
    final TestPropertySpecificationWithOutExpectedValues acceptsAny = TestPropertySpecificationWithOutExpectedValues.INSTANCE;
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("check configuration Test");
    _builder.newLine();
    _builder.append("  ");
    String _name = allowedOnly.getName();
    _builder.append(_name, "  ");
    _builder.append(" = ");
    String _error = this.error(IssueCodes.PARAMETER_VALUE_NOT_ALLOWED);
    _builder.append(_error, "  ");
    _builder.append("\"notAllowed\"");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("for com.avaloq.tools.ddk.^check.TestLanguage {");
    _builder.newLine();
    _builder.append("      ");
    String _name_1 = allowedOnly.getName();
    _builder.append(_name_1, "      ");
    _builder.append(" = ");
    String _noDiagnostic = this.noDiagnostic(IssueCodes.PARAMETER_VALUE_NOT_ALLOWED);
    _builder.append(_noDiagnostic, "      ");
    _builder.append("\"");
    String[] _expectedValues = allowedOnly.getExpectedValues();
    String _head = IterableExtensions.<String>head(((Iterable<String>)Conversions.doWrapArray(_expectedValues)));
    _builder.append(_head, "      ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    String _name_2 = acceptsAny.getName();
    _builder.append(_name_2, "      ");
    _builder.append(" = ");
    String _noDiagnostic_1 = this.noDiagnostic(IssueCodes.PARAMETER_VALUE_NOT_ALLOWED);
    _builder.append(_noDiagnostic_1, "      ");
    _builder.append("\"whatever\"");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    this.validateKernelSourceStrictly("ConfiguredParameterValues.checkcfg", _builder);
  }
}
