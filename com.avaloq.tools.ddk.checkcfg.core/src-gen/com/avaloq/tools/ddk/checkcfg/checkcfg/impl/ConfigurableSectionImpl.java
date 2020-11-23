/**
 */
package com.avaloq.tools.ddk.checkcfg.checkcfg.impl;

import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfigurableSection;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Configurable Section</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.impl.ConfigurableSectionImpl#getParameterConfigurations <em>Parameter Configurations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConfigurableSectionImpl extends MinimalEObjectImpl.Container implements ConfigurableSection
{
	/**
	 * The cached value of the '{@link #getParameterConfigurations() <em>Parameter Configurations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterConfigurations()
	 * @generated
	 * @ordered
	 */
	protected EList<ConfiguredParameter> parameterConfigurations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConfigurableSectionImpl()
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
		return CheckcfgPackage.Literals.CONFIGURABLE_SECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ConfiguredParameter> getParameterConfigurations()
	{
		if (parameterConfigurations == null)
		{
			parameterConfigurations = new EObjectContainmentEList<ConfiguredParameter>(ConfiguredParameter.class, this, CheckcfgPackage.CONFIGURABLE_SECTION__PARAMETER_CONFIGURATIONS);
		}
		return parameterConfigurations;
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
			case CheckcfgPackage.CONFIGURABLE_SECTION__PARAMETER_CONFIGURATIONS:
				return ((InternalEList<?>)getParameterConfigurations()).basicRemove(otherEnd, msgs);
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
			case CheckcfgPackage.CONFIGURABLE_SECTION__PARAMETER_CONFIGURATIONS:
				return getParameterConfigurations();
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
			case CheckcfgPackage.CONFIGURABLE_SECTION__PARAMETER_CONFIGURATIONS:
				getParameterConfigurations().clear();
				getParameterConfigurations().addAll((Collection<? extends ConfiguredParameter>)newValue);
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
			case CheckcfgPackage.CONFIGURABLE_SECTION__PARAMETER_CONFIGURATIONS:
				getParameterConfigurations().clear();
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
			case CheckcfgPackage.CONFIGURABLE_SECTION__PARAMETER_CONFIGURATIONS:
				return parameterConfigurations != null && !parameterConfigurations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ConfigurableSectionImpl
