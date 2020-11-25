/**
 */
package com.avaloq.tools.ddk.check.check.util;

import com.avaloq.tools.ddk.check.check.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.xtext.xbase.XExpression;

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
 * @see com.avaloq.tools.ddk.check.check.CheckPackage
 * @generated
 */
public class CheckSwitch<T> extends Switch<T>
{
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CheckPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CheckSwitch()
	{
		if (modelPackage == null)
		{
			modelPackage = CheckPackage.eINSTANCE;
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
			case CheckPackage.CHECK_CATALOG:
			{
				CheckCatalog checkCatalog = (CheckCatalog)theEObject;
				T result = caseCheckCatalog(checkCatalog);
				if (result == null) result = caseDocumented(checkCatalog);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CheckPackage.DOCUMENTED:
			{
				Documented documented = (Documented)theEObject;
				T result = caseDocumented(documented);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CheckPackage.IMPLICITLY_NAMED:
			{
				ImplicitlyNamed implicitlyNamed = (ImplicitlyNamed)theEObject;
				T result = caseImplicitlyNamed(implicitlyNamed);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CheckPackage.CATEGORY:
			{
				Category category = (Category)theEObject;
				T result = caseCategory(category);
				if (result == null) result = caseDocumented(category);
				if (result == null) result = caseImplicitlyNamed(category);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CheckPackage.CHECK:
			{
				Check check = (Check)theEObject;
				T result = caseCheck(check);
				if (result == null) result = caseDocumented(check);
				if (result == null) result = caseImplicitlyNamed(check);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CheckPackage.SEVERITY_RANGE:
			{
				SeverityRange severityRange = (SeverityRange)theEObject;
				T result = caseSeverityRange(severityRange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CheckPackage.MEMBER:
			{
				Member member = (Member)theEObject;
				T result = caseMember(member);
				if (result == null) result = caseDocumented(member);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CheckPackage.IMPLEMENTATION:
			{
				Implementation implementation = (Implementation)theEObject;
				T result = caseImplementation(implementation);
				if (result == null) result = caseDocumented(implementation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CheckPackage.FORMAL_PARAMETER:
			{
				FormalParameter formalParameter = (FormalParameter)theEObject;
				T result = caseFormalParameter(formalParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CheckPackage.CONTEXT:
			{
				Context context = (Context)theEObject;
				T result = caseContext(context);
				if (result == null) result = caseDocumented(context);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CheckPackage.CONTEXT_VARIABLE:
			{
				ContextVariable contextVariable = (ContextVariable)theEObject;
				T result = caseContextVariable(contextVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CheckPackage.XGUARD_EXPRESSION:
			{
				XGuardExpression xGuardExpression = (XGuardExpression)theEObject;
				T result = caseXGuardExpression(xGuardExpression);
				if (result == null) result = caseXExpression(xGuardExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CheckPackage.XISSUE_EXPRESSION:
			{
				XIssueExpression xIssueExpression = (XIssueExpression)theEObject;
				T result = caseXIssueExpression(xIssueExpression);
				if (result == null) result = caseXExpression(xIssueExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Catalog</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Catalog</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCheckCatalog(CheckCatalog object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documented</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documented</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDocumented(Documented object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Implicitly Named</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Implicitly Named</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImplicitlyNamed(ImplicitlyNamed object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Category</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Category</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCategory(Category object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Check</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Check</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCheck(Check object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Severity Range</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Severity Range</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSeverityRange(SeverityRange object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Member</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Member</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMember(Member object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Implementation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImplementation(Implementation object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Formal Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Formal Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormalParameter(FormalParameter object)
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
	public T caseContext(Context object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Context Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Context Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContextVariable(ContextVariable object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>XGuard Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>XGuard Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseXGuardExpression(XGuardExpression object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>XIssue Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>XIssue Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseXIssueExpression(XIssueExpression object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>XExpression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>XExpression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @since 2.7
	 * @generated
	 */
	public T caseXExpression(XExpression object)
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

} //CheckSwitch
