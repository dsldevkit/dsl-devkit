/**
 */
package com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.impl;

import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.*;

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
public class FormatterTestLanguageFactoryImpl extends EFactoryImpl implements FormatterTestLanguageFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static FormatterTestLanguageFactory init()
  {
    try
    {
      FormatterTestLanguageFactory theFormatterTestLanguageFactory = (FormatterTestLanguageFactory)EPackage.Registry.INSTANCE.getEFactory(FormatterTestLanguagePackage.eNS_URI);
      if (theFormatterTestLanguageFactory != null)
      {
        return theFormatterTestLanguageFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new FormatterTestLanguageFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FormatterTestLanguageFactoryImpl()
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
      case FormatterTestLanguagePackage.ROOT: return createRoot();
      case FormatterTestLanguagePackage.LINE: return createLine();
      case FormatterTestLanguagePackage.DECL: return createDecl();
      case FormatterTestLanguagePackage.ASSIGN: return createAssign();
      case FormatterTestLanguagePackage.METH: return createMeth();
      case FormatterTestLanguagePackage.PARAM: return createParam();
      case FormatterTestLanguagePackage.SPACE: return createSpace();
      case FormatterTestLanguagePackage.TEST_LINEWRAP: return createTestLinewrap();
      case FormatterTestLanguagePackage.TEST_LINEWRAP_MIN_MAX: return createTestLinewrapMinMax();
      case FormatterTestLanguagePackage.TEST_INDENTATION: return createTestIndentation();
      case FormatterTestLanguagePackage.TEST_COLUMN: return createTestColumn();
      case FormatterTestLanguagePackage.TEST_OFFSET: return createTestOffset();
      case FormatterTestLanguagePackage.TEST_RIGHT_PADDING: return createTestRightPadding();
      case FormatterTestLanguagePackage.FQN_OBJ: return createFqnObj();
      case FormatterTestLanguagePackage.FQN_REF: return createFqnRef();
      case FormatterTestLanguagePackage.ENUMERATION: return createEnumeration();
      case FormatterTestLanguagePackage.SUPPRESSED_HIDDEN: return createSuppressedHidden();
      case FormatterTestLanguagePackage.SUPPRESSED_HIDDEN_SUB: return createSuppressedHiddenSub();
      case FormatterTestLanguagePackage.SUPPRESSED_HIDDEN_SUB_SUB: return createSuppressedHiddenSubSub();
      case FormatterTestLanguagePackage.SUPPRESSED_HIDDEN_SUB_ID: return createSuppressedHiddenSubID();
      case FormatterTestLanguagePackage.DATATYPES: return createDatatypes();
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
      case FormatterTestLanguagePackage.ENUM1:
        return createEnum1FromString(eDataType, initialValue);
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
      case FormatterTestLanguagePackage.ENUM1:
        return convertEnum1ToString(eDataType, instanceValue);
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
  public Root createRoot()
  {
    RootImpl root = new RootImpl();
    return root;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Line createLine()
  {
    LineImpl line = new LineImpl();
    return line;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Decl createDecl()
  {
    DeclImpl decl = new DeclImpl();
    return decl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Assign createAssign()
  {
    AssignImpl assign = new AssignImpl();
    return assign;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Meth createMeth()
  {
    MethImpl meth = new MethImpl();
    return meth;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Param createParam()
  {
    ParamImpl param = new ParamImpl();
    return param;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Space createSpace()
  {
    SpaceImpl space = new SpaceImpl();
    return space;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public TestLinewrap createTestLinewrap()
  {
    TestLinewrapImpl testLinewrap = new TestLinewrapImpl();
    return testLinewrap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public TestLinewrapMinMax createTestLinewrapMinMax()
  {
    TestLinewrapMinMaxImpl testLinewrapMinMax = new TestLinewrapMinMaxImpl();
    return testLinewrapMinMax;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public TestIndentation createTestIndentation()
  {
    TestIndentationImpl testIndentation = new TestIndentationImpl();
    return testIndentation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public TestColumn createTestColumn()
  {
    TestColumnImpl testColumn = new TestColumnImpl();
    return testColumn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public TestOffset createTestOffset()
  {
    TestOffsetImpl testOffset = new TestOffsetImpl();
    return testOffset;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public TestRightPadding createTestRightPadding()
  {
    TestRightPaddingImpl testRightPadding = new TestRightPaddingImpl();
    return testRightPadding;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public FqnObj createFqnObj()
  {
    FqnObjImpl fqnObj = new FqnObjImpl();
    return fqnObj;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public FqnRef createFqnRef()
  {
    FqnRefImpl fqnRef = new FqnRefImpl();
    return fqnRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Enumeration createEnumeration()
  {
    EnumerationImpl enumeration = new EnumerationImpl();
    return enumeration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public SuppressedHidden createSuppressedHidden()
  {
    SuppressedHiddenImpl suppressedHidden = new SuppressedHiddenImpl();
    return suppressedHidden;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public SuppressedHiddenSub createSuppressedHiddenSub()
  {
    SuppressedHiddenSubImpl suppressedHiddenSub = new SuppressedHiddenSubImpl();
    return suppressedHiddenSub;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public SuppressedHiddenSubSub createSuppressedHiddenSubSub()
  {
    SuppressedHiddenSubSubImpl suppressedHiddenSubSub = new SuppressedHiddenSubSubImpl();
    return suppressedHiddenSubSub;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public SuppressedHiddenSubID createSuppressedHiddenSubID()
  {
    SuppressedHiddenSubIDImpl suppressedHiddenSubID = new SuppressedHiddenSubIDImpl();
    return suppressedHiddenSubID;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Datatypes createDatatypes()
  {
    DatatypesImpl datatypes = new DatatypesImpl();
    return datatypes;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Enum1 createEnum1FromString(EDataType eDataType, String initialValue)
  {
    Enum1 result = Enum1.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertEnum1ToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public FormatterTestLanguagePackage getFormatterTestLanguagePackage()
  {
    return (FormatterTestLanguagePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static FormatterTestLanguagePackage getPackage()
  {
    return FormatterTestLanguagePackage.eINSTANCE;
  }

} //FormatterTestLanguageFactoryImpl
