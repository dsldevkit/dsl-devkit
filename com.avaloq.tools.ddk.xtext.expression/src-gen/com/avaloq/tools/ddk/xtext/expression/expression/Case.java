/**
 */
package com.avaloq.tools.ddk.xtext.expression.expression;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Case</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.Case#getCondition <em>Condition</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.Case#getThenPar <em>Then Par</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getCase()
 * @model
 * @generated
 */
public interface Case extends SyntaxElement
{
  /**
   * Returns the value of the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Condition</em>' containment reference.
   * @see #setCondition(Expression)
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getCase_Condition()
   * @model containment="true"
   * @generated
   */
  Expression getCondition();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.Case#getCondition <em>Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' containment reference.
   * @see #getCondition()
   * @generated
   */
  void setCondition(Expression value);

  /**
   * Returns the value of the '<em><b>Then Par</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Then Par</em>' containment reference.
   * @see #setThenPar(Expression)
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getCase_ThenPar()
   * @model containment="true"
   * @generated
   */
  Expression getThenPar();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.Case#getThenPar <em>Then Par</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Then Par</em>' containment reference.
   * @see #getThenPar()
   * @generated
   */
  void setThenPar(Expression value);

} // Case
