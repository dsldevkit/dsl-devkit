/**
 */
package com.avaloq.tools.ddk.checkcfg.checkcfg.impl;

import com.avaloq.tools.ddk.check.check.CheckCatalog;

import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck;

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
 * An implementation of the model object '<em><b>Configured Catalog</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.impl.ConfiguredCatalogImpl#getCatalog <em>Catalog</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.impl.ConfiguredCatalogImpl#getCheckConfigurations <em>Check Configurations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConfiguredCatalogImpl extends ConfigurableSectionImpl implements ConfiguredCatalog
{
  /**
   * The cached value of the '{@link #getCatalog() <em>Catalog</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCatalog()
   * @generated
   * @ordered
   */
  protected CheckCatalog catalog;

  /**
   * The cached value of the '{@link #getCheckConfigurations() <em>Check Configurations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCheckConfigurations()
   * @generated
   * @ordered
   */
  protected EList<ConfiguredCheck> checkConfigurations;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConfiguredCatalogImpl()
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
    return CheckcfgPackage.Literals.CONFIGURED_CATALOG;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public CheckCatalog getCatalog()
  {
    if (catalog != null && catalog.eIsProxy())
    {
      InternalEObject oldCatalog = (InternalEObject)catalog;
      catalog = (CheckCatalog)eResolveProxy(oldCatalog);
      if (catalog != oldCatalog)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CheckcfgPackage.CONFIGURED_CATALOG__CATALOG, oldCatalog, catalog));
      }
    }
    return catalog;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CheckCatalog basicGetCatalog()
  {
    return catalog;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setCatalog(CheckCatalog newCatalog)
  {
    CheckCatalog oldCatalog = catalog;
    catalog = newCatalog;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CheckcfgPackage.CONFIGURED_CATALOG__CATALOG, oldCatalog, catalog));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<ConfiguredCheck> getCheckConfigurations()
  {
    if (checkConfigurations == null)
    {
      checkConfigurations = new EObjectContainmentEList<ConfiguredCheck>(ConfiguredCheck.class, this, CheckcfgPackage.CONFIGURED_CATALOG__CHECK_CONFIGURATIONS);
    }
    return checkConfigurations;
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
      case CheckcfgPackage.CONFIGURED_CATALOG__CHECK_CONFIGURATIONS:
        return ((InternalEList<?>)getCheckConfigurations()).basicRemove(otherEnd, msgs);
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
      case CheckcfgPackage.CONFIGURED_CATALOG__CATALOG:
        if (resolve) return getCatalog();
        return basicGetCatalog();
      case CheckcfgPackage.CONFIGURED_CATALOG__CHECK_CONFIGURATIONS:
        return getCheckConfigurations();
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
      case CheckcfgPackage.CONFIGURED_CATALOG__CATALOG:
        setCatalog((CheckCatalog)newValue);
        return;
      case CheckcfgPackage.CONFIGURED_CATALOG__CHECK_CONFIGURATIONS:
        getCheckConfigurations().clear();
        getCheckConfigurations().addAll((Collection<? extends ConfiguredCheck>)newValue);
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
      case CheckcfgPackage.CONFIGURED_CATALOG__CATALOG:
        setCatalog((CheckCatalog)null);
        return;
      case CheckcfgPackage.CONFIGURED_CATALOG__CHECK_CONFIGURATIONS:
        getCheckConfigurations().clear();
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
      case CheckcfgPackage.CONFIGURED_CATALOG__CATALOG:
        return catalog != null;
      case CheckcfgPackage.CONFIGURED_CATALOG__CHECK_CONFIGURATIONS:
        return checkConfigurations != null && !checkConfigurations.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ConfiguredCatalogImpl
