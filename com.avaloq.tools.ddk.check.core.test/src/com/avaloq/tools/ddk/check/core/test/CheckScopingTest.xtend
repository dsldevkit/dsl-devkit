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
package com.avaloq.tools.ddk.check.core.test

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider
import com.avaloq.tools.ddk.check.check.Check
import com.avaloq.tools.ddk.check.check.CheckCatalog
import com.avaloq.tools.ddk.check.check.Implementation
import com.avaloq.tools.ddk.check.check.XIssueExpression
import com.avaloq.tools.ddk.check.core.test.util.CheckTestUtil
import com.google.common.collect.Lists
import com.google.inject.Inject
import java.util.List
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil
import org.junit.Test
import org.junit.runner.RunWith

@InjectWith(typeof(CheckUiInjectorProvider))
@RunWith(typeof(XtextRunner))
class CheckScopingTest extends AbstractCheckTestCase {

  @Inject CheckTestUtil util

  /*
   * The model names are deliberately not including file extension, the actual resources
   * are also missing them. The reason for this is that if current development instance of
   * Eclipse has the Check runtime plugins installed, code will automatically be generated
   * for those resources. In order to avoid that the file extensions have been ommitted.
   */
  def List<String> getRequiredSourceFileNames() {
    Lists::newArrayList("CommonChecks", "SampleChecks")
  }

  def void initializeTestProject() {
    // sources are copied into the project and then built by the Xtext builder
    addSourcesToWorkspace(typeof(CheckScopingTest), requiredSourceFileNames)

    // wait for build to finish, otherwise included catalog may not be resolvable
    IResourcesSetupUtil.reallyWaitForAutoBuild
  }

  /*
   * Tests that a catalog may not reference checks (in implementations, 'def') which are
   * neither local nor included.
   */
  @Test
  def void testIllegalDirectionOfReference() {
    initializeTestProject

    // test that our model is available
    val model = getModel("CommonChecks") as CheckCatalog

    val illegalRefImpl = util.getFirstInstanceOf(model, typeof(Implementation))
    val issueExpr      = util.getFirstInstanceOf(illegalRefImpl, typeof(XIssueExpression))

    assertTrue("Referenced check cannot be resolved", issueExpr.check.eIsProxy)
    assertNull("Referenced check name is null", issueExpr.check.name)
  }

  /*
   * Tests that a check may be documented using a ML_COMMENT. The documentation is inferred
   * in the description field of a check.
   */
  @Test
  def void testCheckDescriptionIsInferred() {
    initializeTestProject
    val check = util.getFirstInstanceOf(getModel("CommonChecks"), typeof(Check))
    assertEquals("Referenced check cannot be resolved", "This check is javadoc-like commented.", check.description)
  }
}
