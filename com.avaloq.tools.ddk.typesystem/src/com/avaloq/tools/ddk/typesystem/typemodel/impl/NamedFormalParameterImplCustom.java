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
package com.avaloq.tools.ddk.typesystem.typemodel.impl;

import com.avaloq.tools.ddk.typesystem.typemodel.IType;
import com.avaloq.tools.ddk.typesystem.typemodel.TypeModelFactory;


/**
 * Implements default behavior for custom methods.
 */
public class NamedFormalParameterImplCustom extends NamedFormalParameterImpl {

  private IType type;

  @Override
  public boolean isMandatory() {
    return true;
  }

  @Override
  public boolean isMulti() {
    return false;
  }

  @Override
  public IType getType() {
    if (type == null) {
      type = TypeModelFactory.eINSTANCE.createNamedType();
    }
    return type;
  }

}

