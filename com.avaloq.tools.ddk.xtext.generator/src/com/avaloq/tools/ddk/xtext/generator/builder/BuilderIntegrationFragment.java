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

import java.util.List;
import java.util.Set;

import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.generator.BindFactory;
import org.eclipse.xtext.generator.Binding;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.linking.lazy.LazyURIEncoder;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;

import com.avaloq.tools.ddk.xtext.linking.FastLazyURIEncoder;
import com.avaloq.tools.ddk.xtext.linking.LazyLinkingResource2;
import com.google.common.collect.Lists;


/**
 * Fragment to properly include the DSL DevKit clustering builder support in generated languages.
 */
@SuppressWarnings({"nls", "deprecation"})
public class BuilderIntegrationFragment extends org.eclipse.xtext.generator.builder.BuilderIntegrationFragment {

  private static final String BUILDER_BUNDLE_NAME = "com.avaloq.tools.ddk.xtext.builder";

  @Override
  public Set<Binding> getGuiceBindingsRt(final Grammar grammar) {
    final Set<Binding> bindings = super.getGuiceBindingsRt(grammar);
    final BindFactory factory = new BindFactory();
    factory.addTypeToType(IContainer.Manager.class.getName(), "com.avaloq.tools.ddk.xtext.builder.CachingStateBasedContainerManager");
    factory.addTypeToType(LazyLinkingResource.class.getName(), LazyLinkingResource2.class.getName());
    factory.addTypeToType(LazyURIEncoder.class.getName(), FastLazyURIEncoder.class.getName());
    final Set<Binding> result = factory.getBindings();
    result.addAll(bindings);
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public String[] getRequiredBundlesRt(final Grammar grammar) {
    final String[] bundles = super.getRequiredBundlesRt(grammar);
    if (bundles != null && bundles.length > 0) {
      final List<String> allBundles = Lists.newArrayList(bundles);
      allBundles.add(BUILDER_BUNDLE_NAME);
      return allBundles.toArray(new String[bundles.length + 1]);
    }
    return new String[] {BUILDER_BUNDLE_NAME};
  }

  /** {@inheritDoc} */
  @Override
  public Set<Binding> getGuiceBindingsUi(final Grammar grammar) {
    final Set<Binding> bindings = super.getGuiceBindingsUi(grammar);
    final BindFactory factory = new BindFactory();
    factory.addConfiguredBinding(IResourceDescriptions.class.getName() + "BuilderScope", "binder.bind(" + IResourceDescriptions.class.getName() + ".class"
        + ").annotatedWith(com.google.inject.name.Names.named(" + ResourceDescriptionsProvider.class.getName() + ".NAMED_BUILDER_SCOPE)).to("
        + "com.avaloq.tools.ddk.xtext.builder.CurrentDescriptions2.ResourceSetAware.class)");
    final Set<Binding> result = factory.getBindings();
    result.addAll(bindings);
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public String[] getRequiredBundlesUi(final Grammar grammar) {
    final String[] bundles = super.getRequiredBundlesUi(grammar);
    if (bundles != null && bundles.length > 0) {
      final List<String> allBundles = Lists.newArrayList(bundles);
      allBundles.add(BUILDER_BUNDLE_NAME);
      return allBundles.toArray(new String[bundles.length + 1]);
    }
    return new String[] {BUILDER_BUNDLE_NAME};
  }

}
