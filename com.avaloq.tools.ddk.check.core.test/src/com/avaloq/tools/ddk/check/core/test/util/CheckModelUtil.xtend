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
package com.avaloq.tools.ddk.check.core.test.util

import java.util.List

/*
 * Provides utility operations for Check model stubs. Only partial models
 * are returned as strings.
 */
class CheckModelUtil {

  /* Returns a base model stub with package (com.test), catalog (c) and grammar (g). */
  def String modelWithGrammar () {'''
    package com.test
    catalog c for grammar g {'''.toString
  }

  /* Returns a base model stub with a default category. */
  def String modelWithCategory () {
    modelWithGrammar+ '''
      category "Default Category" {'''.toString
  }

  /* Returns a dummy category with given ID. */
  def String emptyCategory (String id, String label) { '''
    category «id» "«label»" {
    }'''.toString
  }

  /* Returns a base model stub with a severity range. */
  def String modelWithSeverityRange (String min, String max, String severity) {
    modelWithCategory + '''@SeverityRange(«min» .. «max»)
    «severity» ID "My Check" ()
    message "My Message"'''.toString
  }

  /* Returns a base model stub with a severity range and a default check. */
  def String modelWithSeverityRange (String min, String max) {
    modelWithCategory + '''@SeverityRange(«min» .. «max»)
    '''.toString + modelWithCheck
  }

  /* Returns a base model stub with a check of given ID. */
  def String modelWithCheck (String id) {
    modelWithCategory + '''
      error «id» "Some Error" ()
      message "My Message" {'''.toString
  }

  /*
   * Returns a base model stub with a check (SomeError) with severity 'error'
   * and message (MyMessage).
   */
  def String modelWithCheck () {
    modelWithCheck("ID")
  }

  /* Returns a dummy check with given ID. */
  def String emptyCheck(String id) { '''
    error «id» "Some Error" ()
    message "My message" {
    }'''.toString
  }

  /*
   * Returns a base model stub with a context using context type ContextType
   * 'ctx'.
   */
  def String modelWithContext() {
    modelWithCheck + '''
    for ContextType ctx {'''.toString
  }

  /* Returns a base model stub with a give collection of contexts. */
  def String modelWithContexts(List<String> contexts) {
    modelWithCheck + '''
    «FOR c:contexts»
      «c.toString»
    «ENDFOR»'''.toString
  }

  /* Returns a complete Check model with multiple SL_ and ML_COMMENTS */
  def String modelWithComments() {'''
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
  }'''.toString()

  }

}
