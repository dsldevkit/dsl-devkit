/**
 */
package com.avaloq.tools.ddk.check.check;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.xtext.xbase.XbasePackage;

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
 * @see com.avaloq.tools.ddk.check.check.CheckFactory
 * @model kind="package"
 * @generated
 */
public interface CheckPackage extends EPackage
{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "check";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.avaloq.com/tools/ddk/check/Check";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "check";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CheckPackage eINSTANCE = com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.check.check.impl.DocumentedImpl <em>Documented</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.check.check.impl.DocumentedImpl
	 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getDocumented()
	 * @generated
	 */
	int DOCUMENTED = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED__DESCRIPTION = 0;

	/**
	 * The number of structural features of the '<em>Documented</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.check.check.impl.CheckCatalogImpl <em>Catalog</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.check.check.impl.CheckCatalogImpl
	 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getCheckCatalog()
	 * @generated
	 */
	int CHECK_CATALOG = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CATALOG__DESCRIPTION = DOCUMENTED__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CATALOG__PACKAGE_NAME = DOCUMENTED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Imports</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CATALOG__IMPORTS = DOCUMENTED_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CATALOG__FINAL = DOCUMENTED_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CATALOG__NAME = DOCUMENTED_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Grammar</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CATALOG__GRAMMAR = DOCUMENTED_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Included Catalogs</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CATALOG__INCLUDED_CATALOGS = DOCUMENTED_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CATALOG__CATEGORIES = DOCUMENTED_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Implementations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CATALOG__IMPLEMENTATIONS = DOCUMENTED_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Checks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CATALOG__CHECKS = DOCUMENTED_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CATALOG__MEMBERS = DOCUMENTED_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Catalog</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CATALOG_FEATURE_COUNT = DOCUMENTED_FEATURE_COUNT + 10;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.check.check.impl.ImplicitlyNamedImpl <em>Implicitly Named</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.check.check.impl.ImplicitlyNamedImpl
	 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getImplicitlyNamed()
	 * @generated
	 */
	int IMPLICITLY_NAMED = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLICITLY_NAMED__NAME = 0;

	/**
	 * The number of structural features of the '<em>Implicitly Named</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLICITLY_NAMED_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.check.check.impl.CategoryImpl <em>Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.check.check.impl.CategoryImpl
	 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getCategory()
	 * @generated
	 */
	int CATEGORY = 3;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__DESCRIPTION = DOCUMENTED__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__NAME = DOCUMENTED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__ID = DOCUMENTED_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__LABEL = DOCUMENTED_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Checks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__CHECKS = DOCUMENTED_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_FEATURE_COUNT = DOCUMENTED_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.check.check.impl.CheckImpl <em>Check</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.check.check.impl.CheckImpl
	 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getCheck()
	 * @generated
	 */
	int CHECK = 4;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK__DESCRIPTION = DOCUMENTED__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK__NAME = DOCUMENTED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Severity Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK__SEVERITY_RANGE = DOCUMENTED_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK__FINAL = DOCUMENTED_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK__KIND = DOCUMENTED_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Default Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK__DEFAULT_SEVERITY = DOCUMENTED_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK__ID = DOCUMENTED_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK__LABEL = DOCUMENTED_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Formal Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK__FORMAL_PARAMETERS = DOCUMENTED_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Given Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK__GIVEN_MESSAGE = DOCUMENTED_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Contexts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK__CONTEXTS = DOCUMENTED_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK__MESSAGE = DOCUMENTED_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Check</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_FEATURE_COUNT = DOCUMENTED_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.check.check.impl.SeverityRangeImpl <em>Severity Range</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.check.check.impl.SeverityRangeImpl
	 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getSeverityRange()
	 * @generated
	 */
	int SEVERITY_RANGE = 5;

