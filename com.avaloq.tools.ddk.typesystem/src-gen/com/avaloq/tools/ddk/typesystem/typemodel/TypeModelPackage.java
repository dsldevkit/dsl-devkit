/**
 */
package com.avaloq.tools.ddk.typesystem.typemodel;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

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
 * @see com.avaloq.tools.ddk.typesystem.typemodel.TypeModelFactory
 * @model kind="package"
 * @generated
 */
public interface TypeModelPackage extends EPackage
{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "typemodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.avaloq.com/tools/ddk/typesystem/typemodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "typemodel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TypeModelPackage eINSTANCE = com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.IExpression <em>IExpression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IExpression
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getIExpression()
	 * @generated
	 */
	int IEXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>IExpression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IEXPRESSION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.IType <em>IType</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IType
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getIType()
	 * @generated
	 */
	int ITYPE = 1;

	/**
	 * The number of structural features of the '<em>IType</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITYPE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.INamedElement <em>INamed Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.INamedElement
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getINamedElement()
	 * @generated
	 */
	int INAMED_ELEMENT = 2;

	/**
	 * The number of structural features of the '<em>INamed Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INAMED_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.impl.OverrideDeclarationImpl <em>Override Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.OverrideDeclarationImpl
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getOverrideDeclaration()
	 * @generated
	 */
	int OVERRIDE_DECLARATION = 3;

	/**
	 * The number of structural features of the '<em>Override Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OVERRIDE_DECLARATION_FEATURE_COUNT = INAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.NamedElementImpl
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 4;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = INAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.INamedType <em>INamed Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.INamedType
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getINamedType()
	 * @generated
	 */
	int INAMED_TYPE = 5;

	/**
	 * The number of structural features of the '<em>INamed Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INAMED_TYPE_FEATURE_COUNT = INAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.impl.NamedTypeImpl <em>Named Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.NamedTypeImpl
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getNamedType()
	 * @generated
	 */
	int NAMED_TYPE = 6;

	/**
	 * The number of structural features of the '<em>Named Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_TYPE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter <em>IFormal Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getIFormalParameter()
	 * @generated
	 */
	int IFORMAL_PARAMETER = 7;

	/**
	 * The number of structural features of the '<em>IFormal Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFORMAL_PARAMETER_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.IActualParameter <em>IActual Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IActualParameter
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getIActualParameter()
	 * @generated
	 */
	int IACTUAL_PARAMETER = 8;

	/**
	 * The number of structural features of the '<em>IActual Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IACTUAL_PARAMETER_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.INamedActualParameter <em>INamed Actual Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.INamedActualParameter
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getINamedActualParameter()
	 * @generated
	 */
	int INAMED_ACTUAL_PARAMETER = 9;

	/**
	 * The number of structural features of the '<em>INamed Actual Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INAMED_ACTUAL_PARAMETER_FEATURE_COUNT = IACTUAL_PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.ICallable <em>ICallable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.ICallable
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getICallable()
	 * @generated
	 */
	int ICALLABLE = 16;

	/**
	 * The number of structural features of the '<em>ICallable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICALLABLE_FEATURE_COUNT = INAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.ISubprogram <em>ISubprogram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.ISubprogram
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getISubprogram()
	 * @generated
	 */
	int ISUBPROGRAM = 10;

	/**
	 * The number of structural features of the '<em>ISubprogram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISUBPROGRAM_FEATURE_COUNT = ICALLABLE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.IProcedure <em>IProcedure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IProcedure
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getIProcedure()
	 * @generated
	 */
	int IPROCEDURE = 11;

	/**
	 * The number of structural features of the '<em>IProcedure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IPROCEDURE_FEATURE_COUNT = ISUBPROGRAM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.IFunction <em>IFunction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IFunction
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getIFunction()
	 * @generated
	 */
	int IFUNCTION = 12;

	/**
	 * The number of structural features of the '<em>IFunction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFUNCTION_FEATURE_COUNT = ISUBPROGRAM_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link Iterable<? extends com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter> <em>IFormal Parameter List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Iterable<? extends com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter>
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getIFormalParameterList()
	 * @generated
	 */
	int IFORMAL_PARAMETER_LIST = 13;

	/**
	 * The number of structural features of the '<em>IFormal Parameter List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFORMAL_PARAMETER_LIST_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.impl.NamedFormalParameterImpl <em>Named Formal Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.NamedFormalParameterImpl
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getNamedFormalParameter()
	 * @generated
	 */
	int NAMED_FORMAL_PARAMETER = 14;

	/**
	 * The number of structural features of the '<em>Named Formal Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_FORMAL_PARAMETER_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.INamedFormalParameter <em>INamed Formal Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.INamedFormalParameter
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getINamedFormalParameter()
	 * @generated
	 */
	int INAMED_FORMAL_PARAMETER = 15;

	/**
	 * The number of structural features of the '<em>INamed Formal Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INAMED_FORMAL_PARAMETER_FEATURE_COUNT = INAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.impl.CallableImpl <em>Callable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.CallableImpl
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getCallable()
	 * @generated
	 */
	int CALLABLE = 17;

