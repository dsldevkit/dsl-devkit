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
package com.avaloq.tools.ddk.xtext.scope.generator;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xpand2.XpandFacade;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.generator.AbstractGeneratorFragment;
import org.eclipse.xtext.generator.BindFactory;
import org.eclipse.xtext.generator.Binding;
import org.eclipse.xtext.linking.ILinkingService;
import org.eclipse.xtext.scoping.IScopeProvider;

import com.avaloq.tools.ddk.xtext.scope.ScopeStandaloneSetup;
import com.avaloq.tools.ddk.xtext.scoping.IScopeNameProvider;
import com.google.common.collect.Lists;


/**
 * MWE fragment for the scoping language.
 */
@SuppressWarnings("nls")
public class ScopingFragment extends AbstractGeneratorFragment {

  private static final String RUNTIME_PLUGIN = "com.avaloq.tools.ddk.xtext";

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(ScopingFragment.class);

  /** The model for the scope resource. */
  private Object model;

  private boolean generateTests;

  /**
   * Sets whether validation tests should be generated - default is not / false.
   * Optional.
   *
   * @param generateTests
   *          the new generate tests
   */
  public void setGenerateTests(final boolean generateTests) {
    this.generateTests = generateTests;
  }

  /** {@inheritDoc} */
  @Override
  public void generate(final Grammar grammar, final XpandExecutionContext ctx) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info(NLS.bind("executing generate for {0}", getClass().getName()));
    }
    ScopeStandaloneSetup.doSetup();

    try {
      model = ScopingGeneratorUtil.getScopeModel(grammar, ctx);
    } catch (final FileNotFoundException e) {
      if (LOGGER.isInfoEnabled()) {
        LOGGER.info(NLS.bind("  No scope file found for grammar {0}.", grammar.getName()));
      }
    }

    super.generate(grammar, ctx);

    if (generateTests) {
      XpandFacade.create(ctx).evaluate("com::avaloq::tools::ddk::xtext::scope::generator::ScopeProviderTests::generate", // template
      grammar, // this (the grammar)
      model); // model
      XpandFacade.create(ctx).evaluate("com::avaloq::tools::ddk::xtext::scope::generator::LinkingTests::generate", // template
      grammar); // this (the grammar)
    }
  }

  /** {@inheritDoc} */
  @Override
  public Set<Binding> getGuiceBindingsRt(final Grammar grammar) {
    final BindFactory bindFactory = new BindFactory();
    final String prefix = GrammarUtil.getNamespace(grammar) + ".scoping." + GrammarUtil.getName(grammar);

    bindFactory.addTypeToType(IScopeProvider.class.getName(), prefix + "ScopeProvider");
    bindFactory.addTypeToType(IScopeNameProvider.class.getName(), prefix + "ScopeNameProvider");
    bindFactory.addTypeToType(ILinkingService.class.getName(), "com.avaloq.tools.ddk.xtext.linking.LinkingService");

    return bindFactory.getBindings();
  }

  /** {@inheritDoc} */
  @Override
  public String[] getRequiredBundlesRt(final Grammar grammar) {
    return new String[] {"org.eclipse.emf.ecore", RUNTIME_PLUGIN};
  }

  /** {@inheritDoc} */
  @Override
  public String[] getRequiredBundlesUi(final Grammar grammar) {
    return new String[] {RUNTIME_PLUGIN};
  }

  /** {@inheritDoc} */
  @Override
  public String[] getExportedPackagesRt(final Grammar grammar) {
    return new String[] {GrammarUtil.getNamespace(grammar) + ".scoping"};
  }

  /** {@inheritDoc} */
  @Override
  protected List<Object> getParameters(final Grammar grammar) {
    return Lists.newArrayList(model);
  }

}
