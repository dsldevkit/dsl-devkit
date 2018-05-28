/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.ValidModel#getImports <em>Imports</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.ValidModel#getCategories <em>Categories</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getValidModel()
 * @model
 * @generated
 */
public interface ValidModel extends EObject
{
  /**
   * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.valid.valid.Import}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Imports</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Imports</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getValidModel_Imports()
   * @model containment="true"
   * @generated
   */
  EList<Import> getImports();

  /**
   * Returns the value of the '<em><b>Categories</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.valid.valid.Category}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Categories</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Categories</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getValidModel_Categories()
   * @model containment="true"
   * @generated
   */
  EList<Category> getCategories();

} // ValidModel
