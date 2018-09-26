/**
 */
package com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.util;

import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.*;

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
 * @see com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguagePackage
 * @generated
 */
public class FormatterTestLanguageSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static FormatterTestLanguagePackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FormatterTestLanguageSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = FormatterTestLanguagePackage.eINSTANCE;
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
      case FormatterTestLanguagePackage.ROOT:
      {
        Root root = (Root)theEObject;
        T result = caseRoot(root);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.LINE:
      {
        Line line = (Line)theEObject;
        T result = caseLine(line);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.DECL:
      {
        Decl decl = (Decl)theEObject;
        T result = caseDecl(decl);
        if (result == null) result = caseLine(decl);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.ASSIGN:
      {
        Assign assign = (Assign)theEObject;
        T result = caseAssign(assign);
        if (result == null) result = caseLine(assign);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.METH:
      {
        Meth meth = (Meth)theEObject;
        T result = caseMeth(meth);
        if (result == null) result = caseLine(meth);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.PARAM:
      {
        Param param = (Param)theEObject;
        T result = caseParam(param);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.SPACE:
      {
        Space space = (Space)theEObject;
        T result = caseSpace(space);
        if (result == null) result = caseLine(space);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.TEST_LINEWRAP:
      {
        TestLinewrap testLinewrap = (TestLinewrap)theEObject;
        T result = caseTestLinewrap(testLinewrap);
        if (result == null) result = caseRoot(testLinewrap);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.TEST_LINEWRAP_MIN_MAX:
      {
        TestLinewrapMinMax testLinewrapMinMax = (TestLinewrapMinMax)theEObject;
        T result = caseTestLinewrapMinMax(testLinewrapMinMax);
        if (result == null) result = caseRoot(testLinewrapMinMax);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.TEST_INDENTATION:
      {
        TestIndentation testIndentation = (TestIndentation)theEObject;
        T result = caseTestIndentation(testIndentation);
        if (result == null) result = caseRoot(testIndentation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.TEST_COLUMN:
      {
        TestColumn testColumn = (TestColumn)theEObject;
        T result = caseTestColumn(testColumn);
        if (result == null) result = caseRoot(testColumn);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.TEST_OFFSET:
      {
        TestOffset testOffset = (TestOffset)theEObject;
        T result = caseTestOffset(testOffset);
        if (result == null) result = caseRoot(testOffset);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.TEST_RIGHT_PADDING:
      {
        TestRightPadding testRightPadding = (TestRightPadding)theEObject;
        T result = caseTestRightPadding(testRightPadding);
        if (result == null) result = caseRoot(testRightPadding);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.FQN_OBJ:
      {
        FqnObj fqnObj = (FqnObj)theEObject;
        T result = caseFqnObj(fqnObj);
        if (result == null) result = caseLine(fqnObj);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.FQN_REF:
      {
        FqnRef fqnRef = (FqnRef)theEObject;
        T result = caseFqnRef(fqnRef);
        if (result == null) result = caseLine(fqnRef);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.ENUMERATION:
      {
        Enumeration enumeration = (Enumeration)theEObject;
        T result = caseEnumeration(enumeration);
        if (result == null) result = caseLine(enumeration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.SUPPRESSED_HIDDEN:
      {
        SuppressedHidden suppressedHidden = (SuppressedHidden)theEObject;
        T result = caseSuppressedHidden(suppressedHidden);
        if (result == null) result = caseLine(suppressedHidden);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.SUPPRESSED_HIDDEN_SUB:
      {
        SuppressedHiddenSub suppressedHiddenSub = (SuppressedHiddenSub)theEObject;
        T result = caseSuppressedHiddenSub(suppressedHiddenSub);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.SUPPRESSED_HIDDEN_SUB_SUB:
      {
        SuppressedHiddenSubSub suppressedHiddenSubSub = (SuppressedHiddenSubSub)theEObject;
        T result = caseSuppressedHiddenSubSub(suppressedHiddenSubSub);
        if (result == null) result = caseSuppressedHiddenSub(suppressedHiddenSubSub);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.SUPPRESSED_HIDDEN_SUB_ID:
      {
        SuppressedHiddenSubID suppressedHiddenSubID = (SuppressedHiddenSubID)theEObject;
        T result = caseSuppressedHiddenSubID(suppressedHiddenSubID);
        if (result == null) result = caseSuppressedHiddenSub(suppressedHiddenSubID);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FormatterTestLanguagePackage.DATATYPES:
      {
        Datatypes datatypes = (Datatypes)theEObject;
        T result = caseDatatypes(datatypes);
        if (result == null) result = caseLine(datatypes);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Root</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Root</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRoot(Root object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Line</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Line</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLine(Line object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Decl</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Decl</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDecl(Decl object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Assign</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Assign</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAssign(Assign object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Meth</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Meth</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMeth(Meth object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Param</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Param</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseParam(Param object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Space</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Space</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSpace(Space object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Test Linewrap</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Test Linewrap</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTestLinewrap(TestLinewrap object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Test Linewrap Min Max</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Test Linewrap Min Max</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTestLinewrapMinMax(TestLinewrapMinMax object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Test Indentation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Test Indentation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTestIndentation(TestIndentation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Test Column</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Test Column</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTestColumn(TestColumn object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Test Offset</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Test Offset</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTestOffset(TestOffset object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Test Right Padding</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Test Right Padding</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTestRightPadding(TestRightPadding object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Fqn Obj</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fqn Obj</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFqnObj(FqnObj object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Fqn Ref</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fqn Ref</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFqnRef(FqnRef object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Enumeration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Enumeration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEnumeration(Enumeration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Suppressed Hidden</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Suppressed Hidden</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSuppressedHidden(SuppressedHidden object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Suppressed Hidden Sub</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Suppressed Hidden Sub</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSuppressedHiddenSub(SuppressedHiddenSub object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Suppressed Hidden Sub Sub</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Suppressed Hidden Sub Sub</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSuppressedHiddenSubSub(SuppressedHiddenSubSub object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Suppressed Hidden Sub ID</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Suppressed Hidden Sub ID</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSuppressedHiddenSubID(SuppressedHiddenSubID object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Datatypes</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Datatypes</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDatatypes(Datatypes object)
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

} //FormatterTestLanguageSwitch
