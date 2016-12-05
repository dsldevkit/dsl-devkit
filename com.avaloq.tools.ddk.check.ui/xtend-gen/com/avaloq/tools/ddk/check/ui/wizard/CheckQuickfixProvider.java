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
package com.avaloq.tools.ddk.check.ui.wizard;

import com.avaloq.tools.ddk.check.ui.wizard.CheckProjectInfo;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;

@SuppressWarnings("all")
public class CheckQuickfixProvider {
  public void doGenerate(final CheckProjectInfo info, final IFileSystemAccess fsa) {
    String _fileName = this.fileName(info);
    CharSequence _fileContent = this.fileContent(info);
    fsa.generateFile(_fileName, _fileContent);
  }
  
  public String fileName(final CheckProjectInfo info) {
    StringConcatenation _builder = new StringConcatenation();
    String _path = info.getPath();
    String _catalogName = info.getCatalogName();
    String _plus = (_path + _catalogName);
    String _plus_1 = (_plus + "QuickfixProvider.java");
    _builder.append(_plus_1, "");
    return _builder.toString();
  }
  
  public CharSequence fileContent(final CheckProjectInfo info) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    String _packageName = info.getPackageName();
    _builder.append(_packageName, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import com.avaloq.tools.ddk.check.runtime.quickfix.ICoreQuickfixProvider;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Default quickfix provider for ");
    String _catalogName = info.getCatalogName();
    _builder.append(_catalogName, " ");
    _builder.append(".");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("* <p>");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Note that this class name must start with the catalog name and have <em>QuickfixProvider</em>");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* as suffix. It must be located in the same Java package as the catalog file.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* </p>");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class ");
    String _catalogName_1 = info.getCatalogName();
    _builder.append(_catalogName_1, "");
    _builder.append("QuickfixProvider implements ICoreQuickfixProvider  {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("//  @CoreFix(value = MyIssueCodes.NAME_ENTITY_0)");
    _builder.newLine();
    _builder.append("//  public void fixEntityNameFirstUpper(final Issue issue,");
    _builder.newLine();
    _builder.append("//      ICoreIssueResolutionAcceptor acceptor) {");
    _builder.newLine();
    _builder.append("//    acceptor.accept(issue, \"Correct entity name\",");
    _builder.newLine();
    _builder.append("//        \"Correct name by setting first letter to upper case.\",");
    _builder.newLine();
    _builder.append("//        null, new ICoreSemanticModification() {");
    _builder.newLine();
    _builder.append("//          public void apply(EObject element, ICoreModificationContext context) {");
    _builder.newLine();
    _builder.append("//            if (element instanceof Entity) {");
    _builder.newLine();
    _builder.append("//              final Entity entity = (Entity) element;");
    _builder.newLine();
    _builder.append("//              String newName = String.valueOf(entity.getName().charAt(0)).toUpperCase();");
    _builder.newLine();
    _builder.append("//              if (entity.getName().length() > 1) {");
    _builder.newLine();
    _builder.append("//                newName += entity.getName().substring(1, entity.getName().length());");
    _builder.newLine();
    _builder.append("//              }");
    _builder.newLine();
    _builder.append("//              entity.setName(newName);");
    _builder.newLine();
    _builder.append("//            }");
    _builder.newLine();
    _builder.append("//          }");
    _builder.newLine();
    _builder.append("//        });");
    _builder.newLine();
    _builder.append("//  }");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
