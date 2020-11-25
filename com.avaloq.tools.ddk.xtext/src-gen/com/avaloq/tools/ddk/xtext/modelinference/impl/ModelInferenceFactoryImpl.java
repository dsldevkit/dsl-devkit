/**
 */
package com.avaloq.tools.ddk.xtext.modelinference.impl;

import com.avaloq.tools.ddk.xtext.modelinference.*;

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
public class ModelInferenceFactoryImpl extends EFactoryImpl implements ModelInferenceFactory
{
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ModelInferenceFactory init()
	{
		try
		{
			ModelInferenceFactory theModelInferenceFactory = (ModelInferenceFactory)EPackage.Registry.INSTANCE.getEFactory(ModelInferencePackage.eNS_URI);
			if (theModelInferenceFactory != null)
			{
				return theModelInferenceFactory;
			}
		}
		catch (Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ModelInferenceFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelInferenceFactoryImpl()
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
			case ModelInferencePackage.INFERENCE_CONTAINER: return createInferenceContainer();
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
	public InferenceContainer createInferenceContainer()
	{
		InferenceContainerImplCustom inferenceContainer = new InferenceContainerImplCustom();
		return inferenceContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ModelInferencePackage getModelInferencePackage()
	{
		return (ModelInferencePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ModelInferencePackage getPackage()
	{
		return ModelInferencePackage.eINSTANCE;
	}

} //ModelInferenceFactoryImpl
