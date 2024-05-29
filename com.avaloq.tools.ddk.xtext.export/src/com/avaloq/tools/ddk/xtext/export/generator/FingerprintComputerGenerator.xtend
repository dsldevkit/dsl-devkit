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

package com.avaloq.tools.ddk.xtext.export.generator

import com.avaloq.tools.ddk.xtext.export.export.ExportModel
import com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression
import com.avaloq.tools.ddk.xtext.export.export.InterfaceField
import com.avaloq.tools.ddk.xtext.export.export.InterfaceItem
import com.avaloq.tools.ddk.xtext.export.export.InterfaceNavigation
import com.avaloq.tools.ddk.xtext.expression.generator.CodeGenerationX
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.Naming
import com.google.inject.Inject
import org.eclipse.emf.ecore.EClass

class FingerprintComputerGenerator {

  @Inject extension CodeGenerationX
  @Inject extension GeneratorUtilX
  @Inject extension Naming
  @Inject extension ExportGeneratorX

  def generate(ExportModel it, CompilationContext ctx, extension GenModelUtilX genModelUtil) {
    '''
      package «fingerprintComputer.toJavaPackage»;

      import org.eclipse.emf.ecore.EObject;
      «IF !interfaces.isEmpty»
      import org.eclipse.emf.ecore.EPackage;
      import org.eclipse.emf.ecore.util.Switch;
      «ENDIF»
      import com.avaloq.tools.ddk.xtext.resource.AbstractStreamingFingerprintComputer;

      import com.google.common.hash.Hasher;


      public class «fingerprintComputer.toSimpleName» extends AbstractStreamingFingerprintComputer {

        «IF interfaces.isEmpty»
          // no fingerprint defined
          @Override
          public String computeFingerprint(final org.eclipse.emf.ecore.resource.Resource resource) {
            return null;
          }

        «ENDIF»
        private ThreadLocal<Hasher> hasherAccess = new ThreadLocal<Hasher>();

        «FOR p : interfaces.map[type.EPackage].toSet.sortBy[nsURI]»
          private final Switch<Hasher> «p.name»Switch = new «p.qualifiedSwitchClassName()»<Hasher>() {
            «FOR f : interfaces.filter[type.EPackage == p]»

              «javaContributorComment(f.location())»
              @Override
              public Hasher case«f.type.name»(final «f.type.instanceClassName()» obj) {
                final Hasher hasher = hasherAccess.get();
                «IF f.guard !== null»
                  if (!(«f.guard.javaExpression(ctx.clone('obj', f.type))»)) {
                    return hasher;
                  }
                «ENDIF»
                hasher.putUnencodedChars(obj.eClass().getName()).putChar(ITEM_SEP);
                «val superFPs = f.getSuperInterfaces(f.type)»
                «FOR superFingerprint : superFPs»
                  «FOR superItem : superFingerprint.items»
                    «doProfile(superItem, ctx, genModelUtil, superFingerprint.type)»
                  «ENDFOR»
                «ENDFOR»
                «FOR item : f.items»
                  «doProfile(item, ctx, genModelUtil, f.type)»
                «ENDFOR»
                return hasher;
              }
            «ENDFOR»
          };

        «ENDFOR»
        @Override
        protected void fingerprint(final EObject object, Hasher hasher) {
          hasherAccess.set(hasher);
          «IF !interfaces.isEmpty»
            final EPackage ePackage = object.eClass().getEPackage();
            «FOR p : interfaces.map[type.EPackage].toSet().sortBy[nsURI]»
              if (ePackage == «p.qualifiedPackageInterfaceName()».eINSTANCE) {
                «p.name»Switch.doSwitch(object);
              }
            «ENDFOR»
          «ENDIF»
          hasherAccess.set(null);
        }
      }
    '''
  }

  def dispatch doProfile(InterfaceItem it, CompilationContext ctx, extension GenModelUtilX genModelUtil, EClass type) {
    'ERROR' + it.toString + ' ' + javaContributorComment(it.location())
  }

  def dispatch doProfile(InterfaceField it, CompilationContext ctx, extension GenModelUtilX genModelUtil, EClass type) '''
    «IF field.many && (unordered == true) »
      fingerprintFeature(obj, «field.literalIdentifier()», FingerprintOrder.UNORDERED, hasher);
    «ELSE»
      fingerprintFeature(obj, «field.literalIdentifier()», hasher);
    «ENDIF»
    hasher.putChar(ITEM_SEP);
  '''

  def dispatch doProfile(InterfaceNavigation it, CompilationContext ctx, extension GenModelUtilX genModelUtil, EClass type) '''
    «IF ref.many && (unordered == true) »
      fingerprintRef(obj, «ref.literalIdentifier()», FingerprintOrder.UNORDERED, hasher);
    «ELSE»
      fingerprintRef(obj, «ref.literalIdentifier()», hasher);
    «ENDIF»
    hasher.putChar(ITEM_SEP);
  '''

  def dispatch doProfile(InterfaceExpression it, CompilationContext ctx, extension GenModelUtilX genModelUtil, EClass type) '''
    fingerprintExpr(«expr.javaExpression(ctx.clone('obj', type))», obj, FingerprintOrder.«if (unordered) "UNORDERED" else "ORDERED"», FingerprintIndirection.«if (ref) "INDIRECT" else "DIRECT"», hasher);
    hasher.putChar(ITEM_SEP);
  '''

}
