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
package com.avaloq.tools.ddk.xtext.format.ui.hyperlinking;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.Region;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.hyperlinking.HyperlinkHelper;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkAcceptor;

import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.format.format.GrammarRule;
import com.avaloq.tools.ddk.xtext.format.format.Rule;
import com.avaloq.tools.ddk.xtext.format.format.WildcardRule;
import com.avaloq.tools.ddk.xtext.format.services.FormatGrammarAccess;
import com.google.common.collect.Lists;
import com.google.inject.Inject;


/**
 * Provide hyperlinks from "override" keywords to the extended rule.
 */
public class FormatHyperlinkHelper extends HyperlinkHelper {

  @Inject
  private EObjectAtOffsetHelper eObjectAtOffsetHelper;
  @Inject
  private IGrammarAccess grammarAccess;

  
  @Override
  public void createHyperlinksByOffset(final XtextResource resource, final int offset, final IHyperlinkAcceptor acceptor) {
    final IParseResult parseResult = resource.getParseResult();
    if (parseResult == null || parseResult.getRootNode() == null) {
      return; // Return, no need to call in super.createAdditionalHyperlinks
    }

    // Check if the current parse tree node represents an override keyword, in which case we want to link
    // to the overridden rule
    INode node = NodeModelUtils.findLeafNodeAtOffset(parseResult.getRootNode(), offset);
    if (node != null && isOverrideKeyword(node.getGrammarElement())) {
      Rule rule = (Rule) eObjectAtOffsetHelper.resolveElementAt(resource, offset);
      Region region = new Region(node.getOffset(), node.getLength());
      List<Rule> extendedRules = getExtendedRules(rule);
      for (Rule extendedRule : extendedRules) {
        createHyperlinksTo(resource, region, extendedRule, acceptor);
      }
    }
    super.createHyperlinksByOffset(resource, offset, acceptor);
  }

  /**
   * Gets all extended rules.
   *
   * @param rule
   *          the rule
   * @return list with all extended rules
   */
  private List<Rule> getExtendedRules(final Rule rule) {
    FormatConfiguration model = EcoreUtil2.getContainerOfType(rule, FormatConfiguration.class);
    List<Rule> result = Lists.newArrayList();
    for (FormatConfiguration extendedModel : getExtendedModels(model)) {
      for (Rule candidate : extendedModel.getRules()) {
        if (rule instanceof GrammarRule && candidate instanceof GrammarRule//
            && ((GrammarRule) rule).getTargetRule().equals(((GrammarRule) candidate).getTargetRule())) {
          result.add(candidate);
        } else if (rule instanceof WildcardRule && candidate instanceof WildcardRule) {
          result.add(candidate);
        }
      }
    }
    return result;
  }

  /**
   * Gets all extended models.
   *
   * @param model
   *          the model
   * @return list with all extended models
   */
  private List<FormatConfiguration> getExtendedModels(final FormatConfiguration model) {
    FormatConfiguration extendedModel = model.getExtendedFormatConfiguration();
    List<FormatConfiguration> result = Lists.newArrayList();
    if (extendedModel != null) {
      result.add(extendedModel);
      result.addAll(getExtendedModels(extendedModel));
    }
    return result;
  }

  /**
   * Checks if grammar element is one of Rule's "override" Keywords.
   *
   * @param grammarElement
   *          the grammar element
   * @return true, if is override keyword
   */
  private boolean isOverrideKeyword(final EObject grammarElement) {
    Keyword override1 = ((FormatGrammarAccess) grammarAccess).getWildcardRuleAccess().getOverrideOverrideKeyword_1_0();
    Keyword override2 = ((FormatGrammarAccess) grammarAccess).getGrammarRuleAccess().getOverrideOverrideKeyword_0_0();
    return override1.equals(grammarElement) || override2.equals(grammarElement);
  }

}
