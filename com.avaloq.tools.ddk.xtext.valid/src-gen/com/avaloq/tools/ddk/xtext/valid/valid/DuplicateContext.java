/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Duplicate Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.DuplicateContext#getMarkerType <em>Marker Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.DuplicateContext#getMarkerFeature <em>Marker Feature</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getDuplicateContext()
 * @model
 * @generated
 */
public interface DuplicateContext extends Context
{
  /**
   * Returns the value of the '<em><b>Marker Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Marker Type</em>' reference.
   * @see #setMarkerType(EClass)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getDuplicateContext_MarkerType()
   * @model
   * @generated
   */
  EClass getMarkerType();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.DuplicateContext#getMarkerType <em>Marker Type</em>}' reference.
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
   * <!-- end-user-doc -->
   * @return the value of the '<em>Marker Feature</em>' reference.
   * @see #setMarkerFeature(EStructuralFeature)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getDuplicateContext_MarkerFeature()
   * @model
   * @generated
   */
  EStructuralFeature getMarkerFeature();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.DuplicateContext#getMarkerFeature <em>Marker Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Marker Feature</em>' reference.
   * @see #getMarkerFeature()
   * @generated
   */
  void setMarkerFeature(EStructuralFeature value);

} // DuplicateContext
