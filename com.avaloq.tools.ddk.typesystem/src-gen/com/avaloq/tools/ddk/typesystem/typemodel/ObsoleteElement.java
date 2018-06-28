/**
 */
package com.avaloq.tools.ddk.typesystem.typemodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Obsolete Element</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.avaloq.tools.ddk.typesystem.typemodel.TypeModelPackage#getObsoleteElement()
 * @model abstract="true"
 * @generated
 */
public interface ObsoleteElement extends INamedElement
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isObsolete();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getSubstitute();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	INamedElement getElement();

} // ObsoleteElement
