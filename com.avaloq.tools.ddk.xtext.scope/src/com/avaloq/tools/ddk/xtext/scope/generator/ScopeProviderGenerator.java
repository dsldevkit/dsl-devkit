/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. it program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies it distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.scope.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall;
import com.avaloq.tools.ddk.xtext.expression.generator.CodeGenerationX;
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext;
import com.avaloq.tools.ddk.xtext.expression.generator.EClassComparator;
import com.avaloq.tools.ddk.xtext.expression.generator.ExpressionExtensionsX;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.Naming;
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
import com.google.common.collect.Lists;
import com.google.inject.Inject;

import org.eclipse.emf.ecore.EClass;

public class ScopeProviderGenerator {

  @Inject
  private CodeGenerationX codeGenerationX;
  @Inject
  private ExpressionExtensionsX expressionExtensionsX;
  @Inject
  private GeneratorUtilX generatorUtilX;
  @Inject
  private Naming naming;
  @Inject
  private ScopeProviderX scopeProviderX;

  private ScopeNameProviderGenerator nameProviderGenerator;
  private CompilationContext compilationContext;
  private GenModelUtilX genModelUtil;

  public CharSequence generate(final ScopeModel it, final ScopeNameProviderGenerator nameProviderGenerator, final CompilationContext compilationContext, final GenModelUtilX genModelUtil) {
    this.nameProviderGenerator = nameProviderGenerator;
    this.compilationContext = compilationContext;
    this.genModelUtil = genModelUtil;
    final StringBuilder builder = new StringBuilder();
    builder.append("package ").append(naming.toJavaPackage(scopeProviderX.getScopeProvider(it))).append(";\n");
    builder.append("\n");
    builder.append("import java.util.Arrays;\n");
    builder.append("\n");
    builder.append("import org.apache.logging.log4j.Logger;\n");
    builder.append("import org.apache.logging.log4j.LogManager;\n");
    builder.append("import org.eclipse.emf.ecore.EClass;\n");
    builder.append("import org.eclipse.emf.ecore.EObject;\n");
    builder.append("import org.eclipse.emf.ecore.EPackage;\n");
    builder.append("import org.eclipse.emf.ecore.EReference;\n");
    builder.append("import org.eclipse.emf.ecore.resource.Resource;\n");
    builder.append("\n");
    builder.append("import org.eclipse.xtext.naming.QualifiedName;\n");
    builder.append("import org.eclipse.xtext.resource.IEObjectDescription;\n");
    builder.append("import org.eclipse.xtext.scoping.IScope;\n");
    builder.append("\n");
    builder.append("import com.avaloq.tools.ddk.xtext.scoping.AbstractNameFunction;\n");
    builder.append("import com.avaloq.tools.ddk.xtext.scoping.AbstractPolymorphicScopeProvider;\n");
    builder.append("import com.avaloq.tools.ddk.xtext.scoping.IContextSupplier;\n");
    builder.append("import com.avaloq.tools.ddk.xtext.scoping.INameFunction;\n");
    builder.append("import com.avaloq.tools.ddk.xtext.scoping.NameFunctions;\n");
    builder.append("import com.avaloq.tools.ddk.xtext.util.EObjectUtil;\n");
    builder.append("\n");
    builder.append("import com.google.common.base.Predicate;\n");
    if (!scopeProviderX.allInjections(it).isEmpty()) {
      builder.append("import com.google.inject.Inject;\n");
    }
    builder.append("\n");
    builder.append("@SuppressWarnings(\"all\")\n");
    builder.append("public class ").append(naming.toSimpleName(scopeProviderX.getScopeProvider(it))).append(" extends AbstractPolymorphicScopeProvider {\n");
    builder.append("\n");
    builder.append("  /** Class-wide logger. */\n");
    builder.append("  private static final Logger LOGGER = LogManager.getLogger(").append(naming.toSimpleName(scopeProviderX.getScopeProvider(it))).append(".class);\n");
    if (!scopeProviderX.allInjections(it).isEmpty()) {
      for (final com.avaloq.tools.ddk.xtext.scope.scope.Injection i : scopeProviderX.allInjections(it)) {
        builder.append("  @Inject\n");
        builder.append("  private ").append(i.getType()).append(" ").append(i.getName()).append(";\n");
      }
    }
    builder.append("\n");
    builder.append(scopeMethods(it, naming.toSimpleName(it.getName())));
    builder.append("\n");
    builder.append("}\n");
    return builder;
  }

