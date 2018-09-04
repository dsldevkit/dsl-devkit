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
package com.avaloq.tools.ddk.xtext.format2.scoping;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CompoundElement;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;

import com.avaloq.tools.ddk.xtext.format2.Format2Constants;
import com.avaloq.tools.ddk.xtext.format2.format2.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.format2.format2.Format2Package;
import com.avaloq.tools.ddk.xtext.util.ParseTreeUtil;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


/**
 * This is a utility class which is only intended to be used in this package. It provides utility functions
 * for Format scoping.
 */
class FormatScopeUtil {

  private static final String XTEXT_EXTENSION = "xtext"; //$NON-NLS-1$

  /**
   * Returns all {@link CompoundElement} of type compoundType in a specific context.
   *
   * @param <T>
   *          - Group, UnorderedGroup or Alternatives
   * @param parent
   *          the context from which to return elements
   * @param compoundType
   *          the specific type of the compound elements
   * @return List a list with elements of type compoundType
   */
  @SuppressWarnings("unchecked")
  protected <T extends CompoundElement> List<T> getCompoundElements(final AbstractRule parent, final Class<T> compoundType) {
    AbstractElement alternatives = parent.getAlternatives();
    if (alternatives instanceof Alternatives) {
      Alternatives group = (Alternatives) alternatives;
      return Lists.newArrayList(Iterables.filter(group.getElements(), compoundType));
    } else if (alternatives instanceof CompoundElement) {
      return Lists.newArrayList(Iterables.filter(alternatives.eContents(), compoundType));
    } else if (compoundType.isInstance(alternatives)) {
      return Lists.newArrayList((T) alternatives);
    }
    return Collections.emptyList();
  }

  /**
   * Returns all {@link CompoundElement} of type compoundType in a specific context.
   *
   * @param <T>
   *          - Group, UnorderedGroup or Alternatives
   * @param parent
   *          the context from which to return elements
   * @param compoundType
   *          the specific type of the compound elements
   * @return List a list with elements of type compoundType
   */
  protected <T extends CompoundElement> List<T> getCompoundElements(final CompoundElement parent, final Class<T> compoundType) {
    return Lists.newArrayList(Iterables.filter(parent.getElements(), compoundType));
  }

  /**
   * Get all keywords visible in a specific context.
   *
   * @param element
   *          the context in which to collect keywords.
   * @return List a list with all keywords contained by this context
   */
  protected List<Keyword> getKeywords(final AbstractElement element) {
    return getFilteredEContents(element, Keyword.class);
  }

  /**
   * Get all keywords visible in a specific context which have a specific value.
   *
   * @param element
   *          the context in which to collect keywords.
   * @param value
   *          the value required of the Keywords
   * @return List a list with all keywords contained by this context
   */
  protected List<Keyword> getKeywords(final AbstractElement element, final String value) {
    return Lists.newArrayList(Iterables.filter(getKeywords(element), new Predicate<Keyword>() {
      @Override
      public boolean apply(final Keyword input) {
        return value.equals(input.getValue());
      }
    }));
  }

  /**
   * Get all keywords visible in a specific context.
   *
   * @param rule
   *          the context in which to collect keywords.
   * @return List a list with all keywords contained by this context
   */
  protected List<Keyword> getKeywords(final AbstractRule rule) {
    return getFilteredEContents(rule, Keyword.class);
  }

  /**
   * Get all keywords visible in a specific context which have a specific value.
   *
   * @param rule
   *          the context in which to collect keywords.
   * @param value
   *          the value required of the Keywords
   * @return List a list with all keywords contained by this context
   */
  protected List<Keyword> getKeywords(final AbstractRule rule, final String value) {
    return Lists.newArrayList(Iterables.filter(getKeywords(rule), new Predicate<Keyword>() {
      @Override
      public boolean apply(final Keyword input) {
        return value.equals(input.getValue());
      }
    }));
  }

