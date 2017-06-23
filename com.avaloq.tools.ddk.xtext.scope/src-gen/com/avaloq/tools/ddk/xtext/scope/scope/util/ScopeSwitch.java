/**
 */
package com.avaloq.tools.ddk.xtext.scope.scope.util;

import com.avaloq.tools.ddk.xtext.scope.scope.*;

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
 * @see com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage
 * @generated
 */
public class ScopeSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static ScopePackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ScopeSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = ScopePackage.eINSTANCE;
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
      case ScopePackage.SCOPE_MODEL:
      {
        ScopeModel scopeModel = (ScopeModel)theEObject;
        T result = caseScopeModel(scopeModel);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ScopePackage.IMPORT:
      {
        Import import_ = (Import)theEObject;
        T result = caseImport(import_);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ScopePackage.EXTENSION:
      {
        Extension extension = (Extension)theEObject;
        T result = caseExtension(extension);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ScopePackage.NAMING_SECTION:
      {
        NamingSection namingSection = (NamingSection)theEObject;
        T result = caseNamingSection(namingSection);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ScopePackage.NAMING_DEFINITION:
      {
        NamingDefinition namingDefinition = (NamingDefinition)theEObject;
        T result = caseNamingDefinition(namingDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ScopePackage.SCOPE_DEFINITION:
      {
        ScopeDefinition scopeDefinition = (ScopeDefinition)theEObject;
        T result = caseScopeDefinition(scopeDefinition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ScopePackage.SCOPE_RULE:
      {
        ScopeRule scopeRule = (ScopeRule)theEObject;
        T result = caseScopeRule(scopeRule);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ScopePackage.SCOPE_CONTEXT:
      {
        ScopeContext scopeContext = (ScopeContext)theEObject;
        T result = caseScopeContext(scopeContext);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ScopePackage.SCOPE_EXPRESSION:
      {
        ScopeExpression scopeExpression = (ScopeExpression)theEObject;
        T result = caseScopeExpression(scopeExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ScopePackage.FACTORY_EXPRESSION:
      {
        FactoryExpression factoryExpression = (FactoryExpression)theEObject;
        T result = caseFactoryExpression(factoryExpression);
        if (result == null) result = caseScopeExpression(factoryExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ScopePackage.SCOPE_DELEGATION:
      {
        ScopeDelegation scopeDelegation = (ScopeDelegation)theEObject;
        T result = caseScopeDelegation(scopeDelegation);
        if (result == null) result = caseScopeExpression(scopeDelegation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ScopePackage.NAMED_SCOPE_EXPRESSION:
      {
        NamedScopeExpression namedScopeExpression = (NamedScopeExpression)theEObject;
        T result = caseNamedScopeExpression(namedScopeExpression);
        if (result == null) result = caseScopeExpression(namedScopeExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ScopePackage.GLOBAL_SCOPE_EXPRESSION:
      {
        GlobalScopeExpression globalScopeExpression = (GlobalScopeExpression)theEObject;
        T result = caseGlobalScopeExpression(globalScopeExpression);
        if (result == null) result = caseNamedScopeExpression(globalScopeExpression);
        if (result == null) result = caseScopeExpression(globalScopeExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ScopePackage.DATA_EXPRESSION:
      {
        DataExpression dataExpression = (DataExpression)theEObject;
        T result = caseDataExpression(dataExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ScopePackage.MATCH_DATA_EXPRESSION:
      {
        MatchDataExpression matchDataExpression = (MatchDataExpression)theEObject;
        T result = caseMatchDataExpression(matchDataExpression);
        if (result == null) result = caseDataExpression(matchDataExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ScopePackage.LAMBDA_DATA_EXPRESSION:
      {
        LambdaDataExpression lambdaDataExpression = (LambdaDataExpression)theEObject;
        T result = caseLambdaDataExpression(lambdaDataExpression);
        if (result == null) result = caseDataExpression(lambdaDataExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ScopePackage.SIMPLE_SCOPE_EXPRESSION:
      {
        SimpleScopeExpression simpleScopeExpression = (SimpleScopeExpression)theEObject;
        T result = caseSimpleScopeExpression(simpleScopeExpression);
        if (result == null) result = caseNamedScopeExpression(simpleScopeExpression);
        if (result == null) result = caseScopeExpression(simpleScopeExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ScopePackage.NAMING:
      {
        Naming naming = (Naming)theEObject;
        T result = caseNaming(naming);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ScopePackage.NAMING_EXPRESSION:
      {
        NamingExpression namingExpression = (NamingExpression)theEObject;
        T result = caseNamingExpression(namingExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseScopeModel(ScopeModel object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Import</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Import</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseImport(Import object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Extension</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Extension</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExtension(Extension object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Naming Section</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Naming Section</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNamingSection(NamingSection object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Naming Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Naming Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNamingDefinition(NamingDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseScopeDefinition(ScopeDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Rule</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Rule</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseScopeRule(ScopeRule object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Context</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Context</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseScopeContext(ScopeContext object)
  {
    return null;
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
  public T caseScopeExpression(ScopeExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Factory Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Factory Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFactoryExpression(FactoryExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Delegation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Delegation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseScopeDelegation(ScopeDelegation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Named Scope Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Scope Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNamedScopeExpression(NamedScopeExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Global Scope Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Global Scope Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGlobalScopeExpression(GlobalScopeExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Data Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Data Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDataExpression(DataExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Match Data Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Match Data Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMatchDataExpression(MatchDataExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Lambda Data Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Lambda Data Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLambdaDataExpression(LambdaDataExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Simple Scope Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Simple Scope Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSimpleScopeExpression(SimpleScopeExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Naming</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Naming</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNaming(Naming object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Naming Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Naming Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNamingExpression(NamingExpression object)
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

} //ScopeSwitch
