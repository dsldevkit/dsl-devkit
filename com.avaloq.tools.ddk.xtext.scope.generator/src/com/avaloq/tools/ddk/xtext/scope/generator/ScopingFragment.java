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
package com.avaloq.tools.ddk.xtext.scope.generator;

import java.util.Set;

import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.generator.AbstractGeneratorFragment;
import org.eclipse.xtext.generator.BindFactory;
import org.eclipse.xtext.generator.Binding;
import org.eclipse.xtext.linking.ILinkingService;
import org.eclipse.xtext.scoping.IScopeProvider;

import com.avaloq.tools.ddk.xtext.scoping.IScopeNameProvider;


/**
 * MWE fragment for the scoping language.
 */
@SuppressWarnings("deprecation")
public class ScopingFragment extends AbstractGeneratorFragment {

  private static final String RUNTIME_PLUGIN = "com.avaloq.tools.ddk.xtext"; //$NON-NLS-1$

  /** {@inheritDoc} */
  @Override
  public Set<Binding> getGuiceBindingsRt(final Grammar grammar) {
    final BindFactory bindFactory = new BindFactory();
    final String prefix = GrammarUtil.getNamespace(grammar) + ".scoping." + GrammarUtil.getSimpleName(grammar); //$NON-NLS-1$

    bindFactory.addTypeToType(IScopeProvider.class.getName(), prefix + "ScopeProvider"); //$NON-NLS-1$
    bindFactory.addTypeToType(IScopeNameProvider.class.getName(), prefix + "ScopeNameProvider"); //$NON-NLS-1$
    bindFactory.addTypeToType(ILinkingService.class.getName(), "com.avaloq.tools.ddk.xtext.linking.LinkingService"); //$NON-NLS-1$

    return bindFactory.getBindings();
  }

  /** {@inheritDoc} */
  @Override
  public String[] getRequiredBundlesRt(final Grammar grammar) {
    return new String[] {"org.eclipse.emf.ecore", RUNTIME_PLUGIN}; //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  public String[] getRequiredBundlesUi(final Grammar grammar) {
    return new String[] {RUNTIME_PLUGIN};
  }

  /** {@inheritDoc} */
  @Override
  public String[] getExportedPackagesRt(final Grammar grammar) {
    return new String[] {GrammarUtil.getNamespace(grammar) + ".scoping"}; //$NON-NLS-1$
  }

}
