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
package com.avaloq.tools.ddk.check.test.runtime;

import com.avaloq.tools.ddk.check.TestLanguageUiInjectorProvider;
import com.avaloq.tools.ddk.check.core.test.AbstractCheckTestCase;
import com.avaloq.tools.ddk.check.testLanguage.Model;
import com.avaloq.tools.ddk.check.testLanguage.TestLanguagePackage;
import com.avaloq.tools.ddk.check.ui.internal.TestLanguageActivator;
import com.avaloq.tools.ddk.check.validation.ExecutionEnvironmentIssueCodes;
import com.avaloq.tools.ddk.check.validation.IssueCodes;
import com.avaloq.tools.ddk.check.validation.LibraryChecksIssueCodes;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.Test;
import org.junit.runner.RunWith;

@InjectWith(TestLanguageUiInjectorProvider.class)
@RunWith(XtextRunner.class)
@SuppressWarnings("all")
public class CheckExecutionEnvironmentProjectTest extends AbstractCheckTestCase {
  @Inject
  private ValidationTestHelper helper;
  
  @Inject
  private ParseHelper<Model> parser;
  
  @Override
  public Injector getInjector() {
    TestLanguageActivator _instance = TestLanguageActivator.getInstance();
    return _instance.getInjector(TestLanguageActivator.COM_AVALOQ_TOOLS_DDK_CHECK_TESTLANGUAGE);
  }
  
  @Test
  public void testFranz() {
    try {
      final Model model = this.parser.parse("Hello Franz!");
      this.helper.assertError(model, TestLanguagePackage.Literals.GREETING, ExecutionEnvironmentIssueCodes.FRANZNAME);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testFrans() {
    try {
      final Model model = this.parser.parse("Hello Frans!");
      this.helper.assertNoError(model, ExecutionEnvironmentIssueCodes.FRANZNAME);
      this.helper.assertNoError(model, ExecutionEnvironmentIssueCodes.NAMELENGTH);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testGreetingNameIssue() {
    try {
      final Model model = this.parser.parse("Hello GreetingNameTooLong!");
      this.helper.assertError(model, TestLanguagePackage.Literals.GREETING, ExecutionEnvironmentIssueCodes.NAMELENGTH);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests that both validations emerging from the Java validator as well as such emerging from the check based
   * validator appear. (Fixed by CheckCompositeEValidator).
   */
  @Test
  public void testBugAig709() {
    try {
      final Model model = this.parser.parse("Hello GreetingNameTooLong!");
      this.helper.assertError(model, TestLanguagePackage.Literals.GREETING, ExecutionEnvironmentIssueCodes.NAMELENGTH);
      this.helper.assertWarning(model, TestLanguagePackage.Literals.GREETING, IssueCodes.GREETING_NAME_PREFIX);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testLibraryInjection() {
    try {
      final Model model = this.parser.parse("Hello Peter!");
      this.helper.assertWarning(model, TestLanguagePackage.Literals.GREETING, LibraryChecksIssueCodes.CHECK_CATALOG_IS_ACTIVE);
      this.helper.assertNoError(model, LibraryChecksIssueCodes.CACHE_DOESNT_WORK);
      this.helper.assertNoError(model, LibraryChecksIssueCodes.CACHE_INJECTION_FAILED);
      this.helper.assertNoError(model, LibraryChecksIssueCodes.FORMAL_PARAMETERS);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
