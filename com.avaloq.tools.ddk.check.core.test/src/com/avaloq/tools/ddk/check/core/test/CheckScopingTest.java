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
package com.avaloq.tools.ddk.check.core.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.Implementation;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.avaloq.tools.ddk.check.core.test.util.CheckTestUtil;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import java.util.List;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@InjectWith(CheckUiInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
public class CheckScopingTest extends AbstractCheckTestCase {

  @Inject
  private CheckTestUtil util;

  /*
   * The model names are deliberately not including file extension, the actual resources
   * are also missing them. The reason for this is that if current development instance of
   * Eclipse has the Check runtime plugins installed, code will automatically be generated
   * for those resources. In order to avoid that the file extensions have been omitted.
   */
  public List<String> getRequiredSourceFileNames() {
    return Lists.newArrayList("CommonChecks", "SampleChecks");
  }

  public void initializeTestProject() {
    // sources are copied into the project and then built by the Xtext builder
    addSourcesToWorkspace(CheckScopingTest.class, getRequiredSourceFileNames());
    // wait for build to finish, otherwise included catalog may not be resolvable
    IResourcesSetupUtil.waitForBuild();
  }

  /*
   * Tests that a catalog may not reference checks (in implementations, 'def') which are
   * neither local nor included.
   */
  @Test
  public void testIllegalDirectionOfReference() throws Exception {
    initializeTestProject();

    // test that our model is available
    final CheckCatalog model = (CheckCatalog) getModel("CommonChecks");

    final Implementation illegalRefImpl = util.getFirstInstanceOf(model, Implementation.class);
    final XIssueExpression issueExpr = util.getFirstInstanceOf(illegalRefImpl, XIssueExpression.class);

    assertTrue(issueExpr.getCheck().eIsProxy(), "Referenced check cannot be resolved");
    assertNull(issueExpr.getCheck().getName(), "Referenced check name is null");
  }

  /*
   * Tests that a check may be documented using a ML_COMMENT. The documentation is inferred
   * in the description field of a check.
   */
  @Test
  public void testCheckDescriptionIsInferred() throws Exception {
    initializeTestProject();
    final Check check = util.getFirstInstanceOf(getModel("CommonChecks"), Check.class);
    assertEquals("This check is javadoc-like commented.", check.getDescription(), "Referenced check cannot be resolved");
  }
}
