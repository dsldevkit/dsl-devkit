/**
 */
package com.avaloq.tools.ddk.xtext.modelinference;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.xtext.modelinference.ModelInferenceFactory
 * @model kind="package"
 * @generated
 */
public interface ModelInferencePackage extends EPackage
{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "modelinference";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.avaloq.com/tools/ddk/xtext/modelinference";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "mi";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelInferencePackage eINSTANCE = com.avaloq.tools.ddk.xtext.modelinference.impl.ModelInferencePackageImpl.init();

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.modelinference.impl.InferenceContainerImpl <em>Inference Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.xtext.modelinference.impl.InferenceContainerImpl
	 * @see com.avaloq.tools.ddk.xtext.modelinference.impl.ModelInferencePackageImpl#getInferenceContainer()
	 * @generated
	 */
	int INFERENCE_CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFERENCE_CONTAINER__CONTENTS = 0;

	/**
	 * The feature id for the '<em><b>Fragments</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFERENCE_CONTAINER__FRAGMENTS = 1;

	/**
	 * The number of structural features of the '<em>Inference Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFERENCE_CONTAINER_FEATURE_COUNT = 2;

	/**
	 * The operation id for the '<em>Get Fragment Segment</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFERENCE_CONTAINER___GET_FRAGMENT_SEGMENT__EOBJECT = 0;

	/**
	 * The operation id for the '<em>Get EObject</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFERENCE_CONTAINER___GET_EOBJECT__STRING = 1;

	/**
	 * The number of operations of the '<em>Inference Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFERENCE_CONTAINER_OPERATION_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.modelinference.InferenceContainer <em>Inference Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inference Container</em>'.
	 * @see com.avaloq.tools.ddk.xtext.modelinference.InferenceContainer
	 * @generated
	 */
	EClass getInferenceContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.modelinference.InferenceContainer#getContents <em>Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contents</em>'.
	 * @see com.avaloq.tools.ddk.xtext.modelinference.InferenceContainer#getContents()
	 * @see #getInferenceContainer()
	 * @generated
	 */
	EReference getInferenceContainer_Contents();

	/**
	 * Returns the meta object for the attribute list '{@link com.avaloq.tools.ddk.xtext.modelinference.InferenceContainer#getFragments <em>Fragments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Fragments</em>'.
	 * @see com.avaloq.tools.ddk.xtext.modelinference.InferenceContainer#getFragments()
	 * @see #getInferenceContainer()
	 * @generated
	 */
	EAttribute getInferenceContainer_Fragments();

	/**
	 * Returns the meta object for the '{@link com.avaloq.tools.ddk.xtext.modelinference.InferenceContainer#getFragmentSegment(org.eclipse.emf.ecore.EObject) <em>Get Fragment Segment</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Fragment Segment</em>' operation.
	 * @see com.avaloq.tools.ddk.xtext.modelinference.InferenceContainer#getFragmentSegment(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	EOperation getInferenceContainer__GetFragmentSegment__EObject();

	/**
	 * Returns the meta object for the '{@link com.avaloq.tools.ddk.xtext.modelinference.InferenceContainer#getEObject(java.lang.String) <em>Get EObject</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get EObject</em>' operation.
	 * @see com.avaloq.tools.ddk.xtext.modelinference.InferenceContainer#getEObject(java.lang.String)
	 * @generated
	 */
	EOperation getInferenceContainer__GetEObject__String();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelInferenceFactory getModelInferenceFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals
	{
		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.modelinference.impl.InferenceContainerImpl <em>Inference Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.xtext.modelinference.impl.InferenceContainerImpl
		 * @see com.avaloq.tools.ddk.xtext.modelinference.impl.ModelInferencePackageImpl#getInferenceContainer()
		 * @generated
		 */
		EClass INFERENCE_CONTAINER = eINSTANCE.getInferenceContainer();

		/**
		 * The meta object literal for the '<em><b>Contents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFERENCE_CONTAINER__CONTENTS = eINSTANCE.getInferenceContainer_Contents();

		/**
		 * The meta object literal for the '<em><b>Fragments</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFERENCE_CONTAINER__FRAGMENTS = eINSTANCE.getInferenceContainer_Fragments();

		/**
		 * The meta object literal for the '<em><b>Get Fragment Segment</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation INFERENCE_CONTAINER___GET_FRAGMENT_SEGMENT__EOBJECT = eINSTANCE.getInferenceContainer__GetFragmentSegment__EObject();

		/**
		 * The meta object literal for the '<em><b>Get EObject</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation INFERENCE_CONTAINER___GET_EOBJECT__STRING = eINSTANCE.getInferenceContainer__GetEObject__String();

	}

} //ModelInferencePackage
