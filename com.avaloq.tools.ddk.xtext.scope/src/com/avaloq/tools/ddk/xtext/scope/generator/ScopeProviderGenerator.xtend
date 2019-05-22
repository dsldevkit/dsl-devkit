/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. it program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies it distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.scope.generator

import com.avaloq.tools.ddk.xtext.expression.expression.Expression
import com.avaloq.tools.ddk.xtext.expression.expression.OperationCall
import com.avaloq.tools.ddk.xtext.expression.generator.CodeGenerationX
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext
import com.avaloq.tools.ddk.xtext.expression.generator.EClassComparator
import com.avaloq.tools.ddk.xtext.expression.generator.ExpressionExtensionsX
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.Naming
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
import com.google.common.collect.Lists
import com.google.inject.Inject
import java.util.List
import org.eclipse.emf.ecore.EClass

class ScopeProviderGenerator {

  @Inject extension CodeGenerationX
  @Inject extension ExpressionExtensionsX
  @Inject extension GeneratorUtilX
  @Inject extension Naming
  @Inject extension ScopeProviderX

  ScopeNameProviderGenerator nameProviderGenerator
  CompilationContext compilationContext
  extension GenModelUtilX genModelUtil

  def generate(ScopeModel it, ScopeNameProviderGenerator nameProviderGenerator, CompilationContext compilationContext, GenModelUtilX genModelUtil) {
    this.nameProviderGenerator = nameProviderGenerator
    this.compilationContext = compilationContext
    this.genModelUtil = genModelUtil
    '''
      package «getScopeProvider().toJavaPackage()»;

      import java.util.Arrays;

      import org.apache.log4j.Logger;
      import org.eclipse.emf.ecore.EClass;
      import org.eclipse.emf.ecore.EObject;
      import org.eclipse.emf.ecore.EPackage;
      import org.eclipse.emf.ecore.EReference;
      import org.eclipse.emf.ecore.resource.Resource;

      import org.eclipse.xtext.naming.QualifiedName;
      import org.eclipse.xtext.resource.IEObjectDescription;
      import org.eclipse.xtext.scoping.IScope;

      import com.avaloq.tools.ddk.xtext.scoping.AbstractNameFunction;
      import com.avaloq.tools.ddk.xtext.scoping.AbstractPolymorphicScopeProvider;
      import com.avaloq.tools.ddk.xtext.scoping.IContextSupplier;
      import com.avaloq.tools.ddk.xtext.scoping.INameFunction;
      import com.avaloq.tools.ddk.xtext.scoping.NameFunctions;
      import com.avaloq.tools.ddk.xtext.util.EObjectUtil;

      import com.google.common.base.Predicate;
      «IF it !== null && !allInjections().isEmpty»
      import com.google.inject.Inject;
      «ENDIF»

      @SuppressWarnings("all")
      public class «getScopeProvider().toSimpleName()» extends AbstractPolymorphicScopeProvider {

        /** Class-wide logger. */
        private static final Logger LOGGER = Logger.getLogger(«getScopeProvider().toSimpleName()».class);
        «IF it !== null»

        «IF !allInjections().isEmpty»
          «FOR i : allInjections()»
            @Inject
            private «i.type» «i.name»;
          «ENDFOR»

        «ENDIF»
        «scopeMethods(it, name.toSimpleName())»
      «ENDIF»

      }
    '''
  }

