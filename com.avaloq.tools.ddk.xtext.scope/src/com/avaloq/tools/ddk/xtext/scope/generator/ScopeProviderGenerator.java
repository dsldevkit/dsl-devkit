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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.lib.Pair;

import com.avaloq.tools.ddk.xtext.expression.expression.BooleanLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall;
import com.avaloq.tools.ddk.xtext.expression.expression.IntegerLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.ListLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.NullLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall;
import com.avaloq.tools.ddk.xtext.expression.expression.RealLiteral;
import com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral;
import com.avaloq.tools.ddk.xtext.expression.generator.EClassComparator;
import com.avaloq.tools.ddk.xtext.expression.generator.ExpressionExtensions;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX;
import com.avaloq.tools.ddk.xtext.scope.jvmmodel.ScopeExpressionCompiler;
import com.avaloq.tools.ddk.xtext.scope.jvmmodel.ScopeExpressionTranslator;
import com.avaloq.tools.ddk.xtext.scope.jvmmodel.ScopeTranslationContext;
import com.avaloq.tools.ddk.xtext.scope.scope.FactoryExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.LambdaDataExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.MatchDataExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule;
import com.avaloq.tools.ddk.xtext.scope.scope.SimpleScopeExpression;
import com.google.inject.Inject;


@SuppressWarnings({"checkstyle:MethodName", "nls", "PMD.UnusedFormalParameter"})
public class ScopeProviderGenerator {

  // CPD-OFF — migrated Xtend generator code, kept faithful; de-dup is a migration follow-up (#1339)

  @Inject
  private GeneratorUtilX generatorUtilX;

  @Inject
  private ScopeProviderX scopeProviderX;

  @Inject
  private ScopeExpressionTranslator translator;

  @Inject
  private ScopeExpressionCompiler compiler;

  private ScopeNameProviderGenerator nameProviderGenerator;
  private GenModelUtilX genModelUtil;

  /**
   * Configures the collaborators required by the extracted body methods. Used by the Xbase based
   * {@code ScopeJvmModelInferrer} which attaches the body methods directly to inferred JVM operations rather than
   * generating a full compilation unit through {@link #generate}.
   *
   * @param nameGenerator
   *          the name provider generator, must not be {@code null}
   * @param genModelUtilX
   *          the gen model utility, must not be {@code null}
   * @param model
   *          the scope model a scope provider is being generated for, must not be {@code null}
   */
  public void configure(final ScopeNameProviderGenerator nameGenerator, final GenModelUtilX genModelUtilX, final ScopeModel model) {
    this.nameProviderGenerator = nameGenerator;
    this.genModelUtil = genModelUtilX;
    translator.configure(model);
  }

  // CHECKSTYLE:CONSTANTS-OFF the repeated literals are fragments of the emitted Java source, not nameable constants
  /**
   * Produces the body of the {@code doGetScope(EObject, EReference, String, Resource)} method. Extracted so the
   * Xbase based {@code ScopeJvmModelInferrer} can attach it directly as a method body.
   *
   * @param it
   *          the scope model, must not be {@code null}
   * @return the method body, never {@code null}
   */
  public CharSequence doGetScopeByReferenceBody(final ScopeModel it) {
    final StringConcatenation builder = new StringConcatenation();
    final List<ScopeDefinition> scopes = scopesWithReference(it, true);
    if (!scopes.isEmpty()) {
      builder.append("if (scopeName == null) {");
      builder.newLine();
      builder.append("  ");
      builder.append("return null;");
      builder.newLine();
      builder.append("}");
      builder.newLine();
      builder.newLine();
      builder.append("switch (scopeName) {");
      builder.newLine();
      for (final String name : scopeNames(scopes)) {
        builder.append("case \"");
        builder.append(name);
        builder.append("\":");
        builder.newLineIfNotEmpty();
        for (final ScopeDefinition scope : namedScopes(scopes, name)) {
          builder.append("  ");
          builder.append("if (reference == ");
          builder.append(genModelUtil.literalIdentifier(scope.getReference()), "  ");
          builder.append(") return ");
          builder.append(scopeProviderX.scopeMethodName(scope), "  ");
          builder.append("(context, reference, originalResource);");
          builder.newLineIfNotEmpty();
        }
        builder.append("  ");
        builder.append("break;");
        builder.newLine();
      }
      builder.append("  ");
      builder.append("default: break;");
      builder.newLine();
      builder.append("}");
      builder.newLine();
    }
    builder.append("return null;");
    builder.newLine();
    return builder;
  }

