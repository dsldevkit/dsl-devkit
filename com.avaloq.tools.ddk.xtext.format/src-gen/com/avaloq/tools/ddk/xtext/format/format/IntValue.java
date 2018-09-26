/**
 */
package com.avaloq.tools.ddk.xtext.format.format;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Int Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.IntValue#getLiteral <em>Literal</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.IntValue#getReference <em>Reference</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getIntValue()
 * @model
 * @generated
 */
public interface IntValue extends EObject
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
   * @see #setLiteral(Integer)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getIntValue_Literal()
   * @model
   * @generated
   */
  Integer getLiteral();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.IntValue#getLiteral <em>Literal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Literal</em>' attribute.
   * @see #getLiteral()
   * @generated
   */
  void setLiteral(Integer value);

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
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getIntValue_Reference()
   * @model
   * @generated
   */
  Constant getReference();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.IntValue#getReference <em>Reference</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference</em>' reference.
   * @see #getReference()
   * @generated
   */
  void setReference(Constant value);

} // IntValue