  public CharSequence scopeMethods(final ScopeModel it, final String baseName) {
    final StringBuilder builder = new StringBuilder();

    // doGetScope with EReference
    builder.append("  @Override\n");
    builder.append("  protected IScope doGetScope(final EObject context, final EReference reference, final String scopeName, final Resource originalResource) {\n");
    final List<ScopeDefinition> refScopes = scopeProviderX.allScopes(it).stream().filter(s -> s.getReference() != null).toList();
    if (!refScopes.isEmpty()) {
      builder.append("    if (scopeName == null) {\n");
      builder.append("      return null;\n");
      builder.append("    }\n");
      builder.append("\n");
      builder.append("    switch (scopeName) {\n");
      final Set<String> refScopeNames = refScopes.stream().map(s -> scopeProviderX.getScopeName(s)).collect(Collectors.toCollection(java.util.LinkedHashSet::new));
      for (final String scopeName : refScopeNames) {
        builder.append("    case \"").append(scopeName).append("\":\n");
        for (final ScopeDefinition scope : refScopes.stream().filter(s -> scopeProviderX.getScopeName(s).equals(scopeName)).toList()) {
          builder.append("      if (reference == ").append(genModelUtil.literalIdentifier(scope.getReference())).append(") return ").append(scopeProviderX.scopeMethodName(scope)).append("(context, reference, originalResource);\n");
        }
        builder.append("      break;\n");
      }
      builder.append("      default: break;\n");
      builder.append("    }\n");
    }
    builder.append("    return null;\n");
    builder.append("  }\n");
    builder.append("\n");

    // doGetScope with EClass
    builder.append("  @Override\n");
    builder.append("  protected IScope doGetScope(final EObject context, final EClass type, final String scopeName, final Resource originalResource) {\n");
    final List<ScopeDefinition> typeScopes = scopeProviderX.allScopes(it).stream().filter(s -> s.getReference() == null).toList();
    if (!typeScopes.isEmpty()) {
      builder.append("    if (scopeName == null) {\n");
      builder.append("      return null;\n");
      builder.append("    }\n");
      builder.append("\n");
      builder.append("    switch (scopeName) {\n");
      final Set<String> typeScopeNames = typeScopes.stream().map(s -> scopeProviderX.getScopeName(s)).collect(Collectors.toCollection(java.util.LinkedHashSet::new));
      for (final String scopeName : typeScopeNames) {
        builder.append("    case \"").append(scopeName).append("\":\n");
        for (final ScopeDefinition scope : typeScopes.stream().filter(s -> scopeProviderX.getScopeName(s).equals(scopeName)).toList()) {
          builder.append("      if (type == ").append(genModelUtil.literalIdentifier(scope.getTargetType())).append(") return ").append(scopeProviderX.scopeMethodName(scope)).append("(context, type, originalResource);\n");
        }
        builder.append("      break;\n");
      }
      builder.append("      default: break;\n");
      builder.append("    }\n");
    }
    builder.append("    return null;\n");
    builder.append("  }\n");
    builder.append("\n");

    // doGlobalCache with EReference
    builder.append("  @Override\n");
    builder.append("  protected boolean doGlobalCache(final EObject context, final EReference reference, final String scopeName, final Resource originalResource) {\n");
    final List<ScopeDefinition> refGlobalScopes = refScopes.stream().filter(s -> scopeProviderX.allScopeRules(s).stream().anyMatch(r -> r.getContext().isGlobal())).toList();
    if (!refGlobalScopes.isEmpty()) {
      builder.append("    if (scopeName != null && context.eContainer() == null) {\n");
      builder.append("      switch (scopeName) {\n");
      final Set<String> refGlobalNames = refGlobalScopes.stream().map(s -> scopeProviderX.getScopeName(s)).collect(Collectors.toCollection(java.util.LinkedHashSet::new));
      for (final String scopeName : refGlobalNames) {
        builder.append("      case \"").append(scopeName).append("\":\n");
        for (final ScopeDefinition scope : refScopes.stream().filter(s -> scopeProviderX.getScopeName(s).equals(scopeName)).toList()) {
          final List<ScopeRule> globalRules = scopeProviderX.allScopeRules(scope).stream().filter(r -> r.getContext().isGlobal()).toList();
          if (!globalRules.isEmpty()) {
            builder.append("        if (reference == ").append(genModelUtil.literalIdentifier(scope.getReference())).append(") return true;\n");
          }
        }
        builder.append("        break;\n");
      }
      builder.append("        default: break;\n");
      builder.append("      }\n");
      builder.append("    }\n");
    }
    builder.append("    return false;\n");
    builder.append("  }\n");
    builder.append("\n");

    // doGlobalCache with EClass
    builder.append("  @Override\n");
    builder.append("  protected boolean doGlobalCache(final EObject context, final EClass type, final String scopeName, final Resource originalResource) {\n");
    final List<ScopeDefinition> typeGlobalScopes = typeScopes.stream().filter(s -> scopeProviderX.allScopeRules(s).stream().anyMatch(r -> r.getContext().isGlobal())).toList();
    if (!typeGlobalScopes.isEmpty()) {
      builder.append("    if (context.eContainer() == null) {\n");
      builder.append("      switch (scopeName) {\n");
      final Set<String> typeGlobalNames = typeGlobalScopes.stream().map(s -> scopeProviderX.getScopeName(s)).collect(Collectors.toCollection(java.util.LinkedHashSet::new));
      for (final String scopeName : typeGlobalNames) {
        builder.append("      case \"").append(scopeName).append("\":\n");
        for (final ScopeDefinition scope : typeScopes.stream().filter(s -> scopeProviderX.getScopeName(s).equals(scopeName)).toList()) {
          final List<ScopeRule> globalRules = scopeProviderX.allScopeRules(scope).stream().filter(r -> r.getContext().isGlobal()).toList();
          if (!globalRules.isEmpty()) {
            builder.append("        if (type == ").append(genModelUtil.literalIdentifier(scope.getTargetType())).append(") return true;\n");
          }
        }
        builder.append("        break;\n");
      }
      builder.append("        default: break;\n");
      builder.append("      }\n");
      builder.append("    }\n");
    }
    builder.append("    return false;\n");
    builder.append("  }\n");
    builder.append("\n");

    // Per-scope methods
    for (final ScopeDefinition scope : scopeProviderX.allScopes(it)) {
      builder.append("  protected IScope ").append(scopeProviderX.scopeMethodName(scope)).append("(final EObject context, final ");
      if (scope.getReference() != null) {
        builder.append("EReference ref");
      } else {
        builder.append("EClass type");
      }
      builder.append(", final Resource originalResource) {\n");
      final List<ScopeRule> localRules = scopeProviderX.allScopeRules(scope).stream().filter(r -> !r.getContext().isGlobal()).toList();
      final List<ScopeRule> globalRules = scopeProviderX.allScopeRules(scope).stream().filter(r -> r.getContext().isGlobal()).toList();
      if (globalRules.size() > 1) {
        throw new RuntimeException("only one global rule allowed");
      }
      for (final ScopeRule r : scopeProviderX.sortedRules(scopeProviderX.filterUniqueRules(new ArrayList<>(localRules)))) {
        builder.append("    ").append(generatorUtilX.javaContributorComment(generatorUtilX.location(r))).append("\n");
        if (EClassComparator.isEObjectType(r.getContext().getContextType())) {
          builder.append("    if (true) {\n");
        } else {
          builder.append("    if (context instanceof ").append(genModelUtil.instanceClassName(r.getContext().getContextType())).append(") {\n");
        }
        builder.append("      final ").append(genModelUtil.instanceClassName(r.getContext().getContextType())).append(" ctx = (").append(genModelUtil.instanceClassName(r.getContext().getContextType())).append(") context;\n");
        final List<ScopeRule> rulesForTypeAndContext = localRules.stream().filter(r2 -> scopeProviderX.hasSameContext(r2, r)).toList();
        builder.append(scopeRuleBlock(rulesForTypeAndContext, it, scopeProviderX.contextRef(r) != null ? "ref" : "type", r.getContext().getContextType(), r.getContext().isGlobal()));
        builder.append("    }\n");
      }
      if (!localRules.isEmpty() || !globalRules.isEmpty()) {
        builder.append("\n");
        builder.append("    final EObject eContainer = context.eContainer();\n");
        builder.append("    if (eContainer != null) {\n");
        builder.append("      return internalGetScope(");
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
        builder.append(", \"").append(scopeProviderX.getScopeName(scope)).append("\", originalResource);\n");
        builder.append("    }\n");
        builder.append("\n");
      }
      if (!globalRules.isEmpty()) {
        final ScopeRule r = globalRules.get(0);
        final List<ScopeRule> rulesForTypeAndContext = List.of(r);
        builder.append("    ").append(generatorUtilX.javaContributorComment(generatorUtilX.location(r))).append("\n");
        builder.append("    if (context.eResource() != null) {\n");
        builder.append("      final Resource ctx = context.eResource();\n");
        builder.append(scopeRuleBlock(rulesForTypeAndContext, it, scopeProviderX.contextRef(r) != null ? "ref" : "type", r.getContext().getContextType(), r.getContext().isGlobal()));
        builder.append("    }\n");
        builder.append("\n");
      }
      builder.append("    return null;\n");
      builder.append("  }\n");
      builder.append("\n");
    }

    return builder;
  }

