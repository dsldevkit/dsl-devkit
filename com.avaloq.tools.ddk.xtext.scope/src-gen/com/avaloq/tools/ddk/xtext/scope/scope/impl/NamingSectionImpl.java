/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope.impl;

import com.avaloq.tools.ddk.xtext.scope.scope.Casing;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingSection;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Naming Section</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingSectionImpl#getCasing <em>Casing</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.NamingSectionImpl#getNamings <em>Namings</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NamingSectionImpl extends MinimalEObjectImpl.Container implements NamingSection
{
  /**
   * The default value of the '{@link #getCasing() <em>Casing</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCasing()
   * @generated
   * @ordered
   */
  protected static final Casing CASING_EDEFAULT = Casing.SENSITIVE;

  /**
   * The cached value of the '{@link #getCasing() <em>Casing</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCasing()
   * @generated
   * @ordered
   */
  protected Casing casing = CASING_EDEFAULT;

  /**
   * The cached value of the '{@link #getNamings() <em>Namings</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNamings()
   * @generated
   * @ordered
   */
  protected EList<NamingDefinition> namings;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NamingSectionImpl()
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
    return ScopePackage.Literals.NAMING_SECTION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Casing getCasing()
  {
    return casing;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCasing(Casing newCasing)
  {
    Casing oldCasing = casing;
    casing = newCasing == null ? CASING_EDEFAULT : newCasing;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.NAMING_SECTION__CASING, oldCasing, casing));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<NamingDefinition> getNamings()
  {
    if (namings == null)
    {
      namings = new EObjectContainmentEList<NamingDefinition>(NamingDefinition.class, this, ScopePackage.NAMING_SECTION__NAMINGS);
    }
    return namings;
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
      case ScopePackage.NAMING_SECTION__NAMINGS:
        return ((InternalEList<?>)getNamings()).basicRemove(otherEnd, msgs);
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
      case ScopePackage.NAMING_SECTION__CASING:
        return getCasing();
      case ScopePackage.NAMING_SECTION__NAMINGS:
        return getNamings();
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
      case ScopePackage.NAMING_SECTION__CASING:
        setCasing((Casing)newValue);
        return;
      case ScopePackage.NAMING_SECTION__NAMINGS:
        getNamings().clear();
        getNamings().addAll((Collection<? extends NamingDefinition>)newValue);
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
      case ScopePackage.NAMING_SECTION__CASING:
        setCasing(CASING_EDEFAULT);
        return;
      case ScopePackage.NAMING_SECTION__NAMINGS:
        getNamings().clear();
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
      case ScopePackage.NAMING_SECTION__CASING:
        return casing != CASING_EDEFAULT;
      case ScopePackage.NAMING_SECTION__NAMINGS:
        return namings != null && !namings.isEmpty();
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

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (casing: ");
    result.append(casing);
    result.append(')');
    return result.toString();
  }

} //NamingSectionImpl
