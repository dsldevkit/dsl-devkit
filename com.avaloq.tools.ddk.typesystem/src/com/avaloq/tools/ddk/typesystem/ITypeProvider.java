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

import com.avaloq.tools.ddk.typesystem.typemodel.IExpression;
import com.avaloq.tools.ddk.typesystem.typemodel.INamedElement;
import com.avaloq.tools.ddk.typesystem.typemodel.IType;


/**
 * Interface for type provider.
 */
public interface ITypeProvider {

  /**
   * Gets the type of an expression.
   * <p>
   * An expression yields a typed value when evaluated. Given an expression, this method determines the type of these values. This usually involves inferring
   * the expression's type from its structure.
   * </p>
   * 
   * @param expression
   *          expression to get the type of.
   * @return the {@code expression}'s type. May return null if the type cannot be determined.
   */
  IType getType(IExpression expression);

  /**
   * Gets the expected type of an expression.
   * <p>
   * The expected type of an expression depends on the expression's context.<br>
   * An expression's context can be determined from the expression's location in its containing abstract syntax tree.<br>
   * </p>
   * 
   * @param expression
   *          the expression to get the expected type for
   * @return the {@code expression}'s expected type. May return null if there is not sufficient information to determine its type.
   */
  IType getExpectedType(IExpression expression);

  /**
   * Gets the type of a named element.
   * <p>
   * A named element usually corresponds to a declared element that, when referenced from an expression, will yield a typed value when that expression is
   * evaluated.<br>
   * Variable, function and field declarations are typical examples of named elements that yield a value.
   * </p>
   * 
   * @param element
   *          the named element to get the type of
   * @return the {@code element}'s type if it has one, null otherwise
   */
  IType getTypeForNamedElement(INamedElement element);

}
