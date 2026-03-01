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
package com.avaloq.tools.ddk.check.typing;

import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.XGuardExpression;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XListLiteral;
import org.eclipse.xtext.xbase.annotations.typesystem.XbaseWithAnnotationsTypeComputer;
import org.eclipse.xtext.xbase.typesystem.computation.ITypeComputationState;

@SuppressWarnings({"checkstyle:MethodName"})
public class CheckTypeComputer extends XbaseWithAnnotationsTypeComputer {

  @Override
  public void computeTypes(final XExpression expression, final ITypeComputationState state) {
    if (expression instanceof XIssueExpression) {
      _computeTypes((XIssueExpression) expression, state);
    } else if (expression instanceof XGuardExpression) {
      _computeTypes((XGuardExpression) expression, state);
    } else if (expression.eContainer() instanceof FormalParameter && expression instanceof XListLiteral && ((XListLiteral) expression).getElements().isEmpty()) {
      super.computeTypes(expression, state.withExpectation(state.getReferenceOwner().toLightweightTypeReference(((FormalParameter) expression.eContainer()).getType())));
    } else {
      super.computeTypes(expression, state);
    }
  }

  protected void _computeTypes(final XIssueExpression expression, final ITypeComputationState state) {
    if (expression.getMarkerObject() != null) {
      state.withExpectation(getTypeForName(EObject.class, state)).computeTypes(expression.getMarkerObject());
    }
    if (expression.getMarkerIndex() != null) {
      state.withExpectation(getTypeForName(Integer.class, state)).computeTypes(expression.getMarkerIndex());
    }
    if (expression.getMessage() != null) {
      state.withExpectation(getTypeForName(String.class, state)).computeTypes(expression.getMessage());
    }
    for (XExpression p : expression.getMessageParameters()) {
      state.withExpectation(getTypeForName(Object.class, state)).computeTypes(p);
    }
    for (XExpression d : expression.getIssueData()) {
      state.withExpectation(getTypeForName(String.class, state)).computeTypes(d);
    }
    state.acceptActualType(getPrimitiveVoid(state));
  }

  protected void _computeTypes(final XGuardExpression expression, final ITypeComputationState state) {
    state.withExpectation(getTypeForName(Boolean.class, state)).computeTypes(expression.getGuard());
    state.acceptActualType(getPrimitiveVoid(state));
  }
}
