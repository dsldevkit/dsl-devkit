/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simple Scope Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.SimpleScopeExpression#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getSimpleScopeExpression()
 * @model
 * @generated
 */
public interface SimpleScopeExpression extends NamedScopeExpression
{
  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference.
   * @see #setExpr(Expression)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getSimpleScopeExpression_Expr()
   * @model containment="true"
   * @generated
   */
  Expression getExpr();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.SimpleScopeExpression#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(Expression value);

} // SimpleScopeExpression
