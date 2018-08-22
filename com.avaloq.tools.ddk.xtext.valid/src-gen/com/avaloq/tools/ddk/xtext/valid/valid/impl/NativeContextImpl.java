/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid.impl;

import com.avaloq.tools.ddk.xtext.valid.valid.NativeContext;
import com.avaloq.tools.ddk.xtext.valid.valid.QuickFix;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Native Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.NativeContextImpl#isNamed <em>Named</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.NativeContextImpl#getGivenName <em>Given Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.NativeContextImpl#getMarkerType <em>Marker Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.NativeContextImpl#getMarkerFeature <em>Marker Feature</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.NativeContextImpl#getQuickFixes <em>Quick Fixes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NativeContextImpl extends ContextImpl implements NativeContext
{
  /**
   * The default value of the '{@link #isNamed() <em>Named</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNamed()
   * @generated
   * @ordered
   */
  protected static final boolean NAMED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isNamed() <em>Named</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNamed()
   * @generated
   * @ordered
   */
  protected boolean named = NAMED_EDEFAULT;

  /**
   * The default value of the '{@link #getGivenName() <em>Given Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGivenName()
   * @generated
   * @ordered
   */
  protected static final String GIVEN_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getGivenName() <em>Given Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGivenName()
   * @generated
   * @ordered
   */
  protected String givenName = GIVEN_NAME_EDEFAULT;

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
   * The cached value of the '{@link #getQuickFixes() <em>Quick Fixes</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQuickFixes()
   * @generated
   * @ordered
   */
  protected EList<QuickFix> quickFixes;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NativeContextImpl()
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
    return ValidPackage.Literals.NATIVE_CONTEXT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isNamed()
  {
    return named;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNamed(boolean newNamed)
  {
    boolean oldNamed = named;
    named = newNamed;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.NATIVE_CONTEXT__NAMED, oldNamed, named));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getGivenName()
  {
    return givenName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGivenName(String newGivenName)
  {
    String oldGivenName = givenName;
    givenName = newGivenName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.NATIVE_CONTEXT__GIVEN_NAME, oldGivenName, givenName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMarkerType()
  {
    if (markerType != null && markerType.eIsProxy())
    {
      InternalEObject oldMarkerType = (InternalEObject)markerType;
      markerType = (EClass)eResolveProxy(oldMarkerType);
      if (markerType != oldMarkerType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ValidPackage.NATIVE_CONTEXT__MARKER_TYPE, oldMarkerType, markerType));
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
  public void setMarkerType(EClass newMarkerType)
  {
    EClass oldMarkerType = markerType;
    markerType = newMarkerType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.NATIVE_CONTEXT__MARKER_TYPE, oldMarkerType, markerType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EStructuralFeature getMarkerFeature()
  {
    if (markerFeature != null && markerFeature.eIsProxy())
    {
      InternalEObject oldMarkerFeature = (InternalEObject)markerFeature;
      markerFeature = (EStructuralFeature)eResolveProxy(oldMarkerFeature);
      if (markerFeature != oldMarkerFeature)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ValidPackage.NATIVE_CONTEXT__MARKER_FEATURE, oldMarkerFeature, markerFeature));
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
  public void setMarkerFeature(EStructuralFeature newMarkerFeature)
  {
    EStructuralFeature oldMarkerFeature = markerFeature;
    markerFeature = newMarkerFeature;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.NATIVE_CONTEXT__MARKER_FEATURE, oldMarkerFeature, markerFeature));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<QuickFix> getQuickFixes()
  {
    if (quickFixes == null)
    {
      quickFixes = new EObjectContainmentEList<QuickFix>(QuickFix.class, this, ValidPackage.NATIVE_CONTEXT__QUICK_FIXES);
    }
    return quickFixes;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ValidPackage.NATIVE_CONTEXT__QUICK_FIXES:
        return ((InternalEList<?>)getQuickFixes()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case ValidPackage.NATIVE_CONTEXT__NAMED:
        return isNamed();
      case ValidPackage.NATIVE_CONTEXT__GIVEN_NAME:
        return getGivenName();
      case ValidPackage.NATIVE_CONTEXT__MARKER_TYPE:
        if (resolve) return getMarkerType();
        return basicGetMarkerType();
      case ValidPackage.NATIVE_CONTEXT__MARKER_FEATURE:
        if (resolve) return getMarkerFeature();
        return basicGetMarkerFeature();
      case ValidPackage.NATIVE_CONTEXT__QUICK_FIXES:
        return getQuickFixes();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ValidPackage.NATIVE_CONTEXT__NAMED:
        setNamed((Boolean)newValue);
        return;
      case ValidPackage.NATIVE_CONTEXT__GIVEN_NAME:
        setGivenName((String)newValue);
        return;
      case ValidPackage.NATIVE_CONTEXT__MARKER_TYPE:
        setMarkerType((EClass)newValue);
        return;
      case ValidPackage.NATIVE_CONTEXT__MARKER_FEATURE:
        setMarkerFeature((EStructuralFeature)newValue);
        return;
      case ValidPackage.NATIVE_CONTEXT__QUICK_FIXES:
        getQuickFixes().clear();
        getQuickFixes().addAll((Collection<? extends QuickFix>)newValue);
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
      case ValidPackage.NATIVE_CONTEXT__NAMED:
        setNamed(NAMED_EDEFAULT);
        return;
      case ValidPackage.NATIVE_CONTEXT__GIVEN_NAME:
        setGivenName(GIVEN_NAME_EDEFAULT);
        return;
      case ValidPackage.NATIVE_CONTEXT__MARKER_TYPE:
        setMarkerType((EClass)null);
        return;
      case ValidPackage.NATIVE_CONTEXT__MARKER_FEATURE:
        setMarkerFeature((EStructuralFeature)null);
        return;
      case ValidPackage.NATIVE_CONTEXT__QUICK_FIXES:
        getQuickFixes().clear();
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
      case ValidPackage.NATIVE_CONTEXT__NAMED:
        return named != NAMED_EDEFAULT;
      case ValidPackage.NATIVE_CONTEXT__GIVEN_NAME:
        return GIVEN_NAME_EDEFAULT == null ? givenName != null : !GIVEN_NAME_EDEFAULT.equals(givenName);
      case ValidPackage.NATIVE_CONTEXT__MARKER_TYPE:
        return markerType != null;
      case ValidPackage.NATIVE_CONTEXT__MARKER_FEATURE:
        return markerFeature != null;
      case ValidPackage.NATIVE_CONTEXT__QUICK_FIXES:
        return quickFixes != null && !quickFixes.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (named: ");
    result.append(named);
    result.append(", givenName: ");
    result.append(givenName);
    result.append(')');
    return result.toString();
  }

} //NativeContextImpl