	/**
	 * The feature id for the '<em><b>Min Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEVERITY_RANGE__MIN_SEVERITY = 0;

	/**
	 * The feature id for the '<em><b>Max Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEVERITY_RANGE__MAX_SEVERITY = 1;

	/**
	 * The number of structural features of the '<em>Severity Range</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEVERITY_RANGE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.check.check.impl.MemberImpl <em>Member</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.check.check.impl.MemberImpl
	 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getMember()
	 * @generated
	 */
	int MEMBER = 6;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__DESCRIPTION = DOCUMENTED__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__ANNOTATIONS = DOCUMENTED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__TYPE = DOCUMENTED_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__NAME = DOCUMENTED_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__VALUE = DOCUMENTED_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Member</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_FEATURE_COUNT = DOCUMENTED_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.check.check.impl.ImplementationImpl <em>Implementation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.check.check.impl.ImplementationImpl
	 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getImplementation()
	 * @generated
	 */
	int IMPLEMENTATION = 7;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION__DESCRIPTION = DOCUMENTED__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION__NAME = DOCUMENTED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION__CONTEXT = DOCUMENTED_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLEMENTATION_FEATURE_COUNT = DOCUMENTED_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.check.check.impl.FormalParameterImpl <em>Formal Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.check.check.impl.FormalParameterImpl
	 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getFormalParameter()
	 * @generated
	 */
	int FORMAL_PARAMETER = 8;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_PARAMETER__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_PARAMETER__NAME = 1;

	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_PARAMETER__RIGHT = 2;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_PARAMETER__LABEL = 3;

	/**
	 * The number of structural features of the '<em>Formal Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_PARAMETER_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.check.check.impl.ContextImpl <em>Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.check.check.impl.ContextImpl
	 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getContext()
	 * @generated
	 */
	int CONTEXT = 9;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT__DESCRIPTION = DOCUMENTED__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Context Variable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT__CONTEXT_VARIABLE = DOCUMENTED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT__CONSTRAINT = DOCUMENTED_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_FEATURE_COUNT = DOCUMENTED_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.check.check.impl.ContextVariableImpl <em>Context Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.check.check.impl.ContextVariableImpl
	 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getContextVariable()
	 * @generated
	 */
	int CONTEXT_VARIABLE = 10;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_VARIABLE__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_VARIABLE__NAME = 1;

	/**
	 * The number of structural features of the '<em>Context Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_VARIABLE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.check.check.impl.XGuardExpressionImpl <em>XGuard Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.check.check.impl.XGuardExpressionImpl
	 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getXGuardExpression()
	 * @generated
	 */
	int XGUARD_EXPRESSION = 11;

	/**
	 * The feature id for the '<em><b>Guard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XGUARD_EXPRESSION__GUARD = XbasePackage.XEXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>XGuard Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XGUARD_EXPRESSION_FEATURE_COUNT = XbasePackage.XEXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.check.check.impl.XIssueExpressionImpl <em>XIssue Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.check.check.impl.XIssueExpressionImpl
	 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getXIssueExpression()
	 * @generated
	 */
	int XISSUE_EXPRESSION = 12;

