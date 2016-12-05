/**
 */
package com.avaloq.tools.ddk.checkcfg.checkcfg.impl;

import com.avaloq.tools.ddk.check.check.CheckPackage;

import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgFactory;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredLanguageValidator;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter;
import com.avaloq.tools.ddk.checkcfg.checkcfg.SeverityKind;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.xtext.xbase.XbasePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CheckcfgPackageImpl extends EPackageImpl implements CheckcfgPackage
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass checkConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass configuredLanguageValidatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass configuredCatalogEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass configuredCheckEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass configuredParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum severityKindEEnum = null;

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
	 * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CheckcfgPackageImpl()
	{
		super(eNS_URI, CheckcfgFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link CheckcfgPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CheckcfgPackage init()
	{
		if (isInited) return (CheckcfgPackage)EPackage.Registry.INSTANCE.getEPackage(CheckcfgPackage.eNS_URI);

		// Obtain or create and register package
		CheckcfgPackageImpl theCheckcfgPackage = (CheckcfgPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CheckcfgPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CheckcfgPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CheckPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theCheckcfgPackage.createPackageContents();

		// Initialize created meta-data
		theCheckcfgPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCheckcfgPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CheckcfgPackage.eNS_URI, theCheckcfgPackage);
		return theCheckcfgPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCheckConfiguration()
	{
		return checkConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCheckConfiguration_Name()
	{
		return (EAttribute)checkConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCheckConfiguration_LanguageValidatorConfigurations()
	{
		return (EReference)checkConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCheckConfiguration_LegacyCatalogConfigurations()
	{
		return (EReference)checkConfigurationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConfiguredLanguageValidator()
	{
		return configuredLanguageValidatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConfiguredLanguageValidator_Language()
	{
		return (EAttribute)configuredLanguageValidatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConfiguredLanguageValidator_CatalogConfigurations()
	{
		return (EReference)configuredLanguageValidatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConfiguredCatalog()
	{
		return configuredCatalogEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConfiguredCatalog_Catalog()
	{
		return (EReference)configuredCatalogEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConfiguredCatalog_CheckConfigurations()
	{
		return (EReference)configuredCatalogEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConfiguredCheck()
	{
		return configuredCheckEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConfiguredCheck_Severity()
	{
		return (EAttribute)configuredCheckEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConfiguredCheck_Check()
	{
		return (EReference)configuredCheckEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConfiguredCheck_ParameterConfigurations()
	{
		return (EReference)configuredCheckEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConfiguredParameter()
	{
		return configuredParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConfiguredParameter_Parameter()
	{
		return (EReference)configuredParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConfiguredParameter_NewValue()
	{
		return (EReference)configuredParameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSeverityKind()
	{
		return severityKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CheckcfgFactory getCheckcfgFactory()
	{
		return (CheckcfgFactory)getEFactoryInstance();
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
		checkConfigurationEClass = createEClass(CHECK_CONFIGURATION);
		createEAttribute(checkConfigurationEClass, CHECK_CONFIGURATION__NAME);
		createEReference(checkConfigurationEClass, CHECK_CONFIGURATION__LANGUAGE_VALIDATOR_CONFIGURATIONS);
		createEReference(checkConfigurationEClass, CHECK_CONFIGURATION__LEGACY_CATALOG_CONFIGURATIONS);

		configuredLanguageValidatorEClass = createEClass(CONFIGURED_LANGUAGE_VALIDATOR);
		createEAttribute(configuredLanguageValidatorEClass, CONFIGURED_LANGUAGE_VALIDATOR__LANGUAGE);
		createEReference(configuredLanguageValidatorEClass, CONFIGURED_LANGUAGE_VALIDATOR__CATALOG_CONFIGURATIONS);

		configuredCatalogEClass = createEClass(CONFIGURED_CATALOG);
		createEReference(configuredCatalogEClass, CONFIGURED_CATALOG__CATALOG);
		createEReference(configuredCatalogEClass, CONFIGURED_CATALOG__CHECK_CONFIGURATIONS);

		configuredCheckEClass = createEClass(CONFIGURED_CHECK);
		createEAttribute(configuredCheckEClass, CONFIGURED_CHECK__SEVERITY);
		createEReference(configuredCheckEClass, CONFIGURED_CHECK__CHECK);
		createEReference(configuredCheckEClass, CONFIGURED_CHECK__PARAMETER_CONFIGURATIONS);

		configuredParameterEClass = createEClass(CONFIGURED_PARAMETER);
		createEReference(configuredParameterEClass, CONFIGURED_PARAMETER__PARAMETER);
		createEReference(configuredParameterEClass, CONFIGURED_PARAMETER__NEW_VALUE);

		// Create enums
		severityKindEEnum = createEEnum(SEVERITY_KIND);
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
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		CheckPackage theCheckPackage = (CheckPackage)EPackage.Registry.INSTANCE.getEPackage(CheckPackage.eNS_URI);
		XbasePackage theXbasePackage = (XbasePackage)EPackage.Registry.INSTANCE.getEPackage(XbasePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(checkConfigurationEClass, CheckConfiguration.class, "CheckConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCheckConfiguration_Name(), theEcorePackage.getEString(), "name", null, 0, 1, CheckConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCheckConfiguration_LanguageValidatorConfigurations(), this.getConfiguredLanguageValidator(), null, "languageValidatorConfigurations", null, 0, -1, CheckConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCheckConfiguration_LegacyCatalogConfigurations(), this.getConfiguredCatalog(), null, "legacyCatalogConfigurations", null, 0, -1, CheckConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(configuredLanguageValidatorEClass, ConfiguredLanguageValidator.class, "ConfiguredLanguageValidator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConfiguredLanguageValidator_Language(), theEcorePackage.getEString(), "language", null, 0, 1, ConfiguredLanguageValidator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConfiguredLanguageValidator_CatalogConfigurations(), this.getConfiguredCatalog(), null, "catalogConfigurations", null, 0, -1, ConfiguredLanguageValidator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(configuredCatalogEClass, ConfiguredCatalog.class, "ConfiguredCatalog", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConfiguredCatalog_Catalog(), theCheckPackage.getCheckCatalog(), null, "catalog", null, 0, 1, ConfiguredCatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConfiguredCatalog_CheckConfigurations(), this.getConfiguredCheck(), null, "checkConfigurations", null, 0, -1, ConfiguredCatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(configuredCheckEClass, ConfiguredCheck.class, "ConfiguredCheck", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConfiguredCheck_Severity(), this.getSeverityKind(), "severity", null, 0, 1, ConfiguredCheck.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConfiguredCheck_Check(), theCheckPackage.getCheck(), null, "check", null, 0, 1, ConfiguredCheck.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConfiguredCheck_ParameterConfigurations(), this.getConfiguredParameter(), null, "parameterConfigurations", null, 0, -1, ConfiguredCheck.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(configuredParameterEClass, ConfiguredParameter.class, "ConfiguredParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConfiguredParameter_Parameter(), theCheckPackage.getFormalParameter(), null, "parameter", null, 0, 1, ConfiguredParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConfiguredParameter_NewValue(), theXbasePackage.getXExpression(), null, "newValue", null, 0, 1, ConfiguredParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(severityKindEEnum, SeverityKind.class, "SeverityKind");
		addEEnumLiteral(severityKindEEnum, SeverityKind.DEFAULT);
		addEEnumLiteral(severityKindEEnum, SeverityKind.ERROR);
		addEEnumLiteral(severityKindEEnum, SeverityKind.WARNING);
		addEEnumLiteral(severityKindEEnum, SeverityKind.INFO);
		addEEnumLiteral(severityKindEEnum, SeverityKind.IGNORE);

		// Create resource
		createResource(eNS_URI);
	}

} //CheckcfgPackageImpl
