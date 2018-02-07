/**
 */
package com.avaloq.tools.ddk.xtext.export.export;

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
 * @see com.avaloq.tools.ddk.xtext.export.export.ExportFactory
 * @model kind="package"
 * @generated
 */
public interface ExportPackage extends EPackage
{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "export";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.avaloq.com/tools/ddk/xtext/export/Export";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "export";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExportPackage eINSTANCE = com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportModelImpl
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getExportModel()
	 * @generated
	 */
	int EXPORT_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Extension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT_MODEL__EXTENSION = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT_MODEL__NAME = 1;

	/**
	 * The feature id for the '<em><b>Target Grammar</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT_MODEL__TARGET_GRAMMAR = 2;

	/**
	 * The feature id for the '<em><b>Imports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT_MODEL__IMPORTS = 3;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT_MODEL__EXTENSIONS = 4;

	/**
	 * The feature id for the '<em><b>Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT_MODEL__INTERFACES = 5;

	/**
	 * The feature id for the '<em><b>Exports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT_MODEL__EXPORTS = 6;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT_MODEL_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.ImportImpl <em>Import</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ImportImpl
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getImport()
	 * @generated
	 */
	int IMPORT = 1;

	/**
	 * The feature id for the '<em><b>Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT__PACKAGE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT__NAME = 1;

	/**
	 * The number of structural features of the '<em>Import</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExtensionImpl <em>Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExtensionImpl
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getExtension()
	 * @generated
	 */
	int EXTENSION = 2;

	/**
	 * The feature id for the '<em><b>Extension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSION__EXTENSION = 0;

	/**
	 * The number of structural features of the '<em>Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.DeclarationForTypeImpl <em>Declaration For Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.DeclarationForTypeImpl
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getDeclarationForType()
	 * @generated
	 */
	int DECLARATION_FOR_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION_FOR_TYPE__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Guard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION_FOR_TYPE__GUARD = 1;

	/**
	 * The number of structural features of the '<em>Declaration For Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION_FOR_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceImpl <em>Interface</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceImpl
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getInterface()
	 * @generated
	 */
	int INTERFACE = 4;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE__TYPE = DECLARATION_FOR_TYPE__TYPE;

	/**
	 * The feature id for the '<em><b>Guard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE__GUARD = DECLARATION_FOR_TYPE__GUARD;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE__ITEMS = DECLARATION_FOR_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Interface</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_FEATURE_COUNT = DECLARATION_FOR_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceItemImpl <em>Interface Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceItemImpl
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getInterfaceItem()
	 * @generated
	 */
	int INTERFACE_ITEM = 5;

	/**
	 * The feature id for the '<em><b>Unordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_ITEM__UNORDERED = 0;

	/**
	 * The number of structural features of the '<em>Interface Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_ITEM_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceFieldImpl <em>Interface Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceFieldImpl
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getInterfaceField()
	 * @generated
	 */
	int INTERFACE_FIELD = 6;

	/**
	 * The feature id for the '<em><b>Unordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_FIELD__UNORDERED = INTERFACE_ITEM__UNORDERED;

	/**
	 * The feature id for the '<em><b>Field</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_FIELD__FIELD = INTERFACE_ITEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Interface Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_FIELD_FEATURE_COUNT = INTERFACE_ITEM_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceNavigationImpl <em>Interface Navigation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceNavigationImpl
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getInterfaceNavigation()
	 * @generated
	 */
	int INTERFACE_NAVIGATION = 7;

	/**
	 * The feature id for the '<em><b>Unordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_NAVIGATION__UNORDERED = INTERFACE_ITEM__UNORDERED;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_NAVIGATION__REF = INTERFACE_ITEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Interface Navigation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_NAVIGATION_FEATURE_COUNT = INTERFACE_ITEM_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceExpressionImpl <em>Interface Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceExpressionImpl
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getInterfaceExpression()
	 * @generated
	 */
	int INTERFACE_EXPRESSION = 8;

