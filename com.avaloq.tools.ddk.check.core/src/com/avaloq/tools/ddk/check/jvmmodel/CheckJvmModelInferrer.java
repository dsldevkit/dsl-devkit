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
package com.avaloq.tools.ddk.check.jvmmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.common.types.JvmAnnotationReference;
import org.eclipse.xtext.common.types.JvmAnnotationType;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.EObjectDiagnosticImpl;
import org.eclipse.xtext.xbase.XFeatureCall;
import org.eclipse.xtext.xbase.XMemberFeatureCall;
import org.eclipse.xtext.xbase.XbaseFactory;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

import com.avaloq.tools.ddk.check.CheckConstants;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.Context;
import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.Implementation;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorExtensions;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming;
import com.avaloq.tools.ddk.check.generator.CheckPropertiesGenerator;
import com.avaloq.tools.ddk.check.resource.CheckLocationInFileProvider;
import com.avaloq.tools.ddk.check.runtime.configuration.ICheckConfigurationStoreService;
import com.avaloq.tools.ddk.check.runtime.issue.AbstractIssue;
import com.avaloq.tools.ddk.check.runtime.issue.DispatchingCheckImpl;
import com.avaloq.tools.ddk.check.runtime.issue.DispatchingCheckImpl.DiagnosticCollector;
import com.avaloq.tools.ddk.check.runtime.issue.SeverityKind;
import com.avaloq.tools.ddk.check.validation.IssueCodes;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * <p>Infers a JVM model from the source model.</p>
 *
 * <p>The JVM model should contain all elements that would appear in the Java code
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>
 */
@SuppressWarnings({"checkstyle:MethodName", "nls"})
public class CheckJvmModelInferrer extends AbstractModelInferrer {

  @Inject
  private TypesFactory typesFactory;

  @Inject
  private CheckLocationInFileProvider locationInFileProvider;

  @Inject
  private CheckGeneratorExtensions checkGeneratorExtensions;

  @Inject
  private CheckGeneratorNaming checkGeneratorNaming;

  @Inject
  private JvmTypesBuilder jvmTypesBuilder;

