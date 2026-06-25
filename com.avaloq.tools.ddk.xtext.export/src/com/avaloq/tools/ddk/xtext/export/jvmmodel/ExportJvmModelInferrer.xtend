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
package com.avaloq.tools.ddk.xtext.export.jvmmodel

import com.avaloq.tools.ddk.xtext.export.export.Export
import com.avaloq.tools.ddk.xtext.export.export.ExportModel
import com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression
import com.avaloq.tools.ddk.xtext.export.export.InterfaceField
import com.avaloq.tools.ddk.xtext.export.export.InterfaceItem
import com.avaloq.tools.ddk.xtext.export.export.InterfaceNavigation
import com.avaloq.tools.ddk.xtext.export.generator.ExportGeneratorX
import com.avaloq.tools.ddk.xtext.expression.expression.Expression
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorSupport
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX
import com.avaloq.tools.ddk.xtext.linking.ShortFragmentProvider
import com.avaloq.tools.ddk.xtext.naming.AbstractExportedNameProvider
import com.avaloq.tools.ddk.xtext.resource.AbstractCachingResourceDescriptionManager
import com.avaloq.tools.ddk.xtext.resource.AbstractExportFeatureExtension
import com.avaloq.tools.ddk.xtext.resource.AbstractResourceDescriptionStrategy
import com.avaloq.tools.ddk.xtext.resource.AbstractSelectorFragmentProvider
import com.avaloq.tools.ddk.xtext.resource.AbstractStreamingFingerprintComputer
import com.avaloq.tools.ddk.xtext.resource.IFingerprintComputer
import com.google.common.hash.Hasher
import com.google.inject.Inject
import com.google.inject.Singleton
import java.util.Collection
import java.util.Map
import java.util.Set
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.Switch
import org.eclipse.xtext.common.types.JvmAnnotationReference
import org.eclipse.xtext.common.types.JvmAnnotationType
import org.eclipse.xtext.common.types.JvmGenericType
import org.eclipse.xtext.common.types.JvmVisibility
import org.eclipse.xtext.common.types.TypesFactory
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.util.IAcceptor
import org.eclipse.xtext.util.Strings
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder
import org.eclipse.xtext.xbase.lib.Pair

/**
 * Infers a JVM model from an export model.
 * <p>
 * Replaces the former {@code IGenerator2} based {@code ExportGenerator}: the exported names provider, resource
 * description manager, resource description strategy, resource description constants, fingerprint computer, fragment
 * provider and (for extension models) the export feature extension are now contributed as inferred JVM types whose
 * Java source is emitted by the Xbase {@code JvmModelGenerator}. The method bodies are produced as strings and
 * attached verbatim; framework types referenced by those strings are emitted fully qualified so that the generated
 * compilation units need no import section of their own.
 */
class ExportJvmModelInferrer extends AbstractModelInferrer {

  @Inject extension JvmTypesBuilder
  @Inject extension ExportGeneratorX
  @Inject extension GeneratorUtilX

  @Inject GenModelUtilX genModelUtil
  @Inject TypesFactory typesFactory
  @Inject GeneratorSupport generatorSupport
  @Inject ExportExpressionCompiler compiler
  @Inject ExportExpressionTranslator translator

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
  def dispatch void infer(ExportModel model, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
    if (isPreIndexingPhase) {
      return
    }
    genModelUtil.resource = model.eResource

    // The expression based method body strings are produced eagerly here (inside the project resource loader
    // required by the expression compiler) rather than from within the deferred body closures, which run later
    // during emission.
    val Map<String, String> bodies = newHashMap
    generatorSupport.executeWithProjectResourceLoader(model.projectOf, [
      if (!model.exports.isEmpty) {
        bodies.put('qnDispatch', model.qualifiedNameDispatchBody.toString)
        for (var i = 0; i < model.exports.size; i++) {
          bodies.put('qn' + i, model.qualifiedNameBody(model.exports.get(i)).toString)
        }
        for (p : model.strategyPackages) {
          bodies.put('strategySwitch:' + p.nsURI, model.strategySwitchInitializer(p).toString)
        }
      }
      for (p : model.fingerprintPackages) {
        bodies.put('fpSwitch:' + p.nsURI, model.fingerprintSwitchInitializer(p).toString)
      }
    ])

    inferExportedNamesProvider(model, acceptor, bodies)
    if (!model.extension) {
      inferResourceDescriptionManager(model, acceptor)
    }
    inferResourceDescriptionStrategy(model, acceptor, bodies)
    inferResourceDescriptionConstants(model, acceptor)
    inferFingerprintComputer(model, acceptor, bodies)
    inferFragmentProvider(model, acceptor)
    if (model.extension) {
      inferExportFeatureExtension(model, acceptor)
    }
  }

