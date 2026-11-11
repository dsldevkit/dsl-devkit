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
package com.avaloq.tools.ddk.xtext.naming;

import org.eclipse.xtext.naming.IQualifiedNameConverter.DefaultImpl;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.util.Strings;


/**
 * AcsQualifiedNamesConverter overrides {@link DefaultImpl} in order to use custom segment splitter.
 * This implementation is about 3 times faster than the (uncompiled) regexp splitter.
 */
public class QualifiedNameConverter extends DefaultImpl {

  private static final String DELIMITER = "."; //$NON-NLS-1$

  @Override
  public QualifiedName toQualifiedName(final String qualifiedNameAsString) {
    if (Strings.isEmpty(qualifiedNameAsString)) {
      return super.toQualifiedName(qualifiedNameAsString);
    }
    int firstDot = qualifiedNameAsString.indexOf('.');
    if (firstDot < 0) {
      return QualifiedName.create(qualifiedNameAsString);
    }
    int segmentCount = 2;
    for (int dot = qualifiedNameAsString.indexOf('.', firstDot + 1); dot >= 0; dot = qualifiedNameAsString.indexOf('.', dot + 1)) {
      segmentCount++;
    }
    // keeps all empty segments, like com.avaloq.tools.ddk.util.Strings.split
    String[] segments = new String[segmentCount];
    int start = 0;
    int end = firstDot;
    for (int i = 0; i < segmentCount - 1; i++) {
      segments[i] = qualifiedNameAsString.substring(start, end);
      start = end + 1;
      end = qualifiedNameAsString.indexOf('.', start);
    }
    segments[segmentCount - 1] = qualifiedNameAsString.substring(start);
    return QualifiedName.create(segments);
  }

  @Override
  public final String getDelimiter() {
    return DELIMITER;
  }

}