  // CHECKSTYLE:CONSTANTS-OFF the repeated literals are Java source fragments emitted by this generator, not nameable constants
  // CHECKSTYLE:CHECK-OFF LambdaBodyLength the model-inference closures mirror the Xtext JvmTypesBuilder API and are kept whole
  protected void _infer(final CheckCatalog catalog, final IJvmDeclaredTypeAcceptor acceptor, final boolean preIndexingPhase) {
    // The xbase automatic scoping mechanism (typeRef()) cannot find secondary classes in the same resource. It can
    // only find indexed resources (either in the JDT index or in the xtext index). However, we'll initialize the
    // JVM validator class before the resource gets indexed, so the JVM catalog class cannot be found yet when we
    // create the injection in the validator. Therefore, remember the class here directly, and set it directly
    // in the validator, completely bypassing any scoping.
    if (preIndexingPhase) {
      return;
    }
    final JvmGenericType catalogClass = jvmTypesBuilder.toClass(catalog, checkGeneratorNaming.qualifiedCatalogClassName(catalog));
    final JvmTypeReference issueCodeToLabelMapTypeRef = _typeReferenceBuilder.typeRef(ImmutableMap.class, _typeReferenceBuilder.typeRef(String.class), _typeReferenceBuilder.typeRef(String.class));
    acceptor.accept(catalogClass, it -> {
      final JvmTypeReference parentType = checkedTypeRef(catalog, AbstractIssue.class);
      if (parentType != null) {
        it.getSuperTypes().add(parentType);
      }
      Iterables.addAll(it.getAnnotations(), createAnnotation(checkedTypeRef(catalog, Singleton.class), it1 -> {
      }));
      jvmTypesBuilder.setDocumentation(it, "Issues for " + catalog.getName() + ".");
      Iterables.addAll(it.getMembers(), createInjectedField(catalog, "checkConfigurationStoreService", checkedTypeRef(catalog, ICheckConfigurationStoreService.class)));

      // Create map of issue code to label and associated getter
      it.getMembers().add(jvmTypesBuilder.toField(catalog, checkGeneratorNaming.issueCodeToLabelMapFieldName(), issueCodeToLabelMapTypeRef, it1 -> {
        it1.setStatic(true);
        it1.setFinal(true);
        // Get all issue codes and labels
        final Iterable<XIssueExpression> issues = checkGeneratorExtensions.checkAndImplementationIssues(catalog);
        // Use a TreeMap to eliminate duplicates,
        // and also to sort by qualified issue code name so autogenerated files are more readable and less prone to spurious ordering changes.
        // Do this when compiling the Check, to avoid discovering duplicates at runtime.
        final SortedMap<String, String> sortedUniqueQualifiedIssueCodeNamesAndLabels = new TreeMap<String, String>();
        for (final XIssueExpression issue : issues) {
          final String qualifiedIssueCodeName = checkGeneratorExtensions.qualifiedIssueCodeName(issue);
          final String issueLabel = StringEscapeUtils.escapeJava(checkGeneratorExtensions.issueLabel(issue));
          final String existingIssueLabel = sortedUniqueQualifiedIssueCodeNamesAndLabels.putIfAbsent(qualifiedIssueCodeName, issueLabel);
          if (null != existingIssueLabel && !Objects.equals(issueLabel, existingIssueLabel)) {
            // This qualified issue code name is already in the map, with a different label. Fail the build.
            throw new IllegalArgumentException("Multiple issues found with qualified issue code name: " + qualifiedIssueCodeName);
          }
        }
        jvmTypesBuilder.setInitializer(it1, appendable -> {
          final StringConcatenation builder = new StringConcatenation();
          builder.append(ImmutableMap.class.getSimpleName());
          builder.append(".<");
          builder.append(String.class.getSimpleName());
          builder.append(", ");
          builder.append(String.class.getSimpleName());
          builder.append(">builderWithExpectedSize(");
          builder.append(sortedUniqueQualifiedIssueCodeNamesAndLabels.entrySet().size());
          builder.append(")");
          builder.newLineIfNotEmpty();
          for (final Map.Entry<String, String> qualifiedIssueCodeNameAndLabel : sortedUniqueQualifiedIssueCodeNamesAndLabels.entrySet()) {
            builder.append("  ");
            builder.append(".put(");
            builder.append(qualifiedIssueCodeNameAndLabel.getKey(), "  ");
            builder.append(", \"");
            builder.append(qualifiedIssueCodeNameAndLabel.getValue(), "  ");
            builder.append("\")");
            builder.newLineIfNotEmpty();
          }
          builder.append("  ");
          builder.append(".build()");
          builder.newLine();
          appendable.append(builder);
        });
      }));
      it.getMembers().add(jvmTypesBuilder.toMethod(catalog, checkGeneratorNaming.fieldGetterName(checkGeneratorNaming.issueCodeToLabelMapFieldName()), issueCodeToLabelMapTypeRef, it1 -> {
        final StringConcatenation builder = new StringConcatenation();
        builder.append("Get map of issue code to label for ");
        builder.append(catalog.getName());
        builder.append(".");
        builder.newLineIfNotEmpty();
        builder.newLine();
        builder.append("@returns Map of issue code to label for ");
        builder.append(catalog.getName());
        builder.append(".");
        builder.newLineIfNotEmpty();
        jvmTypesBuilder.setDocumentation(it1, builder.toString());
        it1.setStatic(true);
        it1.setFinal(true);
        jvmTypesBuilder.setBody(it1, appendable -> appendable.append("return " + checkGeneratorNaming.issueCodeToLabelMapFieldName() + ";"));
      }));

      Iterables.addAll(it.getMembers(), IterableExtensions.filterNull(Iterables.concat(ListExtensions.map(catalog.getAllChecks(), c -> createIssue(catalog, c)))));
    });

    acceptor.accept(jvmTypesBuilder.toClass(catalog, checkGeneratorNaming.qualifiedValidatorClassName(catalog)), it -> {
      final JvmTypeReference parentType = checkedTypeRef(catalog, DispatchingCheckImpl.class);
      if (parentType != null) {
        it.getSuperTypes().add(parentType);
      }
      // Constructor will be added automatically.
      jvmTypesBuilder.setDocumentation(it, "Validator for " + catalog.getName() + ".");
      // Create catalog injections
      Iterables.addAll(it.getMembers(), createInjectedField(catalog, checkGeneratorNaming.catalogInstanceName(catalog), _typeReferenceBuilder.typeRef(catalogClass)));
      // Create fields
      Iterables.addAll(it.getMembers(), IterableExtensions.filterNull(ListExtensions.map(catalog.getMembers(), m -> jvmTypesBuilder.toField(m, m.getName(), m.getType(), it1 -> {
        jvmTypesBuilder.setInitializer(it1, m.getValue());
        jvmTypesBuilder.addAnnotations(it1, m.getAnnotations());
      }))));
      // Create catalog name function
      it.getMembers().add(jvmTypesBuilder.toMethod(catalog, "getQualifiedCatalogName", _typeReferenceBuilder.typeRef(String.class), it1 -> {
        jvmTypesBuilder.setBody(it1, appendable -> appendable.append("return \"" + catalog.getPackageName() + "." + catalog.getName() + "\";"));
      }));

      // Create getter for map of issue code to label
      it.getMembers().add(jvmTypesBuilder.toMethod(catalog, checkGeneratorNaming.fieldGetterName(checkGeneratorNaming.issueCodeToLabelMapFieldName()), issueCodeToLabelMapTypeRef, it1 -> {
        it1.setFinal(true);
        jvmTypesBuilder.setBody(it1, appendable -> appendable.append("return " + checkGeneratorNaming.catalogClassName(catalog) + "." + checkGeneratorNaming.fieldGetterName(checkGeneratorNaming.issueCodeToLabelMapFieldName()) + "();"));
      }));

      it.getMembers().add(createDispatcherMethod(catalog));

      // Create methods for contexts in checks
      final List<Check> checks = catalog.getChecks();
      final Iterable<Check> flattenedCategoryChecks = Iterables.concat(ListExtensions.map(catalog.getCategories(), cat -> cat.getChecks()));
      final Iterable<Check> allChecks = Iterables.concat(checks, flattenedCategoryChecks);
      Iterables.addAll(it.getMembers(), IterableExtensions.filterNull(Iterables.concat(IterableExtensions.map(allChecks, chk -> createCheck(chk)))));
      // Create methods for stand-alone context implementations
      Iterables.addAll(it.getMembers(), IterableExtensions.filterNull(ListExtensions.map(catalog.getImplementations(), impl -> createCheckMethod(impl.getContext()))));
    });
    acceptor.accept(jvmTypesBuilder.toClass(catalog, checkGeneratorNaming.qualifiedPreferenceInitializerClassName(catalog)), it -> {
      final JvmTypeReference parentType = checkedTypeRef(catalog, AbstractPreferenceInitializer.class);
      if (parentType != null) {
        it.getSuperTypes().add(parentType);
      }
      it.getMembers().add(jvmTypesBuilder.toField(catalog, "RUNTIME_NODE_NAME", _typeReferenceBuilder.typeRef(String.class), it1 -> {
        it1.setStatic(true);
        it1.setFinal(true);
        jvmTypesBuilder.setInitializer(it1, appendable -> appendable.append("\"" + checkGeneratorExtensions.bundleName(catalog) + "\""));
      }));
      Iterables.addAll(it.getMembers(), createFormalParameterFields(catalog));
      Iterables.addAll(it.getMembers(), createPreferenceInitializerMethods(catalog));
    });
  }
  // CHECKSTYLE:CHECK-ON LambdaBodyLength

