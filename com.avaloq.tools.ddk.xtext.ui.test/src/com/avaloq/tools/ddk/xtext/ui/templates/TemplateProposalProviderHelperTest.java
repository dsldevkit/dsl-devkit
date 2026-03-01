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

package com.avaloq.tools.ddk.xtext.ui.templates;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.inject.Guice;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateBuffer;
import org.eclipse.xtext.XtextRuntimeModule;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.ui.editor.templates.XtextTemplateContext;
import org.eclipse.xtext.ui.editor.templates.XtextTemplateContextType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(InjectionExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TemplateProposalProviderHelperTest {

  private static final String SIMPLE_ENUM_VARIABLE_TYPE = new SimpleEnumTemplateVariableResolver().getType();
  private static final String VARIABLE_NAME = "variableName";
  private static final String RETURNED_PATTERN = "Pattern returned from createSimpleEnumPattern()";

  private static final String TEMPLATE_NAME = "TemplateName";
  private static final String TEMPLATE_DESCRIPTION = "Template description";
  private static final String CONTEXT_TYPE_ID = "context.type.ID";
  private static final boolean IS_AUTO_INSERTABLE = true;

  private static XtextTemplateContext templateContext;

  private static TemplateProposalProviderHelper helper;

  @BeforeAll
  @SuppressWarnings("PMD.SignatureDeclareThrowsException")
  public void beforeAll() throws Exception {
    final IDocument mockDocument = mock(IDocument.class);
    final Position mockPosition = mock(Position.class);
    final IRegion mockRegion = mock(IRegion.class);

    final XtextTemplateContextType templateContextType = new XtextTemplateContextType();
    templateContextType.addResolver(new SimpleEnumTemplateVariableResolver());
    templateContext = new XtextTemplateContext(templateContextType, mockDocument, mockPosition, null, null);

    helper = Guice.createInjector(new XtextRuntimeModule()).getInstance(TemplateProposalProviderHelper.class);

    when(mockDocument.getLineInformationOfOffset(anyInt())).thenReturn(mockRegion);
    when(mockDocument.get(anyInt(), anyInt())).thenReturn("");
  }

  @AfterAll
  public void afterAll() {
    templateContext = null;

    helper = null;
  }

  // CHECKSTYLE:CONSTANTS-OFF
  @Test
  public void testCreateLiteralValuePatternWithNullName() {
    assertThrows(NullPointerException.class, () -> helper.createLiteralValuePattern(null, 42));
  }

  @Test
  public void testCreateLiteralValuePatternWithNameContainingWhitespace() {
    assertThrows(IllegalArgumentException.class, () -> helper.createLiteralValuePattern("Contains whitespace", 42));
  }

  @Test
  public void testCreateLiteralValuePatternWithNullDefaultValue() {
    testCreateLiteralValuePattern(null, RETURNED_PATTERN, RETURNED_PATTERN);
  }

  @Test
  public void testCreateLiteralValuePatternWithFalse() {
    testCreateLiteralValuePattern(false, RETURNED_PATTERN, RETURNED_PATTERN);
  }

  @Test
  public void testCreateLiteralValuePatternWithTrue() {
    testCreateLiteralValuePattern(true, RETURNED_PATTERN, RETURNED_PATTERN);
  }

  @Test
  public void testCreateLiteralValuePatternWithNumber() {
    testCreateLiteralValuePattern(42, RETURNED_PATTERN, RETURNED_PATTERN);
  }

  @Test
  public void testCreateLiteralValuePatternWithString() {
    testCreateLiteralValuePattern("Supercalifragilisticexpialidocious", RETURNED_PATTERN, "\"" + RETURNED_PATTERN + "\"");
  }

  /**
   * Test createLiteralValuePattern() using a mock createSimpleEnumPattern().
   *
   * @param defaultValue    default value to supply to createLiteralValuePattern(), may be {@code null}
   * @param pattern         pattern to return from createSimpleEnumPattern(), may be {@code null}
   * @param expectedResult  expected return value from createLiteralValuePattern(), may be {@code null}
   */
  public void testCreateLiteralValuePattern(final Object defaultValue, final String pattern, final String expectedResult) {

    // ARRANGE
    final TemplateProposalProviderHelper helperSpy = spy(helper);
    when(helperSpy.createTemplateVariablePattern(SIMPLE_ENUM_VARIABLE_TYPE, VARIABLE_NAME, defaultValue)).
        thenReturn(pattern);

    // ACT
    final String actualResult = helperSpy.createLiteralValuePattern(VARIABLE_NAME, defaultValue);

    // ASSERT
    verify(helperSpy).createTemplateVariablePattern(SIMPLE_ENUM_VARIABLE_TYPE, VARIABLE_NAME, defaultValue);
    assertEquals(expectedResult, actualResult, "Expected result");
  }

  @Test
  public void testCreateTemplateVariablePatternWithNullType() {
    assertThrows(NullPointerException.class, () -> helper.createTemplateVariablePattern(null, VARIABLE_NAME));
  }

  @Test
  public void testCreateTemplateVariablePatternWithTypeContainingWhitespace() {
    assertThrows(IllegalArgumentException.class, () -> helper.createTemplateVariablePattern("Contains whitespace", VARIABLE_NAME));
  }

  @Test
  public void testCreateTemplateVariablePatternWithNullName() {
    assertThrows(NullPointerException.class, () -> helper.createTemplateVariablePattern(SIMPLE_ENUM_VARIABLE_TYPE, null));
  }

  public void testCreateTemplateVariablePatternWithNameContainingWhitespace() {
    assertThrows(IllegalArgumentException.class, () -> helper.createTemplateVariablePattern(SIMPLE_ENUM_VARIABLE_TYPE, "Contains whitespace"));
  }

  @Test
  public void testCreateTemplateVariablePatternWithNull() {
    assertThrows(NullPointerException.class, () -> helper.createTemplateVariablePattern(SIMPLE_ENUM_VARIABLE_TYPE, VARIABLE_NAME, (Object[]) null));
  }

  @Test
  public void testCreateTemplateVariablePatternWithNoValues() {
    assertThrows(IllegalArgumentException.class, () -> helper.createTemplateVariablePattern(SIMPLE_ENUM_VARIABLE_TYPE, VARIABLE_NAME));
  }

  @Test
  public void testCreateTemplateVariablePatternWithFalse() {
    testCreateTemplateVariablePattern(new Object[]{false}, "false", "false", "true");
  }

  @Test
  public void testCreateTemplateVariablePatternWithTrue() {
    testCreateTemplateVariablePattern(new Object[]{true}, "true", "true", "false");
  }

  @Test
  public void testCreateTemplateVariablePatternWithMultipleBooleans() {
    testCreateTemplateVariablePattern(new Object[]{false, false, true}, "false", "false", "false", "true");
  }

  @Test
  public void testCreateTemplateVariablePatternWithNumber() {
    testCreateTemplateVariablePattern(new Object[]{42}, "42", "42");
  }

  @Test
  public void testCreateTemplateVariablePatternWithMultipleNumbers() {
    testCreateTemplateVariablePattern(new Object[]{1297, 1314}, "1297", "1297", "1314");
  }

  @Test
  public void testCreateTemplateVariablePatternWithString() {
    testCreateTemplateVariablePattern(new Object[]{"Supercalifragilisticexpialidocious"}, "Supercalifragilisticexpialidocious",
        "Supercalifragilisticexpialidocious");
  }

  @Test
  public void testCreateTemplateVariablePatternWithEmptyString() {
    testCreateTemplateVariablePattern(new Object[]{""}, "", "");
  }

  @Test
  public void testCreateTemplateVariablePatternWithStringContainingWhitespace() {
    testCreateTemplateVariablePattern(new Object[]{"Lorem ipsum dolor sit amet"}, "Lorem ipsum dolor sit amet",
        "Lorem ipsum dolor sit amet");
  }

  @Test
  public void testCreateTemplateVariablePatternWithStringContainingSingleQuotes() {
    testCreateTemplateVariablePattern(new Object[]{"Apostrophe's"}, "Apostrophe's", "Apostrophe's");
  }

  @Test
  public void testCreateTemplateVariablePatternWithStringContainingDoubleQuotes() {
    testCreateTemplateVariablePattern(new Object[]{"CHAIN \"CHUCKIE\""}, "CHAIN \\\"CHUCKIE\\\"",
        "CHAIN \\\"CHUCKIE\\\"");
  }

  @Test
  public void testCreateTemplateVariablePatternWithStringContainingWhitespaceAndSingleAndDoubleQuotes() {
    testCreateTemplateVariablePattern(
        new Object[]{"\"Whoever thinks of going to bed before twelve o'clock is a scoundrel\" - Dr Johnson"},
        "\\\"Whoever thinks of going to bed before twelve o'clock is a scoundrel\\\" - Dr Johnson",
        "\\\"Whoever thinks of going to bed before twelve o'clock is a scoundrel\\\" - Dr Johnson");
  }

  @Test
  public void testCreateTemplateVariablePatternWithMultipleStrings() {
    testCreateTemplateVariablePattern(
        new Object[]{"Twas brillig and the slithy toves", "Did gyre and gimble in the wabe", "All mimsy were the borogroves",
            "And the mome raths outgrabe"}, "Twas brillig and the slithy toves",
        "Twas brillig and the slithy toves", "Did gyre and gimble in the wabe", "All mimsy were the borogroves",
            "And the mome raths outgrabe");
  }

  /**
   * Test createTemplateVariablePattern().
   *
   * @param values          values, may be {@code null}
   * @param expectedResult  expected result of applying a template containing the pattern, may be {@code null}
   * @param expectedValues  expected values offered by a template containing the pattern, may be {@code null}
   */
  private void testCreateTemplateVariablePattern(final Object[] values, final String expectedResult, final String... expectedValues) {
    try {
      // ACT
      final String pattern = helper.createTemplateVariablePattern(SIMPLE_ENUM_VARIABLE_TYPE, VARIABLE_NAME, values);

      // Try using the pattern in a template
      final Template template = new Template(TEMPLATE_NAME, TEMPLATE_DESCRIPTION, CONTEXT_TYPE_ID, pattern, IS_AUTO_INSERTABLE);
      final TemplateBuffer templateBuffer = templateContext.evaluate(template);
      final String actualResult = templateBuffer.getString();
      assertEquals(1, templateBuffer.getVariables().length);
      final String[] actualValues = templateBuffer.getVariables()[0].getValues();

      // ASSERT
      assertEquals(expectedResult, actualResult, "Expected result");
      assertArrayEquals(expectedValues, actualValues, "Expected values");
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }
  // CHECKSTYLE:CONSTANTS-ON

}
