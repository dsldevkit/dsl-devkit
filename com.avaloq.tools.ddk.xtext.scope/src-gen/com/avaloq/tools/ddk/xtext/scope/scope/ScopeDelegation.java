/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Delegation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation#getDelegate <em>Delegate</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation#getExternal <em>External</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation#getScope <em>Scope</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeDelegation()
 * @model
 * @generated
 */
public interface ScopeDelegation extends ScopeExpression
{
  /**
   * Returns the value of the '<em><b>Delegate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Delegate</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Delegate</em>' containment reference.
   * @see #setDelegate(Expression)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeDelegation_Delegate()
   * @model containment="true"
   * @generated
   */
  Expression getDelegate();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation#getDelegate <em>Delegate</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Delegate</em>' containment reference.
   * @see #getDelegate()
   * @generated
   */
  void setDelegate(Expression value);

  /**
   * Returns the value of the '<em><b>External</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>External</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>External</em>' containment reference.
   * @see #setExternal(GlobalScopeExpression)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeDelegation_External()
   * @model containment="true"
   * @generated
   */
  GlobalScopeExpression getExternal();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation#getExternal <em>External</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>External</em>' containment reference.
   * @see #getExternal()
   * @generated
   */
  void setExternal(GlobalScopeExpression value);

  /**
   * Returns the value of the '<em><b>Scope</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scope</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scope</em>' reference.
   * @see #setScope(ScopeDefinition)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeDelegation_Scope()
   * @model
   * @generated
   */
  ScopeDefinition getScope();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation#getScope <em>Scope</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Scope</em>' reference.
   * @see #getScope()
   * @generated
   */
  void setScope(ScopeDefinition value);

} // ScopeDelegation
