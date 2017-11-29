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
import com.avaloq.tools.ddk.check.testLanguage.Greeting;
import com.avaloq.tools.ddk.check.testLanguage.Model;
import com.avaloq.tools.ddk.check.testLanguage.TestLanguagePackage;
import com.avaloq.tools.ddk.check.ui.internal.TestLanguageActivator;
import com.avaloq.tools.ddk.check.validation.ExecutionEnvironmentIssueCodes;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Injector;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.Test;
import org.junit.runner.RunWith;

@InjectWith(TestLanguageUiInjectorProvider.class)
@RunWith(XtextRunner.class)
@SuppressWarnings("all")
public class CheckConfigurationIsAppliedTest extends AbstractCheckTestCase {
  @Inject
  private ValidationTestHelper helper;
  
  @Override
  public Injector getInjector() {
    TestLanguageActivator _instance = TestLanguageActivator.getInstance();
    return _instance.getInjector(TestLanguageActivator.COM_AVALOQ_TOOLS_DDK_CHECK_TESTLANGUAGE);
  }
  
  /**
   * The model names are deliberately not including file extension, the actual resources
   * are also missing them. The reason for this is that if current development instance of
   * Eclipse has the Check runtime plugins installed, code will automatically be generated
   * for those resources. In order to avoid that the file extensions have been ommitted.
   */
  public List<String> getRequiredSourceFileNames() {
    return Lists.<String>newArrayList(".settings/com.avaloq.tools.ddk.checkcfg.core.prefs", "Greetings");
  }
  
  /**
   * Tests that the check configuration copied to the .settings folder of the project is read
   * and applied: the severity is changed from ERROR to WARNING.
   */
  @Test
  public void testCheckConfigurationIsApplied() {
    try {
      List<String> _requiredSourceFileNames = this.getRequiredSourceFileNames();
      this.addSourcesToWorkspace(CheckConfigurationIsAppliedTest.class, _requiredSourceFileNames);
      IResourcesSetupUtil.waitForAutoBuild();
      EObject _model = this.getModel("Greetings");
      final Model model = ((Model) _model);
      EList<Greeting> _greetings = model.getGreetings();
      Greeting _get = _greetings.get(0);
      this.helper.assertWarning(_get, TestLanguagePackage.Literals.GREETING, ExecutionEnvironmentIssueCodes.FRANZNAME);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