	/**
	 * The feature id for the '<em><b>Check</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XISSUE_EXPRESSION__CHECK = XbasePackage.XEXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Marker Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XISSUE_EXPRESSION__MARKER_FEATURE = XbasePackage.XEXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Marker Object</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XISSUE_EXPRESSION__MARKER_OBJECT = XbasePackage.XEXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Marker Index</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XISSUE_EXPRESSION__MARKER_INDEX = XbasePackage.XEXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Message</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XISSUE_EXPRESSION__MESSAGE = XbasePackage.XEXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Message Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XISSUE_EXPRESSION__MESSAGE_PARAMETERS = XbasePackage.XEXPRESSION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Issue Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XISSUE_EXPRESSION__ISSUE_CODE = XbasePackage.XEXPRESSION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Issue Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XISSUE_EXPRESSION__ISSUE_DATA = XbasePackage.XEXPRESSION_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>XIssue Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XISSUE_EXPRESSION_FEATURE_COUNT = XbasePackage.XEXPRESSION_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.check.check.SeverityKind <em>Severity Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.check.check.SeverityKind
	 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getSeverityKind()
	 * @generated
	 */
	int SEVERITY_KIND = 13;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.check.check.TriggerKind <em>Trigger Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.check.check.TriggerKind
	 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getTriggerKind()
	 * @generated
	 */
	int TRIGGER_KIND = 14;


	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.check.check.CheckCatalog <em>Catalog</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Catalog</em>'.
	 * @see com.avaloq.tools.ddk.check.check.CheckCatalog
	 * @generated
	 */
	EClass getCheckCatalog();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getPackageName <em>Package Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package Name</em>'.
	 * @see com.avaloq.tools.ddk.check.check.CheckCatalog#getPackageName()
	 * @see #getCheckCatalog()
	 * @generated
	 */
	EAttribute getCheckCatalog_PackageName();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getImports <em>Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Imports</em>'.
	 * @see com.avaloq.tools.ddk.check.check.CheckCatalog#getImports()
	 * @see #getCheckCatalog()
	 * @generated
	 */
	EReference getCheckCatalog_Imports();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.CheckCatalog#isFinal <em>Final</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Final</em>'.
	 * @see com.avaloq.tools.ddk.check.check.CheckCatalog#isFinal()
	 * @see #getCheckCatalog()
	 * @generated
	 */
	EAttribute getCheckCatalog_Final();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.avaloq.tools.ddk.check.check.CheckCatalog#getName()
	 * @see #getCheckCatalog()
	 * @generated
	 */
	EAttribute getCheckCatalog_Name();

	/**
	 * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getGrammar <em>Grammar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Grammar</em>'.
	 * @see com.avaloq.tools.ddk.check.check.CheckCatalog#getGrammar()
	 * @see #getCheckCatalog()
	 * @generated
	 */
	EReference getCheckCatalog_Grammar();

