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
package com.avaloq.tools.ddk.checkcfg.resource;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.xbase.jvmmodel.JvmLocationInFileProvider;

import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck;


/**
 * Assists in revealing a node model element in the editor.
 */
public class CheckCfgLocationInFileProvider extends JvmLocationInFileProvider {

  @Override
  protected EStructuralFeature getIdentifierFeature(final EObject object) {
    if (object instanceof ConfiguredCatalog) {
      return CheckcfgPackage.Literals.CONFIGURED_CATALOG__CATALOG;
    } else if (object instanceof ConfiguredCheck) {
      return CheckcfgPackage.Literals.CONFIGURED_CHECK__CHECK;
    }
    return super.getIdentifierFeature(object);
  }
}

