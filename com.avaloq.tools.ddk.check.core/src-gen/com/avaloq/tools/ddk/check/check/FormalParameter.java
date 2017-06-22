/**
 */
package com.avaloq.tools.ddk.check.check;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.common.types.JvmTypeReference;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Formal Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.check.check.FormalParameter#getType <em>Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.FormalParameter#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.FormalParameter#getRight <em>Right</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.FormalParameter#getLabel <em>Label</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getFormalParameter()
 * @model
 * @generated
 */
public interface FormalParameter extends EObject
{
	/**
	 * Returns the value of the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' containment reference.
	 * @see #setType(JvmTypeReference)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getFormalParameter_Type()
	 * @model containment="true"
	 * @generated
	 */
	JvmTypeReference getType();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.FormalParameter#getType <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' containment reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(JvmTypeReference value);

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
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getFormalParameter_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.FormalParameter#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right</em>' containment reference.
	 * @see #setRight(XExpression)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getFormalParameter_Right()
	 * @model containment="true"
	 * @generated
	 */
	XExpression getRight();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.FormalParameter#getRight <em>Right</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right</em>' containment reference.
	 * @see #getRight()
	 * @generated
	 */
	void setRight(XExpression value);

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getFormalParameter_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.FormalParameter#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

} // FormalParameter
