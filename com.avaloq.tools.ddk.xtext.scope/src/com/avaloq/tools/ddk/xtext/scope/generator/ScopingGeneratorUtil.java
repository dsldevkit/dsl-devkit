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
package com.avaloq.tools.ddk.xtext.scope.generator;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.internal.xtend.expression.parser.SyntaxConstants;
import org.eclipse.xtend.expression.ExecutionContextImpl;
import org.eclipse.xtend.expression.ResourceManagerDefaultImpl;
import org.eclipse.xtend.expression.TypeSystemImpl;
import org.eclipse.xtend.expression.Variable;
import org.eclipse.xtend.typesystem.Type;
import org.eclipse.xtend.typesystem.emf.EmfRegistryMetaModel;
import org.eclipse.xtext.EcoreUtil2;

import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext;
import com.avaloq.tools.ddk.xtext.expression.generator.EClassComparator;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.scope.scope.Casing;
import com.avaloq.tools.ddk.xtext.scope.scope.Import;
import com.avaloq.tools.ddk.xtext.scope.scope.Injection;
import com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingSection;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule;
import com.avaloq.tools.ddk.xtext.util.EObjectUtil;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;


/**
 * Various utility functions for the scoping generator.
 */
// CHECKSTYLE:COUPLING-OFF
public final class ScopingGeneratorUtil {
  // CHECKSTYLE:COUPLING-ON

  /**
   * Inhibit public instantiation.
   */
  private ScopingGeneratorUtil() {
    // No public constructor
  }

  /**
   * Return a compilation context for Xtend executions during the generator run.
   *
   * @param model
   *          the ScopeModel for which we generate
   * @param genModelUtil
   *          GenModel util, must not be {@code null}
   * @return the {@link CompilationContext}
   */
  public static CompilationContext getCompilationContext(final ScopeModel model, final GenModelUtilX genModelUtil) {
    return new CompilationContext(new ScopeExecutionContext(model), genModelUtil);
  }

  /**
   * Helper class defining the execution context for an Xtend compilation during scope generation.
   * Sets up the metamodels as needed.
   */
  private static class ScopeExecutionContext extends ExecutionContextImpl {

    private static final String VAR_ORIGINAL_RESOURCE = "originalResource"; //$NON-NLS-1$

    ScopeExecutionContext(final ScopeModel model) {
      super(new ResourceManagerDefaultImpl(), new ScopeResource(model), new TypeSystemImpl(), getVariables(model), null, null, null, null, null, null, null, null, null);
      registerMetaModels(model);
    }

    /**
     * Returns the variables which should be visible to the Xtend expressions.
     *
     * @param model
     *          context scope model, must not be {@code null}
     * @return map of variables, never {@code null}
     */
    private static Map<String, Variable> getVariables(final ScopeModel model) {
      Map<String, Variable> result = Maps.newLinkedHashMap();
      result.put(VAR_ORIGINAL_RESOURCE, new Variable(VAR_ORIGINAL_RESOURCE, null));
      for (ScopeModel scopeModel : getAllScopeModels(model)) {
        for (Injection injection : scopeModel.getInjections()) {
          result.putIfAbsent(injection.getName(), new Variable(injection.getName(), null));
        }
      }
      return ImmutableMap.copyOf(result);
    }

    /**
     * Registers all metamodels accessible to the scope model in the Xtend execution context.
     *
     * @param model
     *          scope model to register metamodels for
     */
    private void registerMetaModels(final ScopeModel model) {
      // First, create one meta model that has all the packages that are visible. Use the scope provider to get that list,
      // then convert to a list of EPackages.
      final EPackage[] ePackages = Lists.newArrayList(Iterables.transform(EObjectUtil.getScopeProviderByEObject(model).getScope(model, ScopePackage.Literals.IMPORT__PACKAGE).getAllElements(), d -> (EPackage) EcoreUtil.resolve(d.getEObjectOrProxy(), model))).toArray(new EPackage[0]);
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
  private static class ScopeResource implements org.eclipse.xtend.expression.Resource {

    private final ScopeModel model;
    private String qualifiedName;
    private Set<String> importedExtensions;
    private Set<String> importedNamespaces;

    ScopeResource(final ScopeModel model) {
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
        importedExtensions = Sets.newLinkedHashSet();
        for (ScopeModel included : getAllScopeModels(model)) {
          importedExtensions.addAll(Lists.transform(included.getExtensions(), e -> e.getExtension()));
        }
      }
      return importedExtensions.toArray(new String[importedExtensions.size()]);
    }

    /** {@inheritDoc} */
    @Override
    public String[] getImportedNamespaces() {
      if (importedNamespaces == null) {
        importedNamespaces = Sets.newLinkedHashSet();
        for (ScopeModel included : getAllScopeModels(model)) {
          importedNamespaces.addAll(Collections2.filter(Lists.transform(included.getImports(), i -> {
            if (i.getPackage() != null) {
              return i.getPackage().getName();
            }
            return null;
          }), Predicates.notNull()));
        }
      }
      return importedNamespaces.toArray(new String[importedNamespaces.size()]);
    }

    @Override
    public void setFullyQualifiedName(final String fqn) {
      qualifiedName = fqn;
    }

  }

  /**
   * Determine whether a certain scope expression is case sensitive or not.
   *
   * @param expr
   *          The scope expression.
   * @return true, if the expression is case insensitive; false otherwise.
   */
  public static boolean isCaseInsensitive(final NamedScopeExpression expr) {
    Casing casing = null;
    if (expr.isCaseDef()) {
      casing = expr.getCasing();
    } else {
      NamingSection naming = EcoreUtil2.getContainerOfType(expr, ScopeModel.class).getNaming();
      casing = naming != null ? naming.getCasing() : Casing.SENSITIVE;
    }
    return casing == Casing.INSENSITIVE;
  }

  /**
   * Sort rules according to context type (more specific rules must come first).
   *
   * @param rules
   *          rules to sort
   * @return sorted list of all rules
   */
  public static List<ScopeRule> sortedRules(final Collection<ScopeRule> rules) {
    return EClassComparator.sortedGroups(rules, r -> r.getContext().getContextType());
  }

  /**
   * Returns a set containing the given model plus all (transitively) {@link ScopeModel#getIncludedScopes() included} models.
   *
   * @param model
   *          scope model, must not be {@code null}
   * @return set, never {@code null}
   */
  private static Set<ScopeModel> getAllScopeModels(final ScopeModel model) {
    Set<ScopeModel> result = Sets.newLinkedHashSet();
    getAllScopeModelsInternal(model, result);
    return result;
  }

  /**
   * Adds the given model plus all (transitively) {@link ScopeModel#getIncludedScopes() included} models to the given set.
   *
   * @param model
   *          scope model, must not be {@code null}
   * @param result
   *          set to add to, must not be {@code null}
   */
  private static void getAllScopeModelsInternal(final ScopeModel model, final Set<ScopeModel> result) {
    if (result.add(model)) {
      for (ScopeModel included : model.getIncludedScopes()) {
        getAllScopeModelsInternal(included, result);
      }
    }
  }

}
