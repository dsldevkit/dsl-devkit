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

import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.internal.xtend.expression.parser.SyntaxConstants;
import org.eclipse.xtend.expression.ExecutionContextImpl;
import org.eclipse.xtend.expression.ResourceManagerDefaultImpl;
import org.eclipse.xtend.expression.TypeSystemImpl;
import org.eclipse.xtend.expression.Variable;
import org.eclipse.xtend.typesystem.Type;
import org.eclipse.xtend.typesystem.emf.EmfRegistryMetaModel;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.ExportPackage;
import com.avaloq.tools.ddk.xtext.export.export.Extension;
import com.avaloq.tools.ddk.xtext.export.export.Import;
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorSupport;
import com.avaloq.tools.ddk.xtext.util.EObjectUtil;
import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * Various utility functions for the export language.
 */
public final class ExportGeneratorSupport extends GeneratorSupport {

  /**
   * Return a compilation context for Xtend executions during the generator run.
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
   * Helper class defining the execution context for an Xtend compilation during build generation.
   * Sets up the metamodels as needed.
   */
  private static class ExportExecutionContext extends ExecutionContextImpl {

    ExportExecutionContext(final ExportModel model) {
      super(new ResourceManagerDefaultImpl(), new ExportResource(model), new TypeSystemImpl(), new HashMap<String, Variable>(), null, null, null, null, null, null, null, null, null);
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
      registerMetaModel(new EmfRegistryMetaModel() {
        @Override
        public EPackage[] allPackages() {
          return ePackages;
        }

        @Override
        public Type getTypeForName(final String name) {
          final String[] frags = name.split(SyntaxConstants.NS_DELIM);
          if (frags.length == 2) {
            // convert references which use import alias
            for (Import imp : model.getImports()) {
              if (frags[0].equals(imp.getName()) && imp.getPackage() != null) {
                return super.getTypeForName(imp.getPackage().getName() + SyntaxConstants.NS_DELIM + frags[1]);
              }
            }
          }
          return super.getTypeForName(name);
        }
      });
      // Finally, add the default meta models
      // registerMetaModel(new EmfRegistryMetaModel());
      // registerMetaModel(new JavaBeansMetaModel());
    }
  }

  /**
   * "Fake" resource for Xtend compilation that gives correct extensions and package imports depending on whether
   * we're running Xtend inside the export section or the scoping section.
   */
  private static class ExportResource implements org.eclipse.xtend.expression.Resource {

    private final ExportModel model;
    private String qualifiedName;
    private List<String> importedExtensions;
    private Iterable<String> importedNamespaces;

    ExportResource(final ExportModel model) {
      this.model = model;
    }

    /** {@inheritDoc} */
    @Override
    public String getFullyQualifiedName() {
      if (qualifiedName == null) {
        this.setFullyQualifiedName(model.eResource().getURI().path());
      }
      return qualifiedName;
    }

    /** {@inheritDoc} */
    @Override
    public String[] getImportedExtensions() {
      if (importedExtensions == null) {
        importedExtensions = Lists.transform(model.getExtensions(), new Function<Extension, String>() {
          @Override
          public String apply(final Extension from) {
            return from.getExtension();
          }
        });
      }
      // Hmmm... do we have to care about re-exported extensions? Or does Xtend do that by itself?
      return importedExtensions.toArray(new String[importedExtensions.size()]);
    }

    /** {@inheritDoc} */
    @Override
    public String[] getImportedNamespaces() {
      if (importedNamespaces == null) {
        importedNamespaces = Iterables.filter(Iterables.transform(model.getImports(), new Function<Import, String>() {
          @Override
          public String apply(final Import from) {
            if (from.getPackage() == null) {
              return null;
            }
            final String name = from.getPackage().getName();
            if (name == null || name.length() == 0) {
              return null;
            }
            return name;
          }
        }), Predicates.<String> notNull());
      }
      return Lists.newArrayList(importedNamespaces).toArray(new String[0]);
    }

    @Override
    public void setFullyQualifiedName(final String fqn) {
      qualifiedName = fqn;
    }

  }

}
