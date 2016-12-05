/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check.util;

import org.eclipse.xtext.IGrammarAccess;


/**
 * Utility operation for Grammar Access.
 */
public class GrammarAccessHelper extends GrammarHelper {

  /** The grammar access. */
  private final IGrammarAccess grammarAccess;

  public GrammarAccessHelper(final IGrammarAccess grammarAccess) {
    super(grammarAccess.getGrammar());
    this.grammarAccess = grammarAccess;
  }

  /**
   * Gets the bundle symbolic name.
   * 
   * @return the bundle symbolic name
   */
  public String getBundleSymbolicName() {
    Class<?> c = grammarAccess.getClass();
    return getBundleSymbolicName(c);
  }

}