  private JvmOperation createDispatcherMethod(final CheckCatalog catalog) {
    final JvmTypeReference objectBaseJavaTypeRef = checkedTypeRef(catalog, EObject.class);
    return jvmTypesBuilder.toMethod(catalog, "validate", _typeReferenceBuilder.typeRef("void"), it -> {
      it.setVisibility(JvmVisibility.PUBLIC);
      it.getParameters().add(jvmTypesBuilder.toParameter(catalog, "checkMode", checkedTypeRef(catalog, CheckMode.class)));
      it.getParameters().add(jvmTypesBuilder.toParameter(catalog, "object", objectBaseJavaTypeRef));
      it.getParameters().add(jvmTypesBuilder.toParameter(catalog, "diagnosticCollector", checkedTypeRef(catalog, DiagnosticCollector.class)));
      Iterables.addAll(it.getAnnotations(), createAnnotation(checkedTypeRef(catalog, Override.class), it1 -> {
      }));
      jvmTypesBuilder.setBody(it, out -> emitDispatcherMethodBody(out, catalog, objectBaseJavaTypeRef));
    });
  }

  private void emitDispatcherMethodBody(final ITreeAppendable out, final CheckCatalog catalog, final JvmTypeReference objectBaseJavaTypeRef) {
    /*
     * A catalog may contain both Check and Implementation objects,
     * which in turn may contain Context objects.
     * Categories may optionally be used for grouping checks, and
     * we can include categorized checks by using getAllChecks().
     * We only consider Context objects with a typed contextVariable.
     */
    final Iterable<Context> checkContexts = Iterables.concat(ListExtensions.map(catalog.getAllChecks(), chk -> chk.getContexts()));
    final Iterable<Context> implementationContexts = IterableExtensions.filterNull(ListExtensions.map(catalog.getImplementations(), impl -> impl.getContext()));
    final Iterable<Context> allContexts = IterableExtensions.filter(Iterables.concat(checkContexts, implementationContexts), ctx -> {
      JvmTypeReference type = null;
      if (ctx.getContextVariable() != null) {
        type = ctx.getContextVariable().getType();
      }
      return type != null;
    });

    /*
     * Contexts grouped by CheckType.
     * We use an OrderedMap for deterministic ordering of check type checks.
     * For Context objects we retain their order of appearance, apart from groupings.
     */
    final SortedMap<CheckType, List<Context>> contextsByCheckType = new TreeMap<CheckType, List<Context>>();
    for (final Context context : allContexts) {
      contextsByCheckType.compute(checkGeneratorExtensions.checkType(context), (final CheckType k, final List<Context> lst) -> lst != null ? lst : new ArrayList<Context>()).add(context);
    }

    final String baseTypeName = objectBaseJavaTypeRef.getQualifiedName();

    for (final Iterator<Map.Entry<CheckType, List<Context>>> iterator = contextsByCheckType.entrySet().iterator(); iterator.hasNext();) {
      final Map.Entry<CheckType, List<Context>> entry = iterator.next();
      final String checkType = "CheckType." + entry.getKey();

      out.append("if (checkMode.shouldCheck(" + checkType + ")) {");
      out.increaseIndentation();
      out.newLine();
      out.append("diagnosticCollector.setCurrentCheckType(" + checkType + ");");
      emitInstanceOfConditionals(out, entry.getValue(), catalog, baseTypeName); // with preceding newline for each
      out.decreaseIndentation();
      out.newLine();
      out.append("}");
      if (iterator.hasNext()) { // not at method body end
        out.newLine(); // separator between mode checks
      }
    }
  }

  private void emitInstanceOfConditionals(final ITreeAppendable out, final List<Context> contexts, final CheckCatalog catalog, final String baseTypeName) {
    /*
     * Contexts grouped by fully qualified variable type name,
     * otherwise in order of appearance.
     */
    final SortedMap<String, List<Context>> contextsByVarType = new TreeMap<String, List<Context>>();
    for (final Context context : contexts) {
      contextsByVarType.compute(context.getContextVariable().getType().getQualifiedName(), (final String k, final List<Context> lst) -> lst != null ? lst : new ArrayList<Context>()).add(context);
    }

    /* Ordering for context variable type checks. */
    final List<JvmTypeReference> contextVarTypes = ListExtensions.map(contexts, x -> x.getContextVariable().getType());
    final InstanceOfCheckOrderer.Forest forest = InstanceOfCheckOrderer.orderTypes(contextVarTypes);

    emitInstanceOfTree(out, forest, null, contextsByVarType, catalog, baseTypeName, 0);
  }

