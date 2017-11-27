/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid.impl;

import com.avaloq.tools.ddk.xtext.valid.valid.DuplicateContext;
import com.avaloq.tools.ddk.xtext.valid.valid.UniqueRule;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unique Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.UniqueRuleImpl#getContexts <em>Contexts</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UniqueRuleImpl extends PredefinedRuleImpl implements UniqueRule
{
  /**
   * The cached value of the '{@link #getContexts() <em>Contexts</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContexts()
   * @generated
   * @ordered
   */
  protected EList<DuplicateContext> contexts;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected UniqueRuleImpl()
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
    return ValidPackage.Literals.UNIQUE_RULE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<DuplicateContext> getContexts()
  {
    if (contexts == null)
    {
      contexts = new EObjectContainmentEList<DuplicateContext>(DuplicateContext.class, this, ValidPackage.UNIQUE_RULE__CONTEXTS);
    }
    return contexts;
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
      case ValidPackage.UNIQUE_RULE__CONTEXTS:
        return ((InternalEList<?>)getContexts()).basicRemove(otherEnd, msgs);
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
      case ValidPackage.UNIQUE_RULE__CONTEXTS:
        return getContexts();
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
      case ValidPackage.UNIQUE_RULE__CONTEXTS:
        getContexts().clear();
        getContexts().addAll((Collection<? extends DuplicateContext>)newValue);
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
      case ValidPackage.UNIQUE_RULE__CONTEXTS:
        getContexts().clear();
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
      case ValidPackage.UNIQUE_RULE__CONTEXTS:
        return contexts != null && !contexts.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //UniqueRuleImpl
