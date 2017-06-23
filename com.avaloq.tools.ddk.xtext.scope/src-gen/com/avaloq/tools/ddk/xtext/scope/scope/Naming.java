/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Naming</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.Naming#getNames <em>Names</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getNaming()
 * @model
 * @generated
 */
public interface Naming extends EObject
{
  /**
   * Returns the value of the '<em><b>Names</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Names</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Names</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getNaming_Names()
   * @model containment="true"
   * @generated
   */
  EList<NamingExpression> getNames();

} // Naming
