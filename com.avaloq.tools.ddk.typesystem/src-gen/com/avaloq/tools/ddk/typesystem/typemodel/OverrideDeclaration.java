/**
 */
package com.avaloq.tools.ddk.typesystem.typemodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Override Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.avaloq.tools.ddk.typesystem.typemodel.TypeModelPackage#getOverrideDeclaration()
 * @model abstract="true"
 * @generated
 */
public interface OverrideDeclaration extends INamedElement
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	INamedElement getDefinition();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isOverride();

} // OverrideDeclaration
