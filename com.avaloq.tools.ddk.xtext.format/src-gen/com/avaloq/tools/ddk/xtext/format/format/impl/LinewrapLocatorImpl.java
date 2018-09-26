/**
 */
package com.avaloq.tools.ddk.xtext.format.format.impl;

import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;
import com.avaloq.tools.ddk.xtext.format.format.IntValue;
import com.avaloq.tools.ddk.xtext.format.format.LinewrapLocator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Linewrap Locator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.LinewrapLocatorImpl#getValue <em>Value</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.LinewrapLocatorImpl#getMinimum <em>Minimum</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.LinewrapLocatorImpl#getDefault <em>Default</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.LinewrapLocatorImpl#getMaximum <em>Maximum</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.LinewrapLocatorImpl#isNoLinewrap <em>No Linewrap</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LinewrapLocatorImpl extends LocatorImpl implements LinewrapLocator
{
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
   * The cached value of the '{@link #getMinimum() <em>Minimum</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMinimum()
   * @generated
   * @ordered
   */
  protected IntValue minimum;

  /**
   * The cached value of the '{@link #getDefault() <em>Default</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDefault()
   * @generated
   * @ordered
   */
  protected IntValue default_;

  /**
   * The cached value of the '{@link #getMaximum() <em>Maximum</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMaximum()
   * @generated
   * @ordered
   */
  protected IntValue maximum;

  /**
   * The default value of the '{@link #isNoLinewrap() <em>No Linewrap</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNoLinewrap()
   * @generated
   * @ordered
   */
  protected static final boolean NO_LINEWRAP_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isNoLinewrap() <em>No Linewrap</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNoLinewrap()
   * @generated
   * @ordered
   */
  protected boolean noLinewrap = NO_LINEWRAP_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LinewrapLocatorImpl()
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
    return FormatPackage.Literals.LINEWRAP_LOCATOR;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormatPackage.LINEWRAP_LOCATOR__VALUE, oldValue, newValue);
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
        msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormatPackage.LINEWRAP_LOCATOR__VALUE, null, msgs);
      if (newValue != null)
        msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormatPackage.LINEWRAP_LOCATOR__VALUE, null, msgs);
      msgs = basicSetValue(newValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.LINEWRAP_LOCATOR__VALUE, newValue, newValue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntValue getMinimum()
  {
    return minimum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMinimum(IntValue newMinimum, NotificationChain msgs)
  {
    IntValue oldMinimum = minimum;
    minimum = newMinimum;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormatPackage.LINEWRAP_LOCATOR__MINIMUM, oldMinimum, newMinimum);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMinimum(IntValue newMinimum)
  {
    if (newMinimum != minimum)
    {
      NotificationChain msgs = null;
      if (minimum != null)
        msgs = ((InternalEObject)minimum).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormatPackage.LINEWRAP_LOCATOR__MINIMUM, null, msgs);
      if (newMinimum != null)
        msgs = ((InternalEObject)newMinimum).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormatPackage.LINEWRAP_LOCATOR__MINIMUM, null, msgs);
      msgs = basicSetMinimum(newMinimum, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.LINEWRAP_LOCATOR__MINIMUM, newMinimum, newMinimum));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntValue getDefault()
  {
    return default_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDefault(IntValue newDefault, NotificationChain msgs)
  {
    IntValue oldDefault = default_;
    default_ = newDefault;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormatPackage.LINEWRAP_LOCATOR__DEFAULT, oldDefault, newDefault);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDefault(IntValue newDefault)
  {
    if (newDefault != default_)
    {
      NotificationChain msgs = null;
      if (default_ != null)
        msgs = ((InternalEObject)default_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormatPackage.LINEWRAP_LOCATOR__DEFAULT, null, msgs);
      if (newDefault != null)
        msgs = ((InternalEObject)newDefault).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormatPackage.LINEWRAP_LOCATOR__DEFAULT, null, msgs);
      msgs = basicSetDefault(newDefault, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.LINEWRAP_LOCATOR__DEFAULT, newDefault, newDefault));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntValue getMaximum()
  {
    return maximum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMaximum(IntValue newMaximum, NotificationChain msgs)
  {
    IntValue oldMaximum = maximum;
    maximum = newMaximum;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FormatPackage.LINEWRAP_LOCATOR__MAXIMUM, oldMaximum, newMaximum);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMaximum(IntValue newMaximum)
  {
    if (newMaximum != maximum)
    {
      NotificationChain msgs = null;
      if (maximum != null)
        msgs = ((InternalEObject)maximum).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FormatPackage.LINEWRAP_LOCATOR__MAXIMUM, null, msgs);
      if (newMaximum != null)
        msgs = ((InternalEObject)newMaximum).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FormatPackage.LINEWRAP_LOCATOR__MAXIMUM, null, msgs);
      msgs = basicSetMaximum(newMaximum, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.LINEWRAP_LOCATOR__MAXIMUM, newMaximum, newMaximum));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isNoLinewrap()
  {
    return noLinewrap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNoLinewrap(boolean newNoLinewrap)
  {
    boolean oldNoLinewrap = noLinewrap;
    noLinewrap = newNoLinewrap;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.LINEWRAP_LOCATOR__NO_LINEWRAP, oldNoLinewrap, noLinewrap));
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
      case FormatPackage.LINEWRAP_LOCATOR__VALUE:
        return basicSetValue(null, msgs);
      case FormatPackage.LINEWRAP_LOCATOR__MINIMUM:
        return basicSetMinimum(null, msgs);
      case FormatPackage.LINEWRAP_LOCATOR__DEFAULT:
        return basicSetDefault(null, msgs);
      case FormatPackage.LINEWRAP_LOCATOR__MAXIMUM:
        return basicSetMaximum(null, msgs);
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
      case FormatPackage.LINEWRAP_LOCATOR__VALUE:
        return getValue();
      case FormatPackage.LINEWRAP_LOCATOR__MINIMUM:
        return getMinimum();
      case FormatPackage.LINEWRAP_LOCATOR__DEFAULT:
        return getDefault();
      case FormatPackage.LINEWRAP_LOCATOR__MAXIMUM:
        return getMaximum();
      case FormatPackage.LINEWRAP_LOCATOR__NO_LINEWRAP:
        return isNoLinewrap();
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
      case FormatPackage.LINEWRAP_LOCATOR__VALUE:
        setValue((IntValue)newValue);
        return;
      case FormatPackage.LINEWRAP_LOCATOR__MINIMUM:
        setMinimum((IntValue)newValue);
        return;
      case FormatPackage.LINEWRAP_LOCATOR__DEFAULT:
        setDefault((IntValue)newValue);
        return;
      case FormatPackage.LINEWRAP_LOCATOR__MAXIMUM:
        setMaximum((IntValue)newValue);
        return;
      case FormatPackage.LINEWRAP_LOCATOR__NO_LINEWRAP:
        setNoLinewrap((Boolean)newValue);
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
      case FormatPackage.LINEWRAP_LOCATOR__VALUE:
        setValue((IntValue)null);
        return;
      case FormatPackage.LINEWRAP_LOCATOR__MINIMUM:
        setMinimum((IntValue)null);
        return;
      case FormatPackage.LINEWRAP_LOCATOR__DEFAULT:
        setDefault((IntValue)null);
        return;
      case FormatPackage.LINEWRAP_LOCATOR__MAXIMUM:
        setMaximum((IntValue)null);
        return;
      case FormatPackage.LINEWRAP_LOCATOR__NO_LINEWRAP:
        setNoLinewrap(NO_LINEWRAP_EDEFAULT);
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
      case FormatPackage.LINEWRAP_LOCATOR__VALUE:
        return value != null;
      case FormatPackage.LINEWRAP_LOCATOR__MINIMUM:
        return minimum != null;
      case FormatPackage.LINEWRAP_LOCATOR__DEFAULT:
        return default_ != null;
      case FormatPackage.LINEWRAP_LOCATOR__MAXIMUM:
        return maximum != null;
      case FormatPackage.LINEWRAP_LOCATOR__NO_LINEWRAP:
        return noLinewrap != NO_LINEWRAP_EDEFAULT;
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
    result.append(" (noLinewrap: ");
    result.append(noLinewrap);
    result.append(')');
    return result.toString();
  }

} //LinewrapLocatorImpl
