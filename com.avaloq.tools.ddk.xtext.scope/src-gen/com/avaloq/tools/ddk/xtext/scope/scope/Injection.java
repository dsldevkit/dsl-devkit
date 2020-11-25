/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Injection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.Injection#getType <em>Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.Injection#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getInjection()
 * @model
 * @generated
 */
public interface Injection extends EObject
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' attribute.
   * @see #setType(String)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getInjection_Type()
   * @model
   * @generated
   */
  String getType();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.Injection#getType <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' attribute.
   * @see #getType()
   * @generated
   */
  void setType(String value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getInjection_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.Injection#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

} // Injection
