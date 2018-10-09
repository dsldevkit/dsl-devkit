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

package com.avaloq.tools.ddk.xtext.generator.xbase

import java.util.Set
import org.eclipse.xtext.AbstractRule
import org.eclipse.xtext.Grammar
import org.eclipse.xtext.xtext.UsedRulesFinder
import org.eclipse.xtext.xtext.generator.xbase.XbaseUsageDetector
import static org.eclipse.xtext.util.Strings.equal

/**
 * Override of {@link XbaseUsageDetector}, to fix usesXImportSection() for languages which override XImportSection.
 */
class FixedXbaseUsageDetector extends XbaseUsageDetector {

  override usesXImportSection(Grammar grammar) {
    val Set<AbstractRule> usedRules = newHashSet
    new UsedRulesFinder(usedRules).compute(grammar)
    return usedRules.exists [
      equal(name, 'XImportSection') && equal(type.metamodel.EPackage.name, 'xtype')
    ]
  }
}
