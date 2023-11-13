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
package com.avaloq.tools.ddk.xtext.export.export.impl;

import org.eclipse.emf.ecore.EPackage;


/**
 * Custom implementation of generated operations.
 */
public class ImportImplCustom extends ImportImpl {

  /** {@inheritDoc} Returns either the defined package alias, or the package's namespace prefix, or null if neither is set. */
  @Override
  public String getPackageName() {
    final String result = getName();
    if (result != null) {
      return result;
    }
    final EPackage p = getPackage();
    if (p != null) {
      return p.getNsPrefix();
    }
    return null;
  }

}