	/**
	 * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getIncludedCatalogs <em>Included Catalogs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Included Catalogs</em>'.
	 * @see com.avaloq.tools.ddk.check.check.CheckCatalog#getIncludedCatalogs()
	 * @see #getCheckCatalog()
	 * @generated
	 */
	EReference getCheckCatalog_IncludedCatalogs();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getCategories <em>Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Categories</em>'.
	 * @see com.avaloq.tools.ddk.check.check.CheckCatalog#getCategories()
	 * @see #getCheckCatalog()
	 * @generated
	 */
	EReference getCheckCatalog_Categories();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getImplementations <em>Implementations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Implementations</em>'.
	 * @see com.avaloq.tools.ddk.check.check.CheckCatalog#getImplementations()
	 * @see #getCheckCatalog()
	 * @generated
	 */
	EReference getCheckCatalog_Implementations();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getChecks <em>Checks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Checks</em>'.
	 * @see com.avaloq.tools.ddk.check.check.CheckCatalog#getChecks()
	 * @see #getCheckCatalog()
	 * @generated
	 */
	EReference getCheckCatalog_Checks();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.check.check.CheckCatalog#getMembers <em>Members</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Members</em>'.
	 * @see com.avaloq.tools.ddk.check.check.CheckCatalog#getMembers()
	 * @see #getCheckCatalog()
	 * @generated
	 */
	EReference getCheckCatalog_Members();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.check.check.Documented <em>Documented</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documented</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Documented
	 * @generated
	 */
	EClass getDocumented();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.Documented#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Documented#getDescription()
	 * @see #getDocumented()
	 * @generated
	 */
	EAttribute getDocumented_Description();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.check.check.ImplicitlyNamed <em>Implicitly Named</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Implicitly Named</em>'.
	 * @see com.avaloq.tools.ddk.check.check.ImplicitlyNamed
	 * @generated
	 */
	EClass getImplicitlyNamed();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.ImplicitlyNamed#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.avaloq.tools.ddk.check.check.ImplicitlyNamed#getName()
	 * @see #getImplicitlyNamed()
	 * @generated
	 */
	EAttribute getImplicitlyNamed_Name();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.check.check.Category <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Category</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Category
	 * @generated
	 */
	EClass getCategory();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.Category#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Category#getId()
	 * @see #getCategory()
	 * @generated
	 */
	EAttribute getCategory_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.Category#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Category#getLabel()
	 * @see #getCategory()
	 * @generated
	 */
	EAttribute getCategory_Label();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.check.check.Category#getChecks <em>Checks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Checks</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Category#getChecks()
	 * @see #getCategory()
	 * @generated
	 */
	EReference getCategory_Checks();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.check.check.Check <em>Check</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Check</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Check
	 * @generated
	 */
	EClass getCheck();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.check.check.Check#getSeverityRange <em>Severity Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Severity Range</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Check#getSeverityRange()
	 * @see #getCheck()
	 * @generated
	 */
	EReference getCheck_SeverityRange();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.Check#isFinal <em>Final</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Final</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Check#isFinal()
	 * @see #getCheck()
	 * @generated
	 */
	EAttribute getCheck_Final();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.Check#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Check#getKind()
	 * @see #getCheck()
	 * @generated
	 */
	EAttribute getCheck_Kind();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.Check#getDefaultSeverity <em>Default Severity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Severity</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Check#getDefaultSeverity()
	 * @see #getCheck()
	 * @generated
	 */
	EAttribute getCheck_DefaultSeverity();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.Check#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Check#getId()
	 * @see #getCheck()
	 * @generated
	 */
	EAttribute getCheck_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.Check#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Check#getLabel()
	 * @see #getCheck()
	 * @generated
	 */
	EAttribute getCheck_Label();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.check.check.Check#getFormalParameters <em>Formal Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Formal Parameters</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Check#getFormalParameters()
	 * @see #getCheck()
	 * @generated
	 */
	EReference getCheck_FormalParameters();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.Check#getGivenMessage <em>Given Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Given Message</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Check#getGivenMessage()
	 * @see #getCheck()
	 * @generated
	 */
	EAttribute getCheck_GivenMessage();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.check.check.Check#getContexts <em>Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contexts</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Check#getContexts()
	 * @see #getCheck()
	 * @generated
	 */
	EReference getCheck_Contexts();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.Check#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Check#getMessage()
	 * @see #getCheck()
	 * @generated
	 */
	EAttribute getCheck_Message();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.check.check.SeverityRange <em>Severity Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Severity Range</em>'.
	 * @see com.avaloq.tools.ddk.check.check.SeverityRange
	 * @generated
	 */
	EClass getSeverityRange();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.SeverityRange#getMinSeverity <em>Min Severity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Severity</em>'.
	 * @see com.avaloq.tools.ddk.check.check.SeverityRange#getMinSeverity()
	 * @see #getSeverityRange()
	 * @generated
	 */
	EAttribute getSeverityRange_MinSeverity();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.SeverityRange#getMaxSeverity <em>Max Severity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Severity</em>'.
	 * @see com.avaloq.tools.ddk.check.check.SeverityRange#getMaxSeverity()
	 * @see #getSeverityRange()
	 * @generated
	 */
	EAttribute getSeverityRange_MaxSeverity();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.check.check.Member <em>Member</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Member</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Member
	 * @generated
	 */
	EClass getMember();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.check.check.Member#getAnnotations <em>Annotations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Annotations</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Member#getAnnotations()
	 * @see #getMember()
	 * @generated
	 */
	EReference getMember_Annotations();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.check.check.Member#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Member#getType()
	 * @see #getMember()
	 * @generated
	 */
	EReference getMember_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.Member#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Member#getName()
	 * @see #getMember()
	 * @generated
	 */
	EAttribute getMember_Name();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.check.check.Member#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Member#getValue()
	 * @see #getMember()
	 * @generated
	 */
	EReference getMember_Value();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.check.check.Implementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Implementation</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Implementation
	 * @generated
	 */
	EClass getImplementation();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.Implementation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Implementation#getName()
	 * @see #getImplementation()
	 * @generated
	 */
	EAttribute getImplementation_Name();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.check.check.Implementation#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Context</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Implementation#getContext()
	 * @see #getImplementation()
	 * @generated
	 */
	EReference getImplementation_Context();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.check.check.FormalParameter <em>Formal Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Formal Parameter</em>'.
	 * @see com.avaloq.tools.ddk.check.check.FormalParameter
	 * @generated
	 */
	EClass getFormalParameter();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.check.check.FormalParameter#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see com.avaloq.tools.ddk.check.check.FormalParameter#getType()
	 * @see #getFormalParameter()
	 * @generated
	 */
	EReference getFormalParameter_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.FormalParameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.avaloq.tools.ddk.check.check.FormalParameter#getName()
	 * @see #getFormalParameter()
	 * @generated
	 */
	EAttribute getFormalParameter_Name();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.check.check.FormalParameter#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right</em>'.
	 * @see com.avaloq.tools.ddk.check.check.FormalParameter#getRight()
	 * @see #getFormalParameter()
	 * @generated
	 */
	EReference getFormalParameter_Right();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.FormalParameter#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see com.avaloq.tools.ddk.check.check.FormalParameter#getLabel()
	 * @see #getFormalParameter()
	 * @generated
	 */
	EAttribute getFormalParameter_Label();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.check.check.Context <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Context</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Context
	 * @generated
	 */
	EClass getContext();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.check.check.Context#getContextVariable <em>Context Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Context Variable</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Context#getContextVariable()
	 * @see #getContext()
	 * @generated
	 */
	EReference getContext_ContextVariable();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.check.check.Context#getConstraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Constraint</em>'.
	 * @see com.avaloq.tools.ddk.check.check.Context#getConstraint()
	 * @see #getContext()
	 * @generated
	 */
	EReference getContext_Constraint();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.check.check.ContextVariable <em>Context Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Context Variable</em>'.
	 * @see com.avaloq.tools.ddk.check.check.ContextVariable
	 * @generated
	 */
	EClass getContextVariable();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.check.check.ContextVariable#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see com.avaloq.tools.ddk.check.check.ContextVariable#getType()
	 * @see #getContextVariable()
	 * @generated
	 */
	EReference getContextVariable_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.ContextVariable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.avaloq.tools.ddk.check.check.ContextVariable#getName()
	 * @see #getContextVariable()
	 * @generated
	 */
	EAttribute getContextVariable_Name();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.check.check.XGuardExpression <em>XGuard Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XGuard Expression</em>'.
	 * @see com.avaloq.tools.ddk.check.check.XGuardExpression
	 * @generated
	 */
	EClass getXGuardExpression();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.check.check.XGuardExpression#getGuard <em>Guard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Guard</em>'.
	 * @see com.avaloq.tools.ddk.check.check.XGuardExpression#getGuard()
	 * @see #getXGuardExpression()
	 * @generated
	 */
	EReference getXGuardExpression_Guard();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.check.check.XIssueExpression <em>XIssue Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XIssue Expression</em>'.
	 * @see com.avaloq.tools.ddk.check.check.XIssueExpression
	 * @generated
	 */
	EClass getXIssueExpression();

