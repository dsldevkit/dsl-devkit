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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.xtext.UsedRulesFinder;
import org.eclipse.xtext.xtext.generator.xbase.XbaseUsageDetector;


/**
 * Override of {@link XbaseUsageDetector}, to fix usesXImportSection() for languages which override XImportSection.
 * Can be removed after upgrading to Xtext 2.26 (https://github.com/eclipse/xtext-core/pull/1822)
 */
@SuppressWarnings("all")
public class FixedXbaseUsageDetector extends XbaseUsageDetector {
  @Override
  public boolean usesXImportSection(final Grammar grammar) {
    Set<AbstractRule> usedRules = new HashSet<>();
    new UsedRulesFinder(usedRules).compute(grammar);
    for (AbstractRule it : usedRules) {
      if ("XImportSection".equals(it.getName())
          && EcoreUtil2.isAssignableFrom((EClass) it.eResource().getResourceSet().getEObject(URI.createURI("http://www.eclipse.org/xtext/xbase/Xtype#//XImportSection"), true), (EClass) it.getType().getClassifier())) {
        return true;
      }
    }
    return false;
  }
}
