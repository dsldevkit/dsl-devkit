/**
 */
package com.avaloq.tools.ddk.checkcfg.checkcfg;

import com.avaloq.tools.ddk.check.check.CheckCatalog;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configured Catalog</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog#getCatalog <em>Catalog</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog#getCheckConfigurations <em>Check Configurations</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage#getConfiguredCatalog()
 * @model
 * @generated
 */
public interface ConfiguredCatalog extends EObject
{
	/**
	 * Returns the value of the '<em><b>Catalog</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Catalog</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Catalog</em>' reference.
	 * @see #setCatalog(CheckCatalog)
	 * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage#getConfiguredCatalog_Catalog()
	 * @model
	 * @generated
	 */
	CheckCatalog getCatalog();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog#getCatalog <em>Catalog</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Catalog</em>' reference.
	 * @see #getCatalog()
	 * @generated
	 */
	void setCatalog(CheckCatalog value);

	/**
	 * Returns the value of the '<em><b>Check Configurations</b></em>' containment reference list.
	 * The list contents are of type {@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Check Configurations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Check Configurations</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage#getConfiguredCatalog_CheckConfigurations()
	 * @model containment="true"
	 * @generated
	 */
	EList<ConfiguredCheck> getCheckConfigurations();

} // ConfiguredCatalog
