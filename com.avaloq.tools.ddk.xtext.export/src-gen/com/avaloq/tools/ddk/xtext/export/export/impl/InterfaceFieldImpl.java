/**
 */
package com.avaloq.tools.ddk.xtext.export.export.impl;

import com.avaloq.tools.ddk.xtext.export.export.ExportPackage;
import com.avaloq.tools.ddk.xtext.export.export.InterfaceField;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interface Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceFieldImpl#getField <em>Field</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InterfaceFieldImpl extends InterfaceItemImpl implements InterfaceField
{
	/**
	 * The cached value of the '{@link #getField() <em>Field</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getField()
	 * @generated
	 * @ordered
	 */
	protected EStructuralFeature field;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InterfaceFieldImpl()
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
		return ExportPackage.Literals.INTERFACE_FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EStructuralFeature getField()
	{
		if (field != null && field.eIsProxy())
		{
			InternalEObject oldField = (InternalEObject)field;
			field = (EStructuralFeature)eResolveProxy(oldField);
			if (field != oldField)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExportPackage.INTERFACE_FIELD__FIELD, oldField, field));
			}
		}
		return field;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EStructuralFeature basicGetField()
	{
		return field;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setField(EStructuralFeature newField)
	{
		EStructuralFeature oldField = field;
		field = newField;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExportPackage.INTERFACE_FIELD__FIELD, oldField, field));
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
			case ExportPackage.INTERFACE_FIELD__FIELD:
				if (resolve) return getField();
				return basicGetField();
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
			case ExportPackage.INTERFACE_FIELD__FIELD:
				setField((EStructuralFeature)newValue);
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
			case ExportPackage.INTERFACE_FIELD__FIELD:
				setField((EStructuralFeature)null);
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
			case ExportPackage.INTERFACE_FIELD__FIELD:
				return field != null;
		}
		return super.eIsSet(featureID);
	}

} //InterfaceFieldImpl
