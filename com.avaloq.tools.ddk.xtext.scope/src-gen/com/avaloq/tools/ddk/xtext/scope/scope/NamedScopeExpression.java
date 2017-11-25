/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named Scope Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression#isCaseDef <em>Case Def</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression#getCasing <em>Casing</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression#getNaming <em>Naming</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getNamedScopeExpression()
 * @model
 * @generated
 */
public interface NamedScopeExpression extends ScopeExpression
{
  /**
   * Returns the value of the '<em><b>Case Def</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Case Def</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Case Def</em>' attribute.
   * @see #setCaseDef(boolean)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getNamedScopeExpression_CaseDef()
   * @model
   * @generated
   */
  boolean isCaseDef();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression#isCaseDef <em>Case Def</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Case Def</em>' attribute.
   * @see #isCaseDef()
   * @generated
   */
  void setCaseDef(boolean value);

  /**
   * Returns the value of the '<em><b>Casing</b></em>' attribute.
   * The literals are from the enumeration {@link com.avaloq.tools.ddk.xtext.scope.scope.Casing}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Casing</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Casing</em>' attribute.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Casing
   * @see #setCasing(Casing)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getNamedScopeExpression_Casing()
   * @model
   * @generated
   */
  Casing getCasing();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression#getCasing <em>Casing</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Casing</em>' attribute.
   * @see com.avaloq.tools.ddk.xtext.scope.scope.Casing
   * @see #getCasing()
   * @generated
   */
  void setCasing(Casing value);

  /**
   * Returns the value of the '<em><b>Naming</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Naming</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Naming</em>' containment reference.
   * @see #setNaming(Naming)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getNamedScopeExpression_Naming()
   * @model containment="true"
   * @generated
   */
  Naming getNaming();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression#getNaming <em>Naming</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Naming</em>' containment reference.
   * @see #getNaming()
   * @generated
   */
  void setNaming(Naming value);

} // NamedScopeExpression
