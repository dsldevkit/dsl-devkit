/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.Context#getContextType <em>Context Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.Context#getContextFeature <em>Context Feature</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getContext()
 * @model
 * @generated
 */
public interface Context extends EObject
{
  /**
   * Returns the value of the '<em><b>Context Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Context Type</em>' reference.
   * @see #setContextType(EClass)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getContext_ContextType()
   * @model
   * @generated
   */
  EClass getContextType();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.Context#getContextType <em>Context Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Context Type</em>' reference.
   * @see #getContextType()
   * @generated
   */
  void setContextType(EClass value);

  /**
   * Returns the value of the '<em><b>Context Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Context Feature</em>' reference.
   * @see #setContextFeature(EStructuralFeature)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getContext_ContextFeature()
   * @model
   * @generated
   */
  EStructuralFeature getContextFeature();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.Context#getContextFeature <em>Context Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Context Feature</em>' reference.
   * @see #getContextFeature()
   * @generated
   */
  void setContextFeature(EStructuralFeature value);

} // Context
