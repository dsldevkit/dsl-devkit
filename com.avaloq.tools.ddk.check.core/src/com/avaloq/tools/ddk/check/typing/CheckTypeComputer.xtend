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
package com.avaloq.tools.ddk.check.typing

import org.eclipse.xtext.xbase.annotations.typesystem.XbaseWithAnnotationsTypeComputer
import org.eclipse.xtext.xbase.typesystem.computation.ITypeComputationState
import org.eclipse.xtext.xbase.XExpression
import com.avaloq.tools.ddk.check.check.XIssueExpression
import com.avaloq.tools.ddk.check.check.XGuardExpression
import org.eclipse.emf.ecore.EObject
import com.avaloq.tools.ddk.check.check.FormalParameter
import org.eclipse.xtext.xbase.XListLiteral

class CheckTypeComputer extends XbaseWithAnnotationsTypeComputer {

  override computeTypes(XExpression expression, ITypeComputationState state) {
    if (expression instanceof XIssueExpression) {
      _computeTypes(expression as XIssueExpression, state);
    } else if (expression instanceof XGuardExpression) {
      _computeTypes(expression as XGuardExpression, state);
    } else if (expression.eContainer instanceof FormalParameter && expression instanceof XListLiteral && (expression as XListLiteral).elements.empty) {
      super.computeTypes(expression, state.withExpectation(state.referenceOwner.toLightweightTypeReference((expression.eContainer as FormalParameter).type)));
    } else {
      super.computeTypes(expression, state);
    }
  }

  protected def _computeTypes(XIssueExpression expression, ITypeComputationState state) {
    if (expression.markerObject != null) {
      state.withExpectation(getTypeForName(typeof(EObject), state)).computeTypes(expression.markerObject);
    }
    if (expression.markerIndex != null) {
      state.withExpectation(getTypeForName(typeof(Integer), state)).computeTypes(expression.markerIndex);
    }
    if (expression.message != null) {
      state.withExpectation(getTypeForName(typeof(String), state)).computeTypes(expression.message);
    }
    for (p : expression.messageParameters) {
      state.withExpectation(getTypeForName(typeof(Object), state)).computeTypes(p);
    }
    for (d : expression.issueData) {
      state.withExpectation(getTypeForName(typeof(String), state)).computeTypes(d);
    }
    state.acceptActualType(getPrimitiveVoid (state));
  }

  protected def _computeTypes(XGuardExpression expression, ITypeComputationState state) {
    state.withExpectation(getTypeForName(typeof(Boolean), state)).computeTypes(expression.guard);
    state.acceptActualType(getPrimitiveVoid (state));
  }

}
