/**
 */
package com.avaloq.tools.ddk.xtext.format.format;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Keyword Pair</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.KeywordPair#getLeft <em>Left</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.KeywordPair#getRight <em>Right</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.KeywordPair#getLeftMatchers <em>Left Matchers</em>}</li>
 *   <li>{@link com.avaloq.tools.ddk.xtext.format.format.KeywordPair#getRightMatchers <em>Right Matchers</em>}</li>
 * </ul>
 *
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getKeywordPair()
 * @model
 * @generated
 */
public interface KeywordPair extends GrammarRuleDirective, WildcardRuleDirective
{
  /**
   * Returns the value of the '<em><b>Left</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Left</em>' attribute.
   * @see #setLeft(String)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getKeywordPair_Left()
   * @model
   * @generated
   */
  String getLeft();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.KeywordPair#getLeft <em>Left</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Left</em>' attribute.
   * @see #getLeft()
   * @generated
   */
  void setLeft(String value);

  /**
   * Returns the value of the '<em><b>Right</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Right</em>' attribute.
   * @see #setRight(String)
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getKeywordPair_Right()
   * @model
   * @generated
   */
  String getRight();

  /**
   * Sets the value of the '{@link com.avaloq.tools.ddk.xtext.format.format.KeywordPair#getRight <em>Right</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Right</em>' attribute.
   * @see #getRight()
   * @generated
   */
  void setRight(String value);

  /**
   * Returns the value of the '<em><b>Left Matchers</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.format.format.Matcher}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Left Matchers</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getKeywordPair_LeftMatchers()
   * @model containment="true"
   * @generated
   */
  EList<Matcher> getLeftMatchers();

  /**
   * Returns the value of the '<em><b>Right Matchers</b></em>' containment reference list.
   * The list contents are of type {@link com.avaloq.tools.ddk.xtext.format.format.Matcher}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Right Matchers</em>' containment reference list.
   * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getKeywordPair_RightMatchers()
   * @model containment="true"
   * @generated
   */
  EList<Matcher> getRightMatchers();

} // KeywordPair
