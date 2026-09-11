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
package com.avaloq.tools.ddk.xtext.export.jvmmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.common.types.JvmAnnotationReference;
import org.eclipse.xtext.common.types.JvmAnnotationType;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmStringAnnotationValue;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

import com.avaloq.tools.ddk.xtext.export.export.Export;
import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.Interface;
import com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression;
import com.avaloq.tools.ddk.xtext.export.export.InterfaceField;
import com.avaloq.tools.ddk.xtext.export.export.InterfaceItem;
import com.avaloq.tools.ddk.xtext.export.export.InterfaceNavigation;
import com.avaloq.tools.ddk.xtext.export.export.UserData;
import com.avaloq.tools.ddk.xtext.export.generator.ExportGeneratorX;
import com.avaloq.tools.ddk.xtext.export.generator.ExportOutputConfigurationProvider;
import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorSupport;
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.JavaBodyAppender;
import com.avaloq.tools.ddk.xtext.linking.ShortFragmentProvider;
import com.avaloq.tools.ddk.xtext.naming.AbstractExportedNameProvider;
import com.avaloq.tools.ddk.xtext.resource.AbstractCachingResourceDescriptionManager;
import com.avaloq.tools.ddk.xtext.resource.AbstractExportFeatureExtension;
import com.avaloq.tools.ddk.xtext.resource.AbstractResourceDescriptionStrategy;
import com.avaloq.tools.ddk.xtext.resource.AbstractSelectorFragmentProvider;
import com.avaloq.tools.ddk.xtext.resource.AbstractStreamingFingerprintComputer;
import com.avaloq.tools.ddk.xtext.resource.IFingerprintComputer;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.common.hash.Hasher;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Infers a JVM model from an export model.
 * <p>
 * Replaces the former {@code IGenerator2} based {@code ExportGenerator}: the exported names provider, resource
 * description manager, resource description strategy, resource description constants, fingerprint computer, fragment
 * provider and (for extension models) the export feature extension are now contributed as inferred JVM types whose
 * Java source is emitted by the Xbase {@code JvmModelGenerator}. The method bodies are produced as strings and
 * appended through {@link com.avaloq.tools.ddk.xtext.expression.generator.JavaBodyAppender}, which imports and
 * shortens the framework types they reference while leaving the types of the language being generated fully
 * qualified.
 */
@SuppressWarnings({"nls", "checkstyle:MethodName", "PMD.UnusedFormalParameter"})
public class ExportJvmModelInferrer extends AbstractModelInferrer {
  // CHECKSTYLE:CONSTANTS-OFF the repeated literals are Java source fragments emitted by this generator, not nameable constants
  // CHECKSTYLE:CHECK-OFF LambdaBodyLength the model-inference closures mirror the Xtext JvmTypesBuilder API and are kept whole

  @Inject
  private JvmTypesBuilder jvmTypesBuilder;

  @Inject
  private ExportGeneratorX exportGeneratorX;

  @Inject
  private GeneratorUtilX generatorUtilX;

  @Inject
  private GenModelUtilX genModelUtil;

  @Inject
  private TypesFactory typesFactory;

  @Inject
  private GeneratorSupport generatorSupport;

  @Inject
  private ExportExpressionCompiler compiler;

  @Inject
  private ExportExpressionTranslator translator;

  @Inject
  private IOutputConfigurationProvider outputConfigurationProvider;

  @Inject
  private JavaBodyAppender bodyAppender;

  /**
   * Infers the export provider JVM types for the given export model.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @param acceptor
   *          the type acceptor, must not be {@code null}
   * @param isPreIndexingPhase
   *          whether the method is called in the pre-indexing phase
   */
  protected void _infer(final ExportModel model, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    if (isPreIndexingPhase) {
      return;
    }
    genModelUtil.setResource(model.eResource());

    final JvmGenericType namesProvider = inferExportedNamesProvider(model, acceptor);
    if (!model.isExtension() && !hasCustomResourceDescriptionManager(model)) {
      inferResourceDescriptionManager(model, acceptor);
    }
    final JvmGenericType strategy = inferResourceDescriptionStrategy(model, acceptor);
    inferResourceDescriptionConstants(model, acceptor);
    final JvmGenericType fingerprintComputer = inferFingerprintComputer(model, acceptor);
    final JvmGenericType fragmentProvider = inferFragmentProvider(model, acceptor);
    if (model.isExtension()) {
      inferExportFeatureExtension(model, acceptor, namesProvider, fingerprintComputer, fragmentProvider, strategy);
    }
  }

  /**
   * Renders the given body producer inside the project resource loader required by the expression compiler.
   * The body producers walk the grammar and the EMF model and would force unresolved cross references during
   * the inferrer's invocation; running them from inside a deferred body closure delays that work past the
   * Xtext linking phase, by which time the grammar's parser rules and the model's import packages are linked.
   * <p>
   * The injected {@link GenModelUtilX} is shared across resources and carries the current model's resource as
   * mutable state; the inferrer sets it on entry, but by the time deferred bodies fire another resource may have
   * inferred and clobbered the field. Re-bind it to this body's model resource so the GenPackage lookup runs in
   * the right context.
   *
   * @param model
   *          the export model the body belongs to, must not be {@code null}
   * @param producer
   *          the producer for the body string, must not be {@code null}
   * @return the rendered body string, never {@code null}
   */
  private String renderBody(final ExportModel model, final Supplier<CharSequence> producer) {
    final AtomicReference<String> result = new AtomicReference<>("");
    generatorSupport.executeWithProjectResourceLoader(projectOf(model), () -> {
      genModelUtil.setResource(model.eResource());
      result.set(producer.get().toString());
    });
    return result.get();
  }

  /**
   * Appends a generated method body, importing and shortening the framework types it references. Field initializers
   * are appended through {@link #appendJavaInitializer(ITreeAppendable, String, ExportModel)} instead.
   *
   * @param it
   *          the appendable to write to, must not be {@code null}
   * @param body
   *          the Java source fragment, must not be {@code null}
   * @param context
   *          the export model, used to resolve the referenced types against the classpath, must not be {@code null}
   */
  private void appendJava(final ITreeAppendable it, final String body, final ExportModel context) {
    bodyAppender.appendBody(it, body, context);
  }

  /**
   * Appends a generated field initializer, importing and shortening the framework types it references.
   * <p>
   * {@code JvmModelGenerator} indents a field initializer one level deeper than the field it belongs to, which suits a
   * wrapped expression but leaves the multiline initializers produced here - the anonymous switch classes and the
   * exported EClass array - one level too deep relative to the line they open on. Those templates already indent their
   * content relative to the field, so the additional level is taken back for the duration of the append and restored
   * afterwards, since {@code JvmModelGenerator} decreases it again once the initializer has been written.
   *
   * @param it
   *          the appendable to write to, must not be {@code null}
   * @param body
   *          the Java source fragment, must not be {@code null}
   * @param context
   *          the export model, used to resolve the referenced types against the classpath, must not be {@code null}
   */
  private void appendJavaInitializer(final ITreeAppendable it, final String body, final ExportModel context) {
    it.decreaseIndentation();
    bodyAppender.appendBody(it, body, context);
    it.increaseIndentation();
  }

