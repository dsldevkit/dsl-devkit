/**
 */
package com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.impl;

import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Line;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.TestIndentation;

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
 * An implementation of the model object '<em><b>Test Indentation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.impl.TestIndentationImpl#getSub <em>Sub</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.impl.TestIndentationImpl#getItems <em>Items</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.impl.TestIndentationImpl#isSemi <em>Semi</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestIndentationImpl extends RootImpl implements TestIndentation
{
  /**
   * The cached value of the '{@link #getSub() <em>Sub</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSub()
   * @generated
   * @ordered
   */
  protected EList<TestIndentation> sub;

  /**
   * The cached value of the '{@link #getItems() <em>Items</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getItems()
   * @generated
   * @ordered
   */
  protected EList<Line> items;

  /**
   * The default value of the '{@link #isSemi() <em>Semi</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSemi()
   * @generated
   * @ordered
   */
  protected static final boolean SEMI_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSemi() <em>Semi</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSemi()
   * @generated
   * @ordered
   */
  protected boolean semi = SEMI_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TestIndentationImpl()
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
    return FormatterTestLanguagePackage.Literals.TEST_INDENTATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<TestIndentation> getSub()
  {
    if (sub == null)
    {
      sub = new EObjectContainmentEList<TestIndentation>(TestIndentation.class, this, FormatterTestLanguagePackage.TEST_INDENTATION__SUB);
    }
    return sub;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Line> getItems()
  {
    if (items == null)
    {
      items = new EObjectContainmentEList<Line>(Line.class, this, FormatterTestLanguagePackage.TEST_INDENTATION__ITEMS);
    }
    return items;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSemi()
  {
    return semi;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSemi(boolean newSemi)
  {
    boolean oldSemi = semi;
    semi = newSemi;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatterTestLanguagePackage.TEST_INDENTATION__SEMI, oldSemi, semi));
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
      case FormatterTestLanguagePackage.TEST_INDENTATION__SUB:
        return ((InternalEList<?>)getSub()).basicRemove(otherEnd, msgs);
      case FormatterTestLanguagePackage.TEST_INDENTATION__ITEMS:
        return ((InternalEList<?>)getItems()).basicRemove(otherEnd, msgs);
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
      case FormatterTestLanguagePackage.TEST_INDENTATION__SUB:
        return getSub();
      case FormatterTestLanguagePackage.TEST_INDENTATION__ITEMS:
        return getItems();
      case FormatterTestLanguagePackage.TEST_INDENTATION__SEMI:
        return isSemi();
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
      case FormatterTestLanguagePackage.TEST_INDENTATION__SUB:
        getSub().clear();
        getSub().addAll((Collection<? extends TestIndentation>)newValue);
        return;
      case FormatterTestLanguagePackage.TEST_INDENTATION__ITEMS:
        getItems().clear();
        getItems().addAll((Collection<? extends Line>)newValue);
        return;
      case FormatterTestLanguagePackage.TEST_INDENTATION__SEMI:
        setSemi((Boolean)newValue);
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
      case FormatterTestLanguagePackage.TEST_INDENTATION__SUB:
        getSub().clear();
        return;
      case FormatterTestLanguagePackage.TEST_INDENTATION__ITEMS:
        getItems().clear();
        return;
      case FormatterTestLanguagePackage.TEST_INDENTATION__SEMI:
        setSemi(SEMI_EDEFAULT);
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
      case FormatterTestLanguagePackage.TEST_INDENTATION__SUB:
        return sub != null && !sub.isEmpty();
      case FormatterTestLanguagePackage.TEST_INDENTATION__ITEMS:
        return items != null && !items.isEmpty();
      case FormatterTestLanguagePackage.TEST_INDENTATION__SEMI:
        return semi != SEMI_EDEFAULT;
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
    result.append(" (semi: ");
    result.append(semi);
    result.append(')');
    return result.toString();
  }

} //TestIndentationImpl
