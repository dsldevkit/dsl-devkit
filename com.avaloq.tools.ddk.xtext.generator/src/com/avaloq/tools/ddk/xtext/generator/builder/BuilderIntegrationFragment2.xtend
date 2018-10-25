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

package com.avaloq.tools.ddk.xtext.generator.builder

import com.google.inject.name.Names
import org.eclipse.xtext.resource.IResourceDescriptions
import com.avaloq.tools.ddk.xtext.linking.FastLazyURIEncoder
import org.eclipse.xtext.linking.lazy.LazyURIEncoder
import org.eclipse.xtext.linking.lazy.LazyLinkingResource
import org.eclipse.xtext.resource.IContainer
import com.avaloq.tools.ddk.xtext.linking.LazyLinkingResource2
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess

import static extension org.eclipse.xtext.xtext.generator.model.TypeReference.*
import org.eclipse.xtext.xtext.generator.model.TypeReference
import org.eclipse.xtend2.lib.StringConcatenationClient

class BuilderIntegrationFragment2 extends org.eclipse.xtext.xtext.generator.builder.BuilderIntegrationFragment2 {

  static val BUILDER_BUNDLE_NAME = "com.avaloq.tools.ddk.xtext.builder";

  override addRuntimeGuiceBindings() {
    super.addRuntimeGuiceBindings
    new GuiceModuleAccess.BindingFactory()
      .addTypeToType(IContainer.Manager.typeRef, new TypeReference("com.avaloq.tools.ddk.xtext.builder.CachingStateBasedContainerManager"))
      .addTypeToType(LazyLinkingResource.typeRef, LazyLinkingResource2.typeRef)
      .addTypeToType(LazyURIEncoder.typeRef, FastLazyURIEncoder.typeRef)
      .contributeTo(language.runtimeGenModule)
    if (projectConfig.runtime.manifest !== null) {
      projectConfig.runtime.manifest.requiredBundles += BUILDER_BUNDLE_NAME
    }
  }

  override addEclipsePluginGuiceBindings() {
    super.addEclipsePluginGuiceBindings
    val StringConcatenationClient statement1 =
      '''
      binder.bind(«IResourceDescriptions».class
      ).annotatedWith(«Names».named(«ResourceDescriptionsProvider».NAMED_BUILDER_SCOPE)
      ).to(«new TypeReference('com.avaloq.tools.ddk.xtext.builder', 'CurrentDescriptions2.ResourceSetAware')».class);
      '''
    new GuiceModuleAccess.BindingFactory()
    .addConfiguredBinding(IResourceDescriptions.simpleName + 'BuilderScope', statement1)
    .contributeTo(language.eclipsePluginGenModule)
    if (projectConfig.eclipsePlugin.manifest !== null) {
      projectConfig.eclipsePlugin.manifest.requiredBundles += BUILDER_BUNDLE_NAME
    }
  }

}
