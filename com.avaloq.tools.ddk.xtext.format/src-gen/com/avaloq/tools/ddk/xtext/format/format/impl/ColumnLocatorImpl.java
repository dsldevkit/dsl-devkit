/**
 */
package com.avaloq.tools.ddk.xtext.format.format.impl;

import com.avaloq.tools.ddk.xtext.format.format.ColumnLocator;
import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;
import com.avaloq.tools.ddk.xtext.format.format.IntValue;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Column Locator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.ColumnLocatorImpl#isFixed <em>Fixed</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.ColumnLocatorImpl#getValue <em>Value</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.ColumnLocatorImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.ColumnLocatorImpl#isRelative <em>Relative</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.ColumnLocatorImpl#isNobreak <em>Nobreak</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ColumnLocatorImpl extends LocatorImpl implements ColumnLocator
{
  /**
   * The default value of the '{@link #isFixed() <em>Fixed</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isFixed()
   * @generated
   * @ordered
   */
  protected static final boolean FIXED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isFixed() <em>Fixed</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isFixed()
   * @generated
   * @ordered
   */
  protected boolean fixed = FIXED_EDEFAULT;

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
   * The default value of the '{@link #isRelative() <em>Relative</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isRelative()
   * @generated
   * @ordered
   */
  protected static final boolean RELATIVE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isRelative() <em>Relative</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isRelative()
   * @generated
   * @ordered
   */
  protected boolean relative = RELATIVE_EDEFAULT;

  /**
   * The default value of the '{@link #isNobreak() <em>Nobreak</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNobreak()
   * @generated
   * @ordered
   */
  protected static final boolean NOBREAK_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isNobreak() <em>Nobreak</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNobreak()
   * @generated
   * @ordered
   */
  protected boolean nobreak = NOBREAK_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ColumnLocatorImpl()
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
    return FormatPackage.Literals.COLUMN_LOCATOR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isFixed()
  {
    return fixed;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFixed(boolean newFixed)
  {
    boolean oldFixed = fixed;
    fixed = newFixed;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.COLUMN_LOCATOR__FIXED, oldFixed, fixed));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormatPackage.COLUMN_LOCATOR__VALUE, oldValue, newValue);
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
        msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormatPackage.COLUMN_LOCATOR__VALUE, null, msgs);
      if (newValue != null)
        msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormatPackage.COLUMN_LOCATOR__VALUE, null, msgs);
      msgs = basicSetValue(newValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.COLUMN_LOCATOR__VALUE, newValue, newValue));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormatPackage.COLUMN_LOCATOR__PARAMETER, oldParameter, newParameter);
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
        msgs = ((InternalEObject)parameter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormatPackage.COLUMN_LOCATOR__PARAMETER, null, msgs);
      if (newParameter != null)
        msgs = ((InternalEObject)newParameter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormatPackage.COLUMN_LOCATOR__PARAMETER, null, msgs);
      msgs = basicSetParameter(newParameter, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.COLUMN_LOCATOR__PARAMETER, newParameter, newParameter));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isRelative()
  {
    return relative;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRelative(boolean newRelative)
  {
    boolean oldRelative = relative;
    relative = newRelative;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.COLUMN_LOCATOR__RELATIVE, oldRelative, relative));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isNobreak()
  {
    return nobreak;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNobreak(boolean newNobreak)
  {
    boolean oldNobreak = nobreak;
    nobreak = newNobreak;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.COLUMN_LOCATOR__NOBREAK, oldNobreak, nobreak));
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
      case FormatPackage.COLUMN_LOCATOR__VALUE:
        return basicSetValue(null, msgs);
      case FormatPackage.COLUMN_LOCATOR__PARAMETER:
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
      case FormatPackage.COLUMN_LOCATOR__FIXED:
        return isFixed();
      case FormatPackage.COLUMN_LOCATOR__VALUE:
        return getValue();
      case FormatPackage.COLUMN_LOCATOR__PARAMETER:
        return getParameter();
      case FormatPackage.COLUMN_LOCATOR__RELATIVE:
        return isRelative();
      case FormatPackage.COLUMN_LOCATOR__NOBREAK:
        return isNobreak();
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
      case FormatPackage.COLUMN_LOCATOR__FIXED:
        setFixed((Boolean)newValue);
        return;
      case FormatPackage.COLUMN_LOCATOR__VALUE:
        setValue((IntValue)newValue);
        return;
      case FormatPackage.COLUMN_LOCATOR__PARAMETER:
        setParameter((XExpression)newValue);
        return;
      case FormatPackage.COLUMN_LOCATOR__RELATIVE:
        setRelative((Boolean)newValue);
        return;
      case FormatPackage.COLUMN_LOCATOR__NOBREAK:
        setNobreak((Boolean)newValue);
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
      case FormatPackage.COLUMN_LOCATOR__FIXED:
        setFixed(FIXED_EDEFAULT);
        return;
      case FormatPackage.COLUMN_LOCATOR__VALUE:
        setValue((IntValue)null);
        return;
      case FormatPackage.COLUMN_LOCATOR__PARAMETER:
        setParameter((XExpression)null);
        return;
      case FormatPackage.COLUMN_LOCATOR__RELATIVE:
        setRelative(RELATIVE_EDEFAULT);
        return;
      case FormatPackage.COLUMN_LOCATOR__NOBREAK:
        setNobreak(NOBREAK_EDEFAULT);
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
      case FormatPackage.COLUMN_LOCATOR__FIXED:
        return fixed != FIXED_EDEFAULT;
      case FormatPackage.COLUMN_LOCATOR__VALUE:
        return value != null;
      case FormatPackage.COLUMN_LOCATOR__PARAMETER:
        return parameter != null;
      case FormatPackage.COLUMN_LOCATOR__RELATIVE:
        return relative != RELATIVE_EDEFAULT;
      case FormatPackage.COLUMN_LOCATOR__NOBREAK:
        return nobreak != NOBREAK_EDEFAULT;
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
    result.append(" (fixed: ");
    result.append(fixed);
    result.append(", relative: ");
    result.append(relative);
    result.append(", nobreak: ");
    result.append(nobreak);
    result.append(')');
    return result.toString();
  }

} //ColumnLocatorImpl
