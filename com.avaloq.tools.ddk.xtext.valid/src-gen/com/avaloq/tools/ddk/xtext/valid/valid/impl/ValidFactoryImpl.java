/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid.impl;

import com.avaloq.tools.ddk.xtext.valid.valid.*;

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
public class ValidFactoryImpl extends EFactoryImpl implements ValidFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ValidFactory init()
  {
    try
    {
      ValidFactory theValidFactory = (ValidFactory)EPackage.Registry.INSTANCE.getEFactory(ValidPackage.eNS_URI);
      if (theValidFactory != null)
      {
        return theValidFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ValidFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ValidFactoryImpl()
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
      case ValidPackage.VALID_MODEL: return createValidModel();
      case ValidPackage.IMPORT: return createImport();
      case ValidPackage.CATEGORY: return createCategory();
      case ValidPackage.RULE: return createRule();
      case ValidPackage.PREDEFINED_RULE: return createPredefinedRule();
      case ValidPackage.NATIVE_RULE: return createNativeRule();
      case ValidPackage.SIZE_RULE: return createSizeRule();
      case ValidPackage.RANGE_RULE: return createRangeRule();
      case ValidPackage.UNIQUE_RULE: return createUniqueRule();
      case ValidPackage.CONTEXT: return createContext();
      case ValidPackage.SIMPLE_CONTEXT: return createSimpleContext();
      case ValidPackage.DUPLICATE_CONTEXT: return createDuplicateContext();
      case ValidPackage.NATIVE_CONTEXT: return createNativeContext();
      case ValidPackage.QUICK_FIX: return createQuickFix();
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
      case ValidPackage.CHECK_KIND:
        return createCheckKindFromString(eDataType, initialValue);
      case ValidPackage.SEVERITY_KIND:
        return createSeverityKindFromString(eDataType, initialValue);
      case ValidPackage.QUICK_FIX_KIND:
        return createQuickFixKindFromString(eDataType, initialValue);
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
      case ValidPackage.CHECK_KIND:
        return convertCheckKindToString(eDataType, instanceValue);
      case ValidPackage.SEVERITY_KIND:
        return convertSeverityKindToString(eDataType, instanceValue);
      case ValidPackage.QUICK_FIX_KIND:
        return convertQuickFixKindToString(eDataType, instanceValue);
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
  public ValidModel createValidModel()
  {
    ValidModelImpl validModel = new ValidModelImpl();
    return validModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Import createImport()
  {
    ImportImpl import_ = new ImportImpl();
    return import_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Category createCategory()
  {
    CategoryImpl category = new CategoryImpl();
    return category;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Rule createRule()
  {
    RuleImpl rule = new RuleImpl();
    return rule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public PredefinedRule createPredefinedRule()
  {
    PredefinedRuleImpl predefinedRule = new PredefinedRuleImpl();
    return predefinedRule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NativeRule createNativeRule()
  {
    NativeRuleImpl nativeRule = new NativeRuleImpl();
    return nativeRule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public SizeRule createSizeRule()
  {
    SizeRuleImpl sizeRule = new SizeRuleImpl();
    return sizeRule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public RangeRule createRangeRule()
  {
    RangeRuleImpl rangeRule = new RangeRuleImpl();
    return rangeRule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public UniqueRule createUniqueRule()
  {
    UniqueRuleImpl uniqueRule = new UniqueRuleImpl();
    return uniqueRule;
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
  public SimpleContext createSimpleContext()
  {
    SimpleContextImpl simpleContext = new SimpleContextImpl();
    return simpleContext;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public DuplicateContext createDuplicateContext()
  {
    DuplicateContextImpl duplicateContext = new DuplicateContextImpl();
    return duplicateContext;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NativeContext createNativeContext()
  {
    NativeContextImpl nativeContext = new NativeContextImpl();
    return nativeContext;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public QuickFix createQuickFix()
  {
    QuickFixImpl quickFix = new QuickFixImpl();
    return quickFix;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CheckKind createCheckKindFromString(EDataType eDataType, String initialValue)
  {
    CheckKind result = CheckKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertCheckKindToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
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
  public QuickFixKind createQuickFixKindFromString(EDataType eDataType, String initialValue)
  {
    QuickFixKind result = QuickFixKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertQuickFixKindToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ValidPackage getValidPackage()
  {
    return (ValidPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static ValidPackage getPackage()
  {
    return ValidPackage.eINSTANCE;
  }

} //ValidFactoryImpl
