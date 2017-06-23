/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeExpression#getPrune <em>Prune</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeExpression()
 * @model
 * @generated
 */
public interface ScopeExpression extends EObject
{
  /**
   * Returns the value of the '<em><b>Prune</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Prune</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Prune</em>' containment reference.
   * @see #setPrune(Expression)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeExpression_Prune()
   * @model containment="true"
   * @generated
   */
  Expression getPrune();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeExpression#getPrune <em>Prune</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Prune</em>' containment reference.
   * @see #getPrune()
   * @generated
   */
  void setPrune(Expression value);

} // ScopeExpression
