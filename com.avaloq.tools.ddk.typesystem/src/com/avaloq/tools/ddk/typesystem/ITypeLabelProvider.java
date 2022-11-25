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
package com.avaloq.tools.ddk.typesystem;

import com.avaloq.tools.ddk.typesystem.typemodel.IType;


/**
 * A label provider for model elements that represent a type.
 */
public interface ITypeLabelProvider {

  /**
   * Gets the text label for a type.
   * 
   * @param type
   *          the type to get the label for, may be {@code null}
   * @return the label for the type
   */
  String getText(IType type);

}

