/**
 */
package com.avaloq.tools.ddk.xtext.format.format.impl;

import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;
import com.avaloq.tools.ddk.xtext.format.format.Matcher;
import com.avaloq.tools.ddk.xtext.format.format.MatcherList;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Matcher List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.MatcherListImpl#getMatchers <em>Matchers</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MatcherListImpl extends MinimalEObjectImpl.Container implements MatcherList
{
  /**
   * The cached value of the '{@link #getMatchers() <em>Matchers</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMatchers()
   * @generated
   * @ordered
   */
  protected EList<Matcher> matchers;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MatcherListImpl()
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
    return FormatPackage.Literals.MATCHER_LIST;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<Matcher> getMatchers()
  {
    if (matchers == null)
    {
      matchers = new EObjectContainmentEList<Matcher>(Matcher.class, this, FormatPackage.MATCHER_LIST__MATCHERS);
    }
    return matchers;
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
      case FormatPackage.MATCHER_LIST__MATCHERS:
        return ((InternalEList<?>)getMatchers()).basicRemove(otherEnd, msgs);
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
      case FormatPackage.MATCHER_LIST__MATCHERS:
        return getMatchers();
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
      case FormatPackage.MATCHER_LIST__MATCHERS:
        getMatchers().clear();
        getMatchers().addAll((Collection<? extends Matcher>)newValue);
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
      case FormatPackage.MATCHER_LIST__MATCHERS:
        getMatchers().clear();
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
      case FormatPackage.MATCHER_LIST__MATCHERS:
        return matchers != null && !matchers.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //MatcherListImpl