  public CharSequence scopeRuleBlock(final List<ScopeRule> it, final ScopeModel model, final String typeOrRef, final EClass contextType, final Boolean isGlobal) {
    final StringBuilder builder = new StringBuilder();
    builder.append("      IScope scope = IScope.NULLSCOPE;\n");
    builder.append("      try {\n");
    if (it.stream().anyMatch(r -> r.getContext().getGuard() != null)) {
      boolean first = true;
      final List<ScopeRule> sorted = it.stream()
          .sorted(Comparator.comparingInt(r -> r.getContext().getGuard() == null ? it.size() : it.indexOf(r)))
          .toList();
      for (final ScopeRule r : sorted) {
        if (!first) {
          builder.append(" else ");
        } else {
          builder.append("        ");
          first = false;
        }
        if (r.getContext().getGuard() != null) {
          builder.append("if (").append(codeGenerationX.javaExpression(r.getContext().getGuard(), compilationContext.clone("ctx", scopeProviderX.scopeType(r)))).append(") ");
        }
        builder.append("{\n");
        if (it.size() > 1) {
          builder.append("          ").append(generatorUtilX.javaContributorComment(generatorUtilX.location(r))).append("\n");
        }
        final List<ScopeExpression> reversed = Lists.newArrayList(r.getExprs());
        Collections.reverse(reversed);
        for (final ScopeExpression e : reversed) {
          builder.append(scopeExpression(e, model, typeOrRef, scopeProviderX.getScope(r), isGlobal));
        }
        builder.append("        }");
      }
      if (it.stream().noneMatch(r -> r.getContext().getGuard() == null)) {
        builder.append(" else {\n");
        builder.append("          throw new UnsupportedOperationException(); // continue matching other definitions\n");
        builder.append("        }");
      }
      builder.append("\n");
    } else if (it.size() == 1) {
      final List<ScopeExpression> reversed = Lists.newArrayList(it.get(0).getExprs());
      Collections.reverse(reversed);
      for (final ScopeExpression e : reversed) {
        builder.append(scopeExpression(e, model, typeOrRef, scopeProviderX.getScope(it.get(0)), isGlobal));
      }
    } else {
      final List<String> locations = new ArrayList<>();
      for (final ScopeRule r : it) {
        locations.add(generatorUtilX.location(r));
      }
      error("scope context not unique for definitions: " + String.join(", ", locations));
    }
    builder.append("      } catch (Exception e) {\n");
    builder.append("        LOGGER.error(\"Error calculating scope for ");
    if (isGlobal) {
      builder.append("Resource. Context:");
    } else {
      builder.append(contextType.getName());
    }
    builder.append(" \" + EObjectUtil.getLocationString(context) + \" (").append(scopeProviderX.locatorString(it.get(0))).append(")\", e);\n");
    builder.append("      }\n");
    builder.append("      return scope;\n");
    return builder;
  }

