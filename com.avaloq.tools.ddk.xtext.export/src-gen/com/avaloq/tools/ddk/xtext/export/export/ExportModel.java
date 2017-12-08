/**
 */
package com.avaloq.tools.ddk.xtext.export.export;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#getImports <em>Imports</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#getInterfaces <em>Interfaces</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#getExports <em>Exports</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExportModel()
 * @model
 * @generated
 */
public interface ExportModel extends EObject
{
	/**
	 * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
	 * The list contents are of type {@link com.avaloq.tools.ddk.xtext.export.export.Import}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imports</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExportModel_Imports()
	 * @model containment="true"
	 * @generated
	 */
	EList<Import> getImports();

	/**
	 * Returns the value of the '<em><b>Extensions</b></em>' containment reference list.
	 * The list contents are of type {@link com.avaloq.tools.ddk.xtext.export.export.Extension}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extensions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extensions</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExportModel_Extensions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Extension> getExtensions();

	/**
	 * Returns the value of the '<em><b>Interfaces</b></em>' containment reference list.
	 * The list contents are of type {@link com.avaloq.tools.ddk.xtext.export.export.Interface}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interfaces</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interfaces</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExportModel_Interfaces()
	 * @model containment="true"
	 * @generated
	 */
	EList<Interface> getInterfaces();

	/**
	 * Returns the value of the '<em><b>Exports</b></em>' containment reference list.
	 * The list contents are of type {@link com.avaloq.tools.ddk.xtext.export.export.Export}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exports</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExportModel_Exports()
	 * @model containment="true"
	 * @generated
	 */
	EList<Export> getExports();

} // ExportModel
