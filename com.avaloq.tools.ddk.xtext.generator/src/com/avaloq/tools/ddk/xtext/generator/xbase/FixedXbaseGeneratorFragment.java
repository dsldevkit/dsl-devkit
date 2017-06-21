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

package com.avaloq.tools.ddk.xtext.generator.xbase;

import static com.google.common.collect.Iterables.any;
import static com.google.common.collect.Sets.newHashSet;
import static org.eclipse.xtext.util.Strings.equal;

import java.util.Set;

import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.generator.xbase.XbaseGeneratorFragment;
import org.eclipse.xtext.xtext.UsedRulesFinder;

import com.google.common.base.Predicate;


/**
 * Override of {@link XbaseGeneratorFragment}, to fix usesXImportSection() for languages which override XImportSection.
 */
@SuppressWarnings("restriction")
public class FixedXbaseGeneratorFragment extends XbaseGeneratorFragment {

  @Override
  public boolean usesXImportSection(final Grammar grammar) {
    Set<AbstractRule> usedRules = newHashSet();
    new UsedRulesFinder(usedRules).compute(grammar);
    return any(usedRules, new Predicate<AbstractRule>() {
      @Override
      public boolean apply(final AbstractRule rule) {
        return equal(rule.getName(), "XImportSection") && equal(rule.getType().getMetamodel().getEPackage().getName(), "xtype");
      }
    });
  }

  @Override
  protected String getTemplate() {
    // Use the parent class's template
    return XbaseGeneratorFragment.class.getName().replaceAll("\\.", "::");
  }

}