  def scopeMethods(ScopeModel it, String baseName) '''
    /** {@inheritDoc} */
    @Override
    protected IScope doGetScope(final EObject context, final EReference reference, final String scopeName, final Resource originalResource) {
      «FOR name : allScopes().filter(s|s.reference !== null).map(s|s.getScopeName()).toSet().sortBy(n|if (n=="scope") "" else n) SEPARATOR " else "
     »if ("«name»".equals(scopeName)) {
        «FOR scope : allScopes().filter(s|s.reference !== null).filter(s|s.getScopeName()==name)»
        if (reference == «scope.reference.literalIdentifier()») return «scope.scopeMethodName()»(context, reference, originalResource);
        «ENDFOR»
      }«
      ENDFOR»
      return null;
    }

    /** {@inheritDoc} */
    @Override
    protected IScope doGetScope(final EObject context, final EClass type, final String scopeName, final Resource originalResource) {
      «FOR name : allScopes().filter(s|s.reference === null).map(s|s.getScopeName()).toSet().sortBy(n|if (n=="scope") "" else n) SEPARATOR " else "
     »if ("«name»".equals(scopeName)) {
        «FOR scope : allScopes().filter(s|s.reference === null).filter(s|s.getScopeName()==name)»
        if (type == «scope.targetType.literalIdentifier()») return «scope.scopeMethodName()»(context, type, originalResource);
        «ENDFOR»
      }«
      ENDFOR»
      return null;
    }

    /** {@inheritDoc} */
    @Override
    protected boolean doGlobalCache(final EObject context, final EReference reference, final String scopeName, final Resource originalResource) {
      if (context.eContainer() == null) {
        «FOR name : allScopes().filter(s|s.reference !== null).filter(s|s.allScopeRules().filter(r|r.context.global).size > 0).map(s|s.getScopeName()).toSet().sortBy(n|if (n=="scope") "" else n) SEPARATOR " else "
       »if ("«name»".equals(scopeName)) {
          «FOR scope : allScopes().filter(s|s.reference !== null).filter(s|s.getScopeName()==name)»
          «val globalRules = scope.allScopeRules().filter(r|r.context.global)»
          «IF globalRules.size > 0»
          if (reference == «scope.reference.literalIdentifier()») return true;
          «ENDIF»
          «ENDFOR»
        }«
        ENDFOR»
      }
      return false;
    }

    /** {@inheritDoc} */
    @Override
    protected boolean doGlobalCache(final EObject context, final EClass type, final String scopeName, final Resource originalResource) {
      if (context.eContainer() == null) {
        «FOR name : allScopes().filter(s|s.reference === null).filter(s|s.allScopeRules().filter(r|r.context.global).size > 0).map(s|s.getScopeName()).toSet().sortBy(n|if (n=="scope") "" else n) SEPARATOR " else "
       »if ("«name»".equals(scopeName)) {
          «FOR scope : allScopes().filter(s|s.reference === null).filter(s|s.getScopeName()==name)»
          «val globalRules = scope.allScopeRules().filter(r|r.context.global)»
          «IF globalRules.size > 0»
          if (type == «scope.targetType.literalIdentifier()») return true;
          «ENDIF»
          «ENDFOR»
        }«
        ENDFOR»
      }
      return false;
    }

    «FOR scope : allScopes()»
    protected IScope «scope.scopeMethodName()»(final EObject context, final «IF scope.reference !== null»EReference ref«ELSE»EClass type«ENDIF», final Resource originalResource) {
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
    }

    «ENDFOR»
  '''

  def scopeRuleBlock(List<ScopeRule> it, ScopeModel model, String typeOrRef, EClass contextType, Boolean isGlobal) '''
    IScope scope = IScope.NULLSCOPE;
    try {
      «IF it.exists(r|r.context.guard !== null)»
        «FOR r : it.sortBy(r|if (r.context.guard === null) it.size else it.indexOf(r)) SEPARATOR ' else '
          »«IF r.context.guard !== null»if («r.context.guard.javaExpression(compilationContext.clone('ctx', r.scopeType()))») «ENDIF»{
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
        «error('scope context not unique for definitions: ' + ', '.join(it.map(r|r.location())))»
      «ENDIF»
    } catch (Exception e) {
      LOGGER.error("Error calculating scope for «if (isGlobal) "Resource. Context:" else contextType.name» " + EObjectUtil.getLocationString(context) + " («it.get(0).locatorString()»)", e);
    }
    return scope;
  '''

  def dispatch scopeExpression(ScopeExpression it, ScopeModel model, String typeOrRef, ScopeDefinition scope, Boolean isGlobal) {
    error("Xpand called the wrong definition." + it.toString() + javaContributorComment(it.location()))
  }