  /**
   * Get all RuleCalls visible in a specific context.
   *
   * @param element
   *          the context in which to collect keywords.
   * @return List a list with all RuleCalls contained by this context
   */
  protected List<RuleCall> getRuleCalls(final AbstractElement element) {
    final List<RuleCall> ruleCalls = getFilteredEContents(element, RuleCall.class);
    return Lists.newArrayList(Iterables.filter(ruleCalls, new Predicate<RuleCall>() {
      @Override
      public boolean apply(final RuleCall input) {
        return input.getRule() != null && !input.getRule().eIsProxy();
      }
    }));
  }

  /**
   * Get all RuleCalls visible in a specific context.
   *
   * @param rule
   *          the context in which to collect keywords.
   * @return List a list with all RuleCalls contained by this context
   */
  protected List<RuleCall> getRuleCalls(final AbstractRule rule) {
    final List<RuleCall> ruleCalls = getFilteredEContents(rule, RuleCall.class);
    return Lists.newArrayList(Iterables.filter(ruleCalls, new Predicate<RuleCall>() {
      @Override
      public boolean apply(final RuleCall input) {
        return input.getRule() != null && !input.getRule().eIsProxy();
      }
    }));
  }

  /**
   * Get all Rules referenced from within a specific context.
   *
   * @param element
   *          the context in which to collect keywords.
   * @return List a list with all Rules contained by this context
   */
  protected List<AbstractRule> getReferencedRules(final AbstractElement element) {
    final List<RuleCall> ruleCalls = getRuleCalls(element);
    List<AbstractRule> rules = Lists.transform(ruleCalls, new Function<RuleCall, AbstractRule>() {
      @Override
      public AbstractRule apply(final RuleCall from) {
        return from.getRule();
      }
    });
    return Lists.newArrayList(Sets.newLinkedHashSet(Iterables.filter(rules, Predicates.notNull())).iterator());
  }

  /**
   * Get all Rules referenced from within a specific context.
   *
   * @param rule
   *          the context in which to collect keywords.
   * @return List a list with all Rules contained by this context
   */
  protected List<AbstractRule> getReferencedRules(final AbstractRule rule) {
    final List<RuleCall> ruleCalls = getRuleCalls(rule);
    List<AbstractRule> rules = Lists.transform(ruleCalls, new Function<RuleCall, AbstractRule>() {
      @Override
      public AbstractRule apply(final RuleCall from) {
        return from.getRule();
      }
    });
    return Lists.newArrayList(Sets.newLinkedHashSet(rules).iterator());
  }

  /**
   * Get all Assignments within a specific context.
   *
   * @param element
   *          the context in which to collect keywords.
   * @return List a list with all Assignments contained by this context
   */
  protected List<Assignment> getAssignments(final AbstractElement element) {
    return getFilteredEContents(element, Assignment.class);
  }

  /**
   * Get all Assignments within a specific context.
   *
   * @param rule
   *          the context in which to collect keywords.
   * @return List a list with all Assignments contained by this context
   */
  protected List<Assignment> getAssignments(final AbstractRule rule) {
    return getFilteredEContents(rule, Assignment.class);
  }

  /**
   * Collect all contents of a specific type.
   *
   * @param <T>
   *          type of the contents to collect
   * @param eObj
   *          the container of the elements
   * @param type
   *          type of the contents to collect
   * @return List a list with elements which have eObj as its container and which are of type
   */
  public <T> List<T> getFilteredEContents(final EObject eObj, final Class<T> type) {
    if (eObj != null && !eObj.eIsProxy()) {
      TreeIterator<EObject> eContents = eObj.eAllContents();
      return Lists.newArrayList(Iterators.filter(eContents, type));
    } else {
      return Lists.newArrayList();
    }
  }

  /**
   * Gets the all grammars of the given grammar.
   *
   * @param grammar
   *          the grammar
   * @return all grammars
   */
  protected List<Grammar> getAllGrammars(final Grammar grammar) {
    Collection<Grammar> visitedGrammars = new LinkedHashSet<Grammar>();
    collectAllUsedGrammars(grammar, visitedGrammars);
    return Lists.newArrayList(visitedGrammars);
  }

  /**
   * Recursively collects all used grammars.
   *
   * @param grammar
   *          the grammar
   * @param visited
   *          grammars
   */
  private void collectAllUsedGrammars(final Grammar grammar, final Collection<Grammar> visited) {
    if (grammar == null || !visited.add(grammar)) {
      return;
    }
    for (Grammar usedGrammar : grammar.getUsedGrammars()) {
      collectAllUsedGrammars(usedGrammar, visited);
    }
  }