  private void emitInstanceOfTree(final ITreeAppendable out, final InstanceOfCheckOrderer.Forest forest, final String node, final Map<String, List<Context>> contextsByVarType, final CheckCatalog catalog, final String baseTypeName, final int level) {
    if (node != null) {
      String typeName = node;
      if (Objects.equals(typeName, baseTypeName)) {
        typeName = null;
      }
      final String varName;
      if (typeName == null) {
        varName = "object";
      } else {
        varName = "castObject" + (level > 1 ? Integer.toString(level) : "");
      }

      out.newLine();
      String prefix = "";
      if (typeName != null) {
        prefix = "if (object instanceof final " + typeName + " " + varName + ") ";
      }
      out.append(prefix + "{");
      out.increaseIndentation();

      final List<Context> contexts = contextsByVarType.get(node);
      for (final Context context : contexts) {
        emitCheckMethodCall(out, varName, context, catalog); // with preceding newline
      }
    }

    final Collection<String> subTypes = forest.getSubTypes(node);
    for (final String child : subTypes) {
      emitInstanceOfTree(out, forest, child, contextsByVarType, catalog, baseTypeName, level + 1);
    }

    if (node != null) {
      out.decreaseIndentation();
      out.newLine();
      out.append("}");
    }
  }

  private void emitCheckMethodCall(final ITreeAppendable out, final String varName, final Context context, final CheckCatalog catalog) {
    final String methodName = generateContextMethodName(context);
    final String jMethodName = toJavaLiteral(methodName);
    final String qMethodName = toJavaLiteral(catalog.getName(), methodName);

    out.newLine();
    final StringConcatenation builder = new StringConcatenation();
    builder.append("validate(");
    builder.append(jMethodName);
    builder.append(", ");
    builder.append(qMethodName);
    builder.append(", object,");
    builder.newLineIfNotEmpty();
    builder.append("         ");
    builder.append("() -> ");
    builder.append(methodName, "         ");
    builder.append("(");
    builder.append(varName, "         ");
    builder.append(", diagnosticCollector), diagnosticCollector);");
    out.append(builder);
  }

  private String toJavaLiteral(final String... strings) {
    return "\"" + Strings.convertToJavaString(String.join(".", strings)) + "\"";
  }

  private Iterable<JvmField> createInjectedField(final CheckCatalog context, final String fieldName, final JvmTypeReference type) {
    // Generate @Inject private typeName fieldName;
    if (type == null) {
      return Collections.emptyList();
    }
    final JvmField field = typesFactory.createJvmField();
    field.setSimpleName(fieldName);
    field.setVisibility(JvmVisibility.PRIVATE);
    field.setType(jvmTypesBuilder.cloneWithProxies(type));
    Iterables.addAll(field.getAnnotations(), createAnnotation(checkedTypeRef(context, Inject.class), it -> {
    }));
    return Collections.singleton(field);
  }

  private Iterable<JvmMember> createCheck(final Check chk) {
    // If we don't have FormalParameters, there's no need to do all this song and dance with inner classes.
    if (chk.getFormalParameters().isEmpty()) {
      return ListExtensions.map(chk.getContexts(), ctx -> createCheckMethod(ctx));
    } else {
      return createCheckWithParameters(chk);
    }
  }

  private Iterable<JvmMember> createCheckWithParameters(final Check chk) {
    // Generate an inner class, plus a field holding an instance of that class.
    // Put the formal parameters into that class as fields.
    // For each check context, generate a run method.
    // For each check context, generate an annotated check method outside to call the appropriate run method.
    // This is the only way I found to make those formal parameters visible in the check constraints...
    // The generated Java looks a bit strange, because we suppress actually generating these fields, as we
    // don't use them; we only need them for scoping based on this inferred model.
    final List<JvmMember> newMembers = Lists.newArrayList();
    // First the class
    final JvmGenericType checkClass = jvmTypesBuilder.toClass(chk, StringExtensions.toFirstUpper(chk.getName()) + "Class", it -> {
      it.getSuperTypes().add(_typeReferenceBuilder.typeRef(Object.class));
      it.setVisibility(JvmVisibility.PRIVATE);
      // Add a fields for the parameters, so that they can be linked. We suppress generation of these fields in the generator,
      // and replace all references by calls to the getter function in the catalog.
      Iterables.addAll(it.getMembers(), IterableExtensions.map(IterableExtensions.filter(chk.getFormalParameters(), f -> f.getType() != null && f.getName() != null), f -> jvmTypesBuilder.toField(f, f.getName(), f.getType(), it1 -> it1.setFinal(true))));
    });
    newMembers.add(checkClass);
    newMembers.add(jvmTypesBuilder.toField(chk, StringExtensions.toFirstLower(chk.getName()) + "Impl", _typeReferenceBuilder.typeRef(checkClass), it -> {
      jvmTypesBuilder.setInitializer(it, appendable -> appendable.append("new " + checkClass.getSimpleName() + "()"));
    }));
    Iterables.addAll(newMembers, IterableExtensions.filterNull(ListExtensions.map(chk.getContexts(), ctx -> createCheckCaller(ctx, chk))));
    // If we create these above in the class initializer, the types of the context variables somehow are not resolved yet.
    Iterables.addAll(checkClass.getMembers(), IterableExtensions.filterNull(ListExtensions.map(chk.getContexts(), ctx -> createCheckExecution(ctx))));
    return newMembers;
  }

