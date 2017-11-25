/**
 */
package com.avaloq.tools.ddk.xtext.format.format;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Matcher List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.MatcherList#getMatchers <em>Matchers</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getMatcherList()
 * @model
 * @generated
 */
public interface MatcherList extends EObject
{
  /**
   * Returns the value of the '<em><b>Matchers</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.format.format.Matcher}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Matchers</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Matchers</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getMatcherList_Matchers()
   * @model containment="true"
   * @generated
   */
  EList<Matcher> getMatchers();

} // MatcherList
