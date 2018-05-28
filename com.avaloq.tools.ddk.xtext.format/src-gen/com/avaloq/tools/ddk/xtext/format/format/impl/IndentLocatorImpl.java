/**
 */
package com.avaloq.tools.ddk.xtext.format.format.impl;

import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;
import com.avaloq.tools.ddk.xtext.format.format.IndentLocator;
import com.avaloq.tools.ddk.xtext.format.format.IntValue;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Indent Locator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.IndentLocatorImpl#isIncrement <em>Increment</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.IndentLocatorImpl#getValue <em>Value</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.IndentLocatorImpl#getParameter <em>Parameter</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IndentLocatorImpl extends LocatorImpl implements IndentLocator
{
  /**
   * The default value of the '{@link #isIncrement() <em>Increment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isIncrement()
   * @generated
   * @ordered
   */
  protected static final boolean INCREMENT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isIncrement() <em>Increment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isIncrement()
   * @generated
   * @ordered
   */
  protected boolean increment = INCREMENT_EDEFAULT;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected IntValue value;

  /**
   * The cached value of the '{@link #getParameter() <em>Parameter</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameter()
   * @generated
   * @ordered
   */
  protected XExpression parameter;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IndentLocatorImpl()
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
    return FormatPackage.Literals.INDENT_LOCATOR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isIncrement()
  {
    return increment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIncrement(boolean newIncrement)
  {
    boolean oldIncrement = increment;
    increment = newIncrement;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.INDENT_LOCATOR__INCREMENT, oldIncrement, increment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntValue getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetValue(IntValue newValue, NotificationChain msgs)
  {
    IntValue oldValue = value;
    value = newValue;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormatPackage.INDENT_LOCATOR__VALUE, oldValue, newValue);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(IntValue newValue)
  {
    if (newValue != value)
    {
      NotificationChain msgs = null;
      if (value != null)
        msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormatPackage.INDENT_LOCATOR__VALUE, null, msgs);
      if (newValue != null)
        msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormatPackage.INDENT_LOCATOR__VALUE, null, msgs);
      msgs = basicSetValue(newValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.INDENT_LOCATOR__VALUE, newValue, newValue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XExpression getParameter()
  {
    return parameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParameter(XExpression newParameter, NotificationChain msgs)
  {
    XExpression oldParameter = parameter;
    parameter = newParameter;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormatPackage.INDENT_LOCATOR__PARAMETER, oldParameter, newParameter);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParameter(XExpression newParameter)
  {
    if (newParameter != parameter)
    {
      NotificationChain msgs = null;
      if (parameter != null)
        msgs = ((InternalEObject)parameter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormatPackage.INDENT_LOCATOR__PARAMETER, null, msgs);
      if (newParameter != null)
        msgs = ((InternalEObject)newParameter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormatPackage.INDENT_LOCATOR__PARAMETER, null, msgs);
      msgs = basicSetParameter(newParameter, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.INDENT_LOCATOR__PARAMETER, newParameter, newParameter));
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
      case FormatPackage.INDENT_LOCATOR__VALUE:
        return basicSetValue(null, msgs);
      case FormatPackage.INDENT_LOCATOR__PARAMETER:
        return basicSetParameter(null, msgs);
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
      case FormatPackage.INDENT_LOCATOR__INCREMENT:
        return isIncrement();
      case FormatPackage.INDENT_LOCATOR__VALUE:
        return getValue();
      case FormatPackage.INDENT_LOCATOR__PARAMETER:
        return getParameter();
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
      case FormatPackage.INDENT_LOCATOR__INCREMENT:
        setIncrement((Boolean)newValue);
        return;
      case FormatPackage.INDENT_LOCATOR__VALUE:
        setValue((IntValue)newValue);
        return;
      case FormatPackage.INDENT_LOCATOR__PARAMETER:
        setParameter((XExpression)newValue);
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
      case FormatPackage.INDENT_LOCATOR__INCREMENT:
        setIncrement(INCREMENT_EDEFAULT);
        return;
      case FormatPackage.INDENT_LOCATOR__VALUE:
        setValue((IntValue)null);
        return;
      case FormatPackage.INDENT_LOCATOR__PARAMETER:
        setParameter((XExpression)null);
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
      case FormatPackage.INDENT_LOCATOR__INCREMENT:
        return increment != INCREMENT_EDEFAULT;
      case FormatPackage.INDENT_LOCATOR__VALUE:
        return value != null;
      case FormatPackage.INDENT_LOCATOR__PARAMETER:
        return parameter != null;
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
    result.append(" (increment: ");
    result.append(increment);
    result.append(')');
    return result.toString();
  }

} //IndentLocatorImpl