	/**
	 * The feature id for the '<em><b>Unordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_EXPRESSION__UNORDERED = INTERFACE_ITEM__UNORDERED;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_EXPRESSION__REF = INTERFACE_ITEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expr</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_EXPRESSION__EXPR = INTERFACE_ITEM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Interface Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_EXPRESSION_FEATURE_COUNT = INTERFACE_ITEM_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportImpl <em>Export</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportImpl
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getExport()
	 * @generated
	 */
	int EXPORT = 9;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__TYPE = DECLARATION_FOR_TYPE__TYPE;

	/**
	 * The feature id for the '<em><b>Guard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__GUARD = DECLARATION_FOR_TYPE__GUARD;

	/**
	 * The feature id for the '<em><b>Lookup</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__LOOKUP = DECLARATION_FOR_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Lookup Predicate</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__LOOKUP_PREDICATE = DECLARATION_FOR_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__QUALIFIED_NAME = DECLARATION_FOR_TYPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Naming</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__NAMING = DECLARATION_FOR_TYPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Fragment Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__FRAGMENT_UNIQUE = DECLARATION_FOR_TYPE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Fragment Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__FRAGMENT_ATTRIBUTE = DECLARATION_FOR_TYPE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Fingerprint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__FINGERPRINT = DECLARATION_FOR_TYPE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Resource Fingerprint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__RESOURCE_FINGERPRINT = DECLARATION_FOR_TYPE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__ATTRIBUTES = DECLARATION_FOR_TYPE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>User Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__USER_DATA = DECLARATION_FOR_TYPE_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Export</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT_FEATURE_COUNT = DECLARATION_FOR_TYPE_FEATURE_COUNT + 10;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.UserDataImpl <em>User Data</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.UserDataImpl
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getUserData()
	 * @generated
	 */
	int USER_DATA = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DATA__NAME = 0;

	/**
	 * The feature id for the '<em><b>Expr</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DATA__EXPR = 1;

	/**
	 * The number of structural features of the '<em>User Data</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DATA_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.AttributeImpl
	 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 11;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__ATTRIBUTE = 0;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportModel
	 * @generated
	 */
	EClass getExportModel();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#isExtension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extension</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportModel#isExtension()
	 * @see #getExportModel()
	 * @generated
	 */
	EAttribute getExportModel_Extension();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportModel#getName()
	 * @see #getExportModel()
	 * @generated
	 */
	EAttribute getExportModel_Name();

	/**
	 * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#getTargetGrammar <em>Target Grammar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target Grammar</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportModel#getTargetGrammar()
	 * @see #getExportModel()
	 * @generated
	 */
	EReference getExportModel_TargetGrammar();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#getImports <em>Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Imports</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportModel#getImports()
	 * @see #getExportModel()
	 * @generated
	 */
	EReference getExportModel_Imports();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#getExtensions <em>Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extensions</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportModel#getExtensions()
	 * @see #getExportModel()
	 * @generated
	 */
	EReference getExportModel_Extensions();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#getInterfaces <em>Interfaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Interfaces</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportModel#getInterfaces()
	 * @see #getExportModel()
	 * @generated
	 */
	EReference getExportModel_Interfaces();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.export.export.ExportModel#getExports <em>Exports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exports</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.ExportModel#getExports()
	 * @see #getExportModel()
	 * @generated
	 */
	EReference getExportModel_Exports();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.export.export.Import <em>Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Import</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Import
	 * @generated
	 */
	EClass getImport();

	/**
	 * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.export.export.Import#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Package</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Import#getPackage()
	 * @see #getImport()
	 * @generated
	 */
	EReference getImport_Package();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.export.export.Import#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Import#getName()
	 * @see #getImport()
	 * @generated
	 */
	EAttribute getImport_Name();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.export.export.Extension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extension</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Extension
	 * @generated
	 */
	EClass getExtension();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.export.export.Extension#getExtension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extension</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Extension#getExtension()
	 * @see #getExtension()
	 * @generated
	 */
	EAttribute getExtension_Extension();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.export.export.DeclarationForType <em>Declaration For Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Declaration For Type</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.DeclarationForType
	 * @generated
	 */
	EClass getDeclarationForType();

	/**
	 * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.export.export.DeclarationForType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.DeclarationForType#getType()
	 * @see #getDeclarationForType()
	 * @generated
	 */
	EReference getDeclarationForType_Type();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.export.export.DeclarationForType#getGuard <em>Guard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Guard</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.DeclarationForType#getGuard()
	 * @see #getDeclarationForType()
	 * @generated
	 */
	EReference getDeclarationForType_Guard();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.export.export.Interface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interface</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Interface
	 * @generated
	 */
	EClass getInterface();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.export.export.Interface#getItems <em>Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Items</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Interface#getItems()
	 * @see #getInterface()
	 * @generated
	 */
	EReference getInterface_Items();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceItem <em>Interface Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interface Item</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.InterfaceItem
	 * @generated
	 */
	EClass getInterfaceItem();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceItem#isUnordered <em>Unordered</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unordered</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.InterfaceItem#isUnordered()
	 * @see #getInterfaceItem()
	 * @generated
	 */
	EAttribute getInterfaceItem_Unordered();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceField <em>Interface Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interface Field</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.InterfaceField
	 * @generated
	 */
	EClass getInterfaceField();

	/**
	 * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceField#getField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Field</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.InterfaceField#getField()
	 * @see #getInterfaceField()
	 * @generated
	 */
	EReference getInterfaceField_Field();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceNavigation <em>Interface Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interface Navigation</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.InterfaceNavigation
	 * @generated
	 */
	EClass getInterfaceNavigation();

	/**
	 * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceNavigation#getRef <em>Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ref</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.InterfaceNavigation#getRef()
	 * @see #getInterfaceNavigation()
	 * @generated
	 */
	EReference getInterfaceNavigation_Ref();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression <em>Interface Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interface Expression</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression
	 * @generated
	 */
	EClass getInterfaceExpression();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression#isRef <em>Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ref</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression#isRef()
	 * @see #getInterfaceExpression()
	 * @generated
	 */
	EAttribute getInterfaceExpression_Ref();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression#getExpr <em>Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expr</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression#getExpr()
	 * @see #getInterfaceExpression()
	 * @generated
	 */
	EReference getInterfaceExpression_Expr();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.export.export.Export <em>Export</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Export</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Export
	 * @generated
	 */
	EClass getExport();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isLookup <em>Lookup</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lookup</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Export#isLookup()
	 * @see #getExport()
	 * @generated
	 */
	EAttribute getExport_Lookup();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.export.export.Export#getLookupPredicate <em>Lookup Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Lookup Predicate</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Export#getLookupPredicate()
	 * @see #getExport()
	 * @generated
	 */
	EReference getExport_LookupPredicate();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isQualifiedName <em>Qualified Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Qualified Name</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Export#isQualifiedName()
	 * @see #getExport()
	 * @generated
	 */
	EAttribute getExport_QualifiedName();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.export.export.Export#getNaming <em>Naming</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Naming</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Export#getNaming()
	 * @see #getExport()
	 * @generated
	 */
	EReference getExport_Naming();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isFragmentUnique <em>Fragment Unique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fragment Unique</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Export#isFragmentUnique()
	 * @see #getExport()
	 * @generated
	 */
	EAttribute getExport_FragmentUnique();

	/**
	 * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.export.export.Export#getFragmentAttribute <em>Fragment Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Fragment Attribute</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Export#getFragmentAttribute()
	 * @see #getExport()
	 * @generated
	 */
	EReference getExport_FragmentAttribute();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isFingerprint <em>Fingerprint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fingerprint</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Export#isFingerprint()
	 * @see #getExport()
	 * @generated
	 */
	EAttribute getExport_Fingerprint();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.export.export.Export#isResourceFingerprint <em>Resource Fingerprint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Fingerprint</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Export#isResourceFingerprint()
	 * @see #getExport()
	 * @generated
	 */
	EAttribute getExport_ResourceFingerprint();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.export.export.Export#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Export#getAttributes()
	 * @see #getExport()
	 * @generated
	 */
	EReference getExport_Attributes();

