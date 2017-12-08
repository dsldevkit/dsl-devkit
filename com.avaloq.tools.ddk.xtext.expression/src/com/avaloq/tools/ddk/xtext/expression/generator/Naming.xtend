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

package com.avaloq.tools.ddk.xtext.expression.generator

import org.eclipse.xtext.util.Strings

class Naming {

  def toFileName(String qualifiedName) {
    qualifiedName.toJavaPackage.replace('.', '/') + '/' + qualifiedName.toSimpleName + ".java"
  }

  def toJavaPackage(String qualifiedName) {
    Strings.skipLastToken(qualifiedName, '.')
  }

  def toSimpleName(String qualifiedName) {
    Strings.lastToken(qualifiedName, '.')
  }

}
