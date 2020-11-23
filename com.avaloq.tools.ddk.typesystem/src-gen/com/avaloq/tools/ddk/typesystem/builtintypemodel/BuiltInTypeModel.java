/**
 */
package com.avaloq.tools.ddk.typesystem.builtintypemodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Built In Type Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModel#getInternalTypes <em>Internal Types</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModelPackage#getBuiltInTypeModel()
 * @model
 * @generated
 */
public interface BuiltInTypeModel extends EObject
{
	/**
	 * Returns the value of the '<em><b>Internal Types</b></em>' containment reference list.
	 * The list contents are of type {@link com.avaloq.tools.ddk.typesystem.builtintypemodel.InternalType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Internal Types</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModelPackage#getBuiltInTypeModel_InternalTypes()
	 * @model containment="true"
	 * @generated
	 */
	EList<InternalType> getInternalTypes();

} // BuiltInTypeModel
