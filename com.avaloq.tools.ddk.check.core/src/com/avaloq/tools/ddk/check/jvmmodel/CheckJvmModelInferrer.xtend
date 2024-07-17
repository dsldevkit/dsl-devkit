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
package com.avaloq.tools.ddk.check.jvmmodel

import com.avaloq.tools.ddk.check.CheckConstants
import com.avaloq.tools.ddk.check.check.Check
import com.avaloq.tools.ddk.check.check.CheckCatalog
import com.avaloq.tools.ddk.check.check.Context
import com.avaloq.tools.ddk.check.check.FormalParameter
import com.avaloq.tools.ddk.check.check.Implementation
import com.avaloq.tools.ddk.check.generator.CheckGeneratorExtensions
import com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming
import com.avaloq.tools.ddk.check.generator.CheckPropertiesGenerator
import com.avaloq.tools.ddk.check.resource.CheckLocationInFileProvider
import com.avaloq.tools.ddk.check.runtime.configuration.ICheckConfigurationStoreService
import com.avaloq.tools.ddk.check.runtime.issue.AbstractIssue
import com.avaloq.tools.ddk.check.runtime.issue.DispatchingCheckImpl
import com.avaloq.tools.ddk.check.runtime.issue.SeverityKind
import com.avaloq.tools.ddk.check.validation.IssueCodes
import com.avaloq.tools.ddk.xtext.tracing.ResourceValidationRuleSummaryEvent
import com.google.common.collect.ImmutableMap
import com.google.common.collect.Lists
import com.google.inject.Inject
import com.google.inject.Singleton
import java.util.ArrayList
import java.util.Collections
import java.util.List
import java.util.Map
import java.util.Objects
import java.util.TreeMap
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer
import org.eclipse.core.runtime.preferences.IEclipsePreferences
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.common.types.JvmAnnotationReference
import org.eclipse.xtext.common.types.JvmAnnotationType
import org.eclipse.xtext.common.types.JvmDeclaredType
import org.eclipse.xtext.common.types.JvmField
import org.eclipse.xtext.common.types.JvmMember
import org.eclipse.xtext.common.types.JvmOperation
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.common.types.JvmVisibility
import org.eclipse.xtext.common.types.TypesFactory
import org.eclipse.xtext.diagnostics.Severity
import org.eclipse.xtext.util.Strings
import org.eclipse.xtext.validation.CheckMode
import org.eclipse.xtext.validation.CheckType
import org.eclipse.xtext.validation.EObjectDiagnosticImpl
import org.eclipse.xtext.xbase.XFeatureCall
import org.eclipse.xtext.xbase.XMemberFeatureCall
import org.eclipse.xtext.xbase.XbaseFactory
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1

import static extension com.avaloq.tools.ddk.check.generator.CheckGeneratorExtensions.*
import static extension org.apache.commons.lang.StringEscapeUtils.escapeJava

/**
 * <p>Infers a JVM model from the source model.</p>
 *
 * <p>The JVM model should contain all elements that would appear in the Java code
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>
 */
class CheckJvmModelInferrer extends AbstractModelInferrer {

  @Inject TypesFactory typesFactory
  @Inject CheckLocationInFileProvider locationInFileProvider

  @Inject extension CheckGeneratorExtensions
  @Inject extension CheckGeneratorNaming
  @Inject extension JvmTypesBuilder

  def dispatch infer(CheckCatalog catalog, IJvmDeclaredTypeAcceptor acceptor, boolean preIndexingPhase) {
    // The xbase automatic scoping mechanism (typeRef()) cannot find secondary classes in the same resource. It can
    // only find indexed resources (either in the JDT index or in the xtext index). However, we'll initialize the
    // JVM validator class before the resource gets indexed, so the JVM catalog class cannot be found yet when we
    // create the injection in the validator. Therefore, remember the class here directly, and set it directly
    // in the validator, completely bypassing any scoping.
    if (preIndexingPhase) return;
    val catalogClass = catalog.toClass(catalog.qualifiedCatalogClassName);
    val issueCodeToLabelMapTypeRef = typeRef(ImmutableMap, typeRef(String), typeRef(String))
    acceptor.accept(catalogClass, [
      val parentType = checkedTypeRef(catalog, typeof(AbstractIssue));
      if (parentType !== null) {
        superTypes += parentType;
      }
      annotations += createAnnotation(checkedTypeRef(catalog, typeof(Singleton)), [])
      documentation = '''Issues for «catalog.name».''';
      members += createInjectedField(catalog, 'checkConfigurationStoreService', checkedTypeRef(catalog, typeof(ICheckConfigurationStoreService)));

      // Create map of issue code to label and associated getter
      members += catalog.toField(issueCodeToLabelMapFieldName, issueCodeToLabelMapTypeRef, [
        static = true
        final = true
        // Get all issue codes and labels
        val issues = catalog.checkAndImplementationIssues
        // Use a TreeMap to eliminate duplicates,
        // and also to sort by qualified issue code name so autogenerated files are more readable and less prone to spurious ordering changes.
        // Do this when compiling the Check, to avoid discovering duplicates at runtime.
        val sortedUniqueQualifiedIssueCodeNamesAndLabels = new TreeMap<String, String>();
        for (issue : issues) {
          val qualifiedIssueCodeName = issue.qualifiedIssueCodeName();
          val issueLabel = issue.issueLabel().escapeJava;
          val existingIssueLabel = sortedUniqueQualifiedIssueCodeNamesAndLabels.putIfAbsent(qualifiedIssueCodeName, issueLabel);
          if (null !== existingIssueLabel && issueLabel != existingIssueLabel) {
            // This qualified issue code name is already in the map, with a different label. Fail the build.
            throw new IllegalArgumentException('''Multiple issues found with qualified issue code name: «qualifiedIssueCodeName»''')
          }
        }
        initializer = [append('''
          «ImmutableMap.simpleName».<«String.simpleName», «String.simpleName»>builder()
            «FOR qualifiedIssueCodeNameAndLabel : sortedUniqueQualifiedIssueCodeNamesAndLabels.entrySet»
            .put(«qualifiedIssueCodeNameAndLabel.key», "«qualifiedIssueCodeNameAndLabel.value»")
            «ENDFOR»
            .build()
        ''')]
      ])
      members += catalog.toMethod(issueCodeToLabelMapFieldName.fieldGetterName, issueCodeToLabelMapTypeRef, [
        documentation = '''
          Get map of issue code to label for «catalog.name».

          @returns Map of issue code to label for «catalog.name».
        ''';
        static = true
        final = true
        body = '''return «issueCodeToLabelMapFieldName»;'''
      ])

      members += catalog.allChecks.map(c|createIssue(catalog, c)).flatten.filterNull;
    ]);

    acceptor.accept(catalog.toClass(catalog.qualifiedValidatorClassName), [
      val parentType = checkedTypeRef(catalog, typeof(DispatchingCheckImpl));
      if (parentType !== null) {
        superTypes += parentType;
      }
      // Constructor will be added automatically.
      documentation = '''
        Validator for «catalog.name».''';
      // Create catalog injections
      members += createInjectedField(catalog, catalog.catalogInstanceName, typeRef(catalogClass));
      // Create fields
      members += catalog.members.map(m|m.toField(m.name, m.type) [initializer = m.value; it.addAnnotations(m.annotations);]);
      // Create catalog name function
      members += catalog.toMethod('getQualifiedCatalogName', typeRef(typeof(String))) [
        body = [append('''return "«catalog.packageName».«catalog.name»";''')];
      ];

      // Create getter for map of issue code to label
      members += catalog.toMethod(issueCodeToLabelMapFieldName.fieldGetterName, issueCodeToLabelMapTypeRef, [
        final = true
        body = '''return «catalog.catalogClassName».«issueCodeToLabelMapFieldName.fieldGetterName»();'''
      ])

      members += createDispatcherMethod(catalog);

      // Create methods for contexts in checks
      val allChecks = catalog.checks + catalog.categories.map(cat|cat.checks).flatten;
      members += allChecks.map(chk|createCheck(chk)).flatten;
      // Create methods for stand-alone context implementations
      members += catalog.implementations.map(impl|createCheckMethod(impl.context)).filterNull;
    ]);
    acceptor.accept(catalog.toClass(catalog.qualifiedPreferenceInitializerClassName), [
      val parentType = checkedTypeRef(catalog, typeof(AbstractPreferenceInitializer));
      if (parentType !== null) {
        superTypes += parentType;
      }
      members += catalog.toField('RUNTIME_NODE_NAME', typeRef(typeof(String))) [
        static = true;
        final = true;
        initializer = [append('"' + catalog.bundleName + '"')];
      ];
      members += createFormalParameterFields(catalog);
      members += createPreferenceInitializerMethods(catalog);
    ]);
  }

