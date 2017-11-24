/**
 */
package com.avaloq.tools.ddk.xtext.expression.expression;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constructor Call Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.ConstructorCallExpression#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getConstructorCallExpression()
 * @model
 * @generated
 */
public interface ConstructorCallExpression extends Expression
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
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getConstructorCallExpression_Type()
   * @model containment="true"
   * @generated
   */
  Identifier getType();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.ConstructorCallExpression#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(Identifier value);

} // ConstructorCallExpression
