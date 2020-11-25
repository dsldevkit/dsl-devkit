/**
 */
package com.avaloq.tools.ddk.check.check;

import org.eclipse.emf.common.util.EList;

import org.eclipse.xtext.common.types.JvmTypeReference;

import org.eclipse.xtext.xbase.XExpression;

import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Member</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.check.check.Member#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.Member#getType <em>Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.Member#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.Member#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getMember()
 * @model
 * @generated
 */
public interface Member extends Documented
{
	/**
	 * Returns the value of the '<em><b>Annotations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Annotations</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getMember_Annotations()
	 * @model containment="true"
	 * @generated
	 */
	EList<XAnnotation> getAnnotations();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' containment reference.
	 * @see #setType(JvmTypeReference)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getMember_Type()
	 * @model containment="true"
	 * @generated
	 */
	JvmTypeReference getType();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.Member#getType <em>Type</em>}' containment reference.
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
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getMember_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.Member#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(XExpression)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getMember_Value()
	 * @model containment="true"
	 * @generated
	 */
	XExpression getValue();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.Member#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(XExpression value);

} // Member
