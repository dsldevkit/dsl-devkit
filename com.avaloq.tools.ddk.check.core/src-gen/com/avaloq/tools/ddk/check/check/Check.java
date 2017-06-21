/**
 */
package com.avaloq.tools.ddk.check.check;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Check</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.check.check.Check#getSeverityRange <em>Severity Range</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.Check#isFinal <em>Final</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.Check#getKind <em>Kind</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.Check#getDefaultSeverity <em>Default Severity</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.Check#getId <em>Id</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.Check#getLabel <em>Label</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.Check#getFormalParameters <em>Formal Parameters</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.Check#getGivenMessage <em>Given Message</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.Check#getContexts <em>Contexts</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.Check#getMessage <em>Message</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheck()
 * @model
 * @generated
 */
public interface Check extends Documented, ImplicitlyNamed
{
	/**
	 * Returns the value of the '<em><b>Severity Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Severity Range</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Severity Range</em>' containment reference.
	 * @see #setSeverityRange(SeverityRange)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheck_SeverityRange()
	 * @model containment="true"
	 * @generated
	 */
	SeverityRange getSeverityRange();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.Check#getSeverityRange <em>Severity Range</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Severity Range</em>' containment reference.
	 * @see #getSeverityRange()
	 * @generated
	 */
	void setSeverityRange(SeverityRange value);

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
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheck_Final()
	 * @model
	 * @generated
	 */
	boolean isFinal();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.Check#isFinal <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Final</em>' attribute.
	 * @see #isFinal()
	 * @generated
	 */
	void setFinal(boolean value);

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link com.avaloq.tools.ddk.check.check.TriggerKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see com.avaloq.tools.ddk.check.check.TriggerKind
	 * @see #setKind(TriggerKind)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheck_Kind()
	 * @model
	 * @generated
	 */
	TriggerKind getKind();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.Check#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see com.avaloq.tools.ddk.check.check.TriggerKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(TriggerKind value);

	/**
	 * Returns the value of the '<em><b>Default Severity</b></em>' attribute.
	 * The literals are from the enumeration {@link com.avaloq.tools.ddk.check.check.SeverityKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Severity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Severity</em>' attribute.
	 * @see com.avaloq.tools.ddk.check.check.SeverityKind
	 * @see #setDefaultSeverity(SeverityKind)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheck_DefaultSeverity()
	 * @model
	 * @generated
	 */
	SeverityKind getDefaultSeverity();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.Check#getDefaultSeverity <em>Default Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Severity</em>' attribute.
	 * @see com.avaloq.tools.ddk.check.check.SeverityKind
	 * @see #getDefaultSeverity()
	 * @generated
	 */
	void setDefaultSeverity(SeverityKind value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheck_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.Check#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheck_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.Check#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Formal Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link com.avaloq.tools.ddk.check.check.FormalParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Formal Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Formal Parameters</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheck_FormalParameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<FormalParameter> getFormalParameters();

	/**
	 * Returns the value of the '<em><b>Given Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Given Message</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Given Message</em>' attribute.
	 * @see #setGivenMessage(String)
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheck_GivenMessage()
	 * @model
	 * @generated
	 */
	String getGivenMessage();

	/**
	 * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.Check#getGivenMessage <em>Given Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Given Message</em>' attribute.
	 * @see #getGivenMessage()
	 * @generated
	 */
	void setGivenMessage(String value);

	/**
	 * Returns the value of the '<em><b>Contexts</b></em>' containment reference list.
	 * The list contents are of type {@link com.avaloq.tools.ddk.check.check.Context}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contexts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contexts</em>' containment reference list.
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheck_Contexts()
	 * @model containment="true"
	 * @generated
	 */
	EList<Context> getContexts();

	/**
	 * Returns the value of the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message</em>' attribute.
	 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getCheck_Message()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getMessage();

} // Check
