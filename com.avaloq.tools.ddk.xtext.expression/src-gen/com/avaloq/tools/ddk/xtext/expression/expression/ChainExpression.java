/**
 */
package com.avaloq.tools.ddk.xtext.expression.expression;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Chain Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.ChainExpression#getFirst <em>First</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.ChainExpression#getNext <em>Next</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getChainExpression()
 * @model
 * @generated
 */
public interface ChainExpression extends Expression
{
  /**
   * Returns the value of the '<em><b>First</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>First</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>First</em>' containment reference.
   * @see #setFirst(Expression)
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getChainExpression_First()
   * @model containment="true"
   * @generated
   */
  Expression getFirst();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.ChainExpression#getFirst <em>First</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>First</em>' containment reference.
   * @see #getFirst()
   * @generated
   */
  void setFirst(Expression value);

  /**
   * Returns the value of the '<em><b>Next</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Next</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Next</em>' containment reference.
   * @see #setNext(Expression)
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getChainExpression_Next()
   * @model containment="true"
   * @generated
   */
  Expression getNext();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.ChainExpression#getNext <em>Next</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Next</em>' containment reference.
   * @see #getNext()
   * @generated
   */
  void setNext(Expression value);

} // ChainExpression
