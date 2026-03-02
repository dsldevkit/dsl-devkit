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
package com.avaloq.tools.ddk.xtext.export.generator;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.ExportPackage;
import com.avaloq.tools.ddk.xtext.export.export.Import;
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorSupport;
import com.avaloq.tools.ddk.xtext.expression.generator.type.DefaultXtendExecutionContext;
import com.avaloq.tools.ddk.xtext.expression.generator.type.EmfRegistryMetaModel;
import com.avaloq.tools.ddk.xtext.expression.generator.type.XtendType;
import com.avaloq.tools.ddk.xtext.util.EObjectUtil;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * Various utility functions for the export language.
 */
public final class ExportGeneratorSupport extends GeneratorSupport {

  /**
   * Return a compilation context for executions during the generator run.
   *
   * @param model
   *          the BuildDescription for which we generate
   * @param genModelUtil
   *          GenModel util, must not be {@code null}
   * @return the {@link CompilationContext}
   */
  public CompilationContext getCompilationContext(final ExportModel model, final GenModelUtilX genModelUtil) {
    return new CompilationContext(new ExportExecutionContext(model), genModelUtil);
  }

  /**
   * Helper class defining the execution context for a compilation during build generation.
   * Sets up the metamodels as needed.
   */
  private static class ExportExecutionContext extends DefaultXtendExecutionContext {

    /**
     * Creates a new execution context for the given export model.
     *
     * @param model
     *          the export model
     */
    ExportExecutionContext(final ExportModel model) {
      super();
      registerMetaModels(model);
    }

    /**
     * Register the metamodels for this execution context.
     *
     * @param model
     *          The model
     */
    private void registerMetaModels(final ExportModel model) {
      // First, create one meta model that has all the packages that are visible. Use the scope provider to get that list,
      // then convert to a list of EPackages.
      final EPackage[] ePackages = Lists.newArrayList(Iterables.transform(EObjectUtil.getScopeProviderByEObject(model).getScope(model, ExportPackage.Literals.IMPORT__PACKAGE).getAllElements(), new Function<IEObjectDescription, EPackage>() {
        @Override
        public EPackage apply(final IEObjectDescription from) {
          return (EPackage) EcoreUtil.resolve(from.getEObjectOrProxy(), model);
        }
      })).toArray(new EPackage[0]);
      registerMetaModel(new EmfRegistryMetaModel(ePackages) {
        @Override
        public XtendType getTypeForName(final String name) {
          if (name == null) {
            return null;
          }
          final String[] frags = name.split(NS_DELIM);
          if (frags.length == 2) {
            // convert references which use import alias
            for (Import imp : model.getImports()) {
              if (frags[0].equals(imp.getName()) && imp.getPackage() != null) {
                return super.getTypeForName(imp.getPackage().getName() + NS_DELIM + frags[1]);
              }
            }
          }
          return super.getTypeForName(name);
        }
      });
    }
  }

}
