/**
 */
package com.avaloq.tools.ddk.xtext.format.format;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Matcher</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.Matcher#getLocator <em>Locator</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.Matcher#getType <em>Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.Matcher#getCondition <em>Condition</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getMatcher()
 * @model
 * @generated
 */
public interface Matcher extends EObject
{
  /**
   * Returns the value of the '<em><b>Locator</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Locator</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Locator</em>' containment reference.
   * @see #setLocator(Locator)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getMatcher_Locator()
   * @model containment="true"
   * @generated
   */
  Locator getLocator();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.Matcher#getLocator <em>Locator</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Locator</em>' containment reference.
   * @see #getLocator()
   * @generated
   */
  void setLocator(Locator value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' attribute.
   * The literals are from the enumeration {@link com.avaloq.tools.ddk.xtext.format.format.MatcherType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' attribute.
   * @see com.avaloq.tools.ddk.xtext.format.format.MatcherType
   * @see #setType(MatcherType)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getMatcher_Type()
   * @model
   * @generated
   */
  MatcherType getType();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.Matcher#getType <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' attribute.
   * @see com.avaloq.tools.ddk.xtext.format.format.MatcherType
   * @see #getType()
   * @generated
   */
  void setType(MatcherType value);

  /**
   * Returns the value of the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Condition</em>' containment reference.
   * @see #setCondition(XExpression)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getMatcher_Condition()
   * @model containment="true"
   * @generated
   */
  XExpression getCondition();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.Matcher#getCondition <em>Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' containment reference.
   * @see #getCondition()
   * @generated
   */
  void setCondition(XExpression value);

} // Matcher
