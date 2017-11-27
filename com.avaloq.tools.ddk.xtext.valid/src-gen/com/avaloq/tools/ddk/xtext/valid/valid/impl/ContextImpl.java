/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid.impl;

import com.avaloq.tools.ddk.xtext.valid.valid.Context;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.ContextImpl#getContextType <em>Context Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.ContextImpl#getContextFeature <em>Context Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContextImpl extends MinimalEObjectImpl.Container implements Context
{
  /**
   * The cached value of the '{@link #getContextType() <em>Context Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContextType()
   * @generated
   * @ordered
   */
  protected EClass contextType;

  /**
   * The cached value of the '{@link #getContextFeature() <em>Context Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContextFeature()
   * @generated
   * @ordered
   */
  protected EStructuralFeature contextFeature;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ContextImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ValidPackage.Literals.CONTEXT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getContextType()
  {
    if (contextType != null && contextType.eIsProxy())
    {
      InternalEObject oldContextType = (InternalEObject)contextType;
      contextType = (EClass)eResolveProxy(oldContextType);
      if (contextType != oldContextType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ValidPackage.CONTEXT__CONTEXT_TYPE, oldContextType, contextType));
      }
    }
    return contextType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass basicGetContextType()
  {
    return contextType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContextType(EClass newContextType)
  {
    EClass oldContextType = contextType;
    contextType = newContextType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.CONTEXT__CONTEXT_TYPE, oldContextType, contextType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EStructuralFeature getContextFeature()
  {
    if (contextFeature != null && contextFeature.eIsProxy())
    {
      InternalEObject oldContextFeature = (InternalEObject)contextFeature;
      contextFeature = (EStructuralFeature)eResolveProxy(oldContextFeature);
      if (contextFeature != oldContextFeature)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ValidPackage.CONTEXT__CONTEXT_FEATURE, oldContextFeature, contextFeature));
      }
    }
    return contextFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EStructuralFeature basicGetContextFeature()
  {
    return contextFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContextFeature(EStructuralFeature newContextFeature)
  {
    EStructuralFeature oldContextFeature = contextFeature;
    contextFeature = newContextFeature;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.CONTEXT__CONTEXT_FEATURE, oldContextFeature, contextFeature));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ValidPackage.CONTEXT__CONTEXT_TYPE:
        if (resolve) return getContextType();
        return basicGetContextType();
      case ValidPackage.CONTEXT__CONTEXT_FEATURE:
        if (resolve) return getContextFeature();
        return basicGetContextFeature();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ValidPackage.CONTEXT__CONTEXT_TYPE:
        setContextType((EClass)newValue);
        return;
      case ValidPackage.CONTEXT__CONTEXT_FEATURE:
        setContextFeature((EStructuralFeature)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case ValidPackage.CONTEXT__CONTEXT_TYPE:
        setContextType((EClass)null);
        return;
      case ValidPackage.CONTEXT__CONTEXT_FEATURE:
        setContextFeature((EStructuralFeature)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ValidPackage.CONTEXT__CONTEXT_TYPE:
        return contextType != null;
      case ValidPackage.CONTEXT__CONTEXT_FEATURE:
        return contextFeature != null;
    }
    return super.eIsSet(featureID);
  }

} //ContextImpl