  private JvmOperation createCheckExecution(final Context ctx) {
    if (ctx == null || ctx.getContextVariable() == null) {
      return null;
    }
    final JvmTypeReference contextVariableType = ctx.getContextVariable().getType();
    String simpleName = null;
    if (contextVariableType != null) {
      simpleName = contextVariableType.getSimpleName();
    }
    final String functionName = "run" + StringExtensions.toFirstUpper(simpleName);
    // CPD-OFF — migrated Xtend generator code, kept faithful
    return jvmTypesBuilder.toMethod(ctx, functionName, _typeReferenceBuilder.typeRef("void"), it -> {
      final String parameterName = ctx.getContextVariable().getName() == null ? CheckConstants.IT : ctx.getContextVariable().getName();
      it.getParameters().add(jvmTypesBuilder.toParameter(ctx, parameterName, ctx.getContextVariable().getType()));
      it.getParameters().add(jvmTypesBuilder.toParameter(ctx, "diagnosticCollector", checkedTypeRef(ctx, DiagnosticCollector.class)));
      jvmTypesBuilder.setBody(it, ctx.getConstraint());
    });
    // CPD-ON
  }

  private Iterable<JvmAnnotationReference> createCheckAnnotation(final Context ctx) {
    final JvmTypeReference checkTypeTypeRef = checkedTypeRef(ctx, CheckType.class);
    if (checkTypeTypeRef == null) {
      return Collections.emptyList();
    }
    final XFeatureCall featureCall = XbaseFactory.eINSTANCE.createXFeatureCall();
    featureCall.setFeature(checkTypeTypeRef.getType());
    featureCall.setTypeLiteral(true);
    final XMemberFeatureCall memberCall = XbaseFactory.eINSTANCE.createXMemberFeatureCall();
    memberCall.setMemberCallTarget(featureCall);
    // The grammar doesn't use the CheckType constants directly...
    String name = checkGeneratorExtensions.checkTypeQName(ctx);
    final int i = name.lastIndexOf('.');
    if (i >= 0) {
      name = name.substring(i + 1);
    }
    memberCall.setFeature(IterableExtensions.head(((JvmDeclaredType) checkTypeTypeRef.getType()).findAllFeaturesByName(name)));

    // memberCall needs to belong to a resource.
    // We add it as a separate model to the context's resource.
    ctx.eResource().getContents().add(memberCall);

    return createAnnotation(checkedTypeRef(ctx, org.eclipse.xtext.validation.Check.class), it -> {
      it.getExplicitValues().add(jvmTypesBuilder.toJvmAnnotationValue(memberCall));
    });
  }

  private JvmOperation createCheckCaller(final Context ctx, final Check chk) {
    if (ctx == null || ctx.getContextVariable() == null) {
      return null;
    }
    final JvmTypeReference contextVariableType = ctx.getContextVariable().getType();
    String simpleName = null;
    if (contextVariableType != null) {
      simpleName = contextVariableType.getSimpleName();
    }
    final String functionName = StringExtensions.toFirstLower(chk.getName()) + simpleName;
    // To make the formal parameter visible, we have to generate quite a bit... I see no way to get the XVariableDeclaration for them
    // into the XBlockExpression of ctx.constraint. Just copying them doesn't work; modifies the source model!
    // Therefore, we generate something new: each check becomes a local class

    return jvmTypesBuilder.toMethod(ctx, functionName, _typeReferenceBuilder.typeRef("void"), it -> {
      it.getParameters().add(jvmTypesBuilder.toParameter(ctx, "context", ctx.getContextVariable().getType()));
      it.getParameters().add(jvmTypesBuilder.toParameter(ctx, "diagnosticCollector", checkedTypeRef(ctx, DiagnosticCollector.class)));
      Iterables.addAll(it.getAnnotations(), createCheckAnnotation(ctx));
      jvmTypesBuilder.setDocumentation(it, functionName + "."); // Well, that's not very helpful, but it is what the old compiler did...
      jvmTypesBuilder.setBody(it, appendable -> {
        final JvmTypeReference innerContextVariableType = ctx.getContextVariable().getType();
        String innerSimpleName = null;
        if (innerContextVariableType != null) {
          innerSimpleName = innerContextVariableType.getSimpleName();
        }
        appendable.append(StringExtensions.toFirstLower(chk.getName()) + "Impl" + ".run" + StringExtensions.toFirstUpper(innerSimpleName) + "(context, diagnosticCollector);");
      });
    });
  }

  private JvmOperation createCheckMethod(final Context ctx) {
    // Simple case for contexts of checks that do not have formal parameters. No need to generate nested classes for these.
    if (ctx == null || ctx.getContextVariable() == null) {
      return null;
    }
    final String functionName = generateContextMethodName(ctx);

    return jvmTypesBuilder.toMethod(ctx, functionName, _typeReferenceBuilder.typeRef("void"), it -> {
      final String parameterName = ctx.getContextVariable().getName() == null ? CheckConstants.IT : ctx.getContextVariable().getName();
      it.getParameters().add(jvmTypesBuilder.toParameter(ctx, parameterName, ctx.getContextVariable().getType()));
      it.getParameters().add(jvmTypesBuilder.toParameter(ctx, "diagnosticCollector", checkedTypeRef(ctx, DiagnosticCollector.class)));
      Iterables.addAll(it.getAnnotations(), createCheckAnnotation(ctx));
      jvmTypesBuilder.setDocumentation(it, functionName + "."); // Well, that's not very helpful, but it is what the old compiler did...
      jvmTypesBuilder.setBody(it, ctx.getConstraint());
    });
  }

