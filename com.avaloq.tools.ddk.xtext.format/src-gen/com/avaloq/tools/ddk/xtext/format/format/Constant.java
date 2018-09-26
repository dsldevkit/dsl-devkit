/**
 */
package com.avaloq.tools.ddk.xtext.format.format;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.Constant#isIntType <em>Int Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.Constant#isStringType <em>String Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.Constant#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.Constant#getIntValue <em>Int Value</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.Constant#getStringValue <em>String Value</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getConstant()
 * @model
 * @generated
 */
public interface Constant extends EObject
{
  /**
   * Returns the value of the '<em><b>Int Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Int Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Int Type</em>' attribute.
   * @see #setIntType(boolean)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getConstant_IntType()
   * @model
   * @generated
   */
  boolean isIntType();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.Constant#isIntType <em>Int Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Int Type</em>' attribute.
   * @see #isIntType()
   * @generated
   */
  void setIntType(boolean value);

  /**
   * Returns the value of the '<em><b>String Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>String Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>String Type</em>' attribute.
   * @see #setStringType(boolean)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getConstant_StringType()
   * @model
   * @generated
   */
  boolean isStringType();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.Constant#isStringType <em>String Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>String Type</em>' attribute.
   * @see #isStringType()
   * @generated
   */
  void setStringType(boolean value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getConstant_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.Constant#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Int Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Int Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Int Value</em>' attribute.
   * @see #setIntValue(Integer)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getConstant_IntValue()
   * @model
   * @generated
   */
  Integer getIntValue();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.Constant#getIntValue <em>Int Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Int Value</em>' attribute.
   * @see #getIntValue()
   * @generated
   */
  void setIntValue(Integer value);

  /**
   * Returns the value of the '<em><b>String Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>String Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>String Value</em>' attribute.
   * @see #setStringValue(String)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getConstant_StringValue()
   * @model
   * @generated
   */
  String getStringValue();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.Constant#getStringValue <em>String Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>String Value</em>' attribute.
   * @see #getStringValue()
   * @generated
   */
  void setStringValue(String value);

} // Constant
