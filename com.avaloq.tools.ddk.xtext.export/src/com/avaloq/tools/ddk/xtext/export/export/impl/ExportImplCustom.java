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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAttribute;

import com.avaloq.tools.ddk.xtext.export.export.Attribute;
import com.avaloq.tools.ddk.xtext.export.export.Export;
import com.avaloq.tools.ddk.xtext.export.export.ExportModel;


/**
 * Custom implementation of user-defined EOperations and derived attributes.
 */
public class ExportImplCustom extends ExportImpl {

  @Override
  public EList<EAttribute> getEAttributes() {
    EList<EAttribute> result = new UniqueEList<EAttribute>();

    // First iterate through the field clause
    for (Attribute attribute : getAttributes()) {
      result.add(attribute.getAttribute());
    }

    return result;
  }

  @Override
  public EList<EAttribute> getAllEAttributes() {
    EList<EAttribute> result = getEAttributes();
    for (Export export : ((ExportModel) eContainer()).getExports()) {
      if (export.getType().isSuperTypeOf(this.getType())) {
        result.addAll(export.getEAttributes());
      }
    }
    return result;
  }
}
