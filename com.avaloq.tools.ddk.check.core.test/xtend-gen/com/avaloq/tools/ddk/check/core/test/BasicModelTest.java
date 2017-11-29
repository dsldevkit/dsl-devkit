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
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.avaloq.tools.ddk.check.core.test.util.CheckModelUtil;
import com.avaloq.tools.ddk.check.core.test.util.CheckTestUtil;
import com.google.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@InjectWith(CheckUiInjectorProvider.class)
@RunWith(XtextRunner.class)
@SuppressWarnings("all")
public class BasicModelTest {
  @Inject
  private ParseHelper<CheckCatalog> parser;
  
  @Inject
  private CheckTestUtil util;
  
  @Inject
  private CheckModelUtil modelUtil;
  
  /**
   * Tests that an empty model based on EPackage model reference can be created.
   */
  @Test
  public void testCreateEmptyModelWithPackageReference() {
    try {
      final CheckCatalog model = this.parser.parse("package p catalog c {}");
      Assert.assertNotNull("CheckCatalog with EPackage reference successfully created", model);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests that an empty model based on model reference by Xtext Grammar can be created.
   */
  @Test
  public void testCreateEmptyModelWithGrammarReference() {
    try {
      final CheckCatalog model = this.parser.parse("package p catalog c for grammar com.avaloq.tools.ddk.check.Check {}");
      Assert.assertNotNull("CheckCatalog with Grammar reference successfully created", model);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests that an XIssueExpression takes message parameters.
   */
  @Test
  public void testXIssueExpressionMessageParameters() {
    try {
      String _modelWithContext = this.modelUtil.modelWithContext();
      String _plus = (_modelWithContext + "issue A on it  bind (\"mp0\", \"mp1\")");
      final CheckCatalog model = this.parser.parse(_plus);
      XIssueExpression _firstInstanceOf = this.util.<XIssueExpression>getFirstInstanceOf(model, XIssueExpression.class);
      EList<XExpression> _messageParameters = _firstInstanceOf.getMessageParameters();
      int _size = _messageParameters.size();
      Assert.assertEquals(2, _size);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests that an XIssueExpression takes message parameters when a marker feature has been specified.
   */
  @Test
  public void testXIssueExpressionWithMarkerFeatureMessageParameters() {
    try {
      String _modelWithContext = this.modelUtil.modelWithContext();
      String _plus = (_modelWithContext + "issue A on x#name bind (\"mp0\", \"mp1\")");
      final CheckCatalog model = this.parser.parse(_plus);
      XIssueExpression _firstInstanceOf = this.util.<XIssueExpression>getFirstInstanceOf(model, XIssueExpression.class);
      EList<XExpression> _messageParameters = _firstInstanceOf.getMessageParameters();
      int _size = _messageParameters.size();
      Assert.assertEquals(2, _size);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests that Checks documented with ML_COMMENTs have an inferred description field.
   */
  @Test
  @Ignore("Fails because DocumentedImplCustom uses the null resource description provider to get the document provider")
  public void testInferingOfDescription() {
    try {
      String _modelWithCheck = this.modelUtil.modelWithCheck();
      CheckCatalog _parse = this.parser.parse(_modelWithCheck);
      final Check check = this.util.<Check>getFirstInstanceOf(_parse, Check.class);
      String _description = check.getDescription();
      Assert.assertEquals("No documentation.", _description);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests that Checks have an implicit name which matches the ID.
   */
  @Test
  public void testCheckNameIDIsPresent() {
    try {
      final String id = "CheckID";
      String _modelWithCheck = this.modelUtil.modelWithCheck(id);
      CheckCatalog _parse = this.parser.parse(_modelWithCheck);
      final Check check = this.util.<Check>getFirstInstanceOf(_parse, Check.class);
      String _id = check.getId();
      String _name = check.getName();
      Assert.assertEquals("Check name field matches ID field", _id, _name);
      String _name_1 = check.getName();
      Assert.assertEquals("Check name field matches supplied ID", id, _name_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests that Checks have an implicit name which matches the ID even when the ID is missing.
   */
  @Test
  public void testCheckNameIDIsMissing() {
    try {
      String _modelWithCheck = this.modelUtil.modelWithCheck("");
      CheckCatalog _parse = this.parser.parse(_modelWithCheck);
      final Check check = this.util.<Check>getFirstInstanceOf(_parse, Check.class);
      String _name = check.getName();
      Assert.assertNull("Check name is null", _name);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests that multi- and single-line comments are parsed in a model.
   */
  @Test
  public void testCommentsInModelParse() {
    try {
      String _modelWithComments = this.modelUtil.modelWithComments();
      final CheckCatalog model = this.parser.parse(_modelWithComments);
      Resource _eResource = model.eResource();
      IParseResult _parseResult = ((XtextResource) _eResource).getParseResult();
      boolean _hasSyntaxErrors = _parseResult.hasSyntaxErrors();
      Assert.assertFalse("Syntax errors not expected but occurred", _hasSyntaxErrors);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Tests that t.message is allowed, despite "message" being a keyword.
   */
  @Test
  public void testKeywordAsIdentifier() {
    try {
      String _modelWithContext = this.modelUtil.modelWithContext();
      String _plus = (_modelWithContext + "try { issue bind (\"mp0\", \"mp1\"); } catch (java.lang.Throwable t) { issue bind (t.message, \"foo\"); }");
      String _plus_1 = (_plus + "}}}}");
      final CheckCatalog model = this.parser.parse(_plus_1);
      Resource _eResource = model.eResource();
      IParseResult _parseResult = ((XtextResource) _eResource).getParseResult();
      boolean _hasSyntaxErrors = _parseResult.hasSyntaxErrors();
      Assert.assertFalse("Syntax errors not expected but occurred", _hasSyntaxErrors);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
