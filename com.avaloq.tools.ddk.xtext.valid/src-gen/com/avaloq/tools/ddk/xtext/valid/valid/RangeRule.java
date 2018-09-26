/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Range Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.RangeRule#getMin <em>Min</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.RangeRule#getMax <em>Max</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.RangeRule#getContexts <em>Contexts</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getRangeRule()
 * @model
 * @generated
 */
public interface RangeRule extends PredefinedRule
{
  /**
   * Returns the value of the '<em><b>Min</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Min</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Min</em>' attribute.
   * @see #setMin(int)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getRangeRule_Min()
   * @model
   * @generated
   */
  int getMin();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.RangeRule#getMin <em>Min</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Min</em>' attribute.
   * @see #getMin()
   * @generated
   */
  void setMin(int value);

  /**
   * Returns the value of the '<em><b>Max</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Max</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Max</em>' attribute.
   * @see #setMax(int)
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getRangeRule_Max()
   * @model
   * @generated
   */
  int getMax();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.valid.valid.RangeRule#getMax <em>Max</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Max</em>' attribute.
   * @see #getMax()
   * @generated
   */
  void setMax(int value);

  /**
   * Returns the value of the '<em><b>Contexts</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.valid.valid.SimpleContext}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Contexts</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Contexts</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getRangeRule_Contexts()
   * @model containment="true"
   * @generated
   */
  EList<SimpleContext> getContexts();

} // RangeRule
