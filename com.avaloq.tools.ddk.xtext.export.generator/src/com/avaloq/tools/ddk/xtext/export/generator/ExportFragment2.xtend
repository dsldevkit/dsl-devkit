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

package com.avaloq.tools.ddk.xtext.export.generator

import com.avaloq.tools.ddk.xtext.export.ExportStandaloneSetup
import com.avaloq.tools.ddk.xtext.resource.IFingerprintComputer
import com.google.inject.Inject
import org.apache.log4j.Logger
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy
import org.eclipse.xtext.resource.IFragmentProvider
import org.eclipse.xtext.resource.IResourceDescription
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess
import org.eclipse.xtext.xtext.generator.model.TypeReference

import static org.eclipse.xtext.GrammarUtil.*

import static extension org.eclipse.xtext.xtext.generator.model.TypeReference.*

class ExportFragment2 extends AbstractXtextGeneratorFragment {

  @Inject extension XtextGeneratorNaming

  /**
   * Class-wide logger.
   */
  static final Logger LOGGER = Logger::getLogger(typeof(ExportFragment2))

  val DDK_XTEXT_RUNTIME_BUNDLE = "com.avaloq.tools.ddk.xtext"

  override void generate() {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info('''executing generate for «getClass().getName()»'''.toString)
    }
    val namingPackage = grammar.runtimeBasePackage + '.naming'
    val namingPrefix = namingPackage + '.' + getSimpleName(grammar)
    val resourcePackage = grammar.runtimeBasePackage + '.resource'
    val resourcePrefix = resourcePackage + '.' + getSimpleName(grammar)

    new GuiceModuleAccess.BindingFactory()
    .addTypeToType(IQualifiedNameProvider.typeRef, new TypeReference(namingPrefix + "ExportedNamesProvider"))
    .addTypeToType(IFingerprintComputer.typeRef, new TypeReference(resourcePrefix + "FingerprintComputer"))
    .addTypeToType(IDefaultResourceDescriptionStrategy.typeRef, new TypeReference(resourcePrefix + "ResourceDescriptionStrategy"))
    .addTypeToType(IFragmentProvider.typeRef, new TypeReference(resourcePrefix + "FragmentProvider"))
    .addTypeToType(IResourceDescription.Manager.typeRef, new TypeReference(resourcePrefix + "ResourceDescriptionManager"))
    .contributeTo(language.runtimeGenModule)
    if (projectConfig.runtime.manifest !== null) {
      projectConfig.runtime.manifest.requiredBundles += "org.eclipse.emf.ecore"
      projectConfig.runtime.manifest.requiredBundles += DDK_XTEXT_RUNTIME_BUNDLE
      projectConfig.runtime.manifest.exportedPackages += namingPackage
      projectConfig.runtime.manifest.exportedPackages += resourcePackage
    }
    if (projectConfig.eclipsePlugin.manifest !== null) {
      projectConfig.eclipsePlugin.manifest.requiredBundles += DDK_XTEXT_RUNTIME_BUNDLE
    }

    ExportStandaloneSetup.doSetup()
  }
}