  /**
   * Infers the {@code <Name>ExportedNamesProvider}.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @param acceptor
   *          the type acceptor, must not be {@code null}
   * @return the inferred type, never {@code null}
   */
  private JvmGenericType inferExportedNamesProvider(final ExportModel model, final IJvmDeclaredTypeAcceptor acceptor) {
    final String providerName = exportGeneratorX.getExportedNamesProvider(model);
    final Grammar grammar = exportGeneratorX.getGrammar(model);
    final JvmGenericType inferredType = jvmTypesBuilder.toClass(model, providerName);
    final Procedure1<JvmGenericType> initializer = (final JvmGenericType it) -> {
      it.getSuperTypes().add(_typeReferenceBuilder.typeRef(AbstractExportedNameProvider.class));
      addSuppressWarningsAll(it);
      jvmTypesBuilder.setDocumentation(it, grammar != null
          ? "Qualified name provider for grammar %s providing the qualified names for exported objects.".formatted(grammar.getName())
          : "Qualified name provider providing the qualified names for exported objects.");
      if (!model.getExports().isEmpty()) {
        final Procedure1<JvmOperation> dispatchInitializer = (final JvmOperation method) -> {
          method.setVisibility(JvmVisibility.PUBLIC);
          method.getAnnotations().add(typeOnlyAnnotation(Override.class));
          method.getParameters().add(jvmTypesBuilder.toParameter(model, "object", _typeReferenceBuilder.typeRef(EObject.class)));
          final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> appendJava(appendable,
              renderBody(model, () -> qualifiedNameDispatchBody(model)), model);
          jvmTypesBuilder.setBody(method, body);
        };
        it.getMembers().add(jvmTypesBuilder.toMethod(model, "qualifiedName", _typeReferenceBuilder.typeRef(QualifiedName.class), dispatchInitializer));
        for (final Export c : model.getExports()) {
          final Procedure1<JvmOperation> caseInitializer = (final JvmOperation method) -> {
            method.setVisibility(JvmVisibility.PROTECTED);
            jvmTypesBuilder.setDocumentation(method, qualifiedNameDocumentation(c));
            method.getParameters().add(
                jvmTypesBuilder.toParameter(model, "obj", _typeReferenceBuilder.typeRef(genModelUtil.instanceClassName(c.getType()))));
            final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> appendJava(appendable,
                renderBody(model, () -> qualifiedNameBody(model, c)), model);
            jvmTypesBuilder.setBody(method, body);
          };
          it.getMembers().add(jvmTypesBuilder.toMethod(model, "qualifiedName", _typeReferenceBuilder.typeRef(QualifiedName.class), caseInitializer));
        }
      }
    };
    acceptor.<JvmGenericType> accept(inferredType, initializer);
    return inferredType;
  }

  /**
   * Returns the Javadoc of the {@code qualifiedName} overload generated for a single export declaration.
   *
   * @param it
   *          the export declaration, must not be {@code null}
   * @return the documentation text, never {@code null}
   */
  private String qualifiedNameDocumentation(final Export it) {
    return "Return the qualified name under which a " + it.getType().getName()
        + " object is exported, or <code>null</code> if the object should not be exported.\n"
        + "\n"
        + "@param obj\n"
        + "         The object to be exported\n"
        + "@return The object's qualified name, or <code>null</code> if the object is not to be exported";
  }

  /**
   * Infers the {@code <Name>ResourceDescriptionManager}.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @param acceptor
   *          the type acceptor, must not be {@code null}
   */
  private void inferResourceDescriptionManager(final ExportModel model, final IJvmDeclaredTypeAcceptor acceptor) {
    final Grammar grammar = exportGeneratorX.getGrammar(model);
    final List<Grammar> usedGrammars = grammar != null ? grammar.getUsedGrammars() : new ArrayList<>();
    final Grammar extendedGrammar = usedGrammars.isEmpty() || usedGrammars.get(0).getName().endsWith(".Terminals") ? null : usedGrammars.get(0);
    final String initializerBody = extendedGrammar != null
        ? "com.google.common.collect.ImmutableSet.copyOf(com.google.common.collect.Sets.union(%s.INTERESTING_EXTS, of(/*add extensions here*/)))"
            .formatted(exportGeneratorX.getResourceDescriptionManager(extendedGrammar))
        : "all()";
    final Procedure1<JvmGenericType> initializer = (final JvmGenericType it) -> {
      it.getSuperTypes().add(_typeReferenceBuilder.typeRef(AbstractCachingResourceDescriptionManager.class));
      it.getAnnotations().add(typeOnlyAnnotation(Singleton.class));
      addSuppressWarningsAll(it);
      jvmTypesBuilder.setDocumentation(it, "Resource description manager for %s resources.".formatted(model.getName()));
      final Procedure1<JvmField> fieldInitializer = (final JvmField field) -> {
        field.setVisibility(JvmVisibility.PUBLIC);
        field.setStatic(true);
        field.setFinal(true);
        final Procedure1<ITreeAppendable> value = (final ITreeAppendable appendable) -> appendJavaInitializer(appendable, initializerBody, model);
        jvmTypesBuilder.setInitializer(field, value);
      };
      it.getMembers().add(jvmTypesBuilder.toField(model, "INTERESTING_EXTS",
          _typeReferenceBuilder.typeRef(Set.class, _typeReferenceBuilder.typeRef(String.class)), fieldInitializer));
      final Procedure1<JvmOperation> methodInitializer = (final JvmOperation method) -> {
        method.setVisibility(JvmVisibility.PROTECTED);
        method.getAnnotations().add(typeOnlyAnnotation(Override.class));
        final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> appendable.append("return INTERESTING_EXTS;");
        jvmTypesBuilder.setBody(method, body);
      };
      it.getMembers().add(jvmTypesBuilder.toMethod(model, "getInterestingExtensions",
          _typeReferenceBuilder.typeRef(Set.class, _typeReferenceBuilder.typeRef(String.class)), methodInitializer));
    };
    acceptor.<JvmGenericType> accept(jvmTypesBuilder.toClass(model, exportGeneratorX.getResourceDescriptionManager(model)), initializer);
  }

  /**
   * Infers the {@code <Name>ResourceDescriptionStrategy}.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @param acceptor
   *          the type acceptor, must not be {@code null}
   * @return the inferred type, never {@code null}
   */
  private JvmGenericType inferResourceDescriptionStrategy(final ExportModel model, final IJvmDeclaredTypeAcceptor acceptor) {
    final JvmGenericType inferredType = jvmTypesBuilder.toClass(model, exportGeneratorX.getResourceDescriptionStrategy(model));
    final Procedure1<JvmGenericType> initializer = (final JvmGenericType it) -> {
      it.getSuperTypes().add(_typeReferenceBuilder.typeRef(AbstractResourceDescriptionStrategy.class));
      addSuppressWarningsAll(it);
      final Procedure1<JvmField> eclassesField = (final JvmField field) -> {
        field.setVisibility(JvmVisibility.PRIVATE);
        field.setStatic(true);
        field.setFinal(true);
        final Procedure1<ITreeAppendable> value = (final ITreeAppendable appendable) -> appendJavaInitializer(appendable,
            renderBody(model, () -> exportedEClassesInitializer(model)), model);
        jvmTypesBuilder.setInitializer(field, value);
      };
      it.getMembers().add(jvmTypesBuilder.toField(model, "EXPORTED_ECLASSES",
          _typeReferenceBuilder.typeRef(Set.class, _typeReferenceBuilder.typeRef(EClass.class)), eclassesField));
      final Procedure1<JvmOperation> getExportedEClasses = (final JvmOperation method) -> {
        method.setVisibility(JvmVisibility.PUBLIC);
        method.getAnnotations().add(typeOnlyAnnotation(Override.class));
        method.getParameters().add(jvmTypesBuilder.toParameter(model, "resource", _typeReferenceBuilder.typeRef(Resource.class)));
        final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> appendable.append("return EXPORTED_ECLASSES;");
        jvmTypesBuilder.setBody(method, body);
      };
      it.getMembers().add(jvmTypesBuilder.toMethod(model, "getExportedEClasses",
          _typeReferenceBuilder.typeRef(Set.class, _typeReferenceBuilder.typeRef(EClass.class)), getExportedEClasses));
      if (!model.getExports().isEmpty()) {
        final Procedure1<JvmField> acceptorInitializer = (final JvmField field) -> {
          field.setVisibility(JvmVisibility.PRIVATE);
          field.setFinal(true);
          final Procedure1<ITreeAppendable> value = (final ITreeAppendable appendable) -> appendJavaInitializer(appendable,
              "new ThreadLocal<org.eclipse.xtext.util.IAcceptor<org.eclipse.xtext.resource.IEObjectDescription>>()", model);
          jvmTypesBuilder.setInitializer(field, value);
        };
        it.getMembers().add(jvmTypesBuilder.toField(model, "acceptor", _typeReferenceBuilder.typeRef(ThreadLocal.class,
            _typeReferenceBuilder.typeRef(IAcceptor.class, _typeReferenceBuilder.typeRef(IEObjectDescription.class))), acceptorInitializer));
        for (final EPackage p : strategyPackages(model)) {
          final Procedure1<JvmField> switchInitializer = (final JvmField field) -> {
            field.setVisibility(JvmVisibility.PRIVATE);
            field.setFinal(true);
            final Procedure1<ITreeAppendable> value = (final ITreeAppendable appendable) -> appendJavaInitializer(appendable,
                renderBody(model, () -> strategySwitchInitializer(model, p)), model);
            jvmTypesBuilder.setInitializer(field, value);
          };
          it.getMembers().add(jvmTypesBuilder.toField(model, p.getName() + "ExportSwitch",
              _typeReferenceBuilder.typeRef(Switch.class, _typeReferenceBuilder.typeRef(Boolean.class)), switchInitializer));
        }
        final Procedure1<JvmOperation> doCreate = (final JvmOperation method) -> {
          method.setVisibility(JvmVisibility.PROTECTED);
          method.getAnnotations().add(typeOnlyAnnotation(Override.class));
          method.getParameters().add(jvmTypesBuilder.toParameter(model, "object", _typeReferenceBuilder.typeRef(EObject.class)));
          method.getParameters().add(jvmTypesBuilder.toParameter(model, "acceptor",
              _typeReferenceBuilder.typeRef(IAcceptor.class, _typeReferenceBuilder.typeRef(IEObjectDescription.class))));
          final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> appendJava(appendable,
              renderBody(model, () -> strategyDoCreateBody(model)), model);
          jvmTypesBuilder.setBody(method, body);
        };
        it.getMembers().add(jvmTypesBuilder.toMethod(model, "doCreateEObjectDescriptions", _typeReferenceBuilder.typeRef(Boolean.TYPE), doCreate));
      }
    };
    acceptor.<JvmGenericType> accept(inferredType, initializer);
    return inferredType;
  }

