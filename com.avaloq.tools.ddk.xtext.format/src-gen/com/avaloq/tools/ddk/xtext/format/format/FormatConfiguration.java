/**
 */
package com.avaloq.tools.ddk.xtext.format.format;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.Grammar;

import org.eclipse.xtext.common.types.JvmDeclaredType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration#getTargetGrammar <em>Target Grammar</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration#getExtendedFormatConfiguration <em>Extended Format Configuration</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration#getFormatterBaseClass <em>Formatter Base Class</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration#getConstants <em>Constants</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration#getRules <em>Rules</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getFormatConfiguration()
 * @model
 * @generated
 */
public interface FormatConfiguration extends EObject
{
  /**
   * Returns the value of the '<em><b>Target Grammar</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Grammar</em>' reference.
   * @see #setTargetGrammar(Grammar)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getFormatConfiguration_TargetGrammar()
   * @model
   * @generated
   */
  Grammar getTargetGrammar();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration#getTargetGrammar <em>Target Grammar</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Grammar</em>' reference.
   * @see #getTargetGrammar()
   * @generated
   */
  void setTargetGrammar(Grammar value);

  /**
   * Returns the value of the '<em><b>Extended Format Configuration</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Extended Format Configuration</em>' reference.
   * @see #setExtendedFormatConfiguration(FormatConfiguration)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getFormatConfiguration_ExtendedFormatConfiguration()
   * @model
   * @generated
   */
  FormatConfiguration getExtendedFormatConfiguration();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration#getExtendedFormatConfiguration <em>Extended Format Configuration</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Extended Format Configuration</em>' reference.
   * @see #getExtendedFormatConfiguration()
   * @generated
   */
  void setExtendedFormatConfiguration(FormatConfiguration value);

  /**
   * Returns the value of the '<em><b>Formatter Base Class</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Formatter Base Class</em>' reference.
   * @see #setFormatterBaseClass(JvmDeclaredType)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getFormatConfiguration_FormatterBaseClass()
   * @model
   * @generated
   */
  JvmDeclaredType getFormatterBaseClass();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration#getFormatterBaseClass <em>Formatter Base Class</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Formatter Base Class</em>' reference.
   * @see #getFormatterBaseClass()
   * @generated
   */
  void setFormatterBaseClass(JvmDeclaredType value);

  /**
   * Returns the value of the '<em><b>Constants</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.format.format.Constant}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constants</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getFormatConfiguration_Constants()
   * @model containment="true"
   * @generated
   */
  EList<Constant> getConstants();

  /**
   * Returns the value of the '<em><b>Rules</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.format.format.Rule}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rules</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getFormatConfiguration_Rules()
   * @model containment="true"
   * @generated
   */
  EList<Rule> getRules();

} // FormatConfiguration
