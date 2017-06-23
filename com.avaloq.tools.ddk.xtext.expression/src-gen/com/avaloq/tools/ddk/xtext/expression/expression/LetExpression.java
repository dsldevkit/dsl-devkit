/**
 */
package com.avaloq.tools.ddk.xtext.expression.expression;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Let Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.LetExpression#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.LetExpression#getVarExpr <em>Var Expr</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.LetExpression#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getLetExpression()
 * @model
 * @generated
 */
public interface LetExpression extends Expression
{
  /**
   * Returns the value of the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Identifier</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Identifier</em>' attribute.
   * @see #setIdentifier(String)
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getLetExpression_Identifier()
   * @model
   * @generated
   */
  String getIdentifier();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.LetExpression#getIdentifier <em>Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Identifier</em>' attribute.
   * @see #getIdentifier()
   * @generated
   */
  void setIdentifier(String value);

  /**
   * Returns the value of the '<em><b>Var Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Var Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Var Expr</em>' containment reference.
   * @see #setVarExpr(Expression)
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getLetExpression_VarExpr()
   * @model containment="true"
   * @generated
   */
  Expression getVarExpr();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.LetExpression#getVarExpr <em>Var Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Var Expr</em>' containment reference.
   * @see #getVarExpr()
   * @generated
   */
  void setVarExpr(Expression value);

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
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getLetExpression_Target()
   * @model containment="true"
   * @generated
   */
  Expression getTarget();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.LetExpression#getTarget <em>Target</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' containment reference.
   * @see #getTarget()
   * @generated
   */
  void setTarget(Expression value);

} // LetExpression