	/**
	 * The number of structural features of the '<em>Callable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLABLE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.typemodel.IExpression <em>IExpression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IExpression</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IExpression
	 * @generated
	 */
	EClass getIExpression();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.typemodel.IType <em>IType</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IType</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IType
	 * @generated
	 */
	EClass getIType();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.typemodel.INamedElement <em>INamed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>INamed Element</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.INamedElement
	 * @generated
	 */
	EClass getINamedElement();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.typemodel.OverrideDeclaration <em>Override Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Override Declaration</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.OverrideDeclaration
	 * @generated
	 */
	EClass getOverrideDeclaration();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.typemodel.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.typemodel.INamedType <em>INamed Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>INamed Type</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.INamedType
	 * @generated
	 */
	EClass getINamedType();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.typemodel.NamedType <em>Named Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Type</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.NamedType
	 * @generated
	 */
	EClass getNamedType();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter <em>IFormal Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IFormal Parameter</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter
	 * @generated
	 */
	EClass getIFormalParameter();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.typemodel.IActualParameter <em>IActual Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IActual Parameter</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IActualParameter
	 * @generated
	 */
	EClass getIActualParameter();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.typemodel.INamedActualParameter <em>INamed Actual Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>INamed Actual Parameter</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.INamedActualParameter
	 * @generated
	 */
	EClass getINamedActualParameter();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.typemodel.ISubprogram <em>ISubprogram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ISubprogram</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.ISubprogram
	 * @generated
	 */
	EClass getISubprogram();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.typemodel.IProcedure <em>IProcedure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IProcedure</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IProcedure
	 * @generated
	 */
	EClass getIProcedure();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.typemodel.IFunction <em>IFunction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IFunction</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IFunction
	 * @generated
	 */
	EClass getIFunction();

	/**
	 * Returns the meta object for class '{@link Iterable<? extends com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter> <em>IFormal Parameter List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IFormal Parameter List</em>'.
	 * @see Iterable<? extends com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter>
	 * @model instanceClass="Iterable<? extends com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter>"
	 * @generated
	 */
	EClass getIFormalParameterList();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.typemodel.NamedFormalParameter <em>Named Formal Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Formal Parameter</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.NamedFormalParameter
	 * @generated
	 */
	EClass getNamedFormalParameter();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.typemodel.INamedFormalParameter <em>INamed Formal Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>INamed Formal Parameter</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.INamedFormalParameter
	 * @generated
	 */
	EClass getINamedFormalParameter();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.typemodel.ICallable <em>ICallable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ICallable</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.ICallable
	 * @generated
	 */
	EClass getICallable();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.typesystem.typemodel.Callable <em>Callable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Callable</em>'.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.Callable
	 * @generated
	 */
	EClass getCallable();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TypeModelFactory getTypeModelFactory();

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
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.IExpression <em>IExpression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.IExpression
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getIExpression()
		 * @generated
		 */
		EClass IEXPRESSION = eINSTANCE.getIExpression();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.IType <em>IType</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.IType
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getIType()
		 * @generated
		 */
		EClass ITYPE = eINSTANCE.getIType();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.INamedElement <em>INamed Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.INamedElement
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getINamedElement()
		 * @generated
		 */
		EClass INAMED_ELEMENT = eINSTANCE.getINamedElement();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.impl.OverrideDeclarationImpl <em>Override Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.OverrideDeclarationImpl
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getOverrideDeclaration()
		 * @generated
		 */
		EClass OVERRIDE_DECLARATION = eINSTANCE.getOverrideDeclaration();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.NamedElementImpl
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.INamedType <em>INamed Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.INamedType
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getINamedType()
		 * @generated
		 */
		EClass INAMED_TYPE = eINSTANCE.getINamedType();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.impl.NamedTypeImpl <em>Named Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.NamedTypeImpl
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getNamedType()
		 * @generated
		 */
		EClass NAMED_TYPE = eINSTANCE.getNamedType();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter <em>IFormal Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getIFormalParameter()
		 * @generated
		 */
		EClass IFORMAL_PARAMETER = eINSTANCE.getIFormalParameter();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.IActualParameter <em>IActual Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.IActualParameter
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getIActualParameter()
		 * @generated
		 */
		EClass IACTUAL_PARAMETER = eINSTANCE.getIActualParameter();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.INamedActualParameter <em>INamed Actual Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.INamedActualParameter
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getINamedActualParameter()
		 * @generated
		 */
		EClass INAMED_ACTUAL_PARAMETER = eINSTANCE.getINamedActualParameter();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.ISubprogram <em>ISubprogram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.ISubprogram
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getISubprogram()
		 * @generated
		 */
		EClass ISUBPROGRAM = eINSTANCE.getISubprogram();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.IProcedure <em>IProcedure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.IProcedure
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getIProcedure()
		 * @generated
		 */
		EClass IPROCEDURE = eINSTANCE.getIProcedure();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.IFunction <em>IFunction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.IFunction
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getIFunction()
		 * @generated
		 */
		EClass IFUNCTION = eINSTANCE.getIFunction();

		/**
		 * The meta object literal for the '{@link Iterable<? extends com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter> <em>IFormal Parameter List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Iterable<? extends com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter>
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getIFormalParameterList()
		 * @generated
		 */
		EClass IFORMAL_PARAMETER_LIST = eINSTANCE.getIFormalParameterList();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.impl.NamedFormalParameterImpl <em>Named Formal Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.NamedFormalParameterImpl
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getNamedFormalParameter()
		 * @generated
		 */
		EClass NAMED_FORMAL_PARAMETER = eINSTANCE.getNamedFormalParameter();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.INamedFormalParameter <em>INamed Formal Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.INamedFormalParameter
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getINamedFormalParameter()
		 * @generated
		 */
		EClass INAMED_FORMAL_PARAMETER = eINSTANCE.getINamedFormalParameter();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.ICallable <em>ICallable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.ICallable
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getICallable()
		 * @generated
		 */
		EClass ICALLABLE = eINSTANCE.getICallable();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.typesystem.typemodel.impl.CallableImpl <em>Callable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.CallableImpl
		 * @see com.avaloq.tools.ddk.typesystem.typemodel.impl.TypeModelPackageImpl#getCallable()
		 * @generated
		 */
		EClass CALLABLE = eINSTANCE.getCallable();

	}

} //TypeModelPackage
