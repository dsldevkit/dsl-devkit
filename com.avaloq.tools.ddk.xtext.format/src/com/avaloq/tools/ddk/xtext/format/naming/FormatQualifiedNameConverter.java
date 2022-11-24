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
package com.avaloq.tools.ddk.xtext.format.naming;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.util.Strings;


/**
 * Extension of the default implementation of the qualified name converter.
 */
public class FormatQualifiedNameConverter extends IQualifiedNameConverter.DefaultImpl {

  private static final Pattern STRING_PATTERN = Pattern.compile("\".*\"");

  @Override
  public String getDelimiter() {
    return ".";
  }

  @Override
  public QualifiedName toQualifiedName(final String qualifiedNameAsString) {
    if (qualifiedNameAsString == null || qualifiedNameAsString.equals("") || Strings.isEmpty(getDelimiter())) {
      return super.toQualifiedName(qualifiedNameAsString);
    }
    Matcher m = STRING_PATTERN.matcher(qualifiedNameAsString);
    if (m.find()) {
      String string = m.group();
      String before = qualifiedNameAsString.substring(0, qualifiedNameAsString.indexOf(string));
      String after = qualifiedNameAsString.substring(qualifiedNameAsString.indexOf(string) + string.length());
      String[] segments = segments(before);
      segments[segments.length - 1] = segments[segments.length - 1] + string; // NOPMD using StringBuffer here is silly IMHO
      segments = join(segments, segments(after));
      return QualifiedName.create(segments);
    }
    return super.toQualifiedName(qualifiedNameAsString);
  }

  /**
   * Joins two arrays forming an array containing elements from both.
   * The last head element and first tail element are concatenated and form a single element in teh resulting array, e.g.
   * join([a, b, c], [d, e, f]) => [a, b, cd, e, f].
   *
   * @param head
   *          the head
   * @param tail
   *          the tail
   * @return String[]
   */
  private String[] join(final String[] head, final String... tail) {
    if (tail.length == 0) {
      return head;
    } else if (head.length == 0) {
      return tail;
    }
    String[] result = new String[head.length + tail.length - 1];
    System.arraycopy(head, 0, result, 0, head.length);
    System.arraycopy(tail, 0, result, head.length - 1, tail.length);
    result[head.length - 1] = head[head.length - 1] + tail[0]; // NOPMD using StringBuffer here is silly IMHO
    return result;
  }

  /**
   * Split a string into segments. Segments are identified and split around {@link #getDelimiter()}.
   *
   * @param source
   *          the source - may not be null
   * @return String[]
   */
  private String[] segments(final String source) {
    return source.split(Pattern.quote(getDelimiter()));
  }
}
