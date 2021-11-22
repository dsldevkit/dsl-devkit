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
package com.avaloq.tools.ddk.xtext.format.scoping

import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration
import com.avaloq.tools.ddk.xtext.format.format.FormatPackage
import com.avaloq.tools.ddk.xtext.format.format.GrammarElementReference
import com.avaloq.tools.ddk.xtext.format.format.GrammarRule
import com.avaloq.tools.ddk.xtext.format.format.GroupBlock
import com.avaloq.tools.ddk.xtext.format.naming.FormatScopeNameProvider
import com.google.common.collect.Collections2
import com.google.common.collect.ImmutableList
import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import com.google.inject.Inject
import java.util.Collection
import java.util.LinkedList
import java.util.List
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.AbstractRule
import org.eclipse.xtext.CompoundElement
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.Grammar
import org.eclipse.xtext.resource.EObjectDescription
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.impl.MapBasedScope
import org.eclipse.xtext.scoping.impl.SimpleScope

/**
 * The scope provider for the Format language.
 */
class FormatScopeProvider extends AbstractFormatScopeProvider {

  @Inject
  FormatScopeUtil scopeUtil;
  @Inject
  FormatScopeNameProvider nameProvider;

  /**
  * Provides a scope for given context and reference.
  * If there is no specific scoping method or if there is such a method but it cannot return a scope, the super class method is called.
  */
  override IScope getScope(EObject context, EReference reference) {
    val result = scope(context, reference)
    if (result !== null) {
      return result
    }
    return super.getScope(context, reference)
  }

  /**
  * For a given grammar returns the grammar on which it is based, and transitively all base grammars for that grammar.
  *
  */
  def Iterable<Grammar> getUsedGrammar(Grammar context){
    val LinkedList<Grammar> grammars = newLinkedList()
    grammars.add(context)
    val usedGrammars = EcoreUtil2::getContainerOfType(context, typeof(Grammar)).usedGrammars
    if (usedGrammars!==null && !usedGrammars.empty){
      grammars.addAll( Iterables::concat( Iterables::transform( usedGrammars, [g|getUsedGrammar(g)] )))
    }
    return grammars
  }

  /**
  * For a given formatter returns the grammar on which it is based, and transitively all base grammars for that grammar.
  *
  */
  def Collection<Grammar> getGrammars(EObject context) {
    val LinkedList<Grammar> grammars = newLinkedList()
    val format = EcoreUtil2::getContainerOfType(context, typeof(FormatConfiguration))
    if (format !== null && format.targetGrammar !== null) {
      grammars.add(format.targetGrammar);
      grammars.addAll(getUsedGrammar(format.targetGrammar))
    }
    return grammars
  }

  /**
   * For a given {@link FormatConfiguration} returns transitively all extending format configurations.
   * Usage of LinkedList for {@code formats} does not prevent against duplication of grammars, but a HashSet cannot be used here as there won't be possible to recover the overriding order.
   */
  def Collection<FormatConfiguration> getFormats(FormatConfiguration context) {
    val formats = newLinkedList()
    val format = context
    if (format !== null) {
      formats.add(format);
      if (format.extendedFormatConfiguration !== null) {
        formats.addAll(getFormats(format.extendedFormatConfiguration))
      }
    }
    return formats
  }

  /**
   * In order to ensure the correct path of inheritance/overriding the list of the grammars has to be reversed.
   */
  def List<Grammar> getHierarchyOrderedGrammars(EObject context) {
    return Lists.newArrayList(getGrammars(context)).reverse()
  }

  /**
   * In order to ensure the correct path of inheritance/overriding the list of the format configurations has to be reversed.
   */
  def List<FormatConfiguration> getHierarchyOrderedFormats(EObject context) {
    return Lists.newArrayList(getFormats(EcoreUtil2::getContainerOfType(context, typeof(FormatConfiguration)))).
      reverse()
  }

  /**
   * Creates scopes for a given list of rules for each grammar. Returned scopes are chained (parental relationships).
   */
  def IScope createScopeForAbstractRules(IScope parent, Iterable<EList<AbstractRule>> rulesForGrammars) {
    if (parent === null) {
      return createScopeForAbstractRules(
        MapBasedScope::createScope(IScope::NULLSCOPE, createDescriptions(rulesForGrammars.head)),
        rulesForGrammars.tail);
    } else {
      if (rulesForGrammars.empty) {
        return parent;
      } else {
        return createScopeForAbstractRules(
          MapBasedScope::createScope(parent, createDescriptions(rulesForGrammars.head)), rulesForGrammars.tail);
      }
    }
  }

  /**
   * Creates a scope for a given list of elements.
   */
  def <T extends EObject> IScope createScopeForEObjects(List<T> elements) {
    return MapBasedScope::createScope(IScope::NULLSCOPE, createDescriptions(elements));
  }

   /**
   * Creates a simple scope for a given object description.
   */
  def createSimpleScope(IEObjectDescription description) {
    return new SimpleScope(IScope::NULLSCOPE, ImmutableList::of(description));
  }

  /**
   * Creates a scope for a given list of compound elements.
   */
  def IScope createScopeForCompoundElements(List<CompoundElement> compoundElements){
    return MapBasedScope::createScope(IScope::NULLSCOPE, createDescriptionsForCompoundElements(compoundElements))
  }


