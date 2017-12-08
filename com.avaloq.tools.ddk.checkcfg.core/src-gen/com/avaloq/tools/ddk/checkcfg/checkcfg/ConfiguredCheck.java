/**
 */
package com.avaloq.tools.ddk.checkcfg.checkcfg;

import com.avaloq.tools.ddk.check.check.Check;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configured Check</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck#getSeverity <em>Severity</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck#getCheck <em>Check</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage#getConfiguredCheck()
 * @model
 * @generated
 */
public interface ConfiguredCheck extends ConfigurableSection
{
	/**
	 * Returns the value of the '<em><b>Severity</b></em>' attribute.
	 * The literals are from the enumeration {@link com.avaloq.tools.ddk.checkcfg.checkcfg.SeverityKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Severity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Severity</em>' attribute.
	 * @see com.avaloq.tools.ddk.checkcfg.checkcfg.SeverityKind
	 * @see #setSeverity(SeverityKind)
	 * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage#getConfiguredCheck_Severity()
	 * @model
	 * @generated
	 */
	SeverityKind getSeverity();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck#getSeverity <em>Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Severity</em>' attribute.
	 * @see com.avaloq.tools.ddk.checkcfg.checkcfg.SeverityKind
	 * @see #getSeverity()
	 * @generated
	 */
	void setSeverity(SeverityKind value);

	/**
	 * Returns the value of the '<em><b>Check</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Check</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Check</em>' reference.
	 * @see #setCheck(Check)
	 * @see com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage#getConfiguredCheck_Check()
	 * @model
	 * @generated
	 */
	Check getCheck();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck#getCheck <em>Check</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Check</em>' reference.
	 * @see #getCheck()
	 * @generated
	 */
	void setCheck(Check value);

} // ConfiguredCheck
