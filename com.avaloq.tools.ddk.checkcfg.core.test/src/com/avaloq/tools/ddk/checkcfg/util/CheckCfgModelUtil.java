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
package com.avaloq.tools.ddk.checkcfg.util;

/*
 * Provides utility operations for Check Configuration model stubs. Only partial models
 * are returned as strings.
 */
public class CheckCfgModelUtil {

  /**
   * Returns a basic model with the given name.
   *
   * @param name the model name
   * @return the model string
   */
  public String basicModel(final String name) {
    return "check configuration " + name + " {";
  }

  /**
   * Returns a basic model with the default name.
   *
   * @return the model string
   */
  public String basicModel() {
    return basicModel("testing");
  }

  /**
   * Returns a basic model with a catalog.
   *
   * @return the model string
   */
  public String basicModelWithCatalog() {
    return basicModel() + "catalog Sample {";
  }

  /**
   * Returns a basic model with a catalog and a test.
   *
   * @return the model string
   */
  public String basicModelWithTest() {
    return basicModelWithCatalog() + "Test";
  }

  /**
   * Returns a basic model with a catalog and a disabled test.
   *
   * @return the model string
   */
  public String basicModelWithDisabledTest() {
    return basicModelWithCatalog() + "ignore Test";
  }
}
