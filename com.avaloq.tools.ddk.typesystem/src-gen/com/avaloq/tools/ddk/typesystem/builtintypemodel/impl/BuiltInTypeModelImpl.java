/**
 */
package com.avaloq.tools.ddk.typesystem.builtintypemodel.impl;

import com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModel;
import com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModelPackage;
import com.avaloq.tools.ddk.typesystem.builtintypemodel.InternalType;

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
 * An implementation of the model object '<em><b>Built In Type Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.typesystem.builtintypemodel.impl.BuiltInTypeModelImpl#getInternalTypes <em>Internal Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BuiltInTypeModelImpl extends MinimalEObjectImpl.Container implements BuiltInTypeModel
{
	/**
	 * The cached value of the '{@link #getInternalTypes() <em>Internal Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInternalTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<InternalType> internalTypes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BuiltInTypeModelImpl()
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
		return BuiltInTypeModelPackage.Literals.BUILT_IN_TYPE_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InternalType> getInternalTypes()
	{
		if (internalTypes == null)
		{
			internalTypes = new EObjectContainmentEList<InternalType>(InternalType.class, this, BuiltInTypeModelPackage.BUILT_IN_TYPE_MODEL__INTERNAL_TYPES);
		}
		return internalTypes;
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
			case BuiltInTypeModelPackage.BUILT_IN_TYPE_MODEL__INTERNAL_TYPES:
				return ((InternalEList<?>)getInternalTypes()).basicRemove(otherEnd, msgs);
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
			case BuiltInTypeModelPackage.BUILT_IN_TYPE_MODEL__INTERNAL_TYPES:
				return getInternalTypes();
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
			case BuiltInTypeModelPackage.BUILT_IN_TYPE_MODEL__INTERNAL_TYPES:
				getInternalTypes().clear();
				getInternalTypes().addAll((Collection<? extends InternalType>)newValue);
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
			case BuiltInTypeModelPackage.BUILT_IN_TYPE_MODEL__INTERNAL_TYPES:
				getInternalTypes().clear();
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
			case BuiltInTypeModelPackage.BUILT_IN_TYPE_MODEL__INTERNAL_TYPES:
				return internalTypes != null && !internalTypes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //BuiltInTypeModelImpl
