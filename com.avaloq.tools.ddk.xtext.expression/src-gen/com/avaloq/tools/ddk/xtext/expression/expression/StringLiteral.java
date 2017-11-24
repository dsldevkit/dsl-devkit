/**
 */
package com.avaloq.tools.ddk.xtext.expression.expression;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral#getVal <em>Val</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getStringLiteral()
 * @model
 * @generated
 */
public interface StringLiteral extends Literal
{
  /**
   * Returns the value of the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Val</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Val</em>' attribute.
   * @see #setVal(String)
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getStringLiteral_Val()
   * @model
   * @generated
   */
  String getVal();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral#getVal <em>Val</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Val</em>' attribute.
   * @see #getVal()
   * @generated
   */
  void setVal(String value);

} // StringLiteral
