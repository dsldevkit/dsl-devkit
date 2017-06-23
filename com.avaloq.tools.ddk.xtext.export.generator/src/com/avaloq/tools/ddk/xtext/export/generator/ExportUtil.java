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
package com.avaloq.tools.ddk.xtext.export.generator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtend.expression.ExecutionContextImpl;
import org.eclipse.xtend.expression.ResourceManagerDefaultImpl;
import org.eclipse.xtend.expression.TypeSystemImpl;
import org.eclipse.xtend.expression.Variable;
import org.eclipse.xtend.type.impl.java.JavaBeansMetaModel;
import org.eclipse.xtend.typesystem.emf.EmfRegistryMetaModel;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.avaloq.tools.ddk.xtext.export.export.Export;
import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.ExportPackage;
import com.avaloq.tools.ddk.xtext.export.export.Extension;
import com.avaloq.tools.ddk.xtext.export.export.Import;
import com.avaloq.tools.ddk.xtext.generator.expression.CompilationContext;
import com.avaloq.tools.ddk.xtext.generator.util.EClassComparator;
import com.avaloq.tools.ddk.xtext.generator.util.GeneratorUtil;
import com.avaloq.tools.ddk.xtext.util.EObjectUtil;
import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;


/**
 * Various utility functions for the export language.
 */
public final class ExportUtil {

  /**
   * Returns the EClass associated with an Export object.
   */
  private static final class ExportEClassFunction implements Function<Export, EClass> {
    /** {@inheritDoc} */
    @Override
    public EClass apply(final Export from) {
      return from.getType();
    }
  }

  /**
   * Inhibit public instantiation.
   */
  private ExportUtil() {
    // No public constructor
  }

  /**
   * Return a compilation context for Xtend executions during the generator run.
   *
   * @param model
   *          the BuildDescription for which we generate
   * @return the {@link CompilationContext}
   */
  public static CompilationContext getCompilationContext(final ExportModel model) {
    return new CompilationContext(new ExportExecutionContext(model));
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
          return (EPackage) from.getEObjectOrProxy();
        }
      })).toArray(new EPackage[0]);
      registerMetaModel(new EmfRegistryMetaModel() {
        @Override
        public EPackage[] allPackages() {
          return ePackages;
        }
      });
      // Finally, add the default meta models
      registerMetaModel(new EmfRegistryMetaModel());
      registerMetaModel(new JavaBeansMetaModel());
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

  /**
   * Equivalent to {@link com.avaloq.tools.ddk.xtext.export.export.Export#getAllEAttributes()}, but static, so that it can be used from
   * Xtend.
   *
   * @param export
   *          the export clause to query
   * @return the list of all EAttributes that are exported by an export clause. This includes the attributes,
   *         plus the export key, if it is an attribute, plus all attributes exported from supertypes of the
   *         exported type.
   */
  public static List<EAttribute> getAllExportedEAttributes(final com.avaloq.tools.ddk.xtext.export.export.Export export) {
    return export.getAllEAttributes();
  }

  /**
   * Sort exports according to type (more specific rules must come first).
   *
   * @param exports
   *          exports to sort
   * @return sorted list of all exports
   */
  public static List<Export> sortedExports(final Collection<Export> exports) {
    return EClassComparator.sortedGroups(exports, new ExportEClassFunction());
  }

  /**
   * Sort exports according to package and type (more specific rules must come first).
   *
   * @param exports
   *          exports to sort
   * @return sorted map of all exports
   */
  public static ListMultimap<EPackage, Export> sortedExportsByEPackage(final Collection<Export> exports) {
    return EClassComparator.sortedEPackageGroups(exports, new ExportEClassFunction());
  }

  /**
   * Returns a type map for the given exports.
   *
   * @param exports
   *          export objects to map
   * @param grammar
   *          Xtext grammar
   * @return mappings
   */
  public static Map<EClass, Export> typeMap(final Collection<Export> exports, final Grammar grammar) {
    return GeneratorUtil.typeMap(exports, grammar, new ExportEClassFunction());
  }

  /**
   * Returns a value from a map using the specified key.
   *
   * @param map
   *          the map
   * @param key
   *          the key
   * @return the value or {@code null}
   */
  public static Object get(final Map<Object, Object> map, final Object key) {
    return map.get(key);
  }

  /**
   * Returns a value from a multimap using the specified key.
   *
   * @param map
   *          the map
   * @param key
   *          the key
   * @return the value or {@code null}
   */
  public static List<? extends Object> get(final ListMultimap<Object, Object> map, final Object key) {
    return map.get(key);
  }
}

