/**
 */
package com.avaloq.tools.ddk.xtext.format.format;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Grammar Element Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference#getAssignment <em>Assignment</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference#getRuleCall <em>Rule Call</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference#getSelf <em>Self</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference#getRule <em>Rule</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference#getKeyword <em>Keyword</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getGrammarElementReference()
 * @model
 * @generated
 */
public interface GrammarElementReference extends EObject
{
  /**
   * Returns the value of the '<em><b>Assignment</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Assignment</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Assignment</em>' reference.
   * @see #setAssignment(Assignment)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getGrammarElementReference_Assignment()
   * @model
   * @generated
   */
  Assignment getAssignment();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference#getAssignment <em>Assignment</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Assignment</em>' reference.
   * @see #getAssignment()
   * @generated
   */
  void setAssignment(Assignment value);

  /**
   * Returns the value of the '<em><b>Rule Call</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rule Call</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rule Call</em>' reference.
   * @see #setRuleCall(RuleCall)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getGrammarElementReference_RuleCall()
   * @model
   * @generated
   */
  RuleCall getRuleCall();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference#getRuleCall <em>Rule Call</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rule Call</em>' reference.
   * @see #getRuleCall()
   * @generated
   */
  void setRuleCall(RuleCall value);

  /**
   * Returns the value of the '<em><b>Self</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Self</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Self</em>' reference.
   * @see #setSelf(AbstractRule)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getGrammarElementReference_Self()
   * @model
   * @generated
   */
  AbstractRule getSelf();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference#getSelf <em>Self</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Self</em>' reference.
   * @see #getSelf()
   * @generated
   */
  void setSelf(AbstractRule value);

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
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getGrammarElementReference_Rule()
   * @model
   * @generated
   */
  AbstractRule getRule();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference#getRule <em>Rule</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rule</em>' reference.
   * @see #getRule()
   * @generated
   */
  void setRule(AbstractRule value);

  /**
   * Returns the value of the '<em><b>Keyword</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Keyword</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Keyword</em>' reference.
   * @see #setKeyword(Keyword)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getGrammarElementReference_Keyword()
   * @model
   * @generated
   */
  Keyword getKeyword();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference#getKeyword <em>Keyword</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Keyword</em>' reference.
   * @see #getKeyword()
   * @generated
   */
  void setKeyword(Keyword value);

} // GrammarElementReference