  /**
   * Infers the {@code <Name>ResourceDescriptionConstants} interface.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @param acceptor
   *          the type acceptor, must not be {@code null}
   */
  private void inferResourceDescriptionConstants(final ExportModel model, final IJvmDeclaredTypeAcceptor acceptor) {
    final Procedure1<JvmGenericType> initializer = (final JvmGenericType it) -> {
      it.setInterface(true);
      for (final Export c : model.getExports().stream().filter(export -> !export.getType().isAbstract()).toList()) {
        for (final EAttribute attr : c.getAllEAttributes()) {
          final Procedure1<JvmField> fieldInitializer = (final JvmField field) -> {
            field.setVisibility(JvmVisibility.PUBLIC);
            field.setStatic(true);
            field.setFinal(true);
            final Procedure1<ITreeAppendable> value = (final ITreeAppendable appendable) -> appendable
                .append("\"" + Strings.convertToJavaString(attr.getName()) + "\"");
            jvmTypesBuilder.setInitializer(field, value);
          };
          it.getMembers().add(jvmTypesBuilder.toField(model, exportGeneratorX.constantName(attr, c.getType()),
              _typeReferenceBuilder.typeRef(String.class), fieldInitializer));
        }
        for (final UserData data : exportGeneratorX.allUserData(c)) {
          final Procedure1<JvmField> fieldInitializer = (final JvmField field) -> {
            field.setVisibility(JvmVisibility.PUBLIC);
            field.setStatic(true);
            field.setFinal(true);
            final Procedure1<ITreeAppendable> value = (final ITreeAppendable appendable) -> appendable
                .append("\"" + Strings.convertToJavaString(data.getName()) + "\"");
            jvmTypesBuilder.setInitializer(field, value);
          };
          it.getMembers().add(jvmTypesBuilder.toField(model, exportGeneratorX.constantName(data, c.getType()),
              _typeReferenceBuilder.typeRef(String.class), fieldInitializer));
        }
      }
    };
    acceptor.<JvmGenericType> accept(jvmTypesBuilder.toClass(model, exportGeneratorX.getResourceDescriptionConstants(model)), initializer);
  }

  /**
   * Infers the {@code <Name>FingerprintComputer}.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @param acceptor
   *          the type acceptor, must not be {@code null}
   * @return the inferred type, never {@code null}
   */
  private JvmGenericType inferFingerprintComputer(final ExportModel model, final IJvmDeclaredTypeAcceptor acceptor) {
    final JvmGenericType inferredType = jvmTypesBuilder.toClass(model, exportGeneratorX.getFingerprintComputer(model));
    final Procedure1<JvmGenericType> initializer = (final JvmGenericType it) -> {
      it.getSuperTypes().add(_typeReferenceBuilder.typeRef(AbstractStreamingFingerprintComputer.class));
      addSuppressWarningsAll(it);
      if (model.getInterfaces().isEmpty()) {
        final Procedure1<JvmOperation> computeFingerprint = (final JvmOperation method) -> {
          method.setVisibility(JvmVisibility.PUBLIC);
          method.getAnnotations().add(typeOnlyAnnotation(Override.class));
          method.getParameters().add(jvmTypesBuilder.toParameter(model, "resource", _typeReferenceBuilder.typeRef(Resource.class)));
          final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> appendable
              .append("// no fingerprint defined\nreturn null;");
          jvmTypesBuilder.setBody(method, body);
        };
        it.getMembers().add(jvmTypesBuilder.toMethod(model, "computeFingerprint", _typeReferenceBuilder.typeRef(String.class), computeFingerprint));
      }
      final Procedure1<JvmField> hasherAccessField = (final JvmField field) -> {
        field.setVisibility(JvmVisibility.PRIVATE);
        final Procedure1<ITreeAppendable> value = (final ITreeAppendable appendable) -> appendJavaInitializer(appendable,
            "new ThreadLocal<com.google.common.hash.Hasher>()", model);
        jvmTypesBuilder.setInitializer(field, value);
      };
      it.getMembers().add(jvmTypesBuilder.toField(model, "hasherAccess",
          _typeReferenceBuilder.typeRef(ThreadLocal.class, _typeReferenceBuilder.typeRef(Hasher.class)), hasherAccessField));
      for (final EPackage p : fingerprintPackages(model)) {
        final Procedure1<JvmField> switchField = (final JvmField field) -> {
          field.setVisibility(JvmVisibility.PRIVATE);
          field.setFinal(true);
          final Procedure1<ITreeAppendable> value = (final ITreeAppendable appendable) -> appendJavaInitializer(appendable,
              renderBody(model, () -> fingerprintSwitchInitializer(model, p)), model);
          jvmTypesBuilder.setInitializer(field, value);
        };
        it.getMembers().add(jvmTypesBuilder.toField(model, p.getName() + "Switch",
            _typeReferenceBuilder.typeRef(Switch.class, _typeReferenceBuilder.typeRef(Hasher.class)), switchField));
      }
      final Procedure1<JvmOperation> fingerprint = (final JvmOperation method) -> {
        method.setVisibility(JvmVisibility.PROTECTED);
        method.getAnnotations().add(typeOnlyAnnotation(Override.class));
        method.getParameters().add(jvmTypesBuilder.toParameter(model, "object", _typeReferenceBuilder.typeRef(EObject.class)));
        method.getParameters().add(jvmTypesBuilder.toParameter(model, "hasher", _typeReferenceBuilder.typeRef(Hasher.class)));
        final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> appendJava(appendable,
            renderBody(model, () -> fingerprintMethodBody(model)), model);
        jvmTypesBuilder.setBody(method, body);
      };
      it.getMembers().add(jvmTypesBuilder.toMethod(model, "fingerprint", _typeReferenceBuilder.typeRef(Void.TYPE), fingerprint));
    };
    acceptor.<JvmGenericType> accept(inferredType, initializer);
    return inferredType;
  }

