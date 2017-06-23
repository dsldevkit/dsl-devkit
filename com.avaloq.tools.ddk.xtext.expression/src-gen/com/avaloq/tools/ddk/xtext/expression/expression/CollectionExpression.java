/**
 */
package com.avaloq.tools.ddk.xtext.expression.expression;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collection Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.CollectionExpression#getVar <em>Var</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.CollectionExpression#getExp <em>Exp</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getCollectionExpression()
 * @model
 * @generated
 */
public interface CollectionExpression extends Expression, FeatureCall
{
  /**
   * Returns the value of the '<em><b>Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Var</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Var</em>' attribute.
   * @see #setVar(String)
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getCollectionExpression_Var()
   * @model
   * @generated
   */
  String getVar();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.CollectionExpression#getVar <em>Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Var</em>' attribute.
   * @see #getVar()
   * @generated
   */
  void setVar(String value);

  /**
   * Returns the value of the '<em><b>Exp</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Exp</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Exp</em>' containment reference.
   * @see #setExp(Expression)
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getCollectionExpression_Exp()
   * @model containment="true"
   * @generated
   */
  Expression getExp();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.CollectionExpression#getExp <em>Exp</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Exp</em>' containment reference.
   * @see #getExp()
   * @generated
   */
  void setExp(Expression value);

} // CollectionExpression
