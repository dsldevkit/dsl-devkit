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
package com.avaloq.tools.ddk.xtext.scope.jvmmodel

import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorSupport
import com.avaloq.tools.ddk.xtext.scope.generator.ScopeNameProviderGenerator
import com.avaloq.tools.ddk.xtext.scope.generator.ScopeProviderGenerator
import com.avaloq.tools.ddk.xtext.scope.generator.ScopeProviderX
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel
import com.avaloq.tools.ddk.xtext.scoping.AbstractPolymorphicScopeProvider
import com.avaloq.tools.ddk.xtext.scoping.AbstractScopeNameProvider
import com.avaloq.tools.ddk.xtext.scoping.INameFunction
import com.google.inject.Inject
import com.google.inject.Singleton
import java.util.Map
import org.apache.logging.log4j.Logger
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.common.types.JvmAnnotationReference
import org.eclipse.xtext.common.types.JvmAnnotationType
import org.eclipse.xtext.common.types.JvmGenericType
import org.eclipse.xtext.common.types.JvmVisibility
import org.eclipse.xtext.common.types.TypesFactory
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder

/**
 * Infers a JVM model from a scope model.
 * <p>
 * Replaces the former {@code IGenerator2} based {@code ScopeGenerator}: the {@code <Name>ScopeProvider} and
 * {@code <Name>ScopeNameProvider} are now contributed as inferred JVM types whose Java source is emitted by the
 * Xbase {@code JvmModelGenerator}. The method bodies are produced as strings by the (still expression based) scope
 * generators and attached verbatim; framework types referenced by those strings are emitted fully qualified so that
 * the generated compilation unit needs no import section of its own.
 */
class ScopeJvmModelInferrer extends AbstractModelInferrer {

  @Inject extension JvmTypesBuilder
  @Inject extension ScopeProviderX

  @Inject TypesFactory typesFactory
  @Inject ScopeProviderGenerator providerGenerator
  @Inject ScopeNameProviderGenerator nameProviderGenerator
  @Inject GenModelUtilX genModelUtil
  @Inject GeneratorSupport generatorSupport

