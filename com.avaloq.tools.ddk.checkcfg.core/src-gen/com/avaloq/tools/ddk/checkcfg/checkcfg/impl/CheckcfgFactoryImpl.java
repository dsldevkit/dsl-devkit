/**
 */
package com.avaloq.tools.ddk.checkcfg.checkcfg.impl;

import com.avaloq.tools.ddk.checkcfg.checkcfg.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CheckcfgFactoryImpl extends EFactoryImpl implements CheckcfgFactory
{
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CheckcfgFactory init()
	{
		try
		{
			CheckcfgFactory theCheckcfgFactory = (CheckcfgFactory)EPackage.Registry.INSTANCE.getEFactory(CheckcfgPackage.eNS_URI);
			if (theCheckcfgFactory != null)
			{
				return theCheckcfgFactory;
			}
		}
		catch (Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CheckcfgFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CheckcfgFactoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass)
	{
		switch (eClass.getClassifierID())
		{
			case CheckcfgPackage.CHECK_CONFIGURATION: return createCheckConfiguration();
			case CheckcfgPackage.CONFIGURED_LANGUAGE_VALIDATOR: return createConfiguredLanguageValidator();
			case CheckcfgPackage.CONFIGURED_CATALOG: return createConfiguredCatalog();
			case CheckcfgPackage.CONFIGURED_CHECK: return createConfiguredCheck();
			case CheckcfgPackage.CONFIGURED_PARAMETER: return createConfiguredParameter();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue)
	{
		switch (eDataType.getClassifierID())
		{
			case CheckcfgPackage.SEVERITY_KIND:
				return createSeverityKindFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue)
	{
		switch (eDataType.getClassifierID())
		{
			case CheckcfgPackage.SEVERITY_KIND:
				return convertSeverityKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CheckConfiguration createCheckConfiguration()
	{
		CheckConfigurationImpl checkConfiguration = new CheckConfigurationImpl();
		return checkConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfiguredLanguageValidator createConfiguredLanguageValidator()
	{
		ConfiguredLanguageValidatorImpl configuredLanguageValidator = new ConfiguredLanguageValidatorImpl();
		return configuredLanguageValidator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfiguredCatalog createConfiguredCatalog()
	{
		ConfiguredCatalogImpl configuredCatalog = new ConfiguredCatalogImpl();
		return configuredCatalog;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfiguredCheck createConfiguredCheck()
	{
		ConfiguredCheckImpl configuredCheck = new ConfiguredCheckImpl();
		return configuredCheck;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfiguredParameter createConfiguredParameter()
	{
		ConfiguredParameterImpl configuredParameter = new ConfiguredParameterImpl();
		return configuredParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SeverityKind createSeverityKindFromString(EDataType eDataType, String initialValue)
	{
		SeverityKind result = SeverityKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSeverityKindToString(EDataType eDataType, Object instanceValue)
	{
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CheckcfgPackage getCheckcfgPackage()
	{
		return (CheckcfgPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CheckcfgPackage getPackage()
	{
		return CheckcfgPackage.eINSTANCE;
	}

} //CheckcfgFactoryImpl
