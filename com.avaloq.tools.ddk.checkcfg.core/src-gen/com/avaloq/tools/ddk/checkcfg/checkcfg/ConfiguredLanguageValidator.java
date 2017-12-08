/**
 */
package com.avaloq.tools.ddk.checkcfg.checkcfg;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configured Language Validator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredLanguageValidator#getLanguage <em>Language</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredLanguageValidator#getCatalogConfigurations <em>Catalog Configurations</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage#getConfiguredLanguageValidator()
 * @model
 * @generated
 */
public interface ConfiguredLanguageValidator extends ConfigurableSection
{
	/**
	 * Returns the value of the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Language</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Language</em>' attribute.
	 * @see #setLanguage(String)
	 * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage#getConfiguredLanguageValidator_Language()
	 * @model
	 * @generated
	 */
	String getLanguage();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredLanguageValidator#getLanguage <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Language</em>' attribute.
	 * @see #getLanguage()
	 * @generated
	 */
	void setLanguage(String value);

	/**
	 * Returns the value of the '<em><b>Catalog Configurations</b></em>' containment reference list.
	 * The list contents are of type {@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Catalog Configurations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Catalog Configurations</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage#getConfiguredLanguageValidator_CatalogConfigurations()
	 * @model containment="true"
	 * @generated
	 */
	EList<ConfiguredCatalog> getCatalogConfigurations();

} // ConfiguredLanguageValidator
