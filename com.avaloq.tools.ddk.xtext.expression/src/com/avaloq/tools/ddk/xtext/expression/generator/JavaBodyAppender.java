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
package com.avaloq.tools.ddk.xtext.expression.generator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;

import com.google.inject.Inject;


/**
 * Appends a generated Java body fragment to an {@link ITreeAppendable}, routing the fully qualified names of framework
 * types through {@link ITreeAppendable#append(JvmType)}.
 * <p>
 * Body fragments are produced as plain source text by the expression based body producers and would, when appended as
 * a {@link CharSequence}, never reach the compilation unit's import manager - every type would stay fully qualified.
 * Appending a {@link JvmType} instead registers it for import and prints the shortened name, falling back to the
 * fully qualified name whenever the simple name would clash with another import.
 * <p>
 * Only types from the {@link #FRAMEWORK_PACKAGE_PREFIXES framework packages} are shortened. Types belonging to the
 * language being generated (its EMF model, generated switches, EPackage literals and hand written scoping or export
 * helpers) stay fully qualified, which is how the templates that preceded the JVM model inferrers emitted them.
 */
public class JavaBodyAppender {

  /**
   * Package prefixes of the generator's own runtime and of the frameworks it builds on. Types from these packages are
   * imported and referenced by their simple name; everything else - most notably the generated code of the language
   * being processed - stays fully qualified.
   */
  private static final List<String> FRAMEWORK_PACKAGE_PREFIXES = List.of(//
      "java.", //$NON-NLS-1$
      "javax.", //$NON-NLS-1$
      "org.apache.", //$NON-NLS-1$
      "org.eclipse.", //$NON-NLS-1$
      "com.google.", //$NON-NLS-1$
      "com.avaloq.tools.ddk."); //$NON-NLS-1$

  /**
   * Matches a package qualified type name: one or more lower case package segments followed by a single capitalized
   * type segment. Trailing member accesses such as {@code .Literals.ROW} or {@code .Builder} are deliberately not
   * part of the match, so that only the top level type is resolved and the members are copied verbatim.
   */
  private static final Pattern QUALIFIED_TYPE_NAME = Pattern.compile("(?:[a-z_$][\\w$]*\\.)+[A-Z][\\w$]*"); //$NON-NLS-1$

  @Inject
  private TypeReferences typeReferences;

  /**
   * Appends the given Java source fragment, shortening the framework types it references.
   * <p>
   * Trailing whitespace is stripped first. A multiline template always ends with a newline, and
   * {@code JvmModelGenerator} writes another one before the closing brace of the member it is
   * generating, so an unstripped fragment yields a blank line at the end of every method body that
   * the templates preceding the JVM model inferrers did not produce. The fragment's line breaks are
   * written through {@link #appendText(ITreeAppendable, String)} so that its blank lines do not pick
   * up the appendable's indentation.
   *
   * @param appendable
   *          the appendable to write to, must not be {@code null}
   * @param body
   *          the Java source fragment, must not be {@code null}
   * @param context
   *          a model element used to resolve the referenced types against the classpath, must not be {@code null}
   */
  public void appendBody(final ITreeAppendable appendable, final String body, final EObject context) {
    final String content = body.stripTrailing();
    final StringBuilder verbatim = new StringBuilder();
    final Matcher matcher = QUALIFIED_TYPE_NAME.matcher(content);
    int index = 0;
    while (index < content.length()) {
      final char current = content.charAt(index);
      if (current == '"' || current == '\'') {
        final int end = endOfLiteral(content, index);
        verbatim.append(content, index, end);
        index = end;
      } else if (isCommentStart(content, index)) {
        final int end = endOfComment(content, index);
        verbatim.append(content, index, end);
        index = end;
      } else {
        final JvmType type = isTypeNameStart(content, index) && matcher.region(index, content.length()).lookingAt()
            ? findFrameworkType(matcher.group(), context)
            : null;
        if (type == null) {
          verbatim.append(current);
          index++;
        } else {
          appendText(appendable, verbatim.toString());
          verbatim.setLength(0);
          appendable.append(type);
          index = matcher.end();
        }
      }
    }
    appendText(appendable, verbatim.toString());
  }

