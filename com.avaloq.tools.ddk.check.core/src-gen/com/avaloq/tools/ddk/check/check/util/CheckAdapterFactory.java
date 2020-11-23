/**
 */
package com.avaloq.tools.ddk.check.check.util;

import com.avaloq.tools.ddk.check.check.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.check.check.CheckPackage
 * @generated
 */
public class CheckAdapterFactory extends AdapterFactoryImpl
{
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CheckPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CheckAdapterFactory()
	{
		if (modelPackage == null)
		{
			modelPackage = CheckPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object)
	{
		if (object == modelPackage)
		{
			return true;
		}
		if (object instanceof EObject)
		{
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CheckSwitch<Adapter> modelSwitch =
		new CheckSwitch<Adapter>()
		{
			@Override
			public Adapter caseCheckCatalog(CheckCatalog object)
			{
				return createCheckCatalogAdapter();
			}
			@Override
			public Adapter caseDocumented(Documented object)
			{
				return createDocumentedAdapter();
			}
			@Override
			public Adapter caseImplicitlyNamed(ImplicitlyNamed object)
			{
				return createImplicitlyNamedAdapter();
			}
			@Override
			public Adapter caseCategory(Category object)
			{
				return createCategoryAdapter();
			}
			@Override
			public Adapter caseCheck(Check object)
			{
				return createCheckAdapter();
			}
			@Override
			public Adapter caseSeverityRange(SeverityRange object)
			{
				return createSeverityRangeAdapter();
			}
			@Override
			public Adapter caseMember(Member object)
			{
				return createMemberAdapter();
			}
			@Override
			public Adapter caseImplementation(Implementation object)
			{
				return createImplementationAdapter();
			}
			@Override
			public Adapter caseFormalParameter(FormalParameter object)
			{
				return createFormalParameterAdapter();
			}
			@Override
			public Adapter caseContext(Context object)
			{
				return createContextAdapter();
			}
			@Override
			public Adapter caseContextVariable(ContextVariable object)
			{
				return createContextVariableAdapter();
			}
			@Override
			public Adapter caseXGuardExpression(XGuardExpression object)
			{
				return createXGuardExpressionAdapter();
			}
			@Override
			public Adapter caseXIssueExpression(XIssueExpression object)
			{
				return createXIssueExpressionAdapter();
			}
			@Override
			public Adapter caseXExpression(XExpression object)
			{
				return createXExpressionAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object)
			{
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target)
	{
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.check.check.CheckCatalog <em>Catalog</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.check.check.CheckCatalog
	 * @generated
	 */
	public Adapter createCheckCatalogAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.check.check.Documented <em>Documented</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.check.check.Documented
	 * @generated
	 */
	public Adapter createDocumentedAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.check.check.ImplicitlyNamed <em>Implicitly Named</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.check.check.ImplicitlyNamed
	 * @generated
	 */
	public Adapter createImplicitlyNamedAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.check.check.Category <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.check.check.Category
	 * @generated
	 */
	public Adapter createCategoryAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.check.check.Check <em>Check</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.check.check.Check
	 * @generated
	 */
	public Adapter createCheckAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.check.check.SeverityRange <em>Severity Range</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.check.check.SeverityRange
	 * @generated
	 */
	public Adapter createSeverityRangeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.check.check.Member <em>Member</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.check.check.Member
	 * @generated
	 */
	public Adapter createMemberAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.check.check.Implementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.check.check.Implementation
	 * @generated
	 */
	public Adapter createImplementationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.check.check.FormalParameter <em>Formal Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.check.check.FormalParameter
	 * @generated
	 */
	public Adapter createFormalParameterAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.check.check.Context <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.check.check.Context
	 * @generated
	 */
	public Adapter createContextAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.check.check.ContextVariable <em>Context Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.check.check.ContextVariable
	 * @generated
	 */
	public Adapter createContextVariableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.check.check.XGuardExpression <em>XGuard Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.check.check.XGuardExpression
	 * @generated
	 */
	public Adapter createXGuardExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.avaloq.tools.ddk.check.check.XIssueExpression <em>XIssue Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.avaloq.tools.ddk.check.check.XIssueExpression
	 * @generated
	 */
	public Adapter createXIssueExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.xtext.xbase.XExpression <em>XExpression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.xtext.xbase.XExpression
	 * @since 2.7
	 * @generated
	 */
	public Adapter createXExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter()
	{
		return null;
	}

} //CheckAdapterFactory
