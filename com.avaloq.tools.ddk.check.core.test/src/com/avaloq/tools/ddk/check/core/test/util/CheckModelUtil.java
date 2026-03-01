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
package com.avaloq.tools.ddk.check.core.test.util;

import java.util.List;


/**
 * Provides utility operations for Check model stubs. Only partial models
 * are returned as strings.
 */
public class CheckModelUtil {

  /**
   * Returns a base model stub with package (com.test), catalog (c) and grammar (g).
   */
  public String modelWithGrammar() {
    StringBuilder builder = new StringBuilder();
    builder.append("package com.test");
    builder.append("\n");
    builder.append("catalog c for grammar g {");
    return builder.toString();
  }

  /**
   * Returns a base model stub with a default category.
   */
  public String modelWithCategory() {
    String modelWithGrammar = this.modelWithGrammar();
    StringBuilder builder = new StringBuilder();
    builder.append("category \"Default Category\" {");
    return modelWithGrammar + builder.toString();
  }

  /**
   * Returns a dummy category with given ID.
   */
  public String emptyCategory(final String id, final String label) {
    StringBuilder builder = new StringBuilder();
    builder.append("category ");
    builder.append(id);
    builder.append(" \"");
    builder.append(label);
    builder.append("\" {\n");
    builder.append("}");
    return builder.toString();
  }

  /**
   * Returns a base model stub with a severity range.
   */
  public String modelWithSeverityRange(final String min, final String max, final String severity) {
    String modelWithCategory = this.modelWithCategory();
    StringBuilder builder = new StringBuilder();
    builder.append("@SeverityRange(");
    builder.append(min);
    builder.append(" .. ");
    builder.append(max);
    builder.append(")\n");
    builder.append("    ");
    builder.append(severity);
    builder.append(" ID \"My Check\" ()\n");
    builder.append("    ");
    builder.append("message \"My Message\"");
    return modelWithCategory + builder.toString();
  }

  /**
   * Returns a base model stub with a severity range and a default check.
   */
  public String modelWithSeverityRange(final String min, final String max) {
    String modelWithCategory = this.modelWithCategory();
    StringBuilder builder = new StringBuilder();
    builder.append("@SeverityRange(");
    builder.append(min);
    builder.append(" .. ");
    builder.append(max);
    builder.append(")\n");
    return modelWithCategory + builder.toString() + modelWithCheck();
  }

  /**
   * Returns a base model stub with a check of given ID.
   */
  public String modelWithCheck(final String id) {
    String modelWithCategory = this.modelWithCategory();
    StringBuilder builder = new StringBuilder();
    builder.append("error ");
    builder.append(id);
    builder.append(" \"Some Error\" ()\n");
    builder.append("message \"My Message\" {");
    return modelWithCategory + builder.toString();
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
    StringBuilder builder = new StringBuilder();
    builder.append("error ");
    builder.append(id);
    builder.append(" \"Some Error\" ()\n");
    builder.append("message \"My message\" {\n");
    builder.append("}");
    return builder.toString();
  }

  /**
   * Returns a base model stub with a context using context type ContextType
   * 'ctx'.
   */
  public String modelWithContext() {
    String modelWithCheck = this.modelWithCheck();
    StringBuilder builder = new StringBuilder();
    builder.append("for ContextType ctx {");
    return modelWithCheck + builder.toString();
  }

  /**
   * Returns a base model stub with a give collection of contexts.
   */
  public String modelWithContexts(final List<String> contexts) {
    String modelWithCheck = this.modelWithCheck();
    StringBuilder builder = new StringBuilder();
    for (final String c : contexts) {
      builder.append(c.toString());
      builder.append("\n");
      builder.append("    ");
    }
    return modelWithCheck + builder.toString();
  }

  /**
   * Returns a complete Check model with multiple SL_ and ML_COMMENTS.
   */
  public String modelWithComments() {
    StringBuilder builder = new StringBuilder();
    builder.append("package com.test // SL1\n");
    builder.append("/* ML1 */\n");
    builder.append("catalog c /* ML2 */ for grammar g {\n");
    builder.append("  // SL2\n");
    builder.append("  category \"My cat\" {\n");
    builder.append("    /* ML3 */\n");
    builder.append("    // SL3\n");
    builder.append("    error MYerr \"My Err\" (int Abc = 23) message \"A\" {\n");
    builder.append("      for Atype thisName {\n");
    builder.append("        val x = 3 // SL4\n");
    builder.append("        // SL5\n");
    builder.append("        /* ML5 */ issue /* ML4 */\n");
    builder.append("        // SL6\n");
    builder.append("      }\n");
    builder.append("    }\n");
    builder.append("  } // SL7\n");
    builder.append("}");
    return builder.toString();
  }
}
