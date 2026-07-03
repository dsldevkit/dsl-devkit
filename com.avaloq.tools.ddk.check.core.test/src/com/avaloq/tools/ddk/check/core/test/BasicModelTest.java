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

import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.avaloq.tools.ddk.check.core.test.util.CheckModelUtil;
import com.avaloq.tools.ddk.check.core.test.util.CheckTestUtil;
import com.google.inject.Inject;


@InjectWith(CheckUiInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
@SuppressWarnings("nls")
public class BasicModelTest {

  private static final String SYNTAX_ERRORS_NOT_EXPECTED = "Syntax errors not expected but occurred";

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
  public void testCreateEmptyModelWithPackageReference() throws Exception {
    final CheckCatalog model = parser.parse("package p catalog c {}");
    assertNotNull(model, "CheckCatalog with EPackage reference successfully created");
  }

  /**
   * Tests that an empty model based on model reference by Xtext Grammar can be created.
   */
  @Test
  public void testCreateEmptyModelWithGrammarReference() throws Exception {
    final CheckCatalog model = parser.parse("package p catalog c for grammar com.avaloq.tools.ddk.check.Check {}");
    assertNotNull(model, "CheckCatalog with Grammar reference successfully created");
  }

  /* Tests that an XIssueExpression takes message parameters. */
  @Test
  public void testXIssueExpressionMessageParameters() throws Exception {
    final CheckCatalog model = parser.parse(modelUtil.modelWithContext() + "issue A on it  bind (\"mp0\", \"mp1\")");
    assertEquals(2, util.getFirstInstanceOf(model, XIssueExpression.class).getMessageParameters().size());
  }

  /* Tests that an XIssueExpression takes message parameters when a marker feature has been specified. */
  @Test
  public void testXIssueExpressionWithMarkerFeatureMessageParameters() throws Exception {
    final CheckCatalog model = parser.parse(modelUtil.modelWithContext() + "issue A on x#name bind (\"mp0\", \"mp1\")");
    assertEquals(2, util.getFirstInstanceOf(model, XIssueExpression.class).getMessageParameters().size());
  }

  /* Tests that Checks documented with ML_COMMENTs have an inferred description field. */
  @Test
  @Disabled("Fails because DocumentedImplCustom uses the null resource description provider to get the document provider")
  public void testInferingOfDescription() throws Exception {
    final Check check = util.getFirstInstanceOf(parser.parse(modelUtil.modelWithCheck()), Check.class);
    assertEquals("No documentation.", check.getDescription());
  }

  /* Tests that Checks have an implicit name which matches the ID. */
  @Test
  public void testCheckNameIDIsPresent() throws Exception {
    final String id = "CheckID";
    final Check check = util.getFirstInstanceOf(parser.parse(modelUtil.modelWithCheck(id)), Check.class);
    assertEquals(check.getId(), check.getName(), "Check name field matches ID field");
    assertEquals(id, check.getName(), "Check name field matches supplied ID");
  }

  /* Tests that Checks have an implicit name which matches the ID even when the ID is missing. */
  @Test
  public void testCheckNameIDIsMissing() throws Exception {
    final Check check = util.getFirstInstanceOf(parser.parse(modelUtil.modelWithCheck("")), Check.class);
    assertNull(check.getName(), "Check name is null");
  }

  /* Tests that multi- and single-line comments are parsed in a model. */
  @Test
  public void testCommentsInModelParse() throws Exception {
    final CheckCatalog model = parser.parse(modelUtil.modelWithComments());
    assertFalse(((XtextResource) model.eResource()).getParseResult().hasSyntaxErrors(), SYNTAX_ERRORS_NOT_EXPECTED);
  }

  /* Tests that t.message is allowed, despite "message" being a keyword. */
  @Test
  public void testKeywordAsIdentifier() throws Exception {
    final CheckCatalog model = parser.parse(
        modelUtil.modelWithContext()
            + "try { issue bind (\"mp0\", \"mp1\"); } catch (java.lang.Throwable t) { issue bind (t.message, \"foo\"); }"
            + "}}}}");
    assertFalse(((XtextResource) model.eResource()).getParseResult().hasSyntaxErrors(), SYNTAX_ERRORS_NOT_EXPECTED);
  }
}