  /**
   * Produces the body of the {@code doGetScope(EObject, EClass, String, Resource)} method.
   *
   * @param it
   *          the scope model, must not be {@code null}
   * @return the method body, never {@code null}
   */
  public CharSequence doGetScopeByTypeBody(final ScopeModel it) {
    final StringConcatenation builder = new StringConcatenation();
    final List<ScopeDefinition> scopes = scopesWithReference(it, false);
    if (!scopes.isEmpty()) {
      builder.append("if (scopeName == null) {");
      builder.newLine();
      builder.append("  ");
      builder.append("return null;");
      builder.newLine();
      builder.append("}");
      builder.newLine();
      builder.newLine();
      builder.append("switch (scopeName) {");
      builder.newLine();
      for (final String name : scopeNames(scopes)) {
        builder.append("case \"");
        builder.append(name);
        builder.append("\":");
        builder.newLineIfNotEmpty();
        for (final ScopeDefinition scope : namedScopes(scopes, name)) {
          builder.append("  ");
          builder.append("if (type == ");
          builder.append(genModelUtil.literalIdentifier(scope.getTargetType()), "  ");
          builder.append(") return ");
          builder.append(scopeProviderX.scopeMethodName(scope), "  ");
          builder.append("(context, type, originalResource);");
          builder.newLineIfNotEmpty();
        }
        builder.append("  ");
        builder.append("break;");
        builder.newLine();
      }
      builder.append("  ");
      builder.append("default: break;");
      builder.newLine();
      builder.append("}");
      builder.newLine();
    }
    builder.append("return null;");
    builder.newLine();
    return builder;
  }

  /**
   * Produces the body of the {@code doGlobalCache(EObject, EReference, String, Resource)} method.
   *
   * @param it
   *          the scope model, must not be {@code null}
   * @return the method body, never {@code null}
   */
  public CharSequence doGlobalCacheByReferenceBody(final ScopeModel it) {
    final StringConcatenation builder = new StringConcatenation();
    final List<ScopeDefinition> scopes = scopesWithReference(it, true);
    final List<ScopeDefinition> globalScopes = scopes.stream().filter(s -> !globalRules(s).isEmpty()).toList();
    if (!globalScopes.isEmpty()) {
      builder.append("if (scopeName != null && context.eContainer() == null) {");
      builder.newLine();
      builder.append("  ");
      builder.append("switch (scopeName) {");
      builder.newLine();
      builder.append("  ");
      for (final String name : scopeNames(globalScopes)) {
        builder.append("case \"");
        builder.append(name, "  ");
        builder.append("\":");
        builder.newLineIfNotEmpty();
        for (final ScopeDefinition scope : namedScopes(scopes, name)) {
          builder.append("  ");
          builder.append("  ");
          final List<ScopeRule> globalRules = globalRules(scope);
          builder.newLineIfNotEmpty();
          if (!globalRules.isEmpty()) {
            builder.append("  ");
            builder.append("  ");
            builder.append("if (reference == ");
            builder.append(genModelUtil.literalIdentifier(scope.getReference()), "    ");
            builder.append(") return true;");
            builder.newLineIfNotEmpty();
          }
        }
        builder.append("  ");
        builder.append("  ");
        builder.append("break;");
        builder.newLine();
      }
      builder.append("    ");
      builder.append("default: break;");
      builder.newLine();
      builder.append("  ");
      builder.append("}");
      builder.newLine();
      builder.append("}");
      builder.newLine();
    }
    builder.append("return false;");
    builder.newLine();
    return builder;
  }

  /**
   * Produces the body of the {@code doGlobalCache(EObject, EClass, String, Resource)} method.
   *
   * @param it
   *          the scope model, must not be {@code null}
   * @return the method body, never {@code null}
   */
  public CharSequence doGlobalCacheByTypeBody(final ScopeModel it) {
    final StringConcatenation builder = new StringConcatenation();
    final List<ScopeDefinition> scopes = scopesWithReference(it, false);
    final List<ScopeDefinition> globalScopes = scopes.stream().filter(s -> !globalRules(s).isEmpty()).toList();
    if (!globalScopes.isEmpty()) {
      builder.append("if (context.eContainer() == null) {");
      builder.newLine();
      builder.append("  ");
      builder.append("switch (scopeName) {");
      builder.newLine();
      builder.append("  ");
      for (final String name : scopeNames(globalScopes)) {
        builder.append("case \"");
        builder.append(name, "  ");
        builder.append("\":");
        builder.newLineIfNotEmpty();
        for (final ScopeDefinition scope : namedScopes(scopes, name)) {
          builder.append("  ");
          builder.append("  ");
          final List<ScopeRule> globalRules = globalRules(scope);
          builder.newLineIfNotEmpty();
          if (!globalRules.isEmpty()) {
            builder.append("  ");
            builder.append("  ");
            builder.append("if (type == ");
            builder.append(genModelUtil.literalIdentifier(scope.getTargetType()), "    ");
            builder.append(") return true;");
            builder.newLineIfNotEmpty();
          }
        }
        builder.append("  ");
        builder.append("  ");
        builder.append("break;");
        builder.newLine();
      }
      builder.append("    ");
      builder.append("default: break;");
      builder.newLine();
      builder.append("  ");
      builder.append("}");
      builder.newLine();
      builder.append("}");
      builder.newLine();
    }
    builder.append("return false;");
    builder.newLine();
    return builder;
  }

