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
package com.avaloq.tools.ddk.xtext.generator.ui.compare;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.mwe2.runtime.Mandatory;
import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.generator.BindFactory;
import org.eclipse.xtext.generator.Binding;
import org.eclipse.xtext.generator.resourceFactory.ResourceFactoryFragment;


/**
 * This fragment has the same interface as the Xtext 2.0 CompareFragment. It will make the transition smoother.
 */
@SuppressWarnings({"deprecation", "removal"})
public class CompareFragment extends ResourceFactoryFragment {
  private static final Logger LOGGER = LogManager.getLogger(CompareFragment.class);

  private String viewCreator;
  private String bundleName;

  @Override
  public void generate(final Grammar grammar, final XpandExecutionContext ctx) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("generating Compare Framework infrastructure");
    }
    super.generate(grammar, ctx);
  }

  @Override
  public Set<Binding> getGuiceBindingsUi(final Grammar grammar) {
    return new BindFactory().addTypeToType("org.eclipse.compare.IViewerCreator", viewCreator).getBindings();
  }

  @Override
  public String[] getRequiredBundlesUi(final Grammar grammar) {
    return new String[] {bundleName};
  }

  @Override
  @Mandatory
  public void setFileExtensions(final String fileExtensions) {
    super.setFileExtensions(fileExtensions);
  }

  /**
   * Sets the view creator.
   *
   * @param viewCreator
   *          the new view creator
   */
  @Mandatory
  public void setViewCreator(final String viewCreator) {
    this.viewCreator = viewCreator;
  }

  /**
   * Sets bundle where the view creator is located.
   *
   * @param bundleName
   *          the new bundle name
   */
  @Mandatory
  public void setBundleName(final String bundleName) {
    this.bundleName = bundleName;
  }

}
