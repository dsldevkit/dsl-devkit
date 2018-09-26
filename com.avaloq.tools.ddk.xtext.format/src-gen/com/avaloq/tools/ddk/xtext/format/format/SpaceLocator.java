/**
 */
package com.avaloq.tools.ddk.xtext.format.format;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Space Locator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.SpaceLocator#getValue <em>Value</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.SpaceLocator#isNoSpace <em>No Space</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getSpaceLocator()
 * @model
 * @generated
 */
public interface SpaceLocator extends Locator
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
   * @see #setValue(StringValue)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getSpaceLocator_Value()
   * @model containment="true"
   * @generated
   */
  StringValue getValue();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.SpaceLocator#getValue <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' containment reference.
   * @see #getValue()
   * @generated
   */
  void setValue(StringValue value);

  /**
   * Returns the value of the '<em><b>No Space</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>No Space</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>No Space</em>' attribute.
   * @see #setNoSpace(boolean)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getSpaceLocator_NoSpace()
   * @model
   * @generated
   */
  boolean isNoSpace();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.SpaceLocator#isNoSpace <em>No Space</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>No Space</em>' attribute.
   * @see #isNoSpace()
   * @generated
   */
  void setNoSpace(boolean value);

} // SpaceLocator
