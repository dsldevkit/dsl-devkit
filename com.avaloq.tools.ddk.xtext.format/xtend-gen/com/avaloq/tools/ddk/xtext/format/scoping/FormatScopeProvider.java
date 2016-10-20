/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.xtext.format.scoping;

import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;
import com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference;
import com.avaloq.tools.ddk.xtext.format.format.GrammarRule;
import com.avaloq.tools.ddk.xtext.format.format.GroupBlock;
import com.avaloq.tools.ddk.xtext.format.naming.FormatScopeNameProvider;
import com.avaloq.tools.ddk.xtext.format.scoping.FormatScopeUtil;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CompoundElement;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.MapBasedScope;
import org.eclipse.xtext.scoping.impl.SimpleScope;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.scoping.XImportSectionNamespaceScopeProvider;

/**
 * The scope provider for the Format language.
 */
@SuppressWarnings("all")
public class FormatScopeProvider extends XImportSectionNamespaceScopeProvider {
  @Inject
  private FormatScopeUtil scopeUtil;
  
  @Inject
  private FormatScopeNameProvider nameProvider;
  
  /**
   * Provides a scope for given context and reference.
   * If there is no specific scoping method or if there is such a method but it cannot return a scope, the super class method is called.
   */
  @Override
  public IScope getScope(final EObject context, final EReference reference) {
    final IScope result = this.scope(context, reference);
    boolean _notEquals = (!Objects.equal(result, null));
    if (_notEquals) {
      return result;
    }
    return super.getScope(context, reference);
  }
  
  /**
   * For a given grammar returns the grammar on which it is based, and transitively all base grammars for that grammar.
   */
  public Iterable<Grammar> getUsedGrammar(final Grammar context) {
    final LinkedList<Grammar> grammars = CollectionLiterals.<Grammar>newLinkedList();
    grammars.add(context);
    Grammar _containerOfType = EcoreUtil2.<Grammar>getContainerOfType(context, Grammar.class);
    final EList<Grammar> usedGrammars = _containerOfType.getUsedGrammars();
    if (((!Objects.equal(usedGrammars, null)) && (!usedGrammars.isEmpty()))) {
      final Function<Grammar, Iterable<Grammar>> _function = (Grammar g) -> {
        return this.getUsedGrammar(g);
      };
      Iterable<Iterable<Grammar>> _transform = Iterables.<Grammar, Iterable<Grammar>>transform(usedGrammars, _function);
      Iterable<Grammar> _concat = Iterables.<Grammar>concat(_transform);
      Iterables.<Grammar>addAll(grammars, _concat);
    }
    return grammars;
  }
  
  /**
   * For a given formatter returns the grammar on which it is based, and transitively all base grammars for that grammar.
   */
  public Collection<Grammar> getGrammars(final EObject context) {
    final LinkedList<Grammar> grammars = CollectionLiterals.<Grammar>newLinkedList();
    final FormatConfiguration format = EcoreUtil2.<FormatConfiguration>getContainerOfType(context, FormatConfiguration.class);
    if (((!Objects.equal(format, null)) && (!Objects.equal(format.getTargetGrammar(), null)))) {
      Grammar _targetGrammar = format.getTargetGrammar();
      grammars.add(_targetGrammar);
      Grammar _targetGrammar_1 = format.getTargetGrammar();
      Iterable<Grammar> _usedGrammar = this.getUsedGrammar(_targetGrammar_1);
      Iterables.<Grammar>addAll(grammars, _usedGrammar);
    }
    return grammars;
  }
  
  /**
   * For a given {@link FormatConfiguration} returns transitively all extending format configurations.
   * Usage of LinkedList for {@code formats} does not prevent against duplication of grammars, but a HashSet cannot be used here as there won't be possible to recover the overriding order.
   */
  public Collection<FormatConfiguration> getFormats(final FormatConfiguration context) {
    final LinkedList<FormatConfiguration> formats = CollectionLiterals.<FormatConfiguration>newLinkedList();
    final FormatConfiguration format = context;
    boolean _notEquals = (!Objects.equal(format, null));
    if (_notEquals) {
      formats.add(format);
      FormatConfiguration _extendedFormatConfiguration = format.getExtendedFormatConfiguration();
      boolean _notEquals_1 = (!Objects.equal(_extendedFormatConfiguration, null));
      if (_notEquals_1) {
        FormatConfiguration _extendedFormatConfiguration_1 = format.getExtendedFormatConfiguration();
        Collection<FormatConfiguration> _formats = this.getFormats(_extendedFormatConfiguration_1);
        formats.addAll(_formats);
      }
    }
    return formats;
  }
  
