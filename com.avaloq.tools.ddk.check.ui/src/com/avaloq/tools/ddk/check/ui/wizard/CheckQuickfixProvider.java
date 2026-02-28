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

public class CheckQuickfixProvider {

  public void doGenerate(final CheckProjectInfo info, final IFileSystemAccess fsa) {
    fsa.generateFile(fileName(info), fileContent(info));
  }

  public String fileName(final CheckProjectInfo info) {
    return info.getPath() + info.getCatalogName() + "QuickfixProvider.java";
  }

  public CharSequence fileContent(final CheckProjectInfo info) {
    final StringBuilder builder = new StringBuilder();
    builder.append("package ").append(info.getPackageName()).append(";\n");
    builder.append("\n");
    builder.append("import com.avaloq.tools.ddk.check.runtime.quickfix.ICoreQuickfixProvider;\n");
    builder.append("\n");
    builder.append("/**\n");
    builder.append(" * Default quickfix provider for ").append(info.getCatalogName()).append(".\n");
    builder.append(" * <p>\n");
    builder.append(" * Note that this class name must start with the catalog name and have <em>QuickfixProvider</em>\n");
    builder.append(" * as suffix. It must be located in the same Java package as the catalog file.\n");
    builder.append(" * </p>\n");
    builder.append(" */\n");
    builder.append("public class ").append(info.getCatalogName()).append("QuickfixProvider implements ICoreQuickfixProvider  {\n");
    builder.append("\n");
    builder.append("//  @CoreFix(value = MyIssueCodes.NAME_ENTITY_0)\n");
    builder.append("//  public void fixEntityNameFirstUpper(final Issue issue,\n");
    builder.append("//      ICoreIssueResolutionAcceptor acceptor) {\n");
    builder.append("//    acceptor.accept(issue, \"Correct entity name\",\n");
    builder.append("//        \"Correct name by setting first letter to upper case.\",\n");
    builder.append("//        null, new ICoreSemanticModification() {\n");
    builder.append("//          public void apply(EObject element, ICoreModificationContext context) {\n");
    builder.append("//            if (element instanceof Entity) {\n");
    builder.append("//              final Entity entity = (Entity) element;\n");
    builder.append("//              String newName = String.valueOf(entity.getName().charAt(0)).toUpperCase();\n");
    builder.append("//              if (entity.getName().length() > 1) {\n");
    builder.append("//                newName += entity.getName().substring(1, entity.getName().length());\n");
    builder.append("//              }\n");
    builder.append("//              entity.setName(newName);\n");
    builder.append("//            }\n");
    builder.append("//          }\n");
    builder.append("//        });\n");
    builder.append("//  }\n");
    builder.append("\n");
    builder.append("}\n");
    return builder;
  }
}