  private String generateContextMethodName(final Context ctx) {
    final EObject container = ctx.eContainer();
    final String baseName;
    if (container instanceof Check check) {
      baseName = check.getName();
    } else if (container instanceof Implementation implementation) {
      baseName = implementation.getName();
    } else {
      baseName = null;
    }
    final JvmTypeReference contextVariableType = ctx.getContextVariable().getType();
    String simpleName = null;
    if (contextVariableType != null) {
      simpleName = contextVariableType.getSimpleName();
    }
    return StringExtensions.toFirstLower(baseName) + simpleName;
  }

  // CheckCatalog

  // CHECKSTYLE:CHECK-OFF LambdaBodyLength the model-inference closures mirror the Xtext JvmTypesBuilder API and are kept whole
  private Iterable<JvmMember> createIssue(final CheckCatalog catalog, final Check check) {
    final List<JvmMember> members = Lists.newArrayList();
    for (final FormalParameter parameter : check.getFormalParameters()) {
      final JvmTypeReference returnType = parameter.getType();
      if (returnType != null && !returnType.eIsProxy()) {
        final String returnName = returnType.getQualifiedName();
        final String operation;
        if (returnName != null) {
          operation = switch (returnName) {
            case "java.lang.Boolean" -> "getBoolean";
            case "boolean" -> "getBoolean";
            case "java.lang.Integer" -> "getInt";
            case "int" -> "getInt";
            case "java.util.List<java.lang.String>" -> "getStrings";
            case "java.util.List<java.lang.Boolean>" -> "getBooleans";
            case "java.util.List<java.lang.Integer>" -> "getIntegers";
            default -> "getString";
          };
        } else {
          operation = "getString";
        }
        final String parameterKey = CheckPropertiesGenerator.parameterKey(parameter, check);
        String defaultName = "null";
        if (parameter.getRight() != null) {
          defaultName = CheckGeneratorExtensions.splitCamelCase(checkGeneratorNaming.formalParameterGetterName(parameter)).toUpperCase() + "_DEFAULT";
          // Is generated into the PreferenceInitializer. Actually, since we do have it in the initializer, passing it here again
          // as default value is just a safety measure if something went wrong and the property shouldn't be set.
        }
        final String javaDefaultValue = checkGeneratorNaming.preferenceInitializerClassName(catalog) + "." + defaultName;
        members.add(jvmTypesBuilder.toMethod(parameter, checkGeneratorNaming.formalParameterGetterName(parameter), returnType, it -> {
          final StringConcatenation builder = new StringConcatenation();
          builder.append("Gets the run-time value of formal parameter <em>");
          builder.append(parameter.getName());
          builder.append("</em>. The value");
          builder.newLineIfNotEmpty();
          builder.append("returned is either the default as defined in the check definition, or the");
          builder.newLine();
          builder.append("configured value, if existing.");
          builder.newLine();
          builder.newLine();
          builder.append("@param context");
          builder.newLine();
          builder.append("           ");
          builder.append("the context object used to determine the current project in");
          builder.newLine();
          builder.append("           ");
          builder.append("order to check if a configured value exists in a project scope");
          builder.newLine();
          builder.append("@return the run-time value of <em>");
          builder.append(parameter.getName());
          builder.append("</em>");
          jvmTypesBuilder.setDocumentation(it, builder.toString());
          final JvmTypeReference eObjectTypeRef = checkedTypeRef(parameter, EObject.class);
          if (eObjectTypeRef != null) {
            it.getParameters().add(jvmTypesBuilder.toParameter(parameter, "context", eObjectTypeRef));
          }
          jvmTypesBuilder.setBody(it, appendable -> appendable.append("return checkConfigurationStoreService.getCheckConfigurationStore(context)." + operation + "(\"" + parameterKey + "\", " + javaDefaultValue + ");"));
        }));
      } // end if
    } // end for
    members.add(jvmTypesBuilder.toMethod(check, "get" + StringExtensions.toFirstUpper(check.getName()) + "Message", _typeReferenceBuilder.typeRef(String.class), it -> {
      jvmTypesBuilder.setDocumentation(it, CheckJvmModelInferrerUtil.GET_MESSAGE_DOCUMENTATION);
      // Generate one parameter "Object... bindings"
      it.setVarArgs(true);
      it.getParameters().add(jvmTypesBuilder.toParameter(check, "bindings", jvmTypesBuilder.addArrayTypeDimension(_typeReferenceBuilder.typeRef(Object.class))));
      jvmTypesBuilder.setBody(it, appendable -> appendable.append("return org.eclipse.osgi.util.NLS.bind(\"" + Strings.convertToJavaString(check.getMessage()) + "\", bindings);"));
      // TODO (minor): how to get NLS into the imports?
    }));
    final JvmTypeReference severityType = checkedTypeRef(check, SeverityKind.class);
    if (severityType != null) {
      members.add(jvmTypesBuilder.toMethod(check, "get" + StringExtensions.toFirstUpper(check.getName()) + "SeverityKind", severityType, it -> {
        final StringConcatenation builder = new StringConcatenation();
        builder.append("Gets the {@link SeverityKind severity kind} of check");
        builder.newLine();
        builder.append("<em>");
        builder.append(check.getLabel());
        builder.append("</em>. The severity kind returned is either the");
        builder.newLineIfNotEmpty();
        builder.append("default ({@code ");
        builder.append(check.getDefaultSeverity().name());
        builder.append("}), as is set in the check definition, or the");
        builder.newLineIfNotEmpty();
        builder.append("configured value, if existing.");
        builder.newLine();
        builder.newLine();
        builder.append("@param context");
        builder.newLine();
        builder.append("         ");
        builder.append("the context object used to determine the current project in");
        builder.newLine();
        builder.append("         ");
        builder.append("order to check if a configured value exists in a project scope");
        builder.newLine();
        builder.append("@return the severity kind of this check: returns the default (");
        builder.append(check.getDefaultSeverity().name());
        builder.append(") if");
        builder.newLineIfNotEmpty();
        builder.append("        ");
        builder.append("no configuration for this check was found, else the configured");
        builder.newLine();
        builder.append("        ");
        builder.append("value looked up in the configuration store");
        jvmTypesBuilder.setDocumentation(it, builder.toString());
        final JvmTypeReference eObjectTypeRef = checkedTypeRef(check, EObject.class);
        if (eObjectTypeRef != null) {
          it.getParameters().add(jvmTypesBuilder.toParameter(check, "context", eObjectTypeRef));
        }
        jvmTypesBuilder.setBody(it, appendable -> {
          final StringConcatenation bodyBuilder = new StringConcatenation();
          bodyBuilder.append("final int result = checkConfigurationStoreService.getCheckConfigurationStore(context).getInt(\"");
          bodyBuilder.append(CheckPropertiesGenerator.checkSeverityKey(check));
          bodyBuilder.append("\", ");
          bodyBuilder.append(check.getDefaultSeverity().getValue());
          bodyBuilder.append(");");
          bodyBuilder.newLineIfNotEmpty();
          bodyBuilder.append("return SeverityKind.values()[result];");
          appendable.append(bodyBuilder);
        });
      }));
    }
    return members;
  }
  // CHECKSTYLE:CHECK-ON LambdaBodyLength

