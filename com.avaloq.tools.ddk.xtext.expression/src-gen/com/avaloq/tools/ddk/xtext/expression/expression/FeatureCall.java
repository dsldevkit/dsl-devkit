/**
 */
package com.avaloq.tools.ddk.xtext.expression.expression;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall#getTarget <em>Target</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall#getType <em>Type</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getFeatureCall()
 * @model
 * @generated
 */
public interface FeatureCall extends Expression
{
  /**
   * Returns the value of the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' containment reference.
   * @see #setTarget(Expression)
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getFeatureCall_Target()
   * @model containment="true"
   * @generated
   */
  Expression getTarget();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall#getTarget <em>Target</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' containment reference.
   * @see #getTarget()
   * @generated
   */
  void setTarget(Expression value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference.
   * @see #setType(Identifier)
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getFeatureCall_Type()
   * @model containment="true"
   * @generated
   */
  Identifier getType();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(Identifier value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getFeatureCall_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

} // FeatureCall
