/**
 */
package com.avaloq.tools.ddk.xtext.format.format;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Right Padding Locator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.RightPaddingLocator#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getRightPaddingLocator()
 * @model
 * @generated
 */
public interface RightPaddingLocator extends Locator
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
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getRightPaddingLocator_Value()
   * @model containment="true"
   * @generated
   */
  IntValue getValue();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.RightPaddingLocator#getValue <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' containment reference.
   * @see #getValue()
   * @generated
   */
  void setValue(IntValue value);

} // RightPaddingLocator
