/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.export.generator

import com.avaloq.tools.ddk.xtext.export.export.Export
import com.avaloq.tools.ddk.xtext.export.export.ExportModel
import com.avaloq.tools.ddk.xtext.expression.generator.CodeGenerationX
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.Naming
import com.google.inject.Inject

class ResourceDescriptionStrategyGenerator {

  @Inject extension CodeGenerationX
  @Inject extension GeneratorUtilX
  @Inject extension Naming
  @Inject extension ExportGeneratorX

  def generate(ExportModel it, CompilationContext ctx, extension GenModelUtilX genModelUtil) {
    '''
      package «resourceDescriptionStrategy.toJavaPackage»;

      import java.util.Map;
      import java.util.Set;

      import org.eclipse.emf.ecore.EClass;
      import org.eclipse.emf.ecore.EObject;
      import org.eclipse.emf.ecore.EPackage;
      import org.eclipse.emf.ecore.resource.Resource;
      import org.eclipse.emf.ecore.util.Switch;
      import org.eclipse.xtext.resource.IEObjectDescription;
      import org.eclipse.xtext.util.IAcceptor;

      import com.avaloq.tools.ddk.xtext.resource.AbstractResourceDescriptionStrategy;
      «IF exports.exists[e|e.fingerprint||e.resourceFingerprint]»
        import com.avaloq.tools.ddk.xtext.resource.IFingerprintComputer;
      «ENDIF»
      «IF exports.exists(e|e.lookup)»
        import com.avaloq.tools.ddk.xtext.resource.DetachableEObjectDescription;
      «ENDIF»
      import com.avaloq.tools.ddk.xtext.resource.extensions.AbstractForwardingResourceDescriptionStrategyMap;
      import com.google.common.collect.ImmutableMap;
      import com.google.common.collect.ImmutableSet;
      «val types = exports»


      public class «resourceDescriptionStrategy.toSimpleName» extends AbstractResourceDescriptionStrategy {

        private static final Set<EClass> EXPORTED_ECLASSES = ImmutableSet.copyOf(new EClass[] {
          «val e = types.typeMap(grammar)»
          «FOR c : e.keySet.sortBy[literalIdentifier] SEPARATOR ',\n'»«c.literalIdentifier»«ENDFOR»
        });

        @Override
        public Set<EClass> getExportedEClasses(final Resource resource) {
          return EXPORTED_ECLASSES;
        }

        «IF !types.isEmpty»
          private final ThreadLocal<IAcceptor<IEObjectDescription>> acceptor = new ThreadLocal<IAcceptor<IEObjectDescription>>();

          «FOR p : types.filter[!type.abstract].map[type.EPackage].toSet.sortBy[nsURI]»
            private final Switch<Boolean> «p.name»ExportSwitch = new «p.qualifiedSwitchClassName»<Boolean>() {

              @Override
              public Boolean defaultCase(final EObject obj) {
                return true;
              }
              «FOR c : types.filter[!type.abstract && type.EPackage == p]»

                «javaContributorComment(c.location)»
                @Override
                public Boolean case«c.type.name»(final «c.type.instanceClassName()» obj) {
                  «val guard = c.guard.javaExpression(ctx.clone('obj', c.type))»
                  «IF guard.equalsIgnoreCase("false")»
                  «ELSEIF c.guard !== null»
                    «javaContributorComment(c.guard.location)»
                    if («guard») {
                      «generateCaseBody(c, ctx, genModelUtil)»
                    }
                  «ELSEIF c.guard === null»
                    «generateCaseBody(c, ctx, genModelUtil)»
                  «ENDIF»

                  // can «c.type.name» contain any nested «types.map[type].filter[!abstract].map[name].toSet» objects ?
                  return «c.type.canContain(types.map[type].filter[!abstract].toSet, grammar)»;
                }
              «ENDFOR»
            };

          «ENDFOR»
          @Override
          protected boolean doCreateEObjectDescriptions(final EObject object, final IAcceptor<IEObjectDescription> acceptor) {
            try {
              this.acceptor.set(acceptor);
              final EPackage ePackage = object.eClass().getEPackage();
              «FOR p : types.filter[!type.abstract].map[type.EPackage].toSet.sortBy[nsURI]»
                if (ePackage == «p.qualifiedPackageInterfaceName».eINSTANCE) {
                  return «p.name»ExportSwitch.doSwitch(object);
                }
              «ENDFOR»
              «IF it.extension»
                // Extension does not have to cover all EPackages of the language
                return false;
              «ELSE»
                // TODO: generate code for other possible epackages (as defined by grammar)
                return true;
              «ENDIF»
            } finally {
              this.acceptor.set(null);
            }
          }

        «ENDIF»
      }
    '''
  }

  def generateCaseBody(ExportModel it, Export c, CompilationContext ctx, extension GenModelUtilX genModelUtil) {
    val a = c.allEAttributes
    val d = c.allUserData
    '''
      «IF !a.isEmpty || !d.isEmpty || c.fingerprint || c.resourceFingerprint || c.lookup »
        // Use a forwarding map to delay calculation as much as possible; otherwise we may get recursive EObject resolution attempts
        Map<String, String> data = new AbstractForwardingResourceDescriptionStrategyMap() {

          @Override
          protected void fill(final ImmutableMap.Builder<String, String> builder) {
            Object value = null;
            «IF c.fingerprint»
              // Fingerprint
              value = getFingerprint(obj);
              if (value != null) {
                builder.put(IFingerprintComputer.OBJECT_FINGERPRINT, value.toString());
              }
            «ELSEIF c.resourceFingerprint»
              // Resource fingerprint
              value = getFingerprint(obj);
              if (value != null) {
                builder.put(IFingerprintComputer.RESOURCE_FINGERPRINT, value.toString());
              }
            «ENDIF»
            «IF c.lookup»
              // Allow lookups
              «IF c.lookupPredicate !== null»
                «javaContributorComment(c.lookupPredicate.location)»
                if («c.lookupPredicate.javaExpression(ctx.clone('obj', c.type))») {
                  builder.put(DetachableEObjectDescription.ALLOW_LOOKUP, Boolean.TRUE.toString());
                }
              «ELSE»
                builder.put(DetachableEObjectDescription.ALLOW_LOOKUP, Boolean.TRUE.toString());
              «ENDIF»
            «ENDIF»
            «IF !a.isEmpty »
              // Exported attributes
              «FOR attr : a»
                value = obj.eGet(«attr.literalIdentifier», false);
                if (value != null) {
                  builder.put(«resourceDescriptionConstants.toSimpleName».«attr.constantName(c.type)», value.toString());
                }
              «ENDFOR»
            «ENDIF»
            «IF !d.isEmpty »
              // User data
              «FOR data : d»
                value = «data.expr.javaExpression(ctx.clone('obj', c.type))»;
                if (value != null) {
                  builder.put(«resourceDescriptionConstants.toSimpleName».«data.constantName(c.type)», value.toString());
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
}
