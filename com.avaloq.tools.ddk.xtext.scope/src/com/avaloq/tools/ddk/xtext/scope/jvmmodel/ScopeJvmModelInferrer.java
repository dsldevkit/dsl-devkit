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
package com.avaloq.tools.ddk.xtext.scope.jvmmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import org.apache.logging.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.common.types.JvmAnnotationReference;
import org.eclipse.xtext.common.types.JvmAnnotationType;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmStringAnnotationValue;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.common.types.xtext.JvmMemberInitializableResource;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorSupport;
import com.avaloq.tools.ddk.xtext.expression.generator.JavaBodyAppender;
import com.avaloq.tools.ddk.xtext.scope.generator.ScopeNameProviderGenerator;
import com.avaloq.tools.ddk.xtext.scope.generator.ScopeProviderGenerator;
import com.avaloq.tools.ddk.xtext.scope.generator.ScopeProviderX;
import com.avaloq.tools.ddk.xtext.scope.scope.Injection;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scoping.AbstractPolymorphicScopeProvider;
import com.avaloq.tools.ddk.xtext.scoping.AbstractScopeNameProvider;
import com.avaloq.tools.ddk.xtext.scoping.INameFunction;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * Infers a JVM model from a scope model.
 * <p>
 * Replaces the former {@code IGenerator2} based {@code ScopeGenerator}: the {@code <Name>ScopeProvider} and
 * {@code <Name>ScopeNameProvider} are now contributed as inferred JVM types whose Java source is emitted by the
 * Xbase {@code JvmModelGenerator}. The method bodies are produced as strings by the (still expression based) scope
 * generators and appended through {@link com.avaloq.tools.ddk.xtext.expression.generator.JavaBodyAppender}, which
 * imports and shortens the framework types they reference while leaving the types of the language being generated
 * fully qualified.
 */
@SuppressWarnings({"nls", "checkstyle:MethodName", "PMD.UnusedFormalParameter"})
public class ScopeJvmModelInferrer extends AbstractModelInferrer {
  // CHECKSTYLE:CONSTANTS-OFF the repeated literals are names of the inferred members, not nameable constants
  // CHECKSTYLE:CHECK-OFF LambdaBodyLength the model-inference closures mirror the Xtext JvmTypesBuilder API and are kept whole

  @Inject
  private JvmTypesBuilder jvmTypesBuilder;

  @Inject
  private ScopeProviderX scopeProviderX;

  @Inject
  private TypesFactory typesFactory;

  @Inject
  private Provider<ScopeProviderGenerator> providerGenerators;

  @Inject
  private Provider<ScopeNameProviderGenerator> nameProviderGenerators;

  @Inject
  private GenModelUtilX genModelUtil;

  @Inject
  private GeneratorSupport generatorSupport;

