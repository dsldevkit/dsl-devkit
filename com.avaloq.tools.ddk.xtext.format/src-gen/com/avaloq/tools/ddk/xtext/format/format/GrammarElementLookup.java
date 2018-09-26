/**
 */
package com.avaloq.tools.ddk.xtext.format.format;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.AbstractRule;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Grammar Element Lookup</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.GrammarElementLookup#getRule <em>Rule</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.GrammarElementLookup#getKeyword <em>Keyword</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getGrammarElementLookup()
 * @model
 * @generated
 */
public interface GrammarElementLookup extends EObject
{
  /**
   * Returns the value of the '<em><b>Rule</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rule</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rule</em>' reference.
   * @see #setRule(AbstractRule)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getGrammarElementLookup_Rule()
   * @model
   * @generated
   */
  AbstractRule getRule();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.GrammarElementLookup#getRule <em>Rule</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rule</em>' reference.
   * @see #getRule()
   * @generated
   */
  void setRule(AbstractRule value);

  /**
   * Returns the value of the '<em><b>Keyword</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Keyword</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Keyword</em>' attribute.
   * @see #setKeyword(String)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getGrammarElementLookup_Keyword()
   * @model
   * @generated
   */
  String getKeyword();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.GrammarElementLookup#getKeyword <em>Keyword</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Keyword</em>' attribute.
   * @see #getKeyword()
   * @generated
   */
  void setKeyword(String value);

} // GrammarElementLookup
