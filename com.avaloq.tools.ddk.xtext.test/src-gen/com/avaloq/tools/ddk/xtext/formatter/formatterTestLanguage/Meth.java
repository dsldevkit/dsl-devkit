/**
 */
package com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Meth</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Meth#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Meth#getParam <em>Param</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage#getMeth()
 * @model
 * @generated
 */
public interface Meth extends Line
{
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
   * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage#getMeth_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Meth#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Param</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Param}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Param</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Param</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage#getMeth_Param()
   * @model containment="true"
   * @generated
   */
  EList<Param> getParam();

} // Meth
