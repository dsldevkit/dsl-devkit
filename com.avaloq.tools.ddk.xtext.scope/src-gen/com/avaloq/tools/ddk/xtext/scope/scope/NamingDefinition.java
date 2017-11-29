/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Naming Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition#getType <em>Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition#getNaming <em>Naming</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getNamingDefinition()
 * @model
 * @generated
 */
public interface NamingDefinition extends EObject
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(EClass)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getNamingDefinition_Type()
   * @model
   * @generated
   */
  EClass getType();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition#getType <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */
  void setType(EClass value);

  /**
   * Returns the value of the '<em><b>Naming</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Naming</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Naming</em>' containment reference.
   * @see #setNaming(Naming)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getNamingDefinition_Naming()
   * @model containment="true"
   * @generated
   */
  Naming getNaming();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition#getNaming <em>Naming</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Naming</em>' containment reference.
   * @see #getNaming()
   * @generated
   */
  void setNaming(Naming value);

} // NamingDefinition
