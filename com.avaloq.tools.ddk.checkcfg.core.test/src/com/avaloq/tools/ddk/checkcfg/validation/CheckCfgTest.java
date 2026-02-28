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

import com.avaloq.tools.ddk.checkcfg.CheckCfgUiInjectorProvider;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
import com.google.inject.Inject;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@InjectWith(CheckCfgUiInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
public class CheckCfgTest {

  @Inject
  private ValidationTestHelper helper;

  @Inject
  private ParseHelper<CheckConfiguration> parser;

  @Test
  public void testValidLanguageOk() throws Exception {
    final CheckConfiguration model = parser.parse(
        "check configuration Test\n"
        + "\n"
        + "for com.avaloq.tools.ddk.^check.TestLanguage {\n"
        + "\n"
        + "}\n"
        + "\n");
    helper.assertNoIssues(model);
  }

  @Test
  public void testUnknownLanguageNotOk() throws Exception {
    final CheckConfiguration model = parser.parse(
        "check configuration Test\n"
        + "\n"
        + "for com.avaloq.tools.ddk.^check.Unknown {\n"
        + "\n"
        + "}\n"
        + "\n");
    helper.assertError(model, CheckcfgPackage.Literals.CONFIGURED_LANGUAGE_VALIDATOR, IssueCodes.UNKNOWN_LANGUAGE);
  }

}
