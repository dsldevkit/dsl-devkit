/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Naming Section</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingSection#getCasing <em>Casing</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingSection#getNamings <em>Namings</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getNamingSection()
 * @model
 * @generated
 */
public interface NamingSection extends EObject
{
  /**
   * Returns the value of the '<em><b>Casing</b></em>' attribute.
   * The literals are from the enumeration {@link com.avaloq.tools.ddk.xtext.scope.scope.Casing}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Casing</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Casing</em>' attribute.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Casing
   * @see #setCasing(Casing)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getNamingSection_Casing()
   * @model
   * @generated
   */
  Casing getCasing();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingSection#getCasing <em>Casing</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Casing</em>' attribute.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Casing
   * @see #getCasing()
   * @generated
   */
  void setCasing(Casing value);

  /**
   * Returns the value of the '<em><b>Namings</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Namings</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Namings</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getNamingSection_Namings()
   * @model containment="true"
   * @generated
   */
  EList<NamingDefinition> getNamings();

} // NamingSection
