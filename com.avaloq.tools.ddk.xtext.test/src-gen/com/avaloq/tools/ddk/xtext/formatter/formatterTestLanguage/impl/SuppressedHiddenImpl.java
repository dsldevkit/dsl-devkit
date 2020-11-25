/**
 */
package com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.impl;

import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.SuppressedHidden;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.SuppressedHiddenSub;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Suppressed Hidden</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.impl.SuppressedHiddenImpl#getVals <em>Vals</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SuppressedHiddenImpl extends LineImpl implements SuppressedHidden
{
  /**
   * The cached value of the '{@link #getVals() <em>Vals</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVals()
   * @generated
   * @ordered
   */
  protected EList<SuppressedHiddenSub> vals;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SuppressedHiddenImpl()
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
    return FormatterTestLanguagePackage.Literals.SUPPRESSED_HIDDEN;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<SuppressedHiddenSub> getVals()
  {
    if (vals == null)
    {
      vals = new EObjectContainmentEList<SuppressedHiddenSub>(SuppressedHiddenSub.class, this, FormatterTestLanguagePackage.SUPPRESSED_HIDDEN__VALS);
    }
    return vals;
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
      case FormatterTestLanguagePackage.SUPPRESSED_HIDDEN__VALS:
        return ((InternalEList<?>)getVals()).basicRemove(otherEnd, msgs);
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
      case FormatterTestLanguagePackage.SUPPRESSED_HIDDEN__VALS:
        return getVals();
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
      case FormatterTestLanguagePackage.SUPPRESSED_HIDDEN__VALS:
        getVals().clear();
        getVals().addAll((Collection<? extends SuppressedHiddenSub>)newValue);
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
      case FormatterTestLanguagePackage.SUPPRESSED_HIDDEN__VALS:
        getVals().clear();
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
      case FormatterTestLanguagePackage.SUPPRESSED_HIDDEN__VALS:
        return vals != null && !vals.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //SuppressedHiddenImpl
