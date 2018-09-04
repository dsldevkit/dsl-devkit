/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unique Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.valid.valid.UniqueRule#getContexts <em>Contexts</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getUniqueRule()
 * @model
 * @generated
 */
public interface UniqueRule extends PredefinedRule
{
  /**
   * Returns the value of the '<em><b>Contexts</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.valid.valid.DuplicateContext}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Contexts</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Contexts</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getUniqueRule_Contexts()
   * @model containment="true"
   * @generated
   */
  EList<DuplicateContext> getContexts();

} // UniqueRule
