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
package com.avaloq.tools.ddk.xtext.export.export.impl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;

import com.avaloq.tools.ddk.xtext.export.export.Attribute;
import com.avaloq.tools.ddk.xtext.export.export.Export;
import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall;


/**
 * Custom implementation of user-defined EOperations and derived attributes.
 */
public class ExportImplCustom extends ExportImpl {

  @Override
  public EAttribute getNamingAttribute() {
    Expression expression = getNaming();
    if (expression instanceof FeatureCall && ((FeatureCall) expression).getType() != null) {
      StringBuilder name = new StringBuilder();
      for (String identifier : ((FeatureCall) expression).getType().getId()) {
        name.append(name.length() > 0 ? "::" : ""); //$NON-NLS-1$ //$NON-NLS-2$
        name.append(identifier);
      }
      String qualifiedName = name.toString();
      final EClass type = getType();
      if (type != null) {
        EList<EAttribute> attributes = type.getEAllAttributes();
        for (EAttribute attribute : attributes) {
          if (attribute.getName().equals(qualifiedName)) { // NOPMD: AvoidDeeplyNestedIfStmts
            return attribute;
          }
        }
      }
    }
    return null;

  }

  @Override
  public EList<EAttribute> getEAttributes() {
    EList<EAttribute> result = new UniqueEList<EAttribute>();

    // First iterate through the field clause
    for (Attribute attribute : getAttributes()) {
      result.add(attribute.getAttribute());
    }

    // then add the naming clause
    if (getNamingAttribute() != null) {
      result.add(getNamingAttribute());
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
