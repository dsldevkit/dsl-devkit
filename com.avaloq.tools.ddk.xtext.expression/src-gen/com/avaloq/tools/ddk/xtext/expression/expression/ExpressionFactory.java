/**
 */
package com.avaloq.tools.ddk.xtext.expression.expression;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage
 * @generated
 */
public interface ExpressionFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ExpressionFactory eINSTANCE = com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Expression</em>'.
   * @generated
   */
  Expression createExpression();

  /**
   * Returns a new object of class '<em>Syntax Element</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Syntax Element</em>'.
   * @generated
   */
  SyntaxElement createSyntaxElement();

  /**
   * Returns a new object of class '<em>Let Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Let Expression</em>'.
   * @generated
   */
  LetExpression createLetExpression();

  /**
   * Returns a new object of class '<em>Casted Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Casted Expression</em>'.
   * @generated
   */
  CastedExpression createCastedExpression();

  /**
   * Returns a new object of class '<em>If Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>If Expression</em>'.
   * @generated
   */
  IfExpression createIfExpression();

  /**
   * Returns a new object of class '<em>Switch Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Switch Expression</em>'.
   * @generated
   */
  SwitchExpression createSwitchExpression();

  /**
   * Returns a new object of class '<em>Case</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Case</em>'.
   * @generated
   */
  Case createCase();

  /**
   * Returns a new object of class '<em>Operation Call</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operation Call</em>'.
   * @generated
   */
  OperationCall createOperationCall();

  /**
   * Returns a new object of class '<em>Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Literal</em>'.
   * @generated
   */
  Literal createLiteral();

  /**
   * Returns a new object of class '<em>Boolean Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Boolean Literal</em>'.
   * @generated
   */
  BooleanLiteral createBooleanLiteral();

  /**
   * Returns a new object of class '<em>Integer Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Integer Literal</em>'.
   * @generated
   */
  IntegerLiteral createIntegerLiteral();

  /**
   * Returns a new object of class '<em>Null Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Null Literal</em>'.
   * @generated
   */
  NullLiteral createNullLiteral();

  /**
   * Returns a new object of class '<em>Real Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Real Literal</em>'.
   * @generated
   */
  RealLiteral createRealLiteral();

  /**
   * Returns a new object of class '<em>String Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>String Literal</em>'.
   * @generated
   */
  StringLiteral createStringLiteral();

  /**
   * Returns a new object of class '<em>Global Var Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Global Var Expression</em>'.
   * @generated
   */
  GlobalVarExpression createGlobalVarExpression();

  /**
   * Returns a new object of class '<em>Feature Call</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature Call</em>'.
   * @generated
   */
  FeatureCall createFeatureCall();

  /**
   * Returns a new object of class '<em>List Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>List Literal</em>'.
   * @generated
   */
  ListLiteral createListLiteral();

  /**
   * Returns a new object of class '<em>Constructor Call Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Constructor Call Expression</em>'.
   * @generated
   */
  ConstructorCallExpression createConstructorCallExpression();

  /**
   * Returns a new object of class '<em>Type Select Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Type Select Expression</em>'.
   * @generated
   */
  TypeSelectExpression createTypeSelectExpression();

  /**
   * Returns a new object of class '<em>Collection Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Collection Expression</em>'.
   * @generated
   */
  CollectionExpression createCollectionExpression();

  /**
   * Returns a new object of class '<em>Identifier</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Identifier</em>'.
   * @generated
   */
  Identifier createIdentifier();

  /**
   * Returns a new object of class '<em>Chain Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Chain Expression</em>'.
   * @generated
   */
  ChainExpression createChainExpression();

  /**
   * Returns a new object of class '<em>Boolean Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Boolean Operation</em>'.
   * @generated
   */
  BooleanOperation createBooleanOperation();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  ExpressionPackage getExpressionPackage();

} //ExpressionFactory
