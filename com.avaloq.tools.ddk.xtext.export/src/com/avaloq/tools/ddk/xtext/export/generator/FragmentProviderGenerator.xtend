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
    val fingerprintedExports = exports.filter[fingerprint && fragmentAttribute != null].toList
    '''
      package «fragmentProvider.toJavaPackage»;

      import org.eclipse.emf.ecore.EClass;
      import org.eclipse.emf.ecore.EObject;
      import org.eclipse.emf.ecore.EPackage;

      import com.avaloq.tools.ddk.xtext.resource.AbstractSelectorFragmentProvider;


      public class «getFragmentProvider().toSimpleName()» extends AbstractSelectorFragmentProvider {

        @Override
        public CharSequence getFragmentSegment(final EObject object) {
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
                  return getFragmentSegment((«c.instanceClassName()») object);
                }
              «ENDFOR»
              default:
                return super.getFragmentSegment(object);
              }
            }
          «ENDFOR»
          return super.getFragmentSegment(object);
        }

        «FOR e : fingerprintedExports»
          protected CharSequence getFragmentSegment(final «e.type.instanceClassName()» obj) {
            return computeSelectorFragmentSegment(obj, «e.fragmentAttribute.literalIdentifier()», «e.fragmentUnique»);
          }

        «ENDFOR»
      }
    '''
  }

}
