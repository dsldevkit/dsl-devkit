/**
 */
package com.avaloq.tools.ddk.xtext.expression.expression;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.OperationCall#getParams <em>Params</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getOperationCall()
 * @model
 * @generated
 */
public interface OperationCall extends Expression, FeatureCall
{
  /**
   * Returns the value of the '<em><b>Params</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.expression.expression.Expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Params</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Params</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getOperationCall_Params()
   * @model containment="true"
   * @generated
   */
  EList<Expression> getParams();

} // OperationCall