	/**
	 * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getCheck <em>Check</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Check</em>'.
	 * @see com.avaloq.tools.ddk.check.check.XIssueExpression#getCheck()
	 * @see #getXIssueExpression()
	 * @generated
	 */
	EReference getXIssueExpression_Check();

	/**
	 * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getMarkerFeature <em>Marker Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Marker Feature</em>'.
	 * @see com.avaloq.tools.ddk.check.check.XIssueExpression#getMarkerFeature()
	 * @see #getXIssueExpression()
	 * @generated
	 */
	EReference getXIssueExpression_MarkerFeature();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getMarkerObject <em>Marker Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Marker Object</em>'.
	 * @see com.avaloq.tools.ddk.check.check.XIssueExpression#getMarkerObject()
	 * @see #getXIssueExpression()
	 * @generated
	 */
	EReference getXIssueExpression_MarkerObject();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getMarkerIndex <em>Marker Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Marker Index</em>'.
	 * @see com.avaloq.tools.ddk.check.check.XIssueExpression#getMarkerIndex()
	 * @see #getXIssueExpression()
	 * @generated
	 */
	EReference getXIssueExpression_MarkerIndex();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Message</em>'.
	 * @see com.avaloq.tools.ddk.check.check.XIssueExpression#getMessage()
	 * @see #getXIssueExpression()
	 * @generated
	 */
	EReference getXIssueExpression_Message();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getMessageParameters <em>Message Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Parameters</em>'.
	 * @see com.avaloq.tools.ddk.check.check.XIssueExpression#getMessageParameters()
	 * @see #getXIssueExpression()
	 * @generated
	 */
	EReference getXIssueExpression_MessageParameters();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getIssueCode <em>Issue Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Issue Code</em>'.
	 * @see com.avaloq.tools.ddk.check.check.XIssueExpression#getIssueCode()
	 * @see #getXIssueExpression()
	 * @generated
	 */
	EAttribute getXIssueExpression_IssueCode();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.check.check.XIssueExpression#getIssueData <em>Issue Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Issue Data</em>'.
	 * @see com.avaloq.tools.ddk.check.check.XIssueExpression#getIssueData()
	 * @see #getXIssueExpression()
	 * @generated
	 */
	EReference getXIssueExpression_IssueData();

