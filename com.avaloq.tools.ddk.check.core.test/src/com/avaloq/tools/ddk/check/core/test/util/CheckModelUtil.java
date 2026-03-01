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

/*
 * Provides utility operations for Check model stubs. Only partial models
 * are returned as strings.
 */
@SuppressWarnings("nls")
public class CheckModelUtil {

  /* Returns a base model stub with package (com.test), catalog (c) and grammar (g). */
  public String modelWithGrammar() {
    return """
        package com.test
        catalog c for grammar g {""";
  }

  /* Returns a base model stub with a default category. */
  public String modelWithCategory() {
    return modelWithGrammar() + """
        category "Default Category" {""";
  }

  /* Returns a dummy category with given ID. */
  public String emptyCategory(final String id, final String label) {
    return """
        category %s "%s" {
        }""".formatted(id, label);
  }

  /* Returns a base model stub with a severity range. */
  public String modelWithSeverityRange(final String min, final String max, final String severity) {
    return modelWithCategory() + """
        @SeverityRange(%s .. %s)
            %s ID "My Check" ()
            message "My Message\"""".formatted(min, max, severity);
  }

  /* Returns a base model stub with a severity range and a default check. */
  public String modelWithSeverityRange(final String min, final String max) {
    return modelWithCategory() + """
        @SeverityRange(%s .. %s)
        """.formatted(min, max) + modelWithCheck();
  }

  /* Returns a base model stub with a check of given ID. */
  public String modelWithCheck(final String id) {
    return modelWithCategory() + """
        error %s "Some Error" ()
        message "My Message" {""".formatted(id);
  }
  // CHECKSTYLE:CHECK-ON VariableDeclarationUsageDistance

  /*
   * Returns a base model stub with a check (SomeError) with severity 'error'
   * and message (MyMessage).
   *
   * @return the model stub string
   */
  public String modelWithCheck() {
    return modelWithCheck("ID");
  }

  /* Returns a dummy check with given ID. */
  public String emptyCheck(final String id) {
    return """
        error %s "Some Error" ()
        message "My message" {
        }""".formatted(id);
  }

  /*
   * Returns a base model stub with a context using context type ContextType
   * 'ctx'.
   *
   * @return the model stub string
   */
  public String modelWithContext() {
    return modelWithCheck() + "for ContextType ctx {";
  }

  /* Returns a base model stub with a give collection of contexts. */
  public String modelWithContexts(final List<String> contexts) {
    final StringBuilder builder = new StringBuilder(512);
    for (final String c : contexts) {
      builder.append(c).append("\n    ");
    }
    return modelWithCheck() + builder.toString();
  }

  /* Returns a complete Check model with multiple SL_ and ML_COMMENTS */
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
