/**
 */
package com.avaloq.tools.ddk.xtext.expression.expression;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Switch Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.SwitchExpression#getSwitchExpr <em>Switch Expr</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.SwitchExpression#getCase <em>Case</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.SwitchExpression#getDefaultExpr <em>Default Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getSwitchExpression()
 * @model
 * @generated
 */
public interface SwitchExpression extends Expression
{
  /**
   * Returns the value of the '<em><b>Switch Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Switch Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Switch Expr</em>' containment reference.
   * @see #setSwitchExpr(Expression)
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getSwitchExpression_SwitchExpr()
   * @model containment="true"
   * @generated
   */
  Expression getSwitchExpr();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.SwitchExpression#getSwitchExpr <em>Switch Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Switch Expr</em>' containment reference.
   * @see #getSwitchExpr()
   * @generated
   */
  void setSwitchExpr(Expression value);

  /**
   * Returns the value of the '<em><b>Case</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.expression.expression.Case}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Case</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Case</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getSwitchExpression_Case()
   * @model containment="true"
   * @generated
   */
  EList<Case> getCase();

  /**
   * Returns the value of the '<em><b>Default Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Default Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Default Expr</em>' containment reference.
   * @see #setDefaultExpr(Expression)
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getSwitchExpression_DefaultExpr()
   * @model containment="true"
   * @generated
   */
  Expression getDefaultExpr();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.SwitchExpression#getDefaultExpr <em>Default Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Default Expr</em>' containment reference.
   * @see #getDefaultExpr()
   * @generated
   */
  void setDefaultExpr(Expression value);

} // SwitchExpression
