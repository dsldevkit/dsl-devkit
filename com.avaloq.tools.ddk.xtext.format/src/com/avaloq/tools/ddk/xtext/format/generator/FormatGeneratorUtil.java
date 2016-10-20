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
package com.avaloq.tools.ddk.xtext.format.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.mwe.core.WorkflowInterruptedException;
import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xtend.expression.Variable;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.generator.Generator;
import org.eclipse.xtext.resource.ClasspathUriResolutionException;
import org.eclipse.xtext.validation.Issue;

import com.avaloq.tools.ddk.xtext.format.FormatConstants;
import com.avaloq.tools.ddk.xtext.format.FormatStandaloneSetup;
import com.avaloq.tools.ddk.xtext.format.format.Constant;
import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference;
import com.avaloq.tools.ddk.xtext.format.format.GrammarRule;
import com.avaloq.tools.ddk.xtext.format.format.MatcherType;
import com.avaloq.tools.ddk.xtext.format.format.Rule;
import com.avaloq.tools.ddk.xtext.format.format.WildcardRule;
import com.avaloq.tools.ddk.xtext.generator.util.ModelValidator;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


/**
 * Various utility functions for the format generator.
 */
public final class FormatGeneratorUtil {

  /** Class-wide logger. */
  private static final Logger LOG = Logger.getLogger(FormatGeneratorUtil.class);

  /** Resource checker used to validate loaded model. */
  private static ModelValidator modelValidator;
  private static Object lock = new Object();

  /**
   * Inhibit public instantiation.
   */
  private FormatGeneratorUtil() {
    // No public constructor
  }

  /**
   * Returns a URI corresponding to the default location for format files. This is in the SRC outlet where the Xtext grammar file usually is.
   *
   * @param grammar
   *          Xtext grammar to get format file URI for
   * @param ctx
   *          xpand execution context (defines required SRC outlet)
   * @return the file URI to the default format file location
   */
  public static URI getDefaultFormatLocation(final Grammar grammar, final XpandExecutionContext ctx) {
    final String xmiPath = GrammarUtil.getClasspathRelativePathToXmi(grammar);
    return URI.createFileURI(new File(ctx.getOutput().getOutlet(Generator.SRC).getPath(), xmiPath).getAbsolutePath()).trimFileExtension().appendFileExtension(FormatConstants.FILE_EXTENSION);
  }

  /**
   * Get the qualified class name of the formatter. The class name is prefixed with classNamePrefix.
   *
   * @param grammar
   *          the format grammar
   * @param classNamePrefix
   *          the class prefix - may be null
   * @return String fully qualified name of the formatter class
   */
  public static String getFormatterName(final Grammar grammar, final String classNamePrefix) {
    return GrammarUtil.getNamespace(grammar) + ".formatting." + (classNamePrefix == null ? "" : classNamePrefix) + GrammarUtil.getName(grammar) + "Formatter";
  }

  /**
   * Retrieve the format model associated with a given grammar.
   * <p>
   * <em>Note</em>: Expected to either be in same folder with the same name (except for the extension) or in the SRC outlet.
   * </p>
   *
   * @param grammar
   *          the grammar, must not be {@code null}
   * @param context
   *          xpand execution context, must not be {@code null}
   * @return the format model, or {@code null} if the resource could not be loaded
   * @throws FileNotFoundException
   *           thrown if the format file could not be found
   */
  @SuppressWarnings("nls")
  public static FormatConfiguration getFormatModel(final Grammar grammar, final XpandExecutionContext context) throws FileNotFoundException {
    Variable resourceUriVariable = context.getVariable("resourceUri");
    if (resourceUriVariable == null) {
      return null;
    }
    URI uri = (URI) resourceUriVariable.getValue();
    final Resource grammarResource = grammar.eResource();
    final ResourceSet resourceSet = grammarResource.getResourceSet();
    Resource formatResource = null;
    try {
      formatResource = resourceSet.getResource(uri, true);
    } catch (final ClasspathUriResolutionException e) {
      // make another attempt
      uri = getDefaultFormatLocation(grammar, context);
      try {
        formatResource = resourceSet.getResource(uri, true);
      } catch (WrappedException e1) {
        formatResource = resourceSet.getResource(uri, false);
        if (formatResource != null) {
          resourceSet.getResources().remove(formatResource);
        }
        throw new FileNotFoundException(uri.toString()); // NOPMD
      }
    }
    if (formatResource == null) {
      throw new FileNotFoundException(uri.toString());
    }

    final List<Issue> issues = getModelValidator().validate(formatResource, LOG);

    for (final Issue issue : issues) {
      if (issue.isSyntaxError() || issue.getSeverity() == Severity.ERROR) {
        throw new WorkflowInterruptedException("Errors found in " + uri.toString() + ": " + issue.getMessage());
      }
    }

    return formatResource.getContents().size() == 0 ? null : (FormatConfiguration) formatResource.getContents().get(0);
  }

  /**
   * Returns the {@link ModelValidator}.
   *
   * @return the {@link ModelValidator}, never {@code null}
   */
  private static ModelValidator getModelValidator() {
    synchronized (lock) {
      if (modelValidator == null) {
        modelValidator = new FormatStandaloneSetup().createInjector().getInstance(ModelValidator.class);
      }
      return modelValidator;
    }
  }

