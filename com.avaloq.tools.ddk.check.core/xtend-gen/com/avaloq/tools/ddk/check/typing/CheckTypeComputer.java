/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.check.typing;

import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.XGuardExpression;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.google.common.base.Objects;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XListLiteral;
import org.eclipse.xtext.xbase.annotations.typesystem.XbaseWithAnnotationsTypeComputer;
import org.eclipse.xtext.xbase.typesystem.computation.ITypeComputationState;
import org.eclipse.xtext.xbase.typesystem.references.ITypeReferenceOwner;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference;

@SuppressWarnings("all")
public class CheckTypeComputer extends XbaseWithAnnotationsTypeComputer {
  @Override
  public void computeTypes(final XExpression expression, final ITypeComputationState state) {
    if ((expression instanceof XIssueExpression)) {
      this._computeTypes(((XIssueExpression) expression), state);
    } else {
      if ((expression instanceof XGuardExpression)) {
        this._computeTypes(((XGuardExpression) expression), state);
      } else {
        if ((((expression.eContainer() instanceof FormalParameter) && (expression instanceof XListLiteral)) && ((XListLiteral) expression).getElements().isEmpty())) {
          ITypeReferenceOwner _referenceOwner = state.getReferenceOwner();
          EObject _eContainer = expression.eContainer();
          JvmTypeReference _type = ((FormalParameter) _eContainer).getType();
          LightweightTypeReference _lightweightTypeReference = _referenceOwner.toLightweightTypeReference(_type);
          ITypeComputationState _withExpectation = state.withExpectation(_lightweightTypeReference);
          super.computeTypes(expression, _withExpectation);
        } else {
          super.computeTypes(expression, state);
        }
      }
    }
  }
  
  protected void _computeTypes(final XIssueExpression expression, final ITypeComputationState state) {
    XExpression _markerObject = expression.getMarkerObject();
    boolean _notEquals = (!Objects.equal(_markerObject, null));
    if (_notEquals) {
      LightweightTypeReference _typeForName = this.getTypeForName(EObject.class, state);
      ITypeComputationState _withExpectation = state.withExpectation(_typeForName);
      XExpression _markerObject_1 = expression.getMarkerObject();
      _withExpectation.computeTypes(_markerObject_1);
    }
    XExpression _markerIndex = expression.getMarkerIndex();
    boolean _notEquals_1 = (!Objects.equal(_markerIndex, null));
    if (_notEquals_1) {
      LightweightTypeReference _typeForName_1 = this.getTypeForName(Integer.class, state);
      ITypeComputationState _withExpectation_1 = state.withExpectation(_typeForName_1);
      XExpression _markerIndex_1 = expression.getMarkerIndex();
      _withExpectation_1.computeTypes(_markerIndex_1);
    }
    XExpression _message = expression.getMessage();
    boolean _notEquals_2 = (!Objects.equal(_message, null));
    if (_notEquals_2) {
      LightweightTypeReference _typeForName_2 = this.getTypeForName(String.class, state);
      ITypeComputationState _withExpectation_2 = state.withExpectation(_typeForName_2);
      XExpression _message_1 = expression.getMessage();
      _withExpectation_2.computeTypes(_message_1);
    }
    EList<XExpression> _messageParameters = expression.getMessageParameters();
    for (final XExpression p : _messageParameters) {
      LightweightTypeReference _typeForName_3 = this.getTypeForName(Object.class, state);
      ITypeComputationState _withExpectation_3 = state.withExpectation(_typeForName_3);
      _withExpectation_3.computeTypes(p);
    }
    EList<XExpression> _issueData = expression.getIssueData();
    for (final XExpression d : _issueData) {
      LightweightTypeReference _typeForName_4 = this.getTypeForName(String.class, state);
      ITypeComputationState _withExpectation_4 = state.withExpectation(_typeForName_4);
      _withExpectation_4.computeTypes(d);
    }
    LightweightTypeReference _primitiveVoid = this.getPrimitiveVoid(state);
    state.acceptActualType(_primitiveVoid);
  }
  
  protected void _computeTypes(final XGuardExpression expression, final ITypeComputationState state) {
    LightweightTypeReference _typeForName = this.getTypeForName(Boolean.class, state);
    ITypeComputationState _withExpectation = state.withExpectation(_typeForName);
    XExpression _guard = expression.getGuard();
    _withExpectation.computeTypes(_guard);
    LightweightTypeReference _primitiveVoid = this.getPrimitiveVoid(state);
    state.acceptActualType(_primitiveVoid);
  }
}
