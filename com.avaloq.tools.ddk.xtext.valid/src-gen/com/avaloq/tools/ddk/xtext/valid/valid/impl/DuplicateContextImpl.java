/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid.impl;

import com.avaloq.tools.ddk.xtext.valid.valid.DuplicateContext;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Duplicate Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.DuplicateContextImpl#getMarkerType <em>Marker Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.DuplicateContextImpl#getMarkerFeature <em>Marker Feature</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DuplicateContextImpl extends ContextImpl implements DuplicateContext
{
  /**
   * The cached value of the '{@link #getMarkerType() <em>Marker Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMarkerType()
   * @generated
   * @ordered
   */
  protected EClass markerType;

  /**
   * The cached value of the '{@link #getMarkerFeature() <em>Marker Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMarkerFeature()
   * @generated
   * @ordered
   */
  protected EStructuralFeature markerFeature;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DuplicateContextImpl()
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
    return ValidPackage.Literals.DUPLICATE_CONTEXT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EClass getMarkerType()
  {
    if (markerType != null && markerType.eIsProxy())
    {
      InternalEObject oldMarkerType = (InternalEObject)markerType;
      markerType = (EClass)eResolveProxy(oldMarkerType);
      if (markerType != oldMarkerType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ValidPackage.DUPLICATE_CONTEXT__MARKER_TYPE, oldMarkerType, markerType));
      }
    }
    return markerType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass basicGetMarkerType()
  {
    return markerType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setMarkerType(EClass newMarkerType)
  {
    EClass oldMarkerType = markerType;
    markerType = newMarkerType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.DUPLICATE_CONTEXT__MARKER_TYPE, oldMarkerType, markerType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EStructuralFeature getMarkerFeature()
  {
    if (markerFeature != null && markerFeature.eIsProxy())
    {
      InternalEObject oldMarkerFeature = (InternalEObject)markerFeature;
      markerFeature = (EStructuralFeature)eResolveProxy(oldMarkerFeature);
      if (markerFeature != oldMarkerFeature)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ValidPackage.DUPLICATE_CONTEXT__MARKER_FEATURE, oldMarkerFeature, markerFeature));
      }
    }
    return markerFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EStructuralFeature basicGetMarkerFeature()
  {
    return markerFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setMarkerFeature(EStructuralFeature newMarkerFeature)
  {
    EStructuralFeature oldMarkerFeature = markerFeature;
    markerFeature = newMarkerFeature;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.DUPLICATE_CONTEXT__MARKER_FEATURE, oldMarkerFeature, markerFeature));
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
      case ValidPackage.DUPLICATE_CONTEXT__MARKER_TYPE:
        if (resolve) return getMarkerType();
        return basicGetMarkerType();
      case ValidPackage.DUPLICATE_CONTEXT__MARKER_FEATURE:
        if (resolve) return getMarkerFeature();
        return basicGetMarkerFeature();
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
      case ValidPackage.DUPLICATE_CONTEXT__MARKER_TYPE:
        setMarkerType((EClass)newValue);
        return;
      case ValidPackage.DUPLICATE_CONTEXT__MARKER_FEATURE:
        setMarkerFeature((EStructuralFeature)newValue);
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
      case ValidPackage.DUPLICATE_CONTEXT__MARKER_TYPE:
        setMarkerType((EClass)null);
        return;
      case ValidPackage.DUPLICATE_CONTEXT__MARKER_FEATURE:
        setMarkerFeature((EStructuralFeature)null);
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
      case ValidPackage.DUPLICATE_CONTEXT__MARKER_TYPE:
        return markerType != null;
      case ValidPackage.DUPLICATE_CONTEXT__MARKER_FEATURE:
        return markerFeature != null;
    }
    return super.eIsSet(featureID);
  }

} //DuplicateContextImpl