  /**
   * Gets the all GrammarRules in the inheritance hierarchy excluding overridden rules.
   *
   * @param model
   *          the model
   * @param targetClass
   *          the target class
   * @return all unique grammar rules
   */
  private static List<GrammarRule> getAllUniqueGrammarRules(final FormatConfiguration model, final Class<?> targetClass) {
    final Set<AbstractRule> grammarElements = Sets.newHashSet();
    final Set<String> grammarElementNames = Sets.newHashSet(); // ensure that identically named rules in a parent grammar are not returned
    List<GrammarRule> allRules = getAllGrammarRules(model);
    List<GrammarRule> result = Lists.newArrayList();
    for (int i = allRules.size() - 1; i > -1; i--) {
      GrammarRule rule = allRules.get(i);
      if (grammarElements.add(rule.getTargetRule()) && targetClass.isInstance(rule.getTargetRule())
          && grammarElementNames.add(rule.getTargetRule().getName())) {
        result.add(0, rule);
      }
    }
    return result;
  }

  /**
   * Gets all GrammarRules in the inheritance hierarchy, including overridden rules.
   *
   * @param model
   *          the model
   * @return the all grammar rules
   */
  private static List<GrammarRule> getAllGrammarRules(final FormatConfiguration model) {
    List<GrammarRule> allRules = Lists.newArrayList();
    if (model.getExtendedFormatConfiguration() != null && !model.getExtendedFormatConfiguration().eIsProxy()) {
      allRules.addAll(getAllGrammarRules(model.getExtendedFormatConfiguration()));
    }
    Predicate<Rule> resolvedGrammarRules = new Predicate<Rule>() {
      @Override
      public boolean apply(final Rule input) {
        return input instanceof GrammarRule && ((GrammarRule) input).getTargetRule() != null && !((GrammarRule) input).getTargetRule().eIsProxy();
      }
    };
    for (Rule rule : Iterables.filter(model.getRules(), resolvedGrammarRules)) {
      allRules.add((GrammarRule) rule);
    }
    return allRules;
  }

  /**
   * Gets the ParserRule GrammarRules, excluding overridden ones.
   *
   * @param model
   *          the model
   * @return the parser rules
   */
  public static List<GrammarRule> getParserRules(final FormatConfiguration model) {
    return getAllUniqueGrammarRules(model, ParserRule.class);
  }

  /**
   * Gets the TerminalRule GrammarRules, excluding overridden ones.
   *
   * @param model
   *          the model
   * @return the terminal rules
   */
  public static List<GrammarRule> getTerminalRules(final FormatConfiguration model) {
    return getAllUniqueGrammarRules(model, TerminalRule.class);
  }

  /**
   * Gets the EnumRule GrammarRules, excluding overridden ones.
   *
   * @param model
   *          the model
   * @return the enum rules
   */
  public static List<GrammarRule> getEnumRules(final FormatConfiguration model) {
    return getAllUniqueGrammarRules(model, EnumRule.class);
  }

  /**
   * Gets the wildcard rule.
   *
   * @param model
   *          the model
   * @return the wildcard rule
   */
  public static WildcardRule getWildcardRule(final FormatConfiguration model) {
    Iterable<WildcardRule> filteredRules = Iterables.filter(model.getRules(), WildcardRule.class);
    if (!Iterables.isEmpty(filteredRules)) {
      return filteredRules.iterator().next();
    } else if (model.getExtendedFormatConfiguration() != null && !model.getExtendedFormatConfiguration().eIsProxy()) {
      return getWildcardRule(model.getExtendedFormatConfiguration());
    } else {
      return null;
    }
  }

  /**
   * Gets all constants found in the inheritance hierarchy.
   *
   * @param model
   *          the model
   * @return the all constants
   */
  public static List<Constant> getAllConstants(final FormatConfiguration model) {
    List<Constant> result = Lists.newArrayList();
    if (model.getExtendedFormatConfiguration() != null && !model.getExtendedFormatConfiguration().eIsProxy()) {
      result.addAll(getAllConstants(model.getExtendedFormatConfiguration()));
    }
    result.addAll(model.getConstants());
    return result;
  }

  /**
   * Indicates whether given {@link GrammarElementReference} is contained by a {@link ParserRule} i.e. whether targetRule of the {@link GrammarRule} containing
   * given element is a instanceof {@link ParserRule}.
   *
   * @param grammarElementReference
   *          given grammar element
   * @return true if given grammar element is contained by a parser rule
   */
  public static Boolean containedByParserRule(final GrammarElementReference grammarElementReference) {
    GrammarRule container = EcoreUtil2.getContainerOfType(grammarElementReference, GrammarRule.class);
    return (container != null && container.getTargetRule() instanceof ParserRule);
  }

  /**
   * Checks if a given matcher is a two argument matcher (indicator of beginning and the end of the matcher locators validity).
   *
   * @param matcherType
   *          given {@link MatcherType}
   * @return true is given matcher type is two argument
   */
  public static Boolean isTwoArgumentMatcherType(final MatcherType matcherType) {
    return matcherType == MatcherType.BETWEEN || matcherType == MatcherType.RANGE;
  }

}
