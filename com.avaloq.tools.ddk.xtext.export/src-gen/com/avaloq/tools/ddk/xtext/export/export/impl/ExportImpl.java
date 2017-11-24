/**
 */
package com.avaloq.tools.ddk.xtext.export.export.impl;

import com.avaloq.tools.ddk.xtext.export.export.Attribute;
import com.avaloq.tools.ddk.xtext.export.export.Export;
import com.avaloq.tools.ddk.xtext.export.export.ExportPackage;
import com.avaloq.tools.ddk.xtext.export.export.UserData;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Export</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportImpl#isAllowLookup <em>Allow Lookup</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportImpl#isQualifiedName <em>Qualified Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportImpl#getNaming <em>Naming</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportImpl#isFragmentUnique <em>Fragment Unique</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportImpl#getFragmentAttribute <em>Fragment Attribute</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportImpl#isFingerprint <em>Fingerprint</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportImpl#isResourceFingerprint <em>Resource Fingerprint</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportImpl#getUserData <em>User Data</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExportImpl extends DeclarationForTypeImpl implements Export
{
	/**
	 * The default value of the '{@link #isAllowLookup() <em>Allow Lookup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllowLookup()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALLOW_LOOKUP_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAllowLookup() <em>Allow Lookup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllowLookup()
	 * @generated
	 * @ordered
	 */
	protected boolean allowLookup = ALLOW_LOOKUP_EDEFAULT;

	/**
	 * This is true if the Allow Lookup attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean allowLookupESet;

	/**
	 * The default value of the '{@link #isQualifiedName() <em>Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isQualifiedName()
	 * @generated
	 * @ordered
	 */
	protected static final boolean QUALIFIED_NAME_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isQualifiedName() <em>Qualified Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isQualifiedName()
	 * @generated
	 * @ordered
	 */
	protected boolean qualifiedName = QUALIFIED_NAME_EDEFAULT;

	/**
	 * This is true if the Qualified Name attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean qualifiedNameESet;

	/**
	 * The cached value of the '{@link #getNaming() <em>Naming</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNaming()
	 * @generated
	 * @ordered
	 */
	protected Expression naming;

	/**
	 * The default value of the '{@link #isFragmentUnique() <em>Fragment Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFragmentUnique()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FRAGMENT_UNIQUE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFragmentUnique() <em>Fragment Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFragmentUnique()
	 * @generated
	 * @ordered
	 */
	protected boolean fragmentUnique = FRAGMENT_UNIQUE_EDEFAULT;

	/**
	 * This is true if the Fragment Unique attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean fragmentUniqueESet;

	/**
	 * The cached value of the '{@link #getFragmentAttribute() <em>Fragment Attribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFragmentAttribute()
	 * @generated
	 * @ordered
	 */
	protected EAttribute fragmentAttribute;

	/**
	 * The default value of the '{@link #isFingerprint() <em>Fingerprint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFingerprint()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FINGERPRINT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFingerprint() <em>Fingerprint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFingerprint()
	 * @generated
	 * @ordered
	 */
	protected boolean fingerprint = FINGERPRINT_EDEFAULT;

	/**
	 * This is true if the Fingerprint attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean fingerprintESet;

	/**
	 * The default value of the '{@link #isResourceFingerprint() <em>Resource Fingerprint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isResourceFingerprint()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RESOURCE_FINGERPRINT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isResourceFingerprint() <em>Resource Fingerprint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isResourceFingerprint()
	 * @generated
	 * @ordered
	 */
	protected boolean resourceFingerprint = RESOURCE_FINGERPRINT_EDEFAULT;

	/**
	 * This is true if the Resource Fingerprint attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean resourceFingerprintESet;

	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<Attribute> attributes;

	/**
	 * The cached value of the '{@link #getUserData() <em>User Data</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserData()
	 * @generated
	 * @ordered
	 */
	protected EList<UserData> userData;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExportImpl()
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
		return ExportPackage.Literals.EXPORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAllowLookup()
	{
		return allowLookup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAllowLookup(boolean newAllowLookup)
	{
		boolean oldAllowLookup = allowLookup;
		allowLookup = newAllowLookup;
		boolean oldAllowLookupESet = allowLookupESet;
		allowLookupESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExportPackage.EXPORT__ALLOW_LOOKUP, oldAllowLookup, allowLookup, !oldAllowLookupESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetAllowLookup()
	{
		boolean oldAllowLookup = allowLookup;
		boolean oldAllowLookupESet = allowLookupESet;
		allowLookup = ALLOW_LOOKUP_EDEFAULT;
		allowLookupESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ExportPackage.EXPORT__ALLOW_LOOKUP, oldAllowLookup, ALLOW_LOOKUP_EDEFAULT, oldAllowLookupESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAllowLookup()
	{
		return allowLookupESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isQualifiedName()
	{
		return qualifiedName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualifiedName(boolean newQualifiedName)
	{
		boolean oldQualifiedName = qualifiedName;
		qualifiedName = newQualifiedName;
		boolean oldQualifiedNameESet = qualifiedNameESet;
		qualifiedNameESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExportPackage.EXPORT__QUALIFIED_NAME, oldQualifiedName, qualifiedName, !oldQualifiedNameESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetQualifiedName()
	{
		boolean oldQualifiedName = qualifiedName;
		boolean oldQualifiedNameESet = qualifiedNameESet;
		qualifiedName = QUALIFIED_NAME_EDEFAULT;
		qualifiedNameESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ExportPackage.EXPORT__QUALIFIED_NAME, oldQualifiedName, QUALIFIED_NAME_EDEFAULT, oldQualifiedNameESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetQualifiedName()
	{
		return qualifiedNameESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getNaming()
	{
		return naming;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNaming(Expression newNaming, NotificationChain msgs)
	{
		Expression oldNaming = naming;
		naming = newNaming;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExportPackage.EXPORT__NAMING, oldNaming, newNaming);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNaming(Expression newNaming)
	{
		if (newNaming != naming)
		{
			NotificationChain msgs = null;
			if (naming != null)
				msgs = ((InternalEObject)naming).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExportPackage.EXPORT__NAMING, null, msgs);
			if (newNaming != null)
				msgs = ((InternalEObject)newNaming).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExportPackage.EXPORT__NAMING, null, msgs);
			msgs = basicSetNaming(newNaming, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExportPackage.EXPORT__NAMING, newNaming, newNaming));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFragmentUnique()
	{
		return fragmentUnique;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFragmentUnique(boolean newFragmentUnique)
	{
		boolean oldFragmentUnique = fragmentUnique;
		fragmentUnique = newFragmentUnique;
		boolean oldFragmentUniqueESet = fragmentUniqueESet;
		fragmentUniqueESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExportPackage.EXPORT__FRAGMENT_UNIQUE, oldFragmentUnique, fragmentUnique, !oldFragmentUniqueESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetFragmentUnique()
	{
		boolean oldFragmentUnique = fragmentUnique;
		boolean oldFragmentUniqueESet = fragmentUniqueESet;
		fragmentUnique = FRAGMENT_UNIQUE_EDEFAULT;
		fragmentUniqueESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ExportPackage.EXPORT__FRAGMENT_UNIQUE, oldFragmentUnique, FRAGMENT_UNIQUE_EDEFAULT, oldFragmentUniqueESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetFragmentUnique()
	{
		return fragmentUniqueESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFragmentAttribute()
	{
		if (fragmentAttribute != null && fragmentAttribute.eIsProxy())
		{
			InternalEObject oldFragmentAttribute = (InternalEObject)fragmentAttribute;
			fragmentAttribute = (EAttribute)eResolveProxy(oldFragmentAttribute);
			if (fragmentAttribute != oldFragmentAttribute)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExportPackage.EXPORT__FRAGMENT_ATTRIBUTE, oldFragmentAttribute, fragmentAttribute));
			}
		}
		return fragmentAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute basicGetFragmentAttribute()
	{
		return fragmentAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFragmentAttribute(EAttribute newFragmentAttribute)
	{
		EAttribute oldFragmentAttribute = fragmentAttribute;
		fragmentAttribute = newFragmentAttribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExportPackage.EXPORT__FRAGMENT_ATTRIBUTE, oldFragmentAttribute, fragmentAttribute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFingerprint()
	{
		return fingerprint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFingerprint(boolean newFingerprint)
	{
		boolean oldFingerprint = fingerprint;
		fingerprint = newFingerprint;
		boolean oldFingerprintESet = fingerprintESet;
		fingerprintESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExportPackage.EXPORT__FINGERPRINT, oldFingerprint, fingerprint, !oldFingerprintESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetFingerprint()
	{
		boolean oldFingerprint = fingerprint;
		boolean oldFingerprintESet = fingerprintESet;
		fingerprint = FINGERPRINT_EDEFAULT;
		fingerprintESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ExportPackage.EXPORT__FINGERPRINT, oldFingerprint, FINGERPRINT_EDEFAULT, oldFingerprintESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetFingerprint()
	{
		return fingerprintESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isResourceFingerprint()
	{
		return resourceFingerprint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceFingerprint(boolean newResourceFingerprint)
	{
		boolean oldResourceFingerprint = resourceFingerprint;
		resourceFingerprint = newResourceFingerprint;
		boolean oldResourceFingerprintESet = resourceFingerprintESet;
		resourceFingerprintESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExportPackage.EXPORT__RESOURCE_FINGERPRINT, oldResourceFingerprint, resourceFingerprint, !oldResourceFingerprintESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetResourceFingerprint()
	{
		boolean oldResourceFingerprint = resourceFingerprint;
		boolean oldResourceFingerprintESet = resourceFingerprintESet;
		resourceFingerprint = RESOURCE_FINGERPRINT_EDEFAULT;
		resourceFingerprintESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ExportPackage.EXPORT__RESOURCE_FINGERPRINT, oldResourceFingerprint, RESOURCE_FINGERPRINT_EDEFAULT, oldResourceFingerprintESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetResourceFingerprint()
	{
		return resourceFingerprintESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attribute> getAttributes()
	{
		if (attributes == null)
		{
			attributes = new EObjectContainmentEList<Attribute>(Attribute.class, this, ExportPackage.EXPORT__ATTRIBUTES);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UserData> getUserData()
	{
		if (userData == null)
		{
			userData = new EObjectContainmentEList<UserData>(UserData.class, this, ExportPackage.EXPORT__USER_DATA);
		}
		return userData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EAttribute> getEAttributes()
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
	public EList<EAttribute> getAllEAttributes()
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
	public EAttribute getNamingAttribute()
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
			case ExportPackage.EXPORT__NAMING:
				return basicSetNaming(null, msgs);
			case ExportPackage.EXPORT__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
			case ExportPackage.EXPORT__USER_DATA:
				return ((InternalEList<?>)getUserData()).basicRemove(otherEnd, msgs);
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
			case ExportPackage.EXPORT__ALLOW_LOOKUP:
				return isAllowLookup();
			case ExportPackage.EXPORT__QUALIFIED_NAME:
				return isQualifiedName();
			case ExportPackage.EXPORT__NAMING:
				return getNaming();
			case ExportPackage.EXPORT__FRAGMENT_UNIQUE:
				return isFragmentUnique();
			case ExportPackage.EXPORT__FRAGMENT_ATTRIBUTE:
				if (resolve) return getFragmentAttribute();
				return basicGetFragmentAttribute();
			case ExportPackage.EXPORT__FINGERPRINT:
				return isFingerprint();
			case ExportPackage.EXPORT__RESOURCE_FINGERPRINT:
				return isResourceFingerprint();
			case ExportPackage.EXPORT__ATTRIBUTES:
				return getAttributes();
			case ExportPackage.EXPORT__USER_DATA:
				return getUserData();
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
			case ExportPackage.EXPORT__ALLOW_LOOKUP:
				setAllowLookup((Boolean)newValue);
				return;
			case ExportPackage.EXPORT__QUALIFIED_NAME:
				setQualifiedName((Boolean)newValue);
				return;
			case ExportPackage.EXPORT__NAMING:
				setNaming((Expression)newValue);
				return;
			case ExportPackage.EXPORT__FRAGMENT_UNIQUE:
				setFragmentUnique((Boolean)newValue);
				return;
			case ExportPackage.EXPORT__FRAGMENT_ATTRIBUTE:
				setFragmentAttribute((EAttribute)newValue);
				return;
			case ExportPackage.EXPORT__FINGERPRINT:
				setFingerprint((Boolean)newValue);
				return;
			case ExportPackage.EXPORT__RESOURCE_FINGERPRINT:
				setResourceFingerprint((Boolean)newValue);
				return;
			case ExportPackage.EXPORT__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection<? extends Attribute>)newValue);
				return;
			case ExportPackage.EXPORT__USER_DATA:
				getUserData().clear();
				getUserData().addAll((Collection<? extends UserData>)newValue);
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
			case ExportPackage.EXPORT__ALLOW_LOOKUP:
				unsetAllowLookup();
				return;
			case ExportPackage.EXPORT__QUALIFIED_NAME:
				unsetQualifiedName();
				return;
			case ExportPackage.EXPORT__NAMING:
				setNaming((Expression)null);
				return;
			case ExportPackage.EXPORT__FRAGMENT_UNIQUE:
				unsetFragmentUnique();
				return;
			case ExportPackage.EXPORT__FRAGMENT_ATTRIBUTE:
				setFragmentAttribute((EAttribute)null);
				return;
			case ExportPackage.EXPORT__FINGERPRINT:
				unsetFingerprint();
				return;
			case ExportPackage.EXPORT__RESOURCE_FINGERPRINT:
				unsetResourceFingerprint();
				return;
			case ExportPackage.EXPORT__ATTRIBUTES:
				getAttributes().clear();
				return;
			case ExportPackage.EXPORT__USER_DATA:
				getUserData().clear();
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
			case ExportPackage.EXPORT__ALLOW_LOOKUP:
				return isSetAllowLookup();
			case ExportPackage.EXPORT__QUALIFIED_NAME:
				return isSetQualifiedName();
			case ExportPackage.EXPORT__NAMING:
				return naming != null;
			case ExportPackage.EXPORT__FRAGMENT_UNIQUE:
				return isSetFragmentUnique();
			case ExportPackage.EXPORT__FRAGMENT_ATTRIBUTE:
				return fragmentAttribute != null;
			case ExportPackage.EXPORT__FINGERPRINT:
				return isSetFingerprint();
			case ExportPackage.EXPORT__RESOURCE_FINGERPRINT:
				return isSetResourceFingerprint();
			case ExportPackage.EXPORT__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case ExportPackage.EXPORT__USER_DATA:
				return userData != null && !userData.isEmpty();
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
		result.append(" (allowLookup: ");
		if (allowLookupESet) result.append(allowLookup); else result.append("<unset>");
		result.append(", qualifiedName: ");
		if (qualifiedNameESet) result.append(qualifiedName); else result.append("<unset>");
		result.append(", fragmentUnique: ");
		if (fragmentUniqueESet) result.append(fragmentUnique); else result.append("<unset>");
		result.append(", fingerprint: ");
		if (fingerprintESet) result.append(fingerprint); else result.append("<unset>");
		result.append(", resourceFingerprint: ");
		if (resourceFingerprintESet) result.append(resourceFingerprint); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //ExportImpl
