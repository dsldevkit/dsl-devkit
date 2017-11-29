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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.mwe.core.WorkflowInterruptedException;
import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xtend.expression.ExecutionContextImpl;
import org.eclipse.xtend.expression.ResourceManagerDefaultImpl;
import org.eclipse.xtend.expression.TypeSystemImpl;
import org.eclipse.xtend.expression.Variable;
import org.eclipse.xtend.type.impl.java.JavaBeansMetaModel;
import org.eclipse.xtend.typesystem.emf.EmfRegistryMetaModel;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.generator.Generator;
import org.eclipse.xtext.resource.ClasspathUriResolutionException;
import org.eclipse.xtext.validation.Issue;

import com.avaloq.tools.ddk.xtext.generator.expression.CompilationContext;
import com.avaloq.tools.ddk.xtext.generator.util.EClassComparator;
import com.avaloq.tools.ddk.xtext.generator.util.ModelValidator;
import com.avaloq.tools.ddk.xtext.scope.ScopeStandaloneSetup;
import com.avaloq.tools.ddk.xtext.scope.scope.Casing;
import com.avaloq.tools.ddk.xtext.scope.scope.Injection;
import com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingSection;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule;
import com.avaloq.tools.ddk.xtext.util.EObjectUtil;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.inject.Injector;


/**
 * Various utility functions for the scoping generator.
 */
public final class ScopingGeneratorUtil {

  /** Class-wide logger. */
  private static final Logger LOG = Logger.getLogger(ScopingGeneratorUtil.class);

  /** Guice injector for scope DSL. */
  private static final Injector INJECTOR = new ScopeStandaloneSetup().createInjector();

  /** Resource checker used to validate loaded model. */
  private static final ModelValidator VALIDATOR = INJECTOR.getInstance(ModelValidator.class);

  /**
   * Inhibit public instantiation.
   */
  private ScopingGeneratorUtil() {
    // No public constructor
  }

  /**
   * Returns a URI corresponding to the default location for scope files. This is in the SRC outlet where the Xtext grammar file usually is.
   *
   * @param grammar
   *          Xtext grammar to get scope file URI for
   * @param ctx
   *          xpand execution context (defines required SRC outlet)
   * @return the file URI to the default scope file location
   */
  public static URI getDefaultScopeLocation(final Grammar grammar, final XpandExecutionContext ctx) {
    final String xmiPath = GrammarUtil.getClasspathRelativePathToXmi(grammar);
    return URI.createFileURI(new File(ctx.getOutput().getOutlet(Generator.SRC).getPath(), xmiPath).getAbsolutePath()).trimFileExtension().appendFileExtension("scope"); //$NON-NLS-1$
  }

  /**
   * Retrieve the scope model associated with a given grammar. Expected to either be in same folder with the same name (except for the extension) or in the SRC
   * outlet.
   *
   * @param grammar
   *          the grammar
   * @param ctx
   *          xpand execution context
   * @return the scope model
   * @throws FileNotFoundException
   *           thrown if the scope file could not be found
   */
  @SuppressWarnings("nls")
  public static ScopeModel getScopeModel(final Grammar grammar, final XpandExecutionContext ctx) throws FileNotFoundException {
    final Resource grammarResource = grammar.eResource();
    if (grammarResource == null) {
      return null;
    }

    final ResourceSet resourceSet = grammarResource.getResourceSet();
    URI uri = grammarResource.getURI().trimFileExtension().appendFileExtension("scope");

    Resource scopeResource = null;
    try {
      scopeResource = resourceSet.getResource(uri, true);
    } catch (final ClasspathUriResolutionException e) {
      // make another attempt
      uri = getDefaultScopeLocation(grammar, ctx);
      try {
        scopeResource = resourceSet.getResource(uri, true);
      } catch (WrappedException e1) {
        scopeResource = resourceSet.getResource(uri, false);
        if (scopeResource != null) {
          resourceSet.getResources().remove(scopeResource);
        }
        throw new FileNotFoundException(uri.toString()); // NOPMD
      }
    }

    if (scopeResource == null) {
      throw new FileNotFoundException(uri.toString());
    }
    final List<Issue> issues = VALIDATOR.validate(scopeResource, LOG);

    for (final Issue issue : issues) {
      if (issue.isSyntaxError() || issue.getSeverity() == Severity.ERROR) {
        throw new WorkflowInterruptedException("Errors found in " + uri.toString());
      }
    }

    return scopeResource.getContents().size() == 0 ? null : (ScopeModel) scopeResource.getContents().get(0);
  }

  /**
   * Return a compilation context for Xtend executions during the generator run.
   *
   * @param model
   *          the ScopeModel for which we generate
   * @return the {@link CompilationContext}
   */
  public static CompilationContext getCompilationContext(final ScopeModel model) {
    return new CompilationContext(new ScopeExecutionContext(model));
  }

  /**
   * Naming function for a cross reference feature.
   * If there are multiple assignments within the containing rule having the same feature name,
   * the returned name will be suffixed by a number, e.g. type1.
   *
   * @param ele
   *          the assigned cross reference
   * @return unique feature name of the assignment
   */
  public static String getUniqueFeatureName(final CrossReference ele) {
    final Assignment assignment = GrammarUtil.containingAssignment(ele);
    final AbstractRule rule = GrammarUtil.containingRule(ele);
    final String featureName = assignment.getFeature();
    List<Assignment> identicalFeatureNameAssignments = Lists.newArrayList(Iterables.filter(GrammarUtil.containedAssignments(rule), new Predicate<Assignment>() {
      @Override
      public boolean apply(final Assignment input) {
        return featureName.equals(input.getFeature());
      }
    }));
    if (identicalFeatureNameAssignments.size() > 1) {
      return featureName + identicalFeatureNameAssignments.indexOf(assignment);
    }
    return featureName;
  }

  /**
   * Helper class defining the execution context for an Xtend compilation during scope generation.
   * Sets up the metamodels as needed.
   */
  private static class ScopeExecutionContext extends ExecutionContextImpl {

    private static final String VAR_ORIGINAL_RESOURCE = "originalResource";

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
      final EPackage[] ePackages = Lists.newArrayList(Iterables.transform(EObjectUtil.getScopeProviderByEObject(model).getScope(model, ScopePackage.Literals.IMPORT__PACKAGE).getAllElements(), d -> (EPackage) d.getEObjectOrProxy())).toArray(new EPackage[0]);
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
      return importedNamespaces.toArray(new String[0]);
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
      NamingSection naming = EObjectUtil.eContainer(expr, ScopeModel.class).getNaming();
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
