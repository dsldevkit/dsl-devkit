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

package com.avaloq.tools.ddk.xtext.generator.ui.compare

import com.google.inject.Inject
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager;
import org.eclipse.compare.IViewerCreator
import org.eclipse.emf.mwe2.runtime.Mandatory
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess
import org.eclipse.xtext.xtext.generator.model.TypeReference
import org.eclipse.xtext.xtext.generator.resourceFactory.ResourceFactoryFragment2

import static extension org.eclipse.xtext.GrammarUtil.*
import static extension org.eclipse.xtext.xtext.generator.model.TypeReference.*

class CompareFragment2 extends ResourceFactoryFragment2 {

  @Inject
  extension XtextGeneratorNaming

  val static Logger LOGGER = LogManager.getLogger(CompareFragment2);

  var String viewCreator;
  var String bundleName;

  /**
   * Sets the view creator.
   *
   * @param viewCreator
   *          the new view creator
   */
  @Mandatory
  def void setViewCreator(String viewCreator) {
    this.viewCreator = viewCreator;
  }

  /**
   * Sets bundle where the view creator is located.
   *
   * @param bundleName
   *          the new bundle name
   */
  @Mandatory
  def void setBundleName(String bundleName) {
    this.bundleName = bundleName;
  }

  override void generate() {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("generating Compare Framework infrastructure");
    }

    new GuiceModuleAccess.BindingFactory()
    .addTypeToType(IViewerCreator.typeRef, new TypeReference(viewCreator))
    .contributeTo(language.eclipsePluginGenModule)
    if (projectConfig.eclipsePlugin.manifest !== null) {
      projectConfig.eclipsePlugin.manifest.requiredBundles += bundleName
    }
    if (projectConfig.eclipsePlugin.pluginXml.entries !== null) {
      projectConfig.eclipsePlugin.pluginXml.entries += eclipsePluginXmlContribution()
    }
  }

  def eclipsePluginXmlContribution() {
    val executableExtensionFactory = grammar.eclipsePluginExecutableExtensionFactory
    '''
    <!-- Contributed by «CompareFragment2.typeRef» -->
    <extension point="org.eclipse.compare.contentViewers">
      <viewer id="«grammar.name».compare.contentViewers"
              class="«executableExtensionFactory»:org.eclipse.xtext.ui.compare.InjectableViewerCreator"
              extensions="«language.fileExtensions.join(',')»" label="«grammar.simpleName» Compare">
      </viewer>
    </extension>
    <extension point="org.eclipse.compare.contentMergeViewers">
      <viewer id="«grammar.name».compare.contentMergeViewers"
              class="«executableExtensionFactory»:org.eclipse.xtext.ui.compare.InjectableViewerCreator"
              extensions="«language.fileExtensions.join(',')»" label="«grammar.simpleName» Compare">
       </viewer>
    </extension>
    <extension point="org.eclipse.ui.editors.documentProviders">
      <provider id="«grammar.name».editors.documentProviders"
              class="«executableExtensionFactory»:com.avaloq.tools.ddk.xtext.ui.editor.model.ResponsiveXtextDocumentProvider"
              extensions="«language.fileExtensions.join(',')»">
      </provider>
    </extension>
   '''
  }
}
