/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.xtext.generator.util;

import org.eclipse.xtext.util.Strings;

@SuppressWarnings("all")
public class Naming {
  public String toJavaPackage(final String qualifiedName) {
    return Strings.skipLastToken(qualifiedName, ".");
  }
  
  public String toSimpleName(final String qualifiedName) {
    return Strings.lastToken(qualifiedName, ".");
  }
}
