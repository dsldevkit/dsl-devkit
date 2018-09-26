/**
 */
package com.avaloq.tools.ddk.xtext.modelinference;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Inference Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.modelinference.InferenceContainer#getContents <em>Contents</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.modelinference.InferenceContainer#getFragments <em>Fragments</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.modelinference.ModelInferencePackage#getInferenceContainer()
 * @model
 * @generated
 */
public interface InferenceContainer extends EObject
{
	/**
	 * Returns the value of the '<em><b>Contents</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contents</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contents</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.xtext.modelinference.ModelInferencePackage#getInferenceContainer_Contents()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getContents();

	/**
	 * Returns the value of the '<em><b>Fragments</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fragments</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fragments</em>' attribute list.
	 * @see com.avaloq.tools.ddk.xtext.modelinference.ModelInferencePackage#getInferenceContainer_Fragments()
	 * @model
	 * @generated
	 */
	EList<String> getFragments();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String getFragmentSegment(EObject object);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	EObject getEObject(String fragmentSegment);

} // InferenceContainer