  /**
   * Infers the {@code <Name>ExportedNamesProvider}.
   */
  def private void inferExportedNamesProvider(ExportModel model, IJvmDeclaredTypeAcceptor acceptor, Map<String, String> bodies) {
    val providerName = model.exportedNamesProvider
    acceptor.accept(model.toClass(providerName)) [
      superTypes += typeRef(AbstractExportedNameProvider)
      addSuppressWarningsAll
      documentation = '''Qualified name provider providing the qualified names for exported objects.'''
      if (!model.exports.isEmpty) {
        members += model.toMethod('qualifiedName', typeRef(QualifiedName)) [
          visibility = JvmVisibility.PUBLIC
          annotations += typeOnlyAnnotation(Override)
          parameters += model.toParameter('object', typeRef(EObject))
          val text = bodies.get('qnDispatch')
          body = [append(text)]
        ]
        for (var i = 0; i < model.exports.size; i++) {
          val c = model.exports.get(i)
          val text = bodies.get('qn' + i)
          members += model.toMethod('qualifiedName', typeRef(QualifiedName)) [
            visibility = JvmVisibility.PROTECTED
            parameters += model.toParameter('obj', typeRef(genModelUtil.instanceClassName(c.type)))
            body = [append(text)]
          ]
        }
      }
    ]
  }

  /**
   * Infers the {@code <Name>ResourceDescriptionManager}.
   */
  def private void inferResourceDescriptionManager(ExportModel model, IJvmDeclaredTypeAcceptor acceptor) {
    val grammar = model.grammar
    val usedGrammars = if (grammar !== null) grammar.usedGrammars else newArrayList
    val extendedGrammar = if (usedGrammars.isEmpty || usedGrammars.head.name.endsWith('.Terminals')) null else usedGrammars.head
    val initializer = if (extendedGrammar !== null) {
      '''com.google.common.collect.ImmutableSet.copyOf(com.google.common.collect.Sets.union(«extendedGrammar.resourceDescriptionManager».INTERESTING_EXTS, of(/*add extensions here*/)))'''
    } else {
      '''all()'''
    }
    acceptor.accept(model.toClass(model.resourceDescriptionManager)) [
      superTypes += typeRef(AbstractCachingResourceDescriptionManager)
      annotations += typeOnlyAnnotation(Singleton)
      addSuppressWarningsAll
      documentation = '''Resource description manager for «model.name» resources.'''
      members += model.toField('INTERESTING_EXTS', typeRef(Set, typeRef(String))) [
        visibility = JvmVisibility.PUBLIC
        static = true
        final = true
        initializer = [append(initializer.toString)]
      ]
      members += model.toMethod('getInterestingExtensions', typeRef(Set, typeRef(String))) [
        visibility = JvmVisibility.PROTECTED
        annotations += typeOnlyAnnotation(Override)
        body = [append('return INTERESTING_EXTS;')]
      ]
    ]
  }