  def dispatch IScope scope(GrammarRule context, EReference reference) {
    if (reference == FormatPackage.Literals::GRAMMAR_RULE__TARGET_RULE) {
      val grammars = getHierarchyOrderedGrammars(context)
      val rulesForGrammars = Iterables::transform(grammars, [g|g.rules])
      return createScopeForAbstractRules(null, rulesForGrammars);
    } else if (reference == FormatPackage.Literals::GRAMMAR_ELEMENT_REFERENCE__ASSIGNMENT) {
      return createScopeForEObjects(scopeUtil.getAssignments(context.targetRule));
    } else if (reference == FormatPackage.Literals::GRAMMAR_ELEMENT_REFERENCE__KEYWORD) {
      return createScopeForEObjects(scopeUtil.getKeywords(context.targetRule));
    } else if (reference == FormatPackage.Literals::GRAMMAR_ELEMENT_REFERENCE__RULE_CALL) {
      return createScopeForEObjects(scopeUtil.getRuleCalls(context.targetRule));
    } else if (reference == FormatPackage.Literals::GRAMMAR_ELEMENT_REFERENCE__SELF) {
      val selfDescription = EObjectDescription::create(
        nameProvider.getConstantNameFunction("rule").apply(context.targetRule), context.targetRule);
      return createSimpleScope(selfDescription)
    }
    return IScope::NULLSCOPE;
  }

  def dispatch IScope scope(GroupBlock context, EReference reference) {
    if (reference == FormatPackage.Literals::GROUP_BLOCK__GRAMMAR_ELEMENT) {
      val grammarRule = EcoreUtil2::getContainerOfType(context, typeof(GrammarRule))
      val superGroup = EcoreUtil2::getContainerOfType(context.eContainer(), typeof(GroupBlock))
      if (superGroup === null){
        return createScopeForCompoundElements(scopeUtil.getCompoundElements(grammarRule.targetRule, typeof(CompoundElement)))
      }
      else{
        return createScopeForCompoundElements(scopeUtil.getCompoundElements(superGroup.grammarElement, typeof(CompoundElement)))
      }
    }
    return IScope::NULLSCOPE;
  }

  def dispatch IScope scope(GrammarElementReference context, EReference reference) {
    val grammarRule = EcoreUtil2::getContainerOfType(context, typeof(GrammarRule))
    val groupBlock = EcoreUtil2::getContainerOfType(context, typeof(GroupBlock))
    if (reference == FormatPackage.Literals::GRAMMAR_ELEMENT_REFERENCE__KEYWORD) {
      if (groupBlock !== null) {
        return createScopeForEObjects(scopeUtil.getKeywords(groupBlock.grammarElement));
      }
      return createScopeForEObjects(scopeUtil.getKeywords(grammarRule.targetRule));
    } else if (reference == FormatPackage.Literals::GRAMMAR_ELEMENT_REFERENCE__ASSIGNMENT) {
      if (groupBlock !== null) {
        return createScopeForEObjects(scopeUtil.getAssignments(groupBlock.grammarElement));
      }
      return createScopeForEObjects(scopeUtil.getAssignments(grammarRule.targetRule));
    } else if (reference == FormatPackage.Literals::GRAMMAR_ELEMENT_REFERENCE__RULE_CALL) {
      if (groupBlock !== null) {
        return createScopeForEObjects(scopeUtil.getRuleCalls(groupBlock.grammarElement));
      }
      return createScopeForEObjects(scopeUtil.getRuleCalls(grammarRule.targetRule));
    } else if (reference == FormatPackage.Literals::GRAMMAR_ELEMENT_REFERENCE__SELF) {
      if (groupBlock !== null) {
        val selfDescription = EObjectDescription::create(
          nameProvider.getConstantNameFunction("rule").apply(groupBlock.grammarElement),
          groupBlock.grammarElement)
        return createSimpleScope(selfDescription)
      } else {
        val selfDescription = EObjectDescription::create(
          nameProvider.getConstantNameFunction("rule").apply(grammarRule.targetRule), grammarRule.targetRule);
        return createSimpleScope(selfDescription)
      }
    } else if (reference == FormatPackage.Literals::GRAMMAR_ELEMENT_REFERENCE__RULE) {
      val rulesForGrammars = Iterables::transform(getHierarchyOrderedGrammars(grammarRule), [c|c.rules])
      return createScopeForAbstractRules(null, rulesForGrammars);
    }
    return IScope::NULLSCOPE;
  }

  // default implementation will throw an illegal argument exception
  def dispatch IScope scope(EObject context, EReference reference) {
    return null
  }

  /**
   * Creates object descriptions for a given list of {@link FormatConfiguration}.
   */
  def Collection<IEObjectDescription> createDescriptionsFormats(List<FormatConfiguration> elements) {
    Collections2::transform(elements, [e|EObjectDescription::create(scopeUtil.findTargetGrammar(e).name, e)])
  }

  /**
   * Creates descriptions for a given list of objects.
   */
  def Collection<IEObjectDescription> createDescriptions(List<? extends EObject> elements) {
    val namingFunction = nameProvider.getIndexParameterNameFunction(elements)
    Collections2::transform(elements, [e|EObjectDescription::create(namingFunction.apply(e), e)])
  }

  /**
   * Creates object descriptions for a list of CompundElements ({@code group X} items). Groups are referenced using numbers which are used in formatter and at the same time corresponds to indexes in the list.
   */
  def Collection<IEObjectDescription> createDescriptionsForCompoundElements(List<? extends CompoundElement> elements) {
    val namingFunction = nameProvider.getIndexNameFunction(elements)
    Collections2::transform(elements, [e|EObjectDescription::create(namingFunction.apply(e), e)])
  }

}
