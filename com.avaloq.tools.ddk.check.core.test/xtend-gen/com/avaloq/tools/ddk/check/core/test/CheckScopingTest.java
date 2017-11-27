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
package com.avaloq.tools.ddk.check.core.test;

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.Implementation;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.avaloq.tools.ddk.check.core.test.AbstractCheckTestCase;
import com.avaloq.tools.ddk.check.core.test.util.CheckTestUtil;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import java.util.List;
import junit.framework.TestCase;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.Test;
import org.junit.runner.RunWith;

@InjectWith(CheckUiInjectorProvider.class)
@RunWith(XtextRunner.class)
@SuppressWarnings("all")
public class CheckScopingTest extends AbstractCheckTestCase {
  @Inject
  private CheckTestUtil util;
  
  /**
   * The model names are deliberately not including file extension, the actual resources
   * are also missing them. The reason for this is that if current development instance of
   * Eclipse has the Check runtime plugins installed, code will automatically be generated
   * for those resources. In order to avoid that the file extensions have been ommitted.
   */
  public List<String> getRequiredSourceFileNames() {
    return Lists.<String>newArrayList("CommonChecks", "SampleChecks");
  }
  
  public void initializeTestProject() {
    List<String> _requiredSourceFileNames = this.getRequiredSourceFileNames();
    this.addSourcesToWorkspace(CheckScopingTest.class, _requiredSourceFileNames);
    IResourcesSetupUtil.waitForAutoBuild();
  }
  
  /**
   * Tests that Catalogs can be included in one another.
   */
  @Test
  public void testResolutionOfIncludedCatalog() {
    try {
      this.initializeTestProject();
      EObject _model = this.getModel("SampleChecks");
      final CheckCatalog sampleCheckModel = ((CheckCatalog) _model);
      final CheckCatalog includedCategory = sampleCheckModel.getIncludedCatalogs();
      TestCase.assertNotNull("The included Category is not null", includedCategory);
      boolean _eIsProxy = includedCategory.eIsProxy();
      TestCase.assertFalse("The included Category could be resolved", _eIsProxy);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests that a catalog may not reference checks (in implementations, 'def') which are
   * neither local nor included.
   */
  @Test
  public void testIllegalDirectionOfReference() {
    try {
      this.initializeTestProject();
      EObject _model = this.getModel("CommonChecks");
      final CheckCatalog model = ((CheckCatalog) _model);
      final Implementation illegalRefImpl = this.util.<Implementation>getFirstInstanceOf(model, Implementation.class);
      final XIssueExpression issueExpr = this.util.<XIssueExpression>getFirstInstanceOf(illegalRefImpl, XIssueExpression.class);
      Check _check = issueExpr.getCheck();
      boolean _eIsProxy = _check.eIsProxy();
      TestCase.assertTrue("Referenced check cannot be resolved", _eIsProxy);
      Check _check_1 = issueExpr.getCheck();
      String _name = _check_1.getName();
      TestCase.assertNull("Referenced check name is null", _name);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests that a check may be documented using a ML_COMMENT. The documentation is inferred
   * in the description field of a check.
   */
  @Test
  public void testCheckDescriptionIsInferred() {
    try {
      this.initializeTestProject();
      EObject _model = this.getModel("CommonChecks");
      final Check check = this.util.<Check>getFirstInstanceOf(_model, Check.class);
      String _description = check.getDescription();
      TestCase.assertEquals("Referenced check cannot be resolved", "This check is javadoc-like commented.", _description);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
