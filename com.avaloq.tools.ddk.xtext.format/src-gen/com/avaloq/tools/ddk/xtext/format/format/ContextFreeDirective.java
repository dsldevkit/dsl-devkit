/**
 */
package com.avaloq.tools.ddk.xtext.format.format;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Context Free Directive</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.ContextFreeDirective#getGrammarElements <em>Grammar Elements</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.ContextFreeDirective#getMatcherList <em>Matcher List</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getContextFreeDirective()
 * @model
 * @generated
 */
public interface ContextFreeDirective extends GrammarRuleDirective, WildcardRuleDirective
{
  /**
   * Returns the value of the '<em><b>Grammar Elements</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.format.format.GrammarElementLookup}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Grammar Elements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Grammar Elements</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getContextFreeDirective_GrammarElements()
   * @model containment="true"
   * @generated
   */
  EList<GrammarElementLookup> getGrammarElements();

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
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getContextFreeDirective_MatcherList()
   * @model containment="true"
   * @generated
   */
  MatcherList getMatcherList();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.ContextFreeDirective#getMatcherList <em>Matcher List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Matcher List</em>' containment reference.
   * @see #getMatcherList()
   * @generated
   */
  void setMatcherList(MatcherList value);

} // ContextFreeDirective