  /**
   * Produces the body of a single {@code <scopeMethodName>} scope method. Extracted so the Xbase based
   * {@code ScopeJvmModelInferrer} can attach it directly as a method body.
   *
   * @param scope
   *          the scope definition the method is generated for, must not be {@code null}
   * @param it
   *          the scope model, must not be {@code null}
   * @return the method body, never {@code null}
   * @throws RuntimeException
   *           if the scope definition declares more than one global rule
   */
  public CharSequence scopeMethodBody(final ScopeDefinition scope, final ScopeModel it) {
    final StringConcatenation builder = new StringConcatenation();
    final List<ScopeRule> localRules = scopeProviderX.allScopeRules(scope).stream().filter(r -> !r.getContext().isGlobal()).toList();
    builder.newLineIfNotEmpty();
    final List<ScopeRule> globalRules = globalRules(scope);
    builder.newLineIfNotEmpty();
    if (globalRules.size() > 1) {
      throw new RuntimeException("only one global rule allowed"); // NOPMD the raw type is the contract the generated scope providers were built against
    }
    builder.newLineIfNotEmpty();
    for (final ScopeRule r : scopeProviderX.sortedRules(scopeProviderX.filterUniqueRules(localRules))) {
      final EClass ruleContextType = r.getContext().getContextType();
      builder.append(generatorUtilX.javaContributorComment(generatorUtilX.location(r)));
      builder.newLineIfNotEmpty();
      builder.append("if (");
      if (EClassComparator.isEObjectType(ruleContextType)) {
        builder.append("true");
      } else {
        builder.append("context instanceof ");
        builder.append(genModelUtil.instanceClassName(ruleContextType));
      }
      builder.append(") {");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append("final ");
      builder.append(genModelUtil.instanceClassName(ruleContextType), "  ");
      builder.append(" ctx = (");
      builder.append(genModelUtil.instanceClassName(ruleContextType), "  ");
      builder.append(") context;");
      builder.newLineIfNotEmpty();
      builder.append(" ");
      final List<ScopeRule> rulesForTypeAndContext = localRules.stream().filter(r2 -> scopeProviderX.hasSameContext(r2, r)).toList();
      builder.newLineIfNotEmpty();
      builder.append("  ");
      final String typeOrRef = scopeProviderX.contextRef(r) != null ? "ref" : "type";
      builder.append(scopeRuleBlock(rulesForTypeAndContext, it, typeOrRef, ruleContextType, r.getContext().isGlobal()), "  ");
      builder.newLineIfNotEmpty();
      builder.append("}");
      builder.newLine();
    }
    if (!localRules.isEmpty() || !globalRules.isEmpty()) {
      builder.newLine();
      builder.append("final EObject eContainer = context.eContainer();");
      builder.newLine();
      builder.append("if (eContainer != null) {");
      builder.newLine();
      builder.append("  ");
      builder.append("return internalGetScope(");
      if (!localRules.isEmpty()) {
        builder.append("eContainer");
      } else {
        builder.append("getRootObject(eContainer)");
      }
      builder.append(", ");
      if (scope.getReference() != null) {
        builder.append("ref");
      } else {
        builder.append("type");
      }
      builder.append(", \"");
      builder.append(scopeProviderX.getScopeName(scope), "  ");
      builder.append("\", originalResource);");
      builder.newLineIfNotEmpty();
      builder.append("}");
      builder.newLine();
      builder.newLine();
    }
    if (!globalRules.isEmpty()) {
      final ScopeRule r = globalRules.get(0);
      builder.newLineIfNotEmpty();
      final List<ScopeRule> rulesForTypeAndContext = List.of(r);
      builder.newLineIfNotEmpty();
      builder.append(generatorUtilX.javaContributorComment(generatorUtilX.location(r)));
      builder.newLineIfNotEmpty();
      builder.append("if (context.eResource() != null) {");
      builder.newLine();
      builder.append("  ");
      builder.append("final Resource ctx = context.eResource();");
      builder.newLine();
      builder.append("  ");
      final String typeOrRef = scopeProviderX.contextRef(r) != null ? "ref" : "type";
      builder.append(scopeRuleBlock(rulesForTypeAndContext, it, typeOrRef, r.getContext().getContextType(), r.getContext().isGlobal()), "  ");
      builder.newLineIfNotEmpty();
      builder.append("}");
      builder.newLine();
      builder.newLine();
    }
    builder.append("return null;");
    builder.newLine();
    return builder;
  }

