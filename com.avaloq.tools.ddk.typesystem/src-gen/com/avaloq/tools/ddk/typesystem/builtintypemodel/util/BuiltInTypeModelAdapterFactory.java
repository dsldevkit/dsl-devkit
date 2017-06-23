/**
 */
package com.avaloq.tools.ddk.typesystem.builtintypemodel.util;

import com.avaloq.tools.ddk.typesystem.builtintypemodel.*;

import com.avaloq.tools.ddk.typesystem.typemodel.INamedElement;
import com.avaloq.tools.ddk.typesystem.typemodel.INamedType;
import com.avaloq.tools.ddk.typesystem.typemodel.IType;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModelPackage
 * @generated
 */
public class BuiltInTypeModelAdapterFactory extends AdapterFactoryImpl
{
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static BuiltInTypeModelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BuiltInTypeModelAdapterFactory()
	{
		if (modelPackage == null)
		{
			modelPackage = BuiltInTypeModelPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object)
	{
		if (object == modelPackage)
		{
			return true;
		}
		if (object instanceof EObject)
		{
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BuiltInTypeModelSwitch<Adapter> modelSwitch =
		new BuiltInTypeModelSwitch<Adapter>()
		{
			@Override
			public Adapter caseBuiltInTypeModel(BuiltInTypeModel object)
			{
				return createBuiltInTypeModelAdapter();
			}
			@Override
			public Adapter caseInternalType(InternalType object)
			{
				return createInternalTypeAdapter();
			}
			@Override
			public Adapter caseINamedElement(INamedElement object)
			{
				return createINamedElementAdapter();
			}
			@Override
			public Adapter caseIType(IType object)
			{
				return createITypeAdapter();
			}
			@Override
			public Adapter caseINamedType(INamedType object)
			{
				return createINamedTypeAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object)
			{
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target)
	{
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModel <em>Built In Type Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModel
	 * @generated
	 */
	public Adapter createBuiltInTypeModelAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.builtintypemodel.InternalType <em>Internal Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.builtintypemodel.InternalType
	 * @generated
	 */
	public Adapter createInternalTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.INamedElement <em>INamed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.INamedElement
	 * @generated
	 */
	public Adapter createINamedElementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.IType <em>IType</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.IType
	 * @generated
	 */
	public Adapter createITypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.typesystem.typemodel.INamedType <em>INamed Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.typesystem.typemodel.INamedType
	 * @generated
	 */
	public Adapter createINamedTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter()
	{
		return null;
	}

} //BuiltInTypeModelAdapterFactory