  /**
   * Appends the given text, emitting its line breaks through the appendable's own line break API.
   * <p>
   * {@link ITreeAppendable#append(CharSequence)} follows every line break it finds in the appended text with the
   * appendable's current indentation, which turns the blank lines the body producers emit between the members of a
   * generated anonymous class into lines that consist of nothing but whitespace. {@link ITreeAppendable#blankLine()}
   * writes the line break without the indentation and is therefore used whenever the line that follows is empty.
   *
   * @param appendable
   *          the appendable to write to, must not be {@code null}
   * @param text
   *          the text to append, must not be {@code null}
   */
  private void appendText(final ITreeAppendable appendable, final String text) {
    int start = 0;
    int lineBreak = indexOfLineBreak(text, start);
    while (lineBreak >= 0) {
      appendable.append(text.substring(start, lineBreak));
      start = lineBreak + (text.startsWith("\r\n", lineBreak) ? 2 : 1); //$NON-NLS-1$
      // The indentation belongs to the line that follows the break, so that line decides how the break is written.
      // A text ending in a line break is always followed by more content, hence the indentation is wanted there.
      if (start < text.length() && isLineBreak(text.charAt(start))) {
        appendable.blankLine();
      } else {
        appendable.newLine();
      }
      lineBreak = indexOfLineBreak(text, start);
    }
    appendable.append(text.substring(start));
  }

  /**
   * Returns the position of the first line break at or after the given position.
   *
   * @param text
   *          the text to scan, must not be {@code null}
   * @param start
   *          the position to start scanning at
   * @return the position of the line break, or {@code -1} if the text contains none
   */
  private int indexOfLineBreak(final String text, final int start) {
    for (int i = start; i < text.length(); i++) {
      if (isLineBreak(text.charAt(i))) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Tests whether the given character starts a line break. Xtend templates use the platform's line separator, so both
   * {@code \n} and {@code \r\n} must be recognized.
   *
   * @param character
   *          the character to test
   * @return {@code true} if the character starts a line break
   */
  private boolean isLineBreak(final char character) {
    return character == '\n' || character == '\r';
  }

  /**
   * Resolves the given qualified name to a framework type that may be imported.
   *
   * @param name
   *          the qualified type name, must not be {@code null}
   * @param context
   *          the model element used to resolve the type against the classpath, must not be {@code null}
   * @return the resolved type, or {@code null} if it is not a framework type or cannot be resolved
   */
  private JvmType findFrameworkType(final String name, final EObject context) {
    if (FRAMEWORK_PACKAGE_PREFIXES.stream().noneMatch(name::startsWith)) {
      return null;
    }
    return typeReferences.findDeclaredType(name, context);
  }

  /**
   * Tests whether a qualified type name may start at the given position, i.e. whether the position is at the start of
   * an identifier rather than in the middle of one or behind a member access dot.
   *
   * @param body
   *          the Java source fragment, must not be {@code null}
   * @param index
   *          the position to test
   * @return {@code true} if a type name may start here
   */
  private boolean isTypeNameStart(final String body, final int index) {
    if (!Character.isJavaIdentifierStart(body.charAt(index))) {
      return false;
    }
    final char previous = index == 0 ? ' ' : body.charAt(index - 1);
    return previous != '.' && !Character.isJavaIdentifierPart(previous);
  }

  /**
   * Tests whether a line or block comment starts at the given position.
   *
   * @param body
   *          the Java source fragment, must not be {@code null}
   * @param index
   *          the position to test
   * @return {@code true} if a comment starts here
   */
  private boolean isCommentStart(final String body, final int index) {
    if (body.charAt(index) != '/' || index + 1 >= body.length()) {
      return false;
    }
    final char next = body.charAt(index + 1);
    return next == '/' || next == '*';
  }

  /**
   * Returns the position just after the string or character literal starting at the given position.
   *
   * @param body
   *          the Java source fragment, must not be {@code null}
   * @param start
   *          the position of the opening quote
   * @return the position after the closing quote, or the fragment's length if the literal is unterminated
   */
  private int endOfLiteral(final String body, final int start) {
    final char quote = body.charAt(start);
    int index = start + 1;
    while (index < body.length()) {
      final char current = body.charAt(index);
      if (current == '\\') {
        index += 2;
      } else if (current == quote) {
        return index + 1;
      } else {
        index++;
      }
    }
    return body.length();
  }

  /**
   * Returns the position just after the comment starting at the given position.
   *
   * @param body
   *          the Java source fragment, must not be {@code null}
   * @param start
   *          the position of the comment's first slash
   * @return the position after the comment, or the fragment's length if the comment is unterminated
   */
  private int endOfComment(final String body, final int start) {
    if (body.charAt(start + 1) == '/') {
      final int end = body.indexOf('\n', start);
      return end < 0 ? body.length() : end;
    }
    final int end = body.indexOf("*/", start + 2); //$NON-NLS-1$
    return end < 0 ? body.length() : end + 2;
  }

}