  /**
   * In order to ensure the correct path of inheritance/overriding the list of the grammars has to be reversed.
   */
  public List<Grammar> getHierarchyOrderedGrammars(final EObject context) {
    Collection<Grammar> _grammars = this.getGrammars(context);
    ArrayList<Grammar> _newArrayList = Lists.<Grammar>newArrayList(_grammars);
    return ListExtensions.<Grammar>reverse(_newArrayList);
  }
  
  /**
   * In order to ensure the correct path of inheritance/overriding the list of the format configurations has to be reversed.
   */
  public List<FormatConfiguration> getHierarchyOrderedFormats(final EObject context) {
    FormatConfiguration _containerOfType = EcoreUtil2.<FormatConfiguration>getContainerOfType(context, FormatConfiguration.class);
    Collection<FormatConfiguration> _formats = this.getFormats(_containerOfType);
    ArrayList<FormatConfiguration> _newArrayList = Lists.<FormatConfiguration>newArrayList(_formats);
    return ListExtensions.<FormatConfiguration>reverse(_newArrayList);
  }
  
  /**
   * Creates scopes for a given list of rules for each grammar. Returned scopes are chained (parental relationships).
   */
  public IScope createScopeForAbstractRules(final IScope parent, final Iterable<EList<AbstractRule>> rulesForGrammars) {
    boolean _equals = Objects.equal(parent, null);
    if (_equals) {
      EList<AbstractRule> _head = IterableExtensions.<EList<AbstractRule>>head(rulesForGrammars);
      Collection<IEObjectDescription> _createDescriptions = this.createDescriptions(_head);
      IScope _createScope = MapBasedScope.createScope(IScope.NULLSCOPE, _createDescriptions);
      Iterable<EList<AbstractRule>> _tail = IterableExtensions.<EList<AbstractRule>>tail(rulesForGrammars);
      return this.createScopeForAbstractRules(_createScope, _tail);
    } else {
      boolean _isEmpty = IterableExtensions.isEmpty(rulesForGrammars);
      if (_isEmpty) {
        return parent;
      } else {
        EList<AbstractRule> _head_1 = IterableExtensions.<EList<AbstractRule>>head(rulesForGrammars);
        Collection<IEObjectDescription> _createDescriptions_1 = this.createDescriptions(_head_1);
        IScope _createScope_1 = MapBasedScope.createScope(parent, _createDescriptions_1);
        Iterable<EList<AbstractRule>> _tail_1 = IterableExtensions.<EList<AbstractRule>>tail(rulesForGrammars);
        return this.createScopeForAbstractRules(_createScope_1, _tail_1);
      }
    }
  }
  
  /**
   * Creates a scope for a given list of elements.
   */
  public <T extends EObject> IScope createScopeForEObjects(final List<T> elements) {
    Collection<IEObjectDescription> _createDescriptions = this.createDescriptions(elements);
    return MapBasedScope.createScope(IScope.NULLSCOPE, _createDescriptions);
  }
  
  /**
   * Creates a simple scope for a given object description.
   */
  public SimpleScope createSimpleScope(final IEObjectDescription description) {
    ImmutableList<IEObjectDescription> _of = ImmutableList.<IEObjectDescription>of(description);
    return new SimpleScope(IScope.NULLSCOPE, _of);
  }
  
  /**
   * Creates a scope for a given list of compound elements.
   */
  public IScope createScopeForCompoundElements(final List<CompoundElement> compoundElements) {
    Collection<IEObjectDescription> _createDescriptionsForCompoundElements = this.createDescriptionsForCompoundElements(compoundElements);
    return MapBasedScope.createScope(IScope.NULLSCOPE, _createDescriptionsForCompoundElements);
  }
  
  protected IScope _scope(final GrammarRule context, final EReference reference) {
    boolean _equals = Objects.equal(reference, FormatPackage.Literals.GRAMMAR_RULE__TARGET_RULE);
    if (_equals) {
      final List<Grammar> grammars = this.getHierarchyOrderedGrammars(context);
      final Function<Grammar, EList<AbstractRule>> _function = (Grammar g) -> {
        return g.getRules();
      };
      final Iterable<EList<AbstractRule>> rulesForGrammars = Iterables.<Grammar, EList<AbstractRule>>transform(grammars, _function);
      return this.createScopeForAbstractRules(null, rulesForGrammars);
    } else {
      boolean _equals_1 = Objects.equal(reference, FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__ASSIGNMENT);
      if (_equals_1) {
        AbstractRule _targetRule = context.getTargetRule();
        List<Assignment> _assignments = this.scopeUtil.getAssignments(_targetRule);
        return this.<Assignment>createScopeForEObjects(_assignments);
      } else {
        boolean _equals_2 = Objects.equal(reference, FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__KEYWORD);
        if (_equals_2) {
          AbstractRule _targetRule_1 = context.getTargetRule();
          List<Keyword> _keywords = this.scopeUtil.getKeywords(_targetRule_1);
          return this.<Keyword>createScopeForEObjects(_keywords);
        } else {
          boolean _equals_3 = Objects.equal(reference, FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__RULE_CALL);
          if (_equals_3) {
            AbstractRule _targetRule_2 = context.getTargetRule();
            List<RuleCall> _ruleCalls = this.scopeUtil.getRuleCalls(_targetRule_2);
            return this.<RuleCall>createScopeForEObjects(_ruleCalls);
          } else {
            boolean _equals_4 = Objects.equal(reference, FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__SELF);
            if (_equals_4) {
              Function<EObject, QualifiedName> _constantNameFunction = this.nameProvider.getConstantNameFunction("rule");
              AbstractRule _targetRule_3 = context.getTargetRule();
              QualifiedName _apply = _constantNameFunction.apply(_targetRule_3);
              AbstractRule _targetRule_4 = context.getTargetRule();
              final IEObjectDescription selfDescription = EObjectDescription.create(_apply, _targetRule_4);
              return this.createSimpleScope(selfDescription);
            }
          }
        }
      }
    }
    return IScope.NULLSCOPE;
  }
  
