/**
 */
package com.avaloq.tools.ddk.xtext.export.export.impl;

import com.avaloq.tools.ddk.xtext.export.export.Export;
import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.ExportPackage;
import com.avaloq.tools.ddk.xtext.export.export.Extension;
import com.avaloq.tools.ddk.xtext.export.export.Import;
import com.avaloq.tools.ddk.xtext.export.export.Interface;

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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportModelImpl#isExtension <em>Extension</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportModelImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportModelImpl#getTargetGrammar <em>Target Grammar</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportModelImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportModelImpl#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportModelImpl#getInterfaces <em>Interfaces</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportModelImpl#getExports <em>Exports</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExportModelImpl extends MinimalEObjectImpl.Container implements ExportModel
{
	/**
	 * The default value of the '{@link #isExtension() <em>Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExtension()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXTENSION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExtension() <em>Extension</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExtension()
	 * @generated
	 * @ordered
	 */
	protected boolean extension = EXTENSION_EDEFAULT;

	/**
	 * This is true if the Extension attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean extensionESet;

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
	 * The cached value of the '{@link #getTargetGrammar() <em>Target Grammar</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetGrammar()
	 * @generated
	 * @ordered
	 */
	protected Grammar targetGrammar;

	/**
	 * The cached value of the '{@link #getImports() <em>Imports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImports()
	 * @generated
	 * @ordered
	 */
	protected EList<Import> imports;

	/**
	 * The cached value of the '{@link #getExtensions() <em>Extensions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtensions()
	 * @generated
	 * @ordered
	 */
	protected EList<Extension> extensions;

	/**
	 * The cached value of the '{@link #getInterfaces() <em>Interfaces</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList<Interface> interfaces;

	/**
	 * The cached value of the '{@link #getExports() <em>Exports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExports()
	 * @generated
	 * @ordered
	 */
	protected EList<Export> exports;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExportModelImpl()
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
		return ExportPackage.Literals.EXPORT_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExtension()
	{
		return extension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtension(boolean newExtension)
	{
		boolean oldExtension = extension;
		extension = newExtension;
		boolean oldExtensionESet = extensionESet;
		extensionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExportPackage.EXPORT_MODEL__EXTENSION, oldExtension, extension, !oldExtensionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetExtension()
	{
		boolean oldExtension = extension;
		boolean oldExtensionESet = extensionESet;
		extension = EXTENSION_EDEFAULT;
		extensionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ExportPackage.EXPORT_MODEL__EXTENSION, oldExtension, EXTENSION_EDEFAULT, oldExtensionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetExtension()
	{
		return extensionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName)
	{
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExportPackage.EXPORT_MODEL__NAME, oldName, name));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExportPackage.EXPORT_MODEL__TARGET_GRAMMAR, oldTargetGrammar, targetGrammar));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ExportPackage.EXPORT_MODEL__TARGET_GRAMMAR, oldTargetGrammar, targetGrammar));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Import> getImports()
	{
		if (imports == null)
		{
			imports = new EObjectContainmentEList<Import>(Import.class, this, ExportPackage.EXPORT_MODEL__IMPORTS);
		}
		return imports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Extension> getExtensions()
	{
		if (extensions == null)
		{
			extensions = new EObjectContainmentEList<Extension>(Extension.class, this, ExportPackage.EXPORT_MODEL__EXTENSIONS);
		}
		return extensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Interface> getInterfaces()
	{
		if (interfaces == null)
		{
			interfaces = new EObjectContainmentEList<Interface>(Interface.class, this, ExportPackage.EXPORT_MODEL__INTERFACES);
		}
		return interfaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Export> getExports()
	{
		if (exports == null)
		{
			exports = new EObjectContainmentEList<Export>(Export.class, this, ExportPackage.EXPORT_MODEL__EXPORTS);
		}
		return exports;
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
			case ExportPackage.EXPORT_MODEL__IMPORTS:
				return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
			case ExportPackage.EXPORT_MODEL__EXTENSIONS:
				return ((InternalEList<?>)getExtensions()).basicRemove(otherEnd, msgs);
			case ExportPackage.EXPORT_MODEL__INTERFACES:
				return ((InternalEList<?>)getInterfaces()).basicRemove(otherEnd, msgs);
			case ExportPackage.EXPORT_MODEL__EXPORTS:
				return ((InternalEList<?>)getExports()).basicRemove(otherEnd, msgs);
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
			case ExportPackage.EXPORT_MODEL__EXTENSION:
				return isExtension();
			case ExportPackage.EXPORT_MODEL__NAME:
				return getName();
			case ExportPackage.EXPORT_MODEL__TARGET_GRAMMAR:
				if (resolve) return getTargetGrammar();
				return basicGetTargetGrammar();
			case ExportPackage.EXPORT_MODEL__IMPORTS:
				return getImports();
			case ExportPackage.EXPORT_MODEL__EXTENSIONS:
				return getExtensions();
			case ExportPackage.EXPORT_MODEL__INTERFACES:
				return getInterfaces();
			case ExportPackage.EXPORT_MODEL__EXPORTS:
				return getExports();
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
			case ExportPackage.EXPORT_MODEL__EXTENSION:
				setExtension((Boolean)newValue);
				return;
			case ExportPackage.EXPORT_MODEL__NAME:
				setName((String)newValue);
				return;
			case ExportPackage.EXPORT_MODEL__TARGET_GRAMMAR:
				setTargetGrammar((Grammar)newValue);
				return;
			case ExportPackage.EXPORT_MODEL__IMPORTS:
				getImports().clear();
				getImports().addAll((Collection<? extends Import>)newValue);
				return;
			case ExportPackage.EXPORT_MODEL__EXTENSIONS:
				getExtensions().clear();
				getExtensions().addAll((Collection<? extends Extension>)newValue);
				return;
			case ExportPackage.EXPORT_MODEL__INTERFACES:
				getInterfaces().clear();
				getInterfaces().addAll((Collection<? extends Interface>)newValue);
				return;
			case ExportPackage.EXPORT_MODEL__EXPORTS:
				getExports().clear();
				getExports().addAll((Collection<? extends Export>)newValue);
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
			case ExportPackage.EXPORT_MODEL__EXTENSION:
				unsetExtension();
				return;
			case ExportPackage.EXPORT_MODEL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ExportPackage.EXPORT_MODEL__TARGET_GRAMMAR:
				setTargetGrammar((Grammar)null);
				return;
			case ExportPackage.EXPORT_MODEL__IMPORTS:
				getImports().clear();
				return;
			case ExportPackage.EXPORT_MODEL__EXTENSIONS:
				getExtensions().clear();
				return;
			case ExportPackage.EXPORT_MODEL__INTERFACES:
				getInterfaces().clear();
				return;
			case ExportPackage.EXPORT_MODEL__EXPORTS:
				getExports().clear();
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
			case ExportPackage.EXPORT_MODEL__EXTENSION:
				return isSetExtension();
			case ExportPackage.EXPORT_MODEL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ExportPackage.EXPORT_MODEL__TARGET_GRAMMAR:
				return targetGrammar != null;
			case ExportPackage.EXPORT_MODEL__IMPORTS:
				return imports != null && !imports.isEmpty();
			case ExportPackage.EXPORT_MODEL__EXTENSIONS:
				return extensions != null && !extensions.isEmpty();
			case ExportPackage.EXPORT_MODEL__INTERFACES:
				return interfaces != null && !interfaces.isEmpty();
			case ExportPackage.EXPORT_MODEL__EXPORTS:
				return exports != null && !exports.isEmpty();
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
		result.append(" (extension: ");
		if (extensionESet) result.append(extension); else result.append("<unset>");
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ExportModelImpl
