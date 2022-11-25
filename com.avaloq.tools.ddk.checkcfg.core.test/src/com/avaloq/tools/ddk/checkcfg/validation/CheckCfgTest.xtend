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
package com.avaloq.tools.ddk.checkcfg.validation

import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage
import com.google.inject.Inject
import junit.framework.TestCase
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import org.eclipse.xtext.testing.InjectWith
import com.avaloq.tools.ddk.checkcfg.CheckCfgUiInjectorProvider

@InjectWith(typeof(CheckCfgUiInjectorProvider))
@RunWith(XtextRunner)
class CheckCfgTest extends TestCase {


  @Inject
  ValidationTestHelper helper;

  @Inject
  ParseHelper<CheckConfiguration> parser;

  @Test
  def testValidLanguageOk() {

    val model = parser.parse('''
    check configuration Test

    for com.avaloq.tools.ddk.^check.TestLanguage {

    }

    ''');
    helper.assertNoIssues(model);
  }

  @Test
  def testUnknownLanguageNotOk() {

    val model = parser.parse('''
    check configuration Test

    for com.avaloq.tools.ddk.^check.Unknown {

    }

    ''');
    helper.assertError(model, CheckcfgPackage.Literals::CONFIGURED_LANGUAGE_VALIDATOR, IssueCodes.UNKNOWN_LANGUAGE);
  }

}
