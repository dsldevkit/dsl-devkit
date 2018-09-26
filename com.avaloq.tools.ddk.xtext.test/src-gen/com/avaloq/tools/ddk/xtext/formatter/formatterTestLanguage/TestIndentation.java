/**
 */
package com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Indentation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.TestIndentation#getSub <em>Sub</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.TestIndentation#getItems <em>Items</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.TestIndentation#isSemi <em>Semi</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage#getTestIndentation()
 * @model
 * @generated
 */
public interface TestIndentation extends Root
{
  /**
   * Returns the value of the '<em><b>Sub</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.TestIndentation}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sub</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sub</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage#getTestIndentation_Sub()
   * @model containment="true"
   * @generated
   */
  EList<TestIndentation> getSub();

  /**
   * Returns the value of the '<em><b>Items</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Line}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Items</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Items</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage#getTestIndentation_Items()
   * @model containment="true"
   * @generated
   */
  EList<Line> getItems();

  /**
   * Returns the value of the '<em><b>Semi</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Semi</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Semi</em>' attribute.
   * @see #setSemi(boolean)
   * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage#getTestIndentation_Semi()
   * @model
   * @generated
   */
  boolean isSemi();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.TestIndentation#isSemi <em>Semi</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Semi</em>' attribute.
   * @see #isSemi()
   * @generated
   */
  void setSemi(boolean value);

} // TestIndentation