  // dispatch scopeExpression
  protected CharSequence _scopeExpression(final ScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope, final Boolean isGlobal) {
    return error("Xtend called the wrong definition." + it.toString() + generatorUtilX.javaContributorComment(generatorUtilX.location(it)));
  }

  protected CharSequence _scopeExpression(final FactoryExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope, final Boolean isGlobal) {
    final StringBuilder b = new StringBuilder();
    final CompilationContext ctx = compilationContext.clone("ctx", scopeProviderX.eContainer(it, ScopeRule.class).getContext().getContextType());
    b.append("scope = ").append(javaCall(it.getExpr(), ctx)).append("(scope, ctx, ").append(typeOrRef).append(", originalResource");
    if (it.getExpr() instanceof OperationCall operationCall) {
      for (final Expression param : operationCall.getParams()) {
        b.append(", ").append(codeGenerationX.javaExpression(param, ctx));
      }
    }
    b.append(");\n");
    return b;
  }

  // dispatch javaCall
  protected String _javaCall(final Expression it, final CompilationContext ctx) {
    return error("cannot handle scope factory " + it.toString());
  }

  protected String _javaCall(final OperationCall it, final CompilationContext ctx) {
    if (codeGenerationX.isJavaExtensionCall(it, ctx)) {
      return codeGenerationX.calledJavaMethod(it, ctx);
    } else {
      return "/* Error: cannot handle scope factory " + it.toString() + " */";
    }
  }

