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

import com.google.inject.Inject
import org.eclipse.xtext.IGrammarAccess
import org.eclipse.xtext.naming.QualifiedName

/**
 * {@inheritDoc}
 * Handle escaping any keywords present.
 */
public class EscapingQualifiedNameConverter extends QualifiedNameConverter {

  static val ESCAPE_CHARACTER = "^"

  @Inject
  var IGrammarAccess grammarAccess

  /**
   * {@inheritDoc}
   * Prefix keywords present with an escape character.
   */
  override toString(QualifiedName qualifiedName) {
    return super.toString(escape(qualifiedName))
  }

  /**
   * {@inheritDoc}
   * Ignore escape characters prefixing segments.
   */
  override toQualifiedName(String qualifiedNameAsString) {
    return unescape(super.toQualifiedName(qualifiedNameAsString))
  }

  /**
   * Get the character used to escape keywords.
   * Override this method to use a different escape character.
   *
   * @return character used to escape keywords
   */
  def String getEscapeCharacter() {
    return ESCAPE_CHARACTER
  }

  /**
   * Prefix keywords in a {@link QualifiedName} with the escape character.
   *
   * @param qualifiedName
   *          the {@link QualifiedName}, may be {@code null}
   * @return the {@link QualifiedName} with keywords prefixed with the escape character,
   *         {@code null} if a {@code null} {@link QualifiedName} was supplied
   */
  def private QualifiedName escape(QualifiedName qualifiedName) {
    if (null == qualifiedName) {
      // Nothing to do
      return qualifiedName
    }

    // Find all keywords present in the qualified name
    val segments = qualifiedName.segments
    val keywords = grammarAccess.findKeywords(segments)
    if (keywords.isEmpty) {
      // Nothing to do
      return qualifiedName
    }

    // Prefix keywords in the qualified name with the escape character
    val keywordStrings = keywords.map[value].toSet
    val escapedSegments = segments.map [
      if (keywordStrings.contains(it)) {
        escapeCharacter + it
      } else {
        it
      }
    ]
    return QualifiedName.create(escapedSegments)
  }

  /**
   * Remove escape characters prefixing {@link QualifiedName} segments.
   *
   * @param qualifiedName
   *          the {@link QualifiedName}, may be {@code null}
   * @return the {@link QualifiedName} with escape characters prefixing segments removed,
   *         {@code null} if a {@code null} {@link QualifiedName} was supplied
   */
  def private QualifiedName unescape(QualifiedName qualifiedName) {
    if (null == qualifiedName) {
      // Nothing to do
      return qualifiedName
    }

    // Remove escape characters prefixing segments
    val segments = qualifiedName.segments
    val unescapedSegments = segments.map [
      if (it.startsWith(escapeCharacter)) {
        it.substring(escapeCharacter.length)
      } else {
        it
      }
    ]
    return QualifiedName.create(unescapedSegments)
  }

}
