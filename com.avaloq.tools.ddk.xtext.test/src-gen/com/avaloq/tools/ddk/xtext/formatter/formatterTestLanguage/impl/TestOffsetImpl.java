/**
 */
package com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.impl;

import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.TestOffset;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Offset</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.impl.TestOffsetImpl#getValue <em>Value</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.impl.TestOffsetImpl#getFirst <em>First</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.impl.TestOffsetImpl#getSecond <em>Second</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestOffsetImpl extends RootImpl implements TestOffset
{
  /**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final String VALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected String value = VALUE_EDEFAULT;

  /**
   * The default value of the '{@link #getFirst() <em>First</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFirst()
   * @generated
   * @ordered
   */
  protected static final String FIRST_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getFirst() <em>First</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFirst()
   * @generated
   * @ordered
   */
  protected String first = FIRST_EDEFAULT;

  /**
   * The default value of the '{@link #getSecond() <em>Second</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSecond()
   * @generated
   * @ordered
   */
  protected static final String SECOND_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSecond() <em>Second</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSecond()
   * @generated
   * @ordered
   */
  protected String second = SECOND_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TestOffsetImpl()
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
    return FormatterTestLanguagePackage.Literals.TEST_OFFSET;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(String newValue)
  {
    String oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatterTestLanguagePackage.TEST_OFFSET__VALUE, oldValue, value));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFirst()
  {
    return first;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFirst(String newFirst)
  {
    String oldFirst = first;
    first = newFirst;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatterTestLanguagePackage.TEST_OFFSET__FIRST, oldFirst, first));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSecond()
  {
    return second;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSecond(String newSecond)
  {
    String oldSecond = second;
    second = newSecond;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatterTestLanguagePackage.TEST_OFFSET__SECOND, oldSecond, second));
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
      case FormatterTestLanguagePackage.TEST_OFFSET__VALUE:
        return getValue();
      case FormatterTestLanguagePackage.TEST_OFFSET__FIRST:
        return getFirst();
      case FormatterTestLanguagePackage.TEST_OFFSET__SECOND:
        return getSecond();
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
      case FormatterTestLanguagePackage.TEST_OFFSET__VALUE:
        setValue((String)newValue);
        return;
      case FormatterTestLanguagePackage.TEST_OFFSET__FIRST:
        setFirst((String)newValue);
        return;
      case FormatterTestLanguagePackage.TEST_OFFSET__SECOND:
        setSecond((String)newValue);
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
      case FormatterTestLanguagePackage.TEST_OFFSET__VALUE:
        setValue(VALUE_EDEFAULT);
        return;
      case FormatterTestLanguagePackage.TEST_OFFSET__FIRST:
        setFirst(FIRST_EDEFAULT);
        return;
      case FormatterTestLanguagePackage.TEST_OFFSET__SECOND:
        setSecond(SECOND_EDEFAULT);
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
      case FormatterTestLanguagePackage.TEST_OFFSET__VALUE:
        return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
      case FormatterTestLanguagePackage.TEST_OFFSET__FIRST:
        return FIRST_EDEFAULT == null ? first != null : !FIRST_EDEFAULT.equals(first);
      case FormatterTestLanguagePackage.TEST_OFFSET__SECOND:
        return SECOND_EDEFAULT == null ? second != null : !SECOND_EDEFAULT.equals(second);
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
    result.append(" (value: ");
    result.append(value);
    result.append(", first: ");
    result.append(first);
    result.append(", second: ");
    result.append(second);
    result.append(')');
    return result.toString();
  }

} //TestOffsetImpl
