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
package com.avaloq.tools.ddk.xtext.generator.formatting;

import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.formatting.INodeModelFormatter;
import org.eclipse.xtext.formatting.INodeModelStreamer;
import org.eclipse.xtext.generator.BindFactory;
import org.eclipse.xtext.generator.Binding;

import com.avaloq.tools.ddk.xtext.formatting.DirectNodeModelStreamer;
import com.avaloq.tools.ddk.xtext.formatting.RegionNodeModelFormatter;


/**
 * This class overrides {@link FormatterFragment}.
 * Presently the added functionality is to replace the stock NodeModelFormatter with {@link RegionNodeModelFormatter}.
 */
@SuppressWarnings("deprecation")
public class FormatterFragment extends org.eclipse.xtext.generator.formatting.FormatterFragment {

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(FormatterFragment.class);

  @Override
  public Set<Binding> getGuiceBindingsRt(final Grammar grammar) {
    final Set<Binding> bindings = super.getGuiceBindingsRt(grammar);
    BindFactory bindFactory = new BindFactory();
    bindings.addAll(bindFactory.addTypeToType(INodeModelFormatter.class.getName(), RegionNodeModelFormatter.class.getName()).getBindings());
    bindings.addAll(bindFactory.addTypeToType(INodeModelStreamer.class.getName(), DirectNodeModelStreamer.class.getName()).getBindings());
    return bindings;
  }

  @Override
  public void generate(final Grammar grammar, final XpandExecutionContext ctx) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info(NLS.bind("executing generate for {0}", getClass().getName()));
    }
    super.generate(grammar, ctx);
  }

  @Override
  protected String getTemplate() {
    // there is no FormatterFragment.xpt - this will cause FormatterFragment.xpt to be used
    return FormatterFragment.class.getName().replaceAll("\\.", "::"); //$NON-NLS-1$//$NON-NLS-2$
  }
}
