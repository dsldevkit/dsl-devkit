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

  public String basicModel(final String name) {
    return "check configuration " + name + " {";
  }

  public String basicModel() {
    return basicModel("testing");
  }

  public String basicModelWithCatalog() {
    return basicModel() + "catalog Sample {";
  }

  public String basicModelWithTest() {
    return basicModelWithCatalog() + "Test";
  }

  public String basicModelWithDisabledTest() {
    return basicModelWithCatalog() + "ignore Test";
  }
}