	/**
	 * Returns the meta object for the containment reference list '{@link com.avaloq.tools.ddk.xtext.export.export.Export#getUserData <em>User Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>User Data</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Export#getUserData()
	 * @see #getExport()
	 * @generated
	 */
	EReference getExport_UserData();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.export.export.UserData <em>User Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Data</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.UserData
	 * @generated
	 */
	EClass getUserData();

	/**
	 * Returns the meta object for the attribute '{@link com.avaloq.tools.ddk.xtext.export.export.UserData#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.UserData#getName()
	 * @see #getUserData()
	 * @generated
	 */
	EAttribute getUserData_Name();

	/**
	 * Returns the meta object for the containment reference '{@link com.avaloq.tools.ddk.xtext.export.export.UserData#getExpr <em>Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expr</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.UserData#getExpr()
	 * @see #getUserData()
	 * @generated
	 */
	EReference getUserData_Expr();

	/**
	 * Returns the meta object for class '{@link com.avaloq.tools.ddk.xtext.export.export.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Attribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for the reference '{@link com.avaloq.tools.ddk.xtext.export.export.Attribute#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Attribute</em>'.
	 * @see com.avaloq.tools.ddk.xtext.export.export.Attribute#getAttribute()
	 * @see #getAttribute()
	 * @generated
	 */
	EReference getAttribute_Attribute();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ExportFactory getExportFactory();

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
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportModelImpl
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getExportModel()
		 * @generated
		 */
		EClass EXPORT_MODEL = eINSTANCE.getExportModel();

		/**
		 * The meta object literal for the '<em><b>Extension</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPORT_MODEL__EXTENSION = eINSTANCE.getExportModel_Extension();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPORT_MODEL__NAME = eINSTANCE.getExportModel_Name();

		/**
		 * The meta object literal for the '<em><b>Target Grammar</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPORT_MODEL__TARGET_GRAMMAR = eINSTANCE.getExportModel_TargetGrammar();

		/**
		 * The meta object literal for the '<em><b>Imports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPORT_MODEL__IMPORTS = eINSTANCE.getExportModel_Imports();

		/**
		 * The meta object literal for the '<em><b>Extensions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPORT_MODEL__EXTENSIONS = eINSTANCE.getExportModel_Extensions();

		/**
		 * The meta object literal for the '<em><b>Interfaces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPORT_MODEL__INTERFACES = eINSTANCE.getExportModel_Interfaces();

		/**
		 * The meta object literal for the '<em><b>Exports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPORT_MODEL__EXPORTS = eINSTANCE.getExportModel_Exports();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.ImportImpl <em>Import</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ImportImpl
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getImport()
		 * @generated
		 */
		EClass IMPORT = eINSTANCE.getImport();

		/**
		 * The meta object literal for the '<em><b>Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPORT__PACKAGE = eINSTANCE.getImport_Package();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPORT__NAME = eINSTANCE.getImport_Name();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExtensionImpl <em>Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExtensionImpl
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getExtension()
		 * @generated
		 */
		EClass EXTENSION = eINSTANCE.getExtension();

		/**
		 * The meta object literal for the '<em><b>Extension</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENSION__EXTENSION = eINSTANCE.getExtension_Extension();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.DeclarationForTypeImpl <em>Declaration For Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.DeclarationForTypeImpl
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getDeclarationForType()
		 * @generated
		 */
		EClass DECLARATION_FOR_TYPE = eINSTANCE.getDeclarationForType();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECLARATION_FOR_TYPE__TYPE = eINSTANCE.getDeclarationForType_Type();

		/**
		 * The meta object literal for the '<em><b>Guard</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECLARATION_FOR_TYPE__GUARD = eINSTANCE.getDeclarationForType_Guard();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceImpl <em>Interface</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceImpl
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getInterface()
		 * @generated
		 */
		EClass INTERFACE = eINSTANCE.getInterface();

