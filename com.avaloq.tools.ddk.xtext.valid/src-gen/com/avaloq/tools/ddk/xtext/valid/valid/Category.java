/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.Category#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.Category#getLabel <em>Label</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.Category#getDescription <em>Description</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.Category#getRules <em>Rules</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getCategory()
 * @model
 * @generated
 */
public interface Category extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getCategory_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.Category#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Label</em>' attribute.
   * @see #setLabel(String)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getCategory_Label()
   * @model
   * @generated
   */
  String getLabel();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.Category#getLabel <em>Label</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Label</em>' attribute.
   * @see #getLabel()
   * @generated
   */
  void setLabel(String value);

  /**
   * Returns the value of the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Description</em>' attribute.
   * @see #setDescription(String)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getCategory_Description()
   * @model
   * @generated
   */
  String getDescription();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.Category#getDescription <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Description</em>' attribute.
   * @see #getDescription()
   * @generated
   */
  void setDescription(String value);

  /**
   * Returns the value of the '<em><b>Rules</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.valid.valid.Rule}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rules</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getCategory_Rules()
   * @model containment="true"
   * @generated
   */
  EList<Rule> getRules();

} // Category
