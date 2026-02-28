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
package com.avaloq.tools.ddk.check.ui.wizard;

import org.eclipse.xtext.generator.IFileSystemAccess;

public class CheckNewProject {

  public void doGenerate(final CheckProjectInfo info, final IFileSystemAccess fsa) {
    fsa.generateFile(fileName(info), fileContent(info));
  }

  public String fileName(final CheckProjectInfo info) {
    return info.getPath() + info.getCatalogName() + ".check";
  }

  public CharSequence fileContent(final CheckProjectInfo info) {
    final StringBuilder builder = new StringBuilder();
    builder.append("package ").append(info.getPackageName()).append("\n");
    builder.append("\n");
    builder.append(fileImports(info));
    builder.append("\n");
    builder.append("\n");
    builder.append("/**\n");
    builder.append(" *  Check catalog for ").append(info.getGrammar().getName()).append("\n");
    builder.append(" */\n");
    builder.append("catalog ").append(info.getCatalogName()).append("\n");
    builder.append("for grammar ").append(info.getGrammar().getName()).append(" {\n");
    builder.append("\n");
    builder.append("  // Add categories and checks\n");
    builder.append("\n");
    builder.append("}\n");
    return builder;
  }

  public CharSequence fileImports(final CheckProjectInfo info) {
    // package where top-level grammar rule interfaces are defined
    if (info.getDefaultPackageImport() != null) {
      return "import " + info.getDefaultPackageImport() + ".* ";
    } else {
      return "";
    }
  }
}
