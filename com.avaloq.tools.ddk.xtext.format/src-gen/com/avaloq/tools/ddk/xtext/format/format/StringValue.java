/**
 */
package com.avaloq.tools.ddk.xtext.format.format;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.StringValue#getLiteral <em>Literal</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.StringValue#getReference <em>Reference</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getStringValue()
 * @model
 * @generated
 */
public interface StringValue extends EObject
{
  /**
   * Returns the value of the '<em><b>Literal</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Literal</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Literal</em>' attribute.
   * @see #setLiteral(String)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getStringValue_Literal()
   * @model
   * @generated
   */
  String getLiteral();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.StringValue#getLiteral <em>Literal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Literal</em>' attribute.
   * @see #getLiteral()
   * @generated
   */
  void setLiteral(String value);

  /**
   * Returns the value of the '<em><b>Reference</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reference</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference</em>' reference.
   * @see #setReference(Constant)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getStringValue_Reference()
   * @model
   * @generated
   */
  Constant getReference();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.StringValue#getReference <em>Reference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference</em>' reference.
   * @see #getReference()
   * @generated
   */
  void setReference(Constant value);

} // StringValue