  // PreferenceInitializer.

  private Iterable<JvmMember> createFormalParameterFields(final CheckCatalog catalog) {
    // For each formal parameter, create a public static final field with a unique name derived from the formal parameter and
    // set it to its right-hand side expression. We let Java evaluate this!
    final List<Check> checks = catalog.getChecks();
    final Iterable<Check> flattenedCategoryChecks = Iterables.concat(ListExtensions.map(catalog.getCategories(), cat -> cat.getChecks()));
    final Iterable<Check> allChecks = Iterables.concat(checks, flattenedCategoryChecks);
    final List<JvmMember> result = Lists.newArrayList();
    for (final Check c : allChecks) {
      for (final FormalParameter parameter : c.getFormalParameters()) {
        if (parameter.getType() != null && parameter.getRight() != null) {
          final String defaultName = CheckGeneratorExtensions.splitCamelCase(checkGeneratorNaming.formalParameterGetterName(parameter)).toUpperCase() + "_DEFAULT";
          result.add(jvmTypesBuilder.toField(parameter, defaultName, parameter.getType(), it -> {
            it.setVisibility(JvmVisibility.PUBLIC);
            it.setFinal(true);
            it.setStatic(true);
            jvmTypesBuilder.setInitializer(it, parameter.getRight());
          }));
        }
      }
    }
    return result;
  }