  /**
   * Find the declared Xtext grammar.
   *
   * @param model
   *          the FormatConfiguration for which to retrieve the Grammar
   * @return Grammar or NULL if not found
   */
  public Grammar findTargetGrammar(final FormatConfiguration model) {
    final Resource resource = model.eResource();
    final ResourceSet resourceSet = resource.getResourceSet();
    EList<Resource> resources = resourceSet.getResources();
    Collection<Resource> grammarResources = Collections2.<Resource> filter(resources, new Predicate<Resource>() {
      @Override
      public boolean apply(final Resource r) {
        return r.getURI().lastSegment().equals(resource.getURI().trimFileExtension().appendFileExtension(XTEXT_EXTENSION).lastSegment());
      }
    });
    if (!grammarResources.isEmpty()) {
      return (Grammar) grammarResources.iterator().next().getContents().get(0);
    }
    return null;
  }

  /**
   * Retrieve the FormatConfiguration declared in the given FormatConfiguration.
   *
   * @param model
   *          the FormatConfiguration for which to retrieve the extended/super FormatConfiguration
   * @return extended FormatConfiguration or NULL
   */
  protected FormatConfiguration findExtendedFormatConfiguration(final FormatConfiguration model) {
    String extendedText = ParseTreeUtil.getParsedString(model, Format2Package.Literals.FORMAT_CONFIGURATION__EXTENDED_FORMAT_CONFIGURATION);
    if (extendedText != null) {
      URI extendedURI = findExtensionURI(model, extendedText);
      if (extendedURI != null) {
        Resource extendedResource = model.eResource().getResourceSet().getResource(extendedURI, true);
        if (extendedResource != null && !extendedResource.getContents().isEmpty() && extendedResource.getContents().get(0) instanceof FormatConfiguration) {
          return (FormatConfiguration) extendedResource.getContents().get(0);
        }
      }
    }
    return null;
  }

  /**
   * Find URI of extended FormatConfiguration.
   * The following heuristic is used to find the URI: replace the file name segment of the context's URI with extension name.
   *
   * @param context
   *          the context
   * @param extensionName
   *          the extension name
   * @return the URI if found, otherwise null
   */
  private URI findExtensionURI(final FormatConfiguration context, final String extensionName) {
    URI uri = findExtensionURI(context.eResource(), extensionName);
    if (uri == null) {
      for (Grammar grammar : getAllGrammars(context.getTargetGrammar())) {
        uri = findExtensionURI(grammar.eResource(), extensionName);
        if (uri != null) {
          return uri;
        }
      }
    }
    return uri;
  }

  /**
   * Find URI of extended FormatConfiguration.
   * The following heuristic is used to find the URI: extract grammar name from the extensionName (path name) and then replace the file name segment of the
   * context's URI with the extracted grammar name.
   *
   * @param context
   *          the context
   * @param extensionName
   *          the extension name
   * @return the URI if found, otherwise null
   */
  private URI findExtensionURI(final Resource context, final String extensionName) {
    URI uri = context.getURI().trimFileExtension();
    String name = convertPathNameToShortName(extensionName);
    final URI extensionURI = uri.trimSegments(1).appendSegment(name).appendFileExtension(Format2Constants.FILE_EXTENSION);
    final ResourceSet resourceSet = context.getResourceSet();
    final URIConverter uriConverter = resourceSet.getURIConverter();
    try {
      if (resourceSet.getResource(extensionURI, false) != null || uriConverter.exists(extensionURI, null)) {
        return extensionURI;
      }
      // CHECKSTYLE:OFF
    } catch (Exception e) {// NOPMD
      // no resource found - return null
      // CHECKSTYLE:ON
    }
    return null;
  }

  /**
   * Returns the last segment (part after last dot separator) of the full path (qualified) grammar/format name.
   *
   * @param extensionName
   *          full/path grammar/format name
   * @return short name of the format/grammar
   */
  private String convertPathNameToShortName(final String extensionName) {
    String[] str = extensionName.split("\\.");
    return str[str.length - 1];
  }
}
