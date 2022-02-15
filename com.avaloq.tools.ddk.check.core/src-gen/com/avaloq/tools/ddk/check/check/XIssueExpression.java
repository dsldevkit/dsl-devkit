/**
 */
package com.avaloq.tools.ddk.check.check;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XIssue Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getCheck <em>Check</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getMarkerFeature <em>Marker Feature</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getMarkerObject <em>Marker Object</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getMarkerIndex <em>Marker Index</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getMessage <em>Message</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getMessageParameters <em>Message Parameters</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getIssueCode <em>Issue Code</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getIssueData <em>Issue Data</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getXIssueExpression()
 * @model
 * @generated
 */
public interface XIssueExpression extends XExpression
{
  /**
   * Returns the value of the '<em><b>Check</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Check</em>' reference.
   * @see #setCheck(Check)
   * @see com.avaloq.tools.ddk.check.check.CheckPackage#getXIssueExpression_Check()
   * @model
   * @generated
   */
  Check getCheck();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getCheck <em>Check</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Check</em>' reference.
   * @see #getCheck()
   * @generated
   */
  void setCheck(Check value);

  /**
   * Returns the value of the '<em><b>Marker Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Marker Feature</em>' reference.
   * @see #setMarkerFeature(EStructuralFeature)
   * @see com.avaloq.tools.ddk.check.check.CheckPackage#getXIssueExpression_MarkerFeature()
   * @model
   * @generated
   */
  EStructuralFeature getMarkerFeature();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getMarkerFeature <em>Marker Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Marker Feature</em>' reference.
   * @see #getMarkerFeature()
   * @generated
   */
  void setMarkerFeature(EStructuralFeature value);

  /**
   * Returns the value of the '<em><b>Marker Object</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Marker Object</em>' containment reference.
   * @see #setMarkerObject(XExpression)
   * @see com.avaloq.tools.ddk.check.check.CheckPackage#getXIssueExpression_MarkerObject()
   * @model containment="true"
   * @generated
   */
  XExpression getMarkerObject();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getMarkerObject <em>Marker Object</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Marker Object</em>' containment reference.
   * @see #getMarkerObject()
   * @generated
   */
  void setMarkerObject(XExpression value);

  /**
   * Returns the value of the '<em><b>Marker Index</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Marker Index</em>' containment reference.
   * @see #setMarkerIndex(XExpression)
   * @see com.avaloq.tools.ddk.check.check.CheckPackage#getXIssueExpression_MarkerIndex()
   * @model containment="true"
   * @generated
   */
  XExpression getMarkerIndex();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getMarkerIndex <em>Marker Index</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Marker Index</em>' containment reference.
   * @see #getMarkerIndex()
   * @generated
   */
  void setMarkerIndex(XExpression value);

  /**
   * Returns the value of the '<em><b>Message</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Message</em>' containment reference.
   * @see #setMessage(XExpression)
   * @see com.avaloq.tools.ddk.check.check.CheckPackage#getXIssueExpression_Message()
   * @model containment="true"
   * @generated
   */
  XExpression getMessage();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getMessage <em>Message</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Message</em>' containment reference.
   * @see #getMessage()
   * @generated
   */
  void setMessage(XExpression value);

  /**
   * Returns the value of the '<em><b>Message Parameters</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xtext.xbase.XExpression}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Message Parameters</em>' containment reference list.
   * @see com.avaloq.tools.ddk.check.check.CheckPackage#getXIssueExpression_MessageParameters()
   * @model containment="true"
   * @generated
   */
  EList<XExpression> getMessageParameters();

  /**
   * Returns the value of the '<em><b>Issue Code</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Issue Code</em>' attribute.
   * @see #setIssueCode(String)
   * @see com.avaloq.tools.ddk.check.check.CheckPackage#getXIssueExpression_IssueCode()
   * @model
   * @generated
   */
  String getIssueCode();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getIssueCode <em>Issue Code</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Issue Code</em>' attribute.
   * @see #getIssueCode()
   * @generated
   */
  void setIssueCode(String value);

  /**
   * Returns the value of the '<em><b>Issue Data</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xtext.xbase.XExpression}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Issue Data</em>' containment reference list.
   * @see com.avaloq.tools.ddk.check.check.CheckPackage#getXIssueExpression_IssueData()
   * @model containment="true"
   * @generated
   */
  EList<XExpression> getIssueData();

} // XIssueExpression
