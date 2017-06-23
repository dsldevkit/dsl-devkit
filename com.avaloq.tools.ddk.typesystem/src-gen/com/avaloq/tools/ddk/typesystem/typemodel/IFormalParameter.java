/**
 */
package com.avaloq.tools.ddk.typesystem.typemodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IFormal Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see com.avaloq.tools.ddk.typesystem.typemodel.TypeModelPackage#getIFormalParameter()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IFormalParameter extends EObject
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * isMandatory() == true means that a caller must provide an actual parmeter for this formal parameter.
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isMandatory();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * isMulti() == true means that a caller may provide more than one actual parameter for this formal parameter.
	 * isMulti() == false means that a caller may provide at most 1 actual parameter. Whether a caller must provide
	 * at least one actual parameter in either case depends on isMandatory().
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isMulti();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	IType getType();

} // IFormalParameter
