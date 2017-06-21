/**
 */
package com.avaloq.tools.ddk.checkcfg.checkcfg;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage
 * @generated
 */
public interface CheckcfgFactory extends EFactory
{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CheckcfgFactory eINSTANCE = com.avaloq.tools.ddk.checkcfg.checkcfg.impl.CheckcfgFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Check Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Check Configuration</em>'.
	 * @generated
	 */
	CheckConfiguration createCheckConfiguration();

	/**
	 * Returns a new object of class '<em>Configured Language Validator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Configured Language Validator</em>'.
	 * @generated
	 */
	ConfiguredLanguageValidator createConfiguredLanguageValidator();

	/**
	 * Returns a new object of class '<em>Configured Catalog</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Configured Catalog</em>'.
	 * @generated
	 */
	ConfiguredCatalog createConfiguredCatalog();

	/**
	 * Returns a new object of class '<em>Configured Check</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Configured Check</em>'.
	 * @generated
	 */
	ConfiguredCheck createConfiguredCheck();

	/**
	 * Returns a new object of class '<em>Configured Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Configured Parameter</em>'.
	 * @generated
	 */
	ConfiguredParameter createConfiguredParameter();

	/**
	 * Returns a new object of class '<em>Configurable Section</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Configurable Section</em>'.
	 * @generated
	 */
	ConfigurableSection createConfigurableSection();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	CheckcfgPackage getCheckcfgPackage();

} //CheckcfgFactory
