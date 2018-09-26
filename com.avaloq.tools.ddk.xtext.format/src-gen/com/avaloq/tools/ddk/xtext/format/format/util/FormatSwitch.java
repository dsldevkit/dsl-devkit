/**
 */
package com.avaloq.tools.ddk.xtext.format.format.util;

import com.avaloq.tools.ddk.xtext.format.format.*;

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
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage
 * @generated
 */
public class FormatSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static FormatPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FormatSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = FormatPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param ePackage the package in question.
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
      case FormatPackage.FORMAT_CONFIGURATION:
      {
        FormatConfiguration formatConfiguration = (FormatConfiguration)theEObject;
        T result = caseFormatConfiguration(formatConfiguration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.CONSTANT:
      {
        Constant constant = (Constant)theEObject;
        T result = caseConstant(constant);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.INT_VALUE:
      {
        IntValue intValue = (IntValue)theEObject;
        T result = caseIntValue(intValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.STRING_VALUE:
      {
        StringValue stringValue = (StringValue)theEObject;
        T result = caseStringValue(stringValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.RULE:
      {
        Rule rule = (Rule)theEObject;
        T result = caseRule(rule);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.GRAMMAR_RULE:
      {
        GrammarRule grammarRule = (GrammarRule)theEObject;
        T result = caseGrammarRule(grammarRule);
        if (result == null) result = caseRule(grammarRule);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.WILDCARD_RULE:
      {
        WildcardRule wildcardRule = (WildcardRule)theEObject;
        T result = caseWildcardRule(wildcardRule);
        if (result == null) result = caseRule(wildcardRule);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.GRAMMAR_RULE_DIRECTIVE:
      {
        GrammarRuleDirective grammarRuleDirective = (GrammarRuleDirective)theEObject;
        T result = caseGrammarRuleDirective(grammarRuleDirective);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.WILDCARD_RULE_DIRECTIVE:
      {
        WildcardRuleDirective wildcardRuleDirective = (WildcardRuleDirective)theEObject;
        T result = caseWildcardRuleDirective(wildcardRuleDirective);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.GRAMMAR_ELEMENT_REFERENCE:
      {
        GrammarElementReference grammarElementReference = (GrammarElementReference)theEObject;
        T result = caseGrammarElementReference(grammarElementReference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.GRAMMAR_ELEMENT_LOOKUP:
      {
        GrammarElementLookup grammarElementLookup = (GrammarElementLookup)theEObject;
        T result = caseGrammarElementLookup(grammarElementLookup);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.CONTEXT_FREE_DIRECTIVE:
      {
        ContextFreeDirective contextFreeDirective = (ContextFreeDirective)theEObject;
        T result = caseContextFreeDirective(contextFreeDirective);
        if (result == null) result = caseGrammarRuleDirective(contextFreeDirective);
        if (result == null) result = caseWildcardRuleDirective(contextFreeDirective);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.SPECIFIC_DIRECTIVE:
      {
        SpecificDirective specificDirective = (SpecificDirective)theEObject;
        T result = caseSpecificDirective(specificDirective);
        if (result == null) result = caseGrammarRuleDirective(specificDirective);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.MATCHER_LIST:
      {
        MatcherList matcherList = (MatcherList)theEObject;
        T result = caseMatcherList(matcherList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.GROUP_BLOCK:
      {
        GroupBlock groupBlock = (GroupBlock)theEObject;
        T result = caseGroupBlock(groupBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.KEYWORD_PAIR:
      {
        KeywordPair keywordPair = (KeywordPair)theEObject;
        T result = caseKeywordPair(keywordPair);
        if (result == null) result = caseGrammarRuleDirective(keywordPair);
        if (result == null) result = caseWildcardRuleDirective(keywordPair);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.MATCHER:
      {
        Matcher matcher = (Matcher)theEObject;
        T result = caseMatcher(matcher);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.LOCATOR:
      {
        Locator locator = (Locator)theEObject;
        T result = caseLocator(locator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.NO_FORMAT_LOCATOR:
      {
        NoFormatLocator noFormatLocator = (NoFormatLocator)theEObject;
        T result = caseNoFormatLocator(noFormatLocator);
        if (result == null) result = caseLocator(noFormatLocator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.SPACE_LOCATOR:
      {
        SpaceLocator spaceLocator = (SpaceLocator)theEObject;
        T result = caseSpaceLocator(spaceLocator);
        if (result == null) result = caseLocator(spaceLocator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.RIGHT_PADDING_LOCATOR:
      {
        RightPaddingLocator rightPaddingLocator = (RightPaddingLocator)theEObject;
        T result = caseRightPaddingLocator(rightPaddingLocator);
        if (result == null) result = caseLocator(rightPaddingLocator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.LINEWRAP_LOCATOR:
      {
        LinewrapLocator linewrapLocator = (LinewrapLocator)theEObject;
        T result = caseLinewrapLocator(linewrapLocator);
        if (result == null) result = caseLocator(linewrapLocator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.COLUMN_LOCATOR:
      {
        ColumnLocator columnLocator = (ColumnLocator)theEObject;
        T result = caseColumnLocator(columnLocator);
        if (result == null) result = caseLocator(columnLocator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.OFFSET_LOCATOR:
      {
        OffsetLocator offsetLocator = (OffsetLocator)theEObject;
        T result = caseOffsetLocator(offsetLocator);
        if (result == null) result = caseLocator(offsetLocator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatPackage.INDENT_LOCATOR:
      {
        IndentLocator indentLocator = (IndentLocator)theEObject;
        T result = caseIndentLocator(indentLocator);
        if (result == null) result = caseLocator(indentLocator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Configuration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Configuration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFormatConfiguration(FormatConfiguration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Constant</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Constant</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConstant(Constant object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Int Value</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Int Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIntValue(IntValue object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>String Value</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStringValue(StringValue object)
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
  public T caseRule(Rule object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Grammar Rule</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Grammar Rule</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGrammarRule(GrammarRule object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Wildcard Rule</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Wildcard Rule</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWildcardRule(WildcardRule object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Grammar Rule Directive</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Grammar Rule Directive</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGrammarRuleDirective(GrammarRuleDirective object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Wildcard Rule Directive</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Wildcard Rule Directive</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWildcardRuleDirective(WildcardRuleDirective object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Grammar Element Reference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Grammar Element Reference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGrammarElementReference(GrammarElementReference object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Grammar Element Lookup</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Grammar Element Lookup</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGrammarElementLookup(GrammarElementLookup object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Context Free Directive</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Context Free Directive</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseContextFreeDirective(ContextFreeDirective object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Specific Directive</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Specific Directive</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSpecificDirective(SpecificDirective object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Matcher List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Matcher List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMatcherList(MatcherList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Group Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Group Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGroupBlock(GroupBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Keyword Pair</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Keyword Pair</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseKeywordPair(KeywordPair object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Matcher</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Matcher</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMatcher(Matcher object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Locator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Locator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLocator(Locator object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>No Format Locator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>No Format Locator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNoFormatLocator(NoFormatLocator object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Space Locator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Space Locator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSpaceLocator(SpaceLocator object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Right Padding Locator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Right Padding Locator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRightPaddingLocator(RightPaddingLocator object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Linewrap Locator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Linewrap Locator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLinewrapLocator(LinewrapLocator object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Column Locator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Column Locator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseColumnLocator(ColumnLocator object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Offset Locator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Offset Locator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOffsetLocator(OffsetLocator object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Indent Locator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Indent Locator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIndentLocator(IndentLocator object)
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

} //FormatSwitch
