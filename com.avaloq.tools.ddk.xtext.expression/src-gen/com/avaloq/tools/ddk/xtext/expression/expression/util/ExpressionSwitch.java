/**
 */
package com.avaloq.tools.ddk.xtext.expression.expression.util;

import com.avaloq.tools.ddk.xtext.expression.expression.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.xtext.expression.expression.ExpressionPackage
 * @generated
 */
public class ExpressionSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static ExpressionPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExpressionSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = ExpressionPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case ExpressionPackage.EXPRESSION:
      {
        Expression expression = (Expression)theEObject;
        T result = caseExpression(expression);
        if (result == null) result = caseSyntaxElement(expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.SYNTAX_ELEMENT:
      {
        SyntaxElement syntaxElement = (SyntaxElement)theEObject;
        T result = caseSyntaxElement(syntaxElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.LET_EXPRESSION:
      {
        LetExpression letExpression = (LetExpression)theEObject;
        T result = caseLetExpression(letExpression);
        if (result == null) result = caseExpression(letExpression);
        if (result == null) result = caseSyntaxElement(letExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.CASTED_EXPRESSION:
      {
        CastedExpression castedExpression = (CastedExpression)theEObject;
        T result = caseCastedExpression(castedExpression);
        if (result == null) result = caseExpression(castedExpression);
        if (result == null) result = caseSyntaxElement(castedExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.IF_EXPRESSION:
      {
        IfExpression ifExpression = (IfExpression)theEObject;
        T result = caseIfExpression(ifExpression);
        if (result == null) result = caseExpression(ifExpression);
        if (result == null) result = caseSyntaxElement(ifExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.SWITCH_EXPRESSION:
      {
        SwitchExpression switchExpression = (SwitchExpression)theEObject;
        T result = caseSwitchExpression(switchExpression);
        if (result == null) result = caseExpression(switchExpression);
        if (result == null) result = caseSyntaxElement(switchExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.CASE:
      {
        Case case_ = (Case)theEObject;
        T result = caseCase(case_);
        if (result == null) result = caseSyntaxElement(case_);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.OPERATION_CALL:
      {
        OperationCall operationCall = (OperationCall)theEObject;
        T result = caseOperationCall(operationCall);
        if (result == null) result = caseFeatureCall(operationCall);
        if (result == null) result = caseExpression(operationCall);
        if (result == null) result = caseSyntaxElement(operationCall);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.LITERAL:
      {
        Literal literal = (Literal)theEObject;
        T result = caseLiteral(literal);
        if (result == null) result = caseExpression(literal);
        if (result == null) result = caseSyntaxElement(literal);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.BOOLEAN_LITERAL:
      {
        BooleanLiteral booleanLiteral = (BooleanLiteral)theEObject;
        T result = caseBooleanLiteral(booleanLiteral);
        if (result == null) result = caseLiteral(booleanLiteral);
        if (result == null) result = caseExpression(booleanLiteral);
        if (result == null) result = caseSyntaxElement(booleanLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.INTEGER_LITERAL:
      {
        IntegerLiteral integerLiteral = (IntegerLiteral)theEObject;
        T result = caseIntegerLiteral(integerLiteral);
        if (result == null) result = caseLiteral(integerLiteral);
        if (result == null) result = caseExpression(integerLiteral);
        if (result == null) result = caseSyntaxElement(integerLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.NULL_LITERAL:
      {
        NullLiteral nullLiteral = (NullLiteral)theEObject;
        T result = caseNullLiteral(nullLiteral);
        if (result == null) result = caseLiteral(nullLiteral);
        if (result == null) result = caseExpression(nullLiteral);
        if (result == null) result = caseSyntaxElement(nullLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.REAL_LITERAL:
      {
        RealLiteral realLiteral = (RealLiteral)theEObject;
        T result = caseRealLiteral(realLiteral);
        if (result == null) result = caseLiteral(realLiteral);
        if (result == null) result = caseExpression(realLiteral);
        if (result == null) result = caseSyntaxElement(realLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.STRING_LITERAL:
      {
        StringLiteral stringLiteral = (StringLiteral)theEObject;
        T result = caseStringLiteral(stringLiteral);
        if (result == null) result = caseLiteral(stringLiteral);
        if (result == null) result = caseExpression(stringLiteral);
        if (result == null) result = caseSyntaxElement(stringLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.GLOBAL_VAR_EXPRESSION:
      {
        GlobalVarExpression globalVarExpression = (GlobalVarExpression)theEObject;
        T result = caseGlobalVarExpression(globalVarExpression);
        if (result == null) result = caseExpression(globalVarExpression);
        if (result == null) result = caseSyntaxElement(globalVarExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.FEATURE_CALL:
      {
        FeatureCall featureCall = (FeatureCall)theEObject;
        T result = caseFeatureCall(featureCall);
        if (result == null) result = caseExpression(featureCall);
        if (result == null) result = caseSyntaxElement(featureCall);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.LIST_LITERAL:
      {
        ListLiteral listLiteral = (ListLiteral)theEObject;
        T result = caseListLiteral(listLiteral);
        if (result == null) result = caseExpression(listLiteral);
        if (result == null) result = caseSyntaxElement(listLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.CONSTRUCTOR_CALL_EXPRESSION:
      {
        ConstructorCallExpression constructorCallExpression = (ConstructorCallExpression)theEObject;
        T result = caseConstructorCallExpression(constructorCallExpression);
        if (result == null) result = caseExpression(constructorCallExpression);
        if (result == null) result = caseSyntaxElement(constructorCallExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.TYPE_SELECT_EXPRESSION:
      {
        TypeSelectExpression typeSelectExpression = (TypeSelectExpression)theEObject;
        T result = caseTypeSelectExpression(typeSelectExpression);
        if (result == null) result = caseFeatureCall(typeSelectExpression);
        if (result == null) result = caseExpression(typeSelectExpression);
        if (result == null) result = caseSyntaxElement(typeSelectExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.COLLECTION_EXPRESSION:
      {
        CollectionExpression collectionExpression = (CollectionExpression)theEObject;
        T result = caseCollectionExpression(collectionExpression);
        if (result == null) result = caseFeatureCall(collectionExpression);
        if (result == null) result = caseExpression(collectionExpression);
        if (result == null) result = caseSyntaxElement(collectionExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.IDENTIFIER:
      {
        Identifier identifier = (Identifier)theEObject;
        T result = caseIdentifier(identifier);
        if (result == null) result = caseSyntaxElement(identifier);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.CHAIN_EXPRESSION:
      {
        ChainExpression chainExpression = (ChainExpression)theEObject;
        T result = caseChainExpression(chainExpression);
        if (result == null) result = caseExpression(chainExpression);
        if (result == null) result = caseSyntaxElement(chainExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ExpressionPackage.BOOLEAN_OPERATION:
      {
        BooleanOperation booleanOperation = (BooleanOperation)theEObject;
        T result = caseBooleanOperation(booleanOperation);
        if (result == null) result = caseExpression(booleanOperation);
        if (result == null) result = caseSyntaxElement(booleanOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExpression(Expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Syntax Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Syntax Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSyntaxElement(SyntaxElement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Let Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Let Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLetExpression(LetExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Casted Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Casted Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCastedExpression(CastedExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>If Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>If Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIfExpression(IfExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Switch Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Switch Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSwitchExpression(SwitchExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Case</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Case</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCase(Case object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operation Call</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Call</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOperationCall(OperationCall object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLiteral(Literal object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Boolean Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Boolean Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBooleanLiteral(BooleanLiteral object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Integer Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Integer Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIntegerLiteral(IntegerLiteral object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Null Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Null Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNullLiteral(NullLiteral object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Real Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Real Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRealLiteral(RealLiteral object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>String Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStringLiteral(StringLiteral object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Global Var Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Global Var Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGlobalVarExpression(GlobalVarExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Call</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Call</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureCall(FeatureCall object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>List Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>List Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseListLiteral(ListLiteral object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Constructor Call Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Constructor Call Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConstructorCallExpression(ConstructorCallExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Type Select Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Type Select Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTypeSelectExpression(TypeSelectExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Collection Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Collection Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCollectionExpression(CollectionExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Identifier</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Identifier</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIdentifier(Identifier object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Chain Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Chain Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseChainExpression(ChainExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Boolean Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Boolean Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBooleanOperation(BooleanOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //ExpressionSwitch
