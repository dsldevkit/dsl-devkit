/**
 */
package com.avaloq.tools.ddk.check.check.impl;

import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.avaloq.tools.ddk.check.check.SeverityKind;
import com.avaloq.tools.ddk.check.check.SeverityRange;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Severity Range</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.SeverityRangeImpl#getMinSeverity <em>Min Severity</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.check.check.impl.SeverityRangeImpl#getMaxSeverity <em>Max Severity</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SeverityRangeImpl extends MinimalEObjectImpl.Container implements SeverityRange
{
	/**
	 * The default value of the '{@link #getMinSeverity() <em>Min Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinSeverity()
	 * @generated
	 * @ordered
	 */
	protected static final SeverityKind MIN_SEVERITY_EDEFAULT = SeverityKind.ERROR;

	/**
	 * The cached value of the '{@link #getMinSeverity() <em>Min Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinSeverity()
	 * @generated
	 * @ordered
	 */
	protected SeverityKind minSeverity = MIN_SEVERITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxSeverity() <em>Max Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxSeverity()
	 * @generated
	 * @ordered
	 */
	protected static final SeverityKind MAX_SEVERITY_EDEFAULT = SeverityKind.ERROR;

	/**
	 * The cached value of the '{@link #getMaxSeverity() <em>Max Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxSeverity()
	 * @generated
	 * @ordered
	 */
	protected SeverityKind maxSeverity = MAX_SEVERITY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SeverityRangeImpl()
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
		return CheckPackage.Literals.SEVERITY_RANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SeverityKind getMinSeverity()
	{
		return minSeverity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinSeverity(SeverityKind newMinSeverity)
	{
		SeverityKind oldMinSeverity = minSeverity;
		minSeverity = newMinSeverity == null ? MIN_SEVERITY_EDEFAULT : newMinSeverity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CheckPackage.SEVERITY_RANGE__MIN_SEVERITY, oldMinSeverity, minSeverity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SeverityKind getMaxSeverity()
	{
		return maxSeverity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxSeverity(SeverityKind newMaxSeverity)
	{
		SeverityKind oldMaxSeverity = maxSeverity;
		maxSeverity = newMaxSeverity == null ? MAX_SEVERITY_EDEFAULT : newMaxSeverity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CheckPackage.SEVERITY_RANGE__MAX_SEVERITY, oldMaxSeverity, maxSeverity));
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
			case CheckPackage.SEVERITY_RANGE__MIN_SEVERITY:
				return getMinSeverity();
			case CheckPackage.SEVERITY_RANGE__MAX_SEVERITY:
				return getMaxSeverity();
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
			case CheckPackage.SEVERITY_RANGE__MIN_SEVERITY:
				setMinSeverity((SeverityKind)newValue);
				return;
			case CheckPackage.SEVERITY_RANGE__MAX_SEVERITY:
				setMaxSeverity((SeverityKind)newValue);
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
			case CheckPackage.SEVERITY_RANGE__MIN_SEVERITY:
				setMinSeverity(MIN_SEVERITY_EDEFAULT);
				return;
			case CheckPackage.SEVERITY_RANGE__MAX_SEVERITY:
				setMaxSeverity(MAX_SEVERITY_EDEFAULT);
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
			case CheckPackage.SEVERITY_RANGE__MIN_SEVERITY:
				return minSeverity != MIN_SEVERITY_EDEFAULT;
			case CheckPackage.SEVERITY_RANGE__MAX_SEVERITY:
				return maxSeverity != MAX_SEVERITY_EDEFAULT;
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
		result.append(" (minSeverity: ");
		result.append(minSeverity);
		result.append(", maxSeverity: ");
		result.append(maxSeverity);
		result.append(')');
		return result.toString();
	}

} //SeverityRangeImpl
