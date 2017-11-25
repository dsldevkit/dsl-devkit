/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext#isGlobal <em>Global</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext#getContextType <em>Context Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext#getGuard <em>Guard</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeContext()
 * @model
 * @generated
 */
public interface ScopeContext extends EObject
{
  /**
   * Returns the value of the '<em><b>Global</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Global</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Global</em>' attribute.
   * @see #setGlobal(boolean)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeContext_Global()
   * @model
   * @generated
   */
  boolean isGlobal();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext#isGlobal <em>Global</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Global</em>' attribute.
   * @see #isGlobal()
   * @generated
   */
  void setGlobal(boolean value);

  /**
   * Returns the value of the '<em><b>Context Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Context Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Context Type</em>' reference.
   * @see #setContextType(EClass)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeContext_ContextType()
   * @model
   * @generated
   */
  EClass getContextType();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext#getContextType <em>Context Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Context Type</em>' reference.
   * @see #getContextType()
   * @generated
   */
  void setContextType(EClass value);

  /**
   * Returns the value of the '<em><b>Guard</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Guard</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Guard</em>' containment reference.
   * @see #setGuard(Expression)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeContext_Guard()
   * @model containment="true"
   * @generated
   */
  Expression getGuard();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext#getGuard <em>Guard</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Guard</em>' containment reference.
   * @see #getGuard()
   * @generated
   */
  void setGuard(Expression value);

} // ScopeContext