  // CHECKSTYLE:CHECK-OFF LambdaBodyLength the model-inference closures mirror the Xtext JvmTypesBuilder API and are kept whole
  private Iterable<JvmMember> createPreferenceInitializerMethods(final CheckCatalog catalog) {
    final JvmTypeReference prefStore = checkedTypeRef(catalog, IEclipsePreferences.class);
    final List<JvmMember> result = Lists.newArrayList();

    if (prefStore != null) {
      result.add(jvmTypesBuilder.toMethod(catalog, "initializeDefaultPreferences", _typeReferenceBuilder.typeRef("void"), it -> {
        Iterables.addAll(it.getAnnotations(), createAnnotation(checkedTypeRef(catalog, Override.class), it1 -> {
        }));
        it.setVisibility(JvmVisibility.PUBLIC);
        jvmTypesBuilder.setBody(it, appendable -> appendable.append("IEclipsePreferences preferences = org.eclipse.core.runtime.preferences.InstanceScope.INSTANCE.getNode(RUNTIME_NODE_NAME);\n\ninitializeSeverities(preferences);\ninitializeFormalParameters(preferences);"));
      }));
      final List<Check> checks = catalog.getChecks();
      final Iterable<Check> flattenedCategoryChecks = Iterables.concat(ListExtensions.map(catalog.getCategories(), cat -> cat.getChecks()));
      final Iterable<Check> allChecks = Iterables.concat(checks, flattenedCategoryChecks);
      result.add(jvmTypesBuilder.toMethod(catalog, "initializeSeverities", _typeReferenceBuilder.typeRef("void"), it -> {
        it.setVisibility(JvmVisibility.PRIVATE);
        it.getParameters().add(jvmTypesBuilder.toParameter(catalog, "preferences", prefStore));
        jvmTypesBuilder.setBody(it, appendable -> {
          final StringConcatenation builder = new StringConcatenation();
          for (final Check c : allChecks) {
            builder.newLineIfNotEmpty();
            builder.append("preferences.putInt(\"");
            builder.append(CheckPropertiesGenerator.checkSeverityKey(c));
            builder.append("\", ");
            builder.append(c.getDefaultSeverity().getValue());
            builder.append(");");
            builder.newLineIfNotEmpty();
          }
          appendable.append(builder);
        });
      }));
      result.add(jvmTypesBuilder.toMethod(catalog, "initializeFormalParameters", _typeReferenceBuilder.typeRef("void"), it -> {
        it.setVisibility(JvmVisibility.PRIVATE);
        it.getParameters().add(jvmTypesBuilder.toParameter(catalog, "preferences", jvmTypesBuilder.cloneWithProxies(prefStore)));
        jvmTypesBuilder.setBody(it, appendable -> {
          for (final Check c : allChecks) {
            for (final FormalParameter parameter : c.getFormalParameters()) {
              if (parameter.getRight() != null) {
                final String key = CheckPropertiesGenerator.parameterKey(parameter, c);
                final String defaultFieldName = CheckGeneratorExtensions.splitCamelCase(checkGeneratorNaming.formalParameterGetterName(parameter)).toUpperCase() + "_DEFAULT";
                final JvmTypeReference jvmType = parameter.getType();
                final String typeName = jvmType.getQualifiedName();
                if (typeName != null && typeName.startsWith("java.util.List<")) {
                  // Marshal lists.
                  final List<JvmTypeReference> args = ((JvmParameterizedTypeReference) jvmType).getArguments();
                  if (args != null && args.size() == 1) {
                    final String baseTypeName = IterableExtensions.head(args).getSimpleName();
                    final StringConcatenation builder = new StringConcatenation();
                    builder.append("preferences.put(\"");
                    builder.append(key);
                    builder.append("\", com.avaloq.tools.ddk.check.runtime.configuration.CheckPreferencesHelper.marshal");
                    builder.append(baseTypeName);
                    builder.append("s(");
                    builder.append(defaultFieldName);
                    builder.append("));");
                    builder.newLineIfNotEmpty();
                    appendable.append(builder);
                  } else {
                    final StringConcatenation builder = new StringConcatenation();
                    builder.append("// Found ");
                    builder.append(key);
                    builder.append(" with ");
                    builder.append(typeName);
                    builder.newLineIfNotEmpty();
                    appendable.append(builder);
                  }
                } else {
                  final String operation;
                  if (typeName != null) {
                    operation = switch (typeName) {
                      case "java.lang.Boolean" -> "putBoolean";
                      case "boolean" -> "putBoolean";
                      case "java.lang.Integer" -> "putInt";
                      case "int" -> "putInt";
                      default -> "put";
                    };
                  } else {
                    operation = "put";
                  }
                  final StringConcatenation builder = new StringConcatenation();
                  builder.append("preferences.");
                  builder.append(operation);
                  builder.append("(\"");
                  builder.append(key);
                  builder.append("\", ");
                  builder.append(defaultFieldName);
                  builder.append(");");
                  builder.newLineIfNotEmpty();
                  appendable.append(builder);
                }
              }
            }
          }
        });
      }));
    }
    return result;
  }
  // CHECKSTYLE:CHECK-ON LambdaBodyLength
  // CHECKSTYLE:CONSTANTS-ON

  private Iterable<JvmAnnotationReference> createAnnotation(final JvmTypeReference typeRef, final Procedure1<JvmAnnotationReference> initializer) {
    if (typeRef == null) {
      return Collections.emptyList();
    }

    final JvmAnnotationReference annotation = typesFactory.createJvmAnnotationReference();
    annotation.setAnnotation((JvmAnnotationType) typeRef.getType());
    Objects.requireNonNull(initializer, "Initializer is null").apply(annotation);

    return Collections.singletonList(annotation);
  }

  // Error handling etc.

  private void createError(final String message, final EObject context, final EStructuralFeature feature) {
    final Resource rsc = context.eResource();
    if (rsc != null) {
      EStructuralFeature f = feature;
      if (f == null) {
        f = locationInFileProvider.getIdentifierFeature(context);
      }
      rsc.getErrors().add(new EObjectDiagnosticImpl(Severity.ERROR, IssueCodes.INFERRER_ERROR, "Check compiler: " + message, context, f, -1, null));
    }
  }

  private void createTypeNotFoundError(final String name, final EObject context) {
    createError("Type " + name + " not found; check project setup (missing required bundle?)", context, null);
  }

  private JvmTypeReference checkedTypeRef(final EObject context, final Class<?> clazz) {
    if (clazz == null) {
      createTypeNotFoundError("<unknown>", context);
      return null;
    }
    final JvmTypeReference result = _typeReferenceBuilder.typeRef(clazz);
    if (result == null || result.getType() == null) {
      createTypeNotFoundError(clazz.getName(), context);
      return null;
    }
    return result;
  }

  @Override
  public void infer(final EObject catalog, final IJvmDeclaredTypeAcceptor acceptor, final boolean preIndexingPhase) {
    if (catalog instanceof CheckCatalog checkCatalog) {
      _infer(checkCatalog, acceptor, preIndexingPhase);
    } else if (catalog != null) {
      _infer(catalog, acceptor, preIndexingPhase);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.asList(catalog, acceptor, preIndexingPhase).toString());
    }
  }
}
