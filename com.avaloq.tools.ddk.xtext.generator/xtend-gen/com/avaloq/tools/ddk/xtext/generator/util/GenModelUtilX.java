package com.avaloq.tools.ddk.xtext.generator.util;

import com.avaloq.tools.ddk.xtext.generator.util.GenModelUtil2;
import com.avaloq.tools.ddk.xtext.generator.util.Naming;
import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IGlobalScopeProvider;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class GenModelUtilX {
  @Inject
  @Extension
  private Naming _naming;
  
  @Inject
  private IGlobalScopeProvider globalScopeProvider;
  
  private Resource context;
  
  public Resource setResource(final Resource context) {
    return this.context = context;
  }
  
  public String qualifiedPackageInterfaceName(final EPackage it) {
    final GenPackage genPackage = this.genPackage(it);
    boolean _notEquals = (!Objects.equal(genPackage, null));
    if (_notEquals) {
      return genPackage.getQualifiedPackageInterfaceName();
    } else {
      Class<? extends EPackage> _class = it.getClass();
      boolean _notEquals_1 = (!Objects.equal(_class, EPackageImpl.class));
      if (_notEquals_1) {
        Class<? extends EPackage> _class_1 = it.getClass();
        Class<?>[] _interfaces = _class_1.getInterfaces();
        Class<?> _head = IterableExtensions.<Class<?>>head(((Iterable<Class<?>>)Conversions.doWrapArray(_interfaces)));
        return _head.getName();
      }
    }
    return null;
  }
  
  public String qualifiedSwitchClassName(final EPackage it) {
    String _xblockexpression = null;
    {
      final GenPackage genPackage = this.genPackage(it);
      String _xifexpression = null;
      if (((!Objects.equal(genPackage, null)) && genPackage.isLiteralsInterface())) {
        String _utilitiesPackageName = genPackage.getUtilitiesPackageName();
        String _plus = (_utilitiesPackageName + ".");
        String _switchClassName = genPackage.getSwitchClassName();
        _xifexpression = (_plus + _switchClassName);
      } else {
        String _qualifiedPackageInterfaceName = this.qualifiedPackageInterfaceName(it);
        String _javaPackage = this._naming.toJavaPackage(_qualifiedPackageInterfaceName);
        String _plus_1 = (_javaPackage + ".util.");
        String _name = it.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name);
        String _plus_2 = (_plus_1 + _firstUpper);
        _xifexpression = (_plus_2 + "Switch");
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  protected String _literalIdentifier(final EStructuralFeature it) {
    String _xblockexpression = null;
    {
      final EClass eClass = it.getEContainingClass();
      final EPackage ePackage = eClass.getEPackage();
      final GenPackage genPackage = this.genPackage(ePackage);
      String _xifexpression = null;
      if (((!Objects.equal(genPackage, null)) && genPackage.isLiteralsInterface())) {
        String _literalIdentifier = this.literalIdentifier(eClass);
        String _plus = (_literalIdentifier + "__");
        String _name = it.getName();
        String _format = this.format(_name);
        String _upperCase = _format.toUpperCase();
        _xifexpression = (_plus + _upperCase);
      } else {
        String _qualifiedPackageInterfaceName = this.qualifiedPackageInterfaceName(ePackage);
        String _plus_1 = (_qualifiedPackageInterfaceName + ".eINSTANCE.get");
        String _name_1 = eClass.getName();
        String _plus_2 = (_plus_1 + _name_1);
        String _plus_3 = (_plus_2 + "_");
        String _name_2 = it.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_2);
        String _plus_4 = (_plus_3 + _firstUpper);
        _xifexpression = (_plus_4 + "()");
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  protected String _literalIdentifier(final EClass it) {
    String _xblockexpression = null;
    {
      EPackage _ePackage = it.getEPackage();
      final GenPackage genPackage = this.genPackage(_ePackage);
      String _xifexpression = null;
      if (((!Objects.equal(genPackage, null)) && genPackage.isLiteralsInterface())) {
        String _qualifiedPackageInterfaceName = genPackage.getQualifiedPackageInterfaceName();
        String _plus = (_qualifiedPackageInterfaceName + ".Literals.");
        String _name = it.getName();
        String _format = this.format(_name);
        String _upperCase = _format.toUpperCase();
        _xifexpression = (_plus + _upperCase);
      } else {
        EPackage _ePackage_1 = it.getEPackage();
        String _qualifiedPackageInterfaceName_1 = this.qualifiedPackageInterfaceName(_ePackage_1);
        String _plus_1 = (_qualifiedPackageInterfaceName_1 + ".eINSTANCE.get");
        String _name_1 = it.getName();
        String _plus_2 = (_plus_1 + _name_1);
        _xifexpression = (_plus_2 + "()");
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  protected String _literalIdentifier(final ENamedElement it) {
    return "DOES_NOT_EXIST";
  }
  
  protected String _literalIdentifier(final Void it) {
    return "DOES_NOT_EXIST";
  }
  
  public String classifierIdLiteral(final EClass it) {
    EPackage _ePackage = it.getEPackage();
    String _qualifiedPackageInterfaceName = this.qualifiedPackageInterfaceName(_ePackage);
    String _plus = (_qualifiedPackageInterfaceName + ".");
    String _name = it.getName();
    String _format = this.format(_name);
    String _upperCase = _format.toUpperCase();
    return (_plus + _upperCase);
  }
  
  protected String _instanceClassName(final Void it) {
    return "";
  }
  
  protected String _instanceClassName(final EClassifier it) {
    String _instanceClassName = it.getInstanceClassName();
    boolean _notEquals = (!Objects.equal(_instanceClassName, null));
    if (_notEquals) {
      return it.getInstanceClassName();
    }
    return it.getName();
  }
  
  protected String _instanceClassName(final EDataType it) {
    String _instanceClassName = it.getInstanceClassName();
    boolean _notEquals = (!Objects.equal(_instanceClassName, null));
    if (_notEquals) {
      return it.getInstanceClassName();
    }
    GenDataType _genDataType = this.genDataType(it);
    return _genDataType.getQualifiedInstanceClassName();
  }
  
  protected String _instanceClassName(final EClass it) {
    String _instanceClassName = it.getInstanceClassName();
    boolean _notEquals = (!Objects.equal(_instanceClassName, null));
    if (_notEquals) {
      return it.getInstanceClassName();
    }
    GenClass _genClass = this.genClass(it);
    return _genClass.getQualifiedInterfaceName();
  }
  
  public GenPackage genPackage(final EModelElement it) {
    final EPackage ePackage = EcoreUtil2.<EPackage>getContainerOfType(it, EPackage.class);
    final IScope scope = this.globalScopeProvider.getScope(this.context, GenModelPackage.Literals.GEN_MODEL__GEN_PACKAGES, null);
    if (((!Objects.equal(scope, null)) && (!Objects.equal(ePackage, null)))) {
      String _nsURI = ePackage.getNsURI();
      QualifiedName _create = QualifiedName.create(_nsURI);
      final IEObjectDescription desc = scope.getSingleElement(_create);
      boolean _notEquals = (!Objects.equal(desc, null));
      if (_notEquals) {
        EObject _eObjectOrProxy = desc.getEObjectOrProxy();
        EObject _resolve = EcoreUtil.resolve(_eObjectOrProxy, this.context);
        return ((GenPackage) _resolve);
      }
    }
    GenPackage _xifexpression = null;
    boolean _notEquals_1 = (!Objects.equal(ePackage, null));
    if (_notEquals_1) {
      ResourceSet _resourceSet = this.context.getResourceSet();
      _xifexpression = GenModelUtil2.findGenPackage(ePackage, _resourceSet);
    } else {
      _xifexpression = null;
    }
    return _xifexpression;
  }
  
  public GenModel genModel(final EModelElement it) {
    final GenPackage genPackage = this.genPackage(it);
    GenModel _xifexpression = null;
    boolean _notEquals = (!Objects.equal(genPackage, null));
    if (_notEquals) {
      _xifexpression = genPackage.getGenModel();
    } else {
      _xifexpression = null;
    }
    return _xifexpression;
  }
  
  public GenClass genClass(final EClass it) {
    final GenPackage genPackage = this.genPackage(it);
    GenClass _xifexpression = null;
    boolean _notEquals = (!Objects.equal(genPackage, null));
    if (_notEquals) {
      GenModel _genModel = genPackage.getGenModel();
      GenClassifier _findGenClassifier = _genModel.findGenClassifier(it);
      _xifexpression = ((GenClass) _findGenClassifier);
    } else {
      _xifexpression = null;
    }
    return _xifexpression;
  }
  
  public GenDataType genDataType(final EDataType it) {
    final GenPackage genPackage = this.genPackage(it);
    GenDataType _xifexpression = null;
    boolean _notEquals = (!Objects.equal(genPackage, null));
    if (_notEquals) {
      GenModel _genModel = genPackage.getGenModel();
      GenClassifier _findGenClassifier = _genModel.findGenClassifier(it);
      _xifexpression = ((GenDataType) _findGenClassifier);
    } else {
      _xifexpression = null;
    }
    return _xifexpression;
  }
  
  public String format(final String name) {
    return GenModelUtil2.format(name);
  }
  
  public String literalIdentifier(final ENamedElement it) {
    if (it instanceof EClass) {
      return _literalIdentifier((EClass)it);
    } else if (it instanceof EStructuralFeature) {
      return _literalIdentifier((EStructuralFeature)it);
    } else if (it != null) {
      return _literalIdentifier(it);
    } else if (it == null) {
      return _literalIdentifier((Void)null);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it).toString());
    }
  }
  
  public String instanceClassName(final EClassifier it) {
    if (it instanceof EClass) {
      return _instanceClassName((EClass)it);
    } else if (it instanceof EDataType) {
      return _instanceClassName((EDataType)it);
    } else if (it != null) {
      return _instanceClassName(it);
    } else if (it == null) {
      return _instanceClassName((Void)null);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it).toString());
    }
  }
}
