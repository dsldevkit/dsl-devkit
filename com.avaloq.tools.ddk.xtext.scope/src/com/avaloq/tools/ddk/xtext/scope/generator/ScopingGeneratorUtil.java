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
package com.avaloq.tools.ddk.xtext.scope.generator;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;

import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext;
import com.avaloq.tools.ddk.xtext.expression.generator.EClassComparator;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.type.DefaultXtendExecutionContext;
import com.avaloq.tools.ddk.xtext.expression.generator.type.EmfRegistryMetaModel;
import com.avaloq.tools.ddk.xtext.expression.generator.type.XtendType;
import com.avaloq.tools.ddk.xtext.expression.generator.type.XtendVariable;
import com.avaloq.tools.ddk.xtext.scope.scope.Casing;
import com.avaloq.tools.ddk.xtext.scope.scope.Import;
import com.avaloq.tools.ddk.xtext.scope.scope.Injection;
import com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingSection;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule;
import com.avaloq.tools.ddk.xtext.util.EObjectUtil;
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
   * Return a compilation context for executions during the generator run.
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
   * Helper class defining the execution context for a compilation during scope generation.
   * Sets up the metamodels as needed.
   */
  private static class ScopeExecutionContext extends DefaultXtendExecutionContext {

    private static final String VAR_ORIGINAL_RESOURCE = "originalResource"; //$NON-NLS-1$

    ScopeExecutionContext(final ScopeModel model) {
      super(getVariables(model));
      registerMetaModels(model);
    }

    /**
     * Returns the variables which should be visible to the expressions.
     *
     * @param model
     *          context scope model, must not be {@code null}
     * @return map of variables, never {@code null}
     */
    private static Map<String, XtendVariable> getVariables(final ScopeModel model) {
      Map<String, XtendVariable> result = Maps.newLinkedHashMap();
      result.put(VAR_ORIGINAL_RESOURCE, new XtendVariable(VAR_ORIGINAL_RESOURCE, null));
      for (ScopeModel scopeModel : getAllScopeModels(model)) {
        for (Injection injection : scopeModel.getInjections()) {
          result.putIfAbsent(injection.getName(), new XtendVariable(injection.getName(), null));
        }
      }
      return ImmutableMap.copyOf(result);
    }

    /**
     * Registers all metamodels accessible to the scope model in the execution context.
     *
     * @param model
     *          scope model to register metamodels for
     */
    private void registerMetaModels(final ScopeModel model) {
      // First, create one meta model that has all the packages that are visible. Use the scope provider to get that list,
      // then convert to a list of EPackages.
      final EPackage[] ePackages = Lists.newArrayList(Iterables.transform(EObjectUtil.getScopeProviderByEObject(model).getScope(model, ScopePackage.Literals.IMPORT__PACKAGE).getAllElements(), d -> (EPackage) EcoreUtil.resolve(d.getEObjectOrProxy(), model))).toArray(new EPackage[0]);
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

  /**
   * Determine whether a certain scope expression is case sensitive or not.
   *
   * @param expr
   *          The scope expression.
   * @return true, if the expression is case insensitive; false otherwise.
   */
  public static boolean isCaseInsensitive(final NamedScopeExpression expr) {
    Casing casing;
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
