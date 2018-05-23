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

import org.junit.Test
import com.google.inject.Inject
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.eclipse.xtext.junit4.util.ParseHelper
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration
import org.eclipse.xtext.junit4.InjectWith
import org.junit.runner.RunWith
import com.avaloq.tools.ddk.checkcfg.CheckCfgUiInjectorProvider
import org.eclipse.xtext.junit4.XtextRunner
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage

@InjectWith(CheckCfgUiInjectorProvider)
@RunWith(XtextRunner)
class CheckCfgDuplicateChecksTest{

  @Inject
  private ValidationTestHelper helper;

  @Inject
  private ParseHelper<CheckConfiguration> parser;
  
  /**
   * Test for validation of duplicate language configurations.
   */
  @Test
  def testDuplicateLanguageError() {
  	val model = parser.parse('''
      check configuration DuplicateLanguages
        for com.avaloq.tools.ddk.^check.TestLanguage {}
        for com.avaloq.tools.ddk.^check.TestLanguage {}
      }'''
    )
    helper.assertError(model, CheckcfgPackage::Literals::CONFIGURED_LANGUAGE_VALIDATOR, IssueCodes::DUPLICATE_LANGUAGE_CONFIGURATION)
  }
  
  /**
   * Test for validation of duplicate catalog configurations.
   */
  @Test
  def testDuplicateCatalogError() {
  	val model = parser.parse('''
      check configuration DuplicateCatalogs {
        catalog com.avaloq.tools.ddk.^check.validation.ExecutionEnvironment {}
        catalog com.avaloq.tools.ddk.^check.validation.ExecutionEnvironment {}
      }'''
    )
    helper.assertError(model, CheckcfgPackage::Literals::CONFIGURED_CATALOG, IssueCodes::DUPLICATE_CATALOG_CONFIGURATION)
  }
	
  /**
   * Test for validation of duplicate check configurations.
   */
  @Test
  def testDuplicateCheckError() {
  	val model = parser.parse('''
      check configuration DuplicateChecks {
        catalog com.avaloq.tools.ddk.^check.validation.ExecutionEnvironment {
            default GreetingNameLength
            default GreetingNameLength
          }
      }'''
    )
    helper.assertError(model, CheckcfgPackage::Literals::CONFIGURED_CHECK, IssueCodes::DUPLICATE_CHECK_CONFIGURATION)
  }
}
