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

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Pair;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall;
import com.avaloq.tools.ddk.xtext.expression.expression.IntegerLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall;
import com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral;
import com.avaloq.tools.ddk.xtext.expression.generator.ExpressionExtensions;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX;
import com.avaloq.tools.ddk.xtext.scope.jvmmodel.ScopeExpressionCompiler;
import com.avaloq.tools.ddk.xtext.scope.jvmmodel.ScopeExpressionTranslator;
import com.avaloq.tools.ddk.xtext.scope.jvmmodel.ScopeTranslationContext;
import com.avaloq.tools.ddk.xtext.scope.scope.Naming;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.google.inject.Inject;


@SuppressWarnings({"checkstyle:MethodName", "nls", "PMD.UnusedFormalParameter"})
public class ScopeNameProviderGenerator {

  // CPD-OFF — migrated Xtend generator code, kept faithful; de-dup is a migration follow-up (#1339)

  @Inject
  private GeneratorUtilX generatorUtilX;

  @Inject
  private ScopeProviderX scopeProviderX;

  @Inject
  private ScopeExpressionTranslator translator;

  @Inject
  private ScopeExpressionCompiler compiler;

  private GenModelUtilX genModelUtil;

  /**
   * Configures the collaborators required by the extracted body method. Used by the Xbase based
   * {@code ScopeJvmModelInferrer} which attaches the body method directly to an inferred JVM operation rather than
   * generating a full compilation unit through {@link #generate}.
   *
   * @param genModelUtilX
   *          the gen model utility, must not be {@code null}
   * @param model
   *          the scope model a scope name provider is being generated for, must not be {@code null}
   */
  public void configure(final GenModelUtilX genModelUtilX, final ScopeModel model) {
    this.genModelUtil = genModelUtilX;
    translator.configure(model);
  }

  // CHECKSTYLE:CONSTANTS-OFF the repeated literals are fragments of the emitted Java source, not nameable constants
  /**
   * Produces the body of the {@code internalGetNameFunctions(EClass)} method. Extracted so the Xbase based
   * {@code ScopeJvmModelInferrer} can attach it directly as a method body.
   *
   * @param it
   *          the scope model, must not be {@code null}
   * @return the method body, never {@code null}
   */
  public CharSequence internalGetNameFunctionsBody(final ScopeModel it) {
    final StringConcatenation builder = new StringConcatenation();
    if (it.getNaming() != null) {
      final Set<EPackage> packages = new LinkedHashSet<>();
      for (final NamingDefinition naming : it.getNaming().getNamings()) {
        packages.add(naming.getType().getEPackage());
      }
      for (final EPackage p : packages) {
        builder.append("if (");
        builder.append(genModelUtil.qualifiedPackageInterfaceName(p));
        builder.append(".eINSTANCE == eClass.getEPackage()) {");
        builder.newLineIfNotEmpty();
        builder.append("  ");
        builder.append("switch (eClass.getClassifierID()) {");
        builder.newLine();
        builder.newLine();
        for (final NamingDefinition n : it.getNaming().getNamings()) {
          if (!Objects.equals(n.getType().getEPackage(), p)) {
            continue;
          }
          builder.append("  ");
          builder.append("case ");
          builder.append(genModelUtil.classifierIdLiteral(n.getType()), "  ");
          builder.append(":");
          builder.newLineIfNotEmpty();
          builder.append("  ");
          builder.append("  ");
          builder.append(generatorUtilX.javaContributorComment(generatorUtilX.location(n)), "    ");
          builder.newLineIfNotEmpty();
          builder.append("  ");
          builder.append("  ");
          builder.append("return ");
          builder.append(nameFunctions(n.getNaming(), it), "    ");
          builder.append(";");
          builder.newLineIfNotEmpty();
        }
        builder.newLine();
        builder.append("  ");
        builder.append("default:");
        builder.newLine();
        builder.append("    ");
        builder.append("return !eClass.getESuperTypes().isEmpty() ? getNameFunctions(eClass.getESuperTypes().get(0)) : null;");
        builder.newLine();
        builder.append("  ");
        builder.append("}");
        builder.newLine();
        builder.append("}");
        builder.newLine();
      }
    }
    builder.append("return !eClass.getESuperTypes().isEmpty() ? getNameFunctions(eClass.getESuperTypes().get(0)) : null;");
    builder.newLine();
    return builder;
  }

  public CharSequence nameFunctions(final Naming it, final ScopeModel model) {
    return nameFunctions(it, model, null, null);
  }

