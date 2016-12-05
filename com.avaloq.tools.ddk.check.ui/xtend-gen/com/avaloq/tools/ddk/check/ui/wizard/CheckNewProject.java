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
import com.google.common.base.Objects;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.generator.IFileSystemAccess;

@SuppressWarnings("all")
public class CheckNewProject {
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
    String _plus_1 = (_plus + ".check");
    _builder.append(_plus_1, "");
    return _builder.toString();
  }
  
  public CharSequence fileContent(final CheckProjectInfo info) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    String _packageName = info.getPackageName();
    _builder.append(_packageName, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _fileImports = this.fileImports(info);
    _builder.append(_fileImports, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*  Check catalog for ");
    Grammar _grammar = info.getGrammar();
    String _name = _grammar.getName();
    _builder.append(_name, " ");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("catalog ");
    String _catalogName = info.getCatalogName();
    _builder.append(_catalogName, "");
    _builder.newLineIfNotEmpty();
    _builder.append("for grammar ");
    Grammar _grammar_1 = info.getGrammar();
    String _name_1 = _grammar_1.getName();
    _builder.append(_name_1, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("  ");
    _builder.append("// Add categories and checks");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence fileImports(final CheckProjectInfo info) {
    CharSequence _xifexpression = null;
    String _defaultPackageImport = info.getDefaultPackageImport();
    boolean _notEquals = (!Objects.equal(_defaultPackageImport, null));
    if (_notEquals) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("import ");
      String _defaultPackageImport_1 = info.getDefaultPackageImport();
      _builder.append(_defaultPackageImport_1, "");
      _builder.append(".* ");
      _xifexpression = _builder;
    } else {
      _xifexpression = "";
    }
    return _xifexpression;
  }
}
