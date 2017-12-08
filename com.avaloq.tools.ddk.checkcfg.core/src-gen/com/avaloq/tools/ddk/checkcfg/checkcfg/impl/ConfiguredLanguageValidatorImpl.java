/**
 */
package com.avaloq.tools.ddk.checkcfg.checkcfg.impl;

import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
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
 * An implementation of the model object '<em><b>Configured Language Validator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.impl.ConfiguredLanguageValidatorImpl#getLanguage <em>Language</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.impl.ConfiguredLanguageValidatorImpl#getCatalogConfigurations <em>Catalog Configurations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConfiguredLanguageValidatorImpl extends ConfigurableSectionImpl implements ConfiguredLanguageValidator
{
	/**
	 * The default value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String LANGUAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected String language = LANGUAGE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCatalogConfigurations() <em>Catalog Configurations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCatalogConfigurations()
	 * @generated
	 * @ordered
	 */
	protected EList<ConfiguredCatalog> catalogConfigurations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConfiguredLanguageValidatorImpl()
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
		return CheckcfgPackage.Literals.CONFIGURED_LANGUAGE_VALIDATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLanguage()
	{
		return language;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLanguage(String newLanguage)
	{
		String oldLanguage = language;
		language = newLanguage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CheckcfgPackage.CONFIGURED_LANGUAGE_VALIDATOR__LANGUAGE, oldLanguage, language));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConfiguredCatalog> getCatalogConfigurations()
	{
		if (catalogConfigurations == null)
		{
			catalogConfigurations = new EObjectContainmentEList<ConfiguredCatalog>(ConfiguredCatalog.class, this, CheckcfgPackage.CONFIGURED_LANGUAGE_VALIDATOR__CATALOG_CONFIGURATIONS);
		}
		return catalogConfigurations;
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
			case CheckcfgPackage.CONFIGURED_LANGUAGE_VALIDATOR__CATALOG_CONFIGURATIONS:
				return ((InternalEList<?>)getCatalogConfigurations()).basicRemove(otherEnd, msgs);
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
			case CheckcfgPackage.CONFIGURED_LANGUAGE_VALIDATOR__LANGUAGE:
				return getLanguage();
			case CheckcfgPackage.CONFIGURED_LANGUAGE_VALIDATOR__CATALOG_CONFIGURATIONS:
				return getCatalogConfigurations();
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
			case CheckcfgPackage.CONFIGURED_LANGUAGE_VALIDATOR__LANGUAGE:
				setLanguage((String)newValue);
				return;
			case CheckcfgPackage.CONFIGURED_LANGUAGE_VALIDATOR__CATALOG_CONFIGURATIONS:
				getCatalogConfigurations().clear();
				getCatalogConfigurations().addAll((Collection<? extends ConfiguredCatalog>)newValue);
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
			case CheckcfgPackage.CONFIGURED_LANGUAGE_VALIDATOR__LANGUAGE:
				setLanguage(LANGUAGE_EDEFAULT);
				return;
			case CheckcfgPackage.CONFIGURED_LANGUAGE_VALIDATOR__CATALOG_CONFIGURATIONS:
				getCatalogConfigurations().clear();
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
			case CheckcfgPackage.CONFIGURED_LANGUAGE_VALIDATOR__LANGUAGE:
				return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT.equals(language);
			case CheckcfgPackage.CONFIGURED_LANGUAGE_VALIDATOR__CATALOG_CONFIGURATIONS:
				return catalogConfigurations != null && !catalogConfigurations.isEmpty();
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
		result.append(" (language: ");
		result.append(language);
		result.append(')');
		return result.toString();
	}

} //ConfiguredLanguageValidatorImpl
