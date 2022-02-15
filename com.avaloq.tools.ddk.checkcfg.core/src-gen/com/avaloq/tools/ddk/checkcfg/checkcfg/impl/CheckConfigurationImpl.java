/**
 */
package com.avaloq.tools.ddk.checkcfg.checkcfg.impl;

import com.avaloq.tools.ddk.check.check.FormalParameter;

import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfigurableSection;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredLanguageValidator;

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
 * An implementation of the model object '<em><b>Check Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.impl.CheckConfigurationImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.impl.CheckConfigurationImpl#getLanguageValidatorConfigurations <em>Language Validator Configurations</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.impl.CheckConfigurationImpl#getLegacyCatalogConfigurations <em>Legacy Catalog Configurations</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.impl.CheckConfigurationImpl#getProperties <em>Properties</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CheckConfigurationImpl extends ConfigurableSectionImpl implements CheckConfiguration
{
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
   * The cached value of the '{@link #getLanguageValidatorConfigurations() <em>Language Validator Configurations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLanguageValidatorConfigurations()
   * @generated
   * @ordered
   */
  protected EList<ConfiguredLanguageValidator> languageValidatorConfigurations;

  /**
   * The cached value of the '{@link #getLegacyCatalogConfigurations() <em>Legacy Catalog Configurations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLegacyCatalogConfigurations()
   * @generated
   * @ordered
   */
  protected EList<ConfiguredCatalog> legacyCatalogConfigurations;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CheckConfigurationImpl()
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
    return CheckcfgPackage.Literals.CHECK_CONFIGURATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CheckcfgPackage.CHECK_CONFIGURATION__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<ConfiguredLanguageValidator> getLanguageValidatorConfigurations()
  {
    if (languageValidatorConfigurations == null)
    {
      languageValidatorConfigurations = new EObjectContainmentEList<ConfiguredLanguageValidator>(ConfiguredLanguageValidator.class, this, CheckcfgPackage.CHECK_CONFIGURATION__LANGUAGE_VALIDATOR_CONFIGURATIONS);
    }
    return languageValidatorConfigurations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<ConfiguredCatalog> getLegacyCatalogConfigurations()
  {
    if (legacyCatalogConfigurations == null)
    {
      legacyCatalogConfigurations = new EObjectContainmentEList<ConfiguredCatalog>(ConfiguredCatalog.class, this, CheckcfgPackage.CHECK_CONFIGURATION__LEGACY_CATALOG_CONFIGURATIONS);
    }
    return legacyCatalogConfigurations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<FormalParameter> getProperties()
  {
    // TODO: implement this method to return the 'Properties' reference list
    // Ensure that you remove @generated or mark it @generated NOT
    // The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
    // so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
    throw new UnsupportedOperationException();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<ConfigurableSection> getConfigurableSections()
  {
    // TODO: implement this method
    // Ensure that you remove @generated or mark it @generated NOT
    throw new UnsupportedOperationException();
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
      case CheckcfgPackage.CHECK_CONFIGURATION__LANGUAGE_VALIDATOR_CONFIGURATIONS:
        return ((InternalEList<?>)getLanguageValidatorConfigurations()).basicRemove(otherEnd, msgs);
      case CheckcfgPackage.CHECK_CONFIGURATION__LEGACY_CATALOG_CONFIGURATIONS:
        return ((InternalEList<?>)getLegacyCatalogConfigurations()).basicRemove(otherEnd, msgs);
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
      case CheckcfgPackage.CHECK_CONFIGURATION__NAME:
        return getName();
      case CheckcfgPackage.CHECK_CONFIGURATION__LANGUAGE_VALIDATOR_CONFIGURATIONS:
        return getLanguageValidatorConfigurations();
      case CheckcfgPackage.CHECK_CONFIGURATION__LEGACY_CATALOG_CONFIGURATIONS:
        return getLegacyCatalogConfigurations();
      case CheckcfgPackage.CHECK_CONFIGURATION__PROPERTIES:
        return getProperties();
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
      case CheckcfgPackage.CHECK_CONFIGURATION__NAME:
        setName((String)newValue);
        return;
      case CheckcfgPackage.CHECK_CONFIGURATION__LANGUAGE_VALIDATOR_CONFIGURATIONS:
        getLanguageValidatorConfigurations().clear();
        getLanguageValidatorConfigurations().addAll((Collection<? extends ConfiguredLanguageValidator>)newValue);
        return;
      case CheckcfgPackage.CHECK_CONFIGURATION__LEGACY_CATALOG_CONFIGURATIONS:
        getLegacyCatalogConfigurations().clear();
        getLegacyCatalogConfigurations().addAll((Collection<? extends ConfiguredCatalog>)newValue);
        return;
      case CheckcfgPackage.CHECK_CONFIGURATION__PROPERTIES:
        getProperties().clear();
        getProperties().addAll((Collection<? extends FormalParameter>)newValue);
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
      case CheckcfgPackage.CHECK_CONFIGURATION__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CheckcfgPackage.CHECK_CONFIGURATION__LANGUAGE_VALIDATOR_CONFIGURATIONS:
        getLanguageValidatorConfigurations().clear();
        return;
      case CheckcfgPackage.CHECK_CONFIGURATION__LEGACY_CATALOG_CONFIGURATIONS:
        getLegacyCatalogConfigurations().clear();
        return;
      case CheckcfgPackage.CHECK_CONFIGURATION__PROPERTIES:
        getProperties().clear();
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
      case CheckcfgPackage.CHECK_CONFIGURATION__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CheckcfgPackage.CHECK_CONFIGURATION__LANGUAGE_VALIDATOR_CONFIGURATIONS:
        return languageValidatorConfigurations != null && !languageValidatorConfigurations.isEmpty();
      case CheckcfgPackage.CHECK_CONFIGURATION__LEGACY_CATALOG_CONFIGURATIONS:
        return legacyCatalogConfigurations != null && !legacyCatalogConfigurations.isEmpty();
      case CheckcfgPackage.CHECK_CONFIGURATION__PROPERTIES:
        return !getProperties().isEmpty();
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

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //CheckConfigurationImpl
