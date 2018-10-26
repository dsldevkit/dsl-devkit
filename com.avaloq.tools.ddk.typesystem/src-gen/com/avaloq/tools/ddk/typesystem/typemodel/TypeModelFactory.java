/**
 */
package com.avaloq.tools.ddk.typesystem.typemodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.typesystem.typemodel.TypeModelPackage
 * @generated
 */
public interface TypeModelFactory extends EFactory
{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TypeModelFactory eINSTANCE = com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Named Element</em>'.
	 * @generated
	 */
	NamedElement createNamedElement();

	/**
	 * Returns a new object of class '<em>Named Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Named Type</em>'.
	 * @generated
	 */
	NamedType createNamedType();

	/**
	 * Returns a new object of class '<em>Named Formal Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Named Formal Parameter</em>'.
	 * @generated
	 */
	NamedFormalParameter createNamedFormalParameter();

	/**
	 * Returns a new object of class '<em>Callable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Callable</em>'.
	 * @generated
	 */
	Callable createCallable();

	/**
	 * Returns a new object of class '<em>ICustom Exported Name</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>ICustom Exported Name</em>'.
	 * @generated
	 */
	ICustomExportedName createICustomExportedName();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TypeModelPackage getTypeModelPackage();

} //TypeModelFactory
