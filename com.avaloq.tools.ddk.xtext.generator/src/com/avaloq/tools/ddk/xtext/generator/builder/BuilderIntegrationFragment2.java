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

package com.avaloq.tools.ddk.xtext.generator.builder;

import com.avaloq.tools.ddk.xtext.linking.FastLazyURIEncoder;
import com.avaloq.tools.ddk.xtext.linking.LazyLinkingResource2;
import com.google.inject.name.Names;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.linking.lazy.LazyURIEncoder;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtend2.lib.StringConcatenationClient;

public class BuilderIntegrationFragment2 extends org.eclipse.xtext.xtext.generator.builder.BuilderIntegrationFragment2 {

  private static final String BUILDER_BUNDLE_NAME = "com.avaloq.tools.ddk.xtext.builder";

  @Override
  protected void addRuntimeGuiceBindings() {
    super.addRuntimeGuiceBindings();
    new GuiceModuleAccess.BindingFactory()
        .addTypeToType(TypeReference.typeRef(IContainer.Manager.class), new TypeReference("com.avaloq.tools.ddk.xtext.builder.CachingStateBasedContainerManager"))
        .addTypeToType(TypeReference.typeRef(LazyLinkingResource.class), TypeReference.typeRef(LazyLinkingResource2.class))
        .addTypeToType(TypeReference.typeRef(LazyURIEncoder.class), TypeReference.typeRef(FastLazyURIEncoder.class))
        .contributeTo(getLanguage().getRuntimeGenModule());
    if (getProjectConfig().getRuntime().getManifest() != null) {
      getProjectConfig().getRuntime().getManifest().getRequiredBundles().add(BUILDER_BUNDLE_NAME);
    }
  }

  @Override
  protected void addEclipsePluginGuiceBindings() {
    super.addEclipsePluginGuiceBindings();
    final StringConcatenationClient statement1 = new StringConcatenationClient() {
      @Override
      protected void appendTo(TargetStringConcatenation target) {
        target.append("binder.bind(");
        target.append(TypeReference.typeRef(IResourceDescriptions.class));
        target.append(".class");
        target.newLineIfNotEmpty();
        target.append(").annotatedWith(");
        target.append(TypeReference.typeRef(Names.class));
        target.append(".named(");
        target.append(TypeReference.typeRef(ResourceDescriptionsProvider.class));
        target.append(".NAMED_BUILDER_SCOPE)");
        target.newLineIfNotEmpty();
        target.append(").to(");
        target.append(new TypeReference("com.avaloq.tools.ddk.xtext.builder", "CurrentDescriptions2.ResourceSetAware"));
        target.append(".class);");
        target.newLineIfNotEmpty();
      }
    };
    new GuiceModuleAccess.BindingFactory()
        .addConfiguredBinding(IResourceDescriptions.class.getSimpleName() + "BuilderScope", statement1)
        .contributeTo(getLanguage().getEclipsePluginGenModule());
    if (getProjectConfig().getEclipsePlugin().getManifest() != null) {
      getProjectConfig().getEclipsePlugin().getManifest().getRequiredBundles().add(BUILDER_BUNDLE_NAME);
    }
  }

}