		/**
		 * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERFACE__ITEMS = eINSTANCE.getInterface_Items();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceItemImpl <em>Interface Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceItemImpl
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getInterfaceItem()
		 * @generated
		 */
		EClass INTERFACE_ITEM = eINSTANCE.getInterfaceItem();

		/**
		 * The meta object literal for the '<em><b>Unordered</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERFACE_ITEM__UNORDERED = eINSTANCE.getInterfaceItem_Unordered();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceFieldImpl <em>Interface Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceFieldImpl
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getInterfaceField()
		 * @generated
		 */
		EClass INTERFACE_FIELD = eINSTANCE.getInterfaceField();

		/**
		 * The meta object literal for the '<em><b>Field</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERFACE_FIELD__FIELD = eINSTANCE.getInterfaceField_Field();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceNavigationImpl <em>Interface Navigation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceNavigationImpl
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getInterfaceNavigation()
		 * @generated
		 */
		EClass INTERFACE_NAVIGATION = eINSTANCE.getInterfaceNavigation();

		/**
		 * The meta object literal for the '<em><b>Ref</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERFACE_NAVIGATION__REF = eINSTANCE.getInterfaceNavigation_Ref();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceExpressionImpl <em>Interface Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.InterfaceExpressionImpl
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getInterfaceExpression()
		 * @generated
		 */
		EClass INTERFACE_EXPRESSION = eINSTANCE.getInterfaceExpression();

		/**
		 * The meta object literal for the '<em><b>Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERFACE_EXPRESSION__REF = eINSTANCE.getInterfaceExpression_Ref();

		/**
		 * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERFACE_EXPRESSION__EXPR = eINSTANCE.getInterfaceExpression_Expr();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.ExportImpl <em>Export</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportImpl
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getExport()
		 * @generated
		 */
		EClass EXPORT = eINSTANCE.getExport();

		/**
		 * The meta object literal for the '<em><b>Lookup</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPORT__LOOKUP = eINSTANCE.getExport_Lookup();

		/**
		 * The meta object literal for the '<em><b>Lookup Predicate</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPORT__LOOKUP_PREDICATE = eINSTANCE.getExport_LookupPredicate();

		/**
		 * The meta object literal for the '<em><b>Qualified Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPORT__QUALIFIED_NAME = eINSTANCE.getExport_QualifiedName();

		/**
		 * The meta object literal for the '<em><b>Naming</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPORT__NAMING = eINSTANCE.getExport_Naming();

		/**
		 * The meta object literal for the '<em><b>Fragment Unique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPORT__FRAGMENT_UNIQUE = eINSTANCE.getExport_FragmentUnique();

		/**
		 * The meta object literal for the '<em><b>Fragment Attribute</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPORT__FRAGMENT_ATTRIBUTE = eINSTANCE.getExport_FragmentAttribute();

		/**
		 * The meta object literal for the '<em><b>Fingerprint</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPORT__FINGERPRINT = eINSTANCE.getExport_Fingerprint();

		/**
		 * The meta object literal for the '<em><b>Resource Fingerprint</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPORT__RESOURCE_FINGERPRINT = eINSTANCE.getExport_ResourceFingerprint();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPORT__ATTRIBUTES = eINSTANCE.getExport_Attributes();

		/**
		 * The meta object literal for the '<em><b>User Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPORT__USER_DATA = eINSTANCE.getExport_UserData();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.UserDataImpl <em>User Data</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.UserDataImpl
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getUserData()
		 * @generated
		 */
		EClass USER_DATA = eINSTANCE.getUserData();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_DATA__NAME = eINSTANCE.getUserData_Name();

		/**
		 * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_DATA__EXPR = eINSTANCE.getUserData_Expr();

		/**
		 * The meta object literal for the '{@link com.avaloq.tools.ddk.xtext.export.export.impl.AttributeImpl <em>Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.AttributeImpl
		 * @see com.avaloq.tools.ddk.xtext.export.export.impl.ExportPackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTE__ATTRIBUTE = eINSTANCE.getAttribute_Attribute();

	}

} //ExportPackage
