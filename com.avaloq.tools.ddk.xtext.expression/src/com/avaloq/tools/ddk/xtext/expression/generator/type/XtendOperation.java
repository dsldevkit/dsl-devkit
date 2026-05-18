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
package com.avaloq.tools.ddk.xtext.expression.generator.type;

import java.util.List;


/**
 * Local replacement for {@code org.eclipse.xtend.typesystem.Operation}.
 * Represents an operation on a type.
 */
public interface XtendOperation {

  /**
   * Returns the name of this operation.
   *
   * @return the operation name
   */
  String getName();

  /**
   * Returns the parameter types of this operation.
   *
   * @return list of parameter types
   */
  List<? extends XtendType> getParameterTypes();
}
