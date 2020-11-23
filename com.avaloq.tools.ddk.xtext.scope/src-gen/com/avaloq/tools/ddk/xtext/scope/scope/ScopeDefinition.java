/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getTargetType <em>Target Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getContextType <em>Context Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getReference <em>Reference</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getRules <em>Rules</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeDefinition()
 * @model
 * @generated
 */
public interface ScopeDefinition extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeDefinition_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Target Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Type</em>' reference.
   * @see #setTargetType(EClass)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeDefinition_TargetType()
   * @model
   * @generated
   */
  EClass getTargetType();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getTargetType <em>Target Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Type</em>' reference.
   * @see #getTargetType()
   * @generated
   */
  void setTargetType(EClass value);

  /**
   * Returns the value of the '<em><b>Context Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Context Type</em>' reference.
   * @see #setContextType(EClass)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeDefinition_ContextType()
   * @model
   * @generated
   */
  EClass getContextType();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getContextType <em>Context Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Context Type</em>' reference.
   * @see #getContextType()
   * @generated
   */
  void setContextType(EClass value);

  /**
   * Returns the value of the '<em><b>Reference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference</em>' reference.
   * @see #setReference(EReference)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeDefinition_Reference()
   * @model
   * @generated
   */
  EReference getReference();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition#getReference <em>Reference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference</em>' reference.
   * @see #getReference()
   * @generated
   */
  void setReference(EReference value);

  /**
   * Returns the value of the '<em><b>Rules</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rules</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeDefinition_Rules()
   * @model containment="true"
   * @generated
   */
  EList<ScopeRule> getRules();

} // ScopeDefinition
