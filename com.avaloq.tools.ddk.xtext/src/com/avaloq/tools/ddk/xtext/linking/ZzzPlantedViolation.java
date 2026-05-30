/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.linking;

/**
 * TEMPORARY CI probe — plants one violation per analyzer to validate SARIF inline
 * display + the count-gates. DO NOT MERGE; reverted immediately after observation.
 */
public final class ZzzPlantedViolation {

  /** Trips Checkstyle (EmptyStatement), PMD (SystemPrintln), SpotBugs (null deref). */
  public void trigger() {
    ; // Checkstyle: EmptyStatement
    final String s = null;
    System.out.println(s.length()); // PMD: SystemPrintln ; SpotBugs: NP_ALWAYS_NULL
  }
}
