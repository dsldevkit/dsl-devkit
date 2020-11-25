/**
 */
package com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Suppressed Hidden</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.SuppressedHidden#getVals <em>Vals</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage#getSuppressedHidden()
 * @model
 * @generated
 */
public interface SuppressedHidden extends Line
{
  /**
   * Returns the value of the '<em><b>Vals</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.SuppressedHiddenSub}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Vals</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage#getSuppressedHidden_Vals()
   * @model containment="true"
   * @generated
   */
  EList<SuppressedHiddenSub> getVals();

} // SuppressedHidden