  private def JvmOperation createDispatcherMethod(CheckCatalog catalog) {
    val objectBaseJavaTypeRef = checkedTypeRef(catalog, EObject);
    return catalog.toMethod("validate", typeRef("void"), [
      visibility = JvmVisibility::PUBLIC;
      parameters += catalog.toParameter("checkMode", checkedTypeRef(catalog, CheckMode));
      parameters += catalog.toParameter("object", objectBaseJavaTypeRef);
      parameters += catalog.toParameter("eventCollector", checkedTypeRef(catalog, ResourceValidationRuleSummaryEvent.Collector));
      annotations += createAnnotation(checkedTypeRef(catalog, typeof(Override)), []);
      body = [out | emitDispatcherMethodBody(out, catalog, objectBaseJavaTypeRef)];
    ]);
  }

  private def void emitDispatcherMethodBody(ITreeAppendable out, CheckCatalog catalog, JvmTypeReference objectBaseJavaTypeRef) {
    /* A catalog may contain both Check and Implementation objects,
     * which in turn may contain Context objects.
     * Categories may optionally be used for grouping checks, and
     * we can include categorized checks by using getAllChecks().
     * We only consider Context objects with a typed contextVariable.
     */
    val allContexts = (catalog.allChecks.map(chk | chk.contexts).flatten +
                       catalog.implementations.map(impl | impl.context).filterNull)
      .filter(ctx | ctx.contextVariable?.type !== null);

    /* Contexts grouped by CheckType.
     * We use an OrderedMap for deterministic ordering of check type checks.
     * For Context objects we retain their order of appearance, apart from groupings.
     */
    val contextsByCheckType = new TreeMap<CheckType, List<Context>>();
    for (Context context : allContexts) {
      contextsByCheckType.compute(checkType(context), [k, lst | lst ?: new ArrayList<Context>()]).add(context);
    }

    val baseTypeName = objectBaseJavaTypeRef.qualifiedName;

    for (val iterator = contextsByCheckType.entrySet.iterator(); iterator.hasNext(); ) {
      val entry = iterator.next();

      out.append('''if (checkMode.shouldCheck(CheckType.«entry.key»)) {''');
      out.increaseIndentation;
      emitInstanceOfConditionals(out, entry.value, catalog, baseTypeName); // with preceding newline for each
      out.decreaseIndentation;
      out.newLine;
      out.append("}");
      if (iterator.hasNext()) // not at method body end
        out.newLine; // separator between mode checks
    }
  }

  private def void emitInstanceOfConditionals(ITreeAppendable out, List<Context> contexts, CheckCatalog catalog, String baseTypeName) {
    /* Contexts grouped by fully qualified variable type name,
     * otherwise in order of appearance.
     */
    val contextsByVarType = new TreeMap<String, List<Context>>();
    for (Context context : contexts) {
      contextsByVarType.compute(context.contextVariable.type.qualifiedName,
        [k, lst | lst ?: new ArrayList<Context>()]
      ).add(context);
    }

    /* Ordering for context variable type checks. */
    val List<JvmTypeReference> contextVarTypes = contexts.map([x | x.contextVariable.type]);
    val forest = InstanceOfCheckOrderer.orderTypes(contextVarTypes);

    emitInstanceOfTree(out, forest, null, contextsByVarType, catalog, baseTypeName, 0);
  }

