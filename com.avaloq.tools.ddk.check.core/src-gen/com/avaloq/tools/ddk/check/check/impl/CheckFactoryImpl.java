/**
 */
package com.avaloq.tools.ddk.check.check.impl;

import com.avaloq.tools.ddk.check.check.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CheckFactoryImpl extends EFactoryImpl implements CheckFactory
{
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CheckFactory init()
	{
		try
		{
			CheckFactory theCheckFactory = (CheckFactory)EPackage.Registry.INSTANCE.getEFactory(CheckPackage.eNS_URI);
			if (theCheckFactory != null)
			{
				return theCheckFactory;
			}
		}
		catch (Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CheckFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CheckFactoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass)
	{
		switch (eClass.getClassifierID())
		{
			case CheckPackage.CHECK_CATALOG: return createCheckCatalog();
			case CheckPackage.DOCUMENTED: return createDocumented();
			case CheckPackage.IMPLICITLY_NAMED: return createImplicitlyNamed();
			case CheckPackage.CATEGORY: return createCategory();
			case CheckPackage.CHECK: return createCheck();
			case CheckPackage.SEVERITY_RANGE: return createSeverityRange();
			case CheckPackage.MEMBER: return createMember();
			case CheckPackage.IMPLEMENTATION: return createImplementation();
			case CheckPackage.FORMAL_PARAMETER: return createFormalParameter();
			case CheckPackage.CONTEXT: return createContext();
			case CheckPackage.CONTEXT_VARIABLE: return createContextVariable();
			case CheckPackage.XGUARD_EXPRESSION: return createXGuardExpression();
			case CheckPackage.XISSUE_EXPRESSION: return createXIssueExpression();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue)
	{
		switch (eDataType.getClassifierID())
		{
			case CheckPackage.SEVERITY_KIND:
				return createSeverityKindFromString(eDataType, initialValue);
			case CheckPackage.TRIGGER_KIND:
				return createTriggerKindFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue)
	{
		switch (eDataType.getClassifierID())
		{
			case CheckPackage.SEVERITY_KIND:
				return convertSeverityKindToString(eDataType, instanceValue);
			case CheckPackage.TRIGGER_KIND:
				return convertTriggerKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CheckCatalog createCheckCatalog()
	{
		CheckCatalogImplCustom checkCatalog = new CheckCatalogImplCustom();
		return checkCatalog;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Documented createDocumented()
	{
		DocumentedImplCustom documented = new DocumentedImplCustom();
		return documented;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ImplicitlyNamed createImplicitlyNamed()
	{
		ImplicitlyNamedImpl implicitlyNamed = new ImplicitlyNamedImpl();
		return implicitlyNamed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Category createCategory()
	{
		CategoryImplCustom category = new CategoryImplCustom();
		return category;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Check createCheck()
	{
		CheckImplCustom check = new CheckImplCustom();
		return check;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SeverityRange createSeverityRange()
	{
		SeverityRangeImpl severityRange = new SeverityRangeImpl();
		return severityRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Member createMember()
	{
		MemberImpl member = new MemberImpl();
		return member;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Implementation createImplementation()
	{
		ImplementationImpl implementation = new ImplementationImpl();
		return implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FormalParameter createFormalParameter()
	{
		FormalParameterImpl formalParameter = new FormalParameterImpl();
		return formalParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Context createContext()
	{
		ContextImpl context = new ContextImpl();
		return context;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContextVariable createContextVariable()
	{
		ContextVariableImpl contextVariable = new ContextVariableImpl();
		return contextVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public XGuardExpression createXGuardExpression()
	{
		XGuardExpressionImpl xGuardExpression = new XGuardExpressionImpl();
		return xGuardExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public XIssueExpression createXIssueExpression()
	{
		XIssueExpressionImpl xIssueExpression = new XIssueExpressionImpl();
		return xIssueExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SeverityKind createSeverityKindFromString(EDataType eDataType, String initialValue)
	{
		SeverityKind result = SeverityKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSeverityKindToString(EDataType eDataType, Object instanceValue)
	{
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TriggerKind createTriggerKindFromString(EDataType eDataType, String initialValue)
	{
		TriggerKind result = TriggerKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTriggerKindToString(EDataType eDataType, Object instanceValue)
	{
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CheckPackage getCheckPackage()
	{
		return (CheckPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CheckPackage getPackage()
	{
		return CheckPackage.eINSTANCE;
	}

} //CheckFactoryImpl
