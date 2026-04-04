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
@SuppressWarnings("nls")
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
    final String checkSource = """
        package checkcfgtest

        import com.avaloq.tools.ddk.check.check.Check

        catalog CheckCfgTestChecks
        for grammar com.avaloq.tools.ddk.check.Check {
          /**
           * Test Error Documentation
           */
          live error TestError "Test Error"
          message "Test Error message." {
            for Check c {
              issue on c#name;
            }
          }
        }
        """;
    addCustomerSourceToWorkspace("customer$sca_testchecks.check", checkSource);
    IResourcesSetupUtil.waitForBuild();
  }

  @Test
  public void testSyntax() {
    final String checkcfgSource = """
        check configuration checkconfiguration {
          catalog checkcfgtest.CheckCfgTestChecks {
            default TestError
          }
        }
        """;
    validateCustomerSourceStrictly("checkconfiguration.checkcfg", checkcfgSource);
  }

  @Test
  public void testSyntaxConfiguredLanguage() {
    final String checkcfgSource = """
        check configuration checkconfiguration
          for com.avaloq.tools.ddk.^check.TestLanguage {
            catalog checkcfgtest.CheckCfgTestChecks {
              default TestError
            }
          }
        """;
    validateCustomerSourceStrictly("checkconfiguration.checkcfg", checkcfgSource);
  }

  @Test
  public void testPropertiesOnAllLevels() {
    final String checkcfgSource = """
        check configuration checkconfiguration
          integrationRelevant = true
          testBooleanList = #[true, false, false]

          for com.avaloq.tools.ddk.^check.TestLanguage {
          nameOverrides = #['altName1', 'altName2']

            catalog checkcfgtest.CheckCfgTestChecks {
            default TestError(testNumber = 3, testNumberList = #[1, 2, 3])
            }
          }
        """;
    validateCustomerSourceStrictly("checkconfiguration.checkcfg", checkcfgSource);
  }
}
// CHECKSTYLE:CONSTANTS-ON
