/**
 */
package com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.impl;

import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.SuppressedHiddenSub;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Suppressed Hidden Sub</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.impl.SuppressedHiddenSubImpl#getIdval <em>Idval</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SuppressedHiddenSubImpl extends MinimalEObjectImpl.Container implements SuppressedHiddenSub
{
  /**
   * The default value of the '{@link #getIdval() <em>Idval</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdval()
   * @generated
   * @ordered
   */
  protected static final String IDVAL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIdval() <em>Idval</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdval()
   * @generated
   * @ordered
   */
  protected String idval = IDVAL_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SuppressedHiddenSubImpl()
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
    return FormatterTestLanguagePackage.Literals.SUPPRESSED_HIDDEN_SUB;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getIdval()
  {
    return idval;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIdval(String newIdval)
  {
    String oldIdval = idval;
    idval = newIdval;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatterTestLanguagePackage.SUPPRESSED_HIDDEN_SUB__IDVAL, oldIdval, idval));
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
      case FormatterTestLanguagePackage.SUPPRESSED_HIDDEN_SUB__IDVAL:
        return getIdval();
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
      case FormatterTestLanguagePackage.SUPPRESSED_HIDDEN_SUB__IDVAL:
        setIdval((String)newValue);
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
      case FormatterTestLanguagePackage.SUPPRESSED_HIDDEN_SUB__IDVAL:
        setIdval(IDVAL_EDEFAULT);
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
      case FormatterTestLanguagePackage.SUPPRESSED_HIDDEN_SUB__IDVAL:
        return IDVAL_EDEFAULT == null ? idval != null : !IDVAL_EDEFAULT.equals(idval);
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
    result.append(" (idval: ");
    result.append(idval);
    result.append(')');
    return result.toString();
  }

} //SuppressedHiddenSubImpl
