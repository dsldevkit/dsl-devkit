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
 * Local replacement for {@code org.eclipse.xtend.typesystem.Type}.
 * Represents a type in the expression type system.
 */
public interface XtendType {

  /**
   * Returns the name of this type.
   *
   * @return the type name
   */
  String getName();

  /**
   * Creates a new instance of this type.
   *
   * @return a new instance
   */
  Object newInstance();

  /**
   * Returns all operations defined on this type.
   *
   * @return list of operations
   */
  List<XtendOperation> getAllOperations();

  /**
   * Checks if this type is assignable from the given type.
   *
   * @param other
   *          the other type
   * @return {@code true} if this type is assignable from other
   */
  boolean isAssignableFrom(XtendType other);
}
