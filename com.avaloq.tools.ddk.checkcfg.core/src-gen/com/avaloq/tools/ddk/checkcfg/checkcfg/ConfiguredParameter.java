/**
 */
package com.avaloq.tools.ddk.checkcfg.checkcfg;

import com.avaloq.tools.ddk.check.check.FormalParameter;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configured Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter#getParameter <em>Parameter</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter#getNewValue <em>New Value</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage#getConfiguredParameter()
 * @model
 * @generated
 */
public interface ConfiguredParameter extends EObject
{
  /**
   * Returns the value of the '<em><b>Parameter</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameter</em>' reference.
   * @see #setParameter(FormalParameter)
   * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage#getConfiguredParameter_Parameter()
   * @model
   * @generated
   */
  FormalParameter getParameter();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter#getParameter <em>Parameter</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameter</em>' reference.
   * @see #getParameter()
   * @generated
   */
  void setParameter(FormalParameter value);

  /**
   * Returns the value of the '<em><b>New Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>New Value</em>' containment reference.
   * @see #setNewValue(XExpression)
   * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage#getConfiguredParameter_NewValue()
   * @model containment="true"
   * @generated
   */
  XExpression getNewValue();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter#getNewValue <em>New Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>New Value</em>' containment reference.
   * @see #getNewValue()
   * @generated
   */
  void setNewValue(XExpression value);

} // ConfiguredParameter