  def dispatch scopeExpression(FactoryExpression it, ScopeModel model, String typeOrRef, ScopeDefinition scope, Boolean isGlobal) {
    val b = new StringBuilder
    val ctx = compilationContext.clone('ctx', eContainer(ScopeRule).context.contextType)
    b.append('scope = ').append(javaCall(it.expr, ctx)).append('(scope, ctx, ').append(typeOrRef).append(', originalResource');
    if (expr instanceof OperationCall) {
      for (param : (expr as OperationCall).params) {
        b.append(', ').append(javaExpression(param, ctx))
      }
    }
    b.append(');\n')
    return b
  }

  def dispatch javaCall(Expression it, CompilationContext ctx) {
    error('cannot handle scope factory ' + it.toString())
  }

  def dispatch javaCall(OperationCall it, CompilationContext ctx) {
    if (isJavaExtensionCall(ctx))
      calledJavaMethod(ctx)
    else
      '/* Error: cannot handle scope factory ' + it.toString() + ' */'
  }

  def dispatch scopeExpression(ScopeDelegation it, ScopeModel model, String typeOrRef, ScopeDefinition scope, Boolean isGlobal) '''
    «IF delegate !== null »
    «val delegateString = delegate.serialize()»
    «IF delegateString == "this.eContainer()" || delegateString == "this.eContainer" || delegateString == "eContainer()" || delegateString == "eContainer"»
      scope = newSameScope("«it.locatorString()»", scope, ctx.eContainer()«
    ELSEIF delegateString == "this"»
      scope = newSameScope("«it.locatorString()»", scope, ctx«
    ELSE»
      scope = newDelegateScope("«it.locatorString()»", scope, «
    IF !isGlobal »() -> IContextSupplier.makeIterable(«scopedElements(delegate, model, eContainer(ScopeRule).context.contextType, 'ctx')»)«
    ELSE»«scopedElements(delegate, model, eContainer(ScopeRule).context.contextType, 'ctx')»«
    ENDIF»«
    ENDIF»«
    ELSE»
      scope = newExternalDelegateScope("«it.locatorString()»", scope, «
    query(external, model, typeOrRef, scope)».execute(ctx, originalResource)«
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
    IF name !== null».name(«doExpression (name, model, 'ctx', eContainer(ScopeRule).context.contextType)»)«ENDIF»«
    IF !matchData.isEmpty»«FOR d : matchData».data("«javaEncode(d.key)»", «doExpression (d.value, model, 'ctx', eContainer(ScopeRule).context.contextType)»)«ENDFOR»«ENDIF»«
    IF !domains.isEmpty && domains.get(0) != "*"».domains(«FOR d : domains SEPARATOR ", "»"«javaEncode(d)»"«ENDFOR»)«ENDIF
  »'''

  def dispatch scopeExpressionPart (GlobalScopeExpression it, ScopeModel model, String typeOrRef, ScopeDefinition scope) '''
    «val matchData = data.filter(LambdaDataExpression)»
    «IF matchData.isEmpty && prefix === null»newContainerScope(«ELSEIF matchData.isEmpty && prefix !== null»newPrefixedContainerScope(«ELSE»newDataMatchScope(«ENDIF»"«it.locatorString()»", scope, ctx, «query (it, model, typeOrRef, scope)», originalResource«
    IF !matchData.isEmpty», //
      Arrays.<Predicate<IEObjectDescription>> asList(
    «FOR d : matchData SEPARATOR ","»
    «val CompilationContext cc = compilationContext.cloneWithVariable('ctx', eContainer(ScopeRule).context.contextType, d.desc, 'org::eclipse::xtext::resource::IEObjectDescription')»
        new Predicate<IEObjectDescription>() {
          public boolean apply (final IEObjectDescription «d.desc») {
    «IF d.value.isCompilable(cc.clone('ctx'))»
            return «d.value.javaExpression(cc.clone('ctx'))»;
    «ELSE»
            EXPRESSION_NOT_SUPPORTED("«serialize()»");
    «ENDIF»
          }
        }«
    ENDFOR»)«
    ELSEIF prefix !== null», «doExpression (prefix, model, 'ctx', eContainer(ScopeRule).context.contextType)», «recursivePrefix»«
    ENDIF
  »'''

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
    doExpression(it, model, object, type)
  }

  def doExpression(Expression it, ScopeModel model, String object, EClass type) {
    javaExpression (compilationContext.clone(object, type))
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

}