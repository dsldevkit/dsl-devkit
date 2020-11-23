/**
 */
package com.avaloq.tools.ddk.typesystem.typemodel.impl;

import com.avaloq.tools.ddk.typesystem.typemodel.Callable;
import com.avaloq.tools.ddk.typesystem.typemodel.IActualParameter;
import com.avaloq.tools.ddk.typesystem.typemodel.ICallable;
import com.avaloq.tools.ddk.typesystem.typemodel.ICaseSensitiveNamedElement;
import com.avaloq.tools.ddk.typesystem.typemodel.IExpression;
import com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter;
import com.avaloq.tools.ddk.typesystem.typemodel.IFunction;
import com.avaloq.tools.ddk.typesystem.typemodel.INamedActualParameter;
import com.avaloq.tools.ddk.typesystem.typemodel.INamedElement;
import com.avaloq.tools.ddk.typesystem.typemodel.INamedFormalParameter;
import com.avaloq.tools.ddk.typesystem.typemodel.INamedType;
import com.avaloq.tools.ddk.typesystem.typemodel.IProcedure;
import com.avaloq.tools.ddk.typesystem.typemodel.ISubprogram;
import com.avaloq.tools.ddk.typesystem.typemodel.IType;
import com.avaloq.tools.ddk.typesystem.typemodel.NamedElement;
import com.avaloq.tools.ddk.typesystem.typemodel.NamedFormalParameter;
import com.avaloq.tools.ddk.typesystem.typemodel.NamedType;
import com.avaloq.tools.ddk.typesystem.typemodel.OverrideDeclaration;
import com.avaloq.tools.ddk.typesystem.typemodel.TypeModelFactory;
import com.avaloq.tools.ddk.typesystem.typemodel.TypeModelPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TypeModelPackageImpl extends EPackageImpl implements TypeModelPackage
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iNamedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass overrideDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iCaseSensitiveNamedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iNamedTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iFormalParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iActualParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iNamedActualParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iSubprogramEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iProcedureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iFunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iFormalParameterListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedFormalParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iNamedFormalParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iCallableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass callableEClass = null;

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
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.TypeModelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TypeModelPackageImpl()
	{
		super(eNS_URI, TypeModelFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link TypeModelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TypeModelPackage init()
	{
		if (isInited) return (TypeModelPackage)EPackage.Registry.INSTANCE.getEPackage(TypeModelPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredTypeModelPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		TypeModelPackageImpl theTypeModelPackage = registeredTypeModelPackage instanceof TypeModelPackageImpl ? (TypeModelPackageImpl)registeredTypeModelPackage : new TypeModelPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theTypeModelPackage.createPackageContents();

		// Initialize created meta-data
		theTypeModelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTypeModelPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TypeModelPackage.eNS_URI, theTypeModelPackage);
		return theTypeModelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIExpression()
	{
		return iExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIType()
	{
		return iTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getINamedElement()
	{
		return iNamedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOverrideDeclaration()
	{
		return overrideDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedElement()
	{
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getICaseSensitiveNamedElement()
	{
		return iCaseSensitiveNamedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getINamedType()
	{
		return iNamedTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedType()
	{
		return namedTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIFormalParameter()
	{
		return iFormalParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIActualParameter()
	{
		return iActualParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getINamedActualParameter()
	{
		return iNamedActualParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getISubprogram()
	{
		return iSubprogramEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIProcedure()
	{
		return iProcedureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIFunction()
	{
		return iFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIFormalParameterList()
	{
		return iFormalParameterListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedFormalParameter()
	{
		return namedFormalParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getINamedFormalParameter()
	{
		return iNamedFormalParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getICallable()
	{
		return iCallableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCallable()
	{
		return callableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeModelFactory getTypeModelFactory()
	{
		return (TypeModelFactory)getEFactoryInstance();
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
		iExpressionEClass = createEClass(IEXPRESSION);

		iTypeEClass = createEClass(ITYPE);

		iNamedElementEClass = createEClass(INAMED_ELEMENT);

		overrideDeclarationEClass = createEClass(OVERRIDE_DECLARATION);

		namedElementEClass = createEClass(NAMED_ELEMENT);

		iCaseSensitiveNamedElementEClass = createEClass(ICASE_SENSITIVE_NAMED_ELEMENT);

		iNamedTypeEClass = createEClass(INAMED_TYPE);

		namedTypeEClass = createEClass(NAMED_TYPE);

		iFormalParameterEClass = createEClass(IFORMAL_PARAMETER);

		iActualParameterEClass = createEClass(IACTUAL_PARAMETER);

		iNamedActualParameterEClass = createEClass(INAMED_ACTUAL_PARAMETER);

		iSubprogramEClass = createEClass(ISUBPROGRAM);

		iProcedureEClass = createEClass(IPROCEDURE);

		iFunctionEClass = createEClass(IFUNCTION);

		iFormalParameterListEClass = createEClass(IFORMAL_PARAMETER_LIST);

		namedFormalParameterEClass = createEClass(NAMED_FORMAL_PARAMETER);

		iNamedFormalParameterEClass = createEClass(INAMED_FORMAL_PARAMETER);

		iCallableEClass = createEClass(ICALLABLE);

		callableEClass = createEClass(CALLABLE);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		overrideDeclarationEClass.getESuperTypes().add(this.getINamedElement());
		namedElementEClass.getESuperTypes().add(this.getINamedElement());
		iNamedTypeEClass.getESuperTypes().add(this.getINamedElement());
		iNamedTypeEClass.getESuperTypes().add(this.getIType());
		namedTypeEClass.getESuperTypes().add(this.getNamedElement());
		namedTypeEClass.getESuperTypes().add(this.getINamedType());
		iNamedActualParameterEClass.getESuperTypes().add(this.getIActualParameter());
		iSubprogramEClass.getESuperTypes().add(this.getICallable());
		iProcedureEClass.getESuperTypes().add(this.getISubprogram());
		iFunctionEClass.getESuperTypes().add(this.getISubprogram());
		namedFormalParameterEClass.getESuperTypes().add(this.getNamedElement());
		namedFormalParameterEClass.getESuperTypes().add(this.getINamedFormalParameter());
		iNamedFormalParameterEClass.getESuperTypes().add(this.getINamedElement());
		iNamedFormalParameterEClass.getESuperTypes().add(this.getIFormalParameter());
		iCallableEClass.getESuperTypes().add(this.getINamedElement());
		callableEClass.getESuperTypes().add(this.getNamedElement());
		callableEClass.getESuperTypes().add(this.getICallable());

		// Initialize classes and features; add operations and parameters
		initEClass(iExpressionEClass, IExpression.class, "IExpression", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iTypeEClass, IType.class, "IType", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iNamedElementEClass, INamedElement.class, "INamedElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(iNamedElementEClass, ecorePackage.getEString(), "getName", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(overrideDeclarationEClass, OverrideDeclaration.class, "OverrideDeclaration", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(overrideDeclarationEClass, this.getINamedElement(), "getDefinition", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(overrideDeclarationEClass, ecorePackage.getEBoolean(), "isOverride", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(namedElementEClass, NamedElement.class, "NamedElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iCaseSensitiveNamedElementEClass, ICaseSensitiveNamedElement.class, "ICaseSensitiveNamedElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iNamedTypeEClass, INamedType.class, "INamedType", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(namedTypeEClass, NamedType.class, "NamedType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iFormalParameterEClass, IFormalParameter.class, "IFormalParameter", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(iFormalParameterEClass, ecorePackage.getEBoolean(), "isMandatory", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(iFormalParameterEClass, ecorePackage.getEBoolean(), "isMulti", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(iFormalParameterEClass, this.getIType(), "getType", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iActualParameterEClass, IActualParameter.class, "IActualParameter", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(iActualParameterEClass, this.getIExpression(), "getValue", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iNamedActualParameterEClass, INamedActualParameter.class, "INamedActualParameter", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(iNamedActualParameterEClass, ecorePackage.getEString(), "getName", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iSubprogramEClass, ISubprogram.class, "ISubprogram", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(iSubprogramEClass, this.getIFormalParameterList(), "getParameters", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iProcedureEClass, IProcedure.class, "IProcedure", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iFunctionEClass, IFunction.class, "IFunction", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(iFunctionEClass, this.getIType(), "getReturnType", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iFormalParameterListEClass, Iterable.class, "IFormalParameterList", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS, "Iterable<? extends com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter>");

		initEClass(namedFormalParameterEClass, NamedFormalParameter.class, "NamedFormalParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iNamedFormalParameterEClass, INamedFormalParameter.class, "INamedFormalParameter", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iCallableEClass, ICallable.class, "ICallable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(callableEClass, Callable.class, "Callable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //TypeModelPackageImpl
