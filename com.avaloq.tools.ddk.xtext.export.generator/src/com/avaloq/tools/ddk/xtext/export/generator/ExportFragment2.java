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

package com.avaloq.tools.ddk.xtext.export.generator;

import com.avaloq.tools.ddk.xtext.export.ExportStandaloneSetup;
import com.avaloq.tools.ddk.xtext.resource.IFingerprintComputer;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IFragmentProvider;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;

public class ExportFragment2 extends AbstractXtextGeneratorFragment {

  @Inject
  private XtextGeneratorNaming xtextGeneratorNaming;

  private boolean hasExports = true;

  /**
   * Class-wide logger.
   */
  private static final Logger LOGGER = LogManager.getLogger(ExportFragment2.class);

  private static final String DDK_XTEXT_RUNTIME_BUNDLE = "com.avaloq.tools.ddk.xtext";

  public void setHasExports(final boolean hasExports) {
    this.hasExports = hasExports;
  }

  @Override
  public void generate() {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("executing generate for " + getClass().getName());
    }
    final String namingPackage = xtextGeneratorNaming.getRuntimeBasePackage(getGrammar()) + ".naming";
    final String namingPrefix = namingPackage + "." + GrammarUtil.getSimpleName(getGrammar());
    final String resourcePackage = xtextGeneratorNaming.getRuntimeBasePackage(getGrammar()) + ".resource";
    final String resourcePrefix = resourcePackage + "." + GrammarUtil.getSimpleName(getGrammar());

    if (hasExports) {
      new GuiceModuleAccess.BindingFactory()
        .addTypeToType(TypeReference.typeRef(IQualifiedNameProvider.class), new TypeReference(namingPrefix + "ExportedNamesProvider"))
        .addTypeToType(TypeReference.typeRef(IFingerprintComputer.class), new TypeReference(resourcePrefix + "FingerprintComputer"))
        .addTypeToType(TypeReference.typeRef(IDefaultResourceDescriptionStrategy.class), new TypeReference(resourcePrefix + "ResourceDescriptionStrategy"))
        .addTypeToType(TypeReference.typeRef(IFragmentProvider.class), new TypeReference(resourcePrefix + "FragmentProvider"))
        .addTypeToType(TypeReference.typeRef(IResourceDescription.Manager.class), new TypeReference(resourcePrefix + "ResourceDescriptionManager"))
        .contributeTo(getLanguage().getRuntimeGenModule());
    } else {
      new GuiceModuleAccess.BindingFactory()
        .addTypeToType(TypeReference.typeRef(IResourceDescription.Manager.class), new TypeReference(resourcePrefix + "ResourceDescriptionManager"))
        .contributeTo(getLanguage().getRuntimeGenModule());
    }

    if (getProjectConfig().getRuntime().getManifest() != null) {
      getProjectConfig().getRuntime().getManifest().getRequiredBundles().add("org.eclipse.emf.ecore");
      getProjectConfig().getRuntime().getManifest().getRequiredBundles().add(DDK_XTEXT_RUNTIME_BUNDLE);
      if (hasExports) {
        getProjectConfig().getRuntime().getManifest().getExportedPackages().add(namingPackage);
      }

      getProjectConfig().getRuntime().getManifest().getExportedPackages().add(resourcePackage);
    }
    if (getProjectConfig().getEclipsePlugin().getManifest() != null) {
      getProjectConfig().getEclipsePlugin().getManifest().getRequiredBundles().add(DDK_XTEXT_RUNTIME_BUNDLE);
    }

    ExportStandaloneSetup.doSetup();
  }
}
