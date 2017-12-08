/**
 */
package com.avaloq.tools.ddk.checkcfg.checkcfg;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configurable Section</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfigurableSection#getParameterConfigurations <em>Parameter Configurations</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage#getConfigurableSection()
 * @model
 * @generated
 */
public interface ConfigurableSection extends EObject
{
	/**
	 * Returns the value of the '<em><b>Parameter Configurations</b></em>' containment reference list.
	 * The list contents are of type {@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Configurations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Configurations</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage#getConfigurableSection_ParameterConfigurations()
	 * @model containment="true"
	 * @generated
	 */
	EList<ConfiguredParameter> getParameterConfigurations();

} // ConfigurableSection
