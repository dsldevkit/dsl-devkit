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

package com.avaloq.tools.ddk.xtext.ui.templates

import com.avaloq.tools.ddk.test.core.AfterAll
import com.avaloq.tools.ddk.test.core.BeforeAll
import com.avaloq.tools.ddk.test.core.junit.runners.ClassRunner
import com.google.inject.Guice
import org.eclipse.jface.text.IDocument
import org.eclipse.jface.text.IRegion
import org.eclipse.jface.text.Position
import org.eclipse.jface.text.templates.Template
import org.eclipse.xtext.XtextRuntimeModule
import org.eclipse.xtext.ui.editor.templates.XtextTemplateContext
import org.eclipse.xtext.ui.editor.templates.XtextTemplateContextType
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertArrayEquals
import static org.junit.Assert.assertEquals
import static org.mockito.Matchers.anyInt
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

@RunWith(ClassRunner)
public class TemplateProposalProviderHelperTest {

  static val SIMPLE_ENUM_VARIABLE_TYPE = new SimpleEnumTemplateVariableResolver().type
  static val VARIABLE_NAME = "variableName"
  static val RETURNED_PATTERN = "Pattern returned from createSimpleEnumPattern()"

  static val TEMPLATE_NAME = "TemplateName"
  static val TEMPLATE_DESCRIPTION = "Template description"
  static val CONTEXT_TYPE_ID = "context.type.ID"
  static val IS_AUTO_INSERTABLE = true

  static var IDocument mockDocument
  static var Position mockPosition
  static var IRegion mockRegion

  static var XtextTemplateContext templateContext

  static var TemplateProposalProviderHelper helper

  @BeforeAll
  def void beforeAll() {
    mockDocument = mock(IDocument)
    mockPosition = mock(Position)
    mockRegion = mock(IRegion)

    val templateContextType = new XtextTemplateContextType
    templateContextType.addResolver(new SimpleEnumTemplateVariableResolver)
    templateContext = new XtextTemplateContext(templateContextType, mockDocument, mockPosition, null, null)

    helper = Guice.createInjector(new XtextRuntimeModule).getInstance(TemplateProposalProviderHelper)

    when(mockDocument.getLineInformationOfOffset(anyInt)).thenReturn(mockRegion)
    when(mockDocument.get(anyInt, anyInt)).thenReturn("")
  }

  @AfterAll
  def void afterAll() {
    mockDocument = null
    mockPosition = null
    mockRegion = null

    templateContext = null

    helper = null
  }

  @Test(expected=NullPointerException)
  def void testCreateLiteralValuePatternWithNullName() {
    helper.createLiteralValuePattern(null, 42)
  }

  @Test(expected=IllegalArgumentException)
  def void testCreateLiteralValuePatternWithNameContainingWhitespace() {
    helper.createLiteralValuePattern("Contains whitespace", 42)
  }

  @Test
  def void testCreateLiteralValuePatternWithNullDefaultValue() {
    testCreateLiteralValuePattern(null, RETURNED_PATTERN, RETURNED_PATTERN)
  }

  @Test
  def void testCreateLiteralValuePatternWithFalse() {
    testCreateLiteralValuePattern(false, RETURNED_PATTERN, RETURNED_PATTERN)
  }

  @Test
  def void testCreateLiteralValuePatternWithTrue() {
    testCreateLiteralValuePattern(true, RETURNED_PATTERN, RETURNED_PATTERN)
  }

  @Test
  def void testCreateLiteralValuePatternWithNumber() {
    testCreateLiteralValuePattern(42, RETURNED_PATTERN, RETURNED_PATTERN)
  }

  @Test
  def void testCreateLiteralValuePatternWithString() {
    testCreateLiteralValuePattern("Supercalifragilisticexpialidocious", RETURNED_PATTERN, '''"«RETURNED_PATTERN»"''')
  }

  /**
   * Test createLiteralValuePattern() using a mock createSimpleEnumPattern().
   *
   * @param defaultValue    default value to supply to createLiteralValuePattern(), may be {@code null}
   * @param pattern         pattern to return from createSimpleEnumPattern(), may be {@code null}
   * @param expectedResult  expected return value from createLiteralValuePattern(), may be {@code null}
   */
  def void testCreateLiteralValuePattern(Object defaultValue, String pattern, String expectedResult) {

    // ARRANGE
    val helperSpy = spy(helper)
    when(helperSpy.createTemplateVariablePattern(SIMPLE_ENUM_VARIABLE_TYPE, VARIABLE_NAME, defaultValue)).
      thenReturn(pattern)

    // ACT
    val actualResult = helperSpy.createLiteralValuePattern(VARIABLE_NAME, defaultValue)

    // ASSERT
    verify(helperSpy).createTemplateVariablePattern(SIMPLE_ENUM_VARIABLE_TYPE, VARIABLE_NAME, defaultValue)
    assertEquals("Expected result", expectedResult, actualResult)
  }

  @Test(expected=NullPointerException)
  def void testCreateTemplateVariablePatternWithNullType() {
    helper.createTemplateVariablePattern(null, VARIABLE_NAME)
  }

  @Test(expected=IllegalArgumentException)
  def void testCreateTemplateVariablePatternWithTypeContainingWhitespace() {
    helper.createTemplateVariablePattern("Contains whitespace", VARIABLE_NAME)
  }