  protected IScope _scope(final GroupBlock context, final EReference reference) {
    boolean _equals = Objects.equal(reference, FormatPackage.Literals.GROUP_BLOCK__GRAMMAR_ELEMENT);
    if (_equals) {
      final GrammarRule grammarRule = EcoreUtil2.<GrammarRule>getContainerOfType(context, GrammarRule.class);
      EObject _eContainer = context.eContainer();
      final GroupBlock superGroup = EcoreUtil2.<GroupBlock>getContainerOfType(_eContainer, GroupBlock.class);
      boolean _equals_1 = Objects.equal(superGroup, null);
      if (_equals_1) {
        AbstractRule _targetRule = grammarRule.getTargetRule();
        List<CompoundElement> _compoundElements = this.scopeUtil.<CompoundElement>getCompoundElements(_targetRule, CompoundElement.class);
        return this.createScopeForCompoundElements(_compoundElements);
      } else {
        CompoundElement _grammarElement = superGroup.getGrammarElement();
        List<CompoundElement> _compoundElements_1 = this.scopeUtil.<CompoundElement>getCompoundElements(_grammarElement, CompoundElement.class);
        return this.createScopeForCompoundElements(_compoundElements_1);
      }
    }
    return IScope.NULLSCOPE;
  }
  
  protected IScope _scope(final GrammarElementReference context, final EReference reference) {
    final GrammarRule grammarRule = EcoreUtil2.<GrammarRule>getContainerOfType(context, GrammarRule.class);
    final GroupBlock groupBlock = EcoreUtil2.<GroupBlock>getContainerOfType(context, GroupBlock.class);
    boolean _equals = Objects.equal(reference, FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__KEYWORD);
    if (_equals) {
      boolean _notEquals = (!Objects.equal(groupBlock, null));
      if (_notEquals) {
        CompoundElement _grammarElement = groupBlock.getGrammarElement();
        List<Keyword> _keywords = this.scopeUtil.getKeywords(_grammarElement);
        return this.<Keyword>createScopeForEObjects(_keywords);
      }
      AbstractRule _targetRule = grammarRule.getTargetRule();
      List<Keyword> _keywords_1 = this.scopeUtil.getKeywords(_targetRule);
      return this.<Keyword>createScopeForEObjects(_keywords_1);
    } else {
      boolean _equals_1 = Objects.equal(reference, FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__ASSIGNMENT);
      if (_equals_1) {
        boolean _notEquals_1 = (!Objects.equal(groupBlock, null));
        if (_notEquals_1) {
          CompoundElement _grammarElement_1 = groupBlock.getGrammarElement();
          List<Assignment> _assignments = this.scopeUtil.getAssignments(_grammarElement_1);
          return this.<Assignment>createScopeForEObjects(_assignments);
        }
        AbstractRule _targetRule_1 = grammarRule.getTargetRule();
        List<Assignment> _assignments_1 = this.scopeUtil.getAssignments(_targetRule_1);
        return this.<Assignment>createScopeForEObjects(_assignments_1);
      } else {
        boolean _equals_2 = Objects.equal(reference, FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__RULE_CALL);
        if (_equals_2) {
          boolean _notEquals_2 = (!Objects.equal(groupBlock, null));
          if (_notEquals_2) {
            CompoundElement _grammarElement_2 = groupBlock.getGrammarElement();
            List<RuleCall> _ruleCalls = this.scopeUtil.getRuleCalls(_grammarElement_2);
            return this.<RuleCall>createScopeForEObjects(_ruleCalls);
          }
          AbstractRule _targetRule_2 = grammarRule.getTargetRule();
          List<RuleCall> _ruleCalls_1 = this.scopeUtil.getRuleCalls(_targetRule_2);
          return this.<RuleCall>createScopeForEObjects(_ruleCalls_1);
        } else {
          boolean _equals_3 = Objects.equal(reference, FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__SELF);
          if (_equals_3) {
            boolean _notEquals_3 = (!Objects.equal(groupBlock, null));
            if (_notEquals_3) {
              Function<EObject, QualifiedName> _constantNameFunction = this.nameProvider.getConstantNameFunction("rule");
              CompoundElement _grammarElement_3 = groupBlock.getGrammarElement();
              QualifiedName _apply = _constantNameFunction.apply(_grammarElement_3);
              CompoundElement _grammarElement_4 = groupBlock.getGrammarElement();
              final IEObjectDescription selfDescription = EObjectDescription.create(_apply, _grammarElement_4);
              return this.createSimpleScope(selfDescription);
            } else {
              Function<EObject, QualifiedName> _constantNameFunction_1 = this.nameProvider.getConstantNameFunction("rule");
              AbstractRule _targetRule_3 = grammarRule.getTargetRule();
              QualifiedName _apply_1 = _constantNameFunction_1.apply(_targetRule_3);
              AbstractRule _targetRule_4 = grammarRule.getTargetRule();
              final IEObjectDescription selfDescription_1 = EObjectDescription.create(_apply_1, _targetRule_4);
              return this.createSimpleScope(selfDescription_1);
            }
          } else {
            boolean _equals_4 = Objects.equal(reference, FormatPackage.Literals.GRAMMAR_ELEMENT_REFERENCE__RULE);
            if (_equals_4) {
              List<Grammar> _hierarchyOrderedGrammars = this.getHierarchyOrderedGrammars(grammarRule);
              final Function<Grammar, EList<AbstractRule>> _function = (Grammar c) -> {
                return c.getRules();
              };
              final Iterable<EList<AbstractRule>> rulesForGrammars = Iterables.<Grammar, EList<AbstractRule>>transform(_hierarchyOrderedGrammars, _function);
              return this.createScopeForAbstractRules(null, rulesForGrammars);
            }
          }
        }
      }
    }
    return IScope.NULLSCOPE;
  }
  
