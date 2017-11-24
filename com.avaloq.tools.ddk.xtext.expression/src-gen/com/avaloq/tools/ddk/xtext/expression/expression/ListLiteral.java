/**
 */
package com.avaloq.tools.ddk.xtext.expression.expression;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.expression.expression.ListLiteral#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getListLiteral()
 * @model
 * @generated
 */
public interface ListLiteral extends Expression
{
  /**
   * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.expression.expression.Expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Elements</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage#getListLiteral_Elements()
   * @model containment="true"
   * @generated
   */
  EList<Expression> getElements();

} // ListLiteral
