/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule#getContext <em>Context</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule#getExprs <em>Exprs</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeRule()
 * @model
 * @generated
 */
public interface ScopeRule extends EObject
{
  /**
   * Returns the value of the '<em><b>Context</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Context</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Context</em>' containment reference.
   * @see #setContext(ScopeContext)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeRule_Context()
   * @model containment="true"
   * @generated
   */
  ScopeContext getContext();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule#getContext <em>Context</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Context</em>' containment reference.
   * @see #getContext()
   * @generated
   */
  void setContext(ScopeContext value);

  /**
   * Returns the value of the '<em><b>Exprs</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Exprs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Exprs</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeRule_Exprs()
   * @model containment="true"
   * @generated
   */
  EList<ScopeExpression> getExprs();

} // ScopeRule