	/**
	 * Returns the meta object for enum '{@link com.avaloq.tools.ddk.check.check.SeverityKind <em>Severity Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Severity Kind</em>'.
	 * @see com.avaloq.tools.ddk.check.check.SeverityKind
	 * @generated
	 */
	EEnum getSeverityKind();

	/**
	 * Returns the meta object for enum '{@link com.avaloq.tools.ddk.check.check.TriggerKind <em>Trigger Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Trigger Kind</em>'.
	 * @see com.avaloq.tools.ddk.check.check.TriggerKind
	 * @generated
	 */
	EEnum getTriggerKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CheckFactory getCheckFactory();

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
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.check.check.impl.CheckCatalogImpl <em>Catalog</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.check.check.impl.CheckCatalogImpl
		 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getCheckCatalog()
		 * @generated
		 */
		EClass CHECK_CATALOG = eINSTANCE.getCheckCatalog();

		/**
		 * The meta object literal for the '<em><b>Package Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECK_CATALOG__PACKAGE_NAME = eINSTANCE.getCheckCatalog_PackageName();

		/**
		 * The meta object literal for the '<em><b>Imports</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHECK_CATALOG__IMPORTS = eINSTANCE.getCheckCatalog_Imports();

		/**
		 * The meta object literal for the '<em><b>Final</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECK_CATALOG__FINAL = eINSTANCE.getCheckCatalog_Final();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECK_CATALOG__NAME = eINSTANCE.getCheckCatalog_Name();

		/**
		 * The meta object literal for the '<em><b>Grammar</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHECK_CATALOG__GRAMMAR = eINSTANCE.getCheckCatalog_Grammar();

		/**
		 * The meta object literal for the '<em><b>Included Catalogs</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHECK_CATALOG__INCLUDED_CATALOGS = eINSTANCE.getCheckCatalog_IncludedCatalogs();

		/**
		 * The meta object literal for the '<em><b>Categories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHECK_CATALOG__CATEGORIES = eINSTANCE.getCheckCatalog_Categories();

		/**
		 * The meta object literal for the '<em><b>Implementations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHECK_CATALOG__IMPLEMENTATIONS = eINSTANCE.getCheckCatalog_Implementations();

		/**
		 * The meta object literal for the '<em><b>Checks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHECK_CATALOG__CHECKS = eINSTANCE.getCheckCatalog_Checks();

		/**
		 * The meta object literal for the '<em><b>Members</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHECK_CATALOG__MEMBERS = eINSTANCE.getCheckCatalog_Members();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.check.check.impl.DocumentedImpl <em>Documented</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.check.check.impl.DocumentedImpl
		 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getDocumented()
		 * @generated
		 */
		EClass DOCUMENTED = eINSTANCE.getDocumented();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENTED__DESCRIPTION = eINSTANCE.getDocumented_Description();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.check.check.impl.ImplicitlyNamedImpl <em>Implicitly Named</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.check.check.impl.ImplicitlyNamedImpl
		 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getImplicitlyNamed()
		 * @generated
		 */
		EClass IMPLICITLY_NAMED = eINSTANCE.getImplicitlyNamed();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPLICITLY_NAMED__NAME = eINSTANCE.getImplicitlyNamed_Name();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.check.check.impl.CategoryImpl <em>Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.check.check.impl.CategoryImpl
		 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getCategory()
		 * @generated
		 */
		EClass CATEGORY = eINSTANCE.getCategory();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CATEGORY__ID = eINSTANCE.getCategory_Id();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CATEGORY__LABEL = eINSTANCE.getCategory_Label();

