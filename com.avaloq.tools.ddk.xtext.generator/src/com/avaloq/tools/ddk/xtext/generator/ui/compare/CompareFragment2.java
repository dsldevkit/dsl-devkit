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

import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.compare.IViewerCreator;
import org.eclipse.emf.mwe2.runtime.Mandatory;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtext.xtext.generator.resourceFactory.ResourceFactoryFragment2;

@SuppressWarnings("nls")
public class CompareFragment2 extends ResourceFactoryFragment2 {

  @Inject
  private XtextGeneratorNaming xtextGeneratorNaming;

  private static final Logger LOGGER = LogManager.getLogger(CompareFragment2.class);

  private String viewCreator;
  private String bundleName;

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

  @Override
  public void generate() {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("generating Compare Framework infrastructure");
    }

    new GuiceModuleAccess.BindingFactory()
        .addTypeToType(TypeReference.typeRef(IViewerCreator.class), new TypeReference(viewCreator))
        .contributeTo(getLanguage().getEclipsePluginGenModule());
    if (getProjectConfig().getEclipsePlugin().getManifest() != null) {
      getProjectConfig().getEclipsePlugin().getManifest().getRequiredBundles().add(bundleName);
    }
    if (getProjectConfig().getEclipsePlugin().getPluginXml().getEntries() != null) {
      getProjectConfig().getEclipsePlugin().getPluginXml().getEntries().add(eclipsePluginXmlContribution());
    }
  }

  // CHECKSTYLE:CONSTANTS-OFF
  public CharSequence eclipsePluginXmlContribution() {
    final TypeReference executableExtensionFactory = xtextGeneratorNaming.getEclipsePluginExecutableExtensionFactory(getGrammar());
    final String grammarName = getGrammar().getName();
    final String fileExtensions = String.join(",", getLanguage().getFileExtensions());
    final String simpleName = GrammarUtil.getSimpleName(getGrammar());
    return String.format("""
        <!-- Contributed by %1$s -->
        <extension point="org.eclipse.compare.contentViewers">
          <viewer id="%2$s.compare.contentViewers"
                  class="%3$s:org.eclipse.xtext.ui.compare.InjectableViewerCreator"
                  extensions="%4$s" label="%5$s Compare">
          </viewer>
        </extension>
        <extension point="org.eclipse.compare.contentMergeViewers">
          <viewer id="%2$s.compare.contentMergeViewers"
                  class="%3$s:org.eclipse.xtext.ui.compare.InjectableViewerCreator"
                  extensions="%4$s" label="%5$s Compare">
           </viewer>
        </extension>
        <extension point="org.eclipse.ui.editors.documentProviders">
          <provider id="%2$s.editors.documentProviders"
                  class="%3$s:com.avaloq.tools.ddk.xtext.ui.editor.model.ResponsiveXtextDocumentProvider"
                  extensions="%4$s">
          </provider>
        </extension>
        """, TypeReference.typeRef(CompareFragment2.class), grammarName, executableExtensionFactory, fileExtensions, simpleName);
  }
  // CHECKSTYLE:CONSTANTS-ON
}
