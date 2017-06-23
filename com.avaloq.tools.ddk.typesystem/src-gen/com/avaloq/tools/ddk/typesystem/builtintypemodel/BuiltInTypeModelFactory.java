/**
 */
package com.avaloq.tools.ddk.typesystem.builtintypemodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModelPackage
 * @generated
 */
public interface BuiltInTypeModelFactory extends EFactory
{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BuiltInTypeModelFactory eINSTANCE = com.avaloq.tools.ddk.typesystem.builtintypemodel.impl.BuiltInTypeModelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Built In Type Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Built In Type Model</em>'.
	 * @generated
	 */
	BuiltInTypeModel createBuiltInTypeModel();

	/**
	 * Returns a new object of class '<em>Internal Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Internal Type</em>'.
	 * @generated
	 */
	InternalType createInternalType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	BuiltInTypeModelPackage getBuiltInTypeModelPackage();

} //BuiltInTypeModelFactory
