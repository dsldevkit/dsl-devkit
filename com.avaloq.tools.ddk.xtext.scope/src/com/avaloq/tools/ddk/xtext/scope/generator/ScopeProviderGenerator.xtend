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

package com.avaloq.tools.ddk.xtext.scope.generator

import com.avaloq.tools.ddk.xtext.expression.expression.Expression
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall
import com.avaloq.tools.ddk.xtext.expression.expression.BooleanLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall
import com.avaloq.tools.ddk.xtext.expression.expression.IntegerLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.ListLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.NullLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.RealLiteral
import com.avaloq.tools.ddk.xtext.expression.expression.StringLiteral
import com.avaloq.tools.ddk.xtext.expression.generator.EClassComparator
import com.avaloq.tools.ddk.xtext.expression.generator.ExpressionExtensions
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX
import com.avaloq.tools.ddk.xtext.scope.scope.FactoryExpression
import com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression
import com.avaloq.tools.ddk.xtext.scope.scope.LambdaDataExpression
import com.avaloq.tools.ddk.xtext.scope.scope.MatchDataExpression
import com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeExpression
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule
import com.avaloq.tools.ddk.xtext.scope.scope.SimpleScopeExpression
import com.avaloq.tools.ddk.xtext.scope.jvmmodel.ScopeExpressionCompiler
import com.avaloq.tools.ddk.xtext.scope.jvmmodel.ScopeExpressionMethodRequest
import com.avaloq.tools.ddk.xtext.scope.jvmmodel.ScopeExpressionTranslator
import com.google.common.collect.Lists
import com.google.inject.Inject
import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.util.Strings

class ScopeProviderGenerator {

  @Inject extension GeneratorUtilX
  @Inject extension ScopeProviderX

  @Inject ScopeExpressionTranslator translator
  @Inject ScopeExpressionCompiler compiler

  ScopeNameProviderGenerator nameProviderGenerator
  extension GenModelUtilX genModelUtil

  val List<ScopeExpressionMethodRequest> expressionMethods = newArrayList
  int expressionMethodCounter

  /**
   * Configures the collaborators required by the extracted body methods. Used by the Xbase based
   * {@code ScopeJvmModelInferrer} which attaches the body methods directly to inferred JVM operations rather than
   * generating a full compilation unit through {@link #generate}.
   *
   * @param nameProviderGenerator
   *          the name provider generator, must not be {@code null}
   * @param genModelUtil
   *          the gen model utility, must not be {@code null}
   */
  def void configure(ScopeNameProviderGenerator nameProviderGenerator, GenModelUtilX genModelUtil) {
    this.nameProviderGenerator = nameProviderGenerator
    this.genModelUtil = genModelUtil
    expressionMethods.clear
    expressionMethodCounter = 0
  }

  /**
   * Returns the helper expression methods recorded while the body methods were rendered. The Xbase based
   * {@code ScopeJvmModelInferrer} reads this after rendering all bodies and contributes one inferred operation per
   * request.
   *
   * @return the recorded requests, never {@code null}
   */
  def List<ScopeExpressionMethodRequest> getExpressionMethods() {
    expressionMethods
  }

  /**
   * Produces the body of the {@code doGetScope(EObject, EReference, String, Resource)} method. Extracted so the
   * Xbase based {@code ScopeJvmModelInferrer} can attach it directly as a method body.
   *
   * @param it
   *          the scope model, must not be {@code null}
   * @return the method body, never {@code null}
   */
  def doGetScopeByReferenceBody(ScopeModel it) '''
    «IF !allScopes().filter(s|s.reference !== null).empty»
    if (scopeName == null) {
      return null;
    }

    switch (scopeName) {
    «FOR name : allScopes().filter(s|s.reference !== null).map(s|s.getScopeName()).toSet()
   »case "«name»":
      «FOR scope : allScopes().filter(s|s.reference !== null).filter(s|s.getScopeName()==name)»
      if (reference == «scope.reference.literalIdentifier()») return «scope.scopeMethodName()»(context, reference, originalResource);
      «ENDFOR»
      break;
   «
    ENDFOR»
      default: break;
    }
    «ENDIF»
    return null;
  '''

