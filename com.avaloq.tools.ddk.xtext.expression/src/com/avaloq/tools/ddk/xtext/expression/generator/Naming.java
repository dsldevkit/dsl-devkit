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

import org.eclipse.xtext.util.Strings;

public class Naming {

  // CHECKSTYLE:CONSTANTS-OFF
  public String toFileName(final String qualifiedName) {
    return toJavaPackage(qualifiedName).replace('.', '/') + '/' + toSimpleName(qualifiedName) + ".java";
  }

  public String toJavaPackage(final String qualifiedName) {
    return Strings.skipLastToken(qualifiedName, ".");
  }

  public String toSimpleName(final String qualifiedName) {
    return Strings.lastToken(qualifiedName, ".");
  }
  // CHECKSTYLE:CONSTANTS-ON
}
