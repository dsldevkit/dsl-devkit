/**
 */
package com.avaloq.tools.ddk.xtext.format.format;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Indent Locator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.IndentLocator#isIncrement <em>Increment</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.IndentLocator#getValue <em>Value</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.IndentLocator#getParameter <em>Parameter</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getIndentLocator()
 * @model
 * @generated
 */
public interface IndentLocator extends Locator
{
  /**
   * Returns the value of the '<em><b>Increment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Increment</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Increment</em>' attribute.
   * @see #setIncrement(boolean)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getIndentLocator_Increment()
   * @model
   * @generated
   */
  boolean isIncrement();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.IndentLocator#isIncrement <em>Increment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Increment</em>' attribute.
   * @see #isIncrement()
   * @generated
   */
  void setIncrement(boolean value);

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
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getIndentLocator_Value()
   * @model containment="true"
   * @generated
   */
  IntValue getValue();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.IndentLocator#getValue <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' containment reference.
   * @see #getValue()
   * @generated
   */
  void setValue(IntValue value);

  /**
   * Returns the value of the '<em><b>Parameter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameter</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameter</em>' containment reference.
   * @see #setParameter(XExpression)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getIndentLocator_Parameter()
   * @model containment="true"
   * @generated
   */
  XExpression getParameter();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.IndentLocator#getParameter <em>Parameter</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameter</em>' containment reference.
   * @see #getParameter()
   * @generated
   */
  void setParameter(XExpression value);

} // IndentLocator
