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
package com.avaloq.tools.ddk.xtext.format.generator;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.formatting.IFormatter;
import org.eclipse.xtext.formatting.INodeModelFormatter;
import org.eclipse.xtext.formatting.INodeModelStreamer;
import org.eclipse.xtext.generator.AbstractGeneratorFragment;
import org.eclipse.xtext.generator.BindFactory;
import org.eclipse.xtext.generator.Binding;

import com.avaloq.tools.ddk.xtext.format.FormatStandaloneSetup;
import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.formatting.AbstractExtendedFormatter;
import com.avaloq.tools.ddk.xtext.formatting.DirectNodeModelStreamer;
import com.avaloq.tools.ddk.xtext.formatting.RegionNodeModelFormatter;
import com.google.common.collect.Lists;


/**
 * MWE fragment for the format language.
 */
@SuppressWarnings("nls")
public class FormatFragment extends AbstractGeneratorFragment {

  private static final String RUNTIME_PLUGIN = "com.avaloq.tools.ddk.xtext";

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(FormatFragment.class);

  /** The model for the format resource. */
  private FormatConfiguration model;

  private String baseFormatterClassName = AbstractExtendedFormatter.class.getName();

  /**
   * Set the super type / base class of the formatter.
   *
   * @param baseFormatterClass
   *          the FQN of the base formatter
   */
  public void setBaseFormatterClassName(final String baseFormatterClass) {
    this.baseFormatterClassName = baseFormatterClass;
  }

  /** {@inheritDoc} */
  @Override
  public void generate(final Grammar grammar, final XpandExecutionContext ctx) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("executing generate for " + getClass().getName());
    }

    try {
      model = FormatGeneratorUtil.getFormatModel(grammar, ctx);
    } catch (final FileNotFoundException e) {
      if (LOGGER.isInfoEnabled()) {
        LOGGER.info("  No format file found for grammar " + grammar.getName());
      }
    }

    if (model != null) {
      FormatStandaloneSetup.doSetup();
      super.generate(grammar, ctx);
    }
  }

  /** {@inheritDoc} */
  @Override
  public Set<Binding> getGuiceBindingsRt(final Grammar grammar) {
    final BindFactory bindFactory = new BindFactory();
    bindFactory.addTypeToType(IFormatter.class.getName(), FormatGeneratorUtil.getFormatterName(grammar, ""));
    bindFactory.addTypeToType(INodeModelFormatter.class.getName(), RegionNodeModelFormatter.class.getName());
    bindFactory.addTypeToType(INodeModelStreamer.class.getName(), DirectNodeModelStreamer.class.getName());
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
  protected List<Object> getParameters(final Grammar grammar) {
    return Lists.newArrayList(model, baseFormatterClassName);
  }
}
