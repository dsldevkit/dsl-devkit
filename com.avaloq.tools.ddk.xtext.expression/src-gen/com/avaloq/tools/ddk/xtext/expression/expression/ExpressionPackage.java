/**
 */
package com.avaloq.tools.ddk.xtext.expression.expression;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionFactory
 * @model kind="package"
 * @generated
 */
public interface ExpressionPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "expression";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.avaloq.com/tools/ddk/xtext/expression/Expression";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "expression";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ExpressionPackage eINSTANCE = com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl.init();

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.SyntaxElementImpl <em>Syntax Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.SyntaxElementImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getSyntaxElement()
   * @generated
   */
  int SYNTAX_ELEMENT = 1;

  /**
   * The number of structural features of the '<em>Syntax Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYNTAX_ELEMENT_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionImpl <em>Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getExpression()
   * @generated
   */
  int EXPRESSION = 0;

  /**
   * The number of structural features of the '<em>Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_FEATURE_COUNT = SYNTAX_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.LetExpressionImpl <em>Let Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.LetExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getLetExpression()
   * @generated
   */
  int LET_EXPRESSION = 2;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LET_EXPRESSION__IDENTIFIER = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Var Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LET_EXPRESSION__VAR_EXPR = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LET_EXPRESSION__TARGET = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Let Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LET_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.CastedExpressionImpl <em>Casted Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.CastedExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getCastedExpression()
   * @generated
   */
  int CASTED_EXPRESSION = 3;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CASTED_EXPRESSION__TYPE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CASTED_EXPRESSION__TARGET = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Casted Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CASTED_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.IfExpressionImpl <em>If Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.IfExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getIfExpression()
   * @generated
   */
  int IF_EXPRESSION = 4;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EXPRESSION__CONDITION = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Then Part</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EXPRESSION__THEN_PART = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Else Part</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EXPRESSION__ELSE_PART = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>If Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.SwitchExpressionImpl <em>Switch Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.SwitchExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getSwitchExpression()
   * @generated
   */
  int SWITCH_EXPRESSION = 5;

  /**
   * The feature id for the '<em><b>Switch Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWITCH_EXPRESSION__SWITCH_EXPR = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Case</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWITCH_EXPRESSION__CASE = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Default Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWITCH_EXPRESSION__DEFAULT_EXPR = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Switch Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SWITCH_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.CaseImpl <em>Case</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.CaseImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getCase()
   * @generated
   */
  int CASE = 6;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CASE__CONDITION = SYNTAX_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Then Par</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CASE__THEN_PAR = SYNTAX_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Case</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CASE_FEATURE_COUNT = SYNTAX_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.OperationCallImpl <em>Operation Call</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.OperationCallImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getOperationCall()
   * @generated
   */
  int OPERATION_CALL = 7;

  /**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL__TARGET = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL__TYPE = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL__NAME = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL__PARAMS = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Operation Call</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.LiteralImpl <em>Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.LiteralImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getLiteral()
   * @generated
   */
  int LITERAL = 8;

  /**
   * The number of structural features of the '<em>Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.BooleanLiteralImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getBooleanLiteral()
   * @generated
   */
  int BOOLEAN_LITERAL = 9;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_LITERAL__VAL = LITERAL_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Boolean Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_LITERAL_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.IntegerLiteralImpl <em>Integer Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.IntegerLiteralImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getIntegerLiteral()
   * @generated
   */
  int INTEGER_LITERAL = 10;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_LITERAL__VAL = LITERAL_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Integer Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_LITERAL_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.NullLiteralImpl <em>Null Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.NullLiteralImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getNullLiteral()
   * @generated
   */
  int NULL_LITERAL = 11;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NULL_LITERAL__VAL = LITERAL_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Null Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NULL_LITERAL_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.RealLiteralImpl <em>Real Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.RealLiteralImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getRealLiteral()
   * @generated
   */
  int REAL_LITERAL = 12;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REAL_LITERAL__VAL = LITERAL_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Real Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REAL_LITERAL_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.StringLiteralImpl <em>String Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.StringLiteralImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getStringLiteral()
   * @generated
   */
  int STRING_LITERAL = 13;

  /**
   * The feature id for the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_LITERAL__VAL = LITERAL_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>String Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_LITERAL_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.GlobalVarExpressionImpl <em>Global Var Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.GlobalVarExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getGlobalVarExpression()
   * @generated
   */
  int GLOBAL_VAR_EXPRESSION = 14;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_VAR_EXPRESSION__NAME = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Global Var Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GLOBAL_VAR_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.FeatureCallImpl <em>Feature Call</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.FeatureCallImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getFeatureCall()
   * @generated
   */
  int FEATURE_CALL = 15;

  /**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_CALL__TARGET = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_CALL__TYPE = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_CALL__NAME = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Feature Call</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_CALL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.ListLiteralImpl <em>List Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ListLiteralImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getListLiteral()
   * @generated
   */
  int LIST_LITERAL = 16;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_LITERAL__ELEMENTS = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>List Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.ConstructorCallExpressionImpl <em>Constructor Call Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ConstructorCallExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getConstructorCallExpression()
   * @generated
   */
  int CONSTRUCTOR_CALL_EXPRESSION = 17;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRUCTOR_CALL_EXPRESSION__TYPE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Constructor Call Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRUCTOR_CALL_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.TypeSelectExpressionImpl <em>Type Select Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.TypeSelectExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getTypeSelectExpression()
   * @generated
   */
  int TYPE_SELECT_EXPRESSION = 18;

  /**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_SELECT_EXPRESSION__TARGET = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_SELECT_EXPRESSION__TYPE = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_SELECT_EXPRESSION__NAME = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Type Select Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_SELECT_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.CollectionExpressionImpl <em>Collection Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.CollectionExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getCollectionExpression()
   * @generated
   */
  int COLLECTION_EXPRESSION = 19;

  /**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_EXPRESSION__TARGET = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_EXPRESSION__TYPE = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_EXPRESSION__NAME = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_EXPRESSION__VAR = EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Exp</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_EXPRESSION__EXP = EXPRESSION_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Collection Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.IdentifierImpl <em>Identifier</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.IdentifierImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getIdentifier()
   * @generated
   */
  int IDENTIFIER = 20;

  /**
   * The feature id for the '<em><b>Cl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER__CL = SYNTAX_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Id1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER__ID1 = SYNTAX_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER__ID = SYNTAX_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Identifier</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER_FEATURE_COUNT = SYNTAX_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.ChainExpressionImpl <em>Chain Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ChainExpressionImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getChainExpression()
   * @generated
   */
  int CHAIN_EXPRESSION = 21;

  /**
   * The feature id for the '<em><b>First</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CHAIN_EXPRESSION__FIRST = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Next</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CHAIN_EXPRESSION__NEXT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Chain Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CHAIN_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.BooleanOperationImpl <em>Boolean Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.BooleanOperationImpl
   * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getBooleanOperation()
   * @generated
   */
  int BOOLEAN_OPERATION = 22;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__LEFT = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__OPERATOR = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION__RIGHT = EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Boolean Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_OPERATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;


  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.Expression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.Expression
   * @generated
   */
  EClass getExpression();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.SyntaxElement <em>Syntax Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Syntax Element</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.SyntaxElement
   * @generated
   */
  EClass getSyntaxElement();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.LetExpression <em>Let Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Let Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.LetExpression
   * @generated
   */
  EClass getLetExpression();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.expression.expression.LetExpression#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.LetExpression#getIdentifier()
   * @see #getLetExpression()
   * @generated
   */
  EAttribute getLetExpression_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.LetExpression#getVarExpr <em>Var Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var Expr</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.LetExpression#getVarExpr()
   * @see #getLetExpression()
   * @generated
   */
  EReference getLetExpression_VarExpr();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.LetExpression#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Target</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.LetExpression#getTarget()
   * @see #getLetExpression()
   * @generated
   */
  EReference getLetExpression_Target();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.CastedExpression <em>Casted Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Casted Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.CastedExpression
   * @generated
   */
  EClass getCastedExpression();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.CastedExpression#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.CastedExpression#getType()
   * @see #getCastedExpression()
   * @generated
   */
  EReference getCastedExpression_Type();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.CastedExpression#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Target</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.CastedExpression#getTarget()
   * @see #getCastedExpression()
   * @generated
   */
  EReference getCastedExpression_Target();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.IfExpression <em>If Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>If Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.IfExpression
   * @generated
   */
  EClass getIfExpression();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.IfExpression#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.IfExpression#getCondition()
   * @see #getIfExpression()
   * @generated
   */
  EReference getIfExpression_Condition();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.IfExpression#getThenPart <em>Then Part</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Then Part</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.IfExpression#getThenPart()
   * @see #getIfExpression()
   * @generated
   */
  EReference getIfExpression_ThenPart();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.IfExpression#getElsePart <em>Else Part</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Else Part</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.IfExpression#getElsePart()
   * @see #getIfExpression()
   * @generated
   */
  EReference getIfExpression_ElsePart();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.SwitchExpression <em>Switch Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Switch Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.SwitchExpression
   * @generated
   */
  EClass getSwitchExpression();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.SwitchExpression#getSwitchExpr <em>Switch Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Switch Expr</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.SwitchExpression#getSwitchExpr()
   * @see #getSwitchExpression()
   * @generated
   */
  EReference getSwitchExpression_SwitchExpr();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.expression.expression.SwitchExpression#getCase <em>Case</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Case</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.SwitchExpression#getCase()
   * @see #getSwitchExpression()
   * @generated
   */
  EReference getSwitchExpression_Case();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.SwitchExpression#getDefaultExpr <em>Default Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Default Expr</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.SwitchExpression#getDefaultExpr()
   * @see #getSwitchExpression()
   * @generated
   */
  EReference getSwitchExpression_DefaultExpr();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.Case <em>Case</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Case</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.Case
   * @generated
   */
  EClass getCase();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.Case#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.Case#getCondition()
   * @see #getCase()
   * @generated
   */
  EReference getCase_Condition();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.Case#getThenPar <em>Then Par</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Then Par</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.Case#getThenPar()
   * @see #getCase()
   * @generated
   */
  EReference getCase_ThenPar();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.OperationCall <em>Operation Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Call</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.OperationCall
   * @generated
   */
  EClass getOperationCall();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.expression.expression.OperationCall#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Params</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.OperationCall#getParams()
   * @see #getOperationCall()
   * @generated
   */
  EReference getOperationCall_Params();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.Literal <em>Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Literal</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.Literal
   * @generated
   */
  EClass getLiteral();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.BooleanLiteral <em>Boolean Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Boolean Literal</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.BooleanLiteral
   * @generated
   */
  EClass getBooleanLiteral();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.expression.expression.BooleanLiteral#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.BooleanLiteral#getVal()
   * @see #getBooleanLiteral()
   * @generated
   */
  EAttribute getBooleanLiteral_Val();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.IntegerLiteral <em>Integer Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Integer Literal</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.IntegerLiteral
   * @generated
   */
  EClass getIntegerLiteral();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.expression.expression.IntegerLiteral#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.IntegerLiteral#getVal()
   * @see #getIntegerLiteral()
   * @generated
   */
  EAttribute getIntegerLiteral_Val();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.NullLiteral <em>Null Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Null Literal</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.NullLiteral
   * @generated
   */
  EClass getNullLiteral();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.expression.expression.NullLiteral#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.NullLiteral#getVal()
   * @see #getNullLiteral()
   * @generated
   */
  EAttribute getNullLiteral_Val();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.RealLiteral <em>Real Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Real Literal</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.RealLiteral
   * @generated
   */
  EClass getRealLiteral();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.expression.expression.RealLiteral#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.RealLiteral#getVal()
   * @see #getRealLiteral()
   * @generated
   */
  EAttribute getRealLiteral_Val();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral <em>String Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>String Literal</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral
   * @generated
   */
  EClass getStringLiteral();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Val</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral#getVal()
   * @see #getStringLiteral()
   * @generated
   */
  EAttribute getStringLiteral_Val();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.GlobalVarExpression <em>Global Var Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Global Var Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.GlobalVarExpression
   * @generated
   */
  EClass getGlobalVarExpression();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.expression.expression.GlobalVarExpression#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.GlobalVarExpression#getName()
   * @see #getGlobalVarExpression()
   * @generated
   */
  EAttribute getGlobalVarExpression_Name();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall <em>Feature Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Call</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall
   * @generated
   */
  EClass getFeatureCall();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Target</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall#getTarget()
   * @see #getFeatureCall()
   * @generated
   */
  EReference getFeatureCall_Target();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall#getType()
   * @see #getFeatureCall()
   * @generated
   */
  EReference getFeatureCall_Type();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall#getName()
   * @see #getFeatureCall()
   * @generated
   */
  EAttribute getFeatureCall_Name();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.ListLiteral <em>List Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>List Literal</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ListLiteral
   * @generated
   */
  EClass getListLiteral();

  /**
   * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.expression.expression.ListLiteral#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elements</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ListLiteral#getElements()
   * @see #getListLiteral()
   * @generated
   */
  EReference getListLiteral_Elements();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.ConstructorCallExpression <em>Constructor Call Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Constructor Call Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ConstructorCallExpression
   * @generated
   */
  EClass getConstructorCallExpression();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.ConstructorCallExpression#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ConstructorCallExpression#getType()
   * @see #getConstructorCallExpression()
   * @generated
   */
  EReference getConstructorCallExpression_Type();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.TypeSelectExpression <em>Type Select Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type Select Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.TypeSelectExpression
   * @generated
   */
  EClass getTypeSelectExpression();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.CollectionExpression <em>Collection Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Collection Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.CollectionExpression
   * @generated
   */
  EClass getCollectionExpression();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.expression.expression.CollectionExpression#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Var</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.CollectionExpression#getVar()
   * @see #getCollectionExpression()
   * @generated
   */
  EAttribute getCollectionExpression_Var();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.CollectionExpression#getExp <em>Exp</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Exp</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.CollectionExpression#getExp()
   * @see #getCollectionExpression()
   * @generated
   */
  EReference getCollectionExpression_Exp();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.Identifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Identifier</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.Identifier
   * @generated
   */
  EClass getIdentifier();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.expression.expression.Identifier#getCl <em>Cl</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cl</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.Identifier#getCl()
   * @see #getIdentifier()
   * @generated
   */
  EAttribute getIdentifier_Cl();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.Identifier#getId1 <em>Id1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id1</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.Identifier#getId1()
   * @see #getIdentifier()
   * @generated
   */
  EReference getIdentifier_Id1();

  /**
   * Returns the meta object for the attribute list '{@link com.avaloq.tools.ddk.xtext.expression.expression.Identifier#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Id</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.Identifier#getId()
   * @see #getIdentifier()
   * @generated
   */
  EAttribute getIdentifier_Id();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.ChainExpression <em>Chain Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Chain Expression</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ChainExpression
   * @generated
   */
  EClass getChainExpression();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.ChainExpression#getFirst <em>First</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>First</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ChainExpression#getFirst()
   * @see #getChainExpression()
   * @generated
   */
  EReference getChainExpression_First();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.ChainExpression#getNext <em>Next</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Next</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.ChainExpression#getNext()
   * @see #getChainExpression()
   * @generated
   */
  EReference getChainExpression_Next();

  /**
   * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.expression.expression.BooleanOperation <em>Boolean Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Boolean Operation</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.BooleanOperation
   * @generated
   */
  EClass getBooleanOperation();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.BooleanOperation#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.BooleanOperation#getLeft()
   * @see #getBooleanOperation()
   * @generated
   */
  EReference getBooleanOperation_Left();

  /**
   * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.expression.expression.BooleanOperation#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Operator</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.BooleanOperation#getOperator()
   * @see #getBooleanOperation()
   * @generated
   */
  EAttribute getBooleanOperation_Operator();

  /**
   * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.expression.expression.BooleanOperation#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see com.avaloq.tools.ddk.xtext.expression.expression.BooleanOperation#getRight()
   * @see #getBooleanOperation()
   * @generated
   */
  EReference getBooleanOperation_Right();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ExpressionFactory getExpressionFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionImpl <em>Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getExpression()
     * @generated
     */
    EClass EXPRESSION = eINSTANCE.getExpression();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.SyntaxElementImpl <em>Syntax Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.SyntaxElementImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getSyntaxElement()
     * @generated
     */
    EClass SYNTAX_ELEMENT = eINSTANCE.getSyntaxElement();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.LetExpressionImpl <em>Let Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.LetExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getLetExpression()
     * @generated
     */
    EClass LET_EXPRESSION = eINSTANCE.getLetExpression();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LET_EXPRESSION__IDENTIFIER = eINSTANCE.getLetExpression_Identifier();

    /**
     * The meta object literal for the '<em><b>Var Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LET_EXPRESSION__VAR_EXPR = eINSTANCE.getLetExpression_VarExpr();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LET_EXPRESSION__TARGET = eINSTANCE.getLetExpression_Target();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.CastedExpressionImpl <em>Casted Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.CastedExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getCastedExpression()
     * @generated
     */
    EClass CASTED_EXPRESSION = eINSTANCE.getCastedExpression();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CASTED_EXPRESSION__TYPE = eINSTANCE.getCastedExpression_Type();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CASTED_EXPRESSION__TARGET = eINSTANCE.getCastedExpression_Target();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.IfExpressionImpl <em>If Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.IfExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getIfExpression()
     * @generated
     */
    EClass IF_EXPRESSION = eINSTANCE.getIfExpression();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_EXPRESSION__CONDITION = eINSTANCE.getIfExpression_Condition();

    /**
     * The meta object literal for the '<em><b>Then Part</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_EXPRESSION__THEN_PART = eINSTANCE.getIfExpression_ThenPart();

    /**
     * The meta object literal for the '<em><b>Else Part</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_EXPRESSION__ELSE_PART = eINSTANCE.getIfExpression_ElsePart();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.SwitchExpressionImpl <em>Switch Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.SwitchExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getSwitchExpression()
     * @generated
     */
    EClass SWITCH_EXPRESSION = eINSTANCE.getSwitchExpression();

    /**
     * The meta object literal for the '<em><b>Switch Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SWITCH_EXPRESSION__SWITCH_EXPR = eINSTANCE.getSwitchExpression_SwitchExpr();

    /**
     * The meta object literal for the '<em><b>Case</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SWITCH_EXPRESSION__CASE = eINSTANCE.getSwitchExpression_Case();

    /**
     * The meta object literal for the '<em><b>Default Expr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SWITCH_EXPRESSION__DEFAULT_EXPR = eINSTANCE.getSwitchExpression_DefaultExpr();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.CaseImpl <em>Case</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.CaseImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getCase()
     * @generated
     */
    EClass CASE = eINSTANCE.getCase();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CASE__CONDITION = eINSTANCE.getCase_Condition();

    /**
     * The meta object literal for the '<em><b>Then Par</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CASE__THEN_PAR = eINSTANCE.getCase_ThenPar();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.OperationCallImpl <em>Operation Call</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.OperationCallImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getOperationCall()
     * @generated
     */
    EClass OPERATION_CALL = eINSTANCE.getOperationCall();

    /**
     * The meta object literal for the '<em><b>Params</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OPERATION_CALL__PARAMS = eINSTANCE.getOperationCall_Params();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.LiteralImpl <em>Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.LiteralImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getLiteral()
     * @generated
     */
    EClass LITERAL = eINSTANCE.getLiteral();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.BooleanLiteralImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getBooleanLiteral()
     * @generated
     */
    EClass BOOLEAN_LITERAL = eINSTANCE.getBooleanLiteral();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BOOLEAN_LITERAL__VAL = eINSTANCE.getBooleanLiteral_Val();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.IntegerLiteralImpl <em>Integer Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.IntegerLiteralImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getIntegerLiteral()
     * @generated
     */
    EClass INTEGER_LITERAL = eINSTANCE.getIntegerLiteral();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INTEGER_LITERAL__VAL = eINSTANCE.getIntegerLiteral_Val();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.NullLiteralImpl <em>Null Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.NullLiteralImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getNullLiteral()
     * @generated
     */
    EClass NULL_LITERAL = eINSTANCE.getNullLiteral();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NULL_LITERAL__VAL = eINSTANCE.getNullLiteral_Val();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.RealLiteralImpl <em>Real Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.RealLiteralImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getRealLiteral()
     * @generated
     */
    EClass REAL_LITERAL = eINSTANCE.getRealLiteral();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REAL_LITERAL__VAL = eINSTANCE.getRealLiteral_Val();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.StringLiteralImpl <em>String Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.StringLiteralImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getStringLiteral()
     * @generated
     */
    EClass STRING_LITERAL = eINSTANCE.getStringLiteral();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute STRING_LITERAL__VAL = eINSTANCE.getStringLiteral_Val();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.GlobalVarExpressionImpl <em>Global Var Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.GlobalVarExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getGlobalVarExpression()
     * @generated
     */
    EClass GLOBAL_VAR_EXPRESSION = eINSTANCE.getGlobalVarExpression();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GLOBAL_VAR_EXPRESSION__NAME = eINSTANCE.getGlobalVarExpression_Name();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.FeatureCallImpl <em>Feature Call</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.FeatureCallImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getFeatureCall()
     * @generated
     */
    EClass FEATURE_CALL = eINSTANCE.getFeatureCall();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE_CALL__TARGET = eINSTANCE.getFeatureCall_Target();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE_CALL__TYPE = eINSTANCE.getFeatureCall_Type();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE_CALL__NAME = eINSTANCE.getFeatureCall_Name();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.ListLiteralImpl <em>List Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ListLiteralImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getListLiteral()
     * @generated
     */
    EClass LIST_LITERAL = eINSTANCE.getListLiteral();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIST_LITERAL__ELEMENTS = eINSTANCE.getListLiteral_Elements();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.ConstructorCallExpressionImpl <em>Constructor Call Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ConstructorCallExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getConstructorCallExpression()
     * @generated
     */
    EClass CONSTRUCTOR_CALL_EXPRESSION = eINSTANCE.getConstructorCallExpression();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONSTRUCTOR_CALL_EXPRESSION__TYPE = eINSTANCE.getConstructorCallExpression_Type();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.TypeSelectExpressionImpl <em>Type Select Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.TypeSelectExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getTypeSelectExpression()
     * @generated
     */
    EClass TYPE_SELECT_EXPRESSION = eINSTANCE.getTypeSelectExpression();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.CollectionExpressionImpl <em>Collection Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.CollectionExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getCollectionExpression()
     * @generated
     */
    EClass COLLECTION_EXPRESSION = eINSTANCE.getCollectionExpression();

    /**
     * The meta object literal for the '<em><b>Var</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COLLECTION_EXPRESSION__VAR = eINSTANCE.getCollectionExpression_Var();

    /**
     * The meta object literal for the '<em><b>Exp</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COLLECTION_EXPRESSION__EXP = eINSTANCE.getCollectionExpression_Exp();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.IdentifierImpl <em>Identifier</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.IdentifierImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getIdentifier()
     * @generated
     */
    EClass IDENTIFIER = eINSTANCE.getIdentifier();

    /**
     * The meta object literal for the '<em><b>Cl</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IDENTIFIER__CL = eINSTANCE.getIdentifier_Cl();

    /**
     * The meta object literal for the '<em><b>Id1</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IDENTIFIER__ID1 = eINSTANCE.getIdentifier_Id1();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IDENTIFIER__ID = eINSTANCE.getIdentifier_Id();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.ChainExpressionImpl <em>Chain Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ChainExpressionImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getChainExpression()
     * @generated
     */
    EClass CHAIN_EXPRESSION = eINSTANCE.getChainExpression();

    /**
     * The meta object literal for the '<em><b>First</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CHAIN_EXPRESSION__FIRST = eINSTANCE.getChainExpression_First();

    /**
     * The meta object literal for the '<em><b>Next</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CHAIN_EXPRESSION__NEXT = eINSTANCE.getChainExpression_Next();

    /**
     * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.expression.expression.impl.BooleanOperationImpl <em>Boolean Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.BooleanOperationImpl
     * @see com.avaloq.tools.ddk.xtext.expression.expression.impl.ExpressionPackageImpl#getBooleanOperation()
     * @generated
     */
    EClass BOOLEAN_OPERATION = eINSTANCE.getBooleanOperation();

    /**
     * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BOOLEAN_OPERATION__LEFT = eINSTANCE.getBooleanOperation_Left();

    /**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BOOLEAN_OPERATION__OPERATOR = eINSTANCE.getBooleanOperation_Operator();

    /**
     * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BOOLEAN_OPERATION__RIGHT = eINSTANCE.getBooleanOperation_Right();

  }

} //ExpressionPackage