  /**
   * Infers the {@code <Name>FragmentProvider} (full provider, short stub, or none, depending on the model).
   *
   * @param model
   *          the export model, must not be {@code null}
   * @param acceptor
   *          the type acceptor, must not be {@code null}
   * @return the inferred type, or {@code null} if the model needs no fragment provider
   */
  private JvmGenericType inferFragmentProvider(final ExportModel model, final IJvmDeclaredTypeAcceptor acceptor) {
    final List<Export> fingerprintedExports = model.getExports().stream()
        .filter(export -> export.isFingerprint() && export.getFragmentAttribute() != null).toList();
    if (!fingerprintedExports.isEmpty() || model.isExtension()) {
      final JvmGenericType inferredType = jvmTypesBuilder.toClass(model, exportGeneratorX.getFragmentProvider(model));
      final Procedure1<JvmGenericType> initializer = (final JvmGenericType it) -> {
        it.getSuperTypes().add(_typeReferenceBuilder.typeRef(AbstractSelectorFragmentProvider.class));
        addSuppressWarningsAll(it);
        if (!fingerprintedExports.isEmpty()) {
          final Procedure1<JvmOperation> appendFragmentSegment = (final JvmOperation method) -> {
            method.setVisibility(JvmVisibility.PUBLIC);
            method.getAnnotations().add(typeOnlyAnnotation(Override.class));
            method.getParameters().add(jvmTypesBuilder.toParameter(model, "object", _typeReferenceBuilder.typeRef(EObject.class)));
            method.getParameters().add(jvmTypesBuilder.toParameter(model, "builder", _typeReferenceBuilder.typeRef(StringBuilder.class)));
            final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> appendJava(appendable,
                renderBody(model, () -> appendFragmentSegmentBody(model, fingerprintedExports)), model);
            jvmTypesBuilder.setBody(method, body);
          };
          it.getMembers().add(
              jvmTypesBuilder.toMethod(model, "appendFragmentSegment", _typeReferenceBuilder.typeRef(Boolean.TYPE), appendFragmentSegment));
        }
        if (model.isExtension()) {
          final Procedure1<JvmOperation> fallback = (final JvmOperation method) -> {
            method.setVisibility(JvmVisibility.PROTECTED);
            method.getAnnotations().add(typeOnlyAnnotation(Override.class));
            method.getParameters().add(jvmTypesBuilder.toParameter(model, "object", _typeReferenceBuilder.typeRef(EObject.class)));
            method.getParameters().add(jvmTypesBuilder.toParameter(model, "builder", _typeReferenceBuilder.typeRef(StringBuilder.class)));
            final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> appendable
                .append("// For export extension we must return false, so the logic will try other extensions\nreturn false;");
            jvmTypesBuilder.setBody(method, body);
          };
          it.getMembers().add(
              jvmTypesBuilder.toMethod(model, "appendFragmentSegmentFallback", _typeReferenceBuilder.typeRef(Boolean.TYPE), fallback));
        }
        for (final Export e : fingerprintedExports) {
          final Procedure1<JvmOperation> typedSegment = (final JvmOperation method) -> {
            method.setVisibility(JvmVisibility.PROTECTED);
            method.getParameters().add(
                jvmTypesBuilder.toParameter(model, "obj", _typeReferenceBuilder.typeRef(genModelUtil.instanceClassName(e.getType()))));
            method.getParameters().add(jvmTypesBuilder.toParameter(model, "builder", _typeReferenceBuilder.typeRef(StringBuilder.class)));
            final Procedure1<ITreeAppendable> body = (final ITreeAppendable appendable) -> appendJava(appendable,
                renderBody(model, () -> "return computeSelectorFragmentSegment(obj, %s, %s, builder);"
                    .formatted(genModelUtil.literalIdentifier(e.getFragmentAttribute()), e.isFragmentUnique())),
                model);
            jvmTypesBuilder.setBody(method, body);
          };
          it.getMembers().add(
              jvmTypesBuilder.toMethod(model, "appendFragmentSegment", _typeReferenceBuilder.typeRef(Boolean.TYPE), typedSegment));
        }
      };
      acceptor.<JvmGenericType> accept(inferredType, initializer);
      return inferredType;
    } else if (!model.getExports().isEmpty()) {
      final JvmGenericType inferredType = jvmTypesBuilder.toClass(model, exportGeneratorX.getFragmentProvider(model));
      final Procedure1<JvmGenericType> initializer = (final JvmGenericType it) -> {
        it.getSuperTypes().add(_typeReferenceBuilder.typeRef(ShortFragmentProvider.class));
        addSuppressWarningsAll(it);
      };
      acceptor.<JvmGenericType> accept(inferredType, initializer);
      return inferredType;
    }
    return null;
  }

  /**
   * Infers the {@code <Name>ExportFeatureExtension} (extension models only).
   * <p>
   * The injected collaborators are referenced through the {@link JvmGenericType}s inferred for them rather than by
   * name: the classes do not exist on the classpath while the model is being inferred, so resolving them by name
   * would yield an unknown type reference which the {@code JvmModelGenerator} emits fully qualified instead of
   * importing.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @param acceptor
   *          the type acceptor, must not be {@code null}
   * @param namesProvider
   *          the inferred exported names provider, must not be {@code null}
   * @param fingerprintComputer
   *          the inferred fingerprint computer, must not be {@code null}
   * @param fragmentProvider
   *          the inferred fragment provider, may be {@code null}
   * @param resourceDescriptionStrategy
   *          the inferred resource description strategy, must not be {@code null}
   */
  private void inferExportFeatureExtension(final ExportModel model, final IJvmDeclaredTypeAcceptor acceptor,
      final JvmGenericType namesProvider, final JvmGenericType fingerprintComputer, final JvmGenericType fragmentProvider,
      final JvmGenericType resourceDescriptionStrategy) {
    final Procedure1<JvmGenericType> initializer = (final JvmGenericType it) -> {
      it.getSuperTypes().add(_typeReferenceBuilder.typeRef(AbstractExportFeatureExtension.class));
      addSuppressWarningsAll(it);
      it.getMembers().add(jvmTypesBuilder.toField(model, "namesProvider", _typeReferenceBuilder.typeRef(namesProvider), injectedField()));
      it.getMembers().add(jvmTypesBuilder.toField(model, "fingerprintComputer", _typeReferenceBuilder.typeRef(fingerprintComputer), injectedField()));
      it.getMembers().add(jvmTypesBuilder.toField(model, "fragmentProvider",
          typeRefOrName(fragmentProvider, exportGeneratorX.getFragmentProvider(model)), injectedField()));
      it.getMembers().add(
          jvmTypesBuilder.toField(model, "resourceDescriptionStrategy", _typeReferenceBuilder.typeRef(resourceDescriptionStrategy), injectedField()));
      it.getMembers().add(jvmTypesBuilder.toMethod(model, "getNamesProvider", _typeReferenceBuilder.typeRef(IQualifiedNameProvider.class),
          overriddenGetter("return namesProvider;")));
      it.getMembers().add(jvmTypesBuilder.toMethod(model, "getFingerprintComputer", _typeReferenceBuilder.typeRef(IFingerprintComputer.class),
          overriddenGetter("return fingerprintComputer;")));
      it.getMembers().add(jvmTypesBuilder.toMethod(model, "getFragmentProvider", _typeReferenceBuilder.typeRef(AbstractSelectorFragmentProvider.class),
          overriddenGetter("return fragmentProvider;")));
      it.getMembers().add(jvmTypesBuilder.toMethod(model, "getResourceDescriptionStrategy",
          _typeReferenceBuilder.typeRef(AbstractResourceDescriptionStrategy.class), overriddenGetter("return resourceDescriptionStrategy;")));
    };
    acceptor.<JvmGenericType> accept(jvmTypesBuilder.toClass(model, exportGeneratorX.getExportFeatureExtension(model)), initializer);
  }

  /**
   * Returns the initializer of a {@code private} field annotated with {@code @Inject}.
   *
   * @return the field initializer, never {@code null}
   */
  private Procedure1<JvmField> injectedField() {
    return (final JvmField field) -> {
      field.setVisibility(JvmVisibility.PRIVATE);
      field.getAnnotations().add(typeOnlyAnnotation(Inject.class));
    };
  }

