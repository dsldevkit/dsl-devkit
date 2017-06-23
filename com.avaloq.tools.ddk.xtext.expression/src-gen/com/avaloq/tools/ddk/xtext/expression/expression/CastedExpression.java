/**
 */
package com.avaloq.tools.ddk.xtext.expression.expression;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Casted Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.CastedExpression#getType <em>Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.CastedExpression#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getCastedExpression()
 * @model
 * @generated
 */
public interface CastedExpression extends Expression
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference.
   * @see #setType(Identifier)
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getCastedExpression_Type()
   * @model containment="true"
   * @generated
   */
  Identifier getType();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.CastedExpression#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(Identifier value);

  /**
   * Returns the value of the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' containment reference.
   * @see #setTarget(Expression)
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getCastedExpression_Target()
   * @model containment="true"
   * @generated
   */
  Expression getTarget();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.CastedExpression#getTarget <em>Target</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' containment reference.
   * @see #getTarget()
   * @generated
   */
  void setTarget(Expression value);

} // CastedExpression
