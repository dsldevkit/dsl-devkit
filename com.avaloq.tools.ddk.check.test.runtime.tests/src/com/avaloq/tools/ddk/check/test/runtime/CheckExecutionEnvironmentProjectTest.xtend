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
package com.avaloq.tools.ddk.check.test.runtime

import com.avaloq.tools.ddk.check.TestLanguageUiInjectorProvider
import com.avaloq.tools.ddk.check.core.test.AbstractCheckTestCase
import com.avaloq.tools.ddk.check.testLanguage.Model
import com.avaloq.tools.ddk.check.testLanguage.TestLanguagePackage
import com.avaloq.tools.ddk.check.ui.internal.TestLanguageActivator
import com.avaloq.tools.ddk.check.validation.ExecutionEnvironmentIssueCodes
import com.avaloq.tools.ddk.check.validation.LibraryChecksIssueCodes
import com.google.inject.Inject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@InjectWith(typeof(TestLanguageUiInjectorProvider))
@RunWith(typeof(XtextRunner))
class CheckExecutionEnvironmentProjectTest extends AbstractCheckTestCase {

  @Inject
  ValidationTestHelper helper

  @Inject
  ParseHelper<Model> parser

  override getInjector() {
    TestLanguageActivator::instance.getInjector(TestLanguageActivator::COM_AVALOQ_TOOLS_DDK_CHECK_TESTLANGUAGE)
  }

  @Test
  def void testFranz() {
    val model = parser.parse("Hello Franz!")
    helper.assertError(model, TestLanguagePackage.Literals::GREETING, ExecutionEnvironmentIssueCodes::FRANZNAME)
  }

  @Test
  def void testFrans() {
    val model = parser.parse("Hello Frans!")
    helper.assertNoError(model, ExecutionEnvironmentIssueCodes::FRANZNAME)
    helper.assertNoError(model, ExecutionEnvironmentIssueCodes::NAMELENGTH)
  }

  @Test
  def void testGreetingNameIssue() {
    val model = parser.parse("Hello GreetingNameTooLong!")
    helper.assertError(model, TestLanguagePackage.Literals::GREETING, ExecutionEnvironmentIssueCodes::NAMELENGTH)
  }

  /*
   * Tests that both validations emerging from the Java validator as well as such emerging from the check based
   * validator appear. (Fixed by CheckCompositeEValidator).
   */
  @Test
  //@BugTest("AIG-709") // this plugin should be ACF independent
  def void testBugAig709() {
    val model = parser.parse("Hello GreetingNameTooLong!")
    helper.assertError(model, TestLanguagePackage.Literals::GREETING, ExecutionEnvironmentIssueCodes::NAMELENGTH)
    helper.assertWarning(model, TestLanguagePackage.Literals::GREETING, IssueCodesConstants::GREETING_NAME_PREFIX)
  }

  @Test
  def void testLibraryInjection() {
    val model = parser.parse("Hello Peter!");
    helper.assertWarning(model, TestLanguagePackage.Literals::GREETING, LibraryChecksIssueCodes::CHECK_CATALOG_IS_ACTIVE);
    helper.assertNoError(model, LibraryChecksIssueCodes::CACHE_DOESNT_WORK);
    helper.assertNoError(model, LibraryChecksIssueCodes::CACHE_INJECTION_FAILED);
    helper.assertNoError(model, LibraryChecksIssueCodes::FORMAL_PARAMETERS);
  }
}
