/**
 */
package com.avaloq.tools.ddk.checkcfg.checkcfg.impl;

import com.avaloq.tools.ddk.check.check.Check;

import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter;
import com.avaloq.tools.ddk.checkcfg.checkcfg.SeverityKind;

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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Configured Check</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.impl.ConfiguredCheckImpl#getSeverity <em>Severity</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.impl.ConfiguredCheckImpl#getCheck <em>Check</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.checkcfg.checkcfg.impl.ConfiguredCheckImpl#getParameterConfigurations <em>Parameter Configurations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConfiguredCheckImpl extends MinimalEObjectImpl.Container implements ConfiguredCheck
{
	/**
	 * The default value of the '{@link #getSeverity() <em>Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeverity()
	 * @generated
	 * @ordered
	 */
	protected static final SeverityKind SEVERITY_EDEFAULT = SeverityKind.DEFAULT;

	/**
	 * The cached value of the '{@link #getSeverity() <em>Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeverity()
	 * @generated
	 * @ordered
	 */
	protected SeverityKind severity = SEVERITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCheck() <em>Check</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCheck()
	 * @generated
	 * @ordered
	 */
	protected Check check;

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
	protected ConfiguredCheckImpl()
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
		return CheckcfgPackage.Literals.CONFIGURED_CHECK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SeverityKind getSeverity()
	{
		return severity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSeverity(SeverityKind newSeverity)
	{
		SeverityKind oldSeverity = severity;
		severity = newSeverity == null ? SEVERITY_EDEFAULT : newSeverity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CheckcfgPackage.CONFIGURED_CHECK__SEVERITY, oldSeverity, severity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Check getCheck()
	{
		if (check != null && check.eIsProxy())
		{
			InternalEObject oldCheck = (InternalEObject)check;
			check = (Check)eResolveProxy(oldCheck);
			if (check != oldCheck)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CheckcfgPackage.CONFIGURED_CHECK__CHECK, oldCheck, check));
			}
		}
		return check;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Check basicGetCheck()
	{
		return check;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCheck(Check newCheck)
	{
		Check oldCheck = check;
		check = newCheck;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CheckcfgPackage.CONFIGURED_CHECK__CHECK, oldCheck, check));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConfiguredParameter> getParameterConfigurations()
	{
		if (parameterConfigurations == null)
		{
			parameterConfigurations = new EObjectContainmentEList<ConfiguredParameter>(ConfiguredParameter.class, this, CheckcfgPackage.CONFIGURED_CHECK__PARAMETER_CONFIGURATIONS);
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
			case CheckcfgPackage.CONFIGURED_CHECK__PARAMETER_CONFIGURATIONS:
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
			case CheckcfgPackage.CONFIGURED_CHECK__SEVERITY:
				return getSeverity();
			case CheckcfgPackage.CONFIGURED_CHECK__CHECK:
				if (resolve) return getCheck();
				return basicGetCheck();
			case CheckcfgPackage.CONFIGURED_CHECK__PARAMETER_CONFIGURATIONS:
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
			case CheckcfgPackage.CONFIGURED_CHECK__SEVERITY:
				setSeverity((SeverityKind)newValue);
				return;
			case CheckcfgPackage.CONFIGURED_CHECK__CHECK:
				setCheck((Check)newValue);
				return;
			case CheckcfgPackage.CONFIGURED_CHECK__PARAMETER_CONFIGURATIONS:
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
			case CheckcfgPackage.CONFIGURED_CHECK__SEVERITY:
				setSeverity(SEVERITY_EDEFAULT);
				return;
			case CheckcfgPackage.CONFIGURED_CHECK__CHECK:
				setCheck((Check)null);
				return;
			case CheckcfgPackage.CONFIGURED_CHECK__PARAMETER_CONFIGURATIONS:
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
			case CheckcfgPackage.CONFIGURED_CHECK__SEVERITY:
				return severity != SEVERITY_EDEFAULT;
			case CheckcfgPackage.CONFIGURED_CHECK__CHECK:
				return check != null;
			case CheckcfgPackage.CONFIGURED_CHECK__PARAMETER_CONFIGURATIONS:
				return parameterConfigurations != null && !parameterConfigurations.isEmpty();
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
		result.append(" (severity: ");
		result.append(severity);
		result.append(')');
		return result.toString();
	}

} //ConfiguredCheckImpl
