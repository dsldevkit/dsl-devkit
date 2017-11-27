/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#isOptional <em>Optional</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#getCheckKind <em>Check Kind</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#getSeverity <em>Severity</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#getLabel <em>Label</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#getDescription <em>Description</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#getMessage <em>Message</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getRule()
 * @model
 * @generated
 */
public interface Rule extends EObject
{
  /**
   * Returns the value of the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Optional</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Optional</em>' attribute.
   * @see #setOptional(boolean)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getRule_Optional()
   * @model
   * @generated
   */
  boolean isOptional();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#isOptional <em>Optional</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Optional</em>' attribute.
   * @see #isOptional()
   * @generated
   */
  void setOptional(boolean value);

  /**
   * Returns the value of the '<em><b>Check Kind</b></em>' attribute.
   * The literals are from the enumeration {@link com.avaloq.tools.ddk.xtext.valid.valid.CheckKind}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Check Kind</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Check Kind</em>' attribute.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.CheckKind
   * @see #setCheckKind(CheckKind)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getRule_CheckKind()
   * @model
   * @generated
   */
  CheckKind getCheckKind();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#getCheckKind <em>Check Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Check Kind</em>' attribute.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.CheckKind
   * @see #getCheckKind()
   * @generated
   */
  void setCheckKind(CheckKind value);

  /**
   * Returns the value of the '<em><b>Severity</b></em>' attribute.
   * The literals are from the enumeration {@link com.avaloq.tools.ddk.xtext.valid.valid.SeverityKind}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Severity</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Severity</em>' attribute.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.SeverityKind
   * @see #setSeverity(SeverityKind)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getRule_Severity()
   * @model
   * @generated
   */
  SeverityKind getSeverity();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#getSeverity <em>Severity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Severity</em>' attribute.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.SeverityKind
   * @see #getSeverity()
   * @generated
   */
  void setSeverity(SeverityKind value);

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
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getRule_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

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
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getRule_Label()
   * @model
   * @generated
   */
  String getLabel();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#getLabel <em>Label</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Label</em>' attribute.
   * @see #getLabel()
   * @generated
   */
  void setLabel(String value);

  /**
   * Returns the value of the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Description</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Description</em>' attribute.
   * @see #setDescription(String)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getRule_Description()
   * @model
   * @generated
   */
  String getDescription();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#getDescription <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Description</em>' attribute.
   * @see #getDescription()
   * @generated
   */
  void setDescription(String value);

  /**
   * Returns the value of the '<em><b>Message</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Message</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Message</em>' attribute.
   * @see #setMessage(String)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getRule_Message()
   * @model
   * @generated
   */
  String getMessage();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.Rule#getMessage <em>Message</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Message</em>' attribute.
   * @see #getMessage()
   * @generated
   */
  void setMessage(String value);

} // Rule
