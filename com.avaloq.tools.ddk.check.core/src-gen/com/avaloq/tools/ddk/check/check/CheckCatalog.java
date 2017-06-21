/**
 */
package com.avaloq.tools.ddk.check.check;

import org.eclipse.emf.common.util.EList;

import org.eclipse.xtext.Grammar;

import org.eclipse.xtext.xtype.XImportSection;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Catalog</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getPackageName <em>Package Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getImports <em>Imports</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.CheckCatalog#isFinal <em>Final</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getGrammar <em>Grammar</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getIncludedCatalogs <em>Included Catalogs</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getCategories <em>Categories</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getImplementations <em>Implementations</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getChecks <em>Checks</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getMembers <em>Members</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheckCatalog()
 * @model
 * @generated
 */
public interface CheckCatalog extends Documented
{
	/**
	 * Returns the value of the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package Name</em>' attribute.
	 * @see #setPackageName(String)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheckCatalog_PackageName()
	 * @model
	 * @generated
	 */
	String getPackageName();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getPackageName <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Name</em>' attribute.
	 * @see #getPackageName()
	 * @generated
	 */
	void setPackageName(String value);

	/**
	 * Returns the value of the '<em><b>Imports</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imports</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imports</em>' containment reference.
	 * @see #setImports(XImportSection)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheckCatalog_Imports()
	 * @model containment="true"
	 * @generated
	 */
	XImportSection getImports();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getImports <em>Imports</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Imports</em>' containment reference.
	 * @see #getImports()
	 * @generated
	 */
	void setImports(XImportSection value);

	/**
	 * Returns the value of the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Final</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Final</em>' attribute.
	 * @see #setFinal(boolean)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheckCatalog_Final()
	 * @model
	 * @generated
	 */
	boolean isFinal();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.CheckCatalog#isFinal <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Final</em>' attribute.
	 * @see #isFinal()
	 * @generated
	 */
	void setFinal(boolean value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheckCatalog_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Grammar</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grammar</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grammar</em>' reference.
	 * @see #setGrammar(Grammar)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheckCatalog_Grammar()
	 * @model
	 * @generated
	 */
	Grammar getGrammar();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getGrammar <em>Grammar</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grammar</em>' reference.
	 * @see #getGrammar()
	 * @generated
	 */
	void setGrammar(Grammar value);

	/**
	 * Returns the value of the '<em><b>Included Catalogs</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Included Catalogs</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Included Catalogs</em>' reference.
	 * @see #setIncludedCatalogs(CheckCatalog)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheckCatalog_IncludedCatalogs()
	 * @model
	 * @generated
	 */
	CheckCatalog getIncludedCatalogs();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getIncludedCatalogs <em>Included Catalogs</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Included Catalogs</em>' reference.
	 * @see #getIncludedCatalogs()
	 * @generated
	 */
	void setIncludedCatalogs(CheckCatalog value);

	/**
	 * Returns the value of the '<em><b>Categories</b></em>' containment reference list.
	 * The list contents are of type {@link com.avaloq.tools.ddk.check.check.Category}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Categories</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Categories</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheckCatalog_Categories()
	 * @model containment="true"
	 * @generated
	 */
	EList<Category> getCategories();

	/**
	 * Returns the value of the '<em><b>Implementations</b></em>' containment reference list.
	 * The list contents are of type {@link com.avaloq.tools.ddk.check.check.Implementation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementations</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheckCatalog_Implementations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Implementation> getImplementations();

	/**
	 * Returns the value of the '<em><b>Checks</b></em>' containment reference list.
	 * The list contents are of type {@link com.avaloq.tools.ddk.check.check.Check}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Checks</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Checks</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheckCatalog_Checks()
	 * @model containment="true"
	 * @generated
	 */
	EList<Check> getChecks();

	/**
	 * Returns the value of the '<em><b>Members</b></em>' containment reference list.
	 * The list contents are of type {@link com.avaloq.tools.ddk.check.check.Member}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Members</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Members</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheckCatalog_Members()
	 * @model containment="true"
	 * @generated
	 */
	EList<Member> getMembers();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<Check> getAllChecks();

} // CheckCatalog
