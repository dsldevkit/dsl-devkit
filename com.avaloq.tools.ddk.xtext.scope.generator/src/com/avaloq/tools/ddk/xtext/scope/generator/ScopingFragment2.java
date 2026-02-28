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

package com.avaloq.tools.ddk.xtext.scope.generator;

import com.avaloq.tools.ddk.xtext.linking.LinkingService;
import com.avaloq.tools.ddk.xtext.scoping.IScopeNameProvider;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.linking.ILinkingService;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;

public class ScopingFragment2 extends AbstractXtextGeneratorFragment {

  private static final String RUNTIME_PLUGIN = "com.avaloq.tools.ddk.xtext";

  @Override
  public void generate() {
    final String prefix = GrammarUtil.getNamespace(getGrammar()) + ".scoping." + GrammarUtil.getSimpleName(getGrammar());
    new GuiceModuleAccess.BindingFactory()
      .addTypeToType(TypeReference.typeRef(IScopeProvider.class), new TypeReference(prefix + "ScopeProvider"))
      .addTypeToType(TypeReference.typeRef(IScopeNameProvider.class), new TypeReference(prefix + "ScopeNameProvider"))
      .addTypeToType(TypeReference.typeRef(ILinkingService.class), TypeReference.typeRef(LinkingService.class))
      .contributeTo(getLanguage().getRuntimeGenModule());

    if (getProjectConfig().getRuntime().getManifest() != null) {
      getProjectConfig().getRuntime().getManifest().getRequiredBundles().add("org.eclipse.emf.ecore");
      getProjectConfig().getRuntime().getManifest().getRequiredBundles().add(RUNTIME_PLUGIN);
      getProjectConfig().getRuntime().getManifest().getExportedPackages().add(GrammarUtil.getNamespace(getGrammar()) + ".scoping");
      getProjectConfig().getRuntime().getManifest().getImportedPackages().add("org.apache.logging.log4j");
    }

    if (getProjectConfig().getEclipsePlugin().getManifest() != null) {
      getProjectConfig().getRuntime().getManifest().getRequiredBundles().add(RUNTIME_PLUGIN);
    }
  }
}
