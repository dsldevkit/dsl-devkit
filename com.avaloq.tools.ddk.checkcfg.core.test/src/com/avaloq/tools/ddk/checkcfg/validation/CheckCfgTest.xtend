/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.checkcfg.validation

import com.avaloq.tools.ddk.checkcfg.CheckCfgUiInjectorProvider
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage
import com.google.inject.Inject
import junit.framework.TestCase
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@InjectWith(CheckCfgUiInjectorProvider)
@RunWith(XtextRunner)
class CheckCfgTest extends TestCase {


  @Inject
  private ValidationTestHelper helper;

  @Inject
  private ParseHelper<CheckConfiguration> parser;

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
