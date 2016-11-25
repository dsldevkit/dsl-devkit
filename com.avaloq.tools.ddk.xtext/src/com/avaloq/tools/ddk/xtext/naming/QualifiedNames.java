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
package com.avaloq.tools.ddk.xtext.naming;

import java.util.Locale;

import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;


/**
 * Utility functions for {@link QualifiedName}.
 */
public final class QualifiedNames {

  private static final String EMPTY_STRING = ""; //$NON-NLS-1$
  private static final String UNRESOLVED_PREFIX = "unresolved "; //$NON-NLS-1$

  private static final IQualifiedNameConverter CONVERTER = new QualifiedNameConverter() {
    @Override
    public QualifiedName toQualifiedName(final String str) {
      if (str == null || str.length() == 0) {
        return null;
      }
      return super.toQualifiedName(str);
    }
  };

  private QualifiedNames() {
    // private
  }

  /**
   * Creates a simple unqualified name (i.e. one segment) for the given string.
   *
   * @param name
   *          the name
   * @return a unqualified name or null if the input string was null or empty
   */
  public static QualifiedName safeQualifiedName(final String name) {
    return CONVERTER.toQualifiedName(name);
  }

  /**
   * Determines whether the given name represents an unresolved name (unsed for imported names).
   *
   * @param name
   *          name to check
   * @return true if it is an unresolved name
   */
  public static boolean isUnresolvedName(final QualifiedName name) {
    return name.getSegmentCount() == 1 && name.getFirstSegment().startsWith(QualifiedNames.UNRESOLVED_PREFIX);
  }

  /**
   * Creates an unresolved name for the given string.
   *
   * @param name
   *          name; treated as a single segment
   * @return an unresolved qualified name
   */
  public static QualifiedName toUnresolvedName(final String name) {
    return CONVERTER.toQualifiedName(QualifiedNames.UNRESOLVED_PREFIX + name.toLowerCase(Locale.ENGLISH));
  }

  /**
   * Creates an unresolved name for the given qualified name (last segment taken only).
   *
   * @param name
   *          name
   * @return an unresolved qualified name
   */
  public static QualifiedName toUnresolvedName(final QualifiedName name) {
    return toUnresolvedName(name.isEmpty() ? EMPTY_STRING : name.getLastSegment());
  }
}