  /**
   * Produces the Java source for the guard of the given scope rule, for use in the rule block's {@code if}
   * condition. The guard is evaluated against the {@code ctx} variable holding the rule's context object.
   * <p>
   * The implicit ({@code this}) receiver is therefore typed with the rule's <em>context</em> type - the type
   * {@code ctx} is declared and cast to in {@link #scopeMethodBody} - and not with the scope's target type. The
   * legacy generator passed the target type here, which was harmless as long as extension operations were resolved
   * by name alone, but makes {@code this.someExtension()} resolve against the wrong type now that extension
   * candidates are matched against the receiver type. A global rule ({@code context *}) has no context type; there
   * {@code ctx} is the {@link org.eclipse.emf.ecore.resource.Resource} and the legacy (target type) behaviour is
   * kept.
   *
   * @param r
   *          the scope rule whose guard to translate, must not be {@code null}
   * @return the Java source of the guard expression, never {@code null}
   */
  public String guardExpression(final ScopeRule r) {
    final EClass contextType = r.getContext().getContextType() != null ? r.getContext().getContextType() : scopeProviderX.scopeType(r);
    return compiler.javaExpression(r.getContext().getGuard(),
        translator.newCompilationContext("ctx", contextType, List.of(), r.getContext().getGuard()));
  }

  /**
   * Produces the Java source for an embedded {@link String} valued expression (e.g. a container query data value or
   * a prefix). The expression is evaluated against the {@code ctx} variable holding the rule's context object.
   *
   * @param expr
   *          the expression to translate, must not be {@code null}
   * @param contextType
   *          the type of the {@code ctx} context variable, must not be {@code null}
   * @return the Java source of the expression, never {@code null}
   */
  public String stringExpression(final Expression expr, final EClass contextType) {
    return compiler.javaExpression(expr, translator.newCompilationContext("ctx", contextType, List.of(), expr));
  }