  private def void emitInstanceOfTree(ITreeAppendable out, InstanceOfCheckOrderer.Forest forest, String node, Map<String, List<Context>> contextsByVarType, CheckCatalog catalog, String baseTypeName, int level) {
    if (node !== null) {
      var String typeName = node;
      if (typeName == baseTypeName)
        typeName = null;
      val varName = if (typeName === null) "object" else "castObject" + (if (level > 1) Integer.toString(level) else "");

      out.newLine;
      out.append('''«IF typeName !== null»if (object instanceof «typeName» «varName») «ENDIF»{''');
      out.increaseIndentation;

      val contexts = contextsByVarType.get(node);
      for (context : contexts) {
        emitCheckMethodCall(out, varName, context, catalog); // with preceding newline
      }
    }

    for (child : forest.getSubTypes(node)) {
      emitInstanceOfTree(out, forest, child, contextsByVarType, catalog, baseTypeName, level + 1);
    }

    if (node !== null) {
      out.decreaseIndentation;
      out.newLine;
      out.append('}');
    }
  }

  private def void emitCheckMethodCall(ITreeAppendable out, String varName, Context context, CheckCatalog catalog) {
    val methodName = generateContextMethodName(context);
    val jMethodName = toJavaLiteral(methodName);
    val qMethodName = toJavaLiteral(catalog.name, methodName);

    out.newLine;
    out.append('''
      validate(«jMethodName», «qMethodName», object,
               () -> «methodName»(«varName»), eventCollector);''');
  }

  private def String toJavaLiteral(String... strings) {
    return '''"«Strings::convertToJavaString(String.join(".", strings))»"''';
  }

  private def Iterable<JvmField> createInjectedField(CheckCatalog context, String fieldName, JvmTypeReference type) {
    // Generate @Inject private typeName fieldName;
    if (type === null) {
      return Collections::emptyList;
    }
    val field = typesFactory.createJvmField();
    field.simpleName = fieldName;
    field.visibility = JvmVisibility::PRIVATE;
    field.type = cloneWithProxies(type);
    field.annotations += createAnnotation(checkedTypeRef(context, typeof(Inject)), []);
    return Collections::singleton(field);
  }

  private def Iterable<JvmMember> createCheck(Check chk) {
    // If we don't have FormalParameters, there's no need to do all this song and dance with inner classes.
    if (chk.formalParameters.empty) {
      return chk.contexts.map(ctx|createCheckMethod(ctx) as JvmMember);
    } else {
      return createCheckWithParameters(chk);
    }
  }

  private def Iterable<JvmMember> createCheckWithParameters(Check chk) {
    // Generate an inner class, plus a field holding an instance of that class.
    // Put the formal parameters into that class as fields.
    // For each check context, generate a run method.
    // For each check context, generate an annotated check method outside to call the appropriate run method.
    // This is the only way I found to make those formal parameters visible in the check constraints...
    // The generated Java looks a bit strange, because we suppress actually generating these fields, as we
    // don't use them; we only need them for scoping based on this inferred model.
    val List<JvmMember> newMembers = Lists::newArrayList;
    // First the class
    val checkClass = chk.toClass(chk.name.toFirstUpper + 'Class') [
      superTypes += typeRef(typeof(Object));
      visibility = JvmVisibility::PRIVATE;
      // Add a fields for the parameters, so that they can be linked. We suppress generation of these fields in the generator,
      // and replace all references by calls to the getter function in the catalog.
      members += chk.formalParameters.filter(f|f.type !== null && f.name !== null).map(f|f.toField(f.name, f.type) [final = true]);
    ];
    newMembers += checkClass;
    newMembers += chk.toField(chk.name.toFirstLower + 'Impl', typeRef(checkClass)) [initializer = [append('''new «checkClass.simpleName»()''')]];
    newMembers += chk.contexts.map(ctx|createCheckCaller(ctx, chk)).filterNull;
    // If we create these above in the class initializer, the types of the context variables somehow are not resolved yet.
    checkClass.members += chk.contexts.map(ctx|createCheckExecution(ctx)).filterNull;
    return newMembers;
  }

  private def JvmOperation createCheckExecution(Context ctx) {
    if (ctx === null || ctx.contextVariable === null) {
      return null;
    }
    val String functionName = 'run' + ctx.contextVariable.type?.simpleName.toFirstUpper;
    ctx.toMethod(functionName, typeRef('void')) [
      parameters += ctx.contextVariable.toParameter(if (ctx.contextVariable.name === null) CheckConstants::IT else ctx.contextVariable.name, ctx.contextVariable.type);
      body = ctx.constraint;
    ]
  }

