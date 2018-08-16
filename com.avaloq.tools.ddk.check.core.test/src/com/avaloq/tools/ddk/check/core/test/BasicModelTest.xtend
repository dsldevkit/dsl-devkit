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
import com.avaloq.tools.ddk.check.check.XIssueExpression
import com.avaloq.tools.ddk.check.core.test.util.CheckModelUtil
import com.avaloq.tools.ddk.check.core.test.util.CheckTestUtil
import com.google.inject.Inject
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*

@InjectWith(typeof(CheckUiInjectorProvider))
@RunWith(typeof(XtextRunner))
class BasicModelTest {

  @Inject
  ParseHelper<CheckCatalog> parser

  @Inject
  CheckTestUtil util

  @Inject
  CheckModelUtil modelUtil

  /**
   * Tests that an empty model based on EPackage model reference can be created.
   */
  @Test
  def void testCreateEmptyModelWithPackageReference() {
    val model = parser.parse("package p catalog c {}")
    assertNotNull("CheckCatalog with EPackage reference successfully created", model);
  }

  /**
   * Tests that an empty model based on model reference by Xtext Grammar can be created.
   */
  @Test
  def void testCreateEmptyModelWithGrammarReference() {
    val model = parser.parse("package p catalog c for grammar com.avaloq.tools.ddk.check.Check {}")
    assertNotNull("CheckCatalog with Grammar reference successfully created", model);
  }

  /* Tests that an XIssueExpression takes message parameters. */
  @Test
  def void testXIssueExpressionMessageParameters() {
    val model = parser.parse(modelUtil.modelWithContext + "issue A on it  bind (\"mp0\", \"mp1\")")
    assertEquals(2, util.getFirstInstanceOf(model, typeof(XIssueExpression)).messageParameters.size)
  }

  /* Tests that an XIssueExpression takes message parameters when a marker feature has been specified. */
  @Test
  def void testXIssueExpressionWithMarkerFeatureMessageParameters() {
    val model = parser.parse(modelUtil.modelWithContext + "issue A on x#name bind (\"mp0\", \"mp1\")")
    assertEquals(2, util.getFirstInstanceOf(model, typeof(XIssueExpression)).messageParameters.size)
  }

  /* Tests that Checks documented with ML_COMMENTs have an inferred description field. */
  @Test
  @Ignore("Fails because DocumentedImplCustom uses the null resource description provider to get the document provider")
  def void testInferingOfDescription() {
    val check = util.getFirstInstanceOf(parser.parse(modelUtil.modelWithCheck), typeof(Check))
    assertEquals("No documentation.", check.description)
  }

  /* Tests that Checks have an implicit name which matches the ID. */
  @Test
  def void testCheckNameIDIsPresent() {
    val id = "CheckID"
    val check = util.getFirstInstanceOf(parser.parse(modelUtil.modelWithCheck(id)), typeof(Check))
    assertEquals("Check name field matches ID field", check.id, check.name)
    assertEquals("Check name field matches supplied ID", id, check.name)
  }

  /* Tests that Checks have an implicit name which matches the ID even when the ID is missing. */
  @Test
  def void testCheckNameIDIsMissing() {
    val check = util.getFirstInstanceOf(parser.parse(modelUtil.modelWithCheck("")), typeof(Check))
    assertNull("Check name is null", check.name)
  }

  /* Tests that multi- and single-line comments are parsed in a model. */
  @Test
  def void testCommentsInModelParse() {
    val model = parser.parse(modelUtil.modelWithComments)
    assertFalse("Syntax errors not expected but occurred", (model.eResource as XtextResource).parseResult.hasSyntaxErrors)
  }

  /* Tests that t.message is allowed, despite "message" being a keyword. */
  @Test
  def void testKeywordAsIdentifier() {
    val model = parser.parse(modelUtil.modelWithContext + "try { issue bind (\"mp0\", \"mp1\"); } catch (java.lang.Throwable t) { issue bind (t.message, \"foo\"); }" + "}}}}");
    assertFalse("Syntax errors not expected but occurred", (model.eResource as XtextResource).parseResult.hasSyntaxErrors);
  }
}
