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
package com.avaloq.tools.ddk.xtext.format.scoping;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.CompoundElement;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.MapBasedScope;
import org.eclipse.xtext.scoping.impl.SimpleScope;

import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;
import com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference;
import com.avaloq.tools.ddk.xtext.format.format.GrammarRule;
import com.avaloq.tools.ddk.xtext.format.format.GroupBlock;
import com.avaloq.tools.ddk.xtext.format.naming.FormatScopeNameProvider;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * The scope provider for the Format language.
 */
@SuppressWarnings({"checkstyle:MethodName", "PMD.UnusedFormalParameter"})
public class FormatScopeProvider extends AbstractFormatScopeProvider {

  @Inject
  private FormatScopeUtil scopeUtil;

  @Inject
  private FormatScopeNameProvider nameProvider;

  /**
   * Provides a scope for given context and reference.
   * If there is no specific scoping method or if there is such a method but it cannot return a scope, the super class method is called.
   *
   * @param context
   *          the context object
   * @param reference
   *          the reference
   * @return the scope for the given context and reference
   */
  @Override
  public IScope getScope(final EObject context, final EReference reference) {
    final IScope result = scope(context, reference);
    if (result != null) {
      return result;
    }
    return super.getScope(context, reference);
  }

  /**
   * For a given grammar returns the grammar on which it is based, and transitively all base grammars for that grammar.
   *
   * @param context
   *          the grammar
   * @return all used grammars including the given one
   */
  public Iterable<Grammar> getUsedGrammar(final Grammar context) {
    final List<Grammar> grammars = new LinkedList<>();
    grammars.add(context);
    final EList<Grammar> usedGrammars = EcoreUtil2.getContainerOfType(context, Grammar.class).getUsedGrammars();
    if (usedGrammars != null && !usedGrammars.isEmpty()) {
      Iterables.addAll(grammars, Iterables.concat(Iterables.transform(usedGrammars, (Grammar g) -> getUsedGrammar(g))));
    }
    return grammars;
  }

  /**
   * For a given formatter returns the grammar on which it is based, and transitively all base grammars for that grammar.
   *
   * @param context
   *          the context object
   * @return the collection of grammars
   */
  public Collection<Grammar> getGrammars(final EObject context) {
    final List<Grammar> grammars = new LinkedList<>();
    final FormatConfiguration format = EcoreUtil2.getContainerOfType(context, FormatConfiguration.class);
    if (format != null && format.getTargetGrammar() != null) {
      grammars.add(format.getTargetGrammar());
      Iterables.addAll(grammars, getUsedGrammar(format.getTargetGrammar()));
    }
    return grammars;
  }

  /**
   * For a given {@link FormatConfiguration} returns transitively all extending format configurations.
   * Usage of LinkedList for {@code formats} does not prevent against duplication of grammars, but a HashSet cannot be used here as there won't be possible to recover the overriding order.
   *
   * @param context
   *          the format configuration
   * @return all format configurations including the given one and its extensions
   */
  public Collection<FormatConfiguration> getFormats(final FormatConfiguration context) {
    final List<FormatConfiguration> formats = new LinkedList<>();
    final FormatConfiguration format = context;
    if (format != null) {
      formats.add(format);
      if (format.getExtendedFormatConfiguration() != null) {
        formats.addAll(getFormats(format.getExtendedFormatConfiguration()));
      }
    }
    return formats;
  }

  /**
   * In order to ensure the correct path of inheritance/overriding the list of the grammars has to be reversed.
   *
   * @param context
   *          the context object
   * @return the list of grammars in hierarchy order
   */
  public List<Grammar> getHierarchyOrderedGrammars(final EObject context) {
    return Lists.reverse(Lists.newArrayList(getGrammars(context)));
  }

  /**
   * In order to ensure the correct path of inheritance/overriding the list of the format configurations has to be reversed.
   *
   * @param context
   *          the context object
   * @return the list of format configurations in hierarchy order
   */
  public List<FormatConfiguration> getHierarchyOrderedFormats(final EObject context) {
    return Lists.reverse(Lists.newArrayList(getFormats(EcoreUtil2.getContainerOfType(context, FormatConfiguration.class))));
  }

