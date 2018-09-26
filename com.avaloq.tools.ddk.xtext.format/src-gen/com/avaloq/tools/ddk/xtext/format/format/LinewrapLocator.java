/**
 */
package com.avaloq.tools.ddk.xtext.format.format;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Linewrap Locator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.LinewrapLocator#getValue <em>Value</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.LinewrapLocator#getMinimum <em>Minimum</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.LinewrapLocator#getDefault <em>Default</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.LinewrapLocator#getMaximum <em>Maximum</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.LinewrapLocator#isNoLinewrap <em>No Linewrap</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getLinewrapLocator()
 * @model
 * @generated
 */
public interface LinewrapLocator extends Locator
{
  /**
   * Returns the value of the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' containment reference.
   * @see #setValue(IntValue)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getLinewrapLocator_Value()
   * @model containment="true"
   * @generated
   */
  IntValue getValue();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.LinewrapLocator#getValue <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' containment reference.
   * @see #getValue()
   * @generated
   */
  void setValue(IntValue value);

  /**
   * Returns the value of the '<em><b>Minimum</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Minimum</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Minimum</em>' containment reference.
   * @see #setMinimum(IntValue)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getLinewrapLocator_Minimum()
   * @model containment="true"
   * @generated
   */
  IntValue getMinimum();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.LinewrapLocator#getMinimum <em>Minimum</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Minimum</em>' containment reference.
   * @see #getMinimum()
   * @generated
   */
  void setMinimum(IntValue value);

  /**
   * Returns the value of the '<em><b>Default</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Default</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Default</em>' containment reference.
   * @see #setDefault(IntValue)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getLinewrapLocator_Default()
   * @model containment="true"
   * @generated
   */
  IntValue getDefault();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.LinewrapLocator#getDefault <em>Default</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Default</em>' containment reference.
   * @see #getDefault()
   * @generated
   */
  void setDefault(IntValue value);

  /**
   * Returns the value of the '<em><b>Maximum</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Maximum</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Maximum</em>' containment reference.
   * @see #setMaximum(IntValue)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getLinewrapLocator_Maximum()
   * @model containment="true"
   * @generated
   */
  IntValue getMaximum();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.LinewrapLocator#getMaximum <em>Maximum</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Maximum</em>' containment reference.
   * @see #getMaximum()
   * @generated
   */
  void setMaximum(IntValue value);

  /**
   * Returns the value of the '<em><b>No Linewrap</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>No Linewrap</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>No Linewrap</em>' attribute.
   * @see #setNoLinewrap(boolean)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getLinewrapLocator_NoLinewrap()
   * @model
   * @generated
   */
  boolean isNoLinewrap();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.LinewrapLocator#isNoLinewrap <em>No Linewrap</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>No Linewrap</em>' attribute.
   * @see #isNoLinewrap()
   * @generated
   */
  void setNoLinewrap(boolean value);

} // LinewrapLocator
