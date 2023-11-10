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
package com.avaloq.tools.ddk.xtext.scope.resource;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.resource.DefaultLocationInFileProvider;

import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;


/**
 * Location in file provider.
 */
public class ScopeLocationInFileProvider extends DefaultLocationInFileProvider {

  @Override
  protected EStructuralFeature getIdentifierFeature(final EObject obj) {
    if (obj instanceof ScopeDefinition) {
      return ((ScopeDefinition) obj).getContextType() != null ? ScopePackage.Literals.SCOPE_DEFINITION__CONTEXT_TYPE
          : ScopePackage.Literals.SCOPE_DEFINITION__TARGET_TYPE;
    }
    return super.getIdentifierFeature(obj);
  }

}
