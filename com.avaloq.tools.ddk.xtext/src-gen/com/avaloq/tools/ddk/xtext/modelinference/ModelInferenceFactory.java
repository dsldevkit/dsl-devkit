/**
 */
package com.avaloq.tools.ddk.xtext.modelinference;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.xtext.modelinference.ModelInferencePackage
 * @generated
 */
public interface ModelInferenceFactory extends EFactory
{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelInferenceFactory eINSTANCE = com.avaloq.tools.ddk.xtext.modelinference.impl.ModelInferenceFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Inference Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Inference Container</em>'.
	 * @generated
	 */
	InferenceContainer createInferenceContainer();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ModelInferencePackage getModelInferencePackage();

} //ModelInferenceFactory