  /**
   * Returns the initializer of a {@code protected} overriding method with the given body.
   *
   * @param body
   *          the Java source of the method body, must not be {@code null}
   * @return the method initializer, never {@code null}
   */
  private Procedure1<JvmOperation> overriddenGetter(final String body) {
    return (final JvmOperation method) -> {
      method.setVisibility(JvmVisibility.PROTECTED);
      method.getAnnotations().add(typeOnlyAnnotation(Override.class));
      final Procedure1<ITreeAppendable> methodBody = (final ITreeAppendable appendable) -> appendable.append(body);
      jvmTypesBuilder.setBody(method, methodBody);
    };
  }

  /**
   * Returns a type reference to the given inferred type, falling back to a reference resolved by name when no type
   * was inferred.
   *
   * @param inferredType
   *          the inferred type, may be {@code null}
   * @param qualifiedName
   *          the fully qualified name to fall back to, must not be {@code null}
   * @return the type reference, never {@code null}
   */
  private JvmTypeReference typeRefOrName(final JvmGenericType inferredType, final String qualifiedName) {
    return inferredType == null ? _typeReferenceBuilder.typeRef(qualifiedName) : _typeReferenceBuilder.typeRef(inferredType);
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Body producers (framework types fully qualified so the generated compilation units need no imports)
  // -------------------------------------------------------------------------------------------------------------------

  /**
   * Returns the EPackages of the non-abstract exported types, sorted by namespace URI.
   * <p>
   * Filters out null entries: an Export whose {@code type} is an unresolved proxy returns a null
   * {@code EPackage}. This happens during editor reconciliation when {@code _infer} runs before the
   * project's index has resolved the type. Skipping those entries keeps the inferred JVM model
   * well-formed; reconciliation re-runs once linking completes and produces the full set.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @return the EPackages, never {@code null}
   */
  private Iterable<EPackage> strategyPackages(final ExportModel model) {
    return model.getExports().stream()
        .filter(export -> export.getType() != null && !export.getType().isAbstract())
        .map(export -> export.getType().getEPackage())
        .filter(Objects::nonNull)
        .collect(Collectors.toCollection(LinkedHashSet::new))
        .stream()
        .sorted(Comparator.comparing(EPackage::getNsURI))
        .toList();
  }

  /**
   * Returns the EPackages of the fingerprint interfaces, sorted by namespace URI. See
   * {@link #strategyPackages} for the rationale on filtering nulls.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @return the EPackages, never {@code null}
   */
  private Iterable<EPackage> fingerprintPackages(final ExportModel model) {
    return model.getInterfaces().stream()
        .filter(declaration -> declaration.getType() != null)
        .map(declaration -> declaration.getType().getEPackage())
        .filter(Objects::nonNull)
        .collect(Collectors.toCollection(LinkedHashSet::new))
        .stream()
        .sorted(Comparator.comparing(EPackage::getNsURI))
        .toList();
  }

  /**
   * Produces the body of the dispatching {@code qualifiedName} method.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @return the Java source fragment, never {@code null}
   */
  private CharSequence qualifiedNameDispatchBody(final ExportModel model) {
    final List<Export> types = model.getExports();
    final Set<EClass> exportedEClasses = types.stream().map(Export::getType).collect(Collectors.toCollection(LinkedHashSet::new));
    final ListMultimap<EPackage, Export> exportsMap = exportGeneratorX.sortedExportsByEPackage(types);
    final StringConcatenation builder = new StringConcatenation();
    builder.append("org.eclipse.emf.ecore.EClass eClass = object.eClass();");
    builder.newLine();
    builder.append("org.eclipse.emf.ecore.EPackage ePackage = eClass.getEPackage();");
    builder.newLine();
    for (final EPackage p : exportsMap.keySet().stream().sorted(Comparator.comparing(EPackage::getNsURI)).toList()) {
      builder.append("if (ePackage == ");
      builder.append(genModelUtil.qualifiedPackageInterfaceName(p));
      builder.append(".eINSTANCE) {");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append("int classifierID = eClass.getClassifierID();");
      builder.newLine();
      builder.append("  ");
      builder.append("switch (classifierID) {");
      builder.newLine();
      for (final EClass c : Iterables.filter(p.getEClassifiers(), EClass.class)) {
        if (exportedEClasses.stream().anyMatch(e -> e.isSuperTypeOf(c))) {
          builder.append("  ");
          builder.append("case ");
          builder.append(genModelUtil.classifierIdLiteral(c), "  ");
          builder.append(": {");
          builder.newLineIfNotEmpty();
          builder.append("  ");
          builder.append("  ");
          builder.append("return qualifiedName((");
          builder.append(genModelUtil.instanceClassName(c), "    ");
          builder.append(") object);");
          builder.newLineIfNotEmpty();
          builder.append("  ");
          builder.append("}");
          builder.newLine();
        }
      }
      builder.append("  ");
      builder.append("default:");
      builder.newLine();
      builder.append("    ");
      builder.append("return null;");
      builder.newLine();
      builder.append("  ");
      builder.append("}");
      builder.newLine();
      builder.append("}");
      builder.newLine();
    }
    builder.append("return null;");
    builder.newLine();
    return builder;
  }

  /**
   * Produces the body of the {@code qualifiedName} overload generated for a single export declaration.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @param c
   *          the export declaration, must not be {@code null}
   * @return the Java source fragment, never {@code null}
   */
  private CharSequence qualifiedNameBody(final ExportModel model, final Export c) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append(generatorUtilX.javaContributorComment(generatorUtilX.location(c)));
    builder.newLineIfNotEmpty();
    if (c.getNaming() != null) {
      builder.append("final Object name = ");
      builder.append(javaExpr(c.getNaming(), c.getType(), model));
      builder.append(";");
      builder.newLineIfNotEmpty();
      builder.append("return name != null ? ");
      if (c.isQualifiedName()) {
        builder.append("getConverter().toQualifiedName(String.valueOf(name))");
      } else {
        builder.append("qualifyWithContainerName(obj, String.valueOf(name))");
      }
      builder.append(" : null;");
      builder.newLineIfNotEmpty();
    } else {
      builder.append("return ");
      if (c.isQualifiedName()) {
        builder.append("getConverter().toQualifiedName(getResolver().apply(obj))");
      } else {
        builder.append("qualifyWithContainerName(obj, getResolver().apply(obj))");
      }
      builder.append("; // \"name\" attribute by default");
      builder.newLineIfNotEmpty();
    }
    return builder;
  }

  /**
   * Produces the initializer of the {@code EXPORTED_ECLASSES} constant.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @return the Java source fragment, never {@code null}
   */
  private CharSequence exportedEClassesInitializer(final ExportModel model) {
    final Map<EClass, Export> e = exportGeneratorX.typeMap(model.getExports(), exportGeneratorX.getGrammar(model));
    final StringConcatenation builder = new StringConcatenation();
    builder.append("com.google.common.collect.ImmutableSet.copyOf(new org.eclipse.emf.ecore.EClass[] {");
    builder.newLine();
    builder.append("  ");
    boolean hasElements = false;
    for (final EClass c : e.keySet().stream().sorted(Comparator.comparing(eClass -> genModelUtil.literalIdentifier(eClass))).toList()) {
      if (hasElements) {
        builder.appendImmediate(",\n", "  ");
      } else {
        hasElements = true;
      }
      builder.append(genModelUtil.literalIdentifier(c), "  ");
    }
    builder.newLineIfNotEmpty();
    builder.append("})");
    return builder;
  }

