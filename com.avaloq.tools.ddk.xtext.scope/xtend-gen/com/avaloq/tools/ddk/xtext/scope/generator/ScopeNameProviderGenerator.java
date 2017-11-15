package com.avaloq.tools.ddk.xtext.scope.generator;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall;
import com.avaloq.tools.ddk.xtext.expression.expression.IntegerLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall;
import com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral;
import com.avaloq.tools.ddk.xtext.generator.expression.CodeGenerationX;
import com.avaloq.tools.ddk.xtext.generator.expression.CompilationContext;
import com.avaloq.tools.ddk.xtext.generator.expression.ExpressionExtensionsX;
import com.avaloq.tools.ddk.xtext.generator.util.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.generator.util.GeneratorUtilX;
import com.avaloq.tools.ddk.xtext.generator.util.Naming;
import com.avaloq.tools.ddk.xtext.scope.generator.ScopeProviderX;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingSection;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class ScopeNameProviderGenerator {
  @Inject
  @Extension
  private CodeGenerationX _codeGenerationX;
  
  @Inject
  @Extension
  private ExpressionExtensionsX _expressionExtensionsX;
  
  @Inject
  @Extension
  private Naming _naming;
  
  @Inject
  @Extension
  private GeneratorUtilX _generatorUtilX;
  
  @Inject
  @Extension
  private ScopeProviderX _scopeProviderX;
  
  @Extension
  private GenModelUtilX genModelUtil;
  
  private CompilationContext compilationContext;
  
  public CharSequence generate(final ScopeModel it, final CompilationContext compilationContext, final GenModelUtilX genModelUtil) {
    CharSequence _xblockexpression = null;
    {
      this.compilationContext = compilationContext;
      this.genModelUtil = genModelUtil;
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package ");
      String _scopeNameProvider = this._scopeProviderX.getScopeNameProvider(it);
      String _javaPackage = this._naming.toJavaPackage(_scopeNameProvider);
      _builder.append(_javaPackage, "");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("import org.eclipse.emf.ecore.EClass;");
      _builder.newLine();
      _builder.append("import org.eclipse.emf.ecore.EObject;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import org.eclipse.xtext.naming.QualifiedName;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import com.avaloq.tools.ddk.xtext.scoping.AbstractNameFunction;");
      _builder.newLine();
      _builder.append("import com.avaloq.tools.ddk.xtext.scoping.AbstractScopeNameProvider;");
      _builder.newLine();
      _builder.append("import com.avaloq.tools.ddk.xtext.scoping.INameFunction;");
      _builder.newLine();
      _builder.append("import com.avaloq.tools.ddk.xtext.scoping.NameFunctions;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import com.google.common.base.Function;");
      _builder.newLine();
      _builder.append("import com.google.common.collect.Lists;");
      _builder.newLine();
      _builder.append("import com.google.inject.Singleton;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("@SuppressWarnings(\"all\")");
      _builder.newLine();
      _builder.append("@Singleton");
      _builder.newLine();
      _builder.append("public class ");
      String _scopeNameProvider_1 = this._scopeProviderX.getScopeNameProvider(it);
      String _simpleName = this._naming.toSimpleName(_scopeNameProvider_1);
      _builder.append(_simpleName, "");
      _builder.append(" extends AbstractScopeNameProvider {");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("  ");
      _builder.append("/** {@inheritDoc} */");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("public Iterable<INameFunction> internalGetNameFunctions(final EClass eClass) {");
      _builder.newLine();
      {
        if (((!Objects.equal(it, null)) && (!Objects.equal(it.getNaming(), null)))) {
          {
            NamingSection _naming = it.getNaming();
            EList<NamingDefinition> _namings = _naming.getNamings();
            final Function1<NamingDefinition, EPackage> _function = (NamingDefinition it_1) -> {
              EClass _type = it_1.getType();
              return _type.getEPackage();
            };
            List<EPackage> _map = ListExtensions.<NamingDefinition, EPackage>map(_namings, _function);
            Set<EPackage> _set = IterableExtensions.<EPackage>toSet(_map);
            for(final EPackage p : _set) {
              _builder.append("    ");
              _builder.append("if (");
              String _qualifiedPackageInterfaceName = this.genModelUtil.qualifiedPackageInterfaceName(p);
              _builder.append(_qualifiedPackageInterfaceName, "    ");
              _builder.append(".eINSTANCE == eClass.getEPackage()) {");
              _builder.newLineIfNotEmpty();
              _builder.append("    ");
              _builder.append("  ");
              _builder.append("switch (eClass.getClassifierID()) {");
              _builder.newLine();
              _builder.newLine();
              {
                NamingSection _naming_1 = it.getNaming();
                EList<NamingDefinition> _namings_1 = _naming_1.getNamings();
                final Function1<NamingDefinition, Boolean> _function_1 = (NamingDefinition n) -> {
                  EClass _type = n.getType();
                  EPackage _ePackage = _type.getEPackage();
                  return Boolean.valueOf(Objects.equal(_ePackage, p));
                };
                Iterable<NamingDefinition> _filter = IterableExtensions.<NamingDefinition>filter(_namings_1, _function_1);
                for(final NamingDefinition n : _filter) {
                  _builder.append("    ");
                  _builder.append("  ");
                  _builder.append("case ");
                  EClass _type = n.getType();
                  String _classifierIdLiteral = this.genModelUtil.classifierIdLiteral(_type);
                  _builder.append(_classifierIdLiteral, "      ");
                  _builder.append(":");
                  _builder.newLineIfNotEmpty();
                  _builder.append("    ");
                  _builder.append("  ");
                  _builder.append("  ");
                  String _location = this._generatorUtilX.location(n);
                  String _javaContributorComment = this._generatorUtilX.javaContributorComment(_location);
                  _builder.append(_javaContributorComment, "        ");
                  _builder.newLineIfNotEmpty();
                  _builder.append("    ");
                  _builder.append("  ");
                  _builder.append("  ");
                  _builder.append("return ");
                  com.avaloq.tools.ddk.xtext.scope.scope.Naming _naming_2 = n.getNaming();
                  CharSequence _nameFunctions = this.nameFunctions(_naming_2, it);
                  _builder.append(_nameFunctions, "        ");
                  _builder.append(";");
                  _builder.newLineIfNotEmpty();
                }
              }
              _builder.newLine();
              _builder.append("    ");
              _builder.append("  ");
              _builder.append("default:");
              _builder.newLine();
              _builder.append("    ");
              _builder.append("    ");
              _builder.append("return !eClass.getESuperTypes().isEmpty() ? getNameFunctions(eClass.getESuperTypes().get(0)) : null;");
              _builder.newLine();
              _builder.append("    ");
              _builder.append("  ");
              _builder.append("}");
              _builder.newLine();
              _builder.append("    ");
              _builder.append("}");
              _builder.newLine();
            }
          }
        }
      }
      _builder.append("    ");
      _builder.append("return !eClass.getESuperTypes().isEmpty() ? getNameFunctions(eClass.getESuperTypes().get(0)) : null;");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  public CharSequence nameFunctions(final com.avaloq.tools.ddk.xtext.scope.scope.Naming it, final ScopeModel model) {
    return this.nameFunctions(it, model, null, null);
  }
  
  public CharSequence nameFunctions(final com.avaloq.tools.ddk.xtext.scope.scope.Naming it, final ScopeModel model, final String contextName, final EClass contextType) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Lists.<INameFunction> newArrayList(");
    {
      EList<NamingExpression> _names = it.getNames();
      boolean _hasElements = false;
      for(final NamingExpression n : _names) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "");
        }
        String _nameFunction = this.nameFunction(n, model, contextName, contextType);
        _builder.append(_nameFunction, "");
      }
    }
    _builder.append(")");
    return _builder;
  }
  
  protected String _nameFunction(final NamingExpression it, final ScopeModel model, final String contextName, final EClass contextType) {
    String _xifexpression = null;
    boolean _isFactory = it.isFactory();
    if (_isFactory) {
      String _xifexpression_1 = null;
      if ((Objects.equal(contextName, null) || Objects.equal(contextType, null))) {
        Expression _expression = it.getExpression();
        CompilationContext _clone = this.compilationContext.clone("UNEXPECTED_THIS");
        _xifexpression_1 = this._codeGenerationX.javaExpression(_expression, _clone);
      } else {
        Expression _expression_1 = it.getExpression();
        CompilationContext _clone_1 = this.compilationContext.clone("UNEXPECTED_THIS", null, contextName, contextType);
        _xifexpression_1 = this._codeGenerationX.javaExpression(_expression_1, _clone_1);
      }
      _xifexpression = _xifexpression_1;
    } else {
      String _xifexpression_2 = null;
      boolean _isExport = it.isExport();
      if (_isExport) {
        _xifexpression_2 = "NameFunctions.exportNameFunction()";
      } else {
        Expression _expression_2 = it.getExpression();
        _xifexpression_2 = this.nameFunction(_expression_2, model, contextName, contextType);
      }
      _xifexpression = _xifexpression_2;
    }
    return _xifexpression;
  }
  
  protected String _nameFunction(final Expression it, final ScopeModel model, final String contextName, final EClass contextType) {
    String _serialize = this._expressionExtensionsX.serialize(it);
    String _plus = ("EXPRESSION_NOT_SUPPORTED(\"" + _serialize);
    return (_plus + "\")");
  }
  
  protected String _nameFunction(final StringLiteral it, final ScopeModel model, final String contextName, final EClass contextType) {
    String _val = it.getVal();
    String _plus = ("NameFunctions.fromConstant(\"" + _val);
    return (_plus + "\")");
  }
  
  protected String _nameFunction(final IntegerLiteral it, final ScopeModel model, final String contextName, final EClass contextType) {
    int _val = it.getVal();
    String _plus = ("NameFunctions.fromConstant(String.valueOf(" + Integer.valueOf(_val));
    return (_plus + "))");
  }
  
  protected String _nameFunction(final FeatureCall it, final ScopeModel model, final String contextName, final EClass contextType) {
    StringConcatenation _builder = new StringConcatenation();
    CompilationContext _xifexpression = null;
    boolean _equals = Objects.equal(contextName, null);
    if (_equals) {
      EClass _scopeType = this._scopeProviderX.scopeType(it);
      _xifexpression = this.compilationContext.clone("obj", _scopeType);
    } else {
      EClass _scopeType_1 = this._scopeProviderX.scopeType(it);
      _xifexpression = this.compilationContext.clone("obj", _scopeType_1, "ctx", contextType);
    }
    final CompilationContext currentContext = _xifexpression;
    _builder.newLineIfNotEmpty();
    {
      if (((Objects.equal(it.getTarget(), null) || this._codeGenerationX.isThisCall(it.getTarget())) && this._codeGenerationX.isSimpleFeatureCall(it, currentContext))) {
        _builder.append("NameFunctions.fromFeature(");
        EStructuralFeature _feature = this._scopeProviderX.feature(it);
        String _literalIdentifier = this.genModelUtil.literalIdentifier(_feature);
        _builder.append(_literalIdentifier, "");
        _builder.append(")");
      } else {
        boolean _isSimpleNavigation = this._codeGenerationX.isSimpleNavigation(it, currentContext);
        if (_isSimpleNavigation) {
          _builder.append("new AbstractNameFunction() {");
          _builder.newLineIfNotEmpty();
          _builder.append("  ");
          _builder.append("public QualifiedName apply(final EObject object) {");
          _builder.newLine();
          _builder.append("    ");
          _builder.append("final ");
          EClass _scopeType_2 = this._scopeProviderX.scopeType(it);
          String _instanceClassName = this.genModelUtil.instanceClassName(_scopeType_2);
          _builder.append(_instanceClassName, "    ");
          _builder.append(" obj = (");
          EClass _scopeType_3 = this._scopeProviderX.scopeType(it);
          String _instanceClassName_1 = this.genModelUtil.instanceClassName(_scopeType_3);
          _builder.append(_instanceClassName_1, "    ");
          _builder.append(") object;");
          _builder.newLineIfNotEmpty();
          _builder.append("      ");
          _builder.append("return toQualifiedName(");
          String _javaExpression = this._codeGenerationX.javaExpression(it, currentContext);
          _builder.append(_javaExpression, "      ");
          _builder.append(");");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append("}");
          _builder.newLine();
          _builder.append("  ");
          _builder.append("}");
        } else {
          _builder.append("EXPRESSION_NOT_SUPPORTED(\"");
          String _serialize = this._expressionExtensionsX.serialize(it);
          _builder.append(_serialize, "");
          _builder.append("\")");
        }
      }
    }
    return _builder.toString();
  }
  
  protected String _nameFunction(final OperationCall it, final ScopeModel model, final String contextName, final EClass contextType) {
    StringConcatenation _builder = new StringConcatenation();
    CompilationContext _xifexpression = null;
    boolean _equals = Objects.equal(contextName, null);
    if (_equals) {
      EClass _scopeType = this._scopeProviderX.scopeType(it);
      _xifexpression = this.compilationContext.clone("obj", _scopeType);
    } else {
      EClass _scopeType_1 = this._scopeProviderX.scopeType(it);
      _xifexpression = this.compilationContext.clone("obj", _scopeType_1, "ctx", contextType);
    }
    final CompilationContext currentContext = _xifexpression;
    _builder.newLineIfNotEmpty();
    {
      boolean _isCompilable = this._codeGenerationX.isCompilable(it, currentContext);
      if (_isCompilable) {
        _builder.append("new AbstractNameFunction() {");
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        _builder.append("public QualifiedName apply(final EObject object) {");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("final ");
        EClass _scopeType_2 = this._scopeProviderX.scopeType(it);
        String _instanceClassName = this.genModelUtil.instanceClassName(_scopeType_2);
        _builder.append(_instanceClassName, "    ");
        _builder.append(" obj = (");
        EClass _scopeType_3 = this._scopeProviderX.scopeType(it);
        String _instanceClassName_1 = this.genModelUtil.instanceClassName(_scopeType_3);
        _builder.append(_instanceClassName_1, "    ");
        _builder.append(") object;");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("return toQualifiedName(");
        String _javaExpression = this._codeGenerationX.javaExpression(it, currentContext);
        _builder.append(_javaExpression, "    ");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("}");
      } else {
        _builder.append("EXPRESSION_NOT_SUPPORTED(\"");
        String _serialize = this._expressionExtensionsX.serialize(it);
        _builder.append(_serialize, "");
        _builder.append("\")");
      }
    }
    return _builder.toString();
  }
  
  public String nameFunction(final EObject it, final ScopeModel model, final String contextName, final EClass contextType) {
    if (it instanceof IntegerLiteral) {
      return _nameFunction((IntegerLiteral)it, model, contextName, contextType);
    } else if (it instanceof OperationCall) {
      return _nameFunction((OperationCall)it, model, contextName, contextType);
    } else if (it instanceof StringLiteral) {
      return _nameFunction((StringLiteral)it, model, contextName, contextType);
    } else if (it instanceof FeatureCall) {
      return _nameFunction((FeatureCall)it, model, contextName, contextType);
    } else if (it instanceof Expression) {
      return _nameFunction((Expression)it, model, contextName, contextType);
    } else if (it instanceof NamingExpression) {
      return _nameFunction((NamingExpression)it, model, contextName, contextType);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, model, contextName, contextType).toString());
    }
  }
}
