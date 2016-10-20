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

import java.util.List;

import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameConverter.DefaultImpl;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.util.Strings;


/**
 * DdkQualifiedNamesConverter overrides {@link DefaultImpl} in order to use custom segment splitter.
 * This implementation is about 3 times faster than the (uncompiled) regexp splitter.
 */
public class DdkQualifiedNameConverter extends DefaultImpl implements IQualifiedNameConverter {

  private static final String DELIMITER = "."; //$NON-NLS-1$

  @Override
  public QualifiedName toQualifiedName(final String qualifiedNameAsString) {
    if (Strings.isEmpty(qualifiedNameAsString)) {
      return super.toQualifiedName(qualifiedNameAsString);
    }
    List<String> segs = Strings.split(qualifiedNameAsString, '.');
    return QualifiedName.create(segs);
  }

  @Override
  public final String getDelimiter() {
    return DELIMITER;
  }

}