  public CharSequence scopeRuleBlock(final List<ScopeRule> it, final ScopeModel model, final String typeOrRef, final EClass contextType,
      final Boolean isGlobal) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append("IScope scope = IScope.NULLSCOPE;");
    builder.newLine();
    builder.append("try {");
    builder.newLine();
    if (it.stream().anyMatch(r -> r.getContext().getGuard() != null)) {
      builder.append("  ");
      final List<ScopeRule> sorted = new ArrayList<>(it);
      sorted.sort(Comparator.comparingInt((ScopeRule r) -> r.getContext().getGuard() == null ? it.size() : it.indexOf(r)));
      boolean hasElements = false;
      for (final ScopeRule r : sorted) {
        if (hasElements) {
          builder.appendImmediate(" else ", "  ");
        } else {
          hasElements = true;
        }
        if (r.getContext().getGuard() != null) {
          builder.append("if (");
          builder.append(guardExpression(r), "  ");
          builder.append(") ");
        }
        builder.append("{");
        builder.newLineIfNotEmpty();
        builder.append("  ");
        builder.append("  ");
        if (it.size() > 1) {
          builder.append(generatorUtilX.javaContributorComment(generatorUtilX.location(r)), "    ");
          builder.newLineIfNotEmpty();
          builder.append("  ");
          builder.append("  ");
        }
        for (final ScopeExpression e : reversed(r.getExprs())) {
          builder.append(scopeExpression(e, model, typeOrRef, scopeProviderX.getScope(r), isGlobal), "    ");
        }
        builder.newLineIfNotEmpty();
        builder.append("  ");
        builder.append("}");
      }
      if (it.stream().noneMatch(r -> r.getContext().getGuard() == null)) {
        builder.append(" else {");
        builder.newLineIfNotEmpty();
        builder.append("  ");
        builder.append("  ");
        builder.append("throw new UnsupportedOperationException(); // continue matching other definitions");
        builder.newLine();
        builder.append("  ");
        builder.append("}");
      }
      builder.newLineIfNotEmpty();
    } else if (it.size() == 1) {
      builder.append("  ");
      for (final ScopeExpression e : reversed(it.get(0).getExprs())) {
        builder.append(scopeExpression(e, model, typeOrRef, scopeProviderX.getScope(it.get(0)), isGlobal), "  ");
      }
      builder.newLineIfNotEmpty();
    } else {
      builder.append("  ");
      error("scope context not unique for definitions: " + it.stream().map(generatorUtilX::location).collect(Collectors.joining(", ")));
      builder.newLineIfNotEmpty();
    }
    builder.append("} catch (Exception e) {");
    builder.newLine();
    builder.append("  ");
    builder.append("LOGGER.error(\"Error calculating scope for ");
    builder.append(isGlobal ? "Resource. Context:" : contextType.getName(), "  ");
    builder.append(" \" + com.avaloq.tools.ddk.xtext.util.EObjectUtil.getLocationString(context) + \" (");
    builder.append(scopeProviderX.locatorString(it.get(0)), "  ");
    builder.append(")\", e);");
    builder.newLineIfNotEmpty();
    builder.append("}");
    builder.newLine();
    builder.append("return scope;");
    builder.newLine();
    return builder;
  }

  protected CharSequence _scopeExpression(final ScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope,
      final Boolean isGlobal) {
    error("Xtend called the wrong definition." + it.toString() + generatorUtilX.javaContributorComment(generatorUtilX.location(it)));
    return null;
  }

  protected CharSequence _scopeExpression(final FactoryExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope,
      final Boolean isGlobal) {
    final StringBuilder b = new StringBuilder(512);
    final String method = translator.resolveFactoryMethod(it.getExpr(), model);
    if (method == null) {
      error("cannot resolve scope factory " + (it.getExpr() != null ? it.getExpr().toString() : null)
          + "; expected a static method of a declared extension class, or a qualified static call Type.method(...)");
      return b;
    }
    b.append("scope = ").append(method).append("(scope, ctx, ").append(typeOrRef).append(", originalResource");
    if (it.getExpr() instanceof OperationCall) {
      for (final Expression param : ((OperationCall) it.getExpr()).getParams()) {
        b.append(", ").append(factoryArgument(param));
      }
    }
    b.append(");\n");
    return b;
  }

  /**
   * Produces the Java source for a single scope factory argument. Only the literal and context-variable argument
   * forms that occur in scope sources are supported; anything else is reported as an error. This keeps the factory
   * emission independent of the legacy {@code .ext}/{@code CompilationContext} expression compiler.
   *
   * @param it
   *          the argument expression, must not be {@code null}
   * @return the Java source for the argument, never {@code null}
   */
  protected String _factoryArgument(final Expression it) {
    error("unsupported scope factory argument " + it.toString());
    return null;
  }

  protected String _factoryArgument(final StringLiteral it) {
    return "\"" + Strings.convertToJavaString(it.getVal()) + "\"";
  }

  protected String _factoryArgument(final IntegerLiteral it) {
    return Integer.toString(it.getVal());
  }

  protected String _factoryArgument(final RealLiteral it) {
    return it.getVal();
  }

  protected String _factoryArgument(final BooleanLiteral it) {
    return it.getVal();
  }

  protected String _factoryArgument(final NullLiteral it) {
    return "null";
  }

  protected String _factoryArgument(final FeatureCall it) {
    if (it.getName() == null && it.getType() != null) {
      return String.join(".", it.getType().getId());
    }
    error("unsupported scope factory argument " + it.toString());
    return null;
  }

  protected CharSequence _scopeExpression(final ScopeDelegation it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope,
      final Boolean isGlobal) {
    final StringConcatenation builder = new StringConcatenation();
    if (it.getDelegate() != null) {
      final String delegateString = ExpressionExtensions.serialize(it.getDelegate());
      builder.newLineIfNotEmpty();
      if (Objects.equals(delegateString, "this.eContainer()") || Objects.equals(delegateString, "this.eContainer")
          || Objects.equals(delegateString, "eContainer()") || Objects.equals(delegateString, "eContainer")) {
        builder.append("scope = newSameScope(\"");
        builder.append(scopeProviderX.locatorString(it));
        builder.append("\", scope, ctx.eContainer()");
      } else if (Objects.equals(delegateString, "this")) {
        builder.newLineIfNotEmpty();
        builder.append("scope = newSameScope(\"");
        builder.append(scopeProviderX.locatorString(it));
        builder.append("\", scope, ctx");
      } else {
        builder.newLineIfNotEmpty();
        builder.append("scope = newDelegateScope(\"");
        builder.append(scopeProviderX.locatorString(it));
        builder.append("\", scope, ");
        if (!isGlobal) {
          builder.append("() -> com.avaloq.tools.ddk.xtext.scoping.IContextSupplier.makeIterable(");
          builder.append(scopedElements(it.getDelegate(), model, ruleContextType(it), "ctx"));
          builder.append(")");
        } else {
          builder.append(scopedElements(it.getDelegate(), model, ruleContextType(it), "ctx"));
        }
      }
    } else {
      builder.newLineIfNotEmpty();
      builder.append("scope = newExternalDelegateScope(\"");
      builder.append(scopeProviderX.locatorString(it));
      builder.append("\", scope, ");
      builder.append(query(it.getExternal(), model, typeOrRef, scope));
      builder.append(".execute(originalResource)");
    }
    builder.append(", ");
    if (it.getScope() != null && !Objects.equals(scopeProviderX.typeOrRef(it.getScope()), scopeProviderX.typeOrRef(scopeProviderX.getScope(it)))) {
      builder.append(genModelUtil.literalIdentifier(scopeProviderX.typeOrRef(it.getScope())));
    } else {
      builder.append(typeOrRef);
    }
    builder.append(", \"");
    builder.append(it.getScope() != null && it.getScope().getName() != null ? it.getScope().getName() : "scope");
    builder.append("\", originalResource);");
    builder.newLineIfNotEmpty();
    return builder;
  }

  protected CharSequence _scopeExpression(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope,
      final Boolean isGlobal) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append("scope = ");
    builder.append(scopeExpressionPart(it, model, typeOrRef, scope));
    builder.append(scopeExpressionNaming(it, model, typeOrRef, scope));
    builder.append(scopeExpressionCasing(it, model, typeOrRef, scope));
    builder.append(");");
    builder.newLineIfNotEmpty();
    return builder;
  }

  protected CharSequence _scopeExpression(final SimpleScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope,
      final Boolean isGlobal) {
    final StringConcatenation builder = new StringConcatenation();
    if (isEmptyList(it.getExpr())) {
      builder.append("// Empty scope from ");
      builder.append(generatorUtilX.location(it));
      builder.newLineIfNotEmpty();
    } else {
      builder.append("scope = ");
      builder.append(scopeExpressionPart(it, model, typeOrRef, scope));
      builder.append(scopeExpressionNaming(it, model, typeOrRef, scope));
      builder.append(scopeExpressionCasing(it, model, typeOrRef, scope));
      builder.append(");");
      builder.newLineIfNotEmpty();
    }
    return builder;
  }

  protected CharSequence _scopeExpressionPart(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef,
      final ScopeDefinition scope) {
    error("Xtend called the wrong definition for scopeExpressionPart with this=" + it.toString()
        + generatorUtilX.javaContributorComment(generatorUtilX.location(it)));
    return null;
  }

  protected CharSequence _scopeExpressionPart(final SimpleScopeExpression it, final ScopeModel model, final String typeOrRef,
      final ScopeDefinition scope) {
    return "newSimpleScope(\"" + scopeProviderX.locatorString(it) + "\", scope, "
        + scopedElements(it.getExpr(), model, ruleContextType(it), "ctx") + ", ";
  }

  public CharSequence query(final GlobalScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append("newQuery(");
    builder.append(genModelUtil.literalIdentifier(it.getType()));
    builder.append(")");
    final List<MatchDataExpression> matchData = dataOfType(it, MatchDataExpression.class);
    if (it.getName() != null) {
      builder.append(".name(");
      builder.append(nameValue(it.getName(), ruleContextType(it), model));
      builder.append(")");
    }
    if (!matchData.isEmpty()) {
      for (final MatchDataExpression d : matchData) {
        builder.append(".data(\"");
        builder.append(Strings.convertToJavaString(d.getKey()));
        builder.append("\", ");
        builder.append(stringExpression(d.getValue(), ruleContextType(it)));
        builder.append(")");
      }
    }
    if (!it.getDomains().isEmpty() && !Objects.equals(it.getDomains().get(0), "*")) {
      builder.append(".domains(");
      boolean hasElements = false;
      for (final String d : it.getDomains()) {
        if (hasElements) {
          builder.appendImmediate(", ", "");
        } else {
          hasElements = true;
        }
        builder.append("\"");
        builder.append(Strings.convertToJavaString(d));
        builder.append("\"");
      }
      builder.append(")");
    }
    return builder;
  }

  /**
   * Produces the Java for a container query name argument.
   *
   * @param name
   *          the name expression, must not be {@code null}
   * @param contextType
   *          the type of the rule context object, must not be {@code null}
   * @param model
   *          the scope model, must not be {@code null}
   * @return the Java name argument source, never {@code null}
   */
  public String nameValue(final Expression name, final EClass contextType, final ScopeModel model) {
    return doExpression(name, model, "ctx", contextType);
  }

  protected CharSequence _scopeExpressionPart(final GlobalScopeExpression it, final ScopeModel model, final String typeOrRef,
      final ScopeDefinition scope) {
    final StringConcatenation builder = new StringConcatenation();
    final List<LambdaDataExpression> matchData = dataOfType(it, LambdaDataExpression.class);
    builder.newLineIfNotEmpty();
    if (matchData.isEmpty() && it.getPrefix() == null) {
      builder.append("newContainerScope(");
    } else if (matchData.isEmpty() && it.getPrefix() != null) {
      builder.append("newPrefixedContainerScope(");
    } else {
      builder.append("newDataMatchScope(");
    }
    builder.append("\"");
    builder.append(scopeProviderX.locatorString(it));
    builder.append("\", scope, ctx, ");
    builder.append(query(it, model, typeOrRef, scope));
    builder.append(", originalResource");
    if (!matchData.isEmpty()) {
      builder.append(", //");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append("java.util.Arrays.asList(");
      builder.newLine();
      boolean hasElements = false;
      for (final LambdaDataExpression d : matchData) {
        if (hasElements) {
          builder.appendImmediate(",", "");
        } else {
          hasElements = true;
        }
        builder.append(lambdaDataMatch(d, model, ruleContextType(it), it));
      }
      builder.append(")");
    } else if (it.getPrefix() != null) {
      builder.append(", ");
      builder.append(stringExpression(it.getPrefix(), ruleContextType(it)));
      builder.append(", ");
      builder.append(it.isRecursivePrefix());
    }
    return builder;
  }

  /**
   * Produces the Java lambda for a single data match filter. The filter value is emitted inline (or as an
   * {@code EXPRESSION_NOT_SUPPORTED} marker when the compiler cannot handle it).
   *
   * @param d
   *          the lambda data expression, must not be {@code null}
   * @param model
   *          the scope model, must not be {@code null}
   * @param contextType
   *          the type of the rule context object, must not be {@code null}
   * @param owner
   *          the enclosing global scope expression (used for diagnostics), must not be {@code null}
   * @return the Java lambda source, never {@code null}
   */
  public String lambdaDataMatch(final LambdaDataExpression d, final ScopeModel model, final EClass contextType, final GlobalScopeExpression owner) {
    final String descType = "org.eclipse.xtext.resource.IEObjectDescription";
    final ScopeTranslationContext cc = translator.newCompilationContext("ctx", contextType, List.of(Pair.of(d.getDesc(), descType)), owner);
    if (compiler.isCompilable(d.getValue(), cc)) {
      return d.getDesc() + " -> " + compiler.javaExpression(d.getValue(), cc);
    }
    return d.getDesc() + " -> EXPRESSION_NOT_SUPPORTED(\"" + ExpressionExtensions.serialize(owner) + "\")";
  }

  protected CharSequence _scopeExpressionNaming(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef,
      final ScopeDefinition scope) {
    error("Xtend called the wrong definition for scopeExpressionNaming with this=" + it.toString()
        + generatorUtilX.javaContributorComment(generatorUtilX.location(it)));
    return null;
  }

  protected CharSequence _scopeExpressionNaming(final SimpleScopeExpression it, final ScopeModel model, final String typeOrRef,
      final ScopeDefinition scope) {
    return name(it, model, typeOrRef, "ctx", ruleContextType(it));
  }

  protected CharSequence _scopeExpressionNaming(final GlobalScopeExpression it, final ScopeModel model, final String typeOrRef,
      final ScopeDefinition scope) {
    return ", " + name(it, model, typeOrRef, "ctx", ruleContextType(it));
  }

  public String scopeExpressionCasing(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    return ", " + Boolean.toString(scopeProviderX.isCaseInsensitive(it));
  }

  /**
   * Produces the Java source for the element collection of a scope expression.
   * <p>
   * The expression is inlined at the call site, exactly as the legacy generator did. Extracting it into a generated
   * helper method is not an option: the scope templates pass the result straight into overloaded framework calls
   * such as {@code newSimpleScope(...)}, so the helper would need the expression's exact static type. That type is
   * not available, because the helper bodies would be emitted as source fragments rather than as linked
   * {@link org.eclipse.xtext.xbase.XExpression}s, so the helper would have to be declared as returning
   * {@code Object} and the generated code would not compile.
   *
   * @param it
   *          the expression to translate, must not be {@code null}
   * @param model
   *          the scope model, must not be {@code null}
   * @param type
   *          the type of the context variable, may be {@code null} for global rules
   * @param object
   *          the name of the context variable, must not be {@code null}
   * @return the Java source of the expression, never {@code null}
   */
  public String scopedElements(final Expression it, final ScopeModel model, final EClass type, final String object) {
    return doExpression(it, model, object, type);
  }

  public String doExpression(final Expression it, final ScopeModel model, final String object, final EClass type) {
    return compiler.javaExpression(it, translator.newCompilationContext(object, type, List.of(), it));
  }

  public CharSequence name(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef, final String contextName,
      final EClass contextType) {
    if (it.getNaming() != null) {
      return nameProviderGenerator.nameFunctions(it.getNaming(), model, contextName, contextType);
    }
    return "getNameFunctions(" + typeOrRef + ")";
  }

  public void error(final String message) {
    throw new RuntimeException(message); // NOPMD the raw type is the contract the generated scope providers were built against
  }
  // CHECKSTYLE:CONSTANTS-ON

  private boolean _isEmptyList(final Expression it) {
    return false;
  }

  private boolean _isEmptyList(final ListLiteral it) {
    return it.getElements().isEmpty();
  }

  /**
   * Returns the scope definitions of the given model that either have or do not have a reference, preserving the
   * order {@code allScopes} established.
   *
   * @param model
   *          the scope model, must not be {@code null}
   * @param withReference
   *          {@code true} to keep the definitions that carry a reference, {@code false} to keep the others
   * @return the matching scope definitions, never {@code null}
   */
  private List<ScopeDefinition> scopesWithReference(final ScopeModel model, final boolean withReference) {
    return scopeProviderX.allScopes(model).stream().filter(s -> (s.getReference() != null) == withReference).toList();
  }

  /**
   * Returns the distinct scope names of the given definitions, in encounter order.
   *
   * @param scopes
   *          the scope definitions, must not be {@code null}
   * @return the distinct scope names, never {@code null}
   */
  private Set<String> scopeNames(final List<ScopeDefinition> scopes) {
    final Set<String> names = new LinkedHashSet<>();
    for (final ScopeDefinition scope : scopes) {
      names.add(scopeProviderX.getScopeName(scope));
    }
    return names;
  }

  /**
   * Returns the definitions among the given ones that carry the given scope name.
   *
   * @param scopes
   *          the scope definitions, must not be {@code null}
   * @param name
   *          the scope name to match, may be {@code null}
   * @return the matching scope definitions, never {@code null}
   */
  private List<ScopeDefinition> namedScopes(final List<ScopeDefinition> scopes, final String name) {
    return scopes.stream().filter(s -> Objects.equals(scopeProviderX.getScopeName(s), name)).toList();
  }

  /**
   * Returns the global rules of the given scope definition.
   *
   * @param scope
   *          the scope definition, must not be {@code null}
   * @return the global scope rules, never {@code null}
   */
  private List<ScopeRule> globalRules(final ScopeDefinition scope) {
    return scopeProviderX.allScopeRules(scope).stream().filter(r -> r.getContext().isGlobal()).toList();
  }

  /**
   * Returns the context type of the scope rule containing the given element.
   *
   * @param element
   *          the element inside a scope rule, must not be {@code null}
   * @return the rule's context type, {@code null} for a global rule
   */
  private EClass ruleContextType(final ScopeExpression element) {
    return scopeProviderX.eContainer(element, ScopeRule.class).getContext().getContextType();
  }

  /**
   * Returns the data expressions of the given global scope expression that are of the given type, in model order.
   *
   * @param <T>
   *          the data expression type
   * @param it
   *          the global scope expression, must not be {@code null}
   * @param type
   *          the data expression type to keep, must not be {@code null}
   * @return the matching data expressions, never {@code null}
   */
  private <T> List<T> dataOfType(final GlobalScopeExpression it, final Class<T> type) {
    return it.getData().stream().filter(type::isInstance).map(type::cast).toList();
  }

  /**
   * Returns a reversed mutable copy of the given list.
   *
   * @param <T>
   *          the element type
   * @param list
   *          the list to reverse, must not be {@code null}
   * @return the reversed copy, never {@code null}
   */
  private static <T> List<T> reversed(final List<T> list) {
    final List<T> copy = new ArrayList<>(list);
    Collections.reverse(copy);
    return copy;
  }

  public CharSequence scopeExpression(final ScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope,
      final Boolean isGlobal) {
    return switch (it) {
      case SimpleScopeExpression expression -> _scopeExpression(expression, model, typeOrRef, scope, isGlobal);
      case FactoryExpression expression -> _scopeExpression(expression, model, typeOrRef, scope, isGlobal);
      case NamedScopeExpression expression -> _scopeExpression(expression, model, typeOrRef, scope, isGlobal);
      case ScopeDelegation delegation -> _scopeExpression(delegation, model, typeOrRef, scope, isGlobal);
      case null -> throw new IllegalArgumentException(
          "Unhandled parameter types: " + Arrays.<Object>asList(it, model, typeOrRef, scope, isGlobal).toString());
      default -> _scopeExpression(it, model, typeOrRef, scope, isGlobal);
    };
  }

  public String factoryArgument(final Expression it) {
    return switch (it) {
      case BooleanLiteral literal -> _factoryArgument(literal);
      case IntegerLiteral literal -> _factoryArgument(literal);
      case NullLiteral literal -> _factoryArgument(literal);
      case RealLiteral literal -> _factoryArgument(literal);
      case StringLiteral literal -> _factoryArgument(literal);
      case FeatureCall call -> _factoryArgument(call);
      case null -> throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it).toString());
      default -> _factoryArgument(it);
    };
  }

  public CharSequence scopeExpressionPart(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    return switch (it) {
      case GlobalScopeExpression expression -> _scopeExpressionPart(expression, model, typeOrRef, scope);
      case SimpleScopeExpression expression -> _scopeExpressionPart(expression, model, typeOrRef, scope);
      case null -> throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it, model, typeOrRef, scope).toString());
      default -> _scopeExpressionPart(it, model, typeOrRef, scope);
    };
  }

  public CharSequence scopeExpressionNaming(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef,
      final ScopeDefinition scope) {
    return switch (it) {
      case GlobalScopeExpression expression -> _scopeExpressionNaming(expression, model, typeOrRef, scope);
      case SimpleScopeExpression expression -> _scopeExpressionNaming(expression, model, typeOrRef, scope);
      case null -> throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it, model, typeOrRef, scope).toString());
      default -> _scopeExpressionNaming(it, model, typeOrRef, scope);
    };
  }

  private boolean isEmptyList(final Expression it) {
    return switch (it) {
      case ListLiteral literal -> _isEmptyList(literal);
      case null -> throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it).toString());
      default -> _isEmptyList(it);
    };
  }

}
