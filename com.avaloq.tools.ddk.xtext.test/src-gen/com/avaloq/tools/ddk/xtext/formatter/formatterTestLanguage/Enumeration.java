/**
 */
package com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enumeration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Enumeration#getVal <em>Val</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage#getEnumeration()
 * @model
 * @generated
 */
public interface Enumeration extends Line
{
  /**
   * Returns the value of the '<em><b>Val</b></em>' attribute list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Enum1}.
   * The literals are from the enumeration {@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Enum1}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Val</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Val</em>' attribute list.
   * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Enum1
   * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage#getEnumeration_Val()
   * @model unique="false"
   * @generated
   */
  EList<Enum1> getVal();

} // Enumeration
