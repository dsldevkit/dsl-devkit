/**
 */
package com.avaloq.tools.ddk.xtext.format.format.impl;

import com.avaloq.tools.ddk.xtext.format.format.Constant;
import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;
import com.avaloq.tools.ddk.xtext.format.format.Rule;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xtext.Grammar;

import org.eclipse.xtext.common.types.JvmDeclaredType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.FormatConfigurationImpl#getTargetGrammar <em>Target Grammar</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.FormatConfigurationImpl#getExtendedFormatConfiguration <em>Extended Format Configuration</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.FormatConfigurationImpl#getFormatterBaseClass <em>Formatter Base Class</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.FormatConfigurationImpl#getConstants <em>Constants</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.impl.FormatConfigurationImpl#getRules <em>Rules</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FormatConfigurationImpl extends MinimalEObjectImpl.Container implements FormatConfiguration
{
  /**
   * The cached value of the '{@link #getTargetGrammar() <em>Target Grammar</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetGrammar()
   * @generated
   * @ordered
   */
  protected Grammar targetGrammar;

  /**
   * The cached value of the '{@link #getExtendedFormatConfiguration() <em>Extended Format Configuration</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExtendedFormatConfiguration()
   * @generated
   * @ordered
   */
  protected FormatConfiguration extendedFormatConfiguration;

  /**
   * The cached value of the '{@link #getFormatterBaseClass() <em>Formatter Base Class</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFormatterBaseClass()
   * @generated
   * @ordered
   */
  protected JvmDeclaredType formatterBaseClass;

  /**
   * The cached value of the '{@link #getConstants() <em>Constants</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstants()
   * @generated
   * @ordered
   */
  protected EList<Constant> constants;

  /**
   * The cached value of the '{@link #getRules() <em>Rules</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRules()
   * @generated
   * @ordered
   */
  protected EList<Rule> rules;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FormatConfigurationImpl()
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
    return FormatPackage.Literals.FORMAT_CONFIGURATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Grammar getTargetGrammar()
  {
    if (targetGrammar != null && targetGrammar.eIsProxy())
    {
      InternalEObject oldTargetGrammar = (InternalEObject)targetGrammar;
      targetGrammar = (Grammar)eResolveProxy(oldTargetGrammar);
      if (targetGrammar != oldTargetGrammar)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormatPackage.FORMAT_CONFIGURATION__TARGET_GRAMMAR, oldTargetGrammar, targetGrammar));
      }
    }
    return targetGrammar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Grammar basicGetTargetGrammar()
  {
    return targetGrammar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetGrammar(Grammar newTargetGrammar)
  {
    Grammar oldTargetGrammar = targetGrammar;
    targetGrammar = newTargetGrammar;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.FORMAT_CONFIGURATION__TARGET_GRAMMAR, oldTargetGrammar, targetGrammar));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FormatConfiguration getExtendedFormatConfiguration()
  {
    if (extendedFormatConfiguration != null && extendedFormatConfiguration.eIsProxy())
    {
      InternalEObject oldExtendedFormatConfiguration = (InternalEObject)extendedFormatConfiguration;
      extendedFormatConfiguration = (FormatConfiguration)eResolveProxy(oldExtendedFormatConfiguration);
      if (extendedFormatConfiguration != oldExtendedFormatConfiguration)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormatPackage.FORMAT_CONFIGURATION__EXTENDED_FORMAT_CONFIGURATION, oldExtendedFormatConfiguration, extendedFormatConfiguration));
      }
    }
    return extendedFormatConfiguration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FormatConfiguration basicGetExtendedFormatConfiguration()
  {
    return extendedFormatConfiguration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExtendedFormatConfiguration(FormatConfiguration newExtendedFormatConfiguration)
  {
    FormatConfiguration oldExtendedFormatConfiguration = extendedFormatConfiguration;
    extendedFormatConfiguration = newExtendedFormatConfiguration;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.FORMAT_CONFIGURATION__EXTENDED_FORMAT_CONFIGURATION, oldExtendedFormatConfiguration, extendedFormatConfiguration));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmDeclaredType getFormatterBaseClass()
  {
    if (formatterBaseClass != null && formatterBaseClass.eIsProxy())
    {
      InternalEObject oldFormatterBaseClass = (InternalEObject)formatterBaseClass;
      formatterBaseClass = (JvmDeclaredType)eResolveProxy(oldFormatterBaseClass);
      if (formatterBaseClass != oldFormatterBaseClass)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FormatPackage.FORMAT_CONFIGURATION__FORMATTER_BASE_CLASS, oldFormatterBaseClass, formatterBaseClass));
      }
    }
    return formatterBaseClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmDeclaredType basicGetFormatterBaseClass()
  {
    return formatterBaseClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFormatterBaseClass(JvmDeclaredType newFormatterBaseClass)
  {
    JvmDeclaredType oldFormatterBaseClass = formatterBaseClass;
    formatterBaseClass = newFormatterBaseClass;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FormatPackage.FORMAT_CONFIGURATION__FORMATTER_BASE_CLASS, oldFormatterBaseClass, formatterBaseClass));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Constant> getConstants()
  {
    if (constants == null)
    {
      constants = new EObjectContainmentEList<Constant>(Constant.class, this, FormatPackage.FORMAT_CONFIGURATION__CONSTANTS);
    }
    return constants;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Rule> getRules()
  {
    if (rules == null)
    {
      rules = new EObjectContainmentEList<Rule>(Rule.class, this, FormatPackage.FORMAT_CONFIGURATION__RULES);
    }
    return rules;
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
      case FormatPackage.FORMAT_CONFIGURATION__CONSTANTS:
        return ((InternalEList<?>)getConstants()).basicRemove(otherEnd, msgs);
      case FormatPackage.FORMAT_CONFIGURATION__RULES:
        return ((InternalEList<?>)getRules()).basicRemove(otherEnd, msgs);
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
      case FormatPackage.FORMAT_CONFIGURATION__TARGET_GRAMMAR:
        if (resolve) return getTargetGrammar();
        return basicGetTargetGrammar();
      case FormatPackage.FORMAT_CONFIGURATION__EXTENDED_FORMAT_CONFIGURATION:
        if (resolve) return getExtendedFormatConfiguration();
        return basicGetExtendedFormatConfiguration();
      case FormatPackage.FORMAT_CONFIGURATION__FORMATTER_BASE_CLASS:
        if (resolve) return getFormatterBaseClass();
        return basicGetFormatterBaseClass();
      case FormatPackage.FORMAT_CONFIGURATION__CONSTANTS:
        return getConstants();
      case FormatPackage.FORMAT_CONFIGURATION__RULES:
        return getRules();
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
      case FormatPackage.FORMAT_CONFIGURATION__TARGET_GRAMMAR:
        setTargetGrammar((Grammar)newValue);
        return;
      case FormatPackage.FORMAT_CONFIGURATION__EXTENDED_FORMAT_CONFIGURATION:
        setExtendedFormatConfiguration((FormatConfiguration)newValue);
        return;
      case FormatPackage.FORMAT_CONFIGURATION__FORMATTER_BASE_CLASS:
        setFormatterBaseClass((JvmDeclaredType)newValue);
        return;
      case FormatPackage.FORMAT_CONFIGURATION__CONSTANTS:
        getConstants().clear();
        getConstants().addAll((Collection<? extends Constant>)newValue);
        return;
      case FormatPackage.FORMAT_CONFIGURATION__RULES:
        getRules().clear();
        getRules().addAll((Collection<? extends Rule>)newValue);
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
      case FormatPackage.FORMAT_CONFIGURATION__TARGET_GRAMMAR:
        setTargetGrammar((Grammar)null);
        return;
      case FormatPackage.FORMAT_CONFIGURATION__EXTENDED_FORMAT_CONFIGURATION:
        setExtendedFormatConfiguration((FormatConfiguration)null);
        return;
      case FormatPackage.FORMAT_CONFIGURATION__FORMATTER_BASE_CLASS:
        setFormatterBaseClass((JvmDeclaredType)null);
        return;
      case FormatPackage.FORMAT_CONFIGURATION__CONSTANTS:
        getConstants().clear();
        return;
      case FormatPackage.FORMAT_CONFIGURATION__RULES:
        getRules().clear();
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
      case FormatPackage.FORMAT_CONFIGURATION__TARGET_GRAMMAR:
        return targetGrammar != null;
      case FormatPackage.FORMAT_CONFIGURATION__EXTENDED_FORMAT_CONFIGURATION:
        return extendedFormatConfiguration != null;
      case FormatPackage.FORMAT_CONFIGURATION__FORMATTER_BASE_CLASS:
        return formatterBaseClass != null;
      case FormatPackage.FORMAT_CONFIGURATION__CONSTANTS:
        return constants != null && !constants.isEmpty();
      case FormatPackage.FORMAT_CONFIGURATION__RULES:
        return rules != null && !rules.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //FormatConfigurationImpl
