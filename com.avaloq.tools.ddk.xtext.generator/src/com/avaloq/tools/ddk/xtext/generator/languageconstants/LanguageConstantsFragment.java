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
package com.avaloq.tools.ddk.xtext.generator.languageconstants;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xpand2.output.Outlet;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.generator.AbstractGeneratorFragment;
import org.eclipse.xtext.generator.Naming;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;


/**
 * Fragment for configuring language specific constants.
 */
public class LanguageConstantsFragment extends AbstractGeneratorFragment {

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(LanguageConstantsFragment.class);

  /** Variables / Fragment Parameters. */
  private List<String> fileExtensions;
  private String preferredFileExtension;

  /** The Constant METAMODEL_SRC_GEN_OUTLET is the name of the outlet for generated metamodel artefacts. */
  public static final String METAMODEL_SRC_GEN_OUTLET = "METAMODEL_SRC_GEN"; //$NON-NLS-1$
  private static final String ISO_8859_1 = "ISO-8859-1"; //$NON-NLS-1$

  /** The corresponding outlet. */
  private Outlet metamodelSrcGenOulet;

  /** {@inheritDoc} */
  @Override
  public void generate(final Grammar grammar, final XpandExecutionContext ctx) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info(NLS.bind("executing generate for {0}", getClass().getName())); //$NON-NLS-1$
    }
    addMetamodelSrcGenOutlet(grammar, ctx);
    super.generate(grammar, ctx);
  }

  /**
   * Returns the qualified name of the Constants class.
   *
   * @param grammar
   *          the grammar
   * @param prefix
   *          the prefix (e.g. "Abstract", "Default", ...
   * @param naming
   *          the naming
   * @return the qualified name
   */
  public static String getQualifiedName(final Grammar grammar, final String prefix, final Naming naming) {
    return naming.basePackageRuntime(grammar) + "." + prefix + GrammarUtil.getSimpleName(grammar) + "Constants"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Override
  protected List<Object> getParameters(final Grammar grammar) {
    return Lists.newArrayList(getPreferredFileExtension(grammar), getFileExtensions(grammar));
  }

  /**
   * Manually manages a list of extensions.
   *
   * @param fileExtensions
   *          a comma-separated list of extensions
   */

  public void setFileExtensions(final String fileExtensions) {
    if ("".equals(fileExtensions.trim())) { //$NON-NLS-1$
      return;
    }
    this.fileExtensions = new ArrayList<String>();
    String[] split = fileExtensions.split("\\s*,\\s*"); //$NON-NLS-1$
    for (String string : split) {
      this.fileExtensions.add(string);
    }
  }

  /**
   * Returns the list of file extensions. If no value was manually set,
   * looks in the GenModel.
   *
   * @param grammar
   *          the grammar for which the are defined
   * @return a list of file extensions
   */
  public List<String> getFileExtensions(final Grammar grammar) {
    if (fileExtensions != null && !fileExtensions.isEmpty()) {
      return fileExtensions;
    }
    return ImmutableList.of();
  }

  /**
   * An alternative implementation is to use
   * com.avaloq.tools.ddk.xtext.generator.util.GeneratorUtil.getFileExtensions(org.eclipse.xtext.Grammar).
   * However, we want to use the preferred file extension.
   *
   * @param fileExtension
   *          the preferred file extension
   */
  public void setPreferedFileExtension(final String fileExtension) {
    this.preferredFileExtension = fileExtension;
  }

  /**
   * Returns the preferred file extension. If not manually set, the
   * first item in {@link fileExtensions} is returned.
   *
   * @param grammar
   *          the grammar for which the preferred file extension applies
   * @return the preferred file extension
   */
  public String getPreferredFileExtension(final Grammar grammar) {
    if (preferredFileExtension != null) {
      return preferredFileExtension;
    } else if (!getFileExtensions(grammar).isEmpty()) {
      return getFileExtensions(grammar).get(0);
    } else {
      return GrammarUtil.getSimpleName(grammar).toLowerCase();
    }
  }

  /**
   * Used to set outlet SRC_TEST or SRC_TEST_GEN - all other outlets are ignored.
   *
   * @param outlet
   *          the outlet
   */
  public void addOutlet(final Outlet outlet) {
    if (METAMODEL_SRC_GEN_OUTLET.equals(outlet.getName())) {
      metamodelSrcGenOulet = outlet;
    }
  }

  /**
   * Ensures that METAMODEL_SRC_GEN_OUTLET outlet is defined for the execution context.
   * Unless specific outlets were specified for this fragment default outlets are created.
   *
   * @param grammar
   *          the grammar
   * @param ctx
   *          the Xpand execution context
   */
  protected void addMetamodelSrcGenOutlet(final Grammar grammar, final XpandExecutionContext ctx) {
    Outlet outlet = metamodelSrcGenOulet;
    if (outlet == null) {
      final String path = "../" + getNaming().basePackageRuntime(grammar) + "/src-gen"; //$NON-NLS-1$//$NON-NLS-2$
      outlet = new Outlet(false, ISO_8859_1, METAMODEL_SRC_GEN_OUTLET, true, path);
    }
    ctx.getOutput().addOutlet(outlet);
  }

  /**
   * Utility function to join a list of strings with a given separator.
   * <p>
   * Note: Google Collections's Joiner is unfortunately difficult to use directly from Xtend.
   *
   * @param separator
   *          separator
   * @param elements
   *          elements to join
   * @return joined list of elements
   */
  public static String join(final String separator, final List<String> elements) {
    return Joiner.on(separator).join(elements);
  }

}
