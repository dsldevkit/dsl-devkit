/**
 */
package com.avaloq.tools.ddk.typesystem.builtintypemodel;

import com.avaloq.tools.ddk.typesystem.typemodel.TypeModelPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModelFactory
 * @model kind="package"
 * @generated
 */
public interface BuiltInTypeModelPackage extends EPackage
{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "builtintypemodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.avaloq.com/tools/ddk/typesystem/typemodel/builtintypemodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "builtintypemodel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BuiltInTypeModelPackage eINSTANCE = com.avaloq.tools.ddk.typesystem.builtintypemodel.impl.BuiltInTypeModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.builtintypemodel.impl.BuiltInTypeModelImpl <em>Built In Type Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.impl.BuiltInTypeModelImpl
	 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.impl.BuiltInTypeModelPackageImpl#getBuiltInTypeModel()
	 * @generated
	 */
	int BUILT_IN_TYPE_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Internal Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_TYPE_MODEL__INTERNAL_TYPES = 0;

	/**
	 * The number of structural features of the '<em>Built In Type Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_TYPE_MODEL_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.builtintypemodel.impl.InternalTypeImpl <em>Internal Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.impl.InternalTypeImpl
	 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.impl.BuiltInTypeModelPackageImpl#getInternalType()
	 * @generated
	 */
	int INTERNAL_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_TYPE__NAME = TypeModelPackage.INAMED_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Internal Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_TYPE_FEATURE_COUNT = TypeModelPackage.INAMED_TYPE_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModel <em>Built In Type Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Built In Type Model</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModel
	 * @generated
	 */
	EClass getBuiltInTypeModel();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModel#getInternalTypes <em>Internal Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Internal Types</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModel#getInternalTypes()
	 * @see #getBuiltInTypeModel()
	 * @generated
	 */
	EReference getBuiltInTypeModel_InternalTypes();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.builtintypemodel.InternalType <em>Internal Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Internal Type</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.InternalType
	 * @generated
	 */
	EClass getInternalType();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.typesystem.builtintypemodel.InternalType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.InternalType#getName()
	 * @see #getInternalType()
	 * @generated
	 */
	EAttribute getInternalType_Name();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BuiltInTypeModelFactory getBuiltInTypeModelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals
	{
		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.builtintypemodel.impl.BuiltInTypeModelImpl <em>Built In Type Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.impl.BuiltInTypeModelImpl
		 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.impl.BuiltInTypeModelPackageImpl#getBuiltInTypeModel()
		 * @generated
		 */
		EClass BUILT_IN_TYPE_MODEL = eINSTANCE.getBuiltInTypeModel();

		/**
		 * The meta object literal for the '<em><b>Internal Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BUILT_IN_TYPE_MODEL__INTERNAL_TYPES = eINSTANCE.getBuiltInTypeModel_InternalTypes();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.builtintypemodel.impl.InternalTypeImpl <em>Internal Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.impl.InternalTypeImpl
		 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.impl.BuiltInTypeModelPackageImpl#getInternalType()
		 * @generated
		 */
		EClass INTERNAL_TYPE = eINSTANCE.getInternalType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERNAL_TYPE__NAME = eINSTANCE.getInternalType_Name();

	}

} //BuiltInTypeModelPackage
