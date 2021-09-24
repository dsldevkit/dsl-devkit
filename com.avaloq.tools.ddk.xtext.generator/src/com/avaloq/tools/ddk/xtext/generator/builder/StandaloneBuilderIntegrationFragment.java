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
package com.avaloq.tools.ddk.xtext.generator.builder;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.generator.AbstractGeneratorFragment;

import com.google.common.collect.Lists;


/**
 * Fragment to properly include the DSL DevKit clustering builder support in generated languages.
 */
@SuppressWarnings({"nls", "deprecation"})
public class StandaloneBuilderIntegrationFragment extends AbstractGeneratorFragment {

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(StandaloneBuilderIntegrationFragment.class);

  private final List<String> fileExtensions = Lists.newArrayList();

  /**
   * Adds file extensions for which to generate standalone builder extensions.
   *
   * @param extensions
   *          extensions to add
   */
  public void setFileExtensions(final String extensions) {
    if ("".equals(extensions.trim())) {
      return;
    }
    String[] ext = extensions.split("\\s*,\\s*");
    for (String string : ext) {
      this.fileExtensions.add(string);
    }
  }

  @Override
  protected List<Object> getParameters(final Grammar g) {
    return Collections.singletonList((Object) fileExtensions);
  }

  @Override
  public void generate(final Grammar grammar, final XpandExecutionContext ctx) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info(NLS.bind("executing generate for {0}", getClass().getName()));
    }
    super.generate(grammar, ctx);
    // generateStandaloneBuilderPluginXml(grammar, ctx);
  }

  /*
   * TODO consider enabling for OSGi based standalone builder.
   * <p>
   * private boolean generateStandaloneBuilderPluginXml;
   * private static boolean standaloneBuilderPluginXmlDeleted;
   * public void setGenerateStandaloneBuilderPluginXml(final boolean value) {
   * generateStandaloneBuilderPluginXml = value;
   * }
   * public void generateStandaloneBuilderPluginXml(final Grammar grammar, final XpandExecutionContext ctx) {
   * if (generateStandaloneBuilderPluginXml) {
   * if (!standaloneBuilderPluginXmlDeleted) {
   * new File("../com.avaloq.tools.ddk.abs.modelcache.builder/plugin.xml_gen").delete();
   * standaloneBuilderPluginXmlDeleted = true;
   * }
   * final Outlet outlet = ctx.getOutput().getOutlet("STANDALONE_BUILDER");
   * if (outlet == null) {
   * ctx.getOutput().addOutlet(new Outlet(true, ctx.getOutput().getOutlet(Generator.PLUGIN_RT).getFileEncoding(), "STANDALONE_BUILDER", true,
   * "../com.avaloq.tools.ddk.abs.modelcache.builder/"));
   * }
   * XpandFacade.create(ctx).evaluate2(getTemplate() + "::addToStandaloneBuilderPluginXml", grammar, getParameters(grammar));
   * }
   * }
   */

}
