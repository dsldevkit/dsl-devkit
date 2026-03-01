/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.scope.generator;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall;
import com.avaloq.tools.ddk.xtext.expression.expression.IntegerLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall;
import com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral;
import com.avaloq.tools.ddk.xtext.expression.generator.CodeGenerationX;
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext;
import com.avaloq.tools.ddk.xtext.expression.generator.ExpressionExtensionsX;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.Naming;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.google.inject.Inject;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;


@SuppressWarnings({"checkstyle:MethodName", "PMD.UnusedFormalParameter"})
public class ScopeNameProviderGenerator {

  @Inject
  private CodeGenerationX codeGenerationX;

  @Inject
  private ExpressionExtensionsX expressionExtensionsX;

  @Inject
  private Naming naming;

  @Inject
  private GeneratorUtilX generatorUtilX;

  @Inject
  private ScopeProviderX scopeProviderX;

  private GenModelUtilX genModelUtil;

  private CompilationContext compilationContext;

  // CHECKSTYLE:CONSTANTS-OFF
  /**
   * Generates the scope name provider class.
   *
   * @param it
   *          the scope model
   * @param compilationContext
   *          the compilation context
   * @param genModelUtil
   *          the gen model utility
   * @return the generated source code
   */
  // CHECKSTYLE:CHECK-OFF HiddenField
  public CharSequence generate(final ScopeModel it, final CompilationContext compilationContext, final GenModelUtilX genModelUtil) {
    // CHECKSTYLE:CHECK-ON HiddenField
    this.compilationContext = compilationContext;
    this.genModelUtil = genModelUtil;
    final StringBuilder builder = new StringBuilder(2048);
    builder.append("package ").append(naming.toJavaPackage(scopeProviderX.getScopeNameProvider(it))).append(";\n");
    builder.append('\n');
    builder.append("import java.util.Arrays;\n");
    builder.append('\n');
    builder.append("import org.eclipse.emf.ecore.EClass;\n");
    builder.append('\n');
    builder.append("import org.eclipse.xtext.naming.QualifiedName;\n");
    builder.append('\n');
    builder.append("import com.avaloq.tools.ddk.xtext.scoping.AbstractScopeNameProvider;\n");
    builder.append("import com.avaloq.tools.ddk.xtext.scoping.INameFunction;\n");
    builder.append("import com.avaloq.tools.ddk.xtext.scoping.NameFunctions;\n");
    builder.append('\n');
    builder.append("import com.google.common.base.Function;\n");
    builder.append("import com.google.inject.Singleton;\n");
    builder.append('\n');
    builder.append("@SuppressWarnings(\"all\")\n");
    builder.append("@Singleton\n");
    builder.append("public class ").append(naming.toSimpleName(scopeProviderX.getScopeNameProvider(it))).append(" extends AbstractScopeNameProvider {\n");
    builder.append('\n');
    builder.append("  @Override\n");
    builder.append("  public Iterable<INameFunction> internalGetNameFunctions(final EClass eClass) {\n");
    if (it.getNaming() != null) {
      final Set<EPackage> packages = it.getNaming().getNamings().stream()
          .map(nd -> nd.getType().getEPackage())
          .collect(Collectors.toSet());
      for (final EPackage p : packages) {
        builder.append("    if (").append(genModelUtil.qualifiedPackageInterfaceName(p)).append(".eINSTANCE == eClass.getEPackage()) {\n");
        builder.append("      switch (eClass.getClassifierID()) {\n");
        builder.append('\n');

        for (final NamingDefinition n : it.getNaming().getNamings()) {
          if (Objects.equals(n.getType().getEPackage(), p)) {
            builder.append("      case ").append(genModelUtil.classifierIdLiteral(n.getType())).append(":\n");
            builder.append("        ").append(generatorUtilX.javaContributorComment(generatorUtilX.location(n))).append('\n');
            builder.append("        return ").append(nameFunctions(n.getNaming(), it)).append(";\n");
          }
        }
        builder.append('\n');
        builder.append("      default:\n");
        builder.append("        return !eClass.getESuperTypes().isEmpty() ? getNameFunctions(eClass.getESuperTypes().get(0)) : null;\n");
        builder.append("      }\n");
        builder.append("    }\n");
      }
    }
    builder.append("    return !eClass.getESuperTypes().isEmpty() ? getNameFunctions(eClass.getESuperTypes().get(0)) : null;\n");
    builder.append("  }\n");
    builder.append('\n');
    builder.append("}\n");
    return builder;
  }
  // CHECKSTYLE:CONSTANTS-ON

  /**
   * Generates name functions for a naming definition.
   *
   * @param it
   *          the naming definition
   * @param model
   *          the scope model
   * @return the generated name functions
   */
  public CharSequence nameFunctions(final com.avaloq.tools.ddk.xtext.scope.scope.Naming it, final ScopeModel model) {
    return nameFunctions(it, model, null, null);
  }

  /**
   * Generates name functions for a naming definition with context.
   *
   * @param it
   *          the naming definition
   * @param model
   *          the scope model
   * @param contextName
   *          the context name
   * @param contextType
   *          the context type
   * @return the generated name functions
   */
  public CharSequence nameFunctions(final com.avaloq.tools.ddk.xtext.scope.scope.Naming it, final ScopeModel model, final String contextName, final EClass contextType) {
    final StringBuilder builder = new StringBuilder(512);
    builder.append("Arrays.asList(");
    boolean first = true;
    for (final NamingExpression n : it.getNames()) {
      if (!first) {
        builder.append(", ");
      }
      first = false;
      builder.append(nameFunction(n, model, contextName, contextType));
    }
    builder.append(')');
    return builder;
  }

