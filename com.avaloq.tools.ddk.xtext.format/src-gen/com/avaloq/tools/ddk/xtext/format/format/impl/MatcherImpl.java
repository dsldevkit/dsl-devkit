/**
 */
package com.avaloq.tools.ddk.xtext.format.format.impl;

import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;
import com.avaloq.tools.ddk.xtext.format.format.Locator;
import com.avaloq.tools.ddk.xtext.format.format.Matcher;
import com.avaloq.tools.ddk.xtext.format.format.MatcherType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Matcher</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.MatcherImpl#getLocator <em>Locator</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.MatcherImpl#getType <em>Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.MatcherImpl#getCondition <em>Condition</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MatcherImpl extends MinimalEObjectImpl.Container implements Matcher
{
  /**
   * The cached value of the '{@link #getLocator() <em>Locator</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLocator()
   * @generated
   * @ordered
   */
  protected Locator locator;

  /**
   * The default value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected static final MatcherType TYPE_EDEFAULT = MatcherType.BEFORE;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected MatcherType type = TYPE_EDEFAULT;

  /**
   * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCondition()
   * @generated
   * @ordered
   */
  protected XExpression condition;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MatcherImpl()
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
    return FormatPackage.Literals.MATCHER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Locator getLocator()
  {
    return locator;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLocator(Locator newLocator, NotificationChain msgs)
  {
    Locator oldLocator = locator;
    locator = newLocator;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormatPackage.MATCHER__LOCATOR, oldLocator, newLocator);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLocator(Locator newLocator)
  {
    if (newLocator != locator)
    {
      NotificationChain msgs = null;
      if (locator != null)
        msgs = ((InternalEObject)locator).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormatPackage.MATCHER__LOCATOR, null, msgs);
      if (newLocator != null)
        msgs = ((InternalEObject)newLocator).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormatPackage.MATCHER__LOCATOR, null, msgs);
      msgs = basicSetLocator(newLocator, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.MATCHER__LOCATOR, newLocator, newLocator));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MatcherType getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(MatcherType newType)
  {
    MatcherType oldType = type;
    type = newType == null ? TYPE_EDEFAULT : newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.MATCHER__TYPE, oldType, type));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XExpression getCondition()
  {
    return condition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCondition(XExpression newCondition, NotificationChain msgs)
  {
    XExpression oldCondition = condition;
    condition = newCondition;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormatPackage.MATCHER__CONDITION, oldCondition, newCondition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCondition(XExpression newCondition)
  {
    if (newCondition != condition)
    {
      NotificationChain msgs = null;
      if (condition != null)
        msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormatPackage.MATCHER__CONDITION, null, msgs);
      if (newCondition != null)
        msgs = ((InternalEObject)newCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormatPackage.MATCHER__CONDITION, null, msgs);
      msgs = basicSetCondition(newCondition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.MATCHER__CONDITION, newCondition, newCondition));
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
      case FormatPackage.MATCHER__LOCATOR:
        return basicSetLocator(null, msgs);
      case FormatPackage.MATCHER__CONDITION:
        return basicSetCondition(null, msgs);
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
      case FormatPackage.MATCHER__LOCATOR:
        return getLocator();
      case FormatPackage.MATCHER__TYPE:
        return getType();
      case FormatPackage.MATCHER__CONDITION:
        return getCondition();
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
      case FormatPackage.MATCHER__LOCATOR:
        setLocator((Locator)newValue);
        return;
      case FormatPackage.MATCHER__TYPE:
        setType((MatcherType)newValue);
        return;
      case FormatPackage.MATCHER__CONDITION:
        setCondition((XExpression)newValue);
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
      case FormatPackage.MATCHER__LOCATOR:
        setLocator((Locator)null);
        return;
      case FormatPackage.MATCHER__TYPE:
        setType(TYPE_EDEFAULT);
        return;
      case FormatPackage.MATCHER__CONDITION:
        setCondition((XExpression)null);
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
      case FormatPackage.MATCHER__LOCATOR:
        return locator != null;
      case FormatPackage.MATCHER__TYPE:
        return type != TYPE_EDEFAULT;
      case FormatPackage.MATCHER__CONDITION:
        return condition != null;
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
    result.append(" (type: ");
    result.append(type);
    result.append(')');
    return result.toString();
  }

} //MatcherImpl