  protected IScope _scope(final EObject context, final EReference reference) {
    return null;
  }
  
  /**
   * Creates object descriptions for a given list of {@link FormatConfiguration}.
   */
  public Collection<IEObjectDescription> createDescriptionsFormats(final List<FormatConfiguration> elements) {
    final Function<FormatConfiguration, IEObjectDescription> _function = (FormatConfiguration e) -> {
      Grammar _findTargetGrammar = this.scopeUtil.findTargetGrammar(e);
      String _name = _findTargetGrammar.getName();
      return EObjectDescription.create(_name, e);
    };
    return Collections2.<FormatConfiguration, IEObjectDescription>transform(elements, _function);
  }
  
  /**
   * Creates descriptions for a given list of objects.
   */
  public Collection<IEObjectDescription> createDescriptions(final List<? extends EObject> elements) {
    Collection<IEObjectDescription> _xblockexpression = null;
    {
      final Function<EObject, QualifiedName> namingFunction = this.nameProvider.getIndexParameterNameFunction(elements);
      final Function<EObject, IEObjectDescription> _function = (EObject e) -> {
        QualifiedName _apply = namingFunction.apply(e);
        return EObjectDescription.create(_apply, e);
      };
      _xblockexpression = Collections2.transform(elements, _function);
    }
    return _xblockexpression;
  }
  
  /**
   * Creates object descriptions for a list of CompundElements ({@code group X} items). Groups are referenced using numbers which are used in formatter and at the same time corresponds to indexes in the list.
   */
  public Collection<IEObjectDescription> createDescriptionsForCompoundElements(final List<? extends CompoundElement> elements) {
    Collection<IEObjectDescription> _xblockexpression = null;
    {
      final Function<EObject, QualifiedName> namingFunction = this.nameProvider.getIndexNameFunction(elements);
      final Function<CompoundElement, IEObjectDescription> _function = (CompoundElement e) -> {
        QualifiedName _apply = namingFunction.apply(e);
        return EObjectDescription.create(_apply, e);
      };
      _xblockexpression = Collections2.transform(elements, _function);
    }
    return _xblockexpression;
  }
  
  public IScope scope(final EObject context, final EReference reference) {
    if (context instanceof GrammarRule) {
      return _scope((GrammarRule)context, reference);
    } else if (context instanceof GrammarElementReference) {
      return _scope((GrammarElementReference)context, reference);
    } else if (context instanceof GroupBlock) {
      return _scope((GroupBlock)context, reference);
    } else if (context != null) {
      return _scope(context, reference);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(context, reference).toString());
    }
  }
}