  @Inject
  private JavaBodyAppender bodyAppender;

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
  protected void _infer(final ScopeModel element, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    if (isPreIndexingPhase) {
      return;
    }
    final String providerName = scopeProviderX.getScopeProvider(element);
    final Procedure1<JvmGenericType> providerInitializer = (final JvmGenericType it) -> {
      it.getSuperTypes().add(_typeReferenceBuilder.typeRef(AbstractPolymorphicScopeProvider.class));
      addSuppressWarningsAll(it);
      jvmTypesBuilder.setDocumentation(it, "The scope provider for %s.".formatted(Strings.emptyIfNull(element.getName())));
      final Procedure1<JvmField> loggerInitializer = (final JvmField field) -> {
        field.setStatic(true);
        field.setFinal(true);
        final Procedure1<ITreeAppendable> initializer = (final ITreeAppendable appendable) -> appendJava(appendable,
            "org.apache.logging.log4j.LogManager.getLogger(%s.class)".formatted(simpleName(providerName)), element);
        jvmTypesBuilder.setInitializer(field, initializer);
      };
      it.getMembers().add(jvmTypesBuilder.toField(element, "LOGGER", _typeReferenceBuilder.typeRef(Logger.class), loggerInitializer));
      for (final Injection injection : scopeProviderX.allInjections(element)) {
        final Procedure1<JvmField> injectionInitializer = (final JvmField field) -> {
          field.setVisibility(JvmVisibility.PRIVATE);
          field.getAnnotations().add(typeOnlyAnnotation(Inject.class));
        };
        final JvmField injectedField = jvmTypesBuilder.toField(element, injection.getName(),
            _typeReferenceBuilder.typeRef(injection.getType()), injectionInitializer);
        if (injectedField != null) { // toField returns null for an unnamed injection; the original Xtend += (operator_add) skipped nulls
          it.getMembers().add(injectedField);
        }
      }
      it.getMembers().add(jvmTypesBuilder.toMethod(element, "doGetScope", _typeReferenceBuilder.typeRef(IScope.class),
          scopeEntryPoint(element, "reference", EReference.class, (provider, names) -> provider.doGetScopeByReferenceBody(element))));
      it.getMembers().add(jvmTypesBuilder.toMethod(element, "doGetScope", _typeReferenceBuilder.typeRef(IScope.class),
          scopeEntryPoint(element, "type", EClass.class, (provider, names) -> provider.doGetScopeByTypeBody(element))));
      it.getMembers().add(jvmTypesBuilder.toMethod(element, "doGlobalCache", _typeReferenceBuilder.typeRef(Boolean.TYPE),
          scopeEntryPoint(element, "reference", EReference.class, (provider, names) -> provider.doGlobalCacheByReferenceBody(element))));
      it.getMembers().add(jvmTypesBuilder.toMethod(element, "doGlobalCache", _typeReferenceBuilder.typeRef(Boolean.TYPE),
          scopeEntryPoint(element, "type", EClass.class, (provider, names) -> provider.doGlobalCacheByTypeBody(element))));
      for (final ScopeDefinition scope : scopeProviderX.allScopes(element)) {
        final boolean hasReference = scope.getReference() != null;
        final Procedure1<JvmOperation> scopeMethodInitializer = (final JvmOperation method) -> {
          method.setVisibility(JvmVisibility.PROTECTED);
          method.getParameters().add(jvmTypesBuilder.toParameter(element, "context", _typeReferenceBuilder.typeRef(EObject.class)));
          if (hasReference) {
            method.getParameters().add(jvmTypesBuilder.toParameter(element, "ref", _typeReferenceBuilder.typeRef(EReference.class)));
          } else {
            method.getParameters().add(jvmTypesBuilder.toParameter(element, "type", _typeReferenceBuilder.typeRef(EClass.class)));
          }
          method.getParameters().add(jvmTypesBuilder.toParameter(element, "originalResource", _typeReferenceBuilder.typeRef(Resource.class)));
          final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> appendJava(appendable,
              renderBody(element, (provider, names) -> provider.scopeMethodBody(scope, element)), element);
          jvmTypesBuilder.setBody(method, body);
        };
        it.getMembers().add(jvmTypesBuilder.toMethod(element, scopeProviderX.scopeMethodName(scope),
            _typeReferenceBuilder.typeRef(IScope.class), scopeMethodInitializer));
      }
    };
    acceptor.<JvmGenericType> accept(jvmTypesBuilder.toClass(element, providerName), providerInitializer);

    final String nameProviderName = scopeProviderX.getScopeNameProvider(element);
    final Procedure1<JvmGenericType> nameProviderInitializer = (final JvmGenericType it) -> {
      it.getSuperTypes().add(_typeReferenceBuilder.typeRef(AbstractScopeNameProvider.class));
      it.getAnnotations().add(typeOnlyAnnotation(Singleton.class));
      addSuppressWarningsAll(it);
      jvmTypesBuilder.setDocumentation(it, "The scope name provider for %s.".formatted(Strings.emptyIfNull(element.getName())));
      final Procedure1<JvmOperation> methodInitializer = (final JvmOperation method) -> {
        method.setVisibility(JvmVisibility.PUBLIC);
        method.getAnnotations().add(typeOnlyAnnotation(Override.class));
        method.getParameters().add(jvmTypesBuilder.toParameter(element, "eClass", _typeReferenceBuilder.typeRef(EClass.class)));
        final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> appendJava(appendable,
            renderBody(element, (provider, names) -> names.internalGetNameFunctionsBody(element)), element);
        jvmTypesBuilder.setBody(method, body);
      };
      it.getMembers().add(jvmTypesBuilder.toMethod(element, "internalGetNameFunctions",
          _typeReferenceBuilder.typeRef(Iterable.class, _typeReferenceBuilder.typeRef(INameFunction.class)), methodInitializer));
    };
    acceptor.<JvmGenericType> accept(jvmTypesBuilder.toClass(element, nameProviderName), nameProviderInitializer);
    // Acceptor initializers alone run before infer returns to resource loading. Register after both roots
    // have been accepted so Xtext marks both types for demand-driven member initialization.
    final Resource resource = element.eResource();
    if (resource instanceof JvmMemberInitializableResource initializableResource) {
      initializableResource.addJvmMemberInitializer(() -> {
        // Nothing to initialize eagerly; registering the (empty) initializer marks the types for demand-driven initialization.
      });
    }
  }