  // CHECKSTYLE:CONSTANTS-OFF
  protected String _nameFunction(final NamingExpression it, final ScopeModel model, final String contextName, final EClass contextType) {
    if (it.isFactory()) {
      if (contextName == null || contextType == null) {
        return codeGenerationX.javaExpression(it.getExpression(), compilationContext.clone("UNEXPECTED_THIS"));
      } else {
        return codeGenerationX.javaExpression(it.getExpression(), compilationContext.clone("UNEXPECTED_THIS", null, contextName, contextType));
      }
    } else if (it.isExport()) {
      return "NameFunctions.exportNameFunction()";
    } else {
      return nameFunction(it.getExpression(), model, contextName, contextType);
    }
  }

  protected String _nameFunction(final Expression it, final ScopeModel model, final String contextName, final EClass contextType) {
    return "EXPRESSION_NOT_SUPPORTED(\"" + expressionExtensionsX.serialize(it) + "\")";
  }

  protected String _nameFunction(final StringLiteral it, final ScopeModel model, final String contextName, final EClass contextType) {
    return "NameFunctions.fromConstant(\"" + it.getVal() + "\")";
  }

  protected String _nameFunction(final IntegerLiteral it, final ScopeModel model, final String contextName, final EClass contextType) {
    return "NameFunctions.fromConstant(String.valueOf(" + it.getVal() + "))";
  }

  protected String _nameFunction(final FeatureCall it, final ScopeModel model, final String contextName, final EClass contextType) {
    final CompilationContext currentContext = (contextName == null)
        ? compilationContext.clone("obj", scopeProviderX.scopeType(it))
        : compilationContext.clone("obj", scopeProviderX.scopeType(it), "ctx", contextType);
    final StringBuilder builder = new StringBuilder(512);
    if ((it.getTarget() == null || codeGenerationX.isThisCall(it.getTarget())) && codeGenerationX.isSimpleFeatureCall(it, currentContext)) {
      builder.append("NameFunctions.fromFeature(").append(genModelUtil.literalIdentifier(scopeProviderX.feature(it))).append(')');
    } else if (codeGenerationX.isSimpleNavigation(it, currentContext)) {
      builder.append('\n');
      builder.append("object -> {\n");
      builder.append("    final ").append(genModelUtil.instanceClassName(scopeProviderX.scopeType(it))).append(" obj = (").append(genModelUtil.instanceClassName(scopeProviderX.scopeType(it))).append(") object;\n");
      builder.append("    return toQualifiedName(").append(codeGenerationX.javaExpression(it, currentContext)).append(");\n");
      builder.append("  }\n");
    } else {
      builder.append("EXPRESSION_NOT_SUPPORTED(\"").append(expressionExtensionsX.serialize(it)).append("\")");
    }
    return builder.toString();
  }

  protected String _nameFunction(final OperationCall it, final ScopeModel model, final String contextName, final EClass contextType) {
    final CompilationContext currentContext = (contextName == null)
        ? compilationContext.clone("obj", scopeProviderX.scopeType(it))
        : compilationContext.clone("obj", scopeProviderX.scopeType(it), "ctx", contextType);
    final StringBuilder builder = new StringBuilder(512);
    if (codeGenerationX.isCompilable(it, currentContext)) {
      builder.append("object -> {\n");
      builder.append("    final ").append(genModelUtil.instanceClassName(scopeProviderX.scopeType(it))).append(" obj = (").append(genModelUtil.instanceClassName(scopeProviderX.scopeType(it))).append(") object;\n");
      builder.append("    return toQualifiedName(").append(codeGenerationX.javaExpression(it, currentContext)).append(");\n");
      builder.append("  }\n");
    } else {
      builder.append("EXPRESSION_NOT_SUPPORTED(\"").append(expressionExtensionsX.serialize(it)).append("\")");
    }
    return builder.toString();
  }
  // CHECKSTYLE:CONSTANTS-ON

  /**
   * Dispatches to the appropriate name function generator based on the type of the given object.
   *
   * @param it
   *          the object to generate a name function for
   * @param model
   *          the scope model
   * @param contextName
   *          the context name
   * @param contextType
   *          the context type
   * @return the generated name function code
   * @throws IllegalArgumentException
   *           if the parameter types are not handled
   */
  public String nameFunction(final EObject it, final ScopeModel model, final String contextName, final EClass contextType) {
    if (it instanceof IntegerLiteral integerLiteral) {
      return _nameFunction(integerLiteral, model, contextName, contextType);
    } else if (it instanceof OperationCall operationCall) {
      return _nameFunction(operationCall, model, contextName, contextType);
    } else if (it instanceof StringLiteral stringLiteral) {
      return _nameFunction(stringLiteral, model, contextName, contextType);
    } else if (it instanceof FeatureCall featureCall) {
      return _nameFunction(featureCall, model, contextName, contextType);
    } else if (it instanceof Expression expression) {
      return _nameFunction(expression, model, contextName, contextType);
    } else if (it instanceof NamingExpression namingExpression) {
      return _nameFunction(namingExpression, model, contextName, contextType);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
          + Arrays.<Object>asList(it, model, contextName, contextType).toString());
    }
  }
}
