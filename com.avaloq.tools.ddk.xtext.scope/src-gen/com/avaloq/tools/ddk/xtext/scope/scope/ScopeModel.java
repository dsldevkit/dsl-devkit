/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getIncludedScopes <em>Included Scopes</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getImports <em>Imports</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getInjections <em>Injections</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getNaming <em>Naming</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getScopes <em>Scopes</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeModel()
 * @model
 * @generated
 */
public interface ScopeModel extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeModel_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Included Scopes</b></em>' reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Included Scopes</em>' reference list.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeModel_IncludedScopes()
   * @model
   * @generated
   */
  EList<ScopeModel> getIncludedScopes();

  /**
   * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.scope.scope.Import}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Imports</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeModel_Imports()
   * @model containment="true"
   * @generated
   */
  EList<Import> getImports();

  /**
   * Returns the value of the '<em><b>Extensions</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.scope.scope.Extension}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Extensions</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeModel_Extensions()
   * @model containment="true"
   * @generated
   */
  EList<Extension> getExtensions();

  /**
   * Returns the value of the '<em><b>Injections</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.scope.scope.Injection}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Injections</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeModel_Injections()
   * @model containment="true"
   * @generated
   */
  EList<Injection> getInjections();

  /**
   * Returns the value of the '<em><b>Naming</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Naming</em>' containment reference.
   * @see #setNaming(NamingSection)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeModel_Naming()
   * @model containment="true"
   * @generated
   */
  NamingSection getNaming();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel#getNaming <em>Naming</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Naming</em>' containment reference.
   * @see #getNaming()
   * @generated
   */
  void setNaming(NamingSection value);

  /**
   * Returns the value of the '<em><b>Scopes</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scopes</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getScopeModel_Scopes()
   * @model containment="true"
   * @generated
   */
  EList<ScopeDefinition> getScopes();

} // ScopeModel
