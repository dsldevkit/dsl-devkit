/**
 */
package com.avaloq.tools.ddk.xtext.export.export.impl;

import com.avaloq.tools.ddk.xtext.export.export.*;

import org.eclipse.emf.ecore.EClass;
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
public class ExportFactoryImpl extends EFactoryImpl implements ExportFactory
{
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ExportFactory init()
	{
		try
		{
			ExportFactory theExportFactory = (ExportFactory)EPackage.Registry.INSTANCE.getEFactory(ExportPackage.eNS_URI);
			if (theExportFactory != null)
			{
				return theExportFactory;
			}
		}
		catch (Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ExportFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExportFactoryImpl()
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
			case ExportPackage.EXPORT_MODEL: return createExportModel();
			case ExportPackage.IMPORT: return createImport();
			case ExportPackage.EXTENSION: return createExtension();
			case ExportPackage.DECLARATION_FOR_TYPE: return createDeclarationForType();
			case ExportPackage.INTERFACE: return createInterface();
			case ExportPackage.INTERFACE_ITEM: return createInterfaceItem();
			case ExportPackage.INTERFACE_FIELD: return createInterfaceField();
			case ExportPackage.INTERFACE_NAVIGATION: return createInterfaceNavigation();
			case ExportPackage.INTERFACE_EXPRESSION: return createInterfaceExpression();
			case ExportPackage.EXPORT: return createExport();
			case ExportPackage.USER_DATA: return createUserData();
			case ExportPackage.ATTRIBUTE: return createAttribute();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExportModel createExportModel()
	{
		ExportModelImpl exportModel = new ExportModelImpl();
		return exportModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Import createImport()
	{
		ImportImplCustom import_ = new ImportImplCustom();
		return import_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Extension createExtension()
	{
		ExtensionImpl extension = new ExtensionImpl();
		return extension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeclarationForType createDeclarationForType()
	{
		DeclarationForTypeImpl declarationForType = new DeclarationForTypeImpl();
		return declarationForType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Interface createInterface()
	{
		InterfaceImpl interface_ = new InterfaceImpl();
		return interface_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InterfaceItem createInterfaceItem()
	{
		InterfaceItemImpl interfaceItem = new InterfaceItemImpl();
		return interfaceItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InterfaceField createInterfaceField()
	{
		InterfaceFieldImpl interfaceField = new InterfaceFieldImpl();
		return interfaceField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InterfaceNavigation createInterfaceNavigation()
	{
		InterfaceNavigationImpl interfaceNavigation = new InterfaceNavigationImpl();
		return interfaceNavigation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InterfaceExpression createInterfaceExpression()
	{
		InterfaceExpressionImpl interfaceExpression = new InterfaceExpressionImpl();
		return interfaceExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Export createExport()
	{
		ExportImplCustom export = new ExportImplCustom();
		return export;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserData createUserData()
	{
		UserDataImpl userData = new UserDataImpl();
		return userData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute createAttribute()
	{
		AttributeImpl attribute = new AttributeImpl();
		return attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExportPackage getExportPackage()
	{
		return (ExportPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ExportPackage getPackage()
	{
		return ExportPackage.eINSTANCE;
	}

} //ExportFactoryImpl
