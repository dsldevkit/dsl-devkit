/**
 */
package com.avaloq.tools.ddk.check.check;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Implementation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.check.check.Implementation#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.Implementation#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getImplementation()
 * @model
 * @generated
 */
public interface Implementation extends Documented
{
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
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getImplementation_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.Implementation#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context</em>' containment reference.
	 * @see #setContext(Context)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getImplementation_Context()
	 * @model containment="true"
	 * @generated
	 */
	Context getContext();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.Implementation#getContext <em>Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context</em>' containment reference.
	 * @see #getContext()
	 * @generated
	 */
	void setContext(Context value);

} // Implementation