  /**
   * Builds the initializer of one of the four {@code doGetScope}/{@code doGlobalCache} entry points. They differ only
   * in the type of their second parameter and in the generator method that produces their body; every other
   * structural call is identical.
   *
   * @param element
   *          the scope model, must not be {@code null}
   * @param secondParameterName
   *          the name of the reference or type parameter, must not be {@code null}
   * @param secondParameterType
   *          the type of the reference or type parameter, must not be {@code null}
   * @param producer
   *          the producer of the method body, must not be {@code null}
   * @return the method initializer, never {@code null}
   */
  private Procedure1<JvmOperation> scopeEntryPoint(final ScopeModel element, final String secondParameterName,
      final Class<?> secondParameterType, final BiFunction<ScopeProviderGenerator, ScopeNameProviderGenerator, CharSequence> producer) {
    return (final JvmOperation method) -> {
      method.setVisibility(JvmVisibility.PROTECTED);
      method.getAnnotations().add(typeOnlyAnnotation(Override.class));
      method.getParameters().add(jvmTypesBuilder.toParameter(element, "context", _typeReferenceBuilder.typeRef(EObject.class)));
      method.getParameters().add(jvmTypesBuilder.toParameter(element, secondParameterName, _typeReferenceBuilder.typeRef(secondParameterType)));
      method.getParameters().add(jvmTypesBuilder.toParameter(element, "scopeName", _typeReferenceBuilder.typeRef(String.class)));
      method.getParameters().add(jvmTypesBuilder.toParameter(element, "originalResource", _typeReferenceBuilder.typeRef(Resource.class)));
      final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> appendJava(appendable, renderBody(element, producer), element);
      jvmTypesBuilder.setBody(method, body);
    };
  }

  /** Renders only during emission, with fresh model-specific generators and a restored resource context. */
  private String renderBody(final ScopeModel model, final BiFunction<ScopeProviderGenerator, ScopeNameProviderGenerator, CharSequence> producer) {
    final Resource previousContext = genModelUtil.getContext();
    final List<String> result = new ArrayList<>(List.of(""));
    try {
      generatorSupport.executeWithProjectResourceLoader(projectOf(model), () -> {
        genModelUtil.setResource(model.eResource());
        final ScopeProviderGenerator provider = providerGenerators.get();
        final ScopeNameProviderGenerator names = nameProviderGenerators.get();
        provider.configure(names, genModelUtil, model);
        names.configure(genModelUtil, model);
        result.set(0, producer.apply(provider, names).toString());
      });
      return result.get(0);
    } finally {
      genModelUtil.setResource(previousContext);
    }
  }

  /**
   * Appends a generated method body or field initializer, importing and shortening the framework types it references.
   *
   * @param it
   *          the appendable to write to, must not be {@code null}
   * @param body
   *          the Java source fragment, must not be {@code null}
   * @param context
   *          the scope model, used to resolve the referenced types against the classpath, must not be {@code null}
   */
  private void appendJava(final ITreeAppendable it, final String body, final ScopeModel context) {
    bodyAppender.appendBody(it, body, context);
  }

  /**
   * Adds a {@code @SuppressWarnings("all")} annotation to the given type.
   *
   * @param type
   *          the type to annotate, must not be {@code null}
   */
  private void addSuppressWarningsAll(final JvmGenericType type) {
    final JvmAnnotationReference annotation = typesFactory.createJvmAnnotationReference();
    final JvmType annotationType = _typeReferenceBuilder.typeRef(SuppressWarnings.class).getType();
    annotation.setAnnotation((JvmAnnotationType) annotationType);
    final JvmStringAnnotationValue value = typesFactory.createJvmStringAnnotationValue();
    value.getValues().add("all");
    annotation.getExplicitValues().add(value);
    type.getAnnotations().add(annotation);
  }

  /**
   * Creates a marker annotation reference (without values) for the given annotation type.
   *
   * @param annotationClass
   *          the annotation type, must not be {@code null}
   * @return the annotation reference, never {@code null}
   */
  private JvmAnnotationReference typeOnlyAnnotation(final Class<?> annotationClass) {
    final JvmAnnotationReference annotation = typesFactory.createJvmAnnotationReference();
    final JvmType annotationType = _typeReferenceBuilder.typeRef(annotationClass).getType();
    annotation.setAnnotation((JvmAnnotationType) annotationType);
    return annotation;
  }

  /**
   * Returns the workspace project containing the given scope model, if any.
   *
   * @param element
   *          the scope model, must not be {@code null}
   * @return the containing project, or {@code null} if it cannot be determined
   */
  private IProject projectOf(final ScopeModel element) {
    final URI uri = element.eResource().getURI();
    if (uri.isPlatformResource()) {
      final IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(uri.toPlatformString(true));
      if (resource != null) {
        return resource.getProject();
      }
    }
    return null;
  }

  /**
   * Returns the simple (unqualified) name of a fully qualified Java type name.
   *
   * @param fqn
   *          the fully qualified name, must not be {@code null}
   * @return the simple name, never {@code null}
   */
  private String simpleName(final String fqn) {
    return fqn.substring(fqn.lastIndexOf('.') + 1);
  }

  @Override
  public void infer(final EObject element, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    switch (element) {
      case ScopeModel model -> _infer(model, acceptor, isPreIndexingPhase);
      case null -> throw new IllegalArgumentException(
          "Unhandled parameter types: " + Arrays.<Object>asList(element, acceptor, isPreIndexingPhase).toString());
      default -> _infer(element, acceptor, isPreIndexingPhase);
    }
  }
  // CHECKSTYLE:CHECK-ON LambdaBodyLength
  // CHECKSTYLE:CONSTANTS-ON
}
