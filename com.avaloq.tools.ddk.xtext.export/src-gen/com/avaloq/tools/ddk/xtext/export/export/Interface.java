/**
 */
package com.avaloq.tools.ddk.xtext.export.export;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interface</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.Interface#getItems <em>Items</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getInterface()
 * @model
 * @generated
 */
public interface Interface extends DeclarationForType
{
	/**
	 * Returns the value of the '<em><b>Items</b></em>' containment reference list.
	 * The list contents are of type {@link com.avaloq.tools.ddk.xtext.export.export.InterfaceItem}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Items</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Items</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getInterface_Items()
	 * @model containment="true"
	 * @generated
	 */
	EList<InterfaceItem> getItems();

} // Interface
