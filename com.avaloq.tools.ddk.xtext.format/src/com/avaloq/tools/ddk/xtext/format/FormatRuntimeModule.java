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
package com.avaloq.tools.ddk.xtext.format;

import com.avaloq.tools.ddk.xtext.format.conversion.FormatValueConverterService;
import com.avaloq.tools.ddk.xtext.format.generator.FormatOutputConfigurationProvider;
import com.avaloq.tools.ddk.xtext.format.naming.FormatQualifiedNameConverter;
import com.avaloq.tools.ddk.xtext.format.naming.FormatQualifiedNameProvider;
import com.avaloq.tools.ddk.xtext.format.resource.FormatResource;
import com.avaloq.tools.ddk.xtext.format.resource.FormatResourceDescriptionStrategy;
import com.avaloq.tools.ddk.xtext.format.scoping.FormatLinkingService;
import com.avaloq.tools.ddk.xtext.format.scoping.FormatScopeProvider;
import com.google.inject.Binder;
import com.google.inject.name.Names;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.linking.ILinkingService;
import org.eclipse.xtext.linking.LinkingScopeProviderBinding;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.containers.IAllContainersState;
import org.eclipse.xtext.resource.containers.ResourceSetBasedAllContainersStateProvider;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;
import org.eclipse.xtext.xbase.scoping.XImportSectionNamespaceScopeProvider;
import org.eclipse.xtext.xtext.generator.model.project.IXtextProjectConfig;
import org.eclipse.xtext.xtext.generator.model.project.XtextProjectConfig;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class FormatRuntimeModule extends AbstractFormatRuntimeModule {

  @Override
  public Class<? extends XtextResource> bindXtextResource() {
    return FormatResource.class;
  }

  /**
   * Binds a class loader required by the resource manager.
   *
   * @return bound class loader instance
   */
  @Override
  public ClassLoader bindClassLoaderToInstance() {
    return getClass().getClassLoader();
  }

  @Override
  public Class<? extends IValueConverterService> bindIValueConverterService() {
    return FormatValueConverterService.class;
  }

  /**
   * Binds custom qualified name converter.
   *
   * @return the implementation
   */
  @Override
  public Class<? extends IQualifiedNameConverter> bindIQualifiedNameConverter() {
    return FormatQualifiedNameConverter.class;
  }

  /** {@inheritDoc} */
  @Override
  public Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
    return FormatQualifiedNameProvider.class;
  }

  /** {@inheritDoc} */
  @Override
  public Class<? extends IScopeProvider> bindIScopeProvider() {
    return FormatScopeProvider.class;
  }

  /** {@inheritDoc} */
  @Override
  public void configureLinkingIScopeProvider(final Binder binder) {
    binder.bind(IScopeProvider.class).annotatedWith(LinkingScopeProviderBinding.class).to(FormatScopeProvider.class);
  }

  /** {@inheritDoc} */
  @Override
  // CHECKSTYLE:OFF
  public Class<? extends IAllContainersState.Provider> bindIAllContainersState$Provider() {
    // CHECKSTYLE:ON
    return ResourceSetBasedAllContainersStateProvider.class;
  }

  /** {@inheritDoc} */
  @Override
  public Class<? extends ILinkingService> bindILinkingService() {
    return FormatLinkingService.class;
  }

  /** {@inheritDoc} */
  @Override
  public Class<? extends IDefaultResourceDescriptionStrategy> bindIDefaultResourceDescriptionStrategy() {
    return FormatResourceDescriptionStrategy.class;
  }

  /**
   * Binds a custom output configuration provider.
   *
   * @return the format output configuration provider
   */
  public Class<? extends IOutputConfigurationProvider> bindIOutputConfigurationProvider() {
    return FormatOutputConfigurationProvider.class;
  }

  /** {@inheritDoc} */
  @Override
  public void configureIScopeProviderDelegate(final Binder binder) {
    binder.bind(IScopeProvider.class).annotatedWith(Names.named(AbstractDeclarativeScopeProvider.NAMED_DELEGATE)).to(XImportSectionNamespaceScopeProvider.class);
  }

  @Override
  public void configure(final Binder binder) {
    super.configure(binder);
    binder.bind(IXtextProjectConfig.class).to(XtextProjectConfig.class);
  }
}