  private def Iterable<JvmAnnotationReference> createCheckAnnotation (Context ctx) {
    val checkTypeTypeRef = checkedTypeRef(ctx, typeof(CheckType));
    if (checkTypeTypeRef === null) {
      return Collections::emptyList;
    }
    val XFeatureCall featureCall = XbaseFactory::eINSTANCE.createXFeatureCall();
    featureCall.feature = checkTypeTypeRef.type;
    featureCall.typeLiteral = true;
    val XMemberFeatureCall memberCall = XbaseFactory::eINSTANCE.createXMemberFeatureCall();
    memberCall.memberCallTarget = featureCall;
    // The grammar doesn't use the CheckType constants directly...
    var String name = checkTypeQName(ctx);
    val int i = name.lastIndexOf('.');
    if (i >= 0) {
      name = name.substring(i+1);
    }
    memberCall.feature = (checkTypeTypeRef.type as JvmDeclaredType).findAllFeaturesByName(name).head;

    // memberCall needs to belong to a resource.
    // We add it as a separate model to the context's resource.
    ctx.eResource.contents.add(memberCall)

    return createAnnotation(checkedTypeRef(ctx, typeof(org.eclipse.xtext.validation.Check)), [
      explicitValues += memberCall.toJvmAnnotationValue();
    ]);
  }

  private def JvmOperation createCheckCaller(Context ctx, Check chk) {
    if (ctx === null || ctx.contextVariable === null) {
      return null;
    }
    val String functionName = chk.name.toFirstLower + ctx.contextVariable.type?.simpleName;
    // To make the formal parameter visible, we have to generate quite a bit... I see no way to get the XVariableDeclaration for them
    // into the XBlockExpression of ctx.constraint. Just copying them doesn't work; modifies the source model!
    // Therefore, we generate something new: each check becomes a local class

    ctx.toMethod(functionName, typeRef('void')) [
      parameters += ctx.contextVariable.toParameter("context", ctx.contextVariable.type);
      annotations += createCheckAnnotation(ctx);
      documentation = functionName + '.'; // Well, that's not very helpful, but it is what the old compiler did...
      body = [append('''
        «chk.name.toFirstLower + 'Impl'».run«ctx.contextVariable.type?.simpleName.toFirstUpper»(context);'''
      )]
    ]
  }

  private def JvmOperation createCheckMethod(Context ctx) {
    // Simple case for contexts of checks that do not have formal parameters. No need to generate nested classes for these.
    if (ctx === null || ctx.contextVariable === null) {
      return null;
    }
    val String functionName = generateContextMethodName(ctx);

    ctx.toMethod(functionName, typeRef('void')) [
      parameters += ctx.contextVariable.toParameter(if (ctx.contextVariable.name === null) CheckConstants::IT else ctx.contextVariable.name, ctx.contextVariable.type);
      annotations += createCheckAnnotation(ctx);
      documentation = functionName + '.'; // Well, that's not very helpful, but it is what the old compiler did...
      body = ctx.constraint;
    ]
  }

  private def String generateContextMethodName(Context ctx) {
    return switch container : ctx.eContainer {
      Check : container.name
      Implementation: container.name
    }.toFirstLower + ctx.contextVariable.type?.simpleName;
  }

  // CheckCatalog