  public String javaCall(final Expression it, final CompilationContext ctx) {
    if (it instanceof OperationCall operationCall) {
      return _javaCall(operationCall, ctx);
    } else if (it != null) {
      return _javaCall(it, ctx);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + it);
    }
  }

  protected CharSequence _scopeExpression(final ScopeDelegation it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope, final Boolean isGlobal) {
    final StringBuilder builder = new StringBuilder();
    if (it.getDelegate() != null) {
      final String delegateString = expressionExtensionsX.serialize(it.getDelegate());
      if ("this.eContainer()".equals(delegateString) || "this.eContainer".equals(delegateString) || "eContainer()".equals(delegateString) || "eContainer".equals(delegateString)) {
        builder.append("        scope = newSameScope(\"").append(scopeProviderX.locatorString(it)).append("\", scope, ctx.eContainer()");
      } else if ("this".equals(delegateString)) {
        builder.append("        scope = newSameScope(\"").append(scopeProviderX.locatorString(it)).append("\", scope, ctx");
      } else {
        builder.append("        scope = newDelegateScope(\"").append(scopeProviderX.locatorString(it)).append("\", scope, ");
        if (!isGlobal) {
          builder.append("() -> IContextSupplier.makeIterable(").append(scopedElements(it.getDelegate(), model, scopeProviderX.eContainer(it, ScopeRule.class).getContext().getContextType(), "ctx")).append(")");
        } else {
          builder.append(scopedElements(it.getDelegate(), model, scopeProviderX.eContainer(it, ScopeRule.class).getContext().getContextType(), "ctx"));
        }
      }
    } else {
      builder.append("        scope = newExternalDelegateScope(\"").append(scopeProviderX.locatorString(it)).append("\", scope, ");
      builder.append(query(it.getExternal(), model, typeOrRef, scope)).append(".execute(originalResource)");
    }
    builder.append(", ");
    if (it.getScope() != null && scopeProviderX.typeOrRef(it.getScope()) != scopeProviderX.typeOrRef(scopeProviderX.getScope(it))) {
      builder.append(genModelUtil.literalIdentifier(scopeProviderX.typeOrRef(it.getScope())));
    } else {
      builder.append(typeOrRef);
    }
    builder.append(", \"");
    if (it.getScope() != null && it.getScope().getName() != null) {
      builder.append(it.getScope().getName());
    } else {
      builder.append("scope");
    }
    builder.append("\", originalResource);\n");
    return builder;
  }

  protected CharSequence _scopeExpression(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope, final Boolean isGlobal) {
    final StringBuilder builder = new StringBuilder();
    builder.append("        scope = ").append(scopeExpressionPart(it, model, typeOrRef, scope));
    builder.append(scopeExpressionNaming(it, model, typeOrRef, scope));
    builder.append(scopeExpressionCasing(it, model, typeOrRef, scope)).append(");\n");
    return builder;
  }

  protected CharSequence _scopeExpression(final SimpleScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope, final Boolean isGlobal) {
    final StringBuilder builder = new StringBuilder();
    if (expressionExtensionsX.isEmptyList(it.getExpr())) {
      builder.append("        // Empty scope from ").append(generatorUtilX.location(it)).append("\n");
    } else {
      builder.append("        scope = ").append(scopeExpressionPart(it, model, typeOrRef, scope));
      builder.append(scopeExpressionNaming(it, model, typeOrRef, scope));
      builder.append(scopeExpressionCasing(it, model, typeOrRef, scope)).append(");\n");
    }
    return builder;
  }

  public CharSequence scopeExpression(final ScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope, final Boolean isGlobal) {
    if (it instanceof FactoryExpression factoryExpression) {
      return _scopeExpression(factoryExpression, model, typeOrRef, scope, isGlobal);
    } else if (it instanceof ScopeDelegation scopeDelegation) {
      return _scopeExpression(scopeDelegation, model, typeOrRef, scope, isGlobal);
    } else if (it instanceof SimpleScopeExpression simpleScopeExpression) {
      return _scopeExpression(simpleScopeExpression, model, typeOrRef, scope, isGlobal);
    } else if (it instanceof GlobalScopeExpression) {
      // GlobalScopeExpression extends NamedScopeExpression, must be checked first
      return _scopeExpression((NamedScopeExpression) it, model, typeOrRef, scope, isGlobal);
    } else if (it instanceof NamedScopeExpression namedScopeExpression) {
      return _scopeExpression(namedScopeExpression, model, typeOrRef, scope, isGlobal);
    } else if (it != null) {
      return _scopeExpression(it, model, typeOrRef, scope, isGlobal);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + it);
    }
  }

  // dispatch scopeExpressionPart
  protected String _scopeExpressionPart(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    return error("Xtend called the wrong definition for scopeExpressionPart with this=" + it.toString() + generatorUtilX.javaContributorComment(generatorUtilX.location(it)));
  }

  protected String _scopeExpressionPart(final SimpleScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    return "newSimpleScope(\"" + scopeProviderX.locatorString(it) + "\", scope, " + scopedElements(it.getExpr(), model, scopeProviderX.eContainer(it, ScopeRule.class).getContext().getContextType(), "ctx") + ", ";
  }

  protected CharSequence _scopeExpressionPart(final GlobalScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    final StringBuilder builder = new StringBuilder();
    final List<LambdaDataExpression> matchData = new ArrayList<>();
    for (final Object d : it.getData()) {
      if (d instanceof LambdaDataExpression lambdaDataExpression) {
        matchData.add(lambdaDataExpression);
      }
    }
    builder.append("\n");
    if (matchData.isEmpty() && it.getPrefix() == null) {
      builder.append("newContainerScope(");
    } else if (matchData.isEmpty() && it.getPrefix() != null) {
      builder.append("newPrefixedContainerScope(");
    } else {
      builder.append("newDataMatchScope(");
    }
    builder.append("\"").append(scopeProviderX.locatorString(it)).append("\", scope, ctx, ");
    builder.append(query(it, model, typeOrRef, scope)).append(", originalResource");
    if (!matchData.isEmpty()) {
      builder.append(", //\n");
      builder.append("      Arrays.asList(\n");
      boolean firstData = true;
      for (final LambdaDataExpression d : matchData) {
        if (!firstData) {
          builder.append(",\n");
        }
        firstData = false;
        final CompilationContext cc = compilationContext.cloneWithVariable("ctx", scopeProviderX.eContainer(it, ScopeRule.class).getContext().getContextType(), d.getDesc(), "org::eclipse::xtext::resource::IEObjectDescription");
        if (codeGenerationX.isCompilable(d.getValue(), cc.clone("ctx"))) {
          builder.append("    ").append(d.getDesc()).append(" -> ").append(codeGenerationX.javaExpression(d.getValue(), cc.clone("ctx")));
        } else {
          builder.append("    ").append(d.getDesc()).append(" -> EXPRESSION_NOT_SUPPORTED(\"").append(expressionExtensionsX.serialize(it)).append("\")");
        }
      }
      builder.append("  )");
    } else if (it.getPrefix() != null) {
      builder.append(", ").append(doExpression(it.getPrefix(), model, "ctx", scopeProviderX.eContainer(it, ScopeRule.class).getContext().getContextType()));
      builder.append(", ").append(it.isRecursivePrefix());
    }
    return builder;
  }

  public CharSequence scopeExpressionPart(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    if (it instanceof GlobalScopeExpression globalScopeExpression) {
      return _scopeExpressionPart(globalScopeExpression, model, typeOrRef, scope);
    } else if (it instanceof SimpleScopeExpression simpleScopeExpression) {
      return _scopeExpressionPart(simpleScopeExpression, model, typeOrRef, scope);
    } else if (it != null) {
      return _scopeExpressionPart(it, model, typeOrRef, scope);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + it);
    }
  }

  public CharSequence query(final GlobalScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    final StringBuilder builder = new StringBuilder();
    builder.append("newQuery(").append(genModelUtil.literalIdentifier(it.getType())).append(")");
    final List<MatchDataExpression> matchData = new ArrayList<>();
    for (final Object d : it.getData()) {
      if (d instanceof MatchDataExpression matchDataExpression) {
        matchData.add(matchDataExpression);
      }
    }
    if (it.getName() != null) {
      builder.append(".name(").append(doExpression(it.getName(), model, "ctx", scopeProviderX.eContainer(it, ScopeRule.class).getContext().getContextType())).append(")");
    }
    if (!matchData.isEmpty()) {
      for (final MatchDataExpression d : matchData) {
        builder.append(".data(\"").append(codeGenerationX.javaEncode(d.getKey())).append("\", ").append(doExpression(d.getValue(), model, "ctx", scopeProviderX.eContainer(it, ScopeRule.class).getContext().getContextType())).append(")");
      }
    }
    if (!it.getDomains().isEmpty() && !"*".equals(it.getDomains().get(0))) {
      builder.append(".domains(");
      boolean firstDomain = true;
      for (final String d : it.getDomains()) {
        if (!firstDomain) {
          builder.append(", ");
        }
        firstDomain = false;
        builder.append("\"").append(codeGenerationX.javaEncode(d)).append("\"");
      }
      builder.append(")");
    }
    return builder;
  }

  // dispatch scopeExpressionNaming
  protected String _scopeExpressionNaming(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    return error("Xtend called the wrong definition for scopeExpressionNaming with this=" + it.toString() + generatorUtilX.javaContributorComment(generatorUtilX.location(it)));
  }

  protected String _scopeExpressionNaming(final SimpleScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    return name(it, model, typeOrRef, "ctx", scopeProviderX.eContainer(it, ScopeRule.class).getContext().getContextType());
  }

  protected String _scopeExpressionNaming(final GlobalScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    return ", " + name(it, model, typeOrRef, "ctx", scopeProviderX.eContainer(it, ScopeRule.class).getContext().getContextType());
  }

  public String scopeExpressionNaming(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    if (it instanceof GlobalScopeExpression globalScopeExpression) {
      return _scopeExpressionNaming(globalScopeExpression, model, typeOrRef, scope);
    } else if (it instanceof SimpleScopeExpression simpleScopeExpression) {
      return _scopeExpressionNaming(simpleScopeExpression, model, typeOrRef, scope);
    } else if (it != null) {
      return _scopeExpressionNaming(it, model, typeOrRef, scope);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + it);
    }
  }

  public String scopeExpressionCasing(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef, final ScopeDefinition scope) {
    return ", " + Boolean.toString(scopeProviderX.isCaseInsensitive(it));
  }

  public String scopedElements(final Expression it, final ScopeModel model, final EClass type, final String object) {
    return doExpression(it, model, object, type);
  }

  public String doExpression(final Expression it, final ScopeModel model, final String object, final EClass type) {
    return codeGenerationX.javaExpression(it, compilationContext.clone(object, type));
  }

  public String name(final NamedScopeExpression it, final ScopeModel model, final String typeOrRef, final String contextName, final EClass contextType) {
    if (it.getNaming() != null) {
      return nameProviderGenerator.nameFunctions(it.getNaming(), model, contextName, contextType).toString();
    } else {
      return "getNameFunctions(" + typeOrRef + ")";
    }
  }

  public String error(final String message) {
    throw new RuntimeException(message);
  }
}