  /**
   * Infers the {@code <Name>ResourceDescriptionStrategy}.
   */
  def private void inferResourceDescriptionStrategy(ExportModel model, IJvmDeclaredTypeAcceptor acceptor, Map<String, String> bodies) {
    acceptor.accept(model.toClass(model.resourceDescriptionStrategy)) [
      superTypes += typeRef(AbstractResourceDescriptionStrategy)
      addSuppressWarningsAll
      members += model.toField('EXPORTED_ECLASSES', typeRef(Set, typeRef(EClass))) [
        visibility = JvmVisibility.PRIVATE
        static = true
        final = true
        initializer = [append(model.exportedEClassesInitializer.toString)]
      ]
      members += model.toMethod('getExportedEClasses', typeRef(Set, typeRef(EClass))) [
        visibility = JvmVisibility.PUBLIC
        annotations += typeOnlyAnnotation(Override)
        parameters += model.toParameter('resource', typeRef(Resource))
        body = [append('return EXPORTED_ECLASSES;')]
      ]
      if (!model.exports.isEmpty) {
        members += model.toField('acceptor', typeRef(ThreadLocal, typeRef(IAcceptor, typeRef(IEObjectDescription)))) [
          visibility = JvmVisibility.PRIVATE
          final = true
          initializer = [append('new ThreadLocal<org.eclipse.xtext.util.IAcceptor<org.eclipse.xtext.resource.IEObjectDescription>>()')]
        ]
        for (p : model.strategyPackages) {
          val text = bodies.get('strategySwitch:' + p.nsURI)
          members += model.toField(p.name + 'ExportSwitch', typeRef(Switch, typeRef(Boolean))) [
            visibility = JvmVisibility.PRIVATE
            final = true
            initializer = [append(text)]
          ]
        }
        members += model.toMethod('doCreateEObjectDescriptions', typeRef(Boolean.TYPE)) [
          visibility = JvmVisibility.PROTECTED
          annotations += typeOnlyAnnotation(Override)
          parameters += model.toParameter('object', typeRef(EObject))
          parameters += model.toParameter('acceptor', typeRef(IAcceptor, typeRef(IEObjectDescription)))
          body = [append(model.strategyDoCreateBody.toString)]
        ]
      }
    ]
  }

  /**
   * Infers the {@code <Name>ResourceDescriptionConstants} interface.
   */
  def private void inferResourceDescriptionConstants(ExportModel model, IJvmDeclaredTypeAcceptor acceptor) {
    acceptor.accept(model.toClass(model.resourceDescriptionConstants)) [
      ^interface = true
      for (c : model.exports.filter[!it.type.abstract]) {
        for (attr : c.allEAttributes) {
          members += model.toField(constantName(attr, c.type), typeRef(String)) [
            visibility = JvmVisibility.PUBLIC
            static = true
            final = true
            initializer = [append('"' + Strings.convertToJavaString(attr.name) + '"')]
          ]
        }
        for (data : c.allUserData) {
          members += model.toField(constantName(data, c.type), typeRef(String)) [
            visibility = JvmVisibility.PUBLIC
            static = true
            final = true
            initializer = [append('"' + Strings.convertToJavaString(data.name) + '"')]
          ]
        }
      }
    ]
  }

  /**
   * Infers the {@code <Name>FingerprintComputer}.
   */
  def private void inferFingerprintComputer(ExportModel model, IJvmDeclaredTypeAcceptor acceptor, Map<String, String> bodies) {
    acceptor.accept(model.toClass(model.fingerprintComputer)) [
      superTypes += typeRef(AbstractStreamingFingerprintComputer)
      addSuppressWarningsAll
      if (model.interfaces.isEmpty) {
        members += model.toMethod('computeFingerprint', typeRef(String)) [
          visibility = JvmVisibility.PUBLIC
          annotations += typeOnlyAnnotation(Override)
          parameters += model.toParameter('resource', typeRef(Resource))
          body = [append('// no fingerprint defined\nreturn null;')]
        ]
      }
      members += model.toField('hasherAccess', typeRef(ThreadLocal, typeRef(Hasher))) [
        visibility = JvmVisibility.PRIVATE
        initializer = [append('new ThreadLocal<com.google.common.hash.Hasher>()')]
      ]
      for (p : model.fingerprintPackages) {
        val text = bodies.get('fpSwitch:' + p.nsURI)
        members += model.toField(p.name + 'Switch', typeRef(Switch, typeRef(Hasher))) [
          visibility = JvmVisibility.PRIVATE
          final = true
          initializer = [append(text)]
        ]
      }
      members += model.toMethod('fingerprint', typeRef(Void.TYPE)) [
        visibility = JvmVisibility.PROTECTED
        annotations += typeOnlyAnnotation(Override)
        parameters += model.toParameter('object', typeRef(EObject))
        parameters += model.toParameter('hasher', typeRef(Hasher))
        body = [append(model.fingerprintMethodBody.toString)]
      ]
    ]
  }

