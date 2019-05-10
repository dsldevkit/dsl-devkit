/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope.impl;

import com.avaloq.tools.ddk.xtext.scope.scope.Extension;
import com.avaloq.tools.ddk.xtext.scope.scope.Import;
import com.avaloq.tools.ddk.xtext.scope.scope.Injection;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingSection;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeModelImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeModelImpl#getIncludedScopes <em>Included Scopes</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeModelImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeModelImpl#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeModelImpl#getInjections <em>Injections</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeModelImpl#getNaming <em>Naming</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.impl.ScopeModelImpl#getScopes <em>Scopes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScopeModelImpl extends MinimalEObjectImpl.Container implements ScopeModel
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
   * The cached value of the '{@link #getIncludedScopes() <em>Included Scopes</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIncludedScopes()
   * @generated
   * @ordered
   */
  protected EList<ScopeModel> includedScopes;

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
   * The cached value of the '{@link #getInjections() <em>Injections</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInjections()
   * @generated
   * @ordered
   */
  protected EList<Injection> injections;

  /**
   * The cached value of the '{@link #getNaming() <em>Naming</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNaming()
   * @generated
   * @ordered
   */
  protected NamingSection naming;

  /**
   * The cached value of the '{@link #getScopes() <em>Scopes</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScopes()
   * @generated
   * @ordered
   */
  protected EList<ScopeDefinition> scopes;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ScopeModelImpl()
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
    return ScopePackage.Literals.SCOPE_MODEL;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_MODEL__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ScopeModel> getIncludedScopes()
  {
    if (includedScopes == null)
    {
      includedScopes = new EObjectResolvingEList<ScopeModel>(ScopeModel.class, this, ScopePackage.SCOPE_MODEL__INCLUDED_SCOPES);
    }
    return includedScopes;
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
      imports = new EObjectContainmentEList<Import>(Import.class, this, ScopePackage.SCOPE_MODEL__IMPORTS);
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
      extensions = new EObjectContainmentEList<Extension>(Extension.class, this, ScopePackage.SCOPE_MODEL__EXTENSIONS);
    }
    return extensions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Injection> getInjections()
  {
    if (injections == null)
    {
      injections = new EObjectContainmentEList<Injection>(Injection.class, this, ScopePackage.SCOPE_MODEL__INJECTIONS);
    }
    return injections;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamingSection getNaming()
  {
    return naming;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetNaming(NamingSection newNaming, NotificationChain msgs)
  {
    NamingSection oldNaming = naming;
    naming = newNaming;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_MODEL__NAMING, oldNaming, newNaming);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNaming(NamingSection newNaming)
  {
    if (newNaming != naming)
    {
      NotificationChain msgs = null;
      if (naming != null)
        msgs = ((InternalEObject)naming).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScopePackage.SCOPE_MODEL__NAMING, null, msgs);
      if (newNaming != null)
        msgs = ((InternalEObject)newNaming).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScopePackage.SCOPE_MODEL__NAMING, null, msgs);
      msgs = basicSetNaming(newNaming, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ScopePackage.SCOPE_MODEL__NAMING, newNaming, newNaming));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ScopeDefinition> getScopes()
  {
    if (scopes == null)
    {
      scopes = new EObjectContainmentEList<ScopeDefinition>(ScopeDefinition.class, this, ScopePackage.SCOPE_MODEL__SCOPES);
    }
    return scopes;
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
      case ScopePackage.SCOPE_MODEL__IMPORTS:
        return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
      case ScopePackage.SCOPE_MODEL__EXTENSIONS:
        return ((InternalEList<?>)getExtensions()).basicRemove(otherEnd, msgs);
      case ScopePackage.SCOPE_MODEL__INJECTIONS:
        return ((InternalEList<?>)getInjections()).basicRemove(otherEnd, msgs);
      case ScopePackage.SCOPE_MODEL__NAMING:
        return basicSetNaming(null, msgs);
      case ScopePackage.SCOPE_MODEL__SCOPES:
        return ((InternalEList<?>)getScopes()).basicRemove(otherEnd, msgs);
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
      case ScopePackage.SCOPE_MODEL__NAME:
        return getName();
      case ScopePackage.SCOPE_MODEL__INCLUDED_SCOPES:
        return getIncludedScopes();
      case ScopePackage.SCOPE_MODEL__IMPORTS:
        return getImports();
      case ScopePackage.SCOPE_MODEL__EXTENSIONS:
        return getExtensions();
      case ScopePackage.SCOPE_MODEL__INJECTIONS:
        return getInjections();
      case ScopePackage.SCOPE_MODEL__NAMING:
        return getNaming();
      case ScopePackage.SCOPE_MODEL__SCOPES:
        return getScopes();
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
      case ScopePackage.SCOPE_MODEL__NAME:
        setName((String)newValue);
        return;
      case ScopePackage.SCOPE_MODEL__INCLUDED_SCOPES:
        getIncludedScopes().clear();
        getIncludedScopes().addAll((Collection<? extends ScopeModel>)newValue);
        return;
      case ScopePackage.SCOPE_MODEL__IMPORTS:
        getImports().clear();
        getImports().addAll((Collection<? extends Import>)newValue);
        return;
      case ScopePackage.SCOPE_MODEL__EXTENSIONS:
        getExtensions().clear();
        getExtensions().addAll((Collection<? extends Extension>)newValue);
        return;
      case ScopePackage.SCOPE_MODEL__INJECTIONS:
        getInjections().clear();
        getInjections().addAll((Collection<? extends Injection>)newValue);
        return;
      case ScopePackage.SCOPE_MODEL__NAMING:
        setNaming((NamingSection)newValue);
        return;
      case ScopePackage.SCOPE_MODEL__SCOPES:
        getScopes().clear();
        getScopes().addAll((Collection<? extends ScopeDefinition>)newValue);
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
      case ScopePackage.SCOPE_MODEL__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ScopePackage.SCOPE_MODEL__INCLUDED_SCOPES:
        getIncludedScopes().clear();
        return;
      case ScopePackage.SCOPE_MODEL__IMPORTS:
        getImports().clear();
        return;
      case ScopePackage.SCOPE_MODEL__EXTENSIONS:
        getExtensions().clear();
        return;
      case ScopePackage.SCOPE_MODEL__INJECTIONS:
        getInjections().clear();
        return;
      case ScopePackage.SCOPE_MODEL__NAMING:
        setNaming((NamingSection)null);
        return;
      case ScopePackage.SCOPE_MODEL__SCOPES:
        getScopes().clear();
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
      case ScopePackage.SCOPE_MODEL__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ScopePackage.SCOPE_MODEL__INCLUDED_SCOPES:
        return includedScopes != null && !includedScopes.isEmpty();
      case ScopePackage.SCOPE_MODEL__IMPORTS:
        return imports != null && !imports.isEmpty();
      case ScopePackage.SCOPE_MODEL__EXTENSIONS:
        return extensions != null && !extensions.isEmpty();
      case ScopePackage.SCOPE_MODEL__INJECTIONS:
        return injections != null && !injections.isEmpty();
      case ScopePackage.SCOPE_MODEL__NAMING:
        return naming != null;
      case ScopePackage.SCOPE_MODEL__SCOPES:
        return scopes != null && !scopes.isEmpty();
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

} //ScopeModelImpl
