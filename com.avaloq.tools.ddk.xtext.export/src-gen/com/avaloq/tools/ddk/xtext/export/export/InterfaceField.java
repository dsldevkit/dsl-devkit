/**
 */
package com.avaloq.tools.ddk.xtext.export.export;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interface Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceField#getField <em>Field</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getInterfaceField()
 * @model
 * @generated
 */
public interface InterfaceField extends InterfaceItem
{
	/**
	 * Returns the value of the '<em><b>Field</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field</em>' reference.
	 * @see #setField(EStructuralFeature)
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getInterfaceField_Field()
	 * @model
	 * @generated
	 */
	EStructuralFeature getField();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceField#getField <em>Field</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field</em>' reference.
	 * @see #getField()
	 * @generated
	 */
	void setField(EStructuralFeature value);

} // InterfaceField
