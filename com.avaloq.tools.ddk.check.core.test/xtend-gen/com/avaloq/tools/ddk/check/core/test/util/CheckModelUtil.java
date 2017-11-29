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
package com.avaloq.tools.ddk.check.core.test.util;

import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

/**
 * Provides utility operations for Check model stubs. Only partial models
 * are returned as strings.
 */
@SuppressWarnings("all")
public class CheckModelUtil {
  /**
   * Returns a base model stub with package (com.test), catalog (c) and grammar (g).
   */
  public String modelWithGrammar() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package com.test");
    _builder.newLine();
    _builder.append("catalog c for grammar g {");
    return _builder.toString();
  }
  
  /**
   * Returns a base model stub with a default category.
   */
  public String modelWithCategory() {
    String _modelWithGrammar = this.modelWithGrammar();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("category \"Default Category\" {");
    String _string = _builder.toString();
    return (_modelWithGrammar + _string);
  }
  
  /**
   * Returns a dummy category with given ID.
   */
  public String emptyCategory(final String id, final String label) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("category ");
    _builder.append(id, "");
    _builder.append(" \"");
    _builder.append(label, "");
    _builder.append("\" {");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    return _builder.toString();
  }
  
  /**
   * Returns a base model stub with a severity range.
   */
  public String modelWithSeverityRange(final String min, final String max, final String severity) {
    String _modelWithCategory = this.modelWithCategory();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@SeverityRange(");
    _builder.append(min, "");
    _builder.append(" .. ");
    _builder.append(max, "");
    _builder.append(")");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append(severity, "    ");
    _builder.append(" ID \"My Check\" ()");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("message \"My Message\"");
    String _string = _builder.toString();
    return (_modelWithCategory + _string);
  }
  
  /**
   * Returns a base model stub with a severity range and a default check.
   */
  public String modelWithSeverityRange(final String min, final String max) {
    String _modelWithCategory = this.modelWithCategory();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@SeverityRange(");
    _builder.append(min, "");
    _builder.append(" .. ");
    _builder.append(max, "");
    _builder.append(")");
    _builder.newLineIfNotEmpty();
    String _string = _builder.toString();
    String _plus = (_modelWithCategory + _string);
    String _modelWithCheck = this.modelWithCheck();
    return (_plus + _modelWithCheck);
  }
  
  /**
   * Returns a base model stub with a check of given ID.
   */
  public String modelWithCheck(final String id) {
    String _modelWithCategory = this.modelWithCategory();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("error ");
    _builder.append(id, "");
    _builder.append(" \"Some Error\" ()");
    _builder.newLineIfNotEmpty();
    _builder.append("message \"My Message\" {");
    String _string = _builder.toString();
    return (_modelWithCategory + _string);
  }
  
  /**
   * Returns a base model stub with a check (SomeError) with severity 'error'
   * and message (MyMessage).
   */
  public String modelWithCheck() {
    return this.modelWithCheck("ID");
  }
  
  /**
   * Returns a dummy check with given ID.
   */
  public String emptyCheck(final String id) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("error ");
    _builder.append(id, "");
    _builder.append(" \"Some Error\" ()");
    _builder.newLineIfNotEmpty();
    _builder.append("message \"My message\" {");
    _builder.newLine();
    _builder.append("}");
    return _builder.toString();
  }
  
  /**
   * Returns a base model stub with a context using context type ContextType
   * 'ctx'.
   */
  public String modelWithContext() {
    String _modelWithCheck = this.modelWithCheck();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("for ContextType ctx {");
    String _string = _builder.toString();
    return (_modelWithCheck + _string);
  }
  
  /**
   * Returns a base model stub with a give collection of contexts.
   */
  public String modelWithContexts(final List<String> contexts) {
    String _modelWithCheck = this.modelWithCheck();
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final String c : contexts) {
        String _string = c.toString();
        _builder.append(_string, "");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
      }
    }
    String _string_1 = _builder.toString();
    return (_modelWithCheck + _string_1);
  }
  
  /**
   * Returns a complete Check model with multiple SL_ and ML_COMMENTS
   */
  public String modelWithComments() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package com.test // SL1");
    _builder.newLine();
    _builder.append("/* ML1 */");
    _builder.newLine();
    _builder.append("catalog c /* ML2 */ for grammar g {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("// SL2");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("category \"My cat\" {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("/* ML3 */");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("// SL3");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("error MYerr \"My Err\" (int Abc = 23) message \"A\" {");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("for Atype thisName {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("val x = 3 // SL4");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// SL5");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("/* ML5 */ issue /* ML4 */");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// SL6");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("} // SL7");
    _builder.newLine();
    _builder.append("}");
    return _builder.toString();
  }
}
