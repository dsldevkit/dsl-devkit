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
package com.avaloq.tools.ddk.typesystem;

import com.avaloq.tools.ddk.typesystem.typemodel.IType;


/**
 * Provides methods for computing whether two types conform, usually for the purpose of type checking.
 * <p>
 * Conformance is a general term and there may be different ways in which two types may conform.
 * </p>
 */
public interface ITypeConformanceComputer {

  /**
   * Checks whether a value having a given type can be assigned to a location having a given type.
   * <p>
   * The parameter names {@code lType} and {@code rType} come from the corresponding concepts of l-value and r-value in programming language semantics. An
   * expression on the right-hand side of an assignment denotes a value referred to as an r-value. An expression the the left-hand side of an assignment denotes
   * a location referred to as an l-value. The actual meaning of "is assignable" depends on a programming language and may involve concepts such as subtyping
   * and/or implicit type casts. Note that, in many languages, binding an actual parameter to a formal parameter in a function/procedure call has the same
   * semantics as assignment.
   * </p>
   * 
   * @param lType
   *          the type of an l-value, must not be {@code null}.
   * @param rType
   *          the type of an r-value, must not be {@code null}.
   * @return true if a value having type {@code rType} can be assigned to a location having type {@code lType}.
   * @throws IncompleteTypeInformationException
   *           if {@code lType} or {@code rType} are incomplete.
   */
  boolean isAssignable(IType lType, IType rType) throws IncompleteTypeInformationException;

  /**
   * Checks if two operands of a relational operator can be compared.
   * <p>
   * An ordered relational operator is one of <, <=, >, >=. <br>
   * An unordered relational operator tests for equality or inequality.
   * </p>
   * 
   * @param lType
   *          the type of the left operand to compare, must not be {@code null}.
   * @param rType
   *          the type of the right operand to compare, must not be {@code null}.
   * @param isOrdered
   *          specifies whether the relation is ordered or not
   * @return returns true if {@code lType} and {@code rType} can be compared with respect to an
   *         ordered or unordered relation as specified by {@code isOrdered}.
   * @throws IncompleteTypeInformationException
   *           if {@code lType} or {@code rType} are incomplete.
   */
  boolean isComparable(final IType lType, final IType rType, final boolean isOrdered) throws IncompleteTypeInformationException;

}