  /**
   * Produces the initializer of the export switch of a single EPackage.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @param p
   *          the EPackage, must not be {@code null}
   * @return the Java source fragment, never {@code null}
   */
  private CharSequence strategySwitchInitializer(final ExportModel model, final EPackage p) {
    final List<Export> types = model.getExports();
    final StringConcatenation builder = new StringConcatenation();
    builder.append("new ");
    builder.append(genModelUtil.qualifiedSwitchClassName(p));
    builder.append("<Boolean>() {");
    builder.newLineIfNotEmpty();
    builder.newLine();
    builder.append("  ");
    builder.append("@Override");
    builder.newLine();
    builder.append("  ");
    builder.append("public Boolean defaultCase(final org.eclipse.emf.ecore.EObject obj) {");
    builder.newLine();
    builder.append("    ");
    builder.append("return true;");
    builder.newLine();
    builder.append("  ");
    builder.append("}");
    builder.newLine();
    for (final Export c : types.stream()
        .filter(export -> !export.getType().isAbstract() && Objects.equals(export.getType().getEPackage(), p)).toList()) {
      builder.newLine();
      builder.append("  ");
      builder.append(generatorUtilX.javaContributorComment(generatorUtilX.location(c)), "  ");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append("@Override");
      builder.newLine();
      builder.append("  ");
      builder.append("public Boolean case");
      builder.append(c.getType().getName(), "  ");
      builder.append("(final ");
      builder.append(genModelUtil.instanceClassName(c.getType()), "  ");
      builder.append(" obj) {");
      builder.newLineIfNotEmpty();
      if (c.getGuard() == null) {
        builder.append("  ");
        builder.append("  ");
        builder.append(generateCaseBody(model, c), "    ");
        builder.newLineIfNotEmpty();
      } else {
        builder.append("  ");
        builder.append("  ");
        final String guard = javaExpr(c.getGuard(), c.getType(), model);
        builder.newLineIfNotEmpty();
        if (!"false".equalsIgnoreCase(guard)) {
          builder.append("  ");
          builder.append("  ");
          builder.append(generatorUtilX.javaContributorComment(generatorUtilX.location(c.getGuard())), "    ");
          builder.newLineIfNotEmpty();
          builder.append("  ");
          builder.append("  ");
          builder.append("if (");
          builder.append(guard, "    ");
          builder.append(") {");
          builder.newLineIfNotEmpty();
          builder.append("  ");
          builder.append("  ");
          builder.append("  ");
          builder.append(generateCaseBody(model, c), "      ");
          builder.newLineIfNotEmpty();
          builder.append("  ");
          builder.append("  ");
          builder.append("}");
          builder.newLine();
        }
      }
      builder.newLine();
      builder.append("  ");
      builder.append("  ");
      builder.append("// can ");
      builder.append(c.getType().getName(), "    ");
      builder.append(" contain any nested ");
      builder.append(nonAbstractExportedTypeNames(types), "    ");
      builder.append(" objects ?");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append("  ");
      builder.append("return ");
      builder.append(generatorUtilX.canContain(c.getType(), nonAbstractExportedTypes(types), exportGeneratorX.getGrammar(model)), "    ");
      builder.append(";");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append("}");
      builder.newLine();
    }
    builder.append("}");
    return builder;
  }

  /**
   * Returns the non-abstract exported types, in declaration order.
   *
   * @param types
   *          the export declarations, must not be {@code null}
   * @return the non-abstract exported types, never {@code null}
   */
  private Set<EClass> nonAbstractExportedTypes(final List<Export> types) {
    return types.stream().map(Export::getType).filter(type -> !type.isAbstract()).collect(Collectors.toCollection(LinkedHashSet::new));
  }

  /**
   * Returns the names of the non-abstract exported types, in declaration order.
   *
   * @param types
   *          the export declarations, must not be {@code null}
   * @return the type names, never {@code null}
   */
  private Set<String> nonAbstractExportedTypeNames(final List<Export> types) {
    return types.stream().map(Export::getType).filter(type -> !type.isAbstract()).map(EClass::getName)
        .collect(Collectors.toCollection(LinkedHashSet::new));
  }

  /**
   * Returns the name under which the {@code <Name>ResourceDescriptionConstants} interface is referenced from the
   * generated resource description strategy. Both types are generated into the model's {@code resource} package, so
   * the simple name suffices and keeps the emitted {@code builder.put(...)} calls readable.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @return the simple name of the constants interface, never {@code null}
   */
  private String resourceDescriptionConstantsName(final ExportModel model) {
    final String qualifiedName = exportGeneratorX.getResourceDescriptionConstants(model);
    return qualifiedName.substring(qualifiedName.lastIndexOf('.') + 1);
  }

  /**
   * Produces the body of a single {@code caseXyz} method of the export switch.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @param c
   *          the export declaration, must not be {@code null}
   * @return the Java source fragment, never {@code null}
   */
  private CharSequence generateCaseBody(final ExportModel model, final Export c) {
    final List<EAttribute> a = c.getAllEAttributes();
    final List<UserData> d = exportGeneratorX.allUserData(c);
    final StringConcatenation builder = new StringConcatenation();
    final boolean hasExportedData = !a.isEmpty() || !d.isEmpty();
    final boolean hasFingerprintOrLookup = c.isFingerprint() || c.isResourceFingerprint() || c.isLookup();
    if (hasExportedData || hasFingerprintOrLookup) {
      builder.append("// Use a forwarding map to delay calculation as much as possible; otherwise we may get recursive EObject resolution attempts");
      builder.newLine();
      builder.append("java.util.Map<String, String> data = new com.avaloq.tools.ddk.xtext.resource.extensions.AbstractForwardingResourceDescriptionStrategyMap() {");
      builder.newLine();
      builder.newLine();
      builder.append("  ");
      builder.append("@Override");
      builder.newLine();
      builder.append("  ");
      builder.append("protected void fill(final com.google.common.collect.ImmutableMap.Builder<String, String> builder) {");
      builder.newLine();
      builder.append("    ");
      builder.append("Object value = null;");
      builder.newLine();
      if (c.isFingerprint()) {
        builder.append("    ");
        builder.append("// Fingerprint");
        builder.newLine();
        builder.append("    ");
        builder.append("value = getFingerprint(obj);");
        builder.newLine();
        builder.append("    ");
        builder.append("if (value != null) {");
        builder.newLine();
        builder.append("    ");
        builder.append("  ");
        builder.append("builder.put(com.avaloq.tools.ddk.xtext.resource.IFingerprintComputer.OBJECT_FINGERPRINT, value.toString());");
        builder.newLine();
        builder.append("    ");
        builder.append("}");
        builder.newLine();
      } else if (c.isResourceFingerprint()) {
        builder.append("    ");
        builder.append("// Resource fingerprint");
        builder.newLine();
        builder.append("    ");
        builder.append("value = getFingerprint(obj);");
        builder.newLine();
        builder.append("    ");
        builder.append("if (value != null) {");
        builder.newLine();
        builder.append("    ");
        builder.append("  ");
        builder.append("builder.put(com.avaloq.tools.ddk.xtext.resource.IFingerprintComputer.RESOURCE_FINGERPRINT, value.toString());");
        builder.newLine();
        builder.append("    ");
        builder.append("}");
        builder.newLine();
      }
      if (c.isLookup()) {
        builder.append("    ");
        builder.append("// Allow lookups");
        builder.newLine();
        if (c.getLookupPredicate() != null) {
          builder.append("    ");
          builder.append(generatorUtilX.javaContributorComment(generatorUtilX.location(c.getLookupPredicate())), "    ");
          builder.newLineIfNotEmpty();
          builder.append("    ");
          builder.append("if (");
          builder.append(javaExpr(c.getLookupPredicate(), c.getType(), model), "    ");
          builder.append(") {");
          builder.newLineIfNotEmpty();
          builder.append("    ");
          builder.append("  ");
          builder.append("builder.put(com.avaloq.tools.ddk.xtext.resource.DetachableEObjectDescription.ALLOW_LOOKUP, Boolean.TRUE.toString());");
          builder.newLine();
          builder.append("    ");
          builder.append("}");
          builder.newLine();
        } else {
          builder.append("    ");
          builder.append("builder.put(com.avaloq.tools.ddk.xtext.resource.DetachableEObjectDescription.ALLOW_LOOKUP, Boolean.TRUE.toString());");
          builder.newLine();
        }
      }
      if (!a.isEmpty()) {
        builder.append("    ");
        builder.append("// Exported attributes");
        builder.newLine();
        for (final EAttribute attr : a) {
          builder.append("    ");
          builder.append("value = obj.eGet(");
          builder.append(genModelUtil.literalIdentifier(attr), "    ");
          builder.append(", false);");
          builder.newLineIfNotEmpty();
          builder.append("    ");
          builder.append("if (value != null) {");
          builder.newLine();
          builder.append("    ");
          builder.append("  ");
          builder.append("builder.put(");
          builder.append(resourceDescriptionConstantsName(model), "      ");
          builder.append(".");
          builder.append(exportGeneratorX.constantName(attr, c.getType()), "      ");
          builder.append(", value.toString());");
          builder.newLineIfNotEmpty();
          builder.append("    ");
          builder.append("}");
          builder.newLine();
        }
      }
      if (!d.isEmpty()) {
        builder.append("    ");
        builder.append("// User data");
        builder.newLine();
        for (final UserData data : d) {
          builder.append("    ");
          builder.append("value = ");
          builder.append(javaExpr(data.getExpr(), c.getType(), model), "    ");
          builder.append(";");
          builder.newLineIfNotEmpty();
          builder.append("    ");
          builder.append("if (value != null) {");
          builder.newLine();
          builder.append("    ");
          builder.append("  ");
          builder.append("builder.put(");
          builder.append(resourceDescriptionConstantsName(model), "      ");
          builder.append(".");
          builder.append(exportGeneratorX.constantName(data, c.getType()), "      ");
          builder.append(", value.toString());");
          builder.newLineIfNotEmpty();
          builder.append("    ");
          builder.append("}");
          builder.newLine();
        }
      }
      builder.append("  ");
      builder.append("}");
      builder.newLine();
      builder.append("};");
      builder.newLine();
      builder.append("acceptEObjectDescription(obj, data, acceptor.get());");
      builder.newLine();
    } else {
      builder.append("acceptEObjectDescription(obj, acceptor.get());");
      builder.newLine();
    }
    return builder;
  }

