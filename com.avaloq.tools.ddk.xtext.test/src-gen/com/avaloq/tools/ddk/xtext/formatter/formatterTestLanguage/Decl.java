/**
 */
package com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Decl</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Decl#getType <em>Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Decl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage#getDecl()
 * @model
 * @generated
 */
public interface Decl extends Line
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' attribute list.
   * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage#getDecl_Type()
   * @model unique="false"
   * @generated
   */
  EList<String> getType();

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute list.
   * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage#getDecl_Name()
   * @model unique="false"
   * @generated
   */
  EList<String> getName();

} // Decl
