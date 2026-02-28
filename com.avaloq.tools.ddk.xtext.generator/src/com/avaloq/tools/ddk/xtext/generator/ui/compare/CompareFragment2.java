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

public class CompareFragment2 extends ResourceFactoryFragment2 {

  @Inject
  private XtextGeneratorNaming _xtextGeneratorNaming;

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

  public CharSequence eclipsePluginXmlContribution() {
    final TypeReference executableExtensionFactory = _xtextGeneratorNaming.getEclipsePluginExecutableExtensionFactory(getGrammar());
    final String fileExtensions = String.join(",", getLanguage().getFileExtensions());
    final StringBuilder builder = new StringBuilder();
    builder.append("<!-- Contributed by ").append(TypeReference.typeRef(CompareFragment2.class)).append(" -->\n");
    builder.append("<extension point=\"org.eclipse.compare.contentViewers\">\n");
    builder.append("  <viewer id=\"").append(getGrammar().getName()).append(".compare.contentViewers\"\n");
    builder.append("          class=\"").append(executableExtensionFactory).append(":org.eclipse.xtext.ui.compare.InjectableViewerCreator\"\n");
    builder.append("          extensions=\"").append(fileExtensions).append("\" label=\"").append(GrammarUtil.getSimpleName(getGrammar())).append(" Compare\">\n");
    builder.append("  </viewer>\n");
    builder.append("</extension>\n");
    builder.append("<extension point=\"org.eclipse.compare.contentMergeViewers\">\n");
    builder.append("  <viewer id=\"").append(getGrammar().getName()).append(".compare.contentMergeViewers\"\n");
    builder.append("          class=\"").append(executableExtensionFactory).append(":org.eclipse.xtext.ui.compare.InjectableViewerCreator\"\n");
    builder.append("          extensions=\"").append(fileExtensions).append("\" label=\"").append(GrammarUtil.getSimpleName(getGrammar())).append(" Compare\">\n");
    builder.append("   </viewer>\n");
    builder.append("</extension>\n");
    builder.append("<extension point=\"org.eclipse.ui.editors.documentProviders\">\n");
    builder.append("  <provider id=\"").append(getGrammar().getName()).append(".editors.documentProviders\"\n");
    builder.append("          class=\"").append(executableExtensionFactory).append(":com.avaloq.tools.ddk.xtext.ui.editor.model.ResponsiveXtextDocumentProvider\"\n");
    builder.append("          extensions=\"").append(fileExtensions).append("\">\n");
    builder.append("  </provider>\n");
    builder.append("</extension>\n");
    return builder;
  }
}
