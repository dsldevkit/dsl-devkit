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

package com.avaloq.tools.ddk.checkcfg.syntax;

import com.avaloq.tools.ddk.checkcfg.util.CheckCfgTestUtil;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractValidationTest;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTestUtil;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

// CHECKSTYLE:CONSTANTS-OFF
public class CheckCfgSyntaxTest extends AbstractValidationTest {

  @Override
  protected AbstractXtextTestUtil getXtextTestUtil() {
    return CheckCfgTestUtil.getInstance();
  }

  @Override
  protected List<String> getRequiredSourceFileNames() {
    return new LinkedList<String>();
  }

  @BeforeAll
  public void setup() {
    final StringBuilder builder = new StringBuilder(512);
    builder.append("package checkcfgtest\n");
    builder.append('\n');
    builder.append("import com.avaloq.tools.ddk.check.check.Check\n");
    builder.append('\n');
    builder.append("catalog CheckCfgTestChecks\n");
    builder.append("for grammar com.avaloq.tools.ddk.check.Check {\n");
    builder.append("  /**\n");
    builder.append("   * Test Error Documentation\n");
    builder.append("   */\n");
    builder.append("  live error TestError \"Test Error\"\n");
    builder.append("  message \"Test Error message.\" {\n");
    builder.append("    for Check c {\n");
    builder.append("      issue on c#name;\n");
    builder.append("    }\n");
    builder.append("  }\n");
    builder.append("}\n");
    final String checkSource = builder.toString();
    addCustomerSourceToWorkspace("customer$sca_testchecks.check", checkSource);
    IResourcesSetupUtil.waitForBuild();
  }

  @Test
  public void testSyntax() {
    final StringBuilder builder = new StringBuilder(256);
    builder.append("check configuration checkconfiguration {\n");
    builder.append("  catalog checkcfgtest.CheckCfgTestChecks {\n");
    builder.append("    default TestError\n");
    builder.append("  }\n");
    builder.append("}\n");
    final String checkcfgSource = builder.toString();
    validateCustomerSourceStrictly("checkconfiguration.checkcfg", checkcfgSource);
  }

  @Test
  public void testSyntaxConfiguredLanguage() {
    final StringBuilder builder = new StringBuilder(256);
    builder.append("check configuration checkconfiguration\n");
    builder.append("  for com.avaloq.tools.ddk.^check.TestLanguage {\n");
    builder.append("    catalog checkcfgtest.CheckCfgTestChecks {\n");
    builder.append("      default TestError\n");
    builder.append("    }\n");
    builder.append("  }\n");
    final String checkcfgSource = builder.toString();
    validateCustomerSourceStrictly("checkconfiguration.checkcfg", checkcfgSource);
  }

  @Test
  public void testPropertiesOnAllLevels() {
    final StringBuilder builder = new StringBuilder(512);
    builder.append("check configuration checkconfiguration\n");
    builder.append("  integrationRelevant = true\n");
    builder.append("  testBooleanList = #[true, false, false]\n");
    builder.append('\n');
    builder.append("  for com.avaloq.tools.ddk.^check.TestLanguage {\n");
    builder.append("  nameOverrides = #['altName1', 'altName2']\n");
    builder.append('\n');
    builder.append("    catalog checkcfgtest.CheckCfgTestChecks {\n");
    builder.append("    default TestError(testNumber = 3, testNumberList = #[1, 2, 3])\n");
    builder.append("    }\n");
    builder.append("  }\n");
    final String checkcfgSource = builder.toString();
    validateCustomerSourceStrictly("checkconfiguration.checkcfg", checkcfgSource);
  }
}
// CHECKSTYLE:CONSTANTS-ON