		/**
		 * The meta object literal for the '<em><b>Checks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CATEGORY__CHECKS = eINSTANCE.getCategory_Checks();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.check.check.impl.CheckImpl <em>Check</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.check.check.impl.CheckImpl
		 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getCheck()
		 * @generated
		 */
		EClass CHECK = eINSTANCE.getCheck();

		/**
		 * The meta object literal for the '<em><b>Severity Range</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHECK__SEVERITY_RANGE = eINSTANCE.getCheck_SeverityRange();

		/**
		 * The meta object literal for the '<em><b>Final</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECK__FINAL = eINSTANCE.getCheck_Final();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECK__KIND = eINSTANCE.getCheck_Kind();

		/**
		 * The meta object literal for the '<em><b>Default Severity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECK__DEFAULT_SEVERITY = eINSTANCE.getCheck_DefaultSeverity();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECK__ID = eINSTANCE.getCheck_Id();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECK__LABEL = eINSTANCE.getCheck_Label();

		/**
		 * The meta object literal for the '<em><b>Formal Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHECK__FORMAL_PARAMETERS = eINSTANCE.getCheck_FormalParameters();

		/**
		 * The meta object literal for the '<em><b>Given Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECK__GIVEN_MESSAGE = eINSTANCE.getCheck_GivenMessage();

		/**
		 * The meta object literal for the '<em><b>Contexts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHECK__CONTEXTS = eINSTANCE.getCheck_Contexts();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECK__MESSAGE = eINSTANCE.getCheck_Message();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.check.check.impl.SeverityRangeImpl <em>Severity Range</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.check.check.impl.SeverityRangeImpl
		 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getSeverityRange()
		 * @generated
		 */
		EClass SEVERITY_RANGE = eINSTANCE.getSeverityRange();

		/**
		 * The meta object literal for the '<em><b>Min Severity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEVERITY_RANGE__MIN_SEVERITY = eINSTANCE.getSeverityRange_MinSeverity();

		/**
		 * The meta object literal for the '<em><b>Max Severity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEVERITY_RANGE__MAX_SEVERITY = eINSTANCE.getSeverityRange_MaxSeverity();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.check.check.impl.MemberImpl <em>Member</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.check.check.impl.MemberImpl
		 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getMember()
		 * @generated
		 */
		EClass MEMBER = eINSTANCE.getMember();

		/**
		 * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER__ANNOTATIONS = eINSTANCE.getMember_Annotations();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER__TYPE = eINSTANCE.getMember_Type();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEMBER__NAME = eINSTANCE.getMember_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER__VALUE = eINSTANCE.getMember_Value();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.check.check.impl.ImplementationImpl <em>Implementation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.check.check.impl.ImplementationImpl
		 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getImplementation()
		 * @generated
		 */
		EClass IMPLEMENTATION = eINSTANCE.getImplementation();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPLEMENTATION__NAME = eINSTANCE.getImplementation_Name();

		/**
		 * The meta object literal for the '<em><b>Context</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPLEMENTATION__CONTEXT = eINSTANCE.getImplementation_Context();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.check.check.impl.FormalParameterImpl <em>Formal Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.check.check.impl.FormalParameterImpl
		 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getFormalParameter()
		 * @generated
		 */
		EClass FORMAL_PARAMETER = eINSTANCE.getFormalParameter();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORMAL_PARAMETER__TYPE = eINSTANCE.getFormalParameter_Type();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORMAL_PARAMETER__NAME = eINSTANCE.getFormalParameter_Name();

