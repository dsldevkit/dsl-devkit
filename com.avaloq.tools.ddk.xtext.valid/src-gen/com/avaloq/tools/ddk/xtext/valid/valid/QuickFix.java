/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Quick Fix</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFix#getQuickFixKind <em>Quick Fix Kind</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFix#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFix#getLabel <em>Label</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFix#getMessage <em>Message</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getQuickFix()
 * @model
 * @generated
 */
public interface QuickFix extends EObject
{
  /**
   * Returns the value of the '<em><b>Quick Fix Kind</b></em>' attribute.
   * The literals are from the enumeration {@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFixKind}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Quick Fix Kind</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Quick Fix Kind</em>' attribute.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.QuickFixKind
   * @see #setQuickFixKind(QuickFixKind)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getQuickFix_QuickFixKind()
   * @model
   * @generated
   */
  QuickFixKind getQuickFixKind();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFix#getQuickFixKind <em>Quick Fix Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Quick Fix Kind</em>' attribute.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.QuickFixKind
   * @see #getQuickFixKind()
   * @generated
   */
  void setQuickFixKind(QuickFixKind value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getQuickFix_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFix#getName <em>Name</em>}' attribute.
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
   * <p>
   * If the meaning of the '<em>Label</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Label</em>' attribute.
   * @see #setLabel(String)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getQuickFix_Label()
   * @model
   * @generated
   */
  String getLabel();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFix#getLabel <em>Label</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Label</em>' attribute.
   * @see #getLabel()
   * @generated
   */
  void setLabel(String value);

  /**
   * Returns the value of the '<em><b>Message</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Message</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Message</em>' attribute.
   * @see #setMessage(String)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getQuickFix_Message()
   * @model
   * @generated
   */
  String getMessage();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFix#getMessage <em>Message</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Message</em>' attribute.
   * @see #getMessage()
   * @generated
   */
  void setMessage(String value);

} // QuickFix
