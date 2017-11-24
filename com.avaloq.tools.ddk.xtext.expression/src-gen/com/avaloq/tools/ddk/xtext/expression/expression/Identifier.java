/**
 */
package com.avaloq.tools.ddk.xtext.expression.expression;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Identifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.Identifier#getCl <em>Cl</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.Identifier#getId1 <em>Id1</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.Identifier#getId <em>Id</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getIdentifier()
 * @model
 * @generated
 */
public interface Identifier extends SyntaxElement
{
  /**
   * Returns the value of the '<em><b>Cl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cl</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cl</em>' attribute.
   * @see #setCl(String)
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getIdentifier_Cl()
   * @model
   * @generated
   */
  String getCl();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.Identifier#getCl <em>Cl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cl</em>' attribute.
   * @see #getCl()
   * @generated
   */
  void setCl(String value);

  /**
   * Returns the value of the '<em><b>Id1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id1</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id1</em>' containment reference.
   * @see #setId1(Identifier)
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getIdentifier_Id1()
   * @model containment="true"
   * @generated
   */
  Identifier getId1();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.expression.expression.Identifier#getId1 <em>Id1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id1</em>' containment reference.
   * @see #getId1()
   * @generated
   */
  void setId1(Identifier value);

  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute list.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getIdentifier_Id()
   * @model unique="false"
   * @generated
   */
  EList<String> getId();

} // Identifier