		/**
		 * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORMAL_PARAMETER__RIGHT = eINSTANCE.getFormalParameter_Right();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORMAL_PARAMETER__LABEL = eINSTANCE.getFormalParameter_Label();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.check.check.impl.ContextImpl <em>Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.check.check.impl.ContextImpl
		 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getContext()
		 * @generated
		 */
		EClass CONTEXT = eINSTANCE.getContext();

		/**
		 * The meta object literal for the '<em><b>Context Variable</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTEXT__CONTEXT_VARIABLE = eINSTANCE.getContext_ContextVariable();

		/**
		 * The meta object literal for the '<em><b>Constraint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTEXT__CONSTRAINT = eINSTANCE.getContext_Constraint();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.check.check.impl.ContextVariableImpl <em>Context Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.check.check.impl.ContextVariableImpl
		 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getContextVariable()
		 * @generated
		 */
		EClass CONTEXT_VARIABLE = eINSTANCE.getContextVariable();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTEXT_VARIABLE__TYPE = eINSTANCE.getContextVariable_Type();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTEXT_VARIABLE__NAME = eINSTANCE.getContextVariable_Name();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.check.check.impl.XGuardExpressionImpl <em>XGuard Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.check.check.impl.XGuardExpressionImpl
		 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getXGuardExpression()
		 * @generated
		 */
		EClass XGUARD_EXPRESSION = eINSTANCE.getXGuardExpression();

		/**
		 * The meta object literal for the '<em><b>Guard</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference XGUARD_EXPRESSION__GUARD = eINSTANCE.getXGuardExpression_Guard();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.check.check.impl.XIssueExpressionImpl <em>XIssue Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.check.check.impl.XIssueExpressionImpl
		 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getXIssueExpression()
		 * @generated
		 */
		EClass XISSUE_EXPRESSION = eINSTANCE.getXIssueExpression();

		/**
		 * The meta object literal for the '<em><b>Check</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference XISSUE_EXPRESSION__CHECK = eINSTANCE.getXIssueExpression_Check();

		/**
		 * The meta object literal for the '<em><b>Marker Feature</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference XISSUE_EXPRESSION__MARKER_FEATURE = eINSTANCE.getXIssueExpression_MarkerFeature();

		/**
		 * The meta object literal for the '<em><b>Marker Object</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference XISSUE_EXPRESSION__MARKER_OBJECT = eINSTANCE.getXIssueExpression_MarkerObject();

		/**
		 * The meta object literal for the '<em><b>Marker Index</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference XISSUE_EXPRESSION__MARKER_INDEX = eINSTANCE.getXIssueExpression_MarkerIndex();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference XISSUE_EXPRESSION__MESSAGE = eINSTANCE.getXIssueExpression_Message();

		/**
		 * The meta object literal for the '<em><b>Message Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference XISSUE_EXPRESSION__MESSAGE_PARAMETERS = eINSTANCE.getXIssueExpression_MessageParameters();

		/**
		 * The meta object literal for the '<em><b>Issue Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute XISSUE_EXPRESSION__ISSUE_CODE = eINSTANCE.getXIssueExpression_IssueCode();

		/**
		 * The meta object literal for the '<em><b>Issue Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference XISSUE_EXPRESSION__ISSUE_DATA = eINSTANCE.getXIssueExpression_IssueData();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.check.check.SeverityKind <em>Severity Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.check.check.SeverityKind
		 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getSeverityKind()
		 * @generated
		 */
		EEnum SEVERITY_KIND = eINSTANCE.getSeverityKind();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.check.check.TriggerKind <em>Trigger Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.check.check.TriggerKind
		 * @see com.avaloq.tools.ddk.check.check.impl.CheckPackageImpl#getTriggerKind()
		 * @generated
		 */
		EEnum TRIGGER_KIND = eINSTANCE.getTriggerKind();

	}

} //CheckPackage
