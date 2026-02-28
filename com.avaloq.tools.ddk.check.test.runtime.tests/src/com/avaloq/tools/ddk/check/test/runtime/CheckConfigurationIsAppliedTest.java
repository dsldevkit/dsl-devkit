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
package com.avaloq.tools.ddk.check.test.runtime;

import java.util.List;

import com.avaloq.tools.ddk.check.TestLanguageUiInjectorProvider;
import com.avaloq.tools.ddk.check.core.test.AbstractCheckTestCase;
import com.avaloq.tools.ddk.check.testLanguage.Model;
import com.avaloq.tools.ddk.check.testLanguage.TestLanguagePackage;
import com.avaloq.tools.ddk.check.ui.internal.TestLanguageActivator;
import com.avaloq.tools.ddk.check.validation.ExecutionEnvironmentIssueCodes;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@InjectWith(TestLanguageUiInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
public class CheckConfigurationIsAppliedTest extends AbstractCheckTestCase {

  @Inject
  private ValidationTestHelper helper;

  @Override
  public Injector getInjector() {
    return TestLanguageActivator.getInstance().getInjector(TestLanguageActivator.COM_AVALOQ_TOOLS_DDK_CHECK_TESTLANGUAGE);
  }

  /*
   * The model names are deliberately not including file extension, the actual resources
   * are also missing them. The reason for this is that if current development instance of
   * Eclipse has the Check runtime plugins installed, code will automatically be generated
   * for those resources. In order to avoid that the file extensions have been ommitted.
   */
  public List<String> getRequiredSourceFileNames() {
    return Lists.newArrayList(".settings/com.avaloq.tools.ddk.checkcfg.core.prefs", "Greetings");
  }

  /*
   * Tests that the check configuration copied to the .settings folder of the project is read
   * and applied: the severity is changed from ERROR to WARNING.
   */
  @Test
  public void testCheckConfigurationIsApplied() throws Exception {
    // sources are copied into the project and then built by the Xtext builder
    addSourcesToWorkspace(CheckConfigurationIsAppliedTest.class, getRequiredSourceFileNames());
    // wait for build to finish, otherwise included catalog may not be resolvable
    IResourcesSetupUtil.waitForBuild();
    final Model model = (Model) getModel("Greetings");
    helper.assertWarning(model.getGreetings().get(0), TestLanguagePackage.Literals.GREETING, ExecutionEnvironmentIssueCodes.FRANZNAME);
  }

}
