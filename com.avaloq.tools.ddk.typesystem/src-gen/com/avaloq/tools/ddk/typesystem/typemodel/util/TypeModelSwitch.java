/**
 */
package com.avaloq.tools.ddk.typesystem.typemodel.util;

import com.avaloq.tools.ddk.typesystem.typemodel.*;

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
 * @see com.avaloq.tools.ddk.typesystem.typemodel.TypeModelPackage
 * @generated
 */
public class TypeModelSwitch<T> extends Switch<T>
{
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TypeModelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeModelSwitch()
	{
		if (modelPackage == null)
		{
			modelPackage = TypeModelPackage.eINSTANCE;
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
			case TypeModelPackage.IEXPRESSION:
			{
				IExpression iExpression = (IExpression)theEObject;
				T result = caseIExpression(iExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypeModelPackage.ITYPE:
			{
				IType iType = (IType)theEObject;
				T result = caseIType(iType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypeModelPackage.INAMED_ELEMENT:
			{
				INamedElement iNamedElement = (INamedElement)theEObject;
				T result = caseINamedElement(iNamedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypeModelPackage.OVERRIDE_DECLARATION:
			{
				OverrideDeclaration overrideDeclaration = (OverrideDeclaration)theEObject;
				T result = caseOverrideDeclaration(overrideDeclaration);
				if (result == null) result = caseINamedElement(overrideDeclaration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypeModelPackage.NAMED_ELEMENT:
			{
				NamedElement namedElement = (NamedElement)theEObject;
				T result = caseNamedElement(namedElement);
				if (result == null) result = caseINamedElement(namedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypeModelPackage.ICASE_SENSITIVE_NAMED_ELEMENT:
			{
				ICaseSensitiveNamedElement iCaseSensitiveNamedElement = (ICaseSensitiveNamedElement)theEObject;
				T result = caseICaseSensitiveNamedElement(iCaseSensitiveNamedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypeModelPackage.INAMED_TYPE:
			{
				INamedType iNamedType = (INamedType)theEObject;
				T result = caseINamedType(iNamedType);
				if (result == null) result = caseINamedElement(iNamedType);
				if (result == null) result = caseIType(iNamedType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypeModelPackage.NAMED_TYPE:
			{
				NamedType namedType = (NamedType)theEObject;
				T result = caseNamedType(namedType);
				if (result == null) result = caseNamedElement(namedType);
				if (result == null) result = caseINamedType(namedType);
				if (result == null) result = caseINamedElement(namedType);
				if (result == null) result = caseIType(namedType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypeModelPackage.IFORMAL_PARAMETER:
			{
				IFormalParameter iFormalParameter = (IFormalParameter)theEObject;
				T result = caseIFormalParameter(iFormalParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypeModelPackage.IACTUAL_PARAMETER:
			{
				IActualParameter iActualParameter = (IActualParameter)theEObject;
				T result = caseIActualParameter(iActualParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypeModelPackage.INAMED_ACTUAL_PARAMETER:
			{
				INamedActualParameter iNamedActualParameter = (INamedActualParameter)theEObject;
				T result = caseINamedActualParameter(iNamedActualParameter);
				if (result == null) result = caseIActualParameter(iNamedActualParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypeModelPackage.ISUBPROGRAM:
			{
				ISubprogram iSubprogram = (ISubprogram)theEObject;
				T result = caseISubprogram(iSubprogram);
				if (result == null) result = caseICallable(iSubprogram);
				if (result == null) result = caseINamedElement(iSubprogram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypeModelPackage.IPROCEDURE:
			{
				IProcedure iProcedure = (IProcedure)theEObject;
				T result = caseIProcedure(iProcedure);
				if (result == null) result = caseISubprogram(iProcedure);
				if (result == null) result = caseICallable(iProcedure);
				if (result == null) result = caseINamedElement(iProcedure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypeModelPackage.IFUNCTION:
			{
				IFunction iFunction = (IFunction)theEObject;
				T result = caseIFunction(iFunction);
				if (result == null) result = caseISubprogram(iFunction);
				if (result == null) result = caseICallable(iFunction);
				if (result == null) result = caseINamedElement(iFunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypeModelPackage.IFORMAL_PARAMETER_LIST:
			{
				@SuppressWarnings("unchecked") Iterable<? extends IFormalParameter> iFormalParameterList = (Iterable<? extends IFormalParameter>)theEObject;
				T result = caseIFormalParameterList(iFormalParameterList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypeModelPackage.NAMED_FORMAL_PARAMETER:
			{
				NamedFormalParameter namedFormalParameter = (NamedFormalParameter)theEObject;
				T result = caseNamedFormalParameter(namedFormalParameter);
				if (result == null) result = caseNamedElement(namedFormalParameter);
				if (result == null) result = caseINamedFormalParameter(namedFormalParameter);
				if (result == null) result = caseINamedElement(namedFormalParameter);
				if (result == null) result = caseIFormalParameter(namedFormalParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypeModelPackage.INAMED_FORMAL_PARAMETER:
			{
				INamedFormalParameter iNamedFormalParameter = (INamedFormalParameter)theEObject;
				T result = caseINamedFormalParameter(iNamedFormalParameter);
				if (result == null) result = caseINamedElement(iNamedFormalParameter);
				if (result == null) result = caseIFormalParameter(iNamedFormalParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypeModelPackage.ICALLABLE:
			{
				ICallable iCallable = (ICallable)theEObject;
				T result = caseICallable(iCallable);
				if (result == null) result = caseINamedElement(iCallable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypeModelPackage.CALLABLE:
			{
				Callable callable = (Callable)theEObject;
				T result = caseCallable(callable);
				if (result == null) result = caseNamedElement(callable);
				if (result == null) result = caseICallable(callable);
				if (result == null) result = caseINamedElement(callable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IExpression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IExpression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIExpression(IExpression object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IType</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IType</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIType(IType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>INamed Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>INamed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseINamedElement(INamedElement object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Override Declaration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Override Declaration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOverrideDeclaration(OverrideDeclaration object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ICase Sensitive Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ICase Sensitive Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseICaseSensitiveNamedElement(ICaseSensitiveNamedElement object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>INamed Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>INamed Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseINamedType(INamedType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedType(NamedType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IFormal Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IFormal Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIFormalParameter(IFormalParameter object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IActual Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IActual Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIActualParameter(IActualParameter object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>INamed Actual Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>INamed Actual Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseINamedActualParameter(INamedActualParameter object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ISubprogram</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ISubprogram</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseISubprogram(ISubprogram object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IProcedure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IProcedure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIProcedure(IProcedure object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IFunction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IFunction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIFunction(IFunction object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IFormal Parameter List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IFormal Parameter List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIFormalParameterList(Iterable<? extends IFormalParameter> object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Formal Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Formal Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedFormalParameter(NamedFormalParameter object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>INamed Formal Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>INamed Formal Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseINamedFormalParameter(INamedFormalParameter object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ICallable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ICallable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseICallable(ICallable object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Callable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Callable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCallable(Callable object)
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

} //TypeModelSwitch