  /**
   * Infers the scope provider and scope name provider JVM types for the given scope model.
   *
   * @param element
   *          the scope model, must not be {@code null}
   * @param acceptor
   *          the type acceptor, must not be {@code null}
   * @param isPreIndexingPhase
   *          whether the method is called in the pre-indexing phase
   */
  def dispatch void infer(ScopeModel element, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
    if (isPreIndexingPhase) {
      return
    }
    genModelUtil.resource = element.eResource
    providerGenerator.configure(nameProviderGenerator, genModelUtil)
    nameProviderGenerator.configure(genModelUtil)

    // The method body strings are produced eagerly here (inside the project resource loader required by the
    // expression compiler) rather than from within the deferred body closures, which run later during emission.
    val Map<String, String> bodies = newHashMap
    generatorSupport.executeWithProjectResourceLoader(element.projectOf, [
      bodies.put('doGetScopeRef', providerGenerator.doGetScopeByReferenceBody(element).toString)
      bodies.put('doGetScopeType', providerGenerator.doGetScopeByTypeBody(element).toString)
      bodies.put('doGlobalCacheRef', providerGenerator.doGlobalCacheByReferenceBody(element).toString)
      bodies.put('doGlobalCacheType', providerGenerator.doGlobalCacheByTypeBody(element).toString)
      for (scope : element.allScopes) {
        bodies.put('scope:' + scope.scopeMethodName, providerGenerator.scopeMethodBody(scope, element).toString)
      }
      bodies.put('nameFunctions', nameProviderGenerator.internalGetNameFunctionsBody(element).toString)
    ])

    val providerName = element.scopeProvider
    acceptor.accept(element.toClass(providerName)) [
      superTypes += typeRef(AbstractPolymorphicScopeProvider)
      addSuppressWarningsAll
      documentation = '''The scope provider for «element.name».'''
      members += element.toField('LOGGER', typeRef(Logger)) [
        static = true
        final = true
        initializer = [append('''org.apache.logging.log4j.LogManager.getLogger(«providerName.simpleName».class)''')]
      ]
      for (injection : element.allInjections) {
        members += element.toField(injection.name, typeRef(injection.type)) [
          visibility = JvmVisibility.PRIVATE
          annotations += typeOnlyAnnotation(Inject)
        ]
      }
      members += element.toMethod('doGetScope', typeRef(IScope)) [
        visibility = JvmVisibility.PROTECTED
        annotations += typeOnlyAnnotation(Override)
        parameters += element.toParameter('context', typeRef(EObject))
        parameters += element.toParameter('reference', typeRef(EReference))
        parameters += element.toParameter('scopeName', typeRef(String))
        parameters += element.toParameter('originalResource', typeRef(Resource))
        val text = bodies.get('doGetScopeRef')
        body = [append(text)]
      ]
      members += element.toMethod('doGetScope', typeRef(IScope)) [
        visibility = JvmVisibility.PROTECTED
        annotations += typeOnlyAnnotation(Override)
        parameters += element.toParameter('context', typeRef(EObject))
        parameters += element.toParameter('type', typeRef(EClass))
        parameters += element.toParameter('scopeName', typeRef(String))
        parameters += element.toParameter('originalResource', typeRef(Resource))
        val text = bodies.get('doGetScopeType')
        body = [append(text)]
      ]
      members += element.toMethod('doGlobalCache', typeRef(Boolean.TYPE)) [
        visibility = JvmVisibility.PROTECTED
        annotations += typeOnlyAnnotation(Override)
        parameters += element.toParameter('context', typeRef(EObject))
        parameters += element.toParameter('reference', typeRef(EReference))
        parameters += element.toParameter('scopeName', typeRef(String))
        parameters += element.toParameter('originalResource', typeRef(Resource))
        val text = bodies.get('doGlobalCacheRef')
        body = [append(text)]
      ]
      members += element.toMethod('doGlobalCache', typeRef(Boolean.TYPE)) [
        visibility = JvmVisibility.PROTECTED
        annotations += typeOnlyAnnotation(Override)
        parameters += element.toParameter('context', typeRef(EObject))
        parameters += element.toParameter('type', typeRef(EClass))
        parameters += element.toParameter('scopeName', typeRef(String))
        parameters += element.toParameter('originalResource', typeRef(Resource))
        val text = bodies.get('doGlobalCacheType')
        body = [append(text)]
      ]
      for (scope : element.allScopes) {
        val text = bodies.get('scope:' + scope.scopeMethodName)
        val hasReference = scope.reference !== null
        members += element.toMethod(scope.scopeMethodName, typeRef(IScope)) [
          visibility = JvmVisibility.PROTECTED
          parameters += element.toParameter('context', typeRef(EObject))
          if (hasReference) {
            parameters += element.toParameter('ref', typeRef(EReference))
          } else {
            parameters += element.toParameter('type', typeRef(EClass))
          }
          parameters += element.toParameter('originalResource', typeRef(Resource))
          body = [append(text)]
        ]
      }
      for (request : providerGenerator.expressionMethods) {
        addExpressionMethod(it, element, request)
      }
    ]

    val nameProviderName = element.scopeNameProvider
    acceptor.accept(element.toClass(nameProviderName)) [
      superTypes += typeRef(AbstractScopeNameProvider)
      annotations += typeOnlyAnnotation(Singleton)
      addSuppressWarningsAll
      documentation = '''The scope name provider for «element.name».'''
      members += element.toMethod('internalGetNameFunctions', typeRef(Iterable, typeRef(INameFunction))) [
        visibility = JvmVisibility.PUBLIC
        annotations += typeOnlyAnnotation(Override)
        parameters += element.toParameter('eClass', typeRef(EClass))
        val text = bodies.get('nameFunctions')
        body = [append(text)]
      ]
    ]
  }