  /**
   * Infers the {@code <Name>FragmentProvider} (full provider, short stub, or none, depending on the model).
   */
  def private void inferFragmentProvider(ExportModel model, IJvmDeclaredTypeAcceptor acceptor) {
    val fingerprintedExports = model.exports.filter[fingerprint && fragmentAttribute !== null].toList
    if (!fingerprintedExports.isEmpty || model.extension) {
      acceptor.accept(model.toClass(model.fragmentProvider)) [
        superTypes += typeRef(AbstractSelectorFragmentProvider)
        addSuppressWarningsAll
        if (!fingerprintedExports.isEmpty) {
          members += model.toMethod('appendFragmentSegment', typeRef(Boolean.TYPE)) [
            visibility = JvmVisibility.PUBLIC
            annotations += typeOnlyAnnotation(Override)
            parameters += model.toParameter('object', typeRef(EObject))
            parameters += model.toParameter('builder', typeRef(StringBuilder))
            body = [append(model.appendFragmentSegmentBody(fingerprintedExports).toString)]
          ]
        }
        if (model.extension) {
          members += model.toMethod('appendFragmentSegmentFallback', typeRef(Boolean.TYPE)) [
            visibility = JvmVisibility.PROTECTED
            annotations += typeOnlyAnnotation(Override)
            parameters += model.toParameter('object', typeRef(EObject))
            parameters += model.toParameter('builder', typeRef(StringBuilder))
            body = [append('// For export extension we must return false, so the logic will try other extensions\nreturn false;')]
          ]
        }
        for (e : fingerprintedExports) {
          members += model.toMethod('appendFragmentSegment', typeRef(Boolean.TYPE)) [
            visibility = JvmVisibility.PROTECTED
            parameters += model.toParameter('obj', typeRef(genModelUtil.instanceClassName(e.type)))
            parameters += model.toParameter('builder', typeRef(StringBuilder))
            body = [append('''return computeSelectorFragmentSegment(obj, «genModelUtil.literalIdentifier(e.fragmentAttribute)», «e.fragmentUnique», builder);''')]
          ]
        }
      ]
    } else if (!model.exports.isEmpty) {
      acceptor.accept(model.toClass(model.fragmentProvider)) [
        superTypes += typeRef(ShortFragmentProvider)
        addSuppressWarningsAll
      ]
    }
  }

