/**
 */
package com.avaloq.tools.ddk.check.check;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.check.check.Context#getContextVariable <em>Context Variable</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.Context#getConstraint <em>Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getContext()
 * @model
 * @generated
 */
public interface Context extends Documented
{
	/**
	 * Returns the value of the '<em><b>Context Variable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context Variable</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context Variable</em>' containment reference.
	 * @see #setContextVariable(ContextVariable)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getContext_ContextVariable()
	 * @model containment="true"
	 * @generated
	 */
	ContextVariable getContextVariable();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.Context#getContextVariable <em>Context Variable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context Variable</em>' containment reference.
	 * @see #getContextVariable()
	 * @generated
	 */
	void setContextVariable(ContextVariable value);

	/**
	 * Returns the value of the '<em><b>Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraint</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraint</em>' containment reference.
	 * @see #setConstraint(XExpression)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getContext_Constraint()
	 * @model containment="true"
	 * @generated
	 */
	XExpression getConstraint();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.Context#getConstraint <em>Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraint</em>' containment reference.
	 * @see #getConstraint()
	 * @generated
	 */
	void setConstraint(XExpression value);

} // Context
