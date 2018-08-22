/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Native Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#isNamed <em>Named</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#getGivenName <em>Given Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#getMarkerType <em>Marker Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#getMarkerFeature <em>Marker Feature</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#getQuickFixes <em>Quick Fixes</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getNativeContext()
 * @model
 * @generated
 */
public interface NativeContext extends Context
{
  /**
   * Returns the value of the '<em><b>Named</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Named</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Named</em>' attribute.
   * @see #setNamed(boolean)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getNativeContext_Named()
   * @model
   * @generated
   */
  boolean isNamed();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#isNamed <em>Named</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Named</em>' attribute.
   * @see #isNamed()
   * @generated
   */
  void setNamed(boolean value);

  /**
   * Returns the value of the '<em><b>Given Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Given Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Given Name</em>' attribute.
   * @see #setGivenName(String)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getNativeContext_GivenName()
   * @model
   * @generated
   */
  String getGivenName();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#getGivenName <em>Given Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Given Name</em>' attribute.
   * @see #getGivenName()
   * @generated
   */
  void setGivenName(String value);

  /**
   * Returns the value of the '<em><b>Marker Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Marker Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Marker Type</em>' reference.
   * @see #setMarkerType(EClass)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getNativeContext_MarkerType()
   * @model
   * @generated
   */
  EClass getMarkerType();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#getMarkerType <em>Marker Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Marker Type</em>' reference.
   * @see #getMarkerType()
   * @generated
   */
  void setMarkerType(EClass value);

  /**
   * Returns the value of the '<em><b>Marker Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Marker Feature</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Marker Feature</em>' reference.
   * @see #setMarkerFeature(EStructuralFeature)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getNativeContext_MarkerFeature()
   * @model
   * @generated
   */
  EStructuralFeature getMarkerFeature();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.NativeContext#getMarkerFeature <em>Marker Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Marker Feature</em>' reference.
   * @see #getMarkerFeature()
   * @generated
   */
  void setMarkerFeature(EStructuralFeature value);

  /**
   * Returns the value of the '<em><b>Quick Fixes</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.valid.valid.QuickFix}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Quick Fixes</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Quick Fixes</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getNativeContext_QuickFixes()
   * @model containment="true"
   * @generated
   */
  EList<QuickFix> getQuickFixes();

} // NativeContext
