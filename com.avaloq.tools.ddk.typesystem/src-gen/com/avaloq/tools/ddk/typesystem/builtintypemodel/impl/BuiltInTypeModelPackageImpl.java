/**
 */
package com.avaloq.tools.ddk.typesystem.builtintypemodel.impl;

import com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModel;
import com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModelFactory;
import com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModelPackage;
import com.avaloq.tools.ddk.typesystem.builtintypemodel.InternalType;

import com.avaloq.tools.ddk.typesystem.typemodel.TypeModelPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BuiltInTypeModelPackageImpl extends EPackageImpl implements BuiltInTypeModelPackage
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass builtInTypeModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass internalTypeEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private BuiltInTypeModelPackageImpl()
	{
		super(eNS_URI, BuiltInTypeModelFactory.eINSTANCE);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link BuiltInTypeModelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static BuiltInTypeModelPackage init()
	{
		if (isInited) return (BuiltInTypeModelPackage)EPackage.Registry.INSTANCE.getEPackage(BuiltInTypeModelPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredBuiltInTypeModelPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		BuiltInTypeModelPackageImpl theBuiltInTypeModelPackage = registeredBuiltInTypeModelPackage instanceof BuiltInTypeModelPackageImpl ? (BuiltInTypeModelPackageImpl)registeredBuiltInTypeModelPackage : new BuiltInTypeModelPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		TypeModelPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theBuiltInTypeModelPackage.createPackageContents();

		// Initialize created meta-data
		theBuiltInTypeModelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theBuiltInTypeModelPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(BuiltInTypeModelPackage.eNS_URI, theBuiltInTypeModelPackage);
		return theBuiltInTypeModelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBuiltInTypeModel()
	{
		return builtInTypeModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBuiltInTypeModel_InternalTypes()
	{
		return (EReference)builtInTypeModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInternalType()
	{
		return internalTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInternalType_Name()
	{
		return (EAttribute)internalTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BuiltInTypeModelFactory getBuiltInTypeModelFactory()
	{
		return (BuiltInTypeModelFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents()
	{
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		builtInTypeModelEClass = createEClass(BUILT_IN_TYPE_MODEL);
		createEReference(builtInTypeModelEClass, BUILT_IN_TYPE_MODEL__INTERNAL_TYPES);

		internalTypeEClass = createEClass(INTERNAL_TYPE);
		createEAttribute(internalTypeEClass, INTERNAL_TYPE__NAME);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents()
	{
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		TypeModelPackage theTypeModelPackage = (TypeModelPackage)EPackage.Registry.INSTANCE.getEPackage(TypeModelPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		internalTypeEClass.getESuperTypes().add(theTypeModelPackage.getINamedType());

		// Initialize classes and features; add operations and parameters
		initEClass(builtInTypeModelEClass, BuiltInTypeModel.class, "BuiltInTypeModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBuiltInTypeModel_InternalTypes(), this.getInternalType(), null, "internalTypes", null, 0, -1, BuiltInTypeModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(internalTypeEClass, InternalType.class, "InternalType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInternalType_Name(), ecorePackage.getEString(), "name", null, 0, 1, InternalType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //BuiltInTypeModelPackageImpl