  private def Iterable<JvmMember> createIssue(CheckCatalog catalog, Check check) {
    val List<JvmMember> members = Lists::newArrayList();
    for (FormalParameter parameter : check.formalParameters) {
      val JvmTypeReference returnType  = parameter.type;
      if (returnType !== null && !returnType.eIsProxy) {
        val String returnName  = returnType.qualifiedName;
        val String operation   = switch returnName {
          case 'java.lang.Boolean'                 : 'getBoolean'
          case 'boolean'                           : 'getBoolean'
          case 'java.lang.Integer'                 : 'getInt'
          case 'int'                               : 'getInt'
          case 'java.util.List<java.lang.String>'  : 'getStrings'
          case 'java.util.List<java.lang.Boolean>' : 'getBooleans'
          case 'java.util.List<java.lang.Integer>' : 'getIntegers'
          default                                  : 'getString'
        }
        val String parameterKey  = CheckPropertiesGenerator::parameterKey(parameter, check);
        var String defaultName = 'null';
        if (parameter.right !== null) {
          defaultName = parameter.formalParameterGetterName.splitCamelCase.toUpperCase + '_DEFAULT';
          // Is generated into the PreferenceInitializer. Actually, since we do have it in the initializer, passing it here again
          // as default value is just a safety measure if something went wrong and the property shouldn't be set.
        }
        val javaDefaultValue = catalog.preferenceInitializerClassName + '.' + defaultName;
        members += parameter.toMethod(parameter.formalParameterGetterName, returnType) [
          documentation = '''
            Gets the run-time value of formal parameter <em>«parameter.name»</em>. The value
            returned is either the default as defined in the check definition, or the
            configured value, if existing.

            @param context
                       the context object used to determine the current project in
                       order to check if a configured value exists in a project scope
            @return the run-time value of <em>«parameter.name»</em>''';
          val eObjectTypeRef = checkedTypeRef(parameter, typeof(EObject));
          if (eObjectTypeRef !== null) {
            parameters += parameter.toParameter('context', eObjectTypeRef);
          }
          body = [append('''
            return checkConfigurationStoreService.getCheckConfigurationStore(context).«operation»("«parameterKey»", «javaDefaultValue»);'''
          )];
        ];
      } // end if
    } // end for
    members += check.toMethod('get' + check.name.toFirstUpper + 'Message', typeRef(typeof(String))) [
      documentation = CheckJvmModelInferrerUtil.GET_MESSAGE_DOCUMENTATION;
      // Generate one parameter "Object... bindings"
      varArgs = true;
      parameters += check.toParameter('bindings', addArrayTypeDimension(typeRef(typeof(Object))));
      body = [append('''
        return org.eclipse.osgi.util.NLS.bind("«Strings::convertToJavaString(check.message)»", bindings);'''
      )];
      // TODO (minor): how to get NLS into the imports?
    ];
    val severityType = checkedTypeRef(check, typeof(SeverityKind));
    if (severityType !== null) {
      members += check.toMethod('get' + check.name.toFirstUpper + 'SeverityKind', severityType) [
        documentation = '''
          Gets the {@link SeverityKind severity kind} of check
          <em>«check.label»</em>. The severity kind returned is either the
          default ({@code «check.defaultSeverity.name()»}), as is set in the check definition, or the
          configured value, if existing.

          @param context
                   the context object used to determine the current project in
                   order to check if a configured value exists in a project scope
          @return the severity kind of this check: returns the default («check.defaultSeverity.name()») if
                  no configuration for this check was found, else the configured
                  value looked up in the configuration store''';
        val eObjectTypeRef = checkedTypeRef(check, typeof(EObject));
        if (eObjectTypeRef !== null) {
          parameters += check.toParameter('context', eObjectTypeRef);
        }
        body = [append('''
          final int result = checkConfigurationStoreService.getCheckConfigurationStore(context).getInt("«CheckPropertiesGenerator::checkSeverityKey(check)»", «check.defaultSeverity.value»);
          return SeverityKind.values()[result];'''
        )];
      ];
    }
    return members;
  }

  // PreferenceInitializer.

  private def Iterable<JvmMember> createFormalParameterFields (CheckCatalog catalog) {
    // For each formal parameter, create a public static final field with a unique name derived from the formal parameter and
    // set it to its right-hand side expression. We let Java evaluate this!
    val allChecks = catalog.checks + catalog.categories.map(cat|cat.checks).flatten;
    val List<JvmMember> result = Lists::newArrayList();
    for (Check c : allChecks) {
      for (FormalParameter parameter : c.formalParameters) {
        if (parameter.type !== null && parameter.right !== null) {
          val String defaultName = parameter.formalParameterGetterName.splitCamelCase.toUpperCase + '_DEFAULT';
          result += parameter.toField(defaultName, parameter.type) [
            visibility = JvmVisibility::PUBLIC;
            final = true;
            static = true;
            initializer = parameter.right;
          ];
        }

      }
    }
    return result;
  }

