/**
 */
package com.avaloq.tools.ddk.check.check;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XGuard Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.check.check.XGuardExpression#getGuard <em>Guard</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getXGuardExpression()
 * @model
 * @generated
 */
public interface XGuardExpression extends XExpression
{
	/**
	 * Returns the value of the '<em><b>Guard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Guard</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Guard</em>' containment reference.
	 * @see #setGuard(XExpression)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getXGuardExpression_Guard()
	 * @model containment="true"
	 * @generated
	 */
	XExpression getGuard();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.XGuardExpression#getGuard <em>Guard</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Guard</em>' containment reference.
	 * @see #getGuard()
	 * @generated
	 */
	void setGuard(XExpression value);

} // XGuardExpression
