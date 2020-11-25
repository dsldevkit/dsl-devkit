/**
 */
package com.avaloq.tools.ddk.xtext.export.export;

import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interface Navigation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceNavigation#getRef <em>Ref</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getInterfaceNavigation()
 * @model
 * @generated
 */
public interface InterfaceNavigation extends InterfaceItem
{
	/**
	 * Returns the value of the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ref</em>' reference.
	 * @see #setRef(EReference)
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getInterfaceNavigation_Ref()
	 * @model
	 * @generated
	 */
	EReference getRef();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceNavigation#getRef <em>Ref</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ref</em>' reference.
	 * @see #getRef()
	 * @generated
	 */
	void setRef(EReference value);

} // InterfaceNavigation