  /**
   * Creates scopes for a given list of rules for each grammar. Returned scopes are chained (parental relationships).
   *
   * @param parent
   *          the parent scope, may be {@code null}
   * @param rulesForGrammars
   *          the rules for each grammar
   * @return the chained scope
   */
  public IScope createScopeForAbstractRules(final IScope parent, final Iterable<EList<AbstractRule>> rulesForGrammars) {
    if (parent == null) {
      return createScopeForAbstractRules(
        MapBasedScope.createScope(IScope.NULLSCOPE, createDescriptions(Iterables.getFirst(rulesForGrammars, null))),
        Iterables.skip(rulesForGrammars, 1));
    } else {
      if (Iterables.isEmpty(rulesForGrammars)) {
        return parent;
      } else {
        return createScopeForAbstractRules(
          MapBasedScope.createScope(parent, createDescriptions(Iterables.getFirst(rulesForGrammars, null))),
          Iterables.skip(rulesForGrammars, 1));
      }
    }
  }

  /**
   * Creates a scope for a given list of elements.
   *
   * @param <T>
   *          the element type
   * @param elements
   *          the list of elements
   * @return the scope
   */
  public <T extends EObject> IScope createScopeForEObjects(final List<T> elements) {
    return MapBasedScope.createScope(IScope.NULLSCOPE, createDescriptions(elements));
  }

  /**
   * Creates a simple scope for a given object description.
   *
   * @param description
   *          the object description
   * @return the simple scope
   */
  public SimpleScope createSimpleScope(final IEObjectDescription description) {
    return new SimpleScope(IScope.NULLSCOPE, ImmutableList.of(description));
  }

  /**
   * Creates a scope for a given list of compound elements.
   *
   * @param compoundElements
   *          the list of compound elements
   * @return the scope
   */
  public IScope createScopeForCompoundElements(final List<CompoundElement> compoundElements) {
    return MapBasedScope.createScope(IScope.NULLSCOPE, createDescriptionsForCompoundElements(compoundElements));
  }

  protected IScope _scope(final GrammarRule context, final EReference reference) {
    if (reference == FormatPackage.Literals.GRAMMAR_RULE__TARGET_RULE) {
      final List<Grammar> grammars = getHierarchyOrderedGrammars(context);
      final Iterable<EList<AbstractRule>> rulesForGrammars = Iterables.transform(grammars, (Grammar g) -> g.getRules());
      return createScopeForAbstractRules(null, rulesForGrammars);
    } else if (reference == FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__ASSIGNMENT) {
      return createScopeForEObjects(scopeUtil.getAssignments(context.getTargetRule()));
    } else if (reference == FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__KEYWORD) {
      return createScopeForEObjects(scopeUtil.getKeywords(context.getTargetRule()));
    } else if (reference == FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__RULE_CALL) {
      return createScopeForEObjects(scopeUtil.getRuleCalls(context.getTargetRule()));
    } else if (reference == FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__SELF) {
      final IEObjectDescription selfDescription = EObjectDescription.create(
        nameProvider.getConstantNameFunction("rule").apply(context.getTargetRule()), context.getTargetRule());
      return createSimpleScope(selfDescription);
    }
    return IScope.NULLSCOPE;
  }

  protected IScope _scope(final GroupBlock context, final EReference reference) {
    if (reference == FormatPackage.Literals.GROUP_BLOCK__GRAMMAR_ELEMENT) {
      final GrammarRule grammarRule = EcoreUtil2.getContainerOfType(context, GrammarRule.class);
      final GroupBlock superGroup = EcoreUtil2.getContainerOfType(context.eContainer(), GroupBlock.class);
      if (superGroup == null) {
        return createScopeForCompoundElements(scopeUtil.getCompoundElements(grammarRule.getTargetRule(), CompoundElement.class));
      } else {
        return createScopeForCompoundElements(scopeUtil.getCompoundElements(superGroup.getGrammarElement(), CompoundElement.class));
      }
    }
    return IScope.NULLSCOPE;
  }

