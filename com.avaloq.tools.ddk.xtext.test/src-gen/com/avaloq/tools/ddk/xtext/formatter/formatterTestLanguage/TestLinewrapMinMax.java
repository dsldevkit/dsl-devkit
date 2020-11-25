/**
 */
package com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Linewrap Min Max</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.TestLinewrapMinMax#getItems <em>Items</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage#getTestLinewrapMinMax()
 * @model
 * @generated
 */
public interface TestLinewrapMinMax extends Root
{
  /**
   * Returns the value of the '<em><b>Items</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Line}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Items</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage#getTestLinewrapMinMax_Items()
   * @model containment="true"
   * @generated
   */
  EList<Line> getItems();

} // TestLinewrapMinMax
