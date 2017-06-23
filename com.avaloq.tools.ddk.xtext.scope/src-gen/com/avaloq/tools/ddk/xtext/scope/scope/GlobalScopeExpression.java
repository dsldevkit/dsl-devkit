/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Global Scope Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#getType <em>Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#getName <em>Name</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#getData <em>Data</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#getDomains <em>Domains</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getGlobalScopeExpression()
 * @model
 * @generated
 */
public interface GlobalScopeExpression extends NamedScopeExpression
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(EClass)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getGlobalScopeExpression_Type()
   * @model
   * @generated
   */
  EClass getType();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#getType <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */
  void setType(EClass value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' containment reference.
   * @see #setName(Expression)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getGlobalScopeExpression_Name()
   * @model containment="true"
   * @generated
   */
  Expression getName();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(Expression value);

  /**
   * Returns the value of the '<em><b>Prefix</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Prefix</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Prefix</em>' containment reference.
   * @see #setPrefix(Expression)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getGlobalScopeExpression_Prefix()
   * @model containment="true"
   * @generated
   */
  Expression getPrefix();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression#getPrefix <em>Prefix</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Prefix</em>' containment reference.
   * @see #getPrefix()
   * @generated
   */
  void setPrefix(Expression value);

  /**
   * Returns the value of the '<em><b>Data</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.scope.scope.DataExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Data</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getGlobalScopeExpression_Data()
   * @model containment="true"
   * @generated
   */
  EList<DataExpression> getData();

  /**
   * Returns the value of the '<em><b>Domains</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Domains</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Domains</em>' attribute list.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getGlobalScopeExpression_Domains()
   * @model unique="false"
   * @generated
   */
  EList<String> getDomains();

} // GlobalScopeExpression