  /**
   * Produces the body of the {@code doGetScope(EObject, EClass, String, Resource)} method.
   *
   * @param it
   *          the scope model, must not be {@code null}
   * @return the method body, never {@code null}
   */
  def doGetScopeByTypeBody(ScopeModel it) '''
    «IF !allScopes().filter(s|s.reference === null).empty»
    if (scopeName == null) {
      return null;
    }

    switch (scopeName) {
    «FOR name : allScopes().filter(s|s.reference === null).map(s|s.getScopeName()).toSet()
   »case "«name»":
      «FOR scope : allScopes().filter(s|s.reference === null).filter(s|s.getScopeName()==name)»
      if (type == «scope.targetType.literalIdentifier()») return «scope.scopeMethodName()»(context, type, originalResource);
      «ENDFOR»
      break;
   «
    ENDFOR»
      default: break;
    }
    «ENDIF»
    return null;
  '''

  /**
   * Produces the body of the {@code doGlobalCache(EObject, EReference, String, Resource)} method.
   *
   * @param it
   *          the scope model, must not be {@code null}
   * @return the method body, never {@code null}
   */
  def doGlobalCacheByReferenceBody(ScopeModel it) '''
    «IF !allScopes().filter(s|s.reference !== null).filter(s|s.allScopeRules().filter(r|r.context.global).size > 0).empty»
    if (scopeName != null && context.eContainer() == null) {
      switch (scopeName) {
      «FOR name : allScopes().filter(s|s.reference !== null).filter(s|s.allScopeRules().filter(r|r.context.global).size > 0).map(s|s.getScopeName()).toSet()
     »case "«name»":
        «FOR scope : allScopes().filter(s|s.reference !== null).filter(s|s.getScopeName()==name)»
        «val globalRules = scope.allScopeRules().filter(r|r.context.global)»
        «IF globalRules.size > 0»
        if (reference == «scope.reference.literalIdentifier()») return true;
        «ENDIF»
        «ENDFOR»
        break;
     «
      ENDFOR»
        default: break;
      }
    }
    «ENDIF»
    return false;
  '''

  /**
   * Produces the body of the {@code doGlobalCache(EObject, EClass, String, Resource)} method.
   *
   * @param it
   *          the scope model, must not be {@code null}
   * @return the method body, never {@code null}
   */
  def doGlobalCacheByTypeBody(ScopeModel it) '''
    «IF !allScopes().filter(s|s.reference === null).filter(s|s.allScopeRules().filter(r|r.context.global).size > 0).empty»
    if (context.eContainer() == null) {
      switch (scopeName) {
      «FOR name : allScopes().filter(s|s.reference === null).filter(s|s.allScopeRules().filter(r|r.context.global).size > 0).map(s|s.getScopeName()).toSet()
     »case "«name»":
        «FOR scope : allScopes().filter(s|s.reference === null).filter(s|s.getScopeName()==name)»
        «val globalRules = scope.allScopeRules().filter(r|r.context.global)»
        «IF globalRules.size > 0»
        if (type == «scope.targetType.literalIdentifier()») return true;
        «ENDIF»
        «ENDFOR»
        break;
      «
       ENDFOR»
        default: break;
      }
    }
    «ENDIF»
    return false;
  '''

  /**
   * Produces the body of a single {@code <scopeMethodName>} scope method. Extracted so the Xbase based
   * {@code ScopeJvmModelInferrer} can attach it directly as a method body.
   *
   * @param scope
   *          the scope definition the method is generated for, must not be {@code null}
   * @param it
   *          the scope model, must not be {@code null}
   * @return the method body, never {@code null}
   */
  def scopeMethodBody(ScopeDefinition scope, ScopeModel it) '''
    «val localRules = scope.allScopeRules().filter(r|!r.context.global).toList»
    «val globalRules = scope.allScopeRules().filter(r|r.context.global).toList»
    «if (globalRules.size > 1) throw new RuntimeException("only one global rule allowed")»
    «FOR r : localRules.filterUniqueRules().sortedRules()»
    «javaContributorComment(r.location())»
    if («IF EClassComparator.isEObjectType(r.context.contextType)»true«ELSE»context instanceof «r.context.contextType.instanceClassName()»«ENDIF») {
      final «r.context.contextType.instanceClassName()» ctx = («r.context.contextType.instanceClassName()») context;
     «val rulesForTypeAndContext = localRules.filter(r2|r2.hasSameContext(r)).toList»
      «scopeRuleBlock(rulesForTypeAndContext, it, if (r.contextRef() !== null) 'ref' else 'type', r.context.contextType, r.context.global)»
    }
    «ENDFOR»
    «IF !localRules.isEmpty || !globalRules.isEmpty»

    final EObject eContainer = context.eContainer();
    if (eContainer != null) {
      return internalGetScope(«IF !localRules.isEmpty»eContainer«ELSE»getRootObject(eContainer)«ENDIF», «IF scope.reference !== null»ref«ELSE»type«ENDIF», "«scope.getScopeName()»", originalResource);
    }

    «ENDIF»
    «IF !globalRules.isEmpty»
    «val r = globalRules.head»
    «val rulesForTypeAndContext = #[r]»
    «javaContributorComment(r.location())»
    if (context.eResource() != null) {
      final Resource ctx = context.eResource();
      «scopeRuleBlock(rulesForTypeAndContext, it, if (r.contextRef() !== null) 'ref' else 'type', r.context.contextType, r.context.global)»
    }

    «ENDIF»
    return null;
  '''

