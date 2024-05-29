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
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.Naming
import com.google.inject.Inject
import org.eclipse.emf.ecore.EClass

class FragmentProviderGenerator {

  @Inject extension GeneratorUtilX
  @Inject extension Naming
  @Inject extension ExportGeneratorX

  def generate(ExportModel it, CompilationContext ctx, extension GenModelUtilX genModelUtil) {
    val grammar = grammar
    val fingerprintedExports = exports.filter[fingerprint && fragmentAttribute !== null].toList
    '''
      package «fragmentProvider.toJavaPackage»;

      «IF !fingerprintedExports.isEmpty»
      import org.eclipse.emf.ecore.EClass;
      «ENDIF»
      «IF !fingerprintedExports.isEmpty || it.extension»
      import org.eclipse.emf.ecore.EObject;
      «ENDIF»
      «IF !fingerprintedExports.isEmpty»
      import org.eclipse.emf.ecore.EPackage;
      «ENDIF»

      import com.avaloq.tools.ddk.xtext.resource.AbstractSelectorFragmentProvider;


      public class «getFragmentProvider().toSimpleName()» extends AbstractSelectorFragmentProvider {
        «IF !fingerprintedExports.isEmpty»
        @Override
        public boolean appendFragmentSegment(final EObject object, StringBuilder builder) {
          EClass eClass = object.eClass();
          EPackage ePackage = eClass.getEPackage();
          «val typeMap = fingerprintedExports.typeMap(grammar)»
          «val sortedExportsMap = fingerprintedExports.sortedExportsByEPackage()»
          «FOR p : sortedExportsMap.keySet()»
            if (ePackage == «p.qualifiedPackageInterfaceName()».eINSTANCE) {
              int classifierID = eClass.getClassifierID();
              switch (classifierID) {
              «FOR c : p.EClassifiers.filter(EClass).filter(c|fingerprintedExports.map[type].exists(e|e.isSuperTypeOf(c)))»
                «val e = typeMap.get(c)»
                «javaContributorComment(e.location())»
                case «c.classifierIdLiteral()»: {
                  return appendFragmentSegment((«c.instanceClassName()») object, builder);
                }
              «ENDFOR»
              default:
                return super.appendFragmentSegment(object, builder);
              }
            }
          «ENDFOR»
          return super.appendFragmentSegment(object, builder);
        }
        «ENDIF»
        «IF it.extension»
          @Override
          protected boolean appendFragmentSegmentFallback(final EObject object, StringBuilder builder) {
            // For export extension we must return false, so the logic will try other extensions
            return false;
          }

        «ENDIF»
        «FOR e : fingerprintedExports»
          protected boolean appendFragmentSegment(final «e.type.instanceClassName()» obj, StringBuilder builder) {
            return computeSelectorFragmentSegment(obj, «e.fragmentAttribute.literalIdentifier()», «e.fragmentUnique», builder);
          }

        «ENDFOR»
      }
    '''
  }

}
