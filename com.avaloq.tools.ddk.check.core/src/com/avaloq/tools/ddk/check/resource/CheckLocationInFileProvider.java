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
package com.avaloq.tools.ddk.check.resource;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.xbase.jvmmodel.JvmLocationInFileProvider;

import com.avaloq.tools.ddk.check.check.Category;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.avaloq.tools.ddk.check.check.Context;


/**
 * Provides structural features used for determining which tokens to select in an editor
 * once a model element is revealed.
 */
public class CheckLocationInFileProvider extends JvmLocationInFileProvider {

  @Override
  public EStructuralFeature getIdentifierFeature(final EObject object) {
    if (object instanceof Check) {
      if (((Check) object).getId() != null) {
        return CheckPackage.Literals.CHECK__ID;
      } else {
        return CheckPackage.Literals.CHECK__LABEL;
      }
    } else if (object instanceof Category) {
      if (((Category) object).getId() != null) {
        return CheckPackage.Literals.CATEGORY__ID;
      } else {
        return CheckPackage.Literals.CATEGORY__LABEL;
      }
    } else if (object instanceof Context) {
      return CheckPackage.Literals.CONTEXT__CONTEXT_VARIABLE;
    }
    return super.getIdentifierFeature(object);
  }

}