  public CharSequence nameFunctions(final Naming it, final ScopeModel model, final String contextName,
      final EClass contextType) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append("java.util.Arrays.asList(");
    boolean hasElements = false;
    for (final NamingExpression n : it.getNames()) {
      if (hasElements) {
        builder.appendImmediate(", ", "");
      } else {
        hasElements = true;
      }
      builder.append(nameFunction(n, model, contextName, contextType));
    }
    builder.append(")");
    return builder;
  }

  protected String _nameFunction(final NamingExpression it, final ScopeModel model, final String contextName,
      final EClass contextType) {
    if (it.isFactory()) {
      if (contextName == null || contextType == null) {
        return compiler.javaExpression(it.getExpression(), translator.newCompilationContext("UNEXPECTED_THIS", null, List.of(), it));
      }
      return compiler.javaExpression(it.getExpression(),
          translator.newCompilationContext("UNEXPECTED_THIS", null, List.of(Pair.of(contextName, genModelUtil.instanceClassName(contextType))), it));
    } else if (it.isExport()) {
      return "com.avaloq.tools.ddk.xtext.scoping.NameFunctions.exportNameFunction()";
    } else {
      return nameFunction(it.getExpression(), model, contextName, contextType);
    }
  }

  protected String _nameFunction(final Expression it, final ScopeModel model, final String contextName,
      final EClass contextType) {
    return "EXPRESSION_NOT_SUPPORTED(\"" + ExpressionExtensions.serialize(it) + "\")";
  }

  protected String _nameFunction(final StringLiteral it, final ScopeModel model, final String contextName,
      final EClass contextType) {
    return "com.avaloq.tools.ddk.xtext.scoping.NameFunctions.fromConstant(\"" + it.getVal() + "\")";
  }

  protected String _nameFunction(final IntegerLiteral it, final ScopeModel model, final String contextName,
      final EClass contextType) {
    return "com.avaloq.tools.ddk.xtext.scoping.NameFunctions.fromConstant(String.valueOf(" + it.getVal() + "))";
  }

  protected String _nameFunction(final FeatureCall it, final ScopeModel model, final String contextName,
      final EClass contextType) {
    final StringConcatenation builder = new StringConcatenation();
    final ScopeTranslationContext currentContext = newContext(it, contextName, contextType);
    builder.newLineIfNotEmpty();
    if ((it.getTarget() == null || isThisCall(it.getTarget())) && compiler.isSimpleFeatureCall(it, currentContext)) {
      builder.append("com.avaloq.tools.ddk.xtext.scoping.NameFunctions.fromFeature(");
      builder.append(genModelUtil.literalIdentifier(scopeProviderX.feature(it)));
      builder.append(")");
    } else if (compiler.isSimpleNavigation(it, currentContext)) {
      builder.newLineIfNotEmpty();
      builder.append("object -> {");
      builder.newLine();
      builder.append("    ");
      builder.append("final ");
      builder.append(genModelUtil.instanceClassName(scopeProviderX.scopeType(it)), "    ");
      builder.append(" obj = (");
      builder.append(genModelUtil.instanceClassName(scopeProviderX.scopeType(it)), "    ");
      builder.append(") object;");
      builder.newLineIfNotEmpty();
      builder.append("    ");
      builder.append("return toQualifiedName(");
      builder.append(compiler.javaExpression(it, currentContext), "    ");
      builder.append(");");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append("}");
      builder.newLine();
    } else {
      builder.append("EXPRESSION_NOT_SUPPORTED(\"");
      builder.append(ExpressionExtensions.serialize(it));
      builder.append("\")");
    }
    return builder.toString();
  }

  protected String _nameFunction(final OperationCall it, final ScopeModel model, final String contextName,
      final EClass contextType) {
    final StringConcatenation builder = new StringConcatenation();
    final ScopeTranslationContext currentContext = newContext(it, contextName, contextType);
    builder.newLineIfNotEmpty();
    if (compiler.isCompilable(it, currentContext)) {
      builder.append("object -> {");
      builder.newLine();
      builder.append("    ");
      builder.append("final ");
      builder.append(genModelUtil.instanceClassName(scopeProviderX.scopeType(it)), "    ");
      builder.append(" obj = (");
      builder.append(genModelUtil.instanceClassName(scopeProviderX.scopeType(it)), "    ");
      builder.append(") object;");
      builder.newLineIfNotEmpty();
      builder.append("    ");
      builder.append("return toQualifiedName(");
      builder.append(compiler.javaExpression(it, currentContext), "    ");
      builder.append(");");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append("}");
      builder.newLine();
      builder.append("    ");
    } else {
      builder.append("EXPRESSION_NOT_SUPPORTED(\"");
      builder.append(ExpressionExtensions.serialize(it));
      builder.append("\")");
    }
    return builder.toString();
  }
  // CHECKSTYLE:CONSTANTS-ON

  /**
   * Creates the compilation context the {@code obj} name function bodies are translated against.
   *
   * @param expression
   *          the expression being translated, must not be {@code null}
   * @param contextName
   *          the name of the extra context variable, or {@code null} if there is none
   * @param contextType
   *          the type of the extra context variable, only read when {@code contextName} is given
   * @return the compilation context, never {@code null}
   */
  private ScopeTranslationContext newContext(final Expression expression, final String contextName, final EClass contextType) {
    final List<Pair<String, String>> extraVariables = contextName == null
        ? List.of()
        : List.of(Pair.of(contextName, genModelUtil.instanceClassName(contextType)));
    return translator.newCompilationContext("obj", scopeProviderX.scopeType(expression), extraVariables, expression);
  }

  private boolean _isThisCall(final Expression it) {
    return false;
  }

  private boolean _isThisCall(final FeatureCall it) {
    if (it.getName() != null || it.getType() == null || it.getType().getId() == null) {
      return false;
    }
    return it.getType().getId().size() == 1 && Objects.equals(it.getType().getId().get(0), "this");
  }

  public String nameFunction(final EObject it, final ScopeModel model, final String contextName,
      final EClass contextType) {
    return switch (it) {
      case IntegerLiteral literal -> _nameFunction(literal, model, contextName, contextType);
      case OperationCall call -> _nameFunction(call, model, contextName, contextType);
      case StringLiteral literal -> _nameFunction(literal, model, contextName, contextType);
      case FeatureCall call -> _nameFunction(call, model, contextName, contextType);
      case Expression expression -> _nameFunction(expression, model, contextName, contextType);
      case NamingExpression expression -> _nameFunction(expression, model, contextName, contextType);
      case null, default -> throw new IllegalArgumentException(
          "Unhandled parameter types: " + Arrays.<Object>asList(it, model, contextName, contextType).toString());
    };
  }

  private boolean isThisCall(final Expression it) {
    return switch (it) {
      case FeatureCall call -> _isThisCall(call);
      case null -> throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it).toString());
      default -> _isThisCall(it);
    };
  }

}
