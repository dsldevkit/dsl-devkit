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
package com.avaloq.tools.ddk.sample.helloworld.check;

import com.avaloq.tools.ddk.check.core.test.AbstractCheckTestCase;
import com.avaloq.tools.ddk.sample.helloworld.helloWorld.HelloWorldPackage;
import com.avaloq.tools.ddk.sample.helloworld.helloWorld.Model;
import com.avaloq.tools.ddk.sample.helloworld.ui.HelloWorldUiInjectorProvider;
import com.avaloq.tools.ddk.sample.helloworld.ui.internal.HelloworldActivator;
import com.avaloq.tools.ddk.sample.helloworld.validation.ExecutionEnvironmentIssueCodes;
import com.avaloq.tools.ddk.sample.helloworld.validation.IssueCodes;
import com.avaloq.tools.ddk.sample.helloworld.validation.LibraryChecksIssueCodes;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@InjectWith(HelloWorldUiInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
public class CheckExecutionEnvironmentProjectTest extends AbstractCheckTestCase {

  @Inject
  private ValidationTestHelper helper;

  @Inject
  private ParseHelper<Model> parser;

  @Override
  protected Injector getInjector() {
    return HelloworldActivator.getInstance().getInjector(HelloworldActivator.COM_AVALOQ_TOOLS_DDK_SAMPLE_HELLOWORLD_HELLOWORLD);
  }

  @Test
  public void testFranz() throws Exception {
    final Model model = parser.parse("Hello Franz!");
    helper.assertError(model, HelloWorldPackage.Literals.GREETING, ExecutionEnvironmentIssueCodes.FRANZNAME);
  }

  @Test
  public void testFrans() throws Exception {
    final Model model = parser.parse("Hello Frans!");
    helper.assertNoError(model, ExecutionEnvironmentIssueCodes.FRANZNAME);
    helper.assertNoError(model, ExecutionEnvironmentIssueCodes.NAMELENGTH);
  }

  @Test
  public void testGreetingNameIssue() throws Exception {
    final Model model = parser.parse("Hello GreetingNameTooLong!");
    helper.assertError(model, HelloWorldPackage.Literals.GREETING, ExecutionEnvironmentIssueCodes.NAMELENGTH);
  }

  /*
   * Tests that both validations emerging from the Java validator as well as such emerging from the check based
   * validator appear. (Fixed by CheckCompositeEValidator).
   */
  @Test
  //@BugTest("AIG-709") // this plugin should be ACF independent
  public void testBugAig709() throws Exception {
    final Model model = parser.parse("Hello GreetingNameTooLong!");
    helper.assertError(model, HelloWorldPackage.Literals.GREETING, ExecutionEnvironmentIssueCodes.NAMELENGTH);
    helper.assertWarning(model, HelloWorldPackage.Literals.GREETING, IssueCodes.GREETING_NAME_PREFIX);
  }

  @Test
  public void testLibraryInjection() throws Exception {
    final Model model = parser.parse("Hello Peter!");
    helper.assertWarning(model, HelloWorldPackage.Literals.GREETING, LibraryChecksIssueCodes.CHECK_CATALOG_IS_ACTIVE);
    helper.assertNoError(model, LibraryChecksIssueCodes.CACHE_DOESNT_WORK);
    helper.assertNoError(model, LibraryChecksIssueCodes.CACHE_INJECTION_FAILED);
    helper.assertNoError(model, LibraryChecksIssueCodes.FORMAL_PARAMETERS);
  }
}
