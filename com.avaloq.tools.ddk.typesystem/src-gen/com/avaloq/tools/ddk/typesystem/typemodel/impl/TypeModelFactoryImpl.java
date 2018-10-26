/**
 */
package com.avaloq.tools.ddk.typesystem.typemodel.impl;

import com.avaloq.tools.ddk.typesystem.typemodel.*;

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
public class TypeModelFactoryImpl extends EFactoryImpl implements TypeModelFactory
{
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TypeModelFactory init()
	{
		try
		{
			TypeModelFactory theTypeModelFactory = (TypeModelFactory)EPackage.Registry.INSTANCE.getEFactory(TypeModelPackage.eNS_URI);
			if (theTypeModelFactory != null)
			{
				return theTypeModelFactory;
			}
		}
		catch (Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TypeModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeModelFactoryImpl()
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
			case TypeModelPackage.NAMED_ELEMENT: return createNamedElement();
			case TypeModelPackage.NAMED_TYPE: return createNamedType();
			case TypeModelPackage.NAMED_FORMAL_PARAMETER: return createNamedFormalParameter();
			case TypeModelPackage.CALLABLE: return createCallable();
			case TypeModelPackage.ICUSTOM_EXPORTED_NAME: return createICustomExportedName();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamedElement createNamedElement()
	{
		NamedElementImplCustom namedElement = new NamedElementImplCustom();
		return namedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamedType createNamedType()
	{
		NamedTypeImpl namedType = new NamedTypeImpl();
		return namedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamedFormalParameter createNamedFormalParameter()
	{
		NamedFormalParameterImplCustom namedFormalParameter = new NamedFormalParameterImplCustom();
		return namedFormalParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Callable createCallable()
	{
		CallableImpl callable = new CallableImpl();
		return callable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ICustomExportedName createICustomExportedName()
	{
		ICustomExportedNameImpl iCustomExportedName = new ICustomExportedNameImpl();
		return iCustomExportedName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeModelPackage getTypeModelPackage()
	{
		return (TypeModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TypeModelPackage getPackage()
	{
		return TypeModelPackage.eINSTANCE;
	}

} //TypeModelFactoryImpl
