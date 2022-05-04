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
package com.avaloq.tools.ddk.sample.helloworld.check

import com.avaloq.tools.ddk.check.core.test.AbstractCheckTestCase
import com.google.common.collect.Lists
import com.google.inject.Inject
import java.util.List
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil
import org.junit.Test
import org.junit.runner.RunWith
import com.avaloq.tools.ddk.sample.helloworld.helloWorld.Model
import com.avaloq.tools.ddk.sample.helloworld.ui.internal.HelloworldActivator
import com.avaloq.tools.ddk.sample.helloworld.helloWorld.HelloWorldPackage
import com.avaloq.tools.ddk.sample.helloworld.validation.ExecutionEnvironmentIssueCodes
import com.avaloq.tools.ddk.sample.helloworld.ui.HelloWorldUiInjectorProvider

@InjectWith(typeof(HelloWorldUiInjectorProvider))
@RunWith(XtextRunner)
class CheckConfigurationIsAppliedTest extends AbstractCheckTestCase {

  @Inject
  ValidationTestHelper helper

  override getInjector() {
    HelloworldActivator::instance.getInjector(HelloworldActivator::COM_AVALOQ_TOOLS_DDK_SAMPLE_HELLOWORLD_HELLOWORLD)
  }

  /*
   * The model names are deliberately not including file extension, the actual resources
   * are also missing them. The reason for this is that if current development instance of
   * Eclipse has the Check runtime plugins installed, code will automatically be generated
   * for those resources. In order to avoid that the file extensions have been ommitted.
   */
  def List<String> getRequiredSourceFileNames() {
    Lists::newArrayList('.settings/com.avaloq.tools.ddk.checkcfg.core.prefs', 'Greetings')
  }

  /*
   * Tests that the check configuration copied to the .settings folder of the project is read
   * and applied: the severity is changed from ERROR to WARNING.
   */
  @Test
  def void testCheckConfigurationIsApplied() {
    // sources are copied into the project and then built by the Xtext builder
    addSourcesToWorkspace(typeof(CheckConfigurationIsAppliedTest), requiredSourceFileNames)
    // wait for build to finish, otherwise included catalog may not be resolvable
    IResourcesSetupUtil::waitForBuild
    val model = getModel("Greetings") as Model
    helper.assertWarning(model.greetings.get(0), HelloWorldPackage$Literals::GREETING, ExecutionEnvironmentIssueCodes::FRANZNAME)
  }

}