  /**
   * Infers the {@code <Name>ExportFeatureExtension} (extension models only).
   */
  def private void inferExportFeatureExtension(ExportModel model, IJvmDeclaredTypeAcceptor acceptor) {
    acceptor.accept(model.toClass(model.exportFeatureExtension)) [
      superTypes += typeRef(AbstractExportFeatureExtension)
      addSuppressWarningsAll
      members += model.toField('namesProvider', typeRef(model.exportedNamesProvider)) [
        visibility = JvmVisibility.PRIVATE
        annotations += typeOnlyAnnotation(Inject)
      ]
      members += model.toField('fingerprintComputer', typeRef(model.fingerprintComputer)) [
        visibility = JvmVisibility.PRIVATE
        annotations += typeOnlyAnnotation(Inject)
      ]
      members += model.toField('fragmentProvider', typeRef(model.fragmentProvider)) [
        visibility = JvmVisibility.PRIVATE
        annotations += typeOnlyAnnotation(Inject)
      ]
      members += model.toField('resourceDescriptionStrategy', typeRef(model.resourceDescriptionStrategy)) [
        visibility = JvmVisibility.PRIVATE
        annotations += typeOnlyAnnotation(Inject)
      ]
      members += model.toMethod('getNamesProvider', typeRef(IQualifiedNameProvider)) [
        visibility = JvmVisibility.PROTECTED
        annotations += typeOnlyAnnotation(Override)
        body = [append('return namesProvider;')]
      ]
      members += model.toMethod('getFingerprintComputer', typeRef(IFingerprintComputer)) [
        visibility = JvmVisibility.PROTECTED
        annotations += typeOnlyAnnotation(Override)
        body = [append('return fingerprintComputer;')]
      ]
      members += model.toMethod('getFragmentProvider', typeRef(AbstractSelectorFragmentProvider)) [
        visibility = JvmVisibility.PROTECTED
        annotations += typeOnlyAnnotation(Override)
        body = [append('return fragmentProvider;')]
      ]
      members += model.toMethod('getResourceDescriptionStrategy', typeRef(AbstractResourceDescriptionStrategy)) [
        visibility = JvmVisibility.PROTECTED
        annotations += typeOnlyAnnotation(Override)
        body = [append('return resourceDescriptionStrategy;')]
      ]
    ]
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Body producers (framework types fully qualified so the generated compilation units need no imports)
  // -------------------------------------------------------------------------------------------------------------------

  /**
   * Returns the EPackages of the non-abstract exported types, sorted by namespace URI.
   */
  def private Iterable<EPackage> strategyPackages(ExportModel model) {
    model.exports.filter[!type.abstract].map[type.EPackage].toSet.sortBy[nsURI]
  }

  /**
   * Returns the EPackages of the fingerprint interfaces, sorted by namespace URI.
   */
  def private Iterable<EPackage> fingerprintPackages(ExportModel model) {
    model.interfaces.map[type.EPackage].toSet.sortBy[nsURI]
  }

  def private CharSequence qualifiedNameDispatchBody(ExportModel model) {
    val types = model.exports
    val exportedEClasses = types.map[type].toSet
    val exportsMap = types.sortedExportsByEPackage
    '''
      org.eclipse.emf.ecore.EClass eClass = object.eClass();
      org.eclipse.emf.ecore.EPackage ePackage = eClass.getEPackage();
      «FOR p : exportsMap.keySet.sortBy[nsURI]»
        if (ePackage == «genModelUtil.qualifiedPackageInterfaceName(p)».eINSTANCE) {
          int classifierID = eClass.getClassifierID();
          switch (classifierID) {
          «FOR c : p.EClassifiers.filter(EClass).filter[c|exportedEClasses.exists[e|e.isSuperTypeOf(c)]]»
            case «genModelUtil.classifierIdLiteral(c)»: {
              return qualifiedName((«genModelUtil.instanceClassName(c)») object);
            }
          «ENDFOR»
          default:
            return null;
          }
        }
      «ENDFOR»
      return null;
    '''
  }

  def private CharSequence qualifiedNameBody(ExportModel model, Export c) {
    '''
      «javaContributorComment(c.location)»
      «IF c.naming !== null»
        final Object name = «javaExpr(c.naming, c.type, model)»;
        return name != null ? «IF c.qualifiedName»getConverter().toQualifiedName(String.valueOf(name))«ELSE»qualifyWithContainerName(obj, String.valueOf(name))«ENDIF» : null;
      «ELSE»
        return «IF c.qualifiedName»getConverter().toQualifiedName(getResolver().apply(obj))«ELSE»qualifyWithContainerName(obj, getResolver().apply(obj))«ENDIF»; // "name" attribute by default
      «ENDIF»
    '''
  }

  def private CharSequence exportedEClassesInitializer(ExportModel model) {
    val e = model.exports.typeMap(model.grammar)
    '''
      com.google.common.collect.ImmutableSet.copyOf(new org.eclipse.emf.ecore.EClass[] {
        «FOR c : e.keySet.sortBy[genModelUtil.literalIdentifier(it)] SEPARATOR ',\n'»«genModelUtil.literalIdentifier(c)»«ENDFOR»
      })'''
  }

  def private CharSequence strategySwitchInitializer(ExportModel model, EPackage p) {
    val types = model.exports
    '''
      new «genModelUtil.qualifiedSwitchClassName(p)»<Boolean>() {

        @Override
        public Boolean defaultCase(final org.eclipse.emf.ecore.EObject obj) {
          return true;
        }
        «FOR c : types.filter[!type.abstract && type.EPackage == p]»

          «javaContributorComment(c.location)»
          @Override
          public Boolean case«c.type.name»(final «genModelUtil.instanceClassName(c.type)» obj) {
            «IF c.guard === null»
              «generateCaseBody(model, c)»
            «ELSE»
              «val guard = javaExpr(c.guard, c.type, model)»
              «IF !guard.equalsIgnoreCase("false")»
                «javaContributorComment(c.guard.location)»
                if («guard») {
                  «generateCaseBody(model, c)»
                }
              «ENDIF»
            «ENDIF»

            // can «c.type.name» contain any nested «types.map[type].filter[!abstract].map[name].toSet» objects ?
            return «c.type.canContain(types.map[type].filter[!abstract].toSet, model.grammar)»;
          }
        «ENDFOR»
      }'''
  }

  def private CharSequence generateCaseBody(ExportModel model, Export c) {
    val a = c.allEAttributes
    val d = c.allUserData
    '''
      «IF !a.isEmpty || !d.isEmpty || c.fingerprint || c.resourceFingerprint || c.lookup »
        // Use a forwarding map to delay calculation as much as possible; otherwise we may get recursive EObject resolution attempts
        java.util.Map<String, String> data = new com.avaloq.tools.ddk.xtext.resource.extensions.AbstractForwardingResourceDescriptionStrategyMap() {

          @Override
          protected void fill(final com.google.common.collect.ImmutableMap.Builder<String, String> builder) {
            Object value = null;
            «IF c.fingerprint»
              // Fingerprint
              value = getFingerprint(obj);
              if (value != null) {
                builder.put(com.avaloq.tools.ddk.xtext.resource.IFingerprintComputer.OBJECT_FINGERPRINT, value.toString());
              }
            «ELSEIF c.resourceFingerprint»
              // Resource fingerprint
              value = getFingerprint(obj);
              if (value != null) {
                builder.put(com.avaloq.tools.ddk.xtext.resource.IFingerprintComputer.RESOURCE_FINGERPRINT, value.toString());
              }
            «ENDIF»
            «IF c.lookup»
              // Allow lookups
              «IF c.lookupPredicate !== null»
                «javaContributorComment(c.lookupPredicate.location)»
                if («javaExpr(c.lookupPredicate, c.type, model)») {
                  builder.put(com.avaloq.tools.ddk.xtext.resource.DetachableEObjectDescription.ALLOW_LOOKUP, Boolean.TRUE.toString());
                }
              «ELSE»
                builder.put(com.avaloq.tools.ddk.xtext.resource.DetachableEObjectDescription.ALLOW_LOOKUP, Boolean.TRUE.toString());
              «ENDIF»
            «ENDIF»
            «IF !a.isEmpty »
              // Exported attributes
              «FOR attr : a»
                value = obj.eGet(«genModelUtil.literalIdentifier(attr)», false);
                if (value != null) {
                  builder.put(«model.resourceDescriptionConstants».«constantName(attr, c.type)», value.toString());
                }
              «ENDFOR»
            «ENDIF»
            «IF !d.isEmpty »
              // User data
              «FOR data : d»
                value = «javaExpr(data.expr, c.type, model)»;
                if (value != null) {
                  builder.put(«model.resourceDescriptionConstants».«constantName(data, c.type)», value.toString());
                }
              «ENDFOR»
            «ENDIF»
          }
        };
        acceptEObjectDescription(obj, data, acceptor.get());
      «ELSE»
        acceptEObjectDescription(obj, acceptor.get());
      «ENDIF»
    '''
  }

  def private CharSequence strategyDoCreateBody(ExportModel model) {
    '''
      try {
        this.acceptor.set(acceptor);
        final org.eclipse.emf.ecore.EPackage ePackage = object.eClass().getEPackage();
        «FOR p : model.strategyPackages»
          if (ePackage == «genModelUtil.qualifiedPackageInterfaceName(p)».eINSTANCE) {
            return «p.name»ExportSwitch.doSwitch(object);
          }
        «ENDFOR»
        «IF model.extension»
          // Extension does not have to cover all EPackages of the language
          return false;
        «ELSE»
          // TODO: generate code for other possible epackages (as defined by grammar)
          return true;
        «ENDIF»
      } finally {
        this.acceptor.set(null);
      }
    '''
  }

  def private CharSequence fingerprintSwitchInitializer(ExportModel model, EPackage p) {
    '''
      new «genModelUtil.qualifiedSwitchClassName(p)»<com.google.common.hash.Hasher>() {
        «FOR f : model.interfaces.filter[type.EPackage == p]»

          «javaContributorComment(f.location)»
          @Override
          public com.google.common.hash.Hasher case«f.type.name»(final «genModelUtil.instanceClassName(f.type)» obj) {
            final com.google.common.hash.Hasher hasher = hasherAccess.get();
            «IF f.guard !== null»
              if (!(«javaExpr(f.guard, f.type, model)»)) {
                return hasher;
              }
            «ENDIF»
            hasher.putUnencodedChars(obj.eClass().getName()).putChar(ITEM_SEP);
            «FOR superFingerprint : f.getSuperInterfaces(f.type)»
              «FOR superItem : superFingerprint.items»
                «doProfile(superItem, model, superFingerprint.type)»
              «ENDFOR»
            «ENDFOR»
            «FOR item : f.items»
              «doProfile(item, model, f.type)»
            «ENDFOR»
            return hasher;
          }
        «ENDFOR»
      }'''
  }

  def private CharSequence fingerprintMethodBody(ExportModel model) {
    '''
      hasherAccess.set(hasher);
      «IF !model.interfaces.isEmpty»
        final org.eclipse.emf.ecore.EPackage ePackage = object.eClass().getEPackage();
        «FOR p : model.fingerprintPackages»
          if (ePackage == «genModelUtil.qualifiedPackageInterfaceName(p)».eINSTANCE) {
            «p.name»Switch.doSwitch(object);
          }
        «ENDFOR»
      «ENDIF»
      hasherAccess.set(null);
    '''
  }

  def private dispatch CharSequence doProfile(InterfaceItem it, ExportModel model, EClass type) {
    'ERROR' + it.toString + ' ' + javaContributorComment(it.location)
  }

  def private dispatch CharSequence doProfile(InterfaceField it, ExportModel model, EClass type) '''
    «IF field.many && (unordered == true) »
      fingerprintFeature(obj, «genModelUtil.literalIdentifier(field)», FingerprintOrder.UNORDERED, hasher);
    «ELSE»
      fingerprintFeature(obj, «genModelUtil.literalIdentifier(field)», hasher);
    «ENDIF»
    hasher.putChar(ITEM_SEP);
  '''

  def private dispatch CharSequence doProfile(InterfaceNavigation it, ExportModel model, EClass type) '''
    «IF ref.many && (unordered == true) »
      fingerprintRef(obj, «genModelUtil.literalIdentifier(ref)», FingerprintOrder.UNORDERED, hasher);
    «ELSE»
      fingerprintRef(obj, «genModelUtil.literalIdentifier(ref)», hasher);
    «ENDIF»
    hasher.putChar(ITEM_SEP);
  '''

  def private dispatch CharSequence doProfile(InterfaceExpression it, ExportModel model, EClass type) '''
    fingerprintExpr(«javaExpr(expr, type, model)», obj, FingerprintOrder.«if (unordered) "UNORDERED" else "ORDERED"», FingerprintIndirection.«if (ref) "INDIRECT" else "DIRECT"», hasher);
    hasher.putChar(ITEM_SEP);
  '''

  def private CharSequence appendFragmentSegmentBody(ExportModel model, Collection<Export> fingerprintedExports) {
    val typeMap = fingerprintedExports.typeMap(model.grammar)
    val sortedExportsMap = fingerprintedExports.sortedExportsByEPackage
    '''
      org.eclipse.emf.ecore.EClass eClass = object.eClass();
      org.eclipse.emf.ecore.EPackage ePackage = eClass.getEPackage();
      «FOR p : sortedExportsMap.keySet»
        if (ePackage == «genModelUtil.qualifiedPackageInterfaceName(p)».eINSTANCE) {
          int classifierID = eClass.getClassifierID();
          switch (classifierID) {
          «FOR c : p.EClassifiers.filter(EClass).filter[c|fingerprintedExports.map[type].exists[e|e.isSuperTypeOf(c)]]»
            «val e = typeMap.get(c)»
            «javaContributorComment(e.location)»
            case «genModelUtil.classifierIdLiteral(c)»: {
              return appendFragmentSegment((«genModelUtil.instanceClassName(c)») object, builder);
            }
          «ENDFOR»
          default:
            return super.appendFragmentSegment(object, builder);
          }
        }
      «ENDFOR»
      return super.appendFragmentSegment(object, builder);
    '''
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
  def private String javaExpr(Expression expression, EClass type, ExportModel model) {
    compiler.javaExpression(expression, translator.newCompilationContext('obj', type, <Pair<String, String>>newArrayList, model)).toString
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
   * Returns the workspace project containing the given export model, if any.
   *
   * @param model
   *          the export model, must not be {@code null}
   * @return the containing project, or {@code null} if it cannot be determined
   */
  def private IProject projectOf(ExportModel model) {
    val uri = model.eResource.URI
    if (uri.isPlatformResource) {
      val resource = ResourcesPlugin.workspace.root.findMember(uri.toPlatformString(true))
      if (resource !== null) {
        return resource.project
      }
    }
    null
  }

}