  @Test(expected=NullPointerException)
  def void testCreateTemplateVariablePatternWithNullName() {
    helper.createTemplateVariablePattern(SIMPLE_ENUM_VARIABLE_TYPE, null)
  }

  @Test(expected=IllegalArgumentException)
  def void testCreateTemplateVariablePatternWithNameContainingWhitespace() {
    helper.createTemplateVariablePattern(SIMPLE_ENUM_VARIABLE_TYPE, "Contains whitespace")
  }

  @Test(expected=NullPointerException)
  def void testCreateTemplateVariablePatternWithNull() {
    helper.createTemplateVariablePattern(SIMPLE_ENUM_VARIABLE_TYPE, VARIABLE_NAME, null)
  }

  @Test(expected=IllegalArgumentException)
  def void testCreateTemplateVariablePatternWithNoValues() {
    helper.createTemplateVariablePattern(SIMPLE_ENUM_VARIABLE_TYPE, VARIABLE_NAME)
  }

  @Test
  def void testCreateTemplateVariablePatternWithFalse() {
    testCreateTemplateVariablePattern(#[false], "false", #["false", "true"])
  }

  @Test
  def void testCreateTemplateVariablePatternWithTrue() {
    testCreateTemplateVariablePattern(#[true], "true", #["true", "false"])
  }

  @Test
  def void testCreateTemplateVariablePatternWithMultipleBooleans() {
    testCreateTemplateVariablePattern(#[false, false, true], "false", #["false", "false", "true"])
  }

  @Test
  def void testCreateTemplateVariablePatternWithNumber() {
    testCreateTemplateVariablePattern(#[42], "42", #["42"])
  }

  @Test
  def void testCreateTemplateVariablePatternWithMultipleNumbers() {
    testCreateTemplateVariablePattern(#[1297, 1314], "1297", #["1297", "1314"])
  }

  @Test
  def void testCreateTemplateVariablePatternWithString() {
    testCreateTemplateVariablePattern(#["Supercalifragilisticexpialidocious"], "Supercalifragilisticexpialidocious",
      #["Supercalifragilisticexpialidocious"])
  }

  @Test
  def void testCreateTemplateVariablePatternWithEmptyString() {
    testCreateTemplateVariablePattern(#[""], "", #[""])
  }

  @Test
  def void testCreateTemplateVariablePatternWithStringContainingWhitespace() {
    testCreateTemplateVariablePattern(#["Lorem ipsum dolor sit amet"], "Lorem ipsum dolor sit amet",
      #["Lorem ipsum dolor sit amet"])
  }

  @Test
  def void testCreateTemplateVariablePatternWithStringContainingSingleQuotes() {
    testCreateTemplateVariablePattern(#["Apostrophe's"], "Apostrophe's", #["Apostrophe's"])
  }

  @Test
  def void testCreateTemplateVariablePatternWithStringContainingDoubleQuotes() {
    testCreateTemplateVariablePattern(#['''CHAIN "CHUCKIE"'''.toString], '''CHAIN \"CHUCKIE\"''',
      #['''CHAIN \"CHUCKIE\"'''])
  }

  @Test
  def void testCreateTemplateVariablePatternWithStringContainingWhitespaceAndSingleAndDoubleQuotes() {
    testCreateTemplateVariablePattern(
      #['''"Whoever thinks of going to bed before twelve o'clock is a scoundrel" - Dr Johnson'''.
        toString], '''\"Whoever thinks of going to bed before twelve o'clock is a scoundrel\" - Dr Johnson''',
      #['''\"Whoever thinks of going to bed before twelve o'clock is a scoundrel\" - Dr Johnson'''])
  }

  @Test
  def void testCreateTemplateVariablePatternWithMultipleStrings() {
    testCreateTemplateVariablePattern(
      #["Twas brillig and the slithy toves", "Did gyre and gimble in the wabe", "All mimsy were the borogroves",
        "And the mome raths outgrabe"], "Twas brillig and the slithy toves",
      #["Twas brillig and the slithy toves", "Did gyre and gimble in the wabe", "All mimsy were the borogroves",
        "And the mome raths outgrabe"])
  }

  /**
   * Test createTemplateVariablePattern().
   *
   * @param values          values, may be {@code null}
   * @param expectedResult  expected result of applying a template containing the pattern, may be {@code null}
   * @param expectedValues  expected values offered by a template containing the pattern, may be {@code null}
   */
  private def void testCreateTemplateVariablePattern(Object[] values, String expectedResult, String[] expectedValues) {
    // ACT
    val pattern = helper.createTemplateVariablePattern(SIMPLE_ENUM_VARIABLE_TYPE, VARIABLE_NAME, values)

    // Try using the pattern in a template
    val template = new Template(TEMPLATE_NAME, TEMPLATE_DESCRIPTION, CONTEXT_TYPE_ID, pattern, IS_AUTO_INSERTABLE)
    val templateBuffer = templateContext.evaluate(template)
    val actualResult = templateBuffer.string
    assertEquals(1, templateBuffer.variables.length)
    val actualValues = templateBuffer.variables.get(0).values

    // ASSERT
    assertEquals("Expected result", expectedResult, actualResult)
    assertArrayEquals("Expected values", expectedValues, actualValues)
  }

}
