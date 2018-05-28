/**
 */
package com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.impl;

import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Enum1;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Enumeration;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enumeration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.impl.EnumerationImpl#getVal <em>Val</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnumerationImpl extends LineImpl implements Enumeration
{
  /**
   * The cached value of the '{@link #getVal() <em>Val</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVal()
   * @generated
   * @ordered
   */
  protected EList<Enum1> val;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EnumerationImpl()
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
    return FormatterTestLanguagePackage.Literals.ENUMERATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Enum1> getVal()
  {
    if (val == null)
    {
      val = new EDataTypeEList<Enum1>(Enum1.class, this, FormatterTestLanguagePackage.ENUMERATION__VAL);
    }
    return val;
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
      case FormatterTestLanguagePackage.ENUMERATION__VAL:
        return getVal();
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
      case FormatterTestLanguagePackage.ENUMERATION__VAL:
        getVal().clear();
        getVal().addAll((Collection<? extends Enum1>)newValue);
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
      case FormatterTestLanguagePackage.ENUMERATION__VAL:
        getVal().clear();
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
      case FormatterTestLanguagePackage.ENUMERATION__VAL:
        return val != null && !val.isEmpty();
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
    result.append(" (val: ");
    result.append(val);
    result.append(')');
    return result.toString();
  }

} //EnumerationImpl
