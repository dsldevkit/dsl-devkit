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
package com.avaloq.tools.ddk.checkcfg.util

/*
 * Provides utility operations for Check Configuration model stubs. Only partial models
 * are returned as strings.
 */
class CheckCfgModelUtil {

  def String basicModel(String name) {'''
    check configuration «name» {'''.toString
  }

  def String basicModel() {
    basicModel("testing")
  }

  def String basicModelWithCatalog() {
    basicModel + '''
      catalog Sample {'''.toString
  }

  def String basicModelWithTest() {
    basicModelWithCatalog + '''
      Test'''.toString
  }

  def String basicModelWithDisabledTest() {
    basicModelWithCatalog + '''
      ignore Test'''.toString
  }

}
