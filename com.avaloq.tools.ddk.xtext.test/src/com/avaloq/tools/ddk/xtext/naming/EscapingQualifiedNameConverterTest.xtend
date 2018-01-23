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

package com.avaloq.tools.ddk.xtext.naming

import com.google.inject.AbstractModule
import com.google.inject.Guice
import java.util.Arrays
import java.util.List
import java.util.stream.Collectors
import java.util.stream.Stream
import org.eclipse.xtext.IGrammarAccess
import org.eclipse.xtext.Keyword
import org.eclipse.xtext.naming.QualifiedName
import org.junit.After
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

/**
 * Test {@link EscapingQualifiedNameConverter}.
 */
public class EscapingQualifiedNameConverterTest {

  // Test data
  static val String EXPECTED_DELIMITER = "."
  static val String EXPECTED_ESCAPE_CHARACTER = "^"
  var List<String> keywords
  var List<String> segments
  var String string
  var QualifiedName qualifiedName

  private IGrammarAccess mockGrammarAccess
  private EscapingQualifiedNameConverter converter

  /**
   * Set up test data, mocks and UUT before each test.
   */
  @Before
  def void before() {

    // Create test data
    keywords = Arrays.asList("keyword1", "keyword2")
    val nonKeywords = Arrays.asList("nonkeyword1", "nonkeyword2")
    segments = Stream.of(keywords, keywords, nonKeywords).flatMap[stream].collect(Collectors.toList)

    val escapedKeywords = keywords.map[EXPECTED_ESCAPE_CHARACTER + it]
    val escapedSegments = Stream.of(escapedKeywords, escapedKeywords, nonKeywords).flatMap[stream].collect(
      Collectors.toList)

    string = String.join(EXPECTED_DELIMITER, escapedSegments)
    qualifiedName = QualifiedName.create(segments)

    // Create mock
    mockGrammarAccess = mock(IGrammarAccess)

    // Create injector, with binding for mock
    val injector = Guice.createInjector(new AbstractModule {
      override configure() {
        bind(IGrammarAccess).toInstance(mockGrammarAccess)
      }
    })

    // Create UUT
    converter = injector.getInstance(EscapingQualifiedNameConverter)
  }

  /**
   * Tear down test data, mocks and UUT after each test.
   */
  @After
  def void after() {
    keywords = null
    segments = null
    string = null
    qualifiedName = null

    mockGrammarAccess = null
    converter = null
  }

  /**
   * Test toString() when passed a null {@link QualifiedName}.
   */
  @Test(expected=IllegalArgumentException)
  def void testToStringWithNullQualifiedName() {
    converter.toString(null)
  }

  /**
   * Test toString().
   */
  @Test
  def void testToString() {

    // ARRANGE
    // Set expectations
    when(mockGrammarAccess.findKeywords(segments)).thenReturn(keywords.map [
      val mockKeyword = mock(Keyword)
      when(mockKeyword.value).thenReturn(it)
      return mockKeyword
    ])

    // ACT
    val actualString = converter.toString(qualifiedName)

    // ASSERT
    assertEquals(string, actualString)
  }

  /**
   * Test toQualifiedName() when passed a null string.
   */
  @Test(expected=IllegalArgumentException)
  def void testToQualifiedNameWIthNullString() {
    converter.toQualifiedName(null)
  }

  /**
   * Test toQualifiedName() when passed an empty string.
   */
  @Test(expected=IllegalArgumentException)
  def void testToQualifiedNameWIthEmptyString() {
    converter.toQualifiedName("")
  }

  /**
   * Test toQualifiedName().
   */
  @Test
  def void testToQualifiedName() {

    // ACT
    val actualQualifiedName = converter.toQualifiedName(string)

    // ASSERT
    assertEquals(qualifiedName, actualQualifiedName)

  }

  /**
   * Test getEscapeCharacter().
   */
  @Test
  def void testGetEscapeCharacter() {

    // ACT
    val actualEscapeCharacter = converter.escapeCharacter

    // ASSERT
    assertEquals(EXPECTED_ESCAPE_CHARACTER, actualEscapeCharacter)
  }

}
