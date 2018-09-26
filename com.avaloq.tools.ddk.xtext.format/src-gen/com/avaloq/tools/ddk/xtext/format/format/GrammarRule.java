/**
 */
package com.avaloq.tools.ddk.xtext.format.format;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.AbstractRule;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Grammar Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.GrammarRule#getTargetRule <em>Target Rule</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.GrammarRule#getDirectives <em>Directives</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getGrammarRule()
 * @model
 * @generated
 */
public interface GrammarRule extends Rule
{
  /**
   * Returns the value of the '<em><b>Target Rule</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Rule</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Rule</em>' reference.
   * @see #setTargetRule(AbstractRule)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getGrammarRule_TargetRule()
   * @model
   * @generated
   */
  AbstractRule getTargetRule();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.GrammarRule#getTargetRule <em>Target Rule</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Rule</em>' reference.
   * @see #getTargetRule()
   * @generated
   */
  void setTargetRule(AbstractRule value);

  /**
   * Returns the value of the '<em><b>Directives</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Directives</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Directives</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getGrammarRule_Directives()
   * @model containment="true"
   * @generated
   */
  EList<EObject> getDirectives();

} // GrammarRule
