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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
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
import com.avaloq.tools.ddk.check.check.Category;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.Context;
import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.Implementation;
import com.avaloq.tools.ddk.check.check.Member;
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
@SuppressWarnings({"checkstyle:MethodName"})
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

  // CHECKSTYLE:CONSTANTS-OFF
  // CHECKSTYLE:CHECK-OFF LambdaBodyLength
  protected void _infer(final CheckCatalog catalog, final IJvmDeclaredTypeAcceptor acceptor, final boolean preIndexingPhase) {
    // The xbase automatic scoping mechanism (typeRef()) cannot find secondary classes in the same resource. It can
    // only find indexed resources (either in the JDT index or in the xtext index). However, we'll initialize the
    // JVM validator class before the resource gets indexed, so the JVM catalog class cannot be found yet when we
    // create the injection in the validator. Therefore, remember the class here directly, and set it directly
    // in the validator, completely bypassing any scoping.
    if (preIndexingPhase) {
      return;
    }
    JvmGenericType catalogClass = jvmTypesBuilder.toClass(catalog, checkGeneratorNaming.qualifiedCatalogClassName(catalog));
    JvmTypeReference issueCodeToLabelMapTypeRef = _typeReferenceBuilder.typeRef(ImmutableMap.class, _typeReferenceBuilder.typeRef(String.class), _typeReferenceBuilder.typeRef(String.class));
    acceptor.<JvmGenericType>accept(catalogClass, (JvmGenericType it) -> {
      JvmTypeReference parentType = checkedTypeRef(catalog, AbstractIssue.class);
      if (parentType != null) {
        it.getSuperTypes().add(parentType);
      }
      Iterables.addAll(it.getAnnotations(), createAnnotation(checkedTypeRef(catalog, Singleton.class), (JvmAnnotationReference it1) -> {
      }));
      jvmTypesBuilder.setDocumentation(it, "Issues for " + catalog.getName() + ".");
      Iterables.addAll(it.getMembers(), createInjectedField(catalog, "checkConfigurationStoreService", checkedTypeRef(catalog, ICheckConfigurationStoreService.class)));

      // Create map of issue code to label and associated getter
      it.getMembers().add(jvmTypesBuilder.toField(catalog, checkGeneratorNaming.issueCodeToLabelMapFieldName(), issueCodeToLabelMapTypeRef, (JvmField it1) -> {
        it1.setStatic(true);
        it1.setFinal(true);
        // Get all issue codes and labels
        Iterable<XIssueExpression> issues = checkGeneratorExtensions.checkAndImplementationIssues(catalog);
        // Use a TreeMap to eliminate duplicates,
        // and also to sort by qualified issue code name so autogenerated files are more readable and less prone to spurious ordering changes.
        // Do this when compiling the Check, to avoid discovering duplicates at runtime.
        Map<String, String> sortedUniqueQualifiedIssueCodeNamesAndLabels = new TreeMap<String, String>();
        for (XIssueExpression issue : issues) {
          String qualifiedIssueCodeName = checkGeneratorExtensions.qualifiedIssueCodeName(issue);
          String issueLabel = StringEscapeUtils.escapeJava(checkGeneratorExtensions.issueLabel(issue));
          String existingIssueLabel = sortedUniqueQualifiedIssueCodeNamesAndLabels.putIfAbsent(qualifiedIssueCodeName, issueLabel);
          if (null != existingIssueLabel && !Objects.equals(issueLabel, existingIssueLabel)) {
            // This qualified issue code name is already in the map, with a different label. Fail the build.
            throw new IllegalArgumentException("Multiple issues found with qualified issue code name: " + qualifiedIssueCodeName);
          }
        }
        jvmTypesBuilder.setInitializer(it1, (ITreeAppendable appendable) -> {
          StringBuilder sb = new StringBuilder(512);
          sb.append(ImmutableMap.class.getSimpleName()).append(".<").append(String.class.getSimpleName()).append(", ").append(String.class.getSimpleName()).append(">builderWithExpectedSize(").append(sortedUniqueQualifiedIssueCodeNamesAndLabels.entrySet().size()).append(")\n");
          for (Map.Entry<String, String> qualifiedIssueCodeNameAndLabel : sortedUniqueQualifiedIssueCodeNamesAndLabels.entrySet()) {
            sb.append("  .put(").append(qualifiedIssueCodeNameAndLabel.getKey()).append(", \"").append(qualifiedIssueCodeNameAndLabel.getValue()).append("\")\n");
          }
          sb.append("  .build()\n");
          appendable.append(sb.toString());
        });
      }));
      it.getMembers().add(jvmTypesBuilder.toMethod(catalog, checkGeneratorNaming.fieldGetterName(checkGeneratorNaming.issueCodeToLabelMapFieldName()), issueCodeToLabelMapTypeRef, (JvmOperation it1) -> {
        jvmTypesBuilder.setDocumentation(it1, "Get map of issue code to label for " + catalog.getName() + ".\n\n@returns Map of issue code to label for " + catalog.getName() + ".\n");
        it1.setStatic(true);
        it1.setFinal(true);
        jvmTypesBuilder.setBody(it1, (ITreeAppendable appendable) -> {
          appendable.append("return " + checkGeneratorNaming.issueCodeToLabelMapFieldName() + ";");
        });
      }));

      Iterables.addAll(it.getMembers(), IterableExtensions.<JvmMember>filterNull(Iterables.<JvmMember>concat(ListExtensions.<Check, Iterable<JvmMember>>map(catalog.getAllChecks(), (Check c) -> createIssue(catalog, c)))));
    });

    acceptor.<JvmGenericType>accept(jvmTypesBuilder.toClass(catalog, checkGeneratorNaming.qualifiedValidatorClassName(catalog)), (JvmGenericType it) -> {
      JvmTypeReference parentType = checkedTypeRef(catalog, DispatchingCheckImpl.class);
      if (parentType != null) {
        it.getSuperTypes().add(parentType);
      }
      // Constructor will be added automatically.
      jvmTypesBuilder.setDocumentation(it, "Validator for " + catalog.getName() + ".");
      // Create catalog injections
      Iterables.addAll(it.getMembers(), createInjectedField(catalog, checkGeneratorNaming.catalogInstanceName(catalog), _typeReferenceBuilder.typeRef(catalogClass)));
      // Create fields
      Iterables.addAll(it.getMembers(), ListExtensions.<Member, JvmField>map(catalog.getMembers(), (Member m) -> jvmTypesBuilder.toField(m, m.getName(), m.getType(), (JvmField it1) -> {
        jvmTypesBuilder.setInitializer(it1, m.getValue());
        jvmTypesBuilder.addAnnotations(it1, m.getAnnotations());
      })));
      // Create catalog name function
      it.getMembers().add(jvmTypesBuilder.toMethod(catalog, "getQualifiedCatalogName", _typeReferenceBuilder.typeRef(String.class), (JvmOperation it1) -> {
        jvmTypesBuilder.setBody(it1, (ITreeAppendable appendable) -> {
          appendable.append("return \"" + catalog.getPackageName() + "." + catalog.getName() + "\";");
        });
      }));

      // Create getter for map of issue code to label
      it.getMembers().add(jvmTypesBuilder.toMethod(catalog, checkGeneratorNaming.fieldGetterName(checkGeneratorNaming.issueCodeToLabelMapFieldName()), issueCodeToLabelMapTypeRef, (JvmOperation it1) -> {
        it1.setFinal(true);
        jvmTypesBuilder.setBody(it1, (ITreeAppendable appendable) -> {
          appendable.append("return " + checkGeneratorNaming.catalogClassName(catalog) + "." + checkGeneratorNaming.fieldGetterName(checkGeneratorNaming.issueCodeToLabelMapFieldName()) + "();");
        });
      }));

      it.getMembers().add(createDispatcherMethod(catalog));

      // Create methods for contexts in checks
      EList<Check> checks = catalog.getChecks();
      Iterable<Check> flattenedCatChecks = Iterables.<Check>concat(ListExtensions.<Category, EList<Check>>map(catalog.getCategories(), (Category cat) -> cat.getChecks()));
      Iterable<Check> allChecks = Iterables.<Check>concat(checks, flattenedCatChecks);
      Iterables.addAll(it.getMembers(), Iterables.<JvmMember>concat(IterableExtensions.<Check, Iterable<JvmMember>>map(allChecks, (Check chk) -> createCheck(chk))));
      // Create methods for stand-alone context implementations
      Iterables.addAll(it.getMembers(), IterableExtensions.<JvmOperation>filterNull(ListExtensions.<Implementation, JvmOperation>map(catalog.getImplementations(), (Implementation impl) -> createCheckMethod(impl.getContext()))));
    });
    acceptor.<JvmGenericType>accept(jvmTypesBuilder.toClass(catalog, checkGeneratorNaming.qualifiedPreferenceInitializerClassName(catalog)), (JvmGenericType it) -> {
      JvmTypeReference parentType = checkedTypeRef(catalog, AbstractPreferenceInitializer.class);
      if (parentType != null) {
        it.getSuperTypes().add(parentType);
      }
      it.getMembers().add(jvmTypesBuilder.toField(catalog, "RUNTIME_NODE_NAME", _typeReferenceBuilder.typeRef(String.class), (JvmField it1) -> {
        it1.setStatic(true);
        it1.setFinal(true);
        jvmTypesBuilder.setInitializer(it1, (ITreeAppendable appendable) -> {
          appendable.append("\"" + checkGeneratorExtensions.bundleName(catalog) + "\"");
        });
      }));
      Iterables.addAll(it.getMembers(), createFormalParameterFields(catalog));
      Iterables.addAll(it.getMembers(), createPreferenceInitializerMethods(catalog));
    });
  }
  // CHECKSTYLE:CHECK-ON LambdaBodyLength

  private JvmOperation createDispatcherMethod(final CheckCatalog catalog) {
    JvmTypeReference objectBaseJavaTypeRef = checkedTypeRef(catalog, EObject.class);
    return jvmTypesBuilder.toMethod(catalog, "validate", _typeReferenceBuilder.typeRef("void"), (JvmOperation it) -> {
      it.setVisibility(JvmVisibility.PUBLIC);
      it.getParameters().add(jvmTypesBuilder.toParameter(catalog, "checkMode", checkedTypeRef(catalog, CheckMode.class)));
      it.getParameters().add(jvmTypesBuilder.toParameter(catalog, "object", objectBaseJavaTypeRef));
      it.getParameters().add(jvmTypesBuilder.toParameter(catalog, "diagnosticCollector", checkedTypeRef(catalog, DiagnosticCollector.class)));
      Iterables.addAll(it.getAnnotations(), createAnnotation(checkedTypeRef(catalog, Override.class), (JvmAnnotationReference it1) -> {
      }));
      jvmTypesBuilder.setBody(it, (ITreeAppendable out) -> {
        emitDispatcherMethodBody(out, catalog, objectBaseJavaTypeRef);
      });
    });
  }

  private void emitDispatcherMethodBody(final ITreeAppendable out, final CheckCatalog catalog, final JvmTypeReference objectBaseJavaTypeRef) {
    /* A catalog may contain both Check and Implementation objects,
     * which in turn may contain Context objects.
     * Categories may optionally be used for grouping checks, and
     * we can include categorized checks by using getAllChecks().
     * We only consider Context objects with a typed contextVariable.
     */
    Iterable<Context> checkContexts = Iterables.<Context>concat(ListExtensions.<Check, EList<Context>>map(catalog.getAllChecks(), (Check chk) -> chk.getContexts()));
    Iterable<Context> implContexts = IterableExtensions.<Context>filterNull(ListExtensions.<Implementation, Context>map(catalog.getImplementations(), (Implementation impl) -> impl.getContext()));
    Iterable<Context> allContexts = IterableExtensions.<Context>filter(Iterables.<Context>concat(checkContexts, implContexts), (Context ctx) -> {
      JvmTypeReference type = null;
      if (ctx.getContextVariable() != null) {
        type = ctx.getContextVariable().getType();
      }
      return type != null;
    });

    /* Contexts grouped by CheckType.
     * We use an OrderedMap for deterministic ordering of check type checks.
     * For Context objects we retain their order of appearance, apart from groupings.
     */
    Map<CheckType, List<Context>> contextsByCheckType = new TreeMap<CheckType, List<Context>>();
    for (Context context : allContexts) {
      contextsByCheckType.compute(checkGeneratorExtensions.checkType(context), (CheckType k, List<Context> lst) -> lst != null ? lst : new java.util.ArrayList<Context>()).add(context);
    }

    String baseTypeName = objectBaseJavaTypeRef.getQualifiedName();

    for (Iterator<Map.Entry<CheckType, List<Context>>> iterator = contextsByCheckType.entrySet().iterator(); iterator.hasNext();) {
      Map.Entry<CheckType, List<Context>> entry = iterator.next();
      String checkType = "CheckType." + entry.getKey();

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
    /* Contexts grouped by fully qualified variable type name,
     * otherwise in order of appearance.
     */
    Map<String, List<Context>> contextsByVarType = new TreeMap<String, List<Context>>();
    for (Context context : contexts) {
      contextsByVarType.compute(context.getContextVariable().getType().getQualifiedName(),
        (String k, List<Context> lst) -> lst != null ? lst : new java.util.ArrayList<Context>()
      ).add(context);
    }

    /* Ordering for context variable type checks. */
    List<JvmTypeReference> contextVarTypes = ListExtensions.<Context, JvmTypeReference>map(contexts, (Context x) -> x.getContextVariable().getType());
    InstanceOfCheckOrderer.Forest forest = InstanceOfCheckOrderer.orderTypes(contextVarTypes);

    emitInstanceOfTree(out, forest, null, contextsByVarType, catalog, baseTypeName, 0);
  }

  private void emitInstanceOfTree(final ITreeAppendable out, final InstanceOfCheckOrderer.Forest forest, final String node, final Map<String, List<Context>> contextsByVarType, final CheckCatalog catalog, final String baseTypeName, final int level) {
    if (node != null) {
      String typeName = node;
      if (Objects.equals(typeName, baseTypeName)) {
        typeName = null;
      }
      String varName;
      if (typeName == null) {
        varName = "object";
      } else {
        varName = "castObject" + (level > 1 ? Integer.toString(level) : "");
      }

      out.newLine();
      StringBuilder sb = new StringBuilder(512);
      if (typeName != null) {
        sb.append("if (object instanceof final ").append(typeName).append(' ').append(varName).append(") ");
      }
      sb.append('{');
      out.append(sb.toString());
      out.increaseIndentation();

      List<Context> ctxList = contextsByVarType.get(node);
      for (Context context : ctxList) {
        emitCheckMethodCall(out, varName, context, catalog); // with preceding newline
      }
    }

    Collection<String> subTypes = forest.getSubTypes(node);
    for (String child : subTypes) {
      emitInstanceOfTree(out, forest, child, contextsByVarType, catalog, baseTypeName, level + 1);
    }

    if (node != null) {
      out.decreaseIndentation();
      out.newLine();
      out.append("}");
    }
  }

  private void emitCheckMethodCall(final ITreeAppendable out, final String varName, final Context context, final CheckCatalog catalog) {
    String methodName = generateContextMethodName(context);
    String jMethodName = toJavaLiteral(methodName);
    String qMethodName = toJavaLiteral(catalog.getName(), methodName);

    out.newLine();
    out.append("validate(" + jMethodName + ", " + qMethodName + ", object,\n         () -> " + methodName + "(" + varName + ", diagnosticCollector), diagnosticCollector);");
  }

  private String toJavaLiteral(final String... strings) {
    return "\"" + Strings.convertToJavaString(String.join(".", strings)) + "\"";
  }

  private Iterable<JvmField> createInjectedField(final CheckCatalog context, final String fieldName, final JvmTypeReference type) {
    // Generate @Inject private typeName fieldName;
    if (type == null) {
      return Collections.emptyList();
    }
    JvmField field = typesFactory.createJvmField();
    field.setSimpleName(fieldName);
    field.setVisibility(JvmVisibility.PRIVATE);
    field.setType(jvmTypesBuilder.cloneWithProxies(type));
    Iterables.addAll(field.getAnnotations(), createAnnotation(checkedTypeRef(context, Inject.class), (JvmAnnotationReference it) -> {
    }));
    return Collections.singleton(field);
  }

  private Iterable<JvmMember> createCheck(final Check chk) {
    // If we don't have FormalParameters, there's no need to do all this song and dance with inner classes.
    if (chk.getFormalParameters().isEmpty()) {
      return ListExtensions.<Context, JvmMember>map(chk.getContexts(), (Context ctx) -> (JvmMember) createCheckMethod(ctx));
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
    List<JvmMember> newMembers = Lists.newArrayList();
    // First the class
    JvmGenericType checkClass = jvmTypesBuilder.toClass(chk, StringExtensions.toFirstUpper(chk.getName()) + "Class", (JvmGenericType it) -> {
      it.getSuperTypes().add(_typeReferenceBuilder.typeRef(Object.class));
      it.setVisibility(JvmVisibility.PRIVATE);
      // Add a fields for the parameters, so that they can be linked. We suppress generation of these fields in the generator,
      // and replace all references by calls to the getter function in the catalog.
      Iterables.addAll(it.getMembers(), IterableExtensions.<FormalParameter, JvmField>map(IterableExtensions.<FormalParameter>filter(chk.getFormalParameters(), (FormalParameter f) -> f.getType() != null && f.getName() != null), (FormalParameter f) -> jvmTypesBuilder.toField(f, f.getName(), f.getType(), (JvmField it1) -> {
        it1.setFinal(true);
      })));
    });
    newMembers.add(checkClass);
    newMembers.add(jvmTypesBuilder.toField(chk, StringExtensions.toFirstLower(chk.getName()) + "Impl", _typeReferenceBuilder.typeRef(checkClass), (JvmField it) -> {
      jvmTypesBuilder.setInitializer(it, (ITreeAppendable appendable) -> {
        appendable.append("new " + checkClass.getSimpleName() + "()");
      });
    }));
    Iterables.addAll(newMembers, IterableExtensions.<JvmOperation>filterNull(ListExtensions.<Context, JvmOperation>map(chk.getContexts(), (Context ctx) -> createCheckCaller(ctx, chk))));
    // If we create these above in the class initializer, the types of the context variables somehow are not resolved yet.
    Iterables.addAll(checkClass.getMembers(), IterableExtensions.<JvmOperation>filterNull(ListExtensions.<Context, JvmOperation>map(chk.getContexts(), (Context ctx) -> createCheckExecution(ctx))));
    return newMembers;
  }

  private JvmOperation createCheckExecution(final Context ctx) {
    if (ctx == null || ctx.getContextVariable() == null) {
      return null;
    }
    JvmTypeReference ctxVarType = ctx.getContextVariable().getType();
    String simpleName = null;
    if (ctxVarType != null) {
      simpleName = ctxVarType.getSimpleName();
    }
    String functionName = "run" + StringExtensions.toFirstUpper(simpleName);
    return jvmTypesBuilder.toMethod(ctx, functionName, _typeReferenceBuilder.typeRef("void"), (JvmOperation it) -> {
      String paramName = ctx.getContextVariable().getName() == null ? CheckConstants.IT : ctx.getContextVariable().getName();
      it.getParameters().add(jvmTypesBuilder.toParameter(ctx, paramName, ctx.getContextVariable().getType()));
      it.getParameters().add(jvmTypesBuilder.toParameter(ctx, "diagnosticCollector", checkedTypeRef(ctx, DiagnosticCollector.class)));
      jvmTypesBuilder.setBody(it, ctx.getConstraint());
    });
  }

  private Iterable<JvmAnnotationReference> createCheckAnnotation(final Context ctx) {
    JvmTypeReference checkTypeTypeRef = checkedTypeRef(ctx, CheckType.class);
    if (checkTypeTypeRef == null) {
      return Collections.emptyList();
    }
    XFeatureCall featureCall = XbaseFactory.eINSTANCE.createXFeatureCall();
    featureCall.setFeature(checkTypeTypeRef.getType());
    featureCall.setTypeLiteral(true);
    XMemberFeatureCall memberCall = XbaseFactory.eINSTANCE.createXMemberFeatureCall();
    memberCall.setMemberCallTarget(featureCall);
    // The grammar doesn't use the CheckType constants directly...
    String name = checkGeneratorExtensions.checkTypeQName(ctx);
    int i = name.lastIndexOf('.');
    if (i >= 0) {
      name = name.substring(i + 1);
    }
    memberCall.setFeature(IterableExtensions.head(((JvmDeclaredType) checkTypeTypeRef.getType()).findAllFeaturesByName(name)));

    // memberCall needs to belong to a resource.
    // We add it as a separate model to the context's resource.
    ctx.eResource().getContents().add(memberCall);

    return createAnnotation(checkedTypeRef(ctx, org.eclipse.xtext.validation.Check.class), (JvmAnnotationReference it) -> {
      it.getExplicitValues().add(jvmTypesBuilder.toJvmAnnotationValue(memberCall));
    });
  }

  private JvmOperation createCheckCaller(final Context ctx, final Check chk) {
    if (ctx == null || ctx.getContextVariable() == null) {
      return null;
    }
    JvmTypeReference ctxVarType = ctx.getContextVariable().getType();
    String simpleName = null;
    if (ctxVarType != null) {
      simpleName = ctxVarType.getSimpleName();
    }
    String functionName = StringExtensions.toFirstLower(chk.getName()) + simpleName;
    // To make the formal parameter visible, we have to generate quite a bit... I see no way to get the XVariableDeclaration for them
    // into the XBlockExpression of ctx.constraint. Just copying them doesn't work; modifies the source model!
    // Therefore, we generate something new: each check becomes a local class

    return jvmTypesBuilder.toMethod(ctx, functionName, _typeReferenceBuilder.typeRef("void"), (JvmOperation it) -> {
      it.getParameters().add(jvmTypesBuilder.toParameter(ctx, "context", ctx.getContextVariable().getType()));
      it.getParameters().add(jvmTypesBuilder.toParameter(ctx, "diagnosticCollector", checkedTypeRef(ctx, DiagnosticCollector.class)));
      Iterables.addAll(it.getAnnotations(), createCheckAnnotation(ctx));
      jvmTypesBuilder.setDocumentation(it, functionName + "."); // Well, that's not very helpful, but it is what the old compiler did...
      jvmTypesBuilder.setBody(it, (ITreeAppendable appendable) -> {
        JvmTypeReference ctxVarType1 = ctx.getContextVariable().getType();
        String simpleName1 = null;
        if (ctxVarType1 != null) {
          simpleName1 = ctxVarType1.getSimpleName();
        }
        appendable.append(StringExtensions.toFirstLower(chk.getName()) + "Impl" + ".run" + StringExtensions.toFirstUpper(simpleName1) + "(context, diagnosticCollector);");
      });
    });
  }

  private JvmOperation createCheckMethod(final Context ctx) {
    // Simple case for contexts of checks that do not have formal parameters. No need to generate nested classes for these.
    if (ctx == null || ctx.getContextVariable() == null) {
      return null;
    }
    String functionName = generateContextMethodName(ctx);

    return jvmTypesBuilder.toMethod(ctx, functionName, _typeReferenceBuilder.typeRef("void"), (JvmOperation it) -> {
      String paramName = ctx.getContextVariable().getName() == null ? CheckConstants.IT : ctx.getContextVariable().getName();
      it.getParameters().add(jvmTypesBuilder.toParameter(ctx, paramName, ctx.getContextVariable().getType()));
      it.getParameters().add(jvmTypesBuilder.toParameter(ctx, "diagnosticCollector", checkedTypeRef(ctx, DiagnosticCollector.class)));
      Iterables.addAll(it.getAnnotations(), createCheckAnnotation(ctx));
      jvmTypesBuilder.setDocumentation(it, functionName + "."); // Well, that's not very helpful, but it is what the old compiler did...
      jvmTypesBuilder.setBody(it, ctx.getConstraint());
    });
  }

  private String generateContextMethodName(final Context ctx) {
    EObject container = ctx.eContainer();
    String baseName;
    if (container instanceof Check check) {
      baseName = check.getName();
    } else if (container instanceof Implementation impl) {
      baseName = impl.getName();
    } else {
      baseName = null;
    }
    JvmTypeReference ctxVarType = ctx.getContextVariable().getType();
    String simpleName = null;
    if (ctxVarType != null) {
      simpleName = ctxVarType.getSimpleName();
    }
    return StringExtensions.toFirstLower(baseName) + simpleName;
  }

  // CheckCatalog

  // CHECKSTYLE:CHECK-OFF LambdaBodyLength
  private Iterable<JvmMember> createIssue(final CheckCatalog catalog, final Check check) {
    List<JvmMember> members = Lists.newArrayList();
    for (FormalParameter parameter : check.getFormalParameters()) {
      JvmTypeReference returnType = parameter.getType();
      if (returnType != null && !returnType.eIsProxy()) {
        String returnName = returnType.getQualifiedName();
        String operation;
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
        String parameterKey = CheckPropertiesGenerator.parameterKey(parameter, check);
        String defaultName = "null";
        if (parameter.getRight() != null) {
          defaultName = CheckGeneratorExtensions.splitCamelCase(checkGeneratorNaming.formalParameterGetterName(parameter)).toUpperCase() + "_DEFAULT";
          // Is generated into the PreferenceInitializer. Actually, since we do have it in the initializer, passing it here again
          // as default value is just a safety measure if something went wrong and the property shouldn't be set.
        }
        String javaDefaultValue = checkGeneratorNaming.preferenceInitializerClassName(catalog) + "." + defaultName;
        members.add(jvmTypesBuilder.toMethod(parameter, checkGeneratorNaming.formalParameterGetterName(parameter), returnType, (JvmOperation it) -> {
          jvmTypesBuilder.setDocumentation(it, "Gets the run-time value of formal parameter <em>" + parameter.getName() + "</em>. The value\nreturned is either the default as defined in the check definition, or the\nconfigured value, if existing.\n\n@param context\n           the context object used to determine the current project in\n           order to check if a configured value exists in a project scope\n@return the run-time value of <em>" + parameter.getName() + "</em>");
          JvmTypeReference eObjectTypeRef = checkedTypeRef(parameter, EObject.class);
          if (eObjectTypeRef != null) {
            it.getParameters().add(jvmTypesBuilder.toParameter(parameter, "context", eObjectTypeRef));
          }
          jvmTypesBuilder.setBody(it, (ITreeAppendable appendable) -> {
            appendable.append("return checkConfigurationStoreService.getCheckConfigurationStore(context)." + operation + "(\"" + parameterKey + "\", " + javaDefaultValue + ");");
          });
        }));
      } // end if
    } // end for
    members.add(jvmTypesBuilder.toMethod(check, "get" + StringExtensions.toFirstUpper(check.getName()) + "Message", _typeReferenceBuilder.typeRef(String.class), (JvmOperation it) -> {
      jvmTypesBuilder.setDocumentation(it, CheckJvmModelInferrerUtil.GET_MESSAGE_DOCUMENTATION);
      // Generate one parameter "Object... bindings"
      it.setVarArgs(true);
      it.getParameters().add(jvmTypesBuilder.toParameter(check, "bindings", jvmTypesBuilder.addArrayTypeDimension(_typeReferenceBuilder.typeRef(Object.class))));
      jvmTypesBuilder.setBody(it, (ITreeAppendable appendable) -> {
        appendable.append("return org.eclipse.osgi.util.NLS.bind(\"" + Strings.convertToJavaString(check.getMessage()) + "\", bindings);");
      });
      // TODO (minor): how to get NLS into the imports?
    }));
    JvmTypeReference severityType = checkedTypeRef(check, SeverityKind.class);
    if (severityType != null) {
      members.add(jvmTypesBuilder.toMethod(check, "get" + StringExtensions.toFirstUpper(check.getName()) + "SeverityKind", severityType, (JvmOperation it) -> {
        jvmTypesBuilder.setDocumentation(it, "Gets the {@link SeverityKind severity kind} of check\n<em>" + check.getLabel() + "</em>. The severity kind returned is either the\ndefault ({@code " + check.getDefaultSeverity().name() + "}), as is set in the check definition, or the\nconfigured value, if existing.\n\n@param context\n         the context object used to determine the current project in\n         order to check if a configured value exists in a project scope\n@return the severity kind of this check: returns the default (" + check.getDefaultSeverity().name() + ") if\n        no configuration for this check was found, else the configured\n        value looked up in the configuration store");
        JvmTypeReference eObjectTypeRef = checkedTypeRef(check, EObject.class);
        if (eObjectTypeRef != null) {
          it.getParameters().add(jvmTypesBuilder.toParameter(check, "context", eObjectTypeRef));
        }
        jvmTypesBuilder.setBody(it, (ITreeAppendable appendable) -> {
          appendable.append("final int result = checkConfigurationStoreService.getCheckConfigurationStore(context).getInt(\"" + CheckPropertiesGenerator.checkSeverityKey(check) + "\", " + check.getDefaultSeverity().getValue() + ");\nreturn SeverityKind.values()[result];");
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
    EList<Check> checks = catalog.getChecks();
    Iterable<Check> flattenedCatChecks = Iterables.<Check>concat(ListExtensions.<Category, EList<Check>>map(catalog.getCategories(), (Category cat) -> cat.getChecks()));
    Iterable<Check> allChecks = Iterables.<Check>concat(checks, flattenedCatChecks);
    List<JvmMember> result = Lists.newArrayList();
    for (Check c : allChecks) {
      for (FormalParameter parameter : c.getFormalParameters()) {
        if (parameter.getType() != null && parameter.getRight() != null) {
          String defaultName = CheckGeneratorExtensions.splitCamelCase(checkGeneratorNaming.formalParameterGetterName(parameter)).toUpperCase() + "_DEFAULT";
          result.add(jvmTypesBuilder.toField(parameter, defaultName, parameter.getType(), (JvmField it) -> {
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

  // CHECKSTYLE:CHECK-OFF LambdaBodyLength
  private Iterable<JvmMember> createPreferenceInitializerMethods(final CheckCatalog catalog) {
    JvmTypeReference prefStore = checkedTypeRef(catalog, IEclipsePreferences.class);
    List<JvmMember> result = Lists.newArrayList();

    if (prefStore != null) {
      result.add(jvmTypesBuilder.toMethod(catalog, "initializeDefaultPreferences", _typeReferenceBuilder.typeRef("void"), (JvmOperation it) -> {
        Iterables.addAll(it.getAnnotations(), createAnnotation(checkedTypeRef(catalog, Override.class), (JvmAnnotationReference it1) -> {
        }));
        it.setVisibility(JvmVisibility.PUBLIC);
        jvmTypesBuilder.setBody(it, (ITreeAppendable appendable) -> {
          appendable.append("IEclipsePreferences preferences = org.eclipse.core.runtime.preferences.InstanceScope.INSTANCE.getNode(RUNTIME_NODE_NAME);\n\ninitializeSeverities(preferences);\ninitializeFormalParameters(preferences);");
        });
      }));
      EList<Check> checks = catalog.getChecks();
      Iterable<Check> flattenedCatChecks = Iterables.<Check>concat(ListExtensions.<Category, EList<Check>>map(catalog.getCategories(), (Category cat) -> cat.getChecks()));
      Iterable<Check> allChecks = Iterables.<Check>concat(checks, flattenedCatChecks);
      result.add(jvmTypesBuilder.toMethod(catalog, "initializeSeverities", _typeReferenceBuilder.typeRef("void"), (JvmOperation it) -> {
        it.setVisibility(JvmVisibility.PRIVATE);
        it.getParameters().add(jvmTypesBuilder.toParameter(catalog, "preferences", prefStore));
        jvmTypesBuilder.setBody(it, (ITreeAppendable appendable) -> {
          StringBuilder sb = new StringBuilder();
          for (Check c : allChecks) {
            sb.append("preferences.putInt(\"").append(CheckPropertiesGenerator.checkSeverityKey(c)).append("\", ").append(c.getDefaultSeverity().getValue()).append(");\n");
          }
          appendable.append(sb.toString());
        });
      }));
      result.add(jvmTypesBuilder.toMethod(catalog, "initializeFormalParameters", _typeReferenceBuilder.typeRef("void"), (JvmOperation it) -> {
        it.setVisibility(JvmVisibility.PRIVATE);
        it.getParameters().add(jvmTypesBuilder.toParameter(catalog, "preferences", jvmTypesBuilder.cloneWithProxies(prefStore)));
        jvmTypesBuilder.setBody(it, (ITreeAppendable appendable) -> {
          for (Check c : allChecks) {
            for (FormalParameter parameter : c.getFormalParameters()) {
              if (parameter.getRight() != null) {
                String key = CheckPropertiesGenerator.parameterKey(parameter, c);
                String defaultFieldName = CheckGeneratorExtensions.splitCamelCase(checkGeneratorNaming.formalParameterGetterName(parameter)).toUpperCase() + "_DEFAULT";
                JvmTypeReference jvmType = parameter.getType();
                String typeName = jvmType.getQualifiedName();
                if (typeName != null && typeName.startsWith("java.util.List<")) {
                  // Marshal lists.
                  EList<JvmTypeReference> args = ((JvmParameterizedTypeReference) jvmType).getArguments();
                  if (args != null && args.size() == 1) {
                    String baseTypeName = IterableExtensions.<JvmTypeReference>head(args).getSimpleName();
                    appendable.append("preferences.put(\"" + key + "\", com.avaloq.tools.ddk.check.runtime.configuration.CheckPreferencesHelper.marshal" + baseTypeName + "s(" + defaultFieldName + "));\n");
                  } else {
                    appendable.append("// Found " + key + " with " + typeName + "\n");
                  }
                } else {
                  String prefOperation;
                  if (typeName != null) {
                    prefOperation = switch (typeName) {
                      case "java.lang.Boolean" -> "putBoolean";
                      case "boolean" -> "putBoolean";
                      case "java.lang.Integer" -> "putInt";
                      case "int" -> "putInt";
                      default -> "put";
                    };
                  } else {
                    prefOperation = "put";
                  }
                  appendable.append("preferences." + prefOperation + "(\"" + key + "\", " + defaultFieldName + ");\n");
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

    JvmAnnotationReference annotation = typesFactory.createJvmAnnotationReference();
    annotation.setAnnotation((JvmAnnotationType) typeRef.getType());
    Objects.requireNonNull(initializer, "Initializer is null").apply(annotation);

    return Collections.singletonList(annotation);
  }

  // Error handling etc.

  private void createError(final String message, final EObject context, final EStructuralFeature feature) {
    Resource rsc = context.eResource();
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
    JvmTypeReference result = _typeReferenceBuilder.typeRef(clazz);
    if (result == null || result.getType() == null) {
      createTypeNotFoundError(clazz.getName(), context);
      return null;
    }
    return result;
  }

  public void infer(final EObject catalog, final IJvmDeclaredTypeAcceptor acceptor, final boolean preIndexingPhase) {
    if (catalog instanceof CheckCatalog checkCatalog) {
      _infer(checkCatalog, acceptor, preIndexingPhase);
    } else if (catalog != null) {
      _infer(catalog, acceptor, preIndexingPhase);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
        + Arrays.<Object>asList(catalog, acceptor, preIndexingPhase).toString());
    }
  }
}