  /**
   * Records a helper method for the guard of the given scope rule and returns the Java call that the rule block
   * uses in its {@code if} condition. The guard is evaluated against the {@code ctx} variable holding the rule's
   * context object.
   *
   * @param r
   *          the scope rule whose guard to translate, must not be {@code null}
   * @return the Java call to the generated guard method, never {@code null}
   */
  def guardCall(ScopeRule r) {
    val request = new ScopeExpressionMethodRequest
    val methodName = 'guard' + (expressionMethodCounter++)
    request.methodName = methodName
    request.returnTypeName = 'boolean'
    request.variableName = 'ctx'
    request.variableTypeName = ctxJavaTypeName(r.context.contextType)
    request.expression = r.context.guard
    request.fallbackBody = 'return ' + compiler.javaExpression(r.context.guard, translator.newCompilationContext('ctx', r.scopeType(), #[], r.context.guard)) + ';'
    expressionMethods.add(request)
    methodName + '(ctx)'
  }

  /**
   * Returns the fully-qualified Java type name to use for the {@code ctx} parameter of a generated helper
   * method. The body templates declare {@code ctx} either as the rule's context EClass instance class
   * (for local rules) or as {@link org.eclipse.emf.ecore.resource.Resource Resource} (for global rules,
   * where the scope is computed against the resource rather than a specific EObject). When the rule is
   * global, {@code ScopeContext.contextType} is {@code null}; without this helper the recorded helper
   * method would receive an empty parameter type and produce invalid Java.
   *
   * @param contextType
   *          the rule's context EClass, may be {@code null} (for global rules)
   * @return the Java type name to use for {@code ctx}, never {@code null}
   */
  def private String ctxJavaTypeName(EClass contextType) {
    if (contextType !== null) contextType.instanceClassName() else 'org.eclipse.emf.ecore.resource.Resource'
  }

  /**
   * Records a helper method for an embedded {@link String} valued expression (e.g. a container query data value or a
   * prefix) and returns the Java call that the surrounding template uses in place of the inlined expression. The
   * expression is evaluated against the {@code ctx} variable holding the rule's context object.
   *
   * @param expr
   *          the expression to translate, must not be {@code null}
   * @param contextType
   *          the type of the {@code ctx} context variable, must not be {@code null}
   * @return the Java call to the generated helper method, never {@code null}
   */
  def stringExpressionCall(Expression expr, EClass contextType) {
    val request = new ScopeExpressionMethodRequest
    val methodName = 'expr' + (expressionMethodCounter++)
    request.methodName = methodName
    request.returnTypeName = 'java.lang.String'
    request.variableName = 'ctx'
    request.variableTypeName = ctxJavaTypeName(contextType)
    request.expression = expr
    request.fallbackBody = 'return ' + compiler.javaExpression(expr, translator.newCompilationContext('ctx', contextType, #[], expr)) + ';'
    expressionMethods.add(request)
    methodName + '(ctx)'
  }

  def scopeRuleBlock(List<ScopeRule> it, ScopeModel model, String typeOrRef, EClass contextType, Boolean isGlobal) '''
    IScope scope = IScope.NULLSCOPE;
    try {
      «IF it.exists(r|r.context.guard !== null)»
        «FOR r : it.sortBy(r|if (r.context.guard === null) it.size else it.indexOf(r)) SEPARATOR ' else '
          »«IF r.context.guard !== null»if («guardCall(r)») «ENDIF»{
          «IF it.size > 1»«javaContributorComment(r.location())»
          «ENDIF
          »«FOR e : Lists.newArrayList(r.exprs).reverse()»«scopeExpression(e, model, typeOrRef, r.getScope(), isGlobal)»«ENDFOR»
        }«ENDFOR»«
        IF !it.exists(r|r.context.guard === null)» else {
          throw new UnsupportedOperationException(); // continue matching other definitions
        }«ENDIF»
      «ELSEIF it.size == 1»
        «FOR e : Lists.newArrayList(it.head.exprs).reverse()»«scopeExpression(e, model, typeOrRef, it.head.getScope(), isGlobal)»«ENDFOR»
      «ELSE»
        «error('scope context not unique for definitions: ' + it.map(r|r.location()).join(', '))»
      «ENDIF»
    } catch (Exception e) {
      LOGGER.error("Error calculating scope for «if (isGlobal) "Resource. Context:" else contextType.name» " + com.avaloq.tools.ddk.xtext.util.EObjectUtil.getLocationString(context) + " («it.get(0).locatorString()»)", e);
    }
    return scope;
  '''

  def dispatch scopeExpression(ScopeExpression it, ScopeModel model, String typeOrRef, ScopeDefinition scope, Boolean isGlobal) {
    error("Xtend called the wrong definition." + it.toString() + javaContributorComment(it.location()))
  }

  def dispatch scopeExpression(FactoryExpression it, ScopeModel model, String typeOrRef, ScopeDefinition scope, Boolean isGlobal) {
    val b = new StringBuilder
    val method = translator.resolveFactoryMethod(it.expr, model)
    if (method === null) {
      error('cannot resolve scope factory ' + it.expr?.toString + '; expected a fully qualified static call Type.method(...)')
      return b
    }
    b.append('scope = ').append(method).append('(scope, ctx, ').append(typeOrRef).append(', originalResource');
    if (expr instanceof OperationCall) {
      for (param : (expr as OperationCall).params) {
        b.append(', ').append(factoryArgument(param))
      }
    }
    b.append(');\n')
    return b
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
  def dispatch factoryArgument(Expression it) {
    error('unsupported scope factory argument ' + it.toString())
  }

  def dispatch factoryArgument(StringLiteral it) {
    '"' + Strings.convertToJavaString(getVal()) + '"'
  }

  def dispatch factoryArgument(IntegerLiteral it) {
    Integer.toString(getVal())
  }

  def dispatch factoryArgument(RealLiteral it) {
    getVal()
  }

  def dispatch factoryArgument(BooleanLiteral it) {
    getVal()
  }

  def dispatch factoryArgument(NullLiteral it) {
    'null'
  }

  def dispatch factoryArgument(FeatureCall it) {
    if (name === null && type !== null) {
      type.id.join('.')
    } else {
      error('unsupported scope factory argument ' + it.toString())
    }
  }

  def dispatch scopeExpression(ScopeDelegation it, ScopeModel model, String typeOrRef, ScopeDefinition scope, Boolean isGlobal) '''
    «IF delegate !== null »
    «val delegateString = ExpressionExtensions.serialize(delegate)»
    «IF delegateString == "this.eContainer()" || delegateString == "this.eContainer" || delegateString == "eContainer()" || delegateString == "eContainer"»
      scope = newSameScope("«it.locatorString()»", scope, ctx.eContainer()«
    ELSEIF delegateString == "this"»
      scope = newSameScope("«it.locatorString()»", scope, ctx«
    ELSE»
      scope = newDelegateScope("«it.locatorString()»", scope, «
    IF !isGlobal »() -> com.avaloq.tools.ddk.xtext.scoping.IContextSupplier.makeIterable(«scopedElements(delegate, model, eContainer(ScopeRule).context.contextType, 'ctx')»)«
    ELSE»«scopedElements(delegate, model, eContainer(ScopeRule).context.contextType, 'ctx')»«
    ENDIF»«
    ENDIF»«
    ELSE»
      scope = newExternalDelegateScope("«it.locatorString()»", scope, «
    query(external, model, typeOrRef, scope)».execute(originalResource)«
    ENDIF», «
    IF it.scope !== null && it.scope.typeOrRef() != getScope(it).typeOrRef()»«it.scope.typeOrRef().literalIdentifier()»«ELSE»«typeOrRef»«ENDIF», "«if (it.scope !== null && it.scope.name !== null) it.scope.name else "scope"»", originalResource);
  '''

  def dispatch scopeExpression(NamedScopeExpression it, ScopeModel model, String typeOrRef, ScopeDefinition scope, Boolean isGlobal) '''
    scope = «scopeExpressionPart (it, model, typeOrRef, scope)»«
    scopeExpressionNaming (it, model, typeOrRef, scope)»«
    scopeExpressionCasing (it, model, typeOrRef, scope)»);
  '''

  def dispatch scopeExpression(SimpleScopeExpression it, ScopeModel model, String typeOrRef, ScopeDefinition scope, Boolean isGlobal) '''
    «IF expr.isEmptyList() »
      // Empty scope from «it.location()»
    «ELSE»
      scope = «scopeExpressionPart (it, model, typeOrRef, scope)»«
      scopeExpressionNaming (it, model, typeOrRef, scope)»«
      scopeExpressionCasing (it, model, typeOrRef, scope)»);
    «ENDIF»
  '''

  def dispatch scopeExpressionPart (NamedScopeExpression it, ScopeModel model, String typeOrRef, ScopeDefinition scope) {
    error("Xtend called the wrong definition for scopeExpressionPart with this=" + it.toString() + javaContributorComment(it.location()))
  }

  def dispatch scopeExpressionPart (SimpleScopeExpression it, ScopeModel model, String typeOrRef, ScopeDefinition scope) {
    'newSimpleScope("' + locatorString() + '", scope, ' + scopedElements(expr, model, eContainer(ScopeRule).context.contextType, "ctx") + ', '
  }

  def query (GlobalScopeExpression it, ScopeModel model, String typeOrRef, ScopeDefinition scope) '''
    newQuery(«type.literalIdentifier()»)«
    val matchData = data.filter(MatchDataExpression)»«
    IF name !== null».name(«nameValue(name, eContainer(ScopeRule).context.contextType, model)»)«ENDIF»«
    IF !matchData.isEmpty»«FOR d : matchData».data("«Strings.convertToJavaString(d.key)»", «stringExpressionCall(d.value, eContainer(ScopeRule).context.contextType)»)«ENDFOR»«ENDIF»«
    IF !domains.isEmpty && domains.get(0) != "*"».domains(«FOR d : domains SEPARATOR ", "»"«Strings.convertToJavaString(d)»"«ENDFOR»)«ENDIF
  »'''

  /**
   * Produces the Java for a container query name argument. When the name expression can be translated by the Xbase
   * based translator to a {@link String} it is extracted into a generated helper method; otherwise the legacy
   * fragment is emitted. The {@code String} guard avoids selecting the {@code name(QualifiedName)} overload by
   * mistake.
   *
   * @param name
   *          the name expression, must not be {@code null}
   * @param contextType
   *          the type of the rule context object, must not be {@code null}
   * @param model
   *          the scope model, must not be {@code null}
   * @return the Java name argument source, never {@code null}
   */
  def nameValue(Expression name, EClass contextType, ScopeModel model) {
    if (translator.canExtractAsString(name, contextType, model)) {
      stringExpressionCall(name, contextType)
    } else {
      doExpression(name, model, 'ctx', contextType)
    }
  }

  def dispatch scopeExpressionPart (GlobalScopeExpression it, ScopeModel model, String typeOrRef, ScopeDefinition scope) '''
    «val matchData = data.filter(LambdaDataExpression)»
    «IF matchData.isEmpty && prefix === null»newContainerScope(«ELSEIF matchData.isEmpty && prefix !== null»newPrefixedContainerScope(«ELSE»newDataMatchScope(«ENDIF»"«it.locatorString()»", scope, ctx, «query (it, model, typeOrRef, scope)», originalResource«
    IF !matchData.isEmpty», //
      java.util.Arrays.asList(
    «FOR d : matchData SEPARATOR ","»
        «lambdaDataMatch(d, model, eContainer(ScopeRule).context.contextType, it)»«
    ENDFOR»  )«
    ELSEIF prefix !== null», «stringExpressionCall(prefix, eContainer(ScopeRule).context.contextType)», «recursivePrefix»«
    ENDIF
  »'''

  /**
   * Produces the Java lambda for a single data match filter. When the filter value can be translated by the Xbase
   * based translator it is extracted into a generated helper method (taking the rule context and the element
   * description) and the lambda delegates to it; otherwise the legacy fragment is emitted (or an
   * {@code EXPRESSION_NOT_SUPPORTED} marker when the legacy compiler cannot handle it either).
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
  def lambdaDataMatch(LambdaDataExpression d, ScopeModel model, EClass contextType, GlobalScopeExpression owner) {
    val descType = 'org.eclipse.xtext.resource.IEObjectDescription'
    val cc = translator.newCompilationContext('ctx', contextType, #[d.desc -> descType], owner)
    if (translator.canExtractAsValue(d.value, contextType, #[d.desc -> descType], model)) {
      val request = new ScopeExpressionMethodRequest
      val methodName = 'expr' + (expressionMethodCounter++)
      request.methodName = methodName
      request.returnTypeName = 'boolean'
      request.variableName = 'ctx'
      request.variableTypeName = ctxJavaTypeName(contextType)
      request.expression = d.value
      request.extraParameters.add(d.desc -> descType)
      request.fallbackBody = 'return ' + compiler.javaExpression(d.value, cc) + ';'
      expressionMethods.add(request)
      d.desc + ' -> ' + methodName + '(ctx, ' + d.desc + ')'
    } else if (compiler.isCompilable(d.value, cc)) {
      d.desc + ' -> ' + compiler.javaExpression(d.value, cc)
    } else {
      d.desc + ' -> EXPRESSION_NOT_SUPPORTED("' + ExpressionExtensions.serialize(owner) + '")'
    }
  }

  def dispatch scopeExpressionNaming (NamedScopeExpression it, ScopeModel model, String typeOrRef, ScopeDefinition scope) {
    error("Xtend called the wrong definition for scopeExpressionNaming with this=" + it.toString() + javaContributorComment(it.location()))
  }

  def dispatch scopeExpressionNaming (SimpleScopeExpression it, ScopeModel model, String typeOrRef, ScopeDefinition scope) {
    name(it, model, typeOrRef, 'ctx', eContainer(ScopeRule).context.contextType)
  }

  def dispatch scopeExpressionNaming (GlobalScopeExpression it, ScopeModel model, String typeOrRef, ScopeDefinition scope) {
    ', ' + name(it, model, typeOrRef, 'ctx', eContainer(ScopeRule).context.contextType)
  }

  def scopeExpressionCasing (NamedScopeExpression it, ScopeModel model, String typeOrRef, ScopeDefinition scope) {
    ', ' + isCaseInsensitive().toString
  }

  def scopedElements(Expression it, ScopeModel model, EClass type, String object) {
    if (object == 'ctx' && translator.canExtractAsValue(it, type, model)) {
      val request = new ScopeExpressionMethodRequest
      val methodName = 'expr' + (expressionMethodCounter++)
      request.methodName = methodName
      request.returnTypeName = null
      request.variableName = 'ctx'
      request.variableTypeName = ctxJavaTypeName(type)
      request.expression = it
      request.fallbackBody = 'return ' + compiler.javaExpression(it, translator.newCompilationContext(object, type, #[], it)) + ';'
      expressionMethods.add(request)
      methodName + '(ctx)'
    } else {
      doExpression(it, model, object, type)
    }
  }

  def doExpression(Expression it, ScopeModel model, String object, EClass type) {
    compiler.javaExpression(it, translator.newCompilationContext(object, type, #[], it))
  }

  def name(NamedScopeExpression it, ScopeModel model, String typeOrRef, String contextName, EClass contextType) {
    if (it.naming !== null)
      nameProviderGenerator.nameFunctions(it.naming, model, contextName, contextType)
    else
      'getNameFunctions(' + typeOrRef + ')'
  }

  def error(String message) {
    throw new RuntimeException(message)
  }

  def private dispatch boolean isEmptyList(Expression it) {
    false
  }

  def private dispatch boolean isEmptyList(ListLiteral it) {
    elements.isEmpty
  }

}
