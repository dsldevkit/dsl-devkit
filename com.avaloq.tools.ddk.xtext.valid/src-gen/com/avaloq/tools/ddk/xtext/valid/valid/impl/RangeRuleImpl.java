/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid.impl;

import com.avaloq.tools.ddk.xtext.valid.valid.RangeRule;
import com.avaloq.tools.ddk.xtext.valid.valid.SimpleContext;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Range Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.RangeRuleImpl#getMin <em>Min</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.RangeRuleImpl#getMax <em>Max</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.impl.RangeRuleImpl#getContexts <em>Contexts</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RangeRuleImpl extends PredefinedRuleImpl implements RangeRule
{
  /**
   * The default value of the '{@link #getMin() <em>Min</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMin()
   * @generated
   * @ordered
   */
  protected static final int MIN_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getMin() <em>Min</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMin()
   * @generated
   * @ordered
   */
  protected int min = MIN_EDEFAULT;

  /**
   * The default value of the '{@link #getMax() <em>Max</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMax()
   * @generated
   * @ordered
   */
  protected static final int MAX_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getMax() <em>Max</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMax()
   * @generated
   * @ordered
   */
  protected int max = MAX_EDEFAULT;

  /**
   * The cached value of the '{@link #getContexts() <em>Contexts</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContexts()
   * @generated
   * @ordered
   */
  protected EList<SimpleContext> contexts;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RangeRuleImpl()
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
    return ValidPackage.Literals.RANGE_RULE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int getMin()
  {
    return min;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setMin(int newMin)
  {
    int oldMin = min;
    min = newMin;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.RANGE_RULE__MIN, oldMin, min));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int getMax()
  {
    return max;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setMax(int newMax)
  {
    int oldMax = max;
    max = newMax;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ValidPackage.RANGE_RULE__MAX, oldMax, max));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<SimpleContext> getContexts()
  {
    if (contexts == null)
    {
      contexts = new EObjectContainmentEList<SimpleContext>(SimpleContext.class, this, ValidPackage.RANGE_RULE__CONTEXTS);
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
      case ValidPackage.RANGE_RULE__CONTEXTS:
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
      case ValidPackage.RANGE_RULE__MIN:
        return getMin();
      case ValidPackage.RANGE_RULE__MAX:
        return getMax();
      case ValidPackage.RANGE_RULE__CONTEXTS:
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
      case ValidPackage.RANGE_RULE__MIN:
        setMin((Integer)newValue);
        return;
      case ValidPackage.RANGE_RULE__MAX:
        setMax((Integer)newValue);
        return;
      case ValidPackage.RANGE_RULE__CONTEXTS:
        getContexts().clear();
        getContexts().addAll((Collection<? extends SimpleContext>)newValue);
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
      case ValidPackage.RANGE_RULE__MIN:
        setMin(MIN_EDEFAULT);
        return;
      case ValidPackage.RANGE_RULE__MAX:
        setMax(MAX_EDEFAULT);
        return;
      case ValidPackage.RANGE_RULE__CONTEXTS:
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
      case ValidPackage.RANGE_RULE__MIN:
        return min != MIN_EDEFAULT;
      case ValidPackage.RANGE_RULE__MAX:
        return max != MAX_EDEFAULT;
      case ValidPackage.RANGE_RULE__CONTEXTS:
        return contexts != null && !contexts.isEmpty();
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
    result.append(" (min: ");
    result.append(min);
    result.append(", max: ");
    result.append(max);
    result.append(')');
    return result.toString();
  }

} //RangeRuleImpl
