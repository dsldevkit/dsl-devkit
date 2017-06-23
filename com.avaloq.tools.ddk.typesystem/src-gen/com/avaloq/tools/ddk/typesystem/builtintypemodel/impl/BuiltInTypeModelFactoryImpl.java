/**
 */
package com.avaloq.tools.ddk.typesystem.builtintypemodel.impl;

import com.avaloq.tools.ddk.typesystem.builtintypemodel.*;

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
public class BuiltInTypeModelFactoryImpl extends EFactoryImpl implements BuiltInTypeModelFactory
{
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BuiltInTypeModelFactory init()
	{
		try
		{
			BuiltInTypeModelFactory theBuiltInTypeModelFactory = (BuiltInTypeModelFactory)EPackage.Registry.INSTANCE.getEFactory(BuiltInTypeModelPackage.eNS_URI);
			if (theBuiltInTypeModelFactory != null)
			{
				return theBuiltInTypeModelFactory;
			}
		}
		catch (Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BuiltInTypeModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BuiltInTypeModelFactoryImpl()
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
			case BuiltInTypeModelPackage.BUILT_IN_TYPE_MODEL: return createBuiltInTypeModel();
			case BuiltInTypeModelPackage.INTERNAL_TYPE: return createInternalType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BuiltInTypeModel createBuiltInTypeModel()
	{
		BuiltInTypeModelImpl builtInTypeModel = new BuiltInTypeModelImpl();
		return builtInTypeModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InternalType createInternalType()
	{
		InternalTypeImpl internalType = new InternalTypeImpl();
		return internalType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BuiltInTypeModelPackage getBuiltInTypeModelPackage()
	{
		return (BuiltInTypeModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static BuiltInTypeModelPackage getPackage()
	{
		return BuiltInTypeModelPackage.eINSTANCE;
	}

} //BuiltInTypeModelFactoryImpl
