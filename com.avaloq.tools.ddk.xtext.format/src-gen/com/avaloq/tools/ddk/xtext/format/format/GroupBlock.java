/**
 */
package com.avaloq.tools.ddk.xtext.format.format;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.CompoundElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Group Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.GroupBlock#getGrammarElement <em>Grammar Element</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.GroupBlock#getMatcherList <em>Matcher List</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.GroupBlock#getSubGroup <em>Sub Group</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.GroupBlock#getDirectives <em>Directives</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getGroupBlock()
 * @model
 * @generated
 */
public interface GroupBlock extends EObject
{
  /**
   * Returns the value of the '<em><b>Grammar Element</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Grammar Element</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Grammar Element</em>' reference.
   * @see #setGrammarElement(CompoundElement)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getGroupBlock_GrammarElement()
   * @model
   * @generated
   */
  CompoundElement getGrammarElement();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.GroupBlock#getGrammarElement <em>Grammar Element</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Grammar Element</em>' reference.
   * @see #getGrammarElement()
   * @generated
   */
  void setGrammarElement(CompoundElement value);

  /**
   * Returns the value of the '<em><b>Matcher List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Matcher List</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Matcher List</em>' containment reference.
   * @see #setMatcherList(MatcherList)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getGroupBlock_MatcherList()
   * @model containment="true"
   * @generated
   */
  MatcherList getMatcherList();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.GroupBlock#getMatcherList <em>Matcher List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Matcher List</em>' containment reference.
   * @see #getMatcherList()
   * @generated
   */
  void setMatcherList(MatcherList value);

  /**
   * Returns the value of the '<em><b>Sub Group</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sub Group</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sub Group</em>' containment reference.
   * @see #setSubGroup(GroupBlock)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getGroupBlock_SubGroup()
   * @model containment="true"
   * @generated
   */
  GroupBlock getSubGroup();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.GroupBlock#getSubGroup <em>Sub Group</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sub Group</em>' containment reference.
   * @see #getSubGroup()
   * @generated
   */
  void setSubGroup(GroupBlock value);

  /**
   * Returns the value of the '<em><b>Directives</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.format.format.GrammarRuleDirective}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Directives</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Directives</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getGroupBlock_Directives()
   * @model containment="true"
   * @generated
   */
  EList<GrammarRuleDirective> getDirectives();

} // GroupBlock
