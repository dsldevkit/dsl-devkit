/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Naming Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression#isExport <em>Export</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression#isFactory <em>Factory</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getNamingExpression()
 * @model
 * @generated
 */
public interface NamingExpression extends EObject
{
  /**
   * Returns the value of the '<em><b>Export</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Export</em>' attribute.
   * @see #setExport(boolean)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getNamingExpression_Export()
   * @model
   * @generated
   */
  boolean isExport();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression#isExport <em>Export</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Export</em>' attribute.
   * @see #isExport()
   * @generated
   */
  void setExport(boolean value);

  /**
   * Returns the value of the '<em><b>Factory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Factory</em>' attribute.
   * @see #setFactory(boolean)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getNamingExpression_Factory()
   * @model
   * @generated
   */
  boolean isFactory();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression#isFactory <em>Factory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Factory</em>' attribute.
   * @see #isFactory()
   * @generated
   */
  void setFactory(boolean value);

  /**
   * Returns the value of the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression</em>' containment reference.
   * @see #setExpression(Expression)
   * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage#getNamingExpression_Expression()
   * @model containment="true"
   * @generated
   */
  Expression getExpression();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression#getExpression <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' containment reference.
   * @see #getExpression()
   * @generated
   */
  void setExpression(Expression value);

} // NamingExpression
