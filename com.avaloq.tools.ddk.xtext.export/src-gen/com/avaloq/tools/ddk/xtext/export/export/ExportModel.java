/**
 */
package com.avaloq.tools.ddk.xtext.export.export;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.Grammar;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#isExtension <em>Extension</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#getTargetGrammar <em>Target Grammar</em>}</li>
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
	 * Returns the value of the '<em><b>Extension</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extension</em>' attribute.
	 * @see #isSetExtension()
	 * @see #unsetExtension()
	 * @see #setExtension(boolean)
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExportModel_Extension()
	 * @model default="false" unsettable="true"
	 * @generated
	 */
	boolean isExtension();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#isExtension <em>Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extension</em>' attribute.
	 * @see #isSetExtension()
	 * @see #unsetExtension()
	 * @see #isExtension()
	 * @generated
	 */
	void setExtension(boolean value);

	/**
	 * Unsets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#isExtension <em>Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetExtension()
	 * @see #isExtension()
	 * @see #setExtension(boolean)
	 * @generated
	 */
	void unsetExtension();

	/**
	 * Returns whether the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#isExtension <em>Extension</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Extension</em>' attribute is set.
	 * @see #unsetExtension()
	 * @see #isExtension()
	 * @see #setExtension(boolean)
	 * @generated
	 */
	boolean isSetExtension();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExportModel_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Target Grammar</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Grammar</em>' reference.
	 * @see #setTargetGrammar(Grammar)
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExportModel_TargetGrammar()
	 * @model
	 * @generated
	 */
	Grammar getTargetGrammar();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#getTargetGrammar <em>Target Grammar</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Grammar</em>' reference.
	 * @see #getTargetGrammar()
	 * @generated
	 */
	void setTargetGrammar(Grammar value);

	/**
	 * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
	 * The list contents are of type {@link com.avaloq.tools.ddk.xtext.export.export.Import}.
	 * <!-- begin-user-doc -->
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
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exports</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportPackage#getExportModel_Exports()
	 * @model containment="true"
	 * @generated
	 */
	EList<Export> getExports();

} // ExportModel