  private def Iterable<JvmMember> createPreferenceInitializerMethods(CheckCatalog catalog) {
    val JvmTypeReference prefStore = checkedTypeRef(catalog, typeof (IEclipsePreferences));
    val List<JvmMember> result = Lists::newArrayList();

    if (prefStore !== null) {
      result += catalog.toMethod('initializeDefaultPreferences', typeRef('void')) [
        annotations += createAnnotation(checkedTypeRef(catalog, typeof(Override)), []);
        visibility = JvmVisibility::PUBLIC;
        body = [append('''
        IEclipsePreferences preferences = org.eclipse.core.runtime.preferences.InstanceScope.INSTANCE.getNode(RUNTIME_NODE_NAME);

        initializeSeverities(preferences);
        initializeFormalParameters(preferences);''')];
      ];
      val allChecks = catalog.checks + catalog.categories.map(cat|cat.checks).flatten;
      result += catalog.toMethod('initializeSeverities', typeRef('void')) [
        visibility = JvmVisibility::PRIVATE;
        parameters += catalog.toParameter('preferences', prefStore);
        body = [append('''«FOR c:allChecks»
        preferences.putInt("«CheckPropertiesGenerator::checkSeverityKey(c)»", «c.defaultSeverity.value»);
        «ENDFOR»''')];
      ];
      result += catalog.toMethod('initializeFormalParameters', typeRef('void')) [
        visibility = JvmVisibility::PRIVATE;
        parameters += catalog.toParameter('preferences', prefStore.cloneWithProxies);
        body = [
          for (Check c : allChecks) {
            for (FormalParameter parameter : c.formalParameters) {
              if (parameter.right !== null) {
                val String key = CheckPropertiesGenerator::parameterKey(parameter, c);
                val String defaultFieldName = parameter.formalParameterGetterName.splitCamelCase.toUpperCase + '_DEFAULT';
                val JvmTypeReference jvmType = parameter.type;
                val String typeName = jvmType.qualifiedName;
                if (typeName !== null && typeName.startsWith("java.util.List<")) {
                  // Marshal lists.
                  val args = (jvmType as JvmParameterizedTypeReference).arguments;
                  if (args !== null && args.size == 1) {
                    val baseTypeName = args.head.simpleName;
                    append('''preferences.put("«key»", com.avaloq.tools.ddk.check.runtime.configuration.CheckPreferencesHelper.marshal«baseTypeName»s(«defaultFieldName»));
                    ''');
                  } else {
                    append('''// Found «key» with «typeName»
                    ''');
                  }
                } else {
                  val String operation = switch typeName {
                    case 'java.lang.Boolean' : 'putBoolean'
                    case 'boolean'           : 'putBoolean'
                    case 'java.lang.Integer' : 'putInt'
                    case 'int'               : 'putInt'
                    default                  : 'put'
                  };
                  append('''preferences.«operation»("«key»", «defaultFieldName»);
                  ''');
                }
              }
            }
          }
        ];
      ];
    }
    return result;

  }

  private def Iterable<JvmAnnotationReference> createAnnotation (JvmTypeReference typeRef, Procedure1<JvmAnnotationReference> initializer) {
    if (typeRef === null) {
      return Collections::emptyList;
    }

    val annotation = typesFactory.createJvmAnnotationReference()
    annotation.annotation = typeRef.type as JvmAnnotationType
    Objects.requireNonNull(initializer, "Initializer is null").apply(annotation)

    return Collections::singletonList(annotation)
  }

  // Error handling etc.

  private def createError (String message, EObject context, EStructuralFeature feature) {
    val Resource rsc = context.eResource;
    if (rsc !== null) {
      var f = feature;
      if (f === null) {
        f = locationInFileProvider.getIdentifierFeature(context);
      }
      rsc.errors += new EObjectDiagnosticImpl(Severity::ERROR, IssueCodes::INFERRER_ERROR, "Check compiler: " + message, context, f, -1, null)
    }
  }

  private def createTypeNotFoundError(String name, EObject context) {
    createError("Type " + name + " not found; check project setup (missing required bundle?)", context, null);
  }

  private def JvmTypeReference checkedTypeRef(EObject context, Class<?> clazz) {
    if (clazz === null) {
      createTypeNotFoundError ("<unknown>", context);
      return null;
    }
    val result = typeRef(clazz);
    if (result === null || result.type === null) {
      createTypeNotFoundError(clazz.name, context);
      return null;
    }
    return result;
  }
}
