/**
 */
package com.avaloq.tools.ddk.xtext.format.format;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Offset Locator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.OffsetLocator#isFixed <em>Fixed</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.OffsetLocator#getValue <em>Value</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.OffsetLocator#isNobreak <em>Nobreak</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getOffsetLocator()
 * @model
 * @generated
 */
public interface OffsetLocator extends Locator
{
  /**
   * Returns the value of the '<em><b>Fixed</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fixed</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fixed</em>' attribute.
   * @see #setFixed(boolean)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getOffsetLocator_Fixed()
   * @model
   * @generated
   */
  boolean isFixed();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.OffsetLocator#isFixed <em>Fixed</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fixed</em>' attribute.
   * @see #isFixed()
   * @generated
   */
  void setFixed(boolean value);

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
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getOffsetLocator_Value()
   * @model containment="true"
   * @generated
   */
  IntValue getValue();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.OffsetLocator#getValue <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' containment reference.
   * @see #getValue()
   * @generated
   */
  void setValue(IntValue value);

  /**
   * Returns the value of the '<em><b>Nobreak</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Nobreak</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Nobreak</em>' attribute.
   * @see #setNobreak(boolean)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getOffsetLocator_Nobreak()
   * @model
   * @generated
   */
  boolean isNobreak();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.OffsetLocator#isNobreak <em>Nobreak</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Nobreak</em>' attribute.
   * @see #isNobreak()
   * @generated
   */
  void setNobreak(boolean value);

} // OffsetLocator
