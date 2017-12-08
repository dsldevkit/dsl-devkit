/**
 */
package com.avaloq.tools.ddk.check.check;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Severity Range</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.check.check.SeverityRange#getMinSeverity <em>Min Severity</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.SeverityRange#getMaxSeverity <em>Max Severity</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getSeverityRange()
 * @model
 * @generated
 */
public interface SeverityRange extends EObject
{
	/**
	 * Returns the value of the '<em><b>Min Severity</b></em>' attribute.
	 * The literals are from the enumeration {@link com.avaloq.tools.ddk.check.check.SeverityKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Severity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Severity</em>' attribute.
	 * @see com.avaloq.tools.ddk.check.check.SeverityKind
	 * @see #setMinSeverity(SeverityKind)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getSeverityRange_MinSeverity()
	 * @model
	 * @generated
	 */
	SeverityKind getMinSeverity();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.SeverityRange#getMinSeverity <em>Min Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Severity</em>' attribute.
	 * @see com.avaloq.tools.ddk.check.check.SeverityKind
	 * @see #getMinSeverity()
	 * @generated
	 */
	void setMinSeverity(SeverityKind value);

	/**
	 * Returns the value of the '<em><b>Max Severity</b></em>' attribute.
	 * The literals are from the enumeration {@link com.avaloq.tools.ddk.check.check.SeverityKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Severity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Severity</em>' attribute.
	 * @see com.avaloq.tools.ddk.check.check.SeverityKind
	 * @see #setMaxSeverity(SeverityKind)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getSeverityRange_MaxSeverity()
	 * @model
	 * @generated
	 */
	SeverityKind getMaxSeverity();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.SeverityRange#getMaxSeverity <em>Max Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Severity</em>' attribute.
	 * @see com.avaloq.tools.ddk.check.check.SeverityKind
	 * @see #getMaxSeverity()
	 * @generated
	 */
	void setMaxSeverity(SeverityKind value);

} // SeverityRange
