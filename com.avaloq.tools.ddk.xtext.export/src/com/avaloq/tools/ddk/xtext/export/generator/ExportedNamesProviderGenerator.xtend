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
import com.avaloq.tools.ddk.xtext.expression.generator.CodeGenerationX
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.Naming
import com.google.inject.Inject
import org.eclipse.emf.ecore.EClass

class ExportedNamesProviderGenerator {

  @Inject extension CodeGenerationX
  @Inject extension GeneratorUtilX
  @Inject extension Naming
  @Inject extension ExportGeneratorX

  def generate(ExportModel it, CompilationContext ctx, extension GenModelUtilX genModelUtil) {
    val grammar = grammar
    '''
      package «exportedNamesProvider.toJavaPackage()»;

      import org.eclipse.emf.ecore.EClass;
      import org.eclipse.emf.ecore.EObject;
      import org.eclipse.emf.ecore.EPackage;
      import org.eclipse.xtext.naming.QualifiedName;

      import com.avaloq.tools.ddk.xtext.naming.AbstractExportedNameProvider;


      /**
       * Qualified name provider for grammar «grammar?.name?:exportedNamesProvider.toSimpleName» providing the qualified names for exported objects.
       */
      public class «exportedNamesProvider.toSimpleName» extends AbstractExportedNameProvider {

        «IF !exports.isEmpty»
          «val types = exports»
          @Override
          public QualifiedName qualifiedName(final EObject object) {
            EClass eClass = object.eClass();
            EPackage ePackage = eClass.getEPackage();
            «val exportedEClasses = types.map[type].toSet()»
            «val exportsMap = types.sortedExportsByEPackage()»
            «FOR p : exportsMap.keySet().sortBy[nsURI]»
              if (ePackage == «p.qualifiedPackageInterfaceName()».eINSTANCE) {
                int classifierID = eClass.getClassifierID();
                switch (classifierID) {
                «FOR c : p.EClassifiers.filter(EClass).filter(c|exportedEClasses.exists(e|e.isSuperTypeOf(c)))»
                  case «c.classifierIdLiteral()»: {
                    return qualifiedName((«c.instanceClassName()») object);
                  }
                «ENDFOR»
                default:
                  return null;
                }
              }
            «ENDFOR»
            return null;
          }

          «FOR c : types»
            /**
             * Return the qualified name under which a «c.type.name» object is exported, or <code>null</code> if the object should not be exported.
             *
             * @param obj
             *          The object to be exported
             * @return The object's qualified name, or <code>null</code> if the object is not to be exported
             */
            protected QualifiedName qualifiedName(final «c.type.instanceClassName()» obj) {
              «javaContributorComment(c.location())»
              «IF c.naming != null»
                final Object name = «c.naming.javaExpression(ctx.clone('obj', c.type))»;
                return name != null ? «IF c.qualifiedName»getConverter().toQualifiedName(String.valueOf(name))«ELSE»qualifyWithContainerName(obj, String.valueOf(name))«ENDIF» : null;
              «ELSE»
                return «IF c.qualifiedName»getConverter().toQualifiedName(getResolver().apply(obj))«ELSE»qualifyWithContainerName(obj, getResolver().apply(obj))«ENDIF»; // "name" attribute by default
              «ENDIF»
            }

          «ENDFOR»
        «ENDIF»
      }
    '''
  }
}