  protected IScope _scope(final GrammarElementReference context, final EReference reference) {
    final GrammarRule grammarRule = EcoreUtil2.getContainerOfType(context, GrammarRule.class);
    final GroupBlock groupBlock = EcoreUtil2.getContainerOfType(context, GroupBlock.class);
    if (reference == FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__KEYWORD) {
      if (groupBlock != null) {
        return createScopeForEObjects(scopeUtil.getKeywords(groupBlock.getGrammarElement()));
      }
      return createScopeForEObjects(scopeUtil.getKeywords(grammarRule.getTargetRule()));
    } else if (reference == FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__ASSIGNMENT) {
      if (groupBlock != null) {
        return createScopeForEObjects(scopeUtil.getAssignments(groupBlock.getGrammarElement()));
      }
      return createScopeForEObjects(scopeUtil.getAssignments(grammarRule.getTargetRule()));
    } else if (reference == FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__RULE_CALL) {
      if (groupBlock != null) {
        return createScopeForEObjects(scopeUtil.getRuleCalls(groupBlock.getGrammarElement()));
      }
      return createScopeForEObjects(scopeUtil.getRuleCalls(grammarRule.getTargetRule()));
    } else if (reference == FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__SELF) {
      if (groupBlock != null) {
        final IEObjectDescription selfDescription = EObjectDescription.create(
          nameProvider.getConstantNameFunction("rule").apply(groupBlock.getGrammarElement()),
          groupBlock.getGrammarElement());
        return createSimpleScope(selfDescription);
      } else {
        final IEObjectDescription selfDescription = EObjectDescription.create(
          nameProvider.getConstantNameFunction("rule").apply(grammarRule.getTargetRule()), grammarRule.getTargetRule());
        return createSimpleScope(selfDescription);
      }
    } else if (reference == FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__RULE) {
      final Iterable<EList<AbstractRule>> rulesForGrammars = Iterables.transform(getHierarchyOrderedGrammars(grammarRule), (Grammar c) -> c.getRules());
      return createScopeForAbstractRules(null, rulesForGrammars);
    }
    return IScope.NULLSCOPE;
  }

  // default implementation will throw an illegal argument exception
  protected IScope _scope(final EObject context, final EReference reference) {
    return null;
  }

  /**
   * Dispatch method for scope resolution based on runtime type of context.
   *
   * @param context
   *          the context object
   * @param reference
   *          the reference
   * @return the scope, or {@code null} if no specific scope applies
   * @throws IllegalArgumentException if the context type is not handled
   */
  public IScope scope(final EObject context, final EReference reference) {
    if (context instanceof GrammarRule grammarRule) {
      return _scope(grammarRule, reference);
    } else if (context instanceof GrammarElementReference grammarElementReference) {
      return _scope(grammarElementReference, reference);
    } else if (context instanceof GroupBlock groupBlock) {
      return _scope(groupBlock, reference);
    } else if (context != null) {
      return _scope(context, reference);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
        + Arrays.<Object>asList(context, reference).toString());
    }
  }

  /**
   * Creates object descriptions for a given list of {@link FormatConfiguration}.
   *
   * @param elements
   *          the list of format configurations
   * @return the collection of object descriptions
   */
  public Collection<IEObjectDescription> createDescriptionsFormats(final List<FormatConfiguration> elements) {
    return Collections2.transform(elements, (FormatConfiguration e) -> EObjectDescription.create(scopeUtil.findTargetGrammar(e).getName(), e));
  }

  /**
   * Creates descriptions for a given list of objects.
   *
   * @param elements
   *          the list of objects
   * @return the collection of object descriptions
   */
  public Collection<IEObjectDescription> createDescriptions(final List<? extends EObject> elements) {
    final Function<EObject, QualifiedName> namingFunction = nameProvider.getIndexParameterNameFunction(elements);
    return Collections2.transform(elements, (EObject e) -> EObjectDescription.create(namingFunction.apply(e), e));
  }

  /**
   * Creates object descriptions for a list of CompundElements ({@code group X} items). Groups are referenced using numbers which are used in formatter and at the same time corresponds to indexes in the list.
   *
   * @param elements
   *          the list of compound elements
   * @return the collection of object descriptions
   */
  public Collection<IEObjectDescription> createDescriptionsForCompoundElements(final List<? extends CompoundElement> elements) {
    final Function<EObject, QualifiedName> namingFunction = nameProvider.getIndexNameFunction(elements);
    return Collections2.transform(elements, (CompoundElement e) -> EObjectDescription.create(namingFunction.apply(e), e));
  }
}