  /**
   * Produces the body of {@code doCreateEObjectDescriptions}.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @return the Java source fragment, never {@code null}
   */
  private CharSequence strategyDoCreateBody(final ExportModel model) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append("try {");
    builder.newLine();
    builder.append("  ");
    builder.append("this.acceptor.set(acceptor);");
    builder.newLine();
    builder.append("  ");
    builder.append("final org.eclipse.emf.ecore.EPackage ePackage = object.eClass().getEPackage();");
    builder.newLine();
    for (final EPackage p : strategyPackages(model)) {
      builder.append("  ");
      builder.append("if (ePackage == ");
      builder.append(genModelUtil.qualifiedPackageInterfaceName(p), "  ");
      builder.append(".eINSTANCE) {");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append("  ");
      builder.append("return ");
      builder.append(p.getName(), "    ");
      builder.append("ExportSwitch.doSwitch(object);");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append("}");
      builder.newLine();
    }
    if (model.isExtension()) {
      builder.append("  ");
      builder.append("// Extension does not have to cover all EPackages of the language");
      builder.newLine();
      builder.append("  ");
      builder.append("return false;");
      builder.newLine();
    } else {
      builder.append("  ");
      builder.append("// TODO: generate code for other possible epackages (as defined by grammar)");
      builder.newLine();
      builder.append("  ");
      builder.append("return true;");
      builder.newLine();
    }
    builder.append("} finally {");
    builder.newLine();
    builder.append("  ");
    builder.append("this.acceptor.set(null);");
    builder.newLine();
    builder.append("}");
    builder.newLine();
    return builder;
  }

  /**
   * Produces the initializer of the fingerprint switch of a single EPackage.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @param p
   *          the EPackage, must not be {@code null}
   * @return the Java source fragment, never {@code null}
   */
  private CharSequence fingerprintSwitchInitializer(final ExportModel model, final EPackage p) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append("new ");
    builder.append(genModelUtil.qualifiedSwitchClassName(p));
    builder.append("<com.google.common.hash.Hasher>() {");
    builder.newLineIfNotEmpty();
    for (final Interface f : model.getInterfaces().stream()
        .filter(declaration -> Objects.equals(declaration.getType().getEPackage(), p)).toList()) {
      builder.newLine();
      builder.append("  ");
      builder.append(generatorUtilX.javaContributorComment(generatorUtilX.location(f)), "  ");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append("@Override");
      builder.newLine();
      builder.append("  ");
      builder.append("public com.google.common.hash.Hasher case");
      builder.append(f.getType().getName(), "  ");
      builder.append("(final ");
      builder.append(genModelUtil.instanceClassName(f.getType()), "  ");
      builder.append(" obj) {");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append("  ");
      builder.append("final com.google.common.hash.Hasher hasher = hasherAccess.get();");
      builder.newLine();
      if (f.getGuard() != null) {
        builder.append("  ");
        builder.append("  ");
        builder.append("if (!(");
        builder.append(javaExpr(f.getGuard(), f.getType(), model), "    ");
        builder.append(")) {");
        builder.newLineIfNotEmpty();
        builder.append("  ");
        builder.append("  ");
        builder.append("  ");
        builder.append("return hasher;");
        builder.newLine();
        builder.append("  ");
        builder.append("  ");
        builder.append("}");
        builder.newLine();
      }
      builder.append("  ");
      builder.append("  ");
      builder.append("hasher.putUnencodedChars(obj.eClass().getName()).putChar(ITEM_SEP);");
      builder.newLine();
      for (final Interface superFingerprint : exportGeneratorX.getSuperInterfaces(f, f.getType())) {
        for (final InterfaceItem superItem : superFingerprint.getItems()) {
          builder.append("  ");
          builder.append("  ");
          builder.append(doProfile(superItem, model, superFingerprint.getType()), "    ");
          builder.newLineIfNotEmpty();
        }
      }
      for (final InterfaceItem item : f.getItems()) {
        builder.append("  ");
        builder.append("  ");
        builder.append(doProfile(item, model, f.getType()), "    ");
        builder.newLineIfNotEmpty();
      }
      builder.append("  ");
      builder.append("  ");
      builder.append("return hasher;");
      builder.newLine();
      builder.append("  ");
      builder.append("}");
      builder.newLine();
    }
    builder.append("}");
    return builder;
  }

  /**
   * Produces the body of the {@code fingerprint} method.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @return the Java source fragment, never {@code null}
   */
  private CharSequence fingerprintMethodBody(final ExportModel model) {
    final StringConcatenation builder = new StringConcatenation();
    builder.append("hasherAccess.set(hasher);");
    builder.newLine();
    if (!model.getInterfaces().isEmpty()) {
      builder.append("final org.eclipse.emf.ecore.EPackage ePackage = object.eClass().getEPackage();");
      builder.newLine();
      for (final EPackage p : fingerprintPackages(model)) {
        builder.append("if (ePackage == ");
        builder.append(genModelUtil.qualifiedPackageInterfaceName(p));
        builder.append(".eINSTANCE) {");
        builder.newLineIfNotEmpty();
        builder.append("  ");
        builder.append(p.getName(), "  ");
        builder.append("Switch.doSwitch(object);");
        builder.newLineIfNotEmpty();
        builder.append("}");
        builder.newLine();
      }
    }
    builder.append("hasherAccess.set(null);");
    builder.newLine();
    return builder;
  }

  private CharSequence _doProfile(final InterfaceItem it, final ExportModel model, final EClass type) {
    return "ERROR" + it.toString() + " " + generatorUtilX.javaContributorComment(generatorUtilX.location(it));
  }

  private CharSequence _doProfile(final InterfaceField it, final ExportModel model, final EClass type) {
    final boolean unorderedMany = it.getField().isMany() && it.isUnordered();
    final StringBuilder builder = new StringBuilder(512);
    builder.append("fingerprintFeature(obj, ").append(genModelUtil.literalIdentifier(it.getField()));
    if (unorderedMany) {
      builder.append(", FingerprintOrder.UNORDERED, hasher);\n");
    } else {
      builder.append(", hasher);\n");
    }
    builder.append("hasher.putChar(ITEM_SEP);\n");
    return builder;
  }

  private CharSequence _doProfile(final InterfaceNavigation it, final ExportModel model, final EClass type) {
    final boolean unorderedMany = it.getRef().isMany() && it.isUnordered();
    final StringBuilder builder = new StringBuilder(512);
    builder.append("fingerprintRef(obj, ").append(genModelUtil.literalIdentifier(it.getRef()));
    if (unorderedMany) {
      builder.append(", FingerprintOrder.UNORDERED, hasher);\n");
    } else {
      builder.append(", hasher);\n");
    }
    builder.append("hasher.putChar(ITEM_SEP);\n");
    return builder;
  }

  private CharSequence _doProfile(final InterfaceExpression it, final ExportModel model, final EClass type) {
    return """
        fingerprintExpr(%s, obj, FingerprintOrder.%s, FingerprintIndirection.%s, hasher);
        hasher.putChar(ITEM_SEP);
        """.formatted(javaExpr(it.getExpr(), type, model), it.isUnordered() ? "UNORDERED" : "ORDERED", it.isRef() ? "INDIRECT" : "DIRECT");
  }

  /**
   * Produces the body of the dispatching {@code appendFragmentSegment} method.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @param fingerprintedExports
   *          the fingerprinted export declarations, must not be {@code null}
   * @return the Java source fragment, never {@code null}
   */
  private CharSequence appendFragmentSegmentBody(final ExportModel model, final Collection<Export> fingerprintedExports) {
    final Map<EClass, Export> typeMap = exportGeneratorX.typeMap(fingerprintedExports, exportGeneratorX.getGrammar(model));
    final ListMultimap<EPackage, Export> sortedExportsMap = exportGeneratorX.sortedExportsByEPackage(fingerprintedExports);
    final StringConcatenation builder = new StringConcatenation();
    builder.append("org.eclipse.emf.ecore.EClass eClass = object.eClass();");
    builder.newLine();
    builder.append("org.eclipse.emf.ecore.EPackage ePackage = eClass.getEPackage();");
    builder.newLine();
    for (final EPackage p : sortedExportsMap.keySet()) {
      builder.append("if (ePackage == ");
      builder.append(genModelUtil.qualifiedPackageInterfaceName(p));
      builder.append(".eINSTANCE) {");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      builder.append("int classifierID = eClass.getClassifierID();");
      builder.newLine();
      builder.append("  ");
      builder.append("switch (classifierID) {");
      builder.newLine();
      for (final EClass c : Iterables.filter(p.getEClassifiers(), EClass.class)) {
        if (fingerprintedExports.stream().map(Export::getType).anyMatch(e -> e.isSuperTypeOf(c))) {
          builder.append("  ");
          final Export e = typeMap.get(c);
          builder.newLineIfNotEmpty();
          builder.append("  ");
          builder.append(generatorUtilX.javaContributorComment(generatorUtilX.location(e)), "  ");
          builder.newLineIfNotEmpty();
          builder.append("  ");
          builder.append("case ");
          builder.append(genModelUtil.classifierIdLiteral(c), "  ");
          builder.append(": {");
          builder.newLineIfNotEmpty();
          builder.append("  ");
          builder.append("  ");
          builder.append("return appendFragmentSegment((");
          builder.append(genModelUtil.instanceClassName(c), "    ");
          builder.append(") object, builder);");
          builder.newLineIfNotEmpty();
          builder.append("  ");
          builder.append("}");
          builder.newLine();
        }
      }
      builder.append("  ");
      builder.append("default:");
      builder.newLine();
      builder.append("    ");
      builder.append("return super.appendFragmentSegment(object, builder);");
      builder.newLine();
      builder.append("  ");
      builder.append("}");
      builder.newLine();
      builder.append("}");
      builder.newLine();
    }
    builder.append("return super.appendFragmentSegment(object, builder);");
    builder.newLine();
    return builder;
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Helpers
  // -------------------------------------------------------------------------------------------------------------------

  /**
   * Compiles an embedded expression to a Java expression string in the context of the implicit variable {@code obj}
   * of the given type.
   *
   * @param expression
   *          the expression to compile, must not be {@code null}
   * @param type
   *          the type of the implicit {@code obj} variable, must not be {@code null}
   * @param model
   *          the export model, must not be {@code null}
   * @return the Java expression, never {@code null}
   */
  private String javaExpr(final Expression expression, final EClass type, final ExportModel model) {
    return compiler.javaExpression(expression, translator.newCompilationContext("obj", type, new ArrayList<Pair<String, String>>(), model));
  }

  /**
   * Adds a {@code @SuppressWarnings("all")} annotation to the given type.
   *
   * @param type
   *          the type to annotate, must not be {@code null}
   */
  private void addSuppressWarningsAll(final JvmGenericType type) {
    final JvmAnnotationReference annotation = typesFactory.createJvmAnnotationReference();
    annotation.setAnnotation((JvmAnnotationType) _typeReferenceBuilder.typeRef(SuppressWarnings.class).getType());
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
    annotation.setAnnotation((JvmAnnotationType) _typeReferenceBuilder.typeRef(annotationClass).getType());
    return annotation;
  }

  /**
   * Returns the workspace project containing the given export model, if any.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @return the containing project, or {@code null} if it cannot be determined
   */
  private IProject projectOf(final ExportModel model) {
    final URI uri = model.eResource().getURI();
    if (uri.isPlatformResource()) {
      final IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(uri.toPlatformString(true));
      if (resource != null) {
        return resource.getProject();
      }
    }
    return null;
  }

  /**
   * Tests whether the project already provides its own {@code <Name>ResourceDescriptionManager}.
   * <p>
   * The resource description manager is a stub: the generator only ever seeded it, after which it is owned and
   * customized by the language developer (typically to narrow {@code INTERESTING_EXTS}). The legacy generator
   * expressed this through the {@link ExportOutputConfigurationProvider#STUB_OUTPUT} output configuration, which
   * writes to the hand written source folder and does not override existing resources. Inferred JVM types always go
   * to the default output folder instead, so the check has to be made here - otherwise the generated class would
   * duplicate the existing one and neither compilation unit would compile.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @return {@code true} if a class with that name already exists in the stub source folder
   */
  private boolean hasCustomResourceDescriptionManager(final ExportModel model) {
    final IProject project = projectOf(model);
    final OutputConfiguration stubConfiguration = outputConfigurationProvider.getOutputConfigurations().stream()
        .filter(configuration -> Objects.equals(configuration.getName(), ExportOutputConfigurationProvider.STUB_OUTPUT))
        .findFirst().orElse(null);
    final String stubDirectory = stubConfiguration != null ? stubConfiguration.getOutputDirectory() : null;
    if (project == null || stubDirectory == null) {
      return false;
    }
    return project.getFile(stubDirectory + "/" + exportGeneratorX.getResourceDescriptionManager(model).replace(".", "/") + ".java").exists();
  }

  //////////////////////////////////////////////////
  // DISPATCHERS
  //////////////////////////////////////////////////
  @Override
  public void infer(final EObject model, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    if (model instanceof ExportModel exportModel) {
      _infer(exportModel, acceptor, isPreIndexingPhase);
    } else if (model != null) {
      _infer(model, acceptor, isPreIndexingPhase);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(model, acceptor, isPreIndexingPhase));
    }
  }

  private CharSequence doProfile(final InterfaceItem it, final ExportModel model, final EClass type) {
    if (it instanceof InterfaceExpression interfaceExpression) {
      return _doProfile(interfaceExpression, model, type);
    } else if (it instanceof InterfaceField interfaceField) {
      return _doProfile(interfaceField, model, type);
    } else if (it instanceof InterfaceNavigation interfaceNavigation) {
      return _doProfile(interfaceNavigation, model, type);
    } else if (it != null) {
      return _doProfile(it, model, type);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it, model, type));
    }
  }

  // CHECKSTYLE:CHECK-ON LambdaBodyLength
  // CHECKSTYLE:CONSTANTS-ON

}
