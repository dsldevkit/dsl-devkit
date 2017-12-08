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
package com.avaloq.tools.ddk.checkcfg.syntax;

import com.avaloq.tools.ddk.checkcfg.util.CheckCfgTestUtil;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextTestUtil;
import com.avaloq.tools.ddk.xtext.test.validation.AbstractValidationTest;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil;
import org.junit.Test;

@SuppressWarnings("all")
public class CheckCfgSyntaxTest extends AbstractValidationTest {
  @Override
  protected AbstractXtextTestUtil getXtextTestUtil() {
    return CheckCfgTestUtil.getInstance();
  }
  
  @Override
  protected List<String> getRequiredSourceFileNames() {
    return new LinkedList<String>();
  }
  
  @Test
  public void testSyntax() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package checkcfgtest");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.avaloq.tools.ddk.check.check.Check");
    _builder.newLine();
    _builder.newLine();
    _builder.append("catalog CheckCfgTestChecks");
    _builder.newLine();
    _builder.append("for grammar com.avaloq.tools.ddk.check.Check {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("* Test Error Documentation");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("live error TestError \"Test Error\"");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("message \"Test Error message.\" {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("for Check c {");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("issue on c#name;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final String checkSource = _builder.toString();
    this.addCustomerSourceToWorkspace("customer$sca_testchecks.check", checkSource);
    IResourcesSetupUtil.waitForAutoBuild();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("check configuration checkconfiguration {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("catalog checkcfgtest.CheckCfgTestChecks {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("default TestError");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    final String checkcfgSource = _builder_1.toString();
    this.validateCustomerSourceStrictly("checkconfiguration.checkcfg", checkcfgSource);
  }
  
  @Test
  public void testSyntaxConfiguredLanguage() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("check configuration checkconfiguration");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("for com.avaloq.tools.ddk.^check.TestLanguage {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("catalog checkcfgtest.CheckCfgTestChecks {");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("default TestError");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    final String checkcfgSource = _builder.toString();
    this.validateCustomerSourceStrictly("checkconfiguration.checkcfg", checkcfgSource);
  }
  
  @Test
  public void testPropertiesOnAllLevels() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("check configuration checkconfiguration");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("integrationRelevant = true");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("testBooleanList = #[true, false, false]");
    _builder.newLine();
    _builder.newLine();
    _builder.append("  ");
    _builder.append("for com.avaloq.tools.ddk.^check.TestLanguage {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("nameOverrides = #[\'altName1\', \'altName2\']");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("catalog checkcfgtest.CheckCfgTestChecks {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("default TestError(testNumber = 3, testNumberList = #[1, 2, 3])");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    final String checkcfgSource = _builder.toString();
    this.validateCustomerSourceStrictly("checkconfiguration.checkcfg", checkcfgSource);
  }
}
