/**
 */
package com.avaloq.tools.ddk.xtext.format.format.impl;

import com.avaloq.tools.ddk.xtext.format.format.Constant;
import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constant</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.ConstantImpl#isIntType <em>Int Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.ConstantImpl#isStringType <em>String Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.ConstantImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.ConstantImpl#getIntValue <em>Int Value</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.ConstantImpl#getStringValue <em>String Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConstantImpl extends MinimalEObjectImpl.Container implements Constant
{
  /**
   * The default value of the '{@link #isIntType() <em>Int Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isIntType()
   * @generated
   * @ordered
   */
  protected static final boolean INT_TYPE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isIntType() <em>Int Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isIntType()
   * @generated
   * @ordered
   */
  protected boolean intType = INT_TYPE_EDEFAULT;

  /**
   * The default value of the '{@link #isStringType() <em>String Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isStringType()
   * @generated
   * @ordered
   */
  protected static final boolean STRING_TYPE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isStringType() <em>String Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isStringType()
   * @generated
   * @ordered
   */
  protected boolean stringType = STRING_TYPE_EDEFAULT;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getIntValue() <em>Int Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIntValue()
   * @generated
   * @ordered
   */
  protected static final Integer INT_VALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIntValue() <em>Int Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIntValue()
   * @generated
   * @ordered
   */
  protected Integer intValue = INT_VALUE_EDEFAULT;

  /**
   * The default value of the '{@link #getStringValue() <em>String Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStringValue()
   * @generated
   * @ordered
   */
  protected static final String STRING_VALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getStringValue() <em>String Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStringValue()
   * @generated
   * @ordered
   */
  protected String stringValue = STRING_VALUE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConstantImpl()
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
    return FormatPackage.Literals.CONSTANT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isIntType()
  {
    return intType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIntType(boolean newIntType)
  {
    boolean oldIntType = intType;
    intType = newIntType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.CONSTANT__INT_TYPE, oldIntType, intType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isStringType()
  {
    return stringType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStringType(boolean newStringType)
  {
    boolean oldStringType = stringType;
    stringType = newStringType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.CONSTANT__STRING_TYPE, oldStringType, stringType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.CONSTANT__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Integer getIntValue()
  {
    return intValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIntValue(Integer newIntValue)
  {
    Integer oldIntValue = intValue;
    intValue = newIntValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.CONSTANT__INT_VALUE, oldIntValue, intValue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getStringValue()
  {
    return stringValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStringValue(String newStringValue)
  {
    String oldStringValue = stringValue;
    stringValue = newStringValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.CONSTANT__STRING_VALUE, oldStringValue, stringValue));
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
      case FormatPackage.CONSTANT__INT_TYPE:
        return isIntType();
      case FormatPackage.CONSTANT__STRING_TYPE:
        return isStringType();
      case FormatPackage.CONSTANT__NAME:
        return getName();
      case FormatPackage.CONSTANT__INT_VALUE:
        return getIntValue();
      case FormatPackage.CONSTANT__STRING_VALUE:
        return getStringValue();
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
      case FormatPackage.CONSTANT__INT_TYPE:
        setIntType((Boolean)newValue);
        return;
      case FormatPackage.CONSTANT__STRING_TYPE:
        setStringType((Boolean)newValue);
        return;
      case FormatPackage.CONSTANT__NAME:
        setName((String)newValue);
        return;
      case FormatPackage.CONSTANT__INT_VALUE:
        setIntValue((Integer)newValue);
        return;
      case FormatPackage.CONSTANT__STRING_VALUE:
        setStringValue((String)newValue);
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
      case FormatPackage.CONSTANT__INT_TYPE:
        setIntType(INT_TYPE_EDEFAULT);
        return;
      case FormatPackage.CONSTANT__STRING_TYPE:
        setStringType(STRING_TYPE_EDEFAULT);
        return;
      case FormatPackage.CONSTANT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case FormatPackage.CONSTANT__INT_VALUE:
        setIntValue(INT_VALUE_EDEFAULT);
        return;
      case FormatPackage.CONSTANT__STRING_VALUE:
        setStringValue(STRING_VALUE_EDEFAULT);
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
      case FormatPackage.CONSTANT__INT_TYPE:
        return intType != INT_TYPE_EDEFAULT;
      case FormatPackage.CONSTANT__STRING_TYPE:
        return stringType != STRING_TYPE_EDEFAULT;
      case FormatPackage.CONSTANT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case FormatPackage.CONSTANT__INT_VALUE:
        return INT_VALUE_EDEFAULT == null ? intValue != null : !INT_VALUE_EDEFAULT.equals(intValue);
      case FormatPackage.CONSTANT__STRING_VALUE:
        return STRING_VALUE_EDEFAULT == null ? stringValue != null : !STRING_VALUE_EDEFAULT.equals(stringValue);
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
    result.append(" (intType: ");
    result.append(intType);
    result.append(", stringType: ");
    result.append(stringType);
    result.append(", name: ");
    result.append(name);
    result.append(", intValue: ");
    result.append(intValue);
    result.append(", stringValue: ");
    result.append(stringValue);
    result.append(')');
    return result.toString();
  }

} //ConstantImpl
