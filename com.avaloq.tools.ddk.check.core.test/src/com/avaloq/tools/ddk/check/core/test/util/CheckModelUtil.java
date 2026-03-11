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


// CHECKSTYLE:CONSTANTS-OFF
/**
 * Provides utility operations for Check model stubs. Only partial models
 * are returned as strings.
 */
public class CheckModelUtil {

  /**
   * Returns a base model stub with package (com.test), catalog (c) and grammar (g).
   *
   * @return the model stub string
   */
  public String modelWithGrammar() {
    return "package com.test\n" + "catalog c for grammar g {";
  }

  /**
   * Returns a base model stub with a default category.
   *
   * @return the model stub string
   */
  public String modelWithCategory() {
    return this.modelWithGrammar() + "category \"Default Category\" {";
  }

  /**
   * Returns a dummy category with given ID.
   *
   * @param id
   *          the category ID
   * @param label
   *          the category label
   * @return the category string
   */
  public String emptyCategory(final String id, final String label) {
    return String.format("category %s \"%s\" {\n}", id, label);
  }

  /**
   * Returns a base model stub with a severity range.
   *
   * @param min
   *          the minimum severity
   * @param max
   *          the maximum severity
   * @param severity
   *          the default severity
   * @return the model stub string
   */
  public String modelWithSeverityRange(final String min, final String max, final String severity) {
    return this.modelWithCategory() + String.format("@SeverityRange(%s .. %s)\n    %s ID \"My Check\" ()\n    message \"My Message\"", min, max, severity);
  }

  /**
   * Returns a base model stub with a severity range and a default check.
   *
   * @param min
   *          the minimum severity
   * @param max
   *          the maximum severity
   * @return the model stub string
   */
  public String modelWithSeverityRange(final String min, final String max) {
    return this.modelWithCategory() + String.format("@SeverityRange(%s .. %s)\n", min, max) + modelWithCheck();
  }

  /**
   * Returns a base model stub with a check of given ID.
   *
   * @param id
   *          the check ID
   * @return the model stub string
   */
  public String modelWithCheck(final String id) {
    return this.modelWithCategory() + String.format("error %s \"Some Error\" ()\nmessage \"My Message\" {", id);
  }

  /**
   * Returns a base model stub with a check (SomeError) with severity 'error'
   * and message (MyMessage).
   *
   * @return the model stub string
   */
  public String modelWithCheck() {
    return this.modelWithCheck("ID");
  }

  /**
   * Returns a dummy check with given ID.
   *
   * @param id
   *          the check ID
   * @return the check string
   */
  public String emptyCheck(final String id) {
    return String.format("error %s \"Some Error\" ()\nmessage \"My message\" {\n}", id);
  }

  /**
   * Returns a base model stub with a context using context type ContextType
   * 'ctx'.
   *
   * @return the model stub string
   */
  public String modelWithContext() {
    return this.modelWithCheck() + "for ContextType ctx {";
  }

  /**
   * Returns a base model stub with a give collection of contexts.
   *
   * @param contexts
   *          the list of context strings
   * @return the model stub string
   */
  public String modelWithContexts(final List<String> contexts) {
    String modelWithCheck = this.modelWithCheck();
    StringBuilder builder = new StringBuilder(512);
    for (final String c : contexts) {
      builder.append(c);
      builder.append('\n');
      builder.append("    ");
    }
    return modelWithCheck + builder.toString();
  }

  /**
   * Returns a complete Check model with multiple SL_ and ML_COMMENTS.
   *
   * @return the model stub string
   */
  public String modelWithComments() {
    return """
        package com.test // SL1
        /* ML1 */
        catalog c /* ML2 */ for grammar g {
          // SL2
          category "My cat" {
            /* ML3 */
            // SL3
            error MYerr "My Err" (int Abc = 23) message "A" {
              for Atype thisName {
                val x = 3 // SL4
                // SL5
                /* ML5 */ issue /* ML4 */
                // SL6
              }
            }
          } // SL7
        }""";
  }
}
// CHECKSTYLE:CONSTANTS-ON
