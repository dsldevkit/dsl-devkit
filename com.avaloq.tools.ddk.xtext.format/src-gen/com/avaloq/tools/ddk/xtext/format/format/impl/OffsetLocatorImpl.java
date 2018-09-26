/**
 */
package com.avaloq.tools.ddk.xtext.format.format.impl;

import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;
import com.avaloq.tools.ddk.xtext.format.format.IntValue;
import com.avaloq.tools.ddk.xtext.format.format.OffsetLocator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Offset Locator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.OffsetLocatorImpl#isFixed <em>Fixed</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.OffsetLocatorImpl#getValue <em>Value</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.OffsetLocatorImpl#isNobreak <em>Nobreak</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OffsetLocatorImpl extends LocatorImpl implements OffsetLocator
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
  protected OffsetLocatorImpl()
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
    return FormatPackage.Literals.OFFSET_LOCATOR;
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
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.OFFSET_LOCATOR__FIXED, oldFixed, fixed));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormatPackage.OFFSET_LOCATOR__VALUE, oldValue, newValue);
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
        msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormatPackage.OFFSET_LOCATOR__VALUE, null, msgs);
      if (newValue != null)
        msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormatPackage.OFFSET_LOCATOR__VALUE, null, msgs);
      msgs = basicSetValue(newValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.OFFSET_LOCATOR__VALUE, newValue, newValue));
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
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.OFFSET_LOCATOR__NOBREAK, oldNobreak, nobreak));
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
      case FormatPackage.OFFSET_LOCATOR__VALUE:
        return basicSetValue(null, msgs);
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
      case FormatPackage.OFFSET_LOCATOR__FIXED:
        return isFixed();
      case FormatPackage.OFFSET_LOCATOR__VALUE:
        return getValue();
      case FormatPackage.OFFSET_LOCATOR__NOBREAK:
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
      case FormatPackage.OFFSET_LOCATOR__FIXED:
        setFixed((Boolean)newValue);
        return;
      case FormatPackage.OFFSET_LOCATOR__VALUE:
        setValue((IntValue)newValue);
        return;
      case FormatPackage.OFFSET_LOCATOR__NOBREAK:
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
      case FormatPackage.OFFSET_LOCATOR__FIXED:
        setFixed(FIXED_EDEFAULT);
        return;
      case FormatPackage.OFFSET_LOCATOR__VALUE:
        setValue((IntValue)null);
        return;
      case FormatPackage.OFFSET_LOCATOR__NOBREAK:
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
      case FormatPackage.OFFSET_LOCATOR__FIXED:
        return fixed != FIXED_EDEFAULT;
      case FormatPackage.OFFSET_LOCATOR__VALUE:
        return value != null;
      case FormatPackage.OFFSET_LOCATOR__NOBREAK:
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
    result.append(", nobreak: ");
    result.append(nobreak);
    result.append(')');
    return result.toString();
  }

} //OffsetLocatorImpl