  /**
   * Adds a {@code @SuppressWarnings("all")} annotation to the given type.
   *
   * @param type
   *          the type to annotate, must not be {@code null}
   */
  def private void addSuppressWarningsAll(JvmGenericType type) {
    val annotation = typesFactory.createJvmAnnotationReference
    annotation.annotation = typeRef(SuppressWarnings).type as JvmAnnotationType
    val value = typesFactory.createJvmStringAnnotationValue
    value.values += 'all'
    annotation.explicitValues += value
    type.annotations += annotation
  }

  /**
   * Creates a marker annotation reference (without values) for the given annotation type.
   *
   * @param annotationClass
   *          the annotation type, must not be {@code null}
   * @return the annotation reference, never {@code null}
   */
  def private JvmAnnotationReference typeOnlyAnnotation(Class<?> annotationClass) {
    val annotation = typesFactory.createJvmAnnotationReference
    annotation.annotation = typeRef(annotationClass).type as JvmAnnotationType
    annotation
  }

  /**
   * Contributes a private helper operation for an embedded scope expression. The expression is translated into an
   * Xbase {@link org.eclipse.xtext.xbase.XExpression} attached as the method body; if the translator cannot (yet)
   * translate it, the recorded legacy body string is emitted instead so behaviour is preserved.
   *
   * @param type
   *          the inferred provider type to add the method to, must not be {@code null}
   * @param element
   *          the scope model the expression originates from, must not be {@code null}
   * @param request
   *          the recorded helper method request, must not be {@code null}
   */
  def private void addExpressionMethod(JvmGenericType type, ScopeModel element, ScopeExpressionMethodRequest request) {
    val parameter = element.toParameter(request.variableName, typeRef(request.variableTypeName))
    val extraParams = newArrayList
    for (extra : request.extraParameters) {
      val extraParam = element.toParameter(extra.key, typeRef(extra.value))
      extraParams += extraParam
    }
    val returnType = if (request.returnTypeName == 'boolean') {
        typeRef(Boolean.TYPE)
      } else if (request.returnTypeName !== null) {
        typeRef(request.returnTypeName)
      } else {
        typeRef(Object)
      }
    type.members += element.toMethod(request.methodName, returnType) [
      visibility = JvmVisibility.PRIVATE
      parameters += parameter
      parameters += extraParams
      // The body is the legacy string fragment produced by ScopeExpressionCompiler.javaExpression, captured
      // upstream in ScopeProviderGenerator. We do NOT try to emit an XExpression here: the translator
      // builds free-standing XExpressions (no eResource), which neither JvmTypesBuilder.setBody(XExpression)
      // (uses the resource for logical-container association) nor XbaseCompiler.compile (uses the resource
      // for the type-reference owner) can handle. The string fragment is self-contained and parses cleanly.
      val fallback = request.fallbackBody
      body = [append(fallback)]
    ]
  }

  /**
   * Returns the workspace project containing the given scope model, if any.
   *
   * @param element
   *          the scope model, must not be {@code null}
   * @return the containing project, or {@code null} if it cannot be determined
   */
  def private IProject projectOf(ScopeModel element) {
    val uri = element.eResource.URI
    if (uri.isPlatformResource) {
      val resource = ResourcesPlugin.workspace.root.findMember(uri.toPlatformString(true))
      if (resource !== null) {
        return resource.project
      }
    }
    null
  }

  /**
   * Returns the simple (unqualified) name of a fully qualified Java type name.
   *
   * @param fqn
   *          the fully qualified name, must not be {@code null}
   * @return the simple name, never {@code null}
   */
  def private String simpleName(String fqn) {
    fqn.substring(fqn.lastIndexOf('.') + 1)
  }

}
